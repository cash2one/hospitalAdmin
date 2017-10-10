<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/media/css/daterangepicker-bs3.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/jquery.autocomplete.css?${v }" />

<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.autocomplete.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/admin.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/admin/index" method="POST" id="form"  class="form-inline">
        <input type="hidden" id="type" name="type" value="${type }" />
    	<div class="panel panel-default">
        	<div class="panel-body">
        		<button type="button" class="btn btn-info add-admin"><i class="icon-plus"></i> 新增医生账号</button>
        		<div class="input-group" style="margin-left: 42%;">
        		    <div class="form-group">
								    	<select style="width:140px;height: 34px;border-color: #ccc;"  id="hospitalMajorid" name="hospitalMajorid" >
								    	     <option value="">全部科室</option>
									    	      <c:if test="${!empty majorInfosList }">
											    	<c:forEach items="${majorInfosList }" var="majorInfosList" >
													    	<c:if test="${!empty hospitalMajorid }">
			                                                      <option value="${majorInfosList.id}"  ${majorInfosList.id==hospitalMajorid?'selected':''} >${majorInfosList.hospitalDoctorMajor.major}</option>
															</c:if>
															 <c:if test="${empty hospitalMajorid }">
															      <option value="${majorInfosList.id}">${majorInfosList.hospitalDoctorMajor.major}</option>
															 </c:if>
											    	   
											    	</c:forEach>
											      </c:if>
									    	</select>
						</div>	
						<div class="form-group">		    	
						    <select  style="width:140px;margin-left:30px;height: 34px;border-color: #ccc;" id="titleid" name="titleid" >
						       <option value="">全部职称</option>
							    <c:if test="${!empty hospitalDoctorTitle }">
							    	<c:forEach items="${hospitalDoctorTitle }" var="hospitalDoctorTitle">
							    	   <c:if test="${!empty titleid }">
				                                      <option value="${hospitalDoctorTitle[0]}" ${hospitalDoctorTitle[0] == titleid?'selected':''} >${hospitalDoctorTitle[2] }</option>
							    	   </c:if>
							    	   <c:if test="${empty titleid }">
							    	      <option value="${hospitalDoctorTitle[0]}">${hospitalDoctorTitle[2] }</option>
							    	   </c:if>
							    	   
							    	</c:forEach>
							    </c:if>
					    	</select>
					    </div>
							    	
					 </div>
	            <div class="col-lg-3" style="float:right">
	                <div class="input-group">
	                    <input type="text" class="form-control" name="username" placeholder="姓名/登录账号" value="${username }" />
	                    <span class="input-group-btn">
	                        <button class="btn btn-danger" type="submit"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	            </div>
				<hr style="margin:20px 0px;">
	            <table class="bordered">
	                <thead>  
	                    <tr>
	                        <th>账户名</th>
	                        <th>是否启用</th>
	                        <th>是否锁定</th>
	                        <th>上次登录</th>
	                        <th>所属医院</th>
	                         <th>所属科室</th>
	                        <th>职称</th>
	                        <th>手机号码</th>
	                        <th>姓名</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result}" var="data">
	                	<tr>
		                    <td>${data.username }</td>
		                    <td>
		                    	<c:if test="${data.isEnabled }">已启用</c:if>
								<c:if test="${!data.isEnabled }">未启用</c:if>
		                    </td>
		                    <td>
		                    	<c:if test="${data.isLocked }">已锁定</c:if>
								<c:if test="${!data.isLocked }">未锁定</c:if>
		                    </td>
		                    <td><fmt:formatDate value="${data.loginDate }" type="both"/></td>
		                    <td>${data.hospitalInfo.name }</td>
		                    <td>${data.hospitalDoctorMajor.major }</td>
		                    <td>${data.hospitalDoctorTitle.name }</td>
		                    <td>${data.mobile }</td>
		                    <td>${data.name }</td>
		                    <td>
		                        <button type="button" class="btn btn-danger btn-sm update-admin" title="编辑" value="${data.id }|${data.username }|${data.password }|${data.name }|${data.mobile }|${data.hospitalDoctorTitle.id }|${data.hospitalMajorInfo.id }|${data.introduction }|${data.isHospitalNst }"><i class="icon-pencil"></i> 编辑</button>
		                        <c:if test="${data.isLocked }">
		                        	<button type="button" class="btn btn-warning btn-sm unlock" title="解锁" value="${data.id }"><i class="icon-wrench"></i> 解锁</button>
		                        </c:if>
		                        <c:choose>
		                        	<c:when test="${data.isEnabled }">
		                        		<button type="button" class="btn btn-info btn-sm disable" title="禁用" value="${data.id }"><i class="icon-lock"></i> &nbsp;禁用</button>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<button type="button" class="btn btn-info btn-sm useable" title="启用" value="${data.id }"><i class="icon-unlock"></i> 启用</button>
		                        	</c:otherwise>
		                        </c:choose>
		                        <c:if test="${resetPas }">
		                        	<button type="button" class="btn btn-info btn-sm disable" title="重置密码" value="${data.id }" onclick="resetPas(${data.id });"><i class="icon-lock"></i> &nbsp;重置密码</button>
		                        </c:if>
		                    </td>
		                </tr>
	                </c:forEach>
	                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="10" style="color:red">暂无记录</td>
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
<jsp:include page="/WEB-INF/web/admin/admin_dialog.jsp"></jsp:include>