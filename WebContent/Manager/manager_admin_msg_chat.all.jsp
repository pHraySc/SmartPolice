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
			<li><a href="#">聊天信息管理</a></li>
			<li class="active">聊天详细信息</li>
		</ol>
		<div class="son-table">
			<table class="table">
				<caption> 详 细 信 息</caption>
				
				<tbody>
				<s:if test="#session.allchatmsgInfo.size()>0">
							<s:iterator value="#session.allchatmsgInfo" status="firmuser" id="fs">
					<tr class="active">
						<td>生成时间：<s:property value="sendtime"/></td>
					</tr>
					<tr class="active">
				
						<td>接收者类型：<s:property value="RECVTYPE"/></td>
					</tr>
					<tr class="success">
	
						<td>消息类容：<s:property value="content"/></td>
					</tr>
					
					
						</s:iterator>
							</s:if>
				</tbody>
			</table>
			<a href="managerAdminMsg_chat.action" target="right"><button class="btn btn-info">返 回</button></a>
		</div>

	</body>

	</html>