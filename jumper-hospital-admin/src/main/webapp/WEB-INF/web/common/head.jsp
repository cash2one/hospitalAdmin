<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page import="com.jumper.hospital.utils.Consts"%>  
<%@page import="com.jumper.hospital.utils.Const"%> 
<%
	Subject currentUser = SecurityUtils.getSubject();
	String user = currentUser.getPrincipal().toString();
%>
 <jsp:include page="/WEB-INF/web/common/webimDailog.jsp"></jsp:include> 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="${pageContext.request.contextPath}/media/image/weblogo.png?${v }">
 
		<title>京柏医疗妇幼医生工作站</title>

		<!-- Bootstrap core CSS -->
		<link href="${pageContext.request.contextPath}/media/css/bootstrap.css?${v }" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/media/css/offcanvas.css?${v }" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/media/font-awesome/css/font-awesome.min.css?${v }" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/media/css/drop-down.css?${v }" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/media/css/menu.css?${v }" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/media/css/remote.css?${v }" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/media/css/style.css?${v }" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/media/css/bootstrap-fileupload.css?${v }" rel="stylesheet">
		

		<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery-1.10.1.min.js?${v }"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery-migrate-1.2.1.min.js?${v }"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/bootstrap.min.js?${v }"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/Validform_v5.3.2_min.js?${v }"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/app.js?${v }"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/refreshInq.js?${v }"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/ajaxfileupload.js?${v }"></script>
		<!-- 审核报告js处理-->
		<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/report/reportTips.js?${v }"></script>
		
		<!-- webim相关 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/css/index.css?${v }" />
		<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/react.css" /> --%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/blue2.css?${v }" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/blue.css?${v }" /> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/refreshInq.js?${v }"></script> 
		<!-- 新加webim整理的 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/lib/md5/spark-md5.js?${v }" ></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sdk/webim.min.js?${v }"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tool/artDialog.source.js?${v }" ></script>
		<!--Tool -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tool/jquery.nicescroll.min.js?${v }"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/sdk/json2.js?${v }" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tool/tool.js?${v }" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/public/webuploader.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/public/webIMLogin.js?${v}"></script> 
		 <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/public/webIMChat.js?${v}"></script> --%>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/public/public_kz.js?${v}"></script> 
		<!--新加webim整理的 -->
		
		<style type="text/css">
			html{
				overflow-x: scroll;
			}
			body {
				background: #F0F0F0;
				min-width: 1660px;
				overflow-y: hidden;
			}
			.message-dialog{
				width: 800px;
				height: 500px;
			}
		</style>
		<script type="text/javascript"">
		//跳到建海主页
		function openCloudInex(hosId){
		App.ajaxRequest(
				'/visits/index',{'hospitalId':hosId},'get',true,'json',
				function resultHandler(data){
					if(data == "error"){
						App.dialog_show("", "请求出错，请刷新或者重新登录重试！", function(){
							$(".info-dialog-close").trigger("click");
						});
					}else{
					if(data.length > 0){
						window.open(data);
					}
					}
				}
			);
		}
			$(function(){
				$(".update-pass").click(function(){
					$("#up-id").val("");
					$("#up-name").val("");
					$("#up-username").val("");
					$("#up-password").val("");
					$("#new-password").val("");
					$("#a-new-password2").val("");
					if(!$(".password-update-error").hasClass("hide")){
						$(".password-update-error").addClass("hide");
					}
					App.ajaxRequest(
						'/base/admin',{},'get',true,'json',
						function resultHandler(data){
							$("#up-id").val(data.id);
							$("#up-name").val(data.name);
							$("#up-username").val(data.username);
							
							$(".password-update-href").trigger("click");
						}
					);
				});
				
				$(".password-update-sure").click(function(){
					$("#password-update-form").Validform({
						/** 自定义错误消息显示 **/
						tiptype:function(msg){
							App.validateErrorInit(".password-update-error", msg);
						},
						showAllError:false,
						tipSweep:true,
						ajaxPost:true,
						beforeSubmit:function(curform){
							/** 表单验证成功后的回调,取消错误验证消息 **/
							App.validateSuccess(".password-update-error");
						},
						callback:function(request, data){
							$(".password-update-cancel").trigger("click");
							if(request instanceof Object){
								var loginStatus = request.getResponseHeader("loginStatus");
								if(loginStatus == "accessDenied"){
									App.noPermission();
								}
							}else{
								var message = "";
								if(request == "success"){
									message = "修改管理员信息成功！";
								}else if(request == "failed"){
									message = "修改管理员信息失败！";
								}else if(request == "paramsError"){
									message = "参数错误，请刷新浏览器重试！";
								}else if(request == "hospitalIdIsNull"){
									message = "您填写的医院名称不存在，请核对后在试！";
								}else if(request == "error"){
									message = "处理异常！";
								}else if(request == "passError"){
									message = "原密码错误！";
								}
								
								App.dialog_show("提示！", message, function(){
									$(".info-dialog-close").trigger("click");
									if(request == 'success'){
										location.reload();
									}
								});
							}
						}
					});
				});
				
				//退出接口
				$(".login-out").click(function(){
					//添加日志===>退出日志
					App.addOperateLog("登陆","退出","系统","账号退出系统");
					location.href="${pageContext.request.contextPath}/base/login_out";
				});
				
			});
		</script>
	</head>
   	
	<body>
		<!-- 页头 -->
		<nav class="navbar navbar-inverse navbar-fixed-top" style="min-width:1260px">
          <div class="container-fluid" style="padding-left:0px;padding-right:10px;">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#" style="margin-left:10px;margin-right:30px"><img src="${pageContext.request.contextPath}/media/image/logo.png" /></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                	<shiro:hasPermission name="remote:base">
                		<li <c:if test="${module == 'report' }">class="active"</c:if>><a href="${pageContext.request.contextPath}/report/audit">监护管理<span id="isShowHeadTip"></span></a></li>
                		
                	</shiro:hasPermission>
                	<shiro:hasPermission name="pregnant:base">
                		<li <c:if test="${module == 'pregnant' }">class="active"</c:if>><a href="${pageContext.request.contextPath}/pregnant/archiveIndex">孕期管理</a></li>
                	</shiro:hasPermission>
                	<shiro:hasPermission name="order:base">
                		<li <c:if test="${module == 'user' }">class="active"</c:if>><a href="${pageContext.request.contextPath}/user/index?type=0">订单管理</a></li>
                	</shiro:hasPermission>
                    <shiro:hasPermission name="hospital:base">
                    	<li <c:if test="${module == 'hospital' }">class="active"</c:if>><a href="${pageContext.request.contextPath}/hos/hospitalInfo">医院管理</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="info:base">
                    	<li <c:if test="${module == 'information'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/information/index">资讯管理</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="consult:base">
	                    <li <c:if test="${module == 'consult' }">class="active"</c:if>>
		                    <a href="${pageContext.request.contextPath}/consult/consultList">
		                    	医院问诊<img id="tishi" src="${pageContext.request.contextPath}/media/image/black.png" style="float:right;"/>
		                    </a>
	                    </li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="family:base">
                    	<li <c:if test="${module == 'family'}">class="active"</c:if>><a href="${sessionScope.loginParam}" onclick="${sessionScope.loginParam}">全科医生</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="school:base">
                    	<li <c:if test="${module == 'school' }">class="active"</c:if>><a href="${pageContext.request.contextPath}/school/library2">孕妇学校</a></li>
                    </shiro:hasPermission>
                     <shiro:hasPermission name="visits:base">
                    	<li <c:if test="${module == 'visits' }">class="active"</c:if>><a href="#####" onclick="openCloudInex(${hospitalInfo.id})" >云随访</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="auth:base">
                    	<li <c:if test="${module == 'auth' }">class="active"</c:if>><a href="${pageContext.request.contextPath}/auth/index">权限管理</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="weight:base">
                    	<li <c:if test="${module == 'weight' }">class="active"</c:if>><a href="${pageContext.request.contextPath}/weight/index">体重营养</a></li>
                    </shiro:hasPermission>
					<shiro:hasPermission name="highrisk:base">
                    	<li <c:if test="${module == 'highrisk' }">class="active"</c:if>><a href="${pageContext.request.contextPath}/highrisk/index">高危系统</a></li>
                    </shiro:hasPermission>
                    <%-- <li <c:if test="${module == 'order' }">class="active"</c:if>><a href="${pageContext.request.contextPath}/order/visitOrder">订单统计</a></li> --%>
                </ul>
                <div class="nav navbar-nav" style="height:50px;line-height:50px;color:white;float:right;"><!-- navbar-right  -->
                	<ul class="nav navbar-nav">
                	
                <!-- 短消息菜单开始-->
                <c:set var="YWFY_HOSPITAL_ID" value="<%=Const.YWHOSPITAL_ID %>"/>  
                <c:if test='${hospitalInfo.id!=YWFY_HOSPITAL_ID}'>
					<li class="dropdown" id="header_inbox_bar" style="margin-right:10px;">
						<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
							<i class="icon-envelope"></i>
							<span class="badge consult_message">0</span>
							<input type="hidden" id="hospital_id" value=${hospitalInfo.id} />
							<audio style="display:none" src="${pageContext.request.contextPath}/media/sound/alert.mp3" id="chatAudio"></audio>
