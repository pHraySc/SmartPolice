<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<script src="js/jquery-1.8.0.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="css/login.css"/>
		<script src="js/check.js"></script>
		<title></title>
		<script>
			$(document).ready(function(){
 				$(".btn-reg").click(function(){
    				$(".login-box").slideUp(600);
    				$(".login-img").hide(600,function(){
    					$(".reg-box").delay("150").slideDown(500);
    				});
  				});
  				$(".back").click(function(){
    				$(".reg-box").slideUp(600,function(){
    					$(".login-box").delay("300").slideDown(600);
    					$(".login-img").delay("150").slideDown(600);
    				});
  				});
			});
		</script>
	</head>

	<body>
		<div class="page">
			<img src="img/u0.png" width="100%"/>
			<div class="box">
			<div class="login-img">
					<img src="img/security.png" />
			</div>
			<div class="login-box">
				<div class="login-box-w">
					<form class="bs-example bs-example-form" role="form" action="manager-login.action" method="post">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-user" style="color: lightslategray; font-size: 19px;"></span></span>
							<input type="text" name="username" class="form-control" placeholder="请输入用户名..." id="name_l" onblur="checkName_l();"/><span class="warning-l" id="nameinfo_l"></span>
						</div>
						<br>
						<div class="input-group" style="margin-top: 10px;">
							<span class="input-group-addon"><span class="glyphicon glyphicon-lock" style="color: lightslategray; font-size: 19px;"></span></span>
							<input type="password" name="password" class="form-control" placeholder="请输入密码..." id="pwd_l" onblur="checkPassword_l();"/><span class="warning-l" id="passwordinfo_l"></span>
						</div>
						<button type="submit" class="btn btn-login">
							<span style="font-size: 16px;">登 &nbsp;录</span>
						</button>
					</form>
				</div>
				<div class="forget">
					<label class="checkbox-inline">
						<input type="checkbox" id="inlineCheckbox1" value="option1">
						<span style="color: white;">记住我的登录信息</span>
					</label>
				</div>

<!-- 				<div class="registered">
					<button type="button" class="btn btn-reg">
						<span style="font-size: 16px;">现在注册</span>
					</button>
				</div> -->
			</div>
			</div>

			<div class="reg-box">
				<div class="reg-box-w">
					<form class="form-horizontal" role="form" action="register.action" method="post">
						<div class="form-group">
							<span style="position: relative; top: 25px; left: -55px; font-size: 15px;color: white;">用户名 :</span>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="name" id="name" onblur="checkName();"/><span class="warning" id="nameinfo"></span>
							</div>
						</div>
						<div class="form-group">
							<span style="position: relative; top: 25px; left: -53px; font-size: 15px;color: white;">密 &nbsp;&nbsp;码 :</span>
							<div class="col-sm-10">
								<input type="password" class="form-control" name="pwd" id="pwd" onblur="checkPassword();"/><span class="warning" id="passwordinfo"></span>
							</div>
						</div>
						<div class="form-group">
							<span style="position: relative; top: 26px; left: -72px; font-size: 15px;color: white;">确认密码 :</span>
							<div class="col-sm-10">
								<input type="password" class="form-control" name="pwd1" id="pwd1" onblur="checkRepassword();"/><span class="warning" id="repasswordinfo"></span>
							</div>
						</div>
						<div class="form-group">
							<span style="position: relative; top: 25px; left: -55px; font-size: 15px;color: white;">邮 &nbsp;&nbsp;箱 :</span>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="email" id="email" onblur="checkEmail();"/><span class="warning" id="emailinfo"></span>
							</div>
						</div>
						<div class="form-group">
							<span style="position: relative; top: 25px; left: -58px; font-size: 15px;color: white;">身份证 :</span>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="idcard" id="idcard" onblur="checkIdcard();"/><span class="warning" id="idcardinfo"></span>
							</div>
						</div>
						<div class="form-group">
							<span style="position: relative; top: 25px; left: -74px; font-size: 15px;color: white;">联系方式 :</span>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="number" id="number" onblur="checkTel();"/><span class="warning" id="telinfo"></span>
							</div>
						</div>
						<div class="form-group">
							<span style="position: relative; top: 25px; left: -74px; font-size: 15px;color: white;">出生年月 :</span>
							<div class="col-sm-10">
								<input type="date" class="form-control" name="birthday">
							</div>
						</div>
						<div class="back">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="button" class="btn btn-back">返 &nbsp;回</button>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-reg2">现在注册</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>

</html>