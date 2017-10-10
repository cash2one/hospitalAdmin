$().ready(function(){
	//在弹框中的表单内容提交之前，先绑定一个click事件 与后台进行交互
	$("button[data-toggle='modal']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			//清理所有已经设好的值
			$(".mid").val("");
			$(".mmloginCount").val("");
			$(".name").val("");
			$(".email").val("");
			$(".mlockTime").val("");
			$("#enabled").html("");
			$("#locked").html("");
			$(".username").val("");
			$("#password").val("");
			$("#repeat_password").val("");
			$("#hospital").html("");
			$("#role").html("");
			
			if($(this).text() == " 新增管理员"){
				App.ajaxRequest(//调用app.js中已封装好的ajax请求--传入相应参数
					"doctor/ajaxDoctors",
					null,"POST",true,"json",
					function resultHandler(data){
						console.info(data);//在前端控制台打印
						var hosps=data.hospitals;
						var roles=data.roles;
						var roleCon="";
						for(var j=0;j<roles.length;j++){
							roleCon+="<input type='checkbox' name='roleId' value="+roles[j].id+" >"+roles[j].name+"&nbsp;";
						}
						var content="<option value='0'>--- 请选择医院 ---</option>";
						for(var i=0;i<hosps.length;i++){
							content+='<option value="'+hosps[i].id+'" >'+hosps[i].name+'</option>';
						}
						console.info(roleCon);
						$("#enabled").html("<input type='radio' name='isEnabled' value='1' checked='checked'/> 是"+
              					"<input type='radio' name='isEnabled' value='0' /> 否");
						$("#locked").html("<input type='radio' name='isLocked' value='1'/> 是"+
              					"<input type='radio' name='isLocked' value='0' checked='checked' /> 否");
						$("#role").html(roleCon);
						$("#hospital").html(content);
						
					},
					function faultHander(data){
						App.dialog_show("", "请求失败，网络错误！", function(){
							$(".info-dialog-close").trigger("click");
						});
					}
				);
			}
			if($(this).text() == "修改"){
				var id = $(this).attr("value");
				App.ajaxRequest(//调用app.js中已封装好的ajax请求--传入相应参数
					"doctor/ajaxDoctors",
					"id="+id,
					"POST",true,"json",
					function resultHandler(data){
						console.info(data);//在前端控制台打印
						var admin = data.admin;
						var roles = data.roles;
						$(".mid").val(admin.id);
						$(".mmloginCount").val(admin.loginFailureCount);
						$(".name").val(admin.name);
						$(".email").val(admin.email);
						$(".mlockTime").val(admin.lockedDate);
						if(admin.isEnabled){
							var isEnabled="<input type='radio' name='isEnabled' value='1' checked='checked'> 是";
							isEnabled+="<input type='radio' name='isEnabled' value='0'> 否";
						}else{
							var isEnabled="<input type='radio' name='isEnabled' value='1'> 是";
							isEnabled+="<input type='radio' name='isEnabled' value='0' checked='checked'> 否";
						}
						$("#enabled").html(isEnabled);
						if(admin.isLocked){
							var isLocked="<input type='radio' name='isLocked' value='1' checked='checked'> 是";
							isLocked+="<input type='radio' name='isLocked' value='0'> 否";
						}else{
							var isLocked="<input type='radio' name='isLocked' value='1'> 是";
							isLocked+="<input type='radio' name='isLocked' value='0' checked='checked'> 否";
						}
						$("#locked").html(isLocked);
						$(".username").val(admin.username);
						$("#password").val(admin.password);
						$("#repeat_password").val(admin.password);
						var hosp = "<option selected='selected' value='"+admin.hospId+"'>"+admin.hospName+"</option>"
						$("#hospital").html(hosp);
						for(var m=0;m<roles.length;m++){
							$("#role").append("<input type='checkbox' name='roleId' id='"+roles[m].id+"' value='"+roles[m].id+"'>"+roles[m].name);
							for(var n=0;n<admin.roleIds.length;n++){
								if(roles[m].id == admin.roleIds[n]){
									$("#"+roles[m].id).attr("checked","checked");
								}
							}
						}
						console.info($("#role").html());
					},
					function faultHander(data){
						App.dialog_show("", "请求失败，网络错误！", function(){
							$(".info-dialog-close").trigger("click");
						});
					}
				);
			}
		
		});
	});
	/*提交表单时，判断是否满足设定好的条件*/
	$("#submit").unbind("click");
	$("#submit").bind("click",function(){
		if("" == $(".name").val()){
			App.dialog_show("温馨提示","亲，名字可别忘了填哦",function(){
				$(".info-dialog-close").trigger("click");
			});
			return false;
		}
		if("" == $(".email").val()){
			App.dialog_show("温馨提示","亲，邮箱可别忘了填而且要是有效的哦",function(){
				$(".info-dialog-close").trigger("click");
			});
			return false;
		}
		if("" == $(".username").val()){
			App.dialog_show("温馨提示","亲，请另外再取一个别名",function(){
				$(".info-dialog-close").trigger("click");
			});
			return false;
		}
		if("" == $("#password").val()){
			App.dialog_show("温馨提示","亲，密码不能为空哦",function(){
				$(".info-dialog-close").trigger("click");
			});
			return false;
		}
		if("" == $("#repeat_password").val()){
			App.dialog_show("温馨提示","亲，请务必确认一次密码",function(){
				$(".info-dialog-close").trigger("click");
			});
			return false;
		}
		if($("#repeat_password").val() != $("#password").val()){
			App.dialog_show("温馨提示","亲，两次输入的密码不一致哦，请重新输入",function(){
				$(".info-dialog-close").trigger("click");
			});
			return false;
		}
		
		if($("input[name='roleId']:checked").length<1){
			App.dialog_show("温馨提示","亲，至少要给一个角色哦",function(){
				$(".info-dialog-close").trigger("click");
			});
			return false;
		}
		if("0" == $("#hospital").val()){
			App.dialog_show("温馨提示","亲，一定要选一家医院哦",function(){
				$(".info-dialog-close").trigger("click");
			});
			return false;
		}
		return true;
	});
});
