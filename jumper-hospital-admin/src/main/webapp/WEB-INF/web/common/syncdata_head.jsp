<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta http-equiv="X-UA-Compatible" content="IE=10"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">  
	<title>数据监护</title>
	<meta charset="utf-8">
	<c:choose>
			<c:when test="${params.cssType == 'jh'}">
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/syncdata_jh_css/head.css">
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/syncdata_jh_css/style.css">
			</c:when>
			<c:otherwise>
			    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/syncdata_css/head.css">
				<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/syncdata_css/style.css">
			</c:otherwise>
	</c:choose>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/highchart/highcharts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/highchart/highcharts-more.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/highchart/exporting.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/table_click.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/laydate/laydate.js"></script>
	 <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/jquery.nicescroll.min.js"></script>
	 <script type="text/javascript">
	 $(function(){
	 	$("body").niceScroll({ 
			   railpadding: { top:0, right: 2, left: 0, bottom:0 },
               cursorborder:"",
               cursoropacitymin: 0.2,
               cursoropacitymax: 0.2,
               cursorcolor:"#000000",
               zindex: "99",
               cursorwidth: "9px"
		 })
	 })
	 </script>
</head>