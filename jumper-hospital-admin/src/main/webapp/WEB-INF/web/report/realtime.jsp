<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>天使医生母婴监护健康管理系统</title>
        <link rel="icon" href="${pageContext.request.contextPath}/media/image/weblogo.png">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/bootstrap.css?${v }" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/realtime.css?${v }" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery-1.10.1.min.js?${v }"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/app.js?${v }"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/leon.js?${v }"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/detail.js?${v }"></script>
        <script type="text/javascript">
        	var eArray = new Array();
    		var paramsArray = new Array();
    		
            $(function(){
            	$(".close").click(function(){
            		var userId = $(".detail-panel").attr("id");
            		Detail.clearContext(userId);
            		if(detailTimer != null){
            			clearInterval(detailTimer);
            		}
            		$(".dialog").hide();
            		$("#chatIframe").attr("src","");
            	});
            	
            	$(".cancel").click(function(){
            		var userId = $(".detail-panel").attr("id");
            		Detail.clearContext(userId);
            		if(detailTimer != null){
            			clearInterval(detailTimer);
            		}
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
           					location.reload();
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
               					Leon.clear(e);
               					$("table").find("div[user="+userId+"]").find("input.data").val(heartRate);
               					$("table").find("div[user="+userId+"]").find("input.offsetX").val(0);
               					$("table").find("div[user="+userId+"]").find("input.fetalMove").val(fetalMoveData);
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
               					/** 重置offsetX偏移量 **/
               					if(fetalRateArray.length < 480){
               						$("table").find("div[user="+userId+"]").find("input.offsetX").val(0);
               					}else{
               						if(heartRate != '' && heartRate.length > 0){
               							var oldOffsetX = $($("table").find("div[user="+userId+"]")).find("input.offsetX").val();
                   						$("table").find("div[user="+userId+"]").find("input.offsetX").val(parseInt(oldOffsetX) - heartRate.length);
               						}
               					}
               					/**
               					var oldOffsetX = $($("table").find("div[user="+userId+"]")).find("input.offsetX").val();
               					if(oldOffsetX == ''){
               						$("table").find("div[user="+userId+"]").find("input.offsetX").val(-heartRate.length);
               					}else{
               						$("table").find("div[user="+userId+"]").find("input.offsetX").val(parseInt(oldOffsetX) - heartRate.length);
               					}
               					**/
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
	    	<input type="hidden" id="hospitalName" value="${amdin.hospitalInfo.name }" />
	    	<table align="center" cellspacing="15">
	    		<tr>
	    		<c:forEach items="${page.result }" var="data" varStatus="status">
	    			<c:if test="${data != null }">
	    				<td style="width:595px">
		    				<div class="chart-div" user="${data.userId }" order="${data.id }" id="chart${status.index }" orderId="${data.orderId}" monitorTimes="${data.monitorTimes }">
		    					<ul>
				                    <li><span class="badge">${status.index + 1 }</span></li>
				                    <li style="width:10%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">${data.userName }</li>
				                    <li>${data.age }岁</li>
				                    <li>孕${data.preganyWeek }周</li>
				                    <li class="timeNow">--</li>
				                    <li class="valueNow"><span class='value-blod'>--</span>bpm</li>
				                    <li class="fetalMoveNow">--</li>
				                </ul>
		    					<div class="chart${status.index }">
		    						<input type="hidden" class="data" autocomplete="off" value="" />
		    						<input type="hidden" class="offsetX" autocomplete="off" value="0" />
		    						<input type="hidden" class="fetalMove" autocomplete="off" value="" />
						            <canvas class="oyLabel"></canvas>
						            <canvas class="contentChart"></canvas>
						            <div>
						                <canvas class="oxLabel"></canvas>
						            </div>
						        </div>
		    				</div>
		    			</td>
	    			</c:if>
	    			<c:if test="${data == null }">
	    				<td style="width:595px">
	    					<div class="chart-div" user="" order="" id="chart${status.index }">
		    					<ul>
				                    <li><span class="badge">${status.index + 1 }</span></li>
				                    <li style="width:10%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">姓名</li>
				                    <li>年龄</li>
				                    <li>孕周</li>
				                    <li class="timeNow">--</li>
				                    <li class="valueNow">--</li>
				                    <li class="fetalMoveNow">--</li>
				                </ul>
		    					<div class="chart${status.index }">
		    						<input type="hidden" class="data" autocomplete="off" value="" />
		    						<input type="hidden" class="offsetX" autocomplete="off" value="0" />
		    						<input type="hidden" class="fetalMove" autocomplete="off" value="" />
						            <canvas class="oyLabel"></canvas>
						            <canvas class="contentChart"></canvas>
						            <div>
						                <canvas class="oxLabel"></canvas>
						            </div>
						        </div>
		    				</div>
	    				</td>
	    			</c:if>
	    			<c:if test="${(status.index + 1) % 3 == 0 && status.index != 8 }">
	    				</tr><tr>
	    			</c:if>
	    		</c:forEach>
	    		</tr>
	    	</table>
	    	<c:if test="${page != null && page.result != null && page.totalCount > 0 }">
		    	<div style="margin:0px 40px">
		    		<jsp:include page="../common/page.jsp"></jsp:include>
		    	</div>
		    	<div class="clearFix"></div>
		    	<br>
	    	</c:if>
	    </form>
    </body>
</html>

<div class="dialog" style="display:none">
	<div class="modal" id="modal-detail" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="top:5%;display:block">
		<div class="modal-dialog" style="width:1300px;min-width:1300px">
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
				            </div>
				            <div class="clearFix"></div>
				        </div>
		    		</div>
		    		<iframe id="chatIframe" width="100%" height="350px" frameborder="0" src=""></iframe>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default cancel" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal-backdrop in"></div>
</div>