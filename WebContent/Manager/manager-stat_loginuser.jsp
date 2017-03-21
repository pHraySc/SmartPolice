<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<link href="../css/table_2.css" rel="stylesheet" />
		<script src="../js/jquery-2.1.4.min.js"></script>
		<script src="../bootstrap/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="../css/font-awesome.min.css" />
		<link rel="stylesheet" href="../css/table.css" />
		<title></title>
	</head>

	<body>
		<ol class="breadcrumb">
			<li><a href="#">首页</a></li>
			<li><a href="#">系统信息管理</a></li>
			<li class="active">登录用户信息</li>
		</ol>
		<div class="tables">
			<div class="row">
				<div class="col-xs-12">
					<div class="table-header">
						登录用户信息
					</div>
					<div class="table-responsive">
						<table id="sample-table-2" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>序 号</th>
									<th>用户名</th>
									<th>注册时间</th>
									<th>最近登录时间</th>
									<th>当前状态</th>
									
									<th>列表操作</th>
								</tr>
							</thead>

							<tbody>
							
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">详 情 信 息</h4>
					</div>
					<div class="modal-body">
						<table class="table">
							<tbody>
								<tr class="active">
									<td>用户名称：</td>
									<td>用户账号：</td>
								</tr>
								<tr class="active">
								<td>用户类型</td>
									<td>用户级别</td>
									</tr>
								<tr class="success">
									<td>联系方式：</td>
									<td>注册时间：</td>
								</tr>
								<tr class="success">
								<td>邮箱：</td>
								</tr>
								<tr class="danger">
									<td width="50%" height="250">设备列表：</td>
									<td width="50%" height="250">详细描述：</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" class="btn btn-primary">
							确定
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<script type="text/javascript">
			window.jQuery || document.write("<script src='js/jquery-2.1.4.min.js'>" + "<" + "/script>");
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
						null, null, null, null,{
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