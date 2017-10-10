$(function(){
	var temp_mobile = "";
	/** 初始化查询开始时间控件 **/
	$('#startTime').daterangepicker({
		singleDatePicker: true,
		format:"YYYY-MM-DD",
		startDate : new Date,
		language: 'zh-CN' //汉化
	});
	/** 初始化查询结束时间控件 **/
	$('#endTime').daterangepicker({
		singleDatePicker: true,
		format:"YYYY-MM-DD",
		startDate : new Date,
		language: 'zh-CN' //汉化
	});
	/** 初始化查询开始时间控件 **/
	$('#invalidStartTime').daterangepicker({
		singleDatePicker: true,
		format:"YYYY-MM-DD",
		startDate : new Date,
		language: 'zh-CN' //汉化
	});
	/** 初始化查询结束时间控件 **/
	$('#invalidEndTime').daterangepicker({
		singleDatePicker: true,
		format:"YYYY-MM-DD",
		startDate : new Date,
		language: 'zh-CN' //汉化
	});
	/** 预产期时间控件 **/
	$('#order-edc').daterangepicker({
		singleDatePicker: true,
		format:"YYYY-MM-DD",
		startDate : new Date,
		language: 'zh-CN' //汉化
	});
	
	/** 实时监护、常规监护标签切换 **/
	$(".remote_href").click(function(){
		$("#startTime").val("");
		$("endTime").val("");
		var type = $(this).attr("value");
		$("#remoteType").val(type);
		$("#form").submit();
	});
	
	/** 退费、消费结束标签切换 **/
	$(".expire_href").click(function(){
		$("#startTime").val("");
		$("endTime").val("");
		var type = $(this).attr("value");
		$("#expireType").val(type);
		$("#form").submit();
	});
	
	$(".time-search-btn").click(function(){
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var startDate = new Date(startTime);
		var endDate = new Date(endTime);
		if(startDate > endDate){
			App.dialog_show("", "开始时间不能大于结束时间！", function(){
				$(".info-dialog-close").trigger("click");
			});
			return;
		}
		$("#form").submit();
	});
	
	/** 输入手机号码自动补全 **/
	$("#ordermobile").autocomplete(baseURL+"/user/find",{
		dataType: "json",
        minChars:3,
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
                    result: row.name + " <" + row.mobile + ">"
                }
            });
        },
        formatItem: function(row, i, max){
        	return row.name + " <" + row.mobile + ">";
        }
    }).result(function(e, row) {
    	$("#user-id").val(row.id);
        $("#ordermobile").val(row.mobile);
        $("#order-realName").val(row.name);
        $("#order-age").val(row.age);
        $("#order-edc").val(row.edc);
        
        temp_mobile = row.mobile;
        
        $("#ordermobile").unbind('change').change(function(){
        	var value = $("#ordermobile").val();
        	if(temp_mobile != value){
        		$("#user-id").val("");
        		$("#order-realName").val("");
                $("#order-age").val("");
                $("#order-edc").val("");
        	}
        });
    });
	
