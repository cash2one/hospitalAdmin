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
<style>
tr:hover {
   background-color: #FBF8E9;
}

table tr td{
	margin:12px 0;
}
td input ,td textarea{
	border:1px solid #EAEAEA;
	height:25px;
}
td input{
	width:200px;
}
td textarea{
	width:400px;
	height:50px;
}
.input-style{
	padding: 12px 1px 12px 13px;
}
td input{
	border:1px solid #DEDEDE;
	height:22px;
}
.tr-title{
	width:75px;
}
.btn-cancel{
	width:122px;background-color: rgb(173, 173, 173);
	border-radius: 5px !important;
}
.btn-ok{
	width:122px;background-color: rgb(255, 110, 127);
	border-radius: 5px !important;
}
.btn-myInfo,.btn-myInfo:focus{
	background-color:#73D6D1;
}
.btn-danger,.btn-danger:focus{
	background-color:#FF739D;
}

 .select-border{
 	border:1px solid #e5e5e5;
	width:98px;
 }
.validateTip{
			display: inline-block;
			position:absolute;
			color:red;
			white-space: nowrap;
}
td{
			position:relative;
		}
.clear-background{
background-color:rgba(0,0,0,0);
}
.userinfo-head{background-color:#ccc;border:1px solid #fff;}

.Validform_checktip {
	
	line-height: 20px;
	height: 20px;
	overflow: hidden;
	color: #999;
	font-size: 10px;
}

.Validform_right {
	color: #71b83d;
	padding-left: 20px;
	background: url(images/right.png) no-repeat left center;
}

.Validform_wrong {
	color: red;
	padding-left: 20px;
	white-space: nowrap;
	background: url(images/error.png) no-repeat left center;
}

.Validform_loading {
	padding-left: 20px;
	background: url(images/onLoad.gif) no-repeat left center;
}

.Validform_error {
	background-color: #ffe7e7;
}

#Validform_msg {
	color: #7d8289;
	font: 12px/1.5 tahoma, arial, \5b8b\4f53, sans-serif;
	width: 280px;
	-webkit-box-shadow: 2px 2px 3px #aaa;
	-moz-box-shadow: 2px 2px 3px #aaa;
	background: #fff;
	position: absolute;
	top: 0px;
	right: 50px;
	z-index: 99999;
	display: none;
	filter: progid:DXImageTransform.Microsoft.Shadow(Strength=3, Direction=135,
		Color='#999999' );
	box-shadow: 2px 2px 0 rgba(0, 0, 0, 0.1);
}

#Validform_msg .iframe {
	position: absolute;
	left: 0px;
	top: -1px;
	z-index: -1;
}

#Validform_msg .Validform_title {
	line-height: 25px;
	height: 25px;
	text-align: left;
	font-weight: bold;
	padding: 0 8px;
	color: #fff;
	position: relative;
	background- color: #999;
	background: -moz-linear-gradient(top, #999, #666 100%);
	background: -webkit-gradient(linear, 0 0, 0 100%, from(#999), to(#666) );
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#999999',
		endColorstr='#666666' );
}

#Validform_msg a.Validform_close:link,#Validform_msg a.Validform_close:visited
	{
	line-height: 22px;
	position: absolute;
	right: 8px;
	top: 0px;
	color: #fff;
	text- decoration: none;
}

#Validform_msg a.Validform_close:hover {
	color: #ccc;
}

#Validform_msg .Validform_info {
	padding: 8px;
	border: 1px solid #bbb;
	border-top: none;
	text-align: left;
}
</style>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
<form hidden="hidden" action="${pageContext.request.contextPath}/fDoctor/viewDetail" method="get" id="toDetail">
  	<input type="hidden" name="id"/>
  	<input type="submit">
