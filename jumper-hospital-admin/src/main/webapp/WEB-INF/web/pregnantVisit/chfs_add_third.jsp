<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/menu.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/daterangepicker-bs3.css">
<!DOCTYPE html>
<html>
<head>
<title>产后访视(42)-新增/编辑</title>
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
</head>

<body style="background: #F0F0F0;">
<div>
 		<div class="row row-offcanvas row-offcanvas-right" style="margin-top: 20px;">                
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
					        	<div class="gw_rote"> <span class="float-left" style="font-size:16px;">档案管理 > 档案详情 > <a style=" color:#ff7ea5">产后访视</a></span></div>
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
					                	<%@ include file="/WEB-INF/web/pregnantVisit/headUrl.jsp"%>
					                        <div id="myTabContent" class="tab-content">
					                           <div class="tab-pane fade in active float-left" id="gwsc">
					                             	<div class="panel">
					                                        <div class="panel-body" style="padding-left:-15px;"> 
					                                            <ul class="nav nav-tabs no-border-margin1" style="border-bottom: none!important;float:left;">
							                                       <li id="one" class="" onclick="tabSelect(1);">
							                                            <a href="javascript:void(0);" class="remote_href"   style="padding:10px 5px;">产后第一次访视新增</a>
							                                        </li>
					                                                <li id="two" class="" onclick="tabSelect(2);">
					                                                	<a href="javascript:void(0);" class="remote_href"   style="padding:10px 5px;">产后第二次访视新增</a>
					                                                </li>
					                                                <li id="third" class="" onclick="tabSelect(3);">
					                                                	<a href="javascript:void(0);" class="remote_href"   style="padding:10px 5px;">产后42天访视新增</a>
					                                                </li>
					                                            </ul>
					                                        </div>
					                                        
					        						</div>
					                           </div>
											</div>
					                </div>
					                    <!-- 产后访视42天 -->
					               <form id="submitForm" method="post">
					                 <div id="chanfang2" class="panel panel-default  float-left" style="margin:15px;width: 98%;">
					                 		<input type="hidden" value="${archiveId}" id="archiveId" />
											<input type="hidden" value="${archiveBaseId}" id="archiveBaseId" /><!-- 档案id -->
					                		<input type="hidden" name="id"  value="${visitRecord.id }" /> <!-- 编辑记录Id -->
						                    <input type="hidden" name="userId" id ="userId" value="${userId }" />
											<input type="hidden" name="visiterType" id ="visitType" value="${visitType }"/> <!-- 产后42天随访 -->
					                    <div class="weight-title">基本情况</div>
					                    <div class="panel-body">
					                        <table class="gw_xx">
					                                    <tbody>
					                                    <tr>
