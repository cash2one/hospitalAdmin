<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<div class="panel panel-default">
       	<div class="panel-body" style="width: 60%;text-align: center;margin: auto;">
       		<div class="form-group form-inline" style="text-align:left;">
		  		<c:if test="${!empty news.fromLogoUrl }">
		  			<img src="${filePath }${news.fromLogoUrl }" style="width:60px;height:60px">
		  		</c:if>&nbsp;&nbsp;&nbsp;&nbsp;
		  		<span>${news.sourceFrom }</span>&nbsp;&nbsp;&nbsp;&nbsp;
		  		<span><fmt:formatDate value="${news.publishTime }" type="both" /></span>
		  	</div>
		  	<div class="form-group form-inline" style="font-size: 18px;">
		  		${news.title }
		  	</div>
		  	<div class="form-group form-inline">
		  		${news.content }
		  	</div>
		  	<div class="form-group form-inline" style="text-align: right;">
		  		<span>阅读：${news.clicks }</span>
		  	</div>
        </div>
    </div>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>