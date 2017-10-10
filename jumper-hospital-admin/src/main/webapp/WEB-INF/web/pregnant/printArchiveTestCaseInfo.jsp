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
			$("#printBaseInfoOut").bind("click",function(){
				$(".top-la").hide(); 
				window.print();  
			});
			$("#backBaseInfo").bind("click",function(){
				history.go(-1);  
			});
});
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/media/js/pregnantArchive/jquery.tips.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/showArchiveTestCase.js"></script>
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
			<h2>初检情况</h2>
		</div>

		<div class="panel-body dayin" style="padding:15px;">
			<table>
				<tbody>
					<tr>
						<td width="20%">姓名： ${userBookBasicInfo.name }</td>
						<td width="20%">年龄：${userBookBasicInfo.age }岁</td>
						<td width="25%">联系电话：${userBookBasicInfo.mobile }</td>
						<td width="35%">身份证号：${userBookBasicInfo.idNo }</td>

					</tr>
				</tbody>
			</table>
		</div>

		<div id="cont_roll">
			<div class="weight-title">初次检查</div>
			  <input type="hidden" id="willCheckProject" value="${userArchiveTestCase.willCheckProject }"/>
                    <input type="hidden" id="referenceProject" value="${userArchiveTestCase.referenceProject }"/>
			<div class="panel-body">
				<div id="live_inf" style="margin-top:25px;">
					<span>神志</span>
					<div class="live_inftable">
						<table class="gw_xx">
							<tbody>
								<tr>
									<td width="20%">身高：${userArchiveTestCase.height } cm</td>
									<td width="20%">体重：${userArchiveTestCase.weight } kg</td>
									<td width="20%">BMI：${userArchiveTestCase.bmi }</td>
									<td width="20%">血压：${userArchiveTestCase.dbp } -
										${userArchiveTestCase.sbp } mmHg</td>
									<td>浮肿： <c:if
											test="${userArchiveTestCase.edemaState==0 }">无</c:if> <c:if
											test="${userArchiveTestCase.edemaState==1 }">轻</c:if> <c:if
											test="${userArchiveTestCase.edemaState==2 }">中</c:if> <c:if
											test="${userArchiveTestCase.edemaState==3 }">重</c:if></td>
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
									<td width="20%">心肺： 
										<c:if test="${userArchiveTestCase.heartLungState==0 }">正常</c:if>
                                        <c:if test="${userArchiveTestCase.heartLungState==1 }">异常</c:if>${userArchiveTestCase.heartLungDescription }
									</td>
									<td width="20%">肝脾： 
										<c:if test="${userArchiveTestCase.liverSpleenState==0 }">正常</c:if>
                                        <c:if test="${userArchiveTestCase.liverSpleenState==1 }">异常</c:if>${userArchiveTestCase.liverSpleenDescription }
									</td>
									<td width="20%">甲状腺：
										<c:if test="${userArchiveTestCase.thyroidState==0 }">正常</c:if>
                                        <c:if test="${userArchiveTestCase.thyroidState==1 }">异常</c:if>${userArchiveTestCase.thyroidDescription }
									</td>
									<td width="20%">四肢： 
										<c:if test="${userArchiveTestCase.fourLimbsState==0 }">正常</c:if>
                                        <c:if test="${userArchiveTestCase.fourLimbsState==1 }">异常</c:if>${userArchiveTestCase.fourLimbsDescription }
									</td>
									<td width="20%">皮肤： 
										<c:if test="${userArchiveTestCase.skinState==0 }">正常</c:if>
                                        <c:if test="${userArchiveTestCase.skinState==1 }">异常</c:if>${userArchiveTestCase.skinDescription }
									</td>
								</tr>
								<tr>
									<td>乳房：
										<c:if test="${userArchiveTestCase.breastState==0 }">正常</c:if>
                                   		<c:if test="${userArchiveTestCase.breastState==1 }">乳头凹陷</c:if>
                                   		<c:if test="${userArchiveTestCase.breastState==2 }">乳头扁平</c:if>
                                   		<c:if test="${userArchiveTestCase.breastState==3 }">乳头包块</c:if>
									</td>
									<td>步态： 
										<c:if test="${userArchiveTestCase.gaitState==0 }">正常</c:if>
                                        <c:if test="${userArchiveTestCase.gaitState==1}">异常</c:if>${userArchiveTestCase.gaitDescription }
									</td>
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
									<td width="20%">外阴：${userArchiveTestCase.vulva }</td>
									<td width="20%">阴道：${userArchiveTestCase.vagina }</td>
									<td width="20%">宫颈：${userArchiveTestCase.cervix }</td>
									<td width="20%">内诊情况：${userArchiveTestCase.clinicalSituation }</td>
									<td width="20%">宫体：${userArchiveTestCase.corpus }</td>
								</tr>
								<tr>
									<td>宫高：${userArchiveTestCase.fundalHeight }  cm</td>
									<td>附件：${userArchiveTestCase.adjunct }</td>
									<td>腹围：${userArchiveTestCase.abdominalGirth }  cm</td>
									<td>胎心：${userArchiveTestCase.fetalHeart }</td>
									<td>先露：${userArchiveTestCase.presentation }</td>
								</tr>
								<tr>
									<td>衔接：${userArchiveTestCase.engagement }</td>
									<td>水肿：${userArchiveTestCase.edema }</td>
									<td>其他：${userArchiveTestCase.other }</td>
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
							<td width="20%">髂前上棘间径：${userArchiveTestCase.diameterSpineSkeletons } cm</td>
							<td width="20%">髂嵴间径：${userArchiveTestCase.skeletonsRidgeDiameter }  cm</td>
							<td width="20%">骶耻外径：${userArchiveTestCase.externalConjugate } cm</td>
							<td width="20%">坐骨结节间径：${userArchiveTestCase.ischialIntertuberalDiameter } cm</td>
							<td width="20%">耻骨弓角度：${userArchiveTestCase.anglePubicArch } 度</td>
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
                                                 <td>
                                                 
                                                 </td>
                                            </tr>
                                         </tbody>
						</table>
					</div>
				</div>

				<div id="live_inf">
					<span>备查项目</span>
					<div class="live_inftable">

						<table class="gw_xx cha_lab">
							 <tbody id="referenceProjectTable">
                                             <tr>
                                                 <td>
                                                 
                                                 </td>
                                            </tr>
                                         </tbody>
						</table>
					</div>
				</div>

				<div class="weight-title">孕前检查结果</div>
				<div class="panel-body mod_btn">
					<div style="margin-bottom:25px;">
						<table class="gw_xx">
							<tbody>
								<tr>
									<td colspan="2">血常规： Hb  ${userArchiveTestCasePregnancy.hb }  g/l 
									<span class="kong">MCV ${userArchiveTestCasePregnancy.mcv }</span> 
									<span class="kong">MCH ${userArchiveTestCasePregnancy.mch }</span> 
									<span class="kong">PLT${userArchiveTestCasePregnancy.plt } ×10</span> 
									<span class="kong">其他 ${userArchiveTestCasePregnancy.other }</span></td>
									<td>尿常规： 白细胞 ${userArchiveTestCasePregnancy.whiteBlood } 个/u 
									<span class="kong">红细胞 ${userArchiveTestCasePregnancy.redBlood } 个/ul</span></td>
								</tr>
								<tr>
									<td>血蛋白： 
										<c:if test="${userArchiveTestCasePregnancy.protein==0 }">阴性</c:if>
                                    	<c:if test="${userArchiveTestCasePregnancy.protein==1 }">阳性</c:if>
									</td>
									<td>尿比重： ${userArchiveTestCasePregnancy.sg }</td>
									<td>孕妇血型： 
										<c:if test="${userArchiveTestCasePregnancy.pregnantWomenBloodType==0 }">A</c:if>
                                      	<c:if test="${userArchiveTestCasePregnancy.pregnantWomenBloodType==1 }">B</c:if>
                                      	<c:if test="${userArchiveTestCasePregnancy.pregnantWomenBloodType==2 }">O</c:if>
                                      	<c:if test="${userArchiveTestCasePregnancy.pregnantWomenBloodType==3 }">AB</c:if>
									</td>
									<td>丈夫血型 ： 
										<c:if test="${userArchiveTestCasePregnancy.husbardBloodType==0 }">A</c:if>
                                       	<c:if test="${userArchiveTestCasePregnancy.husbardBloodType==1 }">B</c:if>
                                       	<c:if test="${userArchiveTestCasePregnancy.husbardBloodType==2 }">O</c:if>
                                       	<c:if test="${userArchiveTestCasePregnancy.husbardBloodType==3 }">AB</c:if>
									</td>
								</tr>
								<tr>
									<td>肝肾功能：${userArchiveTestCasePregnancy.hepatorenalFunction }</td>
									<td>围产四项：${userArchiveTestCasePregnancy.perinatalFourItems }</td>
									<td>乙肝三对：${userArchiveTestCasePregnancy.hepatitisThreePair }</td>
									<td>丙肝、病毒、HIV： ${userArchiveTestCasePregnancy.hiv }</td>
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
										<td>促甲状腺激素TSH：${userArchiveTestCasePregnancy.tsh } mIU/L</td>
										<td>游离三碘甲状腺原氨酸FT3：${userArchiveTestCasePregnancy.fts } pmol/L</td>
										<td>血清游离甲状腺激素FT4：${userArchiveTestCasePregnancy.ftf } nmol/L</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<table class="gw_xx">
						<tr>
							<td>地贫筛查：${userArchiveTestCasePregnancy.poorScreening }</td>
						</tr>
						<tr>
							<td>支原体、衣原体、淋球菌：  ${userArchiveTestCasePregnancy.mcg }</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="weight-title">孕期补充检查结果</div>
			<div class="panel-body mod_btn">
				<table class="gw_xx ">
					<tbody>
						<tr>
							<td width="50%">早期四维彩超（时间、头臀长、NT）：${userArchiveTestCasePregnancy.earlyFourDimensional }</td>
							<td width="50%">唐氏筛查情况：早期筛查 ${userArchiveTestCasePregnancy.earlyScreening }<span class="kong">中期筛查 ${userArchiveTestCasePregnancy.midScreening }</span></td>
						</tr>
						<tr>
							<td width="50%">无创DNA或羊水穿刺情况：${userArchiveTestCasePregnancy.noninvasiveDna }</td>
							<td width="50%">中晚期四维彩超情况（孕周及异常情况）：${userArchiveTestCasePregnancy.midLateFourDimensional }</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="weight-title">诊断</div>
			<div class="gw_jy">
				<div class="cbzd">
					<p class="float-left">诊断内容：</p>
					${userArchiveTestCaseDiagnose.diagnoseModelContent }

				</div>
				<div class="panel-body">
					<table class="gw_xx">
						<tbody>
							<tr>
								<td>检查医生： </td>
								<td>登记人员：</td>
								<td>检查医院：</td>
								<td>检查日期：</td>
								<td>初检孕周：</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>