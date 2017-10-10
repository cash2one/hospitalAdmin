<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <!-- 页头引入 -->
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<style>
<!--

-->
.message-dialog {width:596px !important; }
</style>
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$("a[type='delete']").click(function(){
			$this = $(this);
			App.confirm_show("提示信息", "确定要结束问诊吗？", function(){
				$.post(
					baseURL+"/consult/toEndConsult1",
					{id:$this.attr("value")},
					function(data){
						if(data=="success"){
							window.location.href=baseURL+"/consult/endList";
						}
					}
				);
			});
		});
		
		
		$(".hospital-im").bind("click", function(){
			//获取某一行用户信息
			var id = $(this).attr("xid");
			var cid =$($("#table").find("input[id='consult"+id+"']")).val();//接收用户名称
			//console.log($("#table").find("input[id='userId"+id+"']"));
			var adminId =$("#adminId").val();//发送用户ID
			var hospitalId =$("#hospitalId").val();//发送医院ID
			var adminUsername =$("#adminUsername").val();//发送用户名称
			var userId =$($("#table").find("input[id='userId"+id+"']")).val();//接收用户ID
			var nickName =$($("#table").find("input[id='nickName"+id+"']")).val();//接收用户名称
			var consult =$($("#table").find("input[id='consult"+id+"']")).val();//接收用户名称
			var busCode ="50101";//业务代码
			var color = 1;
			var fromUserType=3;//  1：医生 2：患者 用户 3：其他 
			//console.log("userid:"+userId +"hospitalId"+hospitalId);
			//建立用户实时聊天
			var src = imURL+"fromUserId="+ hospitalId +"&fromNickName="+adminUsername+"&fromHeadUrl&fromUserType="+fromUserType+"&&toUserId="+userId+"&toNickName="+nickName+"&toHeadUrl&toUserType=2&userType=1&busCode="+busCode+"&color="+color+"&consultantId="+consult;
			//console.log(src);
	     	$('#hospitalChatModalLabel').html("正在和【"+nickName+"】对话中");
	    	$('#hospitalChatIframe').attr("src",src);
	    	$('#hospitalChatModal').modal('show');
		});
	});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/hospital/hospital.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/hospital/artDialog.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/hospital/iframeTools.js?${v }"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/hospital.css?${v }">
