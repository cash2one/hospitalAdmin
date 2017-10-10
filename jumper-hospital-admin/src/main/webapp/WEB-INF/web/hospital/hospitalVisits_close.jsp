<%@page import="com.jumper.hospital.enums.Week"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  <!-- 页头引入 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/hospital.css?${v }">
<link href="${pageContext.request.contextPath}/media/css/service.css" rel="stylesheet">
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/hospitalVisit.js"></script> --%>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script> --%>
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>

<script type="text/javascript">
		window.onload=function(){
		var status1 = $("#status").val();
		
		if(status1==0){
			$("#visitStatus1").text("已关闭");
			//document.getElementById("editMoney").style.display="none";
			document.getElementById("editTime").style.display="none";
			document.getElementById("editMajor").style.display="none";
			
		}else if(status1==1){
			$("#visitStatus1").text("已开通");
			document.getElementById("editMoney").style.display="inline";
			document.getElementById("editTime").style.display="inline";
			document.getElementById("editMajor").style.display="inline";
		
		}
		
		/* var majors = $("#majors").val();
		var majorList = document.getElementsByName("major");
		var majorInt = majors.split(",");
		for(var i=0;i<majorInt.length;i++){
			for(var j=0;j<majorList.length;j++){
				if(majorInt[i]==majorList[j].value){
					$("input:checkbox").eq(j).attr("checked","true");
				}
			}
		} */
		
	};
	$(function(){
		var beginTime = $("#begin").val();
		var endTime = $("#end").val();
		
		$("select[name='begin_Time'] option").each(function(){
			if($(this).text()==beginTime){
				$(this).attr("selected","selected");
			}
		});
		$("select[name='end_Time'] option").each(function(){
			if($(this).text()==endTime){
				$(this).attr("selected","selected");
			}
		});
		$("#ok").click(function(){
			if($("#money").val()!=null&&$("#money").val()!=""&&$("#money").val().trim().length!=0){
				var money = parseFloat($("#money").val());
				
			}else{
				var money = 0;
			}
			$("#myMoney").innerText = money+" 元/次";
				$.post(
					baseURL+"/hospitalVisit/editMoney",
					{cost:money},
					function(data){
						if(data=="success"){
							App.dialog_show("提示！", "金额修改成功！", function(){
								$(".info-dialog-close").trigger("click");
								window.location.reload();
							});
						}else{
							App.dialog_show("提示！", "金额修改失败！", function(){
								$(".info-dialog-close").trigger("click");
							});
						}
					}
				);
		});
		
		$("#editMoney").click(function(){
			$("#money").val($("#editMoney").val());
		});
		
		$("#week_high").change(function(){
			var weeklow = $("#week_low").val();
			var weekhigh = $("#week_high").val();
			if(weeklow>weekhigh){
				App.dialog_show("提示！", "您的开通日期设置不正确！", function(){
					$(".info-dialog-close").trigger("click");
				});
			}
		});
		
		//点击关闭服务，将页面内容设置为不可点击（按钮变灰）,开通状态设置为0
		$("#closeBut").click(function(){
			$.post(
				baseURL+"/hospitalVisit/closeVisit",
				//"/JumperRemoteAdmin/hospitalVisit/closeVisit",
				function(data){
					var datas = eval("("+data+")");
					if(datas.msg=="success"){
						App.dialog_show("提示！", "服务已关闭！", function(){
							$(".info-dialog-close").trigger("click");
							$("#status").val(0);
							window.location.reload();
						});
						$("#visitStatus1").text("已关闭");
						//window.location.reload();
						/* $("#editMoney").attr("disabled","true");
						$("#editTime").attr("disabled","true");
						$("#editMajor").attr("disabled","true"); */
					}
				}
			);
			
		});
		
		//点击开通，将按钮恢复
		$("#openBut").click(function(){
			if($("#major").val()==0){
				App.dialog_show("提示！", "科室不能为空！如需帮助，请拨打客服电话：0755-26825795", function(){
					$(".info-dialog-close").trigger("click");
				});
				return;
			}else{
				var weekLow = $("#week_low").val();
				var weekHigh = $("#week_high").val();
				var beginTime = $("#begin_Time").val();
				var endTime = $("#end_Time").val();
				if(weekLow>weekHigh){
					App.dialog_show("提示！", "您的开通日期设置不正确！", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
				else if(parseInt(beginTime)>parseInt(endTime)){
					App.dialog_show("提示！", "您的问诊开始时间大于结束时间了！", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
				else if(parseInt(endTime)-parseInt(beginTime)<1){
					App.dialog_show("提示！", "您的问诊时间必须大于一小时！", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
				else{
					$.post(
						baseURL+"/hospitalVisit/openTwice",
						//"/JumperRemoteAdmin/hospitalVisit/openTwice",
						{weekLow:$("#week_low").val(),weekHigh:$("#week_high").val(),beginTime:$("#begin_Time option:selected").text(),endTime:$("#end_Time option:selected").text()},
						function(data){
							if(data=="success"){
								App.dialog_show("提示！", "服务已开通！", function(){
									$(".info-dialog-close").trigger("click");
									$("#status").val(1);
									//window.location.reload();
									window.location.href=baseURL+"/hospitalVisit/index";
								});
								$("#visitStatus1").text("已开通");
								//window.location.reload();
								/* $("#editMoney").attr("disabled","false");
								$("#editTime").attr("disabled","false");
								$("#editMajor").attr("disabled","false"); */
								
							}else{
								App.dialog_show("提示！", "服务开通失败！", function(){
									$(".info-dialog-close").trigger("click");
									window.location.reload();
								});
							}
						}
					);
				}
			}
		});
		
		$("#editTime").click(function(){
			var weekLow = $("#week_low").val();
			var weekHigh = $("#week_high").val();
			var beginTime = $("#begin_Time").val();
			var endTime = $("#end_Time").val();
			if(weekLow>weekHigh){
				App.dialog_show("提示！", "您的开通日期设置不正确！", function(){
					$(".info-dialog-close").trigger("click");
				});
			}
			else if(parseInt(beginTime)>parseInt(endTime)){
				App.dialog_show("提示！", "您的问诊开始时间大于结束时间了！", function(){
					$(".info-dialog-close").trigger("click");
				});
			}
			else if(parseInt(endTime)-parseInt(beginTime)<1){
				App.dialog_show("提示！", "您的问诊时间必须大于一小时！", function(){
					$(".info-dialog-close").trigger("click");
				});
			}
			else{
				App.confirm_show("提示信息", "确定要修改时间吗？", function(){
				$(".info-confirm-cancel").trigger("click");
					$.post(
					baseURL+"/hospitalVisit/editTime",
					//"/JumperRemoteAdmin/hospitalVisit/editTime",
					{weekLow:$("#week_low").val(),weekHigh:$("#week_high").val(),beginTime:$("#begin_Time option:selected").text(),endTime:$("#end_Time option:selected").text()},
					function(data){
						if(data=="success"){
							var message = "";
							if(data=="success"){
								message = "问诊时间修改成功！";
							}
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
								if(data == 'success'){
									location.reload();
								}
							});
						}
					}
				); 
			});
			
			}
			
		});
		
		/* $("#editMajor").click(function(){
		    var checkedMajor = new Array();
			$("input[type='checkbox']").each(function(){
				if($(this).attr("checked")){
					checkedMajor.push($(this).attr("value"));
				}
			});
			if(checkedMajor.length==0){
				App.dialog_show("提示！", "至少选中一个科室！", function(){
					$(".info-dialog-close").trigger("click");
				});
			}
			App.confirm_show("提示信息", "确定要修改科室吗？", function(){
				$(".info-confirm-cancel").trigger("click");
				$.post(
				baseURL+"/hospitalVisit/editMajor",
				{checkedMajor:checkedMajor.join()},
				function(data){
					$(".info-confirm-cancel").trigger("click");
					if(data=="success"){
						App.dialog_show("提示！", "科室修改成功！", function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
					}
				}
			);
			});
			
		}); */
		
		$("#But").click(function(){//
			App.dialog_show("提示！", "异常提示：主人，现在我有点累了，先休息一下！请稍后再试！", function(){
				$(".info-dialog-close").trigger("click");
			});
		});
		
	});
	
</script>
<div class="col-xs-12 col-sm-9" style="margin-bottom: 10px;">
   <div class="panel panel-default">
       <div class="panel-body">
		  <div class="portlet-title">
	 	    <div class="actions" style="margin-top: auto;margin-bottom: auto;">
	 	    	<input type="hidden" id="major" value="${major}"/>
	    	    <img src="${pageContext.request.contextPath}/media/image/hospital.png"/>&nbsp;&nbsp;医院问诊<br/>
	    	    <!--需要判断，如果开通金额为0的话，显示问诊类型为免费问诊  -->
	    	    <input type="hidden" id="status" value="${hospitalService.status}"/>
	    	    <span style="float: right;margin-right:15px;color: #BEBEBE;" id="visitStatus1">
	  	    		<!--判断状态，如果是已开通则显示已开通字样，如果是未开通则显示未开通字样  -->
  	    			<c:if test="${hospitalService.status==0}">已关闭</c:if>
  	    			<c:if test="${hospitalService.status==1}">已开通</c:if>
	  	    	</span>
	  	    	
	  	    	<span style="color: #BEBEBE;" id="visitStatus2">&emsp;&emsp;&emsp;&emsp;&nbsp;
	  	    	<c:choose>
	  	    		<c:when test="${hospitalService.status==1}">
	  	    			<c:if test="${hospitalService.servicecost==null||hospitalService.servicecost==0}">
	    	    	您开通的医院问诊类型为免费问诊
	    	    </c:if>
	    	    <c:if test="${hospitalService.servicecost!=null&&hospitalService.servicecost>0}">
	    	    	您开通的医院问诊类型为付费问诊
	    	    </c:if>
	  	    		</c:when>
	  	    	</c:choose>
	  	    	
	    	    
	    	    </span>
	  	    </div>
	  	    
	  	    <hr style="margin-right:15px"/>
	  	    <img src="${pageContext.request.contextPath}/media/image/logo1.png">&nbsp;&nbsp;介绍<br/><br/>
	  	    &nbsp;&nbsp;1、医院问诊即当我院已开通医院问诊服务，我院可在服务期间即时为患者发起的问诊进行解答。<br/><br/>
	  	    &nbsp;&nbsp;2、通过医院问诊服务，不仅可节省人力和加强对患者的服务力度，还可让患者足不出户获得院内医生的专业解答。<br/>
	  	    
	   	  	<hr style="margin-right:15px"/>
	  	    <img src="${pageContext.request.contextPath}/media/image/logo1.png">&nbsp;&nbsp;金额设置<br/><br/>
	  	    <div class="panel-detail">
					<ul class="service-ul">
							<li>
								<div class="service-panel">
									<span id="myMoney">${hospitalService.servicecost} 元/次</span>
								</div>
								<div class="service-delete" style="position:relative;top: -100px;left: 60px;visibility: hidden;">
									<button type="button" class="btn btn-link" value=""><i class="icon-remove"></i></button>
								</div>
								<div class="service-edit">
									<button id="editMoney" type="button" class="btn btn-link" data-toggle="modal" data-target="#myModal" value="${hospitalService.servicecost}"><i class="icon-pencil"></i></button>
								</div>
							</li>
					</ul>
					<br/><br/><br/><br/><br/><br/>
				</div>
	  	    <hr style="margin-right:15px;display:block;"/>
	  	    <img src="${pageContext.request.contextPath}/media/image/logo1.png">&nbsp;&nbsp;时间设置
	  	    
	  	    <br/><br/>
	  	    <!--时间设置：时间区间用下拉列表显示  -->
	  	    &nbsp;&nbsp;<select id="week_low" name="week_low">
	  	    	<c:set var="weeklow" value="${hospitalService.weeklow}"></c:set>
	  	    	<c:forEach items="${weekList}" var="week">
	  	    		<c:choose>
	  	    			<c:when test="${weeklow==week.key}">
	  	    				<option selected="selected" value="${week.key}">${week.value}</option>
	  	    			</c:when>
	  	    			<c:otherwise>
	  	    				<option value="${week.key}">${week.value}</option>
	  	    			</c:otherwise>
	  	    		</c:choose>
	  	    	</c:forEach>
	  	    </select>
	  	    
	  	    至
	  	    <select id="week_high" name="week_high">
	  	    	<c:set var="weekhigh" value="${hospitalService.weekhigh}"></c:set>
	  	    	<c:forEach items="${weekList}" var="week">
	  	    		<c:choose>
	  	    			<c:when test="${weekhigh==week.key}">
	  	    				<option selected="selected" value="${week.key}">${week.value}</option>
	  	    			</c:when>
	  	    			<c:otherwise>
	  	    				<option value="${week.key}">${week.value}</option>
	  	    			</c:otherwise>
	  	    		</c:choose>
	  	    	</c:forEach>
	  	    </select>&emsp;&emsp;
	  	     <fmt:formatDate var="begin" value="${hospitalService.beginTime}" pattern="HH:mm" />
	  	     <%-- <fmt:formatDate var="end" value="${hospitalService.endTime}" pattern="HH:mm" /> --%>
	  	     <input type="hidden" id="begin" value="${begin}"/>
	  	     <input type="hidden" id="end" value="${endTime}"/>
	  	   <%--  <input name="begin_Time" id="begin_Time"  class="text" style="width:110px"  value="${begin}"/> --%>
	  	    <select id="begin_Time" name="begin_Time" style="width:100px;text-align:center;">
	  	    	<option value="7">07:00</option><option value="7.5">07:30</option>
	  	    	<option value="8">08:00</option><option value="8.5">08:30</option>
	  	    	<option value="9">09:00</option><option value="9.5">09:30</option>
	  	    	<option value="10">10:00</option><option value="10.5">10:30</option>
	  	    	<option value="11">11:00</option><option value="11.5">11:30</option>
	  	    	<option value="12">12:00</option><option value="12.5">12:30</option>
	  	    	<option value="13">13:00</option><option value="13.5">13:30</option>
	  	    	<option value="14">14:00</option><option value="14.5">14:30</option>
	  	    	<option value="15">15:00</option><option value="15.5">15:30</option>
	  	    	<option value="16">16:00</option><option value="16.5">16:30</option>
	  	    	<option value="17">17:00</option><option value="17.5">17:30</option>
	  	    	<option value="18">18:00</option><option value="18.5">18:30</option>
	  	    	<option value="19">19:00</option><option value="19.5">19:30</option>
	  	    	<option value="20">20:00</option><option value="20.5">20:30</option>
	  	    	<option value="21">21:00</option><option value="21.5">21:30</option>
	  	    	<option value="22">22:00</option><option value="22.5">22:30</option>
	  	    	<option value="23">23:00</option><option value="23.5">23:30</option>
	  	    	
	  	    </select>
	  	     至
	  	    <%-- <input name="end_Time" id="end_Time"  class="text" style="width:110px"  value="${end}"/> --%>
	  	    <select id="end_Time" name="end_Time"  style="width:100px;text-align:center;">
	  	  	    <option value="7">07:00</option><option value="7.5">07:30</option>
	  	    	<option value="8">08:00</option><option value="8.5">08:30</option>
	  	    	<option value="9">09:00</option><option value="9.5">09:30</option>
	  	    	<option value="10">10:00</option><option value="10.5">10:30</option>
	  	    	<option value="11">11:00</option><option value="11.5">11:30</option>
	  	    	<option value="12">12:00</option><option value="12.5">12:30</option>
	  	    	<option value="13">13:00</option><option value="13.5">13:30</option>
	  	    	<option value="14">14:00</option><option value="14.5">14:30</option>
	  	    	<option value="15">15:00</option><option value="15.5">15:30</option>
	  	    	<option value="16">16:00</option><option value="16.5">16:30</option>
	  	    	<option value="17">17:00</option><option value="17.5">17:30</option>
	  	    	<option value="18">18:00</option><option value="18.5">18:30</option>
	  	    	<option value="19">19:00</option><option value="19.5">19:30</option>
	  	    	<option value="20">20:00</option><option value="20.5">20:30</option>
	  	    	<option value="21">21:00</option><option value="21.5">21:30</option>
	  	    	<option value="22">22:00</option><option value="22.5">22:30</option>
	  	    	<option value="23">23:00</option><option value="23.5">23:30</option>
	  	    </select>
	  	    &emsp;&emsp;&emsp;&emsp;
	  	    <button id="editTime"  style="width:26px;height:17px;background: url('${pageContext.request.contextPath}/media/image/edit.png');repeat:none;" class="btn btn-pink"></button>
	  	    
	  	    
	  	    <hr style="margin-right:15px"/>
	  	    <img src="${pageContext.request.contextPath}/media/image/logo1.png">&nbsp;&nbsp;科室设置
	  	     	
	  	    <br/><br/>
	  	   
	  	    <!--从数据库中获取该医院所拥有的科室信息，进行显示  -->
	  	   
	  	  <%--  <input id="majors" type="hidden" value="${majors}"> 
	  	   <input id="majorList" type="hidden" value="${majorList}"> 
	  	    &nbsp;&nbsp; <c:forEach items="${majorList}" var="major">
	  	    	<input type="checkbox" name="major" value="${major.id}"/>&nbsp;${major.major}&emsp;&emsp;&emsp;
	  	    </c:forEach> 
	  	     <button id="editMajor" style="width:26px;height:17px;background: url('${pageContext.request.contextPath}/media/image/edit.png');repeat:none;" class="btn btn-pink"></button> --%>
	  	   &nbsp;&nbsp;<c:forEach items="${majorInfos}" var="major">
	  	    	<%-- <input type="checkbox" name="major" value="${major.id}"/>&nbsp; --%>${major.major}&emsp;&emsp;&emsp;
	  	    </c:forEach>
	  	   
	  	    <br/><br/><br/>
	  	    <c:if test="${hospitalService.status==1}">
	  	    	 <div id="close"  style="text-align:center;">
			  	    <button type="button" id="closeBut" class="btn btn-pink" style="width: 141px;height:47px;align:center;background: url('${pageContext.request.contextPath}/media/image/close.png');repeat:none;"></button>
			   	  </div>
	  	    </c:if>
	  	   <c:if test="${hospitalService.status==0}">
	  	   		<div id="open"  style="text-align:center;">
		   	  		<button type="button" id="openBut" class="btn btn-pink" style="width: 141px;height:47px;align:center;background: url('${pageContext.request.contextPath}/media/image/open.png');repeat:none;"></button>
		   	  	</div>
	  	   </c:if>
	  	   <c:if test="${hospitalService.status!=0&&hospitalService.status!=1}">
	  	   		<div id="open_error" style="text-align:center;">
		   	  		<button type="button" id="But" class="btn btn-pink" style="width: 141px;height:47px;align:center;background: url('${pageContext.request.contextPath}/media/image/open.png');repeat:none;"></button>
		   	  	</div>
	  	   </c:if>
	   	  </div> 
	   </div>
	</div>
	
 </div>
 
 <!-- 模态框（Modal） -->
 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	  	<div class="modal-content">
	        <form id="submitForm" action="" method="post">
	      		<div class="modal-header">
	          		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	               					 &times;
	          		</button>
					<input type="hidden" name="type" class="mType">
					<input type="hidden" name="hospitalId" class="mHospId">
					<input type="hidden" name="id" class="mId">
					<input type="hidden" name="addTime" class="mAddTime" >
					<!-- <input type="hidden" name="imgUrl" class="mImgUrl"> -->
					<div class="description">
				       <label class="">设置医院问诊费用<span class="required">*</span></label><br/><br/>
				       <div class="contents" style="margin-left: 150px;"><!--  /^((\d*[1-9]\.\d{2}))$/g-->
				          服务费用：<input type="text" name="servicecost" id="money" onkeyup="this.value=this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3')"/>&nbsp;元/次
				       </div>
				       
				    </div>
				    
	       		</div>
	       		<div class="modal-footer">
	         		<button type="button" class="btn btn-default" 
	             			data-dismiss="modal">取消</button>
	          		<button type="button" class="btn btn-danger" id="ok" data-dismiss="modal">确定</button>
	       		</div>
	          </form>
	  	</div>
	</div>
  </div>
  
    <!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>

<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/service.js"></script>