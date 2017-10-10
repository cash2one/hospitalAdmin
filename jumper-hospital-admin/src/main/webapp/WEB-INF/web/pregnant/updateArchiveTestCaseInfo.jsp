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
	src="${pageContext.request.contextPath}/media/js/laydate-v1.1/laydate/laydate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/media/js/pregnantArchive/updateArchiveTestCase.js"></script>
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
label {
    margin-right: 10px;
}
input, select {
    height: 25px;
    padding: 0 5px;
}
.modal-body table th{text-align:center;}
.modal-body table td{text-align:center;}
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
						<span class="float-left" style="font-size:16px;">档案管理 >档案详情
							> <a style=" color:#ff7ea5">初检情况-修改</a>
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
									<%-- <li><a href="${pageContext.request.contextPath}/pregnant/showArchiveBaseInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" >基本信息</a></li>
   							<li><a href="${pageContext.request.contextPath}/pregnant/showArchiveHistoryTakingInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}">病史询问</a></li> --%>
									<li class="active"><a>初检情况</a>
									</li>
									<%--  <li><a>高危筛查</a></li>
                            <li><a href="${pageContext.request.contextPath}/monitor/weight" >监测数据</a></li>
                            <li><a>分娩结局</a></li>
                            <li ><a>产后访视</a></li>
                            <li><a href="${pageContext.request.contextPath}/neonatal/lookIndex?userId=230" >新生儿访视</a></li> --%>
								</ul>

							</div>
			 				<input type="hidden" id="archiveId" name="id" value="${archiveId }"/>
							<input type="hidden" id="archiveBaseId" value="${archiveBaseId }"/>
							<input type="hidden" id="willCheckProjectData" value="${userArchiveTestCase.willCheckProject }"/>
							<input type="hidden" id="referenceProjectData" value="${userArchiveTestCase.referenceProject }"/>
							<form action="${pageContext.request.contextPath}/pregnant/updateArchiveTestCaseInfo" method="post" id="updateArchiveTestCaseForm">
							<input type="hidden" name="id" value="${userArchiveTestCase.id }"/>
							<input type="hidden" name="archiveBaseId" value="${archiveBaseId }"/>
							<input type="hidden" name="archivesId" value="${archiveId }"/>
								<div id="cont_roll" class="panel panel-default float-left"
									style=" margin:15px;width: 98%;">
									<div class="weight-title">初次检查</div>

									<div class="panel-body">
										<div id="live_inf" style="margin-top:25px;">
											<span>神志</span>
											<div class="live_inftable">
												<table class="gw_xx">
													<tbody>
														<tr>
															<td>身高：<input style="width:80px;"
																value="${userArchiveTestCase.height }" name="height"
																type="text"> cm</td>
															<td>体重：<input style="width:80px;"
																value="${userArchiveTestCase.weight }" name="weight"
																type="text"> kg</td>
															<td>BMI：<input type="text" name="bmi"
																value="${userArchiveTestCase.bmi }" />
															</td>
															<td>血压：<input style="width:80px;" name="dbp"
																value="${userArchiveTestCase.dbp }" type="text"
																placeholder="输入舒张值"> - <input
																style="width:80px;" name="sbp"
																value="${userArchiveTestCase.sbp }" type="text"
																placeholder="输入收缩值">mmHg</td>
															<td>浮肿： <label><input style="width:10px;"
																	type="radio" name="edemaState" value="0"
																	<c:if test="${userArchiveTestCase.edemaState==0 }">checked</c:if>>无</label>
																<label><input style="width:10px;" type="radio"
																	name="edemaState" value="1"
																	<c:if test="${userArchiveTestCase.edemaState==1 }">checked</c:if>>
																	轻</label> <label><input style="width:10px;"
																	type="radio" name="edemaState" value="2"
																	<c:if test="${userArchiveTestCase.edemaState==2 }">checked</c:if>>
																	中</label> <label><input style="width:10px;"
																	type="radio" name="edemaState" value="3"
																	<c:if test="${userArchiveTestCase.edemaState==3 }">checked</c:if>>
																	重</label></td>
														</tr>

													</tbody>
												</table>
											</div>
										</div>
									</div>


									<div class="panel-body">
										<div id="live_inf">
											<span>体格检查</span>
											<div class="live_inftable">
												<table class="gw_xx">
													<tbody>
														<tr>
															<td>心肺： <label><input style="width:10px;"
																	type="radio" name="heartLungState" value="0"
																	<c:if test="${userArchiveTestCase.heartLungState==0 }">checked</c:if>>正常</label>
																<label><input style="width:10px;" type="radio"
																	name="heartLungState" value="1"
																	<c:if test="${userArchiveTestCase.heartLungState==1 }">checked</c:if>>异常</label>
																<input type="text"
																value="${userArchiveTestCase.heartLungDescription }"
																name="heartLungDescription" placeholder="请输入异常内容">
															</td>
															<td>肝脾： <label><input style="width:10px;"
																	type="radio" name="liverSpleenState" value="0"
																	<c:if test="${userArchiveTestCase.liverSpleenState==0 }">checked</c:if>>正常</label>
																<label><input style="width:10px;" type="radio"
																	name="liverSpleenState" value="1"
																	<c:if test="${userArchiveTestCase.liverSpleenState==1 }">checked</c:if>>
																	异常</label> <input type="text"
																value="${userArchiveTestCase.liverSpleenDescription }"
																name="liverSpleenDescription" placeholder="请输入异常内容">

															</td>
															<td>甲状腺： <label><input style="width:10px;"
																	type="radio" name="thyroidState" value="0"
																	<c:if test="${userArchiveTestCase.thyroidState==0 }">checked</c:if>>正常</label>
																<label><input style="width:10px;" type="radio"
																	name="thyroidState" value="1"
																	<c:if test="${userArchiveTestCase.thyroidState==1 }">checked</c:if>>
																	异常</label> <input type="text"
																value="${userArchiveTestCase.thyroidDescription }"
																name="thyroidDescription" placeholder="请输入异常内容">

															</td>


														</tr>
														<tr>
															<td>四肢： <label><input style="width:10px;"
																	type="radio" name="fourLimbsState" value="0"
																	<c:if test="${userArchiveTestCase.fourLimbsState==0 }">checked</c:if>>正常</label>
																<label><input style="width:10px;" type="radio"
																	name="fourLimbsState" value="1"
																	<c:if test="${userArchiveTestCase.fourLimbsState==1 }">checked</c:if>>异常</label>
																<input type="text"
																value="${userArchiveTestCase.fourLimbsDescription }"
																placeholder="请输入异常内容"></td>
															<td>皮肤： <label><input style="width:10px;"
																	type="radio" name="skinState" value="0"
																	<c:if test="${userArchiveTestCase.skinState==0 }">checked</c:if>>正常</label>
																<label><input style="width:10px;" type="radio"
																	name="skinState" value="1"
																	<c:if test="${userArchiveTestCase.skinState==1 }">checked</c:if>>
																	异常</label> <input type="text"
																value="${userArchiveTestCase.skinDescription }"
																name="skinDescription" placeholder="请输入异常内容"></td>
															<td>乳房： <label><input style="width:10px;"
																	type="radio" name="breastState" value="0"
																	<c:if test="${userArchiveTestCase.breastState==0 }">checked</c:if>>正常</label>
																<label><input style="width:10px;" type="radio"
																	name="breastState" value="1"
																	<c:if test="${userArchiveTestCase.breastState==1 }">checked</c:if>>
																	乳头凹陷</label> <label><input style="width:10px;"
																	type="radio" name="breastState" value="2"
																	<c:if test="${userArchiveTestCase.breastState==2 }">checked</c:if>>乳头扁平</label>
																<label><input style="width:10px;" type="radio"
																	name="breastState" value="3"
																	<c:if test="${userArchiveTestCase.breastState==3 }">checked</c:if>>
																	乳房包块</label> <input type="text"
																value="${userArchiveTestCase.breastDescription }"
																name="breastDescription" placeholder="请输入其他情况">

															</td>
														</tr>
														<tr>
															<td>步态： <label><input style="width:10px;"
																	type="radio" name="gaitState" value="0"
																	<c:if test="${userArchiveTestCase.gaitState==0 }">checked</c:if>>正常</label>
																<label><input style="width:10px;" type="radio"
																	name="gaitState" value="1"
																	<c:if test="${userArchiveTestCase.gaitState==1 }">checked</c:if>>
																	异常</label> <input type="text"
																value="${userArchiveTestCase.gaitDescription }"
																name="gaitDescription" placeholder="请输入异常内容"></td>
														</tr>


													</tbody>
												</table>
											</div>
										</div>
									</div>


									<div class="panel-body">
										<div id="live_inf">
											<span>妇科检查</span>
											<div class="live_inftable">
												<table class="gw_xx">
													<tbody>
														<tr>
															<td>外阴：<input value="${userArchiveTestCase.vulva }"
																name="vulva" type="text">
															</td>
															<td>阴道：<input value="${userArchiveTestCase.vagina }"
																name="vagina" type="text">
															</td>
															<td>宫颈：<input value="${userArchiveTestCase.cervix }"
																name="cervix" type="text">
															</td>
															<td>内诊情况：<input
																value="${userArchiveTestCase.clinicalSituation }"
																name="clinicalSituation" type="text">
															</td>
															<td>宫体：<input value="${userArchiveTestCase.corpus }"
																name="corpus" type="text">
															</td>
														</tr>
														<tr>

															<td>宫高：<input style="width:100px;"
																value="${userArchiveTestCase.fundalHeight }"
																name="fundalHeight" type="text"> cm</td>
															<td>附件：<input
																value="${userArchiveTestCase.adjunct }" name="adjunct"
																type="text">
															</td>
															<td>腹围：<input style="width:100px;"
																value="${userArchiveTestCase.abdominalGirth }"
																name="abdominalGirth" type="text"> cm</td>
															<td>胎心：<input
																value="${userArchiveTestCase.fetalHeart }"
																name="fetalHeart" type="text">
															</td>
															<td>先露：<input
																value="${userArchiveTestCase.presentation }"
																name="presentation" type="text">
															</td>
														</tr>
														<tr>

															<td>衔接：<input
																value="${userArchiveTestCase.engagement }"
																name="engagement" type="text">
															</td>
															<td>水肿：<input value="${userArchiveTestCase.edema }"
																name="edema" type="text">
															</td>
															<td>其他：<input value="${userArchiveTestCase.other }"
																name="other" type="text" placeholder="">
															</td>
														</tr>

													</tbody>
												</table>
											</div>
										</div>
									</div>



									<div class="weight-title">骨盆外测量</div>
									<div class="panel-body">
										<table class="gw_xx">
											<tbody>
												<tr>
													<td>髂前上棘间径：<input style="width:100px;"
														value="${userArchiveTestCase.diameterSpineSkeletons }"
														name="diameterSpineSkeletons" type="text"> cm</td>
													<td>髂嵴间径：<input style="width:100px;"
														value="${userArchiveTestCase.skeletonsRidgeDiameter }"
														name="skeletonsRidgeDiameter" type="text"> cm</td>
													<td>骶耻外径：<input style="width:100px;"
														value="${userArchiveTestCase.externalConjugate }"
														name="externalConjugate" type="text"> cm</td>
													<td>坐骨结节间径：<input style="width:100px;"
														value="${userArchiveTestCase.ischialIntertuberalDiameter }"
														name="ischialIntertuberalDiameter" type="text"> cm</td>
												</tr>
												<tr>

													<td>耻骨弓角度：<input style="width:100px;"
														value="${userArchiveTestCase.anglePubicArch }"
														name="anglePubicArch" type="text"> 度</td>
												</tr>
											</tbody>
										</table>
									</div>


									<div class="weight-title">实验室检查</div>
									<div class="panel-body mod_btn">

										<div id="live_inf" style="margin-top:25px;">
											<span>必查项目</span>
											<div class="live_inftable">
												<table class="gw_xx cha_lab">
													<tbody id="willCheckProjectTable">
														<tr>
															<td><label><input style="width:10px;"
																	type="checkbox" value="1"> 血常规</label> <label><input
																	style="width:10px;" type="checkbox" value="2">
																	尿常规</label> <label><input style="width:10px;"
																	type="checkbox" value="3"> 血型</label> <label><input
																	style="width:10px;" type="checkbox" value="4">
																	肝功能</label> <label><input style="width:10px;"
																	type="checkbox" value="5"> 肾功能</label> <label><input
																	style="width:10px;" type="checkbox" value="6">
																	乙肝</label> <label><input style="width:10px;"
																	type="checkbox" value="7"> 梅毒</label> <label><input
																	style="width:10px;" type="checkbox" value="8">
																	HIV</label> <label><input style="width:10px;"
																	type="checkbox" value="9"> 阴道分泌物</label></td>
														</tr>


													</tbody>
												</table>
											</div>
										</div>
 											<input type="hidden" name="willCheckProject" id="willCheckProject"/>
										<div id="live_inf">
											<span>备查项目</span>
											<div class="live_inftable">

												<table class="gw_xx cha_lab">
													<tbody id="referenceProjectTable">
														<tr>
															<td><label><input style="width:10px;"
																	type="checkbox" value="1"> 丙肝</label> <label><input
																	style="width:10px;" type="checkbox" value="2">
																	OGTT</label> <label><input style="width:10px;"
																	type="checkbox" value="3"> 空腹血糖</label> <label><input
																	style="width:10px;" type="checkbox" value="4">
																	甲状腺功能</label> <label><input style="width:10px;"
																	type="checkbox" value="5"> TORCH</label> <label><input
																	style="width:10px;" type="checkbox" value="6">
																	血脂</label> <label><input style="width:10px;"
																	type="checkbox" value="7"> 血清铁蛋白</label> <label><input
																	style="width:10px;" type="checkbox" value="8">
																	HCG</label> <label><input style="width:10px;"
																	type="checkbox" value="9"> 孕酮</label> <label><input
																	style="width:10px;" type="checkbox" value="10">
																	结核菌素试验</label> <label><input style="width:10px;"
																	type="checkbox" value="11"> 抗D滴度检查（Rh阴性）</label> <label><input
																	style="width:10px;" type="checkbox" value="12">
																	宫颈细胞学</label> <label><input style="width:10px;"
																	type="checkbox" value="13"> 宫颈分泌物（淋球菌和沙眼衣原体）</label> <label><input
																	style="width:10px;" type="checkbox" value="14">
																	BV</label></td>
														</tr>


													</tbody>
												</table>
											</div>
										</div>
											<input type="hidden" name="referenceProject" id="referenceProject"/>
										<div class="weight-title">孕前检查结果</div>
										<div class="panel-body mod_btn">
											<div style="margin-bottom:25px;">
												<table class="gw_xx">
													<tbody>
														<tr>
															<td colspan="2">血常规： Hb <input style="width:50px;"
																value="${userArchiveTestCasePregnancy.hb }" name="hb"
																type="text"> g/l <span class="kong">MCV <input
																	style="width:50px;"
																	value="${userArchiveTestCasePregnancy.mcv }" name="mcv"
																	type="text">
															</span> <span class="kong">MCH <input style="width:50px;"
																	value="${userArchiveTestCasePregnancy.mch }" name="mch"
																	type="text">
															</span> <span class="kong">PLT <input style="width:50px;"
																	value="${userArchiveTestCasePregnancy.plt }" name="plt"
																	type="text"> ×10</span> <span class="kong">其他 <input
																	value="${userArchiveTestCasePregnancy.other }"
																	name="pregant-other" type="text">
															</span></td>
															<td>尿常规： 白细胞 <input style="width:50px;"
																value="${userArchiveTestCasePregnancy.whiteBlood }"
																name="whiteBlood" type="text"> 个/u <span
																class="kong">红细胞 <input style="width:50px;"
																	value="${userArchiveTestCasePregnancy.redBlood }"
																	name="redBlood" type="text"> 个/ul</span></td>
														</tr>

														<tr>
															<td>血蛋白： <label><input style="width:10px;"
																	value="" name="protein" type="radio"
																	<c:if test="${userArchiveTestCasePregnancy.protein==0 }">checked</c:if>>
																	阴性</label> <label><input style="width:10px;" value=""
																	name="protein" type="radio"
																	<c:if test="${userArchiveTestCasePregnancy.protein==1 }">checked</c:if>>
																	阳性</label></td>
															<td>尿比重：<input style="width:50px;"
																value="${userArchiveTestCasePregnancy.sg }" name="sg"
																type="text">
															</td>
															<td>孕妇血型： <label><input style="width:10px;"
																	value="" name="pregnantWomenBloodType" type="radio"
																	value="0"
																	<c:if test="${userArchiveTestCasePregnancy.pregnantWomenBloodType==0 }">checked</c:if>>
																	A</label> <label><input style="width:10px;" value=""
																	name="pregnantWomenBloodType" type="radio" value="1"
																	<c:if test="${userArchiveTestCasePregnancy.pregnantWomenBloodType==1 }">checked</c:if>>
																	B</label> <label><input style="width:10px;" value=""
																	name="pregnantWomenBloodType" type="radio" value="2"
																	<c:if test="${userArchiveTestCasePregnancy.pregnantWomenBloodType==2 }">checked</c:if>>
																	O</label> <label><input style="width:10px;" value=""
																	name="pregnantWomenBloodType" type="radio" value="3"
																	<c:if test="${userArchiveTestCasePregnancy.pregnantWomenBloodType==3 }">checked</c:if>>
																	AB</label> <span class="kong">RH：</span></td>
															<td>丈夫血型： <label><input style="width:10px;"
																	value="" name="husbardBloodType" type="radio" value="0"
																	<c:if test="${userArchiveTestCasePregnancy.husbardBloodType==0 }">checked</c:if>>
																	A</label> <label><input style="width:10px;" value=""
																	name="husbardBloodType" type="radio" value="1"
																	<c:if test="${userArchiveTestCasePregnancy.husbardBloodType==1 }">checked</c:if>>
																	B</label> <label><input style="width:10px;" value=""
																	name="husbardBloodType" type="radio" value="2"
																	<c:if test="${userArchiveTestCasePregnancy.husbardBloodType==2 }">checked</c:if>>
																	O</label> <label><input style="width:10px;" value=""
																	name="husbardBloodType" type="radio" value="3"
																	<c:if test="${userArchiveTestCasePregnancy.husbardBloodType==3 }">checked</c:if>>
																	AB</label> <span class="kong">RH：</span></td>
														</tr>

														<tr>
															<td>肝肾功能：<input type="text"
																value="${userArchiveTestCasePregnancy.hepatorenalFunction }"
																name="hepatorenalFunction" />
															</td>
															<td>围产四项：<input type="text"
																value="${userArchiveTestCasePregnancy.perinatalFourItems }"
																name="perinatalFourItems" />
															</td>
															<td>乙肝三对：<input type="text"
																value="${userArchiveTestCasePregnancy.hepatitisThreePair }"
																name="hepatitisThreePair" />
															</td>
															<td>丙肝、病毒、HIV：<input type="text"
																value="${userArchiveTestCasePregnancy.hiv }" name="hiv" />
															</td>
														</tr>

													</tbody>
												</table>
											</div>

											<div id="live_inf">
												<span>甲状腺功能</span>
												<div class="live_inftable">

													<table class="gw_xx">
														<tbody>
															<tr style="height:45px;">
																<td>促甲状腺激素TSH：<input type="text"
																	value="${userArchiveTestCasePregnancy.tsh }" name="tsh" />
																	mIU/L</td>
																<td>游离三碘甲状腺原氨酸FT3：<input type="text"
																	value="${userArchiveTestCasePregnancy.fts }" name="fts">
																	pmol/L</td>
																<td>血清游离甲状腺激素FT4：<input type="text"
																	value="${userArchiveTestCasePregnancy.ftf }" name="ftf">
																	nmol/L</td>

															</tr>
														</tbody>
													</table>
												</div>
											</div>

											<table class="gw_xx">
												<tr>
													<td>地贫筛查：<input style="width:60%" type="text"
														value="${userArchiveTestCasePregnancy.poorScreening }"
														name="poorScreening" />
													</td>
												</tr>
												<tr>
													<td>支原体、衣原体、淋球菌：<input style="width:60%" type="text"
														value="${userArchiveTestCasePregnancy.mcg }" name="mcg" />
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="weight-title">孕期补充检查结果</div>
									<div class="panel-body mod_btn">
										<table class="gw_xx ">
											<tbody>
												<tr>
													<td width="50%">早期四维彩超（时间、头臀长、NT）：<input
														style="width:55%" type="text"
														value="${userArchiveTestCasePregnancy.earlyFourDimensional }"
														name="earlyFourDimensional" />
													</td>
													<td width="50%">唐氏筛查情况：早期筛查 <input type="text"
														value="${userArchiveTestCasePregnancy.earlyScreening }"
														name="earlyScreening" /> <span class="kong"> 中期筛查 <input
															type="text"
															value="${userArchiveTestCasePregnancy.midScreening }"
															name="midScreening" />
													</span></td>
												</tr>
												<tr>
													<td width="50%">无创DNA或羊水穿刺情况：<input style="width:55%"
														type="text"
														value="${userArchiveTestCasePregnancy.noninvasiveDna }"
														name="noninvasiveDna" />
													</td>
													<td width="50%">中晚期四维彩超情况（孕周及异常情况）：<input
														style="width:55%" type="text"
														value="${userArchiveTestCasePregnancy.midLateFourDimensional }"
														name="midLateFourDimensional" />
													</td>

												</tr>

											</tbody>
										</table>

									</div>


									<div class="weight-title">诊断</div>
									<div class="gw_jy">
										<div class="cbzd">
											<p class="float-left">诊断内容：</p>
											<input type="hidden" id="testCaseDiagnoseIdData" value="${userArchiveTestCaseDiagnose.id }"/>
											<input type="hidden" id="diagnoseModelContentData" value="${userArchiveTestCaseDiagnose.diagnoseModelContent }"/>
											<select class="float-right" style=" height:30px;"
												name="testCaseDiagnoseId" id="testCaseDiagnoseId">

											</select> <a class="btn btn-default float-right " href="#"
												style="height:30px; width:90px;margin-right:10px;"
												data-toggle="modal" data-target="#myModal1" id="manageModel">管理模板</a>
										</div>
										<p>
											<textarea class="form-control" rows="10" maxlength="1000"
												placeholder="不超过1000字" id="diagnoseModelContent"></textarea>
										</p>

									</div>

									<div class="panel-body">
										<table class="gw_xx">
											<tbody>
												<tr>
													<td><span style="color:red; font-size:18px;">*</span>检查医生：
														<input style="width:80px" type="text" value="">
													</td>
													<td>登记人员：XXX</td>
													<td>检查医院：广东省XXXXXXXXXXX医院</td>
													<td>检查日期： <input
														style="width: 100px;height:28px; display:inline-block"
														type="text" name="startTime" id="startTime"
														placeholder="开始日期" class="form-control" value="">
													</td>
													<td>初检孕周： 孕 3 周 2 天</td>
												</tr>
											</tbody>
										</table>
									</div>


								</div>
							</form>

						</div>


						<div class="gw_btn">
							<input class="btn btn-default oneline"
								style="width: 85px; height:40px;padding:0px 0px;" readonly
								value="取消" type="button"> <input class="btn btn-pink"
								style="width: 85px; height:40px;padding:0px 0px; margin-left:20px"
								readonly value="保存" type="button" id="updateArchiveTestCaseBtn">
						</div>
					</div>



					<!-- 弹出框 -->
					<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header" style="text-align:center">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">诊断</h4>
								</div>
								<div class="modal-body">

									<table
										style="border-collapse:collapse; border:0px solid #D0D0D0;" class="bordered">
										
										<th height="25px">序号</th>
										<th height="25px">名称</th>
										<th height="25px">内容</th>
										<th height="25px">操作</th>
										
										<tbody id="testCaseDiagnoseModel">

										</tbody>
									</table>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary"
										data-toggle="modal" data-target="#myModal2"
										id="addTestCaseDiagnoseBtn">新增模板</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal -->
					</div>
					<!-- 模态框结束 -->
					<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header" style="text-align:center">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">新增模板</h4>
								</div>
								<div class="modal-body">
									<input type="hidden" id="operationType" value="insert" /> <input
										type="hidden" id="updateTestCaseId" value="" />
									<div style="padding:0 15px; margin-bottom:15px;">
										<span>名称：</span><input type="text" value=""
											name="diagnoseModelName" id="diagnoseModelName"
											style=" width:95%;padding:0 10px;">
									</div>
									<div style="padding:0 15px;">
										<span style="vertical-align:top;">内容：</span>
										<textarea style=" width:95%;padding:0 10px;"
											name="diagnoseModelContent" id="diagnoseModelContentNew"></textarea>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary"
										id="saveTestCaseDiagnoseBtn">保存</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal -->
					</div>
				</div>

			</div>
			<script type="text/javascript">
		$(function(){
			laydate({
			    elem: '#startTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			    event: 'focus' ,//响应事件。如果没有传入event，则按照默认的click
				max: laydate.now()
			});
		});
	</script>

			<!-- 引入页尾 -->

</body>
