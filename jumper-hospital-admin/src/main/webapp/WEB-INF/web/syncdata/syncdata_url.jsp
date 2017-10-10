<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<!-- 检测数据头部url "${pageContext.request.contextPath}/monitor/weight?userId=${userId}" userId=${params.userId}-->
</head>
					<ul id="myTab" class="nav nav-tabs">
   							<li><a href="${pageContext.request.contextPath}/pregnant/showArchiveBaseInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}">基本信息</a></li>
							<li><a href="${pageContext.request.contextPath}/pregnant/showArchiveHistoryTakingInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}">病史询问</a></li>
							<li> <a href="${pageContext.request.contextPath}/pregnant/showArchiveTestCaseInfo?archiveId=${archiveId}&archiveBaseId=${archiveBaseId}">初检情况</a> </li>
                           	<li> <a  class="active2" href="javascript:void(0);" >监测数据</a></li>
				            <li><a href="${pageContext.request.contextPath}/PregnantEnd/lookIndex?userId=${params.userId}&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" >分娩结局</a></li>
				            <li><a href="${pageContext.request.contextPath}/postnatal/vistRecord?userId=${params.userId}&visitType=1&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}">产后访视</a></li>
				            <li><a href="${pageContext.request.contextPath}/neonatal/lookIndex?userId=${params.userId}&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" >新生儿访视</a></li>
					</ul>
</html>