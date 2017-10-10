<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<style type="text/css"">
	table tr td{
		font-size: 15px;
		height:30px;
	}
	table tr td:FIRST-CHILD {
		width:16%;
	}
</style>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<div class="panel panel-default">
	  <div class="panel-heading">用户详情</div>
	  <div class="panel-body">
	    <table>
	    	<tr>
	    		<td>昵称</td>
	    		<td>${user.nickName }</td>
	    	</tr>
	    	<tr>
	    		<td>真实姓名</td>
	    		<td>${user.userExitInfo.realName }</td>
	    	</tr>
	    	<tr>
	    		<td>年龄</td>
	    		<td>${user.userExitInfo.age }</td>
	    	</tr>
	    	<tr>
	    		<td>手机号码</td>
	    		<td>${user.userExitInfo.contactPhone }</td>
	    	</tr>
	    	<tr>
	    		<td>所在地区</td>
	    		<td>${user.province.proName }${user.city.cityName }</td>
	    	</tr>
	    	<tr>
	    		<td>体重</td>
	    		<c:if test="${empty user.userExitInfo.weight }"><td>暂无数据</td></c:if>
	    		<c:if test="${not empty user.userExitInfo.weight }"><td>${user.userExitInfo.weight }kg</td></c:if>
	    	</tr>
	    	<tr>
	    		<td>身高</td>
	    		<c:if test="${empty user.userExitInfo.height }"><td>暂无数据</td></c:if>
	    		<c:if test="${not empty user.userExitInfo.height }"><td>${user.userExitInfo.height }cm</td></c:if>
	    	</tr>
	    	<tr>
	    		<td>状态</td>
	    		<td>
	    			<c:if test="${user.userExitInfo.currentIdentity == 0 }">怀孕中</c:if>
	    			<c:if test="${user.userExitInfo.currentIdentity == 1 }">已有宝宝</c:if>
	    		</td>
	    	</tr>
	    	<tr>
	    		<c:if test="${user.userExitInfo.currentIdentity == 0 }">
	    			<td>预产期</td>
	    			<td><fmt:formatDate value="${user.expectedDateOfConfinement }" type="date"/></td>
	    		</c:if>
	    		<c:if test="${user.userExitInfo.currentIdentity == 1 }">
	    			<td>宝宝生日</td>
	    			<td><fmt:formatDate value="${user.userExitInfo.babyBirthday }" type="date"/></td>
	    		</c:if>
	    	</tr>
	    </table>
	  </div>
</div>

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>