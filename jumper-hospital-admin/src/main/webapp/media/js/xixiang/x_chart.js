/**
 * Canvas胎心图工具类
 * @auther rent
 * @date 2015-10-30
 **/
var ChartData = [];
function Chart(){
	var me = this;
 	me.curNstData = "";
	/** 浏览器是否支持canvas **/
	me.isCanvas = true;
	/** 选择器正则 **/
	me.idExpr = /^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/;
	me.classExpr = /^(?:\s*(<[\w\W]+>)[^>]*|.([\w-]*))$/;
	
	/** Y轴每一格之间的距离 **/
	me.vertical_size = 20;
	/** X轴每一格之间的距离 **/
	me.horizontal_size = 40; //一分钟之内的小格长度
	/** 垂直多少个cell **/
	me.required_vertical = 21;
	/** 水平多少个cell **/
	me.required_horizontal = 60;
	/** 左侧Y轴坐标宽度 **/
	me.label_weight = 50;
	/** 底部X轴坐标高度 **/
	me.label_height = 50;
	/** canvas宽度 **/
	me.canvas_width = 1400;
	/** canvas高度 **/
	me.canvas_height = 420;
	/** 宫缩垂直多少个cell **/
	me.uterus_vertical = 11;
	/** 宫缩图的高度 **/
	me.uterus_height = 200;
	
	
	/** 拖动点计算 **/
	me.offsetX = 0;
	me.offsetY = 0;
	me.default_start = 0;
	me.default_end = 0;
	me.start_point = 0;
	me.end_point = 0;
	me.offset_can = 0;

    /** 选取以后结果 **/
    me.result_start = 0;
    me.result_end = 1200;
	/** 颜色值数组定义 **/
	me.colorSelect = {
		"line" : "rgb(241,137,168)",//红色线条颜色
		"safe" : "rgba(67,205,128,0.3)",//绿色正常背景填充颜色，0.3的透明度
		"black" : "rgb(0,0,0)",
		"grey" : "rgba(153,153,153,0.5)",
		"green": "rgba(44,53,247,1)",
		"transparent": "rgba(0,0,0,0)"
	};
	
	me.endX_point = 0; //取得最后一个点的x坐标
	me.isLoad = true;
	
	me.speed = window.location.search.split('speed=')[1] || 3;//得到走纸速度
	
	me.id = '';
	me.power = 1.1667022051405838;
	//是否可点击
	me.isClick = false;
};
Chart.prototype = {
	/** DOM查询canvas对象 **/
	get : function(id){
		return document.getElementById(id);
	},
	/** 获取胎心图的宽度 **/
	getCanvasWidth : function(){
		var me = this;
        if(me.speed == 1){
        	me.horizontal_size = 26.6;
//      	me.canvas_width = 2100;
        }else if(me.speed == 2){
        	me.horizontal_size = 35;
//      	me.canvas_width = 1620;
        }else if(me.speed == 3){
        	me.horizontal_size = 51.87;
//      	me.canvas_width = 1080;
        }else if(me.speed == 20){
        	me.horizontal_size = 23.33;
        	
//      	me.canvas_width = 2400;
        }else if(me.speed == 30){
//      	me.canvas_width = 3600;
        }
		return me.canvas_width;
	},
	/** 获取胎心图的高度 **/
	getCanvasHeight : function(){
		return this.canvas_height;
	},
	/** 获取左侧Y轴坐标宽度 **/
	getLabelWeight : function(){
		return this.label_weight;
	},
	/** 获取底部X轴坐标高度 **/
	getLabelHeight : function(){
		return this.label_height;
	},
	/** 获取宫缩图的高度 **/
	getUterusHeight : function(){
		return this.uterus_height;
	},
	/** 是否是整数 **/
	isInteger : function(object){
		return typeof object === 'number' && object % 1 === 0;
	},
	getDataByElement : function(element){
		for(var i = 0;i < ChartData.length; i++){
			var item = ChartData[i];
			for(var key in item){
				if(key == element){
					return item[key];
				}
			}
		}
	},
	/*
	 * 
	 *	fhr: 曲线 data
	 * 	bl:  基线 baseData
	 *	ua:  宫缩 uterusData
	 * 	fmp: 胎动 fetalMoveData
	 *  ad:  曲线图标注 加速,减速 adData
	 * 
	 * */
	 
	loadData: function() {
		if(!window.location.search) return false;
	 	var location = window.location.search.split('?')[1].split('&');
		var element = 'chart' + location[0].split('=')[1];
		if(!location[1] && !location[2] && !location[3]) return false;
		var start = location[1].split('=')[1];
		var end = location[2].split('=')[1];
		if(!start || !start) return false;
		var speed = location[3].split('=')[1];
		var fhr = $.trim($('.content-canvas .data').val()) && JSON.stringify(JSON.parse($('.content-canvas .data').val()).slice(start,end)); 
		var ua = $.trim($('.content-canvas .uterusData').val()) && JSON.stringify(JSON.parse($('.content-canvas .uterusData').val()).slice(start,end));
		var fetalMoveData = $('.content-canvas .fetalMoveData').val();
		(typeof fetalMoveData == "string") && (fetalMoveData = fetalMoveData.split(','));
		var fmp = $.trim(fetalMoveData) && JSON.stringify(fetalMoveData);
		var bl = $.trim($('.content-canvas .baseData').val()) && JSON.stringify($('.content-canvas .baseData').val());
		var ad = $.trim($('.content-canvas .adData').val()) && JSON.stringify($('.content-canvas .adData').val());
		if(element && JSON.parse(fhr) && JSON.parse(fhr).length > 0){
			return myData = "{"+element+":{\"fhr_data\":["+fhr+"], \"bl_data\":["+bl+"], \"ad_data\":["+ ad +"], \"ua_data\":["+ua+"], \"fmp_data\":["+fmp+"]}}";
		}
		else{
			return false;
		}
	},
	/** 总入口，准备数据，调用插件 **/
	init : function(config){
		var me = this;
		
		/** 解析数据 **/
		var element = config.element;
		var data = me.loadData() || "{"+element+":{\"fhr_data\":["+config.fhr+"], \"bl_data\":["+config.bl+"], \"ad_data\":["+ JSON.stringify(config.ad) +"], \"ua_data\":["+config.ua+"], \"fmp_data\":[["+config.fmp+"]]}}";
		ChartData.push(eval("("+data+")"));
		
		me.curNstData = config.fhr+"-"+config.bl+"-"+config.ad+"-"+config.ua+"-"+config.fmp;

		/** 判断是否支持canvas,不支持则调用Flex **/
		var canvas = document.createElement("canvas");
        if(!canvas.getContext || !canvas.getContext('2d')){
            console.log("浏览器不支持Canvas！");
            me.isCanvas = false;

            var swfDiv = $("#"+element)[0];
            swfDiv.style.width = "1280px";
            swfDiv.style.height = "370px";

            //var swfRoot = element.replace("#", "");
            swfobject.embedSWF("nst.swf", element, "1280", "370", "9");
            return;
        }

        /** 初始化一些变量 **/
		me.hasLeftClick = false;
		var hasRightClick = false;
        var rander_start = 0;
        var rander_width = 0;
        var timer = null;
        var is_back = false;
        /** 添加canvas控件 **/
		$("#"+element).find('.chart-canvas').html("<canvas class='oyLabel'></canvas><canvas class='uterusYLabel'></canvas><div class='content-chart'><canvas class='contentChart'></canvas><canvas class='oxLabel'></canvas><canvas class='uterusChart'></canvas></div>");
        /** 初始化canvas控件，并进行第一次绘制 **/
		me.chartInit(element);
        var canvasObj = $("#"+element+" .content-chart canvas");
        /** 记录canvas区域位置，用于后续的选取区域范围确定 **/
       	var canvas_to_window = {};
       	var sTime = setInterval(function(){
	       	if(document.readyState == "complete") {
	       		clearInterval(sTime);
		       	canvas_to_window = canvasObj[0].getBoundingClientRect();
		        me.default_start = Math.floor(canvas_to_window.left);
		        me.start_point = Math.floor(canvas_to_window.left);
		        me.default_end = Math.floor(canvas_to_window.right);
		        me.end_point = Math.floor(canvas_to_window.right);
	       	}
       	}, 200);
        
      	
        var canvasEvent = {
        	context: {},
        	click: function(event){
        		var button = event.button;
            	if(button == 0){   // 鼠标左键点击
     				canvasEvent.leftClick(event);
            	}else { // 鼠标右键点击
            		canvasEvent.rightClick(event);
            	}
        	},
        	leftClick: function(event){
        		var current_point = (event.x || event.clientX);
            	is_back = false;
            	me.offset_can = 0;
            	var start = '';
            	var scrollLeft = parseFloat($(event.currentTarget).parent('.content-chart').attr('data-scroll')) || 0;
            	me.power = (me.canvas_width / $(event.currentTarget).width());
            	var current_spot = (current_point - canvas_to_window.left + scrollLeft) * me.power;
            	for(var i = 0;i < canvasObj.length;i++){
        			this.context[i] = canvasObj[i].getContext('2d');
        			me.draw(element,i);
        			if((hasRightClick && current_point > me.start_point - scrollLeft && current_point < me.end_point) || current_spot > me.endX_point){
	            		me.randerReportArea(this.context[i], 0 , 0);
	            		if(i == 0){
		            		me.hasLeftClick = false;
		            		hasRightClick = false;
		            		me.start_point = me.default_start;
		            		me.end_point = me.default_end;
	            		}
	            	}else{
	            		if(i == 0){
		            		me.hasLeftClick = true;
		            		hasRightClick = false;
		            		me.start_point = current_point + scrollLeft;
	            		}
						start = (me.start_point - canvas_to_window.left) * me.power;
	            		start = (start < 0 ? 0: start);
	            		rander_start = start;
	            		me.randerReportArea(this.context[i], start , 4);
	            	}
        		}
            	$('button[consumerid="'+ me.id +'"]').attr('data-start', start/(me.horizontal_size/40));
            	$('button[consumerid="'+ me.id +'"]').attr('data-end', '');
        	},
        	rightClick: function(event){
        		if(!me.hasLeftClick) {
        			return;
        		}
        		var point_x = event.x || event.clientX;
    			var scrollLeft = parseFloat($(event.currentTarget).parent('.content-chart').attr('data-scroll')) || 0;
    			me.power = (me.canvas_width / $(event.currentTarget).width());
				if((point_x > me.start_point - scrollLeft) && (point_x < me.default_end || me.end_point == 0)){
					hasRightClick = true;
					for(var i = 0;i < canvasObj.length;i++){
	            		if(timer != null){
	            			clearInterval(timer);
	            			timer = null;
	            			is_back = true;
	            		}
	            		this.context[i] = canvasObj[i].getContext('2d');
	                	this.context[i].clearRect(0,0,me.canvas_width,me.canvas_height);
	                   	rander_start = (rander_start >= 0 ? rander_start : 0);
	                   	rander_width = (point_x - me.start_point + scrollLeft) * me.power;
						if(rander_start + rander_width > me.endX_point){
							rander_width = me.endX_point - rander_start;
						}
						me.draw(element,i);
						me.randerReportArea(this.context[i], rander_start, rander_width);
						if(i == 0){
							me.end_point = point_x;
						}
	        		}
					$('button[consumerid="'+ me.id +'"]').attr('data-end', (rander_start+rander_width)/(me.horizontal_size / 40));
              }
        	}
        }
        $('.content-canvas .content-chart').scroll(function(){
        	$(this).attr('data-scroll', $(this).scrollLeft());
        })
        $(canvasObj).mousedown(function(event){
			me.speed = $(event.currentTarget).closest('.panel-body').find('.table .speed').val();
			me.isClick = $('#chart'+me.id).find('.chart-canvas').attr('data-isclick');
			if(me.isClick == "false") return false;
        	canvasEvent.click(event);
        })
        $('.content-canvas canvas').contextmenu(function (){
            return false;
        })
	},
	/** 此方法提供数据给flex画图，仅在不支持canvas时调用 **/
	initNSTData : function(){
		return this.curNstData;
	},
	/** 初始化canvas控件以及第一次渲染数据 **/
	chartInit : function(element){
		var me = this;
		me.id = element.split('chart')[1];
		// 动态设置走纸速度
		var select = $("#"+element).prev('.table').find('.speed');
		var index = 3;
		if(me.speed == 1){
			index = 0;
		}else if(me.speed == 2){
			index = 1;
		}else if(me.speed == 3){
			index = 2;
		}else if(me.speed == 20){
			index = 3;
		}
		if(select.length > 0){
			select.prop('selectedIndex' , index);
		}
		$('button[consumerid="'+ me.id +'"]').attr('data-speed', me.speed);
        /** 初始化画图 **/
        var can = $("#"+element+" .contentChart")[0];
        can.width = me.getCanvasWidth();
        can.height = me.getCanvasHeight();
        context = can.getContext('2d');
        me.drawGrid(context, element);
	
        /** 初始化y轴坐标 **/
        var yLabel = $("#"+element+" .oyLabel")[0];
        yLabel.width = me.getLabelWeight();
        yLabel.height = me.getCanvasHeight();
        var ylContext = yLabel.getContext('2d');
        me.drawLabelY(ylContext); 

        /** 初始化X轴坐标 **/
        var xLabel = $("#"+element+" .oxLabel")[0];
        xLabel.width = me.getCanvasWidth();
        xLabel.height = me.getLabelHeight();
        var xlContext = xLabel.getContext('2d');
        me.drawLabelX(xlContext);
        $("#"+element+" .oxLabel").width($("#"+element+" .contentChart").width());
        /** 初始化宫缩图 **/
        var uterusChart = $("#"+element+" .uterusChart")[0];
        uterusChart.width = me.getCanvasWidth();
        uterusChart.height = me.getUterusHeight();
        var uterusContext = uterusChart.getContext('2d');
        me.drawUterus(uterusContext, element);
        $("#"+element+" .uterusChart").width($("#"+element+" .contentChart").width());
        /** 宫缩Y轴坐标 **/
        var uyLabel = $("#"+element+" .uterusYLabel")[0];
        uyLabel.width = me.getLabelWeight();
        uyLabel.height = me.getUterusHeight();
        var uyContext = uyLabel.getContext('2d');
        me.drawUterusLabelY(uyContext);
	},
	/** 清空重绘 **/
	draw : function(element, index){
		var me = this;
		if(index == 0){
	        var obj = $("#"+element+" .contentChart")[0];
	        var context = obj.getContext('2d');
	        context.clearRect(0,0,me.canvas_width,me.canvas_height);
	        me.moveEvent(context, element, me.offsetX);
	    }
		if(index == 1){
	        var xLabelObj = $("#"+element+" .oxLabel")[0];
	        var xLabelContext = xLabelObj.getContext('2d');
	        xLabelContext.clearRect(0,0,me.canvas_width,me.label_height);
	        me.changeXLabel(xLabelContext, me.offsetX);
       	}
		if(index == 2){
	        var uterusYLabel = $("#"+element+" .uterusChart")[0];
	        var uyContext = uterusYLabel.getContext('2d');
	        uyContext.clearRect(0,0,me.canvas_width,me.uterus_height);
	        me.drawUterus(uyContext, element);
		}
	},
	/** 获取canvas相对于浏览器窗口的位置信息 **/
	windowToCanvas : function(canvas, x, y){
		var bbox = canvas[0].getBoundingClientRect();
        return {
            x:x - bbox.left - (bbox.width - canvas.width) / 2,
            y:y - bbox.top - (bbox.height - canvas.height) / 2
        };
	},
	/** 胎心图区域绘图总方法 **/
	drawGrid : function(context, element){
		var me = this;
		/** X轴 **/
		me.drawGridLineX(context);
		/** Y轴 **/
		me.drawGridLineY(context);
		/** 正常范围背景渲染 **/
		me.randerNormalArea(context);
		/** 胎心曲线描点 **/
		me.randerValue(context, element, me.offsetX);
	},
	/** 画出X轴线 **/
	drawGridLineX : function(context){
		var me = this;
		/** X轴初始化 **/
		for(var i = 0;i <= me.required_vertical;i++){
			context.beginPath();
			var oy = me.vertical_size * i - 0.5;
			if(i == 0){
				oy = 0.5;
			}

			/** 刻度加粗 **/
			if(i % 3 == 0){
				context.lineWidth = 2;
			}else{
				context.lineWidth = 1;
			}
			context.moveTo(0.5, oy);
			context.lineTo(me.canvas_width+0.5, oy);
            context.strokeStyle = me.colorSelect['line'];
            context.stroke();
            context.closePath();
		}
	},
	/** 画出Y轴线 **/
	drawGridLineY : function(context){
		/** Y轴初始化 **/
		var me = this;
		if(me.offsetX >= 0){
			for(var j = 0;j <= me.required_horizontal;j++){
				context.beginPath();
				var ox = me.horizontal_size * j - 0.5;
				if(j == 0){
					ox = 0.5;
				}
				/** 刻度加粗 **/
				if(j % 3 == 0){
					context.lineWidth = 2;
				}else{
					context.lineWidth = 1;
				}
				//context.lineWidth = 1;
				context.moveTo(ox, 0.5);
				context.lineTo(ox, me.canvas_height + 0.5);
	            context.strokeStyle = me.colorSelect['line'];
	            context.stroke();
	            context.closePath();
			}
		}else{
			for(var j = 0;j <= required_horizontal;j++){
				var absOffsetX = Math.abs(offsetX);
				var beginPoint = me.horizontal_size - me.absOffsetX % me.horizontal_size;
				var blodLine = 6 - Math.ceil(absOffsetX / 40);

				context.beginPath();
				var ox = beginPoint + me.horizontal_size * j - 0.5;
				context.lineWidth = 1;
				context.moveTo(ox, 0.5);
				context.lineTo(ox, canvas_height + 0.5);
	            context.strokeStyle = colorSelect['line'];
	            context.stroke();
	            context.closePath();
			}
		}

		/** 固定首位两条线 **/
//		context.beginPath();
//		context.moveTo(0.5, 0.5);
//		context.lineTo(0.5, me.canvas_height + 0.5);
//      context.strokeStyle = me.colorSelect['line'];
//      context.stroke();
//      context.closePath();
//
//      context.beginPath();
//		context.moveTo(3239.5, 0.5);
//		context.lineTo(3239.5, me.canvas_height + 0.5);
//      context.strokeStyle = me.colorSelect['line'];
//      context.stroke();
//      context.closePath();
	},
	/** 此处渲染正常区域透明绿色 **/
	randerNormalArea : function(context){
		var me = this;
		context.beginPath();
		context.fillStyle = me.colorSelect['safe'];
		context.fillRect(0,me.vertical_size * 8,me.canvas_width,me.vertical_size * 5);
		context.closePath();
	},
	/** X轴横坐标设置值 **/
	drawLabelX : function(context){
		var me = this;
		var xValue = 0;
		for(var j = 0;j <= me.required_horizontal;j++){
			if(j % 3 == 0){
				context.font = "23px 黑体";
				context.fillStyle = "black";
				xValue = j / 6 * 2;
				if(j == 0){//根据分钟厘米每小格40
					//j等于0的时候   开始 (xValue=0,0,20)
					context.fillText(xValue, me.horizontal_size * j, 35);
				}else if(j == me.required_horizontal){
					//j等于60的时候      (xValue=60/6*2,40*60-30,20)
					context.fillText(xValue, me.horizontal_size * j - 30, 35);
				}else{
					//j>0 并且 j不等60的时候   (1-59/6*2,40*(1-59)-10,20) 
					context.fillText(xValue, me.horizontal_size * j - 10, 35);
				}
			}
		}
	},
	/** Y轴纵坐标设置值 **/
	drawLabelY : function(context){
		var me = this;
		var yValue = 240;
		for(var i = 0;i <= me.required_vertical;i++){
			if(i % 3 == 0){
				context.font = "23px 黑体";
				context.fillStyle = "black";
				if(i == 0){
					context.fillText(yValue, 10, me.vertical_size * i + 20);
				}else if(i == me.required_vertical){
					context.fillText(yValue, 10, me.vertical_size * i);
				}else{
					context.fillText(yValue, 10, me.vertical_size * i + 10);
				}
				yValue = yValue - 30;
			}
		}
	},
	/** 胎心曲线描点 **/
	randerValue : function(context, element, ox){
		var me = this;
		var fhrData = eval(me.getDataByElement(element).fhr_data[0]);
		var blData = eval(me.getDataByElement(element).bl_data[0]);
		var adData = eval(me.getDataByElement(element).ad_data[0]);
 		var fmpData = eval(me.getDataByElement(element).fmp_data[0]);
		if(fhrData && fhrData.length > 0){
			if (ox >= 0) {
				ox = 0;
				me.offsetX = 0;
			}else{
				ox = Math.abs(ox);
			}
			context.beginPath();
			var fhrLength = fhrData.length;
			for(var i = ox;i < fhrLength; i++){
				var x_point = (me.isInteger(i - ox) ? i - ox : i - ox + 0.5) * me.horizontal_size / 40;
				var y_height = me.canvas_height - (fhrData[i] - 30) * 2;
				var y_point = (me.isInteger(y_height) ? y_height : y_height + 0.5);
				if(Math.abs(fhrData[i] - fhrData[i - 1]) > 30){
					context.moveTo(x_point, y_point);
				}else{
					context.lineTo(x_point, y_point);
				}
				if(me.isLoad && i == fhrLength - 1){
					me.isLoad = false;
					me.endX_point = x_point;
					//重新设置canvas长度
					me.canvas_width = me.result_end = x_point + me.canvas_width;
					me.required_horizontal = Math.ceil(me.canvas_width /me.horizontal_size);
					$('button[consumerid="'+ me.id +'"]').attr('data-endPoint', me.endX_point);
					$('button[consumerid="'+ me.id +'"]').attr('data-startPoint', 0);
					me.draw(element,0);
					me.chartInit(element);
					return false;
				}
			}
			context.lineWidth = 1;
            context.strokeStyle = me.colorSelect['black'];
            context.stroke();
            context.closePath();
		}
		
		if(blData && blData.length > 0){
			if (ox >= 0) {
				ox = 0;
				me.offsetX = 0;
			}else{
				ox = Math.abs(ox);
			}
			context.beginPath();
			var blLength = blData.length;
			for(var i = ox;i < blLength; i++){
				var blX = (me.isInteger(i - ox) ? i - ox : i - ox + 0.5) * me.horizontal_size/40;
				var y_height = me.canvas_height - (blData[i] - 30) * 2;
				var blY = (me.isInteger(y_height) ? y_height : y_height + 0.5);
				context.lineTo(blX, blY);
			}
			context.lineWidth = 2;
            context.strokeStyle = me.colorSelect['green'];
            context.stroke();
            context.closePath();
		}
		//箭头
		/**
		* ctx：Canvas绘图环境
		* fromX, fromY：起点坐标（也可以换成p1，只不过它是一个数组）
		* toX, toY：终点坐标 (也可以换成p2，只不过它是一个数组)
		* theta：三角斜边一直线夹角
		* headlen：三角斜边长度
		* width：箭头线宽度
		* color：箭头颜色
		* */
		function drawArrow(ctx, fromX, fromY, toX, toY,theta,headlen,width,color) {
		    theta = typeof(theta) != 'undefined' ? theta : 30;
		    headlen = typeof(theta) != 'undefined' ? headlen : 10;
		    width = typeof(width) != 'undefined' ? width : 1;
		    color = typeof(color) != 'color' ? color : '#000';
		    // 计算各角度和对应的P2,P3坐标
		    var angle = Math.atan2(fromY - toY, fromX - toX) * 180 / Math.PI,
		        angle1 = (angle + theta) * Math.PI / 180,
		        angle2 = (angle - theta) * Math.PI / 180,
		        topX = headlen * Math.cos(angle1),
		        topY = headlen * Math.sin(angle1),
		        botX = headlen * Math.cos(angle2),
		        botY = headlen * Math.sin(angle2);
		    ctx.save();
		    ctx.beginPath();
		    var arrowX = fromX - topX,
		        arrowY = fromY - topY;
		    ctx.moveTo(arrowX, arrowY);
		    ctx.moveTo(fromX, fromY);
		    ctx.lineTo(toX, toY);
		    arrowX = toX + topX;
		    arrowY = toY + topY;
		    ctx.moveTo(arrowX, arrowY);
		    ctx.lineTo(toX, toY);
		    arrowX = toX + botX;
		    arrowY = toY + botY;
		    ctx.lineTo(arrowX, arrowY);
		    ctx.strokeStyle = color;
		    ctx.lineWidth = width;
		    ctx.stroke();
		    ctx.restore();
		}
		
		if(adData && adData.length > 0){
			context.beginPath();
			var adLength = adData.length;
			var size = 1.3;
			for(var i = ox;i < adLength; i++){
				var y_height = me.canvas_height - (adData[i].y - 30) * 2;
				var adY = (me.isInteger(y_height) ? y_height : y_height + 0.5);
				var adX = (me.isInteger(adData[i].x) ? adData[i].x : adData[i].x + 0.5) * 2 * me.horizontal_size / 40;
				if(adData[i].flag){
					drawArrow(context, adX, adY-30, adX, adY-6, 30* size, 8* size, 2.5* size,'#313030');  //down 加速
				}else{
					drawArrow(context, adX, adY+30, adX, adY+6, 30* size, 8* size, 2.5* size,'#313030');  //up 减速
				}
			}
		}
		
		ox = Math.abs(ox);
		if(fmpData && fmpData.length > 0){
			var fmpLength = fmpData.length;
			for(var i = 0;i < fmpLength; i++){
				context.beginPath();
				context.fillStyle = "rgb(0,0,0)";
			    context.fillRect((fmpData[i] * 2 - ox) * me.horizontal_size / 40, 390, 4 , 16);
			    context.fill();
			    context.stroke();
			}
		}
	},
	/** 拖动canvas时重绘胎心图方法 **/
	moveEvent : function(context, element, ox){
		var me = this;
		/** X轴 **/
		me.drawGridLineX(context);
		/** Y轴 **/
		me.drawGridLineY(context);
		/** 正常范围背景渲染 **/
		me.randerNormalArea(context);
		/** 胎心曲线描点 **/
		me.randerValue(context, element, ox);
	},
	/** 改变X轴 **/
	changeXLabel : function(context, ox){
		var me = this;
		if(ox >= 0){
			context.clearRect(0,0,me.getCanvasWidth(),me.getLabelHeight());
			me.drawLabelX(context);
		}else{
			for(var j = 0;j <= me.required_horizontal;j++){
				var absOffsetX = Math.abs(me.offsetX);
				var beginPoint = me.horizontal_size - absOffsetX % me.horizontal_size;
				var blodLine = 6 - Math.ceil(absOffsetX / 40);
				var index = (Math.floor(absOffsetX / 240) + 1) * 2;

				context.font = "23px 黑体";
				context.fillStyle = "black";

				/** 刻度加粗 **/
				if(j == blodLine){
					context.fillText(index, beginPoint + me.horizontal_size * j - 10, 20);
				}else{
					if((j - blodLine) % 3 == 0){
						context.fillText((j - blodLine) / 6 * 2 + 2, beginPoint + me.horizontal_size * j - 10, 20);
					}
				}
			}
		}
	},
	/** 画出宫缩图Y轴值 **/
	drawUterusLabelY : function(context){
		var me = this;
		var yValue = 100;
		for(var i = 0;i < me.uterus_vertical;i++){
			if(i % 2 == 0){
				context.font = "23px 黑体";
				context.fillStyle = "black";
				if(i == 0){
					context.fillText(yValue, 10, me.vertical_size * i + 16);
				}else if(i == me.uterus_vertical - 1){
					context.fillText(yValue, 10, me.vertical_size * i + 0);
				}else{
					context.fillText(yValue, 10, me.vertical_size * i + 9);
				}
				yValue = yValue - 20;
			}
		}
	},
	/** 宫缩图画图总方法 **/
	drawUterus : function(context, element){
		var me = this;
		me.drawUterusXLine(context);
		me.drawUterusYLine(context);
		me.randerUterusValue(context, element, me.offsetX);
	},
	/** 宫缩图X轴表格画图方法 **/
	drawUterusXLine : function(context){
		var me = this;
		for(var i = 0; i < me.uterus_vertical; i++){
			context.beginPath();
			var oy = me.vertical_size * i - 0.5;
			if(i == 0){
				oy = 0.5;
			}

			/** 刻度加粗 **/
			if(i % 2 == 0){
				context.lineWidth = 2;
			}else{
				context.lineWidth = 1;
			}
			context.moveTo(0.5, oy);
			context.lineTo(me.canvas_width + 0.5, oy);
            context.strokeStyle = me.colorSelect['line'];
            context.stroke();
            context.closePath();
		}
	},
	/** 宫缩图Y轴表格画图方法 **/
	drawUterusYLine : function(context){
		var me = this;
		/** Y轴初始化 **/
		if(me.offsetX >= 0){
			for(var j = 0;j <= me.required_horizontal;j++){
				context.beginPath();
				var ox = me.horizontal_size * j - 0.5;
				if(j == 0){
					ox = 0.5;
				}
				
				context.lineWidth = 1;
				context.moveTo(ox, 0.5);
				context.lineTo(ox, me.canvas_height + 0.5);
	            context.strokeStyle = me.colorSelect['line'];
	            context.stroke();
	            context.closePath();
			}
		}else{
			for(var j = 0;j <= me.required_horizontal;j++){
				var absOffsetX = Math.abs(me.offsetX);
				var beginPoint = me.horizontal_size - absOffsetX % me.horizontal_size;
				var blodLine = 6 - Math.ceil(absOffsetX / 40);

				context.beginPath();
				var ox = beginPoint + me.horizontal_size * j - 0.5;
				context.lineWidth = 1;
				context.moveTo(ox, 0.5);
				context.lineTo(ox, me.canvas_height + 0.5);
	            context.strokeStyle = me.colorSelect['line'];
	            context.stroke();
	            context.closePath();
			}
		}

		/** 固定首位两条线 **/
		context.beginPath();
		context.moveTo(0.5, 0.5);
		context.lineTo(0.5, me.canvas_height + 0.5);
        context.strokeStyle = me.colorSelect['line'];
        context.stroke();
        context.closePath();

        context.beginPath();
		context.moveTo(2399.5, 0.5);
		context.lineTo(2399.5, me.canvas_height + 0.5);
        context.strokeStyle = me.colorSelect['line'];
        context.stroke();
        context.closePath();
	},
	/** 宫缩曲线图描点 **/
	randerUterusValue : function(context, element, ox){
		var me = this;
		var uaData = eval(me.getDataByElement(element).ua_data[0]);
		if(uaData && uaData.length > 0){
			ox = Math.abs(ox);
			context.beginPath();
			var uaLength = uaData.length;
			for(var i = ox;i < uaLength; i++){
				var uaX = (me.isInteger(i - ox) ? i - ox + 0.5 : i - ox) * me.horizontal_size / 40;
				var uaY = me.isInteger(me.uterus_height - uaData[i] * 2) ? me.uterus_height - uaData[i] * 2 + 0.5 : me.uterus_height - uaData[i] * 2;
				context.lineTo(uaX,  uaY);
			}
			context.lineWidth = 1;
            context.strokeStyle = me.colorSelect['black'];
            context.stroke();
            context.closePath();
		}
	},
	/** 生成报告胎心选取区域范围渲染 **/
	randerReportArea : function(context, start, width){
		var me = this;
		if(me.hasLeftClick){
			context.fillStyle = me.colorSelect['grey'];
		}else{
			context.fillStyle = me.colorSelect['transparent'];
		}
		context.fillRect(start,0,width,me.canvas_height);
	},
	clearReportArea : function(context){
		context.fillRect(0,0,0,0);
	},
	/** 设置选取范围值，用于返回到前端，并提供给后台生成报告 **/
	setResultData : function(result){
		var me = this;
		var resultArray = result.split("-");
		me.result_start = resultArray[0];
		me.result_end = resultArray[1];
	},
	/** 对外获取胎心选取区域的方法 **/
	getResultData : function(element){
		var me = this;
		var fhrData = me.getDataByElement(element).fhr_data;
		if(me.isCanvas){
			var start_index = (me.start_point - me.default_start) * me.power;
            var end_index = (end_point - default_start) * me.power;
            me.result_start = start_index + Math.abs(me.offsetX) - Math.abs(me.offset_can);
            me.result_end = end_index + Math.abs(me.offsetX);
		}
		me.result_end = me.result_end == 1200 ? fhrData.length : me.result_end;
		return me.result_start+"-"+me.result_end;
	},
	initSelect : function(element,value){
		var me = this; 
        me.speed = value;
        var eId = element.split('chart')[1];
        $('button[consumerid="'+ eId +'"]').attr('data-speed', me.speed);
        var dataArr = me.getDataByElement(element);
        dataArr.element = element;
        me.init(dataArr);
        // 如果正在播放,暂停播放
        $("#panel"+ eId).find(".timeNow").text("--:--:--");
		$("#chart"+ eId).parent("div").find(".valueNow").text("--次/分");
		if($("#panel"+ eId).find(".play").attr('data-isMediaPlay') == "true"){
			$("#panel"+ eId).find(".play").click();
		}
	}
}
