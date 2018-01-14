<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/include.jsp"%>
<html>
<head>
<title>Title</title>
</head>
<body>
	<div class="easyui-panel" title="数据列表" 	style="width: 100%; height:500px;">
		<table id="">
			<tr>
				<td>appid</td>
				<td>${appid}</td>
			</tr>
			<tr>
				<td>secret</td>
				<td>${secret}</td>
			</tr>
			<tr>
				<td>expires_in</td>
				<td>${expiresIn}</td>
			</tr>
			<tr>
				<td>access_token</td>
				<td>${accessToken}</td>
			</tr>
		</table>
	</div>
	
	<div id="tb">
		<a href="javascript:deleteMachineArr()" class="easyui-linkbutton"
			data-options="iconCls:'icon-help',plain:true">删除</a>
	</div>


	<script type="text/javascript">


  


    $(function(){
    	$.ajax({
    	      url:"http://127.0.0.1:8080/eh/area/selectArea.do",    //请求的url地址
    	      dataType:"json",   //返回格式为json
    	      async:true,//请求是否异步，默认为异步，这也是ajax重要特性 
    	      type:"post",   //请求方式
    	      success:function(req){
    	          //请求成功时处理
    	          console.log(req);    
    	              
    	      },
    	      error:function(){
    	          //请求出错处理
    	      }
    	      });
		})
	</script>

</body>
</html>

