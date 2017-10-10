<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/media/js/app.js?${v }"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/media/js/Validform_v5.3.2_min.js"></script>
<!-- 内容页 -->
<style>
.headColor {
	color: #ffffff;
}

.page-border {
	height: 30px;
	line-height: 15px;
	background-color: #FFFFFF;
}

.page-border:active {
	color: #000;
}
/*分页按钮和输入框的高度*/
.input {
	border: 1px solid #E0E0E0;
	width: 50px
}

.modal-body ul li {
	padding: 6px 0;
}

table tr td {
	margin: 12px 0;
}

td input,td textarea {
	border: 1px solid #EAEAEA;
	height: 25px;
}

td input {
	width: 200px;
}

td textarea {
	width: 400px;
	height: 50px;
}

.input-style {
	padding: 12px 1px 12px 13px;
}

td input {
	border: 1px solid #DEDEDE;
	height: 22px;
}

.tr-title {
	width: 75px;
}

.btn-cancel {
	width: 122px;
	background-color: rgb(173, 173, 173);
	border-radius: 5px !important;
}

.btn-ok {
	width: 122px;
	background-color: rgb(255, 110, 127);
	border-radius: 5px !important;
}

.btn-myInfo,.btn-myInfo:focus {
	background-color: #73D6D1;
}

.btn-danger,.btn-danger:focus {
	background-color: #FF739D;
}

.select-border {
	border: 1px solid #e5e5e5;
	width: 98px;
}

td {
	position: relative;
}

