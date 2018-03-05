package com.ycb.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.service.WxOrderService;
/**
 *
 * @author chenghui
 * 同步微信订单的用户
 */
@Service
public class SyncWxUserJob {
	@Autowired
	private  WxOrderService  wxOrderService;
	
	public  void jobs(){
			
		List<String> list=wxOrderService.queryUsergroupby();
		
		
	}
}
