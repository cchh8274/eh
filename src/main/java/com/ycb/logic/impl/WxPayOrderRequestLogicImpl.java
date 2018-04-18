package com.ycb.logic.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.wxpay.sdk.WXPayUtil;
import com.ycb.bean.PayConfig;
import com.ycb.bean.RequestOrder;
import com.ycb.logic.ILogic;
import com.ycb.logic.ParametersVo;
import com.ycb.logic.ResultEnum;
import com.ycb.util.Constants;
import com.ycb.util.HttpUtils;

/**
 * 1. 保存订单信息-》订单表 
 * 2. 组织要请求微信的数据 请求微信sdk 调用微信支付统一下单的接口 http 
 * 3. 解析微信返回的数据
 * 
 * @author chenghui
 *
 */
@Component
public class WxPayOrderRequestLogicImpl implements	ILogic<ParametersVo<String, Object>> {

	@Override
	public ResultEnum exec(ParametersVo<String, Object> param) throws Exception {
		RequestOrder  requestOrder=(RequestOrder) param.get("requestOrder");
		PayConfig payInfo = new PayConfig();
		Map<String,String> requestmap=new HashMap<String,String>();
		createRequestMap(requestmap,requestOrder);
		String requestXml=WXPayUtil.generateSignature(requestmap, payInfo.getKey());
		String reponse=HttpUtils.submitPost(Constants.WX_PAY_ORDER, requestXml);
		Map<String,String> respnoseMap=WXPayUtil.xmlToMap(reponse);
		return null;
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
}
