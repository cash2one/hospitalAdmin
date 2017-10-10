$(function(){
	/** 界面加载完成以后，根据医生绑定医生职称 **/
	var title = $(".doctor-select option:selected").attr("t");
	if(title){
		$(".doctor-title-input").val(title);
	}
	/** 常用医生按钮点击事件 **/
	$("#common_doctor_btn").bind("click", offEdit.commonDcotorDialog);
	/** 添加医生 **/
	$("#add_doctor").bind("click", offEdit.addDcotor);
	/** 选择常用医生 **/
	$(".doctor-confirm-sure").bind("click", offEdit.sureCheck);
	/** 删除模板 **/
	$(".delete_doctor").live("click", offEdit.deleteCommonDoctor);
	/** 编辑医生信息 **/
	$(".edit_doctor").live("click", offEdit.editCommonDoctor);
	/** 取消编辑医生信息 **/
	$(".cancel_doctor").live("click", offEdit.cancelDoctor);
	/** 保存常用医生信息 **/
	$(".save_doctor").live("click", offEdit.saveCommonDoctor);
	/** 选择医生下拉框监听 **/
	$(".doctor-select").bind("change", offEdit.doctorChange);
	/** 免费、付费监听 **/
	$(".cost-div input[name='costType']").bind("change", offEdit.costOperate);
	/** 上传图片 **/
	$("#course_image").live('change', {'element':'course_image'} , offEdit.fileUpload);
	/** 常用地址 **/
	$("#commonAddress").bind("click", offEdit.commonAddress);
	/** 添加常用地址信息 **/
	$("#add_address").bind("click", offEdit.addAddress);
	/** 选择地址 **/
	$(".address-confirm-sure").bind("click", offEdit.chooseAddressSure);
	/** 注意事项模板 **/
	$("#commonNotice").bind("click", offEdit.commonNotice);
	/** 添加注意事项 **/
	$("#add_notice").bind("click", offEdit.addNotice);
	/** 选择注意事项 **/
	$(".notice-confirm-sure").bind("click", offEdit.chooseNoticeSure);
	/** 删除字典内容 **/
	$(".delete_address").live("click", offEdit.deleteCommonDictionary);
	$(".delete_notice").live("click", offEdit.deleteCommonDictionary);
	/** 保存线下课程内容 **/
	$("#save_off_line").bind("click", offEdit.saveOffOk);
	/** 保存网络课程内容 **/
	$("#save_on_line").bind("click", offEdit.saveOnOk);
	/** 判断线上课程名是否出现重复相同 **/
	$("#save_on_line1").bind("click", offEdit.compareOnlineCourse);
	/** 判断添加的课程是否出现课程名相同 **/
	$("#save_off_line1").bind("click",offEdit.compareCourseName);
});

