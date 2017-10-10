<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$(".refund_type").on("click", refund.tab_label_chg);
		$(".refuse").on("click", refund.refuse_money);
		$("#refuse_reason").on("propertychange input", refund.refuse_input_listener);
		$(".refuse-sure").on("click", refund.sure_refuse_request);
		$(".agree-refund").on("click", refund.agree_refund);
		$(".ok-refund").on("click", refund.ok_refund);
		refund.show_refund_left_time();
		self.setInterval(refund.show_refund_left_time, 30000);
	});
	
	var refund = function(){
		return {
			tab_label_chg : function(){
				var boolean = $(this).parent().hasClass("current");
				if(boolean){
					return;
				}
				var type = $(this).attr("value");
				$("#typeValue").val(type);
				$("#search-btn").trigger("click");
			},
			show_refund_left_time : function(){
				$(".use").each(function(){
					var d = new Date();
					var year = d.getFullYear();   
					var month = d.getMonth() + 1;   
					var day = d.getDate();
					var refundEndTime = year+"-"+month+"-"+day+" 23:59:59";
					var millisecond = new Date(refundEndTime.replace(/-/g,"/")).getTime() - new Date().getTime();
					var leftDay = millisecond % (24*3600*1000);
					var hour = Math.floor(leftDay / (1000*3600));
					var leftHour = leftDay % (3600*1000);
					var second = Math.floor(leftHour / (60*1000));
					$(this).children().text("("+hour+"小时"+second+"分钟后自动退款)");
				});
			},
			refuse_money : function(){
				var id = $(this).attr("value");
				$("#refund-id").val(id);
				$(".cancel-refund-href").trigger("click");
			},
			refuse_input_listener : function(){
				var content = $(this).val();
				if(content.length >= 200){
					var limit = content.substring(0, 200);
					$(this).val(limit);
					$("#refuse-left").text(200);
					return;
				}
				$("#refuse-left").text(content.length);
			},
			sure_refuse_request : function(){
				$("#refund-refuse-form").Validform({
					tiptype:function(msg){
						$(".refuse-error").removeClass("hide");
						$(".refuse-error-msg").text(msg);
					},
					showAllError:false,
					tipSweep:true,
					ajaxPost:true,
					beforeSubmit:function(curform){
						var content = $("#refuse_reason").val();
						var pattern = /[^\x00-\xff]/;
						if(!pattern.test(content)){
							$(".refuse-error-msg").text("需要填写中文字体！");
							return false;
						}
						App.validateSuccess(".refuse-error");
					},
					callback:function(request, data){
						$(".refuse-cancel").trigger("click");
						if(request instanceof Object){
							var loginStatus = request.getResponseHeader("loginStatus");
							if(loginStatus == "accessDenied"){
								App.noPermission();
							}
						}else{
							var message = "";
							if(request == "success"){
								message = "亲，你已拒绝退款！";
							}else if(request == "failed"){
								message = "拒绝退款失败！";
							}else if(request == "paramsError"){
								message = "参数错误，请刷新浏览器重试！";
							}else if(request == "error"){
								message = "系统处理异常！";
							}
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
								if(request == 'success'){
									location.reload();
								}
							});
							setTimeout(function() {
								location.reload();
							}, 2000);
						}
					}
				});
			},
			agree_refund : function(){
				var id = $(this).attr("value");
				var money = $(this).attr("money");
				App.confirm_show("提示！", "您将退还"+money+"元给用户！是否确认？", function(){
					$(".info-confirm-cancel").trigger("click");
					refund.refund_request(id, 9);
				});
			},
			ok_refund : function(){
				var id = $(this).attr("value");
				var money = $(this).attr("money");
				var flag = App.confirm_show("提示！", "您好！我们已经协商已经达成一致意见退还"+money+"元服务费用给用户，请您尽快确认退款。", function(){
					$(".info-confirm-cancel").trigger("click");
					refund.refund_request(id, 10);
				});
			},
			refund_request : function(refundId, type){
				App.ajaxRequest('/consult/execRefund', {'id':refundId, 'type':type}, 'get', true, 'json',
					function resultHandler(data){
						var message = "";
						if(data == 'failed'){
							message = "退费失败！";
						}else if(data == 'success'){
							message = "已退费！";
						}else if(data == 'paramsError'){
							message = "参数错误！";
						}else if(data == "error"){
							message = "退费处理异常！";
						}else{
							message = data;
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
					}
				);
			}
		};
	}();
