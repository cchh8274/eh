package com.ycb.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.xbase.frame.util.DateUtils;
import cn.com.xbase.frame.util.HttpUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.ycb.service.WxUserService;
import com.ycb.util.AccessTokenUtils;
import com.ycb.util.Constants;
import com.ycb.util.IDGeneratorTools;

/**
 *
 * @author chenghui 同步微信关注者列表及微信用户详情
 */
@Service
public class SyncWxUserJob {
	private static final Log LOGGER = LogFactory.getLog(SyncWxUserJob.class);

	@Autowired
	private WxUserService wxUserService;

	public void jobs() {
		try {
			LOGGER.info("SyncWxUserJob.run同步微信用户列表开始,同步时间为:"
					+ DateUtils.getCurrDate());
			String token = AccessTokenUtils.getAccessToken(); // 获取token信息
			if (StringUtils.isEmpty(token)) {
				LOGGER.info("SyncWxUserJob.run同步微信用户列表,获取token信息为空");
				return;
			}
			String nextOpenid = wxUserService.queryNextOpenid();
			String url = Constants.WX_USER_LIST + "?access_token=" + token
					+ "&next_openid=" + nextOpenid;
			String listResponse = HttpUtils.submitGet(url);
			if (StringUtils.isEmpty(listResponse)) {
				LOGGER.info("SyncWxUserJob.run同步微信用户列表,获取微信用户列表为空!");
				return;
			}
			String userlist = JSON.parseObject(listResponse).getString("data");
			if (StringUtils.isEmpty(listResponse)) {
				LOGGER.info("SyncWxUserJob.run同步微信用户列表,获取微信用户数据为空!");
				return;
			}
			List<Object> array = JSON.parseArray(userlist);
			List<HashMap<String, Object>> userInfos=new ArrayList<HashMap<String,Object>>();
			for (Object object : array) {
				if(copyUserInfo(token, object.toString()) ==null){
					continue;
				}
				userInfos.add(copyUserInfo(token, object.toString()));
			}
			this.wxUserService.insetListWxUser(userInfos);
			LOGGER.info("SyncWxUserJob.run同步微信用户列表,获取微信用户完成!");
		} catch (Exception e) {
			LOGGER.info("SyncWxUserJob.run同步微信用户列表异常");
			LOGGER.error(e.getMessage(),e);
		}
	}

	private HashMap<String,Object> copyUserInfo(String token, String openid) {
		String url = Constants.WX_USER_INFO + "?access_token=" + token
				+ "&openid=" + openid + "&lang=zh_CN";
		String response = HttpUtils.submitGet(url);
		if (StringUtils.isEmpty(response)) {
			return null;
		}
		JSONObject info=(JSONObject) JSON.parse(response);
		HashMap<String,Object>  hashMap=new HashMap<String, Object>();
		hashMap.put("id", IDGeneratorTools.createId());
		hashMap.put("id", info.getString("openid"));
		hashMap.put("id", info.getString("nickname"));
		hashMap.put("id", info.getString("sex"));
		hashMap.put("id", info.getString("city"));
		hashMap.put("id", info.getString("country"));
		hashMap.put("id", info.getString("province"));
		hashMap.put("id", info.getString("language"));
		hashMap.put("id", info.getString("headimgurl"));
		hashMap.put("id", info.getString("subscribe_time"));
		hashMap.put("id", info.getString("unionid"));
		hashMap.put("id", info.getString("remark"));
		hashMap.put("id", info.getString("groupid"));
		hashMap.put("id", info.getString("subscribe_scene"));
		hashMap.put("id", info.getString("qr_scene"));
		hashMap.put("id", info.getString("qr_scene_str"));
		hashMap.put("id", "system");
		hashMap.put("id", new Date());
		return hashMap; 
	}
}
