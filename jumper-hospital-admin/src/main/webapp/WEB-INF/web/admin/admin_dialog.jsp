<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 公用dialog -->
<a class="btn btn-pink hide info-dialog-href" data-toggle="modal" data-target="#info-dialog"></a>
<div class="modal" id="info-dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
<div class="modal" id="info-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true">
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
<div class="modal" id="admin-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="margin-top: -120px;">
	<div class="modal-dialog">
		<form id="edit-admin-form" action="${pageContext.request.contextPath }/admin/edit" method="post">
			<input type="hidden" name="id" id="admin-id" value="" /><div class="modal-content">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title order-confirm-title" id="confirmLabelTitle">编辑管理员信息</h4>
		      	</div>
		      	<div class="alert alert-danger hide admin-error" role="alert"></div>
				<div class="modal-body admin-confirm-body">
					<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">姓名</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="name" name="name" placeholder="医生姓名" datatype="*" nullmsg="请输入医生姓名" errormsg="请输入医生姓名!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">用户名</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="username" name="username" placeholder="用户名" datatype="*" nullmsg="请输入用户名" errormsg="请输入用户名!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">手机号</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="mobile" name="mobile" maxlength="11" placeholder="手机号" datatype="*" nullmsg="请输入手机号码" errormsg="请输入手机号码!">
					    </div>
	  				</div>
	  				<c:if test="${!empty majorInfosList }">
		  				<div class="form-group">
		    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">科室</label>
						    <div class="col-sm-10 float-right">
						    	<select  class="form-control" id="editHospitalMajorid" name="hospitalMajorid" >
								    	<c:forEach items="${majorInfosList }" var="majorInfosList" >
								    	   <option value="${majorInfosList.id}">${majorInfosList.hospitalDoctorMajor.major}</option>
								    	</c:forEach>
							    	</select>
						    </div>
		  				</div>
	  				</c:if>
	  				<c:if test="${!empty hospitalDoctorTitle }">
		  				<div class="form-group">
		    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">职称</label>
						    <div class="col-sm-10 float-right">
						    	<select class="form-control" id="editTitleid" name="titleid" >
							    	<c:forEach items="${hospitalDoctorTitle }" var="hospitalDoctorTitle" varStatus="hos">
							    	   <option value="${hospitalDoctorTitle[0]}">${hospitalDoctorTitle[2] }</option>
							    	</c:forEach>
						    	</select>
						    </div>
		  				</div>
		  			</c:if>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">密码</label>
					    <div class="col-sm-10 float-right">
					    	<input type="password" class="form-control" id="password" name="password" placeholder="密码" datatype="*6-100" nullmsg="请设置密码！" errormsg="密码必须在6位以上！">
					    </div>
	  				</div>
	  				
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">确认密码</label>
					    <div class="col-sm-10 float-right">
					    	<input type="password" class="form-control" id="password2" name="password2" placeholder="确认密码" datatype="*" recheck="password" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">医生简介</label>
					    <div class="col-sm-10 float-right">
					    	<textarea  class="form-control" id="introduction" name="introduction" maxlength="1000" placeholder="医生简介" ></textarea>
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label font-normal">所属角色</label>
					    <div class="col-sm-10 float-right admin-role">
					    	<c:if test="${empty roles }">
					    		<span style="color:red">暂无角色信息</span>
					    	</c:if>
					    	<c:if test="${!empty roles }">
					    		<c:forEach var="role" items="${roles }">
					    			<label class='radio-inline'>
					    				<input type='checkbox' class="role-radio" name='role' datatype='*' nullmsg='请选择角色信息！' value='${role.id }'>${role.name }
					    			</label>
					    		</c:forEach>
					    	</c:if>
					    	<!--孕期诊所权限  -->
			    			<label class='radio-inline'>
			    				<input id="isHospitalNst" type='checkbox' name='isHospitalNst' value='1'>孕期诊所
			    			</label>
					    </div>
	  				</div>
	  				<c:if test="${admin.username == 'admin' }">
	  					<div class="form-group belong-hospital">
		    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">所属医院</label>
						    <div class="col-sm-10 float-right">
						    	<input type="hidden" class="form-control" id="hospitalId" name="hospitalId">
						    	<input type="text" class="form-control" id="admin-hospital" name="hospitalName" placeholder="请输入医院名称">
						    </div>
		  				</div>
	  				</c:if>
	  				<!--  云随访添加字段 ----------------------------------------------------      start-->
	  				 <div class="clearFix"></div>
	  				 
	  				<!--  <div id="cloud_content" class="cloud_content">
                    	<hr style="border-top:1px solid #e0e0e0; margin:20px 0px;">
                        <div class="form-group job_num">
	    					<label class="col-sm-2 control-label inline-label font-normal">医生工号</label>
					    	<div class="col-sm-10 float-right">
								<input datatype='*' nullmsg="请输入工号" name="doctorWorkNum" id="job-num" class="form-control" placeholder="请输入工号(英文或数字)" type="text" maxlength="15"
									onkeyup="value=value.replace(/[\W]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
							</div>
                        </div>
                        <div class="form-group">
	    					<label class="col-sm-2 control-label inline-label font-normal">随访账户角色</label>
					    	<div class="col-sm-10 float-right">
					    				<select id="visit-role" class="form-control" name="visitorRole">
                                        	<option value="201">科主任</option>
                                            <option value="202">护士长</option>
                                            <option value="203">护士</option>
                                            <option value="204">主治医生</option>
                                        </select>
					    	</div>
                        </div> 
                        
                        <div id="cloudMsg" style="display: none;">
                        	<p id="workNum" style="color:#d9534f; float:right;margin-top:-14px;margin-right:6px;"></p>
                        </div>
                    </div> -->
                    <!--  云随访添加字段---------------------------------------------------------       end-->
				</div>
				<div class="modal-footer" id="cloudMsg" style="display: none;">
                        	<p id="workNum" style="color:#d9534f; float:right;margin-top:-14px;margin-right:6px;"></p>
                </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default admin-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger admin-confirm-sure">确定</button>
				</div>
			</div>
		</form>
	</div>
