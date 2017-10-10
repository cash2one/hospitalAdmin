/**
 * Canvas胎心图工具类
 * @auther rent
 * @date 2015-10-30
 **/
var offsetX = 0;
var offsetY = 0;
var default_start = 467;
var default_end = 1687;
var start_point = 487;
var end_point = 1687;
var offset_can = 0;
var Chart = function(){
	
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

	var testArray = [137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135];

	/** 颜色值数组定义 **/
	var colorSelect = {
		"line" : "rgb(241,137,168)",//红色线条颜色
		"safe" : "rgba(67,205,128,0.3)",//绿色正常背景填充颜色，0.3的透明度
		"black" : "rgb(0,0,0)",
		"grey" : "rgba(153,153,153,0.5)"
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
		init : function(element){
			var is_start = false;
	        var rander_start = 0;
	        var rander_width = 0;
	        var timer = null;
	        var is_back = false;
			
			Chart.chartInit(element);
            var canvasObj = $("#"+element+" .contentChart")[0];
            
            var canvas_to_window = canvasObj.getBoundingClientRect();
            default_start = Math.floor(canvas_to_window.left);
            start_point = Math.floor(canvas_to_window.left);
            default_end = Math.floor(canvas_to_window.right);
            end_point = Math.floor(canvas_to_window.right);
            
			$("#"+element).mousemove(function(event){
				if(event.pageY >= canvas_to_window.top && event.pageY <= canvas_to_window.bottom && event.pageX > default_end && is_start){
					if(timer == null){
            			timer = setInterval(function(){
	            			canvasObj.width = Chart.getCanvasWidth();
	            			canvasObj.height = Chart.getCanvasHeight();
	            			context = canvasObj.getContext('2d');
            				context.clearRect(0,0,canvas_width,canvas_height);
	            			offsetX--;
	            			offset_can++;
		                   	rander_start = (start_point - canvas_to_window.left) * 2 - offset_can;
		                   	rander_width = (default_end - start_point) * 2 + Math.abs(offset_can);

		                   	Chart.moveEvent(context, element, offsetX);
		            		Chart.randerReportArea(context, rander_start, rander_width);
		            		
		            		var xLabelObj = $("#"+element+" .oxLabel")[0];
				            var xLabelContext = xLabelObj.getContext('2d');
				            xLabelContext.clearRect(0,0,canvas_width,label_height);
				            Chart.changeXLabel(xLabelContext, offsetX);
                		}, 5);
            		}
				}
			});
            
            canvasObj.onmousedown = function(event){
                var pos = Chart.windowToCanvas(canvasObj,event.clientX,event.clientY);
                canvasObj.onmousemove = function(event){
                    canvasObj.style.cursor = "move";
                    var pos1 = Chart.windowToCanvas(canvasObj,event.clientX,event.clientY);
                    var x = pos1.x - pos.x;
                    var y = pos1.y - pos.y;
                    pos = pos1;
                    offsetX+=x;
                    offsetY+=y;
                    Chart.draw(element, "drag");
                }
                canvasObj.onmouseup = function(){
                    canvasObj.onmousemove = null;
                    canvasObj.onmouseup = null;
                    canvasObj.style.cursor = "default";
                }
                canvasObj.ondblclick = function(event){
                	is_start = true;
                	is_back = false;
                	offset_can = 0;
	            	start_point = event.x || event.clientX;
	            	canvasObj.width = Chart.getCanvasWidth();
	            	canvasObj.height = Chart.getCanvasHeight();
	            	context = canvasObj.getContext('2d');

	            	context.clearRect(0,0,canvas_width,canvas_height);
	            	Chart.moveEvent(context, element, offsetX);
	            	Chart.randerReportArea(context, (start_point - canvas_to_window.left)*2, 5);

	            	canvasObj.onmousemove = function(event){
	            		var point_x = event.x || event.clientX;
	                    if(point_x > start_point && point_x < default_end){
	                    	context.clearRect(0,0,canvas_width,canvas_height);
                    		if(timer != null){
                    			clearInterval(timer);
                    			timer = null;
                    			is_back = true;
                    		}

		                   	rander_start = is_back ? rander_start : (start_point - canvas_to_window.left) * 2;
		                   	rander_width = is_back ? (point_x - start_point) * 2 + Math.abs(offset_can) : (point_x - start_point) * 2;

                    		Chart.moveEvent(context, element, offsetX);
		            		Chart.randerReportArea(context, rander_start, rander_width);
	            			end_point = point_x;
	                    }
	                }
	            }
            }
		},
		chartInit : function(element){
            /** 初始化画图 **/
            var can = $("#"+element+" .contentChart")[0];
            can.width = Chart.getCanvasWidth();
            can.height = Chart.getCanvasHeight();
            context = can.getContext('2d');
            Chart.drawGrid(context, element);

            /** 初始化y周坐标 **/
            var yLabel = $("#"+element+" .oyLabel")[0];
            yLabel.width = Chart.getLabelWeight();
            yLabel.height = Chart.getCanvasHeight();
            var ylContext = yLabel.getContext('2d');
            Chart.drawLabelY(ylContext);

            /** 初始化X轴坐标 **/
            var xLabel = $("#"+element+" .oxLabel")[0];
            xLabel.width = Chart.getCanvasWidth();
            xLabel.height = Chart.getLabelHeight();
            var xlContext = xLabel.getContext('2d');
            Chart.drawLabelX(xlContext);
		},
		draw : function(element, type){
            var obj = $("#"+element+" .contentChart")[0];
            var context = obj.getContext('2d');
            context.clearRect(0,0,canvas_width,canvas_height);
            Chart.moveEvent(context, element, offsetX);
            
            var xLabelObj = $("#"+element+" .oxLabel")[0];
            var xLabelContext = xLabelObj.getContext('2d');
            xLabelContext.clearRect(0,0,canvas_width,label_height);
            Chart.changeXLabel(xLabelContext, offsetX);

            /** 改变音乐时长 **/
            if(offsetX < 0){
            	if(type == 'drag'){
            		var absOffsetX = Math.abs(offsetX);
                	var startTime = Math.floor(absOffsetX / 2);
                    
                    var data = $($("#"+element).children(".data")).val();
        			var dataArray = eval('(' + data + ')');
        			if(absOffsetX <= dataArray.length){
        				/** 播放状态下才播放，否则不播放 **/
        				if(!media.paused){
        					media.currentTime = startTime;
                            media.play();
        				}
        			}
            	}
            }
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
			Chart.drawGridLineX(context);
			/** Y轴 **/
			Chart.drawGridLineY(context);
			/** 正常范围背景渲染 **/
			Chart.randerNormalArea(context);
			/** 胎心曲线描点 **/
			Chart.randerValue(context, element, offsetX);
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
					/*if(j % 6 == 0){
						context.lineWidth = 2;
					}else{
						context.lineWidth = 1;
					}*/
					context.lineWidth = 1;
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
				if(j % 3 == 0){
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
		randerValue : function(context, element, ox){
			var data = $($("#"+element).children(".data")).val();
			var fetalMoveData = $($("#"+element).children(".fetalMoveData")).val();
			var fetalMoveArray = fetalMoveData.split(",");
			if(data.trim() != "" && data != 'null'){
				var dataArray;
				if(data.indexOf("[") != -1 && data.indexOf("]") != -1){
					dataArray = eval('(' + data + ')');
				}else{
					data.replace("[", "");
					data.replace("]", "");
					dataArray = data.split(",");
				}
				if (ox >= 0) {
					ox = 0;
					offsetX = 0;
					context.beginPath();
					for(var i = 0;i < dataArray.length;i++){
						if(Math.abs(dataArray[i] - dataArray[i - 1]) > 30){
							context.moveTo(i + 0.5, canvas_height - (dataArray[i]-30)*2 + 0.5);
						}else{
							context.lineTo(i + 0.5, canvas_height - (dataArray[i]-30)*2 + 0.5);
						}
					}
					context.lineWidth = 1;
		            context.strokeStyle = colorSelect['black'];
		            context.stroke();
		            context.closePath();
				}else{
					ox = Math.abs(ox);
					context.beginPath();
					for(var i = ox;i < dataArray.length;i++){
						if(Math.abs(dataArray[i] - dataArray[i - 1]) > 30){
							context.moveTo(i - ox + 0.5, canvas_height - (dataArray[i]-30)*2 + 0.5);
						}else{
							context.lineTo(i - ox + 0.5, canvas_height - (dataArray[i]-30)*2 + 0.5);
						}
					}
					context.lineWidth = 1;
		            context.strokeStyle = colorSelect['black'];
		            context.stroke();
		            context.closePath();
				}
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
		moveEvent : function(context, element, ox){
			/** X轴 **/
			Chart.drawGridLineX(context);
			/** Y轴 **/
			Chart.drawGridLineY(context);
			/** 正常范围背景渲染 **/
			Chart.randerNormalArea(context);
			/** 胎心曲线描点 **/
			Chart.randerValue(context, element, ox);
		},
		changeXLabel : function(context, ox){
			if(ox >= 0){
				context.clearRect(0,0,Chart.getCanvasWidth(),Chart.getLabelHeight());
				Chart.drawLabelX(context);
			}else{
				for(var j = 0;j <= required_horizontal;j++){
					var absOffsetX = Math.abs(offsetX);
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
							context.fillText((j - blodLine) / 6 * 2 + 2, beginPoint + horizontal_size * j - 10, 20);
						}
					}
				}
			}
		},
		randerReportArea : function(context, start, width){
			context.fillStyle =colorSelect['grey'];
			context.fillRect(start,0,width,canvas_height);
		}
	}
	
}();