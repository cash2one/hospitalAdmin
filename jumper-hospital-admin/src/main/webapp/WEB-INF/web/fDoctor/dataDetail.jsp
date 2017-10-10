<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.jumper.hospital.utils.TestItemUtil"%>  
<%@ page import="com.jumper.hospital.vo.familyDoctor.TestItem"%>  
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/style.css?${v }" />
<link href="${pageContext.request.contextPath}/media/css/report.css?${v }" rel="stylesheet">
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
     .other-detail{
	     display:inline-block;
	     height:340px;
	     width:600px;
	     text-align: center;
	     background-color:#F4F4F4;
	     margin: 16px;
     }
     ul li{
     list-style:none;}
     .reset{
    margin:0;
    padding:0;
    border:0;
    outline:0;
    font-size:100%;
    vertical-align:baseline;
    background:transparent;
}
table td{
	border:1px solid #000;
}
.clearfloat:after{display:block;clear:both;content:"";visibility:hidden;height:0}
.clearfloat{zoom:1} 
.rt{
text-align:center;border:2px solid #F3F3F3;margin-left: 0px;
border-right: 0px;    width: 50% !important;
position: absolute;  left:50%;  top: 0px!important;
}
.detailLink{
float: left;margin: 8px;border-bottom: 2px solid red;
 margin-bottom: 0px;   line-height: 32px;
}
.headLink{
border-bottom:1px solid #F0F0F0;padding: 0;background-color:#fff;
}
.reportLink{
float: left;margin: 8px;margin-bottom: 0px;    line-height: 32px;
}
.heartImg{width:32px;height:28px;}
.panel-style{
width: 1300px;margin:auto;border:0px solid;
}
.chart_{text-align:center;background-color: #FFF;border:none;width:1250px;}
.heartBlock{list-style: none;margin:0;padding:0;  margin-bottom: 31px;}
.taidong{display:inline-block;height:60px;width:100px;border-right: 1px solid #000;text-align: center;}
.taidong-font{color:red;font-size:23px;}
.xinlv{display:inline-block;height:60px;width:100px;text-align: center;}
.xinlv-font{color:red;font-size:23px;}
.left-table{text-align:center;border:2px solid #F3F3F3;margin-left: 0px;border-right: 0px; width: 50% !important;}
</style>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9" style="background-color:#fff;">
<div>
			 <ul class="reset clearfloat" class="headLink" >
			 	<li class="reportLink"><a href="${pageContext.request.contextPath}/fDoctor/rePortDetail?id=${id}">报告单</a></li>
			 	<li class="detailLink">详细数据</li>
			 </ul>
		 </div>
			<div class="panel panel-default panel-style" id="panel${consumer.id}">
		        <div class="panel-body" >
			  <c:if test="${heart!=null }"> 
		            <div class="panel-head">
		                <ul>
		                    <li  style="width: 85px;"><img class="heartImg" src="${pageContext.request.contextPath}/media/image/heart.png"/>胎心</li>
		                </ul>
		            </div>
		            <div class="panel-head" style="margin-top:70px;">
		                <ul>
		                    <li> </li>
		                    <li>${user.name }  </li>
		                    <li>${user.age }岁</li>
		                    <li>孕${gestationalWeek}周</li>
		                    <li><img value="${consumer.id }" class="play" src="${pageContext.request.contextPath}/media/image/button_play_hover.png"></li>
		                    <li class="timeNow">--</li>
		                    <li class="valueNow">--</li>
		                </ul>
		            </div>
		            <div class="clearFix"></div>
		            <div class="chart-div chart_" id="chart${consumer.id }">
		            	<input type="hidden" class="music" value="${music }" />
		            	<input type="hidden" class="data" value="${heardata }" />
		            	<input type="hidden" class="fetalMoveData" value="${fetalMoveData }" />
		                <canvas class="oyLabel"></canvas>
		                <canvas class="contentChart"></canvas>
		                <div>
		                    <canvas class="oxLabel"></canvas>
		                </div>
		            </div>
		            <div class="heatTimeLabel" style="text-align:center;">
		                    <span>监测开始时间：${heartTestStart}</span>
		                    <span style="margin: 0px 240px;">监测结束时间：${heartTestend }</span>
		                    <span>监测时长：${testTime}</span>
		            </div>
		          <div style="text-align:left;">
		          		  <ul class="heartBlock" >
		            	<li class="taidong">
		            		<p>胎动次数</p>
		            		<p><span class="taidong-font">${heart.fetalMoveTimes }</span>  次</p>
		            	</li>
		            	<li class="xinlv">
		            		<p>平均胎心率</p>
		            		<p><span class="xinlv-font">${heart.averageRate }</span>  bmp</p>
		            	
		            	</li>
		            	<li class="clearfix"></li>
		            </ul>
		          </div>
		      </c:if>
		       <!-- 其他的监测书记展示 -->
		        <div  style="position: relative;height:300px;">
		        
		        			<table id="leftTable"
		        			 <c:if  test="${ sugar==null && sugar==null && temperature==null  && pressure==null}">
		        				hidden="hidden"
		        			</c:if>
		        			   class="table detail table-bordered left-table" >
								<tr style="background-color:#EEEEEE">
									<th  style="text-align:center;" width="17%">检测项目</th>
									<th  style="text-align:center;" width="16%">结果</th>
									<th  style="text-align:center;" width="17%">参考区间</th>
								</tr> 
								<c:if test="${ sugar!=null}">
								<tr style="position: relative;">
									<td>血糖</td>
									<td>${sugar.averageValue  }${init['sugar'].company}
										<c:choose>
											<c:when test="${sugar.sugarState==0 }">↓</c:when>
											<c:when test="${sugar.sugarState==2 }">↑</c:when>
										</c:choose>
									</td>
									<td>${init['sugar'].range}</td>
								</tr>
							 </c:if> 
							 <c:if test="${ temperature!=null}">
								<tr>
									<td>体温 </td>
									<td>${temperature.averageValue }${init['temperature'].company}
											<c:choose>
											<c:when test="${temperature.temperatureState==0 }">↓</c:when>
								<c:when test="${temperature.temperatureState==2 }">↑</c:when>
										</c:choose>
									 </td> 
									<td>${init['temperature'].range } </td>
								</tr>
							</c:if> 
							<c:if test="${pressure!=null}">
								<tr>
									<td rowspan="2" style="vertical-align: middle;">血压</td>
									<td>收缩压:${pressure.pressureHeight }${init['pressureHeight'].company}
									<c:choose>
											<c:when test="${pressure.pressureHeightResult==0 }">↓</c:when>
											<c:when test="${pressure.pressureHeightResult==2 }">↑</c:when>
										</c:choose>
									</td>       
									<td>${init['pressureHeight'].range} </td>
								</tr>
								<tr>
									<td>舒张压:${pressure.pressureLow }${init['pressureLow'].company}
										<c:choose>
											<c:when test="${pressure.pressureLowResult==0 }">↓</c:when>
											<c:when test="${pressure.pressureLowResult==2}">↑</c:when>
										</c:choose>
									</td>    
									<td>${init['pressureLow'].range}</td>
								</tr>
							
							</c:if> 	
							</table>
		        		 
		        			<table 
		        			id="rightTable"
		        			<c:if  test="${oxygen==null && weight==null}">
		        				hidden="hidden"
		        			</c:if> 
		        			 class="table detail table-bordered rt"  >
								<tr style="background-color:#EEEEEE">
									<th  style="text-align:center;" width="17%">检测项目</th>
									<th  style="text-align:center;" width="16%">结果</th>
									<th  style="text-align:center;" width="17%">参考区间</th>
								</tr> 
								<c:if test="${ oxygen!=null}">
								<tr>
									<td rowspan="2"  style="vertical-align: middle;">血氧</td>
									<td>平均血氧:${oxygen.averageOxygen }${init['oxygen'].company}
										<c:choose>
											<c:when test=" ${oxygen.oxygeResultState==0 }">↓</c:when>
											<c:when test=" ${oxygen.oxygeResultState==2}">↑</c:when>
										</c:choose>
									</td>
									<td>${init['oxygen'].range}</td>
								</tr>
								 
								<tr>
									<td>心率:${oxygen.averagePulse }${init['heart'].company}
											<c:choose>
 										<c:when test=" ${oxygen.pulseResultState==0 }">↓</c:when>
											<c:when test=" ${oxygen.pulseResultState==2}">↑</c:when>
										</c:choose>
									</td>
									<td>${init['heart'].range}</td>
								</tr>
							</c:if> 
								<c:if test="${ weight!=null}">
									<tr>
										<td>体重</td>
										<td>${weight.averageValue }${init['weight'].company}</td>
										<td>无</td>
									</tr>
								</c:if>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							
							</table>
		        					
		        	 <c:if test="${sugar== null && temperature == null && pressure==null && oxygen==null && heart==null}">
			      		<tr>
			      			<td colspan="8" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
		        </div>
		  </div>
	</div>
</div>
<script type="text/javascript">
/* 如果左边的表格没有数据,右边的表格自动左对齐 */
if($("#leftTable").is(":hidden")){
$("#rightTable").css("left","0");
} 
</script>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>
