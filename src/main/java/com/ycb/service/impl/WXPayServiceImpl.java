package com.ycb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.ycb.controller.WXController;
import com.ycb.dao.MachineMapper;
import com.ycb.dao.WxOrderMapper;
import com.ycb.entity.WxOrder;
import com.ycb.model.RequestOrder;
import com.ycb.model.WXParamerVO;
import com.ycb.service.WXPayService;
import com.ycb.util.AmountUtils;
import com.ycb.util.Contant;
import com.ycb.util.HttpUtils;
import com.ycb.util.IdGenTools;
import com.ycb.util.KeyUtils;
import com.ycb.util.MyConfig;
import com.ycb.util.Sign;
import com.ycb.util.SignUtil;

@Service
public class WXPayServiceImpl implements WXPayService {
	private static final Logger logger = Logger.getLogger(WXController.class);

	@Autowired
	private WxOrderMapper wxOrderMapper;
	@Autowired
	private MachineMapper machineMapper;

	@Override
	public Map<String,String> payOrder(RequestOrder vo) {
		try {
			String orderNo = IdGenTools.cteateId();
			logger.info("生成的订单号为=>" + orderNo);
			String amount = AmountUtils.changeY2F(vo.getTotalfee());
			logger.info("生成的订单金额为=>" + amount);
			WxOrder wxOrder = new WxOrder();
			wxOrder.setTotalFee(vo.getTotalfee());
			wxOrder.setNum(vo.getNum());
			wxOrder.setOpenId(vo.getOpenid());
			wxOrder.setUnit(vo.getUnit());
			wxOrder.setOrderNo(orderNo);
			wxOrderMapper.insertSelective(wxOrder);

			int i = machineMapper.queryMachine(vo.getUnit(), vo.getMach());
			Map<String,Integer> accach=new HashMap<String, Integer>();
			accach.put("id", i);
			accach.put("num",Integer.valueOf(vo.getNum()));
			
			
			MyConfig config = new MyConfig();
			WXPay wxpay = new WXPay(config);
			Map<String, String> data = new HashMap<String, String>();
			data.put("device_info", "WEB"); // 商品描述
			String  nonce=KeyUtils.getNonce(); //随机字符
			data.put("nonce_str",nonce);
			data.put("sign_type","MD5");
			data.put("body", "小张南山店-超市"); // 商品描述
//			data.put("detail", Contant.detil); // 商品详情
			data.put("out_trade_no", orderNo); // 商户订单号
//			data.put("fee_type", Contant.fee_type);// 标价币种
			data.put("total_fee", "88");// 标价金额
			data.put("spbill_create_ip", vo.getIpadress());// 终端IP
			data.put("time_start", vo.getStartTime());// 交易起始时间
			data.put("time_expire", vo.getEndTime());// 交易结束时间
//			data.put("goods_tag", Contant.goods_tag);// 订单优惠标记
			data.put("notify_url", Contant.notify_url);// 通知地址
			data.put("trade_type", Contant.trade_type);// 交易类型
//			data.put("limit_pay", Contant.limit_pay);// 指定支付方式
			data.put("openid", vo.getOpenid());// 用户标识
			String sign=KeyUtils.signMD5(credMap(data));
			data.put("sign",sign);
//			data.put("attach", "<![CDATA["+JSON.toJSONString(accach)+"]");// 用户标识
			
			logger.info("请求微信的报文为=>" + JSON.toJSONString(data));
			Map<String, String> resp = wxpay.unifiedOrder(data);
			logger.info("请求微信返回的报文为=>"+JSON.toJSONString(resp));
			if (resp.get("result_code").equals("SUCCESS")
					&& resp.get("return_code").equals("SUCCESS")) {
				String pay = resp.get("prepay_id");
				String wxsign=resp.get("sign");
				String wxnocestr=resp.get("nonce_str");
				Map<String,String>  call=new HashMap<String, String>();
				call.put("appId",Contant.appid);
				call.put("package", pay);
				call.put("paytimestamp", Sign.create_timestamp());
//				Map<String,String> signmap=Sign.sign(toketULR("1"), vo.getUrl());
//				call.put("paynonceStr", signmap.get("nonceStr"));
//				call.put("paySign", signmap.get("signature"));
				call.put("paynonceStr", wxnocestr);
				call.put("paySign",KeyUtils.signMap(creMap(resp)));
//				call.put("paySign", wxsign);
				return call;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, String> requsetWXpay(String order) {
		// TODO Auto-generated method stub
		return null;
	}

	public void requsetWXpay(Map<String, String> resultMap) {
		// TODO Auto-generated method stub

	}
	private HashMap<String,String> creMap(Map<String, String> resp){
		HashMap<String,String> map=new HashMap<String, String>();
		map.put("appId", Contant.appid);
		map.put("timeStamp", Sign.create_timestamp());
		map.put("nonceStr",resp.get("nonce_str"));
		map.put("package", "prepay_id="+resp.get("prepay_id"));
		map.put("signType","MD5");
		map.put("paySign",resp.get("sign"));
		return map;
	}
	private Map<String,String> credMap(Map<String, String> resp){
		resp.put("appid", Contant.appid);
		resp.put("mch_id", Contant.mch_id);
		return resp;
	}
	
	
	/**
	 * 回调结果通知
	 * 
	 * @throws Exception
	 */
	public void callBackWXpay(Map<String, String> resultMap) throws Exception {
		String code = resultMap.get("return_code");
		if (!code.equals("success")) {
			return;
		}
		String orderno = resultMap.get("out_trade_no");
		WxOrder order = wxOrderMapper.queryOrder(orderno);
		String amout = AmountUtils.changeF2Y(resultMap.get("total_fee"));
		if (!amout.equals(order.getTotalFee())) {
			resultMap.clear();
			resultMap.put("return_code", "FAIL");
			resultMap.put("return_msg", "失敗");
		}
		resultMap.clear();
		resultMap.put("return_code", "SUCCESS");
		resultMap.put("return_msg", "OK");

	}

	@Override
	public Map<String, String> requsetWXpay(WXParamerVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private static String  toketULR(String id){
		
		String token=HttpUtils.submitGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx88cb890e1e079473&secret=5982d81fbb3a64d413e9a4f1eabe0898");
		JSONObject oo=(JSONObject) JSON.parse(token);
		String ks="https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
				+ "access_token="+oo.getString("access_token")+"&type=jsapi";
		String k=HttpUtils.submitGet(ks);
		return  ((JSONObject)JSON.parse(k)).getString("ticket");
	}

	@Override
	public Map<String, String> config(String url) {
		Map<String,String> signmap=Sign.sign(toketULR("1"), url);
		signmap.put("appId", Contant.appid);
		logger.info("生成数据=》"+JSON.toJSONString(signmap));
		return signmap;
	}
	
}
