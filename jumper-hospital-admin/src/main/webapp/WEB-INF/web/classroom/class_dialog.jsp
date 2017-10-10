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
			<div class="modal-body info-confirm-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default info-confirm-cancel" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-danger info-confirm-sure">确定</button>
			</div>
		</div>
	</div>
</div>

<!-- 编辑频道信息 -->
<a class="btn btn-pink hide edit-chanel-href" data-toggle="modal" data-target="#edit-chanel-confrim"></a>
<div class="modal" id="edit-chanel-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="edit-chanel-form" action="${pageContext.request.contextPath }/classroom/eidtChanel" method="post">
				<input type="hidden" name="id" id="editChanelId" value="" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title edit-chanel-title">修改频道</h4>
		      	</div>
		      	<div class="alert alert-danger hide edit-chanel-error" role="alert"></div>
				<div class="modal-body order-confirm-body">
					<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal"><span style="color:red">*</span>输入频道名称</label>
					    <div class="col-sm-12">
					    	<input type="text" class="form-control" id="editChanelName" name="chanelName" placeholder="频道名称" datatype="/^[\u4E00-\u9FA5A-Za-z0-9]{1,8}$/" nullmsg="频道名称不能为空!" errormsg="频道名称不能超过8个字，且不能有特殊字符!">
					    </div>
	  				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default edit-chanel-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger edit-chanel-confirm-sure">保存</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 设置banner dialog -->
<a class="btn btn-pink hide add-banner-href" data-toggle="modal" data-target="#add-banner-confrim"></a>
<div class="modal" id="add-banner-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="add-banner-form" action="${pageContext.request.contextPath }/classroom/banner" method="post">
				<input type="hidden" name="newsId" value="" id="news_id" />
				<input type="hidden" name="periods" value="" id="periods" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title common-address-confirm-title">请选择孕阶段</h4>
		      	</div>
		      	<div class="alert alert-danger hide add-banner-error" role="alert"></div>
				<div class="modal-body order-confirm-body">
					<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal"><span style="color:red">*</span>孕阶段</label>
					    <div class="col-sm-12">
					    	<input type="checkbox" name="period_checkbox" value="1" datatype='*' nullmsg='请选择孕阶段！' />孕4-12周&nbsp;
					    	<input type="checkbox" name="period_checkbox" value="2" datatype='*' nullmsg='请选择孕阶段！'  />孕13-28周&nbsp;
					    	<input type="checkbox" name="period_checkbox" value="3" datatype='*' nullmsg='请选择孕阶段！'  />孕29-40周&nbsp;
					    	<input type="checkbox" name="period_checkbox" value="4" datatype='*' nullmsg='请选择孕阶段！'  />0-6个月&nbsp;
					    	<input type="checkbox" name="period_checkbox" value="5" datatype='*' nullmsg='请选择孕阶段！'  />7-12个月&nbsp;
					    	<input type="checkbox" name="period_checkbox" value="6" datatype='*' nullmsg='请选择孕阶段！'  />1-3周岁
					    </div>
	  				</div>
				</div>
				<div class="modal-footer">
					<button type="reset" class="hide" id="banner-confirm-reset"></button>
					<button type="button" class="btn btn-default banner-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger banner-confirm-sure">确认选择</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 选择频道 -->
<a class="btn btn-pink hide choose-chanel-href" data-toggle="modal" data-target="#choose-chanel-confrim"></a>
<div class="modal" id="choose-chanel-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="choose-chanel-form" action="${pageContext.request.contextPath }/classroom/publish" method="post">
				<input type="hidden" name="id" value="" id="choolse_chanel_news_id" />
				<input type="hidden" name="belong" value="49" id="belong" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title choose-chanel-confirm-title">请选择所属频道信息</h4>
		      	</div>
		      	<div class="alert alert-danger hide choose-chanel-error" role="alert"></div>
				<div class="modal-body order-confirm-body">
					<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal"><span style="color:red">*</span>所属频道</label>
					    <div class="col-sm-12">
					    	<select class="form-control" name="chanel" datatype="*" nullmsg="请选择频道信息!">
					    		<c:forEach items="${chc }" var="chanel">
					    			<option value="${chanel.id }">${chanel.chanelName }</option>
					    		</c:forEach>
					    	</select>
					    </div>
	  				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default choose-chanel-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger choose-chanel-sure">确认选择</button>
				</div>
			</form>
		</div>
	</div>
</div>