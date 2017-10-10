<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/media/css/daterangepicker-bs3.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/jquery.autocomplete.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/style_trail.css?${v }" />

<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.autocomplete.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/daterangepicker.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/refund.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/user/payRefundAnnal" method="POST" id="form">
        <input type="hidden" id="type" name="type" value="${type }" />
        <input type="hidden" id="remoteType" name="remoteType" value="${remoteType }" />
        <input type="hidden" id="expireType" name="expireType" value="${expireType }" />  
    	<div class="panel panel-default">
    	<div class="gw_rote"> <span class="float-left" style="font-size:16px;">订单管理 > <a style=" color:#ff7ea5">退费列表</a></span></div>
			<div class="panel-body">
				<hr style="margin:20px 0px;">
	            <table class="bordered" style="table-layout:fixed;">
	                <thead>  
	                    <tr>
	                        <th style="width:13%">订单号</th>
	                        <th>孕妇姓名</th>
	                        <th>孕妇电话</th>
	                        <th>服务费用</th>
	                        <th>剩余服务次数</th>
	                        <th>服务类型</th>
	                        <th>订单生成日期</th>
	                        <th style=" width:30%;">退费原因</th>
	                        <th>操作</th>
	                        <th>退费状态</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result }" var="data">
	                	<tr>
		                    <td>${data.monitorOrder.orderId }</td>
		                    <td>${data.monitorOrder.monitorUserId.realName }</td>
		                    <td>${data.monitorOrder.monitorUserId.mobile }</td>
		                    <td>${data.monitorOrder.money } 元/ ${data.monitorOrder.monitorCount }次</td>
		                    <td>${data.monitorOrder.leftCount }</td>
		                    <td>${data.monitorOrder.remoteType }</td>
		                    <td><fmt:formatDate value="${data.monitorOrder.addTime }" type="both"/></td>
		                    <td style="overflow: hidden;word-break: break-all">${data.refundOrigin }</td>
		                    <td>
		                    	<c:if test="${ data.orderId.handleState == 1 }">
		                    		<button type="button" class="btn btn-info btn-sm refundBtn" title="去处理" value="${data.id}">去处理</button>
		                    	</c:if>
		                    	<c:if test="${ data.orderId.handleState != 1}">
		                    		<button type="button" class="btn btn-info btn-sm refundBtn" disabled="disabled" title="去处理" value="${data.id}">去处理</button>
		                    	</c:if>
					        </td>
					        <td style="line-height:30px;">
					       		<c:if test="${ data.orderId.handleState == 1 }">
					       			待退费
					       		</c:if>
					       		<c:if test="${ data.orderId.handleState == 2 }">
					       			处理中
					       		</c:if>
					       		<c:if test="${ data.orderId.handleState == 5 }">
					       			处理中
					       		</c:if>
					       		<c:if test="${ data.orderId.handleState == 4 }">
					       			处理中
					       		</c:if>
					       		<c:if test="${ data.orderId.handleState == 3 }">
					       			已退费
					       		</c:if>
					       		<c:if test="${ data.orderId.handleState == 6 }">
					       			已退费
					       		</c:if>
					       		<c:if test="${ data.orderId.handleState == -1 }">
					       			<span style="color:red">已拒绝</span>
					       		</c:if>
					       		<c:if test="${ data.orderId.handleState == -2 }">
					       			<span style="color:red">已拒绝</span>
					       		</c:if>
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
<jsp:include page="/WEB-INF/web/ordertrial/dialog_trail.jsp"></jsp:include>