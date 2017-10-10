<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="en" class="no-js">
<head>
	<meta charset="utf-8">
	<title>天使医生</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0 , maximum-scale=1.0, user-scalable=0">	
	<meta content="width=device-width, initial-scale=1.0" name="viewport">
	<style type="text/css">
		body{background: white;font-family:tahoma,'宋体';letter-spacing:1.2px!important; margin:0; padding:0;}
		.detail img{max-width: 100%;}       
		p{line-height:150%;letter-spacing:1px;word-break:break-all;}
		a{text-decoration:none;cursor:hand;color:black;}
		.tab{width:100%; padding:3px; word-break:break-all; word-wrap:break-all;}
		td{width:100%;}
	</style>
</head>

<body>
	<div style="margin:8px;">



<!-- 五条 -->
<div class="detail">
<center>
		<table class="tab">
		
		  		<tbody><tr>
		  		  <td style="margin-top:10px;width:100%; font-size: 28px;">
					     <b>${news.title }</b>
				  </td>
		  		</tr>
				
				<tr>
					<td style="padding-bottom:10px">
				    	<font style="margin-left:5px;font-family:Microsoft YaHei;color: #999999">${time }</font>
		
				   </td>
				   </tr>
				   <tr>
				    <td>
				        <div style="width:100%;">
						    <img style="width: 100%;" src="${BASE_PATH}/${news.imageUrl }">
						</div>
				   </td>
				  </tr>
				   <tr>
			    <td style="padding-bottom:50px; padding-top: 10px;">										
					&nbsp;&nbsp;&nbsp;&nbsp;<span style="line-height:30px; font-family : 微软雅黑,宋体;">${news.content }</span><br><br>
			   </td>
		 </tr>
		  
		</tbody></table>
	</center></div>
	
	<center>
	
</center></div>

 

</body></html>