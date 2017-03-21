<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>智能平台系统</title>
<style>
		.btn {
			position: relative;
			top:10px;
			left: 688px;
			display: inline-block;
			padding: 6px 12px;
			margin-bottom: 0;
			font-size: 14px;
			font-weight: 400;
			line-height: 0.8;
			text-align: center;
			white-space: nowrap;
			vertical-align: middle;
			-ms-touch-action: manipulation;
			touch-action: manipulation;
			cursor: pointer;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;
			background-image: none;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #fff;
			background-color: #3c8dbc;
		}
		 .change , .delete{
		 top:10px;
			left: 890px;
			display: inline-block;
			padding: 6px 12px;
			margin-bottom: 0;
			font-size: 14px;
			font-weight: 400;
			line-height: 0.8;
			text-align: center;
			white-space: nowrap;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #fff;
			background-color: #3c8dbc;
			cursor: pointer;
			margin:10px 10px;
		 }
	</style>
</head>

<body>
	<!-- Container -->
	<div id="container">
		<!-- Header -->
		<jsp:include page="firm_admin_header.jsp"></jsp:include>
		<!-- END Header -->
		<div class="clear"></div>
		<!-- Content -->
		<div id="content">
			<h2 class="top-title">设备详细信息</h2>
			<form method="post" action="#" name="form">
			<s:if test="#session.deviceInfo.size()>0">
							<s:iterator value="#session.deviceInfo" status="st" id="pdl">
						
				<div class="clear"></div>
				<!-- Clear floatting -->
				<table class="list-table">
					<tbody>
						<tr>
							<td width="60">设备名称:</td>
							<td width="350"><s:property value="#pdl.devicename"/></td>
							<td width="60">设备类型:</td>
							<td width="350"><s:property value="#pdl.type"/></td>
						</tr>
						<tr>
							<td>上传时间:</td>
							<td><s:property value="#pdl.date"/></td>
							<td>所属公司:</td>
							<td><s:property value="#pdl.name"/></td>
						</tr>

						<tr>
							<td>审核时间:</td>
							<<td><s:property value="#pdl.checkdate"/></td>
						</tr>
								</s:iterator>
                		</s:if>
					</tbody>
				</table>
				
			</form>
			<input type="submit" name="删除" class="delete" value="删除设备"/><input type="submit" name="更改" class="change" value="更改设备"/>
			<a href="../Company/firm_Admin_Device.jsp"><input type="button" class="btn" value="返回"/></a>
			<hr />
			<!-- Divider -->

			<!-- Pagination -->
			<!-- End Pagination -->
		</div>
		<!-- END Content -->
	</div>
	<!-- END Container -->
	<div class="clear"></div>

		<div id="footer-container" style="margin-top: 60px">
        <div id="footer">
        	<span style="position:absolute;top:60px;font-size: 13px;color: white;">Investor Relations | 关于我们 | 安全保障 | 战略合作：MSN中文网 | Copyright ©.All Rights Reserved. 京ICP证080047号 京公网安备110000000006号</span>
        </div>
 	</div>


	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>

