<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${pageContext.request.contextPath}/media/css/bootstrap.css?${v }" rel="stylesheet">
	<style type="text/css">
		body{
			background: #F0F0F0;
		}
		.col-xs-12{
			width:100%;
			position:absolute;
		}
		
		.panel-default{
			position:relative;
			margin:0 auto;
			margin-top:300px;
			width:40%;
			height:300px;
		}
		
		.col-xs-18{
			font-size:x-large;
			color:#796e6e;
			border-bottom:1px solid #F0F0F0;
		}
		
		.col-xs-19{
			font-size:xx-large;
			color:#b94444;
			height:150px;
			line-height:150px;
			text-align: center;
			
		}
	</style>
  </head>
  <body>
	<div class="col-xs-12 col-sm-9">
		<div class="panel panel-default">
        	<div class="panel-body">
        		<div class="col-xs-18">温馨提示!</div>
        		<div class="col-xs-19">
        			你暂时没有权限访问此页面!请返回...
        		</div>
		  		<div style="text-align:center;">
			  		<a class="btn btn-default" href="javascript:history.go(-1);" style="width:120px">&nbsp;返&nbsp;回&nbsp;</a>
			  	</div>
	        </div>
	    </div>
	</div>
  </body>
</html>