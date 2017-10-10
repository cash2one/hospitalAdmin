
$(function(){
	var temp_mobile = "";
	var channelWay = "";
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
	
	$("input[name='channelWay']").change(function(){
		changePayWay();
	});
	
	function changePayWay(){
		var id = $("input[name='channelWay']:checked").attr("payId");
		$("#payId").val(id);
	}
	
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
        $("#order-address").val(row.address);
        temp_mobile = row.mobile;
        
        $("#ordermobile").unbind('change').change(function(){
        	var value = $("#ordermobile").val();
        	if(temp_mobile != value){
        		$("#user-id").val("");
        		$("#order-realName").val("");
                $("#order-age").val("");
                $("#order-edc").val("");
                $("#order-address").val("");
        	}
        });
    });
	
	//院内外都需要预产期（之前院内没有预产期）
//	$("input[name=remoteOption]").live("change", function(){
//		debugger
//		var type = $(this).attr("tag");
//		if(type == 2){
//			$(".edc-div").show();
//			$("#order-edc").show();
//		}else{
//			$(".edc-div").hide();
//			$("#order-edc").hide();
//		}
//	});
	
	/** 新增用户订单弹框 **/
	$(".add-order").click(function(){
		resetForm();
		
		$(".other-options").hide();
		
		$(".order-service").removeClass("hide");
		requestMonitorService(-1);
	});
	
	/** 退费列表跳转 */
	$(".refund-list").click(function(){
		var type = $("#type").val();
		var remoteType = $("#remoteType").val();
		var expireType = $("#expireType").val();
		
		window.location.href = baseURL + "/user/payRefundAnnal?type=" + type + "&remoteType=" + remoteType + "&expireType=" + expireType;
	});
	
	/** 详情跳转 */
	$(".order-detail").click(function(){
		var id = $(this).attr("value");
		var type = $("#type").val();
		var remoteType = $("#remoteType").val();
		var expireType = $("#expireType").val();
		
		window.location.href = baseURL + "/user/orderDetail?type=" + type + "&remoteType=" + remoteType + "&expireType=" + expireType + "&orderId=" + id;
	})
	
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
								/*var items = data[i].options;
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
								}*/
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
						
						$("input[name='channelWay']:first").attr("checked", true);
						//判断金额是否为0
						$(".real-monitor, .inside-monitor").find("input[type='radio']").change(function(){
							var _this = $(this);
							if (_this.prop("checked")) {
								var value = parseFloat(_this.parent().text().split("/")[1]);
								
								if (value == 0) {
									
									$("input[name='channelWay']").parent().hide();
									$("input[name='channelWay']:last").parent().show();
									$("input[name='channelWay']:last").attr("checked", true);
								}else{
									$("input[name='channelWay']").parent().show();
									$("input[name='channelWay']:first").attr("checked", true);
								}
								changePayWay();
							}
						});
						
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
		var edc = $("#order-edc").val();
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
				if (typeof request === "string") {
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
				} else if (typeof request === "object") {
					var message = "编辑订单信息成功！";
					if(request.result == "success"){
						if (request.channel == 1 || request.channel == 2) {
							$("#payOrderId").text(request.orderId);
							$("#payOrderFee").text(request.fee);
							request.channel === 1 ? $("#payOrderChannel").html("<img width='20px' height='20px' src='"+baseURL+"/media/image/zhifubao.png'/>  支付宝支付") : $("#payOrderChannel").html("<img width='20px' height='20px' src='"+baseURL+"/media/image/weixin.png'/>  微信支付");
							
							console.info(request.qrCode);
							var qrCode = $("<img />").attr("src", baseURL+request.qrCode);
							
							$("#payOrderImg").html(qrCode);
							
							$(".order-pay-href").trigger("click");
							$("#order-pay-confrim").on("hidden.bs.modal", function(e){
//								location.reload();
							});
							
							return;
						}
						
					}else if(request.result == "failed"){
						message = "编辑订单信息失败！";
					}else if(request.result == "paramsError"){
						message = "请选择服务项目！";
					}else if(request.result == "error"){
						message = "处理异常！";
					}else if(request.result == "mobileExist"){
						message = "该手机号码已注册，请直接选择用户信息！";
					}
					
					App.dialog_show("提示！", message, function(){
						$(".info-dialog-close").trigger("click");
						if(request.result == 'success'){
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
		var channel = $(this).attr("channel");
		var remote = $(this).attr("remote");
		App.confirm_show("", "确认为"+userName+"办理"+remote+"退费？", function(){
			$(".info-confirm-cancel").trigger("click");
			App.ajaxRequest(
				'/user/backNew', {'orderId':recordId, 'channel': channel}, 'get', true, 'json',
				function resultHandler(data){
					var message = "";
					if(data == 'failed'){
						message = "退费失败！";
					}else if(data == 'success'){
						message = "退费成功！";
					}else{
						message = "该订单已有退费信息！";
					}
					App.dialog_show("提示！", message, function(){
						$(".info-dialog-close").trigger("click");
						if(data == 'success'){
							location.reload();
							return;
						}
					});
					
					setTimeout(function() {
						location.reload();
					}, 2000);
				}
			);
		});
	});
	
	$("#confirmCancel,.close").click(function(){
		location.reload();
	});
	
	/** 修改用户订单 **/
	$(".update-order").click(function(){
		$(".other-options").show();
		resetForm();
		var recordId = $(this).attr("value");
		var tag = $(this).attr("tag");
		var edc = $("#order-edc").val();
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
				//$("#order-address").attr("disabled","disabled");
				
				$(".order-service").addClass("hide");
				$(".edc-div").show();
				$(".order-service").html("");
				$(".order-confirm-title").text("监护套餐用户信息更改");
				
				$(".order-confirm-href").trigger("click");
			}
		);
		
	});

	
	function resetForm(){
		//$("#otherOpt").prop("checked", false);
		$("#order-id").val("");
		$("#user-id").val("");
		$("#ordermobile").val("");
		$("#order-realName").val("");
		$("#order-age").val("");
		$("#order-edc").val("");
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