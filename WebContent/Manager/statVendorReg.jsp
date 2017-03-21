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
	<link rel="stylesheet" type="text/css" href="style/statVendorReg.css"> 	
	   
	<script type="text/javascript" src="js_m/jquery-3.1.1.min.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script type="text/javascript" src="js_m/index.js"></script>
    <script type="text/javascript" src="js_m/zhu.js"></script>
    <script type="text/javascript" src="js_m/bing.js"></script>
    <script type="text/javascript" src="js_m/turn.js"></script>
   
	
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
            <div>
           
            <ul class="putin">      
                 <li>
                   <!-- <img src="icon_m/daochu.png"/>  -->
                   <span class="daochu daochu_dayin"></span>
                    <a  href="#">导出</a>
                 </li>  
                 <li> 
                     <!-- <img src="icon_m/dayin.png">  -->
                      <span class="dayin daochu_dayin"></span>           
                     <a  href="#">打印</a>
                 </li>
            </ul>
      
            <div class="account">
             <span>厂商注册数据统计</span>
             <span class="search">
             <form  name="input" action="" method="get">
             <input type="text" name=" " class="inputtext" />
             <input type="submit"  class="submit"  value="搜索" />
             </form>
             </span>
           </div> 
           </div> 
              <div id="notice"  class="notice">
                   <div id="notice_tit" class="notice_tit">
                   
                       <ul id="tab_t">
                           <li class="select">柱状图</li>
                           <li class="select1">饼状图</li>
                       </ul>
                   </div>
                   
                   <div id="tab_c" class="notice_noc" >
                         <div  id="containerz" style="width: 600px; height: 400px; margin: 0px auto; "></div>
                         <div class="active0" id="containerb" style="width: 600px; height: 400px; margin: 0px auto;display:none;"></div>
                   </div>
              </div>  
       </div>    
    </div>


</body>
</html>