</div>

<!-- 修改管理员信息dialog(权限管理用) -->
<a class="btn btn-pink hide auth-admin-confirm-href" data-toggle="modal" data-target="#auth-admin-confrim"></a>
<div class="modal" id="auth-admin-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true">
	<div class="modal-dialog">
		<form id="edit-auth-admin-form" action="${pageContext.request.contextPath }/auth/edit" method="post">
			<input type="hidden" name="id" id="auth-admin-id" value="" />
			<div class="modal-content">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title order-confirm-title">编辑管理员信息</h4>
		      	</div>
		      	<div class="alert alert-danger hide auth-admin-error" role="alert"></div>
				<div class="modal-body auth-admin-confirm-body">
					<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">姓名</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="auth-admin-name" name="name" placeholder="管理员姓名" datatype="*" nullmsg="请输入管理员姓名" errormsg="请输入管理员姓名!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">用户名</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="auth-username" name="username" placeholder="用户名" datatype="*" nullmsg="请输入用户名" errormsg="请输入用户名!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">密码</label>
					    <div class="col-sm-10 float-right">
					    	<input type="password" class="form-control" id="auth-password" name="password" placeholder="密码" datatype="*6-100" nullmsg="请设置密码！" errormsg="密码必须在6位以上！">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">确认密码</label>
					    <div class="col-sm-10 float-right">
					    	<input type="password" class="form-control" id="auth-password2" name="password2" placeholder="确认密码" datatype="*" recheck="password" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">手机号</label>
					    <div class="col-sm-10 float-right">
					   		<input type="text" class="form-control" id="auth-mobile" name="mobile" placeholder="管理员手机" datatype="/^1\d{10}$/" errormsg="手机号码格式不对!">
					    </div>
	  				</div>
	  				<c:if test="${admin.username == 'admin' }">
	  					<div class="form-group auth-belong-hospital">
		    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">所属医院</label>
						    <div class="col-sm-10 float-right">
						    	<input type="hidden" class="form-control" id="authHospitalId" name="hospitalId">
						    	<input type="text" class="form-control" id="auth-admin-hospital" name="hospitalName" placeholder="请输入医院名称">
						    </div>
		  				</div>
	  				</c:if>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default auth-admin-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger auth-admin-confirm-sure">确定</button>
				</div>
			</div>
		</form>
	</div>
