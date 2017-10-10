<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@page import="com.jumper.hospital.utils.Const"%> 

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/media/css/x_report.css?${v }" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/xixiang/x_chart.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/xixiang/x_report.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/xixiang/drag.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/warning.js?${v }"></script>
<style type="text/css">
/**  .box{width:100%; overflow:hidden; border-radius:5px;}
.box-3 dl{ display:table; position:absolute; bottom:10%; right:35%; width:300px; background:#fff;box-shadow: 0 0 7px rgba(0,0,0,.3); border-radius:3px; }
.box-3 dl dd{width:100%;height: 40px;line-height: 40px; font-size: 20px;position: relative;background:#d9534f;; text-align:center;border-radius: 0 0 5px 5px;}
.box-3 dl dd img{ margin:auto}
.box-3 dl p{ text-align:center; width:100%; margin-top:25px; margin-bottom:10px; font-size:16px;}
.call_hide{display:none;}  **/
* {
	margin: 0;
	padding: 0;
}
.content-canvas {
	width: 1265px;
	margin: 20px auto 0;
	position: relative;
}

.content-canvas canvas {
	moz-user-select: -moz-none;
	-moz-user-select: none;
	-o-user-select: none;
	-khtml-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
	user-select: none;
	display: block;
}

.content-canvas .content-chart {
	position: relative;
	width: 1200px;
	margin: 0 auto;
	overflow-x: auto;
	overflow-y: hidden;
}

.contentChart {
	height: 360px;
}

.oyLabel {
	width: 25px;
	height: 360px;
	position: absolute;
	left: 0px;
	top: 0;
}

.oxLabel {
	height: 25px;
}

.uterusYLabel {
	width: 25px;
	height: 160px;
	margin-top: 0px;
	border: none;
	position: absolute;
	left: 0px;
	top: 387px;
}

