$(function(){
	/** 绑定月份时间切换 **/
	$(".circle-other").bind("click", schedule.monthChange);
	/** 年份切换 **/
	$("#year-select").bind("change", schedule.yearChange);
	/** 安排课程 **/
	$(".circle-btn").bind("click",{type:0} , schedule.editCourse);
	/** 已安排课程div点击 **/
	$(".course-day-plan").bind("click",{type:1} , schedule.editCourse);
	/** 选择课程按钮 **/
	$("#choose_course").bind("click", schedule.courseChoose);
	/** 搜索按钮 **/
	$("#search_submit").bind("click", schedule.searchCourse);
	/** 确认选择课程 **/
	$(".course-search-sure").bind("click", schedule.chooseSure);
	/** 添加课程信息 **/
	$("#add_course_detail").bind("click", schedule.addCourseDetail);
	/** 取消该课程 **/
	$(".course-cancel").bind("click", schedule.courseCancel);
	/** 导出PDF课程表信息 **/
	$("#export-pdf").bind("click", schedule.exportPDF);
	/** 批量缴费 **/
	$("#batch_pay").bind("click", schedule.batchPay);
	/** 批量退费 **/
	$("#batch_back").bind("click", schedule.batchBack);
	/** 批量推送 **/
	$("#batch_push").bind("click", schedule.batchPush);
	/** 缴费 **/
	$(".pay-money").bind("click", schedule.payMoney);
	/** 退款 **/
	$(".back-money").bind("click", schedule.backMoney);
	/** 推送消息 **/
	$(".push-message").bind("click", schedule.pushMessage);
	/** 消息推送确定按钮 **/
	$(".push-message-sure").bind("click", schedule.pushMessageSubmit);
	/** 导出excel表格 **/
	$("#export-excel").bind("click", schedule.exportExcel);
	/** 网络课程下架 **/
	$(".course-operate-down").bind("click", {'state' : 'down'}, schedule.onlineCourseOperate);
	/** 网络课程上架 **/
	$(".course-operate-up").bind("click", {'state' : 'up'}, schedule.onlineCourseOperate);
	/** 导出线上课程的签到情况Excel表格 **/
	$("#export-online-excel").bind("click", schedule.exportOnlineExcel);
});

function parseNum(num,str){//保留位数,字符串
	 var intnum=parseInt(str)+"";
	 var resule="";
	 if(intnum.length<num){
		 var count=num-intnum.length;
		 for(var i=0;i<count;i++){
			 resule+="0";
		 }
	 }
	 return resule+intnum;
}

