<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <!-- 页头引入 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>

<script type="text/javascript">
	$(function(){
		$("#type").change(function(){
			$("#visitType").val($("#type").val());
			/* $("#form").attr("action","${pageContext.request.contextPath}/consult/searchEndConsultByType?type="+$("#type").val());*/
			window.location.href=baseURL+"/consult/searchEndConsult?visitType="+$("#type").val(); 
		});
	});
</script>
<div class="col-xs-12 col-sm-9" style="float:right;">
<form action="${pageContext.request.contextPath}/consult/searchEndConsult" method="post" id="form" >
   <div class="panel panel-default"><!-- style="margin-right: 200px"  -->
       <div class="panel-body">
	 	    <%-- <div class="actions" style="margin-top: auto;margin-bottom: auto;">
	 	    	
	  	    		<div class="col-lg-3" style="float:right">
	                <div class="input-group">
	                    <input type="text" class="form-control" name="searchKey" placeholder="输入手机号码或姓名" value="${searchKey}" />
	                    <span class="input-group-btn">
	                        <button class="btn btn-pink" type="submit"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	            </div>
	  	    	<div class="form-group" style="width:22%;float:right;">
    				<label for="inputEmail3" class="control-label inline-label font-normal">问诊类型：</label>
				    <div class="col-sm-8 float-right">
				    	<input type="hidden" id="visitType" value=""/>
				    	<select name="visitType" class="form-control" style="width:200px" id="type">
	             	   		<option value="0" <c:if test="${visitType==0}">selected="selected"</c:if>>全部类型</option>
	                		<option value="1" <c:if test="${visitType==1}">selected="selected"</c:if>>免费问诊</option>
	                		<option value="2" <c:if test="${visitType==2}">selected="selected"</c:if>>付费问诊</option>
	                	</select>
				    </div>
  				</div>
	  	    </div> --%>
	  	    <div class="actions" style="margin-top: auto;margin-bottom: auto;">
	  	    		<div class="form-group" style="width:22%;float:left;">
    				<label for="inputEmail3" class="control-label inline-label font-normal">问诊类型：</label>
				    <div class="col-sm-8 float-right">
				    	<input type="hidden" id="visitType" value=""/>
				    	<select name="visitType" class="form-control" style="width:200px" id="type">
	             	   		<option value="0" <c:if test="${visitType==0}">selected="selected"</c:if>>全部类型</option>
	                		<option value="1" <c:if test="${visitType==1}">selected="selected"</c:if>>免费问诊</option>
	                		<option value="2" <c:if test="${visitType==2}">selected="selected"</c:if>>付费问诊</option>
	                	</select>
				    </div>
  				</div>
	  	    		<div class="col-lg-3" style="float:right">
	                <div class="input-group">
	                
	                    <input type="text" class="form-control" name="searchKey" placeholder="输入手机号码或姓名" value="${searchKey}" />
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
	   			<span style="color:#FF749B;">结束问诊</span>
	   			<hr style="height:1px;border:none;border-top:1px solid #E0E0E0;" />
	   		</div>
	   		<div>
		       <table class="table table-bordered table-hover" style="vertical-align:middle;">
		   			<thead>
					    <tr style="background:#ECECEC;font-weight:bold;">
					       <td>序号</td>
					       <td>用户头像</td>
					       <td>用户姓名</td>
					       <td>用户手机</td>
					       <td>问诊类型</td>
					       <td>提问时间</td>
					       <td>结束时间</td>
					       <td>评价</td>
					       <td>管理员</td>
					       <td>操作</td>
					    </tr>
		   			</thead>
		   			
		   				<c:forEach items="${consultList.result}" var="consultComment" varStatus="v">
		   				<tbody>
					   		 	<tr>
					   		 		<td style="line-height:80px;">${v.count}</td>
							       <td style="line-height:80px;">
							       
							      <%--  <img src="${pageContext.request.contextPath}/media/image/users/${consultComment.imgUrl}" style="max-width:160px;max-height:80px;"/> --%>
							        <c:choose>
							        <c:when test="${consultComment.imgUrl==null||consultComment.imgUrl==''}">
							       		<img src="${pageContext.request.contextPath}/media/image/defaultuser.png" style="max-width:160px;max-height:80px;"/>
							       </c:when>
							       	<c:when test="${fn:contains(consultComment.imgUrl,'http')}">
							       		<img src="${consultComment.imgUrl}" style="max-width:160px;max-height:80px;"/>
							       	</c:when>
							       	<c:otherwise>
							       		<img src="${BASE_PATH}/${consultComment.imgUrl}" style="max-width:160px;max-height:80px;"/>
							       	</c:otherwise>
							       </c:choose>
							       </td>
							       <td style="line-height:80px;">${consultComment.nickName}</td>
							       <td style="line-height:80px;">${consultComment.phone}</td>
							       <td style="line-height:80px;">
							       <c:if test="${consultComment.type!=null&&consultComment.type>0}">付费问诊</c:if>
							       <c:if test="${consultComment.type==null||consultComment.type==0}">免费问诊</c:if>
							       </td>
							       <td style="line-height:80px;"><fmt:formatDate value ="${consultComment.askTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
							       <td style="line-height:80px;"><fmt:formatDate value ="${consultComment.endTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
							       <!--判断，如果状态是2：未回复，3：已回复，4：待处理  -->
							       <td style="line-height:80px;">
							      
							      <c:set var="comment" value="${consultComment.comment}"></c:set>
							      
							       <c:if test="${fn:length(comment) > 10}">
							       		<c:out value="${fn:substring(comment, 0, 10)}......" />
							       </c:if>
							       <c:if test="${fn:length(comment) <= 10}">
							       		<c:out value="${comment}" />
							       </c:if>
							       </td><!-- ${pageContext.request.contextPath}/consult/lookAllEndDetails?id=${consultComment.id} -->
							      <td style="line-height:80px;">${consultComment.adminName}</td>
							       <td style="line-height:80px;text-align: center;"><!--  -->
								       <a class="btn btn-success btn-sm" style="margin-top: 25px;"  
								       href="${pageContext.request.contextPath}/consult/lookTodayDetails?id=${hospitalId}&userId=${consultComment.userId}&consultId=${consultComment.id}">查看详情  </a>
							       </td>
						    	</tr>
						    	</tbody>
			   				</c:forEach>
			   			
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
 
  <!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>