$(function () {
//	var healthNum = $("#healthNum").val();//保健号
	initweight = eval($("#initWeight").attr("title"));  //体重值
	initbmi = eval($("#bmi").attr("value"));   //bmi的值
	var type = $("#type").val();
	var xData = $("#xData").val();
	//获取孕妇在整个保健期的具体日期--组装成一个数组
	var totalData = $("#totalData").val();
	var totalDataArra = totalData.substring(1,totalData.length-1).split(",");
	var totalDataDay = new Array(totalDataArra.length);
	for(var i=0;i<totalDataArra.length;i++){
		var totalDataArrb = totalDataArra[i].split(":");
		totalDataDay[i] = "Date.UTC("+totalDataArrb[0]+","+(totalDataArrb[1]-1)+","+totalDataArrb[2]+")";
	}
	
	//计算健康体重趋势
	y_safecategory=[];
	
	if(type == "day" && xData != ""){//以天查询
		var xDataArra = xData.substring(1,xData.length-1).split(",");
		var xDataDay = new Array(xDataArra.length);
		for(var i=0;i<xDataArra.length;i++){
			var xDataArrb = xDataArra[i].split(":");
			xDataDay[i] = "Date.UTC("+xDataArrb[0]+","+(xDataArrb[1]-1)+","+xDataArrb[2]+")";
		}
		var start = 0;
		var end = 0;
		for(var j=0;j<=totalDataDay.length;j++){
			if(totalDataDay[j] == xDataDay[0]){
				start = j;
				continue;
			}
			if(totalDataDay[j] == xDataDay[xDataDay.length -1]){
				end = j;
				break;
			}
		}
		//配置健康曲线数据
		for(var i=start;i<=end;i++){
			if(i<=90){
				y_safecategory.push([eval(totalDataDay[i]),i/90+initweight,i/30+initweight]);
			}
			if(i>90){
				if(initbmi<18.5){
					y_safecategory.push([eval(totalDataDay[i]),i*23/380-169/38+initweight,i*3/38-78/19+initweight]);
				}else if(initbmi<=24.9){
					y_safecategory.push([eval(totalDataDay[i]),i*21/380-151/38+initweight,i*13/190-60/19+initweight]);
				}else if(initbmi<=29.9){
					y_safecategory.push([eval(totalDataDay[i]),i*3/95-35/19+initweight,i*17/380-39/38+initweight]);
				}else{
					y_safecategory.push([eval(totalDataDay[i]),i*2/95-17/19+initweight,i*3/95+3/19+initweight])
				}
			}
		}
		//console.info(y_safecategory);
	}else{//以孕周查看
		for(var i=0;i<=280;i++){
			if(i<=90){
				y_safecategory.push([i,i/90+initweight,i/30+initweight]);
			}
			if(i>90){
				if(initbmi<18.5){
					y_safecategory.push([i,i*23/380-169/38+initweight,i*3/38-78/19+initweight]);
				}else if(initbmi<=24.9){
					y_safecategory.push([i,i*21/380-151/38+initweight,i*13/190-60/19+initweight]);
				}else if(initbmi<=29.9){
					y_safecategory.push([i,i*3/95-35/19+initweight,i*17/380-39/38+initweight]);
//					alert(y_safecategory);
				}else{
					y_safecategory.push([i,i*2/95-17/19+initweight,i*3/95+3/19+initweight])
				}
			}
		}
	}
	data = $("#weightData").val();//第几周称的体重和体重值
	var dayData = "";
	if($("#type").val() == "day" || $("#type").val() == "month"){//以天查看
		//配置 数据
		var dataArra = data.substring(1,data.length-1).split(",");
		if(dataArra != "" && dataArra != null){
			dayData = "["
				for(var i=0;i<dataArra.length;i++){
					var dataArrb = dataArra[i].substring(1,dataArra[i].length-1).split(":");
					//console.info(dataArrb);
					if(i == (dataArra.length -1)){
						dayData += "[Date.UTC("+dataArrb[0]+","+(dataArrb[1]-1)+","+dataArrb[2]+"),"+dataArrb[3]+"]";
					}else{
						dayData += "[Date.UTC("+dataArrb[0]+","+(dataArrb[1]-1)+","+dataArrb[2]+"),"+dataArrb[3]+"],";
					}
				}
			dayData += "]";
			dayData = eval(dayData);
		}
		//绘制图表
		if(type == "month"){
			
		}else{
			$('#container').highcharts({
				colors: ['#191970'],
				chart: {
					type: 'spline'
				},
				title: {
					text: '孕期体重监护情况'
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
					/*dateTimeLabelFormats: { // don't display the dummy year
						//day: '%m-%d',
						day: '%m-%d',
						// year: '%Y'
					}*/
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
						text: '体重(kg)',
						style: {
							color: '#333',
							fontWeight: 'bold',
							fontSize: '12px',
							fontFamily: 'Trebuchet MS, Verdana, sans-serif'
						}
					}
				},
				tooltip: {
					shared:true,
					crosshairs: true,//竖线
					valueDecimals: 2,
					valueSuffix: 'kg',
					xDateFormat: '%Y-%m-%d',
					headerFormat:'<b>{point.key}</b><br/>'
					/*formatter: function() {
		                    return '<b>'+ Highcharts.dateFormat('%Y-%m-%d', this.x)+'</b><br/>'+
		                    this.series.name +': '+ this.y +'kg';
		            }*/
				},
				
				series: [
				         { 
						 name: '健康体重',
						 data: y_safecategory, 
						 z_index:0,
						 type: 'arearange',
						 lineWidth: 0, 
						 color: Highcharts.getOptions().colors[0], fillOpacity: 0.3
				         },
				         {
				        	 name: '孕妇体重',
				        	 data: dayData
				        	 
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
	}
	if($("#type").val() == "" || $("#type").val() == null){//以孕周查看
		data = eval($("#weightData").val());//第几周称的体重和体重值
		//绘制表格
		$('#container').highcharts({
				colors: ['#191970'],
				chart: {
					type: 'spline'
				},
				title: { text: '孕期体重监护情况' }, 
				xAxis: { 
					min:0,
		            max:280,
		            maxZoom:14,
		            tickInterval:14,
		            gridLineWidth: 1,
		            labels: {
		                step:1,
		                formatter: function () {
		                    return (this.value/7);
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
					//min:10,
		            //max:120,
		            lineWidth: 1,
		            tickWidth: 1,
		            tickInterval:10,
		            tickColor: '#000',
		            title: {
		                text: '体重(kg)',
		                style: {
		                    color: '#333',
		                    fontWeight: 'bold',
		                    fontSize: '12px',
		                    fontFamily: 'Trebuchet MS, Verdana, sans-serif'
		                }
		            }
				}, 
				tooltip: {
//					shared:true,
//		            crosshairs: true,//竖线
//		            valueDecimals: 2,
		            valueSuffix: 'kg',
		            formatter:function(){//不四舍五入
		                return   '<b>孕周:'+(((this.x/7)+"").split(".")[0])+"."+(((this.x%7)+"").split(".")[0])+
		                		 '</b><br/>'+this.series.name+':'+Highcharts.numberFormat((this.y),2,'.');
		             }
		            
				},
				series: [
				         { 
				        	 name: '健康体重',
				        	 data: y_safecategory, 
				        	 z_index:0,
				        	 type: 'arearange',
				        	 lineWidth: 0, 
				        	 color: Highcharts.getOptions().colors[0], fillOpacity: 0.3
				         },
				         { 
				        	 name:'孕妇体重',
				        	 data:data,
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
});
