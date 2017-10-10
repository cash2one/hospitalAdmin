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
			<div class="modal-body info-confirm-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default info-confirm-cancel" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-danger info-confirm-sure">确定</button>
			</div>
		</div>
	</div>
</div>

<!-- 常用医生模板dialog -->
<a class="btn btn-pink hide common-doctor-href" data-toggle="modal" data-target="#common-doctor-confrim"></a>
<div class="modal" id="common-doctor-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static" style="top:0">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="edit-order-form" action="${pageContext.request.contextPath }/user/edit" method="post">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title common-doctor-confirm-title" id="confirmLabelTitle">常用医生模板</h4>
		      	</div>
				<div class="modal-body order-confirm-body">
					<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal"><span style="color:red">*</span>医生姓名</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="doctorName" name="mobile" placeholder="医生姓名">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">&nbsp;医生职称</label>
					    <div class="col-sm-10 float-right">
					    	<select name="courseDoctor" id="doctorTitle" class="form-control">
					    		<c:if test="${doctorTitle != null }">
					    			<c:forEach var="title" items="${doctorTitle }">
					    				<c:if test="${title != '其它' }">
					    					<option value="${title }">${title }</option>
					    				</c:if>
					    			</c:forEach>
					    			<c:forEach var="title" items="${doctorTitle }">
					    				<c:if test="${title == '其它' }">
					    					<option value="${title }">${title }</option>
					    				</c:if>
					    			</c:forEach>
					    		</c:if>
					    	</select>
					    </div>
	  				</div>
	  				<div class="form-group" style="text-align:center">
	  					<button id="add_doctor" type="button" class="btn btn-success" style="width:100px">添&nbsp;加</button>
	  				</div>
	  				<span style="color:#999999">授课医生列表</span>
	  				<hr style="margin-top:8px;margin-bottom:8px" />
	  				<table id="common_doctor_tab">
	  					<c:if test="${commonDoctor != null }">
	  						<c:forEach var="doctor" items="${commonDoctor }">
	  							<tr>
			  						<td><input type="radio" name="choose_doctor" /></td>
			  						<td>${doctor.doctorName }</td>
			  						<td>${doctor.doctorTitle }</td>
			  						<td class="hide doctor-title-select">
			  							<select>
								    		<c:if test="${doctorTitle != null }">
								    			<c:forEach var="title" items="${doctorTitle }">
								    				<c:if test="${title != '其它' }">
								    					<option <c:if test="${doctor.doctorTitle == title }">selected</c:if> value="${title }">${title }</option>
								    				</c:if>
								    			</c:forEach>
								    			<c:forEach var="title" items="${doctorTitle }">
								    				<c:if test="${title == '其它' }">
								    					<option <c:if test="${doctor.doctorTitle == title }">selected</c:if> value="${title }">${title }</option>
								    				</c:if>
								    			</c:forEach>
								    		</c:if>
								    	</select>
			  						</td>
			  						<td>
			  							<a href="#" value="${doctor.id }" doc="${doctor.doctorName }" class="delete_doctor">删除</a>
			  							<a href='#' value="${doctor.id }" class='edit_doctor'>编辑</a>
			  						</td>
			  						<td class="hide">
			  							<a href="#" value="${doctor.id }" class="cancel_doctor">取消</a>
			  							<a href='#' value="${doctor.id }" class='save_doctor'>保存</a>
			  						</td>
			  					</tr>
	  						</c:forEach>
	  					</c:if>
	  				</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default doctor-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger doctor-confirm-sure">确认选择</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 常用地址模板dialog -->
<a class="btn btn-pink hide common-address-href" data-toggle="modal" data-target="#common-address-confrim"></a>
<div class="modal" id="common-address-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="edit-order-form" action="${pageContext.request.contextPath }/user/edit" method="post">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title common-address-confirm-title">常用地址模板</h4>
		      	</div>
				<div class="modal-body order-confirm-body">
					<div class="form-group">
	    				<label class="col-sm-2 control-label inline-label font-normal"><span style="color:red">*</span>添加地址</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="address_input" name="mobile" placeholder="地址详情">
					    </div>
	  				</div>
	  				<div class="form-group" style="text-align:center">
	  					<button id="add_address" type="button" class="btn btn-success" style="width:100px">添&nbsp;加</button>
	  				</div>
	  				<span style="color:#999999">常用地址列表</span>
	  				<hr style="margin-top:8px;margin-bottom:8px" />
	  				<table id="common_address_tab">
	  					<c:if test="${commonAddress != null }">
	  						<c:forEach var="address" items="${commonAddress }" varStatus="status">
	  							<tr>
			  						<td><input type="radio" name="choose_address" /></td>
			  						<td>常用地址${status.index+1 }</td>
			  						<td>${address.description }</td>
			  						<td><a href="#" value="${address.id }" class="delete_address">删除</a></td>
			  					</tr>
	  						</c:forEach>
	  					</c:if>
	  				</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default address-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger address-confirm-sure">确认选择</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 注意事项dialog -->
