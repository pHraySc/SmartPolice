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
			<li><a href="#">平台管理</a></li>
			<li><a href="#">反馈信息管理</a></li>
			<li class="active">反馈消息详情</li>
		</ol>
		<div class="son-table">
			<table class="table">
				<caption> 详 细 信 息</caption>
				
				<tbody>
				<s:if test="#session.allcommentInfo.size()>0">
							<s:iterator value="#session.allcommentInfo" status="firmuser" id="fs">
					<tr class="active">
						<td>标题：<s:property value="title"/></td>
					</tr>
					<tr class="active">
				
						<td>是否处理：<s:property value="handle"/></td>
					</tr>
					<tr class="success">
	
						<td>处理意见：<s:property value="reply"/></td>
					</tr>
					<tr class="active">
						<td>处理时间：<s:property value="replydate"/></td>
					</tr>
					<tr class="active">
						<td>处理人员编号：<s:property value="replyid"/></td>
					</tr>
					<tr class="active">
						<td>反馈类容：<s:property value="content"/></td>
					</tr>
					
						</s:iterator>
							</s:if>
				</tbody>
			</table>
			<a href="managerAdmincomment.action" target="right"><button class="btn btn-info">返 回</button></a>
		</div>

	</body>

	</html>