<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="/common/include.jsp" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table id="maintable"></table>
	


<script type="text/javascript" charset="utf-8">
$(function(){
	
	$("#maintable").datagrid({
		url:'<%=request.getContextPath()%>/user/selectUserList.do',
		method:'post',
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize:2,
		pageList:[2,4,6,8],
		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		idField : 'id',
		loadMsg:'候着。。。',
		toolbar: '#tb',
		columns:[ 
		    [
			     {field:'id',title:'ID',width:120},    
			     {field:'loginname',title:'账户名',width:120},    
			     {field:'name',title:'真实名',width:120},    
			     {field:'createdatetime',title:'创建时间',width:120},    
			     {field:'updatedatetime',title:'修改时间',width:120},    
		    ]    
		] 
	});
	
})
</script>	
</body>
</html>