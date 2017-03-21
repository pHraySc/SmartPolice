<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <!-- JQuery UI CSS Framework -->
    <link rel="stylesheet" type="text/css" href="css/ui-lightness/jquery-ui-1.8.4.custom.css"  />
    <!-- End JQuery UI CSS Framework -->
	
    <!-- Styles specific for current page -->
    <link rel="stylesheet" type="text/css" href="js/markitup/skins/markitup/style.css" />
	<link rel="stylesheet" type="text/css" href="js/markitup/sets/html/style.css" />
    <link rel="stylesheet" type="text/css" href="js/jqplot/jquery.jqplot.css" />
     <!-- END Specific Styles -->
    
    <!-- General Styles common for all pages -->
    <link rel="stylesheet" type="text/css" href="css/globals.css" />
    <link rel="stylesheet" type="text/css" href="css/commons.css" />
    <link rel="stylesheet" type="text/css" href="css/plugins.css" />
    <!-- END General Styles -->
    
    <!-- Current Theme styles. These are changed via theme switcher -->	
    <link rel="stylesheet" id="active-theme-globals" type="text/css" href="themes/sleek-wood/css/globals.css" />
	<link rel="stylesheet" id="active-theme-commons" type="text/css" href="themes/sleek-wood/css/commons.css" />
	<link rel="stylesheet" id="active-theme-plugins" type="text/css" href="themes/sleek-wood/css/plugins.css" /> 
    <!-- END Current Theme styles -->
    
    <link rel="stylesheet" type="text/css" href="css/dropdown.css" />
    <script  type="text/javascript">
    function inequipment(){
    	var equipment = document.getElementById('equipment');
    	equipment.style.display='block';
    }
    function outequipment(){
    	var equipment = document.getElementById('equipment');
    	equipment.style.display='none';
    }
    
    </script>
     <script  type="text/javascript">
    function insoftware(){
    	var software = document.getElementById('software');
    	software.style.display='block';
    }
    function outsoftware(){
    	var software = document.getElementById('software');
    	software.style.display='none';
    }
    
    </script>
       <script  type="text/javascript">
    function inmessage(){
    	var message = document.getElementById('message');
    	message.style.display='block';
    }
    function outmessage(){
    	var message = document.getElementById('message');
    	message.style.display='none';
    }
    
    </script>
    
    <style>
		*{
			font-family: '微软雅黑'；
		}
		#header-middle span{
			color: white;
			font-size: 24px;
			position: absolute;
			left: 110px;
			top: 50px;
		}
		#header-middle img{
		height:80px;
		}
		#equipment,#software,#message{
		display:none;
		}
		#equipment:hover,#software:hover{
		color:red;
		display:none;
		}
		
		.equipment,.software{
		width:150px;
		z-index:999;
		}
		dl{
		background:red;
		border:1px solid red;
		}
    </style>
</head>
<body>
        <div id="header">
    		<div id="header-top">
            	<div id="top-block-left">
                	
                </div>
                <ul id="top-toolbox">
          
                    <li><a href="firmAdminSelf.action?id=<%=session.getAttribute("id") %>" id="profile-link">个人中心</a></li>
                    <li><a href="#" id="profile-link">欢迎你:<%=session.getAttribute("username") %></a></li>
                    <li><a href="firmlogout.action">退出</a></li>
                </ul>
                <div class="clear"></div>
            </div>
            <div id="header-middle">
               <img alt="logo" src="image/logo.png">
           		<span>欢 迎 登 陆 厂 商 平 台</span>
            </div>
            <!-- Top Menu -->
           <div id="menu" >
            	<ul class="topnav">
            	<% %>
                    <li><a href="../Company/index.jsp" id="menu-page">厂商首页&nbsp;&nbsp;&nbsp;&nbsp;</a></li>

                    <li class="equipment" onmouseover='inequipment()'onmouseout='outequipment()'>
                             <a href="javascript:;">设备管理</a>
			                 <dl id="equipment" >
			                       <dd><a href="firm_up_device.jsp" id="menu-history">上传设备信息</a></dd>
			                       <dd><a href="firmAdminDevive" id="menu-report">设备信息管理</a></dd>
			                 </dl>
                </li>
                  <li class="software" onmouseover='insoftware()'onmouseout='outsoftware()'>
                             <a href="javascript:;">软件管理</a>
			                 <dl id="software" >
			                       <dd style="background-color:red;"><a href="firm_up-soft.jsp" id="menu-report">上传软件信息</a></dd>
			                       <dd><a href="firmAdminSoft" id="menu-APP">软件信息管理</a></dd>
			                 </dl>
                 </li>
                  <li class="software" onmouseover='inmessage()'onmouseout='outmessage()'>
                             <a href="javascript:;">信息管理</a>
			                 <dl id="message" >
			                       <dd style="background-color:red;"><a href="firmAdminmsg" id="menu-report">通知消息</a></dd>
			                       <dd><a href="firm_feedback_msg.jsp" id="suggestion">意见反馈</a></dd>
			                 </dl>
                 </li>
                </ul>
		  	</div> 
		  	
            <!-- END Menu -->
        </div>
        <div class="mid" style="height:68px;width:auto;"></div>
        
         <script type="text/javascript" src="js/dropdown.js"></script>
       
        <script type="text/javascript" src="js/jquery.js"></script>
          <script type="text/javascript">
           $(document).ready(function(){ 
        	   //$("#uboxstyle").css("display","none");
        	});
         
         </script>
	    <script type="text/javascript" src="js/tablesort.js"></script>
	    <script type="text/javascript" src="js/jquery.nyromodal-1.6.2.pack.js"></script>
	    <script type="text/javascript" charset="utf-8" src="js/jquery.uniform.min.js" ></script>
	    <script type="text/javascript" src="js/jquery-ui-1.8.4.custom.min.js"></script>
	    <script type="text/javascript" charset="utf-8" src="js/custom.js" ></script>
	    
	    
		<script type="text/javascript" src="js/markitup/jquery.markitup.js"></script>
	    <script type="text/javascript" src="js/markitup/sets/html/set.js"></script>
		
		<!--[if IE]><script language="javascript" type="text/javascript" src="js/jqplot/excanvas.js"></script>
		<![endif]-->
	    <script type="text/javascript" src="js/jqplot/jquery.jqplot.js"></script>
	   	<script type="text/javascript" src="js/jqplot/plugins/jqplot.categoryAxisRenderer.js"></script>
		<script type="text/javascript" src="js/jqplot/plugins/jqplot.barRenderer.js"></script>
</body>
</html>