$(function () {
	//舒张压
	var lowData = $("#lowData").val();
	//收缩压
	var highData = $("#highData").val();
	if($("#type").val() == "day" || $("#type").val() == "month"){//以天或者月查询
		if(lowData == "" && highData == ""){
			lowData = eval(lowData);
			highData = eval(highData);
		}else{
			//配置舒张压
			var lowDataArra = lowData.substring(1,lowData.length-1).split(",");
			var lowDataDay = [];
			for(var i=0;i<lowDataArra.length;i++){
				var lowDataArrb = lowDataArra[i].substring(1,lowDataArra[i].length-1).split(":");
				lowDataDay[i] = "[Date.UTC("+lowDataArrb[0]+","+(lowDataArrb[1]-1)+","+lowDataArrb[2]+"),"+lowDataArrb[3]+"]";
			}
			lowData = eval("["+lowDataDay+"]");
			//console.info(lowData);
			//配置收缩压
			var highDataArra = highData.substring(1,highData.length-1).split(",");
			var highDataDay = [];
			for(var i=0;i<highDataArra.length;i++){
				var highDataArrb = highDataArra[i].substring(1,highDataArra[i].length-1).split(":");
				highDataDay[i] = "[Date.UTC("+highDataArrb[0]+","+(highDataArrb[1]-1)+","+highDataArrb[2]+"),"+highDataArrb[3]+"]";
			}
			highData = eval("["+highDataDay+"]");
			//console.info(highData);
		}
	}
    //以孕周查看
    if($("#type").val() == "" || $("#type").val() == null){
    	lowData = eval(lowData);
    	highData = eval(highData);
		//绘制表格
    	$('#container').highcharts({
				chart: {
					//renderTo: 'container',
					type: 'spline'
				},
				title: { text: '孕期血压监护情况' }, 
				xAxis: { 
					min:0,
		            max:280,
		            maxZoom:14,
		            tickInterval:14,
		            gridLineWidth: 1,
		            labels: {
		                step:1,
		                formatter: function () {
		                    return this.value/7;
		                }
		            },
		            title: {
		                text: '孕周(week)',
		                style: {
		                    color: '#333',
		                    fontWeight: 'bold',
		                    fontSize: '12px',
		                    fontFamily: 'Trebuchet MS, Verdana, sans-serif'
		                }
		            }
				}, 
				yAxis: { 
		            lineWidth: 1,
		            tickWidth: 1,
		            tickColor: '#000',
		            title: {
		                text: '血压(mmHg)',
		                style: {
		                    color: '#333',
		                    fontWeight: 'bold',
		                    fontSize: '12px',
		                    fontFamily: 'Trebuchet MS, Verdana, sans-serif'
		                }
		            }
				}, 
				tooltip: {
					crosshairs: true,//竖线
					formatter: function() {//不四舍五入
		                    return '<b>孕周:'+(((this.x/7)+"").split(".")[0])+"."+(((this.x%7)+"").split(".")[0])+"<br>"+
		                    this.series.name+":"+this.y+"mmHg"
		            }
					/*
					shared:true,
		            crosshairs: true,//竖线
		            valueDecimals: 2,
		            valueSuffix: 'bpm',
		            headerFormat:"孕周:{point.x}<br/>",*/
					
				},
				series: [
				         { 
				        	 name: '舒张压',
				        	 data: lowData, 
				        	 z_index:0,
				        	 lineWidth: 2 
				         },
				         { 
				        	 name:'收缩压',
				        	 data: highData,
				        	 marker: {
				        		 fillColor: 'white',
				        		 lineWidth: 2,
				        		 lineColor: '#00FFFF'
				        	 },
				        	 zIndex: 1 
				         }],
		         credits:{
		             enabled:false
		         },
		         exporting:{
		             enabled:false
		         }
		});
		//var chart = new Highcharts.Chart(options);
	}
    
    //以天或者月查看
    if($("#type").val() == "day" || $("#type").val() == "month"){
    	$('#container').highcharts({
			chart: {
				type: 'spline'
			},
			title: {
				text: '孕期血压监护情况'
			},
			xAxis: {
				type: 'datetime',
				title: {
					text: '天(day)',
					style: {
						color: '#333',
						fontWeight: 'bold',
						fontSize: '12px',
						fontFamily: 'Trebuchet MS, Verdana, sans-serif'
					}
				},
				labels: {  
	            	formatter: function () {  
	            		return Highcharts.dateFormat('%Y-%m-%d', this.value);  
	            	}  
	            }  
			},
			yAxis: {
				lineWidth: 1,
				tickWidth: 1,
				tickColor: '#000',
				title: {
					text: '血压(mmHg)',
					style: {
						color: '#333',
						fontWeight: 'bold',
						fontSize: '12px',
						fontFamily: 'Trebuchet MS, Verdana, sans-serif'
					}
				}
			},
			tooltip: {
				//shared:true,
				crosshairs: true,//竖线
				/*valueDecimals: 2,
				valueSuffix: 'mmHg'*/
				formatter: function() {
	                    return '<b>'+ Highcharts.dateFormat('%Y-%m-%d', this.x)+'</b><br/>'+
	                    this.series.name+":"+this.y +'mmHg';
	            }
			},
			
			series: [
			         { 
					 name: '舒张压',
					 data: lowData, 
					 z_index:0,
					 lineWidth: 2
			         },
			         {
			        	 name: '收缩压',
			        	 data: highData
			        	 
			         }
			         ],
			         credits:{
			        	 enabled:false
			         },
			         exporting:{
			        	 enabled:false
			         }
		});
    }
});	