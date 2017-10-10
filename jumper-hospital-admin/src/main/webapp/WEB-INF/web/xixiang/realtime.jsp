<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.jumper.hospital.utils.Const"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>天使医生母婴监护健康管理系统</title>
        <link rel="icon" href="${pageContext.request.contextPath}/media/image/weblogo.png">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/bootstrap.css?${v }" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/x_realtime.css?${v }" />
        <style type="text/css">
        	.panel-body{
        		position: relative;
        	}
        	.leftIm{
        		position: absolute;
    			top: 70px;
    			left: 1269px;
        	}
        
			
			body #modal-detail{
				overflow: inherit;
				position: absolute;
			}
			/* 弹出框样式 */
     		.bordered{
     			float: left;
			    width: 100%;
			    border-collapse: collapse !important;
			    margin-bottom: 20px;
			    border: solid #ccc 1px;
			    -moz-border-radius: 6px;
			    -webkit-border-radius: 6px;
			    border-radius: 6px;
			    -webkit-box-shadow: 0 1px 1px #ccc;
			    -moz-box-shadow: 0 1px 1px #ccc;
			    box-shadow: 0 1px 1px #ccc;
     		}
        	
        	.bordered tr{
			border: 1px solid #ccc;
			    height: 38px;
			}
			
			.bordered th{
			text-align: center;
			    border: 1px solid #ccc;
			}
			
			.bordered td{
			border: 1px solid #ccc;
			}
			.panel-default{
				overflow: hidden;
			}
			/* 设置button的样式 */
			.close1 {
				float: right;
			    font-size: 21px;
			    font-weight: bold;
			    line-height: 1;
			    color: #000;
			    text-shadow: 0 1px 0 #fff;
			    filter: alpha(opacity=20);
			    opacity: .2;
			}
			.message-dialog {width:596px !important; }
        </style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery-1.10.1.min.js?${v }"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/app.js?${v }"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/x_leon.js?${v }"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/x_detail.js?${v }"></script>
        <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/warning.js?${v }"></script> --%>
        <script type="text/javascript">
        
	     	// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	        var curWwwPath = window.document.location.href;
	        // 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	        var pathName = window.document.location.pathname;
	        var pos = curWwwPath.indexOf(pathName);
	        // 获取主机地址，如： http://localhost:8083
	        var localhostPaht = curWwwPath.substring(0, pos);
	        // 获取带"/"的项目名，如：/uimcardprj
	        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	        console.log(pathName);
	        /* var url ="http://localhost:8081/common"; */
	        var url = localhostPaht + projectName;
        	var eArray = new Array();
    		var paramsArray = new Array();
    		var datail=false;
            $(function(){
            	$(".close").click(function(){
            		var userId = $(".detail-panel").attr("id");
            		Detail.clearContext(userId);
            		if(detailTimer != null){
            			clearInterval(detailTimer);
            		}
            		window.location.reload();
					$("#datailstop").val(false);
            		$(".dialog").hide();
            		$("#chatIframe").attr("src","");
            	});
            	
            	$(".cancel").click(function(){
            		var userId = $(".detail-panel").attr("id");
            		Detail.clearContext(userId);
            		if(detailTimer != null){
            			clearInterval(detailTimer);
            		}
            		window.location.reload();
					$("#datailstop").val(false);
            		$(".dialog").hide();
            	});
            	
            	/** 初始化所有Chart **/
            	$(".chart-div").each(function(index, element){
            		var element = $(this).attr("id");
            		var userId = $(this).attr("user");
            		var orderId = $(this).attr("order");
            		if(userId != '' && orderId != ''){
            			eArray.push(element);
                		paramsArray.push("{user_id:"+userId+",order_id:"+orderId+",index:0}");
            		}
            		if(userId == ''){
            			Leon.init(element, 0);
            		}else{
            			Leon.init(element, userId);
            		}
            	});
            	//预警记录点击事件
            	$(".warning_info , .badge-danger").click(function(){
            		$(".waring_class").nextAll().remove();
            		$("#warning-confirm").show(); 
            		var userId = $(this).attr("userId");
            		var hospitalId= $(this).attr("hospitalId");
            		var monitorOrderId= $(this).attr("monitorOrderId");
            		var addTime = $(this).attr("start_time");
            	 /* alert("用户Id:" + userId +"医院id:"+hospitalId+"monitorOrderIdid:"+id);  */
            		$.ajax({
        				 url : url + "/report/getRealTimeWarnInfo", 
        				type : "POST",
        				dataType : "json",
        				data : {
        					"userId": userId,
        					"monitorOrderId": monitorOrderId,
        					"hospitalId": hospitalId,
        					"addTime": addTime 
        				},
        				success : function(data) {
        					var oDate = JSON.parse(data);
        					var a = oDate.data;
        					//js加载数据
       					    for (var i = 0; i < a.length ; i++) {
           						var id = a[i].id;//序号
           						var date = a[i].addTime;//添加日期
           						var addDate = date.substring(0, 10);//添加日期
           						var addTime = date.substring(11, date.length);//添加时间
           						var type = a[i].type;//报警类型
           						if(type == 1){
           							type="胎心率";
           						}
           						var alarmValue = a[i].alarmValue;//报警值
           						var lowerLimit = a[i].lowerLimit;//下限
           						var upperLimit = a[i].upperLimit;//上限
           					    $(".waring_class").after('<tr><td>'+(Math.abs(i - a.length ))+'</td><td>'+addDate+'</td><td>'+addTime+'</td><td>'+type+'</td><td>'+alarmValue+'</td><td>'+upperLimit+'</td><td>'+lowerLimit+'</td></tr>'); 
           					    $(".warning-confirm").show();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
        					}
        						return;
        					}
        				}
        			);
            	 
            		
            	});
            	//刷新页面
        		$(".close1").click(function(){
        			window.location.reload(); 
        		});
            	/**
            	* 获得参数以后，第一次请求，获取历史数据
            	**/
            	try {
               		var hospitalId = $("#hospitalId").val();
               		App.ajaxDefineRequest(realTimeRequestUrl, {"hospital_id" : hospitalId, "cmd_type" : "getAllData", "data" : JSON.stringify(paramsArray)},
               		"get", true, "json", 
               		function resultHandler(data){
               			var status = data.status;
               			var message = data.status_msg;
               			var result = data.data;
               			var dangerArray = new Array();
               			if(status == 1 && result.length > 0){
               				for(var i = 0;i < result.length;i++){
                   				var userId = result[i].user_id;
                   				var heartRate = result[i].heart_rate;
                   				var fetalMoveData = result[i].fetal_move;
                   				var extraParam = result[i].extra_param;
                   				var isAlarm = result[i].isAlarm;
                   				var uterusData = result[i].uterus;
                   				
                   				var starttime = result[i].start_time;
                   				$("table").find("div[user="+userId+"]").find("span.badge").attr("start_time",starttime);
                   				
                   				if(isAlarm != '' && isAlarm == 1){
                      					$("table").find("div[user="+userId+"]").find("span.badge").attr("class", "badge-danger");
                      					var dangerIndex = $("table").find("div[user="+userId+"]").find("span.badge-danger").text();
                      					dangerArray.push(dangerIndex);
                      				}
                   				
                   				var e = $("table").find("div[user="+userId+"]").attr("id");
                   				/** 表示要重测 **/
                   				if(extraParam == 'retest'){
                   					Leon.clear(e);
                   					Leon.init(e, userId);
                   				}else{
                   					/** 不用重测，重置Data要显示的数据 **/
                   					var oldData = $("table").find("div[user="+userId+"]").find("input.data").val();
                   					if(oldData == ''){
                   						$("table").find("div[user="+userId+"]").find("input.data").val(heartRate);
                   					}else{
                   						$("table").find("div[user="+userId+"]").find("input.data").val(oldData+","+heartRate);
                   					}
                   					var oldUterusData = $("table").find("div[user="+userId+"]").find("input.uterusData").val();
                   					if(oldUterusData == ''){
                   						$("table").find("div[user="+userId+"]").find("div.no_toco").show();
                   						$("table").find("div[user="+userId+"]").find("input.uterusData").val(uterusData);
                   					}else{
                   						$("table").find("div[user="+userId+"]").find("div.no_toco").hide();
                   						$("table").find("div[user="+userId+"]").find("input.uterusData").val(oldUterusData+","+uterusData);
                   					}
                   					/** 初始化胎动数据 **/
                   					var fetalMove = $("table").find("div[user="+userId+"]").find("input.fetalMove").val();
                   					if(fetalMove == ''){
                   						$("table").find("div[user="+userId+"]").find("input.fetalMove").val(fetalMoveData);
                   					}else{
                   						if(fetalMoveData != null && fetalMoveData != ''){
                							$("table").find("div[user="+userId+"]").find("input.fetalMove").val(fetalMove+","+fetalMoveData);
                						}
                   					}
                   					/** 重绘 **/
                   					Leon.draw(e);
                   				}
                   			}
               			}
               			randerDangerUser(dangerArray);
               			getCurrentData();
               		},
               		function faultHander(data){
               			//alert("服务器异常！");
               			getCurrentData();
               		});
				} catch (e) {
					alert(e.message);
					getCurrentData();
				}
				
            });
            
            function getCurrentData(){
				var datail=$("#datailstop").val();
				if(datail!="false"){
					return ;
				}
            	var hospitalId = $("#hospitalId").val();
            	var currentArray = new Array();
            	$(".chart-div").each(function(index, element){
            		var element = $(this).attr("id");
            		var userId = $(this).attr("user");
            		var orderId = $(this).attr("order");
            		var heartData = $(this).find("input.data").val();
            		var index = 0;
            		if(heartData != ''){
            			index = heartData.split(",").length - 1;
            		}
            		if(userId != '' && orderId != ''){
            			currentArray.push("{user_id:"+userId+",order_id:"+orderId+",index:"+index+"}");
            		}
            	});
            	
          		App.ajaxDefineRequest(realTimeRequestUrl, {"hospital_id" : hospitalId, "cmd_type" : "getCurrData", "data" : JSON.stringify(currentArray)},
                "get", true, "json", 
           		function resultHandler(data){
           			var status = data.status;
           			var message = data.status_msg;
           			var result = data.data;
           			var isNewUser = data.isNewUser;
           			
           			if(isNewUser == 1){
           				var sound = new Audio();
           				sound.setAttribute("src", servicePath+"/media/sound/1.mp3");
           				sound.play();
           				setTimeout(function(){
           					location.reload(true);
           				}, 1000);
       					return;
       				}
           			
           			var dangerArray = new Array();
           			if(status == 1 && result.length > 0){
           				/** 这里防止多用户异常，多次提示音问题 **/
                    	var isDangering = false;
           				for(var i = 0;i < result.length;i++){
               				var userId = result[i].user_id;
               				var heartRate = result[i].heart_rate.split(",");
               				var fetalMoveData = result[i].fetal_move;
               				var extraParam = result[i].extra_param;
               				var isAlarm = result[i].isAlarm;
               				var uterusData = result[i].uterus;
               				
               				var isDanger = false;
               				if(isAlarm != '' && isAlarm == 1){
               					$("table").find("div[user="+userId+"]").find("span.badge").attr("class", "badge-danger");
               					var dangerIndex = $("table").find("div[user="+userId+"]").find("span.badge-danger").text();
               					dangerArray.push(dangerIndex);
               					/** 预警提示音 **/
               					if(!isDangering){
               						var danger = new Audio();
                   					danger.setAttribute("src", servicePath+"/media/sound/warning.mp3");
                   					danger.play();
                   					isDangering = true;
               					}
               				}else if(isAlarm == 0){
               					$("table").find("div[user="+userId+"]").find("span.badge-danger").attr("class","badge");
               				}
               				
               				var e = $("table").find("div[user="+userId+"]").attr("id");
               				/** 表示要重测 **/
               				if(extraParam == 'retest'){
               					window.location.reload(); 
               					Leon.clear(e);
               					$("table").find("div[user="+userId+"]").find("input.data").val(heartRate);
               					$("table").find("div[user="+userId+"]").find("input.offsetX").val(0);
               					$("table").find("div[user="+userId+"]").find("input.fetalMove").val(fetalMoveData);
               					$("table").find("div[user="+userId+"]").find("input.uterusData").val(uterusData);
               					Leon.init(e, userId);
               				}else{
               					/** 不用重测，重置Data要显示的数据 **/
               					var oldData = $("table").find("div[user="+userId+"]").find("input.data").val();
               					var fetalRateArray = oldData.split(",");
               					if(oldData == ''){
               						$("table").find("div[user="+userId+"]").find("input.data").val(heartRate);
               					}else{
               						if(heartRate != '' && heartRate.length > 0){
               							$("table").find("div[user="+userId+"]").find("input.data").val(oldData+","+heartRate);
               						}
               					}
               					var oldUterusData = $("table").find("div[user="+userId+"]").find("input.uterusData").val();
               					if(oldUterusData == ''){
               						$("table").find("div[user="+userId+"]").find("div.no_toco").show();
               						$("table").find("div[user="+userId+"]").find("input.uterusData").val(uterusData);
               					}else{
               						if(uterusData != '' && uterusData.length > 0){
               							$("table").find("div[user="+userId+"]").find("div.no_toco").hide();
               							$("table").find("div[user="+userId+"]").find("input.uterusData").val(oldUterusData+","+uterusData);
               						}
               					}
               					/** 重置offsetX偏移量 **/
               					if(fetalRateArray.length < 480){
               						$("table").find("div[user="+userId+"]").find("input.offsetX").val(0);
               					}else{
               						if(heartRate != '' && heartRate.length > 0){
               							var oldOffsetX = $($("table").find("div[user="+userId+"]")).find("input.offsetX").val();
                   						$("table").find("div[user="+userId+"]").find("input.offsetX").val(parseInt(oldOffsetX) - heartRate.length);
               						}
               					}
               					/** 重置胎动数据 **/
               					var fetalMove = $("table").find("div[user="+userId+"]").find("input.fetalMove").val();
            					if(fetalMove == '' && fetalMoveData != ''){
            						$("table").find("div[user="+userId+"]").find("input.fetalMove").val(fetalMoveData);
            					}else{
            						if(fetalMoveData != null && fetalMoveData != ''){
            							if(fetalMove != fetalMoveData){
            								$("table").find("div[user="+userId+"]").find("input.fetalMove").val(fetalMoveData);
            							}
            						}
            					}
               					/** 重绘 **/
               					Leon.draw(e);
               				}
               			}
           			}
           			randerDangerUser(dangerArray);
           			getCurrentData();
           		},
           		function faultHander(data){
           			//alert("服务器异常！");
           			getCurrentData();
           		});
            }
            
            function randerDangerUser(dangerArray){
            	if(dangerArray != '' && dangerArray.length > 0){
            		var content = "";
            		for(var i = 0; i < dangerArray.length; i++){
            			content+="<li><span class='badge-danger'>"+dangerArray[i]+"</span></li>";
            		}
            		$(".danger-ul").html(content);
            	}else{
            		$(".danger-ul").html("");
            	}
            }
            
        </script>
    </head>
    <body>
	    <div class="danger-user">
	    	<div style="padding: 5px 5px;background-color: #eee;">异常用户</div>
	    	<ul class="danger-ul"></ul>
	    </div>
    	<form action="${pageContext.request.contextPath}/report/realTime" method="POST" id="form">
	    	<input type="hidden" id="hospitalId" value="${hospitalInfo.id }" />
	    	<input type="hidden" id="userId" value="${data.userId  }" />
	    	<input type="hidden" id="hospitalName" value="${hospitalInfo.name }" />
			<input type="hidden" id="datailstop" value="false" />
	    	<table align="center" cellspacing="15">
	    		<tr>
	    		<c:forEach items="${page.result }" var="data" varStatus="status">
	    			<c:if test="${data != null }">
	    				<td style="width:595px">
		    				<div class="chart-div" user="${data.userId }" order="${data.id }" id="chart${status.index }" orderId="${data.orderId}"
		    				 questionId="${data.questionId}"  monitorTimes="${data.monitorTimes }">
		    					<ul>
		    						<!-- 下面的设置只针对义务妇幼显示设置 -->
		    						<!-- 如果是义乌妇幼就显示并且可点击 -->
		    						<c:set var="YWFY_HOSPITAL_ID" value="<%=Const.YWHOSPITAL_ID %>"/>  
				   					<c:if test="${hospitalInfo.id == YWFY_HOSPITAL_ID}">
				                    	<li style="width:6%"><span class="badge warning_info" userId="${data.userId }" monitorOrderId="${data.monitorOrderId }" hospitalId="${hospitalInfo.id}">${status.index + 1 }</span></li>
				                    </c:if>
				                    <!-- 如果不是义乌妇幼显示不可点击 -->
				                    <c:if test="${hospitalInfo.id != YWFY_HOSPITAL_ID}">
				                    	<li style="width:6%"><span class="badge">${status.index + 1 }</span></li>
				                    </c:if>
				                    <li style="width:6%">${fn:substring(data.remoteType, 0, 2) }</li>
				                    <li style="width:10%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">${data.userName }</li>
				                    <li style="width:6%">${data.age }岁</li>
				                    <li style="width:10%">孕${data.preganyWeek }周</li>
				                    <li style="width:20%">手机:${data.mobile }</li>
				                    <li class="timeNow">--</li>
				                    <li class="valueNow"><span class='value-blod'>--</span>bpm</li>
				                    <li class="fetalMoveNow">--</li>
				                </ul>
		    					<div class="chart${status.index }">
		    						<input type="hidden" class="data" autocomplete="off" value="" />
		    						<input type="hidden" class="offsetX" autocomplete="off" value="0" />
		    						<input type="hidden" class="fetalMove" autocomplete="off" value="" />
		    						<input type="hidden" class="uterusData" autocomplete="off" value="" />
						            <canvas class="oyLabel"></canvas>
						            <canvas class="contentChart"></canvas>
						            <div>
						                <canvas class="oxLabel"></canvas>
						            </div>
						            <div class="no_toco" style="color:red;display:none">用户未上传宫缩数据</div>
						            <canvas class="uterusYLabel"></canvas>
			                		<canvas class="uterusChart"></canvas>
						        </div>
		    				</div>
		    			</td>
	    			</c:if>
	    			<c:if test="${data == null }">
	    				<td style="width:595px">
	    					<div class="chart-div" user="${data.userId }" order="" id="chart${status.index }">
		    					<ul>
		    						<%-- <c:set var="YWFY_HOSPITAL_ID" value="<%=Const.YWHOSPITAL_ID %>"/>  
				   					<c:if test="${hospitalInfo.id == YWFY_HOSPITAL_ID}">
				                 	  <li style="width:6%"><span class="badge warning_info" userId="12263" monitorOrderId="4098" hospitalId="8569">${status.index + 1 }</span></li>
				                    </c:if>
				                    <c:if test="${hospitalInfo.id != YWFY_HOSPITAL_ID}">
				                 	  <li style="width:6%"><span class="badge">${status.index + 1 }</span></li>
				                    </c:if> --%>
				                  	<li style="width:6%"><span class="badge">${status.index + 1 }</span></li>
				                    <li style="width:10%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">姓名</li>
				                    <li style="width:6%">年龄</li>
				                    <li style="width:10%">孕周</li>
				                    <li style="width:20%">手机:</li>
				                    <li class="timeNow">--</li>
				                    <li class="valueNow">--</li>
				                    <li class="fetalMoveNow">--</li>
				                </ul>
		    					<div class="chart${status.index }">
		    						<input type="hidden" class="data" autocomplete="off" value="" />
		    						<input type="hidden" class="offsetX" autocomplete="off" value="0" />
		    						<input type="hidden" class="fetalMove" autocomplete="off" value="" />
		    						<input type="hidden" class="uterusData" autocomplete="off" value="" />
						            <canvas class="oyLabel"></canvas>
						            <canvas class="contentChart" id="${status.index}"></canvas>
						            <div>
						                <canvas class="oxLabel"></canvas>
						            </div>
						            <div class="no_toco" style="color:red;display:none">用户未上传宫缩数据</div>
						            <canvas class="uterusYLabel"></canvas>
			                		<canvas class="uterusChart"></canvas>
						        </div>
		    				</div>
	    				</td>
	    			</c:if>
	    			<c:if test="${(status.index + 1) % 3 == 0 && status.index != 5 }">
	    				</tr><tr>
	    			</c:if>
	    		</c:forEach>
	    		</tr>
	    	</table>
	    	<c:if test="${page != null && page.result != null && page.totalCount > 0 }">
		    	<div style="margin:0px 40px">
		    		<jsp:include page="../common/page.jsp"></jsp:include>
		    	<%-- 	<jsp:include page="../hospital/warning_dialog.jsp"></jsp:include> --%>
		    	</div>
		    	<div class="clearFix"></div>
		    	<br>
	    	</c:if>
	    </form>
	    