<a class="btn btn-pink hide common-notice-href" data-toggle="modal" data-target="#common-notice-confrim"></a>
<div class="modal" id="common-notice-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="edit-order-form" action="${pageContext.request.contextPath }/user/edit" method="post">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title common-notice-confirm-title">注意事项模板</h4>
		      	</div>
				<div class="modal-body notice-confirm-body">
					<div class="form-group">
	    				<label class="col-sm-2 control-label inline-label font-normal"><span style="color:red">*</span>模板内容</label>
					    <div class="col-sm-10 float-right">
					    	<textarea rows="3" placeholder="0-180个字" id="notice_area" style="width:100%;max-width: 473.28px"  ></textarea>
					    </div>
	  				</div>
	  				<div class="form-group" style="text-align:center">
	  					<button id="add_notice" type="button" class="btn btn-success" style="width:100px">添&nbsp;加</button>
	  				</div>
	  				<span style="color:#999999">常用注意事项列表</span>
	  				<hr style="margin-top:8px;margin-bottom:8px" />
	  				<table id="common_notice_tab">
	  					<c:if test="${commonNotice != null }">
	  						<c:forEach var="notice" items="${commonNotice }" varStatus="status">
	  							<tr>
			  						<td><input type="radio" name="choose_notice" /></td>
			  						<td>模板${status.index+1 }</td>
			  						<td style="width:80%">${notice.description }</td>
			  						<td><a href="#" value="${notice.id }" class="delete_notice">删除</a></td>
			  					</tr>
	  						</c:forEach>
	  					</c:if>
	  				</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default notice-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger notice-confirm-sure">确认选择</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 课程搜索dialog -->
<a class="btn btn-pink hide course-search-a" data-toggle="modal" data-target="#course-search-confrim"></a>
<div class="modal" id="course-search-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static" style="top: 10%">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form id="course-search-form" action="" method="post">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="confirmLabelTitle">课程选择</h4>
		      	</div>
				<div class="modal-body course-search-body">
					<div class="input-group" style="width:50%;margin-left: auto;margin-right: auto;margin-bottom:10px">
	                    <input type="text" class="form-control course_search_key" placeholder="课程编号/课程名称/授课医师" value="">
	                    <span class="input-group-btn">
	                        <button class="btn btn-danger" id="search_submit" type="button"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	  				<span style="color:#999999;">课程库</span>
	  				<hr style="margin-top:8px;margin-bottom:8px" />
	  				<table id="course_search_tab" class="bordered">
	  					<thead>  
		                    <tr>
		                    	<th></th>
		                        <th>课程编号</th>
		                        <th>课程名称</th>
		                        <th>费用</th>
		                        <th>授课医师</th>
		                        <th>医师职称</th>
		                        <th>操作</th>
		                    </tr>
		                </thead>
		                <tbody class="search-content">
		                	<tr>
		  						<td><input type="radio" name="choose_course" /></td>
		  						<td>aaa</td>
		  						<td>bbb</td>
		  						<td>ccc</td>
		  						<td>ddd</td>
		  						<td><a href="${pageContext.request.contextPath}/school/offEdit?recordId=${plan.courseId }&libraryType=0&operateType=0">查看详情</a></td>
		  					</tr>
		                </tbody>
	  				</table>
				</div>
				<div class="clearFix" style="margin-bottom:10px"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default course-search-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger course-search-sure">确认选择</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 消息推送dialog -->
<a class="btn btn-pink hide push-message-href" data-toggle="modal" data-target="#push-message"></a>
<div class="modal" id="push-message" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="push-message-form" action="${pageContext.request.contextPath }/schedule/batchPush" method="post">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="push-message-title">消息推送</h4>
		      	</div>
		      	<input type="hidden" name="ids" id="select-ids" value="" />
		      	<input type="hidden" name="courseId" id="course_id" value=""/>
		      	<div class="alert alert-danger hide push-error" role="alert"></div>
				<div class="modal-body desc-confirm-body">
					<div class="form-group">
	    				<label class="col-sm-2 control-label inline-label font-normal">推送形式</label>
					    <div class="col-sm-10 float-right">
					    	<input type="radio" name="pushType" value="1" datatype="*" nullmsg="请选择类型!" checked="checked"/>手机短信推送
					    	<!-- 取消预约时只能手机推送，隐藏APP推送 -->
					    	<!-- <div id="hide_app_push" style="float: left;"><input type="radio" name="pushType" value="2" datatype="*" nullmsg="请选择类型!" />APP消息推送  </div> -->
					    </div>
	  				</div>
					<div class="form-group" style="height:120px;">
	    				<label class="col-sm-2 control-label inline-label font-normal">消息内容</label>
					    <div class="col-sm-10 float-right">
					    	<textarea class="form-control" rows="5" id="message-content" name="content" datatype="*" nullmsg="消息内容不能为空!" value=""></textarea>
					    </div>
	  				</div>
	  			  <div  style="margin-left: 50px;display: none;" id="tips">注: 请填写取消课程的原因,并推送给已预约的客户</div>
	  				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default push-message-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger push-message-sure">确定</button>
				</div>
			</form>
		</div>
	</div>
</div>


<!-- 公用消息聊天窗口 -->
<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true" style="top:10%">
   <div class="modal-dialog message-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <center><h4 class="modal-title"  style="font-weight:bold" id="messageModalLabel"></h4></center>
         </div>
         <div class="modal-body">
         	<center><div style="display:none" class="report"><a href="" class="reportUrl" target= _blank style="font-weight:bold">点击</a>查看监护报告详情</div></center>
		 	<iframe id="chatIframe" width="100%" height="450px" frameborder="0" src=""></iframe>
         </div>
         <div class="modal-footer rule-modal-footer">
         	<center>
            <button type="button" class="btn btn-default close-chat" data-dismiss="modal">关闭</button>
            </center>
         </div>
      </div>
	</div>
</div>