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
			<li><a href="#">消息管理</a></li>
			<li><a href="#">通知信息管理</a></li>
			<li class="active">通知详细信息</li>
		</ol>
		<div class="son-table">
			<table class="table">
				<caption> 详 细 信 息</caption>
				
				<tbody>
				<s:if test="#session.allnoticemsgInfo.size()>0">
							<s:iterator value="#session.allnoticemsgInfo" status="firmuser" id="fs">
					<tr class="active">
						<td>标题：<s:property value="title"/></td>
					</tr>
					<tr class="active">
				
						<td>类容：<s:property value="content"/></td>
					</tr>
					
					
						</s:iterator>
							</s:if>
				</tbody>
			</table>
			<a href="managerAdminMsg_notice.action" target="right"><button class="btn btn-info">返 回</button></a>
		</div>

	</body>

	</html>