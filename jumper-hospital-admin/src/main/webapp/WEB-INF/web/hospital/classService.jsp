<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/media/css/service.css?${v }" rel="stylesheet">
<script type="text/javascript">
	$(function(){
		$(".class-service-operate").click(function(){
			var state = $(this).attr("value");
			if(state == 0){
				msg = "关闭后，关联医院的用户将看不到您发布的文章。确定关闭？";
			}else{
				msg = "确认开通课堂管理服务？";
			}
			App.confirm_show("", msg, function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/hos/classOperate',
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
					<c:if test="${isClass }"><img src="${pageContext.request.contextPath}/media/image/check_success.png"></c:if>
					<c:if test="${!isClass }"><img src="${pageContext.request.contextPath}/media/image/check_failed.png"></c:if>
					课堂管理
				</div>
				<div class="float-right">
					<c:if test="${isClass }">已开通</c:if>
					<c:if test="${!isClass }">未开通</c:if>
				</div>
			</div>
			<div class="panel-title">
				<span>介绍</span>
			</div>
			<div class="instraction">
				<p>1、开通后医院可以自行撰写，审核资讯，并推送给关联医院的用户群体。</p>
				<p>2、开通后，天使医生系统更新的文章会同步到医院的资讯素材库，您可以进行审核、修改或发布。</p>
				<p>3、不开通或关闭后，将由天使医生系统进行资讯更新。</p>
			</div>
			
			<div class="panel-title">
				<span>温馨提示</span>
			</div>
			<div class="instraction">
				<p>1、为了维护医院的良好形象，开通后，建议每周进行4次以上的更新。</p>
				<p>2、开通后，建议至少在每个已开通的频道下推送一篇文章。</p>
			</div>
			
			<hr />
			<div class="panel-btn">
				<c:if test="${!isClass }">
					<button type="button" class="btn btn-danger class-service-operate" value="1">开通服务</button>
				</c:if>
				<c:if test="${isClass }">
					<button type="button" class="btn btn-success class-service-operate" value="0">关闭服务</button>
				</c:if>
			</div>
		</div>
	</div>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>