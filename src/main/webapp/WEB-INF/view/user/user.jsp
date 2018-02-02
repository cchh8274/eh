<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<%@include file="/common/include.jsp"%>
</head>
<body>
<div class="easyui-panel" title="系统用户管理" 	style="width: 100%; height: 70px;margin-bottom:5px">
		
</div>
<div class="easyui-panel" title="数据列表" 	style="width: 100%; height:500px;">
		<table id="usertable"></table>
</div>
	
<div id="tb">
		<a href="javascript:deleteMachineArr()" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">删除</a>
</div>
</body>
<script type="text/javascript" charset="utf-8">
$(function(){
	 $("#areaDataGrid").datagrid({
         url:"<%=request.getContextPath()%>/user/queryUserList.do",
				method : 'post',
				pagination : true,
				rownumbers : true,
				fit:true,
				pageNumber : 1,
				pageSize : 10,
				pageList : [ 10, 20, 30, 50 ],
				striped : true,
				rownumbers : true,
				pagination : true,
				singleSelect : true,
				checkOnSelect : false,
				selectOnCheck : false,
				toolbar : '#tb',
				idField : 'id',
				loadMsg : 'load...',
				sortName : 'id',
				toolbar : '#tb',
				columns : [ [ {
					field : '',
					title : 'code',
					checkbox : true,
					width : 120
				}, {
					field : 'id',
					title : 'ID',
					width : 120,
					order : 'desc'
				}, {
					field : 'name',
					title : '区域名称',
					width : 120,
					order : 'desc'
				},
				] ],
			
			})
		})
})	
</script>
</html>
