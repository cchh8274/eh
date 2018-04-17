package com.ycb.test;

import com.ycb.util.HttpUtils;

public class MenTest {
	public static void main(String[] args) {
		String url = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?"
				+ "access_token=8_cwzk8tTkblbCaw7Jsm5N39w"
				+ "ueBEDxvGp5ArbTDAuxVe6S2stBgg3VhTNwnUovleRAT"
				+ "4q8n26o7Xx5rn-BTcANO3MXxuacXyMwhlBskFsFQz0V"
				+ "NA_ZB1q2c3l-BOhsLGOIOB9lbBsD8ekYLlCNIUjAJAHCO";

		String str = HttpUtils.submitGet(url);
		System.out.println(str);
	}
}
