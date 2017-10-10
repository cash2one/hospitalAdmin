<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/service.css?${v }">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/ajaxfileupload.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/hospital.js?${v }"></script>
<div class="col-xs-12 col-sm-9">
    <div class="panel panel-default">
        <div class="panel-body">
        	<input type="hidden" name="id" id="hospitalId" value="${hospitalInfo.id }" />
            <div class="form-group">
                <label style="margin-right: 20px;">医院名称</label>
                <input type="text" class="col-sm-8" id="hospitalName" value="${hospitalInfo.name }" style="border:none" readonly></input>
            </div>
            <div class="form-group">
                <label style="margin-right: 20px;">医院地址</label>
                <input type="text" class="col-sm-8" id="hospitalAddress" value="${hospitalInfo.address }" style="border:none" readonly></input>
            </div>
            <div class="form-group">
                <label style="margin-right: 20px;">医院电话</label>
                <input type="text" class="col-sm-8" id="hospitalPhone" value="${hospitalInfo.phone }" style="border:none" readonly></input>
            </div>
            <div class="form-group">
                <label style="margin-right: 20px;">医院介绍</label>
                <textarea rows="6" class="col-sm-10" id="hospitalDesc" style="border:none;vertical-align: middle;" readonly>${hospitalInfo.introduction }</textarea>
            </div>
            <div class="form-group">
                <label style="margin-right: 20px;">医院照片</label>
                <c:if test="${hospitalInfo.imgUrl == '' }">
                 	<img style="width:180px;height:120px" id="hospitalImg" src="${pageContext.request.contextPath}/media/image/default.jpg">
                </c:if>
                <c:if test="${hospitalInfo.imgUrl != ''}">
               	    <img style="width:180px;height:120px" id="hospitalImg" value="${hospitalInfo.imgUrl }" src="${filePath }${hospitalInfo.imgUrl }">
                </c:if>
                
            </div>
			<div style="position: absolute;top: 90px;right: 120px;">
				<button type="button" class="btn btn-danger update-hospital">修改医院信息</button>
			</div>

            <div class="panel-title-both" style="margin-top: 40px;">
                <div class="float-left both-left">
                    <span>科室描述</span>
                </div>
                <div class="float-right both-right">
                    <button type="button" class="btn btn-link desc-add"><i class="icon-pencil"></i>&nbsp;新增</button>
                </div>
            </div>
            <div class="clearFix"></div>
            <hr style="margin-top:0">
            
            <c:forEach var="desc" items="${officeDescs }" varStatus="status">
            	<c:if test="${!status.last }">
            		<div>
		                <p>${desc.content }</p>
		                <img src="${filePath }${desc.imgUrl }">
		                <div style="float:right;margin-right:50px;margin-bottom:16px">
		                    <button type="button" class="btn btn-danger desc-del-btn" value="${desc.id }">删除</button>
		                    <button type="button" class="btn btn-success desc-update-btn" value="${desc.id }-${desc.imgUrl }">修改</button>
		                </div>
		            </div>
		            <div class="clearFix"></div>
		            <hr>
            	</c:if>
            	<c:if test="${status.last }">
            		<div>
		                <p>${desc.content }</p>
		                <img src="${filePath }${desc.imgUrl }">
		                <div style="float:right;margin-right:50px;margin-bottom:16px">
		                    <button type="button" class="btn btn-danger desc-del-btn" value="${desc.id }">删除</button>
		                    <button type="button" class="btn btn-success desc-update-btn" value="${desc.id }-${desc.imgUrl }">修改</button>
		                </div>
		            </div>
            	</c:if>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>