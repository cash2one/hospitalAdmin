<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/media/css/daterangepicker-bs3.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/jquery.autocomplete.css?${v }" />

<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.autocomplete.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/daterangepicker.js?${v }"></script>
  <script type="text/javascript">
   $(function(){
   		/** 修改资讯 **/
   		//查看消息详情
		$(".detail").click(function(){
			var content = $(this).attr("value");
			App.dialog_show("消息详情", "消息内容："+content, function(){
				$(".info-dialog-close").trigger("click");
			});
		
			/* resetForm();
			var recordId = $(this).attr("value");
			App.ajaxRequest(
				'/myMessageService/modifyList', {'id':recordId}, 'get', true, 'json',
				function resultHandler(data){
					$("#news_title").val(data.title);
					$("#news_content").val(data.content);
				}
			);
				$(".model-dialog-href").trigger("click");*/
		}); 
   });
		
	function resetForm(){
		$("#id").val("");
		$("#news_title").val("");
		$("#news_content").val("");
	}
	
		function del(id){
			App.confirm_show("提示！", "确定要删除消息吗？", function(){
				$(".info-confirm-cancel").trigger("click");
				$.post(
					baseURL+"/myMessageService/delete",
					{id:id},
					function(data){
						if(data=="Y"){
							App.dialog_show("提示", "删除成功！", function(){
								$(".info-dialog-close").trigger("click");
								window.location.reload();
							});
						}else{
							App.dialog_show("提示", "删除失败！", function(){
								$(".info-dialog-close").trigger("click");
							});
						}
					}
				);
			});
			//window.event.returnValue = false; 
		}
		
	  function 	refresh(){
	   window.location.href=baseURL+"/myMessageService/index";
       return true;
	}
    </script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/myMessageService/index" method="POST" id="form">
    	<div class="panel panel-default">
        	<div class="panel-body">
        		<!-- <button type="button" class="btn btn-success" id="news" data-toggle="modal" data-target="#myModal" title=" 推送消息" ><i class="icon-plus"></i> 推送消息</button>  -->
	            <div class="col-lg-3" style="float:right">
	            </div>
	           <div class="col-lg-3" style="float:right">
	                <div class="input-group">
	                    <span class="input-group-btn">
	                          <!--   <button class="btn btn-danger" type="submit">推送消息</button>-->
	                    </span>
	                </div>
	            </div>
	        	<%--<ul class="nav nav-tabs no-border-margin" style="border-bottom: none!important;">
					<li class="<c:if test="${remoteType == 1 }">current</c:if>"><a href="#" class="remote_href" value="1">实时监控</a></li>
					<li class="<c:if test="${remoteType == 0 }">current</c:if>"><a href="#" class="remote_href" value="0">常规监控</a></li>
				</ul>--%>
				<div class="clearFix"></div>
				<div class="clearFix"></div>
				<hr style="margin:20px 10px;">
	            <table class="bordered" style="width:100%;">
	                <thead>  
	                    <tr>
	                        <th>序号</th>
	                        <th>消息内容</th>
	                        <th>推送时间</th>
	                       <th>用户群</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${news}" var="data" varStatus="v">
	                
	                	<tr>
		                    <td style="width:10%;">${v.count}</td>
		                    <td style="width:30%;">
		                    <c:set var="content" value="${data.content}"></c:set>
							      
							       <c:if test="${fn:length(content) > 20}">
							       		<c:out value="${fn:substring(content, 0, 20)}......" />
							       </c:if>
							       <c:if test="${fn:length(content) <= 20}">
							       		<c:out value="${content}" />
							       </c:if>
		                    </td>
		                   <%--  <td><fmt:formatDate value="${data.addTime }" type="both"/></td> --%>
		                  <td style="width:20%"><c:if test="${data.pushTime != null }"><fmt:formatDate value="${data.pushTime }" type="both"/></c:if></td>
		                  <td style="width:20%;">
		                 <%--  <c:choose> --%>
		                 	 ${data.pregnantWeek}
		                  	<%-- <c:when test="${data.pregnantWeek==-1}">
		                  	所有用户
		                  </c:when>
		                  <c:otherwise>
		                  	${data.pregnantWeek}周
		                  	</c:otherwise> --%>
		                  	<%-- <c:when test="${flags=='0'}">
		                  	孕${data.pregnantWeek}周
		                  </c:when>
		                  <c:when test="${flags=='1'}">
		                  	宝宝${data.pregnantWeek}周
		                  </c:when> 
		                  </c:choose>--%>
		                  
		                  </td>
		                    <td style="width:30%;">
		                   	<button type="button" class="btn btn-success btn-sm detail"
											title="详情" value="${data.content}" ><i class="icon-folder-open-alt"></i>详情</button> 
		                    &nbsp;&nbsp;
		                     <a class="btn btn-danger btn-sm" href="###" onclick="del(${data.id});">
		                     	删除
		                     </a>
		    		        <!--  <a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/myMessageService/push?id=${data.id}">推送</a> -->
		                    </td>
		                </tr>
	                </c:forEach>
	                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="7" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
	            </table>
	            <jsp:include page="../common/page.jsp"></jsp:include>
	        </div>
	    </div>
	</form>
</div>
 <!-- 模态框（Modal） -->
<%-- <div class="modal bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="top:10%">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form id="edit-desc-form" action="${pageContext.request.contextPath}/myMessageService/modify" method="post">
				<input type="hidden" name="id" id="descId" value="" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title desc-confirm-title" id="confirmLabelTitle">推送消息</h4>
		      	</div>
		      	<div class="alert alert-danger hide desc-error" role="alert"></div>
				<div class="modal-body desc-confirm-body">
					<!--  <div class="form-group">
						<label class="col-sm-2 control-label inline-label font-normal">消息标题</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="news_title" name="title"  value="${data.title}">
					    </div>
					</div>-->

					<div class="form-group" style="height: 300px;">
						<label class="col-sm-2 control-label inline-label font-normal" >消息内容：</label>
					    <div class="col-sm-10 float-right">
					    <!-- 	<input type="text" class="form-control" style="height: 250px" id="news_content" name="content" value="${data.content}"> -->
					        <textarea class="form-control" id="news_content" name="content" style="width:80%;height:150px;">${data.content}</textarea>
					    </div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default desc-confirm-cancel" data-dismiss="modal" onclick="refresh();">取消</button>
					<button type="submit" class="btn btn-danger desc-confirm-sure">推送</button>
				</div>
			</form>
		</div>
	</div>
</div> --%>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>