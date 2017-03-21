<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <script src="js/jquery-2.1.4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/page.css" />
	<title>智能平台系统</title>
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
        	<!-- 'status' div is used as a container for interactive message blocks(success, warning, error, etc..) --> 
        	<div id="status"></div>

           	<h2 class="top-title">消 息 管 理</h2>
            <form method="post" action="#" name="form">
                 
           		<table class="list-table">
           			
            	<thead>
                	<tr>
                    	<th>序号</th>
                        <th>发件人</th>
                        <th>发送时间</th>
                        <th>消息主题</th>
                        <th>消息内容</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
           			<s:if test="#session.firmmsg.size()>0">
							<s:iterator value="#session.firmmsg" status="firmuser" id="pdl">
                	<tr>
      
							<td><s:property value="#pdl.id"/></td>
			               	<td><s:property value="#pdl.name"/></a></td>
			            	<td><s:property value="#pdl.sendtime"/></td>
			                <td><s:property value="#pdl.title"/></td>
			                <td><s:property value="#pdl.content"/></td>
			                <td><a href="firmAdminMsgInfo.action?id=<s:property value="#pdl.noticeid"/>"><input type=button class="btn btn-success" value="查看详情" /></a></td>

                	</tr>
                	</s:iterator>
							</s:if>
                </tbody>
                
                </table>
           	</form>
			<ul class="pagination"></ul>
           	<hr /> <!-- Divider -->
           	<div  style="text-align: center;">

   			</div>
        </div>
        <!-- END Content -->
    </div>
    <!-- END Container -->
    <div class="clear"></div>
    
    <!-- Footer Container -->
   	<div id="footer-container" style="margin-top: 60px">
        <div id="footer">
        	<span style="position:absolute;top:60px;font-size: 13px;color: white;">Investor Relations | 关于我们 | 安全保障 | 战略合作：MSN中文网 | Copyright ©.All Rights Reserved. 京ICP证080047号 京公网安备110000000006号</span>
        </div>
 	</div>
</body>
<script>
$(function(){
	var len = $('tr').length;
	for(var i = 1;i<len;i++){
    	$('tr:eq('+i+') td:eq(0)').text(i);
	}  
});

$(document).ready(function() {
	var $table = $('table');
	//分页效果
	var currentPage = 0; //当前页
	var pageSize = 10; //每页行数（不包括表头）
	//绑定分页事件
	$table.bind('repaginate', function() {
		$table.find('tbody tr').hide().slice(currentPage * pageSize, (currentPage + 1) * pageSize).show();
	});
	var numRows = $table.find('tbody tr').length; //记录总条数
	var numPages = Math.ceil(numRows / pageSize); //总页数
	var $pager = $('.pagination'); //分页div
	for (var page = 0; page < numPages; page++) {
		//为分页标签加上链接
		$('<li><a href="#">' + (page + 1) + '</a></li>')
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
</script>
</html>

