package com.ycb.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycb.dao.DicMapper;
import com.ycb.entity.Dic;
import com.ycb.exception.WxException;

/**
 * 
 * @author chenghui
 *
 */
public class WxAessTokenUtils implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() throws BeansException {
		return applicationContext;
	}

	@SuppressWarnings(value = { "all" })
	public <T> T getbean(String classname) {
		return (T) getApplicationContext().getBean(classname);
	}

	public String getAessToken() {
		String aessToken="";
		try {
			DicMapper dicMapper = getbean("DicMapper");
			Dic dic=dicMapper.selectByPrimaryKey(1);
			if(dic == null || StringUtils.isBlank(dic.getAccessToken())){
				dicMapper.deleteAll();
				String  response=HttpUtils.submitGet(WxUrlUtils.getAccessToken());
				if(StringUtils.isBlank(response)){
					throw new WxException("调用微信接口失败");
				}
				JSONObject  batisInfo=JSON.parseObject(response);
				Dic  insertDic=new Dic();
				insertDic.setExpiresIn(batisInfo.getString("expires_in"));
				insertDic.setAccessToken(batisInfo.getString("access_token"));
				insertDic.setCreatetime(DateUtils.formatDate(new Date(), "yyyyMMddhhmmss"));
				dicMapper.insertSelective(dic);
				aessToken=batisInfo.getString("access_token");
			}
			int miunens=Integer.valueOf(dic.getExpiresIn())/60/60;
			
			
		} catch (WxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
