// JavaScript Document
$( function(){
$("#tab_t li.select").click(function(){
	$(".notice_noc").children().first().css("display","block");
	$(".notice_noc").children().last().css("display","none");

	
	
	})
$("#tab_t li.select1").click(function(){
	$(".notice_noc").children().first().css("display","none");
	$(".notice_noc").children().last().css("display","block");
	})
});


 $(document).ready(function(){
       //常规的获取ID
        var tab_t = document.getElementById("tab_t");
        var tab_t_li = tab_t.getElementsByTagName("li");
      
       //第一次遍历循环 
        for(var i=0; i<tab_t_li.length; i++){
            tab_t_li[i].index=i;     //添加索引值
            tab_t_li[i].onclick=function(){
       //第二次循环重置样式（每一个都不添加样式内容也隐藏）
                for(var j=0;j<tab_t_li.length;j++){
                    tab_t_li[j].className='';
                   
                   }
                tab_t_li[this.index].className = 'select';
               
                 }
         }
        
    })









