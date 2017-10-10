<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/media/css/service.css?${v }" rel="stylesheet">
<script type="text/javascript">
	$(function(){
		$(".lease-service-operate").click(function(){
			var state = $(this).attr("value");
			if(state == 0){
				msg = "确认关闭设备租赁服务？";
			}else{
				msg = "确认开通设备租赁服务？";
			}
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/hos/leaseOperate',
					{'state':state},
					'get',
					true,
					'json',
					function resultHandler(data){
						var message = "";
						if(state == 0){
							if(data == 'failed'){
								message = "服务关闭失败！";
							}else if(data == 'success'){
								message = "服务关闭成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "程序错误，关闭失败！";
							}
						}else{
							if(data == 'failed'){
								message = "服务开通失败！";
							}else if(data == 'success'){
								message = "服务开通成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "程序错误，开通失败！";
							}
						}
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
					}
				);
			});
		});
	});
</script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="panel-head">
				<div class="float-left">
					<c:if test="${isLease }"><img src="${pageContext.request.contextPath}/media/image/check_success.png"></c:if>
					<c:if test="${!isLease }"><img src="${pageContext.request.contextPath}/media/image/check_failed.png"></c:if>
					设备租赁
				</div>
				<div class="float-right">
					<c:if test="${isLease }">已开通</c:if>
					<c:if test="${!isLease }">未开通</c:if>
				</div>
			</div>
			<div class="panel-title">
				<span>介绍</span>
			</div>
			<div class="instraction">
				<p>1、开通设备租赁服务，孕妇可以在天使医生用户端预约租赁设备，并在医院领取设备。</p>
				<p>2、设备租赁更加有效地解决了孕妇院外自主监测，真正意义的实现远程监护。</p>
			</div>
			<hr />
			<div class="panel-btn">
				<c:if test="${!isLease }">
					<button type="button" class="btn btn-danger lease-service-operate" value="1">开通服务</button>
				</c:if>
				<c:if test="${isLease }">
					<button type="button" class="btn btn-success lease-service-operate" value="0">关闭服务</button>
				</c:if>
			</div>
		</div>
	</div>
</div>

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>