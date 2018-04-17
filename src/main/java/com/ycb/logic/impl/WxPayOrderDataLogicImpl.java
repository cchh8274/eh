package com.ycb.logic.impl;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.ycb.logic.ILogic;
import com.ycb.logic.ParametersVo;
import com.ycb.logic.ResultEnum;
import com.ycb.model.RequestOrder;
import com.ycb.util.AmountUtils;
import com.ycb.util.DateUtils;
import com.ycb.util.IDGeneratorTools;
import com.ycb.util.IPAdressUtils;
/**
 * 组织数据
 * @author chenghui
 *
 */
@Component
public class WxPayOrderDataLogicImpl implements ILogic<ParametersVo<String, Object>>{
	private static final String format = "yyyyMMddHHmmss";
	@Override
	public ResultEnum exec(ParametersVo<String, Object> param) throws Exception {
		RequestOrder vo = (RequestOrder)param.get("reuqestOrder");
		String startTime = DateUtils.getbjTime(); //获取当前请求时间
		vo.setStartTime(startTime); 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtils.paresDate(format, startTime));
		calendar.add(Calendar.MINUTE, 5);
		String endTime = DateUtils.formatDate(calendar.getTime(), format);
		vo.setEndTime(endTime);
		vo.setIpadress(IPAdressUtils.getIpAddr((HttpServletRequest) param.get("request")));
		String orderNo = IDGeneratorTools.createId();
		String amount = AmountUtils.changeY2F(vo.getTotalfee());
		vo.setTotalfee(amount);
		vo.setOrderno(orderNo);
		return ResultEnum.ParkOk;
	}

}
