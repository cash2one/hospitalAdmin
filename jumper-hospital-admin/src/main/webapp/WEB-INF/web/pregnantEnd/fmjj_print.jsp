<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>新生儿访视</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- 页头引入 -->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/media/css/pregnant/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/media/css/pregnant/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/media/css/pregnant/menu.css">
</head>
<body>
<div class="dayin">
                  <div style="width:100%; text-align:center"><h2>新生儿访视</h2></div>
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
			<div>
				<div class="weight-title">基本情况</div>
				<div class="panel-body">
					<table class="gw_xx">
						<tbody>
							<tr>
								<td width="35%">助产机构：${delivery.midwifery }</td>
								<td width="30%">住院号：${delivery.inpatientNumber }</td>
								<td width="35%">产后修养地址：${adress }</td>
							</tr>
							<tr>
								<td>分娩时间：<fmt:formatDate value="${delivery.deliveryTime }" pattern="yyyy-MM-dd HH:mm"/></span>
								</td>
								<td>分娩孕周：孕<span class="kong">${delivery.deliveryWeek}</span> 周 <span class="kong">${delivery.deliveryDay}</span> 天</td>
								<td>胎数：
								 <c:if test="${delivery.tire==1 }">单胎</c:if>
								 <c:if test="${delivery.tire==2 }">双胎</c:if>
								 <c:if test="${delivery.tire==3 }">多胎</c:if>
								 ${delivery.tireRemark }
								</td>
							</tr>
							<tr>
								<td>分娩方式：
									<c:if test="${delivery.deliveryWay==1 }">顺产</c:if>
									<c:if test="${delivery.deliveryWay==2 }">胎吸</c:if>
									<c:if test="${delivery.deliveryWay==3 }">臀助产</c:if>
									<c:if test="${delivery.deliveryWay==4 }">臀牵引</c:if>
									<c:if test="${delivery.deliveryWay==5 }">产钳</c:if>
									<c:if test="${delivery.deliveryWay==6 }">剖宫产</c:if>
									
								</td>
								<td>手术指征：${delivery.operIndication}</td>
								<td>胎盘娩出：
									<c:if test="${delivery.deliveryPlacenta==1 }">自然娩出</c:if>
									<c:if test="${delivery.deliveryPlacenta==2 }">人工剥离胎盘</c:if>
								</td>
							</tr>
							<tr>
								<td>胎盘情况：
									<c:if test="${delivery.placentalState==1 }">胎盘完整</c:if>
									<c:if test="${delivery.placentalState==2 }">胎盘残留</c:if>
									${delivery.placentalStateRemark }cm
								</td>
								<td>会阴情况：
									<c:if test="${delivery.perinealCondition==1 }">完整</c:if>
									<c:if test="${delivery.perinealCondition==2 }">切开</c:if>
									<c:if test="${delivery.perinealCondition==3 }">I级撕裂</c:if>
									<c:if test="${delivery.perinealCondition==4 }">II级撕裂</c:if>
									<c:if test="${delivery.perinealCondition==5 }">III级撕裂</c:if>
									<c:if test="${delivery.perinealCondition==6 }">IV级撕裂</c:if>
								</td>
								<td>产后并发症：
								 <c:if test="${delivery.postCompli==0 }">无</c:if>
								 <c:if test="${delivery.postCompli==1 }">有</c:if>
								 <span class="kong">${delivery.postCompliRemark }</span>
								</td>
							</tr>
							  <tr>
	                             <td >产后2小时：
                                  <label>出血量</label>${delivery.bleedAmount }ml 
                                 </td>
                                 <td>血压：
			                       	舒张压：${delivery.diastolic }mmHg &nbsp;&nbsp;&nbsp;收缩压：${delivery.systolic }mmHg
			                    </td>
                             </tr>
                             <tr>
                             	<td colspan="2" >总产程
                             		<label>${delivery.produceHour }小时</label>
                             		<label>${delivery.produceMin }分</label>
                             	</td>
                             </tr> 
						</tbody>
					</table>
				</div>
				
				<div class="weight-title">新生儿情况</div>
				<div class="panel-body">
					<table class="gw_xx">
						<tbody>
							<tr>
								<td width="35%">性别： 
								    <c:if test="${delivery.babySex==1 }">男</c:if>
									<c:if test="${delivery.babySex==2 }">女</c:if> 
									<c:if test="${delivery.babySex==0 }">其他</c:if>
								</td>
								<td width="30%">出生体重：${delivery.babyWeight }g</td>
								<td>身长：${delivery.babyHeight }cm</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="weight-title">出生时情况</div>
				<div class="panel-body mod_btn">
					<table class="gw_xx ">
						<tbody>
							<tr>
								<td colspan="2">Apgar评分： 1分钟 ${delivery.apgar1 } 分 
									<span class="kong">5分钟 ${delivery.apgar2 } 分</span> 
									<span class="kong">10分钟 ${delivery.apgar3 } 分</span>
								</td>
								<td>胎儿状况：
								    <c:if test="${delivery.fetalCondition==1 }">早产</c:if>
									<c:if test="${delivery.fetalCondition==2 }">足月产</c:if> 
									<c:if test="${delivery.fetalCondition==3 }">过期产</c:if>
									<c:if test="${delivery.fetalCondition==4 }">活产</c:if>
									<c:if test="${delivery.fetalCondition==5 }">死胎</c:if> 
									<c:if test="${delivery.fetalCondition==6 }">死产</c:if>
								</td>

								<td>新生儿死亡：<fmt:formatDate value="${delivery.babyDeadTime }" pattern="yyyy-MM-dd HH:mm" /></span></td>
								<td>死亡原因：${delivery.deadReason}</td>

							</tr>
							<tr>
								<td colspan="2">出生缺陷： 
									${delivery.birthDefect}
									${delivery.birthDefectRemark }
								</td>
								<td>窒息： 
									<c:if test="${delivery.choke==0 }">无</c:if>
									<c:if test="${delivery.choke==1 }">有</c:if>
								</td>
								<td>窒息程度：
								    <c:if test="${delivery.chokeDegree==1 }">轻</c:if>
									<c:if test="${delivery.chokeDegree==2 }">重</c:if>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="weight-title">诊断</div>
				<div class="gw_jy">
					<div class="cbzd">
						<p class="float-left">诊断内容：</p>
						${delivery.diagnosticContent }
					</div>

					<div class="panel-body">
						<table class="gw_xx">
							<tbody>
								<tr>
									<td>登记机构：${delivery.registerAgency }</td>
									<td>登记人员：${delivery.registerPerson }</td>
									<td>登记日期：<fmt:formatDate value="${delivery.registerTime }" pattern="yyyy-MM-dd" />
									</td>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
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