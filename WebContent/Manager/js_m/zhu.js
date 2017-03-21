// JavaScript
 $(document).ready(function() {  
   var chart = {
      type: 'column'
   };
   var title = {
      text: '统计人数'   
   };
   var subtitle = {
      text: ''  
   };
   var xAxis = {
      categories: ['通过','审核','待审核','未通过'],
      crosshair: true
   };
   var yAxis = {
      min: 0,
      title: {
         text: '总人数 (个)'         
      }      
   };
   var tooltip = {
      headerFormat: '<span style="font-size:12px">{point.key}</span><table>',
      pointFormat: '<tr style="font-size:14px" style="color:{series.color};padding:0">{series.name}: </tr>' +
         '<tr style="padding:0"><b>{point.y:.1f} 个</tr>',
      footerFormat: '</table>',
      shared: true,
      useHTML: true,
	  
   };
   var plotOptions = {
      column: {
         pointPadding: 0.2,
         borderWidth: 0
      }
   };  
   var credits = {
      enabled: false
   };
   
   var series= [{
        name: '人数',
            data: [49, 71, 106, 129],
			
        }];     
      
   var json = {};   
   json.chart = chart; 
   json.title = title;   
   json.subtitle = subtitle; 
   json.tooltip = tooltip;
   json.xAxis = xAxis;
   json.yAxis = yAxis;  
   json.series = series;
   json.plotOptions = plotOptions;  
   json.credits = credits;
   $('#containerz').highcharts(json);
  
});