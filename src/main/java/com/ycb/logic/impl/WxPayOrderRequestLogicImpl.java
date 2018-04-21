package com.ycb.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.xbase.frame.util.DateUtils;
import cn.com.xbase.frame.util.HttpUtils;
import cn.kanmars.ecm.dao.TblOrderDealMapper;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayUtil;
import com.ycb.bean.PayConfig;
import com.ycb.bean.RequestOrder;
import com.ycb.logic.ILogic;
import com.ycb.logic.ParametersVo;
import com.ycb.logic.ResultEnum;
import com.ycb.util.Constants;
import com.ycb.util.IDGeneratorTools;
import com.ycb.util.Sign;

/**
 * 1. 保存订单信息-》订单表 
 * 2. 组织要请求微信的数据 请求微信sdk 调用微信支付统一下单的接口 http 
 * 3. 解析微信返回的数据
 * !!!,微信支付的关键点就是签名，签名失败是导致收不到的回调的大多数原因
 * @author chenghui
 *
 */
@Component
public class WxPayOrderRequestLogicImpl implements	ILogic<ParametersVo<String, Object>> {
	
	private static Log LOGGER=LogFactory.getLog(WxPayOrderRequestLogicImpl.class);
	
	@Autowired
	private TblOrderDealMapper  tblOrderDealMapper;
	
	@Override
	public ResultEnum exec(ParametersVo<String, Object> param) throws Exception {
		RequestOrder  requestOrder=(RequestOrder) param.get("requestOrder");
		String  logkey=(String) param.get("logkey");
		LOGGER.info("WxPayOrderRequestLogicImpl.exec--请求微信支付开始,logkey:"+logkey);
		LOGGER.info("WxPayOrderRequestLogicImpl.exec--保存支付订单数据开始,logkey:"+logkey);
		List<HashMap> list=new ArrayList<HashMap>(1);
				list.add(copyWxOrderinfo(requestOrder));
		int i =tblOrderDealMapper.insertBatch(list);
		LOGGER.info("WxPayOrderRequestLogicImpl.exec--保存支付订单数据完成,logkey:"+logkey);
		LOGGER.info("WxPayOrderRequestLogicImpl.exec--创建微信支付接口数据完开始,logkey:"+logkey);
		PayConfig payInfo = new PayConfig();
		Map<String,String> requestmap=new HashMap<String,String>();
		createRequestMap(requestmap,requestOrder);
		LOGGER.info("WxPayOrderRequestLogicImpl.exec--创建微信支付接口数据完成,logkey:"+logkey);
		String requestXml=WXPayUtil.generateSignature(requestmap, payInfo.getKey());
		LOGGER.info("WxPayOrderRequestLogicImpl.exec--请求微信统一下单的接口开始,请求微信的xml:"+requestXml+",logkey:"+logkey);
		String reponse=HttpUtils.submitPost(Constants.WX_PAY_ORDER, requestXml);
		LOGGER.info("WxPayOrderRequestLogicImpl.exec--请求微信统一下单的接口完成,微信接口返回的xml:"+reponse+",logkey:"+logkey);
		Map<String,String> respnoseMap=WXPayUtil.xmlToMap(reponse);
		if(!requestmap.get("result_code").equals(Constants.PAY_STATUS_SUCCESS) 
				||respnoseMap.get("return_code").equals(Constants.PAY_STATUS_SUCCESS)){
			param.setResDesc("微信支付接口调用失败!");
			LOGGER.info("WxPayOrderRequestLogicImpl.exec--请求微信统一下单的接口调用失败,微信接口未返回成功:"+reponse+",logkey:"+logkey);
			return ResultEnum.ParkCase01;
		}
		/**
		 * 返回前端，拉起支付窗口
		 */
		String pay = respnoseMap.get("prepay_id");
		String wxsign = respnoseMap.get("sign");
		String wxnocestr = respnoseMap.get("nonce_str");
		/** 反馈给前端的数据 ,前端用于拉起支付窗口**/
		HashMap<String,String>  resultMap=new HashMap<String, String>();
		/** appID  必选项  注意I大写  */
		resultMap.put("appId", payInfo.getAppID());
		/** package  必选项  统一下单接口返回的字符串 参数的键必须为 package value 必须为 prepay_id= 拼接  */
		resultMap.put("package", "prepay_id=" + pay);
		/** 反馈前端的时间戳  必须选项**/
		resultMap.put("timeStamp", Sign.create_timestamp());
		/** 随机字符串  必选项  统一下单接口返回的随机字符串 注意S 大写  */
		resultMap.put("nonceStr", wxnocestr);
		/** 签名方式  必选项 默认MD5 注意T 大写  */
		resultMap.put("signType", "MD5");
		LOGGER.info("WxPayOrderRequestLogicImpl.exec--反馈前端支付,要签名的数据为"+JSON.toJSONString(resultMap)+",logkey:"+logkey);
		String result = WXPayUtil.generateSignature(resultMap, payInfo.getKey());
		resultMap.put("sign", result);
		param.setResDesc(JSON.toJSONString(resultMap));
		LOGGER.info("WxPayOrderRequestLogicImpl.exec--反馈前端支付,微信接口返回的xml:"+JSON.toJSONString(resultMap)+",logkey:"+logkey);
		return ResultEnum.ParkOk;
	}

