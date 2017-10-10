$(function(){
	$(".unlock").bind("click", {'state' : 'unlock'}, Admin.operate);
	$(".disable").bind("click", {'state' : 'disabled'}, Admin.operate);
	$(".useable").bind("click", {'state' : 'useable'}, Admin.operate);
	/** 新增管理员 **/
	$(".add-admin").bind("click", Admin.addAdmin);
	$(".update-admin").bind("click", Admin.updateAdmin);
	$(".admin-confirm-sure").bind("click", Admin.confirmOk);
	$(".role-radio").bind("click",Admin.getAuthor);//根据角色id获取权限
	
	
	/** 自动补全医院信息 **/
	$("#admin-hospital").autocomplete(baseURL+"/hos/find",{
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
    	$("#hospitalId").val(row.id);
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
					'/admin/operate',
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
		addAdmin : function(){
			Admin.formReset();
			$('#username').removeAttr("disabled");
			$(".admin-confirm-href").trigger("click");
		},
		updateAdmin : function(){
			Admin.cloudReset();
			var params = $(this).attr("value").split("|");
			if(params == '' || params.length < 1){
				return;
			}
			var id = params[0];
			var username = params[1];
			var password = params[2];
			var name = params[3];
			var mobile = params[4];
			var titleid= params[5];
			var hospitalMajorid= params[6];
			var introduction= params[7];
			
			//是否添加孕期诊所
			var isHospitalNst=params[8];
			if(isHospitalNst=="true"){
				$("#isHospitalNst").attr("checked","checked");
			}
			
			$("#admin-id").val(id);
			$("#name").val(name);
			$("#username").val(username);
			$("#password").val(password);
			$("#password2").val(password);
			$("#mobile").val(mobile);
			$("#editTitleid option[value='"+titleid+"']").attr("selected", "selected"); 
			//$("#titleid").val(titleid);
			$("#editHospitalMajorid option[value='"+hospitalMajorid+"']").attr("selected","selected"); 
			//$("#hospitalMajorid").val(hospitalMajorid);
			$("#introduction").val(introduction);
			$(".belong-hospital").hide();
			$('#username').attr('disabled',"true");
			
			Admin.gerRoleInfo(id);
			//Admin.getCloudViditInfo(id);去掉云随访权限开通
			$(".admin-confirm-href").trigger("click");
		},
		getCloudViditInfo : function(adminId){
			App.ajaxRequest(
					'/visits/getCloudViditInfo',{'adminId':adminId},'get',true,'json',
					function resultHandler(data){
						if(data == "error"){
							App.dialog_show("", "请求出错，请刷新或者重新登录重试！", function(){
								$(".info-dialog-close").trigger("click");
							});
						}else{
							data.doctorWorkNum.length>0 ? $("#job-num").attr({"value":data.doctorWorkNum, "disabled":"disabled"}):$("#job-num").removeAttr("disabled");
							$("#visit-role").attr("value",data.visitorRole);
						}
					}
				);
		},
		getAuthor : function(roleId){
			var current=$(this);
			if(current.is(":checked")){
				App.ajaxRequest(
						'/auth/getAuthorByrole',{'roleId':current.val()},'get',true,'json',
						function resultHandler(data){
							if(data == "error"){
								App.dialog_show("", "请求出错，请刷新或者重新登录重试！", function(){
									$(".info-dialog-close").trigger("click");
								});
							}else{
								if(data == "yes" && current.is(":checked")){
									/*if($("#job-num").val().length == 0){
										$("#job-num").attr("disabled",false);
									}
									$("#cloud_content").show();
									$("#job-num").attr("datatype","*");*/
								}
							}
						}
					);
			}else{
				App.ajaxRequest(
						'/auth/getAuthorByrole',{'roleId':current.val()},'get',true,'json',
						function resultHandler(data){
							if(data == "error"){
								App.dialog_show("", "请求出错，请刷新或者重新登录重试！", function(){
									$(".info-dialog-close").trigger("click");
								});
							}else{
								/*if(data == "yes"){
									$("#cloud_content").hide();
									$("#job-num").removeAttr("datatype");
								}*/
							}
						}
					);
			}
		},
		gerRoleInfo :  function(adminId){
			App.ajaxRequest(
				'/admin/roles',{'adminId':adminId},'get',true,'json',
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
							//注释掉云随访权限
							/*if(data.author == true){
								$(".cloud_content").show()
							}else{
								$("#job-num").removeAttr("datatype");//清掉工号非空校验
								$(".cloud_content").hide()
							}*/
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
		cloudReset : function(){
			$("#job-num").attr("value","");
			if(!$(".admin-error").hasClass("hide")){
				$(".admin-error").addClass("hide");
			}
//			if(!$("#cloudMsg").hasClass("hide")){
//				$("#cloudMsg").addClass("hide");
//			}
			//$("#cloudMsg").hide();
		},
		formReset : function(){
		 var isOpenHospitalNst=$("#isHospitalNst").attr("isOpenHospitalNst");
			$("#admin-id").val("");
			$("#name").val("");
			$("#username").val("");
			$("#password").val("");
			$("#password2").val("");
			//$("#job-num").val("");
			$("#mobile").val("");
			$("#introduction").val("");
			$(".role-radio").each(function(index, element){
        		$(this).removeAttr("checked");
        	});
			$(".belong-hospital").show();
			if(!$(".admin-error").hasClass("hide")){
				$(".admin-error").addClass("hide");
			}
			//清除工号字段非空校验
//			if(typeof($("#job-num").attr("datatype")) != "undefined"){
//				$("#job-num").removeAttr("datatype");
//			}
//			$("#cloudMsg").hide();
			//$("#cloud_content").hide();
		},
		confirmOk : function(){
			$("#edit-admin-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".admin-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息 **/
					App.validateSuccess(".admin-error");
				},
				callback:function(request, data){
					if(request.msg == -2 || request.msg == -3){
						message = request.msgBox;
						$("#cloudMsg").show();
						$("#workNum").html(message);
						if(!$(".admin-error").hasClass("hide")){
							$(".admin-error").addClass("hide");
						}
					}else if(request == "error"){
						message = "处理失败，该用户名已被占用！";
						$("#cloudMsg").show();
						$("#workNum").html(message);
						if(!$(".admin-error").hasClass("hide")){
							$(".admin-error").addClass("hide");
						}
					}else{
						$(".admin-confirm-cancel").trigger("click");
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
								message = "编辑管理员信息成功！";
							}else if(request == "failed"){
								message = "编辑管理员信息失败！";
							}else if(request == "paramsError"){
								message = "参数错误，请刷新浏览器重试！";
							}
							/*else if(request == "error"){
								message = "处理失败，该用户名已被占用！";
							}*/
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