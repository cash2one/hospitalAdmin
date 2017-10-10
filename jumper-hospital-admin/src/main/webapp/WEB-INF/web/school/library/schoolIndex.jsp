<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<style>
/* 注释掉不然显示不全
html{
overflow-y: hidden;
} */
</style>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-12" style="margin-top: -20px;">
	<iframe src="${url }" class="aaa" style="width: 100%; border: none;"></iframe>
</div>
<script>
$(".aaa").height($(window).height()-31);
$(".sidebar-offcanvas").remove();
$(".container-fluid").css("padding",0)
$(window).resize(function(){
	$(".aaa").height($(window).height()-31);
});
//兼容火狐浏览器
$(function(){
	if(navigator.userAgent.indexOf('Firefox')){
	   $(".aaa").height($(window).height()+680);
   }
}); 

</script>
