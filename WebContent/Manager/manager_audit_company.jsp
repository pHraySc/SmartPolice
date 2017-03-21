<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="css/table_2.css" rel="stylesheet" />
		<script src="js/jquery-2.1.4.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<link rel="stylesheet" href="css/table.css" />
		<script src="js/tancuang.js"></script>
<title>Insert title here</title>
</head>
<body>
		<ol class="breadcrumb">
			<li><a href="#">首页</a></li>
			<li><a href="#">厂商信息管理</a></li>
			<li class="active">厂商注册审核</li>
		</ol>
		<form action="batchHandleaCompanyuser" method="post">
			<div class="tables">
			<div class="row">
				<div class="batch-btn">
					<span>批 量 操 作</span>
					<button class="btn btn-info" name="actiontype" value="pass" >通 过</button>
					<button class="btn btn-danger" name="actiontype" value="refused">拒 绝</button>
				</div>
				<div class="col-xs-12">
					<div class="table-header">
						厂商注册审核
					</div>
					<div class="table-responsive">
						<table id="sample-table-2" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th class="center">
										<label>
											<input type="checkbox" class="ace" />
											<span class="lbl"></span>
										</label>
									</th>
									<th>序 号</th>
									<th>厂商名称</th>
									<th>厂商类型</th>
									<th>厂商说明</th>
									<th>负责人</th>
									<th>列表操作</th>
								</tr>
							</thead>
							
							<tbody>
							<s:if test="#session.auditCompany.size()>0">
							<s:iterator value="#session.auditCompany" status="firmuser" id="fu">
								<tr>
								
									<td class="center">
										<label>
											<input type="checkbox" name="ids" value="<s:property value="#fu.userid"/>"/>
											<span class="lbl"></span>
										</label>
									</td>
									<td><s:property value="#fu.id"/></td>
									<td><s:property value="#fu.name"/></td>
									<td><s:property value="#fu.type"/></td>
									<td><s:property value="#fu.demo"/></td>
									<td><s:property value="#fu.username"/></td>
									<td><a href="ManagerPassCompany.action?id=<s:property value="#fu.userid"/>"><input type="button" class="btn btn-info" value="通过"></a> 
									<a href="ManagerRefusedCompany.action?id=<s:property value="#fu.userid"/>"><input type="button" class="btn btn-danger" value="拒绝 "></a> 
									<a href="ManagerAuditConpanyInfo.action?id=<s:property value="#fu.userid"/>"><input type="button" class="btn btn-success" value="查看详情"></a></td>
								</tr>
							</s:iterator>
							</s:if>
							</tbody>					
						</table>
					</div>
				</div>
			</div>
		</div>
		
		</form>
	
		<script type="text/javascript">
			window.jQuery || document.write("<script src='js/jquery-1.8.0.min.js'>" + "<" + "/script>");
		</script>
		<script type="text/javascript">
			if ("ontouchend" in document) document.write("<script src='js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
		</script>
		<script src="js/jquery.dataTables.min.js"></script>
		<script src="js/jquery.dataTables.bootstrap.js"></script>
		<script type="text/javascript">
		$(function(){
        	var len = $('tr').length;
        	for(var i = 1;i<len;i++){
            	$('tr:eq('+i+') td:eq(1)').text(i);
        	}  
		});
			jQuery(function($) {
				var oTable1 = $('#sample-table-2').dataTable({
					"aoColumns": [{
							"bSortable": false
						},
						null, null, null, null, null, {
							"bSortable": false
						}
					]
				});
				$('table th input:checkbox').on('click', function() {
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
						.each(function() {
							this.checked = that.checked;
							$(this).closest('tr').toggleClass('selected');
						});
				});
				$('[data-rel="tooltip"]').tooltip({
					placement: tooltip_placement
				});

				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
					var off2 = $source.offset();
					var w2 = $source.width();
					if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
					return 'left';
				}
			})
		</script>
</body>
</html>