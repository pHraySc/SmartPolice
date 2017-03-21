<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html xmlns="http://www.w3.org/1999/xhtml">

		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<link href="css/user_info.css" rel="stylesheet" />
			<script src="js/jquery-2.1.4.min.js"></script>
			<script src="../bootstrap/js/bootstrap.min.js"></script>
			<title></title>
		</head>

		<body onload="showMsg()">
			<div id="container">
				<!-- Header -->
				<jsp:include page="firm_admin_header.jsp"></jsp:include>
				<!-- END Header -->
				<div class="clear"></div>
				<div class="box">
					<a href="###">
						<div class="info active">用 户 信 息</div>
					</a>
					<a href="###">
						<div class="re-info">密 码 修 改</div>
					</a>
					<a href="###">
						<div class="message">消 息 盒</div>
					</a>
					<div class="user-box">
						<form action="firmAdminChangeInfo" method="post">
						<p>用 户 信 息</p>
						<hr class="hr1" />
						<br />
						<div class="user-head">
						<img src="image/u14.png" width="100" /> 

						</div>
						<table class="user-info" cellpadding="0" cellspacing="0">
						<s:if test="#session.firmInfo.size()>0">
							<s:iterator value="#session.firmInfo" status="firmuser" id="fs">
							
							<tr>
								<th width="80" height="55">用户名称：</th>
								<td width="220"><s:property value="#fs.username" /></td>
							
							</tr>
							<tr>
								<th width="80" height="55">联系方式：</th>
								<td width="220">
									<span><s:property value="#fs.number" /></span>
									<input type="text" name="number" class="r-input r-num" />
									<a class="revise revise-num" href="###">修改</a>
									<div class="submit-box-num">
										<input type="submit" id="r-num" class="sub sub-num" value="确定" />
										<br>
										<a class="exit exit-num" href="###">取消</a>
									</div>
								</td>
								
							</tr>
							<tr>
								<th width="80" height="55">电子邮箱：</th>
								<td width="220">
									<span><s:property value="#fs.email" /></span>
									<input type="text" name="email" class="r-input r-email" />
									<a class="revise revise-email" href="###">修改</a>
									<div class="submit-box-email">
										<input type="submit" id="r-email" class="sub" value="确定" />
										<br>
										<a class="exit" href="###">取消</a>
									</div>
								</td>
							
							<tr>
								<th colspan="4" width="80" height="50">公司简介：</th>
							</tr>
							<tr>
								<td colspan="4"><span class="user-text"><s:property value="#session.firm.description" /></span></td>
							</tr>
								</s:iterator>
							</s:if>
						</table>
						</form>
					</div>

					<div class="message-box">
						<p>消 息 管 理</p>
						<hr class="hr3" />
						<br />
						<div class="message-list">
							<table class="message-table">
								<thead>
									<tr>
										<th width="100">发件人</th>
										<th width="150">主题</th>
										<th width="150">发件时间</th>
										<th width="150">操作</th>
									</tr>
								</thead>
								<tbody>
									<s:if test="#session.MessageList.size()>0">
										<s:iterator value="#session.MessageList" status="st" id="ml">
											<tr>
												<td><s:property value="#ml.sendpeople" /></td>
												<td><s:property value="#ml.mestitle" /></td>
												<td><s:property value="#ml.sendtime" /></td>
												<td>
													<a href="f_showMessageDetail.action?id=<s:property value="#ml.id" />"><button class="btn btn-info">查看详情</button></a>
													<a href="f_deleteMessage.action?id=<s:property value="#ml.id" />"><input class="btn btn-danger" value="删 除"></a>
												</td>
											</tr>
										</s:iterator>
									</s:if>
								
									
 									<tr> 
										<td>张三</td> 
 										<td>更新通知</td> 
 										<td>2015-9-30</td> 
 										<td> 
 											<button class="btn btn-info" data-toggle="modal" data-target="#myModal">查看详情</button> 
										<button class="btn btn-danger">删 除</button> 
 										</td> 
									</tr>
								</tbody>
							</table>
							<div class="page"></div>
						</div>
					</div>
						<div class="re-info-box">
							<p>密 码 修 改</p>
							<hr class="hr3" />
							<br />
							<div class="re-box">
								<form class="form-horizontal" role="form" action="firmAdimnPassword.action" method="post">
									<div class="form-group">
										<label style="position: relative; top: 32px; left: -55px; font-size: 15px;color: #545454;font-weight: 400">旧 密 码 :</label>
										<div class="col-sm-10">
											<input type="password" class="form-control" name="pwd1" id="pwd1" onblur="checkPassword1();" /><span class="warning" id="passwordinfo"></span>
										</div>
									</div>

									<div class="form-group">
										<label style="position: relative; top: 32px; left: -55px; font-size: 15px;color: #545454;font-weight: 400">新 密 码 :</label>
										<div class="col-sm-10">
											<input type="password" class="form-control" name="pwd2" id="pwd2" onblur="checkPassword2();" /><span class="warning" id="passwordinfo"></span>
										</div>
									</div>

									<div class="form-group">
										<label style="position: relative; top: 32px; left: -63px; font-size: 15px;color: #545454;font-weight: 400">确认密码 :</label>
										<div class="col-sm-10">
											<input type="password" class="form-control" name="Rpwd" id="Rpwd" onblur="checkRepassword();" /><span class="warning" id="repasswordinfo"></span>
										</div>
									</div>
									<div class="btn-ture">
									    <span style="margin-right:50px"><a href="http://localhost:8080/IntelSecurity/ent_home/f_showFirmuserDetail">取消</a></span>
										<button class="btn btn-info" style="height: 30px;" >确 定</button>
									</div>
								</form>
								<div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="footer-container" style="margin-top: 100px">
					<div id="footer">
						<span style="position:absolute;top:60px;font-size: 13px;color: white;">Investor Relations | 关于我们 | 安全保障 | 战略合作：MSN中文网 | Copyright ©.All Rights Reserved. 京ICP证080047号 京公网安备110000000006号</span>
					</div>
				</div>
		</body>
		<script type="text/javascript">
			$(document).ready(function() {
				var $table = $(".message-table");
				//分页效果
				var currentPage = 0; //当前页
				var pageSize = 8; //每页行数（不包括表头）
				//绑定分页事件
				$table.bind('repaginate', function() {
					$table.find('tbody tr').hide().slice(currentPage * pageSize, (currentPage + 1) * pageSize).show();
				});
				var numRows = $table.find('tbody tr').length; //记录总条数
				var numPages = Math.ceil(numRows / pageSize); //总页数
				var $pager = $('.page'); //分页div
				for (var page = 0; page < numPages; page++) {
					//为分页标签加上链接
					$('<a class="page-btn" href="#" ><span>' + (page + 1) + '</span></a>')
						.bind("click", {
							"newPage": page
						}, function(event) {
							currentPage = event.data["newPage"];
							$table.trigger("repaginate");
						})
						.appendTo($pager);
					$pager.append("  ");
				}
				$table.trigger("repaginate"); //初始化 
			});
			
			$(function() {
				$(".revise").click(function() {
					$(this).hide(100);
					$(this).siblings("div").show(100);
					$(this).siblings(".r-input").show(100);
					$(this).siblings("span").hide(100);
				});
				$(".exit").click(function() {
					$(this).parent("div").hide(100);
					$(this).parent("div").siblings(".revise").show(100);
					$(this).parent("div").siblings("span").show(100);
					$(this).parent("div").siblings(".r-input").hide(100);
				});
				$(".sub").click(function() {
					if($(".r-input").val()==0){
						alert("输入字段不能为空！");
					}
					else{
						$(this).parent("div").hide(100);
						$(this).parent("div").siblings(".revise").show(100);
						$(this).parent("div").siblings("span").show(100);
						$(this).parent("div").siblings(".r-input").hide(100);
					}
				});
			});
			
			$(function() {
				$(".info").click(function() {
					$(".user-box").fadeIn(300);
					$(".re-info-box").fadeOut(300);
					$(".message-box").fadeOut(300);
					$(".info").addClass("active");
					$(".re-info").removeClass("active");
					$(".message").removeClass("active");
				});
				$(".re-info").click(function() {
					$(".user-box").fadeOut(300);
					$(".re-info-box").fadeIn(300);
					$(".message-box").fadeOut(300);
					$(".re-info").addClass("active");
					$(".info").removeClass("active");
					$(".message").removeClass("active");
				});
				$(".message").click(function() {
					$(".user-box").fadeOut(300);
					$(".re-info-box").fadeOut(300);
					$(".message-box").fadeIn(300);
					$(".message").addClass("active");
					$(".re-info").removeClass("active");
					$(".info").removeClass("active");
				});
			});
		</script>
		<script type="text/javascript">
			function showMsg() {
				var msg = "${backMsg}";
				if (msg != null && msg != "") {
					alert(msg);
				}
			}
		</script>

		</html>