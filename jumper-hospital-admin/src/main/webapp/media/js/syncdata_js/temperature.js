$(function () {
	//体温数据
	var temperatureTime = eval($(".temperatureData").val());
	//console.info(temperatureTime);
	var currentDate = $(".date").val(); //当前展示日期
	//绘制表格
    $('#container').highcharts({
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
                text: '时间',
                style: {
                    color: '#666',
                    fontSize: '14px',
                    fontFamily: '宋体'
                }
            }
        },
        yAxis: {
            /*min:36,
            max:41,*/
            lineWidth: 1,
            tickWidth: 1,
            // tickInterval:5,//5的间隔
            title: {
                text: '体温(℃)',
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
            headerFormat: '<b>'+currentDate+" {point.x}分</b><br/>",
            pointFormat: '孕妇体温：{point.y}℃'
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
        /*legend: {
            x: 0, //距离x轴的距离
            y: 100 //距离Y轴的距离
            layout: 'vertical',
	        align: 'right',//水平方向位置
	        verticalAlign: 'middle',//垂直方向位置
	       borderWidth: 0
        },*/
        series: [{
            name:'孕妇体温',
            marker: {
                fillColor: '#71C0EE',
                lineWidth: 2,
                lineColor: '14F8FB'
            },
            z_index:1,
            data:temperatureTime
        }],
        credits:{
            enabled:false
        },
        exporting:{
            enabled:false
        }
    });
    
});	

