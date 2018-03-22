package com.ycb.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ycb.dao.ReflectInfoMapper;
import com.ycb.dao.WxorderInfoMapper;
import com.ycb.entity.ReflectInfo;
import com.ycb.entity.WxorderInfo;
import com.ycb.service.DataService;
import com.ycb.util.BankAmountUtils;
import com.ycb.util.IDGeneratorTools;
import com.ycb.util.NameUtil;
@Service
public class DataServcieImpl implements DataService{
	@Autowired
	private WxorderInfoMapper  WxorderInfoMapper;
	@Autowired
	private ReflectInfoMapper reflectInfoMapper;
	
	public void insertDatajia(){
		System.out.println("kaishi ");
		List<String> list=NameUtil.Names();
		int num=0;
		for (int i = 0; i < list.size(); i++) {
			WxorderInfo info =new WxorderInfo();
			info.setOrderNo(IDGeneratorTools.createId());
			info.setPrice("688");
			info.setUnit(ramdomu());
			String ss=ramdomm();
			info.setMachine(ss.split("_")[0]);
			info.setMachinecode(ss.split("_")[1]);
			info.setArea("海淀区");
			info.setPayStatus("支付成功");
			info.setOpenId(NameUtil.englishName()+IDGeneratorTools.createId());
			info.setPaytime(NameUtil.randomDate("2017-12-01", "2018-02-01"));
			if(i<120){
				info.setNum("10");
				info.setUserName(list.get(i));
				info.setTotalFee("6880");
			}else{
				int j=ramdomnum();
				if(j==3){
					num++;
					
					
				}
				if(num == 100){
					info.setNum("1");
					info.setUserName(list.get(i));
					info.setTotalFee("688");
				}else{
					info.setNum(j+"");
					info.setUserName(list.get(i));
					info.setTotalFee(688*j+"");
				}
			}
			WxorderInfoMapper.insert(info);
		}
		
		
		
	}
	
	private String  ramdomu(){
		String[] s={"北京交通大学","北京电影学院"};
		int i=new Random().nextInt(2);
		return s[i];
	}
	
	private String  ramdomm(){
		String[] s={"一号机器_001","二号机器_002","三号机器_003"};
		
		int i=new Random().nextInt(3);
		return s[i];
	}
	private int  ramdomnum(){
		int[] s={1,3};
		
		int i=new Random().nextInt(2);
		return s[i];
	}

	@Override
	public void datauser() {
		String[] str={"2017-12-15","2018-01-15","2018-02-15"};
		List<WxorderInfo> list=WxorderInfoMapper.select();
		for (String string : str) {
			for (WxorderInfo wxorderInfo : list) {
				ReflectInfo  in=new ReflectInfo();
				in.setOrderno(IDGeneratorTools.createId());
				in.setCard(BankAmountUtils.getBrankNumber("8"));
				in.setBankname(ramdomnuBK());
				in.setOpenid(wxorderInfo.getOpenId());
				in.setUsername(wxorderInfo.getUserName());
				in.setSydate(string);
				int i=Integer.valueOf(wxorderInfo.getNum());
				in.setMoney(i*200+"");
				reflectInfoMapper.insert(in);
			}
		}
		
		
		
		
	}
	
	private String  ramdomnuBK(){
		String[] s={"招商银行","建设银行","工商银行","农业银行"};
		
		int i=new Random().nextInt(4);
		return s[i];
	}

}	
