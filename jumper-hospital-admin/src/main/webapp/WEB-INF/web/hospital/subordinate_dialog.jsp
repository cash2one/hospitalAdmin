<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 公用dialog -->
<a class="btn btn-pink hide info-dialog-href" data-toggle="modal" data-target="#info-dialog"></a>
<div class="modal" id="info-dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
	        	<button type="button" class="close info-dialog-close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title info-dialog-title" id="gridSystemModalLabel">提示！</h4>
	      	</div>
			<div class="modal-body info-dialog-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger info-dialog-sure">确定</button>
			</div>
		</div>
	</div>
</div>

<!-- 公用confirm -->
<a class="btn btn-pink hide info-confirm-href" data-toggle="modal" data-target="#info-confrim"></a>
<div class="modal" id="info-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title info-confirm-title" id="confirmLabelTitle">提示！</h4>
	      	</div>
			<div class="modal-body info-confirm-body">aaa</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default info-confirm-cancel" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-danger info-confirm-sure">确定</button>
			</div>
		</div>
	</div>
</div>

<!-- 修改管理员信息dialog -->
<a class="btn btn-pink hide admin-confirm-href" data-toggle="modal" data-target="#admin-confrim"></a>
<div class="modal" id="admin-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="margin-top: -120px;height:anto" data-backdrop="static">
	<div class="modal-dialog">
		<form id="edit-admin-form" action="${pageContext.request.contextPath }/hos/subordinates" method="post">
			<div class="modal-content">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title order-confirm-title" id="confirmLabelTitle">新增从属医院</h4>
		      	</div>
		      	<div class="alert alert-danger hide subord-error" role="alert"></div>
				<div class="modal-body admin-confirm-body" style='margin-bottom: 50px'>
	  				<div class="form-group">
					    <div class="col-sm-10 float-right admin-role" >
					    	<c:if test="${empty subordinates }">
					    		<span style="color:red">暂无关系医院信息</span>
					    	</c:if>
					    	<c:if test="${!empty subordinates }">
					    		<c:forEach var="hos" items="${subordinates }">
					    			<input type='checkbox' class="role-radio" name='subordinates' datatype='*' nullmsg='请选择从属医院信息！' value='${hos.id }'>${hos.name }
					    			<br/>
					    		</c:forEach>
					    	</c:if>
					    </div>
	  				</div>
	  				<div class="form-group">
					    <div class="col-sm-10 float-right">
					    	<c:if test="${!empty subordinates }">
					    	<input type='checkbox' name='checkall' id='checkall' value=''>全选
					    	</c:if>
					    </div>
	  				</div>
				</div>
				<div class="clearFix" style='margin-bottom: 8px'></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default subordinate-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger subordinate-confirm-sure">确定</button>
				</div>
			</div>
		</form>
	</div>
</div>