</form>

	<form action="${pageContext.request.contextPath}/fDoctor/userinfo" method="POST" id="form">
		<div class="panel panel-default">
        	<div class="panel-body">
	            <div class="col-lg-3" style="float:right">
	                <div class="input-group">
	                    <input type="text" class="form-control" name="condition" placeholder="姓名/电话" value="${condition }" />
	                    <span class="input-group-btn">
	                        <button class="btn btn-danger headColor" type="submit"><i class="icon-search"></i></button>
	                    </span>
	                </div>
	            </div>
			</div>
		</div>
    	<div class="panel panel-default">
	            <table class="table" id="familyUserInfoList" style="height:100%">
	                <thead>  
	                    <tr  class="userinfo-head">
	                        <th width="11%">用户姓名</th>
	                        <th width="6%">年龄</th>
	                        <th width="10%">孕周</th>
	                        <th width="10%">电话</th>
	                        <th width="13%">末次月经</th>
	                        <th width="30%">地址</th>
	                        <th width="8%">是否高危</th>
	                        <th width="12%">操作</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${page.result }" var="data">
	                	<tr> 
	                		 <td  style="vertical-align: middle;" hidden="hidden" >${data.id}</td>
	                	     <td  style="vertical-align: middle;" >${data.name}</td>
		                     <td   style="vertical-align: middle;" >${data.age }</td>
		                     <td   style="vertical-align: middle;" >${data.pregnantWeek }</td>
		                     <td   style="vertical-align: middle;" >${data.mobile}</td>
		                     <td   style="vertical-align: middle;" ><fmt:formatDate value="${data.lastPeriod }" pattern="yyyy-MM-dd"/>  </td>
		                     <td  style="vertical-align: middle;" >${data.joinAdd}</td>
		                    
		                     <c:choose>
		                     	<c:when test="${data.state ==0}">
		                     	 <td    style="vertical-align: middle;"> 否  </td>
		                     	</c:when>
		                     	<c:when test="${data.state ==1}">
		                     	 <td  style="color:#F56666;vertical-align: middle;">中危</td>
		                     	</c:when>
		                     	<c:otherwise>
		                     		<td style="color:#F56666;vertical-align: middle;">高危</td>
		                     	</c:otherwise>
		                     </c:choose>
		                    
		                     <td>
			                     <button class="btn viewDetail clear-background"  ><img src="${pageContext.request.contextPath}/media/image/detail.png"/>查看报告</button>
			                     <button type="button" class="btn updateUser clear-background"  > 修改</button> 
		                     </td>
		                </tr> 
	                </c:forEach>
	               <%--  <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			      		<tr>
			      			<td colspan="8" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if> --%>
	            </table>
	            
	     <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
			<div class="panel panel-default" >
	       		<div class="panel-body">
		 	    	<span style="color:red">暂无记录</span>
		   		</div>
			</div>
	   	</c:if>
	        </div>
	             <jsp:include page="../common/page.jsp"></jsp:include>
	    </div>
	     
	</form>
</div>

<div id="updateUser"  class="modal fade bs" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg" style="width:557px;">
    <div class="modal-content" style="border-top: 2px solid #FF729C !important;">
    	<div class="modal-header" style="background-color:#F0F0F0">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" style="font-size: 37px;line-height: 30px;">&times;</span></button>
	        <h4 class="modal-title" id="gridSystemModalLabel" style="color:#D0D0D0">修改用户信息</h4>
       </div>
	   
      <div class="modal-body"  >
    <form id="update_user_form">
    <input type="hidden" name="id" id="id"/>
    
      <table style="border:0px solid #000;" >
      
      	<tr>
      		<td align="right">姓名:</td>                           
      		<td class="input-style"><input type="text" datatype="/^[a-zA-Z\u4e00-\u9fa5]{1,}$/" errormsg="姓名只能包含英文或中文,且不能为空" nullmsg="请输入姓名" name="name" id="name" /><span class="Validform_checktip"></span></td>
      	</tr>
      	<tr>
      		<td align="right">年龄:</td> 
      		<td class="input-style"><input type="text" name="age" id="age" datatype="/^[1-9][0-9]{1,}$/" errormsg="年龄只能是正整数"  autocomplete="off"/><span class="Validform_checktip"></span></td>
      	</tr>
      	<tr>
      		<td align="right">手机号码</td>
      		<td class="input-style"><input type="text" datatype="/^(1[0-9][0-9])\d{8}$/" errormsg="手机号码不正确" nullmsg="请输入手机号码"  name="mobile" id="mobile"  autocomplete="off"/><span class="Validform_checktip"></span></td>
      	</tr>
      	<tr>
      		<td align="right">末次月经</td>
      		<td class="input-style"><input type="text" name="lastPeriod"  ignore="ignore" datatype="/((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)$))/ig" errormsg="日期格式不正确"   id="lastPeriod" autocomplete="off"/><span class="Validform_checktip"></span></td>
      	</tr>
      	<tr>
      		<td align="right">身份证号</td>
      		<td class="input-style"><input type="text"  ignore="ignore"  datatype="/^[0-9]*[a-zA-Z0-9]*$/" errormsg="身份证号码只能包含数字和英文字母"  name="identity" id="identity"  autocomplete="off"/><span class="Validform_checktip"></span></td>
      	</tr>
      	<tr>
      		<td align="right">身高</td>
      		<td class="input-style"><input type="text" name="height"  ignore="ignore"  datatype="/^([1-9]\d{0,2}){0,1}$/" errormsg="身高只能是最多三位非0正整数"   id="height" autocomplete="off"/><span class="Validform_checktip"></span></td>
      	</tr>
      	<tr>
      		<td align="right">紧急联系人</td>
      		<td class="input-style"><input type="text" name="linkMan"  ignore="ignore"  datatype="/^[a-zA-Z\u4e00-\u9fa5]*$/" errormsg="紧急联系人只能包含英文或中文"  id="linkMan" autocomplete="off"/><span class="Validform_checktip"></span></td>
      	</tr>
      	<tr>
      		<td align="right">紧急联系人电话</td>
      		<td class="input-style"><input type="text" name="linkTel" ignore="ignore" datatype="/^(1[0-9][0-9])\d{8}$/" errormsg="手机号码不正确" nullmsg="请输入手机号码"  id="linkTel" autocomplete="off"/><span class="Validform_checktip"></span></td>
      	</tr>
      	
      	<tr>
      		<td align="right" class="tr-title">家庭住址:</td>
      		<td class="input-style">
				 <input id="address" type="hidden" name="address"/>
				 <span id="address_"></span>
				  <input type="text"  style="width:126px;" id="detailAdd" name="detailAdd" placeholder="输入更具体的地址">
			</td>
      	</tr>
      	
      	                              
      </table>
      </form>
      </div>
	  
	  <div class="modal-footer" style="text-align:center;background-color:#ECECEC">
	  	<button  class="btn btn-default btn-lg btn-cancel"  data-dismiss="modal" >取消</button>
		<button class="btn btn-danger btn-lg btn-ok"  id="submit">确定</button>
	  </div>
    </div>
  </div>
