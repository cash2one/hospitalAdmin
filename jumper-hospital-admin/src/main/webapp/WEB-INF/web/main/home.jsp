<%@ page language="java" import="java.util.*,java.sql.Timestamp" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页面</title>
<style>
/* *{margin:0 !important; padding:0 !important;} */
</style>
</head>
<body>
<div style="overflow: scroll;margin:0 auto; " id="mainFrameBox">
	<iframe id="mainFrame" frameborder="0" src="${pageContext.request.contextPath}/report/audit" style="margin:0 auto;overflow-x:auto;"></iframe>
</div>
</body>
<script>
$(function(){
	console.log($(window).width());
	console.log($(window).height());
	   $("#mainFrame").width($(window).width()-20);
	   $("#mainFrame").height($(window).height()-90);
	   if(navigator.userAgent.indexOf('Firefox')){
		   $("#mainFrame").height($(window).height()+680);
	   }
	});
</script>

</html>