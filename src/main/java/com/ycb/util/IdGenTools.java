package com.ycb.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IdGenTools {
	public static String cteateId(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String date=sdf.format(new Date());
		Random  random=new Random();
		for (int i = 0; i < 5; i++) {
			date+=random.nextInt(1000);
		}
		return date;
	}
}