/*	$("input[name=remoteOption]").live("change", function(){

		var type = $(this).attr("tag");
		if(type == 2){
			$(".edc-div").show();
			$("#order-edc").show();
		}else{
			$(".edc-div").hide();
			$("#order-edc").hide();
		}
	});*/
	
	/** 新增用户订单弹框 **/
	$(".add-order").click(function(){
		resetForm();
		$(".order-service").removeClass("hide");
		requestMonitorService(-1);
	});
	
	function requestMonitorService(optionId){
		App.ajaxRequest(
			'/user/getService',{},'get',true,'json',
			function resultHandler(data){
				/** var str = JSON.stringify(data); **/
				if(data == "error"){
					App.dialog_show("", "请求出错，请刷新或者重新登录重试！", function(){
						$(".info-dialog-close").trigger("click");
					});
				}else{
					if(data.length < 1){
						App.dialog_show("", "医院暂未开通监护服务，请先开通服务信息！", function(){
							$(".info-dialog-close").trigger("click");
						});
					}else{
						/** var str = JSON.stringify(data); **/
						for(var i = 0;i < data.length;i++){
							/** 0:常规监护，1:实时监护 **/
							if(data[i].type == 0){
								var items = data[i].options;
								if(items.length > 0){
									var content = "";
									for(var j = 0;j < items.length;j++){
										if(optionId == items[j].id){
											content+="<label class='radio-inline'><input type='radio' name='remoteOption' datatype='*' nullmsg='请选择服务项目！' tag='0' checked value='"+items[j].id+"'> "+items[j].number+"次/"+items[j].price+"元</label>";
										}else{
											content+="<label class='radio-inline'><input type='radio' name='remoteOption' datatype='*' nullmsg='请选择服务项目！' tag='0' value='"+items[j].id+"'> "+items[j].number+"次/"+items[j].price+"元</label>";
										}
									}
									$(".normal-monitor").html(content);
								}
							}else if(data[i].type == 1){
								var items = data[i].options;
								if(items.length > 0){
									var content = "";
									for(var j = 0;j < items.length;j++){
										if(optionId == items[j].id){
											content+="<label class='radio-inline'><input type='radio' name='remoteOption' datatype='*' nullmsg='请选择服务项目！' tag='1' checked value='"+items[j].id+"'> "+items[j].number+"次/"+items[j].price+"元</label>";
										}else{
											content+="<label class='radio-inline'><input type='radio' name='remoteOption' datatype='*' nullmsg='请选择服务项目！' tag='1' value='"+items[j].id+"'> "+items[j].number+"次/"+items[j].price+"元</label>";
										}
									}
									$(".real-monitor").html(content);
								}
							}else if(data[i].type == 2){
								var items = data[i].options;
								if(items.length > 0){
									var content = "";
									for(var j = 0;j < items.length;j++){
										if(optionId == items[j].id){
											content+="<label class='radio-inline'><input type='radio' name='remoteOption' datatype='*' nullmsg='请选择服务项目！' tag='2' checked value='"+items[j].id+"'> "+items[j].number+"次/"+items[j].price+"元</label>";
										}else{
											content+="<label class='radio-inline'><input type='radio' name='remoteOption' datatype='*' nullmsg='请选择服务项目！' tag='2' value='"+items[j].id+"'> "+items[j].number+"次/"+items[j].price+"元</label>";
										}
									}
									$(".inside-monitor").html(content);
								}
							}
						}
						$(".order-confirm-href").trigger("click");
					}
				}
			}
		);
	}
	
	/** 新增用户订单提交后台处理 **/
	$(".virtual_btn").on("click", function(){
		var cancel = true;
		/** 添加校验 **/
		var mUserId = $("#user-id").val();
		var realName = $("#order-realName").val();
		var age = $("#order-age").val();
		if(!isNaN(age)){
			if(mUserId.trim() == ''){
				var mobile = $("#ordermobile").val();
				if((/^1[3|4|5|7|8]\d{9}$/.test(mobile))){
					App.ajaxRequest(
						'/user/check', {'mobile':mobile,"userName":realName,"age":age}, 'get', false, 'json',
						function resultHandler(data){
							if(data.exist){
								if(!data.same){
									var flag = confirm("该手机号码已购买过订单，原用户信息与当前不符，继续添加将会覆盖原姓名、年龄等信息，是否继续？")
									if(!flag){
										cancel = false;
									}
								}
							}
						}
					);
				}
			}
		}
		if(!cancel){
			return;
		}
		$(".order-confirm-sure").trigger("click");
	});
	
	$(".order-confirm-sure").click(function(){
		$("#edit-order-form").Validform({
			/** 自定义错误消息显示 **/
			tiptype:function(msg){
				App.validateErrorInit(".order-error", msg);
			},
			showAllError:false,
			tipSweep:true,
			ajaxPost:true,
			ignoreHidden:true,
			beforeSubmit:function(curform){
				var isHidden = $("#order-edc").is(":hidden");
				if(isHidden){
					App.validateSuccess(".order-error");
					return true;
				}
				var edc = $("#order-edc").val();
				var edc_patten = /^\d{4}\-\d{2}\-\d{2}$/;
				var edc_rule = edc_patten.test(edc);
				if(!edc_rule){
					$(".order-error").text("预产期格式错误！");
					return false;
				}
				/** 表单验证成功后的回调,取消错误验证消息 **/
				App.validateSuccess(".order-error");
			},
			callback:function(request, data){
				$(".order-confirm-cancel").trigger("click");
				if(request instanceof Object){
					var loginStatus = request.getResponseHeader("loginStatus");
					if(loginStatus == "accessDenied"){
						App.noPermission();
					}
				}else{
					var message = "";
					if(request == "success"){
						message = "编辑订单信息成功！";
					}else if(request == "failed"){
						message = "编辑订单信息失败！";
					}else if(request == "paramsError"){
						message = "请选择服务项目！";
					}else if(request == "error"){
						message = "处理异常！";
					}else if(request == "mobileExist"){
						message = "该手机号码已注册，请直接选择用户信息！";
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
	});
	
	/** 用户退费 **/
	$(".back-money").click(function(){
		var recordId = $(this).attr("value");
		var userName = $(this).attr("user");
		var remote = $(this).attr("remote");
		App.confirm_show("", "确认为"+userName+"办理"+remote+"退费？", function(){
			$(".info-confirm-cancel").trigger("click");
			App.ajaxRequest(
				'/user/back', {'orderId':recordId}, 'get', true, 'json',
				function resultHandler(data){
					var message = "";
					if(data == 'failed'){
						message = "退费失败！";
					}else if(data == 'success'){
						message = "退费成功！";
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
	});
	
	$("#confirmCancel").click(function(){
		location.reload();
	});
	
	$(".close").click(function(){
		location.reload();
	});
	
	/** 修改用户订单 **/ 
	$(".update-order").click(function(){
		resetForm();
		var recordId = $(this).attr("value");
		var tag = $(this).attr("tag");
		$("#ordermobile").attr("readOnly","true");//电话号码只读
		App.ajaxRequest(
			'/user/info', {'orderId':recordId}, 'get', true, 'json',
			function resultHandler(data){
				if(data == 'paramError'){
					App.dialog_show("", "参数错误！", function(){
						$(".order-confirm-cancel").trigger("click");
						$(".info-dialog-close").trigger("click");
					});
				}
				$("#order-id").val(data.id);
				$("#user-id").val(data.monitorUserId);
				$("#ordermobile").val(data.mobile);
				$("#order-realName").val(data.realName);
				$("#order-age").val(data.age);
				$("#order-edc").val(data.edc);
				$("#order-address").val(data.address);
				
				$(".order-service").addClass("hide");
				$(".edc-div").show();
				
				$(".order-service").html("");
				$(".order-confirm-title").text("监护套餐用户信息更改");
				
				$(".order-confirm-href").trigger("click");
			}
		);
	});
	
	function resetForm(){
		$("#order-id").val("");
		$("#user-id").val("");
		$("#ordermobile").val("");
		$("#order-realName").val("");
		$("#order-age").val("");
		if(!$(".order-error").hasClass("hide")){
			$(".order-error").addClass("hide");
		}
	}
	
	$(".inside-reset").click(function(){
		var item = $(this).attr("value");
		App.confirm_show("", "确认重新监测该订单？", function(){
			$(".info-confirm-cancel").trigger("click");
			App.ajaxRequest(
				'/user/inside', {'id':item}, 'get', true, 'json',
				function resultHandler(data){
					var message = "";
					if(data == 'failed'){
						message = "处理失败！";
					}else if(data == 'success'){
						message = "处理成功！";
					}else if(data == 'error'){
						message = "服务器出错！";
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
	});
	
});