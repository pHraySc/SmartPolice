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
			<li><a href="#">服务器管理</a></li>
			<li><a href="#">服务器信息管理</a></li>
			<li class="active">服务器详细信息</li>
		</ol>
		<div class="son-table">
			<table class="table">
				<caption> 详 细 信 息</caption>
				<s:if test="#session.allServiceInfo.size()>0">
							<s:iterator value="#session.allServiceInfo" status="firmuser" id="fs">
				<tbody>
					<tr class="active">
						<td>服务器类型：<s:property value="type"/></td>
					</tr>
					<tr class="active">
						<td>服务器地址：经度：<s:property value="longitude  "/>纬度：<s:property value="latitude"/></td>
	
					</tr>
					<tr class="success">
						<td>服务器端口:<s:property value="port"/></td>
					</tr>
					<tr class="success">
						<td>服务器IP：<s:property value="ip"/></td>
					</tr>
					<tr class="warning">
						<td>服务器描述：<s:property value="demo"/></td>

					</tr>
					
					</s:iterator>
							</s:if>
				</tbody>
			</table>
			<a href="managerAdminWebservice.action" target="right"><button class="btn btn-info">返 回</button></a>
		</div>

	</body>

	</html>