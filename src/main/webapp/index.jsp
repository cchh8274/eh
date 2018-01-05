<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>XXX系统首页</title>
<%
	com.ycb.entity.business.SessionInfo sessionInfo = (com.ycb.entity.business.SessionInfo) session.getAttribute("sessionInfo");
	if (sessionInfo != null) {//说明 登录成功
		//后台 主页面 
		response.sendRedirect(request.getContextPath()+"/main/main.action");
	} else {//说明 用户没有登录
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
%>
</head>
<body>
</body>
</html>