</div>


<a class="btn btn-pink hide add-payment-admin-dialog" data-toggle="modal" data-target="#payment-admin-confrim"></a>
<div class="modal" id="payment-admin-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<form id="payment-admin-form" action="${pageContext.request.contextPath }/auth/payEdit" method="post">
			<div class="modal-content">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title auth-payment-title">新增支付管理员信息</h4>
		      	</div>
		      	<div class="alert alert-danger hide auth-payment-error" role="alert"></div>
				<div class="modal-body auth-payment-body">
					<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">姓名</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" name="name" placeholder="管理员姓名" datatype="*" nullmsg="请输入管理员姓名" errormsg="请输入管理员姓名!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">用户名</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" name="username" placeholder="用户名" datatype="*" nullmsg="请输入用户名" errormsg="请输入用户名!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">密码</label>
					    <div class="col-sm-10 float-right">
					    	<input type="password" class="form-control" name="password" placeholder="密码" datatype="*6-100" nullmsg="请设置密码！" errormsg="密码必须在6位以上！">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">确认密码</label>
					    <div class="col-sm-10 float-right">
					    	<input type="password" class="form-control" name="password2" placeholder="确认密码" datatype="*" recheck="password" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">手机号</label>
					    <div class="col-sm-10 float-right">
					   		<input type="text" class="form-control" name="mobile" placeholder="管理员手机" datatype="/^1\d{10}$/" errormsg="手机号码格式不对!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">所属角色</label>
					    <div class="col-sm-10 float-right role" style="margin-top: 5px;">
					    </div>
	  				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default auth-payment-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger auth-payment-sure">确定</button>
				</div>
			</div>
		</form>
	</div>
</div>

<!-- 编辑角色信息dialog -->
<a class="btn btn-pink hide role-edit-href" data-toggle="modal" data-target="#role-edit-confrim"></a>
<div class="modal" id="role-edit-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="role-edit-form" action="${pageContext.request.contextPath }/auth/roleEdit" method="post">
				<input type="hidden" name="id" id="role-id" value="" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title order-confirm-title" id="role-edit-title">角色编辑</h4>
		      	</div>
		      	<div class="alert alert-danger hide role-edit-error" role="alert"></div>
				<div class="modal-body option-confirm-body">
					<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">角色名称</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="role-name" name="name" datatype="*" nullmsg="请输入角色名称" errormsg="角色名称不能为空!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">角色描述</label>
					    <div class="col-sm-10 float-right">
					    	<textarea rows="5" class="form-control"  id="role-desc" name="description"></textarea>
					    </div>
	  				</div>
		  			<div class="clearFix"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default role-edit-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger role-edit-sure">确定</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 编辑权限信息dialog -->
<a class="btn btn-pink hide auth-edit-href" data-toggle="modal" data-target="#auth-edit-confrim"></a>
<div class="modal" id="auth-edit-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="auth-edit-form" action="${pageContext.request.contextPath }/auth/authEdit" method="post">
				<input type="hidden" name="id" id="auth-id" value="" />
				<input type="hidden" name="parent" id="auth-parent" value="" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title order-confirm-title" id="auth-edit-title">模块编辑</h4>
		      	</div>
		      	<div class="alert alert-danger hide auth-edit-error" role="alert"></div>
				<div class="modal-body option-confirm-body">
					<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">权限名称</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="auth-name" name="authority" datatype="*" nullmsg="请输入权限名称" errormsg="权限名称不能为空!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">模块名称</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="auth-desc" name="description" datatype="*" nullmsg="请输入角色名称" errormsg="角色名称不能为空!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">请求路径</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="auth-url" name="url" datatype="*" nullmsg="请输入请求路径" errormsg="请求路径不能为空!">
					    </div>
	  				</div>
		  			<div class="clearFix"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default auth-edit-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger auth-edit-sure">确定</button>
				</div>
			</form>
		</div>
	</div>
</div>