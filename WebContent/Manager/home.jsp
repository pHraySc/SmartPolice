<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>智能安防平台</title>
		<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
		<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="Seen Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
		<script type="application/x-javascript">
			addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }
		</script>
		
		<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
		<script src="js/jquery-1.8.3.min.js"></script>
		<script src="js/responsiveslides.min.js"></script>
		<script>
			$(function () {
			      $("#slider").responsiveSlides({
			      	auto: true,
			      	nav: true,
			      	speed: 500,
			        namespace: "callbacks",
			        pager: true,
			      });
			    });
		</script>

		<!---- start-smoth-scrolling---->
		<script type="text/javascript" src="js/move-top.js"></script>
		<script type="text/javascript" src="js/easing.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
				});
			});
		</script>
		<style>
			*{
				font-family: "微软雅黑";
			}
			.user-load{
				position: relative;
				top: -5px;
				margin-left: 8px;
				color: #506A85;
			}
			.user-reg{
				position: relative;
				top: -5px;
				margin-left: 8px;
				color: #506A85;
			}
			.l{
				position: relative;
				top: -5px;
				color: #506A85;
			}
		</style>
		<!---End-smoth-scrolling----

</head>
<body>
<!-- header -->
		<div class="header" id="home">

			<div class="header-top">
				<div class="container">
					<div class="icon">
						<ul>
							<li><i class="message"></i></li>
							<li><a href="mailto:example@mail.com">www.baidu.com</a></li>
							<li><i class="phone"></i></li>
							<li>
								<p>13018281281</p></li>
						</ul>
					</div>
					<div class="social-icons">
						<a href="ent_home/login.jsp"><i class="icon3"></i><span class="user-load">登陆</span></a>&nbsp;&nbsp;&nbsp;&nbsp; <span class="l">|</span>
						<a href="ent_home/reg.jsp"><i class="icon4"></i><span class="user-reg">注册</span></a>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="container">
				<div class="header-bottom">
					<div class="logo">
						<h1><a href="index.html">智 能 安 防</a></h1>
					</div>
					<div class="top-menu">
						<span class="menu"><img src="images/nav-icon.png" alt=""/></span>
						<ul>
							<nav class="cl-effect-5">
								<li><a href="index.html" class="active"><span data-hover="Home">home</span></a></li>
							</nav>
						</ul>
						<div class="clearfix"></div>
					</div>
					<!--script-nav-->
					<script>
						$("span.menu").click(function(){
						 $(".top-menu ul").slideToggle("slow" , function(){
						 });
						 });
					</script>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<!-- header-section-ends -->
		<div class="header-slider">
			<div class="slider">
				<div class="callbacks_container">
					<ul class="rslides" id="slider">
						<li>
							<img src="images/banner.jpg" alt="">
							<div class="caption">
								<h3>最 优 秀 的 解 决 方 案</h3>
							</div>
						</li>
						<li>
							<img src="images/banner1.jpg" alt="">
							<div class="caption">
								<h3>最 可 靠 的 技 术 团 队</h3>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		</div>
		<div class="beautifull">
			<div class="container">
				<div class="beautifull-header">
					<h4>成都大学</h4>
					<p>成都学院（成都大学）（Chengdu University），简称成大，是成都市人民政府主办的唯一一所全日制普通本科院校，实行“省市共建、以市为主”办学体制的综合大学。</p>
				</div>
				<div class="beautifull-grids">
					<div class="col-md-4 beautiful-grid">
						<div class="icon1">
							<i class="heart"></i>
						</div>
						<div class="passion">
							<h4>智能家居</h4>
							<p>成都学院（成都大学）（Chengdu University），简称成大，是成都市人民政府主办的唯一一所全日制普通本科院校，实行“省市共建、以市为主”办学体制的综合大学。</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="col-md-4 beautiful-grid">
						<div class="icon1">
							<i class="lamp"></i>
						</div>
						<div class="passion">
							<h4>智能安防</h4>
							<p>成都学院（成都大学）（Chengdu University），简称成大，是成都市人民政府主办的唯一一所全日制普通本科院校，实行“省市共建、以市为主”办学体制的综合大学。</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="col-md-4 beautiful-grid">
						<div class="icon1">
							<i class="star"></i>
						</div>
						<div class="passion">
							<h4>智能电器</h4>
							<p>成都学院（成都大学）（Chengdu University），简称成大，是成都市人民政府主办的唯一一所全日制普通本科院校，实行“省市共建、以市为主”办学体制的综合大学。</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		
		<div class="wantto-section">
			<div class="container">
				<h4>愿 意 加 入 我 们 吗？</h4>
				<p>加入我们，我们将为您提供更加便捷与高效的服务！</p>
				<a href="#" class="hvr-rectangle-in">现在加入</a>
			</div>
		</div>
		<div class="contact-section">
			<div class="container">
				<div class="contact-grids">
					<div class="col-md-3 contact-grid">
						<h5>关 于 我 们</h5>
						<p>成都大学</p>
						<a href="#" class="more">更多信息<img src="images/arrow.png"></a>
					</div>
					<div class="col-md-3 contact-grid">
						<h5>公 司 地 址</h5>
						<p>成都大学</p>
						<div class="icon2">
							<ul>
								<li><i class="indicate"></i></li>
								<li>
									<p class="label1">成都市 龙泉驿区</p>
								</li>
							</ul>
							<ul>
								<li><i class="phone"></i></li>
								<li>
									<p class="label1">0817-757575757</p>
								</li>
							</ul>
							<ul>
								<li><i class="message"></i></li>
								<li><a href="###">@ABC.com</a></li>
							</ul>
						</div>
					</div>
					<div class="col-md-3 contact-grid">
						<h5>客 服 咨 询</h5>
						<div class="cont">
							<div class="contact-leftgrid">
								<div class="img-grid1">
									<img src="images/pic7.jpg">
								</div>
								<div class="img-grid2">
									<img src="images/pic8.jpg">
								</div>
								<div class="clearfix"></div>
							</div>

							<div class="contact-rightgrid">
								<div class="img-grid3">
									<img src="images/pic9.jpg">
								</div>
								<div class="img-grid4">
									<img src="images/pic10.jpg">
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					<div class="col-md-3 contact-grid">
						<h5>联 系 客 服</h5>
						<p>客服客服客服</p>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="footer-section">
			<div class="container">
				<div class="footer-left">
					<p> Copyright &copy;2015 All rights Reserved | Design by<a href="http://w3layouts.com" target="target_blank">W3Layouts</a></p>
				</div>
				
				<div class="clearfix"></div>
				<script type="text/javascript">
					$(document).ready(function() {
							/*
							var defaults = {
					  			containerID: 'toTop', // fading element id
								containerHoverID: 'toTopHover', // fading element hover id
								scrollSpeed: 1200,
								easingType: 'linear' 
					 		};
							*/
							
							$().UItoTop({ easingType: 'easeOutQuart' });
							
						});
				</script>
				<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
			</div>
		</div>
		</body>
</html>