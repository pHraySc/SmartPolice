<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="UTF-8">
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
			<script src="js/jquery-2.1.4.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<link href="css/son.css" rel="stylesheet" />
		<title>Insert title here</title>
	</head>

	<body>
		<ol class="breadcrumb">
			<li><a href="#">首页</a></li>
			<li><a href="#">注册用户管理</a></li>
			<li><a href="#">用户信息管理</a></li>
			<li class="active">用户详细信息</li>
		</ol>
		<div class="son-table">
			<table class="table">
				<caption> 详 细 信 息</caption>
				
				<tbody>
				<s:if test="#session.allUserInfo.size()>0">
							<s:iterator value="#session.allUserInfo" status="firmuser" id="fs">
					<tr class="active">
						<td>用户账号：<s:property value="username"/></td>
						<td>用户名：<s:property value="name"/></td>
					</tr>
					<tr class="active">
						<td>用户性别：<s:property value="sex"/></td>
						<td>用户状态：<s:property value="state"/></td>
					</tr>
					<tr class="success">
						<td>注册时间：<s:property value="regdate"/></td>
						<td>用户级别：<s:property value="authority"/></td>
					</tr>
					<tr class="warning">
						<td>用户类型：<s:property value="type"/></td>
						<td>用户生日：<s:property value="birth"/></td>

					</tr>
					<tr class="danger">
						<td>接收设置：<s:property value="set"/></td>
					</tr>
						</s:iterator>
							</s:if>
				</tbody>
			</table>
			<a href="managerAdminUserinfo.action" target="right"><button class="btn btn-info">返 回</button></a>
		</div>

	</body>

	</html>