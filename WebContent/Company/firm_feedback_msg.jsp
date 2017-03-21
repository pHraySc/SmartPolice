<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>智能信息平台</title>
</head>
<style>
		.btn{
			position: relative;
			left: 830px;
			display: inline-block;
			padding: 6px 12px;
			margin-bottom: 0;
			font-size: 16px;
			font-weight: 800;
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
			background-color: green;
			height:40px;
			width:100px;
		}
		.btn:hover{
		color:red;
		}
		
		#plugname, #uploadPerson, #plugserial{
		width:280px;
		height:40px;
		}
		#plugversion{
		min-height:160px;
		width:800px;
		}
	</style>
<script type="text/javascript">
	function checkInput() {
		var plugname = document.getElementById("plugname").value.replace(/\s/g,
				"");
		var plugversion = document.getElementById("plugversion").value.replace(
				/\s/g, "");
		var uploadPerson = document.getElementById("uploadPerson").value
				.replace(/\s/g, "");
		var plugserial = document.getElementById("plugserial").value.replace(
				/\s/g, "");
		var plug = document.getElementById("plug").value.replace(/\s/g, "");
		var reg = /\.apk/;
		if (plugname.length == 0 || plugversion.length == 0
				|| uploadPerson.length == 0 || plugserial.length == 0
				|| plug.length == 0) {
			$("#msg").text("选项未填写完，请填写完再提交");
			return false;
		} else if (plugname.length > 20) {
			$("#msg").text("app名称长度不能超过20个字符");
			return false;
		} else if (plugversion.length > 100) {
			$("#msg").text("app描述不能超过100个字符");
			return false;
		} else if (uploadPerson.length > 10) {
			$("#msg").text("上传者长度不能超过10个字符");
			return false;
		}  else {
			return true;
		}
/*else if (!reg.test(plug)) {
			alert("亲，请选择一个后缀为.apk的插件");
			return false;
		}  */
	}
</script>
<body>
	<!-- Container -->
	<div id="container">
		<!-- Header -->
		<jsp:include page="firm_admin_header.jsp"></jsp:include>
		<!-- END Header -->
		<div class="clear"></div>
		<!-- Content -->
		<div id="content">
			<h2 class="top-title">意见反馈</h2>
			&nbsp;&nbsp;&nbsp;<span style="color: red" id="msg"></span>
			<form onsubmit="return checkInput();" method="post"  action="f_uploadIntelappInfo.action" enctype="multipart/form-data">
				<div class="clear"></div>
				<!-- Clear floatting -->
				<table class="list-table" id="equip">
					<tbody>
						<tr>
							<td><input id="plugname" type="text" name="plugname"  placeholder="反馈主题"/></td>
						</tr>
						<tr>
							<td><input type="radio" checked="checked"/>建议  <input type="radio" />投诉  <input type="radio" />表扬   <input type="radio" />问题</td>
						</tr>
                         <tr>
							<td><textarea  id="plugversion" type="text" name="plugversion" placeholder="反馈内容"></textarea></td>
						</tr>
					</tbody>
				</table>

				<br />
				<input type="button" class="btn" value="提交"/>
			</form>
			<hr />
			<!-- Divider -->

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
</body>
</html>
