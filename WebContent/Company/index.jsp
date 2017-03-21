<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <link rel="stylesheet" href="css/index.css"/>
	<title>智能平台系统</title>
</head>

<body onload="time(),addtime()">	
	<!-- Container -->
	<div id="container">
    	<!-- Header -->
       <jsp:include page="firm_admin_header.jsp"></jsp:include>
        <!-- END Header -->
        <div class="clear"></div> 
        <!-- Content -->
 		<div id="content">
        	<!-- 'status' div is used as a container for interactive message blocks(success, warning, error, etc..) --> 
        	<div id="status"></div>
        	<div class="info_left">
           	<h2 class="top-title">个 人 首 页</h2><hr />

           	<div class="wel">
           		<p class="wel-text">尊敬的用户，这是<span> XX</span> 次登录，现在是北京时间: &nbsp;<span id="user-time"></span></p>
           		<p class="new-text">您有 <span>${msgNum}</span> 条未读消息 <a href="f_showFirmuserDetail.action">查看详情</a></p>
          	 	<p class="dev-text">您有 <span>${preCheckingNum}</span> 台设备正在审核中 <a href="f_toSEquipPage.action">查看详情</a></p>
           	</div>
           	</div>
           	<div class="img-wel">
           		
           		<a href="firmAdminSelf.action?id=<%=session.getAttribute("id") %>" class="img-box1">
           			<div class="cbox-1">
           				<img src="../img/coin1.png" width="60px"/>
           				<br>
           				<span>个人中心</span>
           			</div>
           		</a>
           		<a href="firmAdminDevive.action" class="img-box2">
           			<div class="cbox-2">
           				<img src="../img/coin2.png" width="60px"/>
           				<br>
           				<span>设备管理</span>
           			</div>
           		</a>
           		<a href="firmAdminDevive.action" class="img-box3">
           			<div class="cbox-4">
           				<img src="../img/coin4.png" width="60px"/>
           				<br>
           				<span>审核状态</span>
           			</div>
           		</a>
           	</div>
           	
			<div class="news">
				<div class="news-top"><span>平 台 动 态</span></div>
				<div class="news-text">
					<ul>
						<s:if test="#request.firminformationList.size()>0">
							<s:iterator value="#request.firminformationList" status="st" id="fil">
									<li class="news-text-active"><s:property value="#fil.content"/></li>
							</s:iterator>
						</s:if>
<!-- 						<li class="news-text-active">李宝荣任国家机关事务管理局局长(图/简历)</li> -->
<!-- 						<li class="news-text-active">广州市纪委：发现有官员跑官时被骗财骗色</li> -->
<!-- 						<li>上海迪士尼被曝招百名体验员免费玩 官方称不实</li> -->
<!-- 						<li>环保部：雾霾周五起或再袭京津冀</li> -->
<!-- 						<li>天津至保定铁路开始调试 全程只需40分钟</li> -->
<!-- 						<li>马航失联者妻子回应欠贷：不是不还 想等个结果</li> -->
<!-- 						<li>环保部：雾霾周五起或再袭京津冀</li> -->
<!-- 						<li>天津至保定铁路开始调试 全程只需40分钟</li> -->
					</ul>
				</div>
			</div>
			
			<div class="ents">
				<div class="ents-top"><span>加 盟 厂 商</span></div>
				<div class="ents-table">
				<div id="box">
					<table cellpadding="0" cellspacing="0">
					
						<s:if test="#request.firmuserList.size()>0">
							<s:iterator value="#request.firmuserList" status="st" id="ful">
									<tr>
										<td><s:property value="#ful.name"/></th>
										<td><s:property value="#ful.subtime"/></td>
										<td><s:property value="#ful.companypro"/></td>
									</tr>
							</s:iterator>
						</s:if>
						
<!-- 						<tr> -->
<!-- 							<td>XXX科技有限公司</th> -->
<!-- 							<td>2015-9-30</td> -->
<!-- 							<td>设备供应商</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>XXX科技有限公司</th> -->
<!-- 							<td>2015-9-30</td> -->
<!-- 							<td>设备供应商</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>XXX科技有限公司</th> -->
<!-- 							<td>2015-9-30</td> -->
<!-- 							<td>设备供应商</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>XXX科技有限公司</th> -->
<!-- 							<td>2015-9-30</td> -->
<!-- 							<td>设备供应商</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>XXX科技有限公司</th> -->
<!-- 							<td>2015-9-30</td> -->
<!-- 							<td>设备供应商</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>XXX科技有限公司</th> -->
<!-- 							<td>2015-9-30</td> -->
<!-- 							<td>设备供应商</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>XXX科技有限公司</th> -->
<!-- 							<td>2015-9-30</td> -->
<!-- 							<td>设备供应商</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>XXX科技有限公司</th> -->
<!-- 							<td>2015-9-30</td> -->
<!-- 							<td>设备供应商</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>XXX科技有限公司</th> -->
<!-- 							<td>2015-9-30</td> -->
<!-- 							<td>设备供应商</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>XXX科技有限公司</th> -->
<!-- 							<td>2015-9-30</td> -->
<!-- 							<td>设备供应商</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>XXX科技有限公司</th> -->
<!-- 							<td>2015-9-30</td> -->
<!-- 							<td>设备供应商</td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td>XXX科技有限公司</th> -->
<!-- 							<td>2015-9-30</td> -->
<!-- 							<td>设备供应商</td> -->
<!-- 						</tr> -->
					</table>
				</div>
				</div>
			</div>
			
		</div>
	</div>
	<div id="footer-container" style="margin-top: 60px">
        <div id="footer">
        	<span style="position:absolute;top:60px;font-size: 13px;color: white;">Investor Relations | 关于我们 | 安全保障 | 战略合作：MSN中文网 | Copyright ©.All Rights Reserved. 京ICP证080047号 京公网安备110000000006号</span>
        </div>
 	</div>
	<script>
		var m = 0;
		function slide(){
			document.getElementById('box').scrollTop=m;
			m = m+2;
		}
		setInterval("slide()",100);
		
		var currentdate = "";
		function time(){
			var date = new Date();
		    var seperator1 = "-";
		    var seperator2 = ":";
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		            + " " + date.getHours() + seperator2 + date.getMinutes()
		            + seperator2 + date.getSeconds();
		}
		
		function addtime(){
			document.getElementById("user-time").innerText=currentdate; 
		}
	</script>
</body>
</html>

