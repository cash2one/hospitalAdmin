<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <!-- 页头引入 -->
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/media/css/daterangepicker-bs3.css?${v }" />

<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/daterangepicker.js?${v }"></script>
<script type="text/javascript">
	$(function(){
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
	});
	
</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/hospital.css?${v }">
<div class="col-xs-12 col-sm-9" style="float:right;">
<form action="${pageContext.request.contextPath}/order/searchMonitorOrder" method="post" id="form" >
   <div class="panel panel-default" ><!--style="margin-right: 200px"  -->
       <div class="panel-body">
		
	 	    <div class="actions" style="margin-top: auto;margin-bottom: auto;">
	  	    		<div class="col-lg-3" style="float:right">
	                <div class="input-group">
	                    <input type="text" class="form-control" name="searchKey" placeholder="输入手机号码或姓名" value="${searchKey }" />
	                    <span class="input-group-btn">
	                        <button class="btn btn-pink" type="submit"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	            </div>
	  	    	
	  	    </div>
	  	    
	  	    <div class="input-prepend input-group" style="width:500px;margin-top:20px;float:left">
			<span class="add-on input-group-addon">
				<i class="icon-calendar"></i>
			</span>
			<input type="text" style="width: 200px" name="startTime" id="startTime" placeholder="开始时间" class="form-control" value="${startTime }" />
			<span class="add-on input-group-addon">
				<i class="icon-calendar"></i>
			</span>
			<input type="text" style="width: 200px" name="endTime" id="endTime" placeholder="结束时间" class="form-control" value="${endTime }" />
		</div>
		<button type="button" class="btn btn-info" id="searchBtn" title="查询" style="margin-top:20px">查&nbsp;询</button>
	   	 
	   </div>
	</div>


<!--  <span style="float:right;">
 	<input placeholder="yyyy-MM-dd" name="begin_Time" id="begin_Time"  class="text" style="width:110px" onclick="WdatePicker()" value=""/> 至
	<input placeholder="yyyy-MM-dd" name="end_Time" id="end_Time"  class="text" style="width:110px" onclick="WdatePicker()" value=""/>
	</span>  --> 	
	
   <div class="panel panel-default"><!-- style="margin-right: 200px"  -->
       <div class="panel-body">
	   		<div style="margin-bottom:10px;">
	   			<span style="float:left;color:#FF749B;"><img src="${pageContext.request.contextPath}/media/image/count.png"/>&nbsp;&nbsp;服务人数：${count}</span>
	  	    	<span style="float:left;color:#FF749B;"><img src="${pageContext.request.contextPath}/media/image/profit.png"/>&nbsp;&nbsp;
	  	    	<c:if test="${profits==null||profits==''||profits==0}">
	  	    		收益： 0
	  	    	</c:if>
	  	    	<c:if test="${profits!=null}">
	  	    		收益： <fmt:formatNumber value="${profits}" pattern="￥#,###.##" type="currency"/><!--  -->
	  	    	</c:if>
	  	    	</span>
	   			<br/><hr style="height:1px;border:none;border-top:1px solid #E0E0E0;" />
	   		</div>
	   		<div>
		       <table class="table table-bordered table-hover" style="text-align:center; vertical-align:middle;">
		   			<thead>
					    <tr style="background:#ECECEC;font-weight:bold;">
					       <td>序号</td>
					       <td>用户姓名</td>
					       <td>用户手机</td>
					       <td>服务费用</td>
					       <td>订单日期</td>
					    </tr>
		   			</thead>
		   			
		   				<c:forEach items="${monitorOrderList.result}" var="monitorOrder" varStatus="v">
				   		 	<tbody>
					   		 	<tr>
					   		 	   <td style="line-height:30px;">${v.count}</td>
							       <td style="line-height:30px;">${monitorOrder.monitorUserId.realName}</td>
							       <td style="line-height:30px;">${monitorOrder.monitorUserId.mobile}</td>
							       <td style="line-height:30px;">${monitorOrder.money}</td>
							       <td style="line-height:30px;"><fmt:formatDate value ="${monitorOrder.addTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
							      
						    	</tr>
				   			</tbody>
			   			</c:forEach>
		   					<c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="11" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
				</table>
				<jsp:include page="../common/page.jsp"></jsp:include>
			</div>
      </div>
   </div>
 </form>
</div>
 
  <!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>

<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/hospital.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/doctorManager.js"></script>

<script type="text/javascript">
</script>
</html>
