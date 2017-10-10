$(function () {
	
	//x轴的时间	
	var buffer_time =$(".buffer_time").val();
	var time_json =eval('(' + buffer_time + ')')
//	alert(time_json);
	
	 //当前胎动json
	var fetalmoveJson = $(".fetalmoveJson_now").val(); 
	var jsonStr_now = eval('(' + fetalmoveJson + ')');
//	alert(jsonStr_now);
	//12小时胎动数
	var fetalmoveJson_twelve = $(".fetalmoveJson_twelve").val();
	var jsonStr_twelve =$.parseJSON(fetalmoveJson_twelve); 
//	alert(jsonStr_twelve);
	
    $('#container').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: null
        },
        xAxis: {
        	   gridLineWidth: 1,
        	   gridLineColor:'#ccc',//网格竖线颜色
	           type: 'datetime',
               categories: time_json,
	           labels: {  
	                 formatter: function () {  
	                     return Highcharts.dateFormat('%Y-%m-%d', this.value);  
	                 }  
	             }
            },
        yAxis: {
            title: {
                text: '胎动次数',
                style: {
                    color: '#666',
                    fontSize: '14px',
                    fontFamily: '宋体'
                }
            }
        },
        tooltip: {
            crosshairs: true,//竖线
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+
                '<b>'+Highcharts.dateFormat('%Y-%m-%d', this.x) +'</b><br/>'+
                '胎动次数：'+this.y ;
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
        	name:'12小时胎动数',
            marker: {
            	fillColor: '#6CE0DB',
                lineWidth: 2,
                lineColor: '#6CE0DB'
            },
            z_index:1,
            data:jsonStr_twelve
        },{
            name:'当次胎动数',
            marker: {
                fillColor: 'red',
                lineWidth: 2,
                lineColor: 'red'
            },
            z_index:1,
            data: jsonStr_now
        }],
        credits:{
            enabled:false
        },
        exporting:{
            enabled:false
        }
    });
}); 

