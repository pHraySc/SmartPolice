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
			<li><a href="#">厂商信息管理</a></li>
			<li><a href="#">厂商信息审核</a></li>
			<li class="active">厂商详细信息</li>
		</ol>
		<div class="son-table">
			<table class="table">
				<caption> 详 细 信 息</caption>
				
				<tbody>
				<s:if test="#session.alluserInfo.size()>0">
							<s:iterator value="#session.alluserInfo" status="firmuser" id="fs">
					<tr class="active">
						<td>厂商用户名：<s:property value="username"/></td>
					</tr>
					<tr class="active">
						<td>厂商Email：<s:property value="email"/></td>
	
					</tr>
					<tr class="success">
						<td>厂商联系方式：<s:property value="number"/></td>
					</tr>
					<tr class="success">
						<td>用户角色：<s:property value="position"/></td>
					</tr>
					<tr class="warning">
						<td>审核状态：<s:property value="state"/></td>

					</tr>
					
						</s:iterator>
							</s:if>
				</tbody>
			</table>
			<a href="managerAuditCompany.action" target="right"><button class="btn btn-info">返 回</button></a>
		</div>

	</body>

	</html>