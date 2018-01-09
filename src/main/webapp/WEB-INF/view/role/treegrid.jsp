<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table id="tt" style="width:600px;height:400px"></table>  

<script type="text/javascript">
$('#tt').treegrid({    
    url:'<%=request.getContextPath()%>/resource/getResourceTree.do',    
    idField:'id',    
    treeField:'name',    
    columns:[[    
        {title:'Task Name',field:'name',width:180},
        {field:'id',title:'Persons',width:60,align:'right'},    
        {field:'text',title:'Begin Date',width:80},    
        {field:'parentId',title:'parentId',width:80},    
        {field:'state',title:'state',width:80},    
        {field:'iconCls',title:'iconCls',width:80},   
        {field:'children',title:'children',width:80},     
    ]]    
});  
</script>
</body>
</html>