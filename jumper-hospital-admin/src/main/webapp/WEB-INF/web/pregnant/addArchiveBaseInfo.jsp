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
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/addArchiveBaseInfo.js"></script>
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
	width: 40%;
	margin: 30px auto;
}

.modal-header {
	min-height: 16.42857143px;
	padding: 15px;
	border-bottom: 1px solid #fff;
}

input,select {
	height: 25px;
	padding: 0 5px;
}

input[type="radio"],input[type="checkbox"] {
	margin: 0;
}

label {
	margin-right: 10px;
}

.gw_btn {
	width: 280px;
}
</style>

<!--style-->

</head>

<body style="background: #F0F0F0;">

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
						<span class="float-left" style="font-size:16px;">档案管理 >新建档案
							> <a style=" color:#ff7ea5">基本信息</a> </span>
					</div>
					<div class="panel-body">
						<div class="panel panel-default float-left"
							style="margin:15px 0px;width: 100%;">
							<div class=" float-left" style="width:100%;">
								<ul id="myTab" class="nav nav-tabs">
									<li class="active"><a href="#">基本信息</a></li>
									<li><a href="#">病史询问</a></li>
									<li><a href="#">初检情况</a></li>
								</ul>

							</div>
							<form action="${pageContext.request.contextPath}/pregnant/savePregnantArchiveBaseInfo" id="pregnantArchiveBaseForm">
							<input type="hidden" id="hospitalId" value="${hospitalId }"/>
							<input type="hidden" name="archiveId" id="archiveId" value="${archiveId }"/>
							<div id="cont_roll" class="panel panel-default float-left"
								style=" margin:15px;width: 98%;">
								<div class="weight-title">孕妇基本信息</div>
								<div id="jbxx_input" class="panel-body mod_btn">
									<table class="gw_xx">
										<tbody>
											<tr>
												<td><span style="color:red; font-size:18px;">*</span>姓名：<input
													type="text" value="" name="name" id="name"></td>
												<td><span style="color:red; font-size:18px;">*</span>手机电话：<input
													class="" type="tel" value="" data-toggle="modal" name="mobile" id="mobile"
													data-target="#myModal1">
												</td>
												<td><span style="color:red; font-size:18px;">*</span>身份证号：<input style="width:160px;" type="text"
													name="idNo" id="idNo" value=""></td>
											</tr>
											<tr>
												<td><span style="color:red; font-size:18px;">*</span>出生日期：<input
													type="text"  value="" placeholder="日期选择" name="birthday" id="birthday"></td>

												<td>年龄：<input name="age" style="border:none;text-align: right;size:1;width:50px;" id="age" value="0" readonly="readonly"/>岁</td>
												<td>固定电话：<input style="width:50px;" type="text"
													value="" name="areaCode"> - <input type="text" value="" name="phoneNo"></td>
											</tr>
											<tr>
												<td>国籍：<select name="countryId">
														<option value="42">中国</option>
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

												<td>籍贯： <select name="provinceId"
													id="nativePlaceProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${userBookBasicInfo.nowStayProvince == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select> <select name="cityId" id="nativePlaceCity">
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select> <a class="btn" id="nativePlaceBtn">本地区</a>
												</td>

											</tr>
											<tr>
												<td>血型：
												 <label><input style="width:10px;" type="radio" name="bloodType" value="1" checked> A</label>
												 <label><input style="width:10px;" type="radio" name="bloodType" value="2"> B</label>
												 <label><input style="width:10px;" type="radio" name="bloodType"value="3"> O</label>
												 <label><input style="width:10px;" type="radio" name="bloodType"value="4"> AB</label>
												 <label style="margin-left:30px;">RH：<input style="width:10px;"value="5" type="radio" name="bloodType"> 阴性</label>
												</td>
												<td>婚姻状况： <select title="婚姻状况" name="maritalStatus"
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
												<td>结婚日期：<input type="text"  name="marryDate" id="marryDate" value=""></td>

											</tr>
											<tr>
												<td>文化程度： <select title="文化程度" name="educationId">
														<option value="0">请选择文化程度</option>
														<c:forEach items="${educations}" var="education">
															<option value="${education.id}"
																<c:if test="${education.id == userBookBasicInfo.educationId}">selected="selected"</c:if>>${education.name}</option>
														</c:forEach>
												</select>
												</td>
												<td>职业： <select title="职业" name="professionId">
														<option value="0">请选择职业</option>
														<c:forEach items="${professions}" var="profession">
															<option value="${profession.id}"
																<c:if test="${profession.id == userBookBasicInfo.professionId}">selected="selected"</c:if>>${profession.profession}</option>
														</c:forEach>
												</select>
												</td>
												<td>社保卡号：<input type="text" name="socialSecurityCode" value=""></td>

											</tr>
											<tr>
												<td>社保编号：<input type="text" name="socialSecurityNum" value=""></td>

												<td>健康卡号：<input type="text" name="healthCode" value=""></td>
												<td><span style="color:red; font-size:18px;">*</span>末次月经（LMP）：<input type="text" min="1900-01-01" max="${nowDate}" value="" id="lmp"
													placeholder="日期选择" onchange="getPregnantDateBylmp();"></td>

											</tr>
											<tr>
												<td><input type="text"  id="lmp" name="lastPeriod"  placeholder="请选择日期" value=""/></td>
												<td>预产期（EDC)：<input type="text" value="" name="pregnantDate" id="pregnantDate"
													placeholder="日期选择" readonly="readonly"></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="weight-title">孕妇户籍地址信息</div>
								<div class="panel-body mod_btn">
									<table class="gw_xx">
										<tbody>
											<tr>
												<td>户籍类别：
													<label><input style="width:10px;" type="radio" name="resident" value="1" checked> 城镇户口</label>
													<label><input style="width:10px;" type="radio" name="resident" value="2"> 农村户口</label>
												</td>
												<td>户籍地址： <select name="domicoleProvince"
													id="domicoleProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${userBookBasicInfo.nowStayProvince == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select> <select name="domicoleCity" id="domicoleCity">
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select> <a class="btn" id="domicoleProvinceBtn">同籍贯地址</a>
												</td>

											</tr>
											<tr>
												<td>常驻类型： 
												 <label><input style="width:10px;" type="radio" name="residentType" value="1" checked>常住</label>
												 <label><input style="width:10px;" type="radio" name="residentType" value="2">暂住</label> 
												 <label><input style="width:10px;" type="radio" name="residentType" value="3"> 流动</label>
												</td>
												<td><span style="color:red; font-size:18px;">*</span>现住地址：
													<select name="nowStayProvince" id="nowStayProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${userBookBasicInfo.nowStayProvince == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select> <select name="nowStayCity" id="nowStayCity">
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select> <input type="text" value="" placeholder="请输入详细地址">
													<a class="btn" id="nowStayProvinceBtn">本地区</a>
												</td>

											</tr>
											<tr>
												<td>现住址管辖派出所：
												 <select name="nowPoliceStationProvince" id="nowPoliceStationProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${userBookBasicInfo.nowStayProvince == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												 </select>
												 <select name="nowPoliceStationCity" id="nowPoliceStationCity">
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select> 派出所
												<input type="hidden" name="nowPoliceStationAddress" id="nowPoliceStationAddress" value="${userBookBaseInfo.nowPoliceStationAddress }"/>
												</td>
												<td>工作单位：<input type="text" name="workUnit" value="${userBookBaseInfo.workUnit }" style="width:60%">
												</td>
											</tr>
										</tbody>
									</table>
								</div>

								<div class="weight-title">丈夫基本信息</div>
								<div class="panel-body mod_btn">
									<table class="gw_xx">
										<tbody>
											<tr>
												<td>姓名：<input type="text" name="husbandName" value=""></td>
												<td>身份证号：<input type="text" name="husbandIdNo" value=""></td>
												<td>出生日期：<input type="text" name="husbandBirthday"   id="husbandBirthday" value="" placeholder="日期选择" ></td>
											</tr>
											<tr>
												<td>年龄：<input type="text" style="border:none;text-align: right;width:50px;" name="husbandAge" id="husbandAge" value="0" readonly="readonly"/>岁</td>

												<td>联系电话：<input type="text" name="husbandMobile" value=""></td>
												<td>国籍：<select name="husbandCountryId">
														<option value="42">中国</option>
												</select></td>
											</tr>
											<tr>
												<td>籍贯： <select name="husbandProvinceId"
													id="husbandProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${userBookBasicInfo.nowStayProvince == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select> <select name="husbandCityId" id="husbandCity">
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select> <a class="btn" id="husbandProvinceBtn">本地区</a>
												</td>
												<td>民族： <select title="民族" name="husbandNationId">
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

												<td>文化程度： <select title="文化程度" name="husbandEducationId">
														<option value="0">请选择文化程度</option>
														<c:forEach items="${educations}" var="education">
															<option value="${education.id}"
																<c:if test="${education.id == userBookBasicInfo.educationId}">selected="selected"</c:if>>${education.name}</option>
														</c:forEach>
												</select>
												</td>
											</tr>
											<tr>
												<td>职业： <select title="职业" name="husbandJob">
														<option value="0">请选择职业</option>
														<c:forEach items="${professions}" var="profession">
															<option value="${profession.id}"
																<c:if test="${profession.id == userBookBasicInfo.professionId}">selected="selected"</c:if>>${profession.profession}</option>
														</c:forEach>
												</select>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="weight-title">丈夫户籍地址信息</div>
								<div class="panel-body mod_btn">
									<table class="gw_xx">
										<tbody>
											<tr>
												<td>户籍类别： 
													<label><input style="width:10px;" type="radio" name="husbandResident" value="1" checked> 城镇户口</label>
													<label><input style="width:10px;" type="radio" name="husbandResident" value="2">农村户口</label>
												</td>
												<td>户籍地址： <select name="husbandDomicoleProvince"
													id="husbandDomicoleProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${userBookBasicInfo.nowStayProvince == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select> <select name="husbandDomicoleCity" id="husbandDomicoleCity">
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select> <a class="btn" id="husbandDomicoleBtn">同籍贯地址</a>
												</td>
											</tr>
											<tr>
												<td>常驻类型： 
												<label><input style="width:10px;" type="radio" name="husbandResidentType" id="optionsRadios1" value="1" checked>常住</label>
												<label><input style="width:10px;" type="radio" name="husbandResidentType" id="optionsRadios2" value="2"> 暂住</label> 
												<label><input style="width:10px;" type="radio" name="husbandResidentType" id="optionsRadios3" value="3"> 流动</label>
												</td>

												<td>现住地址： <select name="husbandNowStayProvince"
													id="husbandNowStayProvince">
														<option value="0">请选择省份</option>
														<c:forEach items="${provinces}" var="province">
															<option value="${province.id}"
																<c:if test="${userBookBasicInfo.nowStayProvince == province.id}">selected="selected"</c:if>>${province.proName}</option>
														</c:forEach>
												</select> <select name="husbandNowStayCity" id="husbandNowStayCity">
														<option value="${nowStayCity.id}">${nowStayCity.cityName}</option>
												</select> <input type="text" value="" placeholder="请输入详细地址">
													<a class="btn" id="husbandNowStayBtn">同孕妇地址</a>
												</td>
											</tr>
											<tr>
												<td>工作单位：<input type="text" name="husbandWorkUnit" value="" style="width:60%">
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							</form>
						</div>
						<div class="gw_btn">
							<input class="btn btn-default oneline" style="width: 85px; height:40px;padding:0px 0px;" readonly value="下一步" type="button" data-toggle="modal"
								id="submitButton" data-target="#myModal" />
						</div>
					</div>
				</div>
			</div>
	
	<script type="text/javascript">
		$(function(){
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
</body>