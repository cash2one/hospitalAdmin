<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/ajaxfileupload.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/school/offEdit.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/school/offSave" method="POST" id="edit_off_line">
		<input type="hidden" name="id" id="course_id" value="${course.id }" />
		<input type="hidden" name="libraryType" id="typeValue" value="0" />
		<input type="hidden" id="old-appoint" value="${course.appointCount }" />
		<div class="panel panel-default">
        	<div class="panel-body">
        		<div class="alert alert-danger hide off-line-error" role="alert">课程名称不能为空！</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">&nbsp;</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">课程图片</label>
			    	<img style="width:60px;height:60px" src="${filePath }${empty course.courseImage?'/group1/M00/01/A1/CgADQ1bqGF6AIWzrAAAUID1oyQE918.png':course.courseImage }" />
			    	<input type="hidden" name="courseImage" value="${course.courseImage }" />
			    	<input type="file" id="course_image" name="file" style="display: initial;" <c:if test="${operateType == 0 }">disabled</c:if> class="btn btn-default" />
			    	&nbsp;&nbsp;<span style="color:red">图片长宽1:1，最小80*80像素</span>
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">*</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">课程名称</label>
			    	<input type="text" id="course_name" class="form-control" <c:if test="${operateType == 0 }">disabled</c:if> style="width:400px" value="${course.courseName }" name="courseName" datatype="*1-25" nullmsg="课程名称不能为空!" errormsg="课程名称不能超过25个字!" />
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">*</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">授课医师</label>
			    	<select name="courseDoctor" class="form-control doctor-select" <c:if test="${operateType == 0 }">disabled</c:if> style="width:400px" datatype="*" nullmsg="请填写授课医师!" errormsg="请填写授课医师!">
			    		<c:if test="${commonDoctor != null }">
			    			<c:forEach var="cd" items="${commonDoctor }">
			    				<option value="${cd.doctorName }" t="${cd.doctorTitle }" <c:if test="${cd.doctorName == course.courseDoctor }">selected</c:if>>${cd.doctorName }</option>
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
			  	<div class="form-group form-inline cost-div">
			  		<span style="color:red">*</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">课程费用</label>
			    	<input type="radio" name="costType" <c:if test="${operateType == 0||operateType == 2 }">disabled</c:if> <c:if test="${course.courseCost == 0 || course == null }">checked</c:if> value="0"> 免费课程
			    	<input type="radio" name="costType" <c:if test="${operateType == 0||operateType == 2 }">disabled</c:if> <c:if test="${course.courseCost > 0 }">checked</c:if> value="1"> 付费课程&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	<input type="text" class="form-control" id="cost-input" disabled style="width:100px" name="courseCost" value="${course == null ? 0.0 : course.courseCost }" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" errormsg="格式错误，请输入正确的金额!" /> 元
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">*</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">预约名额</label>
			    	<!-- 在修改线下课程时，费用和预约名额是不可修改的 -->
			    	<input type="text" class="form-control" id="appoint-input" <c:if test="${operateType==0 }">disabled</c:if> style="width:400px" value="${course.appointCount }" name="appointCount" datatype="/^[1-9]\d{0,5}$/" nullmsg="预约总人数不能为空!" errormsg="预约总人数必须为1-6长度的整数!" /> 人
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">*</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">上课地址</label>
			    
			    	<input type="text" class="form-control" id="common_address_input" <c:if test="${operateType == 0 }">disabled</c:if> style="width:400px"
			    	  name="courseAddress"
			    	  value="${operateType==1?address:operateType==2||operateType==0?course.courseAddress:'' }"
			  
			    	  datatype="*1-25" nullmsg="上课地址不能为空!" errormsg="上课地址不能超过25个字!" />
			    	<c:if test="${operateType != 0 }">
				    	<button type="button" id="commonAddress" class="my-btn">
				    		<i class="icon-link" style="color:#72D7CF"></i>常用地址模板
				    	</button>
				    </c:if>
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">&nbsp;</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">课程简介</label>
			    	<textarea rows="4" class="form-control" <c:if test="${operateType == 0 }">disabled</c:if> placeholder="0-180个字" style="width:400px" name="courseDesc" datatype="*0-180">${operateType==1?lastCourseDesc:operateType==2||operateType==0?course.courseDesc:'' }</textarea>
			  	</div>
			  	<div class="form-group form-inline">
			  		<span style="color:red">&nbsp;</span>
			    	<label class="col-sm-1" for="exampleInputEmail2">注意事项</label>
			    	<textarea rows="4" class="form-control common_notice_area" <c:if test="${operateType == 0 }">disabled</c:if> placeholder="0-180个字" style="width:400px" name="courseNotice" datatype="*0-180" errormsg="注意事项不能超过180个字!">${operateType==1?lastCourseNotice:operateType==2||operateType==0?course.courseNotice:'' }</textarea>
			    	<c:if test="${operateType != 0 }">
				    	<button type="button" id="commonNotice" class="my-btn">
				    		<i class="icon-warning-sign" style="color:#72D7CF"></i>注意事项模板
				    	</button>
			    	</c:if>
			  	</div>
			  	<c:if test="${operateType != 0 }">
			  		<div style="text-align:center;margin-top:20px">
				  		<button type="button" class="btn btn-default" onclick="javascript:history.go(-1);" style="width:120px">&nbsp;取&nbsp;消&nbsp;</button>
				  		<!-- 真正的用于提交数据的按钮 -->
				  		<button  class="btn btn-danger" type="submit" id="save_off_line" style="display: none" style="width:120px">&nbsp;保&nbsp;存&nbsp;</button>
				  		<!-- 提交前用来判断课程名是否相同，用户可以选择是否添加相同名称的课程 -->
				  		<button  class="btn btn-danger" type="button" id="save_off_line1" style="width:120px">&nbsp;保&nbsp;存&nbsp;</button>
				  	</div>
			  	</c:if>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/school/school_dialog.jsp"></jsp:include>