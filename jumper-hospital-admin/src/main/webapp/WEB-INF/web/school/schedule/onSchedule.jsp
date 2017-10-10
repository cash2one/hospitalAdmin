<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/school/schedule.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/schedule/onLine" method="POST" id="form">
    	<div class="panel panel-default">
        	<div class="panel-body">
        		<div style="display:inline;float:right">
        			<span>课程状态</span>
        			<select class="form-control" name="state" style="width:160px;display:inline">
        				<option value="">全部</option>
        				<c:forEach var="state" items="${courseState }">
        					<option value="${state }" <c:if test="${state == currentState }">selected</c:if>>${state }</option>
        				</c:forEach>
        			</select>&nbsp;&nbsp;&nbsp;&nbsp;
        			<span>课程编码\课程名称\授课医师</span>
        			<input type="text" class="form-control" name="searchKey" id="searchKey" value="${searchKey }" style="width:160px;display:inline" />&nbsp;&nbsp;&nbsp;&nbsp;
        			<button class="btn btn-success" type="submit">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
        		</div>
	            <div class="clearFix"></div>
	            <hr/>
				<!-- 网络课程 -->
				<table class="bordered">
	                <thead>  
	                    <tr>
	                        <th>课程编号</th>
	                        <th>课程名称</th>
	                        <th>授课医师</th>
	                        <th>医师职称</th>
	                        <th>签到人数</th>
	                        <th>分享次数</th>
	                        <th>课程状态</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:if test="${onLine != null && onLine.result != null && onLine.totalCount > 0 }">
	                	<c:forEach var="on" items="${onLine.result }">
	                		<tr>
			                    <td>${on.courseNo }</td>
			                    <td>${on.courseName }</td>
			                    <td>${on.courseDoctor }</td>
			                    <td>${on.courseDoctorTitle }</td>
			                    <td>${on.signCount }</td>
			                    <td>${on.shareCount }</td>
			                    <td>${on.courseState }</td>
			                    <td>
			                        <a role="button" class="btn btn-success btn-sm" title="签到详情" href="${pageContext.request.contextPath}/schedule/signDetail?id=${on.id }" > 签到详情</a>
			                        <c:if test="${on.courseState == '已上架' }">
			                        	<a role="button" class="btn btn-danger btn-sm course-operate-down" title="下架" href="#" value="${on.id }"> 下架</a>	
			                        </c:if>
			                    	<c:if test="${on.courseState == '已下架' }">
			                        	<a role="button" class="btn btn-danger btn-sm course-operate-up" title="上架" href="#" value="${on.id }"> 上架</a>	
			                        </c:if>
			                    </td>
			                </tr>
	                	</c:forEach>
	                </c:if>
	                <c:if test="${onLine == null || onLine.result == null || onLine.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="8" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
	            </table>
	            <c:if test="${onLine != null && onLine.result != null && onLine.totalCount > 0 }">
	            	<jsp:include page="../../common/page.jsp"></jsp:include>
	            </c:if>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>