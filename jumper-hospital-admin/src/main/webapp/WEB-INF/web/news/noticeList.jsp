<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/media/css/daterangepicker-bs3.css?${v }" />
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/daterangepicker.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/notice.js?${v }"></script>
  <script type="text/javascript">
   	$(function(){
		
		/** 初始化查询结束时间控件 **/
		$('#endTime').daterangepicker({
			singleDatePicker: true,
			format:"YYYY-MM-DD",
			startDate : new Date,
			language: 'zh-CN' //汉化
		});
		$("#searchBtn").click(function(){
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			if((startTime!=null&&startTime!="")&&(endTime!=null&&endTime!="")&&(startTime>endTime)){
				App.dialog_show("提示！", "开始时间应该小于结束时间！", function(){
					$(".info-dialog-close").trigger("click");
				});
			}else{
				$("#form").submit();
			}
		});
	});
    </script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/notice/index" method="POST" id="form">
    	<div class="panel panel-default">
        	<div class="panel-body">
	           <div style="float:right">
	                    <span>
	                         <button type="button" class="btn btn-success" id="addNotice" title=" 添加公告" ><i class="icon-plus"></i> 添加公告</button> 
	                    </span>
	            </div>
	        	
				<div class="clearFix"></div>
				<div class="clearFix"></div>
				<hr style="margin:20px 10px;">
	            <table class="bordered" >
	                <thead>  
	                    <tr>
	                        <th>序号</th>
	                        <th>公告内容</th>
	                        <th>添加时间</th>
	                        <th>截止时间</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result}" var="notice" varStatus="v">
	                
	                	<tr>
		                    <td style="width:10%;">${v.count}</td>
		                    <td  style="width:30%;">${notice.notice}
		                    	<%-- <c:set var="content" value="${notice.notice}"></c:set>
							      
							       <c:if test="${fn:length(content) > 20}">
							       		<c:out value="${fn:substring(content, 0, 20)}......" />
							       </c:if>
							       <c:if test="${fn:length(content) <= 20}">
							       		<c:out value="${content}" />
							       </c:if> --%>
		                    </td>
		                    <td style="width:15%;"><fmt:formatDate value="${notice.addTime }" pattern="yyyy-MM-dd"/></td>
		                     <td style="width:15%;"><fmt:formatDate value="${notice.endTime }" pattern="yyyy-MM-dd"/></td>
		                     
		                    <td style="width:30%;">
		                    <%-- <button type="button" class="btn btn-info btn-sm" title="详情"  value="${notice.id}|${notice.notice}|${notice.endTime}"><i class="icon-folder-open-alt"></i>详情</button>&nbsp;&nbsp;  --%>
		                   <c:if test="${notice.status==0||notice.status==1}">
		                   		<button type="button" class="btn btn-success edit-btn" title="修改" value="${notice.id}|${notice.notice}|${notice.endTime}"><i class="icon-pencil"></i>修改</button> &nbsp;&nbsp; 
		                   </c:if>	
		                   
		                   <button type="button" class="btn btn-danger delete" title="删除" onclick="del(${notice.id});">删除</button>  &nbsp;&nbsp; 
									
									<c:if	test="${notice.status==0}">
							    	   
									<button type="button" class="btn btn-info publish" onclick="publish(${notice.id});"
										title="发布"><i class="icon-external-link"></i>发布</button> 
									 
							        </c:if>
							        <c:if	test="${notice.status==1}">
							    	   
									<button type="button" class="btn btn-warning back-publish" onclick="backPublish(${notice.id});"
										title="取消发布"><i class="icon-reply"></i>取消发布</button> 
							        </c:if>
		                    </td>
		                </tr>
	                </c:forEach>
	                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="5" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
	            </table>
	            <jsp:include page="../common/page.jsp"></jsp:include>
	        </div>
	    </div>
	</form>
</div>
 <!-- 模态框（Modal） -->
 <a class="btn btn-pink hide myModel" data-toggle="modal" data-target="#desc-confrim"></a>
<div class="modal" id="desc-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="top:10%">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form id="edit-desc-form" action="" method="post">
				<input type="hidden" name="id" id="noticeId" value="" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title desc-confirm-title" id="confirmLabelTitle">公告</h4>
		      	</div>
		      	<div class="alert alert-danger hide desc-error" role="alert"></div>
				<div class="modal-body desc-confirm-body">
					<!--  <div class="form-group">
						<label class="col-sm-2 control-label inline-label font-normal">消息标题</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="news_title" name="title"  value="${data.title}">
					    </div>
					</div>-->

					<div class="form-group" style="height: 150px;">
						<label class="col-sm-2 control-label inline-label font-normal" >公告内容：</label>
					    <div class="col-sm-10 float-right">
					        <textarea class="form-control" id="notice" name="notice" style="width:80%;height:150px;resize:none;" placeholder="字数小于30字。。。" value=""></textarea>
					    </div>
					    
					</div>
					<div class="form-group" style="height: 30px;">
						<label class="col-sm-2 control-label inline-label font-normal" style="float:left;">截止日期：</label>
					   <div class="input-prepend input-group" style="width:500px;float:left">
							<span class="add-on input-group-addon">
								<i class="icon-calendar"></i>
							</span>
							<input type="text" readonly="readonly" style="width: 200px" name="endTime" id="endTime" placeholder="截止日期" class="form-control" value="" />
						</div>
					</div>
					

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default desc-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger desc-confirm-sure" id="ok">确定</button>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>