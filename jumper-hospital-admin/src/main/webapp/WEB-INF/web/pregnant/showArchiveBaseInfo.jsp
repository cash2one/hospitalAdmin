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
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/addArchiveBaseInfo.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/loadArchiveBaseInfo.js"></script>
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
td
{
    text-align:center;
}
</style>
<!--style-->
</head>
<body style="background: #F0F0F0;">
			<!-- 档案管理 高危筛查 -->
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas"
				style="padding-right: 20px;">

				<ul class="page-sidebar-menu">
					<li class="active"><a
						href="${pageContext.request.contextPath}/pregnant/archiveIndex">
							<i class="icon-plus-sign-alt"></i>&nbsp; <span class="title">档案管理</span>
					</a>
					</li>
				</ul>
			</div>
			<input type="hidden" id="archiveId" value="${archiveId }"/>
			<input type="hidden" id="archiveBaseId" value="${archiveBaseId }"/>
			<!-- 内容页 -->
			<div class="col-xs-12 col-sm-9">
				<div class="panel">
					<div class="gw_rote">
						<span class="float-left" style="font-size:16px;">档案管理 >
							档案详情 > <a style=" color:#ff7ea5">基本信息</a>
						</span>
					</div>
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
						<div class="panel panel-default float-left"
							style="margin:15px 0px;width: 100%;">
							<div class=" float-left" style="width:100%;">
								<ul id="myTab" class="nav nav-tabs">
									<li class="active"><a>基本信息</a>
									</li>
									<li><a
										href="${pageContext.request.contextPath}/pregnant/showArchiveHistoryTakingInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}">病史询问</a>
									</li>
									<li><a
										href="${pageContext.request.contextPath}/pregnant/showArchiveTestCaseInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}">初检情况</a>
									</li>
									 <li><a href="${pageContext.request.contextPath}/monitor/weight?userId=${userBookBasicInfo.userId}&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" >监测数据</a></li>
		                            <li><a href="${pageContext.request.contextPath}/PregnantEnd/lookIndex?userId=${archiveBaseId}&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" >分娩结局</a></li>
		                            <li><a href="${pageContext.request.contextPath}/postnatal/vistRecord?userId=${archiveBaseId}&visitType=1&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}">产后访视</a></li>
		                            <li><a href="${pageContext.request.contextPath}/neonatal/lookIndex?userId=${archiveBaseId}&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" >新生儿访视</a></li>
								</ul>

							</div>

							<div id="cont_roll" class="panel panel-default float-left"
								style=" margin:15px;width: 98%;">
								<div class="weight-title">孕妇基本信息</div>
								<div class="panel-body">
									<table class="gw_xx">
										<tbody>
											<tr>
												<td>姓名：${userBookBasicInfo.name }</td>
												<td>手机电话：${userBookBasicInfo.mobile }</td>
												<td>身份证号：${userBookBasicInfo.idNo}</td>
												<td>出生日期：<fmt:formatDate
														value='${userBookBasicInfo.birthday}' pattern='yyyy-MM-dd' /></span>
												</td>
												<td>年龄：${userBookBasicInfo.age}岁</td>
												<td rowspan="5" width="5%"><img src="${userBookBasicInfo.userImg }"
													width="90px" height="135px;"
													style=" border:1px solid #d0d0d0">
												</td>
											</tr>
											<tr>
												<td>固定电话：</td>
												<td>国籍：${country.country }</td>
												<td>民族：${nation.name}</td>
												<td>籍贯：${privince1.proName }${city1.cityName }</td>
											</tr>
											<tr>
												<td>血型： <c:if
														test="${userBookBasicInfo.bloodType == '1'}">A</c:if> <c:if
														test="${userBookBasicInfo.bloodType == '2'}">B</c:if> <c:if
														test="${userBookBasicInfo.bloodType == '3'}">O</c:if> <c:if
														test="${userBookBasicInfo.bloodType == '4'}">AB</c:if> <c:if
														test="${userBookBasicInfo.bloodType == '5'}">不详</c:if> <c:if
														test="${userBookBasicInfo.bloodType == '6'}">其他</c:if></td>
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
												<td>结婚日期：<fmt:formatDate
														value='${userBookBasicInfo.marryDate}'
														pattern='yyyy-MM-dd' /></td>
												<td>文化程度：${education.name}</td>
												<td>职业：${profession.profession}</td>
											</tr>
											<tr>
												<td>社保卡号：${userHospitalMapping.socialSecurityCode}</td>
												<td>社保编号：${userBookBasicInfo.socialSecurityNum}</td>
												<td>健康卡号：${userHospitalMapping.healthCode}</td>
												<td></td>
												<td>户主姓名：${userBookBasicInfo.householderName }</td>
											</tr>
											<tr>
												<td>与户主关系： <c:if
														test="${userBookBasicInfo.householderRelation == '1'}">本人</c:if>
													<c:if
														test="${userBookBasicInfo.householderRelation == '2'}">夫妻</c:if>
													<c:if
														test="${userBookBasicInfo.householderRelation == '3'}">父女</c:if>
													<c:if
														test="${userBookBasicInfo.householderRelation == '4'}">母女</c:if>
													<c:if
														test="${userBookBasicInfo.householderRelation == '5'}">其他</c:if>
												</td>
												<td>紧急联系人姓名：${userBookBasicInfo.emergencyName}</td>
												<td>紧急联系人电话：${userBookBasicInfo.emergencyPhone}</td>
												<td>末次月经（LMP）：${userBookBasicInfo.lastPeriod }</td>
												<td>预产期（EDC)：<fmt:formatDate
														value='${userBookBasicInfo.pregnantDate }' pattern='yyyy-MM-dd' /></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="weight-title">孕妇户籍地址信息</div>
								<div class="panel-body">
									<table class="gw_xx">
										<tbody>
											<tr>
												<td>户籍类别： <c:if
														test="${userBookBasicInfo.residentType == '1'}">城镇户口</c:if>
													<c:if test="${userBookBasicInfo.residentType == '2'}">农村户口</c:if>
													<c:if test="${userBookBasicInfo.residentType == '3'}">其他</c:if>
												</td>
												<td>户籍地址：${privince3.proName }${city3.cityName }</td>
												<td>常驻类型： <c:if
														test="${userBookBasicInfo.resident == '1'}">常住</c:if> <c:if
														test="${userBookBasicInfo.resident == '2'}">暂住</c:if> <c:if
														test="${userBookBasicInfo.resident == '3'}">流动</c:if></td>
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
												<td>出生日期：<fmt:formatDate
														value='${husbandInfo.birthday}' pattern='yyyy-MM-dd' /></td>
												<td>年龄：${husbandInfo.age}岁</td>
												<td>联系电话：${husbandInfo.mobile}</td>
											</tr>
											<tr>
												<td>国籍：${country.country}</td>
												<td>籍贯：${privince6.proName }${city6.cityName }</td>
												<td>民族：${husbandNation.name}</td>
												<td>文化程度：${husbandEducation.name}</td>
												<td>职业：${husbandInfo.job }</td>
											</tr>
										</tbody>
									</table>
								</div>

								<div class="weight-title">丈夫户籍地址信息</div>
								<div class="panel-body">
									<table class="gw_xx">
										<tbody>
											<tr>
												<td>户籍类别： <c:if test="${husbandInfo.resident == '1'}">
														<span>城镇户口</span>
													</c:if> <c:if test="${husbandInfo.resident == '2'}">
														<span>农村户口</span>
													</c:if> <c:if test="${husbandInfo.resident == '3'}">
														<span>其他</span>
													</c:if></td>
												<td>户籍地址：${privince4.proName }${city4.cityName }</td>
												<td>常驻类型：长住</td>
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
											<c:if test="${userFamilyMemberList!=null}">
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
											</c:if>
											<c:if test="${empty userFamilyMemberList}">
												<tr>
													<td colspan="7" style="text-align: center;color:red;">暂无数据</td>
												</tr>
											</c:if>
										</tbody>
									</table>
								</div>

								<!-- <div class="weight-title">生活环境</div>
								<div class="panel-body">
									<div id="live_inf">
										<span>居住条件</span>
										<div class="live_inftable">
											<table class="gw_xx">
												<tbody>
													<tr>
														<td width="25%">房屋结构：</td>
														<td width="25%">居住楼层：</td>
														<td width="25%">楼房电梯：</td>
														<td width="25%">居住房间：</td>

													</tr>
													<tr>
														<td width="25%">人均面积：</td>
														<td width="25%">厨房情况：</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<div id="live_inf">
										<span>使用设备</span>
										<div class="live_inftable">
											<table class="gw_xx">
												<tbody>
													<tr>
														<td width="25%">饮用水：</td>
														<td width="25%">取暖方式：</td>
														<td width="25%">倒垃圾方式：</td>
														<td width="25%">污水排泄：</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<div id="live_inf">
										<span>家庭卫生情况</span>
										<div class="live_inftable">
											<table class="gw_xx">
												<tbody>
													<tr>
														<td width="25%">害虫情况：</td>
														<td width="25%">室内卫生：</td>

													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<div id="live_inf">
										<span>饮食卫生</span>
										<div class="live_inftable">
											<table class="gw_xx">
												<tbody>
													<tr>
														<td width="25%">是否吸烟：</td>
														<td width="25%">菜板生熟分开：</td>
														<td width="25%">冰箱生熟分开：</td>
														<td width="25%">引用生水：</td>

													</tr>
												</tbody>
											</table>
										</div>
									</div>

								</div> -->

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


						<div class="gw_btn">
							<a href="${pageContext.request.contextPath}/pregnant/toUpdateArchiveBaseInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}"><input
								class="btn btn-default oneline"
								style="width: 85px; height:40px;padding:0px 0px;" readonly
								value="修改" type="button">
							</a> 
							<a href="${pageContext.request.contextPath}/pregnant/toPrintArchiveBaseInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}"><input
								class="btn btn-default oneline"
								style="width: 85px; height:40px;padding:0px 0px;" readonly
								value="打印" type="button">
							</a>
						</div>

						<!-- 打印按钮触发 -->
						<!-- 模态框结束 -->

					</div>
				</div>

			</div>
			<!-- 引入页尾 -->

</body>