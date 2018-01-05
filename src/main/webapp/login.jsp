<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="loginDialog" title="用户登录" style="display: none;">
	<form id="loginForm" method="post" >   
		<table>
       <tr>
        <td><label for="name">用户名:</label></td>
       <td> <input class="easyui-validatebox" type="text" name="loginname" data-options="required:true"  missingMessage="请输入用户名"/> </td>  
	</tr>   
	<tr>
        <td><label for="password">密码:</label></td> 
        <td><input class="easyui-validatebox" type="password" name="pwd" data-options="validType:'password'" /></td>   
    </tr>
      <tr>
    	<td><label>验证码:</label></td>
	    	<td><input  class="easyui-validatebox"  data-options="required:true" name="imgcode"   type="text" id="imgcode" style="width:60px"/>
	    	<img src="<%=request.getContextPath()%>/imageCode" id="imgcoode" onclick="getImageCode()">
	    	<label id="countDown"></label></td>
	   		<td><a class="easyui-linkbutton"  href="javascript:getImageCode(countDown)" >看不清换一个</a></td>
    </tr>
		</table>
	</form>
	</div>
	<script type="text/javascript">
	function loginUser(){
      	$.ajax({
        		url:'<%=request.getContextPath()%>/user/checkSysUserLogin.do',
        		type:'post',
        		//同步
        		async:false,
        		data:$("#loginForm").serialize(),
        		success:function(data){
        			//用户不存在返回true
        			if (data.success) {
        				//alert(data.success);
        				window.location.href="<%=request.getContextPath()%>/main/main.do";
    				}else{
    					$.messager.alert('警告',data.msg,"error");    
    				}
        		}
        	})
 }
	
	
			$(function(){
		 		$('#loginDialog').dialog({    
		 		    title: '登录',    
			   		width: 400,    
		 		    height: 200,     
		 		    //不能关闭窗口
		 		//    closed: true,    
		 		    cache: false,    
		 		    //模态窗口
		 		    modal: true,
		 		    top:250, 
		 		    content : $("#loginForm"),
		 		    buttons:[
		 		             {
		 		            	 text:'登录',
		 		            	 iconCls:'',
		 		            	 handler:function(){
		 		            		loginUser();
		 		              }
		 		             },
		 		           ],
		 		  		onOpen : function() {
		 					$('form :input:first').focus();
		 					$('form :input').keyup(function(event) {
		 						if (event.keyCode == 13) {
		 							loginUser();
		 						}
		 					});
		 				}
		 		});    
		 	})
	
	
	
	
			//随机生成验证码图片
	 	function getImageCode(){
			var thisDate =  new Date();
			//区分当前请求和上一次请求
			document.getElementById("imgcoode").src="<%=request.getContextPath()%>/imageCode?f="+thisDate.getTime();
	}
	</script>
</body>
</html>