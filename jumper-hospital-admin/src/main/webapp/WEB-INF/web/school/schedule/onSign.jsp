<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/school/schedule.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/schedule/signDetail" method="POST" id="form">
		<input type="hidden" id="courseId" name="id" value="${id }" />
    	<div class="panel panel-default">
        	<div class="panel-body">
        		<div><a href="javascript:history.go(-1);">返回列表</a></div>
        		<div style="display:inline;float:right">
        			<span>学员姓名/手机号码</span>
        			<input type="text" class="form-control" name="key" id="searchKey" value="${key }" style="width:160px;display:inline" />&nbsp;&nbsp;&nbsp;&nbsp;
        			<button class="btn btn-success" type="submit">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
        		</div>
	            <div class="clearFix"></div>
        		<div style="margin-top: 20px;">
        			<div style="width:50%;display:inline">
        				批量操作：
        				<a href="#" id="batch_push">推送消息</a>
        			</div>
        			<div style="width:50%;display:inline">
        				<c:if test="${data != null && fn:length(data.result) > 0 }">
	        				<button class="btn btn-danger" type="button" id="export-online-excel" style="float:right">导出Excel表</button>
	        			</c:if>
        			</div>
        		</div>
	            <hr/>
				<!-- 线下课程 -->
				<table class="bordered">
	                <thead>  
	                    <tr>
	                        <th></th>
	                        <th>学员姓名</th>
	                        <th>孕周</th>
	                        <th>手机号码</th>
	                        <th>课程编号</th>
	                        <th>课程名称</th>
	                        <th>授课医师</th>
	                        <th>签到时间</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:if test="${data != null && data.result != null && data.totalCount > 0 }">
	                	<c:forEach var="sign" items="${data.result }">
	                		<tr>
	                			<td><input type="checkbox" name="isCheck" u="${sign.signUserId }" /></td>
			                    <td>${sign.signUserName }</td>
			                    <td>${sign.userWeek }</td>
			                    <td>${sign.signUserPhone }</td>
			                    <td>${course.courseNo }</td>
			                    <td>${course.courseName }</td>
			                    <td>${course.courseDoctor }</td>
			                    <td><fmt:formatDate value="${sign.signTime }" type="both"/></td>
			                    <td>
			                    	<a role="button" class="btn btn-info btn-sm push-message" title="推送消息" href="#" value="${sign.signUserId }" >推送消息</a>
			                    </td>
			                </tr>
	                	</c:forEach>
	                </c:if>
	                <c:if test="${data == null || data.result == null || data.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="9" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
	            </table>
	            <div class="alert alert-danger" role="alert" style="float: right;padding: 6px;">
					总签到：${count }
			    </div>
	            <c:if test="${data != null && data.result != null && data.totalCount > 0 }">
		      		<jsp:include page="../../common/page.jsp"></jsp:include>
		      	</c:if>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/school/school_dialog.jsp"></jsp:include>