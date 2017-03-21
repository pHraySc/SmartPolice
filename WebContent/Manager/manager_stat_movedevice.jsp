<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<script src="js/jquery-1.8.0.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="css/count_1_2_3.css"/>
		<script src="js/Chart.js"></script>
		<script src="echarts.js"></script>
		<title></title>
	</head>

	<body>
		<ol class="breadcrumb">
			<li><a href="#">首页</a></li>
			<li><a href="#">移动监控管理</a></li>
			<li class="active">移动监控统计</li>
		</ol>
 
   <div id="tem" style="width: 1000px;height: 600px;"></div>
		<script>
			window.onload=function tem_img() {
				var list = '<%=session.getAttribute("list")%>';
				
				var tem = echarts.init(document.getElementById('tem'));
				option = {
					    title: {
					        text: '移动设备统计',
				
					    },
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        },
					        formatter: function (params) {
					            var tar = params[1];
					            return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
					        }
					    },
					    grid: {
					        left: '4%',
					        right: '4%',
					        bottom: '2%',
					        containLabel: true
					    },
					    xAxis: {
					        type : 'category',
					        splitLine: {show:false},
					        data : ['总注册数','通过','未审核数','未通过']
					    },
					    yAxis: {
					        type : 'value'
					    },
					    series: [
					        {
					            name: '辅助',
					            type: 'bar',
					            stack:  '总量',
					            itemStyle: {
					                normal: {
					                    barBorderColor: 'rgba(0,0,0,0)',
					                    color: 'rgba(0,0,0,0)'
					                },
					                emphasis: {
					                    barBorderColor: 'rgba(0,0,0,0)',
					                    color: 'rgba(0,0,0,0)'
					                }
					            },
					            data: [0, 1000, 1000, 1000]
					        },
					        {
					            name: '设备数',
					            type: 'bar',
					            stack: '设备 数',
					            label: {
					                normal: {
					                    show: true,
					                    position: 'inside'
					                }
					            },
					            data:[list[2][0], list[7][0] ,list[12][0], list[17][0]]
					        }
					    ]
					};
				tem.setOption(option);
			}
		</script>
		<script type="text/javascript" src="js/echarts.js"></script>
	</body>

</html>