</script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/consult/refund" method="POST" id="form">
		<input type="hidden" name="type" id="typeValue" value="${refundType }" />
    	<div class="panel panel-default">
        	<div class="panel-body">
				<ul class="nav nav-tabs no-border-margin" style="margin-bottom:20px;width:15%;display:inline">
	        		<li <c:if test="${refundType == 1 }">class="current"</c:if>><a href="#" class="refund_type" value="1">待处理</a></li>
					<li <c:if test="${refundType == 2 }">class="current"</c:if>><a href="#" class="refund_type" value="2">退款/拒绝</a></li>
				</ul>
				<div class="col-lg-8" style="float:right;text-align:right;margin-top: 10px;">
					订单编号
					<input type="text" class="form-control" name="orderId" placeholder="订单编号" value="${orderId }" style="display:inline;width:230px" />
					<c:if test="${refundType == 1 }">
		               	 &nbsp;&nbsp;退款状态
		               	 <select class="form-control" style="display:inline;width:230px" name="state">
		                	<option value="0" <c:if test="${state == 0 }">selected</c:if>>全部</option>
		                	<option value="2" <c:if test="${state == 2 }">selected</c:if>>申请退款</option>
		                	<option value="8" <c:if test="${state == 8 }">selected</c:if>>确认退款</option>
		                </select>
	                </c:if>
	                <c:if test="${refundType == 2 }">
		               	 &nbsp;&nbsp;退款状态
		               	 <select class="form-control" style="display:inline;width:230px" name="state">
		                	<option value="0" <c:if test="${state == 0 }">selected</c:if>>全部</option>
		                	<option value="3" <c:if test="${state == 3 }">selected</c:if>>已退款</option>
		                	<option value="4" <c:if test="${state == 4 }">selected</c:if>>拒绝退款</option>
		                	<option value="6" <c:if test="${state == 6 }">selected</c:if>>申诉成功并退款</option>
		                </select>
	                </c:if>
	                &nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-info" id="search-btn" type="submit" style="display:inline"><i class="icon-search"></i>查询</button>
	            </div>
				<!-- 线下课程 -->
				<table class="bordered">
	                <thead>
	                    <tr>
	                        <th>订单编号</th>
	                        <th>申请日期</th>
	                        <th>退款金额</th>
	                        <th style="width:30%">退款原因</th>
	                        <th>退款状态</th>
	                        <th style="width:21%">操作</th>
	                    </tr>
	                </thead>
	                <c:if test="${page != null && page.result != null && page.totalCount > 0 }">
	                	<c:forEach var="refund" items="${page.result }">
	                		<tr>
			                    <td>${refund.orderId }</td>
			                    <td><fmt:formatDate value="${refund.time }" type="both"/></td>
			                    <td>${refund.money }</td>
			                    <td>${refund.reason }</td>
			                    <td>
			                    	<c:choose>
			                    		<c:when test="${refund.state == 2 }">申请退款</c:when>
			                    		<c:when test="${refund.state == 8 }">
				                    		<span class="use" curt="<fmt:formatDate value="${refund.autoTime }" type="both"/>">
				                    			确认退款
				                    			<span></span>
				                    		</span>
			                    		</c:when>
			                    		<c:when test="${refund.state == 3 }">已退款</c:when>
			                    		<c:when test="${refund.state == 4 }">拒绝退款</c:when>
			                    		<c:when test="${refund.state == 6 }">申诉成功并退款</c:when>
			                    	</c:choose>
			                    </td>
			                    <td>
			                    	<a role="button" class="btn btn-info btn-sm" title="查看" href="${pageContext.request.contextPath}/consult/inq?conId=${refund.conId}">查看咨询详情</a>
			                    	<c:if test="${refundType == 1 }">
			                    		<c:choose>
				                    		<c:when test="${refund.state == 2 }">
				                    			<a role="button" class="btn btn-success btn-sm agree-refund" title="编辑" href="#" value="${refund.id }" money="${refund.money }">同意退款</a>
				                    		</c:when>
				                    		<c:when test="${refund.state == 8 }">
					                    		<a role="button" class="btn btn-success btn-sm ok-refund" title="编辑" href="#" value="${refund.id }" money="${refund.money }">确认退款</a>
				                    		</c:when>
				                    	</c:choose>
			                    		<c:if test="${refund.state == 2 }">
				                        	<a role="button" class="btn btn-danger btn-sm refuse" title="删除" href="#" value="${refund.id }">拒绝退款</a>
				                       	</c:if>
			                    	</c:if>
			                    </td>
			                </tr>
	                	</c:forEach>
	                </c:if>
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