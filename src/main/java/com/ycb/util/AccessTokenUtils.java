package com.ycb.util;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.rule.constraint.ConditionAnalyzer.ThisInvocation;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycb.exception.WxException;

import cn.com.xbase.frame.cache.ApplicationCache;
import cn.com.xbase.frame.util.HttpUtils;

/**
 * 动态获取通过appid ,secret 获取token 值 保存在缓存中 并且进行频率刷新 减少调用次数 不和数据库做交接
 * 防异常机制，如果调动出现空,立刻进行一次实时调用
 * 
 * @author chenghui
 *
 */
public class AccessTokenUtils implements ApplicationContextAware {

	private  static final Log LOGGER = LogFactory.getLog(AccessTokenUtils.class);
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}

	/**
	 * 初始化执行
	 */
	@PostConstruct
	public synchronized void init() {
		LOGGER.info("AccessTokenUtils--init start,初始化token信息开始");
		final long startTime = System.currentTimeMillis();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                	LOGGER.info("定时刷新Access_token缓存开始");
                    findApplicationCache();
                    long now = System.currentTimeMillis();
                    try {
                        if ((now - startTime) <= (24 * 60 * 60 * 1000)) {
                            //启动后一天之内
                            Thread.sleep(60 * 1000 * 60);//每30秒刷新一次缓存
                        } else {
                            //启动后一天之后
                            Thread.sleep(60 * 1000 * 120);//每120分钟刷新一次
                        }
                    } catch (InterruptedException e) {
                    	LOGGER.error("定时刷新Access_token缓存异常", e);
                    }
                }
            }
        }).start();
        LOGGER.info("定时刷新Access_token缓存成功");
	}
	/**
	 * 请求微信放到缓存中
	 */
	private static void findApplicationCache() {
		try {
			String params = Constants.ACCESS_TOKEN_URL
					+ "?grant_type=client_credential&" + "appid="
					+ Constants.APPID + "&secret=" + Constants.SECRET;
			String response = HttpUtils.submitGet(params);
			if (StringUtils.isEmpty(response)) {
				throw new WxException("调用微信接口异常");
			}
			JSONObject info=JSON.parseObject(response);
			ApplicationCache.getInstance().put("ACCESS_TOKEN", info.get("access_token"));
		} catch (Exception e) {
			LOGGER.info("填充ACCESS_token异常!");
		}
	}
	/**
	 * 获取缓存中的值
	 * @return
	 */
	public  static String getAccessToken(){
		String  cache=ApplicationCache.getInstance().getStr("ACCESS_TOKEN");
		if(StringUtils.isEmpty(cache)){
			findApplicationCache();
		}
		cache=ApplicationCache.getInstance().getStr("ACCESS_TOKEN");
		return cache;
	}
}
