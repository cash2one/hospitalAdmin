$(function () {
	//当天各时段的血糖值
	var x0=null,x1=null,x2=null,x3=null,x4=null,x5=null,x6=null;
	var y0=null,y1=null,y2=null,y3=null,y4=null,y5=null,y6=null;
	var sugarData = [];
	sugarData = $("#sugarData").val().split(":");
	var sugarArra = [];
	for(var i=0;i<sugarData.length;i++){
		sugarArra = sugarData[i].substring(1,sugarData[i].length-1).split(",");
		if(sugarArra[2] == 0){
			x0 = eval(sugarArra[0]);
			y0 = eval(sugarArra[1]);
		}else if(sugarArra[2] == 1){
			x1 = eval(sugarArra[0]);
			y1 = eval(sugarArra[1]);
		}else if(sugarArra[2] == 2){
			x2 = eval(sugarArra[0]);
			y2 = eval(sugarArra[1]);
		}else if(sugarArra[2] == 3){
			x3 = eval(sugarArra[0]);
			y3 = eval(sugarArra[1]);
		}else if(sugarArra[2] == 4){
			x4 = eval(sugarArra[0]);
			y4 = eval(sugarArra[1]);
		}else if(sugarArra[2] == 5){
			x5 = eval(sugarArra[0]);
			y5 = eval(sugarArra[1]);
		}else if(sugarArra[2] == 6){
			x6 = eval(sugarArra[0]);
			y6 = eval(sugarArra[1]);
		}
	}
	//console.info(sugarData);
	var currentDate = $(".date").val(); //当前日期   
	 $('#container').highcharts({
		    colors: ['#FF0000'],
	        chart: {
	            type: 'spline'
	        },
	        title: {
	            text: null
	        },
	        xAxis: {
	            ordinal:false,
	            min:0,
	            max:24,
	            categories: ['0:00', '1:00', '2:00','3:00', '4:00', '5:00','6:00', '7:00', '8:00','9:00', '10:00', '11:00','12:00', '13:00', '14:00','15:00', '16:00', 
	            '17:00','18:00','19:00', '20:00','21:00','22:00', '23:00','24:00'], 
	            align:'center',
	            minorGridLineColor:'rgb(241,137,168)',
	            gridLineWidth: 1,//line
	            tickWidth: 1,//设置X轴坐标点宽度
	            tickInterval:1,
	            pointPadding:1,
	            minorGridLineColor:'rgb(241,137,168)',
	            title: {
	                text: '时间(time)',
	                style: {
	                    color: '#666',
	                    fontSize: '14px',
	                    fontFamily: '宋体'
	                }
	            }
	        },
	        yAxis: {
	           /* min:3,
	            max:8,
	            lineWidth: 1,
	            tickWidth: 1,
	            tickInterval:1.5,//3的间隔
*/	            title: {
	                text: '孕妇血糖',
	                style: {
	                    color: '#666',
	                    fontSize: '14px',
	                    fontFamily: '宋体'
	                }
	            },
	            labels: {
	                formatter: function() {
	                    return this.value 
	                }
	            }
	        },
	        plotOptions: {
	            spline: {
	                marker: {
	                    radius: 4,
	                    lineColor: '#666666',
	                    lineWidth: 1
	                },
			        dataLabels: { 
			        	enabled: true
			        }
			       // enableMouseTracking: false 
	            }
	        },
	        series: [{
	            name:'早餐前',
	            marker: {
	                lineWidth: 2,
	                fillColor: 'red'
	            },
	            z_index:1,
	            tooltip: {
		            crosshairs: true,//竖线
		            headerFormat: '<b>'+currentDate+' {point.x}</b><br/>',
		            pointFormat: '当餐正常血糖上限:5.1<br/>早餐前：{point.y}'
		        },
	            data: [
	                    {x:x0,y:y0, marker:{ fillColor: 'red'}}
	            ]
	        },{
	            name:'早餐后',
	            marker: {
	                lineWidth: 2,
	                fillColor: 'pink'
	            },
	            tooltip: {
		            crosshairs: true,//竖线
		            headerFormat: '<b>'+currentDate+' {point.x}</b><br/>',
		            pointFormat: '当餐正常血糖上限:5.8<br/>早餐后：{point.y}'
		        },
	        	data:[
	        	      {x:x1,y:y1, marker:{ fillColor: 'pink'}}
	        	]
	        },{
	            name:'午餐前',
	            marker: {
	                colorByPoint: true,
	                lineWidth: 2,
	                fillColor:'blue',
	                lineColor: 'blue'
	            },
	            tooltip: {
		            crosshairs: true,//竖线
		            headerFormat: '<b>'+currentDate+' {point.x}</b><br/>',
		            pointFormat: '当餐正常血糖上限:5.1<br/>午餐前：{point.y}'
		        },
		        data:[
	        	      {x:x2,y:y2, marker:{ fillColor: 'pink'}}
	        	]
	        },{
	            name:'午餐后',
	            marker: {
	                colorByPoint: true,
	                lineWidth: 2,
	                lineColor: 'yellow'
	            },
	            tooltip: {
		            crosshairs: true,//竖线
		            headerFormat: '<b>'+currentDate+' {point.x}</b><br/>',
		            pointFormat: '当餐正常血糖上限:5.8<br/>午餐后：{point.y}'
		        },
		        data:[
	        	      {x:x3,y:y3, marker:{ fillColor: 'pink'}} 
	        	]
	        },{
	            name:'晚餐前',
	            marker: {
	                colorByPoint: true,
	                lineWidth: 2,
	                lineColor: 'black'
	            },
	            tooltip: {
		            crosshairs: true,//竖线
		            headerFormat: '<b>'+currentDate+' {point.x}</b><br/>',
		            pointFormat: '当餐正常血糖上限:5.1<br/>晚餐前：{point.y}'
		        },
	            data:[
	        	      {x:x4,y:y4, marker:{ fillColor: 'pink'}}
	        	]
	        },{
	            name:'晚餐后',
	            marker: {
	                colorByPoint: true,
	                lineWidth: 2,
	                lineColor: 'green'
	            },
	            tooltip: {
		            crosshairs: true,//竖线
		            headerFormat: '<b>'+currentDate+' {point.x}</b><br/>',
		            pointFormat: '当餐正常血糖上限:5.8<br/>晚餐后：{point.y}'
		        },
	            data:[
	        	      {x:x5,y:y5, marker:{ fillColor: 'pink'}} 
	        	]
	        },{
	            name:'睡前',
	            marker: {
	                colorByPoint: true,
	                lineWidth: 2,
	                lineColor: 'purple'
	            },
	            tooltip: {
		            crosshairs: true,//竖线
		            headerFormat: '<b>'+currentDate+' {point.x}</b><br/>',
		            pointFormat: '当餐正常血糖上限:5.1<br/>睡前：{point.y}'
		        },
	            data:[
	        	      {x:x6,y:y6, marker:{ fillColor: 'pink'}},
	        	]
	        },{
	            name:'餐前血糖上限',
	            marker: {
	                colorByPoint: true,
	                lineWidth: 1,
	                lineColor: '#FF0000'
	            },
	            tooltip: {
		            crosshairs: true,//竖线
		            headerFormat: '<b>'+currentDate+' {point.x}</b><br/>',
		            pointFormat: '餐前血糖上限:5.1'
		        },
		        lineWidth: 0.8,//粗细：lineWidth  默认值为2
		        dashStyle:'dash',
	            data:[
	        	      {x:0, y:5.1},
	        	      {x:24, y:5.1}
	        	]
	        },{
	            name:'餐后血糖上限',
	            marker: {
	                colorByPoint: true,
	                lineWidth: 0,
	                lineColor: '#FF0000'
	            },
	            tooltip: {
		            crosshairs: true,//竖线
		            headerFormat: '<b>'+currentDate+' {point.x}</b><br/>',
		            pointFormat: '餐后血糖上限:8.5'
		        },
		        dashStyle:'dash',
		        lineWidth: 0.8,//粗细：lineWidth  默认值为2
	            data:[
	        	      {x:0, y:8.5},
	        	      {x:24, y:8.5}
	        	]
	        }],
	        credits:{
	            enabled:false
	        },
	        exporting:{
	            enabled:false
	        }
	    });
});	