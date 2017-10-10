<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/media/css/daterangepicker-bs3.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/jquery.autocomplete.css?${v }" />

<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.autocomplete.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/subordinate.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/hospital/hospitalSubordinate" method="POST" id="form"  class="form-inline">
    	<div class="panel panel-default">
        	<div class="panel-body">
        		<button type="button" class="btn btn-info add-subordinate"><i class="icon-plus"></i> 新增从属机构</button>
				<hr style="margin:20px 0px;">
	            <table class="bordered">
	                <thead>  
	                    <tr>
	                        <th>序列</th>
	                        <th>医院名称</th>
	                        <th>是否从属</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result}" var="data">
	                	<tr>
		                    <td>${data.subordinateId.id }</td>
		                    <td>
		                    	${data.subordinateId.name }
		                    </td>
		                    <td>
		                   		 已从属
		                    </td>
		                    <td>
		                        <button type="button" class="btn btn-info btn-sm disable" title="取消从属" value="${data.id}"><i class="icon-pencil"></i> 取消从属</button>
		                    </td>
		                </tr>
	                </c:forEach>
	                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="10" style="color:red">暂无记录</td>
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
<jsp:include page="/WEB-INF/web/hospital/subordinate_dialog.jsp"></jsp:include>