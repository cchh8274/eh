package com.ycb.service;

import java.util.HashMap;
import java.util.List;


public interface WxUserService {

	String getUserInfo(String openID) throws Exception;

	void insetListWxUser(List<HashMap<String, Object>> userInfos);

	String queryNextOpenid();

}
