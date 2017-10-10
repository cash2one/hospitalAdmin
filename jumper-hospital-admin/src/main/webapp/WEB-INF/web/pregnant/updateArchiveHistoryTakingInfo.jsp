<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/media/css/pregnant/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/media/css/pregnant/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/media/css/pregnant/menu.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/media/css/pregnant/daterangepicker-bs3.css">
<script type="text/javascript">
$(function(){
$(".mod_btn a").click(function(){
    $(this).toggleClass("active");
});
});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/jquery.tips.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/laydate-v1.1/laydate/laydate.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/updateArchiveHistoryTaking.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/loadArchiveBaseInfo.js"></script>
<style type="text/css">
html{overflow-x: scroll;}
	body {min-width: 1660px;overflow-y: hidden;}
	.message-dialog{width: 800px;height: 500px;}
 	*{padding: 0; margin: 0; }
	.panel{margin-bottom:0px;}
	.modal {
    position: fixed;
    top: 3%;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 1050;
    display: none;
    overflow: hidden;
    -webkit-overflow-scrolling: touch;
    outline: 0;
}
.modal-dialog {
    width: 50%;
    margin: 30px auto;
}
.modal-header {
    min-height: 16.42857143px;
    padding: 15px;
    border-bottom: 1px solid #fff;
}
.gw_btn { width:280px;}
input, select {
    height: 25px;
    padding: 0 5px;
}
</style>
<!--style-->
</head>
<body style="background: #F0F0F0;">
                <!-- 档案管理 高危筛查 -->	                
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
	               	       
		                       <ul class="page-sidebar-menu">
		                          <li class="active"><a
									href="${pageContext.request.contextPath}/pregnant/archiveIndex">
										<i class="icon-plus-sign-alt"></i>&nbsp; <span class="title">档案管理</span>
								</a>
								</li>
		                       </ul>
	                    </div>

