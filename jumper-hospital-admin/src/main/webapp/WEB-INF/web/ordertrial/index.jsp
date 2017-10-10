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
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/user_trail.js?${v }"></script>

<!--这个不用了注释掉  不然会js报错  -->
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/seed-min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/redis/swfobject.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/redis/web_socket.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/redis/init_webSocket.js"></script>--%>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/user/index" method="POST" id="form">
        <input type="hidden" id="type" name="type" value="${type }" />
        <input type="hidden" id="my_name" value="${admin.username}" />
        <input type="hidden" id="connect_state" name="connect_state" value="" />
        <input type="hidden" id="remoteType" name="remoteType" value="${remoteType }" />
        <input type="hidden" id="expireType" name="expireType" value="${expireType }" />  
    	<div class="panel panel-default">
    	
        	<div class="panel-body">
        			<button type="button" class="btn btn-info add-order"><i class="icon-plus"></i> 新增监护订单</button>
        			<button type="button" class="btn btn-danger refund-list"><i class="icon-minus"></i> 退费列表</button>
	            <div class="col-lg-3" style="float:right">
	                <div class="input-group">
	                    <input type="text" class="form-control" name="searchKey" placeholder="姓名/电话" value="${searchKey }" />
	                    <span class="input-group-btn">
	                        <button class="btn btn-danger time-search-btn" type="button"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	            </div>
	            <div class="clearFix"></div>
	            <c:if test="${type == 1 || type == 0 }">
		        	<ul class="nav nav-tabs no-border-margin" style="border-bottom: none!important;float:left;margin-top:20px">
		        		<li class="<c:if test="${remoteType == 1 }">current</c:if>"><a href="#" class="remote_href" value="1">实时监护</a></li>
		        		<li class="<c:if test="${remoteType == 2 }">current</c:if>"><a href="#" class="remote_href" value="2">院内监护</a></li>
					</ul>
				</c:if>
				<c:if test="${type == 2 }">
		        	<ul class="nav nav-tabs no-border-margin" style="border-bottom: none!important;float:left;margin-top:20px">
		        		<li class="<c:if test="${expireType == 2 }">current</c:if>"><a href="#" class="expire_href" value="2">已关闭</a></li>     		
						<li class="<c:if test="${expireType == 0 }">current</c:if>"><a href="#" class="expire_href" value="0">已结束</a></li>
						<li class="<c:if test="${expireType == 1 }">current</c:if>"><a href="#" class="expire_href" value="1">已退费</a></li>
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
						<input type="hidden" name="payChannels" id="selectValue" value="${payChannels}"/>
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
	            <table class="bordered" style="width:100%;">
	                <thead>  
	                    <tr>
	                   	    <!-- 样式调整 -->
	                   	    <c:if test="${type == 0 }">
		                    	<th style="width:5%;">订单号</th>
		                        <th style="width:6%;">孕妇姓名</th>
		                        <th style="width:5%;">孕妇电话</th>    
		                        <th style="width:6%;">服务费用</th>
		                        <th style="width:8%;">剩余服务次数</th>
		                        <th style="width:6%;">服务类型</th>
		                        <th style="width:8%;">订单状态</th>
		                       	<th style="width:12%;" >支付渠道<select name="payChannels" value="${payChannels}" onchange="$('#selectValue').val(this.value);$('.time-search-btn').trigger('click');">
	                        	<option value="">全部</option>
	                        	<option <c:if test="${payChannels==1 }">selected="selected" </c:if> value="1">线下支付</option>
	                        	<option <c:if test="${payChannels==2 }">selected="selected" </c:if> value="2">线上支付</option>
	                        	<option <c:if test="${payChannels==3 }">selected="selected" </c:if> value="3">设备租赁</option>
	                        	</select></th>
	                        </c:if>
	                        <c:if test="${type == 1 }">
		                    	<th style="width:5%;">订单号</th>
		                        <th style="width:6%;">孕妇姓名</th>
		                        <th style="width:5%;">孕妇电话</th>    
		                        <th style="width:6%;">服务费用</th>
		                        <th style="width:9%;">剩余服务次数</th>
		                        <th style="width:6%;">服务类型</th>
		                        <th style="width:6%;">订单状态</th>
		                       	<th style="width:12%;" >支付渠道<select name="payChannels" value="${payChannels}" onchange="$('#selectValue').val(this.value);$('.time-search-btn').trigger('click');">
	                        	<option value="">全部</option>
	                        	<option <c:if test="${payChannels==1 }">selected="selected" </c:if> value="1">线下支付</option>
	                        	<option <c:if test="${payChannels==2 }">selected="selected" </c:if> value="2">线上支付</option>
	                        	<option <c:if test="${payChannels==3 }">selected="selected" </c:if> value="3">设备租赁</option>
	                        	</select></th>
	                        </c:if>
	                        <c:if test="${type == 2 }">
		                    	<th style="width:5%;">订单号</th>
		                        <th style="width:6%;">孕妇姓名</th>
		                        <th style="width:5%;">孕妇电话</th>    
		                        <th style="width:6%;">服务费用</th>
		                        <th style="width:8%;">剩余服务次数</th>
		                        <th style="width:8%;">服务类型</th>
		                        <th style="width:6%;" >失效类型</th>
		                       	<th style="width:12%;" >支付渠道
		                       	<select name="payChannels" value="${payChannels}" onchange="$('#selectValue').val(this.value);$('.time-search-btn').trigger('click');">
		                        	<option value="">全部</option>
		                        	<option <c:if test="${payChannels==1 }">selected="selected" </c:if> value="1">线下支付</option>
		                        	<option <c:if test="${payChannels==2 }">selected="selected" </c:if> value="2">线上支付</option>
		                        	<option <c:if test="${payChannels==3 }">selected="selected" </c:if> value="3">设备租赁</option>
	                        	</select></th>
	                        </c:if>
	                        <!--  要做样式控制直接放到上面去了
	                        <c:if test="${type != 2 }">
	                        	<th style="width:8%;">订单状态</th>
	                        </c:if> 
 	                        <c:if test="${type == 2 }">
	                        	<th style="width:6%;" >失效类型</th>
	                        </c:if> 
	                        <th style="width:12%;" >支付渠道<select name="payChannels" value="${payChannels}" onchange="$('#selectValue').val(this.value);$('.time-search-btn').trigger('click');">
	                        	<option value="">全部</option>
	                        	<option <c:if test="${payChannels==1 }">selected="selected" </c:if> value="1">线下支付</option>
	                        	<option <c:if test="${payChannels==2 }">selected="selected" </c:if> value="2">线上支付</option>
	                        	</select></th>
	                        <th style="width:10%;">订单生成日期</th>
	                        <th>孕妇地址</th>-->
	                        <c:if test="${type == 0 }">
	                       		 <th style="width:10%;">订单生成日期</th>
	                       		 <th>孕妇地址</th>
	                        	 <th style="width:14%;">操作</th>
	                        </c:if>
	                        <c:if test="${type == 1 }">
	                       		<th style="width:9%;">订单生成日期</th>
	                       		<th style="">孕妇地址</th>
	                        	<th style="width:14%;">操作</th>
	                        </c:if>
	                        <c:if test="${type == 2 }">
	                       		 <th style="width:10%;">订单生成日期</th>
	                       		 <th>孕妇地址</th>
	                        	<th style="width:10%;" >失效时间</th>
	                        </c:if>
	                        <!--   要做样式控制直接放到上面去了 
	                        <c:if test="${type == 2 }">
	                        	<th style="width:12%;" >失效时间</th>
	                        </c:if>
	                        <c:if test="${type != 2 }">
	                        	<th style="width:17%;">操作</th>
	                        </c:if>-->
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result }" var="data">
	                	<tr>
	                	<!-- payChannels == 3  设备租赁显示设备租赁订单号-->
               			<c:choose>
                       		<c:when test="${ data.payChannels == 3 }">
                       			<td>${data.leaseOrderId}</td>
                       		</c:when>
                       		<c:otherwise>
                       			<td>${data.orderId}</td>
                       		</c:otherwise>
                       	</c:choose>
		                    <td>${data.monitorUserId.realName==null?data.monitorUserId.userId.userExitInfo.realName:data.monitorUserId.realName }</td>
		                    <td>${data.monitorUserId.mobile }</td>
		                    <td>${data.money }</td>
		                    <td>${data.leftCount }</td>
		                    <td>${data.remoteType }</td>
		                    <c:if test="${type == 0 }">
		                    	<c:if test="${data.dealStates == 0 }">
	                        		<td>未支付</td>
	                        	</c:if>
		                    	<c:if test="${data.dealStates == 3 }">
	                        		<td>已结束</td>
	                        	</c:if>
	                        	<c:if test="${data.dealStates == 2}">
	                        		<td>已退费</td>
	                        	</c:if>
	                        	<c:if test="${data.dealStates == 4}">
	                        		<td>已关闭</td>
	                        	</c:if>
	                        	<c:if test="${data.dealStates==1}">
	                        		<td>已支付</td>
	                        	</c:if>
	                        	<c:if test="${data.dealStates == -1}">
	                        		<td>已支付</td>
	                        	</c:if>
	                        	<c:if test="${data.dealStates == -2}">
	                        		<td>已支付</td>
	                        	</c:if>
	                        	<c:if test="${data.dealStates == 5}">
	                        		<td>退款中</td>
	                        	</c:if>
		                    </c:if>
		                    <c:if test="${type == 1 }">
		                    	<c:if test="${data.dealStates==1}">
		                    		<td>已支付</td>
		                    	</c:if>
		                    </c:if>
		                    <c:if test="${type == 2 }">
		                    	<c:if test="${data.dealStates == 0 }">
	                        		<td>未支付</td>
	                        	</c:if>
		                    	<c:if test="${data.dealStates == 3 }">
	                        		<td>已结束</td>
	                        	</c:if>
	                        	<c:if test="${data.dealStates == 4}">
	                        		<td>已关闭</td>
	                        	</c:if>
	                        	<c:if test="${data.dealStates == 2}">
	                        		<td>已退费</td>
	                        	</c:if>
	                        </c:if>
	                        <td>
								<c:choose>
	                        		<c:when test="${ data.payChannels == 0 }">
	                        			线下支付
	                        		</c:when>
	                        		<c:when test="${ data.payChannels == 3 }">
	                        			设备租赁
	                        		</c:when>
	                        		<c:otherwise>
	                        			线上支付
	                        		</c:otherwise>
	                        	</c:choose>
							</td>
		                    <td><fmt:formatDate value="${data.addTime}" type="both"/></td>
		                    <td style="word-break:break-all;">${data.monitorUserId.address}</td>
		                    <c:if test="${type == 2 }">
		                    	<td><fmt:formatDate value="${data.expireTime}" type="both"/></td>
		                    </c:if>
	                    	<c:if test="${type != 2 }">
	                    		<c:if test="${data.dealStates == 1 }">
	                    			<td>
	                    			<!-- 设备租赁的屏蔽这两个按钮 -->
	                    			<c:if test="${data.payChannels !=3}" >
			                        	<button type="button" class="btn btn-danger btn-sm update-order" title="修改" value="${data.id }" tag="${data.remoteType }">
			                        		<i class="icon-pencil"></i> 修改
			                        	</button>
			                        	<button type="button" class="btn btn-warning btn-sm back-money" title="退费" value="${data.id }" user="${data.monitorUserId.realName }" remote="${data.remoteType }" channel="${data.payChannels}">
											<i class="icon-reply"></i> 退费
										</button>
									</c:if>
									
										<c:choose>
											<c:when test="${data.payChannels == 0 }">
												<button type="button" class="btn btn-success btn-sm order-detail disabled" disabled="disabled" title="详情" value="${data.id }" user="${data.monitorUserId.realName }" remote="${data.remoteType }">
					                        		<i class="icon-folder-open"></i> 详情
					                        	</button>
											</c:when>
											<c:otherwise>
												<button type="button" class="btn btn-success btn-sm order-detail" title="详情" value="${data.id }" user="${data.monitorUserId.realName }" remote="${data.remoteType }">
					                        		<i class="icon-folder-open"></i> 详情
					                        	</button>
											</c:otherwise>
										</c:choose>                   	
			                        	<c:if test="${type == 0 && data.remoteType == '院内监护' && data.state == '监测中'}">
				                        	<button type="button" class="btn btn-info btn-sm inside-reset" title="重测" value="${data.id }" ">
				                        		<i class="icon-repeat"></i> 重测
				                        	</button>
				                        </c:if>
		                        	</td>
	                    		</c:if>
	                    		<c:if test="${data.dealStates == 0 || data.dealStates == 2 || data.dealStates == -1 || data.dealStates == -2 }">
	                    			<td>
	                    			<!-- 设备租赁的屏蔽这两个按钮 -->
	                    			<c:if test="${data.payChannels !=3}" >
	                    				<button type="button" class="btn btn-danger btn-sm update-order" title="修改" value="${data.id }" tag="${data.remoteType }">
			                        		<i class="icon-pencil"></i> 修改
			                        	</button>
										<button type="button" class="btn btn-warning btn-sm back-money" disabled="disabled" title="退费" value="${data.id }" user="${data.monitorUserId.realName }" remote="${data.remoteType }" channel="${data.payChannels}">
											<i class="icon-reply"></i> 退费
										</button>
									</c:if>
										
			                        	<c:choose>
											<c:when test="${data.payChannels == 0 || data.dealStates == 0 }">
												<button type="button" class="btn btn-success btn-sm order-detail disabled" disabled="disabled" title="详情" value="${data.id }" user="${data.monitorUserId.realName }" remote="${data.remoteType }">
					                        		<i class="icon-folder-open"></i> 详情
					                        	</button>
											</c:when>
											<c:otherwise>
												<button type="button" class="btn btn-success btn-sm order-detail" title="详情" value="${data.id }" user="${data.monitorUserId.realName }" remote="${data.remoteType }">
					                        		<i class="icon-folder-open"></i> 详情
					                        	</button>
											</c:otherwise>
										</c:choose> 
	                    			</td>
	                    		</c:if>
	                    		<c:if test="${data.dealStates == 3 || data.dealStates == 4 ||data.dealStates == 5 }">
	                    			<td>
	                    			
	                    			<!-- 设备租赁的屏蔽这两个按钮 -->
	                    			<c:if test="${data.payChannels !=3}" >
	                    				<button type="button" class="btn btn-danger btn-sm update-order" title="修改" value="${data.id }" tag="${data.remoteType }">
			                        		<i class="icon-pencil"></i> 修改
			                        	</button>
										<button type="button" class="btn btn-warning btn-sm back-money" disabled="disabled" title="退费" value="${data.id }" user="${data.monitorUserId.realName }" remote="${data.remoteType }" channel="${data.payChannels}">
											<i class="icon-reply"></i> 退费
										</button>
									</c:if>
									
			                        	<c:choose>
											<c:when test="${data.payChannels == 0 || data.dealStates == 4}">
												<button type="button" class="btn btn-success btn-sm order-detail disabled" disabled="disabled" title="详情" value="${data.id }" user="${data.monitorUserId.realName }" remote="${data.remoteType }">
					                        		<i class="icon-folder-open"></i> 详情
					                        	</button>
											</c:when>
											<c:otherwise>
												<button type="button" class="btn btn-success btn-sm order-detail" title="详情" value="${data.id }" user="${data.monitorUserId.realName }" remote="${data.remoteType }">
					                        		<i class="icon-folder-open"></i> 详情
					                        	</button>
											</c:otherwise>
										</c:choose>  
	                    			</td>
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
	            <c:if test="${admin.getHospitalInfo().getIsAutonomy()==1}">
	           	 	<c:if test="${type==0 }" >
	           	 		<div>今日实时监护使用次数：${usedCount }</div>
	            	</c:if>
	           		 <c:if test="${type==2 }">
	            		<div>实时监护使用总次数：${usedCount }</div>
	            	</c:if> 
	            </c:if>
	            <jsp:include page="../common/page.jsp"></jsp:include>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/ordertrial/dialog_trail.jsp"></jsp:include>