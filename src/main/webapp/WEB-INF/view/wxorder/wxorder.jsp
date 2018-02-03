<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>微信订单管理</title>
<%@ include file="/common/include.jsp"%>
</head>
<body>
	<div class="easyui-panel" title="微信订单管理" style="width: 100%; height: 70px; margin-bottom: 5px">
	</div>
	<div class="easyui-panel" title="数据列表" 	style="width: 100%; height: 500px;">
		<table id="wxordertable"></table>
	</div>
</body>
<script type="text/javascript" charset="utf-8">
$(function(){
	$("#wxordertable").datagrid({
		url:'<%=request.getContextPath()%>/wxorder/queryall.do',
		method:'post',
		pagination:true,
		rownumbers:true,
		pageNumber:1,
		pageSize:10,
		pageList:[10,20,50,100],
		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		idField : 'id',
		loadMsg:'load...',
		toolbar: '#tb',
		columns:[ 
		    [
			     {field:'id',title:'ID',width:120,checkbox:true},    
			     {field:'loginname',title:'账户名',width:120},    
			     {field:'name',title:'真实名',width:120},    
			     {field:'createdatetime',title:'创建时间',width:120},    
			     {field:'updatedatetime',title:'修改时间',width:120},    
		    ]    
		] 
	})
	
	
	
	
})
</script>
</html>