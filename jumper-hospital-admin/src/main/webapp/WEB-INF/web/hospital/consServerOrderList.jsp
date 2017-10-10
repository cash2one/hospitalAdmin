<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/message.js?${v }"></script>

<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/consServerOrder/getConsServiceOrderList?withdrawalsId=${withdrawalsId}&state=${state}"" method="POST" id="form">
	
    	<div class="panel panel-default">
        	<div class="panel-body">
        		
	           
				<div class="clearFix"></div>
			<!--  	<ul class="nav nav-tabs no-border-margin" style="border-bottom: 1px solid #eee!important;;float:left;margin:20px 0px;width:100%">
	        		<li class="<c:if test="${type == 0 }">current</c:if>"><a href="#" class="user_type" value="0">已结算列表</a></li>
					<li class="<c:if test="${type == 1 }">current</c:if>"><a href="#" class="user_type" value="1">未结算列表</a></li>
				</ul>
				-->
				<br>
	            <table class="bordered">
	                <thead>  
	                    <tr>
	                        <th>订单编号</th>
	                        <th>服务项目</th>	                   
	                        <th>费用</th>	                      	        
	                        <th>支付时间</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result }" var="data" varStatus="v">
	                	<tr>		        
		                    <td>${data.orderId }</td>
		                    <td>医院问诊</td>
		                      <!-- 只有4和7是成功退款的 -->
		                    <td>${data.status==4||data.status==7?"-":"+"}${data.trueCost}</td>		          
		                    <td>
		                    
		                  <fmt:formatDate value ="${data.addTime}" pattern="yyyy-MM-dd"></fmt:formatDate>       
		                    </td>
		                </tr>
	                </c:forEach>
	                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="6" style="color:red">暂无记录</td>
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
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>