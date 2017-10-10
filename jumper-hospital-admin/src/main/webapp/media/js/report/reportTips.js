$(function(){
	
	reportTips.reportTip();
});

var reportTips = function(){
	return {
		//首页的提示
		reportTip:function() {
		 	$.ajax({
		 		url : url+"/report/reportTip",
		 		type : "POST",
		 		dataType : "json",
		 		async:false,
		 		data:{},
		 		success : function(json) {
		 			if(json.isShowHeadTip){
		 				$("#isShowHeadTip").css("display","inline-block");
		 			}else{
		 				$("#isShowHeadTip").css("display","none");
		 			}
		 			//待审核报告
		 			if(json.totalNotFinishOrder>0){
		 				$("#totalNotFinishOrder").css("display","inline-block");
		 				$("#totalNotFinishOrder").html(json.totalNotFinishOrder);
		 			}else{
		 				$("#totalNotFinishOrder").css("display","none");
		 			}
		 			//已完成报告
		 			if(json.totalFinishUnredReport>0){
		 				$("#totalFinishUnredReport").css("display","inline-block");
		 				$("#totalFinishUnredReport").html(json.totalFinishUnredReport);
		 			}else{
		 				$("#totalFinishUnredReport").css("display","none");
		 			}
		 		},
		 		error : function(XMLHttpRequest, textStatus, errorThrown) { 
		 			errorTs("获取用户信息失败");
		 			}
		 		});
		}
	}
}();