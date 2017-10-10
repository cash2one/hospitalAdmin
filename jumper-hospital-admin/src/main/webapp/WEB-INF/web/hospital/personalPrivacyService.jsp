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
				msg = "确认显示用户手机号码？";
			}else{
				msg = "确认屏蔽用户手机号码？";
			}
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/hos/personPrivacyOperate',
					{'state':state},
					'get',
					true,
					'json',
					function resultHandler(data){
						var message = "";
						if(state == 0){
							if(data == 'failed'){
								message = "设置用户手机号码显示失败！";
							}else if(data == 'success'){
								message = "设置用户手机号码显示成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "程序错误，设置失败！";
							}
						}else{
							if(data == 'failed'){
								message = "屏蔽用户手机号码失败！";
							}else if(data == 'success'){
								message = "屏蔽用户手机号码成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "程序错误，设置失败！";
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
					<c:if test="${isMobile }"><img src="${pageContext.request.contextPath}/media/image/check_failed.png"></c:if>
					<c:if test="${!isMobile }"><img src="${pageContext.request.contextPath}/media/image/check_success.png"></c:if>
					个人信息隐私
				</div>
				<div class="float-right">
					<c:if test="${isMobile }">已屏蔽</c:if>
					<c:if test="${!isMobile }">未屏蔽</c:if>
				</div>
			</div>
			<div class="panel-title">
				<span>介绍</span>
			</div>
			<div class="instraction">
				<p>1.体重营养管理系统的体重门诊、血糖门诊中手机号码信息是用户的个人隐私，为了维护用户的信息安全，将屏蔽用户的手机号码中间四位号码。</p>
			</div>
			<hr />
			<div class="panel-btn">
				<c:if test="${!isMobile }">
					<button type="button" class="btn btn-danger service-operate" value="1">屏蔽号码</button>
				</c:if>
				<c:if test="${isMobile }">
					<button type="button" class="btn btn-success service-operate" value="0">显示号码</button>
				</c:if>
			</div>
		</div>
	</div>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>