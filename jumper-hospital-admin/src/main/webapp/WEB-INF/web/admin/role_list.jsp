<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/media/css/daterangepicker-bs3.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/jquery.autocomplete.css?${v }" />

<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.autocomplete.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/auth.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/auth/roleList" method="POST" id="form">
    	<div class="panel panel-default">
        	<div class="panel-body">
        		<button type="button" class="btn btn-info add-role"><i class="icon-plus"></i> 新增角色</button>
	            <div class="col-lg-3" style="float:right">
	                <div class="input-group">
	                    <input type="text" class="form-control" name="searchKey" placeholder="角色名称" value="${username }" />
	                    <span class="input-group-btn">
	                        <button class="btn btn-danger" type="submit"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	            </div>
				<hr style="margin:20px 0px;">
	            <table class="bordered">
	                <thead>  
	                    <tr>
	                        <th>角色名称</th>
	                        <th>角色描述</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result }" var="data">
	                	<tr>
		                    <td>${data.name }</td>
		                    <td>${data.description }</td>
		                    <td>
		                        <button type="button" class="btn btn-danger btn-sm edit-role" title="编辑" value="${data.id }|${data.name }|${data.description }"><i class="icon-pencil"></i> 编辑</button>
		                        <a type="button" class="btn btn-warning btn-sm" title="分配权限" href="${pageContext.request.contextPath}/auth/roleAuthority?role=${data.id }" value="${data.id }"><i class="icon-wrench"></i> 分配权限</a>
		                    </td>
		                </tr>
	                </c:forEach>
	                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="3" style="color:red">暂无记录</td>
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
<jsp:include page="/WEB-INF/web/admin/admin_dialog.jsp"></jsp:include>