<!-- 					                                        <td >产后休养地： -->
<!-- 					                                   			 	<select name="" id="provinceId" style="width:90px"> -->
<!-- 						                                                <c:forEach items="${provinces}" var="province" > -->
<!-- 						                                                	<option value="${province.id}">${province.proName }</option> -->
<!-- 						                                                </c:forEach> -->
<!-- 						                                            </select>  -->
<!-- 						                                            <select name="restCity" id="cityId" style="width:90px"> -->
<!-- 						                                            	  <option value=""> --请选择--</option> -->
<!-- 						                                            </select> -->
<!-- 					                                              <input type="text" value="" placeholder="请输入详细地址"> -->
<!-- 					                                        </td> -->
					                                        <td >分娩日期：
					                                        	<input name="deliveryDate" id="d1" placeholder="日期选择"  type="text" value="<fmt:formatDate value="${visitRecord.deliveryDate}" pattern="yyyy-MM-dd"/>" readonly="readonly">
					                                        </td>
					                                    </tr>
					                                    <tr>
					                                        <td colspan="2">分娩方式：
					                                        	<label><input style="width:10px;" type="radio" name="deliveryWay" value="0" <c:if test="${visitRecord.deliveryWay ==0}">checked</c:if>  > 顺产</label>
					                                            <label><input style="width:10px;" type="radio" name="deliveryWay" value="1" <c:if test="${visitRecord.deliveryWay ==1}">checked</c:if> > 产钳</label>
					                                            <label><input style="width:10px;" type="radio" name="deliveryWay" value="2" <c:if test="${visitRecord.deliveryWay ==2}">checked</c:if> > 胎吸</label>
					                                            <label><input style="width:10px;" type="radio" name="deliveryWay" value="3" <c:if test="${visitRecord.deliveryWay ==3}">checked</c:if> > 臀助产</label>
					                                            <label><input style="width:10px;" type="radio" name="deliveryWay" value="4" <c:if test="${visitRecord.deliveryWay ==4}">checked</c:if> > 臀牵引</label>
					                                            <label><input style="width:10px;" type="radio" name="deliveryWay" value="5" <c:if test="${visitRecord.deliveryWay ==5}">checked</c:if> > 剖宫产</label>
					                                        </td>
					                                    </tr>
					                            </tbody>
					                         </table>
					                     </div>
					                     <div class="weight-title">身体情况</div>
					                     <div class="panel-body">
					                         <table class="gw_xx">
					                                  <tbody>
					                                  <tr>
				                                      	<td colspan="2" >孕产期异常情况：
				                                      		<label><input style="width:10px;" type="radio" name="healthStatus" value="1" <c:if test="${visitRecord.healthStatus ==1}">checked</c:if> >无</label>
						          							<span class="kong">
						         							  <label><input style="width:10px;" type="radio" name="healthStatus" value="2" <c:if test="${visitRecord.healthStatus ==2}">checked</c:if> > 有</label>
						                                      <label><input style="width:10px;" type="checkbox" name="illness" value="1"  <c:if test="${fn:contains(visitRecord.illness,'1')}">checked="checked"</c:if> > 高血压</label>
						                                      <label><input style="width:10px;" type="checkbox" name="illness" value="2" <c:if test="${fn:contains(visitRecord.illness,'2')}">checked="checked"</c:if> > 血糖异常</label>
						                                      <label><input style="width:10px;" type="checkbox" name="illness" value="3" <c:if test="${fn:contains(visitRecord.illness,'3')}">checked="checked"</c:if> > 甲亢/甲低</label>
						                                      <label><input style="width:10px;" type="checkbox" name="illness" value="4" <c:if test="${fn:contains(visitRecord.illness,'4')}">checked="checked"</c:if> > 心脏病</label>
						                                      <label><input style="width:10px;" type="checkbox" name="illness" value="5" <c:if test="${fn:contains(visitRecord.illness,'5')}">checked="checked"</c:if> > 胆淤</label>
						                                      <label><input style="width:10px;" type="checkbox" name="illness" value="6" <c:if test="${fn:contains(visitRecord.illness,'6')}">checked="checked"</c:if> > 产后出血</label>
<!-- 						                                      <input value="" type="text" placeholder="输入其他"> -->
						          							</span>
				                                       	</td>
					                                 </tr>
					                                 <tr> 
					<!--                                      <td >健康情况： -->
					<!--                                    		<label>	<input type="radio" name="healthStatus"  value="0" checked />正常</label> -->
					<!--                                    		<label>	<input type="radio" name="healthStatus"  value="1"/>非正常</label> -->
					<!--                                      </td> -->
					                                     <td >心理状况：<input type="text" name="psychologicStatus" value="${visitRecord.psychologicStatus }" > </td>
					                                 </tr>
					                                 <tr>
					                                         <td >血压：<input  type="text" name="bloodPressure" value="${visitRecord.bloodPressure }" placeholder="输入舒张压" datatype="/^(([1-9]+)|([0-9]+\.[0-9]{0,2}))$/" errormsg="请输入数字" ignore="ignore" > 
																	 -<input  type="text" name="bloodPresureLow" value="${visitRecord.bloodPresureLow }"  placeholder="输入收缩压" datatype="/^(([1-9]+)|([0-9]+\.[0-9]{0,2}))$/" errormsg="请输入数字" ignore="ignore" > mmHg 
															</td>  
					                                   <td >体温：<input  type="text" name="tperture" value="${visitRecord.tperture }"  placeholder="请输入"  datatype="/^(([1-9]+)|([0-9]+\.[0-9]{0,2}))$/"  errormsg="请输入数字" ignore="ignore" > ℃ </td>
					                                 </tr>
					                                  <tr>
					                                     <td >乳房：
						                                   	<label><input style="width:10px;" type="radio" name="breast" value="0" <c:if test="${visitRecord.breast ==0 }">checked="checked"</c:if> > 正常</label>
						      								<label><input style="width:10px;" type="radio" name="breast" value="1" <c:if test="${visitRecord.breast ==1 }">checked="checked"</c:if> > 红肿</label>
						                                    <label><input style="width:10px;" type="radio" name="breast" value="2" <c:if test="${visitRecord.breast ==2 }">checked="checked"</c:if> > 未查</label>
					                                    </td>
					                                   <td >乳头：
					                                   <label><input style="width:10px;" type="radio" name="breastHead" value="0" <c:if test="${visitRecord.breastHead ==0 }">checked="checked"</c:if> > 正常</label>
					      							   <label><input style="width:10px;" type="radio" name="breastHead" value="1" <c:if test="${visitRecord.breastHead ==1 }">checked="checked"</c:if> > 平</label>
					                                   <label><input style="width:10px;" type="radio" name="breastHead" value="2" <c:if test="${visitRecord.breastHead ==2 }">checked="checked"</c:if> > 皲裂</label>
					                                   <label><input style="width:10px;" type="radio" name="breastHead" value="3" <c:if test="${visitRecord.breastHead ==3 }">checked="checked"</c:if> > 未查</label>
					                                     </td>
					                                  </tr>
					                                  <tr>
					                                      <td >子宫：
						                                   <label><input style="width:10px;" type="radio" name="uterus" value="0" <c:if test="${visitRecord.uterus ==0 }">checked</c:if> > 正常</label>
						      								<label><input style="width:10px;" type="radio" name="uterus" value="1" <c:if test="${visitRecord.uterus ==1 }">checked</c:if> > 异常</label>
