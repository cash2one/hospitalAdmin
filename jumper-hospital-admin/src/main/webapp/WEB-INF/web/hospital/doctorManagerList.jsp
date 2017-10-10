<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ include file="/WEB-INF/web/common/head.jsp" %> --%>
  <!-- 页头引入 -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/hospital.css?${v }">
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<form action="${pageContext.request.contextPath}/doctor/doctorManagerList" method="post" role="form" >
 <div class="col-xs-12 col-sm-9" style="margin-bottom: 10px;height: 80px;">
   <div class="panel panel-default" style="margin-right: 200px">
       <div class="panel-body">
		  <div class="portlet-title">
		
	 	    <div class="actions" style="margin-top: auto;margin-bottom: auto;">
	    	     <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal"
	                    value="0"><i class="icon-plus"></i> 新增管理员</button>
	  	    	<span style="float: right">
	  	    		<div class="input-group">
			  	    		<input type="text" name="keywords" class="form-control" placeholder="关键词搜索" id="keywords" value="${keywords}">
			  	    		<span class="input-group-btn">
			  	    			<button id ="submitBtn" class="btn btn-pink"  type="submit">搜索</button>
			  	    		</span>
	  	    		</div>
	  	    	</span>
	  	    </div>
	   	  </div> 
	   </div>
	</div>
 </div>
 </form>
 <div class="col-xs-12 col-sm-9" >
   <div class="panel panel-default" style="margin-right: 200px">
       <div class="panel-body">
	   		<div style="margin-bottom:10px;">
	   			<span>管理员列表</span>
	   			<hr style="height:1px;border:none;border-top:1px solid #E0E0E0;" />
	   		</div>
	   		<div>
		       <table class="table table-bordered table-hover" style="text-align:center; vertical-align:middle;">
		   			<thead>
					    <tr style="background:#ECECEC;font-weight:bold;">
					       <td>姓名</td>
					       <td>注册名</td>
					       <td>邮箱</td>
					       <td>最近登陆时间</td>
					       <td>最近登陆IP</td>
					       <td>启用</td>
					       <td>锁定</td>
					       <td>锁定时间</td>
					       <td>操作</td>
					    </tr>
		   			</thead>
		   			<c:if test="${not empty doctors}">
		   				<c:forEach items="${doctors}" var="doctor">
				   		 	<tbody>
					   		 	<tr>
							       <td>${doctor.name}</td>
							       <td>${doctor.username}</td>
							       <td>${doctor.email}</td>
							       <td>${doctor.loginDate}</td>
							       <td>${doctor.loginIp}</td>
							       <td>
							       		<c:if test="${doctor.isEnabled}">是</c:if>
							       		<c:if test="${doctor.isEnabled == false}">否</c:if>
							       </td>
							       <td>
							       		<c:if test="${doctor.isLocked}">是</c:if>
							       		<c:if test="${doctor.isLocked == false}">否</c:if>
							       </td>
							       <td>${doctor.lockedDate}</td>
							       <td>
								       <button type="button" class="btn btn-success btn-sm" id="doctor"
								       		 data-toggle="modal" data-target="#myModal" title="修改" value="${doctor.id}">修改</button>
										&nbsp;&nbsp;
										<a href="${pageContext.request.contextPath}/doctor/delete?id=${doctor.id}"> 
								       		<button type="button" class="btn btn-danger btn-sm" title="删除">删除</button>
								       	</a>
							       </td>
						    	</tr>
				   			</tbody>
			   			</c:forEach>
		   			</c:if>
				</table>
			</div>
      </div>
   </div>
</div>

 <!-- 模态框（Modal） -->
 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	  	<div class="modal-content">
	        <form id="submitForm" action="${pageContext.request.contextPath}/hospital/doctorAddOrEdit" method="post">
	      		<div class="modal-header">
	          		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	               					 &times;
	          		</button>
	          		<br/><br/>
					<input type="hidden" name="id" class="mId" >
					<input type="hidden" name="loginFailureCount" class="mloginCount">
					<input type="hidden" name="lockTime" class="mlockTime">
					<table class="table table-bordered table-hover" style="vertical-align:middle;">
						<tr>
							<td class="tableleft"><span style="color:red">*</span>管理员姓名</td>
							<td>
								<input type="text" name="name" class="name"/>
							</td>
						</tr>
						<tr>
							<td class="tableleft"><span style="color:red">*</span>邮箱</td>
							<td>
								<input type="text" name="email" class="email" />
							</td>
						</tr>
						<tr>
							<td class="tableleft">是否启用</td>
							<td id="enabled"></td>
						</tr>
						<tr>
							<td class="tableleft">是否锁定</td>
							<td id="locked" ></td>
						</tr>
						<tr>
							<td class="tableleft"><span style="color:red">*</span>用户名</td>
							<td>
								<input type="text" name="username" class="username"  />
							</td>
						</tr>
						<tr>
							<td class="tableleft"><span style="color:red">*</span>密码</td>
							<td>
								<input type="password" name="password" id="password"  />
							</td>
						</tr>
						<tr>
							<td class="tableleft"><span style="color:red">*</span>确认密码</td>
							<td>
								<input type="password" name="repeat_password" id="repeat_password" />
							</td>
						</tr>
						<tr>
							<td class="tableleft"><span style="color:red">*</span>所属角色</td>
							<td id="role">
							</td>
						</tr>
						<tr>
							<td class="tableleft"><span style="color:red">*</span>所属医院</td>
							<td>
								<select name="hospId" id="hospital" ></select> 
							</td>
						</tr>
					</table>
	       		</div>
   				<!-- <div class="modal-body">
      				在这里添加一些文本
   				</div> -->
	       		<div class="modal-footer">
	         		<button type="button" class="btn btn-default" 
	             			data-dismiss="modal">取消</button>
	          		<button type="submit" class="btn btn-pink" id="submit">确定</button>
	       		</div>
	          </form>
	  	</div><!-- /.modal-content -->
	</div><!-- /.modal -->
  </div>
  <!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>

<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/hospital.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/doctorManager.js"></script>
<script type="text/javascript">
</script>
</html>