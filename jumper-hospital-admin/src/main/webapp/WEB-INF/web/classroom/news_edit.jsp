<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/media/umeditor/themes/default/css/umeditor.css?${v }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/media/umeditor/umeditor.config.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/umeditor/umeditor.min.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/ajaxfileupload.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/classroom/news.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/classroom/editInfo" method="POST" id="edit_news">
		<input type="hidden" name="id" id="news_id" value="${news.id }" />
		<input type="hidden" name="publish" id="is_publish" value="0" />
		<input type="hidden" name="belong" id="belong-hidden" value="${empty belong ? hospitalInfo.id : belong }" />
		<input type="hidden" name="period" id="period-hidden" value="" />
		<div class="panel panel-default">
        	<div class="panel-body" style="margin-left: 20px;">
        		<div class="alert alert-danger hide edit-news-error" role="alert">课程名称不能为空！</div>
        		<div class="form-group form-inline">
			  		<span style="color:red">*</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">新闻标题</label>
			    	<input type="text" id="title" class="form-control" style="width:695px" name="title" value="${news.title }" datatype="*1-32" nullmsg="新闻标题不能为空!" errormsg="新闻标题不能超过32个字!" />
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">&nbsp;</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">作者</label>
			    	<input type="text" id="sourceFrom" class="form-control" style="width:695px" name="sourceFrom" datatype="*0-16" value="${news.sourceFrom }" errormsg="新闻作者不能超过16个字!" />
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">&nbsp;</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">作者照片</label>
			    	<img style="width:60px;height:60px" src="${filePath }${empty news.fromLogoUrl?'':news.fromLogoUrl }" />
			    	<input type="hidden" name="fromLogoUrl" value="${news.fromLogoUrl }" />
			    	<input type="file" id="from_logo_url" name="file" style="display:initial;width:630px;" class="btn btn-default" />
			    	&nbsp;&nbsp;<span style="color:red">尺寸：80*80像素，大小不超过200kb</span>
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red;float:left">*</span>
			    	<label class="col-sm-1" style="float:left">新闻内容</label>
			    	<div style="float:left">
			    		<script type="text/plain" id="myEditor" name="content" style="width:700px;height:300px">${news.content }</script>
			    	</div>
			  	</div>
			  	<div class="clearfix"></div>
			  	<div class="form-group form-inline" style="margin-top: 15px;">
			  		<span style="color:red">*</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">首页图片</label>
			    	<img style="width:128px;height:64px" src="${filePath }${empty news.imageUrl?'':news.imageUrl }" />
			    	<input type="hidden" name="imageUrl" value="${news.imageUrl }" datatype="*" nullmsg="首页图片不能为空!" />
			    	<input type="file" id="image_url" name="file" style="display:initial;width:562px;" class="btn btn-default" />
			    	&nbsp;&nbsp;<span style="color:red">尺寸：640*300像素，大小不超过2M</span>
			  	</div>
			  	<div class="form-group form-inline period-div">
			  		<span style="color:red">*</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">选择用户群</label>
					<label class="checkbox-inline">
					  	<input type="checkbox" name="periods" value="1" <c:if test="${fn:contains(news.period, '1')}">checked</c:if> datatype="*" nullmsg="请选择用户群！"> 孕早期
					</label>
					<label class="checkbox-inline">
					  	<input type="checkbox" name="periods" value="2" <c:if test="${fn:contains(news.period, '2')}">checked</c:if>> 孕中期
					</label>
					<label class="checkbox-inline">
					  	<input type="checkbox" name="periods" value="3" <c:if test="${fn:contains(news.period, '3')}">checked</c:if>> 孕晚期
					</label>
					<label class="checkbox-inline">
					  	<input type="checkbox" name="periods" value="4" <c:if test="${fn:contains(news.period, '4')}">checked</c:if>> 0-6月
					</label>
					<label class="checkbox-inline">
					  	<input type="checkbox" name="periods" value="5" <c:if test="${fn:contains(news.period, '5')}">checked</c:if>> 6月-1岁
					</label>
					<label class="checkbox-inline">
					  	<input type="checkbox" name="periods" value="6" <c:if test="${fn:contains(news.period, '6')}">checked</c:if>> 1-3岁
					</label>
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">*</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">所属频道</label>
			    	<select name="channelId" class="form-control chanel-select" style="width:695px" datatype="*" nullmsg="请选择频道!" errormsg="请选择频道!">
			    		<option value="">--请选择频道--</option>
			    		<c:if test="${chanels != null }">
			    			<c:forEach var="chanel" items="${chanels }">
			    				<option value="${chanel.id }" <c:if test="${news.channelId == chanel.id }">selected</c:if>>${chanel.chanelName }</option>
			    			</c:forEach>
			    		</c:if>
			    	</select>
			  	</div>
		  		<div style="text-align:center;margin-top:20px">
			  		<button type="button" class="btn btn-default news_edit_cancel" style="width:120px">&nbsp;取&nbsp;消&nbsp;</button>
			  		<!-- 真正的用于提交数据的按钮 -->
			  		<button class="btn btn-info" type="submit" id="news_save" style="width:120px">&nbsp;保&nbsp;存&nbsp;</button>
			  		<button class="btn btn-success" type="button" id="news_save_publish" style="width:120px">&nbsp;发&nbsp;布&nbsp;</button>
			  		<button type="submit" id="save_publish_btn" class="hide"></button> 
			  	</div>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>