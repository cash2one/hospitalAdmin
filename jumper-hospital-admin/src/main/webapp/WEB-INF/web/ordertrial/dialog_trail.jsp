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

<!-- 用户购买医院监控订单dialog -->
<a class="btn btn-pink hide order-confirm-href" data-toggle="modal" data-target="#order-confrim"></a>
<div class="modal" id="order-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="edit-order-form" action="${pageContext.request.contextPath }/user/editOrderNew" method="post">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title order-confirm-title" id="confirmLabelTitle">监护套餐录入</h4>
		      	</div>
		      	<div class="alert alert-danger hide order-error" role="alert"></div>
				<div class="modal-body order-confirm-body">
					<input type="hidden" name="orderId" id="order-id" value="" />
					<input type="hidden" name="id" id="user-id" value="" />
					<input type="hidden" name="payId" id="payId" value="" />
					<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">手机号</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="ordermobile" name="mobile" placeholder="手机号码" datatype="/^1\d{10}$/" nullmsg="请输入手机号码" errormsg="手机号码格式不对!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">真实姓名</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="order-realName" name="realName" placeholder="真实姓名" datatype="*" nullmsg="真实姓名不能为空!" errormsg="真实姓名不能为空!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">年龄</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="order-age" name="age" placeholder="用户年龄" datatype="n" nullmsg="请输入用户年龄" errormsg="年龄必须为数字!">
					    </div>
	  				</div>
	  				<div class="form-group edc-div">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">预产期</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="order-edc" name="edcDate" placeholder="用户预产期" >
					    </div>
	  				</div>
	  				
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal">用户住址</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="order-address" name="address" placeholder="请输入用户住址" datatype="*" ignore="ignore" maxlength="50">
					    </div>
	  				</div>
	  				<!-- 
	  				<div class="form-group other-options">
	    				<label for="otherOpt" class="col-sm-2 control-label inline-label font-normal">其它选项</label>
					    <div class="col-sm-10 float-right">
					    	<input id="otherOpt" name="otherOpt" type="checkbox" value="1">免费新增1次监护服务
					    </div>
	  				</div> -->
	  				<div class="form-group order-service">
	    				<label for="inputEmail3" class="col-sm-2 control-label font-normal">院内监护</label>
					    <div class="col-sm-10 inside-monitor" style="position:relative;margin-left:90px;margin-top:-26px;"><span style="color:red">暂无套餐信息</span></div>
	  				</div>
	  				<!--<div class="form-group order-service">
	    				<label for="inputEmail3" class="col-sm-2 control-label font-normal">常规监护</label>
					    <div class="col-sm-10 float-right normal-monitor"><span style="color:red">暂无套餐信息</span></div>
	  				</div>
	  				--><div class="form-group order-service">
	    				<label for="inputEmail3" class="col-sm-2 control-label font-normal">实时监护</label>
					    <div class="col-sm-10 real-monitor" style="position:relative;margin-left:90px;margin-top:-26px;"><span style="color:red">暂无套餐信息</span></div>
	  				</div>
	  				<!-- 支付类型 -->
	  				<div class="form-group order-service" style="margin-top:20px;">
	    				<label for="channelWay" class="col-sm-2 control-label font-normal">支付方式</label>
	   					<div class="col-sm-10 float-right channel-way">
	   						<c:if test="${payWay != null }">
	   							<c:forEach var="way" items="${payWay }">
					    			<label class="radio-inline"><input name="channelWay" type="radio" value="${way.type }" payId="${way.payConfigId }" >${way.name }</label>
					    		</c:forEach>
	   						</c:if>
	   						<!-- <label class="radio-inline"><input name="channelWay" type="radio" value="1" >支付宝支付</label>
	   						<label class="radio-inline"><input name="channelWay" type="radio" value="2">微信支付</label> -->
	   						<label class="radio-inline"><input name="channelWay" type="radio" value="0" payId="0">线下支付</label>
	   					</div>
	  				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default order-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn hide order-confirm-sure" >提交</button>
					<button type="button" class="btn btn-danger virtual_btn">确定</button>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- 监护服务设置dialog -->
<a class="btn btn-pink hide option-confirm-href" data-toggle="modal" data-target="#option-confrim"></a>
<div class="modal" id="option-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="add-option-form" action="${pageContext.request.contextPath }/rmService/optionEdit" method="post">
				<input type="hidden" name="id" id="option-id" value="" />
				<input type="hidden" name="settingId" id="setting-id" value="" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title order-confirm-title" id="confirmLabelTitle">套餐设置</h4>
		      	</div>
		      	<div class="alert alert-danger hide option-error" role="alert"></div>
				<div class="modal-body option-confirm-body">
					<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal float-left">服务次数</label>
					    <div class="col-sm-7 float-left">
					    	<input type="text" class="form-control" id="option-number" name="number" datatype="n" nullmsg="请输入服务次数" errormsg="服务次数必须为数字!">
					    </div>
					   	<span class="inline-label" style="margin-left: 16px;">次</span>
	  				</div>
	  				<div class="clearFix"></div>
	  				<div class="form-group">
	    				<label for="inputEmail3" class="col-sm-2 control-label inline-label font-normal float-left">套餐费用</label>
					    <div class="col-sm-7 float-left">
					    	<input type="text" class="form-control" id="option-price" name="price" datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" nullmsg="请输入服务费用" errormsg="必须为数字，可以有小数!">
					    </div>
					    <span class="inline-label" style="margin-left: 16px;">元</span>
	  				</div>
		  			<div class="clearFix"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default option-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger option-confirm-sure">确定</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 修改医院信息dialog -->
