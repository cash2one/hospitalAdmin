<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.jumper.hospital.utils.Consts"%>  
<%@page import="com.jumper.hospital.utils.Const"%> 
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/media/css/x_report.css?${v }" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/html2canvas.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/xixiang/x_chart.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/xixiang/x_report.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/xixiang/automaticScoringFischer.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/warning.js?${v }"></script>
<style type="text/css">
	*{
		margin: 0;
		padding: 0;
	}
	.content-canvas{
		width: 1265px;
		margin: 20px auto 0;
		position: relative;
	}
	.content-canvas canvas{
	    moz-user-select: -moz-none;
	    -moz-user-select: none;
	    -o-user-select:none;
	    -khtml-user-select:none;
	    -webkit-user-select:none;
	    -ms-user-select:none;
	    user-select:none;
	    display: block;
	}
	.content-canvas .content-chart{
		position: relative;
		width: 1200px;
		margin: 0 auto;
		overflow-x: auto;
		overflow-y: hidden;
	}
	.contentChart{
	    height: 360px;
	}
	.oyLabel{
	    width: 25px;
	    height: 360px;
	    position: absolute;
	 	left: 0px;
	 	top: 0;
	}
	.oxLabel{
	    height: 25px;
	}
	.uterusYLabel{
	    width: 25px;
	    height: 160px;
	    margin-top: 0px;
	    border:none;
	    position: absolute;
	 	left: 0px;
	 	top: 387px;
	 }
	 .uterusChart{
	    height: 160px;
	    margin-top: 0px;
	    border:none;
	 }
     .message-dialog {width:596px !important; }
     
     td{
  	   text-align: left;
  	   margin-left: 12px;
     }
     .source tr td{
         border: 0px solid #aaa;
     }
     
