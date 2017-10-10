<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/media/css/service.css?${v }" rel="stylesheet">
<script type="text/javascript">
	$(function(){
		$(".service-operate").click(function(){
			var state = $(this).attr("value");
			if(state == 0){
				msg = "确认关闭健康营养管理服务？";
			}else{
				msg = "确认开通健康营养管理服务？";
			}
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/hos/weightOperate',
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
					<c:if test="${isWeight }"><img src="${pageContext.request.contextPath}/media/image/check_success.png"></c:if>
					<c:if test="${!isWeight }"><img src="${pageContext.request.contextPath}/media/image/check_failed.png"></c:if>
					健康营养管理
				</div>
				<div class="float-right">
					<c:if test="${isWeight }">已开通</c:if>
					<c:if test="${!isWeight }">未开通</c:if>
				</div>
			</div>
			<div class="panel-title">
				<span>介绍</span>
			</div>
			<div class="instraction">
				<p>1.医院借用本平台对每位孕妇进行科学的体重、血糖管理。</p>
				<p>2.医护人员能实时跟踪每位孕妇的体重变化、血糖变化及每天的膳食。</p>
				<p>3.实现对每位孕妇的健康营养管理。</p>
			</div>
			<hr />
			<div class="panel-btn">
				<c:if test="${!isWeight }">
					<button type="button" class="btn btn-danger service-operate" value="1">开通服务</button>
				</c:if>
				<c:if test="${isWeight }">
					<button type="button" class="btn btn-success service-operate" value="0">关闭服务</button>
				</c:if>
			</div>
		</div>
	</div>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>