	/**
	 * 创建请求微信的数据
	 * 
	 * @param request
	 */
	private static void createRequestMap(Map<String, String> request, RequestOrder vo) {
		request.put("body", "北京24机器-名额"); // 商品描述
		request.put("out_trade_no", vo.getOrderno()); // 商户订单号
		/** appid 微信分配的公众账号ID 必选字段 */
		request.put("appid", Constants.APPID);
		/** 商户号,微信分配的商户号， 唯一不变 必选字段 */
		request.put("mch_id", Constants.MCH_ID); 
		/** 请求接口的随机字符串,使用微信提供的工具类生成  必选字段 */
		request.put("nonce_str", WXPayUtil.generateNonceStr());
		/** 请求接口的订单总金额 单位为分 必选字段 */
		request.put("total_fee", vo.getTotalfee());// 标价金额
		/**APP和网页支付提交用户端ip*/
		request.put("spbill_create_ip", vo.getIpadress());// 终端IP
		/**该订单的交易起始时间 非必选字段   !!!接口文档非必选字段,但是不添加会返回错误信息 */
		request.put("time_start", vo.getStartTime());// 交易起始时间
		/**该订单的交易结束时间 即存活时间   !!!接口文档非必选字段,但是不添加会返回错误信息 ,并且要距离起始时间,最起码是大于5分钟
		 * 否则也会返回错误,接口文档写的是1分钟, 不得不说微信的文档挺坑人的 */
		request.put("time_expire", vo.getEndTime());// 交易结束时间
		/** 回调通知地址  支付结果回调通知 URL,开发者自己的服务器地址 http**/
		request.put("notify_url", Constants.notify_url);// 通知地址
		/** 交易类型  公众号支付对应的是 JSAPI 必须字段 */
		request.put("trade_type", Constants.TRADE_TYPE);
		/** 
		 * 请求接口的订单,所对应的微信用户唯一标识 openid 通过用户授权获取  
		 * 当支付方式为公众号支付,为必选字段
		 */
		request.put("openid", vo.getOpenid());// 用户标识
	}
	
	private  HashMap  copyWxOrderinfo(RequestOrder requestOrder){
		HashMap<String,String>  wxorder=new HashMap<String, String>();
		wxorder.put("id", IDGeneratorTools.createId());
		wxorder.put("orderNo", requestOrder.getOrderno());
		wxorder.put("body", Constants.WX_GOOD_BODY);
		wxorder.put("detail", Constants.WX_GOOD_DETAIL);
		wxorder.put("number", requestOrder.getNum());
		wxorder.put("price", Constants.WX_GOOD_PRICE); 
		wxorder.put("totalFee", requestOrder.getTotalfee());
		wxorder.put("spbillCreateIp", requestOrder.getIpadress());
		wxorder.put("payStartTime", requestOrder.getStartTime());
		wxorder.put("payEndTime", requestOrder.getEndTime());
		wxorder.put("goodsTag", "");
		wxorder.put("openid", requestOrder.getOpenid());
		wxorder.put("payTime", ""); 
		wxorder.put("payStatus", Constants.PAY_STATUS_INIT); //待支付状态
		wxorder.put("areaId", requestOrder.getAreacode());
		wxorder.put("manicheId", requestOrder.getMachine());
		wxorder.put("universityId", requestOrder.getUnitcode());
		wxorder.put("createTime", DateUtils.getCurrDate());
		wxorder.put("createUser", "易创吧科技");
		return wxorder;
	}





}
