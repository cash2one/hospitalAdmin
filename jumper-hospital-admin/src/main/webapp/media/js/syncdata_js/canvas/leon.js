/**
 * Canvas胎心图工具类
 * @auther rent
 * @date 2015-10-30
 **/
var offsetX = 0;
var offsetY = 0;

var Leon = function(){
	
	/** Y轴每一格之间的距离 **/
	var vertical_size = 20;
	/** X轴每一格之间的距离 **/
	var horizontal_size = 40;
	/** 垂直多少个cell **/
	var required_vertical = 21;
	/** 水平多少个cell **/
	var required_horizontal = 60;
	/** 左侧Y轴坐标宽度 **/
	var label_weight = 50;
	/** 底部X轴坐标高度 **/
	var label_height = 50;
	/** canvas宽度 **/
	var canvas_width = 2400;
	/** canvas高度 **/
	var canvas_height = 420;

	//var testArray = [137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120];
      var testArray = [146,146,147,145,143,145,145,146,147,148,149,150,152,152,153,154,155,156,157,159,159,160,161,163,163,164,165,167,168,168,169,171,171,172,173,175,175,176,177,179,179,180,179,177,178,178,178,143,147,147,147,145,145,147,240,240,240,240,240,240,240,234,232,236,236,240,240,240,240,240,239,240,240,240,238,238,239,240,239,238,238,238,239,239,238,238,239,238,238,238,239,238,239,238,239,239,238,238,236,229,229,221,222,222,223,225,230,230,235,240,234,234,236,228,229,230,231,232,233,233,235,240,240,240,240,240,234,234,237,238,239,239,237,238,238,240,240,235,236,236,238,238,239,238,238,239,239,239,238,239,239,239,238,239,238,239,238,143,142,144,149,149,149,156,158,165,239,238,239,238,239,238,239,238,238,148,146,144,151,153,151,153,149,149,149,149,240,234,229,230,224,226,232,236,240,240,238,229,222,215,210,210,213,214,222,228,234,240,240,228,228,228,228,228,228,229,230,231,235,240,240,239,238,239,238,239,238,239,238,239,238,239,240,240,240,234,235,236,240,234,230,231,232,233,234,235,236,237,240,240,240]
	var fetalMoveArray = [20, 50, 80,120,200];  //胎动值
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
		init : function(element, musicFile){
			//playMusic(musicFile);
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
                    offsetX+=x;
                    offsetY+=y;
                    Leon.draw(element);
                }
                canvasObj.onmouseup = function(){
                    canvasObj.onmousemove = null;
                    canvasObj.onmouseup = null;
                    canvasObj.style.cursor = "default";
                }
            }
		},
		chartInit : function(element){
            /** 初始化画图 **/
            var can = $("."+element+" .contentChart")[0];
            can.width = this.getCanvasWidth();
            can.height = this.getCanvasHeight();
            context = can.getContext('2d');
            this.drawGrid(context);

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
            this.moveEvent(context, offsetX);
            
            var xLabelObj = $("."+element+" .oxLabel")[0];
            var xLabelContext = xLabelObj.getContext('2d');
            xLabelContext.clearRect(0,0,canvas_width,label_height);
            this.changeXLabel(xLabelContext, offsetX);

            /** 改变音乐时长 **/
            /*if(offsetX < 0){
                media.currentTime = Math.abs(offsetX);
            }*/
		},
		windowToCanvas : function(canvas, x, y){
			var bbox = canvas.getBoundingClientRect();
            return {
                x:x - bbox.left - (bbox.width - canvas.width) / 2,
                y:y - bbox.top - (bbox.height - canvas.height) / 2
            };
		},
		/** 画图总方法 **/
		drawGrid : function(context){
			/** X轴 **/
			this.drawGridLineX(context);
			/** Y轴 **/
			this.drawGridLineY(context);
			/** 正常范围背景渲染 **/
			this.randerNormalArea(context);
			/** 胎心曲线描点 **/
			this.randerValue(context, offsetX);
		},
		/** 画出X轴线 **/
		drawGridLineX : function(context){
			/** X轴初始化 **/
			for(var i = 0;i <= required_vertical;i++){
				context.beginPath();
				var oy = vertical_size * i - 0.5;
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
				context.lineTo(canvas_width+0.5, oy);
	            context.strokeStyle = colorSelect['line'];
	            context.stroke();
	            context.closePath();
			}
		},
		/** 画出Y轴线 **/
		drawGridLineY : function(context){
			/** Y轴初始化 **/
			if(offsetX >= 0){
				for(var j = 0;j <= required_horizontal;j++){
					context.beginPath();
					var ox = horizontal_size * j - 0.5;
					if(j == 0){
						ox = 0.5;
					}
					/** 刻度加粗 **/
					if(j % 6 == 0){
						context.lineWidth = 2;
					}else{
						context.lineWidth = 1;
					}
					context.moveTo(ox, 0.5);
					context.lineTo(ox, canvas_height + 0.5);
		            context.strokeStyle = colorSelect['line'];
		            context.stroke();
		            context.closePath();
				}
			}else{
				for(var j = 0;j <= required_horizontal;j++){
					var absOffsetX = Math.abs(offsetX);
					var beginPoint = horizontal_size - absOffsetX % horizontal_size;
					var blodLine = 6 - Math.ceil(absOffsetX / 40);

					context.beginPath();
					var ox = beginPoint + horizontal_size * j - 0.5;
					/** 刻度加粗 **/
					if(j == blodLine){
						context.lineWidth = 2;
					}else{
						if((j - blodLine) % 6 == 0){
							context.lineWidth = 2;
						}else{
							context.lineWidth = 1;
						}
					}
					context.moveTo(ox, 0.5);
					context.lineTo(ox, canvas_height + 0.5);
		            context.strokeStyle = colorSelect['line'];
		            context.stroke();
		            context.closePath();
				}
			}

			/** 固定首位两条线 **/
			context.beginPath();
			context.moveTo(0.5, 0.5);
			context.lineTo(0.5, canvas_height + 0.5);
            context.strokeStyle = colorSelect['line'];
            context.stroke();
            context.closePath();

            context.beginPath();
			context.moveTo(2399.5, 0.5);
			context.lineTo(2399.5, canvas_height + 0.5);
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
				if(j % 6 == 0){
					context.font = "23px 黑体";
					context.fillStyle = "black";
					xValue = j / 6 * 2;
					if(j == 0){
						context.fillText(xValue, horizontal_size * j, 20);
					}else if(j == required_horizontal){
						context.fillText(xValue, horizontal_size * j - 30, 20);
					}else{
						context.fillText(xValue, horizontal_size * j - 10, 20);
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
		randerValue : function(context, ox){
			if (ox >= 0) {
				ox = 0;
				offsetX = 0;
				context.beginPath();
				for(var i = 0;i < testArray.length;i++){
					if(Math.abs(testArray[i] - testArray[i - 1]) > 30){
						context.moveTo(i + 0.5, canvas_height - (testArray[i]-30)*2 + 0.5);
					}else{
						context.lineTo(i + 0.5, canvas_height - (testArray[i]-30)*2 + 0.5);
					}
				}
				context.lineWidth = 1;
	            context.strokeStyle = colorSelect['black'];
	            context.stroke();
	            context.closePath();
			}else{
				ox = Math.abs(ox);
				context.beginPath();
				for(var i = ox;i < testArray.length;i++){
					if(Math.abs(testArray[i] - testArray[i - 1]) > 30){
						context.moveTo(i - ox + 0.5, canvas_height - (testArray[i]-30)*2 + 0.5);
					}else{
						context.lineTo(i - ox + 0.5, canvas_height - (testArray[i]-30)*2 + 0.5);
					}
				}
				context.lineWidth = 1;
	            context.strokeStyle = colorSelect['black'];
	            context.stroke();
	            context.closePath();
			}

			ox = Math.abs(ox);
			if(fetalMoveArray != '' && fetalMoveArray != null && fetalMoveArray.length > 0){
				for(var i = 0;i < fetalMoveArray.length; i++){
					context.beginPath();
					context.fillStyle = "rgb(0,0,0)";
				    context.fillRect(fetalMoveArray[i] * 2 - ox, 390, 4, 16);
				    context.fill();
				    context.stroke();
				}
			}
		},
		moveEvent : function(context, ox){
			/** X轴 **/
			this.drawGridLineX(context);
			/** Y轴 **/
			this.drawGridLineY(context);
			/** 正常范围背景渲染 **/
			this.randerNormalArea(context);
			/** 胎心曲线描点 **/
			this.randerValue(context, ox);
		},
		changeXLabel : function(context, ox){
			if(ox >= 0){
				context.clearRect(0,0,Leon.getCanvasWidth(),Leon.getLabelHeight());
				this.drawLabelX(context);
			}else{
				for(var j = 0;j <= required_horizontal;j++){
					var absOffsetX = Math.abs(offsetX);
					var beginPoint = horizontal_size - absOffsetX % horizontal_size;
					var blodLine = 6 - Math.ceil(absOffsetX / 40);
					var index = (Math.floor(absOffsetX / 240) + 1) * 2;

					context.font = "23px 黑体";
					context.fillStyle = "black";

					/*if((j - blodLine) % 6 == 0){
						context.fillText((j - blodLine) / 6 * 2, beginPoint + horizontal_size * j - 10, 20);
					}*/

					/** 刻度加粗 **/
					if(j == blodLine){
						context.fillText(index, beginPoint + horizontal_size * j - 10, 20);
					}else{
						if((j - blodLine) % 6 == 0){
							context.fillText((j - blodLine) / 6 * 2 + 2, beginPoint + horizontal_size * j - 10, 20);
						}
					}
				}
			}
		}
	}
	
}();