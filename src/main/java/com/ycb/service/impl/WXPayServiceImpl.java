package com.ycb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.xbase.frame.util.DateUtils;
import cn.com.xbase.frame.util.HttpUtils;
import cn.kanmars.ecm.dao.TblMachineInfoMapper;
import cn.kanmars.ecm.dao.TblOrderDealMapper;
import cn.kanmars.ecm.dao.TblPayResultInfoMapper;
import cn.kanmars.ecm.entity.TblMachineInfo;
import cn.kanmars.ecm.entity.TblPayResultInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.ycb.controller.WXController;
import com.ycb.exception.WxException;
import com.ycb.service.WXPayService;
import com.ycb.util.Constants;
import com.ycb.util.IDGeneratorTools;
import com.ycb.util.Sign;

@Service
public class WXPayServiceImpl implements WXPayService {
	private static final Logger logger = Logger.getLogger(WXController.class);

	@Autowired
	private TblOrderDealMapper tblOrderDealMapper;
	@Autowired
	private TblMachineInfoMapper tblmachineInfoMapper;
	@Autowired
	private TblPayResultInfoMapper  tblPayResultInfoMapper;

	private boolean isSign(Map<String, String> call, String key, String sign)
			throws Exception {
		call.put("sign", sign);
		String xml = WXPayUtil.mapToXml(call);
		logger.info("返回结果签名校验时=》" + xml);
		logger.info("返回结果签名校验的Map=》" + JSON.toJSONString(call));
		return WXPayUtil.isSignatureValid(xml, key);
	}



	/**
	 * 回调结果通知
	 * 
	 * @throws Exception
	 */
	public void callBackWXpay(Map<String, String> resultMap) throws Exception {
		String code = resultMap.get("return_code");
		if (!code.equals("SUCCESS")) {
			throw new WxException("支付接口返回失败");
		}
		String orderno = resultMap.get("out_trade_no");
		HashMap map=new HashMap();
		map.put("orderNo", orderno);
		HashMap orderData = tblOrderDealMapper.queryOneMap(map);
		String amout = resultMap.get("total_fee");
		if (!amout.equals(orderData.get("total_fee"))) {
			throw new WxException("支付返回金额和下单金额不一致");
		}
		map.clear();
		map.put("id", orderData.get("id"));
		map.put("payTime_new", resultMap.get("time_end"));
		map.put("payStatus_new", Constants.PAY_STATUS_SUCCESS);
		tblOrderDealMapper.updateCAS(map);
		resultMap.clear();
		//扣减库存
		TblMachineInfo  machine= tblmachineInfoMapper.selectByPrimaryKey((String) orderData.get("maniche_id"),null);
		TblMachineInfo  numMachine=new TblMachineInfo();
		numMachine.setOutSalePlace(Long.valueOf(machine.getOutSalePlace())+Long.valueOf((String) orderData.get("number"))+"".trim());
		numMachine.setLeftPlace(Long.valueOf(machine.getLeftPlace())-Long.valueOf((String) orderData.get("number"))+"".trim());
		numMachine.setId(machine.getId());
		tblmachineInfoMapper.updateByPrimaryKey(numMachine);
		resultMap.put("return_code", "SUCCESS");
		resultMap.put("return_msg", "OK");

	}
	
	
	private void insertCallBack(Map<String, String> resultMap){
		TblPayResultInfo  result=new TblPayResultInfo();
		result.setAppid(IDGeneratorTools.createId());
		result.setMchId(Constants.MCH_ID);
		result.setDeviceInfo(resultMap.get("device_info"));
		result.setNonceStr(resultMap.get("nonce_str"));
		result.setSign(resultMap.get("sign"));
		result.setSignType(resultMap.get("sign_type"));
		result.setResultCode(resultMap.get("result_code"));
		result.setErrCode(resultMap.get("err_code"));
		result.setErrCodeDes(resultMap.get("err_code_des"));
		result.setOpenid(resultMap.get("openid"));
		result.setIsSubscribe(resultMap.get("is_subscribe"));
		result.setTradeType(resultMap.get("trade_type"));
		result.setBankType(resultMap.get("bank_type"));
		result.setTotalFee(resultMap.get("total_fee"));
		result.setSettlementTotalFee(resultMap.get("settlement_total_fee"));
		result.setFeeType(resultMap.get("fee_type"));
		result.setTransactionIdtransactionId(resultMap.get("transaction_idtransaction_id"));
		result.setOutTradeNo(resultMap.get("out_trade_no"));
		result.setTimeEnd(resultMap.get("time_end"));
		result.setCreateTime(DateUtils.getCurrDate());
		result.setCreateUser("system");
		tblPayResultInfoMapper.insert(result);
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
		signmap.put("appId", Constants.APPID);
		logger.info("生成数据=》" + JSON.toJSONString(signmap));
		return signmap;
	}

}
