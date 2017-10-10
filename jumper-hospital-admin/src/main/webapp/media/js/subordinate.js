$(function(){
	$(".unlock").bind("click", {'state' : 'unlock'}, Admin.operate);
	$(".disable").bind("click", {'state' : 'disabled'}, Admin.operate);
	$(".useable").bind("click", {'state' : 'useable'}, Admin.operate);
	/** 新增从属医院 **/
	$(".add-subordinate").bind("click", Admin.addAdmin);
	$(".subordinate-confirm-sure").bind("click", Admin.confirmOk);
	$("#checkall").bind("click", function () {
		var checkvalue = $("#checkall").attr('value');
		if(typeof(checkvalue) == "undefined"){
			return false;
		}
		if($("#checkall").is(':checked')){
			$("input[type='checkbox']").attr("checked",true);
		}else{
			$("input[type='checkbox']").attr("checked",false);
		}
	});
});
var Admin = function(){
	var type = {
		'unlock' : 1,
		'disabled' : 2,
		'useable' : 3
	};
	return {
		operate : function(event){
			var subId = $(this).attr("value");
			var msg = "是否取消该机构的从属关系？";
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/hos/cancel',
					{'id':subId},
					'get',
					true,
					'json',
					function resultHandler(data){
						var message = "";
						if(data == 'failed'){
							message = "处理失败！";
						}else if(data == 'success'){
							message = "处理成功！";
						}else if(data == 'paramsError'){
							message = "参数错误！";
						}else if(data == "error"){
							message = "处理异常！";
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
					}
				);
			});
		},
		addAdmin : function(){
			Admin.formReset();
			$(".admin-confirm-href").trigger("click");
		},
		gerRoleInfo :  function(subordinatesId){
			App.ajaxRequest(
				'/hospital/subordinates',{'subordinatesId':subordinatesId},'get',true,'json',
				function resultHandler(data){
					if(data == "error"){
						App.dialog_show("", "请求出错，请刷新或者重新登录重试！", function(){
							$(".info-dialog-close").trigger("click");
						});
					}else{
						if(data.roleIds.length < 1){
							App.dialog_show("", "暂无角色信息！", function(){
								$(".info-dialog-close").trigger("click");
							});
						}else{
							//清掉选中的角色
							$(".role-radio").attr("checked",false);
							if(data.author == true){
								$(".cloud_content").show()
							}else{
								$("#job-num").removeAttr("datatype");//清掉工号非空校验
								$(".cloud_content").hide()
							}
							for(var i = 0;i < data.roleIds.length;i++){
								$(".role-radio").each(function(index, element){
				            		var roleId = $(this).val();
				            		if(data.roleIds[i] == roleId){
				            			$(this).attr("checked","checked");
				            		}
				            	});
							}
						}
					}
				}
			);
		},
		formReset : function(){
			$(".role-radio").each(function(index, element){
        		$(this).removeAttr("checked");
        	});
			$("#checkall").attr("checked",false);
			$(".belong-hospital").show();
			if(!$(".subord-error").hasClass("hide")){
				$(".subord-error").addClass("hide");
			}
			//清除工号字段非空校验
			if(typeof($("#job-num").attr("datatype")) != "undefined"){
				$("#job-num").removeAttr("datatype");
			}
		},
		confirmOk : function(){
			$("#edit-admin-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".subord-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					var checkvalue = $("input[type='checkbox']").attr('value');
					if (typeof(checkvalue) == "undefined") { 
							App.validateErrorInit(".subord-error", "没有从属医院选择！");
							return false;
					}  
					return $("input[type='checkbox']").is(':checked')==true?true:false;
					/** 表单验证成功后的回调,取消错误验证消息 **/
					App.validateSuccess(".subord-error");
				},
				callback:function(request, data){
					if(request.msg == -2 || request.msg == -3){
						message = request.msgBox;
						if(!$(".subord-error").hasClass("hide")){
							$(".subord-error").addClass("hide");
						}
					}else if(request == "error"){
						message = "处理失败，该从属已存在！";
						if(!$(".subord-error").hasClass("hide")){
							$(".subord-error").addClass("hide");
						}
					}else{
						$(".subordinate-confirm-cancel").trigger("click");
						if(request instanceof Object){
							var message = "";
								message = request.msgBox;
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
								if(request.msg == 1 || request.msg == 0){
									location.reload();
								}
							});
							var loginStatus = request.getResponseHeader("loginStatus");
							if(loginStatus == "accessDenied"){
								App.noPermission();
							}
						}else{
							var message = "";
							if(request == "success"){
								message = "新增从属机构成功！";
							}else if(request == "failed"){
								message = "编辑从属机构失败！";
							}else if(request == "paramsError"){
								message = "参数错误，请刷新浏览器重试！";
							}
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
								if(request == 'success' || request.msg == 0){
									location.reload();
								}
							});
						}
					}
				}
			});
		}
	}
}();