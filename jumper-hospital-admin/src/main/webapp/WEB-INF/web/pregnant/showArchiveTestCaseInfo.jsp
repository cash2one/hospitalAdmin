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
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/showArchiveTestCase.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/loadArchiveBaseInfo.js"></script>
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
.gw_btn { width:280px;}
</style>
<!--style-->
</head>
<body style="background: #F0F0F0;">
                <!-- 档案管理 高危筛查 -->	                
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
	               	       
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
		<div class="panel" >
        	<div class="gw_rote"> <span class="float-left" style="font-size:16px;">档案管理 >档案详情 > <a style=" color:#ff7ea5">初检情况</a></span></div>
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
             <input type="hidden" id="archiveId" value="${archiveId }"/>
			<input type="hidden" id="archiveBaseId" value="${archiveBaseId }"/>
            <div class="panel panel-default float-left" style="margin:15px 0px;width: 100%;">
                <div class=" float-left" style="width:100%;">
                        <ul id="myTab" class="nav nav-tabs">
   							<li><a href="${pageContext.request.contextPath}/pregnant/showArchiveBaseInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" >基本信息</a></li>
   							<li><a href="${pageContext.request.contextPath}/pregnant/showArchiveHistoryTakingInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}">病史询问</a></li>
                            <li class="active"><a>初检情况</a></li>
                             <li><a href="${pageContext.request.contextPath}/monitor/weight?userId=${userBookBasicInfo.userId}&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}"  >监测数据</a></li>
                            <li><a href="${pageContext.request.contextPath}/PregnantEnd/lookIndex?userId=${archiveBaseId}&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}"  >分娩结局</a></li>
                            <li ><a href="${pageContext.request.contextPath}/postnatal/vistRecord?userId=${archiveBaseId}&visitType=1&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" >产后访视</a></li>
                            <li><a href="${pageContext.request.contextPath}/neonatal/lookIndex?userId=${archiveBaseId}&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}"  >新生儿访视</a></li>
  						</ul>
                </div>

             <div id="cont_roll" class="panel panel-default float-left" style=" margin:15px;width: 98%;">  
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
                                                 	<td width="20%">身高： ${userArchiveTestCase.height } cm</td>
                                                    <td width="20%">体重：${userArchiveTestCase.weight } kg</td>
                                                    <td width="20%">BMI：${userArchiveTestCase.bmi }</td>
                                                    <td width="20%">血压：${userArchiveTestCase.dbp } - ${userArchiveTestCase.sbp } mmHg</td>
                                                    <td >浮肿：
                                                    	<c:if test="${userArchiveTestCase.edemaState==0 }">无</c:if>
                                                    	<c:if test="${userArchiveTestCase.edemaState==1 }">轻</c:if>
                                                    	<c:if test="${userArchiveTestCase.edemaState==2 }">中</c:if>
                                                    	<c:if test="${userArchiveTestCase.edemaState==3 }">重</c:if>
                                                    </td>
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
                                                     <td  width="20%">肝脾： 
                                                     	<c:if test="${userArchiveTestCase.liverSpleenState==0 }">正常</c:if>
                                                     	<c:if test="${userArchiveTestCase.liverSpleenState==1 }">异常</c:if>${userArchiveTestCase.liverSpleenDescription }
                                                     </td>
                                                     <td  width="20%">甲状腺： 
                                                     	<c:if test="${userArchiveTestCase.thyroidState==0 }">正常</c:if>
                                                     	<c:if test="${userArchiveTestCase.thyroidState==1 }">异常</c:if>${userArchiveTestCase.thyroidDescription }
                                                     </td>
                                                     <td  width="20%">四肢： 
                                                     	<c:if test="${userArchiveTestCase.fourLimbsState==0 }">正常</c:if>
                                                     	<c:if test="${userArchiveTestCase.fourLimbsState==1 }">异常</c:if>${userArchiveTestCase.fourLimbsDescription }
                                                     </td> 
                                                     <td  width="20%">皮肤：
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
                                                    <td>宫高：${userArchiveTestCase.fundalHeight } cm</td>
                                                    <td>附件：${userArchiveTestCase.adjunct }</td>
                                                    <td>腹围：${userArchiveTestCase.abdominalGirth } cm</td>
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
                                        <td width="20%">髂嵴间径：${userArchiveTestCase.skeletonsRidgeDiameter } cm</td>
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
                                    	<td colspan="2">血常规：
                                        Hb ${userArchiveTestCasePregnancy.hb } g/l
                                        <span class="kong">MCV ${userArchiveTestCasePregnancy.mcv }</span>
                                        <span class="kong">MCH ${userArchiveTestCasePregnancy.mch }</span>
                                        <span class="kong">PLT ${userArchiveTestCasePregnancy.plt } ×10</span>
                                        <span class="kong">其他 ${userArchiveTestCasePregnancy.other }</span>
                                        </td>
                                        <td>尿常规：白细胞 ${userArchiveTestCasePregnancy.whiteBlood } 个/u
                                        <span class="kong">红细胞 ${userArchiveTestCasePregnancy.redBlood } 个/ul</span>
                                        </td>
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
                     	<tr><td>地贫筛查：${userArchiveTestCasePregnancy.poorScreening }</td></tr>
                        <tr><td>支原体、衣原体、淋球菌： ${userArchiveTestCasePregnancy.mcg }</td></tr>
                     </table>   
                        
                   </div>
                    </div>
                    <div class="weight-title">孕期补充检查结果</div>
                     <div class="panel-body mod_btn" >
                     	<table class="gw_xx ">
                                    <tbody>
                                    <tr>
                                        <td width="50%">早期四维彩超（时间、头臀长、NT）：${userArchiveTestCasePregnancy.earlyFourDimensional }</td>
                                        <td width="50%">唐氏筛查情况：早期筛查 ${userArchiveTestCasePregnancy.earlyScreening }<span class="kong"> 中期筛查 ${userArchiveTestCasePregnancy.midScreening }</span></td>
                                       
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
                                    <td >检查医生：</td>
                                    <td >登记人员：</td>
                                    <td >检查医院：</td> 
                                    <td >检查日期：</td>
                                    <td >初检孕周：</td> 
                                </tr>
                        </tbody>
                     </table>
                </div>
            	</div>     
            </div>
            <div class="gw_btn" style=" margin-bottom:20px;">
            <a href="${pageContext.request.contextPath}/pregnant/toUpdateArchiveTestCaseInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}"><input class="btn btn-default oneline" style="width: 85px; height:40px;padding:0px 0px;" readonly value="修改" type="button"></a>
            <a href="${pageContext.request.contextPath}/pregnant/toPrintArchiveTestCaseInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}"><input class="btn btn-default oneline" style="width: 85px; height:40px;padding:0px 0px;" readonly value="打印" type="button"></a>
            </div>
            </div>
	    </div>
    	
</div>
    <script>
       $('.panel-body#myTab a').click(function (e) {
      e.preventDefault();
      $(this).tab('show');
    });

   $(function () { $("[data-toggle='tooltip']").tooltip(); });
	</script>
    
<!-- 引入页尾 -->

</div>
</body>
