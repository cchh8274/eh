package com.ycb.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycb.model.WXParamerVO;
import com.ycb.service.WXPayService;

/**
 * 微信支付调起
 * 
 * @author chenghui
 *
 */
@Controller
@RequestMapping("/ycb/wxpay")
public class WXController {
	@Autowired
	private WXPayService wxPayService;
	
	
	/**
	 * 金额 /数据 机器 
	 * 1.自己订单生成订单
	 * 2，微信请求下单
	 * @return
	 */
	@RequestMapping("/order")
	@ResponseBody
	public Map<String, String> requestWXPay(WXParamerVO vo,HttpServletRequest request) {
		vo.setSpbillcreateip(getIpAddr(request)); //ip地址
		Map<String,String> map=wxPayService.requsetWXpay(vo);
		return map;
	}
	/**
	 * 支付接口响应
	 * @return
	 */
	@RequestMapping("/responseResult")
	@ResponseBody
	public Map<String, String> responseResult() {
		wxPayService.requsetWXpay(null);
		return null;
	} 
	
	/**
	 * 获取当前请求的ip地址
	 * @param request
	 * @return
	 */
    public String getIpAddr(HttpServletRequest request){  
        String ipAddress = request.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                        e.printStackTrace();  
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }  
            }  
            return ipAddress;   
    }
}
