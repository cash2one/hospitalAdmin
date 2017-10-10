<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/media/css/service.css?${v }" rel="stylesheet">
<script type="text/javascript">
	$(function(){
		$(".school-service-operate").click(function(){
			var state = $(this).attr("value");
			if(state == 0){
				msg = "确认关闭孕妇学校服务？";
			}else{
				msg = "确认开通孕妇学校服务？";
			}
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/hos/schoolOperate',
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
					<c:if test="${isSchool }"><img src="${pageContext.request.contextPath}/media/image/check_success.png"></c:if>
					<c:if test="${!isSchool }"><img src="${pageContext.request.contextPath}/media/image/check_failed.png"></c:if>
					孕妇学校
				</div>
				<div class="float-right">
					<c:if test="${isSchool }">已开通</c:if>
					<c:if test="${!isSchool }">未开通</c:if>
				</div>
			</div>
			<div class="panel-title">
				<span>介绍</span>
			</div>
			<div class="instraction">
				<p>1、开通孕妇学校，则孕妇可通过线上去预约线下课程，还可观看网络课程。</p>
				<p>2、在线下课堂孕妇扫描二维码进行签到，观看网络课堂可直接点击签到按钮进行签到。</p>
				<p>3、孕妇学校不仅可以为孕妇提供便捷，更提高了我院的办事效率。</p>
			</div>
			<hr />
			<div class="panel-btn">
				<c:if test="${!isSchool }">
					<button type="button" class="btn btn-danger school-service-operate" value="1">开通服务</button>
				</c:if>
				<c:if test="${isSchool }">
					<button type="button" class="btn btn-success school-service-operate" value="0">关闭服务</button>
				</c:if>
			</div>
		</div>
	</div>
</div>

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>