/**
 * Canvas胎心图工具类
 * 实时胎心九宫格数据展示与处理
 * @auther rent
 * @date 2015-10-30
 **/
//var offsetX = 0;
var detailTimer;
var Leon = function(){
	/** Y轴每一格之间的距离 **/
	var vertical_size = 20;
	/** X轴每一格之间的距离 **/
	var horizontal_size = 40;
	/** 垂直多少个cell **/
	var required_vertical = 21;
	/** 水平多少个cell **/
	var required_horizontal = 25;
	/** 左侧Y轴坐标宽度 **/
	var label_weight = 50;
	/** 底部X轴坐标高度 **/
	var label_height = 50;
	/** canvas宽度 **/
	var canvas_width = 960;
	/** canvas高度 **/
	var canvas_height = 420;

	var testArray = [137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126];

	/** 颜色值数组定义 **/
	var colorSelect = {
		"line" : "rgb(241,137,168)",//红色线条颜色
		"safe" : "rgba(67,205,128,0.3)",//绿色正常背景填充颜色，0.3的透明度
		"black" : "rgb(0,0,0)"
	};

	return {
		/** DOM查询canvas对象 **/
		get : function(id){
			return document.getElementById(id);
		},
		getCanvasWidth : function(){
			return canvas_width;
		},
		getCanvasHeight : function(){
			return canvas_height;
		},
		getLabelWeight : function(){
			return label_weight;
		},
		getLabelHeight : function(){
			return label_height;
		},
		setOffsetX : function(element, value){
			$("."+element).children(".offsetX").val(value);
		},
		getOffsetX : function(element){
			return $("."+element).children(".offsetX").val();
		},
		init : function(element, userId){
            this.chartInit(element);
            var canvasObj = $("."+element+" .contentChart")[0];
            canvasObj.onmousedown = function(event){
                var pos = Leon.windowToCanvas(canvasObj,event.clientX,event.clientY);
                canvasObj.onmousemove = function(event){
                    canvasObj.style.cursor = "move";
                    var pos1 = Leon.windowToCanvas(canvasObj,event.clientX,event.clientY);
                    var x = pos1.x - pos.x;
                    var y = pos1.y - pos.y;
                    pos = pos1;
                    Leon.setOffsetX(element, parseInt(Leon.getOffsetX(element)) + x);
                    //offsetX+=x;
                    Leon.draw(element);
                }
                canvasObj.onmouseup = function(){
                    canvasObj.onmousemove = null;
                    canvasObj.onmouseup = null;
                    canvasObj.style.cursor = "default";
                }
            };
            canvasObj.ondblclick = function(event){
            	if(userId != 0){
            		var userInfo = $("#"+element).find("ul").html();
            		var hospital_id = $("#hospitalId").val();
            		var user_name = $("#"+element).find("ul").children().eq(1).text();
            		var orderId = $("#"+element).attr("orderId");
            		var monitorTimes = $("#"+element).attr("monitorTimes");
	        		/** 用户类型/是否有效/消费订单ID/订单ID/第几次监测/医院ID/用户ID/用户名 **/
					var src = chatURL+"/chat/1/1/0/"+orderId +"/"+monitorTimes+"/"+hospital_id+"/"+userId+"/"+user_name;
		        	$("#chatIframe").attr("src",src);
            		
                	$(".panel-head").children("ul").html(userInfo);
                	$(".panel-body").find(".detail-panel").attr("id", userId);
                	Detail.init(userId);
                	$(".dialog").show();
                	var oldData = $("."+element).children(".data").val();
                	detailTimer = self.setInterval(function(){
			    		var curData = $("."+element).children(".data").val();
			    		var curDataArray = curData.split(",");
			    		if(oldData != curData){
			    			var uInfo = $("#"+element).find("ul").html();
		                	$(".panel-head").children("ul").html(uInfo);
			    			var offsetX = $("."+element).children(".offsetX").val();
			    			if(curDataArray.length >= 1200){
			    				$("#"+userId).children(".offsetX").val(offsetX);
			    			}else{
			    				$("#"+userId).children(".offsetX").val(0);
			    			}
			    			Detail.draw(userId);
			    		}
					},3000);
            	}
            }
		},
		chartInit : function(element){
            /** 初始化画图 **/
            var can = $("."+element+" .contentChart")[0];
            can.width = this.getCanvasWidth();
            can.height = this.getCanvasHeight();
            context = can.getContext('2d');
            this.drawGrid(context, element);

            /** 初始化y周坐标 **/
            var yLabel = $("."+element+" .oyLabel")[0];
            yLabel.width = this.getLabelWeight();
            yLabel.height = this.getCanvasHeight();
            var ylContext = yLabel.getContext('2d');
            this.drawLabelY(ylContext);

            /** 初始化X轴坐标 **/
            var xLabel = $("."+element+" .oxLabel")[0];
            xLabel.width = this.getCanvasWidth();
            xLabel.height = this.getLabelHeight();
            var xlContext = xLabel.getContext('2d');
            this.drawLabelX(xlContext);
		},
		draw : function(element){
            var obj = $("."+element+" .contentChart")[0];
            var context = obj.getContext('2d');
            context.clearRect(0,0,canvas_width,canvas_height);
            this.moveEvent(context, Leon.getOffsetX(element), element);
            
            var xLabelObj = $("."+element+" .oxLabel")[0];
            var xLabelContext = xLabelObj.getContext('2d');
            xLabelContext.clearRect(0,0,canvas_width,label_height);
            this.changeXLabel(xLabelContext, Leon.getOffsetX(element));
		},
		windowToCanvas : function(canvas, x, y){
			var bbox = canvas.getBoundingClientRect();
            return {
                x:x - bbox.left - (bbox.width - canvas.width) / 2,
                y:y - bbox.top - (bbox.height - canvas.height) / 2
            };
		},
		/** 画图总方法 **/
		drawGrid : function(context, element){
			/** X轴 **/
			this.drawGridLineX(context);
			/** Y轴 **/
			this.drawGridLineY(context, element);
			/** 正常范围背景渲染 **/
			this.randerNormalArea(context);
			/** 胎心曲线描点 **/
			this.randerValue(context, Leon.getOffsetX(element), element);
		},
		/** 画出X轴线 **/
		drawGridLineX : function(context){
			/** X轴初始化 **/
			for(var i = 0;i <= required_vertical;i++){
				context.beginPath();
				var oy = vertical_size * i;
				if(i == 0){
					oy = 0;
				}

				/** 刻度加粗 **/
				if(i % 3 == 0){
					context.lineWidth = 2;
				}else{
					context.lineWidth = 1;
				}
				context.moveTo(0, oy);
				context.lineTo(canvas_width, oy);
	            context.strokeStyle = colorSelect['line'];
	            context.stroke();
	            context.closePath();
			}
		},
		/** 画出Y轴线 **/
		drawGridLineY : function(context, element){
			/** Y轴初始化 **/
			if(Leon.getOffsetX(element) >= 0){
				for(var j = 0;j <= required_horizontal;j++){
					context.beginPath();
					var ox = horizontal_size * j;
					if(j == 0){
						ox = 0;
					}
					/** 刻度加粗 **/
					/*if(j % 6 == 0){
						context.lineWidth = 2;
					}else{
						context.lineWidth = 1;
					}*/
					context.lineWidth = 1;
					context.moveTo(ox, 0);
					context.lineTo(ox, canvas_height);
		            context.strokeStyle = colorSelect['line'];
		            context.stroke();
		            context.closePath();
				}
			}else{
				for(var j = 0;j <= required_horizontal;j++){
					var absOffsetX = Math.abs(Leon.getOffsetX(element));
					var beginPoint = horizontal_size - absOffsetX % horizontal_size;
					var blodLine = 6 - Math.ceil(absOffsetX / 40);

					context.beginPath();
					var ox = beginPoint + horizontal_size * j;
					/** 刻度加粗 **/
					/*if(j == blodLine){
						context.lineWidth = 2;
					}else{
						if((j - blodLine) % 6 == 0){
							context.lineWidth = 2;
						}else{
							context.lineWidth = 1;
						}
					}*/
					context.lineWidth = 1;
					context.moveTo(ox, 0);
					context.lineTo(ox, canvas_height);
		            context.strokeStyle = colorSelect['line'];
		            context.stroke();
		            context.closePath();
				}
			}

			/** 固定首位两条线 **/
			context.beginPath();
			context.moveTo(0, 0);
			context.lineTo(0, canvas_height);
            context.strokeStyle = colorSelect['line'];
            context.stroke();
            context.closePath();

            context.beginPath();
			context.moveTo(960, 0);
			context.lineTo(960, canvas_height);
            context.strokeStyle = colorSelect['line'];
            context.stroke();
            context.closePath();
		},
		randerNormalArea : function(context){
			context.beginPath();
			context.fillStyle =colorSelect['safe'];
			context.fillRect(0,vertical_size * 8,canvas_width,vertical_size * 5);
			context.closePath();
		},
		/** X轴横坐标渲染 **/
		drawLabelX : function(context){
			var xValue = 0;
			for(var j = 0;j <= required_horizontal;j++){
				if(j % 3 == 0){
					context.font = "23px 黑体";
					context.fillStyle = "black";
					xValue = j / 6 * 2;
					if(j == 0){
						context.fillText(xValue, horizontal_size * j, 20);
					}else if(j == required_horizontal){
						context.fillText(xValue, horizontal_size * j - 30, 20);
					}else{
						context.fillText(xValue, horizontal_size * j - 15, 20);
					}
				}
			}
		},
		/** Y轴纵坐标渲染 **/
		drawLabelY : function(context){
			var yValue = 240;
			for(var i = 0;i <= required_vertical;i++){
				if(i % 3 == 0){
					context.font = "23px 黑体";
					context.fillStyle = "black";
					if(i == 0){
						context.fillText(yValue, 10, vertical_size * i + 20);
					}else if(i == required_vertical){
						context.fillText(yValue, 10, vertical_size * i);
					}else{
						context.fillText(yValue, 10, vertical_size * i + 10);
					}
					yValue = yValue - 30;
				}
			}
		},
		randerValue : function(context, ox, element){
			var data = $($("."+element).children(".data")).val();
			var fetalMoveData = $($("."+element).children(".fetalMove")).val();
			var dataArray = new Array();
			var fetalMoveArray = new Array();
			if(data != ''){
				dataArray = data.split(",");
			}
			if(fetalMoveData != ''){
				fetalMoveArray = fetalMoveData.split(",");
			}
			if (ox >= 0) {
				ox = 0;
				Leon.setOffsetX(element, 0);
				if(dataArray != '' && dataArray != null && dataArray.length > 0){
					context.beginPath();
					for(var i = 0;i < dataArray.length;i++){
						if(Math.abs(dataArray[i] - dataArray[i - 1]) > 30){
							context.moveTo(i, canvas_height - (dataArray[i]-30)*2);
						}else{
							context.lineTo(i, canvas_height - (dataArray[i]-30)*2);
						}
					}
					context.lineWidth = 1;
		            context.strokeStyle = colorSelect['black'];
		            context.stroke();
		            context.closePath();
				}
			}else{
				ox = Math.abs(ox);
				if(dataArray != '' && dataArray != null && dataArray.length > 0){
					context.beginPath();
					for(var i = ox;i < dataArray.length;i++){
						if(Math.abs(dataArray[i] - dataArray[i - 1]) > 30){
							context.moveTo(i - ox, canvas_height - (dataArray[i]-30)*2);
						}else{
							context.lineTo(i - ox, canvas_height - (dataArray[i]-30)*2);
						}
					}
					context.lineWidth = 1;
		            context.strokeStyle = colorSelect['black'];
		            context.stroke();
		            context.closePath();
				}
			}
			
			if(fetalMoveArray != '' && fetalMoveArray != null && fetalMoveArray.length > 0){
				for(var i = 0;i < fetalMoveArray.length; i++){
					context.beginPath();
					context.fillStyle = "rgb(0,0,0)";
				    context.fillRect(fetalMoveArray[i] * 2 - ox, 390, 4, 16);
				    context.fill();
				    context.stroke();
				}
			}
			this.randerTimeAndCurrentValue(element, ox, dataArray, fetalMoveArray);
		},
		randerTimeAndCurrentValue : function(element, ox, dataArray, fetalMoveArray){
			var timeNow = Math.floor(dataArray.length / 2);
			var hour = Math.floor(timeNow / 3600);
		    var minute = Math.floor((timeNow % 3600) / 60);
		    var second = timeNow % 3600 % 60;
		    hour = hour < 10 ? "0" + hour : hour;
		    minute = minute < 10 ? "0" + minute : minute;
		    second = second < 10 ? "0" + second : second;
		    
		    /** 渲染当前时间 **/
		    $("#"+element).find("li.timeNow").text(hour+":"+minute+":"+second);
		    if(dataArray != '' && dataArray.length > 0){
		    	$("#"+element).find("li.valueNow").find("span.value-blod").text(+dataArray[dataArray.length - 1]);
		    }
		    if(fetalMoveArray != '' && fetalMoveArray.length > 0){
		    	$("#"+element).find("li.fetalMoveNow").text(fetalMoveArray.length+"次");
		    }
		},
		moveEvent : function(context, ox, element){
			/** X轴 **/
			this.drawGridLineX(context);
			/** Y轴 **/
			this.drawGridLineY(context, element);
			/** 正常范围背景渲染 **/
			this.randerNormalArea(context);
			/** 胎心曲线描点 **/
			this.randerValue(context, ox, element);
		},
		changeXLabel : function(context, ox){
			if(ox >= 0){
				context.clearRect(0,0,Leon.getCanvasWidth(),Leon.getLabelHeight());
				this.drawLabelX(context);
			}else{
				for(var j = 0;j <= required_horizontal;j++){
					var absOffsetX = Math.abs(ox);
					var beginPoint = horizontal_size - absOffsetX % horizontal_size;
					var blodLine = 6 - Math.ceil(absOffsetX / 40);
					var index = (Math.floor(absOffsetX / 240) + 1) * 2;

					context.font = "23px 黑体";
					context.fillStyle = "black";

					/** 刻度加粗 **/
					if(j == blodLine){
						context.fillText(index, beginPoint + horizontal_size * j - 10, 20);
					}else{
						if((j - blodLine) % 3 == 0){
							context.fillText((j - blodLine) / 6 * 2 + 2, beginPoint + horizontal_size * j - 15, 20);
						}
					}
				}
			}
		},
		clear : function(element){
			var obj = $("#"+element+" .contentChart")[0];
            var context = obj.getContext('2d');
            context.clearRect(0,0,canvas_width,canvas_height);
            Leon.moveEvent(context, 0, element);
            
            var xLabelObj = $("#"+element+" .oxLabel")[0];
            var xLabelContext = xLabelObj.getContext('2d');
            xLabelContext.clearRect(0,0,canvas_width,label_height);
            Leon.changeXLabel(xLabelContext, 0);
		}
	}
	
}();