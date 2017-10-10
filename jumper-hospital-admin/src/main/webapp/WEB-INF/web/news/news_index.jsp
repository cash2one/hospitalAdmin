<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<!-- 页头引入 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/hospital.css?${v }">
<link href="${pageContext.request.contextPath}/media/umeditor/themes/default/css/umeditor.css?${v }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/media/umeditor/umeditor.config.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/umeditor/umeditor.min.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/ajaxfileupload.js?${v }"></script>
<script type="text/javascript">
	$(function() {
		/** 文件上传绑定 **/
		$("#chooseFile").live("change", {"element":"chooseFile"} , App.fileUpload);
		/** 百度富文本编辑器初始化 **/
		var um = UM.getEditor('myEditor', {
		    autoHeightEnabled: false,
		    autoFloatEnabled: true
		});
		/** 新增医院资讯 **/
		$("#addInformation").on("click", function(){
			$(".model-dialog-href").trigger("click");
		});
		/** 保存并推送消息 **/
		$(".save-and-push").on("click", {"type":"save-and-push"}, information.edit);
		/** 保存至未发布 **/
		$(".save-no-push").on("click", {"type":"save-no-push"}, information.edit);
		/** 删除资讯信息 **/
		$(".information-delete").on("click", information.del);
		/** 发布资讯信息 **/
		$(".info-publish").on("click", information.publish);
		/** 编辑资讯信息 **/
		$(".information-edit").on("click", information.update);
		/** 设置为广告 **/
		$(".ok_push").on("click", {"type":"ok_push"}, information.pushBanner);
		/** 取消设置为广告 **/
		$(".cancel_push").on("click", {"type":"cancel_push"}, information.pushBanner);
	});
	
	var information = function(){
		
		var editType = {
			"save-and-push" : 1,
			"save-no-push" : 2
		};
		
		var pushType = {
			"ok_push" : 1,
			"cancel_push" : 2
		};
	
		return {
			edit : function(event){
				var command = event.data.type;
				$("#edit_type").val(editType[command]);
				information.saveData();
			},
			saveData : function(){
				$("#edit-information-form").Validform({
					/** 自定义错误消息显示 **/
					tiptype:function(msg){
						App.validateErrorInit(".information-error", msg);
					},
					showAllError:false,
					tipSweep:true,
					ajaxPost:true,
					beforeSubmit:function(curform){
						/** 表单验证成功后的回调,取消错误验证消息 **/
						var content = $("#myEditor").text();
						if(content.trim() == ""){
							$(".information-error").text("资讯内容不能为空！");
							return false;
						}
						App.validateSuccess(".information-error");
					},
					callback:function(request, data){
						$(".info-cancel").trigger("click");
						if(request instanceof Object){
							var loginStatus = request.getResponseHeader("loginStatus");
							if(loginStatus == "accessDenied"){
								App.noPermission();
							}
						}else{
							var message = "";
							if(request == "success"){
								message = "编辑资讯信息成功！";
							}else if(request == "error"){
								message = "编辑资讯信息异常！";
							}else if(request == "paramsError"){
								message = "参数错误，请刷新浏览器重试！";
							}else if(request=="pushError"){
								message="发布资讯推送用户消息失败，已保存至未发布列表，请重新发布！";
							}else if(request == "failed"){
								message = "编辑线下课程信息失败！";
							}
							
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
								if(request == 'success'){
									location.reload();
								}else{
									return;
								}
							});
							setTimeout(function() {
								location.reload();
							}, 2000);
						}
					}
				});
			},
			del : function(){
				var id = $(this).attr("value");
				App.confirm_show("提示！", "确认删除资讯信息？", function(){
					$(".info-confirm-cancel").trigger("click");
					App.ajaxRequest('/information/delete', {'id':id}, 'get', true, 'json',
						function resultHandler(data){
							var message = "";
							if(data == 'failed'){
								message = "删除失败！";
							}else if(data == 'success'){
								message = "删除成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "系统错误，请重试！";
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
			},
			publish : function(){
				var id = $(this).attr("value");
				App.confirm_show("提示！", "确认发布该条资讯信息？", function(){
					$(".info-confirm-cancel").trigger("click");
					App.ajaxRequest('/information/publish', {'id':id}, 'get', true, 'json',
						function resultHandler(data){
							var message = "";
							if(data == 'failed'){
								message = "发布失败！";
							}else if(data == 'success'){
								message = "发布成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "系统错误，请重试！";
							}else if(data == 'pushError'){
								message="发布资讯推送用户消息失败，请重新发布！";
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
			},
			update : function(){
				var id = $(this).attr("value");
				var title = $(this).attr("t");
				var image = $(this).attr("i");
				var content = $(this).next().html();
				
				$("#info_id").val(id);
				$("#edit_type").val(editType["save-no-push"]);
				$("#update-title").val(title);
				$("#descImg").val(image);
				$("#myEditor").html(content);
				
				$(".info-add").hide();
				$(".info-update").show();
				
				$(".model-dialog-href").trigger("click");
			},
			pushBanner : function(event){
				var id = $(this).attr("value");
				var command = event.data.type;
				var type = pushType[command];
				
				var notice = type == 1 ? "是否推送医院广告？" : "是否取消推送医院广告？";
				App.confirm_show("提示！", notice, function(){
					$(".info-confirm-cancel").trigger("click");
					App.ajaxRequest('/information/banner', {'id':id, 'operate':type}, 'get', true, 'json',
						function resultHandler(data){
							var message = "";
							if(data == 'failed'){
								message = Number(type) == 1 ? "推送医院广告失败！" : "取消推送广告失败！";
							}else if(data == 'success'){
								message = Number(type) == 1 ? "推送医院广告成功！" : "取消推送广告成功！";
							}else if(data == 'paramsError'){
								message = "参数错误！";
							}else if(data == 'error'){
								message = "系统错误，请重试！";
							}else if(data == 'outCount'){
								message="您推送的广告数已达上限，请先撤下其它广告！";
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
			}
		};
	}();
</script>

<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/information/index" method="post" id="form">
		<input type="hidden" name="operate" value="${operate }" />
		<div class="panel panel-default">
			<div class="panel-body">
				<button type="button" class="btn btn-success add_btn" id="addInformation"><i class="icon-plus"></i> 新增资讯</button>
				<div class="col-lg-3" style="float: right">
					<div class="input-group">
						<input type="text" class="form-control" name="keywords" placeholder="请输入标题" value="${keywords }" /> 
						<span class="input-group-btn">
							<button id="submitBtn" class="btn btn-danger" type="submit"><i class="icon-search"></i></button>
						</span>
					</div>
				</div>
			</div>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-body">
				<div style="margin-bottom: 10px;">
					<a class="btn btn-default <c:if test="${operate == 1 }">btn-danger</c:if>" href="${pageContext.request.contextPath}/information/index">已发布列表</a>
					<a class="btn btn-default <c:if test="${operate == 2 }">btn-danger</c:if>" href="${pageContext.request.contextPath}/information/index?operate=2">未发布列表</a>
					<a class="btn btn-default <c:if test="${operate == 3 }">btn-danger</c:if>" href="${pageContext.request.contextPath}/information/index?operate=3">广告列表</a>
					<hr style="height: 1px; border: none; border-top: 1px solid #E0E0E0;" />
				</div>

				<div>
					<table class="bordered">
						<thead>
							<tr>
								<th>资讯标题</th>
								<th>首页图片</th>
								<c:if test="${operate == 2 }"><th>添加时间</th></c:if>
								<c:if test="${operate != 2 }"><th>发布时间</th></c:if>
								<th>操作</th>
							</tr>
						</thead>
						<c:if test="${!empty data && !empty data.result && fn:length(data.result) > 0}">
							<c:forEach items="${data.result}" var="info">
								<tbody>
									<tr>
										<td>
											<c:choose>
												<c:when test="${fn:length(info.title) > 50 }">
													${fn:substring(info.title, 0, 50) }...
												</c:when>
												<c:otherwise>
													${info.title }
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<img style="width:128px;height:60px" src="${BASE_PATH}/${info.imageUrl}" />
										</td>
										<td>
											<c:if test="${operate == 2 }">
												<fmt:formatDate value="${info.addTime}" type="both" />
											</c:if>
											<c:if test="${operate != 2 }">
												<fmt:formatDate value="${info.pushTime}" type="both" />
											</c:if>
										</td>
										<td>
											<a role="button" class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/information/detail?id=${info.id}" target="_blank">
												<i class="icon-folder-open-alt"></i> 详情
											</a>
											<button type="button" class="btn btn-success btn-sm information-edit" value="${info.id}" t="${info.title }" i="${info.imageUrl }">
												<i class="icon-pencil"></i> 修改
											</button>
											<div style="display:none">
												${info.content }
											</div>
											<button type="button" class="btn btn-danger btn-sm information-delete" value="${info.id }">
												<i class="icon-trash"></i> 删除
											</button>
											
											<c:choose>
												<c:when test="${info.pushStatus == 1}">
													<c:if test="${info.isBanner == 0}">
														<button type="button" class="btn btn-info btn-sm ok_push" value="${info.id }">
															<i class="icon-external-link"></i> 推送医院广告
														</button>
													</c:if>
													<c:if test="${info.isBanner == 1}">
														<button type="button" class="btn btn-warning btn-sm cancel_push" value="${info.id }">
															<i class="icon-reply"></i> 取消医院广告
														</button>
													</c:if>
												</c:when>
												<c:otherwise>
													<button type="button" class="btn btn-info btn-sm info-publish" title="发布" value="${info.id }">
														<i class="icon-external-link"></i> 发布
													</button>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</tbody>
							</c:forEach>
						</c:if>
						<c:if test="${data == null || data.result == null || data.totalCount <= 0 }">
							<tr>
								<td colspan="4" style="color: red">暂无记录</td>
							</tr>
						</c:if>
					</table>
					<jsp:include page="../common/page.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</form>
</div>

<!-- 编辑医院资讯信息Diaglog -->
<a class="btn btn-pink hide model-dialog-href" data-toggle="modal" data-target="#myModal"></a>
<div class="modal bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="top: 10%" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="min-width:900px">
			<form id="edit-information-form" action="${pageContext.request.contextPath}/information/edit" method="post" autocomplete="off">
				<input type="hidden" name="id" id="info_id" value="" />
				<input type="hidden" name="type" id="edit_type" value="" />
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title desc-confirm-title" id="confirmLabelTitle">资讯</h4>
				</div>
				<div class="alert alert-danger hide information-error" role="alert"></div>
				<div class="modal-body desc-confirm-body">
					<div class="form-group">
						<label class="col-sm-2 control-label inline-label font-normal"><span style="color:red">*&nbsp;</span>标题</label>
						<div class="col-sm-10 float-right">
							<input type="text" class="form-control" id="update-title" name="title" datatype="*1-80" nullmsg="资讯标题不能为空!" errormsg="资讯标题不能超过80字!">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label inline-label font-normal"><span style="color:red">*&nbsp;</span>首页图片</label>
						<div class="col-sm-10 float-right form-inline">
							<input id="chooseFile" name="file" type="file" style="display: none" />
							<input id="descImg" class="form-control" name="imageUrl" type="text" value="" readonly="readonly" datatype="*" nullmsg="请选择首页图片!" />
							<a class="btn btn-info upload" onclick="$('input[id=chooseFile]').click();">选择文件</a>
							<span style="color: red;">&nbsp;&nbsp;建议尺寸：640*300像素，大小不超过2M</span>
						</div>
					</div>

					<div class="form-group" style="width: 860px; height: 360px; overflow-x: auto">
						<label class="col-sm-3 control-label inline-label font-normal"><span style="color:red">*&nbsp;</span>正文</label>
						<div class="col-sm-10 float-right" id="content">
							<script type="text/plain" id="myEditor" style="width:700px;height:300px" name="content" datatype="*" nullmsg="资讯内容不能为空!"></script>
						</div>
					</div>

					<img src="" id="descPreview" style="width: 200px; display: none" />

				</div>
				<div class="modal-footer info-add">
					<button type="button" class="btn btn-default info-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger insert-btn save-and-push">保存并发布</button>
					<button type="submit" class="btn btn-danger insert-nopush save-no-push">保存至未发布</button>
				</div>
				<div class="modal-footer info-update hide">
					<button type="button" class="btn btn-default info-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger save-no-push">&nbsp;&nbsp;保&nbsp;存&nbsp;&nbsp;</button>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>