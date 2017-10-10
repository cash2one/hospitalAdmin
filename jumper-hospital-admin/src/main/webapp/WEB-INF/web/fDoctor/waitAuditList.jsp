<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/style.css?${v }" />
<link href="${pageContext.request.contextPath}/media/css/familyDoctor-report.css?${v }" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/app.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/chart.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/familyDoctor/report.js?${v }"></script> 
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
.doctorName{border: 0px solid #EAEAEA;border-bottom: 1px solid #EAEAEA;width: 160px;margin-top: 10px;}
</style>
 <form action="${pageContext.request.contextPath}/fDoctor/waitAudit" method="post" id="form">
<div class="col-xs-12 col-sm-9">
<!-- 节点开始 -->
<c:forEach items="${page.result }" var ="fe">
 <div class="record">
 	<div class="panel panel-default" style="border:1px solid rgb(240,240,240);border-bottom-style: hidden;margin-bottom: 0px;">
			<div class="panel-body" style="padding: 15px 0px;">
				<table class="table userinfo" style="margin-bottom: 0px;text-align:center;height:50px;">
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
							<td>${fe.gestationalWeek}</td>
							<td>${fe.mobile}</td>
							<fmt:formatDate value="${fe.lastPeriod}" pattern="yyyy-MM-dd" var="d" />
							<td>${d}</td>
						 
							<fmt:formatDate value="${fe.checkTime}" pattern="yyyy-MM-dd HH:mm" var="l" />
							<td>${l}</td>
							<td>${fe.joinAdd}</td>
						</tr>
				</table>
				<div class="detailHide" hidden="hidden"><!-- 默认隐藏部分开始 -->
				<hr style="background-color:rgb(240,240,240)"/>
				<table   class="table detail" style="text-align:center;border:2px solid #F3F3F3;width:98%;margin-left:1%;" >
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
						<c:forEach items="${fe.list}" var="l" varStatus="s" step="2">
						 	<tr>
						 		<td>${fe.list[s.index].project }</td>
						 		<td>${fe.list[s.index].result }</td>
						 			<td>
										<c:choose>
											<c:when test="${fe.list[s.index].resultState==2 }">↑</c:when>
											<c:when test="${fe.list[s.index].resultState==0 }">↓</c:when>
										</c:choose>
									</td>
						 		<td>${fe.list[s.index].area }</td>
						 		
						 		<td>${fe.list[s.index+1].project }</td>
						 		<td>${fe.list[s.index+1].result }</td>
						 			<td>
										<c:choose>
											<c:when test="${fe.list[s.index+1].resultState==2 }">↑</c:when>
											<c:when test="${fe.list[s.index+1].resultState==0 }">↓</c:when>
										</c:choose>
									</td>
						 		<td>${fe.list[s.index+1].area }</td>
						 	</tr>
						</c:forEach>
				</table>
				<c:if test="${fe.rawFiles!=null || fe.recordData!=null || fe.fetalMoveValue!=null}">
				<p style="margin-left:1%;">胎心检测图</p>
				<div  class="chart-div" id="chart${fe.examinationId }" style="text-align: center;background-color: #FFF;border:none;width:1250px;text-align:center;width: 96%; margin-left: 2%;">
		            	<input type="hidden" class="music" value="${fe.rawFiles}" /><!-- 音频文件地址 -->
		            	<input type="hidden" class="data" value="${fe.recordData}" /><!-- 胎心文件地址 json文件后台请求后转换为字符串 -->
		            	<input type="hidden" class="fetalMoveData" value="${fe.fetalMoveValue}" /><!--胎动数据  -->
		                <canvas class="oyLabel"></canvas>
		                <canvas class="contentChart"></canvas>
		                <div>
		                    <canvas class="oxLabel"></canvas>
		                </div>
		            </div>
		            <div class="panel-head" style="width:98%;margin-left:1%;margin-bottom: 72px;">
		                <ul style="background-color:#B9EAE7;">
		                    <li hidden="hidden">${status.index + 1 }</li>
		                    <li hidden="hidden">${consumer.userName }</li>
		                    <li hidden="hidden">${consumer.userAge }岁</li>
		                    <li hidden="hidden">孕${consumer.preganyWeek }周</li>
		                    <li style="margin-left:43%;"><img value="${fe.examinationId }" class="play" src="${pageContext.request.contextPath}/media/image/button_play_hover.png"></li>
		                    <li hidden="hidden" class="timeNow">--</li>
		                    <li hidden="hidden" class="valueNow">--</li>
		                </ul>
		            </div>
		            </c:if>
		            <div class="desc" style="marin-top:10px;">
			           <span style="margin-left:1%;">备注:</span>
			           <textarea id="desc${fe.examinationId }" autocomplete="off" placeholder="0-50个字"   maxlength="200" style="resize: none;height:80px;width:80%;border:1px solid #EAEAEA"></textarea> 
		           </div> 
		            <span style="margin-left:1%;">审核医生:</span>
		            <input  class="doctorName" value="${doctorName}"/>
		            
		            <div style="margin-top:10px;margin-left:1%;">
		                	说明：1. 本报告仅对监测样本负责，仅供临床参考，不做诊断证明之用。<br>
		                	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. 请依据医生建议按时复诊或孕妇自感异常随时复诊。
		                </div>
		                <div class="operate-btn">
		                     
		                    <button type="button" class="btn btn-danger btn-lg btn-ok generate-report" value="${fe.examinationId }">生成报告</button>
		                </div>  
				</div><!-- hide部分 -->
			</div>
		</div>
	<img class="arrow" style="width:100%;margin-bottom: 10px;margin-top: -7px;" alt="" src="${pageContext.request.contextPath}/media/image/t_down.png">
 </div>
 </c:forEach>
 <!-- 节点结束 -->
 <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			<div class="panel panel-default" >
	       		<div class="panel-body">
		 	    	<span style="color:red">暂无记录</span>
		   		</div>
			</div>
	   	</c:if>
  <jsp:include page="../common/page.jsp"></jsp:include>
</div>
</form>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>
<script type="text/javascript">
 $(document).ready(function(){  
	 
	window.preEle=$(".detailHide").eq(0).parent().parent().parent(); //默认第一条数据展开
	$(".detailHide").eq(0).show();
	
	preEle.find("img").last().attr("src","${pageContext.request.contextPath}/media/image/t_up.png");
	  $(".detail tr:odd").css("backgroundColor","#F5F5F5");
        $(".detail th").css("backgroundColor","#E1E1E1");//奇数行  
      
        
         $(".userinfo,.arrow").bind("click",function(){
        	 var activeEle=null;
        	if($(this).hasClass("userinfo")){
        		activeEle=$(this).parent().parent().parent();
        	}
        	if($(this).hasClass("arrow")){
        		activeEle=$(this).parent();
        	}
    
        	if(activeEle.html()==preEle.html()){//表示和上一次的元素一致
        		activeEle.find(".detailHide").eq(0).toggle();
        	console.info(activeEle.find("img").last().attr("src"));
        		if(-1!=(activeEle.find("img").last().attr("src").indexOf("up"))){
        			activeEle.find("img").last().attr("src","${pageContext.request.contextPath}/media/image/t_down.png");
        		}else{
        			activeEle.find("img").last().attr("src","${pageContext.request.contextPath}/media/image/t_up.png");
        		}
        	}else{
        		preEle.find(".detailHide").eq(0).hide();
        		preEle.find("img").last().attr("src","${pageContext.request.contextPath}/media/image/t_down.png");
        		//---------------------------
        		activeEle.find(".detailHide").eq(0).toggle();
        		if(-1!=(activeEle.find("img").last().attr("src").indexOf("up"))){
        			activeEle.find("img").last().attr("src","${pageContext.request.contextPath}/media/image/t_down.png");
        		}else{
        			activeEle.find("img").last().attr("src","${pageContext.request.contextPath}/media/image/t_up.png");
        		}
        	}
        	 preEle=activeEle;
        	 
         });
          
 }); 
</script>
 