<!-- 						                                   <input  type="text" placeholder="输入异常内容" > -->
						                                   </td>
						                                   <td >宫底：
						                                   <label><input style="width:10px;" type="radio" name="fundus" value="0" <c:if test="${visitRecord.fundus ==0 }">checked</c:if> > 平脐</label>
						      							   <label><input style="width:10px;" type="radio" name="fundus" value="1" <c:if test="${visitRecord.fundus ==1 }">checked</c:if> > 脐下一指</label>
						                                   <label><input style="width:10px;" type="radio" name="fundus" value="2" <c:if test="${visitRecord.fundus ==2 }">checked</c:if> > 脐下二指</label>
						                                   <label><input style="width:10px;" type="radio" name="fundus" value="3" <c:if test="${visitRecord.fundus ==3 }">checked</c:if> > 脐下三指</label>
						                                   <label><input style="width:10px;" type="radio" name="fundus" value="4" <c:if test="${visitRecord.fundus ==4 }">checked</c:if> > 降入骨盆腔内</label>
						                                   <label><input style="width:10px;" type="radio" name="fundus" value="5" <c:if test="${visitRecord.fundus ==5 }">checked</c:if> > 未查</label>
						                                 </td>
						                              </tr>
					                                  <tr>
					                                   <td colspan="2" >会阴：
					                                   <label><input style="width:10px;" type="radio" name="perinealWound" value="1" <c:if test="${visitRecord.perinealWound ==1 }">checked</c:if> > 正常</label>
					                                   <label><input style="width:10px;" type="radio" name="perinealWound" value="2" <c:if test="${visitRecord.perinealWound ==2 }">checked</c:if> > 硬结</label>
					                                   <label><input style="width:10px;" type="radio" name="perinealWound" value="3" <c:if test="${visitRecord.perinealWound ==3 }">checked</c:if> > 红肿</label>
					                                   <label><input style="width:10px;" type="radio" name="perinealWound" value="4" <c:if test="${visitRecord.perinealWound ==4 }">checked</c:if> > 裂伤</label>
					      							   <label><input style="width:10px;" type="radio" name="perinealWound" value="5" <c:if test="${visitRecord.perinealWound ==5 }">checked</c:if> > 潮红</label>
					                                   <label><input style="width:10px;" type="radio" name="perinealWound" value="6" <c:if test="${visitRecord.perinealWound ==6 }">checked</c:if> > 水肿</label>
					                                   <label><input style="width:10px;" type="radio" name="perinealWound" value="7" <c:if test="${visitRecord.perinealWound ==7 }">checked</c:if> > 伤口</label>
					                                   <label><input style="width:10px;" type="radio" name="perinealWound" value="8" <c:if test="${visitRecord.perinealWound ==8 }">checked</c:if> > 未愈合</label>
					                                   <label><input style="width:10px;" type="radio" name="perinealWound" value="9" <c:if test="${visitRecord.perinealWound ==9 }">checked</c:if> > 增生</label>
					                                   <label><input style="width:10px;" type="radio" name="perinealWound" value="10" <c:if test="${visitRecord.perinealWound ==10 }">checked</c:if> > 未查</label>
					                                   </td>
					                                  </tr>
					                                  <tr>
					                                      <td colspan="2" >恶露： 
					                                       <label><input style="width:10px;" type="radio" name="loquio" value="1" <c:if test="${visitRecord.loquio ==1 }">checked</c:if> > 无</label>
					      								   <label><input style="width:10px;" type="radio" name="loquio" value="2" <c:if test="${visitRecord.loquio ==2 }">checked</c:if> > 有</label>
					<!--                                       <span class="kong">颜色： -->
					<!--                                       <label><input style="width:10px;" type="radio" name="chfs42_els" checked>鲜红</label> -->
					<!--          								<label><input style="width:10px;" type="radio" name="chfs42_els"> 淡红</label> -->
					<!--                                       <label><input style="width:10px;" type="radio" name="chfs42_els"> 暗红</label> -->
					<!--                                       <label><input style="width:10px;" type="radio" name="chfs42_els"> 白</label> -->
					<!--                                       <label><input style="width:10px;" type="radio" name="chfs42_els"> 脓性</label> -->
					<!--                                        </span> -->
					<!--                                       <span class="kong">量： -->
					<!--                                       <label><input style="width:10px;" type="radio" name="chfs42_ell" checked> 多</label> -->
					<!--          								<label><input style="width:10px;" type="radio" name="chfs42_ell"> 中</label> -->
					<!--                                       <label><input style="width:10px;" type="radio" name="chfs42_ell"> 少</label> -->
					<!--                                        </span> -->
					<!--                                       <span class="kong">味： -->
					<!--                                       <label><input style="width:10px;" type="radio" name="chfs42_elw" checked> 无异味</label> -->
					<!--          								<label><input style="width:10px;" type="radio" name="chfs42_elw"> 血腥味</label> -->
					<!--                                       <label><input style="width:10px;" type="radio" name="chfs42_elw"> 臭味</label> -->
					<!--                                        </span> -->
					<!--                                       </td> -->
					                                  </tr>
					                                  <tr>
					                                     <td >腹部伤口：
						                                   <label><input style="width:10px;" type="radio" name="abdominalWound"  value="1" <c:if test="${visitRecord.abdominalWound ==1 }">checked</c:if> > 未愈合</label>
						      							   <label><input style="width:10px;" type="radio" name="abdominalWound" value="2" <c:if test="${visitRecord.abdominalWound ==2 }">checked</c:if> > 愈合</label>
						                                   <label><input style="width:10px;" type="radio" name="abdominalWound"  value="3" <c:if test="${visitRecord.abdominalWound ==3 }">checked</c:if> > 红肿</label>
						                                   <label><input style="width:10px;" type="radio" name="abdominalWound"  value="4" <c:if test="${visitRecord.abdominalWound ==4 }">checked</c:if> > 渗出</label>
						                                   <label><input style="width:10px;" type="radio" name="abdominalWound"  value="5" <c:if test="${visitRecord.abdominalWound ==5 }">checked</c:if> > 增生</label>
						                                </td>
					                                  </tr>
					                                  <tr>
					                                       <td >大便：
						                                   <label><input style="width:10px;" type="radio" name="defecate" value="1" <c:if test="${visitRecord.defecate ==1 }">checked</c:if> > 正常</label>
						      								<label><input style="width:10px;" type="radio" name="defecate" value="2" <c:if test="${visitRecord.defecate ==2 }">checked</c:if> > 干燥</label>
						                                   <label><input style="width:10px;" type="radio" name="defecate" value="3" <c:if test="${visitRecord.defecate ==3 }">checked</c:if> > 未查</label>
						                                   </td>
						                                   <td >小便：
						                                   <label><input style="width:10px;" type="radio" name="piss" value="1" <c:if test="${visitRecord.piss ==1 }">checked</c:if> > 正常</label>
						      								<label><input style="width:10px;" type="radio" name="piss" value="2" <c:if test="${visitRecord.piss ==2 }">checked</c:if> > 不畅</label>
						                                   <label><input style="width:10px;" type="radio" name="piss" value="3" <c:if test="${visitRecord.piss ==3 }">checked</c:if> > 未查</label>
						                                   </td>
						                                </tr>
						                                <tr>
						                                   <td >乳量：
							      							   <label><input style="width:10px;" type="radio" name="breastMilk" value="0" <c:if test="${visitRecord.breastMilk ==0 }">checked</c:if> >少</label>
							                                   <label><input style="width:10px;" type="radio" name="breastMilk" value="1" <c:if test="${visitRecord.breastMilk ==1 }">checked</c:if> > 中</label>
							                                   <label><input style="width:10px;" type="radio" name="breastMilk" value="2" <c:if test="${visitRecord.breastMilk ==2 }">checked</c:if> > 多</label>
							                                   <label><input style="width:10px;" type="radio" name="breastMilk" value="3" <c:if test="${visitRecord.breastMilk ==3 }">checked</c:if> > 无</label>
							                                   <label><input style="width:10px;" type="radio" name="breastMilk" value="4" <c:if test="${visitRecord.breastMilk ==4 }">checked</c:if> > 未查</label>
						                                   </td>
