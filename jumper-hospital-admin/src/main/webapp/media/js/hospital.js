$(function(){
	/** 修改医院信息按钮 **/
	$(".update-hospital").bind("click", hospital.updateHospital);
	/** 文件上传 **/
	$("#hospitalFile").live('change', {'element':'hospitalFile'} , App.fileUpload);
	/** 文件上传 **/
	$("#descFile").live('change', {'element':'descFile'} , App.fileUpload);
	/** 确认修改医院信息 **/
	$(".hospital-confirm-sure").bind("click", hospital.updateOk);
	/** 修改医院描述弹出框及数据加载 **/
	$(".desc-update-btn").bind("click", hospital.updateDesc);
	/** 确认修改或添加 **/
	$(".desc-confirm-sure").bind("click", hospital.editDesc);
	/** 新增医院描述 **/
	$(".desc-add").bind("click", hospital.addDesc);
	/** 删除医院描述 **/
	$(".desc-del-btn").bind("click", hospital.deleteDesc);
});

var hospital = function(){
	
	return {
		updateHospital : function(){
			var hospitalId = $("#hospitalId").val();
			var hospitalName = $("#hospitalName").val();
			var hospitalAddress = $("#hospitalAddress").val();
			var hospitalPhone = $("#hospitalPhone").val();
			var imgUrl = $("#hospitalImg").attr("value");
			var desc = $("#hospitalDesc").val();
			
			$("#dialogHospitalId").val(hospitalId);
			$("#dialogHospitalName").val(hospitalName);
			$("#dialogHospitalAddress").val(hospitalAddress);
			$("#dialogHospitalPhone").val(hospitalPhone);
			$("#dialogHospitalImg").val(imgUrl);
			$("#dialogDesc").val(desc);
			$("#dialogHospitalImgPreview").attr("src", baseFileUrl+imgUrl);
			
			$(".hospital-confirm-href").trigger("click");
		},
		updateOk : function(){
			$("#edit-hospital-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".hospital-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息 **/
					App.validateSuccess(".hospital-error");
				},
				callback:function(request, data){
					$(".hospital-confirm-cancel").trigger("click");
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var message = "";
						if(request == "success"){
							message = "修改医院信息成功！";
						}else if(request == "error"){
							message = "修改医院信息异常！";
						}else if(request == "paramsError"){
							message = "参数错误，请刷新浏览器重试！";
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
		updateDesc : function(){
			hospital.resetDescForm();
			var params = $(this).attr("value");
			if(params == ''){
				return;
			}
			var id = params.split("-")[0];
			var img = params.split("-")[1];
			var content = $(this).parent().prev().prev("p").text();
			
			$("#descId").val(id);
			$("#descContent").text(content);
			$("#descImg").val(img);
			$("#descPreview").attr("src", baseFileUrl+img);
			
			$(".desc-confirm-href").trigger("click");
		},
		addDesc : function(){
			hospital.resetDescForm();
			$(".desc-confirm-href").trigger("click");
		},
		resetDescForm : function(){
			$("#descId").val("");
			$("#descContent").text("");
			$("#descImg").val("");
			$("#descPreview").attr("src", "");
			if(!$(".desc-error").hasClass("hide")){
				$(".desc-error").addClass("hide");
			}
		},
		editDesc : function(){
			$("#edit-desc-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".desc-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息 **/
					App.validateSuccess(".desc-error");
				},
				callback:function(request, data){
					$(".desc-confirm-cancel").trigger("click");
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var message = "";
						if(request == "success"){
							message = "编辑医院描述信息成功！";
						}else if(request == "error"){
							message = "编辑医院描述信息异常！";
						}else if(request == "paramsError"){
							message = "参数错误，请刷新浏览器重试！";
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
		deleteDesc : function(){
			var id = $(this).attr("value");
			App.confirm_show("", "确认删除该条描述？", function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/hos/delete',
					{'id':id},
					'get',
					true,
					'json',
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
							if(data == 'success'){
								location.reload();
							}
						});
					}
				);
			});
		},
		checkDesc : function(element){
			var desc = $(element).val();
			if(desc.length >= 300){
				alert("医院描述不能超过300 字！");
				var result = desc.substring(0, 300);
				$(element).val(result);
			}
			if(desc.length <= 24){
				alert("医院描述不能小于24字！");
			}
			
		},
		 checkAddress : function(element){
			var desc = $(element).val();
			if(desc.length >= 50){
				//alert("医院描述不能超过300 字！");
				//var result = desc.substring(0, 50);
				//$(element).val(result);
			}
			if(desc.length <= 6){
				alert("医院描述不能小于6字！");
			}
		}
		
	}
}();