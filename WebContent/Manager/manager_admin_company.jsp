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
		<title></title>
	</head>

	<body>
		<ol class="breadcrumb">
			<li><a href="#">首页</a></li>
			<li><a href="#">厂商信息管理</a></li>
			<li class="active">厂商信息管理</li>
		</ol>
		<div class="tables">
			<div class="row">
				<div class="col-xs-12">
					<div class="table-header">
						厂商信息管理
					</div>
					<div class="table-responsive">
						<table id="sample-table-2" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>序 号</th>
									<th>厂商名称</th>
									<th>厂商类型</th>
									<th>厂商地址</th>
									<th>负责人</th>
									<th>审核状态</th>
									<th>列表操作</th>
								</tr>
							</thead>

							<tbody>
							<s:if test="#session.allCompany.size()>0">
							<s:iterator value="#session.allCompany" status="firmuser" id="fs">
								<tr>
									

									<td><s:property value="#fs.id" /></td>
									<td><s:property value="#fs.name" /></td>
									<td><s:property value="#fs.type" /></td>
									<td><s:property value="#fs.address" /></td>
									<td><s:property value="#fs.username" /></td>
									<td><s:property value="#fs.state" /></td>
									<td><a href="CompanyDetail1.action?id=<s:property value="#fs.companyid"/>"><input type=button class="btn btn-success" value="查看详情" /></a></td>
								</tr>
							</s:iterator>
							</s:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
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
            	$('tr:eq('+i+') td:eq(0)').text(i);
        	}  
		});
			jQuery(function($) {
				var oTable1 = $('#sample-table-2').dataTable({
					"aoColumns": [{
							"bSortable": false
						},
						null, null, null, null, {
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