</style>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<div class="panel panel-default" id="panel${consumer.consumerId }" style="min-width: 1490px;">
        <div class="panel-body" style="min-width: 1490px;">
            <div class="panel-head">
                <ul>
                    <li style="width:10%;">${status.index + 1 }</li>
                    <li style="width:10%;">${consumer.userName }</li>
                    <li style="width:10%;">${consumer.userAge }岁</li>
                    <li style="width:10%;">孕${consumer.preganyWeek }周</li>
                    <li style="width:10%;">手机：${consumer.mobile }</li>
                    <li style="width:10%;">${consumer.remoteType }</li>
                    <li style="width:10%;"><img value="${consumer.consumerId }" class="play" src="${pageContext.request.contextPath}/media/image/button_play_hover.png"></li>
                    
                    <!-- 下面的设置只针对义务妇幼显示设置 -->
 					<c:set var="YWFY_HOSPITAL_ID" value="<%=Const.YWHOSPITAL_ID %>"/>  
				   	<c:if test="${hospitalInfo.id == YWFY_HOSPITAL_ID}">
                   		<li style="width:10%;"><button type="button" class="btn btn-info warning_info" userId="${consumer.userId }" consumerId="${consumer.consumerId }" hospitalId="${hospitalInfo.id}">预警记录</button></li> 
                  	</c:if> 
                    <li class="timeNow" style="width:10%;">--</li>
                    <li class="valueNow" style="width:10%;">--</li>
                </ul>
            </div>
            <div class="clearFix"></div>
            <div class="chart-div content-canvas" id="chart${consumer.consumerId }">
	            <!-- 需要传给后台的begin -->
	            <!-- 1、recordId -->
	            <input type="hidden"  name="recordId" value="${consumer.recordId}" />
	            <!-- 2、自动评分类型  type -->
	             <input type="hidden"  name="scoringMethod" value="${mHospital.scoringMethod}" />
	            <!-- 需要传给后台的end -->
                <input type="hidden" class="music" value="${consumer.heart.music }" />
				<input type="hidden" class="data" value="${consumer.heart.data }" /> 
				<input type="hidden" class="fetalMoveData" value="${consumer.heart.fetalMoveData }" /> 
				<input type="hidden" class="uterusData" value="${consumer.heart.uterusData }" /> 
				<input type="hidden" class="baseData" value="${fhrInfo.baselines}" />
				<input type="hidden" class="adData" value=${fhrInfo.vocFhrLocation} />
                <div class ='chart-canvas'  data-isClick='false'>
	                <canvas class="oyLabel"></canvas>
	                <canvas class="contentChart"></canvas>
	                <div>
	                    <canvas class="oxLabel"></canvas>
	                </div>
	                <canvas class="uterusYLabel"></canvas>
	                <canvas class="uterusChart"></canvas>
	           	</div>
            </div>
			
 				<table class="table kreb_table" style="margin-bottom:0px">
			            	<tr>
			            		<td>改良fischer评分 </td>
			            		<td>&nbsp;</td>
			            	</tr>
			            	<tr>
			            		<td style="width:45%">
			            			<table class="source">
			            				<tr>
			            					<td style="width:25%;" >名称</td>
			            					<td style="width:25%;" >本例结果</td>
			            					<td style="width:35%;" >分析</td>
			            					<td style="width:25%;">得分</td>
			            				</tr>
			            				<tr>
			            					<td>胎心率基线（bpm）</td>
			            					<td> <input class="leftTableData" style="width: 83px;" type="text" name="basal" value="${fhrInfo.voFhrScore.basal}"/> </td>
			            					<td id="basalAnalysis" >${scoringAnalysis.basalAnalysis}</td>
			            					<td class="rightScoreData" id="basalScore" >${fhrInfo.voFhrScore.basalScore}</td>
			            				</tr>
			            				<tr>
			            					<td>振幅变异(bpm)</td>
			            					<td><input class="leftTableData" style="width: 83px;" type="text" name="bpmVar" value="${fhrInfo.voFhrScore.bpmVar }"/></td>
			            					<td id="bpmVarAnalysis" >${scoringAnalysis.bpmVarAnalysis}</td>
			            					<td class="rightScoreData" id="bpmVScore" >${fhrInfo.voFhrScore.bpmVScore}</td>
			            				</tr>
			            				<tr>
			            					<td>周期变异(cpm)</td>
			            					<td><input class="leftTableData" style="width: 83px;" type="text" name="freqVar" value="${fhrInfo.voFhrScore.freqVar }"/></td>
			            					<td id="freqVarAnalysis" >${scoringAnalysis.freqVarAnalysis}</td>
			            					<td class="rightScoreData" id="freqVScore" >${fhrInfo.voFhrScore.freqVScore}</td>
			            				</tr>
			            				<tr>
			            					<td>加速（次/30分）</td>
			            					<td><input class="leftTableData" style="width: 83px;" type="text" name="numAcc" value="${fhrInfo.voFhrScore.numAcc }"/></td>
			            					<td id="numAccAnalysis" >${scoringAnalysis.numAccAnalysis}</td>
			            					<td class="rightScoreData" id="numAccScore" >${fhrInfo.voFhrScore.numAccScore}</td>
			            				</tr>
			            				<tr>
			            					<td>减速（次/30分）</td>
			            					<td>
			            						<%-- <input style="width: 53px;" type="text" name="numDec" value="${fhrInfo.voFhrScore.numDec }"/> --%>
			            						<select class="leftTableData" style="width: 83px;" name="numDec" >
												  <option value ="0" <c:if test="${fhrInfo.voFhrScore.numDec ==0}"> selected ="selected"</c:if> >无 </option>
												  <option value ="1" <c:if test="${fhrInfo.voFhrScore.numDec == 1}"> selected ="selected"</c:if>>轻度VD&gt2,或ED&gt2</option>
												  <option value="2" <c:if test="${fhrInfo.voFhrScore.numDec ==2}"> selected ="selected"</c:if> >LD,PD,重度VD</option>
												</select>
			            					</td>
			            					<td id="numDecAnalysis" >${scoringAnalysis.numDecAnalysis}</td>
			            					<td class="rightScoreData" id="numDecScore" >${fhrInfo.voFhrScore.numDecScore}</td>
			            				</tr>
			            				<tr>
			            					<td></td>
			            					<td id="numFetalMove"></td>
			            					<td></td>
			            					<td id="fetalMoveScore"></td>
			            				</tr>
			            			</table>
			            		</td>
			            		<td>
			            			<table class="result">
			            				<tr>
			            					<td id="source" >结果:</td>
			            					<td >改良fischer <span id="sourceScore" style="color: red;" > ${fhrInfo.voFhrScore.totalScore}  </span> 分</td>
			            				</tr>
			            				<tr>
			            					<td>NST</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="response" value="1" /> <span value="有反应">有反应</span> 
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="adiaphoria" value="1" /> <span value="无反应">无反应</span>
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="sinusoid" value="1" /> <span value="正弦曲线"> 正弦曲线</span>
			       									</label>
			            						</div>
			            					</td>
			            					<td colspan="2">
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="nstYawp" value="1" />  <span value="不满意1">不满意</span>
			       									</label>
			            						</div>
			            					</td>
			            				</tr>
			            				<tr>
			            					<td>OCT</td>
			            					<td style="width:20%">
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="cstNsCst" value="1" /> <span value="OST或NS-CST">CST或NS-CST</span>
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="positive" value="1" /> <span value="阳性">阳性</span>
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="feminine" value="1" /> <span value="阴性">阴性</span>
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="suspicious" value="1" /> <span value="可疑"> 可疑</span>
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="octYawp" value="1"> <span value="不满意2"> 不满意</span>
			       									</label>
			            						</div>
			            					</td>
			            				</tr>
			            				<tr>
			            					<td>备注：</td>
			            					<td colspan="5">
			            						<textarea rows="2" cols="60" class="descContent" name="remark"></textarea>
			            					</td>
			            				</tr>
			            				<tr>
			            					<td colspan="3">
			            						监护医生：${admin.name }
			            					</td>
			            				</tr>
			            			</table>
			            		</td>
			            	</tr>
			            </table>
	            
		           <div class="operate-btn">
		               <button type="button" class="btn btn-info autoScoreGeneratePDF" speed="${speed}" consumerId="${consumer.consumerId}" start="${start}" end="${end}" >生成报告</button>
		               <button type="button" class="btn btn-danger go-back" value="">返回列表</button>
		           </div>
            </div>
        </div>
    </div>
</div>

<!-- 校验弹出框 -->
<style>
	.close-autoScore{
	    float: right;
	    font-size: 21px;
	    font-weight: bold;
	    line-height: 1;
	    color: #000;
	    text-shadow: 0 1px 0 #fff;
	    filter: alpha(opacity=20);
	    opacity: .2;
	    -webkit-appearance: none;
	    padding: 0;
	    cursor: pointer;
	    background: transparent;
	    border: 0;
	    margin-top: -2px;
	}
</style>
<div class="modal" id="info-dialog-autoScore" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
	        	<button type="button" class="close-autoScore info-dialog-close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title info-dialog-title" id="gridSystemModalLabel">提示！</h4>
	      	</div>
			<div class="modal-body info-dialog-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger info-dialog-sure">确定</button>
			</div>
		</div>
	</div>
</div>

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/hospital/warning_dialog.jsp"></jsp:include>