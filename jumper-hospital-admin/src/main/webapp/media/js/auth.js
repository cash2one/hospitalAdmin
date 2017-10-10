$(function(){
	/** 解锁事件绑定 **/
	$(".unlock").bind("click", {'state' : 'unlock'}, auth.authAdminOperate);
	/** 禁用事件绑定 **/
	$(".disable").bind("click", {'state' : 'disabled'}, auth.authAdminOperate);
	/** 解锁事件绑定 **/
	$(".useable").bind("click", {'state' : 'useable'}, auth.authAdminOperate);
	/** 添加管理员按钮点击事件 **/
	$(".add-auth-admin").bind("click", auth.authAdminEditBtnClick);
	/** 添加管理员数据提交事件 **/
	$(".auth-admin-confirm-sure").bind("click", auth.editAdminSubmitOk);
	/** 新增支付项目管理员 **/
	$(".add-payment").bind("click", auth.editPaymentAdmin);
	/** 新增医院管理员数据提交 **/
	$(".auth-payment-sure").bind("click", auth.editPaymentAdminSubmit);
	/** 编辑管理员数据 **/
	$(".update-admin").bind("click", auth.updateAuthAdmin);
	/** 编辑角色弹框 **/
	$(".add-role").bind("click", auth.roleAdd);
	/** 编辑角色信息数据提交 **/
	$(".role-edit-sure").bind("click", auth.editRoleSubmitOk);
	/** 修改角色信息 **/
	$(".edit-role").bind("click", auth.roleEdit);
	/** 添加权限信息Dialog **/
	$(".add-auth").bind("click", auth.authAdd);
	/** 编辑权限信息Dialog **/
	$(".edit-auth").bind("click", auth.authEdit);
	/** 编辑权限信息后台数据提交 **/
	$(".auth-edit-sure").bind("click", auth.authSubmitOk);
	/** 自动补全医院信息 **/
	$("#auth-admin-hospital").autocomplete(baseURL+"/hos/find",{
		dataType: "json",
        minChars:2,
        max:10,
        autoFill:true,
        matchContains:true,
        width: 473,
        scrollHeight:220,
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row, i, max){
        	return row.name;
        }
    }).result(function(e, row) {
    	$("#authHospitalId").val(row.id);
    });
});

