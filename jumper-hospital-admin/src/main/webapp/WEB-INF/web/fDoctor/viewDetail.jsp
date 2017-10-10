<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/style.css" />
<link href="${pageContext.request.contextPath}/media/css/report.css" rel="stylesheet">
 <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/chart.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/familyDoctor/report.js"></script>  --%>
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
.clearfloat:after{
	display:block;
	clear:both;content:"";
	visibility:hidden;height:0
}
.clearfloat{zoom:1} 
.userinfo-head{
	margin:0 15px;
	border:0px;
}
.userinfo-body{
	padding: 15px 15px;
}
.examin {padding: 15px 38px};
.examin-panel{  margin: 20px 15px;border: 0px;background-color: #F0F0F0; border-radius: 5px !important; padding: 0px 17px;}
.clear-background{color:rgb(0,0,0)}
.body-color{background-color:#fff;}
.pop{
position: fixed;top: 0;right: 0;bottom: 0;
left: 0;z-index: 1050;overflow: hidden;
-webkit-overflow-scrolling: touch;
outline: 0;width: 100%;height: 100%;
background-color: rgba(0, 0, 0, 0.5);
display:inline-table; 
}
.pop iframe{
width:680px;height:80%;
border: 0px solid;
background-color:#FAFAFA;
}
.pop .container{
background-color: rgba(0, 0, 0, 0);margin:0 auto;
display: table-cell;text-align:center;vertical-align: middle;
width:100%;height:100%;
}
</style>
<div class="col-xs-12 col-sm-9 body-color" >
<!-- pop  -->
<div class="pop"  >
		<div class="container">
<div class="head" style="background-color:#fff;width:680px;margin: auto;margin-bottom: -10px;height:60px;">
<h1 style="line-height: 52px;">孕早期风险评分问卷</h1>
</div>
		  <iframe 
		   src="">
		  </iframe>
		</div>
	</div>
<!-- pop  -->
<form hidden="hidden" action="${pageContext.request.contextPath}/fDoctor/viewDetail" method="POST" id="toReport">
  	<input type="hidden" name="id"/>
  	<input type="submit">
</form>
<!-- 节点开始 -->
			
 	<div class="panel panel-default userinfo-head" >
			<div class="panel-body userinfo-body">
				<table class="table userinfo"  >
					<tr>
						<td width="15%">用户姓名</td>
						<td width="10%">年龄</td>
						<td width="10%">孕周</td>
						<td width="10%">电话</td>
						<td width="10%">末次月经</td>
						<td width="35%" align="center">地址</td>
					</tr>
						<tr> 
							<td>${userinfo.name}</td><!-- ↑　↓ -->
							<td>${userinfo.age}</td>
							<td>${gestationalWeek}</td>
							<td>${userinfo.mobile}</td>
							<fmt:formatDate value="${userinfo.lastPeriod}" pattern="yyyy-MM-dd" var="d" />
							<td>${d}</td>
							<td  align="center">${userinfo.joinAdd}</td>
						</tr>
				</table>
		 </div>
	</div>
	<c:forEach items="${examinations }" var="ex" varStatus="s">
	 	<div class="panel panel-default examin-panel" >
			<div class="panel-body examin" >
					<table>
						<tr>
							<td width="20%">${ex.count }</td>
							<fmt:formatDate value="${ex.addTime}" pattern="yyyy-MM-dd HH:mm" var="c" />
							<td width="20%">${c}</td>
							<td width="20%">${ex.pregnantWeek }</td>
							<td width="20%">
							<c:choose>
								<c:when test="${ex.result==1 }">
									正常
								</c:when>
								<c:when test="${ex.result==0 }">
									<span style="color:#FAA28A">异常</span>
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
							
							</td>
							<td width="20%"><a class="btn clear-background" href="${pageContext.request.contextPath}/fDoctor/rePortDetail?id=${ex.id }" >报告&nbsp;&nbsp;</a>|
							<a class="btn v" href="${pageContext.request.contextPath}/fDoctor/dataDetail?id=${ex.id }" >查看数据</a></td>
						</tr>
					</table> 
			 </div>
		</div>
	</c:forEach>
 
	<c:if test="${report!=null }">
	<div class="panel panel-default examin-panel" >
			<div class="panel-body examin" >
					<table>
						<tr>
							<td width="20%">孕早期风险评估报告</td>
							<td width="20%">${report.addTime}</td>
							<td width="20%">${report.pregnantWeek }</td>
							<td width="20%">
							<c:choose>
								<c:when test="${report.state==0 }">
									正常
								</c:when>
								<c:otherwise>
									<span style="color:#FAA28A">异常</span>
								</c:otherwise>
							</c:choose>
							
							</td>
							<td >
							<a  class="btn v lookQuestionDetail" href="${report.resultUrl}"  >查看详情</a>
							</td>
						</tr>
					</table> 
			 </div>
		</div>
	</c:if>	
	<c:if test="${(examinations == null ||fn:length(examinations)==0)&& report==null  }">
			<div class="panel panel-default" >
	       		<div class="panel-body">
		 	    	<span style="color:red">暂无记录</span>
		   		</div>
			</div>
	   	</c:if>
</div>
 
<script type="text/javascript">
 $(".pop").hide();
 $(".container").click(function(){
    $(".pop").hide();
 });
 $(".lookQuestionDetail").click(function(){
 $(".pop").show();
 $(".pop iframe").attr("src",$(this).attr("href"));
 return false;
 });
</script>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>