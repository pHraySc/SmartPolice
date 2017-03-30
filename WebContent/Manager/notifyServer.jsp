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
	<link rel="stylesheet" type="text/css" href="style/notifyServer.css"> 
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
					<li><a href="#"><i></i>聊天消息管理管理</a></li>
					<li><a href="#"><i></i>通知消息</a></li>
				</ul>
    		</li>
    		<li class="main_left">
    			<a href="#"><span></span>平台管理</a>
				<ul>
					<li><a href="#"><i></i>反馈信息管理</a></li>
					<li><a href="#"><i></i>平台通知发布</a></li>
					<li><a href="#"><i></i>厂商信息统计</a></li>
				</ul>
    		</li>
    	</ul>
    </div>
    <!-- 右边的内容部分 -->
    <div class="main_body clearfix">
    	<div class="content fr">
    		<div class="position">
    			<span></span>
    			<h3>当前位置：</h3>
    			<p><a href="#">首页></a></p>
    		</div>
    	<!-- 需要添加的从这里开始 -->
    	<div class="right_message">
    	<div class="right_inf_top">
    	   <span>添加服务器</span>
    	</div>
    	
    	<div class="upload_info fr">
        	<div class="list_margin">
        		<form action="AddServers" method="post">
        			<ul class="upload_list">
        			 <li class="upload_one">
        				<i>服务器类型:</i>
        				<input type="text" id="device_name" class="same same_img1" name="addServers.ServerType" value="">
        				<!-- <span class="prompt"></span> -->
        				<strong></strong>
        			 </li>
        			<li class="upload_one upload_one_xin">
        			    
        				<i>内存大小:</i>
        				<input type="text" id="device_num" class="same same_img2" name="addServers.Memory" value="">
        				<!-- <span class="prompt"></span> -->
        				<strong></strong>
        			</li>
        			<li class="upload_one upload_one_xin">
        			
        		        <i>CPU型号:</i>
        				<input type="text" id="device_CPU" class="same same_img3" name="addServers.CPUVersion" value="">
        				<!-- <span class="prompt"></span> -->	
        				<strong></strong>
        			</li>
        			<li class="upload_one">
        		    <i class="upload_one_i">服务器描述:</i>
        		    <textarea rows="6" cols="40" name="addServers.ServerDesc" id="introduce" class="same"></textarea>
        				<!-- <span class="prompt"></span> -->	
        			</li>
        			<li class="btn">
        				<i></i>
        				<input type="submit" value="添加" class="submit1" id="upload_intro"/>
        				<!-- <input type="reset" value="重置" class="reset" name="res" id="res_intro"> -->
        				<input type="button" value="返回" class="btn_back submit2">
        			</li>
        		</ul>
        		</form>
        		</div>
    	</div>
    	
    	
    	</div><!-- right_message完 -->
    </div><!-- content fr完 -->
   
</div><!-- main_body clearfix完 -->
</body>
<script type="text/javascript">
	$(document).ready(function(){
		/* $(".same").each(function(){
			var bdv = $("<strong>*</strong>");
			$(this).parent().append(bdv);
		}); */
		//获取各个焦点
		$(":input").focus(function(){
		    if($(this).is("#device_name")){
		    	var bdv2 = $("<span class='a0'>正确填写大于3位且小于12位的服务器类型</span>");
		    	$(this).parent().append(bdv2);
		    	$(this).parent().find(".different").remove();
		    	$(this).css("border","1px solid #666");
		    }
		    if($(this).is("#device_num")){
		    	var bdv2 = $("<span class='a0'>正确填写大于6位的内存大小</span>");
		    	$(this).parent().append(bdv2);
		    	$(this).parent().find(".different").remove();
		    	$(this).css("border","1px solid #666");
		    }
		    if($(this).is("#device_CPU")){
		    	var bdv2 = $("<span class='a0'>正确输入CPU型号</span>");
		    	$(this).parent().append(bdv2);
		    	$(this).parent().find(".different").remove();
		    	$(this).css("border","1px solid #666");
		    }
		    if($(this).is("#introduce")){
		    	var bdv2 = $("<span class='a0'>感谢你的描述</span>");
		    	$(this).parent().append(bdv2);
		    	$(this).parent().find(".different").remove();
		    	$(this).css("border","1px solid #666");
		    }
		
	});
		//失去某个焦点
		$(":input").blur(function(){
			$(this).parent().find(".different").remove();
			$(this).parent().find(".a0").remove();
			if($(this).is("#device_name")){
				$(this).css("border","1px solid #D4D4D4");//边框颜色
				$(this).parent().find("strong").remove();
				if(this.value==''||this.value.length<3){
					var bdv1=$("<span class='different'><img src='icon_m/false.png' class='different_img'>用户名不得小于3位</span>");
   					 $(this).parent().append(bdv1);
				}else{
					var bdv1=$("<span class='different'><img src='icon_m/true.png'></span>");
    				$(this).parent().append(bdv1);
				}
			}
			if($(this).is("#device_num")){
				$(this).css("border","1px solid #D4D4D4");
				$(this).parent().find("strong").remove();
				if(this.value==''||this.value.length<6){
					var bdv1=$("<span class='different'><img src='icon_m/false.png'  class='different_img'>内存大小不得小于6位!</span>");
   					 $(this).parent().append(bdv1);
				}else{
					var bdv1=$("<span class='different'><img src='icon_m/true.png'></span>");
    				$(this).parent().append(bdv1);
				}
			}
			if($(this).is("#device_CPU")){
				$(this).css("border","1px solid #D4D4D4");
				$(this).parent().find("strong").remove();
				if(this.value==''||this.value.length<8){
					var bdv1=$("<span class='different'><img src='icon_m/false.png' class='different_img'>请检查CPU型号是否有误！</span>");
   					 $(this).parent().append(bdv1);
				}else{
					var bdv1=$("<span class='different'><img src='icon_m/true.png'></span>");
    				$(this).parent().append(bdv1);
				}
			}
			
			
			if($(this).is("#device_type")){
				$(this).parent().find("strong").remove();
				var bdv1=$("<span class='different'><img src='icon_m/true.png'></span>");
				$(this).parent().append(bdv1);
			}
			if($(this).is("#uploadings")){
				if(this.value.length>0){
					$(this).parent().find("strong").remove();
					var bdv1=$("<span class='different'><img src='icon_m/true.png'></span>");
					$(this).parent().append(bdv1);
				}
				
			}
			if($(this).is("#introduce")){
				$(this).css("border","1px solid #D4D4D4");
				$(this).parent().find("strong").remove();
				if(this.value==''||this.value==null){
					var bdv1=$("<span class='different'> </span>");
   					 $(this).parent().append(bdv1);
				}else{
					var bdv1=$("<span class='different'><img src='icon_m/true_list.png'></span>");
    				$(this).parent().append(bdv1);
				}
			}
		})
		
		$("#res_intro").click(function(){
			/* $("input").val(" "); */
			$('#biaodan')[0].reset();
			/* $("#biaodan .upload_one").append("<strong>*</strong>") */
			var reset_dif=$(this).parent().siblings();
			reset_dif.find(".different").html('');
			reset_dif.find("strong").html('');
			reset_dif.append("<strong>*</strong>");
			
		})
		
		
	})
	
	
	
	
</script>
</html>