<%-- 							<iframe id="count_iframe" style="display:none;" src="${CHAT_URL}/alert/count/${hospitalInfo.id}"></iframe>
							<iframe id="msg_list_iframe" style="display:none;" src="${CHAT_URL}/alert/list/${hospitalInfo.id}"></iframe> --%>
							<ul id="dropdown-menuTest" class="dropdown-menu">
			                 	<li><a href="#" style="display:none;"></a></li>
			                </ul>
						</a>
						<ul class="dropdown-menu" id="msg_list"></ul>
					</li> 
				</c:if>
					<!-- 短消息菜单结束 -->
                    <li style="color:#FE8862"><!-- 当前管理医院： -->${hospitalInfo.name}</li><input type="hidden" value="${hospitalInfo.id}" id="hospitalId"/><input type="hidden" value="${hospitalInfo.name}" id="hospitalName"/>
                    <li style="color:#9d9d9d"><!-- <img src="${pageContext.request.contextPath}/media/image/avatar1_small.jpg" style="margin-right:10px;margin-left:30px">欢迎您， -->&nbsp;&nbsp;<%=user %></li>
                    <li class="dropdown" style="margin-left:20px">
                    
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">退出 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                        	<li><a href="#" class="update-pass">修改密码</a></li>
                            <li><a class="login-out">退出登录</a></li>
                        </ul>
                    </li>
                    </ul>
                </div>
            </div>
          </div>
        </nav>
        <!-- 左侧导航菜单 -->
        <div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-right">
            	<!-- 监测管理 -->
            	<shiro:hasPermission name="remote:base">
	                <c:if test="${module == 'report'}">
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" style="padding-right: 20px;">
	               	   	   <shiro:hasPermission name="remote:wait_audit">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'audit'}">active</c:if>" >
		                             <a href="${pageContext.request.contextPath}/report/audit" >
		                                <i class="icon-bookmark"></i>&nbsp;
		                                <span class="title">待审核报告 <span id="totalNotFinishOrder"></span> </span>
		                            </a>
		                          </li>
		                       </ul>
	                       </shiro:hasPermission>
	                       <shiro:hasPermission name="remote:finish">
		                       <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'finish'}">active</c:if>">	
		                             <a href="${pageContext.request.contextPath}/report/complete">
		                                <i class="icon-bookmark-empty"></i>&nbsp;
		                                <span class="title">已完成报告 <span id="totalFinishUnredReport"> </span> </span>
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
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" style="padding-right: 20px;">
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
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
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
	               	  <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" style="padding-right: 20px;">
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
		                            
		                             <shiro:hasPermission name="hospital:remote:hospitalCustody">
		                                <li class="<c:if test="${submenu == 'remoteService'}">active</c:if>">
		                                   <a href="${pageContext.request.contextPath}/rmService/index">医院监护</a>
		                                </li>
		                             </shiro:hasPermission>

									 <shiro:hasPermission name="hospital:remote:hospitalInquiry">
		                                <li class="<c:if test="${submenu == 'hos_visit' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hospitalVisit/index">医院问诊</a>
			                            </li>
			                         </shiro:hasPermission>
			                            
									 <shiro:hasPermission name="hospital:remote:nutritionManage">
			                            <li class="<c:if test="${submenu == 'weightService' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hos/weight">健康营养管理</a>
			                            </li>
			                         </shiro:hasPermission>
			                         
			                          <shiro:hasPermission name="hospital:remote:personalInformation">
			                             <li class="<c:if test="${submenu == 'personalPrivacyService' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hos/personalPrivacy">个人信息隐私</a>
			                            </li>
			                          </shiro:hasPermission>
			                           
									   <shiro:hasPermission name="hospital:remote:pregnantSchool">
			                            <li class="<c:if test="${submenu == 'schoolService' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hos/school">孕妇学校</a>
			                            </li>
			                           </shiro:hasPermission>
			                           
			                           <shiro:hasPermission name="hospital:remote:classroomManage">
			                            <li class="<c:if test="${submenu == 'classService' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hos/classroom">课堂管理</a>
			                            </li>
			                           </shiro:hasPermission>
			                            
			                            
			                           <shiro:hasPermission name="hospital:remote:lease">
			                            <li class="<c:if test="${submenu == 'leaseService' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hos/lease">设备租赁</a>
			                            </li>
			                           </shiro:hasPermission>
			                            
			                           <shiro:hasPermission name="hospital:remote:networkInte">
			                      		<li class="<c:if test="${submenu == 'networkService' }">active</c:if>">
			                               <a href="${pageContext.request.contextPath}/hos/networkInte">网络诊室</a>
			                            </li>
			                           </shiro:hasPermission>
			                           
			                            
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
	                     
	                       <shiro:hasPermission name="hospital:withdrawalsList">
		                        <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'getWithdrawalsList'}">active</c:if>">	
		                             <a href="${pageContext.request.contextPath}/withdrawals/getWithdrawalsList">
		                                <i class="icon-money"></i>&nbsp;
		                                <span class="title">收益列表</span>
		                            </a>
		                          </li>
		                       </ul>
                           </shiro:hasPermission>
                           <!-- 日志查看 -->
                           <shiro:hasPermission name="hospital:operateLog">
		                        <ul class="page-sidebar-menu">
		                          <li class="<c:if test="${menu == 'operateLog'}">active</c:if>">	
		                             <a href="${pageContext.request.contextPath}/operateLog/showOperateLog?logType=1">
		                                <i class="glyphicon glyphicon-zoom-in"></i>&nbsp;
		                                <span class="title">操作日志</span>
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
	                   	<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" style="padding-right: 20px;">
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
	                      
	                       <shiro:hasPermission name="info:classroom">
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
	                      </shiro:hasPermission>
	                      
	                    </div>
	                </c:if>
                </shiro:hasPermission>
               <!-- 医院问诊-->
               <shiro:hasPermission name="consult:base">
	               <c:if test="${module == 'consult'}">
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
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
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
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
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
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
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
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
<!-- 修改管理员信息dialog -->
<a class="btn btn-pink hide password-update-href" data-toggle="modal" data-target="#password-confrim"></a>
<div class="modal" id="password-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true">
	<div class="modal-dialog">
		<form id="password-update-form" action="${pageContext.request.contextPath }/admin/change" method="post">
			<input type="hidden" name="id" id="up-id" value="" />
			<div class="modal-content">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title password-update-title" id="confirmLabelTitle">修改密码</h4>
		      	</div>
		      	<div class="alert alert-danger hide password-update-error" role="alert"></div>
				<div class="modal-body password-update-body">
					<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">姓名</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="up-name" name="name" placeholder="管理员姓名" datatype="*" nullmsg="请输入管理员姓名" errormsg="请输入管理员姓名!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">登录名</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="up-username" placeholder="登录名" readonly>
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">原密码</label>
					    <div class="col-sm-10 float-right">
					    	<input type="password" class="form-control" id="up-password" name="oldPass" placeholder="原密码" datatype="*6-100" nullmsg="请输入原密码！" errormsg="密码必须在6位以上！">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">新密码</label>
					    <div class="col-sm-10 float-right">
					    	<input type="password" class="form-control" id="new-password" name="password" placeholder="新密码" datatype="*6-100" nullmsg="请设置新密码！" errormsg="密码必须在6位以上！">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">确认密码</label>
					    <div class="col-sm-10 float-right">
					    	<input type="password" class="form-control" id="a-new-password2" name="password2" placeholder="确认密码" datatype="*" recheck="password" nullmsg="请再次输入密码！" errormsg="您两次输入的账号密码不一致！">
					    </div>
	  				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default password-update-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger password-update-sure">确定</button>
				</div>
			</div>
		</form>
	</div>
</div>
