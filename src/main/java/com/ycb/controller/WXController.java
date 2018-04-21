package com.ycb.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.xbase.frame.util.DateUtils;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayUtil;
import com.ycb.bean.RequestOrder;
import com.ycb.logic.ParametersVo;
import com.ycb.logic.ResultEnum;
import com.ycb.logic.impl.WxPayOrderDataLogicImpl;
import com.ycb.logic.impl.WxPayOrderRequestLogicImpl;
import com.ycb.logic.impl.WxPayOrderValidLogicImpl;
import com.ycb.service.WXPayService;

/**
 * 微信支付调起 微信支付
 * 
 * @author chenghui
 *
 */
@Controller
@RequestMapping("/ycb/wxpay")
public class WXController extends BaseController{

	private static final Logger LOGGER = Logger.getLogger(WXController.class);

	@Autowired
	private WXPayService wxPayService;

	/** 校验数据 */
	@Autowired
	private WxPayOrderValidLogicImpl wxPayOrderValidLogicImpl;
	/** 组织数据 */
	@Autowired
	private WxPayOrderDataLogicImpl wxPayOrderDataLogicImpl;
	/** 调用sdk下单 */
	@Autowired
	private WxPayOrderRequestLogicImpl wxPayOrderRequestLogicImpl;
	/**
	 * 金额 /数据 机器 1.自己订单生成订单 2，微信请求下单
	 * 
	 * @return
	 */
	@RequestMapping(value = "requestOrder", method = RequestMethod.POST)
	@ResponseBody
	public String requestWXPay(RequestOrder vo,HttpServletRequest request) {
		String logkey=DateUtils.getCurrDate();
		LOGGER.info("WXController.requestWXPay--微信支付下单请求开始,logkey:"+logkey);
		try {
			ParametersVo<String,Object> parametersVo = new ParametersVo<String,Object>();
			parametersVo.put("requestOrder", vo);
			logkey=vo.getOpenid()+"_"+logkey;
			parametersVo.put("logkey", logkey);
			if(!ResultEnum.ParkOk.equals(wxPayOrderValidLogicImpl.exec(parametersVo))){
				LOGGER.info("WXController.wxPayOrderValidLogicImpl.exec--,校验不通过,resron:"+parametersVo.getResDesc()+",logkey:"+logkey);
				parametersVo.setResCode("002");
				return this.toJSONString(parametersVo.toResultString());
			}
			parametersVo.put("request", request);
			if(!ResultEnum.ParkOk.equals(wxPayOrderDataLogicImpl.exec(parametersVo))){
				parametersVo.setResCode("002");
				return this.toJSONString(parametersVo.toResultString());
			}
			if(!ResultEnum.ParkOk.equals(wxPayOrderRequestLogicImpl.exec(parametersVo))){
				parametersVo.setResCode("002");
				return this.toJSONString(parametersVo.toResultString());
			}
			
			LOGGER.info("WXController.requestWXPay--微信支付下单请求完成,logkey:"+logkey);
			return this.toJSONString(parametersVo.toResultString());
		} catch (Exception e) {
			LOGGER.info("WXController.requestWXPay--微信支付下单请求异常,logkey:"+logkey);
			LOGGER.error("WXController.requestWXPay--微信支付下单请求异常,logkey:"+logkey+","+e.getMessage(),e);
			return this.toJSONString("error","系统异常!");
		}
	}

	/**
	 * 支付接口响应
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "responseResult", method = RequestMethod.POST)
	@ResponseBody
	public String responseResult(HttpServletRequest request)
			throws Exception {
		LOGGER.info("WXController.responseResult--微信通知支付结果接口被请求到,回调开始");
		Map<String, String> reultMap = new HashMap<String, String>();
		try {
			Map<String, String> resultMap = null;
			InputStream inStream = request.getInputStream();
			int _buffer_size = 1024;
			if (inStream != null) {
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] tempBytes = new byte[_buffer_size];
				int count = -1;
				while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
					outStream.write(tempBytes, 0, count);
				}
				tempBytes = null;
				outStream.flush();
				// 将流转换成字符串
				String result = new String(outStream.toByteArray(), "UTF-8");
				LOGGER.info("WXController.responseResult--微信通知支付结果接口,获取都得报文为:"+result);
				LOGGER.info("WXController.responseResult--微信通知支付结果接口,参数转化开始");
				resultMap = WXPayUtil.xmlToMap(result);
			}
			LOGGER.info("WXController.responseResult--微信通知支付结果接口,参数转化完成,转化结果为:"+JSON.toJSONString(resultMap));
			LOGGER.info("WXController.responseResult--微信通知支付结果接口,处理业务逻辑比对金额开始");
			wxPayService.callBackWXpay(resultMap);
			LOGGER.info("微信回调完成=>" + JSON.toJSONString(resultMap));
			reultMap.put("return_code", "SUCCESS");
			reultMap.put("return_msg", "OK");
			LOGGER.info("WXController.responseResult--微信通知支付结果接口,通知成功,业务处理通过,转化结果为:"+ WXPayUtil.mapToXml(reultMap));
		} catch (Exception e) {
			resultMap.put("return_code", "FAIL");
			resultMap.put("return_msg", "失敗");
			LOGGER.info("WXController.responseResult--微信通知支付结果接口出现异常!");
			LOGGER.error(e.getMessage(), e);
		}
		String responseWXxml=WXPayUtil.mapToXml(reultMap);
		LOGGER.info("WXController.responseResult--微信通知支付结果接口,回推微信报文为:"+responseWXxml);
		return responseWXxml;
	}

	
}
