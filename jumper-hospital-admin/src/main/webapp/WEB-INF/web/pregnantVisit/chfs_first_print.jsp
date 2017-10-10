<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>产后访视</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
   	<link rel="stylesheet" type="text/css" href="<%=basePath %>/media/css/pregnant/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/media/css/pregnant/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/media/css/pregnant/menu.css">
</head>
<body>
	<div class="dayin">

		<div style="width:100%; text-align:center">
			<h2>产后访视</h2>
		</div>

		<div class="panel-body dayin" style="padding:15px;">
			<table>
				<tbody>
					<tr>
						<td width="20%">姓名： ${userBaseInfo.name }</td>
						<td width="20%">年龄：${userBaseInfo.age}</td>
						<td width="25%">联系电话：${userBaseInfo.mobile }</td>
						<td width="35%">身份证号：${userBaseInfo.idNo }</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div>
			<div class="panel panel-default float-left"
				style=" width: 100%;border:1px solid #f1f1f1;">
				<div class="weight-title">基本情况</div>
				<div class="panel-body">
					<table class="gw_xx">
						<tbody>
							<tr>
								<td >分娩日期：<fmt:formatDate value="${visitRecord.deliveryDate}" pattern="yyyy-MM-dd"/></td>
                                <td >分娩方式：
                                	<c:if test="${visitRecord.deliveryWay==0 }">顺产</c:if>
                                	<c:if test="${visitRecord.deliveryWay==1 }">低位产钳 </c:if>
                                	<c:if test="${visitRecord.deliveryWay==2 }">胎头吸引</c:if>
                                	<c:if test="${visitRecord.deliveryWay==3 }">臀位助产</c:if>
                                	<c:if test="${visitRecord.deliveryWay==4 }">臀位牵引</c:if>
                                	<c:if test="${visitRecord.deliveryWay==5 }">剖宫产（下段、体部、腹膜外）</c:if>
                                	<c:if test="${visitRecord.deliveryWay==6 }">内倒转</c:if>
                                	<c:if test="${visitRecord.deliveryWay==7 }">毁胎术 </c:if>
                                	<c:if test="${visitRecord.deliveryWay==8 }">其他 </c:if>
                                </td>
                                <td >分娩医院：${visitRecord.deliveryHospital }</td>
                                <td >出院日期：<fmt:formatDate value="${visitRecord.hospitalDate}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="weight-title">身体情况</div>
				<div class="panel-body">
					<table class="gw_xx">
						<tbody>
							<tr>
								  <td >孕产期异常情况：
                                	 	<c:if test="${visitRecord.healthStatus==1 }">无</c:if>
                                		<c:if test="${visitRecord.healthStatus==2 }">有</c:if>,
                                		<c:if test="${fn:contains(visitRecord.illness,'1')}">高血压</c:if>
                                		<c:if test="${fn:contains(visitRecord.illness,'2')}">血糖异常</c:if> 
                                		<c:if test="${fn:contains(visitRecord.illness,'3')}">甲亢/甲低</c:if> 
                                		<c:if test="${fn:contains(visitRecord.illness,'4')}">心脏病</c:if> 
                                		<c:if test="${fn:contains(visitRecord.illness,'5')}">胆淤</c:if> 
                                		<c:if test="${fn:contains(visitRecord.illness,'6')}">产后出血</c:if> 
                                </td>
                                <td >健康情况：
