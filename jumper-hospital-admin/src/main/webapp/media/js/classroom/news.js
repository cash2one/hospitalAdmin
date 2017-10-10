$(function(){
	$(".publish_type").on("click", newsInfo.publisSwitch);
	$("#chanel-switch").on("change", newsInfo.selectSwitch);
	$("#period-switch").on("change", newsInfo.selectSwitch);
	$(".btn-banner").on("click", newsInfo.bannerSwitch);
	$(".btn-belong").on("click", newsInfo.belongSwitch);
	$("#news_save").on("click", newsInfo.saveNewsInfo);
	$("#news_save_publish").on("click", newsInfo.saveAndPublish);
	$("#save_publish_btn").on("click", newsInfo.editNewsInfoSubmit);
	/** 上传图片 **/
	$("#from_logo_url").live('change', {'element':'from_logo_url'} , newsInfo.fileUpload);
	$("#image_url").live('change', {'element':'image_url'} , newsInfo.fileUpload);
	$(".news-update").on("click", newsInfo.updateNewsInfo);
	$(".news-publish").on("click", newsInfo.publishNewsInfo);
	$(".choose-chanel-sure").on("click", newsInfo.chooseChanelSubmit);
	$(".news-banner").on("click", newsInfo.addBannerDialog);
	$(".banner-confirm-sure").on("click", newsInfo.addBannerSubmit);
	$(".news-top").on("click", newsInfo.changeNewsOrderBy);
	$(".news-delete").on("click", newsInfo.delNewsInfo);
	$(".news-push").on("click", newsInfo.pushMessage);
	$(".news_edit_cancel").on("click", newsInfo.newsSaveCancel);
	$(".cancel-publish").on("click", newsInfo.newsCancelPublish);
	
	/** 百度富文本编辑器初始化 **/
	var um = UM.getEditor('myEditor', {
	    autoHeightEnabled: false,
	    autoFloatEnabled: true
	});
});
var newsInfo = function(){
	return {
		publisSwitch : function(){
			var tag = $(this).attr("value");
			$("#publish-hidden").val(tag);
			$("#chanel-switch").val("");
			if(tag == 1){
				$("#belong-hidden").val("");
			}
			$("#form").submit();
		},
		selectSwitch : function(){
			$("#form").submit();
		},
		bannerSwitch : function(){
			var tag = $(this).attr("value");
			$("#banner-hidden").val(tag);
			$("#belong-hidden").val("");
			$("#form").submit();
		},
		belongSwitch : function(){
			var tag = $(this).attr("value");
			$("#belong-hidden").val(tag);
			$("#chanel-switch").val("");
			$("#form").submit();
		},
		saveNewsInfo : function(){
			$("#is_publish").val(0);
			newsInfo.editNewsInfoSubmit();
		},
		saveAndPublish : function(){
			App.confirm_show("提示", "确定发布文章？", function(){
				$(".info-confirm-cancel").trigger("click");
				$("#is_publish").val(1);
				$("#save_publish_btn").trigger("click");
			});
		},
		editNewsInfoSubmit : function(){
			var check_value = "";
			$($(".period-div").find("input[name='periods']:checked")).each(function(){
				if(this.checked){
					check_value+=$(this).val()+"|";
				}
			});
			if(check_value.length > 0){
				check_value = check_value.substring(0, check_value.length - 1);
				$("#period-hidden").val(check_value);
			}
			$("#edit_news").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".edit-news-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息 **/
					var hasContent = UM.getEditor('myEditor').hasContents();
					if(!hasContent){
						$(".edit-news-error").text("资讯内容不能为空！");
						return false;
					}
					App.validateSuccess(".edit-news-error");
				},
				callback:function(request, data){
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var publishType = $("#is_publish").val();
						var message = "";
						if(request == "success"){
							if(publishType == 0){
								message = "编辑课堂资讯信息成功！";
							}else{
								message = "发布成功！";
							}
						}else if(request == "error"){
							if(publishType == 0){
								message = "编辑课堂资讯信息异常！";
							}else{
								message = "发布异常！";
							}
						}else if(request == "paramsError"){
							message = "参数错误，请刷新浏览器重试！";
						}else if(request == "failed"){
							if(publishType == 0){
								message = "编辑课堂资讯信息失败！";
							}else{
								message = "发布失败！";
							}
						}
						var publish = $("#is_publish").val();
						var hospitalId = $("#belong-hidden").val();
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(request == 'success'){
								window.location = baseURL+"/classroom/news?publish="+publish+"&belong="+hospitalId;
							}else{
								return;
							}
						});
						setTimeout(function() {
							window.location = baseURL+"/classroom/news?publish="+publish+"&belong="+hospitalId;
						}, 2000);
					}
				}
			});
		},
		fileUpload : function(event){
			$.ajaxFileUpload({
	            url: baseURL+'/base/upload', 
	            type: 'post',
	            secureuri: false, //一般设置为false
	            fileElementId: event.data.element, // 上传文件的id、name属性名
	            dataType: 'json', //返回值类型，一般设置为json、application/json
	            success: function(data, status){
	            	$("#"+event.data.element).prev("input").val(data);
	            	$("#"+event.data.element).prev("input").prev("img").attr("src", baseFileUrl+data);
	            },
	            error: function(data, status, e){ 
		           	alert("图片上传失败！");
	            }
	        });
		},
		updateNewsInfo : function(){
			var id = $(this).attr("value");
			var isdia = $(this).attr("isdia");
			var belong = $("#belong-hidden").val();
			if(isdia == 0){
				location.href = baseURL+"/classroom/news_edit?id="+id+"&belong="+belong;
			}else{
				App.confirm_show("提示", "修改之后，文章将保存在医院文章列表，且用户的app端将不再显示此文章。如需再次显示，请重新发布。", function(){
					$(".info-confirm-cancel").trigger("click");
					location.href = baseURL+"/classroom/news_edit?id="+id+"&belong="+belong;
				});
			}
		},
		publishNewsInfo : function(){
			var id = $(this).attr("value");
			var belong = $("#belong-hidden").val();
			App.confirm_show("提示", "发布后，用户可在app端查看文章。确定发布？", function(){
				$(".info-confirm-cancel").trigger("click");
				if(belong == 49){
					$("#choolse_chanel_news_id").val(id);
					$(".choose-chanel-href").trigger("click");
				}else{
					App.ajaxRequest('/classroom/publish', {'id':id, 'belong':belong}, 'get', true, 'json',
						function resultHandler(data){
							var message = "";
							if(data == 'failed'){
								message = "发布失败！";
							}else if(data == 'success'){
								message = "发布成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "后台处理异常！";
							}
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
								if(data == 'success'){
									location.href = baseURL+"/classroom/news?publish=1&belong="+belong;
								}
							});
						}
					);
				}
			});
		},
		chooseChanelSubmit : function(){
			$("#choose-chanel-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".choose-chanel-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					App.validateSuccess(".choose-chanel-error");
				},
				callback:function(request, data){
					$(".choose-chanel-cancel").trigger("click");
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var message = "";
						if(request == 'failed'){
							message = "发布失败！";
						}else if(request == 'success'){
							message = "发布成功！";
						}else if(request == 'paramsError'){
							message = "参数错误！";
						}else if(request == 'error'){
							message = "后台处理异常！";
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.href = baseURL+"/classroom/news";
							}
						});
						setTimeout(function() {
							window.location = baseURL+"/classroom/news";
						}, 2000);
					}
				}
			});
		},
		addBannerDialog : function(){
			var id = $(this).attr("value");
			App.confirm_show("提示", "用户将在app端的banner栏看到您发布的资讯。您可在广告列表进行置顶，设置广告的顺序。确定设置为banner广告？", function(){
				$(".info-confirm-cancel").trigger("click");
				$("#banner-confirm-reset").trigger("click");
				$("#news_id").val(id);
				$(".add-banner-href").trigger("click");
			});
		},
		addBannerSubmit : function(){
			var periods = "";
			$("#add-banner-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".add-banner-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					var chk_value = []; 
					$('input[name="period_checkbox"]:checked').each(function(){ 
						chk_value.push($(this).val()); 
					});
					periods = chk_value.join("|");
					$("#periods").val(periods);
					App.validateSuccess(".add-banner-error");
				},
				callback:function(request, data){
					$(".banner-confirm-cancel").trigger("click");
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var message = "";
						if(request == "success"){
							message = "设置Banner广告成功！";
						}else if(request == "error"){
							message = "设置Banner广告异常！";
						}else if(request == "paramsError"){
							message = "参数错误，请刷新浏览器重试！";
						}else if(request == "failed"){
							message = "设置Banner广告失败！";
						}
						
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(request == 'success'){
								location.reload();
							}else{
								return;
							}
						});
						setTimeout(function() {
							location.reload();
						}, 2000);
					}
				}
			});
		},
		changeNewsOrderBy : function(){
			var id = $(this).attr("value");
			window.location.href = baseURL+"/classroom/top?id="+id;
		},
		delNewsInfo : function(){
			var id = $(this).attr("value");
			App.confirm_show("提示", "删除后将不能恢复。您确定要删除该文章？", function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest('/classroom/delete', {'id':id}, 'get', true, 'json',
					function resultHandler(data){
						var message = "";
						if(data == 'failed'){
							message = "删除失败！";
						}else if(data == 'success'){
							message = "删除成功！";
						}else if(data == 'paramsError'){
							message = "参数错误！";
						}else if(data == 'error'){
							message = "后台处理异常！";
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
						setTimeout(function() {
							location.reload();
						}, 2000);
					}
				);
			});
		},
		pushMessage : function(){
			var news_id = $(this).attr("value");
			App.confirm_show("提示", "消息推送后，订阅该频道的用户将收到通知。确定推送吗？", function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest('/classroom/push', {'id':news_id}, 'get', true, 'json',
					function resultHandler(data){
						var message = "";
						if(data == 'failed'){
							message = "推送失败！";
						}else if(data == 'success'){
							message = "推送成功！";
						}else if(data == 'paramsError'){
							message = "参数错误！";
						}else if(data == 'error'){
							message = "后台处理异常！";
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
						});
						setTimeout(function() {
							$(".info-dialog-close").trigger("click");
						}, 2000);
					}
				);
			});
		},
		newsSaveCancel : function(){
			App.confirm_show("提示", "您编辑的文章还未保存，确定取消保存？", function(){
				$(".info-confirm-cancel").trigger("click");
				history.go(-1);
			});
		},
		newsCancelPublish : function(){
			var id = $(this).attr("value");
			App.confirm_show("提示", "取消发布后，用户将看不到该资讯。确定取消发布？", function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest('/classroom/cancelPublish', {'id':id}, 'get', true, 'json',
					function resultHandler(data){
						var message = "";
						if(data == 'failed'){
							message = "操作失败！";
						}else if(data == 'success'){
							message = "操作成功！";
						}else if(data == 'paramsError'){
							message = "参数错误！";
						}else if(data == 'error'){
							message = "程序处理异常！";
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
						setTimeout(function() {
							$(".info-dialog-close").trigger("click");
							location.reload();
						}, 2000);
					}
				);
			});
		}
	}
}();