.uterusChart {
	height: 160px;
	margin-top: 0px;
	border: none;
}
.message-dialog {width:596px !important; }
	/* .call_hide{position:fixed;top:50px;right:300px;z-index:9;} */
	.call_hide{position:fixed;top: 45%; left: 50%;miargin-top:-75px;display:none;margin-left:-145px;z-index:7;border:1px solid #cacaca;}
	.call-ifre1{position: relative;z-index:11;height:144px;}
	.call-fire3{position:absolute;top:10px;right:3px;width:23px;height:20px;text-align:center;z-index:11;cursor: pointer;}
	.call-ifre2{position:absolute;top:0px;left:0;right:0;bottom:999px;z-index:10;}
	iframe body{background:none!important;}
</style>
<!-- 内容页 -->
<c:if test="${!open }">
	<div class="col-xs-12 col-sm-9">
		暂未开通胎心监护服务，立即前往医院管理<a href="${pageContext.request.contextPath}/rmService/index">开通服务？</a>
	</div>
</c:if>
<c:if test="${open }">
	<div class="col-xs-12 col-sm-9">
		<form action="${pageContext.request.contextPath}/report/audit" method="POST" id="form">
			<c:forEach var="consumer" items="${page.result }" varStatus="status">
				<div class="panel panel-default" id="panel${consumer.consumerId }">
			        <div class="panel-body">
			            <div class="panel-head">
			                <ul>
			                    <li style="width:10%;">${status.index + 1 }</li>
			                    <li style="width:10%;overflow: hidden;white-space: nowrap; text-overflow: ellipsis; ">${consumer.userName }</li>
			                    <li style="width:10%;">${consumer.userAge }岁</li>
			                    <li style="width:8%;">孕${consumer.preganyWeek }周</li>
			                    <li style="width:12%;">手机：${consumer.mobile }</li>
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
			            <table class="table" style="margin-bottom:0px">
			            	<tr>
			            		<td><span>监护开始时间：${consumer.heart.time }</span></td>
			            		<td style="text-align:center"><span>监护结束时间：${consumer.heart.endTime }</span></td>
			            		<td style="text-align:right"><span>监护时长：${consumer.heart.minute }</span></td>
			            	</tr>
			            	<tr>
			            		<td><span>留言：${consumer.commentInfo}</span></td>
			            	</tr>
			            </table>
			            <table class="table kreb_table" style="margin-bottom:0px">
			            	<tr>
			            		<td>改良fischer评分表</td>
			            		<td>&nbsp;</td>
			            	</tr>
			            	<tr>
			            		<td style="width:45%">
			            			<table class="source">
			            				<tr>
			            					<td>评分</td>
			            					<td>胎心率基线<br>(BPM)</td>
			            					<td>振幅变异<br>(BPM)</td>
			            					<td>周期变异<br>(次/分)</td>
			            					<td>加速<br>(次/30分)</td>
			            					<td>减速<br>(次/30分)</td>
			            				</tr>
			            				<tr>
			            					<td>0分</td>
			            					<td><100，>180</td>
			            					<td><3</td>
			            					<td><2</td>
			            					<td>0</td>
			            					<td>LD,PD,重度VD</td>
			            				</tr>
			            				<tr>
			            					<td>1分</td>
			            					<td>100-109 161-180</td>
			            					<td>3-5或>25</td>
			            					<td>2-5</td>
			            					<td>1-2</td>
			            					<td>轻度VD>2,或ED>2</td>
			            				</tr>
			            				<tr>
			            					<td>2分</td>
			            					<td>110-160</td>
			            					<td>6-25</td>
			            					<td>>5</td>
			            					<td>>2</td>
			            					<td>无</td>
			            				</tr>
			            			</table>
			            		</td>
			            		<td>
			            			<table class="result">
			            				<tr>
			            					<td>结果：</td>
			            					<td colspan="5">
			            						改良fischer评分&nbsp;&nbsp;&nbsp;&nbsp;
			            						<input type="text" name="source" class="source_input" value="" style="width:70px" /> 分
			            						<!-- 改良Fischer 如果没有宫缩则不自动评分,由于ios传入的数组有问题所以长度要>5 -->
			            						<%-- <c:if test='${consumer.heart.uterusData!=null }'> --%>
			            						<c:if test='${fn:length(consumer.heart.uterusData)>5}'>
			            							<button type="button" class="btn btn-info automaticScoring" style="margin-left: 130px;" consumerId="${consumer.consumerId }" >自动评分</button>
			            						</c:if>
			            					</td>
			            				</tr>
			            				<tr>
			            					<td>NST</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="有反应" />有反应
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="无反应" />无反应
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="正弦曲线" />正弦曲线
			       									</label>
			            						</div>
			            					</td>
			            					<td colspan="2">
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="不满意1" />不满意
			       									</label>
			            						</div>
			            					</td>
			            				</tr>
			            				<tr>
			            					<td>OCT</td>
			            					<td style="width:20%">
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="OST或NS-CST" />CST或NS-CST
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="阳性" />阳性
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="阴性" />阴性
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="可疑" />可疑
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="不满意2"> 不满意
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
			            					<td colspan="3">
			            						日期：${consumer.heart.time }
			            					</td>
			            				</tr>
			            				<tr>
			            					<td colspan="6">医生签名:</td>
			            				</tr>
			            			</table>
			            		</td>
			            	</tr>
			            </table>
			            <div class="table" style="text-align: left;">
			            	走纸速度：
			            	<select class="speed" id="${consumer.consumerId }">
			            		<option value="1">1厘米/分钟</option>
			            		<option value="2">2厘米/分钟</option>
			            		<option value="3" selected>3厘米/分钟</option>
			            		<option value="20">20分钟/页</option>
			            	</select>
			            </div>
			            <div class="chart-div content-canvas" id="chart${consumer.consumerId }">
			            	<input type="hidden" class="music" value="${consumer.heart.music }" />
							<input type="hidden" class="data" value="${consumer.heart.data }" /> 
							<input type="hidden" class="fetalMoveData" value="${consumer.heart.fetalMoveData }" /> 
							<input type="hidden" class="uterusData" value="${consumer.heart.uterusData }" /> 
							<input type="hidden" class="baseData" value="[]" />
							<input type="hidden" class="adData" value="[]" />
			                <div class ='chart-canvas'>
								<canvas class="oyLabel"></canvas>
								<canvas class="contentChart" value="${consumer.consumerId }"></canvas>
								<div>
									<canvas class="oxLabel"></canvas>
								</div>
								<canvas class="uterusYLabel"></canvas>
								<canvas class="uterusChart"></canvas>
							</div>
			            </div>
			            <table class="table">
			            	<tr>
			            		<td colspan="3">
			            			<p>说明：1. 本报告仅对监测样本负责，仅供临床参考，不做诊断证明之用。</p>
			            			<p style="text-indent: 3em;">2. 请依据医生建议按时复诊检查。</p>
			            			<p style="text-indent: 3em;">3. 请孕妇自感不适随时前往医院检查就诊。</p>
			            		</td>
			            	</tr>
			            </table>
		                <div class="operate-btn">
		                <c:if test="${cloudVsit == true}">
							 <button type="button" class="btn btn-success call_cont"
								id="call_cont" userMobile="${consumer.mobile }"
								userName="${consumer.userName }" userId="${consumer.userId }" hospitalId="${hospitalInfo.id}">电话沟通</button>
							&nbsp;&nbsp;&nbsp;&nbsp; 
							</c:if>
							<c:set var="YWFY_HOSPITAL_ID" value="<%=Const.YWHOSPITAL_ID %>"/>  
							<!-- 去掉义乌的即时问诊 -->
							<c:if test="${consumer.remoteType != '院内监护' && hospitalInfo.id!=YWFY_HOSPITAL_ID }">
								<button type="button" class="btn btn-danger report-chat"
									userId="${consumer.userId }" hospitalId="${hospitalInfo.id}"
									userName="${consumer.userName }" orderId="${consumer.orderId }"
									consumerId="${consumer.consumerId }" hospitalName = "${admin.hospitalInfo.name}"
									questionId = "${consumer.questionId}"
									monitorTimes="${consumer.monitorTimes }">即时问诊</button>&nbsp;&nbsp;&nbsp;&nbsp;
		                    </c:if>
							<button type="button" class="btn btn-info generate-report"
								value="${consumer.consumerId }">生成报告</button>
						</div>
			        </div>
			    </div>
			</c:forEach>
			<c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
				<div class="panel panel-default" >
		       		<div class="panel-body">
			 	    	<span style="color:red">暂无记录</span>
			   		</div>
				</div>
		   	</c:if>
		   	<jsp:include page="../common/page.jsp"></jsp:include>
		</form>
			<!-- 
			<c:forEach var="consumer" items="${page.result }" varStatus="status">
				<div class="panel panel-default" id="panel${consumer.consumerId }">
			        <div class="panel-body">
			            <div class="panel-head">
			                <ul>
			                    <li>${status.index + 1 }</li>
			                    <li>${consumer.userName }</li>
			                    <li>${consumer.userAge }岁</li>
			                    <li>孕${consumer.preganyWeek }周</li>
			                    <li><img value="${consumer.consumerId }" class="play" src="${pageContext.request.contextPath}/media/image/button_play_hover.png"></li>
			                    <li class="timeNow">--</li>
			                    <li class="valueNow">--</li>
			                </ul>
			            </div>
			            <div class="clearFix"></div>
			            <div class="chart-div" id="chart${consumer.consumerId }" style="text-align: center;background-color: #FFF;border:none">
			            	<input type="hidden" class="music" value="${consumer.heart.music }" />
			            	<input type="hidden" class="data" value="${consumer.heart.data }" />
			            	<input type="hidden" class="fetalMoveData" value="${consumer.heart.fetalMoveData }" />
			                <canvas class="oyLabel"></canvas>
			                <canvas class="contentChart"></canvas>
			                <div>
			                    <canvas class="oxLabel"></canvas>
			                </div>
			            </div>
			            <table class="table">
			            	<tr>
			            		<td><span>监护开始时间：${consumer.heart.time }</span></td>
			            		<td style="text-align:center"><span>监护结束时间：${consumer.heart.endTime }</span></td>
			            		<td style="text-align:right"><span>监护时长：${consumer.heart.minute }</span></td>
			            	</tr>
			            	<tr>
			            		<td colspan="3">
			            			<label class="font-normal">备注：</label>
			            			<textarea class="descContent" rows="5" name="content" placeholder="50字以内..." value="" style="vertical-align:middle;width: 96%;"></textarea>
			            		</td>
			            	</tr>
			            	<tr>
			            		<td colspan="3">
			            			<label class="font-normal">审核医师：</label>
			            			<span>${admin.name }</span>
			            		</td>
			            	</tr>
			            	<tr>
			            		<td colspan="3">
			            			<p>说明：1. 本报告仅对监测样本负责，仅供临床参考，不做诊断证明之用。</p>
			            			<p style="text-indent: 3em;">2. 请依据医生建议按时复诊或孕妇自感异常随时复诊。</p>
			            		</td>
			            	</tr>
			            </table>
		                <div class="operate-btn">
		                    <button type="button" class="btn btn-danger report-chat" userId="${consumer.userId }" 
		                    hospitalId="${hospitalInfo.id}" userName="${consumer.userName }" 
		                    orderId="${consumer.orderId }" consumerId="${consumer.consumerId }" 
		                    monitorTimes="${consumer.monitorTimes }">即时问诊</button>&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button type="button" class="btn btn-info generate-report" value="${consumer.consumerId }">生成报告</button>
		                </div>
			        </div>
			    </div>
			</c:forEach>
			<c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
				<div class="panel panel-default" >
		       		<div class="panel-body">
			 	    	<span style="color:red">暂无记录</span>
			   		</div>
				</div>
		   	</c:if>
		   	<jsp:include page="../common/page.jsp"></jsp:include>
		   	 -->
	</div>
</c:if>

<!-- 聊天窗口 -->
<div class="modal fade" id="reportChatModal" tabindex="-1" role="dialog" aria-labelledby="reportChatModalLabel" aria-hidden="true">
   <div class="modal-dialog message-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <center><h4 class="modal-title"  style="font-weight:bold" id="reportChatModalLabel"></h4></center>
         </div>
         <div class="modal-body">
		 	<iframe id="hospitalChatIframe" width="570px" height="600px" frameborder="0" src=""></iframe>
         </div>
         <div class="modal-footer rule-modal-footer">
         	<center>
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </center>
         </div>
      </div>
	</div>
</div>
 <div class='box box-3 call_hide'>
 	
	<div class="call-ifre1">
		<div class="call-ifre2"></div>
		<div class="call-fire3"></div>
		<iframe id="callToPatient" src=""  style="relative;width:290px;height:144px;border:0;border-radius:4px;" scrolling="no">
		
		</iframe>
	</div>
</div> 

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/hospital/warning_dialog.jsp"></jsp:include>