<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<link href="${pageContext.request.contextPath}/media/css/bootstrap.css?${v }" rel="stylesheet">
<link href="${pageContext.request.contextPath}/media/css/offcanvas.css?${v }" rel="stylesheet">
<link href="${pageContext.request.contextPath}/media/font-awesome/css/font-awesome.min.css?${v }" rel="stylesheet">
<link href="${pageContext.request.contextPath}/media/css/menu.css?${v }" rel="stylesheet">
<link href="${pageContext.request.contextPath}/media/css/remote.css?${v }" rel="stylesheet">
<link href="${pageContext.request.contextPath}/media/css/style.css?${v }" rel="stylesheet">
<link href="${pageContext.request.contextPath}/media/css/bootstrap-fileupload.css?${v }" rel="stylesheet">
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/code/findUserVerifiedCode" method="POST" id="form">
		<input type="hidden" id="mobile" value="${mobile}" />
    	<div class="panel panel-default">
        	<div class="panel-body">
	            <div class="col-lg-3" style="float:right">
	                <div class="input-group">
	                    <input type="text" class="form-control" id="phone" name="mobile" placeholder="手机号码" value="${mobile}" />
	                    <span class="input-group-btn">
	                        <button class="btn btn-danger" type="submit"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	            </div>
				<div class="clearFix"></div>
				
				<br>
	            <table class="bordered">
	                <thead>  
	                    <tr>
	                        <th>手机号码</th>
	                        <th>验证码</th>
	                        <th>发送时间</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${list}" var="data" varStatus="v">
	                	<tr>
		                    <td>${data[1]}</td>
		                    <td>${data[3]}</td>
		                    <td><fmt:formatDate value="${data[2]}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		                </tr>
	                </c:forEach>
	                <c:if test="${list == null}">
			      		<tr>
			      			<td colspan="6" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
	            </table>
	           	<div>
	           		验证码的目的是为了确保用户是用的是自己的手机，所以</br>
				    &nbsp;&nbsp;&nbsp;&nbsp;1   尽量使用正常渠道获取验证码。即在手机App点击获取验证码进行获取，因为短信可能会有一定的延迟，尽量多尝试几次（建议三次）。</br>
				    &nbsp;&nbsp;&nbsp;&nbsp;2   请反复确认该手机号确实是用户的手机号。 可以尝试给用户发短信或者打电话的方式验证。</br>
				    &nbsp;&nbsp;&nbsp;&nbsp;3   对于频繁使用该功能的用户，可能会被收回访问权限。后台有日志记录。
	           	</div>
	        </div>
	    </div>
	</form>
</div>

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>