textarea {
	resize: none;
}
.mleft{
margin-left: -5px;
}
.btnGradient{
background-color:rgba(0,0,0,0);
}

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
<form action="${pageContext.request.contextPath}/fDoctor/doctor"
	method="post" id="form">

	<div class="col-xs-12 col-sm-9">
		<div class="panel panel-default">
			<div class="panel-body">
				<button type="button" class="btn btn-success headColor "
					data-toggle="modal" id="add_btn">
					<i class="icon-plus"></i> 新增医生
				</button>
				<div class="col-lg-3" style="float:right">
					<div class="input-group">
						<input type="text" class="form-control" name="username"
							placeholder="登录用户名" autocomplete="off" value="${username }" /> <span
							class="input-group-btn">
							<button class="btn btn-danger headColor" type="submit">
								<i class="icon-search"></i>
							</button> </span>
					</div>
				</div>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-body">
				<span id="docList">医生列表</span>
				<hr>
				<table class="bordered">
					<thead>
						<tr>
							<!-- <th hidden="hidden">id</th> -->
							<th  width="10%">登录用户名</th>
							<th hidden="hidden"></th>
						 
							<th  width="10%">医生姓名</th>
							<th  width="10%">管理地址</th>
							<th  width="5%">所属科室</th>
							<th  width="10%">职称</th>
							<th  width="10%">所属医院</th>
							<th  width="20%">备注</th>
							<th  width="13%">操作</th>
						</tr>
					</thead>
					<c:forEach items="${page.result }" var="data">
						<tr>
							<td class="1">${data.username}</td>
							<td class="2" hidden="hidden"><input type="password"
								value="${data.password}" /></td>
							 
							<td class="3">${data.doctorName }</td>
							<td class="4" width="15%">${data.responsibleArea }</td>
							<td class="5"><%-- ${data.hospitalDoctorMajor.major} --%>全科</td>
							<td class="6">${data.position}</td>
							<td class="7">${data.hospitalInfo.name}</td>
							<td class="8" hidden="hidden">${data.provinceId}-${data.cityId}-${data.districtId}-${data.phone}-${data.detailAdd
								}-${data.id}-${data.hospitalId}</td>
							<td style="word-break:break-all; " >${data.remark}</td>
							<td>
								<button type="button" class="btn update_btn btnGradient">修改</button>
							 
							</td>
						</tr>
					</c:forEach>
					<c:if
						test="${page == null || page.result == null || page.totalCount <= 0 }">
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
<!-- 添加医生弹框开始-->
<div id="add_doctor" class="modal fade bs" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" id="addPop" style="width:557px;">
		<div class="modal-content"
			style="border-top: 2px solid #FF729C !important;">
			<div class="modal-header" style="background-color:#F0F0F0">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" style="font-size: 37px;line-height: 30px;">&times;</span>
				</button>
				<h4 class="modal-title" id="gridSystemModalLabel"
					style="color:#D0D0D0">新建医生账号</h4>
			</div>

			<div class="modal-body">
				<form id="add_doctor_form">
					<input type="hidden" name="hospitalId" value="${hospital.id }" />
					<input type="hidden" name="enabled" value="1" /> <input
						type="hidden" name="responsibleArea" id="add_responsibleArea" />

					<table style="border:0px solid #000;">
						<tr>
							<td align="right">医生姓名:</td>
							<td class="input-style"><input type="text"
								datatype="/^[a-zA-Z\u4e00-\u9fa5]{1,}$/"
								errormsg="姓名只能包含英文或中文,且不能为空"
								nullmsg="请输入用户名" name="doctorName"
								class="nickname" /> <span class="Validform_checktip mleft"></span></td>
						</tr>
						<tr>
							<td align="right">登录账号:</td>
							<td class="input-style"><input type="text" name="username"
								id="addFormUsername"
								datatype="/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){5,19}$/"
								errormsg="用户名只能以字母开头，6-20位字母和数字" nullmsg="请输入账号" class="username"
								autocomplete="off" /><span class="Validform_checktip"></span></td>
						</tr>
						<tr>
							<td align="right">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
							<td class="input-style"><input type="text" name="password"
								onfocus="this.type='password'"
								datatype="/^([a-zA-Z0-9]|[*_]){6,12}$/"
								errormsg="只能输入6-12个字母、数字、下划线 " nullmsg="请输入密码 " class="password"
								autocomplete="off" /><span class="Validform_checktip"></span></td>
						</tr>
						<tr>
							<td align="right">确认密码:</td>
							<td class="input-style"><input type="text"
								onfocus="this.type='password'" datatype="*" recheck="password"
								errormsg="您两次输入的账号密码不一致" nullmsg="请输入确认密码" class="password2"
								autocomplete="off" /><span class="Validform_checktip"></span></td>
						</tr>
						<tr>
							<td align="right" class="tr-title">负责地区:</td>
							<td class="input-style">
								<!--省--> <select class="select-border" name="provinceId"
								placeholder="请选择">
									<option>请选择</option>

							</select> <!--市--> <select class="select-border" name="cityId"
								placeholder="请选择">
									<option>请选择</option>
							</select> <!--县  districts--> <select class="select-border"
								name="districtId" placeholder="请选择">
									<option>请选择</option>
							</select> <!--详细地址--> <input type="text" style="width:126px;"
								name="detailAdd" placeholder="输入更具体的地址">
							</td>
						</tr>
						<tr>
							<td align="right">所属科室:</td>
							<!-- <td class="input-style"><input type="text" name="major"  class="notEmpty"/><span class="Validform_checktip"></span></td> -->
							<td><select class="select-border" name="majorId"
								id="add_major" style="margin-left: 12px;" placeholder="请选择">
									<option value="0">全科</option>
							</select>
							</td>
						</tr>
						<tr>
							<td align="right">医生职称:</td>
							<td class="input-style">
								<!-- <input type="text" name="position"  class="notEmpty"/><span class="Validform_checktip"></span> -->
								<select class="select-border" name="position" id="add_position"
								placeholder="请选择">
									<option>请选择</option>
							</select>
							</td>
						</tr>
						<tr>
							<td align="right">联系电话:</td>
							<td class="input-style"><input type="text" name="phone"
								datatype="/^(1[0-9][0-9])\d{8}$/"
								errormsg="手机号码不正确" nullmsg="请输入手机号码" class="mobile" /><span
								class="Validform_checktip"></span></td>
						</tr>
						<tr>
							<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:</td>
							<td class="input-style"><textarea rows="" cols="" class="remark"
									name="remark" placeholder="0-100个字" style="height:80px;"
									id="licenseother" onkeypress="count()" onkeyup="count()"
									onblur="count();" onChange="count();"></textarea></td>
						</tr>
					</table>

					<div class="modal-footer"
						style="text-align:center;background-color:#ECECEC">
						<input readonly="readonly"
							class="btn btn-default btn-lg btn-cancel" data-dismiss="modal"
							value="取消" /> <input readonly="readonly"
							class="btn btn-danger btn-lg btn-ok" id="addOk" value="确定" />
					</div>

				</form>
			</div>


		</div>
	</div>
