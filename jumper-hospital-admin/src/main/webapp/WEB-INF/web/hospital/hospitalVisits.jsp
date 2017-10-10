<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!-- 页头引入 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/hospital.css?${v }">
<link href="${pageContext.request.contextPath}/media/css/service.css?${v }" rel="stylesheet">
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$("#ok").click(function(){
			if($("#money").val()!=null&&$("#money").val()!=""){
				if(!isNaN($("#money").val())){
					var money = parseFloat($("#money").val());
					document.getElementById("myMoney").innerHTML = money+" 元/次";
					App.dialog_show("提示！", "金额设置成功！", function(){
						$(".info-dialog-close").trigger("click");
					});
				}else{
					App.dialog_show("提示！", "您输入的金额不合法！", function(){
						$(".info-dialog-close").trigger("click");
					});
				}
				
			}else{
				document.getElementById("myMoney").innerHTML = 0+" 元/次";
			}
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
		
		$("#openBut").click(function(){
			if($("#major").val()==0){
				App.dialog_show("提示！", "科室不能为空！如需帮助，请拨打客服电话：0755-26825795", function(){
					$(".info-dialog-close").trigger("click");
				});
				return;
			}else{
				var moneys = $("#money").val();
				var money = 0;
				var week_low = $("#week_low").val();
				var weel_high = $("#week_high").val();
				var begin_Time = $("#begin_Time").val();
				var end_Time = $("#end_Time").val();
				if(week_low==null||week_low==""||week_low.trim().length==0){
					App.dialog_show("提示！", "请设置问诊时间", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
				else if(week_low>weel_high){
					App.dialog_show("提示！", "您的开通日期设置不正确！", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
				else if(weel_high==null||weel_high==""||weel_high.trim().length==0){
					App.dialog_show("提示！", "请设置问诊时间", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
				else if(begin_Time==null||begin_Time==""||begin_Time.trim().length==0){
					App.dialog_show("提示！", "请设置问诊时间", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
				else if(end_Time==null||end_Time==""||end_Time.trim().length==0){
					App.dialog_show("提示！", "请设置问诊时间", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
				
				else if(parseInt(begin_Time)>parseInt(end_Time)){
					App.dialog_show("提示！", "您的问诊开始时间大于结束时间了！", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
				else if(parseInt(end_Time)-parseInt(begin_Time)<1){
					App.dialog_show("提示！", "您的问诊时间必须大于一小时！", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}/* else{
					var major = new Array();
					$("input[type='checkbox']:checked").each(function(){
						major.push($(this).val());
					});
					if(major.length==0){
						App.dialog_show("提示！", "请选择科室！", function(){
							$(".info-dialog-close").trigger("click");
						});
					}
				} */
				if(moneys!=null&&moneys!=""&&moneys.trim().length!=0){
					money = parseFloat(moneys);
				}
				
				$.post(
					baseURL+"/hospitalVisit/openVisit",
					{cost:money,
					week_low:$("#week_low").val(),
					week_high:$("#week_high").val(),
					begin_Time:$("#begin_Time option:selected").text(),
					end_Time:$("#end_Time option:selected").text(),
					//major:major.join()
					},
					function(data){
						if(data=="success"){
							//跳转到另外一个页面,先到后台取值，然后跳转
							App.dialog_show("提示！", "服务开通成功！", function(){
								$(".info-dialog-close").trigger("click");
								window.location.href=baseURL+"/hospitalVisit/index";
							});
						}else{
							App.dialog_show("提示！", "服务开通失败！", function(){
							$(".info-dialog-close").trigger("click");
						});
						}
					}
				);
			}
			
		});
	});
	
</script>
<div class="col-xs-12 col-sm-9"><!--margin-bottom: 10px;height: 80px;margin:0px auto;  -->
   <div class="panel panel-default">
       <div class="panel-body">
	 	    <div class="actions" style="margin-top: auto;margin-bottom: auto;">
	 	    <input type="hidden" id="major" value="${major}"/>
	    	    <img src="${pageContext.request.contextPath}/media/image/hospital.png"/>&nbsp;&nbsp;医院问诊
	    	    <!--需要判断，如果开通金额为0的话，显示问诊类型为免费问诊  -->
	    	    <span style="float: right;margin-top: 15px;margin-right:15px;color: #BEBEBE;">
	  	    		<!--判断状态，如果是已开通则显示已开通字样，如果是未开通则显示未开通字样  -->
	  	    		<c:if test="${hospitalInfo.isConsultant==null||hospitalInfo.isConsultant==0}">未开通</c:if>
	  	    		<c:if test="${hospitalInfo.isConsultant==1}">
	  	    			已开通
	  	    		</c:if>
	  	    	</span>
	  	    </div>
	  	    
	  	    <hr style="margin-right:15px"/>
	  	    <img src="${pageContext.request.contextPath}/media/image/logo1.png">&nbsp;&nbsp;介绍<br/><br/>
	  	    &nbsp;&nbsp;1、医院问诊即当我院已开通医院问诊服务，我院可在服务期间即时为患者发起的问诊进行解答。<br/><br/>
	  	    &nbsp;&nbsp;2、通过医院问诊服务，不仅可节省人力和加强对患者的服务力度，还可让患者足不出户获得院内医生的专业解答。<br/>
	   	  	<hr style="margin-right:15px"/>
	  	    <img src="${pageContext.request.contextPath}/media/image/logo1.png">&nbsp;&nbsp;金额设置<br/><br/>
	  	   <%--  <div style="background-color: #E7E7E7;height: 122px;width: 122px;margin-left: 10px;" >
	  	    	<span id="myMoney" style="font-size: 16px;font-weight: bold;margin-left:22px;padding-top: 20px;"></span>
	  	    	<button style="width: 23px;height: 17px;background: url('${pageContext.request.contextPath}/media/image/edit.png');repeat:none;
	  	    	margin-left: 90px;margin-top: 80px;" class="btn btn-pink" data-toggle="modal" data-target="#myModal"></button>
	  	    </div> --%>
	  	     <div class="panel-detail">
					<ul class="service-ul">
							<li>
								<div class="service-panel">
									<span id="myMoney"> 元/次</span>
								</div>
								<div class="service-delete" style="position:relative;top: -100px;left: 60px;visibility: hidden;">
									<button type="button" class="btn btn-link" value=""><i class="icon-remove"></i></button>
								</div>
								<div class="service-edit">
									<button id="editMoney" value="" type="button" class="btn btn-link" data-toggle="modal" data-target="#myModal"><i class="icon-pencil"></i></button>
								</div>
							</li>
					</ul>
					<br/><br/><br/><br/><br/><br/>
				</div>
	  	    <hr style="margin-right:15px"/>
	  	    <img src="${pageContext.request.contextPath}/media/image/logo1.png">&nbsp;&nbsp;时间设置<br/><br/>
	  	    <!--时间设置：时间区间用下拉列表显示  -->
	  	    &nbsp;&nbsp;<select id="week_low" name="week_low">
	  	    	<option value="1">星期一
	  	    	<option value="2">星期二
	  	    	<option value="3">星期三
	  	    	<option value="4">星期四
	  	    	<option value="5">星期五
	  	    	<option value="6">星期六
	  	    	<option value="7">星期日
	  	    </select>
	  	    至
	  	    <select id="week_high" name="week_high">
	  	    	<option value="1">星期一
	  	    	<option value="2">星期二
	  	    	<option value="3">星期三
	  	    	<option value="4">星期四
	  	    	<option value="5">星期五
	  	    	<option value="6">星期六
	  	    	<option value="7">星期日
	  	    </select>&emsp;&emsp;
	  	   <!--  <input name="begin_Time" id="begin_Time"  class="text" style="width:110px" onclick="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm'})"/> 至
	  	        <input name="end_Time" id="end_Time"  class="text" style="width:110px" onclick="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm'})"/> -->
	  	    <select id="begin_Time" style="width:100px;text-align:center;">
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
	  	    <select id="end_Time"  style="width:100px;text-align:center;">
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
	  	   <!--  <select id="begin_Time">
	  	    	
	  	    </select> -->
	  	    
	  	    
	  	    <hr style="margin-right:15px"/>
	  	    <img src="${pageContext.request.contextPath}/media/image/logo1.png">&nbsp;&nbsp;科室设置<br/><br/>
	  	   
	  	   
	  	    <!--从数据库中获取该医院所拥有的科室信息，进行显示  -->
	  	    &nbsp;&nbsp;<c:forEach items="${majorInfos}" var="major">
	  	    	<%-- <input type="checkbox" name="major" value="${major.id}"/>&nbsp; --%>${major.major}&emsp;&emsp;&emsp;
	  	    </c:forEach>
	  	   
	  	    <br/><br/><br/>
	  	    <div style="text-align:center;">
	  	    <button type="button" id="openBut" class="btn btn-pink" style="width: 141px;height:47px;background: url('${pageContext.request.contextPath}/media/image/open.png');repeat:none;"></button>
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
				       <div class="contents" style="margin-left: 150px;"><!-- onKeyUp="if(this.value.length>8){this.value=this.value.substr(0,8);};this.value=this.value.replace(/\d+[.]\d{3}/g,'');" -->
				          服务费用：<input type="text" name="money" id="money" onkeyup="this.value=this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3')"/>&nbsp;元/次
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