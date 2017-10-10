$(function(){
	$(".add-chanel").on("click", chanel.addChanelInfo);
	$(".chanel-update").on("click", chanel.updateChanelInfo);
	$(".edit-chanel-confirm-sure").on("click", chanel.editChanelSubmit);
	$(".chanel-delete").on("click", chanel.deleteChanelInfo);
	$(".chanel-top").on("click", chanel.topChanelInfo);
	$(".default-sub").on("change", chanel.changeDefaultSub);
	$(".chanel-state").on("change", chanel.changeChanelState);
});

var chanel = function(){
	return {
		addChanelInfo : function(){
			$(".edit-chanel-title").text("添加频道");
			$("#editChanelId").val("");
			$("#editChanelName").val("");
			$(".edit-chanel-error").addClass("hide");
			$(".edit-chanel-href").trigger("click");
		},
		updateChanelInfo : function(){
			var confirmMessage = "修改后的名称将在用户的app端显示。确定修改？";
			var id = $(this).attr("value");
			var chanelName = $(this).parent().parent().children().eq(1).html();
			$("#editChanelId").val(id);
			$("#editChanelName").val(chanelName);
			App.confirm_show("提示！", confirmMessage, function(){
				$(".info-confirm-cancel").trigger("click");
				$(".edit-chanel-title").text("修改频道");
				$(".edit-chanel-href").trigger("click");
			});
		},
		editChanelSubmit : function(){
			$("#edit-chanel-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".edit-chanel-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息 **/
					App.validateSuccess(".edit-chanel-error");
				},
				callback:function(request, data){
					$(".edit-chanel-confirm-cancel").trigger("click");
					if(request instanceof Object){
						if(request.hasOwnProperty("result")){
							var result = request.result;
							var defaultNum = request.defaultNum;
							var showNum = request.showNum;
							
							var message = "";
							if(showNum >= 10){
								message = "app端显示不超过10个频道";
							}else{
								if(defaultNum >= 4){
									message = "默认订阅不超过4个频道";
								}else{
									if(result == "success"){
										message = "编辑频道信息成功！";
									}else if(result == "error"){
										message = "编辑频道信息异常！";
									}else if(result == "paramsError"){
										message = "参数错误，请刷新浏览器重试！";
									}else if(result == "failed"){
										message = "编辑频道信息失败！";
									}
								}
							}
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
								if(message != ''){
									location.reload();
								}
							});
						}else{
							var loginStatus = request.getResponseHeader("loginStatus");
							if(loginStatus == "accessDenied"){
								App.noPermission();
							}
						}
					}
				}
			});
		},
		deleteChanelInfo : function(){
			var confirmMessage = "删除后频道的文章将全部清空，且在用户的app端不再显示。确定删除？";
			var element = $(this);
			var id = $(this).attr("value");
			App.confirm_show("提示！", confirmMessage, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest('/classroom/delChanel', {'id':id}, 'get', true, 'json',
					function resultHandler(data){
						var message = "";
						if(data == 'failed'){
							message = "删除失败！";
						}else if(data == 'success'){
							message = "删除成功！";
						}else if(data == 'paramsError'){
							message = "参数错误！";
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							//data == 'success' && $(element).parent().parent().remove();
							location.reload();
						});
						setTimeout(function() {
							location.reload();
						}, 2000);
					}
				);
			});
		},
		topChanelInfo : function(){
			var id = $(this).attr("value");
			location.href = baseURL+"/classroom/topChanel?id="+id;
		},
		changeDefaultSub : function(){
			var id = $(this).attr("flag");
			var operate = $(this).val();
			chanel.changeChanelDefaultAndState(id, operate, 1, $(this));
		},
		changeChanelState : function(){
			var id = $(this).attr("flag");
			var operate = $(this).val();
			chanel.changeChanelDefaultAndState(id, operate, 2, $(this));
		},
		changeChanelDefaultAndState : function(id, operate, type, element){
			App.ajaxRequest('/classroom/state', {'id':id, 'operate':operate, 'type':type}, 'get', true, 'json',
				function resultHandler(data){
					var result = data.result;
					var message = data.message;

					if(result == 'failed' || result == 'error' || result == 'paramsError'){
						App.dialog_show("提示！", message, function(){
							var oldSelect = operate == 0 ? 1 : 0;
							type == 1 && element.find("option[value='"+oldSelect+"']").attr("selected",true);
							type == 2 && element.find("option[value='"+oldSelect+"']").attr("selected",true);
							$(".info-dialog-close").trigger("click");
						});
					}
				}
			);
		}
	}
}();