<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/media/css/report.css?${v }" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/chart.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/report.js?${v }"></script>
<style type="text/css">
	.contentChart{
         width:1200px;
         height:210px;
         margin-top: 20px;
         border:none;
         /**border: 1px solid #DADADA;**/
     }
     .oyLabel{
         width: 25px;
         height: 210px;
         margin-top: 20px;
         border:none;
     }
     .oxLabel{
         width: 1200px;
         height: 25px;
         margin-left: 35px;
         border:none;
     }
     canvas {
		moz-user-select: -moz-none;
		-moz-user-select: none;
		-o-user-select:none;
		-khtml-user-select:none;
		-webkit-user-select:none;
		-ms-user-select:none;
		user-select:none;
	}
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
			                    <li style="width:12%;">${status.index + 1 }</li>
			                    <li style="width:12%;">${consumer.userName }</li>
			                    <li style="width:12%;">${consumer.userAge }岁</li>
			                    <li style="width:12%;">孕${consumer.preganyWeek }周</li>
			                    <li style="width:12%;">${consumer.remoteType }</li>
			                    <li style="width:12%;"><img value="${consumer.consumerId }" class="play" src="${pageContext.request.contextPath}/media/image/button_play_hover.png"></li>
			                    <li class="timeNow" style="width:12%;">--</li>
			                    <li class="valueNow" style="width:12%;">--</li>
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
		                	<c:if test="${consumer.remoteType != '院内监护' }">
		                		<button type="button" class="btn btn-danger report-chat" userId="${consumer.userId }" 
			                    hospitalId="${hospitalInfo.id}" userName="${consumer.userName }" 
			                    orderId="${consumer.orderId }" consumerId="${consumer.consumerId }" 
			                    monitorTimes="${consumer.monitorTimes }">即时问诊</button>&nbsp;&nbsp;&nbsp;&nbsp;
		                	</c:if>
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
		</form>
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
		 	<iframe id="reportChatIframe" width="100%" height="450px" frameborder="0" src=""></iframe>
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