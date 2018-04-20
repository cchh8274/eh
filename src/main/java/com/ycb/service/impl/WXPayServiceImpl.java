package com.ycb.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.ycb.bean.PayConfig;
import com.ycb.bean.RequestOrder;
import com.ycb.controller.WXController;
import com.ycb.dao.MachineMapper;
import com.ycb.dao.WxBackResultMapper;
import com.ycb.dao.WxOrderMapper;
import com.ycb.entity.Machine;
import com.ycb.entity.WxBackResult;
import com.ycb.entity.WxOrder;
import com.ycb.service.WXPayService;
import com.ycb.util.AmountUtils;
import com.ycb.util.Constants;
import com.ycb.util.DateUtils;
import com.ycb.util.HttpUtils;
import com.ycb.util.IDGeneratorTools;
import com.ycb.util.Sign;

@Service
public class WXPayServiceImpl implements WXPayService {
	private static final Logger logger = Logger.getLogger(WXController.class);

	@Autowired
	private WxOrderMapper wxOrderMapper;
	@Autowired
	private MachineMapper machineMapper;
	@Autowired
	private WxBackResultMapper wxBackResultMapper;

	@Override
	public Map<String, String> payOrder(RequestOrder vo) {
		Map<String, String> call = new HashMap<String, String>();
		try {
//			String orderNo = IDGeneratorTools.createId();
//			logger.info("生成的订单号为=>" + orderNo);
//			String amount = AmountUtils.changeY2F(vo.getTotalfee());
//			logger.info("生成的订单金额为=>" + amount);
//			vo.setTotalfee(amount);
//			vo.setOrderno(orderNo);
			PayConfig payInfo = new PayConfig();
			insertWXorder(vo); // 出入业务系统表
			String dataxml = createWxData(vo, payInfo.getKey());
			logger.info("请求微信的报文为=>" + dataxml);
			String responseXML = cn.com.xbase.frame.util.HttpUtils.submitPost(Constants.WX_PAY_ORDER, dataxml);
			logger.info("请求微信返回的报文为=>" + responseXML);
			Map<String, String> resp = WXPayUtil.xmlToMap(responseXML);
			if (!resp.get("result_code").equals("SUCCESS")
					|| !resp.get("return_code").equals("SUCCESS")) {
				call.put("code", "false");
				return call;
			}
			String pay = resp.get("prepay_id");
			String wxsign = resp.get("sign");
			String wxnocestr = resp.get("nonce_str");
			call.put("appId", payInfo.getAppID());
			call.put("package", "prepay_id=" + pay);
			call.put("timeStamp", Sign.create_timestamp());
			call.put("nonceStr", wxnocestr);
			call.put("signType", "MD5");
			logger.info("微信返回组装的MAP=>" + JSON.toJSONString(call));
			String jmSign = WXPayUtil.generateSignature(call, payInfo.getKey());
			logger.info("签名一次结果进行签名=》" + wxsign);
			logger.info("签名二次结果进行签名=》" + jmSign);
			boolean bsign = isSign(call, payInfo.getKey(), jmSign);
			// call.put("paySign",jmSign);
			logger.info("返回结果两者签名校验=》" + bsign);
			logger.info("微信返回组装的MAP 签名完成=>" + JSON.toJSONString(call));
		} catch (Exception e) {
			e.printStackTrace();
			call.put("code", "false");
		}
		return call;
	}

	private boolean isSign(Map<String, String> call, String key, String sign)
			throws Exception {
		call.put("sign", sign);
		String xml = WXPayUtil.mapToXml(call);
		logger.info("返回结果签名校验时=》" + xml);
		logger.info("返回结果签名校验的Map=》" + JSON.toJSONString(call));
		return WXPayUtil.isSignatureValid(xml, key);
	}

