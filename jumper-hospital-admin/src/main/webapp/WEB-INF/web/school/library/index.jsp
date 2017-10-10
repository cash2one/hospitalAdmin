<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/school/library.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/school/library" method="POST" id="form">
	    <input type="hidden" name="libraryTypeTemp" id="libraryTypeTemp" value="${libraryType }" title="用来记录之前的type类型：判断是否出现类型切换"/>
		<input type="hidden" name="libraryType" id="typeValue" value="${libraryType }" />
		<div class="panel panel-default">
        	<div class="panel-body">
				<a type="button" href="${pageContext.request.contextPath}/school/offEdit?libraryType=${libraryType}&operateType=1" class="btn btn-title search-btn" title="添加课程">添加课程</a>
				<shiro:hasPermission name="hospital:remote:setting">
					<c:if test="${!isSchool }">
						<div style="float:right">
			            	暂未开通孕妇学校服务！<a href="${pageContext.request.contextPath}/hos/school">立即开通？</a>
			            </div>
					</c:if>
				</shiro:hasPermission>
	        </div>
	    </div>
    	<div class="panel panel-default">
        	<div class="panel-body">
				<ul class="nav nav-tabs no-border-margin" style="margin-bottom:20px;width:50%;display:inline">
	        		<li <c:if test="${libraryType == 0 }">class="current"</c:if>><a href="#" class="library_type" value="0">线下课程</a></li>
					<li <c:if test="${libraryType == 1 }">class="current"</c:if>><a href="#" class="library_type" value="1">网络课程</a></li>
				</ul>
				<div class="col-lg-3" style="float:right">
	                <input type="text" class="form-control" name="searchKey" placeholder="课程编码\课程名称\授课医师" value="${searchKey }" style="display:inline;width:230px" />
	                &nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-info" type="submit" style="display:inline"><i class="icon-search"></i>查询</button>
	            </div>
				<!-- 线下课程 -->
				<c:if test="${libraryType == 0 }">
					<table class="bordered">
		                <thead>  
		                    <tr>
		                        <th>课程编号</th>
		                        <th>课程图片</th>
		                        <th>课程名称</th>
		                        <th>授课医师</th>
		                        <th>医师职称</th>
		                        <th>预约名额</th>
		                        <th>课程费用</th>
		                        <th>上课地址</th>
		                        <th>操作</th>
		                    </tr>
		                </thead>
		                <c:if test="${offLine != null && offLine.result != null && offLine.totalCount > 0 }">
		                	<c:forEach var="off" items="${offLine.result }">
		                		<tr>
				                    <td>${off.courseNo }</td>
				                    <td style="text-align: center"><img style="width:60px;height:60px" src="${filePath }${off.courseImage }"></td>
				                    <td>${off.courseName }</td>
				                    <td>${off.courseDoctor }</td>
				                    <td>${off.courseDoctorTitle }</td>
				                    <td>${off.appointCount }</td>
				                    <td>
				                    	<c:if test="${off.courseCost == 0 }">免费</c:if>
				                    	<c:if test="${off.courseCost > 0 }">${off.courseCost }元</c:if>
				                    </td>
				                    <td>${off.courseAddress }</td>
				                    <td>
				                    	<a role="button" class="btn btn-info btn-sm" title="查看" href="${pageContext.request.contextPath}/school/offEdit?recordId=${off.id }&libraryType=${libraryType}&operateType=0" ><i class="icon-folder-open-alt"></i> 查看</a>
				                        <a role="button" class="btn btn-success btn-sm" title="编辑" href="${pageContext.request.contextPath}/school/offEdit?recordId=${off.id }&libraryType=${libraryType}&operateType=2" ><i class="icon-folder-open-alt"></i> 编辑</a>
				                    	<a role="button" class="btn btn-danger btn-sm off-delete" title="删除" href="#" value="${off.id }"><i class="icon-folder-open-alt"></i> 删除</a>
				                    </td>
				                </tr>
		                	</c:forEach>
		                </c:if>
		                <c:if test="${offLine == null || offLine.result == null || offLine.totalCount <= 0 }">
				      		<tr>
				      			<td colspan="9" style="color:red">暂无记录</td>
				      		</tr>
				      	</c:if>
		            </table>
		            <jsp:include page="../../common/page.jsp"></jsp:include>
				</c:if>
				<!-- 网络课程 -->
				<c:if test="${libraryType == 1 }">
					<table class="bordered">
		                <thead>  
		                    <tr>
		                        <th>课程编号</th>
		                        <th>课程图片</th>
		                        <th>课程名称</th>
		                        <th>授课医师</th>
		                        <th>医师职称</th>
		                        <th style="width:40%">课程简介</th>
		                        <th>操作</th>
		                    </tr>
		                </thead>
		                <c:if test="${onLine != null && onLine.result != null && onLine.totalCount > 0 }">
		                	<c:forEach var="on" items="${onLine.result }">
		                		<tr>
				                    <td>${on.courseNo }</td>
				                    <td style="text-align:center"><img style="width:90px;height:60px" src="${filePath }${on.courseImage }"></td>
				                    <td>${on.courseName }</td>
				                    <td>${on.courseDoctor }</td>
				                    <td>${on.courseDoctorTitle }</td>
				                    <td>${on.courseDesc }</td>
				                    <td>
				                    	<a role="button" class="btn btn-info btn-sm" title="详情" href="${pageContext.request.contextPath}/school/onDetail?id=${on.id }" target="_black"> 详情</a>
				                        <a role="button" class="btn btn-success btn-sm" title="编辑" href="${pageContext.request.contextPath}/school/offEdit?recordId=${on.id }&libraryType=${libraryType}&operateType=2" > 编辑</a>
				                    	<a role="button" class="btn btn-danger btn-sm on-delete" title="删除" href="#" value="${on.id }"> 删除</a>
				                    </td>
				                </tr>
		                	</c:forEach>
		                </c:if>
		                <c:if test="${onLine == null || onLine.result == null || onLine.totalCount <= 0 }">
				      		<tr>
				      			<td colspan="7" style="color:red">暂无记录</td>
				      		</tr>
				      	</c:if>
		            </table>
		            <jsp:include page="../../common/page.jsp"></jsp:include>
				</c:if>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>