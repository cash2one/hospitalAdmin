<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/message.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/daterangepicker.js?${v }"></script>
<script type="text/javascript">
	$(function(){
		/** 初始化查询开始时间控件 **/
		$('#startTime').daterangepicker({
			singleDatePicker: true,
			format:"YYYY-MM-DD",
			startDate : new Date,
			language: 'zh-CN' //汉化
		});
		/** 初始化查询结束时间控件 **/
		$('#endTime').daterangepicker({
			singleDatePicker: true,
			format:"YYYY-MM-DD",
			startDate : new Date,
			language: 'zh-CN' //汉化
		});
		
		$(".report-detail").click(function(){
			$(this).next("a").trigger("click");
		});
		
		$(".search-btn").bind("click", searchTimeCheck);
		
	});
	
	function searchTimeCheck(){
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var startDate = new Date(startTime);
		var endDate = new Date(endTime);
		if(startDate > endDate){
			App.dialog_show("", "开始时间不能大于结束时间！", function(){
				$(".info-dialog-close").trigger("click");
			});
			return;
		}
		$("#form").submit();
	}
</script>

<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/consServerOrder/getUnBlanceServiceOrderList" method="POST" id="form">
	
    	<div class="panel panel-default">
        	<div class="panel-body">
        		
	           
				<div class="clearFix"></div>  
				<ul class="nav nav-tabs no-border-margin" style="border-bottom: 1px solid #eee!important;;float:left;margin:20px 0px;width:100%">
	        		<li ><a href="${pageContext.request.contextPath}/withdrawals/getWithdrawalsList" class="user_type" value="0">已结算列表</a></li>
					<li class="current"><a href="${pageContext.request.contextPath}/consServerOrder/getUnBlanceServiceOrderList" class="user_type" value="1">未结算列表</a></li>
				</ul>
			
			  <div  style="margin-right: 0px;float: right;margin-right: 0px;">
			 	<button type="button" class="btn btn-info search-btn" title="查询">查&nbsp;询</button>
			  </div>
			
			  <div class="input-prepend input-group" style="width:500px;float:right;margin-right:0px;">
					<span class="add-on input-group-addon">
						<i class="icon-calendar"></i>
					</span>
					<input type="text" style="width: 200px" name="startTime" id="startTime" placeholder="支付起始时间" class="form-control" value="${startTime }" />
					<span class="add-on input-group-addon">
						<i class="icon-calendar"></i>
					</span>
					<input type="text" style="width: 200px" name="endTime" id="endTime" placeholder="支付结束时间" class="form-control" value="${endTime }" />
				</div>
		
			    <font  style="color:#606060;font-weight:600;">未结算为所有还没结束的订单或正在处理退费的订单金额</font>
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
		                    <td>${data.status==4||data.status==7?"-":"+"} ${data.trueCost}</td>		          
		                    <td>
		                     
		                   <fmt:formatDate value ="${data.addTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>   
		                    </td>
		                </tr>
	                </c:forEach>
	                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="6" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
	            </table>
	            <div style="color:#ff7ea5;">注:"+"表示该项费用将会结算给医院</div>
	            <div style="color:#ff7ea5;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"_"表示该项费用已经退款</div>
	            <jsp:include page="../common/page.jsp"></jsp:include>
	        </div>
	    </div>
	</form>
</div>

 

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>