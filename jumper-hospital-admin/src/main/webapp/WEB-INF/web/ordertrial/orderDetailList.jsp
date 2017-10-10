<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/media/css/daterangepicker-bs3.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/jquery.autocomplete.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/style_trail.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/bootstrap.css?${v }">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.autocomplete.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/daterangepicker.js?${v }"></script>


<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="" method="POST" id="form">
        <input type="hidden" id="type" name="type" value="${type }" />
        <input type="hidden" id="remoteType" name="remoteType" value="${remoteType }" />
        <input type="hidden" id="expireType" name="expireType" value="${expireType }" />  
    	<div class="panel panel-default">
    	<div class="gw_rote"> <span class="float-left" style="font-size:16px;">今日订单 > <a style=" color:#ff7ea5">订单详情</a></span></div>
        	<div class="panel-body">
            <div class="clearFix"></div>
            	<hr style="margin:20px 0px;">
        		<h4>孕妇姓名：${ order.monitorUserId.realName}</h4>
				<div class="clearFix"></div>
	            <table class="detail_center bordered">
	                <thead>  
	                    <tr>
	                        <th>序号</th>
	                        <th>时间</th>
	                        <th>操作</th>
	                        <th>购买服务次数</th>
	                        <th>剩余服务次数</th>
	                    </tr>
	                </thead>
	                <c:set var="serialNum" value="0"/>
	                <c:if test="${ payInfo != null }">
	                	<c:set var="serialNum" value="${ serialNum + 1 }"/>
	                	<tr>
		                    <td><c:out value="${ serialNum }" /></td>
		                    <td><fmt:formatDate value ="${payInfo.orderPayTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		                    <td>支付</td>
		                    <td>${order.monitorCount }</td>
		                    <td>${order.monitorCount }</td>
		                </tr>
	                </c:if>
	                <c:set var="monitorCountNum" value="${order.monitorCount}"/>
	                <c:forEach items="${order.consumerList}" var="data" varStatus="v">
	                	<c:set var="serialNum" value="${ serialNum + 1 }"/>
	                	<c:set var="monitorCountNum" value="${ monitorCountNum - 1 }"/>
	                	<tr>
		                    <td>${v.count}</td>
		                    <td><fmt:formatDate value ="${data.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		                    <td>使用</td>
		                    <td>${order.monitorCount }</td>
		                    <td>${monitorCountNum}</td>
		                </tr> 
	                </c:forEach>
	                <c:if test="${ refundInfo != null }">
	                	<c:set var="serialNum" value="${ serialNum + 1 }"/>
	                	<tr>
		                    <td><c:out value="${ serialNum }" /></td>
		                    <td><fmt:formatDate value ="${refundInfo.refundTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		                    <td>申请退费</td>
		                    <td>${order.monitorCount }</td>
		                    <td>${order.leftCount }</td>
		                </tr>
	                </c:if>
	                <c:if test="${ appealInfo != null }">
	                	<c:set var="serialNum" value="${ serialNum + 1 }"/>
	                	<tr>
		                    <td><c:out value="${ serialNum }" /></td>
		                    <td><fmt:formatDate value ="${appealInfo.refundTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		                    <td>申诉</td>
		                    <td>${order.monitorCount }</td>
		                    <td>${order.leftCount }</td>
		                </tr>
	                </c:if>
	                <c:if test="${(order.consumerList == null || empty order.consumerList) && payInfo == null && refundInfo == null && appealInfo == null }">
			      		<tr>
			      			<td colspan="11" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
	            </table>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/ordertrial/dialog_trail.jsp"></jsp:include>