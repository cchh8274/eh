package com.ycb.logic.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class WxPayOrderValidLogicImpl implements
		ILogic<ParametersVo<String, Object>> {
	
	private  final  Log LOGGER=LogFactory.getLog(this.getClass());
	
	@Override
	public ResultEnum exec(ParametersVo<String, Object> vo) throws Exception {
		String logkey=(String) vo.get("logkey");
		RequestOrder requstOrder = (RequestOrder) vo.get("reuqestOrder");
		LOGGER.info("WxPayOrderValidLogicImpl.exec--微信支付校验开始,logkey:"+logkey);
		if (requstOrder == null) {
			return ResultEnum.ParkCase01;
		}
		// openid
		if (StringUtils.isBlank(requstOrder.getOpenid())) {
			vo.setResDesc("微信支付,openid不能为空!");
			return ResultEnum.ParkCase01;
		}
		// area
		if (StringUtils.isBlank(requstOrder.getAreacode())) {
			vo.setResDesc("微信支付,区域编号不能为空!");
			return ResultEnum.ParkCase01;
		}
		// unit
		if (StringUtils.isBlank(requstOrder.getUnitcode())) {
			vo.setResDesc("微信支付,大学编号不能为空!");
			return ResultEnum.ParkCase01;
		}
		// num
		if (StringUtils.isBlank(requstOrder.getNum())) {
			vo.setResDesc("微信支付,购买数量不能为空!");
			return ResultEnum.ParkCase01;
		}
		// totelFee
		if (StringUtils.isBlank(requstOrder.getTotalfee())) {
			vo.setResDesc("微信支付,总金额不能为空!");
			return ResultEnum.ParkCase01;
		}
		if (requstOrder.getTotalfee().equals(
				String.valueOf(Integer.valueOf(requstOrder.getNum()) * 688))) {
			vo.setResDesc("微信支付,金额不正确!");
			return ResultEnum.ParkCase01;
		}
		LOGGER.info("WxPayOrderValidLogicImpl.exec--微信支付校验通过,logkey:"+logkey);	
		return ResultEnum.ParkOk;
	}

}