<!--                                        		<c:if test="${visitRecord.healthStatus==0 }">无</c:if> -->
<!--                                        		<c:if test="${visitRecord.healthStatus==1 }">有</c:if> -->
<!--                                        		<c:if test="${visitRecord.healthStatus==2 }">其他</c:if> -->
                                </td>
                                <td >心理状况：${visitRecord.psychologicStatus } </td>
							</tr>
							<tr>
								<td >血压：${visitRecord.bloodPressure }---${visitRecord.bloodPresureLow }mmHg</td>
                                <td >体温：${visitRecord.tperture }℃ </td>
                                <td >乳房：
                                	   <c:if test="${visitRecord.breast==0 }">未见异常</c:if>
                                	   <c:if test="${visitRecord.breast==1 }">红肿</c:if>
                                	   <c:if test="${visitRecord.breast==2 }">疼痛</c:if>
                                	   <c:if test="${visitRecord.breast==3 }">硬结</c:if>
                                </td>
                                <td >乳头：
                                		<c:if test="${visitRecord.breastHead==0 }">未见异常</c:if>
                                		<c:if test="${visitRecord.breastHead==1 }">凹陷</c:if>
                                		<c:if test="${visitRecord.breastHead==2 }">皲裂</c:if>
                                </td>
							</tr>
							<tr>
								<td >子宫： 
                                	  <c:if test="${visitRecord.uterus==0 }">未见异常</c:if>
                                	  <c:if test="${visitRecord.uterus==1 }">复旧不良</c:if>
                                	  <c:if test="${visitRecord.uterus==2 }">压痛</c:if>
                                	  <c:if test="${visitRecord.uterus==3 }">其他</c:if>
                                </td>
                                <td >宫底：
                                	  <c:if test="${visitRecord.fundus==0 }">平脐</c:if>
                                	  <c:if test="${visitRecord.fundus==1 }">脐下一指 </c:if>
                                	  <c:if test="${visitRecord.fundus==2 }">脐下二指</c:if>
                                	  <c:if test="${visitRecord.fundus==3 }">脐下三指</c:if>
                                	  <c:if test="${visitRecord.fundus==4 }">降入骨盆腔内</c:if>
                                	  <c:if test="${visitRecord.fundus==5 }">未查</c:if>
                                	  <c:if test="${visitRecord.fundus==6 }">未愈合 </c:if>
                                	  <c:if test="${visitRecord.fundus==7 }">增生 </c:if>
                                	  <c:if test="${visitRecord.fundus==8 }">未查 </c:if>
                                </td>
                                <td >会阴：
                                	  <c:if test="${visitRecord.perinealWound==0 }">愈合好</c:if>
                                	  <c:if test="${visitRecord.perinealWound==1 }">未愈合</c:if>
                                	  <c:if test="${visitRecord.perinealWound==2 }">硬结</c:if>
                                	  <c:if test="${visitRecord.perinealWound==3 }">红肿 </c:if>
                                	  <c:if test="${visitRecord.perinealWound==4 }">裂开 </c:if>
                                	  <c:if test="${visitRecord.perinealWound==5 }">其他 </c:if>
                                </td>
							</tr>
							<tr>
								<td >恶露：
                                	 <c:if test="${visitRecord.loquio==1 }">未见异常</c:if>
                                	 <c:if test="${visitRecord.loquio==2 }">异常</c:if>
                                </td>
                                <td >颜色：  </td>
                                <td >量：  </td>
                                <td >味：  </td>
							</tr>
							<tr>
								<td >腹部伤口：
                                	 <c:if test="${visitRecord.abdominalWound==0 }">愈合好</c:if>
                                	 <c:if test="${visitRecord.abdominalWound==1 }">未愈合</c:if>
                                	 <c:if test="${visitRecord.abdominalWound==2 }">硬结</c:if>
                                	 <c:if test="${visitRecord.abdominalWound==3 }">红肿</c:if>
                                	 <c:if test="${visitRecord.abdominalWound==4 }">裂开</c:if>
                                	 <c:if test="${visitRecord.abdominalWound==5 }">增生</c:if>
                                </td>
                                <td >大便：
                                	 <c:if test="${visitRecord.defecate==0 }">正常</c:if>
                                	 <c:if test="${visitRecord.defecate==1 }">干燥</c:if>
                                	 <c:if test="${visitRecord.defecate==2 }">未查</c:if>
                                </td>
                                <td >小便：
                                	 <c:if test="${visitRecord.piss==0 }">正常</c:if>
                                	 <c:if test="${visitRecord.piss==1 }">不畅</c:if>
                                	 <c:if test="${visitRecord.piss==2 }">未查</c:if>
                                </td>
                                <td >乳量： 
                                	  <c:if test="${visitRecord.breastMilk==0 }">多</c:if>
                                	  <c:if test="${visitRecord.breastMilk==1 }">中等</c:if>
                                	  <c:if test="${visitRecord.breastMilk==2 }">少</c:if>
                                	  <c:if test="${visitRecord.breastMilk==3 }">无</c:if>
                                	  <c:if test="${visitRecord.breastMilk==4 }">未查</c:if>
                                </td>
							</tr>
							<tr>
								 <td >会阴切口：
                                	  <c:if test="${visitRecord.perinealWound==0 }">愈合好</c:if>
                                	  <c:if test="${visitRecord.perinealWound==1 }">未愈合</c:if>
                                	  <c:if test="${visitRecord.perinealWound==2 }">硬结</c:if>	
                                	  <c:if test="${visitRecord.perinealWound==3 }">红肿</c:if>	
                                	  <c:if test="${visitRecord.perinealWound==4 }">裂开</c:if>
                                	  <c:if test="${visitRecord.perinealWound==5 }">其他</c:if>		
                                </td>
                                <td >其他： </td>							
                           </tr>
							<tr>
								<td colspan="4">总体评估：
									<c:if test="${visitRecord.overallAbility==0 }">未见异常</c:if>
                                    <c:if test="${visitRecord.overallAbility==1 }">正常</c:if>
								</td>
							</tr>

						</tbody>
					</table>
				</div>
				<div class="panel-body">
					<table class="gw_xx" style="border:1px solid #f1f1f1;"
						cellpadding="10">
						<thead style="border-bottom:1px solid #f1f1f1">
							<th style="padding-left: 15px;">指导：</th>
						</thead>
						<tbody>
							<tr>
								<td style="padding-left: 15px;">类型：
									<td style="padding-left: 15px;"> 类型：
                                    	<c:if test="${visitRecord.guide==0 }">个人卫生</c:if>
                                		<c:if test="${visitRecord.guide==1 }">心理</c:if>
                                		<c:if test="${visitRecord.guide==2 }">营养</c:if>
                                		<c:if test="${visitRecord.guide==3 }">母乳喂养</c:if>
                                		<c:if test="${visitRecord.guide==4 }">新生儿护理与喂养</c:if>
                                    </td> 
								</td>
							</tr>
							<tr>
								<td style="padding-left: 15px;">内容：
									${visitRecord.guideContent }
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
                                	<c:if test="${visitRecord.transferVisit==0 }">无</c:if>
                                	<c:if test="${visitRecord.transferVisit==1 }">有</c:if>
                                </td>
                                <td>随访日期：
                                	<fmt:formatDate value="${visitRecord.visiterDate }" pattern="yyyy-MM-dd"/> 
                                </td>
                                <td>访视人员：
                                 	${visitRecord.visiterName}
                                </td> 
							</tr>
							<tr>
								<td>原因：
                                	${visitRecord.transferReason}
                                </td>
                                <td>访视机构：
                                	${visitRecord.visiterInstitutions }
                                </td>
                                <td>建议就诊机构及科室：
                                	${visitRecord.suggested }
                                </td> 
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>
	  <div class="gw_btn">
		<input class="btn btn-default" style="width: 85px; height:40px;padding:0px 0px;" readonly value="打印" type="button" id="aa"
			   onclick="document.getElementById('aa').style.display = 'none';window.print();" >
	  </div>
</body>
</html>
