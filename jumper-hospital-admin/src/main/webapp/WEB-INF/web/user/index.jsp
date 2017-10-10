<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/media/css/daterangepicker-bs3.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/jquery.autocomplete.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/style.css?${v }" />

<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.autocomplete.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/daterangepicker.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/user.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/user/index" method="POST" id="form">
        <input type="hidden" id="type" name="type" value="${type }" />
        <input type="hidden" id="remoteType" name="remoteType" value="${remoteType }" />
        <input type="hidden" id="expireType" name="expireType" value="${expireType }" />
    	<div class="panel panel-default">
        	<div class="panel-body">
        		<c:if test="${type != 2 }">
        			<button type="button" class="btn btn-info add-order"><i class="icon-plus"></i> 新增监护订单</button>
        		</c:if>
	            <div class="col-lg-3" style="float:right">
	                <div class="input-group">
	                    <input type="text" class="form-control" name="searchKey" placeholder="姓名/电话" value="${searchKey }" />
	                    <span class="input-group-btn">
	                        <button class="btn btn-danger time-search-btn" type="button"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	            </div>
	            <div class="clearFix"></div>
	            <c:if test="${type == 1 || type==0}">
		        	<ul class="nav nav-tabs no-border-margin" style="border-bottom: none!important;float:left;margin-top:20px">
						<li class="<c:if test="${remoteType == 1 }">current</c:if>"><a href="#" class="remote_href" value="1">实时监护</a></li>
						<li class="<c:if test="${remoteType == 2 }">current</c:if>"><a href="#" class="remote_href" value="2">院内监护</a></li>
					</ul>
				</c:if>
				<c:if test="${type == 2 }">
		        	<ul class="nav nav-tabs no-border-margin" style="border-bottom: none!important;float:left;margin-top:20px">
		        		<li class="<c:if test="${expireType == 1 }">current</c:if>"><a href="#" class="expire_href" value="1">已退费订单</a></li>
						<li class="<c:if test="${expireType == 0 }">current</c:if>"><a href="#" class="expire_href" value="0">消费结束订单</a></li>
					</ul>
				</c:if>
				<c:if test="${type == 1 }">
					<button type="button" class="btn btn-info time-search-btn" title="查询" style="margin-top:20px;float:right">查&nbsp;询</button>
					<div class="input-prepend input-group" style="width:500px;margin-top:20px;float:right">
						<span class="add-on input-group-addon">
							<i class="icon-calendar"></i>
						</span>
						<input type="text" style="width: 200px" name="startTime" id="startTime" placeholder="购买开始时间" class="form-control" value="${startTime }" />
						<span class="add-on input-group-addon">
							<i class="icon-calendar"></i>
						</span>
						<input type="text" style="width: 200px" name="endTime" id="endTime" placeholder="购买结束时间" class="form-control" value="${endTime }" />
					</div>
				</c:if>
				<c:if test="${type == 2}">
					<button type="button" class="btn btn-info time-search-btn" title="查询" style="margin-top:20px;float:right">查&nbsp;询</button>
					<div class="input-prepend input-group" style="width:500px;margin-top:20px;float:right">
						<span class="add-on input-group-addon">
							<i class="icon-calendar"></i>
						</span>
						<input type="text" style="width: 200px" name="invalidStartTime" id="invalidStartTime" placeholder="失效开始时间" class="form-control" value="${invalidStartTime }" />
						<span class="add-on input-group-addon">
							<i class="icon-calendar"></i>
						</span>
						<input type="text" style="width: 200px" name="invalidEndTime" id="invalidEndTime" placeholder="失效结束时间" class="form-control" value="${invalidEndTime }" />
					</div>
				</c:if>
				<div class="clearFix"></div>
				<hr style="margin:20px 0px;">
	            <table class="bordered">
	                <thead>  
	                    <tr>
	                        <th style="width:6%;" >用户姓名</th>
	                        <th style="width:5%;">电话</th>
	                        <th style="width:4%;">年龄</th>
	                        <!-- <th style="width:4%;">孕周</th> -->
	                        <th style="width:6%;">服务费用</th>
	                        <th style="width:6%;">服务类型</th>
	                        <th style="width:6%;">购买次数</th>
	                        <th style="width:6%;">剩余次数</th>
	                        <th style="width:10%;">购买时间</th>
	                        <th>用户地址</th>
	                        <c:if test="${type == 2 }">
	                        	<th style="width:10%;">失效时间</th>
	                        	<th style="width:6%;">失效类型</th>
	                        </c:if>
	                        <c:if test="${type != 2 }">
	                        	<th style="width:10%;">操作</th>
	                        </c:if>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result }" var="data">
	                	<tr>
	                	    <td>${data.monitorUserId.realName==null?data.monitorUserId.userId.userExitInfo.realName:data.monitorUserId.realName }</td>
		                    <%-- <td>${data.monitorUserId.realName }</td> --%>
		                    <td>${data.monitorUserId.mobile }</td>
		                    <td>${data.monitorUserId.age }</td>
		                    <%-- <td>${data.pregnantWeek }</td> --%>
		                    <td>${data.money }</td>
		                    <td>${data.remoteType }</td>
		                    <td>${data.monitorCount }</td>
		                    <td>${data.leftCount }</td>
		                    <td><fmt:formatDate value="${data.addTime }" type="both"/></td>
		                    <td>${data.monitorUserId.address}</td>
		                    <c:if test="${type == 2 }">
	                        	<td><fmt:formatDate value="${data.expireTime }" type="both"/></td>
	                        	<c:if test="${data.isEffective }">
	                        		<td>已退费</td>
	                        	</c:if>
	                        	<c:if test="${(!data.isEffective) && (data.leftCount < 1) }">
	                        		<td>消费结束</td>
	                        	</c:if>
	                        </c:if>
		                    
	                    	<c:if test="${type != 2 }">
	                    		<c:if test="${!data.isEffective && data.leftCount != 0 }">
	                    			<td>
			                        	<button type="button" class="btn btn-danger btn-sm update-order" title="修改" value="${data.id }" tag="${data.remoteType }"><i class="icon-pencil"></i> 修改</button>
			                        	<button type="button" class="btn btn-warning btn-sm back-money" title="退费" value="${data.id }" user="${data.monitorUserId.realName }" remote="${data.remoteType }">
			                        		<i class="icon-reply"></i> 退费
			                        	</button>
			                        	<c:if test="${type == 0 && data.remoteType == '院内监护' && data.state == '监测中'}">
				                        	<button type="button" class="btn btn-info btn-sm inside-reset" title="重测" value="${data.id }" ">
				                        		<i class="icon-repeat"></i> 重测
				                        	</button>
				                        </c:if>
		                        	</td>
	                    		</c:if>
	                    		<c:if test="${data.isEffective || data.leftCount <= 0 }">
	                    			<td></td>
	                    		</c:if>
	                        </c:if>
		                </tr>
	                </c:forEach>
	                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<c:if test="${type != 2 }">
			      				<td colspan="11" style="color:red">暂无记录</td>
			      			</c:if>
			      			<c:if test="${type == 2 }">
			      				<td colspan="12" style="color:red">暂无记录</td>
			      			</c:if>
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