<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	Subject currentUser = SecurityUtils.getSubject();
	String user = currentUser.getPrincipal().toString();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="${pageContext.request.contextPath}/media/image/weblogo.png?${v }">
 
		<title>天使医生母婴监护健康管理系统</title>
		<style type="text/css">
			html{
				overflow-x: scroll;
			}
			body {
				background: #F0F0F0;
				min-width: 1600px;
				overflow-y: hidden;
			}
			.message-dialog{
				width: 800px;
				height: 500px;
			}
		</style>
 <!-- 左侧导航菜单 -->
        <div class="">
            <div class="" style="overflow: hidden;">
            	<!-- 监测管理 -->
            	<shiro:hasPermission name="remote:base">
	                <c:if test="${module == 'report'}">
	               	   <div class="sidebar-offcanvas" id="sidebar" style="padding-right: 20px;width:100%;">
	               	   	   <shiro:hasPermission name="remote:wait_audit">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'audit'}">active</c:if>" >
		                             <a href="${pageContext.request.contextPath}/report/audit" >
		                                <i class="icon-bookmark"></i>&nbsp;
		                                <span class="title">待审核报告</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                       <shiro:hasPermission name="remote:finish">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'finish'}">active</c:if>">	
		                             <a href="${pageContext.request.contextPath}/report/complete">
		                                <i class="icon-bookmark-empty"></i>&nbsp;
		                                <span class="title">已完成报告</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                       <shiro:hasPermission name="remote:realtime">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'realTime'}">active</c:if>">
		                             <a href="${pageContext.request.contextPath}/report/realTime" target="_blank">
		                                <i class="icon-eye-open"></i>&nbsp;
		                                <span class="title">查看实时监护</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                    </div>
	                </c:if>
                </shiro:hasPermission>
            
            	<!-- 孕期管理-->
            	<shiro:hasPermission name="pregnant:base">
	                <c:if test="${module == 'pregnant'}">
	               	   <div class="sidebar-offcanvas" id="sidebar" style="padding-right: 20px;">
	               	   	   <shiro:hasPermission name="base:archive">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'archive'}">active</c:if>" >
		                             <a href="${pageContext.request.contextPath}/pregnant/archiveIndex" >
		                                <i class="icon-bookmark"></i>&nbsp;
		                                <span class="title">档案管理</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                    </div>
	                </c:if>
                </shiro:hasPermission>
            
            	<!-- 订单管理 -->
            	<shiro:hasPermission name="order:base">
	                <c:if test="${module == 'user' }">
	               	   <div class="sidebar-offcanvas" style="padding-right: 20px;">
	                       <ul class="page-sidebar-menu">
		                        <li>
		                        	<shiro:hasPermission name="order:remote">
			                            <a href="javascript:;" class="father-menu">
			                                <i class="icon-group"></i>&nbsp;
			                                <span class="title">远程监护订单</span>
			                            </a>
			                            <ul class="sub-menu hide <c:if test="${menu == 'userList' }">show</c:if>">
			                                <li class="<c:if test="${submenu == 'userToday' }">active</c:if>">
			                                   <a href="${pageContext.request.contextPath}/user/index?type=0&remoteType=1">今日订单</a>
			                                </li>
			                                <li class="<c:if test="${submenu == 'userAll' }">active</c:if>">
			                                   <a href="${pageContext.request.contextPath}/user/index?type=1&remoteType=1">有效订单</a>
			                                </li>
			                                <li class="<c:if test="${submenu == 'userDisable' }">active</c:if>">
			                                   <a href="${pageContext.request.contextPath}/user/index?type=2&expireType=1">失效订单</a>
			                                </li>
			                            </ul>
		                            </shiro:hasPermission>
		                            <shiro:hasPermission name="order:visit">
			                            <a href="javascript:;" class="father-menu">
			                                <i class="icon-group"></i>&nbsp;
			                                <span class="title">医院问诊订单</span>
			                            </a>
			                            <ul class="sub-menu hide <c:if test="${menu == 'orderList' }">show</c:if>">
			                                <li class="<c:if test="${submenu == 'visitOrder' }">active</c:if>">
			                                   <a href="${pageContext.request.contextPath}/order/visitOrder">医院问诊订单</a>
			                                </li>
			                            </ul>
		                            </shiro:hasPermission>
		                            
		                            <shiro:hasPermission name="order:device">
			                            <!-- 设备订单 -->
			                            <a href="javascript:;" class="father-menu">
			                                <i class="icon-group"></i>&nbsp;
			                                <span class="title">设备订单</span>
			                            </a>
			                            <ul class="sub-menu hide <c:if test="${menu == 'device' }">show</c:if>">
			                                <li class="<c:if test="${submenu == 'leaseOrder' }">active</c:if>">
			                                   <a href="${pageContext.request.contextPath}/lease/leaseOrder">租赁订单</a>
			                                </li>
			                            </ul>
		                            </shiro:hasPermission>
		                            
		                        </li>
		                    </ul>
		                </div>
	                </c:if>
                </shiro:hasPermission>
            
                <!-- 医院管理模块 -->
                <shiro:hasPermission name="hospital:base">
	                <c:if test="${module == 'hospital'}">
	               	  <div class="sidebar-offcanvas" id="sidebar" style="padding-right: 20px;">
	               	      <shiro:hasPermission name="hospital:info">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'data'}">active</c:if>">
		                             <a href="${pageContext.request.contextPath}/hos/hospitalInfo" >
		                                <i class="icon-file-alt"></i>&nbsp;
		                                <span class="title">医院资料</span>
		                            </a>
		                          </li>
		                       </ul>
		                   </shiro:hasPermission>
	                       <ul class="page-sidebar-menu">
	                          <li>
	                             <a href="javascript:;" class="father-menu">
	                                <i class="icon-user-md"></i>&nbsp;
	                                <span class="title">用户管理</span>
	                            </a>
	                            <ul class="sub-menu hide <c:if test="${menu == 'user'}">show</c:if>">
	                            	<shiro:hasPermission name="hospital:user">
		                                <li class="<c:if test="${submenu == 'list'}">active</c:if>">
		                                   <a href="${pageContext.request.contextPath}/pregnantBook/userList?type=0">用户列表</a>
		                                </li>
	                                </shiro:hasPermission>
	                               <%--  <shiro:hasPermission name="hospital:book">
		                                <li class="<c:if test="${submenu == 'pregnant' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/pregnantBook/pregnantBookList">孕期档案</a>
			                            </li>
		                            </shiro:hasPermission> --%>
	                            </ul>
	                          </li>
	                       </ul>
	                       <shiro:hasPermission name="hospital:admin">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'manager'}">active</c:if>">	
		                             <a href="${pageContext.request.contextPath}/admin/index">
		                                <i class="icon-user"></i>&nbsp;
		                                <span class="title">医生列表</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                       <shiro:hasPermission name="hospital:subordinate">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'subordinate'}">active</c:if>">
		                             <a href="${pageContext.request.contextPath}/hos/hospitalSubordinate" >
		                                <i class="icon-user"></i>&nbsp;
		                                <span class="title">从属下级机构</span>
		                            </a>
		                          </li>
		                       </ul>
		                   </shiro:hasPermission>
	                       <shiro:hasPermission name="hospital:remote:setting">
		                       <ul class="page-sidebar-menu">
		                          <li>
		                             <a href="javascript:;" class="father-menu">
		                                <i class="icon-cog"></i>&nbsp;
		                                <span class="title">服务设置</span>
		                            </a>
		                            <ul class="sub-menu hide <c:if test="${menu == 'service'}">show</c:if>">
		                                <li class="<c:if test="${submenu == 'remoteService'}">active</c:if>">
		                                   <a href="${pageContext.request.contextPath}/rmService/index">医院监护</a>
		                                </li>
		                                <li class="<c:if test="${submenu == 'hos_visit' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hospitalVisit/index">医院问诊</a>
			                            </li>
			                            
			                            <li class="<c:if test="${submenu == 'weightService' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hos/weight">健康营养管理</a>
			                            </li>
			                            
			                             <li class="<c:if test="${submenu == 'personalPrivacyService' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hos/personalPrivacy">个人信息隐私</a>
			                            </li>
			                            
			                            <li class="<c:if test="${submenu == 'schoolService' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hos/school">孕妇学校</a>
			                            </li>
			                            <li class="<c:if test="${submenu == 'classService' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hos/classroom">课堂管理</a>
			                            </li>
			                            <li class="<c:if test="${submenu == 'leaseService' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hos/lease">设备租赁</a>
			                            </li>
			                      		<li class="<c:if test="${submenu == 'networkService' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hos/networkInte">网络诊室</a>
			                            </li>
		                            </ul>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                       
	                       <shiro:hasPermission name="hospital:device">
		                       <ul class="page-sidebar-menu">
		                       		<li>
			                        	<a href="javascript:;" class="father-menu">
			                                <!-- <i class="icon-cog"></i>&nbsp; -->
			                                <i class="glyphicon glyphicon-wrench"></i>&nbsp;
			                                <span class="title">设备业务</span>
			                            </a>
			                            <ul class="sub-menu hide <c:if test="${menu == 'device'}">show</c:if>">
			                                <li class="<c:if test="${submenu == 'deviceInfo'}">active</c:if>">
			                                   <a href="${pageContext.request.contextPath}/lease/deviceService?method=deviceInfo">设备基本信息</a>
			                                </li>
			                                <li class="<c:if test="${submenu == 'marketingSet' }">active</c:if>">
				                               <a href="${pageContext.request.contextPath}/lease/deviceService?method=marketingSet">营销设置</a>
				                            </li>
			                            </ul>     
			                        </li>    
			                   </ul>
		                   </shiro:hasPermission>
	                     
	                       <shiro:hasPermission name="hospital:admin">
		                        <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'manager'}">active</c:if>">	
		                             <a href="${pageContext.request.contextPath}/withdrawals/getWithdrawalsList">
		                                <i class="icon-money"></i>&nbsp;
		                                <span class="title">收益列表</span>
		                            </a>
		                          </li>
		                       </ul>
                           </shiro:hasPermission>
	                    </div>
	                </c:if>
                </shiro:hasPermission>
                
                <!-- 资讯管理 -->
                <shiro:hasPermission name="info:base">
	                <c:if test="${module=='information'}">
	                   	<div class="sidebar-offcanvas" id="sidebar" style="padding-right: 20px;">
	                   		<shiro:hasPermission name="info:news">
		                        <ul class="page-sidebar-menu">
		                          <li>
		                             <a href="javascript:;" class="father-menu">
		                                <i class="icon-folder-close"></i>&nbsp;
		                                <span class="title">资讯</span>
		                            </a>
		                            <ul class="sub-menu hide <c:if test="${menu == 'news'}">show</c:if>">
		                                <li class="<c:if test="${submenu == 'hospital'}">active</c:if>">
		                                   <a href="${pageContext.request.contextPath}/information/index">医院资讯</a>
		                                </li>
		                            </ul>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                       <shiro:hasPermission name="info:message">
		                        <ul class="page-sidebar-menu">
		                          <li>
		                             <a href="javascript:;" class="father-menu">
		                                <i class="icon-comment"></i>&nbsp;
		                                <span class="title">我的消息</span>
		                            </a>
		                            <ul class="sub-menu hide <c:if test="${menu == 'message'}">show</c:if>">
		                                <li class="<c:if test="${submenu == 'list'}">active</c:if>">
		                                   <a href="${pageContext.request.contextPath}/myMessageService/index">消息列表</a>
		                                </li>
		                            </ul>
		                          </li>
		                      </ul>
	                      </shiro:hasPermission>
	                      <shiro:hasPermission name="info:notice">
		                      <ul class="page-sidebar-menu">
		                          <li>
		                             <a href="javascript:;" class="father-menu">
		                                <i class="icon-bullhorn"></i>&nbsp;
		                                <span class="title">医院公告</span>
		                            </a>
		                            <ul class="sub-menu hide <c:if test="${menu == 'notice'}">show</c:if>">
		                                <li class="<c:if test="${submenu == 'noticeList'}">active</c:if>">
		                                   <a href="${pageContext.request.contextPath}/notice/index">公告列表</a>
		                                </li>
		                            </ul>
		                          </li>
		                      </ul>
	                      </shiro:hasPermission>
	                      
	                     	<ul class="page-sidebar-menu">
		                          <li>
		                             <a href="javascript:;" class="father-menu">
		                                <i class="icon-hdd"></i>&nbsp;
		                                <span class="title">课堂</span>
		                            </a>
		                            <ul class="sub-menu hide <c:if test="${menu == 'classroom'}">show</c:if>">
		                                <li class="<c:if test="${submenu == 'news'}">active</c:if>">
		                                   <a href="${pageContext.request.contextPath}/classroom/news">资讯管理</a>
		                                </li>
		                                <li class="<c:if test="${submenu == 'chanel'}">active</c:if>">
		                                   <a href="${pageContext.request.contextPath}/classroom/chanel">频道管理</a>
		                                </li>
		                            </ul>
		                          </li>
		                     </ul>
	                      
	                    </div>
	                </c:if>
                </shiro:hasPermission>
               <!-- 医院问诊-->
               <shiro:hasPermission name="consult:base">
	               <c:if test="${module == 'consult'}">
	               	   <div class="sidebar-offcanvas" style="padding-right: 20px;">
	               	       <shiro:hasPermission name="consult:current">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'today'}">active</c:if>" >
		                             <a href="${pageContext.request.contextPath}/consult/consultList" >
		                               <i class="icon-plus-sign-alt"></i>&nbsp;
		                                <span class="title">问诊中</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                       <shiro:hasPermission name="consult:finish">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'end'}">active</c:if>" >
		                             <a href="${pageContext.request.contextPath}/consult/endList" >
		                               <i class="icon-file-text-alt"></i>&nbsp;
		                                <span class="title">结束问诊</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                       <%-- <ul class="page-sidebar-menu">
	                          <li class="<c:if test="${menu == 'refund'}">active</c:if>" >
	                             <a href="${pageContext.request.contextPath}/consult/refund" >
	                               <i class="icon-reply"></i>&nbsp;
	                                <span class="title">退费列表</span>
	                            </a>
	                          </li>
	                       </ul> --%>
	                    </div>
	                </c:if>
                </shiro:hasPermission>
                <!-- 家庭医生 -->
               <shiro:hasPermission name="family:base">
	               <c:if test="${module == 'family'}">
	               	   <div class="sidebar-offcanvas" style="padding-right: 20px;">
	               	       <shiro:hasPermission name="family:audit">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'waitAudit'}">active</c:if>" >
		                             <a href="${pageContext.request.contextPath}/fDoctor/waitAudit" >
		                               <i class="icon-file-text-alt"></i>&nbsp;
		                                <span class="title">待审核报告</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                       <shiro:hasPermission name="family:userinfo">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'userinfo'}">active</c:if>" >
		                             <a href="${pageContext.request.contextPath}/fDoctor/userinfo" >
		                               <i class="icon-file-text-alt"></i>&nbsp;
		                                <span class="title">用户信息</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                       <shiro:hasPermission name="family:doctor">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'doctor'}">active</c:if>" >
		                             <a href="${pageContext.request.contextPath}/fDoctor/doctor" >
		                               <i class="icon-file-text-alt"></i>&nbsp;
		                                <span class="title">家庭医生账号管理</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                    </div>
	                </c:if>
                </shiro:hasPermission>
                
                <!-- 孕妇学校 -->
                <shiro:hasPermission name="school:base">
	                <c:if test="${module == 'school'}">
	               	   <div class="sidebar-offcanvas" style="padding-right: 20px;">
	               	       <shiro:hasPermission name="school:library">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'library'}">active</c:if>" >
		                             <a href="${pageContext.request.contextPath}/school/library" >
		                               <i class="icon-plus-sign-alt"></i>&nbsp;
		                                <span class="title">课程库</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                       <shiro:hasPermission name="school:schedule">
		                       <ul class="page-sidebar-menu">
		                          <li>
		                             <a href="javascript:;" class="father-menu">
		                               <i class="icon-file-text-alt"></i>&nbsp;
		                                <span class="title">课程表</span>
		                             </a>
		                             <ul class="sub-menu hide <c:if test="${menu == 'schedule'}">show</c:if>">
		                                <li class="<c:if test="${submenu == 'offLine'}">active</c:if>">
		                                   <a href="${pageContext.request.contextPath}/schedule/offLine">线下课程</a>
		                                </li>
		                                <li class="<c:if test="${submenu == 'onLine' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/schedule/onLine">网络课程</a>
			                            </li>
		                            </ul>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                    </div>
	                </c:if>
                </shiro:hasPermission>
                
                <!-- 权限管理-->
                <shiro:hasPermission name="auth:base">
	                <c:if test="${module == 'auth'}">
	               	   <div class="sidebar-offcanvas" style="padding-right: 20px;">
	               	   	   <shiro:hasPermission name="auth:admin">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'adminList'}">active</c:if>" >
		                             <a href="${pageContext.request.contextPath}/auth/index" >
		                               <i class="icon-plus-sign-alt"></i>&nbsp;
		                                <span class="title">管理员列表</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                       <shiro:hasPermission name="auth:role">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'roleList'}">active</c:if>" >
		                             <a href="${pageContext.request.contextPath}/auth/roleList" >
		                               <i class="icon-file-text-alt"></i>&nbsp;
		                                <span class="title">角色列表</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission> 
	                       <shiro:hasPermission name="auth:module">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'moduleList'}">active</c:if>" >
		                             <a href="${pageContext.request.contextPath}/auth/authList" >
		                               <i class="icon-file-text-alt"></i>&nbsp;
		                                <span class="title">模块列表</span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                      </div>
	                </c:if>
                </shiro:hasPermission>
                </div></div>

