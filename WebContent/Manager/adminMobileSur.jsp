<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理智能平台</title>
	<link rel="stylesheet" type="text/css" href="style/reset.css">
	<link rel="stylesheet" type="text/css" href="style/common.css">
	<link rel="stylesheet" type="text/css" href="style/index.css">
	<link rel="stylesheet" type="text/css" href="style/auditVendorReg.css">
	<script type="text/javascript" src="js_m/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="js_m/index.js"></script>
</head>
<body>
	<div class="head"><!--  -->
    	<div class="logo"></div>
    	<div class="left_title fr">
    		<ul>
    			<li><span class="help"></span><a href="#">帮助</a></li>
    			<li><span class="out"></span><a href="#">退出</a></li>
    		</ul>
    	</div>
    </div>
    <div class="menu_time clearfix">
    	<div class="left_time fl">
    		<p>你上次登录的时间是：<span  id="time"></span></p>
        	<p>当前用户：<a href="#">3456</a></p>
        	<p>字号：<a href="#">大</a><a href="#">中</a><a href="#">小</a></p>
    	</div>
    	<div class="menu fr">
    	    	<ul class="fl">
    	    		<li><a href="#"><i></i>返回首页</a></li>
    	    		<li><a href="#"><i></i>个人中心</a></li>
    	    		<li><a href="#"><i></i>刷新</a></li>
    	    		<li><a href="#"><i></i>设置</a></li>
    			</ul>
    			<div class="dynamic_info fl"><a href="#"><i></i></a>(2)</div>
    	 </div> 		
    </div>
    <div class="blue_line"></div>
    <div class="nav fl">
    	<ul>
    		<li class="main_left">
    			<a href="#"><span></span>厂商信息管理</a>
				<ul>
					<li><a href="#"><i></i>厂商信息审核</a></li>
					<li><a href="#"><i></i>厂商信息管理</a></li>
					<li><a href="#"><i></i>注册厂商统计</a></li>
				</ul>
    		</li>
            <li class="main_left">
    			<a href="#"><span></span>前端设备管理</a>
				<ul>
					<li><a href="#"><i></i>前端设备审核</a></li>
					<li><a href="#"><i></i>前端设备管理</a></li>
					<li><a href="#"><i></i>前端设备统计</a></li>
				</ul>
    		</li>
    		<li class="main_left">
    			<a href="#"><span></span>移动监控管理</a>
				<ul>
					<li><a href="#"><i></i>移动监控审核</a></li>
					<li><a href="#"><i></i>移动监控管理</a></li>
					<li><a href="#"><i></i>移动监控统计</a></li>
				</ul>
    		</li>
    		<li class="main_left">
    			<a href="#"><span></span>注册用户管理</a>
				<ul>
					<li><a href="#"><i></i>用户信息管理</a></li>
					<li><a href="#"><i></i>注册用户统计</a></li>
				</ul>
    		</li>
    		<li class="main_left">
    			<a href="#"><span></span>系统信息管理</a>
				<ul>
					<li><a href="#"><i></i>登录设备信息</a></li>
					<li><a href="#"><i></i>登录用户信息</a></li>
					<li><a href="#"><i></i>系统运行信息</a></li>
				</ul>
    		</li>
    		<li class="main_left">
    			<a href="#"><span></span>服务器管理</a>
				<ul>
					<li><a href="#"><i></i>服务器信息管理</a></li>
					<li><a href="#"><i></i>增添服务器</a></li>
				</ul>
    		</li>
    		<li class="main_left">
    			<a href="#"><span></span>消息管理</a>
				<ul>
					<li><a href="#"><i></i>警报消息管理</a></li>
					<li><a href="#"><i></i>聊天消息管理</a></li>
					<li><a href="#"><i></i>通知消息</a></li>
				</ul>
    		</li>
    		<li class="main_left">
    			<a href="#"><span></span>平台管理</a>
				<ul>
					<li><a href="#"><i></i>反馈信息管理</a></li>
					<li><a href="#"><i></i>平台通知发布</a></li>
					<!-- <li><a href="#"><i></i>厂商信息统计</a></li> -->
				</ul>
    		</li>
    	</ul>
    </div>
    <!-- 右边的内容部分 -->
    <div class="main_body">
    	<div class="content fr">
    		<div class="position ">
    			<span></span>
    			<h3>当前位置：</h3>
    			<p><a href="#">首页></a></p>
    		</div>
    	<!-- 需要添加的从这里开始 -->
    		<div class="operation ">
        		<ul class="operating">
        			<li><a href="#"><b></b>删除</a></li>
        			<li><a href="#"><b></b>导出</a></li>
        			<li><a href="#"><b></b>打印</a></li>
        			<li><a href="#"><b></b>批量通过</a></li>
        			<li><a href="#"><b></b>批量删除</a></li>
        			
        		</ul>
        		<div class="table_header_title"><h3>移动监控管理</h3></div>
        		<div class="search fr">
        			<form action="" method="post">
        				<input type="text" value="搜索" class="search_box">
        				<input type="button" value="确定" class="button">
        			</form>       		
        		</div>
        		<!-- 表格 -->
        		<div class="form">
        			<table border="1" class="table_text">
        				<tr>
        					<th>选择</th>
        					<th>序号</th>
        					<th>设备名称</th>
        					<th>设备类型</th>
        					<th>设备条形码</th>
        					<th>厂商</th>
        					
        					<th>操作</th>
        				</tr>
        				<tr>
        					<td>
        						<input type="checkbox" name="" id="">
        					</td>
        					<td>01</td>
        					<td>智能表</td>
        					<td>智能类</td>
        					
        					<td>1233-2324-2341</td>
        					<td>张 三</td>
        					
        					<td>
        						<ul>
        							<li><a href="#"><b class="form_icon_del"></b>删除</a></li>
        							<li><a href="#"><b class="form_icon_mod"></b>修改</a></li>
        							<li><a href="#"><b class="form_icon_check"></b>查看</a></li>
        						</ul>
        					</td>
        				</tr>
        				<tr>
        					<td>
        						<input type="checkbox" name="" id="">
        					</td>
        					<td>02</td>
        					<td>智能表</td>
        					<td>智能类</td>
        					
        					<td>1233-2324-2341</td>
        					<td>张 三</td>
        					
        					<td>
        						<ul>
        							<li><a href="#"><b class="form_icon_del"></b>删除</a></li>
        							<li><a href="#"><b class="form_icon_mod"></b>修改</a></li>
        							<li><a href="#"><b class="form_icon_check"></b>查看</a></li>
        						</ul>
        					</td>
        				</tr>
        				<tr>
        					<td>
        						<input type="checkbox" name="" id="">
        					</td>
        					<td>03</td>
        					<td>智能表</td>
        					<td>智能类</td>
        					
        					<td>1233-2324-2341</td>
        					<td>张 三</td>
        					
        					<td>
        						<ul>
        							<li><a href="#"><b class="form_icon_del"></b>删除</a></li>
        							<li><a href="#"><b class="form_icon_mod"></b>修改</a></li>
        							<li><a href="#"><b class="form_icon_check"></b>查看</a></li>
        						</ul>
        					</td>
        				</tr>
        				<tr>
        					<td>
        						<input type="checkbox" name="" id="">
        					</td>
        					<td>04</td>
        					<td>智能表</td>
        					<td>智能类</td>
        					
        					<td>1233-2324-2341</td>
        					<td>张 三</td>
        					
        					<td>
        						<ul>
        							<li><a href="#"><b class="form_icon_del"></b>删除</a></li>
        							<li><a href="#"><b class="form_icon_mod"></b>修改</a></li>
        							<li><a href="#"><b class="form_icon_check"></b>查看</a></li>
        						</ul>
        					</td>
        				</tr>
        				<tr>
        					<td>
        						<input type="checkbox" name="" id="">
        					</td>
        					<td>05</td>
        					<td>智能表</td>
        					<td>智能类</td>
        					
        					<td>1233-2324-2341</td>
        					<td>张 三</td>
        					
        					<td>
        						<ul>
        							<li><a href="#"><b class="form_icon_del"></b>删除</a></li>
        							<li><a href="#"><b class="form_icon_mod"></b>修改</a></li>
        							<li><a href="#"><b class="form_icon_check"></b>查看</a></li>
        						</ul>
        					</td>
        				</tr>
        			</table>
        			<div class="page_footer"> 
        				<p>共788条信息</p>
        				<span><a href="#">&lt</a></span>
        				<a href="#" class="page_active">1</a>
        				<a href="#">2</a>
        				<a href="#">3</a>
        				<a href="#">4</a>
        				<a href="#">5</a>
        				...
        				<a href="#">123</a>
        				<span><a href="#">&gt</a></span>
        				<div class="jump">
        					跳转到
        					<input type="text" name="" id="">
        					<input type="button" value="确定" id="" name="">
        				</div>
        			</div>
        		</div>
        	</div>
    	
    	
    	<!-- 结束 -->
    </div>
    </div>
</body>
<script type="text/javascript">
	$(".page_footer >a").click(function(){
		$(this).siblings().removeClass("page_active");
		$(this).addClass("page_active");
	});
	/* 表格行间隔色 */
	$(document).ready(function(){
		odd={"background":"#fff"};
		even={"background":"#f9f9f9"};
		odd_even(".table_text",odd,even);
	})
	function odd_even(clas,odd,even){
		$(clas).find("tr").each(function(index,element){
			if(index%2==1){
				$(this).css(odd);
			}else{
				$(this).css(even);
			}
		})
	}
	
</script>
</html>