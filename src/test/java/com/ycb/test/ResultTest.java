package com.ycb.test;
import com.ycb.util.HttpUtils;

public class ResultTest {
		public static void main(String[] args) {
			String xml ="<xml><appid><![CDATA[wx88cb890e1e079473]]></appid><bank_type><![CDATA[CFT]]></bank_type>"
					+ "<cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]>"
					+ "</is_subscribe><mch_id><![CDATA[1496252192]]></mch_id><nonce_str><![CDATA[89e8364760a04b83956dd4431841b50d]]>"
					+ "</nonce_str><openid><![CDATA[oYYPb0gIcCPq0IDh_fedDoJRavJU]]></openid><out_trade_no>"
					+ "<![CDATA[00000000000000885707125506586779]]></out_trade_no><result_code><![CDATA[SUCCESS]]>"
					+ "</result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[4509CB7314D626AF1F07D9229B10AD5B]]>"
					+ "</sign><time_end><![CDATA[20180204010201]]></time_end><total_fee>1</total_fee><trade_type>"
					+ "<![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[4200000053201802046579688419]]>"
					+ "</transaction_id></xml>";
			String	 responseXML = HttpUtils.submitPost("http://127.0.0.1:8080/eh/ycb/wxpay/responseResult.do","xml="+xml);
			System.out.println(responseXML);
		}
}
