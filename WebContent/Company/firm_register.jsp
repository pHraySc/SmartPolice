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
	<script src="../js/check2.js"></script>
	<script>
		function GetLocalIPAddress() {
			var msg="${backMsg}";
			if(msg!=null&&msg!=""){
				alert(msg);
			}
			
			document.getElementById("ip").value = ILData[0];
			
		}
	</script>
	<style>
		*{
			font-family: '微软雅黑';
		}
		strong{
			position: relative;
			top: -5px;
		}
		.btn{
			position: relative;
			height: 30px;
			width: 70px;
			left: 320px;
			margin-left: 30px;
			display: inline-block;
			padding: 6px 12px;
			margin-bottom: 0;
			font-size: 14px;
			font-weight: 400;
			line-height: 0.8;
			text-align: center;
			white-space: nowrap;
			vertical-align: middle;
			-ms-touch-action: manipulation;
			touch-action: manipulation;
			cursor: pointer;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;
			background-image: none;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #fff;
			background-color: #3c8dbc;
		}
		.btn-info{
			background-color: #0099CC;
		}
		.btn-gray{
			background-color: gray;
		}
		.warning{
			color: red;
			font-size: 13px;
		}
	</style>
</head>

<body id="login-body" onload="GetLocalIPAddress()">	

	<div id="login-title">
        
        <p>欢迎登入智能平台系统</p>
    </div>
	
    <form action="firmregister.action" method="post" id="login-form" enctype="multipart/form-data">
        <div id="login-inner">
            <div id="wrapper">
                <p>智能平台账户注册</p>
            </div>
            <label style="color:red;"><s:property value="#request.error"/></label>
            <div class="field-separator">
                <strong>用户名称：</strong>
                <input type="text" name="username" class="txt" id="name" onblur="checkName();"/><br><span class="warning" id="nameinfo"></span>
            </div>
            <div class="field-separator">
                <strong>真实姓名：</strong>
                <input type="text" name="name" class="txt" id="name" onblur="checkName();"/><br><span class="warning" id="nameinfo"></span>
            </div>
            <div class="field-separator">
                <strong>用户密码：</strong>
                <input type="password" name="pwd"class="txt" id="pwd" onblur="checkPassword();"/><br><span class="warning" id="passwordinfo"></span>
            </div>
            <div class="field-separator">
                <strong>重复密码：</strong>

                <input type="password" name="pwd1"class="txt" id="pwd1" onblur="checkRepassword();"/><br><span class="warning" id="repasswordinfo"></span>

            </div>
            <div class="field-separator">
                <strong>电子邮箱：</strong>

                <input type="text" name="email" class="txt" id="email" onblur="checkEmail();"/><br><span class="warning" id="emailinfo"></span>
            </div>
            <div class="field-separator">
                <strong>联系方式：</strong>

                <input type="text" name="number" class="txt" id="number" onblur="checkTel();"/><br><span class="warning" id="telinfo"></span>

            </div>
            <div class="field-separator">
                <strong>所属厂商 ：</strong>

                <input type="text" name="companyid" class="txt" id="companyid" onblur="checkAddress();"/><br><span class="warning" id="addressinfo"></span>

            </div>
            <div class="field-separator">
                <strong>职位描述：</strong>

                <input type="text" name="position"class="txt" id="position" onblur="checkPerson();"/><br><span class="warning" id="personinfo"></span>

            </div>
           <%--  <div class="field-separator">
                <strong>营业执照：</strong>
                <input type="file" name="uploadFile" />
            </div>
            <div class="field-separator">
                <strong>产品文本：</strong>
                <input type="text" name="companypro" />
            </div> --%>
        </div>
        <a href="../Company/firm_login.jsp"><input type="button" value="返回" class="btn btn-gray"></a>
        <button class="btn btn-info">注 册</button>
    </form>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.4.custom.min.js"></script>
	<script type="text/javascript" src="js/custom.js"></script>
</body>
</html>

