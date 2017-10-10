<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/style.css?${v }" />
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/classroom/chanel.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/classroom/chanel" method="POST" id="form">
    	<div class="panel panel-default">
        	<div class="panel-body">
        		<button type="button" class="btn btn-danger add-chanel"><i class="icon-plus"></i> 新增频道</button>
				<hr style="margin:20px 0px;">
	            <table class="bordered">
	                <thead>  
	                    <tr>
	                        <th>顺序</th>
	                        <th>频道名称</th>
	                        <th>订阅人数</th>
	                        <th>文章数量</th>
	                        <th>默认订阅</th>
	                        <th>状态</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result }" var="data" varStatus="status">
	                	<tr>
		                    <td>${status.index + 1 }</td>
		                    <td>${data.chanelName }</td>
		                    <td>${data.subNum }</td>
		                    <td>${data.newsNum }</td>
		                    <td>
		                    	<select class="form-control default-sub" flag="${data.id }">
		                    		<option value="0" <c:if test="${!data.isDefaultSub }">selected</c:if> >否</option>
		                    		<option value="1" <c:if test="${data.isDefaultSub }">selected</c:if>>是</option>
		                    	</select>
		                    </td>
		                    <td>
		                    	<select class="form-control chanel-state" flag="${data.id }">
		                    		<option value="0" <c:if test="${!data.state }">selected</c:if> >隐藏</option>
		                    		<option value="1" <c:if test="${data.state }">selected</c:if>>显示</option>
		                    	</select>
		                    </td>
                   			<td>
	                        	<button type="button" class="btn btn-warning btn-sm chanel-update" title="修改" value="${data.id }"><i class="icon-pencil"></i> 修改</button>
	                        	<button type="button" class="btn btn-danger btn-sm chanel-delete" title="删除" value="${data.id }"><i class="icon-trash"></i> 删除</button>
	                        	<button type="button" class="btn btn-success btn-sm chanel-top" title="置顶" value="${data.id }"><i class="icon-hand-up"></i> 置顶</button>
                        	</td>
		                </tr>
	                </c:forEach>
	                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="7" style="color:red">暂无记录</td>
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
<jsp:include page="/WEB-INF/web/classroom/class_dialog.jsp"></jsp:include>