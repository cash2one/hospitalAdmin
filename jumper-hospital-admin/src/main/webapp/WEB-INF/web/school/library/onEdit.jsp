<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/ajaxfileupload.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/school/offEdit.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/school/onSave" method="POST" id="edit_on_line">
		<input type="hidden" name="id" id="course_id" value="${course.id }" />
		<input type="hidden" name="libraryType" id="typeValue" value="0" />
		<div class="panel panel-default">
        	<div class="panel-body">
        		<div class="alert alert-danger hide on-line-error" role="alert">课程名称不能为空！</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">&nbsp;</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">课程图片</label>
			    	<img style="width:320px;height:150px" src="${filePath }${empty course.courseImage?'/group1/M00/01/A1/CgADQ1bqGTmAYBsLAABuFBLeJgI643.png':course.courseImage }" />
			    	<input type="hidden" name="courseImage" value="${course.courseImage }" />
			    	<input type="file" id="course_image" name="file" style="display: initial;" <c:if test="${operateType == 0 }">disabled</c:if> class="btn btn-default" />
			    	&nbsp;&nbsp;<span style="color:red">图片尺寸：640*300像素，大小不超过2M</span>
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">*</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">课程名称</label>
			    	<input type="text" id="course_name" class="form-control" <c:if test="${operateType == 0 }">disabled</c:if> style="width:400px" value="${course.courseName }" name="courseName" datatype="*" nullmsg="课程名称不能为空!" errormsg="课程名称不能为空!" />
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">*</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">授课医师</label>
			    	<select name="courseDoctor" class="form-control doctor-select" <c:if test="${operateType == 0 }">disabled</c:if> style="width:400px" datatype="*" nullmsg="请填写授课医师!" errormsg="请填写授课医师!">
			    		<c:if test="${commonDoctor != null }">
			    			<c:forEach var="cd" items="${commonDoctor }">
			    				<option value="${cd.doctorName }" t="${cd.doctorTitle }">${cd.doctorName }</option>
			    			</c:forEach>
			    		</c:if>
			    	</select>
			    	<c:if test="${operateType != 0 }">
			    		<button type="button" id="common_doctor_btn" class="my-btn">
				    		<i class="icon-plus" style="color:#72D7CF"></i>常用医生模板
				    	</button>
			    	</c:if>
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">&nbsp;</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">医师职称</label>
			    	<input type="text" class="form-control doctor-title-input" readonly="readonly" style="width:400px" name="courseDoctorTitle" value="${course.courseDoctorTitle }" />
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">*</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">视频地址</label>
			    	<textarea rows="4" class="form-control" <c:if test="${operateType == 0 }">disabled</c:if> style="width:400px" name="courseUrl" datatype="*" nullmsg="网络课程视频地址不能为空!" errormsg="网络课程视频地址不能为空!">${course.courseUrl }</textarea>
			    	<a href="${pageContext.request.contextPath}/movie_desc.html" target="_black" class="my-btn" style="course:point">视频地址填写操作说明</a>
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">&nbsp;</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">课程简介</label>
			    	<textarea rows="4" class="form-control" <c:if test="${operateType == 0 }">disabled</c:if> placeholder="0-180个字" errormsg="课程简介不能超过180个字!" style="width:400px" datatype="*0-180" name="courseDesc">${course.courseDesc }</textarea>
			  	</div>
			  	<c:if test="${operateType != 0 }">
			  		<div style="text-align:center;margin-top:20px">
				  		<button type="button" class="btn btn-default" onclick="javascript:history.go(-1);" style="width:120px">&nbsp;取&nbsp;消&nbsp;</button>
				  		<!-- 提交表单的按钮 -->
				  		<button type="submit" class="btn btn-danger" id="save_on_line" style="width:120px;display:none;">&nbsp;保&nbsp;存&nbsp;</button>
				  		<!-- 用于判断课程名是否重复的 按钮 -->
				  		<button type="button" class="btn btn-danger" id="save_on_line1" style="width:120px">&nbsp;保&nbsp;存&nbsp;</button>
				  	</div>
			  	</c:if>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/school/school_dialog.jsp"></jsp:include>