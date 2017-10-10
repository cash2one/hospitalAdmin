<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath }/media/js/remote.js"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
   <div class="panel panel-default">
        <div class="panel-body">
        	<input type="hidden" id="hospitalId" value="${hospitalId }" />
            <h4 class="sub-header">正在实时监测中</h4>
            <ul>
            	<c:forEach var="live" items="${screen }" varStatus="status">
            		<li>
	                    <div class="panel panel-default">
	                        <div class="panel-body" style="padding:0;color:black">
	                            <div class="left-label">${status.index + 1 }</div>
	                            <div class="user-info">
	                                <div class="base-info">
	                                    <span>${live.userName }</span>
	                                    <span>${live.age }岁</span>
	                                    <span>孕${live.pregancyWeek }周</span>
	                                </div>
	                                <div class="base-info">
	                                    <span>开始时间：${live.startTime }</span>
	                                    <span>监测时长：${live.onlineTime }</span>
	                                </div>
	                            </div>
	                            <%--<div class="sx-close">
                                    <a href="#" title="结束监控" class="end-remote" value="${live.userId }"><i class="icon-remove icon-large"></i></a>
                                </div>
	                            --%><div class="right-operate">
	                                <button type="button" class="btn btn-danger btn-sm end-remote" title="结束监测" value="${live.userId }">结束监测</button>
	                                <button type="button" class="btn btn-info btn-sm test-again" title="重测" value="${live.userId }">重测</button>
	                                <button type="button" class="btn btn-success btn-sm view-user" title="查看" value="${live.userId }">查看</button>
	                            </div>
	                        </div>
	                    </div>
	                </li>
            	</c:forEach>
            </ul>
        </div>
    </div>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>