<!-- 						                                   <td >会阴切口： -->
<!-- 							                                   <label><input style="width:10px;" type="radio"  value="0" > 愈合好</label> -->
<!-- 							      							   <label><input style="width:10px;" type="radio"  value="1"> 未愈合</label> -->
<!-- 							                                   <label><input style="width:10px;" type="radio"  value="2"> 感染</label> -->
<!-- 							                                   <label><input style="width:10px;" type="radio"  value="3"> 其他</label> -->
<!-- 					                                   		</td> -->
<!-- 					                                      <td colspan="2" >其他： <input type="text" placeholder=""></td> -->
					                                   </tr>
					                                   <tr>
					                                      <td colspan="2" >总体评估：
					                                      	<label><input style="width:10px;" type="radio" name="overallAbility" value="0" <c:if test="${visitRecord.overallAbility ==0 }">checked</c:if> > 正常</label>
						      								<label><input style="width:10px;" type="radio" name="overallAbility" value="1" <c:if test="${visitRecord.overallAbility ==1 }">checked</c:if> > 异常</label>
						<!--                                     <label><input  type="text"  placeholder=""></label> -->
						                                   	</td>    
					                                      </td>                                                              
					                                   </tr>
					                                     
					                          </tbody>
					                       </table>
					                    </div>
					                    <div class="panel-body">
					                        <table class="gw_xx" style="border:1px solid #e6e6e6;" cellpadding="10">
					                        		<thead style="border-bottom:1px solid #e6e6e6">
					                                	<th style="padding-left: 15px;"> 指导：
					                                		<a class="btn btn-default float-right " href="#" style="height:30px; width:90px;margin-right:10px;"
					                                		 data-toggle="modal" data-target="#myModal" id="manageModel" >管理模板</a>
					   									</th>
					                                </thead>
					                                <tbody>
					                                <tr>
					                                    <td style="padding-left: 15px;" id="visitBabyType"> 类型：
					        								<label><input style="width:12px;" type="radio" name="guide" tagContent="" value="1" <c:if test="${visitRecord.guide ==1}">checked</c:if> onclick="appendContent();" /> 性保健</label>
					                                        <label><input style="width:12px;" type="radio" name="guide" tagContent="" value="2" <c:if test="${visitRecord.guide ==2}">checked</c:if> onclick="appendContent();" /> 避孕</label>
					                                        <label><input style="width:12px;" type="radio" name="guide" tagContent="" value="3" <c:if test="${visitRecord.guide ==3}">checked</c:if> onclick="appendContent();" /> 婴儿喂养及营养</label>
					                                    </td> 
					                                </tr>
					                                <tr>
					                                    <td style="padding-left: 15px;"> 内容：<br>
					                                    	<textarea style="width:98%" name="guideContent" id="guideContent" >${visitRecord.guideContent }</textarea>
					                                    </td>
					                                </tr>
					                        </tbody>
					                     </table>
					                </div>
					                     	<div class="panel-body" style="padding-left: 30px;">
					                        <table class="gw_xx">
					                                <tbody>
					                                <tr>
					                                    <td>转诊：
					                                   		 <label><input style="width:10px;" type="radio" name="transferVisit" value="3" <c:if test="${visitRecord.transferVisit ==3}">checked</c:if> > 结案</label>
						                            		 <label><input style="width:10px;" type="radio" name="transferVisit" value="4" <c:if test="${visitRecord.transferVisit ==4}">checked</c:if> > 转诊</label>
					                                    </td>
					                                     <td>随访日期：
					                                     	<input  id="d2" placeholder="日期选择" readonly="readonly" name="visiterDate" value="<fmt:formatDate value="${visitRecord.visiterDate}" pattern="yyyy-MM-dd"/>"   >
					                                     </td>
						                                 <td>建议就诊机构及科室： <input  name="suggested" value="${visitRecord.suggested }" placeholder="" >
