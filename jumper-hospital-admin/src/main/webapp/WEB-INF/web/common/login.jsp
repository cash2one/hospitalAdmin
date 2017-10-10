<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<shiro:authenticated>
<%
String path = request.getContextPath();
response.sendRedirect(path+"/base/main");
%>
</shiro:authenticated>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <%
			String message = null;
			String loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
			if (loginFailure != null) {
				if (loginFailure.equals("org.apache.shiro.authc.UnknownAccountException")) {
					message = "此账号不存在";
				} else if (loginFailure.equals("org.apache.shiro.authc.DisabledAccountException")) {
					message = "此账号已被禁用";
				} else if (loginFailure.equals("org.apache.shiro.authc.LockedAccountException")) {
					message = "此账号已被锁定";
				} else if (loginFailure.equals("org.apache.shiro.authc.IncorrectCredentialsException")) {
					message = "用户名或密码错误";
				} else if (loginFailure.equals("org.apache.shiro.authc.AuthenticationException")) {
					message = "账号认证失败";
				}
			}
		%>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="${pageContext.request.contextPath}/media/image/weblogo.png">

        <title>系统登录</title>

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/media/css/bootstrap.css?${v }" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/media/css/login.css?${v }" rel="stylesheet">

        <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery-1.10.1.min.js?${v }"></script>
        <script type="text/javascript">
        	function redirectPage() {  
	        	var width = screen.width;
	        	if(width < 1440){
	        		$(".login-desc").css("margin-top", 30);
	        	}
        	}
			$(function(){
				<%if (message != null) {%>
					$(".error_message").html("<%=message%>");
					$(".error_message").show();
				<%}%>
				redirectPage();
			});
		</script>
    </head>

    <body>
        <div class="login-container">
            <div class="login-desc">
                <img src="${pageContext.request.contextPath}/media/image/login_desc.png">
            </div>
            <hr />
            <div class="left-image">
                <img src="${pageContext.request.contextPath}/media/image/login_img.png">
            </div>
            <div class="login_form">
                <form id="login-form" action="" method="post">
                    <label>用户登录</label>
                    <input type="text" class="form-control" placeholder="账号" name="username" required id="username" style="margin-top: 16px">
                    <input type="password" class="form-control" placeholder="密码" id="password" required name="password" style="margin-top: 16px">
                    <div class="error_message">用户不存在</div>
                    <button type="submit" class="btn" style="margin-top: 25px;margin-bottom: 20px;">登录</button>
                </form>
            </div>
        </div>
        <div class="clearFix"></div>
        <div style="text-align: center;">@2014-2016 深圳市京柏医疗设备有限公司 粤ICP备07070821号-2 </div>
        <div class="login-bg">
            <img src="${pageContext.request.contextPath}/media/image/login_bg.png">
            <div class="img-bg"></div>
        </div>
    </body>

</html>