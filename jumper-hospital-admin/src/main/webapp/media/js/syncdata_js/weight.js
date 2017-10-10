$(function () {
	//var currentDate = $("#c").text(); //当前日期
	var healthNum = $("#healthNum").val();//保健号
	initweight = eval($("#initWeight").attr("title"));  //体重值
	initbmi = eval($("#bmi").attr("value"));   //bmi的值
	//计算健康体重趋势
	y_safecategory=[];
	for(var i=0;i<=280;i++){
		if(i<=90){
			y_safecategory.push([i,i/90+initweight,i/30+initweight]);
		}
		if(i>=90){
			if(initbmi<18.5){
				y_safecategory.push([i,i*23/380-169/38+initweight,i*3/38-78/19+initweight]);
			}else if(initbmi<=24.9){
				y_safecategory.push([i,i*21/380-151/38+initweight,i*13/190-60/19+initweight]);
			}else if(initbmi<=29.9){
				y_safecategory.push([i,i*3/95-35/19+initweight,i*17/380-39/38+initweight]);
				alert(y_safecategory);
			}else{
				y_safecategory.push([i,i*2/95-17/19+initweight,i*3/95+3/19+initweight])
			}
		}
	}
	weekandweight = eval($("#weightData").val());//第几周称的体重和体重值
	title = "孕周(week)";
	min = 0;
	max = 280;
	//不同查看模式切换
	$(".look").on("click", function(){
		var showType = $(this).attr("title");
		var timeFlag = $("#calendar_choose").val();
		window.location.href = "weight?healthNum="+healthNum+"&token="+"&showType="+showType+"&timeFlag="+timeFlag;
	});
	xData = $("#xData").val();
	console.info(weekandweight);
	console.info($("#xData").val());
	//绘制表格
	//$('#container').highcharts({
	var options={
			chart: {
	    		renderTo: 'container',
	            type: 'spline'
	        },
	        title: {
	            text:'血氧图',
	        },
	        xAxis: {
	        	categories: [20160315, 20160316, 20160317, 20160318, 20160319, 20160320, 20160321, 20160322, 20160323, 20160324, 20160325, 20160326, 20160327, 20160328, 20160329, 20160330, 20160331, 20160401, 20160402, 20160403, 20160404, 20160405, 20160406, 20160407, 20160408, 20160409, 20160410, 20160411, 20160412, 20160413],
	            align:'right',
	            minorGridLineColor:'rgb(241,137,168)',
	            gridLineWidth: 1,//line
	            tickWidth: 1,//设置X轴坐标点宽度
	            //tickInterval:1,
	           pointPadding:1,
	            minorGridLineColor:'rgb(241,137,168)',
	            title: {
	                text: '时间',
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
	            min:20,
	            max:120,
	            lineWidth: 1,
	            tickWidth: 1,
	            tickInterval:10,
	            title: {
	                text: 'BPM',
	                style: {
	                    color: '#666',
	                    fontSize: '14px',
	                    fontFamily: '宋体',
	                    // writingMode:'tb-rl'    //文字竖排样式
	                }
	            }
	        },
	        tooltip: {
	            crosshairs: true,//竖线
	            headerFormat:"{point.x}min",
	            pointFormat: 'BPM: {point.y}<br/>',
	            footerFormat:'BPM：{point.y}',
	            // style:{
	            //     text:center
	            // }
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
	        legend: {
	            layout:'vertical',
	            align: 'right', //水平方向位置
	            verticalAlign: 'center', //垂直方向位置
	            x: 222, //距离x轴的距离
	            y: 180 //距离Y轴的距离

	        },
	        series: [{
	           color:'#EFC7A7',
	            marker: {
	                fillColor: '#EB944F',
	                lineWidth: 2,
	                lineColor: '#EB944F'
	            },
	            z_index:3,
	            data: weekandweight
	        }],
	        credits:{
	            enabled:false
	        },
	        exporting:{
	            enabled:false
	        }
        /*colors: ['rgba(67,205,128,0.5)','gray'],//绘图线颜色
        chart: {
        	renderTo: 'container',
            borderWidth: 0,//边框线
            borderColor:'red',//边框线颜色
            plotBackgroundColor: 'rgba(255, 255, 255, .9)',//网格背景颜色
            plotShadow: true,//图表是否使用阴影效果 reflow: false
            plotBorderWidth: 1,//图表的边框大小
            // zoomType: need_zoom,
            panning: true,//平移
            panKey: 'shift',//转向
            backgroundColor: {
                linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },//渐变线
                stops: [
                    [0, 'rgb(255, 255, 255)'],
                    [1, 'rgb(240, 240, 255)']
                ]} ,
            event: {
                mousedown: function (event) {
                    var text,
                        label;
                    if (event.xAxis) {
                        text = 'min: ' + Highcharts.numberFormat(event.xAxis[0].min, 2) + ', max: ' + Highcharts.numberFormat(event.xAxis[0].max, 2)
                    } else {
                        text = 'Selection reset'
                    }
                    label = this.renderer.label(text, 100, 120)
                        .attr({
                            fill: Highcharts.getOptions().colors[0],
                            padding: 10,
                            r: 5,
                            zIndex: 8
                        })
                        .css({
                            color: '#FFFFFF'
                        })
                        .add();

                    setTimeout(function () {
                        label.fadeOut()
                    }, 1000);
                }
            }
        },
        title: {
            text: null,
            x: -20 ,
            style: {
                color: '#000',
                font: 'bold 16px "Trebuchet MS", Verdana, sans-serif'
            }
        },
        plotOptions:{
            line:{
                cursor:'pointer'//鼠标变手形状
            }
        },
        xAxis: {
        	min:min,
            max:max,
            maxZoom:14,
            tickInterval:14,
            gridLineWidth: 1,
            gridLineColor:'rgb(241,137,168)',
            minorGridLineColor:'rgb(241,137,168)',
            lineColor: 'rgb(241,137,168)',
            tickColor: 'rgb(241,137,168)',
            labels: {
                step:1,
                style: {
                    color: '#808080',
                    font: '11px Trebuchet MS, Verdana, sans-serif'
                },
                formatter: function () {
                    return this.value/7
                }
            },
            title: {
                text: title,
                style: {
                    color: '#333',
                    fontWeight: 'bold',
                    fontSize: '12px',
                    fontFamily: 'Trebuchet MS, Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min:10,
            max:100,
            // minorTickInterval:(need_zoom=='none')?10:5,
            plotLines: [{//标示线
                value: 0,
                width: 1,
                color: 'rgb(241,137,168)'
            }],
            gridLineColor:'rgb(241,137,168)',
            minorGridLineColor:'rgb(241,137,168)',
            lineColor: 'rgb(241,137,168)',
            lineWidth: 1,
            tickWidth: 1,
            tickInterval:10,
            tickColor: 'rgb(241,137,168)',
            labels: {
                style: {
                    color: '#808080',
                    font: '11px Trebuchet MS, Verdana, sans-serif'
                }
            },
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
            // pointFormatter: function() {
      //        return '<span style="color:{'+this.series.color+'}">u25CF</span> {'+
      //            this.series.name+'}: <b>{'+this.y+'}</b><br/>.'
            // }
            formatter:function(){
                // var pre_date=new Date(new Date(expectedDate)-(280-this.x)*3600*24*1000);
                // pre_date=pre_date.getFullYear()+'年'+(pre_date.getMonth()+1)+'月'+pre_date.getDate()+'日';
                var week=parseInt(this.x/7);
                var day=(this.x%7==0)?0:this.x%7;
                var aday = Math.round(day,1);
                var s="<b>"+week+"周"+aday+"天</b><br/>";
                if(this.points.length==1&&this.points[0]['color']!="rgba(67,205,128,0.5)"){
                    $.each(this.points,function(){
                        var safe=y_safecategory[this.x];
                        s+='<br><b>健康体重:</b>'+safe[1].toFixed(1)+'kg'+'~'+safe[0].toFixed(1)+'kg'+'<br><b>'+this.series.name+':</b>'+this.y+'kg'
                    })
                }else{
                    $.each(this.points,function(){
                        if(this.point.high==this.point.low){
                            s+='<br><b>'+this.series.name+':</b>'+this.y+'kg'
                        }else{
                            s+='<br><b>'+this.series.name+':</b>'+this.point.low.toFixed(1)+'kg'+'~'+this.point.high.toFixed(1)+'kg'
                        }
                    })
                }
                return s;
            }
        },
        legend: {
            // enabled:(need_zoom=='none')?false:true,
            layout: 'vertical',//标示水平排列
            align: 'right',//排列在右边
            verticalAlign: 'middle',//上下居中
            borderWidth: 0,//边框线
            itemStyle: {//字体设置
                font: '9pt Trebuchet MS, Verdana, sans-serif',
                color: 'black'
            }
        },
        labels: {
            style: {
                color: '#99b'
            }
        },
        navigation: {
            buttonOptions: {
                theme: {
                    stroke:null
                }
            }
        },
        series: [{
            type:'arearange',
            name:'健康体重',
            data:y_safecategory,
            z_index:0
        },{
            name:'孕妇体重',
            data:weekandweight,
            marker: {
                fillColor: 'white',
                lineWidth: 2,
                lineColor: '#00FF66'
            },
            z_index:1
        }],
        credits:{
            enabled:false//隐藏右下角网址
        },
        exporting:{
            enabled:false//隐藏右上角打印
        }*/
    };
	var chart = new Highcharts.Chart(options);
	
	if(xData != "[]"){//说明是以月或者天查看数据
		title = "天(day)";
		xData = eval($("#xData").val());
		console.info(xData);
    	options.xAxis={
    		categories: xData,
    		 align:'right',
             minorGridLineColor:'rgb(241,137,168)',
             gridLineWidth: 1,//line
             tickWidth: 1,//设置X轴坐标点宽度
             //tickInterval:1,
            pointPadding:1,
             minorGridLineColor:'rgb(241,137,168)',
             title: {
                 text: '时间',
                 style: {
                     color: '#666',
                     fontSize: '14px',
                     fontFamily: '宋体'
                 }
             }
    	};
    	var chart = new Highcharts.Chart(options);
	}
	//当点击日期搜索的确定按钮时
    $(".submitBtn").bind("click",function(){
    	var startTime = $("#data1").val();
    	var endTime = $("#data2").val();
    	//var healthNum = $("#healthNum").val();
    	window.location.href = "weight?healthNum="+healthNum+"&startTime="+startTime+"&endTime="+endTime;
    })
});