	/**
	 * 插入业务系统表
	 * 
	 * @param vo
	 */
	private void insertWXorder(RequestOrder vo) {
		WxOrder wxOrder = new WxOrder();
		wxOrder.setTotalFee(vo.getTotalfee());
		wxOrder.setNum(vo.getNum());
		wxOrder.setOpenId(vo.getOpenid());
		wxOrder.setUnit(vo.getUnit());
		wxOrder.setOrderNo(vo.getOrderno());
		wxOrder.setMachine(vo.getMach());
		wxOrder.setArea(vo.getArea());
		wxOrder.setPaytime(DateUtils.formatDate(vo.getStartTime(), "yyyyMMddHHmmss"));
		wxOrder.setPayStatus("false");
		wxOrder.setCreatetime(new Date());
		wxOrderMapper.insertSelective(wxOrder);
	}

	private String createWxData(RequestOrder vo, String key) throws Exception {
		Map<String, String> data = new HashMap<String, String>();
		// data.put("device_info", "WEB"); // 商品描述
//		data.put("sign_type", "MD5");
		data.put("body", "北京24机器-名额"); // 商品描述
		data.put("out_trade_no", vo.getOrderno()); // 商户订单号
		data.put("appid", "wx88cb890e1e079473");
		data.put("mch_id", "1496252192");
		data.put("nonce_str", WXPayUtil.generateNonceStr());
		data.put("total_fee", vo.getTotalfee());// 标价金额
		data.put("spbill_create_ip", vo.getIpadress());// 终端IP
		data.put("time_start", vo.getStartTime());// 交易起始时间
		data.put("time_expire", vo.getEndTime());// 交易结束时间
		data.put("notify_url", Constants.notify_url);// 通知地址
		data.put("trade_type", Constants.trade_type);// 交易类型
		data.put("openid", vo.getOpenid());// 用户标识
		return WXPayUtil.generateSignedXml(data, key);
	}


	/**
	 * 回调结果通知
	 * 
	 * @throws Exception
	 */
	public void callBackWXpay(Map<String, String> resultMap) throws Exception {
		String code = resultMap.get("return_code");
		if (!code.equals("SUCCESS")) {
			return;
		}
		String orderno = resultMap.get("out_trade_no");
		WxOrder order = wxOrderMapper.queryOrder(orderno);
		String amout = resultMap.get("total_fee");
		if (!amout.equals(order.getTotalFee())) {
			resultMap.clear();
			resultMap.put("return_code", "FAIL");
			resultMap.put("return_msg", "失敗");
		}
		WxOrder  wxorder=new WxOrder();
		wxorder.setId(order.getId());
		wxorder.setPayStatus("true");
		wxOrderMapper.updateByPrimaryKeySelective(wxorder);
		resultMap.clear();
//		insertCallBack(resultMap);
		updateNumberManinche(order.getMachine(),order.getUnit(),order.getNum());
		resultMap.put("return_code", "SUCCESS");
		resultMap.put("return_msg", "OK");

	}
	
	
	private void insertCallBack(Map<String, String> resultMap){
		WxBackResult  wxBackResult=new WxBackResult();
		
		wxBackResultMapper.insertSelective(wxBackResult);
		
	}
	
	private void updateNumberManinche(String name,String unit,String num){
		Machine  machine= machineMapper.queryMachineByName(unit, name);
		Machine  numMachine=new Machine();
		numMachine.setLaplces(Long.valueOf(machine.getLaplces())+Long.valueOf(num)+"".trim());
		numMachine.setRplces(Long.valueOf(machine.getRplces())-Long.valueOf(num)+"".trim());
		numMachine.setId(machine.getId());
		machineMapper.updateByPrimaryKeySelective(numMachine);
	}
	
	private static String toketULR(String id) {
		String token = HttpUtils
				.submitGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx88cb890e1e079473&secret=5982d81fbb3a64d413e9a4f1eabe0898");
		JSONObject oo = (JSONObject) JSON.parse(token);
		String ks = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
				+ "access_token=" + oo.getString("access_token")
				+ "&type=jsapi";
		String k = HttpUtils.submitGet(ks);
		return ((JSONObject) JSON.parse(k)).getString("ticket");
	}

	@Override
	public Map<String, String> config(String url) {
		Map<String, String> signmap = Sign.sign(toketULR("1"), url);
		signmap.put("appId", Constants.appid);
		logger.info("生成数据=》" + JSON.toJSONString(signmap));
		return signmap;
	}

}
