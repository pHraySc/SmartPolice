<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="css/first.css"/>
		<title>首页</title>
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script> 
<script type="text/javascript"> 
//日历——————————————————————————————————————————
$(function() {
	
	//绘制月历体——————————————————————————————————————————
	
	 for(var i=0;i<35;i++){
	 $("<li><span></span></li>").appendTo(".month-body").addClass("month-cell"); 
	 }

	  var today=new Date()  
	  
	  var FullYear = today.getFullYear(); //获取年份
	  var m = today.getMonth();           //获取月号
	  var month = today.getMonth()+1;     //获取月份
	  
	  if(month<10){
		 month="0"+month; 
	  }
	  var date = today.getDate();	      //获取日期
	  var day = today.getDay();           //获取星期
	  
	  var monthsNum=[31,28,31,30,31,30,31,31,30,31,30,31];
	  var isleapyear = FullYear%4;        //判断闰年
	  if(isleapyear==0){
		  monthsNum[1]=29;
	  }
  
      if(day==0){
		  day = 7;
	  }
	  var firstDay = day-(date%7-1);       //!important 计算月初星期数

	  if(firstDay==7){                     //如果月初为七，归零
		  firstDay =0;
	  }
	  if(firstDay<0){                       //如果月初为负，加七循环
		  firstDay +=7;
	  }
	  
	  var f = firstDay;
	  for(var j=1;j<=monthsNum[m];j++){
		  $("li.month-cell span").eq(f).text(j).parent().addClass("pink");
		  f++; 
	  }
	  
	  $("li.month-cell span").eq(firstDay-1+date).parent().addClass("red");
	  
	  $(".month-head span").text(FullYear+"年"+month+"月");
	  
})

</script>
</head>
<body>
<div class="mid">
	  <div class="top">
			  <div class="first">
						  <div class="first-left"><img alt="online" src="img/online.png"></div>
						   <div class="first-right">
									   <div class="first-right-top">100</div>
									   <div class="first-right-bottom">当前在线人数</div>
						   </div>
			  </div>
			  <div class="second">
						  <div class="second-left"><img alt="offline" src="img/offliner.png"></div>
						  <div class="second-right">
						               <div class="second-right-top">50000</div>
									   <div class="second-right-bottom"> 新增设备数</div>
						  </div>
			  </div>
			  <div class="third">
						  <div class="third-left"><img alt="adduser" src="img/adduser.png"></div>
						<div class="third-right">
						              <div class="third-right-top">3000</div>
									   <div class="third-right-bottom"> 新 增 用 户 </div>
						</div>
			  </div>
	  
	  </div>
	
	<div class="month-container">
      <div class="month-head"><span></span></div>
      <ul class="month-body">
      <div class="month-cell orange"><span>日</span></div>    
      <div class="month-cell blue"><span>一</span></div>
      <div class="month-cell blue"><span>二</span></div>
      <div class="month-cell blue"><span>三</span></div>
      <div class="month-cell blue"><span>四</span></div>     
      <div class="month-cell blue"><span>五</span></div>      
      <div class="month-cell blue"><span>六</span></div>
      </ul>
      <div class="clear"></div>
</div>
	</div>
	
	
	
	
		</body>
	
	</html>