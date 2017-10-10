<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>

<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/daterangepicker.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/operateLog/operateLog.js?${v }"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/media/im/css/jquery-ui.min.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/im/js/lib/jquery/jquery-ui.js?${v }"></script>  


<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
<form action="${pageContext.request.contextPath}/operateLog/showOperateLog" method="POST" id="form">
		<input type="hidden" name="logType" id="logType" value="${logType}" />
		<input type="hidden" name="quickQuery" id="quickQuery" value="${quickQuery}" />
    	<div class="panel panel-default">
        	<div class="panel-body">
        		
        	    <button class="btn btn-default <c:if test='${quickQuery=="0"}'>active </c:if> quickQuery" style="float:left;margin-left: 5px;border-style:none"  value="0"> 昨天 </button>	
        	    <button class="btn btn-default <c:if test='${quickQuery=="1"}'>active </c:if> quickQuery" style="float:left;margin-left: 5px;border-style:none"  value="1"> 今天 </button>
        	    <button class="btn btn-default <c:if test='${quickQuery=="2"}'>active </c:if> quickQuery" style="float:left;margin-left: 5px;border-style:none"  value="2"> 本周 </button>
        	    <button class="btn btn-default <c:if test='${quickQuery=="3"}'>active </c:if> quickQuery" style="float:left;margin-left: 5px;border-style:none"  value="3"> 本月</button>
        	    <button class="btn btn-default <c:if test='${quickQuery=="4"}'>active </c:if> quickQuery" style="float:left;margin-left: 5px;border-style:none"  value="4"> 所有</button>
        	
        		<!--日历组件  -->
        	     <div class="input-prepend input-group" style="width:500px;float:left">
					<span class="add-on input-group-addon">
						<i class="icon-calendar"></i>
					</span> 
					<input type="text" style="width: 200px" name="beginT" id="beginTime" placeholder="开始时间" class="form-control" value="${beginTime }" />
				
					<span class="add-on input-group-addon">
						<i class="icon-calendar"></i>
					</span>
					<input type="text" style="width: 200px" name="endT" id="endTime" placeholder="结束时间" class="form-control" value="${endTime }" />
				</div>
				
		
        	
	            <div class="col-lg-3" style="float:right;margin-top: 0px;">
	                <div class="input-group">
	                    <input type="text" class="form-control" name="searchKey" placeholder="搜索关键字" value="${searchKey }" />
	                    <span class="input-group-btn">
	                        <button class="btn btn-danger" type="submit"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	            </div>
	            
	            <select class="form-control" name="searchType" value="${searchType}" style="width:120px;float:right;margin-top: 0px;">
				  <option>关键字类型</option>
				  <option value="0" <c:if test='${searchType=="0"}'>selected="selected"</c:if>>操作者</option>
				  <option value="1" <c:if test='${searchType=="1"}'>selected="selected"</c:if>>对象</option>
				</select>
	            
				<div class="clearFix"></div>
				<ul class="nav nav-tabs no-border-margin" style="border-bottom: 1px solid #eee!important;;float:left;margin:20px 0px;width:100%">
	        		<li class="<c:if test="${logType == 1 }">current</c:if>"><a href="#" class="logType" value="1">操作记录</a></li>
					<li class="<c:if test="${logType == 0 }">current</c:if>"><a href="#" class="logType" value="0">登录记录</a></li>
				</ul>
				<br>
	            <table class="bordered">
	                <thead>  
	                    <tr>
	                        <th>时间</th>
	                        <th>操作账号</th>
	                        <th>操作者</th>
	                        <th>动作</th>
	                        <th>对象</th>
	                        <th>对象内容</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result }" var="data" varStatus="v">
	                	<tr>
							<%-- <td>${data.operateTime }</td> --%>
							<td><fmt:formatDate value="${data.operateTime}" type="both"/></td>
		                    <td>${data.userName }</td>
		                    <td>${data.name }</td>
		                    <td>${data.action }</td>
		                    <td>${data.object }</td>
		                    <td>${data.objectContent }</td>
		                </tr>
	                </c:forEach>
	                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="7" style="color:red">暂无记录</td>
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