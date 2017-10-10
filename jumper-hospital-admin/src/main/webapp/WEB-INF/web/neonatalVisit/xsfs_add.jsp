<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>新生儿访视</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
html{overflow-x: scroll;}
body {min-width: 1660px;overflow-y: hidden;}
.message-dialog{width: 800px;height: 500px;}
 *{padding: 0; margin: 0; }
.panel{margin-bottom:0px;}
.modal-body table{border-collapse:collapse; border:0px solid #D0D0D0;}
.modal-body table td{border-top:0;border-right:1px solid #D0D0D0;border-bottom:1px solid #D0D0D0;border-left:0;text-align:center;font-size:14px;}
.modal-body table tr.lastrow td{border-bottom:0;}
.modal-body table tr td.lastCol{border-right:0;}
.modal { position: fixed;top: 5%;right: 0;bottom: 0; left: 0;z-index: 1050;display: none;overflow: hidden; -webkit-overflow-scrolling: touch; outline: 0;}
.modal-dialog {width: 50%; margin: 30px auto;height:285px;}
.modal-header {min-height: 16.42857143px;padding: 15px;border-bottom: 1px solid #fff;}
.modal-body { height:185px; overflow-y:auto; padding:0px;}
.modal-content { height:305px;}
input,select{ height:25px; padding:0 5px;}
input[type="radio"],input[type="checkbox"]{ margin:0;}
label{margin-right:10px;}
</style>
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/menu.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/daterangepicker-bs3.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
$(function(){
$(".mod_btn a").click(function(){
    $(this).toggleClass("active");
});
});
</script>
</head>
<body style="background: #F0F0F0;">
<div class="">
<div class="row row-offcanvas row-offcanvas-right" style="margin-top: 20px;">                
	  	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
	           <ul class="page-sidebar-menu">
	              <li class="active">
		              	<a href="${pageContext.request.contextPath}/pregnant/archiveIndex">
								 <i class="icon-plus-sign-alt"></i>&nbsp; <span class="title">档案管理</span>
						</a>
				 </li>
	           </ul>
	       </div>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
		<div class="panel" >
        	<div class="gw_rote"> <span class="float-left" style="font-size:16px;">档案管理 > 档案详情 > <a style=" color:#ff7ea5">新生儿访视</a></span></div>
			<div class="panel-body">	
		        <div class="panel panel-default" style="margin:15px 0px;">
		        <div class="panel-body">
		                <table class="gw_xx">
		                        <tbody>
			                    	  <tr>
										<td colspan="4">姓名： ${userBaseInfo.name }</td>
										<td colspan="4">联系电话：${userBaseInfo.mobile }</td>
										<td colspan="4">末次月经：${userBaseInfo.lastPeriod }</td>
										<td colspan="4">预产期：  <fmt:formatDate value="${userBaseInfo.pregnantDate }" pattern="yyyy-MM-dd"/></td>
									</tr>
									<tr>
										<td colspan="4">当前孕周：${pregnantWeek }周 ${pregnantDay }天</td>
										<td colspan="4">状态：
											<c:if test="${userBaseInfo.pregnancyState==0 }">怀孕中</c:if>
											<c:if test="${userBaseInfo.pregnancyState==1 }">已分娩</c:if>
										</td>
										<td colspan="4">分娩日期：${userBaseInfo.deliveryTime }</td>
									</tr>   
		                		</tbody>
		             </table>
		        </div>
		    </div>
		    <div class="panel panel-default float-left" style="margin:15px 0px;width: 100%;">
		        <div class=" float-left" style="width:100%;">
		        			<%@ include file="/WEB-INF/web/neonatalVisit/xsfs_url.jsp"%>
		        </div>
		
			<!-- form表单内容 -->
			<form id="submitForm"  method="post">
				<input type="hidden" value="${userId }" id="userId" name="userId" />
				<input type="hidden" value="${archiveId}" id="archiveId" />
				<input type="hidden" value="${archiveBaseId}" id="archiveBaseId" /><!-- 档案id -->
		     <div class="panel panel-default float-left" style=" margin:15px;width: 98%;">  
		        	<div class="weight-title">基本情况</div>
		            <div class="panel-body">
		                <table class="gw_xx">
		                           <tbody>
		                               <tr>
		         						   <td>新生儿姓名：<span style="color:red;">*</span>
		         						     <input type="text"   id="userName" name="userName" placeholder="请输入"  datatype="*1-6" errormsg="新生儿姓名至少1个字符,最多6个字符！" >
		         						   </td>
		                                   <td>性别：<span style="color:red;">*</span>
		                                      	<label><input style="width:12px;" type="radio" name="babySex" value="1" > 男</label>
	                                            <label><input style="width:12px;" type="radio" name="babySex" value="2" > 女</label>
	                                            <label><input style="width:12px;" type="radio" name="babySex" value="0" > 未知</label>
		                                   </td>
		                                   <td>出生日期：<span style="color:red;">*</span>
		                                  	   <input id="d1" placeholder="请选择日期" name="babyBirthday_" readonly="readonly" >
		                                   </td>    
		                               </tr>                           
		                                <tr>
		                                    <td>助产机构：
		                                   		<input type="text"   id="birthWeeks"  datatype="*0-50" errormsg="请输入助产机构名称"   ignore="ignore" name="midwiferyName" placeholder="请输入">
		                                    </td>
		                                    <td>分娩孕周：孕 <span class="kong">
		                                    	<input  style="width:100px;" id="birthWeeks"  datatype="/^\+?[0-9]\d*$/" errormsg="请输入数字"  ignore="ignore" name="birthWeeks" placeholder="请输入数字"> 
		                                    </span>周
		                                     <span class="kong">
		                                     	<input  style="width:100px;"  id="visitDay"   datatype="/^\+?[0-9]\d*$/" errormsg="请输入数字" ignore="ignore" name="visitDay" placeholder="请输入数字" >
		                                     </span> 天 </td>
		                                </tr> 
		                                <tr>
		                                    <td colspan="2">孕产期异常情况:
		                                    <label><input type="radio" name="pregrancyACase" value="1" class="checkNo1"/> 无</label>
		                                    <label><input type="radio" name="pregrancyACase" value="2"  class="checkOnly1"/>有 </label>
		            						<label><input style="width:13px;" type="checkbox"  disabled="disabled" name="pregrancyCaseRemark" value="1" class="no1" > 高血压</label>
		            						<label><input style="width:13px;" type="checkbox"  disabled="disabled" name="pregrancyCaseRemark" value="2" class="no1" > 血糖异常</label>
		            						<label><input style="width:13px;" type="checkbox"  disabled="disabled" name="pregrancyCaseRemark" value="3" class="no1" > 甲亢</label>
		            						<label><input style="width:13px;" type="checkbox"  disabled="disabled" name="pregrancyCaseRemark" value="4" class="no1" > 甲低</label>
		            						<label><input style="width:13px;" type="checkbox"  disabled="disabled" name="pregrancyCaseRemark" value="5" class="no1" > 心脏病</label>
		            						<label><input style="width:13px;" type="checkbox"  disabled="disabled" name="pregrancyCaseRemark" value="6" class="no1" > 胆淤</label>
		            						<label><input style="width:13px;" type="checkbox"  disabled="disabled" name="pregrancyCaseRemark" value="7" class="no1" > 产后出血</label>
		            						<input type="text" name="motherCase" class="clear" placeholder="其他情况" disabled="disabled"  datatype="*0-50" errormsg="最多50个字符！" ignore="ignore">
		                                    </td>
		                                </tr> 
		                           </tr>                                 
		                   </tbody>
		                 </table>
		             </div>
			             <div class="weight-title">出生记录</div>
			            <div class="panel-body">
			                <table class="gw_xx">
			         			 <tbody>
		              					<tr>
		                                     <td>出生情况：  
			                                    <label><input style="width:12px;" type="radio" name="babyCase" value="1" checked> 顺产</label>
			                  					<label><input style="width:12px;" type="radio" name="babyCase" value="2" > 胎吸</label>
			                  					<label><input style="width:12px;" type="radio" name="babyCase" value="3" > 臀助产</label>
			                  					<label><input style="width:12px;" type="radio" name="babyCase" value="4" > 臀牵引</label>
			                  					<label><input style="width:12px;" type="radio" name="babyCase" value="5" > 产钳</label>
			                  					<label><input style="width:12px;" type="radio" name="babyCase" value="6" > 剖宫产</label>
			                                 	<input type="text" name="babyCaseRemark" placeholder="填写其他"  datatype="*0-50" errormsg="最多50个字符！" ignore="ignore">
		                                     </td>
		                                     <td>胎数： 
		                                      	<label><input style="width:12px;" type="radio" name="embryoNum" value="1" checked> 单胎</label>
		                  					  	<label><input style="width:12px;" type="radio" name="embryoNum" value="2" > 双胎</label>
		                  					  	<label><input style="width:12px;" type="radio" name="embryoNum" value="3" > 多胎</label>
		                                      	<input type="text"  placeholder="填写胎数" name="embryoNumCase"  datatype="n0-11" errormsg="请输入数字" ignore="ignore">
		                                     </td>
		                                  </tr> 
		                                  <tr>
		                                      <td>是否有畸形：
		                                     	 <label><input style="width:12px;" type="radio" name="deformed" value="1"  checked> 无</label>
		                  						 <label><input style="width:12px;" type="radio" name="deformed" value="2" > 有</label>
		                                      </td>
		                                      <td>新生儿窒息：
		                                      	<label><input style="width:12px;" type="radio" name="sleepyBaby" value="1" checked> 无</label>
		                  						<label><input style="width:12px;" type="radio" name="sleepyBaby" value="2"> 有</label>
		                                      </td>       
		                                  </tr> 
		                                  <tr>
		                                      <td>新生儿听力筛查：
		                                      	<label><input style="width:12px;" type="radio" name="newbornHearing" value="1" checked> 通过</label>
		                  						<label><input style="width:12px;" type="radio" name="newbornHearing" value="2" > 未通过</label>
		                                      	<label><input style="width:12px;" type="radio" name="newbornHearing" value="3" > 未筛查</label>
		                                      	<label><input style="width:12px;" type="radio" name="newbornHearing" value="4" > 不详</label>
		                                      </td>
		                                      <td>新生儿疾病筛查：
		                                      	<label><input style="width:12px;" type="radio" name="neonatalDisease" value="1"  checked> 甲低</label>
		                  						<label><input style="width:12px;" type="radio" name="neonatalDisease" value="2" > 苯丙酮尿症</label>
		                                      	<input type="text"  name="neonatalDiseaseRemark" placeholder="其他"  datatype="*0-50" errormsg="最多50个字符！" ignore="ignore" > 
		                                      </td>
		                                   </tr> 
		                                  <tr>
		                                      <td colspan="2">Apgar评分：  
		                                       <span class="kong">1分钟 <input style="width:80px;" type="number" name="apgarscore1" min="0" datatype="n0-2" errormsg="请输入1-2位数字"  ignore="ignore"> 分</span>
		                                       <span class="kong">5分钟 <input style="width:80px;" type="number" name="apgarscore2" min="0" datatype="n0-2" errormsg="请输入1-2位数字"  ignore="ignore"> 分</span>
		                                       <span class="kong">10分钟 <input style="width:80px;" type="number" name="apgarscore3" min="0"  datatype="n0-2" errormsg="请输入1-2位数字"  ignore="ignore"> 分</span>
		                                       <label><input style="width:12px;" type="radio"  value="不详"  name="apgarscore4" > 不详</label>
		                                      </td>
		                                  </tr> 
		                            </tr>                                 
		                    </tbody>
		                 </table>
		             </div>
		             <div class="weight-title">生育状况</div>
		             <div class="panel-body">
		                 <table class="gw_xx">
		                 		<tbody>
		             					 <tr>
			                                 <td>新生儿体重：<input style="width:80px;" type="text" name="weight" placeholder="请输入" datatype="/^(([1-9][0-9]+)|([0-9]+\.[0-9]{1,2}))$/" errormsg="请输入数字" ignore="ignore"> kg  </td>
		                                     <td>目前体重：<input style="width:80px;" type="text" name="babyWeight"  placeholder="请输入" datatype="/^(([1-9][0-9]+)|([0-9]+\.[0-9]{1,2}))$/" errormsg="请输入数字" ignore="ignore"> kg </td>
		                                     <td>出生身长：<input style="width:80px;" type="text" name="babyHight"  placeholder="请输入" datatype="/^(([1-9][0-9]+)|([0-9]+\.[0-9]{1,2}))$/" errormsg="请输入数字" ignore="ignore"> cm</td>
		                                 </tr> 
		                                 <tr>
		                                     <td>喂养方式：
		                                      <label><input style="width:10px;" type="radio" name="feedway" value="1"   checked> 纯母乳</label>
		                 					  <label><input style="width:10px;" type="radio" name="feedway" value="2"  > 混合</label>
		                                      <label><input style="width:10px;" type="radio" name="feedway" value="3"  > 人工</label>
		                                     </td>       
		                                     <td>吃奶次数：<input style="width:80px;" type="number" name="feedSize" min="0"  datatype="n0-11" errormsg="请输入数字" ignore="ignore" > 次/日  </td>
		                                     <td>吃奶量：<input style="width:80px;" type="text"name="feedmilk"  datatype="n0-11" errormsg="请输入数字" ignore="ignore"> ml/次</td>
		                                 </tr> 
		                                 <tr>
		                                     <td>呕吐： 
		                                     	<label><input style="width:10px;" type="radio" name="vomit" value="1" checked> 无</label>
		                 						<label><input style="width:10px;" type="radio" name="vomit" value="2" > 有</label>
		                                     </td>
		                                     <td>大便：
		                                      <label><input style="width:10px;" type="radio" name="frequency" value="1" checked> 糊状</label>
		                 					  <label><input style="width:10px;" type="radio" name="frequency" value="2"> 稀状</label>
		                                     </td>
		                                 	<td>大便次数： <input style="width:80px;"  name="frequencyNum" datatype="n0-11" errormsg="请输入数字" ignore="ignore" > 次/日 </td>
		                                 </tr> 
		                                 <tr>
		                                     <td>体温：<input style="width:80px;" type="text" name="temperature"  placeholder="请输入"  datatype="n0-11" errormsg="请输入数字" ignore="ignore"> ℃</td>
		                                     <td>脉率：<input style="width:80px;" type="text"  name="pulseRate"   placeholder="请输入" datatype="n0-11" errormsg="请输入数字" ignore="ignore"> 次/分钟</td>
		                                     <td>呼吸频率：<input style="width:80px;" type="text" name="respiratoryRate" placeholder="请输入" datatype="n0-11" errormsg="请输入数字" ignore="ignore"> 次/分钟</td>
		                                 </tr> 
		                                 <tr>
		                                 	<td>面色：
		                                     <label><input style="width:10px;" type="radio" name="facecolor" value="1" checked> 红润</label>
		                 					 <label><input style="width:10px;" type="radio" name="facecolor" value="2" > 黄染</label>
		                                     <input type="text"  name="facecolorCase" placeholder="填写其他"> 
		                                     </td>
		                                     <td colspan="2">黄疸部位：
		                                     <label><input style="width:10px;" type="radio" name="jaundiceParts" value="1"   checked> 面部</label>
		                 					<label><input style="width:10px;" type="radio"  name="jaundiceParts" value="2"  > 躯干</label>
		                                     <label><input style="width:10px;" type="radio" name="jaundiceParts" value="3" > 四肢</label>
		                                     <label><input style="width:10px;" type="radio" name="jaundiceParts" value="4"  > 手足</label>
		                                     </td>
		                                 </tr> 
		                                 <tr>
		                                     <td colspan="3">前囟：
		                                         <input style="width:80px;" type="text" name="bregma1" datatype="n0-11" errormsg="请输入数字" ignore="ignore" > cm X 
		                                         <input style="width:80px;" type="text" name="bregma2" datatype="n0-11" errormsg="请输入数字" ignore="ignore"> cm
		                                     	<label><input style="width:10px;" type="radio" name="bregmaStatus" value="1" checked> 正常</label>
		                 					 	<label><input style="width:10px;" type="radio" name="bregmaStatus" value="2" > 膨隆</label>
		                                     	<label><input style="width:10px;" type="radio" name="bregmaStatus" value="3" > 凹陷</label>
		                                     	<input type="text" name="bregmaRemark" placeholder="填写其他" > 
		                                     </td>
		                                 </tr> 
		                                 <tr>
		                                 	<td>眼外观：
		                                     <label><input style="width:10px;" type="radio" name="eyeAppearance" value="1" class="ckeckNOeye" checked > 正常</label>
		                 					 <label><input style="width:10px;" type="radio" name="eyeAppearance" value="2" class="checkOnlyeye" > 异常</label>
		                                     <input type="text" class="noeye"  name="eyeAppearanceRemark"  placeholder="填写异常内容" disabled="disabled">
		                                     </td>
		                                     <td>耳外观：
		                                     <label><input style="width:10px;" type="radio" name="earAppearance" value="1" class="ckeckNO2"  checked > 正常</label>
		                 					 <label><input style="width:10px;" type="radio"  name="earAppearance" value="2" class="checkOnly2"  > 异常</label>
		                                     <input type="text" class="no2"  name="earAppearanceRemark" placeholder="异常内容"  datatype="*0-50" errormsg="最多50个字符！" disabled="disabled">
		                                     </td>
		                                     <td>四肢活动度：
		                                     <label><input style="width:10px;" type="radio" name="limbsActivity" value="1"  class="ckeckNO3"  checked> 正常</label>
		                 					 <label><input style="width:10px;" type="radio"  name="limbsActivity" value="2" class="checkOnly3" > 异常</label>
		                                     <input type="text" class="no3" name="limbsActivitRemark" readonly="readonly" placeholder="异常内容"  datatype="*0-50" errormsg="最多50个字符" />
		                                     </td>
		                                  </tr> 
		                                 <tr>
		                                     <td>颈部包块：
		                                     <label><input style="width:10px;" type="radio" name="cervicalMass" value="1" class="ckeckNO4"  checked> 正常</label>
		                 					 <label><input style="width:10px;" type="radio"  name="cervicalMass" value="2" class="checkOnly4" > 异常</label>
		                                     <input type="text"  class="no4" name="cervicalMassRemark"  placeholder="异常内容"  datatype="*0-50" errormsg="最多50个字符！">
		                                     </td>
		                                 	<td>鼻：
		                                     <label><input style="width:10px;" type="radio" name="nose" value="1"  class="ckeckNO5"  checked> 正常</label>
		                 					 <label><input style="width:10px;" type="radio"  name="nose" value="2" class="checkOnly5" > 异常</label>
		                                     <input type="text" class="no5" name="noseRemark"  placeholder="异常内容"  datatype="*0-50" errormsg="最多50个字符！"  >
		                                     </td>
		                                     <td>口腔：
		                                     <label><input style="width:10px;" type="radio" name="mouth" value="1" class="ckeckNO6"  checked> 正常</label>
		                 					 <label><input style="width:10px;" type="radio" name="mouth" value="2" class="checkOnly6" > 异常</label>
		                                     <input type="text" class="no6"  name="mouthRemark" readonly="readonly" placeholder="异常内容"  datatype="*0-50" errormsg="最多50个字符！" >
		                                     </td>
		                                 </tr> 
		                                 <tr>
		                                     <td>皮肤：
		                                     <label><input style="width:10px;" type="radio" name="skin" value="1" class="skinNo"  checked> 正常</label>
		                 					 <label><input style="width:10px;" type="radio" name="skin" value="2" class="skinOnly"> 异常</label>
		                                     <input type="text" class="noskin"  name="skinRemark" readonly="readonly" placeholder="异常内容"  datatype="*0-50" errormsg="最多50个字符！" >
		                                     </td>
		                                     <td>肛门:
		                                     <label><input style="width:10px;" type="radio" name="anus" value="1" class="ckeckNO7"  checked> 正常</label>
		                 					<label><input style="width:10px;" type="radio"  name="anus" value="2" class="checkOnly7" > 异常</label>
		                                     <input type="text" class="no7" name="anusRemark" readonly="readonly" placeholder="异常内容"  datatype="*0-50" errormsg="最多50个字符！">
		                                     </td>
		                                 	<td>心肺听诊：
		                                     <label><input style="width:10px;" type="radio" name="heartLung" value="1" class="ckeckNO8"  checked> 正常</label>
		                 					 <label><input style="width:10px;" type="radio" name="heartLung" value="2" class="checkOnly8" > 异常</label>
		                                     <input type="text" class="no8" name="heartLungRemark"  placeholder="填写异常内容" datatype="*0-50" errormsg="最多50个字符！" >
		                                     </td>
		                                 </tr> 
		                                 <tr>
		                                     <td>腹部触诊：
		                                     <label><input style="width:10px;" type="radio" name="abdominalPalpation" value="1"  class="ckeckNO10"  checked> 正常</label>
		                 					 <label><input style="width:10px;" type="radio" name="abdominalPalpation" value="2" class="checkOnly10" > 异常</label>
		                                     <input type="text" class="no10" name="abdominalPalpationRemark" 	readonly="readonly" placeholder="异常内容"  datatype="*0-50" errormsg="最多50个字符！">
		                                     </td>
		                                     <td>外生器官：
		                                     <label><input style="width:10px;" type="radio" name="pudendum" value="1"  class="ckeckNO9"  checked> 正常</label>
		                 					 <label><input style="width:10px;" type="radio" name="pudendum" value="2"  class="checkOnly9"  > 异常</label>
		                                     <input type="text" class="no9" name="pudendumRemark" readonly="readonly" placeholder="异常内容"  datatype="*0-50" errormsg="最多50个字符！">
		                                     </td>
		                                     <td>脊柱：
		                                     <label><input style="width:10px;" type="radio" name="spine" value="1"    class="ckeckNO11"  checked> 正常</label>
		                 					 <label><input style="width:10px;" type="radio"  name="spine" value="2"  class="checkOnly11"  > 异常</label>
		                                     <input type="text" class="no11"  name="spineRemark" readonly="readonly" placeholder="异常内容"  datatype="*0-50" errormsg="最多50个字符！" >
		                                     </td>
		                                 </tr> 
		                                 <tr>
		                                 	<td>系带：
		                                     <label><input style="width:10px;" type="radio"  name="chalaza" value="1"  checked> 未脱</label>
		                 					 <label><input style="width:10px;" type="radio"  name="chalaza" value="2"> 脱落</label>
		                                     <label><input style="width:10px;" type="radio"  name="chalaza" value="3" > 脐部有渗出</label>
		                                     <input type="text"  name="chalazaRemark" placeholder="其他"  datatype="*0-50" errormsg="最多50个字符！" >
		                                    </td>
		                                 </tr>     
		                            </tr>                                 
		                    </tbody>
		                                                        
		                </table>
		            </div>
		            <div class="weight-title">其它记录</div>
		            <div class="panel-body" style="padding-left:0px 30px;">
		                <table class="gw_xx">
		                            <tbody>
		                             <tr>
		                                  <td colspan="2">转诊建议：
			                                  <label><input style="width:10px;" type="radio" name="suggest" value="1" class="suggestCkeck" > 无</label>
			             					  <label><input style="width:10px;" type="radio" name="suggest" value="2" class="suggestOnly" > 有</label>
			                                  <input type="text" class="suggestReason " name="reason" placeholder="其他" readonly="readonly" >
		                                 </td>
<!-- 		                                 <td>原因： <input type="text"  name="reason" 	placeholder="其他"    > </td> -->
		                                 <td>建议就诊机构与科室：
			                                 <input type="text"  name="institutionsSuggest" placeholder="请输入"  datatype="*0-50" errormsg="最多50个字符！">
		                                 </td>    
		                             </tr> 
		                    </tbody>
		                 </table>
		             </div>
		             <!-- 指导开始 -->
		            <div class="panel-body">
		                <table class="gw_xx" style="border:1px solid #e6e6e6;" cellpadding="10">
			                		<thead style="border-bottom:1px solid #e6e6e6">
				                        	<th style="padding-left: 15px;"> 
				                        		指导： <a class="btn btn-default float-right " href="#" style="height:30px; width:90px;margin-right:10px;" data-toggle="modal"  
				                        		data-target="#myModal" id="manageModel">管理模板</a>
											</th>
			                             </thead>
			                             <tbody>
			                             <tr>
			                            <td style="padding-left: 15px;" id="visitBabyType"> 类型：
	<!-- 			                            <label><input style="width:10px;" type="checkbox" name="adviceType" tagContent="" value="1"> 喂养指导</label> -->
	<!-- 			                            <label><input style="width:10px;" type="checkbox" name="adviceType" tagContent="" value="2"> 发育指导</label> -->
	<!-- 			                            <label><input style="width:10px;" type="checkbox" name="adviceType" tagContent="" value="3"> 防病指导</label> -->
	<!-- 			                            <label><input style="width:10px;" type="checkbox" name="adviceType" tagContent="" value="4"> 预防伤害指导</label> -->
	<!-- 			                            <label><input style="width:10px;" type="checkbox" name="adviceType" tagContent="" value="5"> 口腔保健指导</label> -->
			                            </td> 
			                        </tr>
			                        <tr>
			                            <td style="padding-left: 15px;"> 内容：
			                            		<textarea style="width:98%" name="adviceContent" id="adviceContent"></textarea>
			                            </td>
			                        </tr>
			                </tbody>
			             </table>
		       		 </div>
		       		 <!-- 指导结束 -->
		             	<div class="panel-body" style="padding-left: 30px;">
		                <table class="gw_xx">
		                        <tbody>
		                        <tr>
		                            <td>本次访视日期：<input id="d2"  placeholder="日期选择" name="curdate" readonly="readonly"></td>
		                            <td>下次访视日期：<input id="d3"  placeholder="日期选择" name="nextdate" readonly="readonly"></td>
		                            <td>下次访视地点：<input type="text" name="nextadress" placeholder="请输入"  datatype="*" errormsg="请输入下次访视地点"></td> 
		                        </tr>
		                        <tr>
		                            <td>访视医院：
			                            <select name="visitHospitalId" style="width: 37%;">
			                            	<c:forEach items="${hopList }" var="hosp">
			                                    <option value="${hosp.id }">${hosp.name }</option>
			                            	</c:forEach>
			                            </select>
		                            </td>
		                            <td>访视社区：
			                            <select name="visitCommunityId" >
			                            	<c:forEach items="${commList }" var="comm">
			                                    <option value="${comm.id }">${comm.name }</option>
			                            	</c:forEach>
			                            </select>
		                            </td>
		                            <td>本次访视人员：<input type="text"  name="visiterName"  maxlength="20"></td> 
		                        </tr>
		                </tbody>
		             </table>
		        </div>
		    </div>   
		   </form>   
		  <!-- form表单内容结束 -->
		    </div>
		    
	    		<div class="gw_btn">
					    <input class="btn btn-default" style="width: 85px; height:40px;padding:0px 0px;" readonly value="取消" type="button">
					    <input class="btn btn-pink" style="width: 85px; height:40px;padding:0px 0px; margin-left:20px;" id="submit" value="保存" type="button">
	            </div>
            </div>
	    </div>
    	
</div>
<!-- 弹出框 -->
       <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-dialog">
             <div class="modal-content">
                <div class="modal-header" style="text-align:center">
                   <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">  &times; </button>
                   <h4 class="modal-title" id="myModalLabel"> 指导内容模板 </h4>
                </div>
                <div class="modal-body">
			            <table style="border-collapse:collapse; border:0px solid #D0D0D0;">
			                       <tr height="25px">
			                       	<td>序号</td>
			                        <td>名称</td>
			                        <td>内容</td>
			                        <td>操作</td>
			                       </tr>
			                       <tbody id="testCaseDiagnoseModel">
		                           </tbody>
			            </table>   
		        </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2" id="addGuidanceModel">   新增模板 </button>
             	</div>
      </div> 
       </div> 
      </div>
        
        <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-dialog">
             <div class="modal-content">
                <div class="modal-header" style="text-align:center">
                   <button type="button" class="close"   data-dismiss="modal" aria-hidden="true"> &times; </button>
                   <h4 class="modal-title" id="myModalLabel">新增模板 </h4>
                </div>
                <div class="modal-body">
                		<input type="hidden" id="operationType" value="insert"/>
	                    <input type="hidden" id="updateTestCaseId" value=""/>
          				<div style="padding:0 15px; margin-bottom:15px;">
		          				<span>名称：</span>
		          				<input type="text" id="ModelName"  value="" style=" width:95%;padding:0 10px;"></div>
                        <div style="padding:0 15px;">
	                        	<span style="vertical-align:top;">内容：</span>
	                           <textarea  id="ModelContentNew" style=" width:95%;padding:0 10px;"></textarea>
                        </div>
                </div>
                <div class="modal-footer">
                   <button type="button" class="btn btn-primary" id="saveTestCaseDiagnoseBtn" >保存</button>
                </div>
             </div> 
          </div> 
      </div>
       <!-- 模态框结束 --> 
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/laydate-v1.1/laydate/laydate.js"></script>       
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/visist/jquery.tips.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/visist/babyAdd_js.js"></script>
<script type="text/javascript">
 $(function(){
    for(var i=1;i<4;i++){
	 	laydate({
			    elem: "#d"+i,
			    event: 'focus', //响应事件。如果没有传入event，则按照默认的click
			    format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			    max:laydate.now(),  //设置时间选择的最大值
			    festival: true //显示节日
			});
	 }
 });
</script>
<!-- 引入页尾 -->
</div></div>
</body>
</html>
