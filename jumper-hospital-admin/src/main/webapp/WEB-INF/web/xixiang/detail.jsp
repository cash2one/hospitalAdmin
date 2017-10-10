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
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/warning.js?${v }"></script>
<style type="text/css">
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
            <table class="table" style="margin-bottom:0px">
            	<tr>
            		<td><span>监护开始时间：${consumer.heart.time }</span></td>
            		<td style="text-align:center"><span>监护结束时间：${consumer.heart.endTime }</span></td>
            		<td style="text-align:right"><span>监护时长：${consumer.heart.minute }</span></td>
            	</tr>
            </table>
            <div class="chart-div content-canvas" id="chart${consumer.consumerId }">
            	<input type="hidden" class="music" value="${consumer.heart.music }" />
				<input type="hidden" class="data" value="${consumer.heart.data }" /> 
				<input type="hidden" class="fetalMoveData" value="${consumer.heart.fetalMoveData }" /> 
				<input type="hidden" class="uterusData" value="${consumer.heart.uterusData }" /> 
				<input type="hidden" class="baseData" value="[]" />
				<input type="hidden" class="adData" value="[]" />
                <div class ='chart-canvas' data-isclick='false'>
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
	            		<td><span>监护开始时间：${consumer.heart.time }</span></td>
	            		<td style="text-align:center"><span>监护结束时间：${consumer.heart.endTime }</span></td>
	            		<td style="text-align:right"><span>监护时长：${consumer.heart.minute }</span></td>
	            	</tr>
	            	<tr>
	            		<td colspan="3">
	            			<p>说明：1. 本报告仅对监测样本负责，仅供临床参考，不做诊断证明之用。</p>
	            			<p style="text-indent: 3em;">2. 请依据医生建议按时复诊或孕妇自感异常随时复诊。</p>
	            		</td>
	            	</tr>
	            </table>
	            
	            <c:set var="YWFY_HOSPITAL_ID" value="<%=Const.YWHOSPITAL_ID %>"/>  
                <div class="operate-btn">
                	<c:if test="${consumer.remoteType != '院内监护'&&hospitalInfo.id!=YWFY_HOSPITAL_ID}">
	                    <button type="button" class="btn btn-info report-chat" userId="${consumer.userId }" 
	                    hospitalId="${hospitalInfo.id}" userName="${consumer.userName }" 
	                    orderId="${consumer.orderId }" consumerId="${consumer.consumerId }" questionId = "${consumer.questionId}"
	                    monitorTimes="${consumer.monitorTimes }" hospitalName = "${admin.hospitalInfo.name}">即时问诊</button>&nbsp;&nbsp;&nbsp;&nbsp;
                    </c:if>
                    <button type="button" class="btn btn-danger go-back" value="">返回列表</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 聊天窗口 -->
<div class="modal fade" id="reportChatModal" tabindex="-1" role="dialog" aria-labelledby="reportChatModalLabel" aria-hidden="true">
   <div class="modal-dialog message-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <center><h4 class="modal-title"  style="font-weight:bold" id="reportChatModalLabel"></h4></center>
         </div>
         <div class="modal-body">
         	<center><div style="display:none" class="report"><a href="" class="reportUrl" target= _blank style="font-weight:bold">点击</a>查看监护报告详情</div></center>
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


<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/hospital/warning_dialog.jsp"></jsp:include>