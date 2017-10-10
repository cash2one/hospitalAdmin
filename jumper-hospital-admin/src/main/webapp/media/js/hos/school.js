$(function(){
	/** 开通/关闭线下课程 **/
	$(".isschool-service-operate").bind("click", school.isschool);
	/** 开通/关闭网络课程 **/
	$(".isvideo-service-operate").bind("click", school.isvideo);
	/** 开通/关闭课程资讯 **/
	$(".isclass-service-operate").bind("click", school.isclass);
});

var school = function(){
	return {
		isschool:function(){
			var state = $(this).attr("value");
			var serverName = "isschool";
			if(state == 0){
				msg = "确认关闭线下课程服务？";
			}else{
				msg = "确认开通线下课程服务？";
			}
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/hos/schoolOperate',
					{'state':state,
					 'serverName':serverName},
					'get',
					true,
					'json',
					function resultHandler(data){
						var message = "";
						if(state == 0){
							if(data == 'failed'){
								message = "服务关闭失败！";
							}else if(data == 'success'){
								message = "服务关闭成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "程序错误，关闭失败！";
							}
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
								if(data == 'success'){
									location.reload();
								}
							});
						}else{
							if(data == 'failed'){
								message = "服务开通失败！";
							}else if(data == 'success'){
								message = "您已经开通孕妇学校的线下课程!是否去上传并安排课程？";
								$(".info-confirm-href").trigger("click");
								$(".info-confirm-body").text(message);
								$(".info-confirm-sure").click(function(){
									window.location = baseURL+"/school/library?libraryType=0";
								})
								$(".info-confirm-cancel").click(function(){
									location.reload();
							    });
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "程序错误，开通失败！";
							}
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
							});
						}
						
					}
				);
			});
		},
		isvideo:function(){
			var state = $(this).attr("value");
			var serverName = "isvideo";
			if(state == 0){
				msg = "确认关闭视频课程服务？";
			}else{
				msg = "确认开通视频课程服务？";
			}
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/hos/schoolOperate',
					{'state':state,
					 'serverName':serverName},
					'get',
					true,
					'json',
					function resultHandler(data){
						var message = "";
						if(state == 0){
							if(data == 'failed'){
								message = "服务关闭失败！";
							}else if(data == 'success'){
								message = "服务关闭成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "程序错误，关闭失败！";
							}
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
								if(data == 'success'){
									location.reload();
								}
							});
						}else{
							if(data == 'failed'){
								message = "服务开通失败！";
							}else if(data == 'success'){
								message = "您已经开通孕妇学校的视频课程!是否去上传并发布课程？";
								$(".info-confirm-href").trigger("click");
								$(".info-confirm-body").text(message);
								$(".info-confirm-sure").click(function(){
									window.location = baseURL+"/school/library?libraryType=1&isMyVideo=1&isPublish=true";
								})
								$(".info-confirm-cancel").click(function(){
									location.reload();
							    });
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "程序错误，开通失败！";
							}
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
							});
						}
					}
				);
			});
		},
		isclass:function(){
			var state = $(this).attr("value");
			var serverName = "isclass";
			if(state == 0){
				msg = "确认关闭课程资讯服务？";
			}else{
				msg = "确认开通线下资讯服务？";
			}
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/hos/schoolOperate',
					{'state':state,
					 'serverName':serverName},
					'get',
					true,
					'json',
					function resultHandler(data){
						var message = "";
						if(state == 0){
							if(data == 'failed'){
								message = "服务关闭失败！";
							}else if(data == 'success'){
								message = "服务关闭成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "程序错误，关闭失败！";
							}
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
								if(data == 'success'){
									location.reload();
								}
							});
						}else{
							if(data == 'failed'){
								message = "服务开通失败！";
							}else if(data == 'success'){
								message = "您已经开通孕妇学校的课堂资讯!是否去上传并发布资讯？";
								$(".info-confirm-href").trigger("click");
								$(".info-confirm-body").text(message);
								$(".info-confirm-sure").click(function(){
									window.location = baseURL+"/information/index";
								})
								$(".info-confirm-cancel").click(function(){
									location.reload();
							    });
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "程序错误，开通失败！";
							}
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
							});
						}
						
					}
				);
			});
		},
	}
}();