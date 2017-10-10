<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/media/css/daterangepicker-bs3.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/jquery.autocomplete.css?${v }" />
<style>
	.type_style{
		width:10%;
		height:55px;
		text-align:center;
	}
	.type_select{
		background-color: #5bc0de;
	}
</style>

<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.autocomplete.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/daterangepicker.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/report/finish.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/report/complete" method="POST" id="form">
		<input type="hidden" name="remoteType" id="input-type" value="${remoteType }" />
    	<div class="panel panel-default">
        	<div class="panel-body">
        		<div class="input-prepend input-group" style="width:500px;float:left">
					<span class="add-on input-group-addon">
						<i class="icon-calendar"></i>
					</span>
					<input type="text" style="width: 200px" name="startTime" id="startTime" placeholder="申请开始时间" class="form-control" value="${startTime }" />
					<span class="add-on input-group-addon">
						<i class="icon-calendar"></i>
					</span>
					<input type="text" style="width: 200px" name="endTime" id="endTime" placeholder="申请结束时间" class="form-control" value="${endTime }" />
				</div>
				<button type="button" class="btn btn-info search-btn" title="查询">查&nbsp;询</button>
				
	            <div class="col-lg-3" style="float:right">
	                <div class="input-group">
	                    <input type="text" class="form-control" name="searchKey" placeholder="姓名/电话" value="${searchKey }" />
	                    <span class="input-group-btn">
	                        <button class="btn btn-danger search-btn" type="button"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	            </div>
				<div class="clearFix"></div>
				<hr style="margin:20px 0px;">
				<table class="bordered" style="width:580px;">
				<thead> 
	                <tr>
	                	<td class="type_chg type_style <c:if test="${empty remoteType }">type_select</c:if>" >全部</td>
	                	<c:forEach var="rType" items="${remoteTypes }">
	                		<td class="type_chg type_style <c:if test="${remoteType == rType }">type_select</c:if>" value="${rType }">${rType }</td>
        				</c:forEach>
                    </tr>
                    </thead>
                </table> 
                    
	            <table class="bordered">
	                <thead> 
	                    <tr>
	                        <th style="width:10%;">用户姓名</th>
	                        <th style="width:10%;">报告时间</th>
	                        <th style="width:10%;">电话</th>
	                        <th style="width:10%;">年龄</th>
	                        <th>孕周</th>
	                        <th>监护类型</th>
	                        <th>申请时间</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result }" var="data">
	                	<tr>
		                    <td >${data.userName }</td>
		                    <td >${data.reportTime }</td>
		                    <td >${data.mobile }</td>
		                    <td >${data.age }</td>
		                    <td >${data.preganyWeek }</td>
		                    <td >${data.remoteType }</td>
		                    <td >${data.applyTime }</td>
		                    <td>
		                        <a role="button" class="btn btn-success btn-sm" title="查看报告" href="${data.file }" target="_blank"><i class="icon-folder-open-alt"></i> 查看报告</a>
		                    	<a role="button" class="btn btn-info btn-sm btn-detail" title="查看详情" href="${pageContext.request.contextPath}/report/detail?id=${data.id }"><i class="icon-folder-open-alt"></i> 查看详情</a>
		                    </td>
		                </tr>
	                </c:forEach>
	                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="8" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
	            </table>
	            <jsp:include page="../common/page.jsp"></jsp:include>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>