<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/menu.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/daterangepicker-bs3.css">
<!DOCTYPE html>
<html>
<head>
    <title>产后访视</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <style type="text/css">
	html{overflow-x: scroll;}
	body {min-width: 1660px;overflow-y: hidden;}
	.message-dialog{width: 800px;height: 500px;}
 	*{padding: 0; margin: 0; }
	.panel{margin-bottom:0px;}
	.modal {
    position: fixed;
    top: 3%;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 1050;
    display: none;
    overflow: hidden;
    -webkit-overflow-scrolling: touch;
    outline: 0;
}
.modal-dialog {
    width: 50%;
    margin: 30px auto;
}
.modal-header {
    min-height: 16.42857143px;
    padding: 15px;
    border-bottom: 1px solid #fff;
}
	</style>
   
    <!--style-->
    
</head>

<body style="background: #F0F0F0;">
<div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-right" style="margin-top: 20px;">                
                <!-- 档案管理 高危筛查 -->	                
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
	               	       
		                       <ul class="page-sidebar-menu">
		                         <li class="active"><a
											href="${pageContext.request.contextPath}/pregnant/archiveIndex">
												<i class="icon-plus-sign-alt"></i>&nbsp; <span class="title">档案管理</span>
										</a>
										</li>
		                       </ul>
	                    </div>

<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
		<div class="panel" >
        	<div class="gw_rote"> <span class="float-left" style="font-size:16px;">档案管理 > 档案详情 > <a style=" color:#ff7ea5">产后访视</a></span></div>
        	<div class="panel-body">	
                <div class="panel panel-default" style="margin:15px 0px;">
                <div class="panel-body">
                        <table class="gw_xx">
                                <tbody>
	                                <tr>
										<td colspan="4">姓名： ${userBaseInfo.name }</td>
										<td colspan="4">联系电话：${userBaseInfo.mobile }</td>
										<td colspan="4">末次月经：${userBaseInfo.lastPeriod }</td>
										<td colspan="4">预产期：  <fmt:formatDate value="${userBaseInfo.pregnantDate }" pattern="yyyy-MM-dd"/></td>
									</tr>
									<tr>
										<td colspan="4">当前孕周：${pregnantWeek }周 ${pregnantDay }天</td>
										<td colspan="4">状态：
											<c:if test="${userBaseInfo.pregnancyState==0 }">怀孕中</c:if>
											<c:if test="${userBaseInfo.pregnancyState==1 }">已分娩</c:if>
										</td>
										<td colspan="4">分娩日期：${userBaseInfo.deliveryTime }</td>
									</tr>
                        	  </tbody>
                     </table>
                </div>
            </div>
            <div class="panel panel-default float-left" style="margin:15px 0px;width: 100%;">
                <div class=" float-left" style="width:100%;">
                			<%@ include file="/WEB-INF/web/pregnantVisit/headUrl.jsp"%>
                        <div id="myTabContent" class="tab-content">
                           <div class="tab-pane fade in active float-left" id="gwsc">
                             	<div class="panel">
                                        <div class="panel-body" style="padding-left:-15px;"> 
                                            <ul class="nav nav-tabs no-border-margin1" style="border-bottom: none!important;float:left;">
                                                <li class="">
                                               		 <a href="${pageContext.request.contextPath}/postnatal/vistRecord?userId=${userId}&visitType=1&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" 
                                               		 class="remote_href" value="0" style="padding:10px 5px;">产后第一次访视</a>
                                                </li>
                                                <li class="">
                                               		 <a href="${pageContext.request.contextPath}/postnatal/vistRecord?userId=${userId}&visitType=2&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}" 
                                               		    class="remote_href" value="0" style="padding:10px 5px;">产后第二次访视</a>
                                                </li>
                                                <li class="current">
                                                	<a href="#"  class="remote_href" value="1" style="padding:10px 5px;">产后第42天访视</a>
                                                </li>
                                            </ul>
                                        </div>
                                        
        						</div>
                           </div>
						</div>
                </div>
                
             <div  class="panel panel-default  float-left" style="margin:15px;width: 98%;">
                    <div class="weight-title">基本情况</div>
                    <div class="panel-body">
                        <table class="gw_xx">
                                    <tbody>
                                    <tr>
                                        <td >产后休养地： </td>
                                        <td >分娩日期：<fmt:formatDate value="${visitRecord.deliveryDate}" pattern="yyyy-MM-dd"/> </td>
                                        <td >分娩方式：
                                        	<c:if test="${visitRecord.deliveryWay==0 }">顺产</c:if>
                                        	<c:if test="${visitRecord.deliveryWay==1 }">低位产钳 </c:if>
                                        	<c:if test="${visitRecord.deliveryWay==2 }">胎头吸引</c:if>
                                        	<c:if test="${visitRecord.deliveryWay==3 }">臀位助产</c:if>
                                        	<c:if test="${visitRecord.deliveryWay==4 }">臀位牵引</c:if>
                                        	<c:if test="${visitRecord.deliveryWay==5 }">剖宫产</c:if>
                                        	<c:if test="${visitRecord.deliveryWay==6 }">内倒转</c:if>
                                        	<c:if test="${visitRecord.deliveryWay==7 }">毁胎术 </c:if>
                                        	<c:if test="${visitRecord.deliveryWay==8 }">其他 </c:if>
                                        </td>
                                    </tr>                                 
                            </tbody>
                         </table>
                     </div>
                     <div class="weight-title">身体情况</div>
                     <div class="panel-body">
                         <table class="gw_xx">
                                   <tbody>
                                   <tr>
                                       <td >孕产期异常情况：
                                       		<c:if test="${visitRecord.healthStatus==1 }">无</c:if>
                                       		<c:if test="${visitRecord.healthStatus==2 }">有</c:if>,
                                       		<c:if test="${fn:contains(visitRecord.illness,'1')}">高血压</c:if>
                                       		<c:if test="${fn:contains(visitRecord.illness,'2')}">血糖异常</c:if> 
                                       		<c:if test="${fn:contains(visitRecord.illness,'3')}">甲亢/甲低</c:if> 
                                       		<c:if test="${fn:contains(visitRecord.illness,'4')}">心脏病</c:if> 
                                       		<c:if test="${fn:contains(visitRecord.illness,'5')}">胆淤</c:if> 
                                       		<c:if test="${fn:contains(visitRecord.illness,'6')}">产后出血</c:if> 
                                       		 
                                       </td>
                                       <td >健康情况：