<a class="btn btn-pink hide hospital-confirm-href" data-toggle="modal" data-target="#hospital-confrim"></a>
<div class="modal" id="hospital-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="top:10%" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="edit-hospital-form" action="${pageContext.request.contextPath }/hos/update" method="post">
				<input type="hidden" name="id" id="dialogHospitalId" value="" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title order-confirm-title" id="confirmLabelTitle">编辑医院信息</h4>
		      	</div>
		      	<div class="alert alert-danger hide hospital-error" role="alert"></div>
				<div class="modal-body option-confirm-body">
					<div class="form-group">
	    				<label class="col-sm-2 control-label inline-label font-normal">医院名称</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="dialogHospitalName" name="name" datatype="/^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/" nullmsg="医院名称不能为空!" errormsg="医院名称不能超过20字，且不能有特殊字符!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label class="col-sm-2 control-label inline-label font-normal">医院地址</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="dialogHospitalAddress" name="address" datatype="*1-30" nullmsg="医院地址不能为空!" errormsg="地址字数不能超过30字!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label class="col-sm-2 control-label inline-label font-normal">医院电话</label>
					    <div class="col-sm-10 float-right">
					    	<input type="text" class="form-control" id="dialogHospitalPhone" name="phone" datatype="/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/" nullmsg="医院电话不能为空!" errormsg="电话格式不正确，区号+号码或号码!">
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label class="col-sm-2 control-label inline-label font-normal">医院介绍</label>
					    <div class="col-sm-10 float-right">
					    	<textarea class="form-control" rows="10" id="dialogDesc" placeholder="1000字以内!" name="introduction" onKeyUp="hospital.checkDesc(this);"></textarea>
					    </div>
	  				</div>
	  				<div class="clearFix"></div>
	  				<div class="form-group">
	    				<label class="col-sm-2 control-label inline-label font-normal">医院照片</label>
					    <div class="col-sm-10 float-right form-inline">
					    	<input id="hospitalFile" name="file" type="file" style="display:none">
							<input id="dialogHospitalImg" class="form-control" name="imgUrl" type="text" value=""  readonly="readonly" datatype="*" nullmsg="医院照片不能为空!" errormsg="医院照片不能为空!">
							<a class="btn btn-info" onclick="$('input[id=hospitalFile]').click();">选择文件</a>
							<span style="color:red">* 图片比例9:6，最小:135x90</span>
					    </div>
	  				</div>
	  				
	  				<img src="" id="dialogHospitalImgPreview" style="width:200px" />
	  				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default hospital-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger hospital-confirm-sure">确定</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 编辑医院科室描述dialog -->
<a class="btn btn-pink hide desc-confirm-href" data-toggle="modal" data-target="#desc-confrim"></a>
<div class="modal" id="desc-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="edit-desc-form" action="${pageContext.request.contextPath }/hos/descUpdate" method="post">
				<input type="hidden" name="id" id="descId" value="" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title desc-confirm-title" id="confirmLabelTitle">编辑描述信息</h4>
		      	</div>
		      	<div class="alert alert-danger hide desc-error" role="alert"></div>
				<div class="modal-body desc-confirm-body">
					<div class="form-group" style="height:120px;">
	    				<label class="col-sm-2 control-label inline-label font-normal">描述内容</label>
					    <div class="col-sm-10 float-right">
					    	<textarea class="form-control" rows="5" id="descContent" placeholder="150字以内!" name="content" datatype="*1-150" nullmsg="医院描述不能为空!" errormsg="医院描述不能超过150字!" value=""></textarea>
					    </div>
	  				</div>
	  				<div class="form-group">
	    				<label class="col-sm-2 control-label inline-label font-normal">描述图片</label>
					    <div class="col-sm-10 float-right form-inline">
					    	<input id="descFile" name="file" type="file" style="display:none">
							<input id="descImg" class="form-control" name="imgUrl"  readonly="readonly" type="text" value="">
							<a class="btn btn-info" onclick="$('input[id=descFile]').click();">选择文件</a>
							<span style="color:red">* 图片尺寸建议:350x350</span>
					    </div>
	  				</div>
	  				
	  				<img src="" id="descPreview" style="width:200px" />
	  				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default desc-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger desc-confirm-sure">确定</button>
				</div>
			</form>
		</div>
	</div>
