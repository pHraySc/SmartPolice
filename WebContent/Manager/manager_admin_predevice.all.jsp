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
			<li><a href="#">前端设备管理</a></li>
			<li><a href="#">前端设备管理</a></li>
			<li class="active">设备详细信息</li>
		</ol>
		<div class="son-table">
			<table class="table">
				<caption> 详 细 信 息</caption>
				
				<tbody>
				<s:if test="#session.allPredeviceInfo.size()>0">
							<s:iterator value="#session.allPredeviceInfo" status="firmuser" id="fs">
					<tr class="active">
						<td>设备名称：<s:property value="devicename"/></td>
					</tr>
					<tr class="active">
						<td>设备条形码：<s:property value="code"/></td>
	
					</tr>
					<tr class="success">
						<td>设备登记时间：<s:property value="date"/></td>
					</tr>
					<tr class="success">
						<td>设备所属厂商：<s:property value="name"/></td>
					</tr>
					<tr class="warning">
						<td>设备备注：<s:property value="demo"/></td>

					</tr>
					
						</s:iterator>
							</s:if>
				</tbody>
			</table>
			<a href="managerAdminDevice.action" target="right"><button class="btn btn-info">返 回</button></a>
		</div>

	</body>

	</html>