package com.ycb.model;

import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

public class PayConfig implements WXPayConfig {

	private static final String appid = "wx88cb890e1e079473";
	private static final String mchid = "1496252192";
	private static final String key = "ycbqc18103517010zly17611540713ch";
	private static final String box_key = "371b07d70d2c15198c9e72e26175b74b";


	@Override
	public String getAppID() {
		return appid;
	}

	@Override
	public String getMchID() {
		return mchid;
	}

	@Override
	public String getKey() {
		return box_key;
	}

	@Override
	public InputStream getCertStream() {
		return null;
	}

	@Override
	public int getHttpConnectTimeoutMs() {
		return 8000;
	}

	@Override
	public int getHttpReadTimeoutMs() {
		return 10000;
	}

}
