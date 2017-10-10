$(function(){
	//首先展示第一次的监测的数据
	 //血氧json
	 var oxyJson = eval($("#oxyJson").val()); 
	 //console.info(oxyJson);
	 //心率json
	 var pulseJson = eval($("#pulseJson").val());
	 //console.info(pulseJson);
	 currentDate = $("#addDate").val();
	 var options = {
	    	chart: {
	    		renderTo: 'container',
	            type: 'spline'
	        },
	        title: {
	            text:'血氧图'
	        },
	        xAxis: {
	            min:0,
	            max:20,
	            gridLineWidth: 1,
	            tickInterval:1,
	            pointPadding:1,
	            tickPixelInterval:20,
	            minorGridLineColor:'rgb(241,137,168)',
	            title: {
	                text: '分钟(min)',
	                style: {
	                    color: '#666',
	                    fontSize: '14px',
	                    fontFamily: '宋体'
	                }
	            }
	        },
	        yAxis: {
	            labels: {
	                formatter: function() {
	                    return this.value 
	                }
	            },
	           /* min:50,
	            max:120,*/
	            lineWidth: 1,
	            tickWidth: 1,
	           // tickInterval:10,
	            title: {
	                text: 'BPM',
	                style: {
	                    color: '#666',
	                    fontSize: '14px',
	                    fontFamily: '宋体'
	                    // writingMode:'tb-rl'    //文字竖排样式
	                }
	            }
	        },
	        tooltip: {
	        	shared:true,
	            crosshairs: true,//竖线
	            valueDecimals: 2,
	            valueSuffix: 'bpm',
	            headerFormat:""
	            /*headerFormat:"{point.x}min "+currentDate+"<br/>",
	            pointFormat: 'BPM: {point.y}<br/>',
	            footerFormat:'BPM：{point.y}',*/
	        },
	        plotOptions: {
	            spline: {
	                marker: {
	                    radius: 4,
	                    lineColor: '#E98F4B',
	                    lineWidth: 1
	                }
	            }
	        },
	        /*legend: {
	            layout:'vertical',
	            align: 'right', //水平方向位置
	            verticalAlign: 'center', //垂直方向位置
	            x: 222, //距离x轴的距离
	            y: 180 //距离Y轴的距离

	        },*/
	        series: [{
	        	name: '血氧饱和度',
	            color:'#EFC7A7',
	            marker: {
	                fillColor: '#EB944F',
	                lineWidth: 2,
	                lineColor: '#EB944F'
	            },
	            z_index:3,
	            data: oxyJson
	        },{
	        	name: '心率',
	            marker: {
	                fillColor: '#71C0EE',
	                lineWidth: 2,
	                lineColor: '14F8FB'
	            },
	            z_index:3,
	            data: pulseJson
	        }],
	        credits:{
	            enabled:false
	        },
	        exporting:{
	            enabled:false
	        }
	     }
	 var chart = new Highcharts.Chart(options);
	 //当点击血氧查看按钮时
	 $(document).on("click",".lookOxygen",function(){
		$("#addDate").val($(this).attr("time"));
		$("#id").val($(this).attr("name"));
		$("#monitorTimeLength").val($(this).attr("length"));
		$("#monitorPregnant").val($(this).attr("pregnant"));
		$("#averageOxygen").val($(this).attr("oxygen"));
		$("#averagePulse").val($(this).attr("pulse"));
		$(".submitForm").submit();
	});

});	
