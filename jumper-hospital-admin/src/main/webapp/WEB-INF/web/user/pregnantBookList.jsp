<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <!-- 页头引入 -->
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>

<div class="col-xs-12 col-sm-9" style="float:right;">
<form action="${pageContext.request.contextPath}/pregnantBook/pregnantBookList" method="post" id="form" >
   <div class="panel panel-default" >
       <div class="panel-body">
		
	 	    <div class="actions" style="margin-top: auto;margin-bottom: auto;">
	  	    		<div class="col-lg-3" style="float:right;">
	                <div class="input-group">
	                    <input type="text" class="form-control" name="keyWords" id="keyWords" placeholder="输入手机号码、姓名或证件号码" value="${keyWords}" />
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
	   		<div>
		       <table class="table table-bordered table-hover" style="vertical-align:middle;">
		   			<thead>
					    <tr style="background:#ECECEC;font-weight:bold;">
					       <td>序号</td>
					       <td>用户姓名</td>
					       <td>用户电话</td>
					       <td>出生日期</td>
					       <td>证件号码</td>
					       <td>预产期</td>
					       <td>填表时间</td>
					       <td>操作</td>
					    </tr>
		   			</thead>
		   			
		   				<c:forEach items="${pregnantBookList.result}" var="usersBookBasicInfo" varStatus="v">
				   		 	<tbody>
					   		 	<tr>
					   		 	  <td style="line-height:30px;">${v.count}</td>
							      <td style="line-height:30px;">${usersBookBasicInfo.name}</td>
							      <td style="line-height:30px;">${usersBookBasicInfo.mobile}</td>
							      <td style="line-height:30px;"><fmt:formatDate value="${usersBookBasicInfo.birthday}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
							      <td style="line-height:30px;">${usersBookBasicInfo.ID_no}</td>
							      <td style="line-height:30px;"><fmt:formatDate value ="${usersBookBasicInfo.userInfo.expectedDateOfConfinement}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
							      <td style="line-height:30px;"><fmt:formatDate value ="${usersBookBasicInfo.addTime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
							      <td style="line-height:30px;">
							      	<a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/pregnantBook/lookDetails?uid=${usersBookBasicInfo.userInfo.id}"><i class="icon-folder-open-alt"></i>查看详情</a>
							      	<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/pregnantBook/resetUserInfoBook?uid=${usersBookBasicInfo.userInfo.id}">重置</a>
							      </td>
						    	</tr>
				   			</tbody>
			   			</c:forEach>
		   					<c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="8" style="color:red">暂无记录</td>
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