<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
		<div class="panel" >
        	<div class="gw_rote"> <span class="float-left" style="font-size:16px;">档案管理 > 档案详情 >  <a style=" color:#ff7ea5">病史询问-修改</a></span></div>
        	<div class="panel-body">	
              <div class="panel panel-default" style="margin:15px 0px;">
                <div class="panel-body">
                        <table class="gw_xx">
                                <tbody>
										<tr>
											<td colspan="4">姓名：<span id="archive_name"></span></td>
											<td colspan="4">联系电话：<span id="archive_mobile"></span></td>
											<td colspan="4">末次月经：<span id="archive_lastPeriod"></span></td>
											<td colspan="4">预产期：<span id="archive_pregnantDate"></span></td>
										</tr>
										<tr>
											<td colspan="4">当前孕周：<span id="archive_pregnantWeek"></span>周<span id="archive_pregnantDay"></span>天</td>
											<td colspan="4">状态：<span id="archive_pregnantState"></span>
											</td>
											<td colspan="4">分娩日期：<span id="archive_deliveryTime"></span></td>
										</tr>
									</tbody>
                     </table>
                </div>
            </div>   
            <div class="panel panel-default float-left" style="margin:15px 0px;width: 100%;">
                <div class=" float-left" style="width:100%;">
                        <ul id="myTab" class="nav nav-tabs">
   							<%-- <li><a href="${pageContext.request.contextPath}/pregnant/showArchiveBaseInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}">基本信息</a></li> --%>
   							<li class="active"><a>病史询问</a></li>
                            <%-- <li><a href="${pageContext.request.contextPath}/pregnant/showArchiveTestCaseInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" >初检情况</a></li>
                            <li><a>高危筛查</a></li>
                            <li><a href="${pageContext.request.contextPath}/monitor/weight" >监测数据</a></li>
                            <li><a>分娩结局</a></li>
                            <li ><a>产后访视</a></li>
                            <li><a href="${pageContext.request.contextPath}/neonatal/lookIndex?userId=230" >新生儿访视</a></li> --%>
  						</ul>
                        
                </div>
                <!-- 所有需要拆分的数据 -->
                <input type="hidden" id="archiveId" name="id" value="${archiveId }"/>
				<input type="hidden" id="archiveBaseId" value="${archiveBaseId }"/>
				<input type="hidden" id="pregnancyTimeData" value="${userHistoryTaking.pregnancyTime }"/>
				<input type="hidden" id="morningSicknessTimeData" value="${userHistoryTaking.morningSicknessTime }"/>
				<input type="hidden" id="quickeningTimeData" value="${userHistoryTaking.quickeningTime }"/>
				<input type="hidden" id="animalContactTimeData" value="${userHistoryTaking.animalContactTime }"/>
				<input type="hidden" id="takeTeratogenicDrugContentData" value="${userHistoryTaking.takeTeratogenicDrugContent }"/>
				<input type="hidden" id="anamnesisData" value="${userHistoryTakingOtherInfo.anamnesis }"/>
				<input type="hidden" id="familyHistoryData" value="${userHistoryTakingOtherInfo.familyHistory }"/>
				<input type="hidden" id="allergicTypeData" value="${userHistoryTakingOtherInfo.allergicType }"/>
			<form action="${pageContext.request.contextPath}/pregnant/updateArchiveHistoryTakingInfo" id="updateArchiveHistoryTakingForm">
			<input type="hidden" name="archiveBaseId" value="${archiveId }"/>
			<input type="hidden" name="archiveId" value="${userHistoryTaking.archiveId }"/>
			<input type="hidden" name="userHistoryTakingId" value="${userHistoryTaking.id }"/>
			<input type="hidden" name="userHistoryTakingPregnancyId" value="${userHistoryTakingPregnancy.id }"/>
			<input type="hidden" name="userHistoryTakingOtherInfoId" value="${userHistoryTakingOtherInfo.id }"/>
             <div id="cont_roll" class="panel panel-default float-left" style=" margin:15px;width: 98%;">  
                	<div class="weight-title">本次妊娠情况</div>
                    <div class="panel-body">
                        <table class="gw_xx">
                                    <tbody>
                                    <tr>
                                        <!--<td >末次月经（LMP）：<input type="text" value=""></td>
                                        <td ><span style="color:red; font-size:18px;">*</span>预产期（EDC)：<input type="text" value=""></td>-->
                                        <td >初潮年龄：<input style="width:50px;" type="text" name="menophaniaAge" value="${userHistoryTaking.menophaniaAge }"> 岁</td>
                                        <td >月经周期：<input style="width:80px;" type="text" name="mensesSpace" value="${userHistoryTaking.mensesSpace }" placeholder="经期间隔"> / <input style="width:80px;" type="text" name="mensesDays" value="${userHistoryTaking.mensesDays }" placeholder="持续天数"> 天</td>
                                    </tr>
                                    <tr>
                                        <td >初次确诊怀孕时间：
                                        <input style="width: 100px; display:inline-block; height:27px;" name="firstAffirmPregnancyDate" id="startTime" placeholder="日期选择" class="form-control" value="${userHistoryTaking.firstAffirmPregnancyDate }" type="text"> 
                                    		    孕<input style="width: 50px;display:inline-block;" type="text" id="menstrualCycleWeek" value="">
                                    		    周<input style="width: 50px;display:inline-block;" type="text" id="menstrualCycleDay" value="">天 
                                        <input type="hidden" name="pregnancyTime" id="pregnancyTime" value=""/>
                                        </td>
                                        <td >早孕反应：
                                        <label><input style="width:10px;" type="radio" name="morningSicknessState" value="0" <c:if test="${userHistoryTaking.morningSicknessState==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="morningSicknessState" value="1" <c:if test="${userHistoryTaking.morningSicknessState==1 }">checked</c:if>> 有</label>
                                        	孕<input style="width: 50px;display:inline-block;" type="text" id="morningSicknessWeek" value=""/>
                                        	周<input style="width: 50px;display:inline-block;" type="text" id="morningSicknessDay" value=""/>天 
                                         <input type="hidden" name="morningSicknessTime" id="morningSicknessTime" value=""/>
                                        </td>
                                        <td >初感胎动：
                                        <label><input style="width:10px;" type="radio" name="quickening" value="0" <c:if test="${userHistoryTaking.quickening==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="quickening" value="1" <c:if test="${userHistoryTaking.quickening==1 }">checked</c:if>> 有</label>
                                        	孕<input style="width: 50px;display:inline-block;" type="text" id="quickeningWeek" value="">
                                        	周<input style="width: 50px;display:inline-block;" type="text" id="quickeningDay" value="">天
                                        <input type="hidden" name="quickeningTime" id="quickeningTime"/>
                                         </td>
                                    </tr>                                    
                            </tbody>
                         </table>
                     </div>
                     <div class="weight-title">本次妊娠检查前情况</div>
                     <div class="panel-body mod_btn">
                     <div style="margin-bottom:25px;">
                         <table class="gw_xx">
                                 <tbody>
                                 	<tr>
                                    	<td width="50%">有无异常：
                                        <label><input style="width:10px;" type="radio" name="isAbnormal" value="0" <c:if test="${userHistoryTaking.isAbnormal==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="isAbnormal" value="1" <c:if test="${userHistoryTaking.isAbnormal==1 }">checked</c:if>> 有</label>
                                        <input type="text" value="${userHistoryTaking.abnormalContent }" placeholder="请输入异常内容" style="width:60%;">
                                        </td>
                                        <td width="50%">叶酸服用：
                                        <label><input style="width:10px;" type="radio" name="takingFolicAcid" value="0" <c:if test="${userHistoryTaking.takingFolicAcid==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="takingFolicAcid" value="1" <c:if test="${userHistoryTaking.takingFolicAcid==1 }">checked</c:if>> 有</label>
                                        <span class="kong">孕前：
                                        <label><input style="width:10px;" type="checkbox" name="folicAcidProgestation" value="1" <c:if test="${userHistoryTaking.folicAcidProgestation==1 }">checked</c:if>> 1月</label>
                                        <label><input style="width:10px;" type="checkbox" name="folicAcidProgestation" value="2" <c:if test="${userHistoryTaking.folicAcidProgestation==2 }">checked</c:if>> 2月</label>
                                        <label><input style="width:10px;" type="checkbox" name="folicAcidProgestation" value="3" <c:if test="${userHistoryTaking.folicAcidProgestation==3 }">checked</c:if>> 3月</label>
                                        </span>
                                        <span class="kong">
                                        孕早期：
                                        <label><input style="width:10px;" type="checkbox" name="folicAcidMorningSickness" value="1" <c:if test="${userHistoryTaking.folicAcidMorningSickness==1 }">checked</c:if>> 1月</label>
                                        <label><input style="width:10px;" type="checkbox" name="folicAcidMorningSickness" value="2" <c:if test="${userHistoryTaking.folicAcidMorningSickness==2 }">checked</c:if>> 2月</label>
                                        <label><input style="width:10px;" type="checkbox" name="folicAcidMorningSickness" value="3" <c:if test="${userHistoryTaking.folicAcidMorningSickness==3 }">checked</c:if>> 3月</label>
                                        </span>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<td>第一次检查尿检阳性是停经后：<span class="kong"><input type="text" name="firstExamineAfterMenelipsis" value="${userHistoryTaking.firstExamineAfterMenelipsis }" style="width:50px;"></span>天</td>
                                    </tr>
                                 </tbody>
                         </table>
                     </div>
                     
                     
                         <div id="live_inf">
                        	<span>孕早期情况</span>
                        	<div class="live_inftable">
                        			<table class="gw_xx">
                                         <tbody>
                                             <tr>
                                                 <td width="60%">发热（>38℃）：
                                        <label><input style="width:10px;" type="radio" name="feverState" value="0" <c:if test="${userHistoryTaking.feverState==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="feverState" value="1" <c:if test="${userHistoryTaking.feverState==1 }">checked</c:if>> 有</label>
                                        <span class="kong" style="top:0px;">伴随症状：<input type="text" name="feverDevelop" value="${userHistoryTaking.feverDevelop }" placeholder="请输入伴随症状"></span>
         													
                                                            </td>                   
                                                 <td width="40%">阴道出血：
                                         <label><input style="width:10px;" type="radio" name="colporrhagiaState" value="0" <c:if test="${userHistoryTaking.colporrhagiaState==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="colporrhagiaState" value="1" <c:if test="${userHistoryTaking.colporrhagiaState==1 }">checked</c:if>> 有</label>
                                        <span class="kong" style="top:0px;">伴随症状：<input type="text" name="colporrhagiaDevelop" value="${userHistoryTaking.colporrhagiaDevelop }" placeholder="请输入伴随症状"></span>
         													
                                                              </td>
                                                                    
                                             </tr>
                                             <tr>
                                                  <td width="60%">猫、狗等动物接触史：
                                        <label><input style="width:10px;" type="radio" name="animalContact" value="0" <c:if test="${userHistoryTaking.animalContact==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="animalContact" value="1" <c:if test="${userHistoryTaking.animalContact==1 }">checked</c:if>> 有</label> 
                                        <span class="kong" style="top:0px;">接触时间：
                                     	   孕<input style="width: 50px;display:inline-block;" type="text" id="animalContactStartWeek" value="">
                                     	   周<input style="width: 50px;display:inline-block;" type="text" id="animalContactStartDay" value="">天 至 
                                     	   孕<input style="width: 50px;display:inline-block;" type="text" id="animalContactEndWeek" value="">
                                     	   周<input style="width: 50px;display:inline-block;" type="text" id="animalContactEndDay" value="">天，共<strong id="animalContactNumber"></strong>天
                                     	   <input type="hidden" name="animalContactTime" id="animalContactTime" value=""/>
                                        </span>                
                                                   </td>
                                                  <td width="40%">糖尿病：
                                          <label><input style="width:10px;" type="radio" name="diabetesMellitus" value="0" <c:if test="${userHistoryTaking.diabetesMellitus==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="diabetesMellitus" value="1" <c:if test="${userHistoryTaking.diabetesMellitus==1 }">checked</c:if>> 有</label>         
                                                  </td>                 
    
                                            </tr>
                                                              
                                                                   
                                         </tbody>
                                    </table>
                             </div>
                        </div>
                        
                        <div id="live_inf">
                        	<span>服用可能致畸药物</span>
                        	<div class="live_inftable">
                            <label><input style="width:10px;" type="radio" name="takeTeratogenicDrug" value="0" <c:if test="${userHistoryTaking.takeTeratogenicDrug==0 }">checked</c:if>>无</label>
                            <label><input style="width:10px;" type="radio" name="takeTeratogenicDrug" value="1" <c:if test="${userHistoryTaking.takeTeratogenicDrug==1 }">checked</c:if>>有</label>
                        			<table class="gw_xx">
                                         <tbody id="takeTeratogenicDrugContentTable">
                                         	<c:if test="${userHistoryTaking.takeTeratogenicDrug==0 }">
                                             <tr style="height:45px;">
                                                 <td width="30%"><input style="width:80%;"  type="text" value="" placeholder="请输入药名"></td>                   
                                                 <td width="6%">服用时间段：</td>
                                                 <td width="6%"><input style="width: 100px;height:28px;" type="text" name="startTime" id="takeTeratogenicDrugStartTime1" placeholder="开始日期" class="form-control" value="" ></td>  
		                                         <td width="3%">至</td> 
		                                         <td width="10%"><input style="width: 100px" type="text" value="" name="endTime" id="takeTeratogenicDrugEndTime1" placeholder="结束日期"></td>
                                                <td width="15%"><button type="button" class="btn btn-info" id="takeTeratogenicDrugBtnAdd"><i class="icon-plus"></i> 添加药物</button></td>
                                            </tr>
                                           </c:if>
                                         </tbody>
                                    </table>
                                     <input type="hidden" name="takeTeratogenicDrugContent" id="takeTeratogenicDrugContent" value=""/>
                             </div>
                        </div>
                        <div style="margin-bottom:25px;">
                         <table class="gw_xx">
                                 <tbody>
                                 	<tr>
                                    	<td colspan="3">接触物理性有害因素：
                                        <label><input style="width:10px;" type="radio" name="contactPhysicsHarmful" value="0" <c:if test="${userHistoryTaking.contactPhysicsHarmful==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="contactPhysicsHarmful" value="1" <c:if test="${userHistoryTaking.contactPhysicsHarmful==1 }">checked</c:if>> 有</label>
                                        <span class="kong">
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="1"> 噪声</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="2"> 振动</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="3"> x射线</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="4"> 电磁辐射</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="5"> 高温</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="6"> 微波</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="7"> 极低温</label>
                                                                    <input value="" type="text" placeholder="输入其他">
                                        </span>
                                        <input type="hidden" name="contactPhysicsHarmfulContent" id="contactPhysicsHarmfulContent" value=""/>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<td width="55%">接触高浓度工业毒物：
                                        	<label><input style="width:10px;" type="radio" name="contactIndustrialToxicant" value="0" <c:if test="${userHistoryTaking.contactIndustrialToxicant==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="contactIndustrialToxicant" value="1" <c:if test="${userHistoryTaking.contactIndustrialToxicant==1 }">checked</c:if>> 有</label>
                                        <span class="kong">
                                                                    <label><input style="width:10px;" type="checkbox" name="contactIndustrialToxicantChoose"  value="1"> 重金属</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactIndustrialToxicantChoose"  value="2"> 农药</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactIndustrialToxicantChoose"  value="3"> 有机溶剂</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactIndustrialToxicantChoose"  value="4"> 制药</label>
                                                                    <input value="" type="text" placeholder="输入其他">
                                          </span>
                                          <input type="hidden" name="contactIndustrialToxicantContent" id="contactIndustrialToxicantContent" value=""/>
                                        </td>
                                        <td width="25%">
                                        	环境新装修：
                                        <label><input style="width:10px;" type="radio" name="environmentalNewDecoration" value="0"  <c:if test="${userHistoryTaking.environmentalNewDecoration==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="environmentalNewDecoration" value="1"  <c:if test="${userHistoryTaking.environmentalNewDecoration==1 }">checked</c:if>> 有</label>
                                        </td>
                                        <td td width="20%">
                                        	吸毒：
                                        <label><input style="width:10px;" type="radio" name="takeDrugs" value="0"  <c:if test="${userHistoryTaking.takeDrugs==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="takeDrugs" value="1"  <c:if test="${userHistoryTaking.takeDrugs==1 }">checked</c:if>> 有</label>
                                        </td>
                                    </tr>
                                 </tbody>
                         </table>
                     </div>
                        
                     <div id="live_inf">
                        	<span>饮酒史</span>
                        	<div class="live_inftable">
                            <label><input style="width:10px;" type="radio" name="drinkHistoryState" value="0"  <c:if test="${userHistoryTaking.drinkHistoryState==0 }">checked</c:if>>无</label>
                            <label><input style="width:10px;" type="radio" name="drinkHistoryState" value="1"  <c:if test="${userHistoryTaking.drinkHistoryState==1 }">checked</c:if>> 有</label>
                        			<table class="gw_xx">
                                         <tbody>
                                             <tr style="height:45px;">
                                                 <td>开始时间：<input type="text" name="drinkStartTime" value="${userHistoryTaking.drinkStartTime }" placeholder="日期选择"></td>
                                                 <td>白酒（克）：<input type="text" name="whiteSpirit" value="${userHistoryTaking.whiteSpirit }"></td>
                                                 <td>红酒（克）：<input type="text" name="redSpirit" value="${userHistoryTaking.redSpirit }"></td>
                                                 
                                   
                                            </tr>
                                            <tr style="height:45px;">
                                            	 <td>啤酒（克）：<input type="text" name="beer" value="${userHistoryTaking.beer }"></td>
                                                 <td>饮酒频率：<input type="text" name="drinkFrequency" value="${userHistoryTaking.drinkFrequency }"></td>
                                                 <td>饮酒描述：<input type="text" name="drinkDescription" value="${userHistoryTaking.drinkDescription }"></td>
                                             </tr>
                                            <tr style="height:45px;">
                                                 <td>戒酒时段：<input type="text" name="abstinenceStartTime" value="${userHistoryTaking.abstinenceStartTime }"> 至 
                                                 <input type="text" name="abstinenceEndTime" value="${userHistoryTaking.abstinenceEndTime }"></td>
                                            </tr>
                                         </tbody>
                                    </table>
                             </div>
                        </div>
                        
                     <div id="live_inf">
                        	<span>吸烟史</span>
                        	<div class="live_inftable">
                            <label><input style="width:10px;" type="radio" name="smokeHistoryState" value="0"  <c:if test="${userHistoryTaking.smokeHistoryState==0 }">checked</c:if>>无</label>
                            <label><input style="width:10px;" type="radio" name="smokeHistoryState" value="1"  <c:if test="${userHistoryTaking.smokeHistoryState==1 }">checked</c:if>> 主动</label>
                            <label><input style="width:10px;" type="radio" name="smokeHistoryState" value="2"  <c:if test="${userHistoryTaking.smokeHistoryState==2 }">checked</c:if>> 被动</label>
                        			<table class="gw_xx">
                                         <tbody>
                                             <tr style="height:45px;">
                                                 <td>开始时间：<input type="text" name="smokeStartTime" id="smokeStartTime1" value="${userHistoryTaking.smokeStartTime }" placeholder="日期选择"></td>
                                                 <td>吸烟数（支/天）：<input type="text" name="smokeAmount" value="${userHistoryTaking.smokeAmount }"></td>
                                                 <td>吸烟时段：<input type="text" value="" id="smokeStartTime2"> 至 <input type="text" value="" name="smokeEndTime" id="smokeEndTime" ></td>
                                             </tr>
                                            <tr style="height:45px;">    
                                                 <td>吸烟描述：<input type="text" name="smokeDescription" value="${userHistoryTaking.smokeDescription }"></td>
                                            </tr>                        
                                         </tbody>
                                    </table>
                             </div>
                        </div>
                        
                     <div style="margin-bottom:25px;">
                         <table class="gw_xx">
                                 <tbody>
                                 	<tr>
                                    	<td>病毒感染：
                                        <label><input style="width:10px;" type="radio" name="virusInfectionState" value="0"  <c:if test="${userHistoryTaking.virusInfectionState==0 }">checked</c:if>>无</label>
                                         <label><input style="width:10px;" type="radio" name="virusInfectionState" value="1"  <c:if test="${userHistoryTaking.virusInfectionState==1 }">checked</c:if>>有</label>
                                        <span class="kong">
                                                                    <label><input style="width:10px;" type="checkbox" name="virusInfectionChoose" value="1"> 流感</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="virusInfectionChoose" value="2"> 皮疹</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="virusInfectionChoose" value="3"> 疱疹</label>
                                                                    
                                                                    <input value="" type="text" name="herpes" placeholder="填写疱疹部位">
                                                                    <span class="kong">
                                                                    	患病时间：<input value="${userHistoryTaking.virusInfectionTime }" type="text" name="virusInfectionTime"  placeholder="日期选择">
                                                                    </span>
                                        </span>
                                        <input type="hidden" name="virusInfection" id="virusInfection" value=""/>
                                        </td>

                                  	<td>其他有害因素：<input value="${userHistoryTaking.otherHarmfulFactors }" name="otherHarmfulFactors" type="text" placeholder="请输入其他因素"></td>
                                  </tr>
                                  <tr>
                                  	<td>锻炼方式：
                                    	<label><input style="width:10px;" type="checkbox" value=""> 有氧运动（慢跑、气功等）</label>
                                        <label><input style="width:10px;" type="checkbox" value=""> 无氧运动（篮球、排球）</label>
                                    </td>
                                    <td>饮食习惯：
                                    	<label><input style="width:10px;" type="checkbox" value=""> 普通</label>
                                        <label><input style="width:10px;" type="checkbox" value=""> 嗜咸</label>
                                        <label><input style="width:10px;" type="checkbox" value=""> 嗜甜</label>
                                        <label><input style="width:10px;" type="checkbox" value=""> 喜热</label>
                                        <label><input style="width:10px;" type="checkbox" value=""> 冷饮</label>
                                    </td>
                                  </tr>
                                  <tr>
                                  	<td>睡眠障碍：
                                    	<label><input style="width:10px;" type="checkbox" value=""> 无</label>
                                        <label><input style="width:10px;" type="checkbox" value=""> 入睡困难</label>
                                        <label><input style="width:10px;" type="checkbox" value=""> 早醒</label>
                                        <label><input style="width:10px;" type="checkbox" value=""> 梦游</label>
                                    </td>
                                    <td>睡眠时间（小时/天）：
                                    	<input value="" type="text" >
                                    </td>
                                  </tr>

                                 </tbody>
                         </table>
                     </div>   
                           
                        
                        
                        
                    </div>
                    
                    <div class="weight-title">孕产史</div>
                     <div class="panel-body mod_btn">
                         <table class="gw_xx">
                                 <tbody>
                                    <tr>
                                     <td >孕次（含本次）：<input style="width:80px;" type="text" value="${userHistoryTakingPregnancy.pregnancyCount }"></td>                               
                                     <td >孕产史：
                            <label><input style="width:10px;" type="radio" name="pregnancyHistoryState" value="0"  <c:if test="${userHistoryTakingPregnancy.pregnancyHistoryState==0 }">checked</c:if>>无</label>
                            <label><input style="width:10px;" type="radio" name="pregnancyHistoryState" value="1"  <c:if test="${userHistoryTakingPregnancy.pregnancyHistoryState==1 }">checked</c:if>> 有</label>
                                      </td>                               
                                     <td >不孕不育史：
                             <label><input style="width:10px;" type="radio" name="infertilityHistoryState" value="0"  <c:if test="${userHistoryTakingPregnancy.infertilityHistoryState==0 }">checked</c:if> >无</label>
                            <label><input style="width:10px;" type="radio" name="infertilityHistoryState"  value="1"  <c:if test="${userHistoryTakingPregnancy.infertilityHistoryState==1 }">checked</c:if>> 有</label>
                            <input value="${userHistoryTakingPregnancy.infertilityHistory }" name="infertilityHistory" type="text">        
                                     </td>
                                     </tr>
                                     <tr>                               
                                     <td colspan="3">产次（次）：
                                    <span class="kong">阴道产 <input style="width:80px;" type="text" name="vaginaProduced" value="${userHistoryTakingPregnancy.vaginaProduced }"></span>
									<span class="kong">阴道手术产 <input style="width:80px;" type="text" name="vaginaOperationProduced" value="${userHistoryTakingPregnancy.vaginaOperationProduced }"></span>
                                    <span class="kong">剖宫产 <input style="width:80px;" type="text" name="caesareanSection" value="${userHistoryTakingPregnancy.caesareanSection }"></span>
                                    <span class="kong">产钳 <input style="width:80px;" type="text" name="obstetricForceps" value="${userHistoryTakingPregnancy.obstetricForceps }"></span>
									<span class="kong">宫外孕 <input style="width:80px;" type="text" name="ectopicPregnancy" value="${userHistoryTakingPregnancy.ectopicPregnancy }"></span>
                                    <span class="kong">其他助产 <input style="width:80px;" type="text" name="otherDeliver" value="${userHistoryTakingPregnancy.otherDeliver }"></span>
                                     </td>                               
                                                                    
                                    </tr>
                                    
                                    <tr>
                                    	<td >末次手术时间：<input type="text" value="${userHistoryTakingPregnancy.operationLastTime }" name="operationLastTime" id="operationLastTime" placeholder="日期选择"></td>                               
                                     <td >手术指征：<input type="text" value="${userHistoryTakingPregnancy.operationIndication }" name="operationIndication"></td>                               
                                     <td >并发症：
                             <label><input style="width:10px;" type="radio" name="complicationState" value="0"  <c:if test="${userHistoryTakingPregnancy.complicationState==0 }">checked</c:if>>无</label>
                            <label><input style="width:10px;" type="radio" name="complicationState" value="1"  <c:if test="${userHistoryTakingPregnancy.complicationState==1 }">checked</c:if>> 有</label>
                            <input value="" type="text">        
                                     </td>
                                     </tr>
                                     
                                     <tr>
                                     <td>自然流产（次）：<input style="width:80px;" type="text" name="spontaneousAbortion" value="${userHistoryTakingPregnancy.spontaneousAbortion }" ></td>
									<td>人工流产（次）： <input style="width:80px;" type="text" name="inducedAbortion" value="${userHistoryTakingPregnancy.inducedAbortion }"></td>
                                    <td>引产（次）： <input style="width:80px;" type="text" name="inducedLabour" value="${userHistoryTakingPregnancy.inducedLabour }"></td>
                                    </tr>
                                     
                                     <tr>
                                    <td>早产（次）： <input style="width:80px;" type="text" name="prematureLabour" value="${userHistoryTakingPregnancy.prematureLabour }"></td>
									<td>死胎/产（次）： <input style="width:80px;" type="text" name="stillbirthLabour" value="${userHistoryTakingPregnancy.stillbirthLabour }"></td>
                                    <td>胚胎停育（次）： <input style="width:80px;" type="text" name="embryoDamage" value="${userHistoryTakingPregnancy.embryoDamage }"></td>
                                     </td> 
                                    </tr>
                                    
                                    
                                     <tr>
                                    	<td >末次流产：
                                        <label><input style="width:10px;" type="radio" name="lastAbortionState" value="0"  <c:if test="${userHistoryTakingPregnancy.lastAbortionState==0 }">checked</c:if>>无</label>
                            <label><input style="width:10px;" type="radio" name="lastAbortionState" value="1"  <c:if test="${userHistoryTakingPregnancy.lastAbortionState==1 }">checked</c:if>> 有</label>
                                        </td>                               
                                     <td >流产类型：
                                     	<select name="abortionType">
                                        	<option value="0">请选择</option>
                                            <option value="1" >手术流产</option>
                                            <option value="2" >药物流产</option>
                                            <option value="3" >自然流产</option>
                                        </select>
                                     </td>                               
                                     <td >流产时间：
                            			<input value="${userHistoryTakingPregnancy.abortionTime }" type="text" name="abortionTime" placeholder="日期选择">        
                                     </td>
                                     </tr>
                                     
                                     <tr>
                                    	<td >末次妊娠时间：
                                        <input value="${userHistoryTakingPregnancy.lastPregnancyTime }" name="lastPregnancyTime" type="text" placeholder="日期选择">
                                        </td>                               
                                     <td >新生儿死亡（次）：
                                     	<label><input style="width:10px;" type="radio" name="perinatalMortalityState" value="0" <c:if test="${userHistoryTakingPregnancy.perinatalMortalityState==0 }">checked</c:if>>无</label>
                            <label><input style="width:10px;" type="radio" name="perinatalMortalityState" value="1" <c:if test="${userHistoryTakingPregnancy.perinatalMortalityState==1 }">checked</c:if>> 有</label>
                            <span class="kong">次数 <input value="${userHistoryTakingPregnancy.perinatalMortality }" type="text" name="perinatalMortality"></span>
                                     </td>                               
                                     <td >死亡时间：
                            <input value="${userHistoryTakingPregnancy.deathTime }" type="text" placeholder="日期选择" name="deathTime">        
                                     </td>
                                     </tr>
                                     
                                     
                                     <tr>
                                    	<td >死亡原因：
                                        <input value="${userHistoryTakingPregnancy.deathReason }" name="deathReason" type="text">
                                        </td>                               
                                     <td >现有存活子女：
							                                     男<span class="kong"><input value="${userHistoryTakingPregnancy.nowSurviverBoy }" type="text" name="nowSurviverBoy"></span>
							                             女<span class="kong"><input value="${userHistoryTakingPregnancy.nowSurviverGirl }" type="text" name="nowSurviverGirl"></span>
                                     </td>                               
                                     <td >既往出生缺陷：
                            <label><input style="width:10px;" type="radio" name="alwaysBirthDefectsState" value="0" <c:if test="${userHistoryTakingPregnancy.alwaysBirthDefectsState==0 }">checked</c:if>>无</label>
                            <label><input style="width:10px;" type="radio" name="alwaysBirthDefectsState" value="1" <c:if test="${userHistoryTakingPregnancy.alwaysBirthDefectsState==1 }">checked</c:if>> 有</label>        
                                     </td>
                                     </tr>
                                     
                                     
                                     <tr>
                                    	<td >出生时间：
                                        <input value="${userHistoryTakingPregnancy.birthday }" name="birthday" id="birthday" type="text" placeholder="日期选择">
                                        </td>                               
                                     <td >缺陷描述：
                                     <input value="${userHistoryTakingPregnancy.defectsDescription }" type="text" name="defectsDescription" placeholder="请填写">
                                     </td>                               
                                     <td >结局：
			                            <label><input style="width:10px;" type="radio" name="ending" value="1" <c:if test="${userHistoryTakingPregnancy.ending==1 }">checked</c:if>>存活</label>
			                            <label><input style="width:10px;" type="radio" name="ending" value="2" <c:if test="${userHistoryTakingPregnancy.ending==2 }">checked</c:if>> 矫治痊愈</label>
			                            <label><input style="width:10px;" type="radio" name="ending" value="3" <c:if test="${userHistoryTakingPregnancy.ending==3 }">checked</c:if>> 残障</label>
			                            <label><input style="width:10px;" type="radio" name="ending" value="4" <c:if test="${userHistoryTakingPregnancy.ending==4 }">checked</c:if>> 夭折</label>        
                                     </td>
                                     </tr>
                                     
                                     
                                     <tr>
                                    	<td >既往妊娠合并症史：
                             <label><input style="width:10px;" type="radio" name="alwaysPregnancyHistoryState" value="0" <c:if test="${userHistoryTakingPregnancy.alwaysPregnancyHistoryState==0 }">checked</c:if>>无</label>
                            <label><input style="width:10px;" type="radio" name="alwaysPregnancyHistoryState" value="1" <c:if test="${userHistoryTakingPregnancy.alwaysPregnancyHistoryState==1 }">checked</c:if>/>有</label>
                                        <span class="kong">症状描述 <input value="${userHistoryTakingPregnancy.symptomDescription}" name="symptomDescription" type="text" placeholder="请填写"></span>
                                        </td>                               
                                     <td >其他孕产史：
                                     <input value="${userHistoryTakingPregnancy.otherPergnancyHistory }" name="otherPergnancyHistory" type="text" placeholder="请填写">
                                     </td>                               
                                     </tr>
                      </tbody>
                  </table>
                    </div>
                    
                    <div class="weight-title">其他信息</div>
                     <div class="panel-body mod_btn" >
                     	<div style="margin-bottom:25px;">
                         <table class="gw_xx">
                                 <tbody>
                                     <tr>
                                        <td >主诉：
                                             <input value="${userHistoryTakingOtherInfoF.chiefComplaint }" type="text" name="chiefComplaint" placeholder="请填写">
                                        </td>
                                        <td >现病史：
                                        	<input value="${userHistoryTakingOtherInfo.presentHistory }" type="text" name="presentHistory" placeholder="请填写">
                                        </td> 
                                     </tr>                             
                                 </tbody>                               
                        </table>
                       </div> 
                        <div id="live_inf">
                        	<span>既往病史</span>
                        	<div class="live_inftable">
                            <label><input style="width:10px;" type="radio" name="anamnesisState" value="0" <c:if test="${userHistoryTakingOtherInfo.anamnesisState==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="anamnesisState" value="1" <c:if test="${userHistoryTakingOtherInfo.anamnesisState==1 }">checked</c:if>> 有</label>
                        			<table class="gw_xx">
                                         <tbody id="anamnesisTable">
                                             <c:forEach items="${userPastHistoryList }" var="userPastHistory" varStatus="status">
                                         		<c:if test="${status.index%4>0 }">
                                                 <td height="45px;">
                                                 	<input style="width:10px;" type="checkbox" name="anamnesisChoose" value="${userPastHistory.id }"> ${userPastHistory.name }： 确诊时间 <input style="width:160px;" type="text" id="anamnesisTime${status.index }" value="" placeholder="日期选择">
                                                 </td>
                                                </c:if>
                                                <c:if test="${status.index%4==0 }">
                                                 </tr>
                                                </c:if>
                                         	</c:forEach>                      
                                         </tbody>
                                    </table>
                                     <label><input type="text" value="" id="otherAnamnesisText" placeholder="请输入其他疾病"> </label>确诊时间 <input style="width:160px;" type="text" value="" id="anamnesisOtherTime" placeholder="日期选择">
                                    <input name="anamnesis" type="hidden" id="anamnesis" value=""/>
                             <input name="otherAnamnesis" type="hidden" id="otherAnamnesis" value=""/>
                             </div>
                        </div>
                        <div id="live_inf">
                        	<span>家族病史</span>
                        	<div class="live_inftable">
                            <label><input style="width:10px;" type="radio" name="familyHistoryState" value="0" <c:if test="${userHistoryTakingOtherInfo.familyHistoryState==0 }">checked</c:if>>无</label>
                            <label><input style="width:10px;" type="radio"name="familyHistoryState" value="1" <c:if test="${userHistoryTakingOtherInfo.familyHistoryState==1 }">checked</c:if>> 有</label>
                            <div class="bs_tb">
                        			<table class="bordered" style="width:60%;">
                                    	<thead>
                                        	<th>疾病类型</th>
                                            <th>父亲</th>
                                            <th>母亲</th>
                                            <th>兄弟姐妹</th>
                                            <th>子女</th>
                                        </thead>
                                         <tbody id="familyHistoryTable">
                                              <c:forEach items="${userPastHistoryList }" var="userPastHistory" varStatus="status">
                                             <tr>
                                                 <td>${userPastHistory.name }<input type="hidden" name="userPastHistoryId" value="${userPastHistory.id }"/></td>
                                                 <td><label><input style="width:10px;" type="checkbox" value="1"></label></td>
                                                 <td><label><input style="width:10px;" type="checkbox" value="2"></label></td>
                                                 <td><label><input style="width:10px;" type="checkbox" value="3"></label></td>
                                                 <td><label><input style="width:10px;" type="checkbox" value="4"></label></td>
                                            </tr>
                                            </c:forEach>
                                         </tbody>
                                    </table>
                                     <input type="hidden" name="familyHistory" id="familyHistory"/>
                                   </div>
                                   <table class="gw_xx">
                                   <tr>
                                   		<td colspan="5">父亲其他家族病史：<input style="width:65%" value="${userHistoryTakingOtherInfo.fatherFamilyHistory }" name="fatherFamilyHistory" type="text" placeholder=""></td>
                                   		<td colspan="5">母亲其他家族病史：<input style="width:65%" value="${userHistoryTakingOtherInfo.motherFamilyHistory }" name="motherFamilyHistory" type="text" placeholder=""></td>
                                   </tr>
                                   <tr>
                                   		<td colspan="5">兄弟姐妹其他家族病史：<input style="width:65%" value="${userHistoryTakingOtherInfo.brotherFamilyHistory }" name="brotherFamilyHistory" type="text" placeholder=""></td>
                                        <td colspan="5">子女其他家族病史：<input style="width:65%" value="${userHistoryTakingOtherInfo.chirdFamilyHistory }" name="chirdFamilyHistory" type="text" placeholder=""></td>
                                   </tr>
                                   </table>
                                    
                             </div>
                        </div>
                        <div style="margin-bottom:25px;">
                         <table class="gw_xx">
                                 <tbody>
                                     <tr>
                                        <td width="60%">手术史：
                                        <label><input style="width:10px;" type="radio" name="operationHistoryState" value="0" <c:if test="${userHistoryTakingOtherInfo.operationHistoryState==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="operationHistoryState" value="1" <c:if test="${userHistoryTakingOtherInfo.operationHistoryState==1 }">checked</c:if>> 有</label>
                                         <span class="kong">手术时间 <input style="width:100px;" value="" name="operationTime" type="text" placeholder="日期选择"></span>
                                         <span class="kong">手术名称 <input value="" name="operationName" type="text" placeholder="请填写"></span>
                                        </td>
                                        <td width="40%">输血史：
                                        	<label><input style="width:10px;" type="radio" name="bloodTransfusionHistoryState" value="0" <c:if test="${userHistoryTakingOtherInfo.bloodTransfusionHistoryState==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="bloodTransfusionHistoryState" value="1" <c:if test="${userHistoryTakingOtherInfo.bloodTransfusionHistoryState==1 }">checked</c:if>> 有</label>
                                        </td>
                                     </tr>                             
                                 </tbody>                               
                        </table>
                       </div>
                        <div id="live_inf">
                        	<span>过敏史</span>
                        	<div class="live_inftable">
                            <label><input style="width:10px;" type="radio" name="allergicHistoryState" value="0" <c:if test="${userHistoryTakingOtherInfo.allergicHistoryState==0 }">checked</c:if>>无</label>
                                        <label><input style="width:10px;" type="radio" name="allergicHistoryState" value="1" <c:if test="${userHistoryTakingOtherInfo.allergicHistoryState==1 }">checked</c:if>> 有</label>
                        			<table class="gw_xx">
                                         <tbody id="allergicTypeTable">
                                         	<c:if test="${userHistoryTakingOtherInfo.allergicHistoryState==0 }">
	                                             <tr style="height:45px;">
	                                                 <td>过敏类型：
	                                                 <select>
	                                        				<option>请选择</option>
	                                            			<option>药物过敏</option>
	                                            			<option>花粉过敏</option>
	                                        		</select>
	                                                 </td>
	                                                 <td>过敏物名称：<input style="width:150px" type="text" value=""></td>
	                                                 <td>发现过敏日期：<input style="width:100px;" type="text" id="allergicHistoryStartTime1" value="" placeholder="日期选择"></td>
	                                                 <td>脱敏日期：<input style="width:100px;" type="text" id="allergicHistoryEndTime1" value="" placeholder="日期选择"></td>
	                                                 <td><button type="button" class="btn btn-info" id="allergicHistoryBtnAdd"><i class="icon-plus"></i> 添加</button></td>
	                                            </tr> 
                                            </c:if>
                                         </tbody>
                                    </table>
                                     <input type="hidden" name="allergicType" id="allergicType"/>
                             </div>
                        </div>
                    </div>
                     	
            		</div>     
  			</form>
            </div>
            
           
            <div class="gw_btn">
            <input class="btn btn-default" style="width: 85px; height:40px;padding:0px 0px;" readonly value="取消" type="button">
            <input class="btn btn-pink" style="width: 85px; height:40px;padding:0px 0px; margin-left:20px;" readonly value="保存" id="updateArchiveHistoryTakingSubmit" type="button">
            </div>
            </div>
            
      
	    </div>
    	
</div>
<!-- 引入页尾 -->

 <script type="text/javascript">
     $(function(){
		//将所有id属性包含time的字段，初始化日期选择框
			$("input[id*='Time']").each(function(){
				laydate({
				    elem: '#'+$(this).attr("id"), 
				    event: 'focus' ,
					max: laydate.now(),
				});
			});
		});
	</script>
</body>