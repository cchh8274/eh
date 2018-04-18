package com.ycb.logic.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ycb.bean.RequestOrder;
import com.ycb.logic.ILogic;
import com.ycb.logic.ParametersVo;
import com.ycb.logic.ResultEnum;

/**
 * 校验前台传输的数据正确性 判断金额 判断份数 判断订单信息 请求微信之前的校验
 * 
 * @author chenghui
 *
 */
@Component
public class WxPayOrderValidLogicImpl implements ILogic<ParametersVo<String,Object>> {

	@Override
	public ResultEnum exec(ParametersVo<String,Object> vo) throws Exception {
		RequestOrder requstOrder = (RequestOrder)vo.get("reuqestOrder");
		if (requstOrder == null) {
			return ResultEnum.ParkCase01;
		}
		// openid
		if (StringUtils.isBlank(requstOrder.getOpenid())) {
			return ResultEnum.ParkCase01;
		}
		// area
		if (StringUtils.isBlank(requstOrder.getArea())) {
			return ResultEnum.ParkCase01;
		}
		// unit
		if (StringUtils.isBlank(requstOrder.getUnit())) {
			return ResultEnum.ParkCase01;
		}
		// num
		if (StringUtils.isBlank(requstOrder.getNum())) {
			return ResultEnum.ParkCase01;
		}
		// totelFee
		if (StringUtils.isBlank(requstOrder.getTotalfee())) {
			return ResultEnum.ParkCase01;
		}
		return ResultEnum.ParkOk;
	}

}
