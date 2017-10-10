<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/media/css/daterangepicker-bs3.css?${v }" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/jquery.autocomplete.css?${v }" />
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.autocomplete.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/daterangepicker.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/message.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/report/finish.js?${v }"></script>
<script type="text/javascript">
function chaxunCount(){
	var hospitalId = $("#userhospitalId").val();
	
	
	$.post("${pageContext.request.contextPath}/pregnantBook/userCount",{hospitalId:hospitalId},function(result){
		var tnc = $("#todayNew").html();
		var auc = $("#allUserCount").html();
		if( auc !=result.allUserCount ){
			$("#todayNew").html(result.todayCount); 	
			$("#allUserCount").html(result.allUserCount);
		}
	});
}
	$(function(){
		chaxunCount();
		timedCount();
		$(".user_type").click(function(){
			var type = $(this).attr("value");
			$("#userType").val(type);
			$("#form").submit();
		});
		
		$("#countSelect").click(function(){
			var hospitalId = $("#userhospitalId").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var startDate = new Date(startTime);
			var endDate = new Date(endTime);
			if(startDate > endDate){
				App.dialog_show("", "开始时间不能大于结束时间！", function(){
					$(".info-dialog-close").trigger("click");
				});
			}else{
				$.post("${pageContext.request.contextPath}/pregnantBook/newUserCount",{startTime:startTime,endTime:endTime,hospitalId:hospitalId},function(result){
					$("#timeUserCount").html(result.newCount);
				});
			}
			
			return false;
		});
		function timedCount()
		{
			setInterval("chaxunCount()",10000);
		}
		
	});
	
</script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/pregnantBook/userList" method="POST" id="form">
		<input type="hidden" name="type" id="userType" value="${type }" />
		<input type="hidden" name="userhospitalId" id="userhospitalId"  value="${hospitalId}" />
    	<div class="panel panel-default">
        	<div class="panel-body">
        	<jsp:include page="/WEB-INF/web/hospital/countNew.jsp"></jsp:include>
        		 <button type="button" class="btn btn-success" id="news" title=" 推送消息" style="margin-top: 25px;" ><i class="icon-plus"></i> 推送消息</button>
	            <div class="col-lg-3" style="float:right;margin-top: 25px;">
	                <div class="input-group">
	                    <input type="text" class="form-control" name="searchKey" placeholder="姓名/手机号码" value="${searchKey }" />
	                    <span class="input-group-btn">
	                        <button class="btn btn-danger" type="submit"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	            </div>
				<div class="clearFix"></div>
				<ul class="nav nav-tabs no-border-margin" style="border-bottom: 1px solid #eee!important;;float:left;margin:20px 0px;width:100%">
	        		<li class="<c:if test="${type == 0 }">current</c:if>"><a href="#" class="user_type" value="0">怀孕用户</a></li>
					<li class="<c:if test="${type == 1 }">current</c:if>"><a href="#" class="user_type" value="1">已有宝宝</a></li>
				</ul>
				<br>
				<input type="hidden" id="weekList" value="${pregnantWeekList}"/>
				<input type="hidden" id="babyList" value="${babyList}"/>
	            <table class="bordered">
	                <thead>  
	                    <tr>
	                        <th>序号</th>
	                        <th>用户姓名</th>
	                        <c:if test="${type == 0 }"><th>孕周</th></c:if>
	                        <th>年龄</th>
	                        <c:if test="${type == 1 }"><th>宝宝年龄</th></c:if>
	                        <c:if test="${type == 0 }"><th>预产期</th></c:if>
	                        <c:if test="${type == 1 }"><th>宝宝生日</th></c:if>
	                        <th>手机号码</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result }" var="data" varStatus="v">
	                	<tr>
		                    <td>${v.count}</td>
		                    <td>${data.userName }</td>
		                    <c:if test="${type == 0 }">
			                    <td>${data.preganyWeek }</td>
			                    <td>${data.age }</td>
			                </c:if>
			                <c:if test="${type == 1 }"> 
			                	<td>${data.age }</td>
			                    <td>${data.preganyWeek }</td>
			                </c:if>
		                    <td>${data.preganyDate }</td>
		                    <td>${data.mobile }</td>
		                    <td>
		                    	<a role="button" class="btn btn-success btn-sm" title="查看详情" href="${pageContext.request.contextPath}/pregnantBook/userDetail?id=${data.id}"><i class="icon-folder-open-alt"></i> 详情</a>
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
 <a class="btn btn-pink hide myModel" data-toggle="modal" data-target="#desc-confrim"></a>
<div class="modal bs-example-modal-lg" id="desc-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="top:10%">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form id="edit-desc-form" action="${pageContext.request.contextPath}/myMessageService/modify" method="post">
				<input type="hidden" name="id" id="descId" value="" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title desc-confirm-title" id="confirmLabelTitle">推送消息</h4>
		      	</div>
		      	<div class="alert alert-danger errorMessage" role="alert" id="errorMessage" style="display:none;"></div>
				<div class="modal-body desc-confirm-body">
					<div class="form-group" style="height: 150px;">
						<label class="col-sm-2 control-label inline-label font-normal" >消息内容：</label>
					    <div class="col-sm-10 float-right">
					        <textarea class="form-control" id="news_content" name="content" style="width:80%;height:150px;resize:none;" placeholder="字数小于100字。。。" value=""></textarea>
					    </div>
					    
					</div>
					<div class="form-group" style="height: 30px;">
						<label class="col-sm-2 control-label inline-label font-normal" style="float:left;">推送对象：</label>
					   <div class="input-prepend input-group" style="width:500px;float:left">
							<!-- <select name="pushPeople" id="pushPeople" class="form-control" style="width:140px;display:inline">
             	   			<option value="-1">所有用户</option>
                			</select> -->
                			<select name="select1" id="select1" class="form-control" style="width:165px;display:inline">
                			</select>&nbsp;&nbsp;
                			<select name="select2" id="select2" class="form-control" style="width:160px;display:inline;margin-left:5px;">
                				<!-- <option value="-2">--请选择对象--</option> -->
                			</select>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default desc-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger pushMessage" id="push">推送</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>