var offEdit = function(){
	return {
		commonDcotorDialog:function(){
			$(".common-doctor-href").trigger("click");
		},
		addDcotor:function(){
			var doctorName = $("#doctorName").val();
			var doctorTitle = $("#doctorTitle option:selected").val();
			if(doctorName.trim() == ''){
				alert("医生名称不能为空！");
				return;
			}
			if(doctorName.length > 20){
				alert("医生名称长度不能超过20！");
				return;
			}
			$("#add_doctor").attr({"disabled":"disabled"});
			App.ajaxRequest(
				'/school/addCommonDoctor', {'doctorName':doctorName, 'doctorTitle':doctorTitle}, 'get', true, 'json',
				function resultHandler(data){
					if(data == 'paramError'){
						alert("参数错误！医生姓名不能为空")
					}else if(data == 'failed'){
						alert("添加失败！");
					}else if(data == 'error'){
						alert("系统错误！");
					}else if(data == 'repeat'){
						alert("不能重复添加医生信息！");
					}else{
						var id = data.id;
						var name = data.doctorName;
						var title = data.doctorTitle;
						var selectContent = "<select>"+$("#doctorTitle").html()+"</select>"
						var content = "<tr><td><input type='radio' name='choose_doctor' checked /></td><td>"+name+"</td>" +
										"<td>"+title+"</td><td class='hide doctor-title-select'>"+selectContent+"</td><td>" +
										"<a href='#' value="+id+" doc='"+name+"' class='delete_doctor'>删除</a><a href='#' value="+id+" class='edit_doctor'> 编辑</a></td>" +
										"<td class='hide'><a href='#' class='cancel_doctor'>取消</a><a href='#' value='"+id+"' class='save_doctor'>保存</a></td></tr>";
						$("#common_doctor_tab").append(content);
						/** 将上次value置空 **/
						$("#doctorName").val("");
						/** 将值补到下拉框中 **/
						$(".doctor-select").append("<option value="+name+">"+name+"</option>");
					}
				}
			);
			$("#add_doctor").removeAttr("disabled");
		},
		sureCheck:function(){
			var isCheck = $("#common_doctor_tab input[name='choose_doctor']").is(":checked");
			if(!isCheck){
				alert("请先选择常用医生信息!");
				return;
			}
			var chooseElement = $("#common_doctor_tab input[name='choose_doctor']:checked");
			var name = chooseElement.parent('td').next().html();
			var title = chooseElement.parent('td').next().next().html();
			$(".doctor-select").val(name);
			$(".doctor-title-input").val(title);
			
			$(".doctor-confirm-cancel").trigger("click");
		},
		deleteCommonDoctor:function(){
			var element = $(this);
			var id = $(this).attr("value");
			var name = $(this).attr("doc");
			var isDelete = confirm("是否删除？");
			if(isDelete){
				App.ajaxRequest(
					'/school/deleteCommonDoctor', {'id':id}, 'get', true, 'json',
					function resultHandler(data){
						if(data == 'paramError'){
							alert("参数错误！医生ID不能为空");
						}else if(data == 'failed'){
							alert("添加失败！");
						}else if(data == 'error'){
							alert("系统错误！");
						}else{
							element.parent().parent().remove();
							$(".doctor-select option[value='"+name+"']").remove();
						}
					}
				);
			}
		},
		doctorChange:function(){
			var title = $(".doctor-select option:selected").attr("t");
			$(".doctor-title-input").val(title);
		},
		costOperate:function(){
			var costType = $(".cost-div input[name='costType']:checked").val();
			if(costType == 0){
				$("#cost-input").attr("disabled", "disabled");
				$("#cost-input").val(0);
			}else{
				$("#cost-input").removeAttr("disabled");
			}
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
		commonAddress:function(){
			$(".common-address-href").trigger("click");
		},
		addAddress:function(){
			var address = $("#address_input").val();
			if(address.trim() == ''){
				alert("上课地址不能为空！");
				return;
			}
			if(address.length > 30){
				alert("上课地址少于30字！");
				return;
			}
			$("#add_address").attr({"disabled":"disabled"});
			App.ajaxRequest(
				'/school/addCommonAddress', {'type':0, 'description':address}, 'get', true, 'json',
				function resultHandler(data){
					if(data == 'paramError'){
						alert("参数错误！地址信息不能为空")
					}else if(data == 'failed'){
						alert("添加失败！");
					}else if(data == 'error'){
						alert("系统错误！");
					}else if(data=='overLargest'){
						alert("添加数量不能超过5条");
					}else{
						var id = data.id;
						var name = data.description;
						var index = $("#common_address_tab tr").length + 1;
						
						var content = "<tr><td><input type='radio' name='choose_address' /></td><td>常用地址"+index+"</td>" +
										"<td>"+name+"</td><td><a href='#' value="+id+" class='delete_address'>删除</a></td></tr>";
						$("#common_address_tab").append(content);
						/** 将上次value置空 **/
						$("#address_input").val("");
					}
				}
			);
			$("#add_address").removeAttr("disabled");
		},
		chooseAddressSure:function(){
			var isCheck = $("#common_address_tab input[name='choose_address']").is(":checked");
			if(!isCheck){
				alert("请先选择常用地址信息!");
				return;
			}
			var chooseElement = $("#common_address_tab input[name='choose_address']:checked");
			var desc = chooseElement.parent('td').next().next().html();
			$("#common_address_input").val(desc);
			
			$(".address-confirm-cancel").trigger("click");
		},
		commonNotice:function(){
			$(".common-notice-href").trigger("click");
		},
		addNotice:function(){
			var notice = $("#notice_area").val();
			if(notice.trim() == ''){
				alert("注意事项信息不能为空！");
				return;
			}
			if(notice.length > 180){
				alert("注意事项信息不能超过180个字！");
				return;
			}
			$("#add_notice").attr({"disabled":"disabled"});
			App.ajaxRequest(
				'/school/addCommonAddress', {'type':1, 'description':notice}, 'get', true, 'json',
				function resultHandler(data){
					if(data == 'paramError'){
						alert("参数错误！注意事项信息不能为空");
					}else if(data == 'failed'){
						alert("添加失败！");
					}else if(data == 'error'){
						alert("系统错误！");
					}else if(data=="overLargest"){ 
						alert("注意事项不能超过5条");
					}else{
						var id = data.id;
						var name = data.description;
						var index = $("#common_notice_tab tr").length + 1;
						
						var content = "<tr><td><input type='radio' name='choose_notice' /></td><td>模板"+index+"</td>" +
										"<td>"+name+"</td><td><a href='#' value="+id+" class='delete_notice'>删除</a></td></tr>";
						$("#common_notice_tab").append(content);
						/** 将上次value置空 **/
						$("#notice_area").val("");
					}
				}
			);
			$("#add_notice").removeAttr("disabled");
		},
		chooseNoticeSure:function(){
			var isCheck = $("#common_notice_tab input[name='choose_notice']").is(":checked");
			if(!isCheck){
				alert("请先选择注意事项信息!");
				return;
			}
			var chooseElement = $("#common_notice_tab input[name='choose_notice']:checked");
			var desc = chooseElement.parent('td').next().next().html();
			$(".common_notice_area").val(desc);
			
			$(".notice-confirm-cancel").trigger("click");
		},
		deleteCommonDictionary:function(){
			var element = $(this);
			var id = $(this).attr("value");
			var isDelete = confirm("是否删除？");
			if(isDelete){
				App.ajaxRequest(
					'/school/deleteCommonDictionary', {'id':id}, 'get', true, 'json',
					function resultHandler(data){
						if(data == 'paramError'){
							alert("参数错误！医生ID不能为空")
						}else if(data == 'failed'){
							alert("添加失败！");
						}else if(data == 'error'){
							alert("系统错误！");
						}else{
							element.parent().parent().remove();
						}
					}
				);
			}
		},
		//判断课程名是否出现相同
		compareCourseName:function(){
			App.ajaxRequest(
					'/school/compareCourseName',{'courseName':$("#course_name").val(), 'courseId':$("#course_id").val()}, 'get', true, 'json',
					function resultHandler(data){
						if(data=="repeat"){
							App.confirm_show("", "已经存在相同的课程名，是否继续保存？", function(){
								$(".info-confirm-cancel").trigger("click");
								//点击提交数据表单
								$("#save_off_line").click();
							});
						}else if(data=="success"){
							//未出现课程名重复，判断修改的预约名额是否大于原名额
							var appointCount = $("#appoint-input").val();
							var oldAppointCount = $("#old-appoint").val();
							var flag = offEdit.checkAppointCount(appointCount);
							if(flag){
								$("#save_off_line").click();
							}
						}else{
							App.dialog_show("提示！", "程序出现异常", function(){
								$(".info-dialog-close").trigger("click");
								if(request == 'success'){
									window.location = baseURL+"/school/library?libraryType=0";
								}
							});
						}
					}
				);
				
		},
		saveOffOk:function(){
			$("#edit_off_line").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".off-line-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息,此处可以自定义其它验证，return false将阻止程序执行 **/
					App.validateSuccess(".off-line-error");
				},
				callback:function(request, data){
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var message = "";
						if(request == "success"){
							message = "编辑线下课程成功！";
						}else if(request == "error"){
							message = "编辑线下课程异常！";
						}else if(request == "paramsError"){
							message = "参数错误，请刷新浏览器重试！";
						}else if(request=="repeat"){
							message="课程名出现重复！";
						}else if(request == "failed"){
							message = "编辑线下课程信息失败！";
						}
						
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(request == 'success'){
								window.location = baseURL+"/school/library?libraryType=0";
							}else{
								return;
							}
						});
						setTimeout(function() {
							window.location = baseURL+"/school/library?libraryType=0";
						}, 2000);
					}
				}
			});
		},
		//判断线上课程名是否出现相同
		compareOnlineCourse:function(){
			App.ajaxRequest(
					'/school/compareOnlineCourse',{'courseName':$("#course_name").val(), 'courseId':$("#course_id").val()}, 'get', true, 'json',
					function resultHandler(data){
						if(data=="repeat"){
							App.confirm_show("", "已经存在相同的课程名，是否继续保存？", function(){
								$(".info-confirm-cancel").trigger("click");
								//点击提交数据表单
								$("#save_on_line").click();
							});
						}else if(data=="success"){
							//未出现课程名重复，则直接点击提交表单
							$("#save_on_line").click();
						}else{
							App.dialog_show("提示！", "程序出现异常", function(){
								$(".info-dialog-close").trigger("click");
								if(request == 'success'){
									window.location = baseURL+"/school/library?libraryType=1";
								}
							});
						}
					}
				);
				
		},
		saveOnOk:function(){
			$("#edit_on_line").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".on-line-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息 **/
					App.validateSuccess(".on-line-error");
				},
				callback:function(request, data){
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var message = "";
						if(request == "success"){
							message = "编辑线下课程成功！";
						}else if(request == "error"){
							message = "编辑线下课程异常！";
						}else if(request == "paramsError"){
							message = "参数错误，请刷新浏览器重试！";
						}
						
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(request == 'success'){
								window.location = baseURL+"/school/library?libraryType=1";
							}
						});
						setTimeout(function() {
							window.location = baseURL+"/school/library?libraryType=1";
						}, 2000);
					}
				}
			});
		},
		editCommonDoctor : function(){
			$(this).parent().prev().prev().hide();
			$(this).parent().prev().removeClass("hide");
			$(this).parent().hide();
			$(this).parent().next().removeClass("hide");
		},
		cancelDoctor : function(){
			$(this).parent().prev().show();
			$(this).parent().prev().prev().prev().show();
			$(this).parent().prev().prev().addClass("hide");
			$(this).parent().addClass("hide");
		},
		saveCommonDoctor : function(){
			var id = $(this).attr("value");
			var title = $(this).parent().prev().prev().children().val();
			$(this).parent().prev().prev().prev().text(title);
			App.ajaxRequest(
				'/school/editCommonDoctor', {'id':id, 'doctorTitle':title}, 'get', true, 'json',
				function resultHandler(data){
					if(data == 'paramError'){
						alert("参数错误！")
					}else if(data == 'failed'){
						alert("添加失败！");
					}else if(data == 'error'){
						alert("系统错误！");
					}else{
						$(".cancel_doctor").trigger("click");
					}
				}
			);
		},
		checkAppointCount : function(appoint){
			var oldAppoint = $("#old-appoint").val();
			if(Number(appoint) < Number(oldAppoint)){
				App.dialog_show("提示！", "预约名额不可以小于"+oldAppoint+"人", function(){
					$(".info-dialog-close").trigger("click");
				});
				setTimeout(function() {
					$(".info-dialog-close").trigger("click");
				}, 3000);
				return false;
			}
			return true;
		}
	}
}();

