<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%
	String path = request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<script src="js/jquery-1.8.0.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="css/left.css"/>
		<script src="js/left.js"></script>
		<title>智能安防系统</title>
		
<script type="text/javascript">

function getUntreatedInformationNum(){ 
		var action = "action=yes" ;
		/* 调用sendRequest方法向后台传递InputUser，并接受返回值显示到对应提示信息位置  */
		sendRequest(
				"POST",
				"getUntreatedInformationNum",
				true,
				action,
				function() {
					if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
						var res = xmlhttp.responseText;//显示返回信息
// 						var num = parseInt(res);
						document.getElementById("msgNum").innerHTML=res; 
					}
					var m = $("#msgNum").text();
					if(m>0){
						$("#msgNum").css("color","orangered");
					}
				});
}

function sendRequest(method, url, isAsyns, acc, action) {
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();//新浏览器  IE7+, Firefox, Chrome, Opera, Safari 
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");//老板浏览器IE6, IE5 
		}
		xmlhttp.open(method, url, isAsyns);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");//这行代码很关键，用来把字符串类型的参数序列化成Form Data  
		xmlhttp.send(acc);
		xmlhttp.onreadystatechange = action;
}
setTimeout('getUntreatedInformationNum()',10000);

</script>
		
		
	</head>

	<body onload="getUntreatedInformationNum()">
		<div class="head">
		<img alt="logo" src="img/lock.png">
			<span>智能监控系统</span>
		</div>
		<div class="head-user">
			<img src="img/avatar5.png" style="border-radius: 30px; width: 45px; height: 45px; position: relative;left: 35px; top: 5px; border: white 2px solid;" />
			<span><s:property value="#session.username"/></span>
			<i class="glyphicon glyphicon-comment"></i>
				<s:if test="#session.managermsg.size()>0">
										<s:iterator value="#session.managermsg" status="st" id="pdl">
			<a href="toMessageManagePage" target="right">消息  (<s:property value="#pdl.a"/> )</a>
				</s:iterator>
									</s:if>
		</div>
		<div class="area">
			<a href="index.jsp"><i class="glyphicon glyphicon-home"></i>主 页</a>&nbsp; <a>|</a> &nbsp;<a href="manager-logout.action"><i class="glyphicon glyphicon-off"></i>退 出</a></span>
		</div>
		<div class="menu">
			<div class="menu-head">
				<span><i class="glyphicon glyphicon-list"></i>功能菜单</span>
			</div>
			<div class="menu-l">
				<ul id="menu1" class="menu-list-l">
					<div class="hide-box"></div>
					<li class="menu-list"><a href="###" class="menu-a">厂商信息管理<i class="G glyphicon glyphicon-chevron-right"></i></a>
						<ul id="menu1-son" class="menu-son-up">
							<li class="click"><a href="managerAuditCompany" target="right">厂商注册审核</a></li>
							<li class="click"><a href="managerAdminCompany" target="right">厂商信息管理</a></li>
							<li class="click"><a href="managerStatCompany" target="right">注册厂商统计</a></li>
						</ul>
					</li>
					<div class="hide-box"></div>
					<li class="menu-list"><a href="###" class="menu-a">前端设备管理<i class="G glyphicon glyphicon-chevron-right"></i></a>
						<ul id="menu2-son" class="menu-son-up">
							<li class="click"><a href="managerAuditDevice" target="right">前端设备审核</a></li>
							<li class="click"><a href="managerAdminDevice" target="right">前端设备管理</a></li>
							<li class="click"><a href="managerStatDevice" target="right">设备信息统计</a></li>
						</ul>
					</li>
					<div class="hide-box"></div>
					<li class="menu-list"><a href="###" class="menu-a">移动监控管理<i class="G glyphicon glyphicon-chevron-right"></i></a>
						<ul id="menu3-son" class="menu-son-up">
							<li class="click"><a href="managerAuditMoveDevice" target="right">移动监控审核</a></li>
							<li class="click"><a href="managerAdminMoveDevice" target="right">移动监控管理</a></li>
							<li class="click"><a href="managerStatMoveDevice" target="right">移动监控统计</a></li>
						</ul>
					</li>
					<div class="hide-box"></div>
					<li class="menu-list"><a href="###" class="menu-a">注册用户管理<i class="G glyphicon glyphicon-chevron-right"></i></a>
						<ul id="menu4-son" class="menu-son-up">
							<li class="click"><a href="managerAdminUserinfo" target="right">用户信息管理</a></li>
							<li class="click"><a href="managerStatUserinfo" target="right">注册用户统计</a></li>
						</ul>
					</li>
					<div class="hide-box"></div>
					<li class="menu-list"><a href="###" class="menu-a"> 系统信息管理<i class="G glyphicon glyphicon-chevron-right"></i></a>
						<ul id="menu1-son" class="menu-son-up">
							<li class="click"><a href="Manager/manager_stat_logindev.jsp" target="right">登录设备信息</a></li>
							<li class="click"><a href="Manager/manager-stat_loginuser.jsp" target="right">登录用户信息</a></li>
							<li class="click"><a href="showSubDeviceClassChart" target="right">系统运行信息</a></li>
						</ul>
					</li>
					<div class="hide-box"></div>
					<li class="menu-list"><a href="###" class="menu-a">服务器管理 &nbsp;<i class="G glyphicon glyphicon-chevron-right"></i></a>
						<ul id="menu4-son" class="menu-son-up"> 
							<li class="click"><a href="managerAdminWebservice" target="right">服务器信息管理</a></li>
							<li class="click"><a href="addservice.jsp" target="right">增加服务器</a></li>
						</ul>
					</li> 
				 	<!-- <div class="hide-box"></div>
					<li class="menu-list"><a href="###" class="menu-a">前端映射管理<i class="G glyphicon glyphicon-chevron-right"></i></a>
						<ul id="menu6-son" class="menu-son-up">
							<li class="click"><a href="toAppuserDeviceManagePage" target="right">用户前端映射管理</a></li>
						</ul>
					</li>
					<div class="hide-box"></div>
					<li class="menu-list"><a href="###" class="menu-a">前端设备管理<i class="G glyphicon glyphicon-chevron-right"></i></a>
						<ul id="menu5-son" class="menu-son-up">
							<li class="click"><a href="showDeviceNumStatisticsChart" target="right">前端及附属设备统计</a></li>
							<li class="click"><a href="infoDevice.action" target="right">设备基本信息管理</a></li>
							<li class="click"><a href="showDeviceLineStateChart" target="right">前端设备状态统计</a></li>
							<li class="click"><a href="showSubDeviceClassChart" target="right">附属设备分类统计</a></li>
						</ul>
					</li>  -->
					<div class="hide-box"></div>
					<li class="menu-list"><a href="###" class="menu-a"> 消 息 管 理  &nbsp;<i class="G glyphicon glyphicon-chevron-right"></i></a>
						<ul id="menu1-son" class="menu-son-up">
							<li class="click"><a href="managerAdminMsg_alarm" target="right">报警消息管理</a></li>
							<li class="click"><a href="managerAdminMsg_chat" target="right">聊天消息管理</a></li>
							<li class="click"><a href="managerAdminMsg_notice" target="right">通知消息管理</a></li>
						</ul>
					</li> 
					<div class="hide-box"></div>
					<li class="menu-list"><a href="###" class="menu-a"> 平 台 管 理 <i class="G glyphicon glyphicon-chevron-right"></i></a>
						<ul id="menu4-son" class="menu-son-up">
							<li class="click"><a href="managerAdmincomment" target="right">反馈信息管理</a></li>
							<li class="click"><a href="notice.jsp" target="right">平台通知发布</a></li>
						</ul>
					</li>
					
				</ul>
			</div>
		</div>
		<div class="right">
			<iframe src="Manager/first.jsp" name="right" frameborder="0" scrolling="no" width="100%" height="900px"></iframe>
		</div>
		<div class="bottom">
			<span>Investor Relations | 关于我们 | 安全保障 | 战略合作：MSN中文网 | Copyright ©.All Rights Reserved. 京ICP证080047号 京公网安备110000000006号</span>
		</div>
	</body>

</html>