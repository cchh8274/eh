package com.ycb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wxpay.sdk.WXPay;
//import com.ycb.dao.WxOrderMapper;
//import com.ycb.entity.WxOrder;
//import com.ycb.model.RequestOrder;
import com.ycb.model.WXParamerVO;
import com.ycb.service.WXPayService;
import com.ycb.util.Contant;
//import com.ycb.util.IdGenTools;
import com.ycb.util.WxConfig;

@Service
public class WXPayServiceImpl implements WXPayService {
//	@Autowired
//	private WxOrderMapper  wxOrderMapper;
//	
//	@Override
//	public Map<String,String> requsetWXpay(WXParamerVO vo) {
//		WxConfig config = new WxConfig();
////		WXPay wxpay = new WXPay(config);
//		Map<String, String> data = new HashMap<String, String>();
//		data.put("body", "腾讯充值中心-QQ会员充值");
//		data.put("out_trade_no", "2016090910595900000012");
//		data.put("device_info", "");
//		data.put("fee_type", "CNY");
//		data.put("total_fee", "1");
//		data.put("spbill_create_ip", "123.12.12.123");
//		data.put("notify_url", "http://www.example.com/wxpay/notify");
//		data.put("trade_type", "NATIVE"); // 此处指定为扫码支付
//		data.put("product_id", "12");
//
//		try {
////			Map<String, String> resp = wxpay.unifiedOrder(data);
////			System.out.println(resp);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	

	private Map<String,String> createMap(){
		Map<String, String> data = new HashMap<String, String>();
		data.put("body", "腾讯充值中心-QQ会员充值"); //商品描述
		data.put("detail", "腾讯充值中心-QQ会员充值"); // 商品详情
		data.put("out_trade_no", "腾讯充值中心-QQ会员充值"); // 商户订单号
		data.put("fee_type", Contant.fee_type);// 标价币种
		data.put("total_fee", "腾讯充值中心-QQ会员充值");// 标价金额
		data.put("spbill_create_ip", "腾讯充值中心-QQ会员充值");// 终端IP
		data.put("time_start", "腾讯充值中心-QQ会员充值");// 交易起始时间
		data.put("time_expire", "腾讯充值中心-QQ会员充值");// 交易结束时间
		data.put("goods_tag", Contant.goods_tag);// 订单优惠标记
		data.put("notify_url",Contant.notify_url);// 通知地址
		data.put("trade_type", Contant.trade_type);// 交易类型
		data.put("limit_pay", Contant.limit_pay);// 指定支付方式
		data.put("openid", "");// 用户标识
		
		return null;
	}


	/**
	 * 插入订单 
	 */
//	public String insert(RequestOrder vo) {
//		String orderNo=IdGenTools.cteateId();
//		WxOrder  wxOrder=new WxOrder();
//		wxOrder.setTotalFee(vo.getTotalfee());
//		wxOrder.setNum(vo.getNum());
//		wxOrder.setOpenId(vo.getOpenid());
//		wxOrder.setUnit(vo.getUnit());
//		wxOrder.setOrderNo(orderNo);
//		wxOrderMapper.insertSelective(wxOrder);
//		return orderNo;
//	}



	public Map<String, String> requsetWXpay(String order) {
		// TODO Auto-generated method stub
		return null;
	}


	public void requsetWXpay(Map<String, String> resultMap) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 回调结果通知
	 */
	public void callBackWXpay(Map<String, String> resultMap) {
		String code =resultMap.get("return_code");
		if(!code.equals("success")){
			return ;
		}
		String orderno=resultMap.get("out_trade_no");		
		
		resultMap.clear();
		resultMap.put("return_code", "success");
		resultMap.put("return_msg", "接受成功");
		
	}
}