</div>
<!--弹框结束-->


<!-- 修改医生弹框开始-->
<div id="update_doctor" class="modal fade bs" tabindex="-1"
	role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" style="width:557px;">
		<div class="modal-content"
			style="border-top: 2px solid #FF729C !important;">
			<div class="modal-header" style="background-color:#F0F0F0">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true" style="font-size: 37px;line-height: 30px;">&times;</span>
				</button>
				<h4 class="modal-title" id="gridSystemModalLabel"
					style="color:#D0D0D0">更改医生账号</h4>
			</div>

			<div class="modal-body">
				<form id="update_doctor_form">
					<input type="hidden" name="hospitalId" id="hospitalId_update" /> <input
						type="hidden" name="id" id="doctorId" />
					<table style="border:0px solid #000;">
						<tr>
							<td align="right">医生姓名:</td>
							<td class="input-style"><input type="text" name="doctorName"
								datatype="/^[a-zA-Z\u4e00-\u9fa5]{1,}$/"
								errormsg="姓名只能包含英文或中文,且不能为空" nullmsg="请输入用户名" class="nickname" /><span
								class="Validform_checktip mleft"></span></td>
						</tr>
						<tr>
							<td align="right">登录账号:</td>
							<td class="input-style"><input type="text" name="username"
								datatype="/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/"
								errormsg="账号要以字母开头,且长度6-20" nullmsg="请输入账号" class="username"
								readonly="readonly" /><span class="Validform_checktip"></span>
							</td>
						</tr>
						<tr>
							<td align="right">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
							<td class="input-style"><input type="password"
								datatype="/^([a-zA-Z0-9]|[*._]){6,12}$/"
								errormsg="只能输入6-12个字母、数字、下划线 " nullmsg="请输入密码 " name="password"
								class="password" /><span class="Validform_checktip"></span></td>
						</tr>
						<tr>
							<td align="right">确认密码:</td>
							<td class="input-style"><input type="password" datatype="*"
								recheck="password" errormsg="您两次输入的账号密码不一致" nullmsg="请输入确认密码"
								class="password2" /><span class="Validform_checktip"></span></td>
						</tr>



						<tr>
							<td align="right" class="tr-title">负责地区:</td>
							<td class="input-style"><select class="select-border" name="provinceId" 
								placeholder="请选择">
							</select> <!--市--> <select class="select-border" name="cityId"
								placeholder="请选择">
									<option>请选择</option>
							</select> <select class="select-border" name="districtId"
								id="district_update" placeholder="请选择">

							</select> <input id="updateDetailAdd" name="detailAdd" type="text"
								style="width:126px;" placeholder="输入更具体的地址">
							</td>
						</tr>
						<tr>
							<td align="right">所属科室:</td>
							<td><select class="select-border" name="majorId"
								id="update_major" style="margin-left: 12px;" placeholder="请选择">
									<option value="0">全科</option>
							</select>
							</td>
						</tr>
						<tr>
							<td align="right">医生职称:</td>
							<td class="input-style">
								<!-- <input type="text" name="position" class="notEmpty"/><span class="Validform_checktip"> -->
								<select class="select-border" name="position"
								id="update_position" placeholder="请选择">
									<option>请选择</option>
							</select> </span></td>
						</tr>
						<tr>
							<td align="right">联系电话:</td>
							<td class="input-style"><input type="text"
								datatype="/^(1[0-9][0-9])\d{8}$/"
								errormsg="手机号码不正确" nullmsg="请输入手机号码" name="phone" class="mobile" /><span
								class="Validform_checktip"></span></td>
						</tr>
						<tr>
							<td align="right">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:</td>
							<td class="input-style"><textarea rows="" cols="" 
									name="remark" placeholder="0-100个字" style="height:80px;"
									id="licenseother_"
									 onkeypress="count_()" onkeyup="count_()"
									onblur="count_();" onChange="count()_;"></textarea></td>
						</tr>
					</table>

					<div class="modal-footer"
						style="text-align:center;background-color:#ECECEC">
						<input readonly="readonly"
							class="btn btn-default btn-lg btn-cancel" data-dismiss="modal"
							value="取消" /> <input readonly="readonly"
							class="btn btn-danger btn-lg btn-ok " id="updateOk" value="确定" />
					</div>

				</form>
			</div>


		</div>
	</div>
