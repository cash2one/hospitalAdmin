<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/menu.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/daterangepicker-bs3.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/laydate-v1.1/laydate/laydate.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/addArchiveHistoryTaking.js"></script>
  <style type="text/css">
	html{overflow-x: scroll;}
	body {min-width: 1660px;overflow-y: hidden;}
	.message-dialog{width: 800px;height: 500px;}
 	*{padding: 0; margin: 0; }
	.panel{margin-bottom:0px;}
	.modal {
    position: fixed;
    top: 20%;
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
    
    margin: 30px auto;
}
.modal-header {
    min-height: 16.42857143px;
    padding: 15px;
    border-bottom: 1px solid #fff;
}
input,select{ height:25px; padding:0 5px;}
input[type="radio"],input[type="checkbox"]{ margin:0;}
label{margin-right:10px;}
.gw_btn { width:280px;}
.panel .panel-default table th{text-align: center;}
.panel .panel-default .bordered td {font-size: 14px;text-align: center; border:1px solid #ccc;}
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
        	<div class="gw_rote"> <span class="float-left" style="font-size:16px;">档案管理 >新建档案 > <a style=" color:#ff7ea5">病史询问</a></span></div>
        	<div class="panel-body">	
                
            <div class="panel panel-default float-left" style="margin:15px 0px;width: 100%;">
                <div class=" float-left" style="width:100%;">
                        <ul id="myTab" class="nav nav-tabs">
   							<li><a href="${pageContext.request.contextPath}/pregnant/archiveBaseInfo">基本信息</a></li>
   							<li class="active"><a href="#" >病史询问</a></li>
                            <li><a href="${pageContext.request.contextPath}/pregnant/archiveTestCase" >初检情况</a></li>
  						</ul>
                        
                </div>
		     <form action="${pageContext.request.contextPath}/pregnant/saveArchiveHistoryTakingInfo" id="archiveHistoryTakingForm">
		     <input type="hidden" name="archiveId" value="${archiveBaseId }"/>
		     <input type="hidden" name="hospitalMappingId" value="${archiveId }"/>
             <div id="cont_roll" class="panel panel-default float-left" style=" margin:15px;width: 98%;">  
                	<div class="weight-title">本次妊娠情况</div>
                    <div class="panel-body">
                        <table class="gw_xx">
                                    <tbody>
                                    <tr>
                                        <td >初潮年龄：<input type="text" value="" name="menophaniaAge"/></td>
                                        <td> 月经周期：<input style="width: 50px;display:inline-block;" type="text"  value="0" name="mensesSpace" id="mensesSpace"/>/
                                      	  <input style="width: 50px;display:inline-block;" type="text"  value="0" name="mensesDays" id="mensesDays"/>
                                      	 </td> 
                                    </tr>
                                    <tr>
                                    	<td >初次确诊怀孕时间：
                                        	<input style="width: 160px; display:inline-block; height:28px;" name="firstAffirmPregnancyDate" id="startTime" placeholder="日期选择" class="form-control" value="" type="text">
                                        </td> 
                                      	<td>孕<input style="width: 50px;display:inline-block;" type="text"  value="0" name="menstrualCycleWeek" id="menstrualCycleWeek"/>周
                                      	  <input style="width: 50px;display:inline-block;" type="text"  value="0" name="menstrualCycleDay" id="menstrualCycleDay"/>天 
                                        	<input type="hidden" name="pregnancyTime" id="pregnancyTime" value=""/>
                                      	  </td>
                                    </tr>
                                    <tr>
                                        <td >早孕反应：
                                        <label><input style="width:10px;" type="radio" name="morningSicknessState" value="0" checked>无</label>
                                        <label><input style="width:10px;" type="radio" name="morningSicknessState" value="1"> 有</label>
							                                        孕<input style="width: 50px;display:inline-block;" type="text"   value="0" id="morningSicknessWeek"/>
							                                        周<input style="width: 50px;display:inline-block;" type="text"   value="0" id="morningSicknessDay"/>天 
							             <input type="hidden" name="morningSicknessTime" id="morningSicknessTime" value=""/>
							             </td>
							             <td >初感胎动：
                                        <label><input style="width:10px;" type="radio" name="quickening" value="0" checked>无</label>
                                        <label><input style="width:10px;" type="radio" name="quickening" value="1"> 有</label>
                                      	  孕<input style="width: 50px;display:inline-block;" type="text"   value="0" id="quickeningWeek"/>
                                      	  周<input style="width: 50px;display:inline-block;" type="text"   value="0" id="quickeningDay"/>天 
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
                                        <label><input style="width:10px;" type="radio" name="isAbnormal" value="0" checked>无</label>
                                        <label><input style="width:10px;" type="radio" name="isAbnormal" value="1"> 有</label>
                                        <input type="text" value="" placeholder="请输入异常内容" name="abnormalContent" style="width:60%;">
                                        </td>
                                        <td width="50%">叶酸服用：
                                        <label><input style="width:10px;" type="radio" name="takingFolicAcid" value="0" checked>无</label>
                                        <label><input style="width:10px;" type="radio" name="takingFolicAcid" value="1"> 有</label>
                                        <span class="kong">孕前：
                                        <label><input style="width:10px;" type="checkbox" name="folicAcidProgestation" value="1"> 1月</label>
                                        <label><input style="width:10px;" type="checkbox" name="folicAcidProgestation" value="2"> 2月</label>
                                        <label><input style="width:10px;" type="checkbox" name="folicAcidProgestation" value="3"> 3月</label>
                                        </span>
                                        <span class="kong">孕早期：
                                        <label><input style="width:10px;" type="checkbox" name="folicAcidMorningSickness" value="1"> 1月</label>
                                        <label><input style="width:10px;" type="checkbox" name="folicAcidMorningSickness" value="2"> 2月</label>
                                        <label><input style="width:10px;" type="checkbox" name="folicAcidMorningSickness" value="3"> 3月</label>
                                        </span>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<td>第一次检查尿检阳性是停经后：<span class="kong"><input type="text"  value="" style="width:50px;" name="firstExamineAfterMenelipsis"></span>天</td>
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
			                                        <label><input style="width:10px;" type="radio" name="feverState" value="0" checked>无</label>
			                                        <label><input style="width:10px;" type="radio" name="feverState" value="1"> 有</label>
			                                        <span class="kong" style="top:0px;">伴随症状：<input type="text" value="" placeholder="请输入伴随症状" name="feverDevelop"></span>
                                                 </td>                   
                                                 <td width="40%">阴道出血：
			                                         <label><input style="width:10px;" type="radio" name="colporrhagiaState" value="0" checked>无</label>
			                                        <label><input style="width:10px;" type="radio" name="colporrhagiaState" value="1"> 有</label>
			                                        <span class="kong" style="top:0px;">伴随症状：<input type="text" value="" placeholder="请输入伴随症状" name="colporrhagiaDevelop"></span>
         													
                                                 </td>
                                             </tr>
                                             <tr>
                                             <td width="60%">猫、狗等动物接触史：
		                                        <label><input style="width:10px;" type="radio" name="animalContact" value="0" checked>无</label>
		                                        <label><input style="width:10px;" type="radio" name="animalContact" value="1"> 有</label> 
		                                        <span class="kong" style="top:0px;">接触时间：
                                       				 孕<input style="width: 50px;display:inline-block;" type="text"   value="0" id="animalContactStartWeek">
                                       				 周<input style="width: 50px;display:inline-block;" type="text"   value="0" id="animalContactStartDay">天 
                                       				 至 孕<input style="width: 50px;display:inline-block;" type="text"   value="0" id="animalContactEndWeek">
                                       				 周<input style="width: 50px;display:inline-block;" type="text"   value="0" id="animalContactEndDay">天，共<strong id="animalContactDays">0</strong>天
                                       				 <input type="hidden" name="animalContactTime" id="animalContactTime" value=""/>
                                        		</span>                
                                                </td>
                                                <td width="40%">糖尿病：
		                                          <label><input style="width:10px;" type="radio" name="diabetesMellitus" value="0" checked>无</label>
		                                        <label><input style="width:10px;" type="radio" name="diabetesMellitus" value="1"> 有</label>         
                                                 </td>                 
                                            </tr>
                                         </tbody>
                                    </table>
                             </div>
                        </div>
                        
                        <div id="live_inf">
                        	<span>服用可能致畸药物</span>
                        	<div class="live_inftable">
                            <label><input style="width:10px;" type="radio" name="takeTeratogenicDrug" value="0" checked>无</label>
                            <label><input style="width:10px;" type="radio" name="takeTeratogenicDrug" value="1">有</label>
                        			<table class="gw_xx">
                                         <tbody id="takeTeratogenicDrugTable">
                                             <tr style="height:45px;">
                                                 <td width="30%"><input style="width:80%;"  type="text" value=""  placeholder="请输入药名"></td>                   
                                                 <td width="6%">服用时间段：</td>
                                                 <td width="6%"><input style="width: 160px;height:28px;" type="text"  id="takeTeratogenicDrugStartTime1" placeholder="开始日期" class="form-control" value="" ></td>  
                                         		 <td width="3%">至</td> 
                                         		 <td width="10%"><input style="width: 160px" type="text"  value=""  id="takeTeratogenicDrugEndTime1" placeholder="结束日期"></td>
                                                <td width="15%"><button type="button" class="btn btn-info" id="takeTeratogenicDrugBtnAdd"><i class="icon-plus"></i> 添加药物</button></td>
                                            </tr>
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
                                        <label><input style="width:10px;" type="radio" name="contactPhysicsHarmful" value="0" checked>无</label>
                                        <label><input style="width:10px;" type="radio" name="contactPhysicsHarmful" value="1"> 有</label>
                                        <span class="kong">
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="1"> 噪声</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="2"> 振动</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="3"> x射线</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="4"> 电磁辐射</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="5"> 高温</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="6"> 微波</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactPhysicsHarmfulChoose" value="7"> 极低温</label>
                                                                    <input value="" type="text" name="contactPhysicsHarmfulOtherContent" id="contactPhysicsHarmfulOther" placeholder="输入其他">
                                        </span>
                                        <input type="hidden" name="contactPhysicsHarmfulContent" id="contactPhysicsHarmfulContent" value=""/>
                                        </td>
                                       </tr>
                                    <tr>
                                    	<td>接触高浓度工业毒物：
                                        	<label><input style="width:10px;" type="radio" name="contactIndustrialToxicant" value="0" checked>无</label>
                                        <label><input style="width:10px;" type="radio" name="contactIndustrialToxicant" value="1"/> 有</label>
                                        <span class="kong">
                                                                    <label><input style="width:10px;" type="checkbox" name="contactIndustrialToxicantChoose" value="1"> 重金属</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactIndustrialToxicantChoose" value="2"> 农药</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactIndustrialToxicantChoose" value="3"> 有机溶剂</label>
                                                                    <label><input style="width:10px;" type="checkbox" name="contactIndustrialToxicantChoose" value="4"> 制药</label>
                                                                    <input value="" type="text"  name="contactIndustrialToxicantOtherContent" id="contactIndustrialToxicantOther" placeholder="输入其他">
                                          </span>
                                          <input type="hidden" name="contactIndustrialToxicantContent" id="contactIndustrialToxicantContent" value=""/>
                                        </td>
                                        <td>
                                        	环境新装修：
                                        <label><input style="width:10px;" type="radio" name="environmentalNewDecoration" value="0" checked>无</label>
                                        <label><input style="width:10px;" type="radio" name="environmentalNewDecoration" value="1"/> 有</label>
                                        </td>
                                        <td>
                                        	吸毒：
                                        <label><input style="width:10px;" type="radio" name="takeDrugs" value="0" checked>无</label>
                                        <label><input style="width:10px;" type="radio" name="takeDrugs" value="1"/> 有</label>
                                        </td>
                                    </tr>
                                 </tbody>
                         </table>
                     </div>
                        
                     <div id="live_inf">
                        	<span>饮酒史</span>
                        	<div class="live_inftable">
                            <label><input style="width:10px;" type="radio" name="drinkHistoryState" value="0" checked>无</label>
                            <label><input style="width:10px;" type="radio" name="drinkHistoryState" value="1"/> 有</label>
                        			<table class="gw_xx">
                                         <tbody>
                                             <tr style="height:45px;">
                                                 <td>开始时间：<input type="text" value="" style="width:160px;"  placeholder="日期选择" name="drinkStartTime" id="drinkStartTime"/></td>
                                                 <td>白酒（克）：<input type="text" step="0.01"  value="0" name="whiteSpirit"/></td>
                                                 <td>红酒（克）：<input type="text" step="0.01"  value="0" name="redSpirit"/></td>
                                                 <td>啤酒（克）：<input type="text" step="0.01"  value="0" name="beer"/></td>
                                            </tr>
                                            <tr style="height:45px;">
                                                 <td>饮酒频率：<input type="text" value="" name="drinkFrequency"/></td>
                                                 <td>饮酒描述：<input type="text" value="" name="drinkDescription"/></td>
                                                 <td>戒酒时段：<input type="text" value=""  style="width:160px;" name="abstinenceStartTime"/> 至 <input type="text"  style="width:160px;" value="" name="abstinenceEndTime"/></td>
                                            </tr>
                                         </tbody>
                                    </table>
                             </div>
                        </div>
                     <div id="live_inf">
                        	<span>吸烟史</span>
                        	<div class="live_inftable">
                            <label><input style="width:10px;" type="radio" name="smokeHistoryState" value="0" checked>无</label>
                            <label><input style="width:10px;" type="radio" name="smokeHistoryState" value="1"> 主动</label>
                            <label><input style="width:10px;" type="radio" name="smokeHistoryState" value="2"> 被动</label>
                        			<table class="gw_xx">
                                         <tbody>
                                             <tr style="height:45px;">
                                                 <td>开始时间：<input type="text" style="width:160px;" value=""  placeholder="日期选择" name="smokeStartTime" id="smokeStartTime1"></td>
                                                 <td>吸烟数（支/天）：<input type="text"  value="0" name="smokeAmount"></td>
                                                 <td>吸烟时段：<input type="text" style="width:160px;" value="" id="smokeStartTime2"> 至 <input type="text" style="width:160px;" name="smokeEndTime" id="smokeEndTime" value=""></td>
                                                 <td>吸烟描述：<input type="text"  value="" name="smokeDescription"></td>
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
                                        <label><input style="width:10px;" type="radio" name="virusInfectionState" value="0" checked>无</label>
                                        <label><input style="width:10px;" type="radio" name="virusInfectionState" value="1">有</label>
                                        <span class="kong">
                                             <label><input style="width:10px;" type="checkbox" name="virusInfectionChoose" value="1"> 流感</label>
                                             <label><input style="width:10px;" type="checkbox" name="virusInfectionChoose" value="2"> 皮疹</label>
                                             <label><input style="width:10px;" type="checkbox" name="virusInfectionChoose" value="3"> 疱疹</label>
                                             
                                             <input value="" type="text" name="herpes" placeholder="填写疱疹部位">
                                             <span class="kong">
                                             	患病时间：<input value="" type="text" style="width:160px;" placeholder="日期选择" name="virusInfectionTime" id="virusInfectionTime">
                                             </span>
                                        </span>
                                        <input type="hidden" name="virusInfection" id="virusInfection" value=""/>
                                        </td>
                                  </tr>
                                  <tr>
                                  	<td>其他有害因素：<input value="" type="text" placeholder="请输入其他因素" name="otherHarmfulFactors"></td>
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
                                     <td >孕次（含本次）：<input style="width:80px;" type="text" value="0" name="pregnancyCount"></td>                               
                                     <td >孕产史：
                            <label><input style="width:10px;" type="radio" name="pregnancyHistoryState" value="0" checked>无</label>
                            <label><input style="width:10px;" type="radio" name="pregnancyHistoryState" value="1"/> 有</label>
                                      </td>                               
                                     <td >不孕不育史：
                             <label><input style="width:10px;" type="radio" name="infertilityHistoryState" value="0" checked>无</label>
                            <label><input style="width:10px;" type="radio" name="infertilityHistoryState" value="1"/> 有</label>
                            <input value="" type="text" name="infertilityHistory">        
                                     </td>
                                     </tr>
                                     <tr>                               
                                     <td colspan="3">产次（次）：
                                    <span class="kong">阴道产 <input style="width:80px;" type="text"   value="0" name="vaginaProduced"></span>
									<span class="kong">阴道手术产 <input style="width:80px;" type="text"  value="0"  name="vaginaOperationProduced"></span>
                                    <span class="kong">剖宫产 <input style="width:80px;" type="text"   value="0" name="caesareanSection"></span>
                                    <span class="kong">产钳 <input style="width:80px;" type="text"   value="0" name="obstetricForceps"></span>
									<span class="kong">宫外孕 <input style="width:80px;" type="text"   value="0" name="ectopicPregnancy"></span>
                                    <span class="kong">其他助产 <input style="width:80px;" type="text"   value="0" name="otherDeliver"></span>
                                     </td>                               
                                    </tr>
                                    <tr>
                                    	<td >末次手术时间：<input type="text" value=""  style="width:160px;" placeholder="日期选择" name="operationLastTime" id="operationLastTime"></td>                               
                                     <td >手术指征：<input type="text" value="" name="operationIndication"></td>                               
                                     <td >并发症：
			                             <label><input style="width:10px;" type="radio" name="complicationState" value="0" checked>无</label>
			                            <label><input style="width:10px;" type="radio" name="complicationState" value="1"> 有</label>
			                            <input value="" type="text">        
                                     </td>
                                     </tr>
                                     <tr>
                                     <td>自然流产（次）：<input style="width:80px;" type="text"  value="0" name="spontaneousAbortion"></td>
									<td>人工流产（次）： <input style="width:80px;" type="text"  value="0" name="inducedAbortion"></td>
                                    <td>引产（次）： <input style="width:80px;" type="text"  value="0" name="inducedLabour"></td>
                                    </tr>
                                     
                                     <tr>
                                    <td>早产（次）： <input style="width:80px;" type="text"  value="0" name="prematureLabour"></td>
									<td>死胎/产（次）： <input style="width:80px;" type="text"  value="0" name="stillbirthLabour"></td>
                                    <td>胚胎停育（次）： <input style="width:80px;" type="text"  value="0" name="embryoDamage"></td>
                                    </tr>
                                     <tr>
                                    	<td >末次流产：
                                        <label><input style="width:10px;" type="radio" name="lastAbortionState" value="0" checked>无</label>
                           				<label><input style="width:10px;" type="radio" name="lastAbortionState" value="1"> 有</label>
                                        </td>                               
                                     <td >流产类型：
                                     	<select name="abortionType">
                                        	<option value="0">请选择</option>
                                            <option value="1">手术流产</option>
                                            <option value="2">药物流产</option>
                                            <option value="3">自然流产</option>
                                        </select>
                                     </td>                               
                                     <td >流产时间：
                            			<input value="" type="text"  style="width:160px;"  placeholder="日期选择" name="abortionTime" id="abortionTime">        
                                     </td>
                                     </tr>
                                     <tr>
                                    	<td >末次妊娠时间：
                                        <input value="" type="text"  style="width:160px;" placeholder="日期选择" name="lastPregnancyTime" id="lastPregnancyTime">
                                        </td>                               
                                     <td >新生儿死亡（次）：
                                     	<label><input style="width:10px;" type="radio" name="perinatalMortalityState" value="0" checked>无</label>
			                            <label><input style="width:10px;" type="radio" name="perinatalMortalityState" value="1"> 有</label>
			                            <span class="kong">次数 <input value="0" type="text" name="perinatalMortality"></span>
                                     </td>                               
                                     <td >死亡时间：
                            			<input value="" type="text"  style="width:160px;" placeholder="日期选择" name="deathTime" id="deathTime">        
                                     </td>
                                     </tr>
                                     <tr>
                                    	<td >死亡原因：
                                        <input value="" type="text" name="deathReason">
                                        </td>                               
                                     <td >现有存活子女：
								                              男<span class="kong"><input value="0" type="text" name="nowSurviverBoy"   ></span>
								                             女<span class="kong"><input value="0" type="text" name="nowSurviverGirl"  ></span>
                                     </td>                               
                                     <td >既往出生缺陷：
			                            <label><input style="width:10px;" type="radio" name="alwaysBirthDefectsState" value="0" checked>无</label>
			                            <label><input style="width:10px;" type="radio" name="alwaysBirthDefectsState" value="1"> 有</label>        
                                     </td>
                                     </tr>
                                     <tr>
                                    	<td >出生时间：
                                        <input value="" type="text" style="width:160px;"  placeholder="日期选择" name="birthday" id="birthday">
                                        </td>                               
                                     <td >缺陷描述：
                                     <input value="" type="text" placeholder="请填写" name="defectsDescription">
                                     </td>                               
                                     <td >结局：
			                            <label><input style="width:10px;" type="radio" name="ending" value="1" checked>存活</label>
			                            <label><input style="width:10px;" type="radio" name="ending" value="2"> 矫治痊愈</label>
			                            <label><input style="width:10px;" type="radio" name="ending" value="3"> 残障</label>
			                            <label><input style="width:10px;" type="radio" name="ending" value="4"> 夭折</label>        
			                          </td>
                                     </tr>
                                     <tr>
                                    	<td >既往妊娠合并症史：
			                             <label><input style="width:10px;" type="radio" name="alwaysPregnancyHistoryState" value="0" checked>无</label>
			                            <label><input style="width:10px;" type="radio" name="alwaysPregnancyHistoryState" value="1"/>有</label>
                                        <span class="kong">症状描述 <input value="" type="text" placeholder="请填写" name="symptomDescription"></span>
                                        </td>                               
                                     <td >其他孕产史：
                                     <input value="" type="text" placeholder="请填写" name="otherPergnancyHistory">
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
                                             <input value="" type="text" placeholder="请填写" name="chiefComplaint">
                                        </td>
                                        <td >现病史：
                                        	<input value="" type="text" placeholder="请填写" name="presentHistory">
                                        </td>
                                     </tr>                             
                                 </tbody>                               
                        </table>
                       </div> 
                        <div id="live_inf">
                        	<span>既往病史</span>
                        	<div class="live_inftable">
                            <label><input style="width:10px;" type="radio" name="anamnesisState" value="0" checked>无</label>
                                   <label><input style="width:10px;" type="radio" name="anamnesisState" value="1"> 有</label>
                        			<table class="gw_xx">
                                         <tbody>
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
                             </div>
                             <input name="anamnesis" type="hidden" id="anamnesis" value=""/>
                             <input name="otherAnamnesis" type="hidden" id="otherAnamnesis" value=""/>
                        </div>
                        <div id="live_inf">
                        	<span>家族病史</span>
                        	<div class="live_inftable">
                            <label><input style="width:10px;" type="radio" name="familyHistoryState" value="0" checked>无</label>
                            <label><input style="width:10px;" type="radio"name="familyHistoryState" value="1"> 有</label>
                            <div class="bs_tb">
                        			<table class="bordered" style="width:60%;" id="familyHistoryTable">
                                    	<thead>
                                        	<th>疾病类型</th>
                                            <th>父亲</th>
                                            <th>母亲</th>
                                            <th>兄弟姐妹</th>
                                            <th>子女</th>
                                        </thead>
                                         <tbody>
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
                                   		<td colspan="5">父亲其他家族病史：<input style="width:65%" value="" type="text" placeholder="" name="fatherFamilyHistory"></td>
                                   		<td colspan="5">母亲其他家族病史：<input style="width:65%" value="" type="text" placeholder="" name="motherFamilyHistory"></td>
                                   </tr>
                                   <tr>
                                   		<td colspan="5">兄弟姐妹其他家族病史：<input style="width:65%" value="" type="text" placeholder="" name="brotherFamilyHistory"></td>
                                        <td colspan="5">子女其他家族病史：<input style="width:65%" value="" type="text" placeholder="" name="chirdFamilyHistory"></td>
                                   </tr>
                                   </table>
                                    
                             </div>
                        </div>
                        
                        
                        <div style="margin-bottom:25px;">
                         <table class="gw_xx">
                                 <tbody>
                                     <tr>
                                        <td width="60%">手术史：
                                        <label><input style="width:10px;" type="radio" name="operationHistoryState" value="0" checked>无</label>
                                        <label><input style="width:10px;" type="radio" name="operationHistoryState" value="1"> 有</label>
                                         <span class="kong">手术时间 <input style="width:160px;" value="" type="text" placeholder="日期选择" name="operationTime" id="operationTime"></span>
                                         <span class="kong">手术名称 <input value="" type="text" placeholder="请填写" name="operationName"></span>
                                        </td>
                                        <td width="40%">输血史：
                                        	<label><input style="width:10px;" type="radio" name="bloodTransfusionHistoryState" value="0" checked>无</label>
                                        <label><input style="width:10px;" type="radio" name="bloodTransfusionHistoryState" value="1"> 有</label>
                                        </td>
                                     </tr>                             
                                 </tbody>                               
                        </table>
                       </div>
                        

                        <div id="live_inf">
                        	<span>过敏史</span>
                        	<div class="live_inftable">
                            <label><input style="width:10px;" type="radio" name="allergicHistoryState" value="0" checked>无</label>
                                        <label><input style="width:10px;" type="radio" name="allergicHistoryState" value="1"> 有</label>
                        			<table class="gw_xx" id="allergicTypeTable">
                                         <tbody>
                                             <tr style="height:45px;">
                                                 <td>过敏类型：
                                                 <select>
                                        				<option value="0">请选择</option>
                                            			<option value="1">吸入式过敏</option>
                                            			<option value="2">食入式过敏</option>
                                            			<option value="3">接触式过敏</option>
                                            			<option value="4">注射式过敏</option>
                                            			<option value="5">自身组织抗原</option>
                                        		</select>
                                                 </td>
                                                 <td width="40%">过敏物名称：<input style="width:70%" type="text" value=""></td>
                                                 <td>发现过敏日期：<input style="width:160px;" type="text" value="" placeholder="日期选择" id="allergicHistoryStartTime1"></td>
                                                 <td>脱敏日期：<input style="width:160px;" type="text" value="" placeholder="日期选择" id="allergicHistoryEndTime1"></td>
                                                 <td><button type="button" class="btn btn-info" id="allergicHistoryBtnAdd"><i class="icon-plus"></i> 添加</button></td>
                                            </tr> 
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
            <input class="btn btn-default oneline" style="width: 85px; height:40px;padding:0px 0px;" readonly value="上一步" type="button" onclick="window.location.href='${pageContext.request.contextPath}/pregnant/archiveHistoryTaking'"/>
            <input class="btn btn-default oneline" style="width: 85px; height:40px;padding:0px 0px;" readonly value="下一步" type="button" id="submitBtn" data-toggle="modal" 
   					data-target="#myModal" onclick="window.location.href='${pageContext.request.contextPath}/pregnant/archiveTestCase'"/>
            </div>
            </div>
	    </div>
    	
</div>

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
