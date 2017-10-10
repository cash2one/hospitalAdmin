$(function(){
	$(".service-ul li").on("mouseover", Service.mouseOver);
	$(".service-ul li").on("mouseout", Service.mouseOut);
	$(".option-update").on("click", Service.optionUpdate);
	$(".option-confirm-sure").on("click", Service.updateOk);
	$(".option-add").on("click", Service.optionAdd);
	$(".option-delete").on("click", Service.optionDelete);
	$(".service-close").on("click", {type:0}, Service.operateService);
	$(".service-open").on("click", {type:1}, Service.operateService);
	$(".open-hospital").on("click", Service.openMonitorHospital);
	$(".update-time").on("click", Service.updateTime);
	
	addIndex=0;
	$(".modify-time").on("click", Service.modifyTime);
	$(".add-time").on("click", Service.addColumns);
	$(".delete-time").on("click", Service.delColumns);
	$("select , #is_enabled").on("change",Service.changeSelect);
	
	//$("#returnDeadline").on("change",Service.updateReturnDeadline);
	$("#SaveReturnDeadline").on("click", Service.updateReturnDeadline);
	$(".info-confirm-cancel").on("click", Service.refresh);
	//右边的才添加提示事件
	//$("select[timeId$='_end'],#is_enabled").on("change",Service.changeSelect);
	//院内胎心,院内胎心不需要了，方案改变了
	//$("#innerTubeHeart").on("click", Service.innerTubeHeart);
	//胎心解读
	$("#fetalInterpretation").on("click", Service.fetalInterpretation);	
	
	//修改打印方式
	$("#bt_print").on("click",Service.updatePrintType);
	//保存预警上下限
	$("#bt_warning").on("click",Service.updateWarning);
	//保存短信通知和持续异常分钟数
	$("#bt_warning_message").on("click",Service.btWarningMessage);
	//勾选是否开通短信响应持续异常分钟数
	$("#checkboxId").on("click",Service.clickMessageBox);
	//打印模式初始化，
	var pt = $("#pt").val();	
	for(var i=0;i<$("input[name='printType']").size();i++){		
		if(pt == $("input[name='printType']").eq(i).val()){
			$("input[name='printType']").eq(i).attr('checked', 'checked');
		}
	} 
	//打印模式常规示例图
	$("#cg_print").on("click",Service.cgPrint);
	//打印模式曲线示例图
	$("#qx_print").on("click",Service.qxPrint);
	
	//设置评分方式
	$("#scoringMethodBtn").on("click",Service.settingScoringMethod);
	
	
});

