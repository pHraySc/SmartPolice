$(function($){
    $("a").click(function(){
    	$(this).next("ul").slideToggle(200).closest("li").siblings("li").children("ul").slideUp(200);
		$(this).next("ul").closest("li").children(".menu-a").css({"background-color":"#589EC5","color":"white"});
		$(this).next("ul").closest("li").css("background-color","#589EC5");
		$(this).next("ul").closest("li").prev("div").css("display","block");
		$(this).next("ul").closest("li").siblings("li").children(".menu-a").css({"background-color":"#D4E6F0","color":"#333333"});
		$(this).next("ul").closest("li").siblings("li").css("background-color","#D4E6F0");
		$(this).next("ul").closest("li").siblings("li").prev("div").css("display","none");
		$(this).next("ul").closest("li").children(".menu-a").children(".G").addClass("glyphicon-chevron-right-after");
		$(this).next("ul").closest("li").siblings("li").children(".menu-a").children(".G").removeClass("glyphicon-chevron-right-after");
    })
});
$(function() {
	$(".click").click(function() {
		$(".click").css("background-color","#F0F9FD");
		$(this).css("background-color","lightgray");
	});
});