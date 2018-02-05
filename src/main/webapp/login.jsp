<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/include.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="/common/ycb.ico">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui1.4.5/jquery.min.js"></script>
<title>易创吧后台管理系统</title>

<!-- Bootstrap core CSS -->
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<Style>
		@
		-ms-viewport {
			width: device-width;
		}
		
		@
		-o-viewport {
			width: device-width;
		}
		
		@
		viewport {
			width: device-width;
		}
		
		body {
			padding-top: 40px;
			padding-bottom: 40px;
			background-color: #eee;
		}
		
		.form-signin {
			max-width: 330px;
			padding: 15px;
			margin: 0 auto;
		}
		
		.form-signin .form-signin-heading, .form-signin .checkbox {
			margin-bottom: 10px;
		}
		
		.form-signin .checkbox {
			font-weight: normal;
		}
		
		.form-signin .form-control {
			position: relative;
			height: auto;
			-webkit-box-sizing: border-box;
			-moz-box-sizing: border-box;
			box-sizing: border-box;
			padding: 10px;
			font-size: 16px;
		}
		
		.form-signin .form-control:focus {
			z-index: 2;
		}
		
		.form-signin input[type="email"] {
			margin-bottom: -1px;
			border-bottom-right-radius: 0;
			border-bottom-left-radius: 0;
		}
		
		.form-signin input[type="password"] {
			margin-bottom: 10px;
			border-top-left-radius: 0;
			border-top-right-radius: 0;
		}
</style>

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="container" style="margin-top:100px">
		<form class="form-signin">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="inputEmail" class="sr-only">user name</label> 
			<input  type="text" id="inputEmail" class="form-control" placeholder="user name" required autofocus> 
			<label for="inputPassword" class="sr-only">Password</label> 
			<input 	type="password" id="inputPassword" class="form-control" placeholder="Password" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="button" onclick="login()">Sign
				in</button>
		</form>

	</div>
</body>
<script type="text/javascript" charset="utf-8">
function login(){
	var loginname=$("#inputEmail").val();
	var password=$("#inputPassword").val();
	if(loginname == null || loginname == '' || loginname == undefined){
		alert("请输入用户名!")
		return;
	}
	if(password == null || password == '' || password == undefined){
		alert("请输入密码!")
		return;
	}
	var data= "loginname="+loginname+"&password="+password;
	alert(data)
	$.ajax({
		   type: "POST",
		   url: "<%=request.getContextPath()%>/login/login.do",
		   data: data,
		   success: function(msg){
			   if(msg.code == "true"){
				   window.location.href="<%=request.getContextPath()%>/main/main.do";
			   }else{
				   	alert("用户名或者密码错误!")
			   }
		   },
		   error:function(e){
			   console.log(e);
			   alert("系统异常请联系管理员!")
		   }
	});
}
</script>
</html>