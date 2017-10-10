<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/school/schedule.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/schedule/editCourse" method="POST" id="form">
		<input type="hidden" id="date_value" name="date" value="${dateString }" />
		<input type="hidden" id="choose_course_id" value="" />
		<div class="panel panel-default">
        	<div class="panel-body">
        		<div><a href="javascript:history.go(-1);">返回列表</a></div>
        		<div>
        			<div class="well" style="width:40%;margin-left: auto;margin-right: auto;">
        				<div style="text-align:center"><fmt:formatDate value="${date}" pattern="yyyy年MM月dd日 "/>课程表</div>
        				<div style="margin:20px 0px 20px 60px">
        					<div>上课日期：<fmt:formatDate value="${date}" pattern="yyyy年MM月dd日 "/></div>
        					<div style="margin:16px 0px;">
        						上课时间：
        						<div style="display:inline;">
        							<input type="text" class="form-control start-h" style="width:60px;height:24px;display:inline"></input>
        							&nbsp;:&nbsp;
        							<input type="text" class="form-control start-m" style="width:60px;height:24px;display:inline"></input>
        							&nbsp;-&nbsp;
        							<input type="text" class="form-control end-h" style="width:60px;height:24px;display:inline"></input>
        							&nbsp;:&nbsp;
        							<input type="text" class="form-control end-m" style="width:60px;height:24px;display:inline"></input>
								</div>
        					</div>
        					<div>
        						选择课程：<button type="button" id="choose_course" class="btn btn-default btn-sm">选择课程</button>&nbsp;&nbsp;
        						<span id="plan-course-name" style="display:none"></span>
        					</div>
        				</div>
        				<div style="text-align:center">
        					<button type="button" class="btn btn-danger" id="add_course_detail" style="width:60%;">添加课程</button>
        				</div>
        			</div>
        		</div>
        	</div>
        </div>
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
        			<input type="text" class="form-control" name="key" id="searchKey" value="${key }" style="width:160px;display:inline" />&nbsp;&nbsp;&nbsp;&nbsp;
        			<button class="btn btn-success" type="submit">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
        			<c:set var="isExport" value="0"></c:set>
        			<c:if test="${data != null && fn:length(data) > 0 }">
	                	<c:forEach var="plan" items="${data }">
	                		<c:if test="${plan.courseState == '预约中' || plan.courseState == '已过期' }">
	                			<c:set var="isExport" value="1"></c:set>
	                		</c:if>
	                	</c:forEach>
	                </c:if>
        			<c:if test="${isExport == 1}">
        				<button class="btn btn-danger" type="button" id="export-pdf" target="_black">导出今日课程表</button>
        			</c:if>
        		</div>
	            <div class="clearFix"></div>
	            <hr/>
				<!-- 线下课程 -->
				<table class="bordered">
	                <thead>  
	                    <tr>
	                        <th>课程编号</th>
	                        <th>课程名称</th>
	                        <th>授课医师</th>
	                        <th>费用</th>
	                        <th>上课时间</th>
	                        <th>可预约人数</th>
	                        <th>签到码</th>
	                        <th>已预约人数</th>
	                        <th>已签到人数</th>
	                        <th>课程状态</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:if test="${data != null && fn:length(data) > 0 }">
	                	<c:forEach var="plan" items="${data }">
	                		<tr>
			                    <td>${plan.courseNo }</td>
			                    <td>${plan.courseName }</td>
			                    <td>${plan.courseDoctor }</td>
			                    <td>${plan.courseCost }元</td>
			                    <td>${dateString } ${plan.startTime } - ${plan.endTime }</td>
			                    <td>${plan.appointCount }</td>
			                    <td style="text-align: center"><img style="width:60px;height:60px" src="${filePath }${plan.courseQrUrl }"></td>
			                    <td>${plan.hasAppoint }</td>
			                    <td>${plan.hasSign }</td>
			                    <td>${plan.courseState }</td>
			                    <td>
			                    	<a role="button" class="btn btn-info btn-sm" title="预约列表" href="${pageContext.request.contextPath}/schedule/appoints?detailId=${plan.id }" >预约列表</a>
			                        <a role="button" class="btn btn-success btn-sm" title="下载签到码" href="${pageContext.request.contextPath}/schedule/downQR?id=${plan.id }">下载签到码</a>
			                    	<a role="button" class="btn btn-danger btn-sm" title="查看详情" href="${pageContext.request.contextPath}/school/offEdit?recordId=${plan.courseId }&libraryType=0&operateType=0">查看详情</a>
			                 		<c:if test="${plan.courseState == '预约中' }">
			                    		<a role="button" class="btn btn-primary btn-sm course-cancel" title="取消课程" href="#" value="${plan.id }" value2="${plan.hasAppoint}">取消课程</a>
			                    	</c:if>
			                    </td>
			                     <%--   
			                    <a role="button" class="btn btn-primary btn-sm course-cancel" title="取消课程" href="#" value="${plan.id }" value2="${plan.hasAppoint}">取消课程</a>
			                  --%>
			                </tr>
	                	</c:forEach>
	                </c:if>
	                <c:if test="${data == null || fn:length(data) <= 0 }">
			      		<tr>
			      			<td colspan="12" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
	            </table>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/school/school_dialog.jsp"></jsp:include>