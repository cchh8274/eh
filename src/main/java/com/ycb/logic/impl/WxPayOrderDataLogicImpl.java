package com.ycb.logic.impl;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import cn.com.xbase.frame.util.DateFormatUtils;
import cn.com.xbase.frame.util.DateUtils;

import com.ycb.bean.RequestOrder;
import com.ycb.logic.ILogic;
import com.ycb.logic.ParametersVo;
import com.ycb.logic.ResultEnum;
import com.ycb.util.AmountUtils;
import com.ycb.util.IPAdressUtils;
import com.ycb.util.SnowflakeIdWorker;
/**
 * 组织数据
 * @author chenghui
 *
 */
@Component
public class WxPayOrderDataLogicImpl implements ILogic<ParametersVo<String, Object>>{
	
	private static final Log LOGGER=LogFactory.getLog(WxPayOrderDataLogicImpl.class);
	
	private static final String format = "yyyyMMddHHmmss";
	@Override
	public ResultEnum exec(ParametersVo<String, Object> param) throws Exception {
		RequestOrder vo = (RequestOrder)param.get("reuqestOrder");
		String logkey=(String) param.get("logkey");
		LOGGER.info("WxPayOrderDataLogicImpl.exec--创建微信支付数据开始,logkey:"+logkey);
		String startTime = DateUtils.getCurrDateTime(); //获取当前请求时间
		vo.setStartTime(startTime); 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateFormatUtils.format(startTime, format));
		calendar.add(Calendar.MINUTE, 5);
		String endTime = DateFormatUtils.format(calendar.getTime(), format); //新增5分钟为结束时间
		vo.setEndTime(endTime);
		vo.setIpadress(IPAdressUtils.getIpAddr((HttpServletRequest) param.get("request")));
		long orderNo =new  SnowflakeIdWorker(0,0).nextId();
		String amount = AmountUtils.changeY2F(vo.getTotalfee()); //金额由元转化为分
		vo.setTotalfee(amount); //支付总金额
		vo.setOrderno(String.valueOf(orderNo)); //订单号
		LOGGER.info("WxPayOrderDataLogicImpl.exec--创建微信支付数据结束,logkey:"+logkey);
		return ResultEnum.ParkOk;
	}

}