</div>
<!--弹框结束-->

<script type="text/javascript">

			function getStringUTFLength(str) {
				var value = str.replace(/[\u4e00-\u9fa5]/g, " ");
				//将汉字替换为两个空格
				return value.length;
			}

			function leftUTFString(str, len) {
				if (getStringUTFLength(str) <= len) {
					return str;
				}
				var value = str.substring(0, len);
				while (getStringUTFLength(value) > len) {
					value = value.substring(0, value.length - 1);
				}
				return value;
			}

			function count() {
				var len = 100;
				var value = document.getElementById("licenseother").value;
				if (getStringUTFLength(value) >= len) {
					document.getElementById("licenseother").value = leftUTFString(document.getElementById("licenseother").value, len);
				}
			}
		function count_() {
				var len = 100;
				var value = document.getElementById("licenseother_").value;
				if (getStringUTFLength(value) >= len) {
					document.getElementById("licenseother_").value = leftUTFString(document.getElementById("licenseother_").value, len);
				}
			}
//----------------------------------------
var data = {
		url : "",
		districtData:""
	};
 
	var add=$("#add_doctor_form").Validform({
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
	
add.tipmsg.s="  ";
 
 $("#addOk").click(function(){
 	if(add.check()){
 		FamilyDoctor.addFamilyDoctor(null);
 	}else{
 		alert("校验不通过");
 	}
 }); 
 
 
 
 /* $('#addPop').on('hide.bs.modal', function () {
 alert(0);
 }); 
})*/
	//--------------------------------------------------------------------------------------------------
		var update=$("#update_doctor_form").Validform({
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
 $("#updateOk").click(function(){
 	if(update.check()){
 		FamilyDoctor.updateFamilyDoctor(null);
 	}else{
 		alert("校验不通过");
 	}
 }); 
 
	// 添加家庭医生---------------------------------------------------------
	window.FamilyDoctor = {
			"getHospitalDoctorMajors":function(fn){
				data.url = baseURL+"/fDoctor/getHospitalDoctorMajors";
				$.ajax({
							url : data.url,
							type : "get",
							async : true,
							dataType : "json",
							success : function(res) {
								fn(res);
							}
						});
			},
		"addFamilyDoctor" : function(requestParam) {
			var provinceText = $("#add_doctor_form select").eq(0).find("option:selected").text();
			var cityText = $("#add_doctor_form select").eq(1).find("option:selected").text();
			var districtText = $("#add_doctor_form select").eq(2).find("option:selected").text();

			var responsibleArea = provinceText != cityText ? provinceText + cityText + districtText : cityText + districtText;
			$("#add_responsibleArea").val( responsibleArea);
			data.url = baseURL+"/fDoctor/addFamilyDoctor";
			$.ajax({
						url : data.url,
						data :requestParam = $("#add_doctor_form").serialize(),
						type : "post",
						dataType : "json",
						async: false,
						success : function(res) {
							if("success"==res.flag){
								alert(res.msg);
								$('#add_doctor').modal('hide');
								window.location.reload(location.href);
							}else{
								alert(res.msg);
							}
						}
					});
		},
		"delFamilyDoctor": function(id) {// window.location.reload();
			App.confirm_show("提示！", "确定要删除吗？", function() {
				$(".info-confirm-cancel").trigger("click");
						data.url =  baseURL+"/fDoctor/delFamilyDoctor";
						$.ajax({
									url : data.url,
									data : {
										id : id
									},
									type : "get",
									dataType : "json",
									async: false,
									success : function(res) {
										alert(res.msg);
										window.location.reload(location.href);
									},
									error : function() {
										alert("网络连接出错");
									}
								});
					});
		},// 获取省份对应的城市数据
		"getCitysByProId": function(id, fn) {
			//alert(baseURL);
			data.url = baseURL+"/fDoctor/getCitysByProId";
			$.ajax({
						url : data.url,
						data : {
							id : id
						},
						type : "get",
						dataType : "json",
						async : false,
						success : function(res) {
							fn(res);
						},
						error : function(res) {
							console.info(res);
							alert("网络连接出错");
						}
					});
		},// 根据城市获取对应的区县数据
		"getDistrctByCityId" : function(id, fn) {
			data.url =  baseURL+"/fDoctor/getDistrctByCityId";
			$.ajax({
						url : data.url,
						data : {
							id : id
						},
						type : "get",
						dataType : "json",
						async : false,
						success : function(res) {
							fn(res);
						},
						error : function(res) {
							 alert("网络连接出错");
						}
					});
		},//获取所有的省份数据
		"getAllProvinces" : function( fn) {
			data.url =  baseURL+"/fDoctor/getAllProvinces";
			$.ajax({
						url : data.url,
						type : "get",
						dataType : "json",
						async : false,
						success : function(res) {
							fn(res);
						},
						error : function(res) {
							console.info(res);
							alert("网络连接出错");
						}
					});
		},
		"getFamilyDoctorById" : function(id, fn) {
			data.url =  baseURL+"/fDoctor/getFamilyDoctorById";
			$.ajax({
						url : data.url,
						type : "get",
						data:{id:id},
						dataType : "json",
						async : false,
						success : function(res) {
							fn(res);
						},
						error : function(res) {
							alert("网络连接出错");
						}
					});
		},// 初始化更新表单的数据
		"initUpdateDoctor" : function(t) {
			$("#update_doctor").modal("show");
			//重置表单
			document.getElementById("update_doctor_form").reset();
			//去除上一次的校验提示信息
			$("#update_doctor_form .Validform_checktip").text("");
			//获取医生id  
			var currentTr = $(t).parent().parent();
			var hideArr = currentTr.find("td[class='8']").eq(0).html().split("-");
			var doctorId = hideArr[5];//医生id
			//根据医生id获取要修改的医生信息
			window.FamilyDoctor.getFamilyDoctorById(doctorId,function(res){
			var doctor=res.data;
				$("#hospitalId_update").val(doctor.hospitalId);
				$("#doctorId").val(doctor.id);
				$("#update_doctor_form table tr td[class='input-style'] input[name='username']").eq(0).val(doctor.username);
				$("#update_doctor_form table tr td[class='input-style'] input[name='doctorName']").eq(0).val(doctor.doctorName);
				$("#update_doctor_form table tr td[class='input-style'] input[type='password']").val("******");
				$("#update_doctor_form table tr td[class='input-style'] input[name='major']").eq(0).val(doctor.majorId);
				$("#update_doctor_form table tr td[class='input-style'] input[name='position']").eq(0).val(doctor.position);
				$("#update_doctor_form table tr td[class='input-style'] input[name='phone']").eq(0).val(doctor.phone);  
				$("#licenseother_").text(doctor.remark);
				//处理科室 后面修改为全科
			/* 	window.FamilyDoctor.getHospitalDoctorMajors(function(res){
				var html="";
				$.each(res.data,function(index,val){
					if(doctor.majorId==val.id){
						html+='<option value="'+val.id+ '" selected="selected">'+val.major+' </option>';
					}else{
						html+='<option value="'+val.id+ '" >'+val.major+' </option>';
					}
				});
					$("#update_major").html(html);
			}); */
			//处理省份数据
			FamilyDoctor.getAllProvinces(function(res){
				   var html="";
					$.each(res.data,function(index,val){
						html+='<option value="'+val.id+ '" >'+val.proName+' </option>';
					});
					$("#update_doctor_form select").eq(0).html(html);//
					//设置选择修改前的省份存数据库的数据
					$("#update_doctor_form select").eq(0).val(doctor.provinceId);
				   });
		  //处理市区数据
		  FamilyDoctor.getCitysByProId(doctor.provinceId, function(res) {
							$("#update_doctor_form select").eq(1).empty();
							$("#update_doctor_form select").eq(2).empty();
							var html = "";
							$.each(res.data, function(index, val) {
										html += '<option value="' + val.id + '" >'
												+ val.cityName + ' </option>';
									});
							$("#update_doctor_form select").eq(1).html(html);//
							// 选择下拉框  ( 存数据库的数据)
							$("#update_doctor_form select").eq(1).val(doctor.cityId);
						});
			//
			FamilyDoctor.getDistrctByCityId(doctor.cityId, function(res) {
							$("#update_doctor_form select").eq(2).empty();
							var html = "";
							$.each(res.data, function(index, val) {
										if(doctor.districtId!=val.id){
											html += '<option value="' + val.id + '" >'+ val.name + ' </option>';
										}else{
											html += '<option value="' + val.id + '" selected="selected" >'+ val.name + ' </option>';
										}
									});
							data.districtData=html;//这里在谷歌浏览器下有bug不能给该select添加选项,就存到全局变量中,等表单显示后在填充
							$("#update_doctor_form select").eq(2).html(html);//在火狐中没有问题
						});
			$("#updateDetailAdd").val(doctor.detailAdd); 
			 //获取职称
			window.FamilyDoctor.getDoctorTitles(function(res){
				var html="";
				$.each(res.data,function(index,val){
					html+='<option value="'+val+ '" >'+val+' </option>';
					$("#update_position").html(html);
				});
					$("#update_position").val(doctor.position);//选中已有的职称
			}); 
		});
			 
		},
		"updateFamilyDoctor":function(){
			// 序列化表单
			var requestParam = $("#update_doctor_form").serialize();
			// 封装json
			var vals = requestParam.split("&");
			var json_ = {};
			$.each(vals, function(index, val) {
						var text = val.split("=");
						json_[text[0]] = text[1];
			});
			 
			var provinceText = $("#update_doctor_form select").eq(0).find("option:selected").text();
			var cityText = $("#update_doctor_form select").eq(1).find("option:selected").text();
			var districtText = $("#update_doctor_form select").eq(2).find("option:selected").text();

			var responsibleArea = provinceText != cityText ? provinceText + cityText + districtText : cityText + districtText;

			delete json_['password2'];
			json_.responsibleArea = encodeURI(responsibleArea + $("#updateDetailAdd").val());
			var str = JSON.stringify(json_) + "";
			data.url =  baseURL+"/fDoctor/updateFamilyDoctor";
			 
			$.ajax({
						url : data.url,
						data : {
							param : str
						},
						type : "get",
						dataType : "json",
						success : function(res) {
							alert(res.msg);
							$('#update_doctor').modal('hide');
							window.location.reload(location.href);
						}
					});
			
		},
		"checkUsername":function(username){//同步校验用户名是否唯一
			data.url =  baseURL+"/fDoctor/checkUsername";
			$.ajax({
						url : data.url,
						data : {
							username : username
						},
						type : "get",
						async : false,
						dataType : "json",
						success : function(res) {
							alert(res.msg);
						}
					});
		},
		"getDoctorTitles":function(fn){//获取医生职称数据
			data.url =  baseURL+"/fDoctor/getDoctorTitles";
			$.ajax({
						url : data.url,
						type : "get",
						async : false,
						dataType : "json",
						success : function(res) {
							fn(res);
						}
					});
		}

	};

	//显示添加医生弹框
	$("#add_btn").click(function(){
		$("#add_doctor").modal("show");
		 //获取职称
	window.FamilyDoctor.getDoctorTitles(function(res){
			var html="";
		$.each(res.data,function(index,val){
			html+='<option value="'+val+ '" >'+val+' </option>';
			$("#add_position").html(html);
		});
	}); 	 
		// 获取科室   修改成全科
/*   	window.FamilyDoctor.getHospitalDoctorMajors(function(res){
			var html="";
		$.each(res.data,function(index,val){
			html+='<option value="'+val.id+ '" >'+val.major+' </option>';
			$("#add_major").html(html);
		});
	});  */
	   //请求所有的省份数据
	   window.FamilyDoctor.getAllProvinces(function(res){
	   var html="";
		$.each(res.data,function(index,val){
			html+='<option value="'+val.id+ '" >'+val.proName+' </option>';
		});
		$("#add_doctor_form select").eq(0).html(html);//
		$("#add_doctor_form select").eq(0).val('${provice.id}');
	   });
	   
	  // 获取当前医院所在省份的城市数据
	   FamilyDoctor.getCitysByProId('${provice.id}', function(res){
	   	 var html="";
		$.each(res.data,function(index,val){
			html+='<option value="'+val.id+ '" >'+val.cityName+' </option>';
		});
		$("#add_doctor_form select").eq(1).html(html);//
		$("#add_doctor_form select").eq(1).val(${city.id});
	   }) ;
	   
	    FamilyDoctor.getDistrctByCityId(${city.id}, function(res){
	    var html="";
		$.each(res.data,function(index,val){
			html+='<option value="'+val.id+ '" >'+val.name+' </option>';
		});
		$("#add_doctor_form select").eq(2).html(html);//
	    });
	    
	});
	
	$(".update_btn").click(function(){
		FamilyDoctor.initUpdateDoctor(this);
	});
	
	
	


	// 当省份发生改变
	$("#add_doctor_form select").eq(0).change(function(){
		  FamilyDoctor.getCitysByProId($("#add_doctor_form select").eq(0).val(),function(res){
		  					$("#add_doctor_form select").eq(1).empty();
							$("#add_doctor_form select").eq(2).empty();
							var html="";
							$.each(res.data,function(index,val){
								html+='<option value="'+val.id+ '" >'+val.cityName+' </option>';
							});
							$("#add_doctor_form select").eq(1).html(html);//
							
				   FamilyDoctor.getDistrctByCityId($("#add_doctor_form select").eq(1).val(),function(res){
				  			$("#add_doctor_form select").eq(2).empty();
									var html="";
									$.each(res.data,function(index,val){
										html+='<option value="'+val.id+ '" >'+val.name+' </option>';
									});
									$("#add_doctor_form select").eq(2).html(html);//
				  			});	
							
		 			 });
	});
// 添加医生表单的市区数据改变触发
	$("#add_doctor_form select").eq(1).change(function(){
		  FamilyDoctor.getDistrctByCityId($("#add_doctor_form select").eq(1).val(),function(res){
		  			$("#add_doctor_form select").eq(2).empty();
							var html="";
							$.each(res.data,function(index,val){
								html+='<option value="'+val.id+ '" >'+val.name+' </option>';
							});
							$("#add_doctor_form select").eq(2).html(html);//
		  });
	});//

	//-------------------------------------------------------------------------------------------------------

		// 当修改家庭医生的省数据的时候触发
	$("#update_doctor_form select").eq(0).change(function(){
		FamilyDoctor.getCitysByProId($("#update_doctor_form select").eq(0).val(), function(res) {
							$("#update_doctor_form select").eq(1).empty();
							$("#update_doctor_form select").eq(2).empty();
							var html = "";
							$.each(res.data, function(index, val) {
										html += '<option value="' + val.id + '" >'
												+ val.cityName + ' </option>';
									});
							$("#update_doctor_form select").eq(1).html(html);//
							
										 
						// 如果是直辖市就应该把区县更新
					FamilyDoctor.getDistrctByCityId($("#update_doctor_form select").eq(1).val(), function(res) {
							//$("#update_doctor_form select").eq(2).empty();
							var html = "";
							$.each(res.data, function(index, val) {
										html += '<option value="' + val.id + '" >'
												+ val.name + ' </option>';
									});
							$("#update_doctor_form select").eq(2).html(html);//
							});
						});
	});//
		// 当修改家庭医生的市数据的时候触发
	$("#update_doctor_form select").eq(1).change(function(){
		FamilyDoctor.getDistrctByCityId($("#update_doctor_form select").eq(1).val(), function(res) {
							$("#update_doctor_form select").eq(2).empty();
							var html = "";
							$.each(res.data, function(index, val) {
										html += '<option value="' + val.id + '" >'
												+ val.name + ' </option>';
									});
							$("#update_doctor_form select").eq(2).html(html);//
						});
		   
	});
	//这里是单独处理区县下拉框的值在谷歌中不能正常显示
	$('#update_doctor').on('shown.bs.modal', function () {
	   $("#update_doctor_form select").eq(2).html(data.districtData);//
	}); 
	$('#update_doctor').on('hidden.bs.modal', function () {
	   window.location.reload(location.href);
	}); 
	$('#add_doctor').on('hidden.bs.modal', function () {
	   window.location.reload(location.href);
	}); 




</script>

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>


