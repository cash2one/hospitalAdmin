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
	<form action="${pageContext.request.contextPath}/auth/saveRoleAuth" method="POST" id="form">
		<input type="hidden" name="role_id" value="${role.id }" />
    	<div class="panel panel-default">
        	<div class="panel-body">
        		<ul style="margin-left: 100px;">
					<c:forEach items="${list }" var="auth" varStatus="status">
						<li>
							<label class='checkbox inline'>
								<input type='checkbox' name='auth_list' class="parent_${status.index }" value='${auth.id }' onclick="checkBoxClick(this)"
									<c:forEach items="${role.authorities }" var="cur">
										<c:if test="${cur.id == auth.id }">checked</c:if>
									</c:forEach>
								 />${auth.description }
							</label>
							<ul>
								<c:forEach items="${auth.childs }" var="child">
									<li><label class='checkbox inline'>
										<input type='checkbox' name='auth_list' class="child_${status.index }" value='${child.id }' onclick="checkBoxClick(this)"
											<c:forEach items="${role.authorities }" var="cur">
												<c:if test="${cur.id == child.id }">checked</c:if>
											</c:forEach>
										 />${child.description }
									</label></li>
								</c:forEach>
							</ul>
						</li>
					</c:forEach>
				</ul>
				<div style="text-align:center">
					<button class="btn btn-info" type="submit">保存</button>
					<button class="btn btn-danger" type="button" onclick="back();">返回</button>
				</div>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/admin/admin_dialog.jsp"></jsp:include>