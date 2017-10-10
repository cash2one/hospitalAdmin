<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- <jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include> --%>
<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/bootstrap.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/style.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/menu.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/daterangepicker-bs3.css"> -->
<!DOCTYPE html>
<html>
<head>
    <title>新生儿访视</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/media/css/pregnant/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/media/css/pregnant/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/media/css/pregnant/menu.css">
	<!-- 页头引入 -->
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
</style>
</head>
<body>
	<!--startprint-->
<div class="dayin" id="dty">
                  <div style="width:100%; text-align:center"><h2>新生儿访视</h2></div>
                  <div class="panel-body dayin" style="padding:15px;">
                        <table>
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
             <div>
             <div>  
                	<div class="weight-title">基本情况</div>
                    <div class="panel-body">
                        <table class="gw_xx">
                                    <tbody>
                                         <tr>
                                           <td width="33.3%">新生儿姓名：${vbr.userName }</td>
                                           <td width="33.3%">性别：
											    <c:if test="${vbr.babySex == 0 }">未知</c:if>
											    <c:if test="${vbr.babySex == 1 }">男</c:if>
											    <c:if test="${vbr.babySex == 2 }">女</c:if>
                                            </td>
                                           <td width="33.3%">出生日期：<fmt:formatDate value="${vbr.babyBirthday }" pattern="yyy-MM-dd"/></td>    
                                        </tr> 
                                        <tr>
                                             <td width="33.3%">助产机构：${vbr.midwiferyName }</td>
                                             <td width="33.3%">分娩孕周：孕${vbr.birthWeeks }周${vbr.visitDay }天 </td>
                                             <td width="33.3%">孕产期异常情况：
                                             	<c:if test="${vbr.pregrancyACase==1 }">无 </c:if>
                                             	<c:if test="${vbr.pregrancyACase==2 }">有,
                                             		<c:if test="${fn:contains(vbr.pregrancyCaseRemark ,'1')}">高血压</c:if>
	                                                <c:if test="${fn:contains(vbr.pregrancyCaseRemark ,'2')}">血糖异常</c:if>
	                                                <c:if test="${fn:contains(vbr.pregrancyCaseRemark ,'3')}">甲亢</c:if>
	                                                <c:if test="${fn:contains(vbr.pregrancyCaseRemark ,'4')}">甲低</c:if>
	                                                <c:if test="${fn:contains(vbr.pregrancyCaseRemark ,'5')}">心脏病</c:if>
	                                                <c:if test="${fn:contains(vbr.pregrancyCaseRemark ,'6')}">胆淤</c:if>
	                                                <c:if test="${fn:contains(vbr.pregrancyCaseRemark ,'7')}">产后出血</c:if>,
	                                                	其他：${vbr.motherCase }
                                             	</c:if>
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
	                                <td width="25%">出生情况：
	                                	<c:if test="${vbr.babyCase ==0}">顺产</c:if>
	                                	<c:if test="${vbr.babyCase ==1}">胎吸</c:if>
	                                	<c:if test="${vbr.babyCase ==2}">臀助产</c:if>
	                                	<c:if test="${vbr.babyCase ==3}">臀牵引</c:if>
	                                	<c:if test="${vbr.babyCase ==4}">产钳</c:if>
	                                	<c:if test="${vbr.babyCase ==5}">破宫产</c:if>
	                                	${vbr.babyCaseRemark }  
	                                </td>
	                                <td width="25%">胎数：
	                                 <c:if test="${vbr.embryoNum==0 }">单胎</c:if>
	                                 <c:if test="${vbr.embryoNum==1 }">双胎</c:if>
	                                 <c:if test="${vbr.embryoNum==2 }">多胎</c:if>
	                                	${vbr.embryoNumCase } 
	                                </td>
	                                <td width="25%">是否有畸形：
	                                	<c:if test="${vbr.deformed  ==0}"> 否</c:if>
										<c:if test="${vbr.deformed  ==1}"> 是</c:if>
									</td>
	                                <td width="25%">新生儿窒息：
	                                	<c:if test="${vbr.sleepyBaby  ==1}"> 无</c:if>
	                                	<c:if test="${vbr.sleepyBaby  ==2}"> 有</c:if>
	                                </td>       
                                </tr> 
                                <tr>
	                                <td width="25%">新生儿听力筛查： 
	                                	<c:if test="${vbr.newbornHearing  ==1}"> 通过</c:if>
	                                	<c:if test="${vbr.newbornHearing  ==2}"> 未通过</c:if>
	                                	<c:if test="${vbr.newbornHearing  ==3}"> 未筛查</c:if>
	                                	<c:if test="${vbr.newbornHearing  ==4}"> 不详</c:if>
	                                </td>
	                                <td width="25%">新生儿疾病筛查： 
	                                	<c:if test="${vbr.neonatalDisease  ==1}"> 甲低</c:if>
	                                	<c:if test="${vbr.neonatalDisease  ==2}"> 苯丙酮尿症</c:if>
	                                	${vbr.neonatalDiseaseRemark }
	                                </td>
	                                <td colspan="2">Apgar评分：
	                                	<span class="kong">1分钟 ${vbr.apgarscore1 }分</span>
		                                <span class="kong">5分钟 ${vbr.apgarscore2 }分</span>
		                                <span class="kong">10分钟 ${vbr.apgarscore3 } 分</span>
		                                <label>
		                                <c:if test="${babyRecorded.apgarscore4 =='不详'}">不详</c:if> 
	                                </td>
                                 </tr> 
                            </tbody>
                         </table>
                     </div>
                     <div class="weight-title">生育状况</div>
                     <div class="panel-body">
                         <table class="gw_xx">
                         		<tbody>
                      				<tr>
                                         <td width="25%">新生儿体重：${vbr.weight }kg</td>
                                         <td width="25%">目前体重：${vbr.babyWeight }kg </td>
                                         <td width="25%">出生身长：${vbr.babyHight }cm</td>
                                         <td width="25%">喂养方式：
                                         	<c:if test="${vbr.feedWay==1 }">纯母乳</c:if>
                                         	<c:if test="${vbr.feedWay==2 }">混合</c:if>
                                         	<c:if test="${vbr.feedWay==3 }">人工</c:if>
                                         </td>       
                                     </tr> 
                                     <tr>
                                        <td width="25%">吃奶次数：${vbr.feedSize } 次/日  </td>
                                        <td width="25%">吃奶量：${vbr.feedmilk}ml/次</td>
                                        <td width="25%">呕吐：	
                                        	<c:if test="${vbr.vomit==1 }">无</c:if>
                                        	<c:if test="${vbr.vomit==2 }">有</c:if>
                                        </td>
                                        <td width="25%">大便：
	                                        <c:if test="${vbr.frequency==1 }">糊状</c:if>
	                                        <c:if test="${vbr.frequency==2 }">稀状</c:if>
                                        	大便次数： ${vbr.frequencyNum}次/日
                                     	</td>
                                     </tr>                     
                                     <tr>
                                        <td width="25%">体温： ${vbr.temperature }次/分钟 </td>
                                        <td width="25%">脉率：${vbr.pulseRate }次/分钟</td>
                                        <td width="25%">呼吸频率：${vbr.respiratoryRate}次/分钟</td>
                                     </tr> 
                                     <tr>
                                        <td width="25%">面色：
                                        	<c:if test="${vbr.facecolor== 1 }">红润</c:if>
                                        	<c:if test="${vbr.facecolor== 2 }" >黄染</c:if>
                                        </td>
                                        <td width="25%">黄疸部位：
                                        	<c:if test="${vbr.jaundiceParts== 1 }">面部</c:if>
                                        	<c:if test="${vbr.jaundiceParts== 2 }" >躯干</c:if>
                                        	<c:if test="${vbr.jaundiceParts== 3 }">四肢</c:if>
                                        	<c:if test="${vbr.jaundiceParts== 4 }" >手足</c:if>
                                        </td>
                                        <td colspan="2">前：${vbr.bregma1 }cm X ${vbr.bregma2 } cm
                                        	<c:if test="${vbr.bregmaStatus==1 }">正常</c:if>
                                        	<c:if test="${vbr.bregmaStatus==2 }">膨隆</c:if>
                                        	<c:if test="${vbr.bregmaStatus==3 }">凹陷</c:if>
                                        	${vbr.bregmaRemark }
                                        </td>
                                    </tr> 
                                    <tr>
                                       <td width="25%">眼外观：
                                          	<c:if test="${vbr.eyeAppearance == 1 }">正常</c:if>
                                          	<c:if test="${vbr.eyeAppearance == 2 }">异常</c:if>
                                            ${vbr.eyeAppearanceRemark }
                                       </td>
                                       <td width="25%">耳外观：
                                       		<c:if test="${vbr.earAppearance  == 1 }">正常</c:if>
                                          	<c:if test="${vbr.earAppearance  == 2 }">异常</c:if>
                                          	${vbr.earAppearanceRemark }
                                       </td>
                                       <td width="25%">四肢活动度：
                                       		<c:if test="${vbr.limbsActivity  == 1 }">正常</c:if>
                                          	<c:if test="${vbr.limbsActivity  == 2 }">异常</c:if>
                                            ${vbr.limbsActivityRemark }
                                       </td>
                                       <td width="25%">颈部包块：
                                       		<c:if test="${vbr.cervicalMass  == 1 }">无</c:if>
                                          	<c:if test="${vbr.cervicalMass  == 2 }">有</c:if>&nbsp;${vbr.cervicalMassRemark }
                                      </td>
                                    </tr> 
                                    <tr>
                                       <td width="25%">鼻：
                                       		<c:if test="${vbr.nose  == 1 }">正常</c:if>
                                          	<c:if test="${vbr.nose  == 2 }">异常</c:if>&nbsp;${vbr.noseRemark }
                                            
                                       </td>
                                       <td width="25%">口腔：
                                       		<c:if test="${vbr.mouth  == 1 }">正常</c:if>
                                          	<c:if test="${vbr.mouth  == 2 }">异常</c:if>&nbsp;${vbr.mouthRemark }
                                       		
                                       </td>
                                       <td width="25%">皮肤：	
                                        	<c:if test="${vbr.skin  == 1 }">正常</c:if>
                                          	<c:if test="${vbr.skin  == 2 }">异常</c:if>&nbsp;${vbr.skinRemark }
                                       </td>
                                       <td width="25%">肛门：
                                       		<c:if test="${vbr.anus  == 1 }">正常</c:if>
                                       		<c:if test="${vbr.anus  == 2 }">异常</c:if>${vbr.anusRemark }
                                       </td>
                                    </tr> 
                                    <tr>
                                       <td width="25%">心肺听诊：
                                       		<c:if test="${vbr.heartLung  == 1 }">正常</c:if>
                                          	<c:if test="${vbr.heartLung  == 2 }">异常</c:if>&nbsp;${vbr.heartLungRemark }
                                       </td>
                                       <td width="25%">腹部触诊：
                                       		<c:if test="${vbr.abdominalPalpation  == 1 }">正常</c:if>
                                          	<c:if test="${vbr.abdominalPalpation  == 2 }">异常</c:if>&nbsp;${vbr.abdominalPalpationRemark }
                                       </td>
                                       <td width="25%">外生器官：
                                       		<c:if test="${vbr.pudendum  == 1 }">正常</c:if>
                                          	<c:if test="${vbr.pudendum  == 2 }">异常</c:if>&nbsp;${vbr.pudendumRemark }
                                       </td>
                                       <td width="25%">脊柱：
                                       		<c:if test="${vbr.spine  == 1 }">正常</c:if>
                                          	<c:if test="${vbr.spine  == 2 }">异常</c:if>&nbsp;${vbr.spineRemark }
                                       </td>
                                    </tr> 
                                    <tr>
                                       <td width="25%">系带：
                                       		<c:if test="${vbr.chalaza== 1 }">未脱</c:if>
                                       		<c:if test="${vbr.chalaza== 2 }">脱落</c:if>
                                       		<c:if test="${vbr.chalaza== 3 }">脐部有渗出</c:if>
                                       		 ${vbr.chalazaRemark }
                                       </td>
                                       <td width="25%"></td>
                                       <td width="25%"></td>
                                       <td width="25%"></td>
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
                                            	 <c:if test="${vbr.suggest== 1 }">无</c:if>
                                           		 <c:if test="${vbr.suggest== 2 }">有</c:if>
                                            </td>
                                            <td width="25%">原因：${vbr.reason } </td>
                                            <td width="25%">建议就诊机构与科室：${vbr.institutionsSuggest }</td>    
                                        </tr> 
                            		</tbody>
                         </table>
                     </div>
                     
                    <div class="panel-body">
                        <table class="gw_xx" style="border:1px solid #e6e6e6;" cellpadding="10">
                        		<thead style="border-bottom:1px solid #e6e6e6">
                                	<th style="padding-left: 15px;"> 指导：</th>
                                </thead>
                                <tbody>
                                 <tr>
                                    <td style="padding-left: 15px;"> 类型：${advType}</td> 
                                </tr>
                                <tr>
                                    <td style="padding-left: 15px;"> 内容：${vbr.adviceContent}</td>
                                </tr>
                        </tbody>
                     </table>
                </div>
                     	<div class="panel-body" style="padding-left: 30px;">
                        <table class="gw_xx">
                                <tbody>
	                               <tr>
	                                    <td>本次访视日期：<fmt:formatDate value="${vbr.curdate }" pattern="yyy-MM-dd"/></td>
	                                    <td>下次访视日期：<fmt:formatDate value="${vbr.nextdate }" pattern="yyy-MM-dd"/></td>
	                                    <td>下次访视地点：${vbr.nextadress }</td> 
	                                </tr>
	                                <tr>
	                                    <td>访视医院：${hop}</td>
	                                    <td>访视社区：${comm}</td>
	                                    <td>本次访视人员：${vbr.visiterName }</td> 
	                                </tr>
                        </tbody>
                     </table>
                </div>
            		</div>
            </div>
          
</div>		
	<!--endprint-->
	  <div class="gw_btn">
		<input class="btn btn-default" style="width: 85px; height:40px;padding:0px 0px;" readonly value="打印" type="button" id="aa"
			   onclick="document.getElementById('aa').style.display = 'none';window.print();" >
	  </div>
</body>
</html>
