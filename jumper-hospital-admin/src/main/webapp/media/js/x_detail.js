/**
 * Canvas胎心图工具类
 * @auther rent
 * @date 2015-10-30
 **/
var offsetX = 0;
var offsetY = 0;
var default_start = 487;
var default_end = 1687;
var start_point = 487;
var end_point = 1687;
var offset_can = 0;
var Detail = function(){
	
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
	//var canvas_width = 2400;
	var canvas_width = 1080;
	/** canvas高度 **/
	var canvas_height = 420;
	/** 宫缩垂直多少个cell **/
	var uterus_vertical = 11;
	/** 宫缩图的高度 **/
	var uterus_height = 200;
	
	var testArray = [137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135,137,136,135,135,136,137,137,137,137,137,137,137,138,138,138,138,138,138,137,136,135,135,124,122,120,120,120,120,120,120,120,120,120,120,120,121,121,121,121,121,122,122,124,126,126,126,126,126,128,129,130,131,132,132,131,129,127,126,126,127,128,130,132,136,137,139,139,140,140,141,142,142,141,139,137,134,132,132,132,129,127,126,125,125,125,125,126,128,130,131,132,133,134,136,137,138,138,138,138,138,138,137,137,136,136,135,135,135,135,135,135,135,135,133,133,133,133,133,133,133,133,133,133,132,131,130,130,129,131,132,132,133,133,133,133,133,133,133,134,135,136,136,137,139,140,138,138,137,137,137,137,137,137,136,136,136,0,0,134,134,134,134,133,133,133,133,134,135,135,134,137,137,137,137,137,137,137,137,137,136,135,136,137,137,137,138,140,141,141,140,141,142,142,141,140,139,139,140,141,142,143,143,144,145,144,143,143,141,141,139,139,137,137,137,137,135,136,134,133,133,133,133,133,135,137,139,139,139,138,138,137,137,137,137,137,137,136,137,137,137,136,136,136,136,135,135,135,135,135];

	/** 颜色值数组定义 **/
	var colorSelect = {
		"line" : "rgb(241,137,168)",//红色线条颜色
		"safe" : "rgba(67,205,128,0.3)",//绿色正常背景填充颜色，0.3的透明度
		"black" : "rgb(0,0,0)"
	};
	var onclick=true;
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
			$("#"+element).children(".offsetX").val(value);
		},
		getOffsetX : function(element){
			return $("#"+element).children(".offsetX").val();
		},
		getUterusHeight : function(element){
			return uterus_height;
		},
		init : function(element){
			var is_start = false;
	        var rander_start = 0;
	        var rander_width = 0;
	        var timer = null;
	        var is_back = false;
	        Detail.chartInit(element);
            var canvasObj = $("#"+element+" .contentChart")[0];
            
            var canvas_to_window = canvasObj.getBoundingClientRect();
           
            default_start = Math.floor(canvas_to_window.left);
            start_point = Math.floor(canvas_to_window.left);
            default_end = Math.floor(canvas_to_window.right);
            end_point = Math.floor(canvas_to_window.right);
           
            canvasObj.onmousedown = function(event){
                var pos = Detail.windowToCanvas(canvasObj,event.clientX,event.clientY);
                canvasObj.onmousemove = function(event){
    				 canvasObj.style.cursor = "move";
                     var pos1 = Detail.windowToCanvas(canvasObj,event.clientX,event.clientY);
                     var x = pos1.x - pos.x;
                     var y = pos1.y - pos.y;
                     pos = pos1;
 	                 Detail.setOffsetX(element, parseInt(Detail.getOffsetX(element)) + x);
                     Detail.draw(element, "drag");
                     $("#datailstop").val(true);
             	 	 if(detailTimer != null){
             			clearInterval(detailTimer);
             		 }
                }
                canvasObj.onmouseup = function(){
                    canvasObj.onmousemove = null;
                    canvasObj.onmouseup = null;
                    canvasObj.style.cursor = "default";
                    getCurrentData();
            	 	var oldData = $($("table").find("div[user="+element+"]")).find("input.data").val();
            	 	//var oldData = $(".chart0").children(".data").val();
            	 	self.clearInterval(detailTimer);  
            	 	detailTimer= self.setInterval(function(){
			    		var curData = $($("table").find("div[user="+element+"]")).find("input.data").val();
			    		var curDataArray = curData.split(",");
			    		if(oldData != curData){
			    			var uInfo = $($("table").find("div[user="+element+"]")).find("ul").html();
		                	$(".panel-head").children("ul").html(uInfo);
			    			var offsetX = $($("table").find("div[user="+element+"]")).find(".offsetX").val();
			    			if(curDataArray.length >= 1200){
			    				$("#"+element).children(".offsetX").val(offsetX);
			    			}else{
			    				$("#"+element).children(".offsetX").val(0);
			    			}
			    			Detail.draw(element);
			    		}
					},3000);
                }
                canvasObj.ondblclick = function(event){
                	 var stop=$("#datailstop").val();
                	 if(stop!="true"){
                	 	$("#datailstop").val(true);
                	 	if(detailTimer != null){
                			clearInterval(detailTimer);
                		}
                	 }else{
                	 	$("#datailstop").val(false);
                	 	getCurrentData();
                	 	var oldData = $($("table").find("div[user="+element+"]")).find("input.data").val();
                	 	//var oldData = $(".chart0").children(".data").val();
                	 	self.clearInterval(detailTimer);  
                	 	detailTimer= self.setInterval(function(){
    			    		var curData = $($("table").find("div[user="+element+"]")).find("input.data").val();
    			    		//var fetalMove = $("table").find("div[user="+element+"]").find("input.fetalMove").val();
    			    		var curDataArray = curData.split(",");
    			    		if(oldData != curData){
    			    			var uInfo = $($("table").find("div[user="+element+"]")).find("ul").html();
    		                	$(".panel-head").children("ul").html(uInfo);
    			    			var offsetX = $($("table").find("div[user="+element+"]")).find(".offsetX").val();
    			    			if(curDataArray.length >= 1200){
    			    				$("#"+element).children(".offsetX").val(offsetX);
    			    			}else{
    			    				$("#"+element).children(".offsetX").val(0);
    			    			}
    			    			Detail.draw(element);
    			    		}
    					},3000);
                	 } 
	            }
            }
		},
		chartInit : function(element){
            /** 初始化画图 **/
            var can = $("#"+element+" .contentChart")[0];
            can.width = Detail.getCanvasWidth();
            can.height = Detail.getCanvasHeight();
            context = can.getContext('2d');
            Detail.drawGrid(context, element);

            /** 初始化y周坐标 **/
            var yLabel = $("#"+element+" .oyLabel")[0];
            yLabel.width = Detail.getLabelWeight();
            yLabel.height = Detail.getCanvasHeight();
            var ylContext = yLabel.getContext('2d');
            Detail.drawLabelY(ylContext);

            /** 初始化X轴坐标 **/
            var xLabel = $("#"+element+" .oxLabel")[0];
            xLabel.width = Detail.getCanvasWidth();
            xLabel.height = Detail.getLabelHeight();
            var xlContext = xLabel.getContext('2d');
            Detail.drawLabelX(xlContext);
            
            /** 初始化宫缩图 **/
            var uterusChart = $("#"+element+" .uterusChart")[0];
            uterusChart.width = Detail.getCanvasWidth();
            uterusChart.height = Detail.getUterusHeight();
            var uterusContext = uterusChart.getContext('2d');
            Detail.drawUterus(uterusContext, element);
            
            /** 宫缩Y轴坐标 **/
            var uyLabel = $("#"+element+" .uterusYLabel")[0];
            uyLabel.width = Detail.getLabelWeight();
            uyLabel.height = Detail.getUterusHeight();
            var uyContext = uyLabel.getContext('2d');
            Detail.drawUterusLabelY(uyContext);
		},
		draw : function(element){
            var obj = $("#"+element+" .contentChart")[0];
            var context = obj.getContext('2d');
            context.clearRect(0,0,canvas_width,canvas_height);
            Detail.moveEvent(context, element);
            
            var xLabelObj = $("#"+element+" .oxLabel")[0];
            var xLabelContext = xLabelObj.getContext('2d');
            xLabelContext.clearRect(0,0,canvas_width,label_height);
            Detail.changeXLabel(xLabelContext, element);
            
            var uterusChart = $("#"+element+" .uterusChart")[0];
            var uterusContext = uterusChart.getContext('2d');
            uterusContext.clearRect(0,0,canvas_width,uterus_height);
            Detail.drawUterus(uterusContext, element);
		},
		clearContext : function(element){
			var obj = $("#"+element+" .contentChart")[0];
            var context = obj.getContext('2d');
            context.clearRect(0,0,canvas_width,canvas_height);
            
            var xLabelObj = $("#"+element+" .oxLabel")[0];
            var xLabelContext = xLabelObj.getContext('2d');
            xLabelContext.clearRect(0,0,canvas_width,label_height);
            
            var yLabelObj = $("#"+element+" .oyLabel")[0];
            var yLabelContext = yLabelObj.getContext('2d');
            xLabelContext.clearRect(0,0,label_weight,canvas_height);
            
            var uterusChart = $("#"+element+" .uterusChart")[0];
            var uterusContext = uterusChart.getContext('2d');
            uterusContext.clearRect(0,0,canvas_width,uterus_height);
            this.setOffsetX(element, 0);
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
			Detail.drawGridLineX(context);
			/** Y轴 **/
			Detail.drawGridLineY(context, element);
			/** 正常范围背景渲染 **/
			Detail.randerNormalArea(context);
			/** 胎心曲线描点 **/
			Detail.randerValue(context, element);
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
		drawGridLineY : function(context, element){
			var offsetX = parseInt(Detail.getOffsetX(element));
			/** Y轴初始化 **/
			if(offsetX >= 0){
				for(var j = 0;j <= required_horizontal;j++){
					context.beginPath();
					var ox = 40 * j - 0.5;
					if(j == 0){
						ox = 0.5;
					}
					/** 刻度加粗 **//*
					if(j % 6 == 0){
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
		randerValue : function(context, element){
			var data = $($("table").find("div[user="+element+"]")).find("input.data").val();
			var ox = parseInt(Detail.getOffsetX(element));
			var fetalMoveData = $($("table").find("div[user="+element+"]")).find("input.fetalMove").val();
			var fetalMoveArray = fetalMoveData.split(",");
			if(data != ""){
				var dataArray = data.split(",");
				if (ox >= 0) {
					ox = 0;
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
		moveEvent : function(context, element){
			/** X轴 **/
			Detail.drawGridLineX(context);
			/** Y轴 **/
			Detail.drawGridLineY(context, element);
			/** 正常范围背景渲染 **/
			Detail.randerNormalArea(context);
			/** 胎心曲线描点 **/
			Detail.randerValue(context, element);
		},
		changeXLabel : function(context, element){
			var ox = parseInt(Detail.getOffsetX(element));
			if(ox >= 0){
				context.clearRect(0,0,Detail.getCanvasWidth(),Detail.getLabelHeight());
				Detail.drawLabelX(context);
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
							context.fillText((j - blodLine) / 6 * 2 + 2, beginPoint + horizontal_size * j - 10, 20);
						}
					}
				}
			}
		},
		drawUterusLabelY : function(context){
			var yValue = 100;
			for(var i = 0;i < uterus_vertical;i++){
				if(i % 2 == 0){
					context.font = "23px 黑体";
					context.fillStyle = "black";
					if(i == 0){
						context.fillText(yValue, 10, vertical_size * i + 16);
					}else if(i == uterus_vertical - 1){
						context.fillText(yValue, 10, vertical_size * i);
					}else{
						context.fillText(yValue, 10, vertical_size * i + 5);
					}
					yValue = yValue - 20;
				}
			}
		},
		drawUterus : function(context, element){
			Detail.drawUterusXLine(context);
			Detail.drawUterusYLine(context, element);
			Detail.randerUterusValue(context, element, Detail.getOffsetX(element));
		},
		drawUterusXLine : function(context){
			for(var i = 0; i < uterus_vertical; i++){
				context.beginPath();
				var oy = vertical_size * i - 0.5;
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
				context.lineTo(canvas_width+0.5, oy);
	            context.strokeStyle = colorSelect['line'];
	            context.stroke();
	            context.closePath();
			}
		},
		drawUterusYLine : function(context, element){
			/** Y轴初始化 **/
			if(Detail.getOffsetX(element) >= 0){
				for(var j = 0;j <= required_horizontal;j++){
					context.beginPath();
					var ox = horizontal_size * j - 0.5;
					if(j == 0){
						ox = 0.5;
					}
					
					context.lineWidth = 1;
					context.moveTo(ox, 0.5);
					context.lineTo(ox, canvas_height + 0.5);
		            context.strokeStyle = colorSelect['line'];
		            context.stroke();
		            context.closePath();
				}
			}else{
				for(var j = 0;j <= required_horizontal;j++){
					var absOffsetX = Math.abs(Detail.getOffsetX(element));
					var beginPoint = horizontal_size - absOffsetX % horizontal_size;
					var blodLine = 6 - Math.ceil(absOffsetX / 40);

					context.beginPath();
					var ox = beginPoint + horizontal_size * j - 0.5;
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
		randerUterusValue : function(context, element, ox){
			var data = $($("table").find("div[user="+element+"]")).find("input.uterusData").val();
			if(data.trim() != "" && data != 'null'){
				$(".dialog_no_toco").hide();
				var dataArray;
				if(data.indexOf("[") != -1 && data.indexOf("]") != -1){
					dataArray = eval('(' + data + ')');
				}else{
					data.replace("[", "");
					data.replace("]", "");
					dataArray = data.split(",");
				}
				
				ox = Math.abs(ox);
				context.beginPath();
				for(var i = ox;i < dataArray.length; i++){
					context.lineTo(i - ox + 0.5, uterus_height - dataArray[i]*2 + 0.5);
				}
				context.lineWidth = 1;
	            context.strokeStyle = colorSelect['black'];
	            context.stroke();
	            context.closePath();
			}else{
				$(".dialog_no_toco").show();
			}
		},
		randerReportArea : function(context, start, width){
			context.fillStyle =colorSelect['grey'];
			context.fillRect(start,0,width,canvas_height);
		}
	}
	
}();