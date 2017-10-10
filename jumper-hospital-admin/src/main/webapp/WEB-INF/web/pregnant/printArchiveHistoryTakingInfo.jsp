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
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/showArchiveHistoryTaking.js"></script>
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
<!-- 所有需要拆分的数据 -->
<input type="hidden" id="pregnancyTime" value="${userHistoryTaking.pregnancyTime }"/>
<input type="hidden" id="morningSicknessTime" value="${userHistoryTaking.morningSicknessTime }"/>
<input type="hidden" id="quickeningTime" value="${userHistoryTaking.quickeningTime }"/>
<input type="hidden" id="animalContactTime" value="${userHistoryTaking.animalContactTime }"/>
<input type="hidden" id="takeTeratogenicDrugContent" value="${userHistoryTaking.takeTeratogenicDrugContent }"/>
<input type="hidden" id="anamnesis" value="${userHistoryTakingOtherInfo.anamnesis }"/>
<input type="hidden" id="familyHistory" value="${userHistoryTakingOtherInfo.familyHistory }"/>
<input type="hidden" id="allergicType" value="${userHistoryTakingOtherInfo.allergicType }"/>
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
			<h2>病史询问</h2>
		</div>
		<div class="panel-body dayin" style="padding:15px;">
			<table>
				<tbody>
					<tr>
						<td width="20%">姓名：  ${userBookBasicInfo.name }</td>
						<td width="20%">年龄： ${userBookBasicInfo.age }岁</td>
						<td width="25%">联系电话： ${userBookBasicInfo.mobile }</td>
						<td width="35%">身份证号： ${userBookBasicInfo.idNo }</td>

					</tr>
				</tbody>
			</table>
		</div>
		<div id="cont_roll">
			<div class="weight-title">本次妊娠情况</div>
			<div class="panel-body">
				<table class="gw_xx">
					<tbody>
						<tr>
							<td>初潮年龄：${userHistoryTaking.menophaniaAge }岁</td>
							<td>月经周期：${userHistoryTaking.mensesSpace } / ${userHistoryTaking.mensesDays }  天</td>
						</tr>
						<tr>
							<td>初次确诊怀孕时间：${userHistoryTaking.firstAffirmPregnancyDate }  孕<span class="kong"></span>周<span
								class="kong"></span>天</td>
							<td>早孕反应：
								<c:if test="${userHistoryTaking.morningSicknessState==0 }">无</c:if>
                               	<c:if test="${userHistoryTaking.morningSicknessState==1 }">有</c:if>
							</td>
							<td>初感胎动：
								<c:if test="${userHistoryTaking.quickening==0 }">无</c:if>
                                <c:if test="${userHistoryTaking.quickening==1 }">有</c:if>
							&nbsp;&nbsp;孕<span class="kong" id="quickeningWeek"></span>周<span class="kong" id="quickeningDay"></span>天</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="weight-title">本次妊娠检查前情况</div>
			<div class="panel-body">
				<div style="margin-bottom:25px;">
					<table class="gw_xx">
						<tbody>
							<tr>
								<td>有无异常：
									<c:if test="${userHistoryTaking.isAbnormal==0 }">无</c:if>
                                  	<c:if test="${userHistoryTaking.isAbnormal==1 }">有</c:if>
								</td>
								<td colspan="3">叶酸服用：孕前
									<c:if test="${userHistoryTaking.folicAcidProgestation==1 }">1月</c:if>
                                  	<c:if test="${userHistoryTaking.folicAcidProgestation==2 }">2月</c:if>
                                  	<c:if test="${userHistoryTaking.folicAcidProgestation==3 }">3月</c:if>
								 	&nbsp;&nbsp;&nbsp; 孕早期
								 	<c:if test="${userHistoryTaking.folicAcidMorningSickness==1 }">1月</c:if>
                                   	<c:if test="${userHistoryTaking.folicAcidMorningSickness==2 }">2月</c:if>
                                   	<c:if test="${userHistoryTaking.folicAcidMorningSickness==3 }">3月</c:if>
									&nbsp;&nbsp;&nbsp;第一次检查尿检阳性是停经后&nbsp;<strong>${userHistoryTaking.firstExamineAfterMenelipsis }</strong>&nbsp;天</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="live_inf">
					<span>孕早期情况</span>
					<div class="live_inftable">
						<table class="gw_xx">
							<tbody>
								<tr>
									<td>发热（>38℃）：
										<c:if test="${userHistoryTaking.feverState==0 }">无</c:if>
										<c:if test="${userHistoryTaking.feverState==1 }">有</c:if>
									 伴随症状：${userHistoryTaking.feverDevelop }</td>
									<td>阴道出血： 
										<c:if test="${userHistoryTaking.colporrhagiaState==0 }">无</c:if>
										<c:if test="${userHistoryTaking.colporrhagiaState==1 }">有</c:if>
									 伴随症状：${userHistoryTaking.colporrhagiaDevelop }</td>
								</tr>
								<tr>
									<td>猫、狗等动物接触史：
										<c:if test="${userHistoryTaking.animalContact==0 }">无</c:if>
										<c:if test="${userHistoryTaking.animalContact==1 }">有</c:if>
										接触时间： 孕<span id="animalContactStartWeek" style="position: initial;"></span>周<span id="animalContactStartDay" style="position: initial;"></span>天 至 孕<span id="animalContactEndWeek" style="position: initial;"></span>周<span id="animalContactEndDay" style="position: initial;"></span>天，共<strong id="animalContactNumber"></strong>天
									</td>
									<td>糖尿病：
										<c:if test="${userHistoryTaking.diabetesMellitus==0 }">无</c:if>
										<c:if test="${userHistoryTaking.diabetesMellitus==1 }">有</c:if>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div id="live_inf">
					<span>服用可能致畸药物</span>
					<div class="live_inftable">
						<table class="gw_xx">
							<tbody id="takeTeratogenicDrugContentTable">
                                         
                                         
                            </tbody>
						</table>
					</div>
				</div>
				<div style="margin-bottom:25px;">
					<table class="gw_xx">
						<tbody>
							<tr>
								<td width="35%">接触物理性有害因素： <span class="kong">噪声</span> <span
									class="kong">振动</span>
								</td>
								<td width="25%">接触高浓度工业毒物：
									<c:if test="${userHistoryTaking.contactIndustrialToxicant==0 }">无</c:if>
                                    <c:if test="${userHistoryTaking.contactIndustrialToxicant==1 }">有</c:if>
								</td>
								<td width="25%">环境新装修：
									<c:if test="${userHistoryTaking.environmentalNewDecoration==0 }">无</c:if>
                                    <c:if test="${userHistoryTaking.environmentalNewDecoration==1 }">有</c:if>
								</td>
								<td width="15%">吸毒：
									<c:if test="${userHistoryTaking.takeDrugs==0 }">无</c:if>
                                    <c:if test="${userHistoryTaking.takeDrugs==1 }">有</c:if>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="live_inf">
					<span>饮酒史</span>
					<div class="live_inftable">
						<c:if test="${userHistoryTaking.drinkHistoryState==0 }">无</c:if>
                        <c:if test="${userHistoryTaking.drinkHistoryState==1 }">有</c:if>
						<table class="gw_xx">
							<tbody>
								<tr style="height:45px;">
									<td>开始时间：${userHistoryTaking.drinkStartTime }</td>
									<td>白酒（克）：${userHistoryTaking.whiteSpirit }</td>
									<td>红酒（克）：${userHistoryTaking.redSpirit }</td>
									<td>啤酒（克）：${userHistoryTaking.beer }</td>

								</tr>
								<tr style="height:45px;">
									<td>饮酒频率：${userHistoryTaking.drinkFrequency }</td>
									<td>饮酒描述：${userHistoryTaking.drinkDescription }</td>
									<td>戒酒时段：${userHistoryTaking.abstinenceStartTime } 至 ${userHistoryTaking.abstinenceEndTime }</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div id="live_inf">
					<span>吸烟史</span>
					<div class="live_inftable">
						 <c:if test="${userHistoryTaking.smokeHistoryState==0 }">无</c:if>
	                     <c:if test="${userHistoryTaking.smokeHistoryState==1 }">主动</c:if>
	                     <c:if test="${userHistoryTaking.smokeHistoryState==2 }">被动</c:if>
						<table class="gw_xx">
							<tbody>
								<tr style="height:45px;">
									<td>开始时间：${userHistoryTaking.smokeStartTime }</td>
									<td>吸烟数（支/天）：${userHistoryTaking.smokeAmount }</td>
									<td>吸烟时段：</td>
									<td>吸烟描述：${userHistoryTaking.smokeDescription }</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div style="margin-bottom:25px;">
					<table class="gw_xx">
						<tbody>
							<tr>
								<td>病毒感染： 疱疹，腿部 <span class="kong"> 
								患病时间：${userHistoryTaking.virusInfectionTime }
								</span></td>
								<td>其他有害因素：${userHistoryTaking.otherHarmfulFactors }</td>
								<td>锻炼方式： </td>
							</tr>
							<tr>
								<td>饮食习惯： </td>
								<td>睡眠障碍： </td>
								<td>睡眠时间（小时/天）： </td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
			<div class="weight-title">孕产史</div>
			<div class="panel-body mod_btn">
				<table class="gw_xx">
					<tbody>
						<tr>
							<td>孕次（含本次）：${userHistoryTakingPregnancy.pregnancyCount }</td>
							<td>孕产史：
								<c:if test="${userHistoryTakingPregnancy.pregnancyHistoryState==0 }">无</c:if>
                                <c:if test="${userHistoryTakingPregnancy.pregnancyHistoryState==1 }">有</c:if>
							</td>
							<td>不孕不育史：
								<c:if test="${userHistoryTakingPregnancy.infertilityHistoryState==0 }">无</c:if>
                                <c:if test="${userHistoryTakingPregnancy.infertilityHistoryState==1 }">有</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="3">产次（次）： <span class="kong">阴道产 ${userHistoryTakingPregnancy.vaginaProduced }</span> <span
								class="kong">阴道手术产 ${userHistoryTakingPregnancy.vaginaOperationProduced }</span> 
								<span class="kong">剖宫产 ${userHistoryTakingPregnancy.caesareanSection }</span> <span
								class="kong">产钳 ${userHistoryTakingPregnancy.obstetricForceps }</span> 
								<span class="kong">宫外孕 ${userHistoryTakingPregnancy.ectopicPregnancy }</span> <span
								class="kong">其他助产 ${userHistoryTakingPregnancy.otherDeliver }</span></td>
						</tr>
						<tr>
							<td>末次手术时间：${userHistoryTakingPregnancy.operationLastTime }</td>
							<td>手术指征：${userHistoryTakingPregnancy.operationIndication }</td>
							<td>并发症：
								<c:if test="${userHistoryTakingPregnancy.complicationState==0 }">无</c:if>
                               	<c:if test="${userHistoryTakingPregnancy.complicationState==1 }">有</c:if>
                          	</td>
						</tr>
						<tr>
							<td width="17%">自然流产（次）：${userHistoryTakingPregnancy.spontaneousAbortion }</td>
							<td width="17%">人工流产（次）： ${userHistoryTakingPregnancy.inducedAbortion }</td>
							<td width="16%">引产（次）：  ${userHistoryTakingPregnancy.inducedLabour }</td>
						</tr>

						<tr>
							<td>早产（次）： ${userHistoryTakingPregnancy.prematureLabour }</td>
							<td>死胎/产（次）： ${userHistoryTakingPregnancy.stillbirthLabour }</td>
							<td>胚胎停育（次）： ${userHistoryTakingPregnancy.embryoDamage }</td>
						</tr>
						<tr>
							<td>末次流产：
								<c:if test="${userHistoryTakingPregnancy.lastAbortionState==0 }">无</c:if>
                           		<c:if test="${userHistoryTakingPregnancy.lastAbortionState==1 }">有</c:if>
                           	</td>
							<td>流产类型：自然流产</td>
							<td>流产时间：${userHistoryTakingPregnancy.abortionTime } </td>
						</tr>

						<tr>
							<td>末次妊娠时间：${userHistoryTakingPregnancy.lastPregnancyTime }</td>
							<td>新生儿死亡（次）：${userHistoryTakingPregnancy.perinatalMortality }</td>
							<td>死亡时间：${userHistoryTakingPregnancy.deathTime }</td>
						</tr>
						<tr>
							<td>死亡原因：${userHistoryTakingPregnancy.deathReason }</td>
							<td>现有存活子女： 男 <span class="kong">1</span> 女 <span
								class="kong">3</span></td>
							<td>既往出生缺陷：
								<c:if test="${userHistoryTakingPregnancy.alwaysBirthDefectsState==0 }">无</c:if>
                               	<c:if test="${userHistoryTakingPregnancy.alwaysBirthDefectsState==1 }">有</c:if>
							</td>
						</tr>
						<tr>
							<td>出生时间：${userHistoryTakingPregnancy.birthday }</td>
							<td>缺陷描述：${userHistoryTakingPregnancy.defectsDescription }</td>
							<td>结局：
								<c:if test="${userHistoryTakingPregnancy.ending==1 }">存活</c:if>
                               	<c:if test="${userHistoryTakingPregnancy.ending==2 }">矫治痊愈</c:if>
                               	<c:if test="${userHistoryTakingPregnancy.ending==3 }">残障</c:if>
                               	<c:if test="${userHistoryTakingPregnancy.ending==4 }">夭折</c:if>
							</td>
						</tr>
						<tr>
							<td>既往妊娠合并症史：
								<c:if test="${userHistoryTakingPregnancy.alwaysPregnancyHistoryState==0 }">无</c:if>
                                <c:if test="${userHistoryTakingPregnancy.alwaysPregnancyHistoryState==1 }">有</c:if>
							</td>
							<td>其他孕产史：${userHistoryTakingPregnancy.otherPergnancyHistory }</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="weight-title">其他信息</div>
			<div class="panel-body mod_btn">
				<div style="margin-bottom:25px;">
					<table class="gw_xx">
						<tbody>
							<tr>
								<td>主诉：${UserHistoryTakingOtherInfo.chiefComplaint }</td>
								<td>现病史：${UserHistoryTakingOtherInfo.presentHistory }</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="live_inf">
					<span>既往病史</span>
					<div class="live_inftable">
						<table class="gw_xx" class="bordered">
                                         <tbody id="anamnesisTable">
                                         
                                         
                                         </tbody>
                                    </table>
					</div>
				</div>
				<div id="live_inf">
					<span>家族病史</span>
					<div class="live_inftable">
						<div class="bs_tb">
							<table class="bordered" style="width:60%;">
                                    	<thead>
                                            <th>父亲</th>
                                            <th>母亲</th>
                                            <th>兄弟姐妹</th>
                                            <th>子女</th>
                                        </thead>
                                         <tbody id="familyHistoryTable">
                                         </tbody>
                                    </table>
						</div>
						<table class="gw_xx">
							<tr>
								<td colspan="5">父亲其他家族病史：${userHistoryTakingOtherInfo.fatherFamilyHistory }</td>
								<td colspan="5">母亲其他家族病史：${userHistoryTakingOtherInfo.motherFamilyHistory }</td>
							</tr>
							<tr>
								<td colspan="5">兄弟姐妹其他家族病史：${userHistoryTakingOtherInfo.brotherFamilyHistory }</td>
								<td colspan="5">子女其他家族病史：${userHistoryTakingOtherInfo.chirdFamilyHistory }</td>
							</tr>
						</table>

					</div>
				</div>
				<div style="margin-bottom:25px;">
					<table class="gw_xx">
						<tbody>
							<tr>
								<td width="50%">手术史：
									<c:if test="${userHistoryTakingOtherInfo.operationHistoryState==0 }">无</c:if>
                                    <c:if test="${userHistoryTakingOtherInfo.operationHistoryState==1 }">有</c:if>
								 <span class="kong">手术时间</span>
								 <span class="kong">手术名称 </span></td>
								<td>输血史：
									<c:if test="${userHistoryTakingOtherInfo.bloodTransfusionHistoryState==0 }">无</c:if>
                                    <c:if test="${userHistoryTakingOtherInfo.bloodTransfusionHistoryState==1 }">有</c:if>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="live_inf">
					<span>过敏史</span>
					<div class="live_inftable">
						<label><input style="width:10px;" type="radio" name="gms"
							checked>无</label> <label><input style="width:10px;"
							type="radio" name="gms">
							<c:if test="${userHistoryTakingOtherInfo.allergicHistoryState==0 }">无</c:if>
                            <c:if test="${userHistoryTakingOtherInfo.allergicHistoryState==1 }">有</c:if>	
						</label>
						<table class="gw_xx">
                                         <tbody id="allergicTypeTable">
                                         </tbody>
                                    </table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>