<div class="col-xs-12 col-sm-9" style="float:right;">
<form action="${pageContext.request.contextPath}/consult/searchConsult" method="post" id="form" >
    <input type="hidden" id="adminId" name="adminId" value="${admin.id}" />
								       <input type="hidden" id="adminUsername" name="adminUsername" value=" ${admin.hospitalInfo.name}" />
								        <input type="hidden" id="hospitalId" name="hospitalId" value="${hospitalId}" />
   <div class="panel panel-default" ><!--style="margin-right: 200px"  -->
       <div class="panel-body">
	 	    <div class="actions" style="margin-top: auto;margin-bottom: auto;">
	    	     <span style="float:left;"><i class="icon-group"></i>&nbsp;&nbsp;问诊人数：${counts}</span>
	    	     <input type="hidden"  id="hospitalName_"  name="hospitalName" value="${hospitalInfo.name }"/>
	  	    		<div class="col-lg-3" style="float:right">
	                <div class="input-group">
	                    <input type="text" class="form-control" name="searchKey" placeholder="输入手机号码或姓名" value="${keyWord}" />
	                    <span class="input-group-btn">
	                        <button class="btn btn-pink" type="submit"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	            </div>
	  	    </div>
	   </div>
	</div>
 
   <div class="panel panel-default"><!-- style="margin-right: 200px"  -->
       <div class="panel-body">
	   		<div style="margin-bottom:10px;">
	   			<span style="color:#FF749B;">问诊中</span>
	   			<hr style="height:1px;border:none;border-top:1px solid #E0E0E0;" />
	   		</div>
	   		<div>
		       <table class="table table-bordered table-hover" style="vertical-align:middle;" id="table">
		   			<thead>
					    <tr style="background:#ECECEC;font-weight:bold;">
					       <td>序号</td>
					       <td>用户头像</td>
					       <td>用户姓名</td>
					       <td>用户手机</td>
					       <td>问诊类型</td>
					       <td>用户问题</td>
					       <td>提问时间</td>
					       <td>管理员</td>
					       <td>状态</td>
					       <td>操作</td>
					    </tr>
		   			</thead>
		   			<c:if test="${page != null && page.result != null && page.totalCount > 0 }">
		   				<c:forEach items="${consultList.result}" var="consult" varStatus="v">
				   		 	<tbody>
					   		 	<tr>
					   		 		<td style="line-height:80px;">${v.count}</td>
					   		 		
							       <td style="line-height:80px;">
							       <c:choose>
							       <c:when test="${consult.imgUrl==null||consult.imgUrl==''}">
							       		<img src="${pageContext.request.contextPath}/media/image/defaultuser.png" style="max-width:160px;max-height:80px;"/>
							       </c:when>
							       	<c:when test="${fn:contains(consult.imgUrl,'http')}">
							       		<img src="${consult.imgUrl}" style="max-width:160px;max-height:80px;"/>
							       	</c:when>
							       	<c:otherwise>
							       		<img src="${BASE_PATH}/${consult.imgUrl}" style="max-width:160px;max-height:80px;"/>
							       	</c:otherwise>
							       </c:choose>
							        </td>
							       <td style="line-height:80px;" class="nickName">${consult.nickName}</td>
							       <td style="line-height:80px;">${consult.phone}</td>
							       <td style="line-height:80px;">
							       <c:if test="${consult.type!=null&&consult.type>0}">付费问诊</c:if>
							       <c:if test="${consult.type==null||consult.type==0}">免费问诊</c:if>
							       </td>
							       <td style="line-height:80px; <c:if test="${consult.isVoice==1||consult.isVoice==2}">color:grey;</c:if>">
							       <c:set var="content" value="${consult.content}"></c:set>
							      
							       <c:if test="${fn:length(content) > 11}">
							       		<c:out value="${fn:substring(content, 0, 11)}......" />
							       </c:if>
							       <c:if test="${fn:length(content) <= 11}">
							       		<c:out value="${content}" />
							       </c:if>
							       </td>
							       <td style="line-height:80px;"><fmt:formatDate value ="${consult.askTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
							       <td style="line-height:80px;">${consult.adminName}</td>
							       <!--判断，如果状态是2：未回复，3：已回复，4：待处理  -->
							       <td style="line-height:80px;">
							       <c:if test="${consult.status==2||consult.status==4}">未回复</c:if>
							       <c:if test="${consult.status==3}">已回复</c:if>
							         <c:if test="${consult.status==5}">楼主sb</c:if>
							       <%-- <c:if test="${consult.status==4}">待处理</c:if> --%>
							       </td>
							       <td style="line-height:80px;text-align: center;">
								       <%-- <a class="btn btn-info btn-sm" href="${pageContext.request.contextPath}/consult/lookTodayDetails?id=${consult.id}">查看详情  </a> --%>  
								       <a id="hospital-im" class="hospital-im" href="#" xid="${v.count}">查看详情  </a>
								       <input type="hidden" id="nickName${v.count}" name="nickName" value="${consult.nickName}" />
								       <input type="hidden" id="userId${v.count}" name="userId" value="${consult.userId}" />
								        <input type="hidden" id="consult${v.count}" name="consult" value="${consult.id}" />
								   
								       <c:choose>
							       <c:when test="${consult.imgUrl==null||consult.imgUrl==''}">
							       		<input type="hidden" name="headUrl" value="${pageContext.request.contextPath}/media/image/defaultuser.png"/>
							       </c:when>
							       	<c:when test="${fn:contains(consult.imgUrl,'http')}">
							       		<input type="hidden" name="headUrl" value="${consult.imgUrl}" />
							       	</c:when>
							       	<c:otherwise>
							       		<input type="hidden" name="headUrl" value="${BASE_PATH}/${consult.imgUrl}" />
							       	</c:otherwise>
							       </c:choose>
								       <%-- <c:if test="${consult.endVisit==1}"><a href="javascript:void(0)" class="btn btn-danger btn-sm" type="delete" value="${consult.id}">结束问诊</a></c:if> --%><!--${pageContext.request.contextPath}/consult/toEndConsult?id=${consult.id}  -->
							       </td>
						    	</tr>
				   			</tbody>
			   			</c:forEach>
			   			</c:if>
		   					<c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="10" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
				</table>
				<jsp:include page="../common/page.jsp"></jsp:include>
			</div>
      </div>
   </div>
    </form>

</div>
 <!-- 聊天窗口 -->
<div class="modal fade" id="hospitalChatModal" tabindex="-1" role="dialog" aria-labelledby="hospitalChatModalLabel" aria-hidden="true" style="top:10%" data-backdrop="static">
   <div class="modal-dialog message-dialog">
      <div class="modal-content">
         <div class="modal-body">
		 	<iframe id="hospitalChatIframe" width="570px" height="600px" frameborder="0" src=""></iframe>
         </div>
         <div class="modal-footer rule-modal-footer" style="width:570px" >
         	<center>
            <button type="button" class="btn btn-default close-chat" data-dismiss="modal">关闭</button>
            </center>
         </div>
      </div>
	</div>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>