var auth = function(){
	
	var type = {
		'unlock' : 1,
		'disabled' : 2,
		'useable' : 3
	};
	
	return {
		/** 新增管理员按钮点击事件响应 **/
		authAdminEditBtnClick : function(){
			App.validateSuccess(".auth-admin-error");
			auth.formReset();
			$('#username').removeAttr("disabled");
			$(".auth-admin-confirm-href").trigger("click");
		},
		/** 编辑管理员信息 **/
		updateAuthAdmin : function(){
			App.validateSuccess(".auth-admin-error");
			var params = $(this).attr("value").split("|");
			if(params == '' || params.length < 1){
				return;
			}
			var id = params[0];
			var username = params[1];
			var password = params[2];
			var name = params[3];
			var mobile = params[4];
			
			$("#auth-admin-id").val(id);
			$("#auth-admin-name").val(name);
			$("#auth-username").val(username);
			$("#auth-password").val(password);
			$("#auth-password2").val(password);
			$("#auth-mobile").val(mobile);
			
			$(".auth-belong-hospital").hide();
			$('#username').attr('disabled',"true");
			
			$(".auth-admin-confirm-href").trigger("click");
		},
		/** 管理员操作执行 **/
		authAdminOperate : function(event){
			var adminId = $(this).attr("value");
			var operateType = event.data.state;
			var msg = "";
			switch (operateType) {
				case 'unlock':
					msg = "确认解锁该用户？";
					break;
				case 'disabled':
					msg = "是否禁用该用户？";
					break;
				case 'useable':
					msg = "是否启用该用户？";
					break;
				default:
					break;
			}
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/auth/operate',
					{'id':adminId, 'type' : type[operateType]},
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
		/** 新增、编辑管理员信息提交 **/
		editAdminSubmitOk : function(){
			$("#edit-auth-admin-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".auth-admin-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息 **/
					App.validateSuccess(".auth-admin-error");
				},
				callback:function(request, data){
					$(".auth-admin-confirm-cancel").trigger("click");
					
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var message = "";
						if(request == "success"){
							message = "编辑管理员信息成功！";
						}else if(request == "failed"){
							message = "编辑管理员信息失败！";
						}else if(request == "paramsError"){
							message = "参数错误，请刷新浏览器重试！";
						}else if(request == "error"){
							message = "处理异常！";
						}
						
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(request == 'success'){
								location.reload();
							}
						});
					}
				}
			});
		},
		/** 表单重置 **/
		formReset : function(){
			$("#auth-id").val("");
			$("#auth-name").val("");
			$("#auth-username").val("");
			$("#auth-password").val("");
			$("#auth-password2").val("");
			$(".auth-belong-hospital").show();
			if(!$(".auth-admin-error").hasClass("hide")){
				$(".auth-admin-error").addClass("hide");
			}
		},
		roleAdd : function(){
			$(".role-edit-href").trigger("click");
		},
		editRoleSubmitOk : function(){
			$("#role-edit-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".role-edit-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息 **/
					App.validateSuccess(".role-edit-error");
				},
				callback:function(request, data){
					$(".role-edit-cancel").trigger("click");
					
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var message = "";
						if(request == "success"){
							message = "编辑角色信息成功！";
						}else if(request == "failed"){
							message = "编辑角色信息失败！";
						}
						
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(request == 'success'){
								location.reload();
							}
						});
					}
				}
			});
		},
		roleEdit : function(){
			var params = $(this).attr("value");
			var paramArray = params.split("|");
			var id = paramArray[0];
			var name = paramArray[1];
			var desc = paramArray[2];
			
			$("#role-id").val(id);
			$("#role-name").val(name);
			$("#role-desc").val(desc);
			
			$(".role-edit-href").trigger("click");
		},
		authAdd : function(){
			auth.authEditFormReset();
			var parent = $("#parent-id").val();
			if(parent != ""){
				$("#auth-parent").val(parent);
			}
			$(".auth-edit-href").trigger("click");
		},
		authEdit : function(){
			var parent = $("#parent-id").val();
			var params = $(this).attr("value");
			var paramArray = params.split("|");
			var id = paramArray[0];
			var auth = paramArray[1];
			var desc = paramArray[2];
			var url = paramArray[3];
			
			$("#auth-id").val(id);
			$("#auth-name").val(auth);
			$("#auth-desc").val(desc);
			$("#auth-url").val(url);
			if(parent != ""){
				$("#auth-parent").val(parent);
			}
			$(".auth-edit-href").trigger("click");
		},
		authEditFormReset : function(){
			$("#auth-parent").val("");
			$("#auth-id").val("");
			$("#auth-name").val("");
			$("#auth-desc").val("");
			$("#auth-url").val("");
			if(!$(".auth-edit-error").hasClass("hide")){
				$(".auth-edit-error").addClass("hide");
			}
		},
		authSubmitOk : function(){
			$("#auth-edit-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".auth-edit-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息 **/
					App.validateSuccess(".auth-edit-error");
				},
				callback:function(request, data){
					$(".auth-edit-cancel").trigger("click");
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var message = "";
						if(request == "success"){
							message = "编辑模块信息成功！";
						}else if(request == "failed"){
							message = "编辑模块信息失败！";
						}
						
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(request == 'success'){
								location.reload();
							}
						});
					}
				}
			});
		},
		editPaymentAdmin : function(){
			App.ajaxRequest(
				'/auth/paymentRoles', {'used': 1}, 'get', true, 'json',
				function resultHandler(data){
					if(data == 'error'){
						message = "处理失败！";
					}else{
						var content = "";
						for(var role in data){
							content+="<label class='radio-inline'><input type='radio' name='roleId' value='"+role+"'>"+data[role]+"</label>";
						}
						$(".role").html(content);
					}
				}
			);
			$(".add-payment-admin-dialog").trigger("click");
		},
		editPaymentAdminSubmit : function(){
			$("#payment-admin-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".auth-payment-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息 **/
					App.validateSuccess(".auth-payment-error");
				},
				callback:function(request, data){
					$(".auth-payment-cancel").trigger("click");
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var message = "";
						if(request == "success"){
							message = "添加支付管理员成功！";
						}else if(request == "failed"){
							message = "添加支付管理员失败！";
						}else if(request == "paramsError"){
							message = "参数错误，请刷新浏览器重试！";
						}else if(request == "mobileExist"){
							message = "手机号码已经存在！";
						}else if(request == "error"){
							message = "处理异常！";
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(request == 'success'){
								location.reload();
							}
						});
					}
				}
			});
		}
	}
}();

function checkBoxClick(elements){
	var state = $(elements).attr("class");
	var flag = state.split("_");
	if(flag[0] == 'parent'){
		if($(elements).is(':checked')){
    		$(".child_"+flag[1]).attr("checked", true);
    	}else{
    		$(".child_"+flag[1]).attr("checked", false);
    	}
	}else{
		if($(elements).is(':checked')){
			$(".parent_"+flag[1]).attr("checked", true);
		}
	}
}

function back(){
	window.history.back();
}