<!-- 预警记录dialog -->
<!-- <a class="btn btn-pink hide warning-confirm-href" data-toggle="modal"  data-target="#warning-confirm"></a> -->

<div class="modal" id="warning-confirm" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="margin-top: -120px;height:anto" data-toggle="modal"data-backdrop="static">
	<div class="modal-dialog" style="width: 1000px;height:500px; overflow: auto;">
		<form action="" method="POST" id="form">
    	<div class="panel panel-default">
            <div class="clearFix"></div>
          <button type="button" class="close1" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
        		<h4>预警列表</h4>
        		<div align="right" class="clearFix"></div>
	            <table class="detail_center bordered">
	                <thead align="center">  
	                    <tr class="waring_class">
	                        <th>序号</th>
	                        <th>日期</th>
	                        <th>时间</th>
	                        <th>报警类型</th>
	                        <th>报警值</th>
	                        <th>上限</th>
	                        <th>下限</th>
	                    </tr>
	                </thead>
	            </table>
	        </div>
	    </div>
	</form>
	</div>
	
	
	
	<!--  
	<div class="modal" id="warning-confirm" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="margin-top: -120px;height:anto" data-backdrop="static">
	<div class="modal-dialog" style="width: 1000px;height:500px; overflow: auto;">
		<form action="" method="POST" id="form">
    	<div class="panel panel-default">
            <div class="clearFix"></div>
            <div>
           		<button type="button" class="close1" data-dismiss="modal" aria-label="Close" style="float:right;"><span aria-hidden="true">&times;</span></button> 
          	</div>
        		<h4>预警列表</h4>
        		<div align="right" class="clearFix"></div>
	            <table class="detail_center bordered">
	                <thead align="center">  
	                    <tr class="waring_class">
	                        <th>序号</th>
	                        <th>日期</th>
	                        <th>时间</th>
	                        <th>报警类型</th>
	                        <th>报警值</th>
	                        <th>上限</th>
	                        <th>下限</th>
	                    </tr>
	                </thead>
	            </table>
	        </div>
	    </div>
	</form>
	</div>
	-->
    </body>
