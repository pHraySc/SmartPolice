<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>智能平台系统</title>
    
    <!-- JQuery UI CSS Framework -->
    <link rel="stylesheet" type="text/css" href="css/ui-lightness/jquery-ui-1.8.4.custom.css"  />
    <!-- End JQuery UI CSS Framework -->
	
    <!-- General Styles common for all pages -->
    <link rel="stylesheet" type="text/css" href="css/globals.css" />
    <link rel="stylesheet" type="text/css" href="css/commons.css" />
    <link rel="stylesheet" type="text/css" href="css/plugins.css" />
    <!-- END General Styles -->
    
    <!-- Current Theme styles. These are changed via theme switcher -->	<link rel="stylesheet" id="active-theme-globals" type="text/css" href="themes/sleek-wood/css/globals.css" />
	<link rel="stylesheet" id="active-theme-commons" type="text/css" href="themes/sleek-wood/css/commons.css" />
	<link rel="stylesheet" id="active-theme-plugins" type="text/css" href="themes/sleek-wood/css/plugins.css" /> <!-- END Current Theme styles -->
	<!--<script type="text/javascript" charset="gb2312" src="http://counter.sina.com.cn/ip/"></script>-->
	<script>
		function GetLocalIPAddress() {
			document.getElementById("ip").value = ILData[0];
		}
	</script>
	<style>
		*{
			font-family: '微软雅黑';
		}
		a{
			text-decoration: none;
			color: #FF6666;
		}
	</style>
</head>

<body id="login-body" onload="GetLocalIPAddress()">	

	<div id="login-title">
        
        <p>智能监控系统</p>
    </div>
	
    <form action="firmLogin.action" method="post" id="login-form">
        <div id="login-inner">
            <div id="wrapper">
                <p>智能监控系统登录</p>
            </div>
            <label style="color:red;"><s:property value="#request.error"/></label>
            <div class="field-separator">
                <label for="login">用户名称</label>
                <input type="text" name="username" id="login" class="txt" />

            </div>
            <div class="field-separator">
                <label for="password">用户密码</label>
                <input type="password" name="password" id="password" class="txt" />
            </div>
            <div id="login-bottom">
                <!--  <div id="remember-me">
                	<a href="#">记住我</a>&#124;&nbsp;
                    <a href="#">忘记密码了</a>
            	</div> -->
                <input type="submit" class="submit large round" id="login-button" value="登录" />
                <span style="font-size:14px;position:relative;top:18px">没 有 账 号 ？ 免 费 <a href="firm_register.jsp">注 册 </a>！  || <a href="findback.jsp"> 忘 记 密 码</a></span>
                <div style="clear: both"></div>
            </div>
        </div>
        <input type="text" id="ip" name="ipinfo" style="display: none;" />
    </form>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.4.custom.min.js"></script>
	<script type="text/javascript" src="js/custom.js"></script>
</body>
</html>