</div>


<!-- 公用消息聊天窗口 -->
<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true" style="top:10%" data-backdrop="static">
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


<!-- 拒绝退款dialog -->
<a class="btn btn-pink hide cancel-refund-href" data-toggle="modal" data-target="#cancel-refund-confrim"></a>
<div class="modal" id="cancel-refund-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="refund-refuse-form" action="${pageContext.request.contextPath }/consult/refuse" method="post">
				<input type="hidden" name="id" id="refund-id" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="refundTitle">拒绝原因</h4>
		      	</div>
				<div class="modal-body">
					<textarea rows="6" name="reason" class="form-control" id="refuse_reason" style="padding-bottom: 24px;" datatype="*1-200" nullmsg="不能为空!" errormsg="不能超过200字!"></textarea>
					<div style="float:right;top:-24px;position:inherit;right:16px;color:grey"></div>
				</div>
				<div class="refuse-error hide" role="alert" style="margin:0px 0px 14px 10px;color:red">
					<i class="icon-info-sign icon-large"></i> <span class="refuse-error-msg">错误消息啊</span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default refuse-cancel" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-danger refuse-sure">确认</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 选择是否退费dialog -->
<a class="btn btn-pink hide refund-select-href" data-toggle="modal" data-target="#refund-select-confrim"></a>
<div class="modal" id="refund-select-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="refund-select-form" action="" method="post">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="refund-select-title">退费原因</h4>
		      	</div>
				<div class="modal-body" style="overflow: hidden;word-break: break-all">
					
				</div>
				<div class="refuse-reason-error hide" role="alert" style="margin:0px 0px 14px 10px;color:red">
					<i class="icon-info-sign icon-large"></i> 
					<span class="refund-select-error-msg">错误消息啊</span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default refund-select-sure">退费</button>
					<button type="button" class="btn btn-danger refund-select-refuse">拒绝退费</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 拒绝原因dialog -->
<a class="btn btn-pink hide refund-reason-href" data-toggle="modal" data-target="#refund-reason-confrim"></a>
<div class="modal" id="refund-reason-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="refund-reason-form" action="" method="post">
				<input type="hidden" name="refundId" id="refundId_id_2" />
				<input type="hidden" name="status" id="status_id_2" value="-1" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="refund-reason-title">拒绝原因</h4>
		      	</div>
				<div class="modal-body">
					<textarea name="reason" id="reason_id_2" class="form-control" style="padding-bottom: 24px;" rows="6" datatype="*1-200" nullmsg="不能为空!" errormsg="不能超过200字!"></textarea>
					<div style="float:right;top:-24px;position:inherit;right:16px;color:grey"></div>
				</div>
				<div class="refuse-reason-error hide" role="alert" style="margin:0px 0px 14px 10px;color:red">
					<i class="icon-info-sign icon-large"></i> <span class="refuse-reason-error-msg">错误消息啊</span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default refund-reason-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger refund-reason-ok">拒绝退费</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 订单支付弹窗 -->
<a class="btn btn-pink hide order-pay-href" data-toggle="modal" data-target="#order-pay-confrim"></a>
<div class="modal" id="order-pay-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="order-pay-form" action="" method="post">
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="refund-reason-title">支付</h4>
		      	</div>
				<div class="modal-body">
					<div class="form-group form-group-sm" style="margin-bottom:0px">
	    				<label class="col-sm-3 control-label inline-label font-normal">订单号</label>
					    <div class="col-sm-9 float-right form-inline">
					    	<span id="payOrderId"  style="height:30px;line-height:30px"></span>
					    </div>
	  				</div>
	  				<div class="form-group form-group-sm" style="margin-bottom:0px">
	    				<label class="col-sm-3 control-label inline-label font-normal">费用</label>
					    <div class="col-sm-9 float-right form-inline">
					    	<span id="payOrderFee" style="height:30px;line-height:30px"></span>
					    </div>
	  				</div>
	  				<div class="form-group form-group-sm" style="margin-bottom:0px">
	    				<label class="col-sm-3 control-label inline-label font-normal"></label>
					    <div class="col-sm-9 float-right form-inline">
					    	<span id="payOrderChannel" style="height:30px;line-height:30px"></span>
					    </div>
	  				</div>
	  				<div style="height:130px">
	    				<label class="col-sm-3 control-label inline-label font-normal"></label>
					    <div class="col-sm-9 float-right form-inline">
					    	<div id="payOrderImg"></div>
					    </div>
	  				</div>
				</div>
			</form>
		</div>
	</div>
</div>


<a class="btn btn-pink hide info-public-href" data-toggle="modal"  data-target="#info-dialog10"></a>
<div class="modal" id="info-dialog10" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
	        	<button type="button" class="close info-public-close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title info-public-title" id="gridSystemModalLabel">支付成功！</h4>
	      	</div>
			<div class="modal-body info-public-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger info-public-sure">确定</button>
			</div>
		</div>
	</div>
</div>