</div>

 <script>
 //$("#updateUser").modal("show");
 	/** 初始化时间控件 **/
		$('#lastPeriod').daterangepicker({
			singleDatePicker: true,
			format:"YYYY-MM-DD",
			startDate : new Date,
			maxDate : new Date,
			language: 'zh-CN' //汉化
		});
		
 //给查看报告按钮点击事件
 	$(".viewDetail").bind("click",function(){
	 	var siblings=$(this).parent().siblings("td");
	 	$("#toDetail [name='id']").eq(0).val(siblings.eq(0).html());
	 	$("#toDetail").submit();
	 	return false;
 	});
 	
 	$("#height").bind("change",function(){
 	if ($(this).val().substring(0,1)=="0"){
 		$(this).val($(this).val().substring(1,$(this).val().length));
 	}
 	});
 	 var update=$("#update_user_form").Validform({
		tiptype:function(msg,o,cssctl){
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				if(o.type==3){
					var objtip=o.obj.siblings(".Validform_checktip");
					cssctl(objtip,o.type);
					objtip.text(msg);
				}else{
					var objtip=o.obj.siblings(".Validform_checktip");
					cssctl(objtip,o.type);
					objtip.text("");
				}
			}
		},
	});
	
update.tipmsg.s="  ";
 $("#submit").click(function(){
 if(update.check()){
   var url = baseURL+"/fDoctor/updateFamilyUser";
   var data_=$("#update_user_form").serialize();
	$.ajax({
				url :url,
				data: data_,
				type : "post",
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				async: false,
				dataType : "json",
				success : function(res) {
				 console.info(res);
				 alert(res.msg);
				 if(res.flag=="success")window.location.reload(true);
				}
			});
 	}else{
 		alert("校验不通过");
 	}
 }); 
 
 
 
 	 
 	 
 	 
 	 
	 $(".updateUser").click(function(){
	   var obj=$(this).parent().parent();
	   var id=obj.find(":hidden").eq(0).html();
	   //通过id获取用户信息
	   var url = baseURL+"/fDoctor/getFamilyUser";
				$.ajax({
							url :url,
							data:{
									id:id
							},
							type : "post",
							async : true,
							dataType : "json",
							success : function(res) {
							$("#updateUser").modal("show");
							console.info(res);
							   $("#name").val(res.data.name);
							   $("#age").val(res.data.age);
							   $("#lastPeriod").val(res.lastPeriod);
							   $("#mobile").val(res.data.mobile);
							   $("#detailAdd").val(res.data.detailAdd);
							   $("#address_").text(res.address);
							   $("#address").val(res.address);
							   $("#id").val(res.data.id);
							        
							    $("#identity").val(res.data.identity);
							    if(0==res.data.height){
							    $("#height").val("");
							    }else{
							    $("#height").val(res.data.height);
							    }
							    $("#linkMan").val(res.data.linkMan);
							    $("#linkTel").val(res.data.linkTel);
							}
						});
	 });
  
 	$('#updateUser').on('hidden.bs.modal', function () {
	   window.location.reload(location.href);
	});
 </script>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>