<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/school/schedule.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/schedule/appoints" method="POST" id="form">
		<input type="hidden" id="detailId" name="detailId" value="${detailId }" />
    	<div class="panel panel-default">
        	<div class="panel-body">
        		<div><a href="${pageContext.request.contextPath}/schedule/offLine">返回列表</a></div>
        		<div style="display:inline;float:right">
        			<span>预约状态</span>
        			<select class="form-control" name="appointState" style="width:160px;display:inline">
        				<option value="">全部</option>
        				<c:forEach var="state" items="${appointStateAll }">
        					<option value="${state }" <c:if test="${state == appointState }">selected</c:if>>${state }</option>
        				</c:forEach>
        			</select>&nbsp;&nbsp;&nbsp;&nbsp;
        			<span>签到状态</span>
        			<select class="form-control" name="signState" style="width:160px;display:inline">
        				<option value="">全部</option>
        				<c:forEach var="state" items="${signStateAll }">
        					<option value="${state }" <c:if test="${state == signState }">selected</c:if>>${state }</option>
        				</c:forEach>
        			</select>&nbsp;&nbsp;&nbsp;&nbsp;
        			<span>预约单号\学员姓名\手机号码</span>
        			<input type="text" class="form-control" name="searchKey" id="searchKey" value="${searchKey }" style="width:160px;display:inline" />&nbsp;&nbsp;&nbsp;&nbsp;
        			<button class="btn btn-success" type="submit">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
        		</div>
        		<div class="clearFix"></div>
        		<div style="margin-top: 20px;">
        			<div style="width:50%;display:inline">
        				批量操作：
        				<c:if test="${detail.courseCost > 0 }">
        					<a href="#" id="batch_pay">缴费</a>&nbsp;|&nbsp;
        					<a href="#" id="batch_back">退费</a>&nbsp;|&nbsp;
        				</c:if>
        				<a href="#" id="batch_push">推送消息</a>
        			</div>
        			<div style="width:50%;display:inline">
        				<c:if test="${data != null && fn:length(data.result) > 0 }">
	        				<button class="btn btn-danger" type="button" id="export-excel" style="float:right">导出Excel表</button>
	        			</c:if>
        			</div>
        		</div>
	            <div class="clearFix"></div>
	            <hr/>
				<!-- 线下课程 -->
				<table class="bordered">
	                <thead>  
	                    <tr>
	                        <th></th>
	                        <th>预约单号</th>
	                        <th>学员姓名</th>
	                        <th>孕周</th>
	                        <th>手机号码</th>
	                    <!--   <th>上课时间</th> -->  
	                        <th>预约时间</th>
	                        <th>预约状态</th>
	                        <th>签到时间</th>
	                        <th>签到状态</th>
	                        <th>缴费状态</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:if test="${data != null && fn:length(data.result) > 0 }">
	                	<c:forEach var="appoint" items="${data.result }" varStatus="vs">
	                		<tr>
	                			<td><input type="checkbox" name="isCheck" value="${appoint.id }" value2="${appoint.payState}" valueCount="${costArray[vs.index]}"  u="${appoint.appointUserId }" /></td>
			                    <td>${appoint.appointOrderId }</td>
			                    <td>${appoint.appointUserName }</td>
			                    <td>${appoint.userWeek }</td>
			                    <td>${appoint.appointUserPhone }</td>
			                    <td><fmt:formatDate value="${appoint.addTime }" type="both"/></td>
			                    <td>${appoint.appointState }</td>
			                    <td><fmt:formatDate value="${appoint.signTime }" type="both"/></td>
			                    <td>${appoint.signState }</td>
			                    <td>${appoint.payState }</td>
			                    <td>
			                    <!-- 如果是收费课程，则有退费和缴费按钮，免费课程则没有 -->
			                    <c:if test="${costArray[vs.index]==1}">
			                    	<a role="button" class="btn btn-info btn-sm pay-money"   <c:if test="${appoint.payState=='已缴费'}">style="display:none"</c:if>  value="${appoint.id }" value2="${appoint.payState}" title="缴费" href="#" >缴费</a>
			                        <a role="button" class="btn btn-success btn-sm back-money"  <c:if test="${appoint.payState=='已退费'||appoint.payState=='未缴费'}">style="display:none"</c:if> value="${appoint.id }" value2="${appoint.payState}"  title="退费" href="#">退费</a>
			                    </c:if>
			                    	<a role="button" class="btn btn-danger btn-sm push-message" value="${appoint.appointUserId }" title="推送消息" href="#">推送消息</a>
			                    </td>
			                </tr>
	                	</c:forEach>
	                </c:if>
	                <c:if test="${data == null || fn:length(data.result) <= 0 }">
			      		<tr>
			      			<td colspan="11" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
	            </table>
	            <div class="alert alert-danger" role="alert" style="float: right;padding: 6px;">
					总预约：${result.appoint == null ? 0 : result.appoint } | 总签到：${result.sign == null ? 0 : result.sign }
			    </div>
	            <c:if test="${data != null && fn:length(data.result) > 0 }">
		      		<jsp:include page="../../common/page.jsp"></jsp:include>
		      	</c:if>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/school/school_dialog.jsp"></jsp:include>