var Service = function(){
	
	return {
		cgPrint : function(){
			$(".m1 img").css("display","block");
			$(".m2 img").css("display","none");
		},
		qxPrint : function(){
			$(".m1 img").css("display","none");
			$(".m2 img").css("display","block");
		},
		updatePrintType : function(){
			var printType = $("input[name='printType']:checked").val();
			$.ajax({
				url: url+"/rmService/updatePrintType",
				data:{"printType":printType},
				success:function(data){
					var message = "";					
					data = data.replace(/\"/g, "");					
					if(data == "failed"){
						message = "处理失败！";
					}else if(data == "success"){						
						message = "处理成功！";
					}else if(data == "paramsError"){
						message = "参数错误！";
					}				
					App.dialog_show("提示！", message, function(){
						$(".info-dialog-close").trigger("click");
						if(data == 'success'){
							location.reload();
						}
					});
					
				}
			});
		},
		//保存预警上下限
		updateWarning:function(){
			var upperLimit = $("#upperLimit").val();
			var lowerLimit = $("#lowerLimit").val();
			$.ajax({
				url: url+"/rmService/updateWarning",
				data:{
					"upperLimit":upperLimit,
					"lowerLimit":lowerLimit,
					},
				success:function(data){
					var message = "";					
					data = data.replace(/\"/g, "");					
					if(data == "failed"){
						message = "处理失败！";
					}else if(data == "success"){						
						message = "处理成功！";
					}else if(data == "paramsError"){
						message = "参数错误！";
					}			
					App.dialog_show("提示！", message, function(){
						$(".info-dialog-close").trigger("click");
						if(data == 'success'){
							location.reload();
						}
					});
					
				}
			});
		},
		//保存是否开通短信和异常持续分钟数
		btWarningMessage:function(){
			var isCheck = document.getElementById("checkboxId").checked;
			var sel_minute = $("#sel_minute option:selected").attr("value");
			$.ajax({
				url: url+"/rmService/btWarningMessage",
				data:{
					"isCheck":isCheck,
					"sel_minute":sel_minute,
					},
				success:function(data){
					var message = "";					
					data = data.replace(/\"/g, "");					
					if(data == "failed"){
						message = "处理失败！";
					}else if(data == "success"){						
						message = "处理成功！";
					}else if(data == "paramsError"){
						message = "参数错误！";
					}				
					App.dialog_show("提示！", message, function(){
						$(".info-dialog-close").trigger("click");
						if(data == 'success'){
							location.reload();
						}
					});
				}
			});
		},
		//勾选短信通知响应持续时间
		clickMessageBox:function(){
			var isCheck = document.getElementById("checkboxId").checked;
			if(isCheck == false){
				$("#sel_minute").attr("disabled",true);
				$("#sel_minute").css("background-color","#CCC");
			}else if(isCheck == true){
				$("#sel_minute").attr("disabled",false);
				$("#sel_minute").css("background-color","#FFFFFF");
			}
		},
		
		mouseOver : function(){
			$(this).children(".service-edit").show();
		},
		mouseOut : function(){
			$(this).children(".service-edit").hide();
		},
		optionUpdate : function(){
			var value = $(this).attr("value");
			var tag = $(this).attr("tag");
			var values = value.split("|");
			$("#option-id").val(values[0]);
			if(tag == 2){
				$("#option-number").val(1);
				$("#option-number").attr("readonly","readonly");
			}else{
				$("#option-number").val(values[1]);
				$("#option-number").removeAttr("readonly");
			}
			$("#option-price").val(values[2]);
			$("#setting-id").val(values[3]);
			$(".option-confirm-href").trigger("click");
		},
		optionAdd : function(){
			var value = $(this).attr("value");
			var tag = $(this).attr("tag");
			$("#option-id").val("");
			if(tag == 2){
				$("#option-number").val(1);
				$("#option-number").attr("readonly","readonly");
			}else{
				$("#option-number").val("");
				$("#option-number").removeAttr("readonly");
			}
			$("#option-price").val("");
			$("#setting-id").val(value);
			$(".option-confirm-href").trigger("click");
		},
		optionDelete : function(){
			var optionId = $(this).attr("value");
			if(optionId == ''){
				return;
			}
			App.confirm_show("", "确认删除该服务套餐？", function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/rmService/optionDelete',
					{'optionId':optionId},
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
		updateOk : function(){
			$("#add-option-form").Validform({
				/** 自定义错误消息显示 **/
				tiptype:function(msg){
					App.validateErrorInit(".option-error", msg);
				},
				showAllError:false,
				tipSweep:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					/** 表单验证成功后的回调,取消错误验证消息 **/
					App.validateSuccess(".option-error");
				},
				callback:function(request, data){
					$(".option-confirm-cancel").trigger("click");
					if(request instanceof Object){
						var loginStatus = request.getResponseHeader("loginStatus");
						if(loginStatus == "accessDenied"){
							App.noPermission();
						}
					}else{
						var message = "";
						if(request == "success"){
							message = "服务信息修改成功！";
						}else if(request == "failed"){
							message = "服务信息修改失败！";
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
		/** 开通关闭服务，0:关闭；1：开通 **/
		operateService : function(event){
			var service_id = $(this).attr("value");
			var type = event.data.type;
			var msg = "";
			if(type == 0){
				msg = "确认关闭监护服务？";
			}else{
				msg = "确认开通监护服务？";
			}
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/rmService/operate',
					{'settingId':service_id, 'operateType':type},
					'get',
					false,
					'json',
					function resultHandler(data){
						var message = "";
						if(type == 0){
							if(data == 'failed'){
								message = "服务关闭失败！";
							}else if(data == 'success'){
								message = "服务关闭成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'exist'){
								message = "当前还有胎心监护有效订单请先为用户办理退费才能关闭服务";
							}
						}else{
							if(data == 'failed'){
								message = "服务开通失败！";
							}else if(data == 'success'){
								message = "服务开通成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}
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
		openMonitorHospital : function(){
			App.ajaxRequest(
				'/rmService/hospitalAdd',
				{},
				'get',
				true,
				'json',
				function resultHandler(data){
					var message = "";
					if(data == 'failed'){
						message = "服务开通失败！";
					}else if(data == 'success'){
						message = "服务开通成功！";
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
		//老的修改排班时间
		updateTime : function(){
			var id = $(this).attr("value");
			var startDay = $("#startDay").find("option:selected").text();
			var endDay = $("#endDay").find("option:selected").text();
			var startDayValue = $("#startDay").val();
			var endDayValue = $("#endDay").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			if(startDayValue > endDayValue){
				App.dialog_show("提示！", "开始周不能大于结束周！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return;
			}
			
			var startArray = startTime.split(":");
			var endArray = endTime.split(":");
			if(Number(startArray[0]) >= Number(endArray[0])){
				App.dialog_show("提示！", "开始时间不能大于结束时间！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return;
			}
			
			var msg = "开始时间："+startDay+" - "+endDay+"  "+startTime+" - "+endTime+"，确认修改？";
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
						'/rmService/timeUpdate',
						{"id":id, "startDay":startDayValue, "endDay":endDayValue, "startTime":startTime, "endTime":endTime},
						'post',
						true,
						'json',
						function resultHandler(data){
							var message = "";
							if(data == 'failed'){
								message = "服务时间修改失败！";
							}else if(data == 'success'){
								message = "服务时间修改成功！";
							}else if(data == "paramsError"){
								message = "参数错误，请刷新浏览器重试！";
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
		
		// 新的修改排班时间     拼接数据,更新时间
		modifyTime:function(){
			var dayofweek=$(this).attr("dayofweek");
			//当前医院id
			var hospitalId=$(this).attr("hospitalid");
			//获取当前天是否选中
			var isChecked= $(this).siblings("input").attr("checked");
			var isEnabled="checked"==isChecked;
			//获取当前天的所有数据
			var getAllStateData=[];
			//当前这一条数据
			var currentItemData={};
			var currentdaySelected= $("[currentDay='"+dayofweek+"']");
			for(var i=0;i<currentdaySelected.length;i++){
				currentItemData={};
				currentItemData.dayOfWeek=dayofweek;
				currentItemData.isEnabled=isEnabled;
				var timeid= $(currentdaySelected[i]).attr("timeid").split("_")[0];//当前排班id
				if("add"==timeid){
					timeid=null;
				}
				currentItemData.id=timeid;
				var dataState=$(currentdaySelected[i]).parent().attr("datastate");//获取数据状态
				currentItemData.dataState=dataState;
				//var itemTimeStr= $(currentdaySelected[i]).find('option:selected').text();//时间字符串 
				//都是按照开始结束时间的顺序来的不用标志开始时间结束时间， 两次拼接一条数据,偶数为开始时间，基数为结束时间
				if(i%2 ==0){
					var startTime= $(currentdaySelected[i]).find('option:selected').text();//时间字符串 
					currentItemData.startTime=startTime;
					var endTime= $(currentdaySelected[i+1]).find('option:selected').text();//结束时间
					currentItemData.endTime=endTime;
					getAllStateData.push(currentItemData);
				}
			}
			//console.log(getAllStateData);
			//请求之前判断数据是否正确
			//判断是否有重复时间
			Service.changeSelect(dayofweek);
			
			var modifyWeek=$.trim($(this).siblings("div").text()).split(":")[0];
			var msg = "";
			var orderBystartTimeAsc=[];
			var orderBystartTimeAscStr="";

			//对选中的时间进行排序
			for(var i=0;i<getAllStateData.length;i++){
				orderBystartTimeAsc.push(getAllStateData[i].startTime.split(":")[0]);
			}
			//排除删除状态的数据
			var delColumns =$(this).parent().parent().children("[datastate='delete']").find("select[timeId$='_start'] option:selected ");	
			for(var i=0;i<delColumns.length;i++){
				for(var j=0;j<orderBystartTimeAsc.length;j++){
					if(orderBystartTimeAsc[j]==delColumns[i].innerHTML.split(":")[0]){
						delete orderBystartTimeAsc[j];
						break;
					}
				}
			}
			orderBystartTimeAsc.sort();
			for(var i=0;i<orderBystartTimeAsc.length;i++){
				for(var j=0;j<getAllStateData.length;j++){
					if(orderBystartTimeAsc[i]==getAllStateData[j].startTime.split(":")[0]){
						orderBystartTimeAscStr=orderBystartTimeAscStr+" "+getAllStateData[j].startTime+"-"+getAllStateData[j].endTime+" , ";
						//有重复的只能进来一次
						break;
					}

				}
			}
			
			msg=modifyWeek+":"+orderBystartTimeAscStr+"确认修改？";
			//如果没有勾选，提示勾选后生效。
			if(!isEnabled){
				msg=modifyWeek+":"+orderBystartTimeAscStr+"确认修改？勾选左侧选择框并保存修改后生效。";
			}
			//如果数据校验正确，则开始同步数据
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				
				$.ajax({
					url : url+"/rmService/modifyTime",
					type : "POST",
					dataType : "json",
					contentType: "application/json; charset=utf-8",
					async:false,
					data:JSON.stringify(getAllStateData),
					success : function(data) {
						var message = "";
						if(data == 'failed'){
							message = "服务时间修改失败！";
						}else if(data == 'success'){
							message = "服务时间修改成功！";
						}else if(data == "paramsError"){
							message = "参数错误，请刷新浏览器重试！";
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) { 
						errorTs("插入失败");
						}
					});
			});
			
		},
		//新增时间列
		addColumns:function(){
		//判断当前列是否超过三行，如果超过直接返回
		var allColumnsLength=$(this).parent().parent().children().length;
		var delColumnsLength =$(this).parent().parent().children("[datastate='delete']").length;
		if(allColumnsLength-delColumnsLength>=3){
			return;
		}
		var dayofweek=$(this).attr("dayofweek");
		var hospitalid=$(this).attr("hospitalid");
		var addStr=
			"<div timeid='add_"+addIndex+"' datastate='add' class='todayTimeInterval' style='display:inline'>" +
			 "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
				"<select currentDay='"+dayofweek+"' timeId='add_start' class='form-control' style='width:160px;display:inline'>" +
					"<option value='00:00' selected=''>00:00</option>" +
					"<option value='01:00'>01:00</option>" +
					"<option value='02:00'>02:00</option>" +
					"<option value='03:00'>03:00</option>" +
					"<option value='04:00'>04:00</option>" +
					"<option value='05:00'>05:00</option>" +
					"<option value='06:00'>06:00</option>" +
					"<option value='07:00'>07:00</option>" +
					"<option value='08:00'>08:00</option>" +
					"<option value='09:00'>09:00</option>" +
					"<option value='10:00'>10:00</option>" +
					"<option value='11:00'>11:00</option>" +
					"<option value='12:00'>12:00</option>" +
					"<option value='13:00'>13:00</option>" +
					"<option value='14:00'>14:00</option>" +
					"<option value='15:00'>15:00</option>" +
					"<option value='16:00'>16:00</option>" +
					"<option value='17:00'>17:00</option>" +
					"<option value='18:00'>18:00</option>" +
					"<option value='19:00'>19:00</option>" +
					"<option value='20:00'>20:00</option>" +
					"<option value='21:00'>21:00</option>" +
					"<option value='22:00'>22:00</option>" +
					"<option value='23:00'>23:00</option>" +
					"<option value='24:00'>24:00</option>" +
			  "</select>" +
				"&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;" +
		      "<select currentDay='"+dayofweek+"' timeId='add_end' class='form-control' style='width:160px;display:inline'>" +
		      		"<option value='00:00' selected=''>00:00</option>" +
		      		"<option value='01:00'>01:00</option>" +
		      		"<option value='02:00'>02:00</option>" +
		      		"<option value='03:00'>03:00</option>" +
		      		"<option value='04:00'>04:00</option>" +
		      		"<option value='05:00'>05:00</option>" +
		      		"<option value='06:00'>06:00</option>" +
		      		"<option value='07:00'>07:00</option>" +
		      		"<option value='08:00'>08:00</option>" +
		      		"<option value='09:00'>09:00</option>" +
		      		"<option value='10:00'>10:00</option>" +
		      		"<option value='11:00'>11:00</option>" +
		      		"<option value='12:00'>12:00</option>" +
		      		"<option value='13:00'>13:00</option>" +
		      		"<option value='14:00'>14:00</option>" +
		      		"<option value='15:00'>15:00</option>" +
		      		"<option value='16:00'>16:00</option>" +
		      		"<option value='17:00'>17:00</option>" +
		      		"<option value='18:00'>18:00</option>" +
		      		"<option value='19:00'>19:00</option>" +
		      		"<option value='20:00'>20:00</option>" +
		      		"<option value='21:00'>21:00</option>" +
		      		"<option value='22:00'>22:00</option>" +
		      		"<option value='23:00'>23:00</option>" +
		      		"<option value='24:00'>24:00</option>" +
		      	"</select>" +"&nbsp;"+
		    "<button timeId='add_"+addIndex+"' dayofweek='"+dayofweek+"' hospitalid='"+hospitalid+"' class='btn glyphicon glyphicon-minus delete-time' style='margin-left:20px'></button>"+
		   "</br>"+
		   "</div>";
		 addIndex=addIndex+1;
		 
		 $("#currentDay_"+dayofweek).append(addStr);
		 //消除事件
		 $(".delete-time").off();
		 //添加结束绑定事件
		 $(".delete-time").on("click", Service.delColumns);
		 //消除下拉框改变时间
		 $("select").off();
		 //添加下拉框改变事件,只在右边添加绑定事件
		 $("select[timeId$='_end']").on("change",Service.changeSelect);
		},
		//删除时间列
		delColumns:function(){
			var timeId=$(this).attr("timeId");
			var dayofweek=$(this).attr("dayofweek");
			var hospitalid=$(this).attr("hospitalid");
			//判断是否为新加的数据
			var isNew=timeId.indexOf("add_");
			//如果是新增的直接remove
			if(isNew==0){
				$("[timeId='"+timeId+"']").remove();
			}else{
			//否则隐藏当前div在最后的时候保存更新到数据库
				$("[timeId='"+timeId+"']").hide();
				//隐藏之后设置当前数据为删除状态
				$(this).parent().attr("datastate","delete");
			}
		},
		//判断选中的是否有重叠的时间
		changeSelect:function(dayofweek){
			var times=["00","01","02","03","04","05","06","07","08","09","10","11","12",
			           			"13","14","15","16","17","18","19","20","21","22","23","24"];
			var currentday=$(this).attr("currentday");  
			//设置为修改状态
			if($(this)){
				//判断是否为新增，为新增则不能设置为update
				var isAddState= $(this).parent().attr("datastate");
				if("add"!=isAddState){
					$(this).parent().attr("datastate","update");
				}
			}
			
			if(undefined==currentday){
				var currentday=dayofweek;  
			}
			var currentdaySelected= $("[currentday='"+currentday+"']");
			//一般第一条是开始，第二条是结束的周期循环   不用加过滤条件
			var currentTimeIdflag=0;
			var currentTimeIdArray=[];
			for(var i=0;i<currentdaySelected.length;i++){
				//alert("当前的select：       "+i);
				if(currentTimeIdflag<2){
					var itemTimeStr= $(currentdaySelected[i]).find('option:selected').text();
					//alert(itemTimeStr);
					currentTimeIdArray.push(itemTimeStr.split(":")[0]);
					if(currentTimeIdArray.length==2){
						//判断开始时间是否大于结束时间
						if(currentTimeIdArray[0]>currentTimeIdArray[1]){
							App.dialog_show("提示！", "开始时间大于结束时间,请重新输入", function(){
								$(".info-dialog-close").trigger("click");
							});
						}
						//开始比较是否有重复时间
						for(var index=parseInt(currentTimeIdArray[0]);index<parseInt(currentTimeIdArray[1]);index++){
							//如果已经占用则提示时间重复
							if(times[index].indexOf("_used")>0){
								App.dialog_show("提示！", "当前天所选时间重复，请重新选择", function(){
									$(".info-dialog-close").trigger("click");
								});
							}
							//设置为已经占用
							times[index]=times[index]+"_used";
							//alert(index+"  :  "+times[index]);
							console.log(times);
						}
					}
					currentTimeIdflag=currentTimeIdflag+1;
					//两次1循环
					if(currentTimeIdflag==2){
						currentTimeIdflag=0;
						currentTimeIdArray=[];
					}
				}
			}
		},
		//修改院内胎心状态
		innerTubeHeart:function(){
			//获取按钮的状态
			var state=$(this).attr("state");
			var  msg="";
			var stateData={};
			if(0==state){
				msg="是否要开通院内胎心权限？";
				stateData.isHospitalNstState=true;
			}
			if(1==state){
				msg="是否要关闭院内胎心权限？";
				stateData.isHospitalNstState=false;
			}
			//点击之后的提示
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				//开通院内胎心权限
				$.ajax({
					url : url+"/rmService/updateNst",
					type : "POST",
					dataType : "json",
					//contentType: "application/json; charset=utf-8",
					async:false,
					data:stateData,
					success : function(data) {
						var message = "";
						if(data == 'failed'){
							message = "操作失败！";
						}else if(data == 'success'){
							message = "操作成功！";
						}else if(date=="paramsError"){
							message = "参数错误！";
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) { 
						errorTs("插入失败");
						}
					});
				
			});
			
		},
		//修改胎心解读状态
		fetalInterpretation:function(){
			
			//获取按钮的状态
			var state=$(this).attr("state");
			var  msg="";
			var stateData={};
			if(0==state){
				msg="是否要开通胎心解读权限？";
				stateData.isDoctorNst=true;
			}
			if(1==state){
				msg="是否要关闭胎心解读权限？";
				stateData.isDoctorNst=false;
			}
			//点击之后的提示
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				//开通院内胎心权限
				$.ajax({
					url : url+"/rmService/updateNst",
					type : "POST",
					dataType : "json",
					async:false,
					data:stateData,
					success : function(data) {
						var message = "";
						if(data == 'failed'){
							message = "操作失败！";
						}else if(data == 'success'){
							message = "操作成功！";
						}else if(date=="paramsError"){
							message = "参数错误！";
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) { 
						errorTs("插入失败");
						}
					});
				
			});
			
		},
		updateReturnDeadline:function(){
			var deadlineTime=  $("#returnDeadline").find("option:selected").val();
				$.ajax({
					url : url+"/rmService/updateReturnDeadline",
					type : "POST",
					dataType : "json",
					async:false,
					data:{'returnDeadline':deadlineTime},
					success : function(data) {
						var message = "";
						if(data == 'failed'){
							message = "操作失败！";
						}else if(data == 'success'){
							message = "操作成功！";
						}else if(date=="paramsError"){
							message = "参数错误！";
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) { 
						errorTs("插入失败");
						}
					});
		},
		refresh:function(){
			location.reload();
	  },
	  //设置评分方式
	  settingScoringMethod:function(){
		  var scoringMethod = $("input[name='scoringMethod']:checked").val();
			//点击之后的提示
			App.confirm_show("", "确认修改评分方式？", function(){
				$(".info-confirm-cancel").trigger("click");
				//开通院内胎心权限
				$.ajax({
					url : url+"/rmService/settingScoringMethod",
					type : "POST",
					dataType : "json",
					async:false,
					data:{"scoringMethod":scoringMethod},
					success : function(data) {
						var message = "";
						if(data == 'failed'){
							message = "操作失败！";
						}else if(data == 'success'){
							message = "操作成功！";
						}else if(date=="paramsError"){
							message = "参数错误！";
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) { 
						errorTs("插入失败");
						}
					});
				
			});
	  }
	  
	}
}();