<!--                                        		<c:if test="${visitRecord.healthStatus==0 }">无</c:if> -->
<!--                                        		<c:if test="${visitRecord.healthStatus==1 }">有</c:if> -->
                                       </td>
                                       <td >心理状况：${visitRecord.psychologicStatus }</td>
                                       <td ></td>
                                   </tr>
                                   <tr>
                                       <td >血压：${visitRecord.bloodPressure }---${visitRecord.bloodPresureLow }mmHg </td>
                                       <td >体温：${visitRecord.tperture }℃ </td>
                                       <td >乳房：
                                       	   <c:if test="${visitRecord.breast==0 }">未见异常</c:if>
                                       	   <c:if test="${visitRecord.breast==1 }">红肿</c:if>
                                       	   <c:if test="${visitRecord.breast==2 }">疼痛</c:if>
                                       </td>
                                       <td >乳头：
                                       		<c:if test="${visitRecord.breastHead==0 }">未见异常</c:if>
                                       		<c:if test="${visitRecord.breastHead==1 }">平</c:if>
                                       		<c:if test="${visitRecord.breastHead==2 }">皲裂</c:if>
                                       		<c:if test="${visitRecord.breastHead==3 }">未查</c:if>
                                       </td>
                                   </tr>
                                    <tr>
                                        <td >子宫： 
                                       	  <c:if test="${visitRecord.uterus==0 }">正常</c:if>
                                       	  <c:if test="${visitRecord.uterus==1 }">异常</c:if>
                                       </td>
                                       <td >宫底：
                                       	  <c:if test="${visitRecord.fundus==0 }">平脐</c:if>
                                       	  <c:if test="${visitRecord.fundus==1 }">脐下一指 </c:if>
                                       	  <c:if test="${visitRecord.fundus==2 }">脐下二指</c:if>
                                       	  <c:if test="${visitRecord.fundus==3 }">脐下三指</c:if>
                                       	  <c:if test="${visitRecord.fundus==4 }">降入骨盆腔内</c:if>
                                       	  <c:if test="${visitRecord.fundus==5 }">未查</c:if>
                                       </td>
                                       <td >会阴：
                                       	  <c:if test="${visitRecord.perinealWound==1 }">正常</c:if>
                                       	  <c:if test="${visitRecord.perinealWound==2 }">硬结</c:if>
                                       	  <c:if test="${visitRecord.perinealWound==3 }">红肿 </c:if>
                                       	  <c:if test="${visitRecord.perinealWound==4 }">裂开 </c:if>
                                       	  <c:if test="${visitRecord.perinealWound==5 }">潮红 </c:if>
                                       	  <c:if test="${visitRecord.perinealWound==6 }">水肿 </c:if>
                                       	  <c:if test="${visitRecord.perinealWound==7 }">伤口 </c:if>
                                       	  <c:if test="${visitRecord.perinealWound==8 }">增生</c:if>
                                       	  <c:if test="${visitRecord.perinealWound==7 }">未查</c:if>
                                       </td>
                                       <td ></td>
                                   </tr>
                                    <tr>
                                       <td >恶露：
                                       		<c:if test="${visitRecord.loquio==1 }">无</c:if>
                                       	 	<c:if test="${visitRecord.loquio==2 }">有</c:if>
                                       </td>
                                       <td >颜色：</td>
                                       <td >量：</td>
                                       <td >味：</td>
                                   </tr>
                                   <tr>
                                       <td >腹部伤口：
	                                       	 <c:if test="${visitRecord.abdominalWound==1 }">未愈合</c:if>
	                                       	 <c:if test="${visitRecord.abdominalWound==2 }">愈合</c:if>
	                                       	 <c:if test="${visitRecord.abdominalWound==3 }">红肿</c:if>
	                                       	 <c:if test="${visitRecord.abdominalWound==4 }">渗出</c:if>
	                                       	 <c:if test="${visitRecord.abdominalWound==5 }">增生</c:if>
                                       </td>
                                        <td >大便：
                                       	 <c:if test="${visitRecord.defecate==1 }">正常</c:if>
                                       	 <c:if test="${visitRecord.defecate==2 }">干燥</c:if>
                                       	 <c:if test="${visitRecord.defecate==3 }">未查</c:if>
                                       </td>
                                       <td >小便：
                                       	 <c:if test="${visitRecord.piss==1 }">正常</c:if>
                                       	 <c:if test="${visitRecord.piss==2 }">不畅</c:if>
                                       	 <c:if test="${visitRecord.piss==3 }">未查</c:if>
                                       </td>
                                       <td >乳量： 
                                       	  <c:if test="${visitRecord.breastMilk==0 }">少</c:if>
                                       	  <c:if test="${visitRecord.breastMilk==1 }">中</c:if>
                                       	  <c:if test="${visitRecord.breastMilk==2 }">多</c:if>
                                       	  <c:if test="${visitRecord.breastMilk==3 }">无</c:if>
                                       	  <c:if test="${visitRecord.breastMilk==4 }">未查</c:if>
                                       </td>
                                    </tr>
                                    <tr>
                                       <td >会阴切口：
