<%@ page contentType="text/html;charset=UTF-8" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<head>
	<base href="<%=basePath%>">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<title>登陆</title>
	<style>
	BODY{PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; FONT-FAMILY: "Arial"; BACKGROUND: #3c3c3c; COLOR: #222; FONT-SIZE: 13px; PADDING-TOP: 0px}
	.hide{DISPLAY: none}
	IMG{BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; BORDER-TOP: 0px; BORDER-RIGHT: 0px}
	#logo{TEXT-ALIGN: center; PADDING-BOTTOM: 10px; MARGIN: 80px auto 0px; WIDTH: 247px}
	#login{BACKGROUND-IMAGE: url(images/bg-login.png); PADDING-BOTTOM: 20px; MARGIN: 0px auto; PADDING-LEFT: 20px; WIDTH: 247px; PADDING-RIGHT: 20px; PADDING-TOP: 20px; border-radius: 4px 4px 4px 4px}
	#login FORM{PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-LEFT: 0px; PADDING-RIGHT: 0px; PADDING-TOP: 0px}
	#login P{MARGIN: 10px 0px}
	.control-group{MARGIN-BOTTOM: 10px}
	.control-group .icon-user{BORDER-BOTTOM: #cccccc 1px solid; BORDER-LEFT: #cccccc 1px solid; PADDING-BOTTOM: 4px; LINE-HEIGHT: 20px; PADDING-LEFT: 5px; WIDTH: 16px; PADDING-RIGHT: 5px; DISPLAY: block; BACKGROUND: url(images/bg-loginicons.png) -1px 0px; FLOAT: left; HEIGHT: 20px; OVERFLOW: hidden; BORDER-TOP: #cccccc 1px solid; MARGIN-RIGHT: -1px; BORDER-RIGHT: #cccccc 1px solid; PADDING-TOP: 4px; border-radius: 4px 0 0 4px}
	.control-group .icon-lock{BORDER-BOTTOM: #cccccc 1px solid; BORDER-LEFT: #cccccc 1px solid; PADDING-BOTTOM: 4px; LINE-HEIGHT: 20px; PADDING-LEFT: 5px; WIDTH: 16px; PADDING-RIGHT: 5px; DISPLAY: block; BACKGROUND: url(images/bg-loginicons.png) -1px 0px; FLOAT: left; HEIGHT: 20px; OVERFLOW: hidden; BORDER-TOP: #cccccc 1px solid; MARGIN-RIGHT: -1px; BORDER-RIGHT: #cccccc 1px solid; PADDING-TOP: 4px; border-radius: 4px 0 0 4px}
	.control-group INPUT{BORDER-BOTTOM: #cccccc 1px solid; BORDER-LEFT: #cccccc 1px solid; PADDING-BOTTOM: 4px; LINE-HEIGHT: 20px; BACKGROUND-COLOR: #ffffff; PADDING-LEFT: 6px; WIDTH: 206px; PADDING-RIGHT: 6px; DISPLAY: inline-block; FONT-FAMILY: "Helvetica Neue", Helvetica, Arial, sans-serif; MARGIN-BOTTOM: 10px; HEIGHT: 20px; COLOR: #555555; MARGIN-LEFT: 0px; FONT-SIZE: 14px; VERTICAL-ALIGN: middle; BORDER-TOP: #cccccc 1px solid; BORDER-RIGHT: #cccccc 1px solid; PADDING-TOP: 4px; border-radius: 0 4px 4px 0; box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset; transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s}
	.control-group INPUT:focus{Z-INDEX: 2; OUTLINE-STYLE: dotted; OUTLINE-COLOR: invert; OUTLINE-WIDTH: thin; box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6); -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6); -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6)}
	.login-btn INPUT{BORDER-BOTTOM: 1px solid; BORDER-LEFT: 1px solid; PADDING-BOTTOM: 4px; LINE-HEIGHT: 20px; BACKGROUND-COLOR: #f56c06; MARGIN: 0px auto; PADDING-LEFT: 12px; WIDTH: 80px; PADDING-RIGHT: 12px; DISPLAY: block; BACKGROUND-REPEAT: repeat-x; COLOR: #ffffff; FONT-SIZE: 13px; BORDER-TOP: 1px solid; CURSOR: pointer; BORDER-RIGHT: 1px solid; PADDING-TOP: 4px; border-radius: 4px 4px 4px 4px; box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2) inset, 0 1px 2px rgba(0, 0, 0, 0.05); text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25)}
	.login-btn INPUT:hover{BACKGROUND-COLOR: #fa7716}
	#login-copyright{TEXT-ALIGN: center; PADDING-BOTTOM: 0px; MARGIN: 0px auto; PADDING-LEFT: 10px; WIDTH: 250px; PADDING-RIGHT: 10px; COLOR: #999999; FONT-SIZE: 11px; PADDING-TOP: 10px}
	#login-copyright A{COLOR: #999999; TEXT-DECORATION: none}
	#login-copyright A:hover{BORDER-BOTTOM: #fff 1px dashed; COLOR: #fff; TEXT-DECORATION: none}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.5/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.5/jquery.form.js"></script>
	<script type="text/javascript">
	    if (window.top!=window.self){
	        window.top.location="${pageContext.request.contextPath}/login.jsp"
	    }
		$(function(){
			$("#loginform").ajaxForm({
				beformSubmit: function(){
					if($("#account").val() == "" || $("#password").val() == ""){
						$("#message").html("用户名和密码不能为空");
						return false;
					}
				},
				success: function(jsonObj){
					if(jsonObj.success){
						document.location.href="${pageContext.request.contextPath}/login/main.do";
					}else{
						$("#message").html(jsonObj.message);
					}
				},
				error: function(){
					$("#message").html("登陆失败，请联系管理员处理！");
				}
			});
		});
	</script>
</head>
<body>
	<div style="margin-top: 85.5px;" id="logo"><img alt="HongCMS" src="images/logo-login.png"></div>
	<div id="login">
	<form id="loginform" method="post" action="${pageContext.request.contextPath}/login/login.do">
		<p id="info">请输入用户名和密码</p>
		<div class="control-group"><span class="icon-user"></span><input id="account" name="account" type="text"></div>
		<div class="control-group"><span class="icon-lock"></span><input id="password" name="password" type="password"></div>
		<div><span id="message" style="font-size: 13px; color: red" ></span></div>
		<div class="login-btn"><input id="login-btn" value="登 录" name="submit" type="submit"></div>
	</form>
	</div>
	<div id="login-copyright">珍品网 zhenpin.com</div>
</body></html>