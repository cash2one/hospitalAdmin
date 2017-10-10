$(function(){
	
	/** 初始化查询开始时间控件 **/
	$('#beginTime').daterangepicker({
		singleDatePicker: true,
		format:"YYYY-MM-DD",
		startDate : new Date,
		language: 'zh-CN' //汉化
	});
	/** 初始化查询结束时间控件 **/
	$('#endTime').daterangepicker({
		singleDatePicker: true,
		format:"YYYY-MM-DD",
		startDate : new Date,
		language: 'zh-CN' //汉化
	});
	
	$(".logType").on("click", operateLog.changeType);
	
	$(".quickQuery").on("click", operateLog.quickQuery);
	
});

var operateLog = function(){
	return {
		changeType:function(){
			var type = $(this).attr("value");
			$("#logType").val(type);
			$("#form").submit();
		},
		quickQuery:function(){
			var quickQuerytype = $(this).attr("value");
			$("#quickQuery").val(quickQuerytype);
			$("#form").submit();
		}
	}
}();