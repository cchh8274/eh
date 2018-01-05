package com.ycb.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public class PrintUtil {

	public static  void ajaxOut(HttpServletResponse response,String str){
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer =null;
		try {
			 writer = response.getWriter();
			 writer.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (writer!=null) {
				writer.close();
			}
		}
	}
}
