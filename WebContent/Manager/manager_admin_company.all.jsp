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
			<li><a href="#">厂商信息管理</a></li>
			<li class="active">厂商详细信息</li>
		</ol>
		<div class="son-table">
			<table class="table">
				<caption> 详 细 信 息</caption>
				
				<tbody>
				<s:if test="#session.allCompanyInfo.size()>0">
							<s:iterator value="#session.allCompanyInfo" status="firmuser" id="fs">
					<tr class="active">
						<td>厂商名称：<s:property value="name"/></td>
					</tr>
					<tr class="active">
						<td>联系电话：<s:property value="number"/></td>
						<td>厂商邮箱：<s:property value="email"/></td>
					</tr>
					<tr class="success">
						<td>厂商类型：<s:property value="type"/></td>
						<td>厂商地址：<s:property value="address"/></td>
					</tr>
					<tr class="warning">
						<td>厂商备注：<s:property value="demo"/></td>

					</tr>
					<tr class="danger">
						<td height="250" colspan="2">厂商logo：
						<br><img width="600" src="<s:property value="logo"/>" /></td>
					</tr>
						</s:iterator>
							</s:if>
				</tbody>
			</table>
			<a href="managerAdminCompany.action" target="right"><button class="btn btn-info">返 回</button></a>
		</div>

	</body>

	</html>