<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/menu.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/daterangepicker-bs3.css">
<!DOCTYPE html>
<html>
<head>
<title>分娩結局</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
html {
	overflow-x: scroll;
}

body {
	min-width: 1660px;
	overflow-y: hidden;
}

.message-dialog {
	width: 800px;
	height: 500px;
}

* {
	padding: 0;
	margin: 0;
}

.panel {
	margin-bottom: 0px;
}
</style>
</head>
<body style="background: #F0F0F0;">
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right"
			style="margin-top: 20px;">
			<!-- 档案管理 -->
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">

				<ul class="page-sidebar-menu">
					<li class="active"><a
											href="${pageContext.request.contextPath}/pregnant/archiveIndex">
												<i class="icon-plus-sign-alt"></i>&nbsp; <span class="title">档案管理</span>
										</a>
										</li>
				</ul>

			</div>

			<!-- 内容页 -->
			<div class="col-xs-12 col-sm-9">
				<div class="panel">
					<div class="gw_rote">
						<span class="float-left" style="font-size:16px;">档案管理 >
							档案详情 > <a style=" color:#ff7ea5">分娩結局</a>
						</span>
					</div>
					<div class="panel-body">
						<div class="panel panel-default" style="margin:15px 0px;">
							<div class="panel-body">
								<table class="gw_xx">
									<tbody>
										<tr>
											<td colspan="4">姓名： ${userBaseInfo.name }</td>
											<td colspan="4">联系电话：${userBaseInfo.mobile }</td>
											<td colspan="4">末次月经：${userBaseInfo.lastPeriod }</td>
											<td colspan="4">预产期：  <fmt:formatDate value="${userBaseInfo.pregnantDate }" pattern="yyyy-MM-dd"/></td>
										</tr>
										<tr>
											<td colspan="4">当前孕周：${pregnantWeek }周 ${pregnantDay }天</td>
											<td colspan="4">状态：
												<c:if test="${userBaseInfo.pregnancyState==0 }">怀孕中</c:if>
												<c:if test="${userBaseInfo.pregnancyState==1 }">已分娩</c:if>
											</td>
											<td colspan="4">分娩日期：${userBaseInfo.deliveryTime }</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="panel panel-default float-left"
							style="margin:15px 0px;width: 100%;">
							<div class=" float-left" style="width:100%;">
								<%@ include file="/WEB-INF/web/pregnantEnd/fmjj_url.jsp"%>
							</div>
							<div class="gw_noimg" style="margin-bottom:50px;">
								<img src="${pageContext.request.contextPath}/media/image/gw_tj.jpg" width="90%">
							</div>
							<div class="gw_btn" style="margin-bottom:50px;">
								<button type="button" class="btn btn-success" style="margin-left:35px;" id="add">
										<i class="icon-plus"></i>添加分娩結局
								</button>
							</div>
						</div>
					</div>
				</div>
</body>
<script type="text/javascript">
$(function(){
		$("#add").click(function(){
			window.location.href="${pageContext.request.contextPath}/PregnantEnd/toAddPregnantEnd?userId=${userId}&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}";
		});
});

</script>
</html>
