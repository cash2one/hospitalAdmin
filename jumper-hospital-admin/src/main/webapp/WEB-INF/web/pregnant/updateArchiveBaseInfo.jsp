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
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/updateArchiveBaseInfo.js"></script>
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

input, select {
    height: 25px;
    padding: 0 5px;
}

.gw_btn {
	width: 280px;
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

			<!-- 内容页 -->
			<div class="col-xs-12 col-sm-9">
				<div class="panel">
					<div class="gw_rote">
						<span class="float-left" style="font-size:16px;">档案管理 >
							档案详情 > <a style=" color:#ff7ea5">基本信息-修改</a>
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
									<%-- <li><a href="${pageContext.request.contextPath}/pregnant/showArchiveHistoryTakingInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" >病史询问</a></li>
                            <li><a href="${pageContext.request.contextPath}/pregnant/showArchiveTestCaseInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" >初检情况</a></li>
                            <li><a>高危筛查</a></li>
                            <li><a href="${pageContext.request.contextPath}/monitor/weight" >监测数据</a></li>
                            <li><a>分娩结局</a></li>
                            <li ><a>产后访视</a></li>
                            <li><a href="${pageContext.request.contextPath}/neonatal/lookIndex?userId=230" >新生儿访视</a></li> --%>
								</ul>

							</div>
							<form action="${pageContext.request.contextPath}/pregnant/updatePregnantArchiveBaseInfo" id="updatePregnantArchiveBaseInfoForm" method="post">
							<input type="hidden" id="archiveId" name="id" value="${archiveId }"/>
							<input type="hidden" id="archiveBaseId" name="pregnantUserId" value="${archiveBaseId }"/>
							<input type="hidden" id="husbandInfoId" name="husbandInfoId" value="${husbandInfo.id }"/>
							<input type="hidden"  name=sex value="${userBookBasicInfo.sex }"/>
							<input type="hidden"  name=type value="${userHospitalMapping.type}"/>
							<div id="cont_roll" class="panel panel-default float-left"
								style=" margin:15px;width: 98%;">
								<div class="weight-title">孕妇基本信息</div>
								<div id="jbxx_input" class="panel-body">
									<table class="gw_xx">
										<tbody>
											<tr>
												<td><span style="color:red; font-size:18px;">*</span>姓名：<input
													type="text" value="${userBookBasicInfo.name }" name="name" id="name">
												</td>
												<td><span style="color:red; font-size:18px;">*</span>手机电话：<input
													type="tel" value="${userBookBasicInfo.mobile }" name="mobile" id="mobile"></td>
												<td rowspan="3" width="5%">
													
												</td>
											</tr>
											<tr>
												<td>身份证号：<input style="width:160px;" type="text"
													value="${userBookBasicInfo.idNo }" name="idNo" id="idNo">
												</td>
												<td>出生日期： <input type="text" 
													 value="<fmt:formatDate
														value='${userBookBasicInfo.birthday}' pattern='yyyy-MM-dd' />" placeholder="日期选择"
													name="birthday" id="birthday"></td>
												
											</tr>
											<tr>
												
												<td>年龄：<input name="age" style="border:none;text-align: right;size:1;width:50px;" id="age" value="${userBookBasicInfo.age }" readonly="readonly"/>岁</td>
												<td>固定电话：<input style="width:50px;" type="text"
													value="" name="areaCode"> - <input type="text"
													value="" name="phoneNo">
												</td>
											</tr>
											<tr>
												<td>国籍：
													<select name="countryId" id="country">
												     	<c:forEach items="${countrys}" var="country">
									             			<c:if test="${not empty userBookBasicInfo.countryId}">
									             				<option value="${country.id}" <c:if test="${country.id == userBookBasicInfo.countryId}">selected="selected"</c:if>>${country.country}</option>
										            		</c:if>
												            <c:if test="${empty userBookBasicInfo.countryId}">
												                <option value="${country.id}" <c:if test="${country.country == '中国'}">selected="selected"</c:if>>${country.country}</option>
												            </c:if>
									            		</c:forEach>
													</select>
												</td>
												<td>民族： <select title="民族" name="nationId">
														<c:forEach items="${nations}" var="nation">
															<c:if test="${not empty userBookBasicInfo.nationId}">
																<option value="${nation.id}"
																	<c:if test="${nation.id == userBookBasicInfo.nationId}">selected="selected"</c:if>>${nation.name}</option>
															</c:if>
															<c:if test="${empty userBookBasicInfo.nationId}">
																<option value="${nation.id}"
																	<c:if test="${nation.name == '汉族'}">selected="selected"</c:if>>${nation.name}</option>
															</c:if>
														</c:forEach>
												</select>
												</td>
											</tr>
											<tr>
												<td>籍贯：<select name="provinceId"
													id="nativePlaceProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${userBookBasicInfo.privinceId== province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select> <select name="cityId" id="nativePlaceCity">
														<option value="${userBookBasicInfo.cityId }" selected="selected"></option>
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select></td>
												<td>血型： <label><input style="width:10px;"
														type="radio" name="bloodType"
														value="1"
														<c:if test="${userBookBasicInfo.bloodType==1 }">checked</c:if>>
														A</label> <label><input style="width:10px;" type="radio"
														name="bloodType" value="2"
														<c:if test="${userBookBasicInfo.bloodType==2 }">checked</c:if>>
														B</label> <label><input style="width:10px;" type="radio"
														name="bloodType" value="3"
														<c:if test="${userBookBasicInfo.bloodType==3 }">checked</c:if>>
														O</label> <label><input style="width:10px;" type="radio"
														name="bloodType" value="4"
														<c:if test="${userBookBasicInfo.bloodType==4 }">checked</c:if>>
														AB</label> <label style="margin-left:30px;">RH：<input
														style="width:10px;" value="5"
														type="radio" name="bloodType"
														<c:if test="${userBookBasicInfo.bloodType==5 }">checked</c:if>>
														阴性</label></td>
											</tr>
											<tr>
												
												<td>婚姻状况：<select title="婚姻状况" name="maritalStatus"
													id="maritalStatus">
														<option value="0">请选择</option>
														<option value="10"
															<c:if test="${userBookBasicInfo.maritalStatus == '10'}">selected="selected"</c:if>>已婚</option>
														<option value="20"
															<c:if test="${userBookBasicInfo.maritalStatus == '20'}">selected="selected"</c:if>>未婚</option>
														<option value="21"
															<c:if test="${userBookBasicInfo.maritalStatus == '21'}">selected="selected"</c:if>>初婚</option>
														<option value="22"
															<c:if test="${userBookBasicInfo.maritalStatus == '22'}">selected="selected"</c:if>>再婚</option>
														<option value="23"
															<c:if test="${userBookBasicInfo.maritalStatus == '23'}">selected="selected"</c:if>>复婚
														</option>
														<option value="30"
															<c:if test="${userBookBasicInfo.maritalStatus == '30'}">selected="selected"</c:if>>丧偶</option>
														<option value="40"
															<c:if test="${userBookBasicInfo.maritalStatus == '40'}">selected="selected"</c:if>>离婚</option>
														<option value="90"
															<c:if test="${userBookBasicInfo.maritalStatus == '90'}">selected="selected"</c:if>>未说明的婚姻状况</option>
												</select>
												</td>
												<td>结婚日期：<input type="text" 
													 name="marryDate" id="marryDate"
													value="<fmt:formatDate
														value='${userBookBasicInfo.marryDate }' pattern='yyyy-MM-dd' />"></td>

											</tr>
											<tr>
												<td>文化程度：<select title="文化程度" name="educationId">
														<option value="0">请选择文化程度</option>
														<c:forEach items="${educations}" var="education">
															<option value="${education.id}"
																<c:if test="${education.id == userBookBasicInfo.educationId}">selected="selected"</c:if>>${education.name}</option>
														</c:forEach>
												</select>
												</td>
												<td>职业：<select title="职业" name="professionId">
														<option value="0">请选择职业</option>
														<c:forEach items="${professions}" var="profession">
															<option value="${profession.id}"
																<c:if test="${profession.id == userBookBasicInfo.professionId}">selected="selected"</c:if>>${profession.profession}</option>
														</c:forEach>
												</select>
												</td>
											</tr>
											<tr>
												<td>社保卡号：<input type="text" name="socialSecurityCode"
													value="${userHospitalMapping.socialSecurityCode }">
												</td>
											
												<td>社保编号：<input type="text" name="socialSecurityNum"
													value="${userBookBasicInfo.socialSecurityNum }">
												</td>

											</tr>
											<tr>
												<td>健康卡号：<input type="text" name="healthCode"
													value="${userHospitalMapping.healthCode }">
												</td>
												<td>出生地：<select name="birthAddressId">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${userBookBasicInfo.birthAddressId == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select>
												</td>
											</tr>
											<tr>
												<td>户主姓名：<input type="text" name="householderName"
													value="${userBookBasicInfo.householderName }">
												</td>
											
												<td>与户主关系：
													<!--  <div class="input-group" id="xixi">-->
														<select class="selectpicker input-d2"
															name="householderRelation" id="householderRelation">
															<option value="0">请选择关系</option>
															<option value="1"
																<c:if test="${userBookBasicInfo.householderRelation == '1'}">selected="selected"</c:if>>本人</option>
															<option value="2"
																<c:if test="${userBookBasicInfo.householderRelation == '2'}">selected="selected"</c:if>>夫妻</option>
															<option value="3"
																<c:if test="${userBookBasicInfo.householderRelation == '3'}">selected="selected"</c:if>>父女</option>
															<option value="4"
																<c:if test="${userBookBasicInfo.householderRelation == '4'}">selected="selected"</c:if>>母女</option>
															<option value="5"
																<c:if test="${userBookBasicInfo.householderRelation == '5'}">selected="selected"</c:if>>其他</option>
														</select>
													<!--</div>--></td>
											</tr>
											<tr>
												<td>紧急联系人姓名：<input type="text" name="emergencyName"
													value="${userBookBasicInfo.emergencyName }">
												</td>
												<td>紧急联系人电话：<input type="tel" name="emergencyPhone"
													value="${userBookBasicInfo.emergencyPhone }">
												</td>
											</tr>
											<tr>
												
											
												<td>末次月经（LMP）：<input type="text" name="lastPeriod" id="lmp" value="${userBookBasicInfo.lastPeriod }" placeholder="日期选择">
												</td>
												<td><span style="color:red; font-size:18px;">*</span>预产期（EDC)：<input
													type="text" name="pregnantDate" id="pregnantDate" value="<fmt:formatDate
														value='${userBookBasicInfo.pregnantDate }' pattern='yyyy-MM-dd' />" placeholder="日期选择">
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="weight-title">孕妇户籍地址信息</div>
								<div class="panel-body">
									<table class="gw_xx">
										<tbody>
											<tr>
												<td>户籍类别： <label><input style="width:10px;"
														type="radio" name="resident"
														value="${userBookBasicInfo.resident }"
														<c:if test="${userBookBasicInfo.resident==1 }">checked</c:if> />
														城镇户口</label> <label><input style="width:10px;"
														type="radio" name="resident"
														value="${userBookBasicInfo.resident }"
														<c:if test="${userBookBasicInfo.resident==2 }">checked</c:if> />
														农村户口</label></td>
												<td>户籍地址： <select name="domicoleProvince"
													id="domicoleProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${userBookBasicInfo.domicoleProvince == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select> <select name="domicoleCity" id="domicoleCity">
														<option value="${userBookBasicInfo.domicoleCity }" selected="selected"></option>
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select></td>
											</tr>
											<tr>
												<td>常驻类型： <label><input style="width:10px;"
														type="radio" name="residentType"
														value="1"
														<c:if test="${userBookBasicInfo.residentType==1 }">checked</c:if> />常住</label>
													<label><input style="width:10px;" type="radio"
														name="residentType"
														value="2"
														<c:if test="${userBookBasicInfo.residentType==2 }">checked</c:if> />暂住</label>
													<label><input style="width:10px;" type="radio"
														name="residentType"
														value="3"
														<c:if test="${userBookBasicInfo.residentType==3 }">checked</c:if> />
														流动</label></td>
												<td>现住地址： <select name="nowStayProvince"
													id="nowStayProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${userBookBasicInfo.nowStayProvince == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select> <select name="nowStayCity" id="nowStayCity">
														<option value="${userBookBasicInfo.nowStayCity }" selected="selected"></option>
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select> <input type="text" value="" placeholder="请输入详细地址">
												</td>
											</tr>
											<tr>
												
												<td>现住址管辖派出所： <select name="nowPoliceStationProvince"
													id="nowPoliceStationProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${userBookBasicInfo.nowStayProvince == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select> <select name="nowPoliceStationCity"
													id="nowPoliceStationCity">
														<option value="${userBookBasicInfo.nowStayCity }" selected="selected"></option>
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select><input type="text"/> 派出所 <input type="hidden" name="nowPoliceStationAddress"
													id="nowPoliceStationAddress" value="${userBookBasicInfo.nowPoliceStationAddress }" />
												</td>
												<td>工作单位：<input type="text" value="${userBookBasicInfo.workUnit }">
												</td>

											</tr>


										</tbody>
									</table>
								</div>

								<div class="weight-title">丈夫基本信息</div>
								<div class="panel-body">
									<table class="gw_xx">
										<tbody>
											<tr>
												<td>姓名：<input type="text" value="${husbandInfo.name}">
												</td>
												<td>身份证号：<input type="text" value="${husbandInfo.idNo}">
												</td>
											</tr>
											<tr>
												<td>出生日期：<input type="text" 
													 value="<fmt:formatDate
														value='${husbandInfo.birthday}' pattern='yyyy-MM-dd' />" placeholder="日期选择"
													name="husbandBirthday" id="husbandBirthday"></td>
												<td>年龄:<input name="husbandAge" style="border:none;text-align: right;size:1;width:50px;" id="husbandAge" value="${husbandInfo.age}" readonly="readonly"/>岁</td>

											</tr>
											<tr>
												<td>联系电话：<input type="text" value="">
												</td>
												<td>国籍：
													<select name="husbandCountryId">
														<c:forEach items="${countrys}" var="country">
									             			<c:if test="${not empty husbandInfo.countryId}">
									             				<option value="${country.id}" <c:if test="${country.id == husbandInfo.countryId}">selected="selected"</c:if>>${country.country}</option>
										            		</c:if>
												            <c:if test="${empty husbandInfo.countryId}">
												                <option value="${country.id}" <c:if test="${country.country == '中国'}">selected="selected"</c:if>>${country.country}</option>
												            </c:if>
									            		</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<td>籍贯： 
												 <select name="husbandProvinceId" id="husbandProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${husbandInfo.privinceId == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select>
												<select name="husbandCityId" id="husbandCity">
														<option value="${husbandInfo.cityId }" selected="selected"></option>
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select>
												</td>
												<td>民族：
													<select title="民族" name="husbandNationId">
														<c:forEach items="${nations}" var="nation">
															<c:if test="${not empty userBookBasicInfo.nationId}">
																<option value="${nation.id}"
																	<c:if test="${nation.id == husbandInfo.nationId}">selected="selected"</c:if>>${nation.name}</option>
															</c:if>
															<c:if test="${empty userBookBasicInfo.nationId}">
																<option value="${nation.id}"
																	<c:if test="${nation.name == '汉族'}">selected="selected"</c:if>>${nation.name}</option>
															</c:if>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<td>文化程度：
												 <select title="文化程度" name="husbandEducationId">
														<option value="0">请选择文化程度</option>
														<c:forEach items="${educations}" var="education">
															<option value="${education.id}"
																<c:if test="${education.id == husbandInfo.educationId}">selected="selected"</c:if>>${education.name}</option>
														</c:forEach>
												</select>
												</td>
												<td>职业： 
												<select title="职业" name="husbandJob">
														<option value="0">请选择职业</option>
														<c:forEach items="${professions}" var="profession">
															<option value="${profession.id}"
																<c:if test="${profession.id == husbandInfo.professionId}">selected="selected"</c:if>>${profession.profession}</option>
														</c:forEach>
												</select>
												</td>
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
													<label><input style="width:10px;" type="radio" name="husbandResident" value="1" <c:if test="${husbandInfo.resident==1 }">checked</c:if>> 城镇户口</label>
													<label><input style="width:10px;" type="radio" name="husbandResident" value="2" <c:if test="${husbandInfo.resident==2 }">checked</c:if>>农村户口</label>
												</td>
												<td>户籍地址：
												 <select name="husbandDomicoleProvince"
													id="husbandDomicoleProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${husbandInfo.domicoleProvince == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select> <select name="husbandDomicoleCity" id="husbandDomicoleCity">
														<option value="${husbandInfo.domicoleCity}" selected="selected"></option>
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select> 
												</td>

												<td>常驻类型： 
													<label><input style="width:10px;" type="radio" name="husbandResidentType" id="optionsRadios1" value="1" <c:if test="${husbandInfo.residentType==1 }">checked</c:if>>常住</label>
													<label><input style="width:10px;" type="radio" name="husbandResidentType" id="optionsRadios2" value="2" <c:if test="${husbandInfo.residentType==2 }">checked</c:if>> 暂住</label> 
													<label><input style="width:10px;" type="radio" name="husbandResidentType" id="optionsRadios3" value="3" <c:if test="${husbandInfo.residentType==3 }">checked</c:if>> 流动</label>
												</td>
											</tr>

											<tr>
												<td>现住地址：
												 <select name="husbandNowStayProvince"
													id="husbandNowStayProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${husbandInfo.nowStayProvince == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select> <select name="husbandNowStayCity" id="husbandNowStayCity">
														<option value="${husbandInfo.nowStayCity}" selected="selected"></option>
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select> <input type="text" value="${husbandInfo.nowStayAddress }" placeholder="请输入详细地址">
												</td>
												<td>工作单位：<input type="text" value="${husbandInfo.workUnit }">
												</td>
											</tr>

										</tbody>
									</table>
								</div>
							</div>
							</form>
							<div class="weight-title">家庭成员</div>
								<div class="add-peo" id="addFamilyMemberDiv" style="margin-right:40px;float:right;">
									<img src="${pageContext.request.contextPath}/media/image/btn_addnumbers.png" class="add-p">
								</div>
								<form id="updateFamilyMemberForm" method="post">
								<input type="hidden" id="familyArchiveBaseId" value="${archiveBaseId }"/>
								<div class="panel-body">
									<table class="gw_xx" >
										<thead>
											<th>姓名</th>
											<th>性别</th>
											<th>身份证号码</th>
											<th>出生日期</th>
											<th>与户主关系</th>
											<th>是否医保</th>
											<th>联系电话</th>
										</thead>
										<tbody id="familyMemberTab">
												<c:forEach items="${userFamilyMemberList}" var="vo">
												<tr>
													<td><input type="hidden" name="archiveId"  value="${vo.archiveId }"/>
													<input type="hidden" name="id" value="${vo.id }"/>
													<input type="text" value="${vo.personName }" name="personName" /></td>
													<td>
														<div class="basic-block-cen">
															<div class="input-group add-au" id="xixi">
																<select class="form-control selectpicker" name="sex"
																	title="请选择">
																	<option value="-1"
																		<c:if test="${vo.sex==0 }">selected="selected"</c:if>>请选择</option>
																	<option value="0"
																		<c:if test="${vo.sex==0 }">selected="selected"</c:if>>男</option>
																	<option value="1"
																		<c:if test="${vo.sex==1 }">selected="selected"</c:if>>女</option>
																	<option value="2"
																		<c:if test="${vo.sex==2 }">selected="selected"</c:if>>其他</option>
																</select>
															</div>
					
														</div>
													</td>
													<td><input type="text" value="${vo.idNo }" name="idNo" /></td>
													<td><input type="text" class="time-inp"
														onclick="laydate({max:laydate.now()})" readonly="readonly"
														name="birthday" value="${vo.birthday }" placeholder="选择日期"
														id="d1">
													</td>
													<td>
														<div class="basic-block-cen">
															<div class="input-group add-au" class="xixi">
																<select class="form-control selectpicker"
																	name="houseRelation" title="请选择">
																	<option value="0"
																		<c:if test="${vo.houseRelation==1 }">selected="selected"</c:if>>请选择</option>
																	<option value="1"
																		<c:if test="${vo.houseRelation==1 }">selected="selected"</c:if>>本人</option>
																	<option value="2"
																		<c:if test="${vo.houseRelation==2 }">selected="selected"</c:if>>夫妻</option>
																	<option value="3"
																		<c:if test="${vo.houseRelation==3 }">selected="selected"</c:if>>父子</option>
																	<option value="4"
																		<c:if test="${vo.houseRelation==4 }">selected="selected"</c:if>>母子</option>
																	<option value="5"
																		<c:if test="${vo.houseRelation==5 }">selected="selected"</c:if>>其他</option>
																</select>
															</div>
														</div></td>
													<td>
					
														<div class="basic-block-cen">
															<div class="input-group add-au" id="xixi">
																<select class="form-control selectpicker" name="isMedicare"
																	title="请选择">
																	<option value="-1"
																		<c:if test="${vo.isMedicare==1 }">selected="selected"</c:if>>请选择</option>
																	<option value="1"
																		<c:if test="${vo.isMedicare==1 }">selected="selected"</c:if>>是</option>
																	<option value="0"
																		<c:if test="${vo.isMedicare==0 }">selected="selected"</c:if>>否</option>
																</select>
															</div>
														</div>
													</td>
													<td><input type="text" value="${vo.contactWay }"
														name="contactWay" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								</form>
						</div>
						<div class="gw_btn">
							<input class="btn btn-default oneline"
								style="width: 85px; height:40px;padding:0px 0px;" readonly
								value="取消" type="button" data-toggle="modal"
								data-target="#myModal"> <input
								class="btn btn-default oneline"
								style="width: 85px; height:40px;padding:0px 0px; margin-left:30px;"
								readonly value="保存" id="updateArchiveBaseInfoBtn" type="button">
						</div>
					</div>

					<!-- 打印按钮触发 -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-body">


									<div class="alert alert-warning alert-dismissable">
										<button type="button" class="close" data-dismiss="alert"
											aria-hidden="true">&times;</button>
										警告！您正在退出编辑，是否保存？
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal" onclick="javascript:history.go(-1);">取消</button>
									<button type="button" class="btn btn-primary">保存</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal -->
					</div>
					<!-- 模态框结束 -->


				</div>
			</div>
			<script>
		   	$(function(){ 
		   	  $('.panel-body#myTab a').click(function (e) {
			      e.preventDefault();
			      $(this).tab('show');
			    });
		   		$("[data-toggle='tooltip']").tooltip(); 
		   		
		   		
		   		laydate({
			    elem: '#birthday', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			    event: 'focus' ,//响应事件。如果没有传入event，则按照默认的click
				max: laydate.now(),
				today:function(datas){
					calculateAge(1);
			    },
			    choose: function(datas){ //选择日期完毕的回调
			    	calculateAge(1);
			    }
			});
			laydate({
			    elem: '#husbandBirthday', 
			    event: 'focus',
			    max: laydate.now(),
			    today:function(datas){
					calculateAge(2);
			    },
			    choose: function(datas){ //选择日期完毕的回调
			    	calculateAge(2);
			    }
			});
			laydate({
			    elem: '#marryDate', 
			    event: 'focus',
			    max: laydate.now()
			});
			laydate({
			    elem: '#lmp', 
			    event: 'focus',
			    max: laydate.now(),
			    today:function(datas){
			    	getPregnantDateBylmp();
			    },
			    choose: function(datas){ //选择日期完毕的回调
			    	getPregnantDateBylmp();
			    }
			});
			laydate({
			    elem: '#pregnantDate', 
			    event: 'focus'
			});
		   		});
			</script>

			<!-- 引入页尾 -->

</body>