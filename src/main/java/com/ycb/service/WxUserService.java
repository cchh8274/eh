package com.ycb.service;

import java.util.HashMap;
import java.util.List;


public interface WxUserService {

	String getUserInfo(String openID) throws Exception;

	String queryNextOpenid();

	void insetListWxUser(List<HashMap> userInfos) throws Exception;

}
