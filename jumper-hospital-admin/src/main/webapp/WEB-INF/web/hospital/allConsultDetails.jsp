<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="${pageContext.request.contextPath}/media/css/umeditor.css" type="text/css?${v }" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/hospital.css?${v }">

   <div class="clearfix"></div>
   <!-- BEGIN CONTAINER -->
   <div class="page-container">
   <%@ include file="../common/head.jsp"%>
      <!-- BEGIN SIDEBAR -->
      <div class="page-content">
         <!-- END PAGE HEADER-->
          <div class="col-md-12 col-sm-12" style="width:70%;height: auto;">
               <!-- BEGIN PORTLET-->
               <div class="portlet">
                  <div class="portlet-body" id="chats">
                     <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: auto;"><div class="scroller" style="height: auto; overflow: hidden; width: auto;" data-always-visible="1" data-rail-visible1="1">
                        <ul class="chats" style="list-style: none;">
                        <li class="in">
                              <%-- <img style="max-width:160px;max-height:80px;" class="avatar img-responsive" alt="" src="${pageContext.request.contextPath}/media/image/users/${consult.userInfo.userImg}"> --%>
                              <c:choose>
							       	<c:when test="${fn:contains(consult.userInfo.userImg,'http')}">
							       		<img src="${consult.userInfo.userImg}" class="avatar img-responsive" style="max-width:160px;max-height:80px;"/>
							       	</c:when>
							       	<c:otherwise>
							       		<img src="${BASE_PATH}/${consult.userInfo.userImg}" class="avatar img-responsive" style="max-width:160px;max-height:80px;"/>
							       	</c:otherwise>
							       </c:choose>
                              <div class="message" style="background-color:#EFF7F7;">
                                 <span class="arrow"></span>
                                 <a href="#" class="name">${consult.userInfo.nickName}</a>
                                 <span class="datetime">咨询时间： ${consult.addTime}</span><br/>
                                 <span class="body"><!-- style="display:block;background-color: white;max-width:550px;min-height:45px;line-height:45px;border-radius:10px;font-size: 16px;"   -->
                                 ${consult.content }<br>
                                 </span>
                              </div>
                           </li>
                           
                        <c:forEach items="${replys}" var="reply">
                        	<c:if test="${reply.talker==0}">
                        		<li class="in">
                              <%-- <img style="max-width:160px;max-height:80px;" class="avatar img-responsive" alt="" src="${pageContext.request.contextPath}/media/image/users/${consult.userInfo.userImg}"> --%>
                              <c:choose>
							       	<c:when test="${fn:contains(consult.userInfo.userImg,'http')}">
							       		<img src="${consult.userInfo.userImg}" class="avatar img-responsive" style="max-width:160px;max-height:80px;"/>
							       	</c:when>
							       	<c:otherwise>
							       		<img src="${BASE_PATH}/${consult.userInfo.userImg}" class="avatar img-responsive" style="max-width:160px;max-height:80px;"/>
							       	</c:otherwise>
							       </c:choose>
                              
                              <div class="message"  style="background-color:#EFF7F7;">
                                 <span class="arrow"></span>
                                 <a href="#" class="name">${consult.userInfo.nickName}</a>
                                 <span class="datetime">咨询时间： ${reply.addTime}</span><br/>
                                 <span class="body"><!-- style="display:block;background-color: white;max-width:550px;min-height:45px;line-height:45px;border-radius:10px;font-size: 16px;"   -->
                                 ${reply.replyContent }<br>
                                 </span>
                              </div>
                           </li>
                        	</c:if>
                        	<!--指院方  -->
                        	<c:if test="${reply.talker==1}">
                        		 <li class="out">
                               <%-- <img style="max-width:160px;max-height:80px;" class="avatar img-responsive" alt="" src="${pageContext.request.contextPath}/media/image/${hospitalInfo.imgUrl}"> --%>
                             
                              <c:choose>
							       	<c:when test="${fn:contains(hospitalInfo.imgUrl,'http')}">
							       		<img src="${hospitalInfo.imgUrl}" class="avatar img-responsive" style="max-width:160px;max-height:80px;"/>
							       	</c:when>
							       	<c:otherwise>
							       		<img src="${BASE_PATH}/${hospitalInfo.imgUrl}" class="avatar img-responsive" style="max-width:160px;max-height:80px;"/>
							       	</c:otherwise>
							       </c:choose>
                              <div class="message"  style="background-color:#EFF7F7;">
                                 <span class="arrow"></span>
                                 <a href="#" class="name">天使医生</a>
                                 <span class="datetime">回复时间：： ${reply.addTime}  </span><br/>
                                 <span class="body"><!-- style="display:block;background-color: pink;max-width:550px;min-height:45px;line-height:45px;border-radius:10px;color: black;font-size: 16px;margin-left:350px;"   -->
                                 ${reply.replyContent}<br>
                                 
                                 </span>
                              </div>
                             
                           </li>
                        	</c:if>
                        	
                        </c:forEach>
                        
                           
                        </ul>
                     </div>
                     
               
                    
                     </div>
                  </div>
               </div>
               <!-- END PORTLET-->
            </div>
      </div>
      </div>
      <div style="width:1230px;height:110px;overflow:auto;background-color:#EFF7F7;margin-top:50px;margin-left:250px;">
      <img alt="" src="${pageContext.request.contextPath}/media/image/evaluate.png" style="float:right;"/>
       <form id="myform" action="" method="post">
                     <span style="color:#FF749B;">用户评价</span><br/>
                     <hr/>
                    <div class="page-content">
                 			<div class="input-cont" >
                 			<c:if test="${consult.evaluate==1}">
                 			<center><span style="font-size:20px;">
                 			<c:if test="${comment.statisfaction!=null}">
                 			<c:if test="${comment.statisfaction==1}">解答专业</c:if>
                 			<c:if test="${comment.statisfaction==2}">有点帮助</c:if>
                 			<c:if test="${comment.statisfaction==3}">帮助不大</c:if>
                 			</c:if>
                 			&nbsp;&nbsp;
                 			<c:if test="${comment.content!=null}"></c:if>
                 			${comment.content}
                 			</span></center>
                 			</c:if>
                 			<c:if test="${consult.evaluate==0}">
                 			<center><span style="font-size:20px;">用户暂未评价！</span></center>
                 			</c:if>
                 			
                 			
                 			</div>
                  	</div>
                  </form>
      	</div>
     
      
      
      <!-- END PAGE -->
   
   <a class=" btn default" data-toggle="modal" href="#long" id="dialog_img" style="display:none"></a>
   
   <!-- 图片点击以后弹出层放大div -->
    <div id="long" class="modal fade" tabindex="-1" data-replace="true">
        <div class="modal-dialog">
           <div class="modal-content">
              <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
              </div>
              <div class="modal-body" style="text-align: center;">
                 <img alt="" id="big_img" style="width: 500px;" src="">
              </div>
           </div>
        </div>
     </div>
   
   
   <!-- BEGIN FOOTER -->
    <%@ include file="../common/foot.jsp"%> 
   <!-- BEGIN PAGE LEVEL PLUGINS -->
   <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/common/ajaxfileupload.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/consultantReply.js"></script>
        
 	 <script>
      jQuery(document).ready(function() {  		 
		$(".visible_status").val($("#question_status").val());
    	$('.categroy_id option').filter(function () { return this.text == $("#question_type_name").val(); }).attr('selected', true)
      });
   </script>
   <!-- END JAVASCRIPTS -->
   
</body>
<!-- END BODY -->
</html>