</html>


<div class="dialog" style="display:none">
	<div class="modal" id="modal-detail" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="top:0;display:block">
		<div class="modal-dialog" style="width:1880px;min-width:1880px">
			<div class="modal-content">
				<div class="modal-header" style="border-bottom:none">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		      	</div>
				<div class="modal-body order-confirm-body">
					<div class="panel panel-default" id="panel">
				        <div class="panel-body">
				            <div class="panel-head">
				                <ul>
				                    <li>1</li>
				                    <li>张三</li>
				                    <li>22岁</li>
				                    <li>孕10周</li>
				                    <li class="timeNow">--</li>
				                    <li class="valueNow">--</li>
				                </ul>
				            </div>
				            <div class="clearFix"></div>
				            <div class="detail-panel" id="" style="text-align: center;background-color: #FFF;border:none;width:1250px">
				            	<input type="hidden" class="offsetX" value="0" />
				                <canvas class="oyLabel"></canvas>
				                <canvas class="contentChart"></canvas>
				                <div>
				                    <canvas class="oxLabel"></canvas>
				                </div>
				                <div class="dialog_no_toco" style="color:red;display:none;text-align:left">用户未上传宫缩数据</div>
				                <canvas class="uterusYLabel"></canvas>
			                	<canvas class="uterusChart"></canvas>
			                	<div class="leftIm">
			                	<iframe id="chatIframe" width="600px" height="600px" frameborder="0" src=""></iframe>
			                	</div>
				            </div>
				            <div class="clearFix"></div>
				        </div>
		    		</div>
		    		<!-- <iframe id="chatIframe" width="100%" height="280px" frameborder="0" src=""></iframe> -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default cancel" data-dismiss="modal" id="close">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-backdrop in"></div>
