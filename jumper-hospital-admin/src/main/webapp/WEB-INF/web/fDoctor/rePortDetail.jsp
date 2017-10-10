<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/style.css" />
<link href="${pageContext.request.contextPath}/media/css/familyDoctor-report.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/chart.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/familyDoctor/report.js"></script> 
<style type="text/css">
	.contentChart{
         width:1200px;
         height:210px;
         margin-top: 20px;
         border:none;
         /**border: 1px solid #DADADA;**/
     }
     .oyLabel{
         width: 25px;
         height: 210px;
         margin-top: 20px;
         border:none;
     }
     .oxLabel{
         width: 1200px;
         height: 25px;
         margin-left: 35px;
         border:none;
     }
     
	.table tr td {/* 去除表格的默认边框 */
		border: none !important;
	}
	
	.btn-ok{
	width:122px;background-color: #FF6E7F;
	border-radius: 5px !important;
}

ul li{
	list-style:none;
}
.reset {
    margin:0;
    padding:0;
    border:0;
    outline:0;
    font-size:100%;
    vertical-align:baseline;
    background:transparent;
}
.clearfloat:after{display:block;clear:both;content:"";visibility:hidden;height:0}
.clearfloat{zoom:1} 
.path{
	border-bottom:1px solid #F0F0F0;padding:0;
}
.path li{
	float: left;margin: 8px;
	 margin-bottom: 0px;   line-height: 32px;"
}
.report{
	border-bottom: 2px solid red;
}
.userinfo{margin-bottom: 0px;text-align:center;height:50px;}
.head-panel{border:1px solid rgb(240,240,240);border-bottom-style: hidden;margin-bottom: 0px;}
.detail{text-align:center;border:2px solid #F3F3F3;width:98%;margin-left:1%;}
</style>
<div class="col-xs-12 col-sm-9">
<!-- 节点开始 -->

 <div >
 			<div style="background-color:#fff;">
				 <ul class="reset clearfloat path" >
				 	<li class="report">报告单</li>
				 	<li ><a href="${pageContext.request.contextPath}/fDoctor/dataDetail?id=${id}">详细数据</a></li>
				 </ul>
		 </div>
 	<div class="panel panel-default head-panel" style="">
			<div class="panel-body" style="padding: 15px 0px;">
				<table class="table userinfo" >
					<tr>
						<td width="10%">用户姓名</td>
						<td width="10%">年龄</td>
						<td width="10%">孕周</td>
						<td width="10%">电话</td>
						<td width="10%">末次月经</td>
						<td width="20%">检测时间</td>
						<td width="30%">地址</td>
					</tr>
						<tr>
							<td>${fe.name}</td><!-- ↑　↓ -->
							<td>${fe.age}</td>
							<td>${gestationalWeek}</td>
							<td>${fe.mobile}</td>
							<fmt:formatDate value="${fe.lastPeriod}" pattern="yyyy-MM-dd" var="d" />
							<td>${d}</td>
							<fmt:formatDate value="${familyExamination.addTime}" pattern="yyyy-MM-dd HH:mm" var="l" />
							<td>${l}</td>
							<td>${fe.joinAdd}</td>
						</tr>
				</table>
				<hr style="background-color:rgb(240,240,240)"/>
				<table   class="table detail"  >
						<tr style="background-color:#E1E1E1">
							<th  style="text-align:center;" width="17%">检测项目</th>
							<th  style="text-align:center;" width="15%">结果</th>
							<th  style="text-align:center;" width="2%"></th>
							<th  style="text-align:center;" width="17%">参考区间</th>
							
							<th  style="text-align:center;" width="17%">检测项目</th>
							<th  style="text-align:center;" width="15%">结果</th>
							<th  style="text-align:center;" width="2%"></th>
							<th  style="text-align:center;" width="17%">参考区间</th>
						</tr> 
						<c:forEach items="${result}" var="l" varStatus="s" step="2">
						 	<tr>
						 		<td>${result[s.index].project }</td>
						 		<td>${result[s.index].result }</td>
						 		<td>
										<c:choose>
											<c:when test="${result[s.index].resultState==2 }">↑</c:when>
											<c:when test="${result[s.index].resultState==0 }">↓</c:when>
										</c:choose>
									</td>
						 		<td>${result[s.index].area }</td>
						 		
						 		<td>${result[s.index+1].project }</td>
						 		<td>${result[s.index+1].result }</td>
						 		<td>
										<c:choose>
											<c:when test="${result[s.index+1].resultState==2 }">↑</c:when>
											<c:when test="${result[s.index+1].resultState==0 }">↓</c:when>
										</c:choose>
									</td>
						 		<td>${result[s.index+1].area }</td>
						 	</tr>
						</c:forEach>
				</table>
			<c:if test="${result == null ||fn:length(result)==0}">
			<div class="panel panel-default" >
	       		<div class="panel-body">
		 	    	<span style="color:red">暂无记录</span>
		   		</div>
			</div>
	   	</c:if>
	   	<c:if test="${pdfurl !=null }">
		<p>胎心检测图</p>
 			<!-- <embed width="100%" height="1200px" src="http://192.168.2.67:8888/group1/M00/02/C2/wKgCQ1cZsCmAQfJUAACisFils9I055.PDF"> </embed> -->
 			 <embed width="100%" height="1200px" src="${pdfurl }"> </embed> 
 			 </c:if>
			</div>
		</div>
 	</div>
</div>
</script>
<!-- 引入页尾 -->
