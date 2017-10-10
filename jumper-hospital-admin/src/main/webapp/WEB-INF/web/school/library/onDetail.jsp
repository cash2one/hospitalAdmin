<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,height=device-height,maximum-scale=1.0;">
		<link rel="icon" href="${pageContext.request.contextPath}/media/image/weblogo.png">
		<title>网络课程 - 详情</title>
		<style type="text/css">
			html{
				overflow: hidden;
			}
			body{
				background-color:#EBEBEB;
				margin:0;
				padding:0;
				overflow: hidden;
			}
			.container{
				background-color:#FFFFFF
			}
			.courseName{
				width:100%;
				text-align:center;
				padding:16px 0px 10px 0px;
				font-size:20px;
				font-weight:600
			}
			.courseUrl{
				text-align:center;
				margin:0px 10px
			}
			.courseDesc{
				color:#9B9B9B;
				padding:10px 10px 16px 10px;
				text-indent:2em;
				line-height:1.5;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="courseName">${course.courseName }</div>
			<div class="courseUrl">${course.courseUrl }</div>
			<div class="courseDesc">${course.courseDesc }</div>
		</div>
	</body>
</html>