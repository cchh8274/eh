package com.ycb.yutil;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ycb.util.SortUtils;

public class KeyUtils {

	private static final String device_info = "WEB";

	private static final String appid = "wx88cb890e1e079473";

	private static final String mch_id = "1496252192";

	public static String signMD5(Map<String, String> map) {
		String sort = SortUtils.sortString(map);
		sort = sort + "key=ycbqc18103517010zly17611540713ch";
		String sign = MD5Util.md5(sort).toUpperCase();
		return sign;
		// stringA =
		// "appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";
	}

	public static String getNonce() {
		UUID uuid = UUID.randomUUID();
		String srt = uuid.toString();
		srt = srt.replace("-", "");
		System.out.println("随机字符串为=>" + srt);
		return srt;
	}

	public static long currentTime() {
		long currentTime = Calendar.getInstance().getTimeInMillis();
		return currentTime;
	}

	public static void main(String[] args) {
		String uuid = getNonce();
		System.out.println(uuid);
//		System.out.println(signMD5(Contant.body, uuid));
	}

	public static String signMap(HashMap<String, String> map) {
		String sort = SortUtils.sortString(map);
		sort = sort + "key=ycbqc18103517010zly17611540713ch";
		String sign = MD5Util.md5(sort).toUpperCase();
		return sign;
	}

}
