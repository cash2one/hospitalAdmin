$(function () {
	//查看类型
	var type = $("#type").val();
	//胆固醇
	var cholData = $(".cholData").val();
	//甘油三酯
	var trigData = $(".trigData").val();
	//高密度蛋白质
	var hdlData = $(".hdlData").val();
	//低密度蛋白质
	var ldlData = $(".ldlData").val();
	//胆固醇-高密度蛋白质
	var cholHdlData = $(".cholHdlData").val();
	if(type == "day" || type == "month"){//以月和天查看
		if(cholData == "" && trigData == "" && hdlData == "" && ldlData == "" && cholHdlData == ""){
			cholData = eval(cholData);
			trigData = eval(trigData);
			hdlData = eval(hdlData);
			ldlData = eval(ldlData);
			cholHdlData = eval(cholHdlData);
		}else{
			//配置胆固醇
			cholData = getChartXtime(cholData);
			cholData = eval(cholData);
			//配置甘油三酯
			trigData = getChartXtime(trigData);
			trigData = eval(trigData);
			//配置高密度蛋白质
			hdlData = getChartXtime(hdlData);
			hdlData = eval(hdlData);
			//配置低密度蛋白质
			ldlData = getChartXtime(ldlData);
			ldlData = eval(ldlData);
			//配置胆固醇-高密度蛋白质
			cholHdlData = getChartXtime(cholHdlData);
			cholHdlData = eval(cholHdlData);
			
			$('#container').highcharts({
				chart: {
					type: 'spline'
				},
				title: {
					text: '孕期血脂监护情况'
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
						text: '血脂(mmol/L)',
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
					formatter: function() {
		                    return '<b>'+ Highcharts.dateFormat('%Y-%m-%d', this.x)+'</b><br/>'+
		                    this.series.name+":"+this.y+"mmol/L";
		            }
				},
				
				series: [{
					name:'胆固醇',
					marker: {
						fillColor: '#71C0EE',
						lineWidth: 2,
						lineColor: '14F8FB'
					},
					z_index:1,
					data: cholData
				},{
					name:'甘油三酯',
					marker: {
						fillColor: 'red',
						lineWidth: 2,
						lineColor: 'red'
					},
					z_index:1,
					data: trigData
				},{
					name:'高密度蛋白质',
					marker: {
						fillColor: 'pink',
						lineWidth: 2,
						lineColor: 'pink'
					},
					z_index:1,
					data: hdlData
				},{
					name:'低密度蛋白质',
					marker: {
						fillColor: 'blue',
						lineWidth: 2,
						lineColor: 'blue'
					},
					z_index:1,
					data: ldlData
				},{
					name:'胆固醇/高密度蛋白质',
					marker: {
						fillColor: 'yellow',
						lineWidth: 2,
						lineColor: 'yellow'
					},
					z_index:1,
					data: cholHdlData
				}],
		        credits:{
		        	enabled:false
		        },
		        exporting:{
		        	enabled:false
		        }
			});
		}
	}else{//以孕周查看
		cholData = eval(cholData);
		trigData = eval(trigData);
		hdlData = eval(hdlData);
		ldlData = eval(ldlData);
		cholHdlData = eval(cholHdlData);
		$('#container').highcharts({
			chart: {
				type: 'spline'
			},
			title: {
				text: '孕期血脂监护情况'
			},
			xAxis: {
				min:0,
				max:40,
				align:'right',
				minorGridLineColor:'rgb(241,137,168)',
				gridLineWidth: 1,//line
				tickInterval:2,
				tickWidth: 1,//设置X轴坐标点宽
				pointPadding:1, 
				minorGridLineColor:'rgb(241,137,168)',
				title: {
					text: '孕周(Week)',
					style: {
						color: '#666',
						fontSize: '14px',
						fontFamily: '宋体'
					}
				}
			},
			yAxis: {
				lineWidth: 1,
				tickWidth: 1,
				title: {
					text: '血脂(mmol/L)',
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
			tooltip: {
				crosshairs: true,//竖线
				formatter: function() {
	                    return '<b>孕周:'+this.x+'</b><br/>'+
	                    this.series.name+":"+this.y+"mmol/L";
	            }
			},
			plotOptions: {
				spline: {
					marker: {
						radius: 4,
						lineColor: '#666666',
						lineWidth: 1
					}
				}
			},
			series: [{
				name:'胆固醇',
				marker: {
					fillColor: '#71C0EE',
					lineWidth: 2,
					lineColor: '14F8FB'
				},
				z_index:1,
				data: cholData
			},{
				name:'甘油三酯',
				marker: {
					fillColor: 'red',
					lineWidth: 2,
					lineColor: 'red'
				},
				z_index:1,
				data: trigData
			},{
				name:'高密度蛋白质',
				marker: {
					fillColor: 'pink',
					lineWidth: 2,
					lineColor: 'pink'
				},
				z_index:1,
				data: hdlData
			},{
				name:'低密度蛋白质',
				marker: {
					fillColor: 'blue',
					lineWidth: 2,
					lineColor: 'blue'
				},
				z_index:1,
				data: ldlData
			},{
				name:'胆固醇/高密度蛋白质',
				marker: {
					fillColor: 'yellow',
					lineWidth: 2,
					lineColor: 'yellow'
				},
				z_index:1,
				data: cholHdlData
			}],
			credits:{
				enabled:false
			},
			exporting:{
				enabled:false
			}
		});
	}
	function getChartXtime(data){
    	var dataArra = data.substring(1,data.length-1).split(",");
    	var dataDay = [];
    	for(var i=0;i<dataArra.length;i++){
    		var dataArrb = dataArra[i].substring(1,dataArra[i].length-1).split(":");
    		dataDay[i] = "[Date.UTC("+dataArrb[0]+","+(dataArrb[1]-1)+","+dataArrb[2]+"),"+dataArrb[3]+"]";
    	}
    	data = "["+dataDay+"]";
    	return data;
    }
}) 