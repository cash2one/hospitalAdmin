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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/media/js/pregnantArchive/jquery.tips.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/media/js/pregnantArchive/addArchiveBaseInfo.js"></script>
<style type="text/css">
html {
	overflow-x: scroll;
}

body {
	min-width: 1660px;
	overflow-y: hidden;
}

.message-dialog {
	width: 800px;
	height: 500px;
}

* {
	padding: 0;
	margin: 0;
}

.panel {
	margin-bottom: 0px;
}

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

.gw_btn {
	width: 280px;
}
.top-la ul{
	list-style: none;
	
}
.top-la ul li{
	float: right;
	margin:10px
}
</style>
<!--style-->

<script type="text/javascript">
	$(function(){
		//个人基本信息打印
			$("#printBaseInfoOut").bind("click",function(){
				$(".top-la").hide(); 
				window.print();  
			});
			$("#backBaseInfo").bind("click",function(){
				history.go(-1);  
			});
	});
</script>
</head>
<body>

	<div class="dayin">
			<div class="top-la">
						<ul>
							
							<li id="printBaseInfoOut"><img
								src="${pageContext.request.contextPath}/media/image/btn3.png">
							</li>
							<li id="backBaseInfo"><img
								src="${pageContext.request.contextPath}/media/image/btn_back.png">
							</li>
						</ul>
			</div>
		<div style="width:100%; text-align:center">
			<h2>基本信息</h2>
		</div>

		<div class="panel-body dayin" style="padding:15px;">
			<table>
				<tbody>
					<tr>
						<td width="20%">姓名： ${userBookBasicInfo.name}</td>
						<td width="25%">联系电话： ${userBookBasicInfo.mobile}</td>
						<td width="35%">身份证号：${userBookBasicInfo.idNo}</td>
						<td width="20%">年龄：${userBookBasicInfo.age}岁</td>
					</tr>
					<tr>
						<td width="20%">建册日期： <fmt:formatDate
								value='${userBookBasicInfo.addTime}' pattern='yyyy-MM-dd' />
						</td>
						<td width="25%">建档人员： <c:if
								test="${userHospitalMapping2.memberId!=0 }">
								<span>${communityMember.name }</span>
							</c:if> <c:if test="${userHospitalMapping2.memberId==0 }">
								<span>${newCommunity.responsiblePerson}</span>
							</c:if></td>
						<td colspan="2">建册医院：</td>
					</tr>
					<tr>
						<td width="20%">建档社康：${newCommunity.name}</td>
						<td width="25%">所属社康：${community.name}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="panel panel-default float-left"
			style=" width: 100%;border:1px solid #f1f1f1;">
			<div class="weight-title">孕妇基本信息</div>
			<div class="panel-body">
				<table class="gw_xx">
					<tbody>
						<tr>
							<td>姓名：${userBookBasicInfo.name}</td>
							<td>手机电话：${userBookBasicInfo.mobile}</td>
							<td>身份证号：${userBookBasicInfo.idNo}</td>
						</tr>
						<tr>
							<td>出生日期：<fmt:formatDate
									value='${userBookBasicInfo.birthday}' pattern='yyyy-MM-dd' />
							</td>
							<td>年龄：${userBookBasicInfo.age}岁</td>
						</tr>
						<tr>
							<td>固定电话：${userBookBasicInfo.contactPhone }</td>
							<td>国籍：${country.country }</td>
							<td>民族：${nation.name}</td>
						</tr>
						<tr>
							<td>籍贯：${privince3.proName }${city3.cityName }</td>
							<td>血型：
							    <c:if test="${userBookBasicInfo.bloodType == '1'}">A</c:if> 
							    <c:if test="${userBookBasicInfo.bloodType == '2'}">B</c:if> 
								<c:if test="${userBookBasicInfo.bloodType == '3'}">O</c:if> 
								<c:if test="${userBookBasicInfo.bloodType == '4'}">AB</c:if> 
								<c:if test="${userBookBasicInfo.bloodType == '5'}">不详</c:if> 
								<c:if test="${userBookBasicInfo.bloodType == '6'}">其他</c:if>
							</td>
							<td>婚姻状况：
								<c:if test="${userBookBasicInfo.maritalStatus == '10'}">已婚</c:if>
								<c:if test="${userBookBasicInfo.maritalStatus == '20'}">未婚</c:if>
								<c:if test="${userBookBasicInfo.maritalStatus == '21'}">初婚</c:if>
								<c:if test="${userBookBasicInfo.maritalStatus == '22'}">再婚</c:if>
								<c:if test="${userBookBasicInfo.maritalStatus == '23'}">复婚 </c:if>
								<c:if test="${userBookBasicInfo.maritalStatus == '30'}">丧偶</c:if>
								<c:if test="${userBookBasicInfo.maritalStatus == '40'}">离婚</c:if>
								<c:if test="${userBookBasicInfo.maritalStatus == '90'}">未说明的婚姻状况</c:if>
							</td>
						</tr>
						<tr>
							<td>结婚日期：<fmt:formatDate value='${userBookBasicInfo.marryDate}' pattern='yyyy-MM-dd' /></td>
							<td>文化程度：${education.name}</td>
							<td>职业：${profession.profession}</td>

						</tr>
						<tr>
							<td>社保卡号：${userHospitalMapping.socialSecurityCode}</td>
							<td>社保编号：${userBookBasicInfo.socialSecurityNum}</td>
							<td>健康卡号：${userHospitalMapping.healthCode}</td>

						</tr>
						<tr>
							<td>出生地：</td>
							<td>户主姓名：${userBookBasicInfo.householderName }</td>
							<td>与户主关系：
								 <c:if test="${userBookBasicInfo.householderRelation == '1'}">本人</c:if>
								<c:if test="${userBookBasicInfo.householderRelation == '2'}">夫妻</c:if>
								<c:if test="${userBookBasicInfo.householderRelation == '3'}">父女</c:if>
								<c:if test="${userBookBasicInfo.householderRelation == '4'}">母女</c:if>
								<c:if test="${userBookBasicInfo.householderRelation == '5'}">其他</c:if>
							</td>
						</tr>
						<tr>
							<td>紧急联系人姓名：${userBookBasicInfo.emergencyName}</td>
							<td>紧急联系人电话：${userBookBasicInfo.emergencyPhone}</td>
							<td>末次月经（LMP）：${userBookBasicInfo.lastPeriod}</td>
						</tr>
						<tr>
							<td>预产期（EDC)：<fmt:formatDate value='${userBookBasicInfo.pregnantDate}' pattern='yyyy-MM-dd' /></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="weight-title">孕妇户籍地址信息</div>
			<div class="panel-body">
				<table class="gw_xx">
					<tbody>
						<tr>
							<td>户籍类别：
								<c:if test="${userBookBasicInfo.residentType == '1'}">城镇户口</c:if>
								<c:if test="${userBookBasicInfo.residentType == '2'}">农村户口</c:if>
								<c:if test="${userBookBasicInfo.residentType == '3'}">其他</c:if>
							</td>
							<td>户籍地址：${privince1.proName }${city1.cityName }</td>
							<td>常驻类型：
								 <c:if test="${userBookBasicInfo.resident == '1'}">常住</c:if> 
								 <c:if test="${userBookBasicInfo.resident == '2'}">暂住</c:if> 
								<c:if test="${userBookBasicInfo.resident == '3'}">流动</c:if>
							</td>
						</tr>
						<tr>
							<td>现住地址：${privince2.proName}${city2.cityName}${district.name}${userBookBasicInfo.nowStayAddress}</td>
							<td>现住址管辖派出所：长住</td>
						</tr>
						<tr>
							<td>工作单位：${userBookBasicInfo.workUnit }</td>

						</tr>


					</tbody>
				</table>
			</div>

			<div class="weight-title">丈夫基本信息</div>
			<div class="panel-body">
				<table class="gw_xx">
					<tbody>
						<tr>
							<td>姓名：${husbandInfo.name}</td>
							<td>身份证号：${husbandInfo.idNo}</td>
							<td>出生日期：<fmt:formatDate value='${husbandInfo.birthday}' pattern='yyyy-MM-dd' /></td>
							<td>年龄：${husbandInfo.age}岁</td>
						</tr>
						<tr>
							<td>联系电话：${husbandInfo.mobile}</td>
							<td>国籍：${country.country}</td>
							<td>籍贯：${privince6.proName }${city6.cityName }</td>
							<td>民族：</td>
						</tr>
						<tr>
							<td>文化程度：${education.name}</td>
							<td>职业：</td>
						</tr>


					</tbody>
				</table>
			</div>

			<div class="weight-title">丈夫户籍地址信息</div>
			<div class="panel-body">
				<table class="gw_xx">
					<tbody>
						<tr>
							<td>户籍类别：
								 <c:if test="${husbandInfo.resident == '1'}"><span>城镇户口</span></c:if> 
								 <c:if test="${husbandInfo.resident == '2'}"><span>农村户口</span></c:if> 
								 <c:if test="${husbandInfo.resident == '3'}"><span>其他</span></c:if>
							</td>
							<td>户籍地址：${privince4.proName }${city4.cityName }</td>
							<td>常驻类型：长住</td>
						</tr>
						<tr>
							<td>现住地址：${privince5.proName}${city5.cityName}${district1.name}${husbandInfo.nowStayAddress}</td>
							<td>工作单位：${husbandInfo.workUnit}</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="weight-title">家庭成员</div>
			<div class="panel-body">
				<table class="gw_xx">
					<thead>
						<th>姓名</th>
						<th>性别</th>
						<th>身份证号码</th>
						<th>出生日期</th>
						<th>与户主关系</th>
						<th>是否医保</th>
						<th>联系电话</th>
					</thead>
					<tbody>
						<c:forEach items="${userFamilyMemberList}" var="vo">
						<tr>
							<td><input type="hidden" name="archiveId" value="${vo.archiveId }"/>
							<input type="hidden" name="id" value="${vo.id }"/>
							${vo.personName }</td>
							<td>
								<div class="basic-block-cen">
									<div class="input-group add-au" id="xixi">
										<c:if test="${vo.sex==0 }">男</c:if>
										<c:if test="${vo.sex==1 }">女</c:if>
										<c:if test="${vo.sex==2 }">其他</c:if>
									</div>

								</div>
							</td>
							<td>${vo.idNo }</td>
							<td><fmt:formatDate
								value='${vo.birthday }' pattern='yyyy-MM-dd' /></td>
							<td>
								<div class="basic-block-cen">
									<div class="input-group add-au" class="xixi">
										<c:if test="${vo.houseRelation==1 }">本人</c:if>
										<c:if test="${vo.houseRelation==2 }">夫妻</c:if>
										<c:if test="${vo.houseRelation==3 }">父子</c:if>
										<c:if test="${vo.houseRelation==4 }">母子</c:if>
										<c:if test="${vo.houseRelation==5 }">其他</c:if>
									</div>
								</div></td>
							<td>

								<div class="basic-block-cen">
									<div class="input-group add-au" id="xixi">
										<c:if test="${vo.isMedicare==1 }">是</c:if>
										<c:if test="${vo.isMedicare==0 }">否</c:if>
									</div>
								</div>
							</td>
							<td>${vo.contactWay }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="weight-title">建档信息</div>
			<div class="panel-body">
				<table class="gw_xx">
					<tbody>
						<tr>
							<td>建册日期:</td>
							<td>建档人员：</td>
							<td>建册医院：</td>

						</tr>
						<tr>
							<td>建档社康:</td>
							<td>建档社康电话：</td>
							<td>所属社康：</td>

						</tr>
					</tbody>
				</table>
			</div>


		</div>
	</div>
</body>