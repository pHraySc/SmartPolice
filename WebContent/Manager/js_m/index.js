/**
 * 
 */
$(function(){
	$(".main_left > a").click(function(){
		var nav = $(this).next("ul");
		nav.slideToggle();
		var change=$(this).children();
		changeNode(change);
	});
	function changeNode(mainNode){
		if(mainNode){
			if(mainNode.css("background-image").indexOf("icon_m/nav_one.png")>=0){
				mainNode.css("background-image","url('icon_m/nav_one1.png')");
			}else{
				mainNode.css("background-image","url('icon_m/nav_one.png')");
			}
		}
	}
	// 点击部分
	// $(".main_left ul li").click(function(){
 //        var change_two =$(".main_left").find("i");
        

 //        if(change_two.css("background").indexOf("icon/nav_one.png")>=0){
 //        	change_two.css("background","url('icon/nav_one1.png')");
 //        }else{
 //        	change_two.css("background","url('icon/nav_one.png')");
 //        }
	// });


    // 当前位置部分
	$(".main_left li").click(function(){

		var position = $(this).parent().prev().text();//一级菜单
		var posi_child = $(this).text();//二级菜单

		var get_position = $(".content .position p").find(".dy_info").text();//位置的二级菜单名称
		// alert(get_position)

        $(".content .position p").html('');
		if(get_position==posi_child){
			$(".content .position p").append('<a href="#" class="one_nav_info">'+position+'>'+'</a>'+'<a href="#" class="dy_info">'+posi_child+'</a>');
		}else{
			var one_nav_info = $(".content .position p").find(".two_nav_info").text();
			var two_nav = $(".content .position p").find(".dy_info").text();


			$(".content .position p").append('<a href="#" class="one_nav_info">'+position+'>'+'</a>'+'<a href="#" class="dy_info">'+posi_child+'</a>')
		}
	})






	

})
 /* 年月日等时间 */
			window.onload=function startTime(){
		    	var today = new Date();
		    	var h = today.getHours();
		    	var m = today.getMinutes();
		    	var s = today.getSeconds();
		        m = checkTime(m);
		        s = checkTime(s);
		        var year=today.getFullYear();//年 
		        var month=today.getMonth()+1;//月
		        var date=today.getDate()//日
		        //获取星期
		        var week="";
		        var nunOfWeek=today.getDay();
		        switch(nunOfWeek){
		            case 0:week="星期日";break;
		            case 1:week="星期一";break;
		            case 2:week="星期二";break;
		            case 3:week="星期三";break;
		            case 4:week="星期四";break;
		            case 5:week="星期五";break;
		            case 6:week="星期六";break;
		        }
		        document.getElementById('time').innerHTML =year+"-"+month+"-"+date+" "+h+":"+m+":"+s+"  "+""+week;
		        t = setTimeout(function(){
		        	startTime();
		        },500);
		    }
		    function checkTime(i){
		        if(i<10){
		        	i="0"+i;
		        }
		        return i;
		    }