var schedule = function(){
	
	return {
		monthChange : function(){
			var year = $("#year-select").val();
			var month = $(this).attr("value");
			if(month < 10){
				month = "0"+month;
			}
			var date = year+"-"+month+"-01";
			location.href = baseURL+"/schedule/offLine?date="+date;
		},
		yearChange : function(){
			var year = $("#year-select").val();
			var date = year+"-01-01";
			location.href = baseURL+"/schedule/offLine?date="+date;
		},
		editCourse : function(event){
			var type = event.data.type;
			var year = $(this).attr("y");
			var month = $(this).attr("m");
			var day = $(this).attr("d");
			var date = year+"-"+month+"-"+day;
			
			if(type == 0){
				var dateParam = new Date(year+"-"+month+"-"+Number(day)+" 23:59:59");
				var currentDate = new Date();
				if(dateParam < currentDate){
					
					var d = new Date();
					var dataNow1 = d.getFullYear()+"年"+(d.getMonth()+1)+"月"+d.getDate()+"日";//当前日期yyyy-MM-dd
					App.dialog_show("提示！", "不可以添加当前日期"+dataNow1+"之前的课程！", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
			}
			location.href = baseURL+"/schedule/editCourse?date="+date;
		},
		courseChoose : function(){
			var key = $(".course_search_key").val();
			schedule.requestCourseData(key);
			$(".course-search-a").trigger("click");
		},
		searchCourse : function(){
			var key = $(".course_search_key").val();
			schedule.requestCourseData(key);
		},
		requestCourseData : function(key){
			App.ajaxRequest(
				'/schedule/courseSearch', {'key':key}, 'get', true, 'json',
				function resultHandler(data){
					if(data == 'error'){
						alert("请求出错！");
					}else{
						var content = "";
						for(var i = 0;i < data.length;i++){
							var courseName = data[i].courseName.length > 20 ? data[i].courseName.substring(0, 20) : data[i].courseName;
							content+="<tr><td><input type='radio' name='choose_course' value='"+data[i].id+"' /></td><td>"+data[i].courseNo+"</td><td>"+courseName+"</td>"+
		  						"<td>"+data[i].courseCost+"元</td><td>"+data[i].courseDoctor+"</td><td>"+data[i].courseDoctorTitle+"</td>"+
		  						"<td><a href='"+baseURL+"/school/offEdit?recordId="+data[i].id+"&libraryType=0&operateType=0'>查看详情</a></td></tr>";
						}
						$(".search-content").html(content);
					}
				}
			);
		},
		chooseSure : function(){
			var chooseElement = $("#course_search_tab input[name='choose_course']:checked");
			var value = $(chooseElement).val();
			if(value == null){
				$(".course-search-cancel").trigger("click");
                App.dialog_show("提示！", "请先选择要安排的课程!", function(){
					$(".info-dialog-close").trigger("click");
				});
                return false;
            }
			var courseName = chooseElement.parent('td').next().next().html();
			$("#choose_course_id").val(value);
			$("#plan-course-name").text(courseName);
			$("#plan-course-name").show();
			$(".course-search-cancel").trigger("click");
		},
		addCourseDetail : function(){
			var courseDateStr = $("#date_value").val();
			var courseId = $("#choose_course_id").val();
			var startH = $(".start-h").val();
			var startM = $(".start-m").val();
			var endH = $(".end-h").val();
			var endM = $(".end-m").val();
			
			var d = new Date();
			var dataNow1 = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();//当前日期yyyy-MM-dd
			var dateValue=$("#date_value").val();//课程日期
			var dataNow=new Date(dataNow1).getTime();//当前日期时间戳
			var courseDate=new Date(dateValue+" "+startH+":"+startM).getTime();//课程日期时间戳
			if(courseDate<dataNow){
				App.dialog_show("提示！", "不可以安排"+dataNow1+"号以前的课程！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return;
			}
			
			
			if(courseId.trim() == ""){
				App.dialog_show("提示！", "请先选择课程！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return;
			}
			if(startH.trim() == "" || startM.trim() == "" || endH.trim() == "" || endM.trim() == ""){
				App.dialog_show("提示！", "上课时间不能为空！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return;
			}
			if(isNaN(startH) || isNaN(startM) || isNaN(endH) || isNaN(endM) || startH < 0 || startH > 23 
					|| startM < 0 || startM > 59 || endH < 0 || endH > 23 || endM < 0 || endM > 59){
				App.dialog_show("提示！", "上课时间格式错误！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return;
			}
			if(Number(startH) > Number(endH)){
				App.dialog_show("提示！", "开始时间不能大于结束时间！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return;
			}else if(Number(startH) == Number(endH)){
				if(Number(startM)>= Number(endM)){
					App.dialog_show("提示！", "开始时间不能大于结束时间！", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
			}
			$(".start-h").val(parseNum(2,startH));
			$(".start-m").val(parseNum(2,startM));
			$(".end-h").val(parseNum(2,endH));
			$(".end-m").val(parseNum(2,endM));
			var startTime = parseNum(2,startH)+":"+parseNum(2,startM);
			var endTime = parseNum(2,endH)+":"+parseNum(2,endM);
			App.ajaxRequest(
				'/schedule/addCourseDetail', {'date':courseDateStr, 'courseId':courseId, 'startTime':startTime, 'endTime':endTime}, 'get', true, 'json',
				function resultHandler(data){
					var message = "";
					if(data == 'paramError'){
						message = "参数错误！添加失败";
					}else if(data == 'failed'){
						message = "添加失败！";
					}else if(data == 'error'){
						message = "请求出错！";
					}else if(data == 'success'){
						message = "添加成功！";
					}else if(data=="timeOverlap"){
						message="该医生出现了课程时间上的冲突";
					}
					App.dialog_show("提示！", message, function(){
						$(".info-dialog-close").trigger("click");
						if(data == 'success'){
							location.reload();
						}
					});
				}
			);
		},
		courseCancel : function(){
			var detailId = $(this).attr("value");
			App.ajaxRequest(
					'/schedule/getAppointCount', {'courseId':detailId}, 'get', true, 'json',
					function resultHandler(data){
						var appointCount=data;//通过后台来判断预约人数
						
						if(appointCount==0){//如果预约人数等于0，则可以直接删掉该课程
							App.confirm_show("", "由于该课程没有人员预约，取消预约则直接删除", function(){
								App.ajaxRequest(
										'/schedule/courseCancel', {'id':detailId}, 'get', true, 'json',
										function resultHandler(data){
											var message = "";
											if(data == 'paramError'){
												message = "参数错误！取消失败";
											}else if(data == 'failed'){
												message = "取消失败！";
											}else if(data == 'error'){
												message = "请求出错！";
											}else if(data == 'success'){
												message = "取消成功！";
											}
											
											if(data == 'success'){
												location.reload();
											}                   
										}
									  );
							});
							
					    	
					  }else{//如果有人预约的话，则提示发送推送消息
						//查询出所有预约人的id
						App.ajaxRequest(
								'/schedule/getOppointIds', {'id':detailId}, 'get', true, 'json',
								function resultHandler(data){
									var message = "";
									if(data == 'paramError'){
										message = "参数错误！取消失败";
									}else if(data == 'failed'){
										message = "取消失败！";
									}else if(data == 'error'){
										message = "请求出错！";
									}else if(data == 'success'){
										message = "取消成功！";
									}else{
										var ids =data;
										$("#select-ids").val(ids);
										$("#course_id").val(detailId);
										$("#tips").show();
										//取消预约时，只能手机推送，取消APP推送，隐藏APP推送选项
									  $("input[name='pushType']").attr("disabled","disabled");
								
										$("#hide_app_push").hide();
										$(".push-message-href").trigger("click");
										//点击推送按钮访问该课程
									}
								}
							);
					
					    }
					}
				);
		},
		exportPDF : function(){
			var date = $("#date_value").val();
			location.href = baseURL + "/schedule/downCList?date="+date;
		},
		batchPay : function(){
			var state=true;//判断所有选项中是否有人退过费
			var ids=new Array();
			$.each($("input:checkbox[name='isCheck']:checked"),function(){
				if($(this).attr("value2")=="已缴费"){
					state=false;
				}
				ids.push($(this).val());
			});
			if(state==false){
				App.dialog_show("提示！", "请不要选择已经缴过费的选项！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return false;
			}
			if(ids.length <= 0){
				App.dialog_show("提示！", "请先选择要操作的数据！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return '';
			}
			
			App.confirm_show("", "是否缴费？", function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/schedule/batchPay',{'ids':ids.toString()}, 'post', true, 'json',
					function resultHandler(data){
						if(data == 'paramError'){
							alert("参数错误！");
						}else if(data == 'failed'){
							alert("添加失败！");
						}else if(data == 'error'){
							alert("系统错误！");
						}else{
							App.dialog_show("提示！", "缴费成功", function(){
								$(".info-dialog-close").trigger("click");
								if(request == 'success'){
									location.reload();
								}
							});
							setTimeout(function() {
								location.reload();
							}, 2000);
						}
					}
				);
			});
		},
		batchBack : function(){
			var ids=new Array();
			var state=true;//判断所有选项中是否有人退过费或者未缴费
			var valueCountState=true;//判断是否选中了免费课程
			$.each($("input:checkbox[name='isCheck']:checked"),function(){
				if($(this).attr("value2")=="已退费"||$(this).attr("value2")=='未缴费'){
					state=false;
				}
				if($(this).attr("valueCount")==0){
					valueCountState=false;
				}
				ids.push($(this).val());
			});
			if(state==false){
				App.dialog_show("提示！", "请不要选择已经退费或未缴费的选项！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return false;
			}
			if(valueCountState==false){
				App.dialog_show("提示！", "请不要选择免费课程选项的选项！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return false;
			}
			
			if(ids.length <= 0){
				App.dialog_show("提示！", "请先选择要操作的数据！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return '';
			}
			App.confirm_show("", "是否退费？", function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/schedule/batchBack',{'ids':ids.toString()}, 'post', true, 'json',
					function resultHandler(data){
						if(data == 'paramError'){
							alert("参数错误！");
						}else if(data == 'failed'){
							alert("添加失败！");
						}else if(data == 'error'){
							alert("系统错误！");
						}else{
							App.dialog_show("提示！", "退费成功", function(){
								$(".info-dialog-close").trigger("click");
								if(request == 'success'){
									location.reload();
								}
							});
							setTimeout(function() {
								location.reload();
							}, 2000);
						}
					}
				);
			});	
		},
		batchPush : function(){
			var ids=[];
			var url = "/schedule/batchPush";
			$('input[name="isCheck"]').each(function(){
				if($(this).attr("checked")){
					var val = $(this).attr("u");
					ids.push(val);
				}
			});
			var postdata = "";
			if(ids.length != 0){
				for(var i = 0; i < ids.length; i++){
					postdata += ids[i];
					if(i != ids.length - 1){
						postdata += ",";
					}
				}
			}
			if(ids.length <= 0){
				App.dialog_show("提示！", "请先选择要操作的数据！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return '';
			}
			$("#select-ids").val(postdata);
			$(".push-message-href").trigger("click");
		},
		checkHasSelectItem : function(){
			var ids=[];
			$('input[name="isCheck"]').each(function(){
				if($(this).attr("checked")){
					var val = $(this).val();
					ids.push(val);
				}
			});
			if(ids.length <= 0){
				App.dialog_show("提示！", "请先选择要操作的数据！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return ids;
			}
			if(ids.length != 0){
				var postdata = "";
				for(var i = 0; i < ids.length; i++){
					postdata += ids[i];
					if(i != ids.length - 1){
						postdata += ",";
					}
				}
				return postdata;
			}
		},
		batchRequest : function(url, ids){
			App.ajaxRequest(
				url, {'ids':ids}, 'get', true, 'json',
				function resultHandler(data){
					var message = "";
					if(data == 'paramError'){
						message = "参数错误！";
					}else if(data == 'failed'){
						message = "操作失败！";
					}else if(data == 'error'){
						message = "操作出错！";
					}else if(data == 'success'){
						message = "操作成功！";
					}
					App.dialog_show("提示！", message, function(){
						$(".info-dialog-close").trigger("click");
						if(data == 'success'){
							location.reload();
						}
					});
				}
			);
		},
		payMoney : function(){
			var payState=$(this).attr("value2");
			if(payState=="已缴费"){
				App.dialog_show("提示！", "该用户已经缴费！", function(){
					$(".info-dialog-close").trigger("click");
				});
			    return false;
			}
			var ids=new Array();
			ids.push($(this).attr("value"));	
			App.confirm_show("", "是否缴费？", function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/schedule/batchPay',{'ids':ids.toString()}, 'post', true, 'json',
					function resultHandler(data){
						if(data == 'paramError'){
							alert("参数错误！");
						}else if(data == 'failed'){
							alert("添加失败！");
						}else if(data == 'error'){
							alert("系统错误！");
						}else{
							App.dialog_show("提示！", "缴费成功", function(){
								$(".info-dialog-close").trigger("click");
								if(request == 'success'){
									location.reload();
								}
							});
							setTimeout(function() {
								location.reload();
							}, 2000);
						}
					}
				);
			});	
			
		},
		backMoney : function(){
			var payState=$(this).attr("value2");
			if(payState=="已退费"){
				App.dialog_show("提示！", "该用户已经退费！", function(){
					$(".info-dialog-close").trigger("click");
				});
			    return false;
			}

			var ids=new Array();
			ids.push($(this).attr("value"));	
			App.confirm_show("", "是否退费？", function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/schedule/batchBack',{'ids':ids.toString()}, 'post', true, 'json',
					function resultHandler(data){
						if(data == 'paramError'){
							alert("参数错误！");
						}else if(data == 'failed'){
							alert("添加失败！");
						}else if(data == 'error'){
							alert("系统错误！");
						}else{
							App.dialog_show("提示！", "退费成功", function(){
								$(".info-dialog-close").trigger("click");
								if(request == 'success'){
									location.reload();
								}
							});
							setTimeout(function() {
								location.reload();
							}, 2000);
						}
					}
				);
			});	
		},
		pushMessage : function(){
			var id = $(this).attr("value");
			$("#select-ids").val(id);
			$(".push-message-href").trigger("click");
		},
		pushMessageSubmit : function(){
			$("#push-message-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".push-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息 **/
					App.validateSuccess(".push-error");
				},
				callback:function(request, data){
					$(".push-message-cancel").trigger("click");
					$(".push-error").hide();
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var message = "";
						if(request == "success"){
							message = "消息推送成功！";
						}else if(request == "failed"){
							message = "消息推送失败！";
						}else if(request == "paramsError"){
							message = "参数错误，请刷新浏览器重试！";
						}
						
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(request == "success"){
								window.location.reload();
							}
							
						});
					}
				}
			});
		},
		exportExcel : function(){
			var detailId = $("#detailId").val();
			location.href = baseURL+"/schedule/exportExcel?detailId="+detailId;
		},
		onlineCourseOperate : function(event){
			var courseId = $(this).attr("value");
			var operate = event.data.state == 'up' ? 0 : 1;
			App.ajaxRequest(
				'/schedule/operate', {'courseId':courseId, 'state':operate}, 'get', true, 'json',
				function resultHandler(data){
					var message = "";
					if(data == 'paramError'){
						message = "参数错误！";
					}else if(data == 'failed'){
						message = "操作失败！";
					}else if(data == 'error'){
						message = "操作出错！";
					}else if(data == 'success'){
						message = "操作成功！";
					}
					App.dialog_show("提示！", message, function(){
						$(".info-dialog-close").trigger("click");
						if(data == 'success'){
							location.reload();
						}
					});
				}
			);
		},
		exportOnlineExcel : function(){
			var id = $("#courseId").val();
			location.href = baseURL+"/schedule/exportOnlineExcel?id="+id;
		}
	};
}();