<!--                                        	  <c:if test="${visitRecord.perinealWound==0 }">愈合好</c:if> -->
<!--                                        	  <c:if test="${visitRecord.perinealWound==1 }">未愈合</c:if> -->
<!--                                        	  <c:if test="${visitRecord.perinealWound==2 }">硬结</c:if>	 -->
<!--                                        	  <c:if test="${visitRecord.perinealWound==3 }">红肿</c:if>	 -->
<!--                                        	  <c:if test="${visitRecord.perinealWound==4 }">裂开</c:if> -->
<!--                                        	  <c:if test="${visitRecord.perinealWound==5 }">其他</c:if>		 -->
                                       </td>
                                       <td >其他： </td>
                                   </tr>
                                   <tr>
                                       <td colspan="4" >总体评估：
                                       		<c:if test="${visitRecord.overallAbility==0 }">正常</c:if>
                                        	<c:if test="${visitRecord.overallAbility==1 }">异常</c:if>    
                                       </td>                                                              
                                   </tr>
                           </tbody>
                        </table>
                    </div>
                    <div class="panel-body">
                        <table class="gw_xx" style="border:1px solid #e6e6e6;" cellpadding="10">
                        		<thead style="border-bottom:1px solid #e6e6e6">
                                	<th style="padding-left: 15px;"> 指导：</th>
                                </thead>
                                <tbody>
                                <tr>
                                    <td style="padding-left: 15px;"> 类型：
                                    	<c:if test="${visitRecord.guide==0 }">个人卫生</c:if>
                                		<c:if test="${visitRecord.guide==1 }">心理</c:if>
                                		<c:if test="${visitRecord.guide==2 }">营养</c:if>
                                		<c:if test="${visitRecord.guide==3 }">母乳喂养</c:if>
                                		<c:if test="${visitRecord.guide==4 }">新生儿护理与喂养</c:if>
                                		<c:if test="${visitRecord.guide==5 }">新生儿护理与喂养</c:if>
                                    </td> 
                                </tr>
                                <tr>
                                    <td style="padding-left: 15px;"> 内容：
                                    	${visitRecord.guideContent }
                                    </td>
                                </tr>
                        </tbody>
                     </table>
                </div>
                     	<div class="panel-body" style="padding-left: 30px;">
                        <table class="gw_xx">
                           <tbody>
                                <tr>
                                    <td>转诊：
                                    	<c:if test="${visitRecord.transferVisit==3 }">无</c:if>
                                    	<c:if test="${visitRecord.transferVisit==4 }">有</c:if>
                                    </td>
                                    <td>随访日期：
                                    	<fmt:formatDate value="${visitRecord.visiterDate }" pattern="yyyy-MM-dd"/> 
                                    </td>
                                    <td>访视人员：
                                     	${visitRecord.visiterName}
                                    </td> 
                                </tr>
                                <tr>
                                    <td>原因：
                                    	${visitRecord.transferReason}
                                    </td>
                                    <td>访视机构：
                                    	${visitRecord.visiterInstitutions }
                                    </td>
                                    <td>建议就诊机构及科室：
                                    	${visitRecord.suggested }
                                    </td> 
                                </tr>
                        </tbody>
                     </table>
                </div>
                            </div>
          
                
            </div>
            
            <div class="gw_btn">
            	<a href="${pageContext.request.contextPath}/postnatal/toEditPostVisit?recordId=${visitRecord.id}&visitType=${visitType}&userId=${userId}&archiveId=${archiveId}&archiveBaseId=${archiveBaseId}">
            		<input class="btn btn-default" style="width: 85px; height:40px;padding:0px 0px; margin-right:20px;" readonly value="修改" type="button" >
            	</a>
           	   <input class="btn btn-default" style="width: 85px; height:40px;padding:0px 0px;" readonly value="打印预览" type="button" id="print" >
            </div>

            
            </div>
            
            <div id="print_div" style="display: none;">
	    		 <jsp:include page="/WEB-INF/web/pregnantVisit/chfs_thrid_print.jsp" flush="true" ></jsp:include>
	    	</div>
            
	    </div>
    	
</div>
<script type="text/javascript">
//  $('.panel-body#myTab a').click(function (e) {
//       e.preventDefault();
//       $(this).tab('show');
//     })

//    $(function () { $("[data-toggle='tooltip']").tooltip(); });
   $("#print").click(function(){
		 var ele = document.getElementById("print_div");
         var w = window.open('about:blank');
         w.document.body.innerHTML=ele.innerHTML;
	});
</script>
</div></div>
</body>
</html>
