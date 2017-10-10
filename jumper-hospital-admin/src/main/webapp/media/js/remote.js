$(function(){
	/** 重新监测 **/
	$(".test-again").bind("click", {type:'restart'}, Console._operateRemote);
	/** 焦点用户 **/
	$(".view-user").bind("click", {type:'view'}, Console._operateRemote);
	/** 重新排队 **/
	$(".queue-again").bind("click", {type:'delete'}, Console._operateRemote);
	/** 结束监控(监测完成正常结束) **/
	$(".end-remote").bind("click", Console._endUserRemote);
});

var Console = function(){
	/** 操作类型 **/
	var operateType = {
        'view': 1,
        'delete': 2,
        'restart': 3,
        'end': 4
    };
	
	return {
		/** dialog提示 **/
		_operateRemote: function(event){
			var userId = $(this).attr("value");
			var hospitalId = $("#hospitalId").val();
			switch (event.data.type) {
				case "view":
					App.confirm_show("", "确认查看该用户信息？", function(){
						$(".info-confirm-cancel").trigger("click");
						Console._operateRemoteDetail(userId, hospitalId, event.data.type);
					});
					break;
				case "delete":
					App.confirm_show("", "确认取消监护？", function(){
						$(".info-confirm-cancel").trigger("click");
						Console._operateRemoteDetail(userId, hospitalId, event.data.type);
					});
					break;
				case "restart":
					App.confirm_show("", "确认重新监护？", function(){
						$(".info-confirm-cancel").trigger("click");
						Console._operateRemoteDetail(userId, hospitalId, event.data.type);
					});
					break;
				default:
					break;
			}
			
		},
		_operateRemote: function(event){
			var userId = $(this).attr("value");
			var hospitalId = $("#hospitalId").val();
			switch (event.data.type) {
				case "view":
					App.confirm_show("", "确认查看该用户信息？", function(){
						$(".info-confirm-cancel").trigger("click");
						Console._operateRemoteDetail(userId, hospitalId, event.data.type);
					});
					break;
				case "delete":
					App.confirm_show("", "确认取消监护？", function(){
						$(".info-confirm-cancel").trigger("click");
						Console._operateRemoteDetail(userId, hospitalId, event.data.type);
					});
					break;
				case "restart":
					App.confirm_show("", "确认重新监护？", function(){
						$(".info-confirm-cancel").trigger("click");
						Console._operateRemoteDetail(userId, hospitalId, event.data.type);
					});
					break;
				default:
					break;
			}
			
		},
		/** 具体请求 **/
		_operateRemoteDetail : function(userId, hospitalId, type){
			App.ajaxRequest(
				'/remote/operate',
				{'hospitalId':hospitalId, 'userId':userId, 'operateType':operateType[type]},
				'get',
				true,
				'json',
				function resultHandler(data){
					/** var str = JSON.stringify(data); **/
					var message = "";
					if(data == 'failed'){
						message = "处理失败！";
					}else if(data == 'success'){
						message = "处理成功！";
					}
					App.dialog_show("提示！", message, function(){
						$(".info-dialog-close").trigger("click");
						if(data == 'success'){
							location.reload();
						}
					});
				},
				function faultHander(data){
					App.dialog_show("", "请求失败，网络错误！", function(){
						$(".info-dialog-close").trigger("click");
					});
				}
			);
		},
		/** 结束监测 **/
		_endUserRemote: function(){
			var userId = $(this).attr("value");
			var hospitalId = $("#hospitalId").val();
			App.confirm_show("", "确认结束监护？", function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/remote/end',
					{'hospitalId':hospitalId, 'userId':userId, 'operateType':operateType['end']},
					'POST',
					true,
					'json',
					function resultHandler(data){
						/** var str = JSON.stringify(data); **/
						var message = "";
						if(data == 'failed'){
							message = "处理失败！";
						}else if(data == 'success'){
							message = "处理成功！";
						}else if(data == 'error'){
							message = "踢出大屏幕用户失败！";
						}else if(data == 'paramError'){
							message = "参数错误，请刷新页面重试！";
						}
						
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
					},
					function faultHander(data){
						App.dialog_show("", "请求失败，网络错误！", function(){
							$(".info-dialog-close").trigger("click");
						});
					}
				);
			});
		}
	}
	
}();