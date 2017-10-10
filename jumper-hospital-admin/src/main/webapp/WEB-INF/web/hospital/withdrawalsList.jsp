<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/message.js?${v }"></script>

<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/withdrawals/getWithdrawalsList" method="POST" id="form">
	
    	<div class="panel panel-default">
        	<div class="panel-body">
        		
	           
				<div class="clearFix"></div>
				<ul class="nav nav-tabs no-border-margin" style="border-bottom: 1px solid #eee!important;;float:left;margin:20px 0px;width:100%">
	        		<li class="current"><a href="${pageContext.request.contextPath}/withdrawals/getWithdrawalsList" class="user_type" value="0">已结算列表</a></li>
					<li><a href="${pageContext.request.contextPath}/consServerOrder/getUnBlanceServiceOrderList" class="user_type" value="1">未结算列表</a></li>
				</ul>
				<div style="color:#606060;font-weight:600;margin-bottom: 12px;">已结算金额为您使用天使医生工作站（指此网站）期间所有已结算的金额</div>
			
	            <table class="bordered">
	                <thead>  
	                    <tr>
	                        <th>日期</th>
	                        <th>结算金额</th>	                   
	                        <th>结算时间</th>	                      	        
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result }" var="data" varStatus="v">
	                	<tr>		        
		                    <td>
		                    <fmt:formatDate value ="${data.addTime}" pattern="yyyy年MM月份收益"></fmt:formatDate>
		                    </td>
		                    <td>${data.withdrawalsMoney }</td>
		                    <td>
		                           
		                   <fmt:formatDate value ="${data.settleAccountTime}" pattern="yyyy-MM-dd"></fmt:formatDate>  
		                    </td>		          
		                    <td>
		                    	<a role="button" class="btn btn-success btn-sm" title="查看详情" href="${pageContext.request.contextPath}/consServerOrder/getConsServiceOrderList?withdrawalsId=${data.id}"><i class="icon-folder-open-alt"></i> 详情</a>
		                    </td>
		                </tr>
	                </c:forEach>
	                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="6" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
	            </table>
	            
	       <div style="color:#606060;font-weight:500;">    注：1、请我院把收款姓名、收款银行卡号、联系电话给天使医生平台，天使医生平台每月18号，给我院结算上一个月的收益 .如遇节假日则顺延。 </div> 
      <div style="color:#606060;font-weight:500;">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2、该收益列表暂只有医院问诊的费用。  </div> 
     <div style="color:#606060;font-weight:500;"> &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;3、只有已经失效并已经不可以申请退款或申请退款并已经处理完成的订单费用才会结算给我院。  </div> 
	            <jsp:include page="../common/page.jsp"></jsp:include>
	        </div>
	    </div>
	</form>
</div>

 

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>