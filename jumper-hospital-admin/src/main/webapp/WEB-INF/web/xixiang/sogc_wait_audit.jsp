<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/media/css/x_report.css?${v }" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/xixiang/x_chart.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/xixiang/x_report.js?${v }"></script>
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
	.sogc_info tr{
		height:40px;
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
			                    <li style="width:10%;">${status.index + 1 }</li>
			                    <li style="width:10%;">${consumer.userName }</li>
			                    <li style="width:10%;">${consumer.userAge }岁</li>
			                    <li style="width:8%;">孕${consumer.preganyWeek }周</li>
			                    <li style="width:12%;">手机：${consumer.mobile }</li>
			                    <li style="width:10%;">${consumer.remoteType }</li>
			                    <li style="width:10%;"><img value="${consumer.consumerId }" class="play" src="${pageContext.request.contextPath}/media/image/button_play_hover.png"></li>
			                    <li class="timeNow" style="width:10%;">--</li>
			                    <li class="valueNow" style="width:10%;">--</li>
			                </ul>
			            </div>
			            <div class="clearFix"></div>
			            <!--<table class="table" style="margin-bottom:0px">
			            	<tr>
			            		<td><span>监护开始时间：${consumer.heart.time }</span></td>
			            		<td style="text-align:center"><span>监护结束时间：${consumer.heart.endTime }</span></td>
			            		<td style="text-align:right"><span>监护时长：${consumer.heart.minute }</span></td>
			            	</tr>
			            </table>
			            --><table class="table" style="margin-bottom:0px">
			            	<tr>
			            		<td>
			            			<table class="sogc_info">
			            				<tr>
			            					<td colspan="2"><span>监护开始时间：${consumer.heart.time }</span></td>
			            				</tr>
			            				<tr>
			            					<td colspan="2"><span>监护结束时间：${consumer.heart.endTime }</span></td>
			            				</tr>
			            				<tr>
			            					<td colspan="2"><span>监护时长：${consumer.heart.minute }</span></td>
			            				</tr>
			            				<tr>
			            					<td>备注：</td>
			            					<td>
			            						<textarea rows="4" cols="50" class="descContent" name="remark"></textarea>
			            					</td>
			            				</tr>
			            				<tr>
			            					<td colspan="2">
			            						监护医生：${admin.name }
			            					</td>
			            				</tr>
			            				<tr>
			            					<td colspan="2">
			            						日期：${consumer.heart.time }
			            					</td>
			            				</tr>
			            			</table>
			            			
			            		</td>
			            		<td style="width:55%">
			            			<table class="sogc" style="text-align:left">
			            				<tr>
			            					<td colspan="2">胎心基线</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="1-2-1" />正常
			       									</label>
			            						</div>
			            						(110～160bpm)
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="1-3-1" />胎心过速(>160bpm)
			       									</label>
			            						</div>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="1-3-2" />胎心过缓(<110bpm)
			       									</label>
			            						</div>
			            						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;不伴基线变异缺失
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="1-4-1" />胎心过缓(<110bpm)
			       									</label>
			            						</div>
			            						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;伴基线变异缺失
			            					</td>
			            				</tr>
			            				<tr>
			            					<td colspan="2">基线变异</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="2-2-1" />中度
			       									</label>
			            						</div>
			            						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(6～25bpm)
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="2-3-1" />变异缺失(0bpm)
			       									</label>
			            						</div>
			            						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;不伴反复出现的晚期减速
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="2-3-2" />微小变异(<5bpm)
			       									</label>
			            						</div>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="2-3-3" />显著变异(>25bpm)
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="2-4-1" />变异缺失(0bpm)
			       									</label>
			            						</div>
			            						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;伴胎心过缓
			            						<br>
			            						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;或反复出现变异减速
			            						<br>
			            						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;或晚期减速
			            					</td>
			            				</tr>
			            				<tr>
			            					<td colspan="2">胎心加速</td>
			            					<td>
			       								<label class="checkbox-inline">
												  <input type="checkbox" name="result_item" value="3-2-1"> 有
												</label>
												<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="3-2-2"> 无
												</label>
			            					</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="3-3-1"> 刺激胎儿后仍缺失
												</label>
			            					</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="3-4-1"> 无
												</label>
			            					</td>
			            				</tr>
			            				<tr>
			            					<td rowspan="3">减速</td>
			            					<td>早期减速</td>
			            					<td>
			            						<label class="checkbox-inline">
												  <input type="checkbox" name="result_item" value="4-2-1"> 有
												</label>
												<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="4-2-2"> 无
												</label>
			            					</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="4-3-1"> 无
												</label>
			            					</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="4-4-1"> 无
												</label>
			            					</td>
			            				</tr>
			            				<tr>
			            					<td>变异减速</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="5-2-1"> 无
												</label>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="5-3-1" />反复出现伴微小变异或中度变异
			       									</label>
			            						</div>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="5-3-2" />延长减速(>2min但<10min)
			       									</label>
			            						</div>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="5-3-3" />非特异性变异减速
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="5-4-1"> 反复出现伴基线变异缺失
												</label>
			            					</td>
			            				</tr>
			            				<tr>
			            					<td>晚期减速</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="6-2-1"> 无
												</label>
			            					</td>
			            					<td>
			            						<div class="checkbox">
			            							<label style="font-weight:normal;">
			          									<input type="checkbox" name="result_item" value="6-3-1" />反复出现伴基线中度变异
			       									</label>
			            						</div>
			            					</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="6-4-1"> 反复出现伴基线变异缺失
												</label>
			            					</td>
			            				</tr>
			            				<tr>
			            					<td colspan="2">正弦曲线</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="7-2-1"> 无
												</label>
			            					</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="7-3-1"> 无
												</label>
			            					</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="7-4-1"> 有
												</label>
			            					</td>
			            				</tr>
			            				<tr>
			            					<td colspan="2">胎心监护结果</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="8-2-1"> 正常(I级)
												</label>
			            					</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="8-3-1"> 可疑(II级)
												</label>
			            					</td>
			            					<td>
			            						<label class="checkbox-inline">
												   <input type="checkbox" name="result_item" value="8-4-1"> 异常(III级)
												</label>
			            					</td>
			            				</tr>
			            			</table>
			            		</td>
			            	</tr>
			            </table>
			            <div class="table" style="padding-left: 8px;">
			            	走纸速度：
			            	<select class="speed">
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