<!-- 							                                 <select name="suggested" style="width: 25%;"> -->
<!-- 							                                 	  <option value="">选择机构科室</option>  -->
<!-- 							                                      <c:forEach items="${hospitals}" var="hospital" > -->
<!-- 							                                          <option value="${hospital.name }">${hospital.name }</option>  -->
<!-- 							                                      </c:forEach> -->
<!-- 							                                 </select>  -->
					                                    </td> 
					                                </tr>
					                                <tr>
					                                    <td>原因：<input type="text" name="transferReason" value="${visitRecord.transferReason }"  placeholder="请输入原因"  datatype="*0-50" errormsg="不能超过50个字符" ></td>
						                                <td>访视机构：  <input  name="visiterInstitutions" datatype="*0-50" nullmsg="访视机构不能为空" value="${visitRecord.visiterInstitutions }" >
<!-- 						                                 	<select name="visiterInstitutions" id="hopId" style="width:25%;">   -->
<!-- 						                                   		  <option>选择医院</option>  -->
<!-- 						                                   		  <c:forEach items="${hospitals}" var="hospital" > -->
<!-- 						                                          	<option value="${hospital.id }">${hospital.name }</option> -->
<!-- 						                                          </c:forEach>   -->
<!-- 						                                    </select>  -->
<!-- 							                                <select name="visiterInstitutions"  id="commId" style="width:25%;" > -->
<!-- 							                                	   <option value="">--请选择--</option>   -->
<!-- 								                            </select>  -->
					                                    </td>
					                                    <td>访视人员：<input type="text" name="visiterName" value="${visitRecord.visiterName }" datatype="*0-50" nullmsg="人员不为空，且长度不超过50"  ></td> 
					                                </tr>
					                        </tbody>
					                     </table>
					        	        </div>
					               </div>    
					             </form>  
					            </div>
					            
					            <div class="gw_btn">
						            <input class="btn btn-default" style="width: 85px; height:40px;padding:0px 0px;" readonly value="取消" type="button">
						            <input class="btn btn-pink" style="width: 85px; height:40px;padding:0px 0px; margin-left:20px;" 
						            	readonly value="保存" type="button" id="submit">
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
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2">  新增模板  </button>
                             </div>
                          </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
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
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/delivery/jquery.tips.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/visist/chfs.js"></script>                   
<script type="text/javascript">
$(function () {
	 for(var i=1;i<3;i++){
	 	laydate({
			    elem: "#d"+i,
			    event: 'focus', //响应事件。如果没有传入event，则按照默认的click
			    format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
			    max:laydate.now(),  //设置时间选择的最大值
			    festival: true //显示节日
			});
	 }
	 
var visitType = "${visitType}";
      if(visitType == 1){
      	$("#one").attr("class","current");
      }
      if(visitType == 2){
      	$("#two").attr("class","current");
      }
      if(visitType == 3){
      	$("#third").attr("class","current");
      }  
   });   
   
function tabSelect(type){
	if(type == 1){
		window.location.href="${pageContext.request.contextPath}/postnatal/toAddPostVisit?userId="+${userId}+"&visitType=1&archiveId="+${userBaseInfo.id};
	}else if(type == 2){
		window.location.href="${pageContext.request.contextPath}/postnatal/toAddPostVisit?userId="+${userId}+"&visitType=2&archiveId="+${userBaseInfo.id};
	}else if(type == 3){
		window.location.href="${pageContext.request.contextPath}/postnatal/toAddPostVisit?userId="+${userId}+"&visitType=3&archiveId="+${userBaseInfo.id};
	}
}
</script>
<!-- 引入页尾 -->
</div></div>
</body>
</html>
