$(function() {

	$.fn.serializeJson = function() {

		// 序列化表单数据
		var jsonData1 = {};
		var serializeArray = this.serializeArray();
		$(serializeArray).each(
				function() {
					if (jsonData1[this.name]) {
						if ($.isArray(jsonData1[this.name])) {
							jsonData1[this.name].push(this.value);
						} else {
							jsonData1[this.name] = [ jsonData1[this.name],
									this.value ];
						}
					} else {
						jsonData1[this.name] = this.value;
					}
				});
		var vCount = 0;
		for ( var item in jsonData1) {
			var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
			vCount = (tmp > vCount) ? tmp : vCount;
		}

		if (vCount > 1) {
			var jsonData2 = new Array();
			for ( var i = 0; i < vCount; i++) {
				var jsonObj = {};
				for ( var item in jsonData1) {
					jsonObj[item] = jsonData1[item][i];
				}
				jsonData2.push(jsonObj);
			}
			return JSON.stringify(jsonData2);
		} else {
			return "[" + JSON.stringify(jsonData1) + "]";
		}
	};

	// 表格加行
	$('.add-p')
			.bind(
					"click",
					function() {
						var archiveId = $("#familyArchiveBaseId").val();
							var tr = '<tr><td><input type="hidden" name="archiveId" value="'
									+ archiveId
									+ '"/><input type="hidden" name="id" value=""/><input type="text" name="personName" value=""/></td>'
									+ '<td><div class="basic-block-cen"><div class="input-group add-au" class="xixi"><select class="form-control selectpicker" name="sex" title="请选择"><option value="-1">请选择</option><option value="0">男</option><option value="1">女</option><option value="2">其他</option></select></div></div></td><td><input type="text" name="idNo" value=""/></td><td><input type="text" class="time-inp"  onclick="laydate({max:laydate.now()})"  readonly="readonly" name="birthday" value=""  placeholder="选择日期" id="d1"></td><td><div class="basic-block-cen"><div class="input-group add-au" class="xixi"><select class="form-control selectpicker" title="请选择" name="houseRelation"><option value="0">请选择</option><option value="1">本人</option><option value="2">夫妻</option><option value="3">父子</option><option value="4">母子</option><option value="5">其他</option></select></div></div></td><td><div class="basic-block-cen"><div class="input-group add-au" class="xixi"><select class="form-control selectpicker" title="请选择" name="isMedicare"><option value="-1">请选择</option><option value="1">是</option><option value="0">否</option></select></div></div></td><td><input type="text" name="contactWay" value=""/></td></tr>';
							$('#familyMemberTab').after(tr);
					});

	// 获取用户正在输入的身份证号码赋值出生日期
	$("input[name='idNo']").each(
			function(index) {
				$(this).bind(
						"input propertychange",
						function() {
							var idNo = $(this).val();
							if (idNo.length == 18) {
								// 为出生日期赋值
								var birthday = idNo.substring(6, 10) + "-"
										+ idNo.substring(10, 12) + "-"
										+ idNo.substring(12, 14);
								$("input[name='birthday']:eq(" + index + ")")
										.val(birthday);

							} else {
								$("input[name='birthday']:eq(" + index + ")")
										.val("");
							}
						});
			});

	// 修改档案
	$("#updateArchiveBaseInfoBtn").bind("click", function() {
		// 修改档案-家族成员
		var archiveId = $("#archiveId").val();
		var archiveBaseId = $("#archiveBaseId").val();
		var flag = checkFamilyForm();
		if (flag) {
			var jsonStr = $("#updateFamilyMemberForm").serializeJson();
			$.ajax({
				url : "updateFamilyMemberInfo",
				data : jsonStr,
				dataType : "json",
				type : "post",
				contentType : "application/json",
				success : function(data) {
					if (data == "Y") {
						var flag = checkForm();
						if (flag) {
							$("#updatePregnantArchiveBaseInfoForm").submit();
						}
					} else {
						alert("修改失败，请重试！");
					}
				}
			});
		}
	});

	/* 所有回显省市级联的方法 */
	var countryId = $("#country").val();
	var nowProvinceHtml="";
	var nowCityHtml="";
	var nowStayProvinceId="";
	var nowStayCityId="";
	$("select[name*='rovince']").each(
			function() {
				nowProvinceHtml = $(this);
				nowCityHtml = $(this).next();
				nowStayProvinceId = $(this).val();
				nowStayCityId = $(this).next().val();
				if (null != countryId && countryId != "0") {
					// 与后台交互获取该国家对应的省份
					ajaxLiandong("getProvincesByCountry", nowProvinceHtml,
							countryId, "province",
							'<option value="0">请选择省份 </option>',
							nowStayProvinceId);
					if (nowStayProvinceId != "0" || null != nowStayProvinceId) {
						// 与后台交互获取省份对应的城市
						ajaxLiandong("getCitysByProvince", nowCityHtml,
								nowStayProvinceId, "city",
								'<option value="0">请选择城市 </option>',
								nowStayCityId);
					}
				}
			});

	/* 省-市联动查询 籍贯 */
	$("#nativePlaceProvince").unbind("change");
	$("#nativePlaceProvince").bind(
			"change",
			function() {
				// 先清空
				$("#nativePlaceCity").html("");
				var nativePlaceProvinceId = $("#nativePlaceProvince").val();
				var nativePlaceCity = $("#nativePlaceCity");
				var nowStayCityId = $("#nativePlaceCity").val();
				// 与后台交互获取省份对应的城市
				ajaxLiandong("getCitysByProvince", nativePlaceCity,
						nativePlaceProvinceId, "city",
						'<option value="0">请选择城市 </option>', nowStayCityId);
			});

	/* 省-市联动查询 户籍 */
	$("#domicoleProvince").unbind("change");
	$("#domicoleProvince").bind(
			"change",
			function() {
				// 先清空
				$("#domicoleCity").html("");
				var nativePlaceProvinceId = $("#domicoleProvince").val();
				var nativePlaceCity = $("#domicoleCity");
				var nowStayCityId = $("#domicoleCity").val();
				// 与后台交互获取省份对应的城市
				ajaxLiandong("getCitysByProvince", nativePlaceCity,
						nativePlaceProvinceId, "city",
						'<option value="0">请选择城市 </option>', nowStayCityId);
			});

	/* 省-市联动查询 现居住地 */
	$("#nowStayProvince").unbind("change");
	$("#nowStayProvince").bind(
			"change",
			function() {
				// 先清空
				$("#nowStayCity").html("");
				var nativePlaceProvinceId = $("#nowStayProvince").val();
				var nativePlaceCity = $("#nowStayCity");
				var nowStayCityId = $("#nowStayCity").val();
				// 与后台交互获取省份对应的城市
				ajaxLiandong("getCitysByProvince", nativePlaceCity,
						nativePlaceProvinceId, "city",
						'<option value="0">请选择城市 </option>', nowStayCityId);
			});
	/* 省-市联动查询 现居住地 管辖派出所 */
	$("#nowPoliceStationProvince").unbind("change");
	$("#nowPoliceStationProvince").bind(
			"change",
			function() {
				// 先清空
				$("#nowPoliceStationCity").html("");
				var nativePlaceProvinceId = $("#nowPoliceStationProvince")
						.val();
				var nativePlaceCity = $("#nowPoliceStationCity");
				var nowStayCityId = $("#nowPoliceStationCity").val();
				// 与后台交互获取省份对应的城市
				ajaxLiandong("getCitysByProvince", nativePlaceCity,
						nativePlaceProvinceId, "city",
						'<option value="0">请选择城市 </option>', nowStayCityId);
			});

	// 丈夫
	/* 省-市联动查询 籍贯 */
	$("#husbandProvince").unbind("change");
	$("#husbandProvince").bind(
			"change",
			function() {
				// 先清空
				$("#husbandCity").html("");
				var nativePlaceProvinceId = $("#husbandProvince").val();
				var nativePlaceCity = $("#husbandCity");
				var nowStayCityId = $("#husbandCity").val();
				// 与后台交互获取省份对应的城市
				ajaxLiandong("getCitysByProvince", nativePlaceCity,
						nativePlaceProvinceId, "city",
						'<option value="0">请选择城市 </option>', nowStayCityId);
			});

	/* 省-市联动查询 户籍 */
	$("#husbandDomicoleProvince").unbind("change");
	$("#husbandDomicoleProvince")
			.bind(
					"change",
					function() {
						// 先清空
						$("#husbandDomicoleCity").html("");
						var nativePlaceProvinceId = $(
								"#husbandDomicoleProvince").val();
						var nativePlaceCity = $("#husbandDomicoleCity");
						var nowStayCityId = $("#husbandDomicoleCity").val();
						// 与后台交互获取省份对应的城市
						ajaxLiandong("getCitysByProvince", nativePlaceCity,
								nativePlaceProvinceId, "city",
								'<option value="0">请选择城市 </option>',
								nowStayCityId);
					});

	/* 省-市联动查询 现居住地 */
	$("#husbandNowStayProvince").unbind("change");
	$("#husbandNowStayProvince").bind(
			"change",
			function() {
				// 先清空
				$("#husbandNowStayCity").html("");
				var nativePlaceProvinceId = $("#husbandNowStayProvince").val();
				var nativePlaceCity = $("#husbandNowStayCity");
				var nowStayCityId = $("#husbandNowStayCity").val();
				// 与后台交互获取省份对应的城市
				ajaxLiandong("getCitysByProvince", nativePlaceCity,
						nativePlaceProvinceId, "city",
						'<option value="0">请选择城市 </option>', nowStayCityId);
			});

});

// 二级联动查询 与后台交互function
function ajaxLiandong(url, htmlName, param, arrValue, text, entityId) {
	// 与后台交互
	$.ajax({
		type : 'POST',
		dataType : 'text',
		data : 'id=' + param,
		url : "" + url,
		success : function(rs) {
			if (rs != '') {
				var content = text;
				var results = eval('(' + rs + ')');
				if (arrValue == "province") {// 国家-省 二级联动
					for ( var i = 0; i < results.length; i++) {
						if (results[i].id == entityId) {
							content += '<option value="' + results[i].id
									+ '" selected="selected">'
									+ results[i].proName + '</option>';
						} else {
							content += '<option value="' + results[i].id + '">'
									+ results[i].proName + '</option>';
						}
					}
				}
				if (arrValue == "city") {// 省-市 二级联动
					for ( var i = 0; i < results.length; i++) {
						if (results[i].id == entityId) {
							content += '<option value="' + results[i].id
									+ '" selected="selected">'
									+ results[i].cityName + '</option>';
						} else {
							content += '<option value="' + results[i].id + '">'
									+ results[i].cityName + '</option>';
						}
					}
				}
				if (arrValue == "district") {// 市-地区 二级联动
					for ( var i = 0; i < results.length; i++) {
						if (results[i].id == entityId) {
							content += '<option value="' + results[i].id
									+ '" selected="selected">'
									+ results[i].name + '</option>';
						} else {
							content += '<option value="' + results[i].id + '">'
									+ results[i].name + '</option>';
						}
					}
				}
				htmlName.html(content);
			}
		},
		error : function(rs) {
			alert(rs);
		}
	});
}

// 根据生日计算年龄
function calculateAge(obj) {
	var dateText = "";
	if (obj == 1) {
		dateText = $("#birthday").val();
	} else {
		dateText = $("#husbandBirthday").val();
	}
	if (dateText != null && dateText != "") {
		// 根据生日计算年龄
		$.ajax({
			url : "calculateAgeByBirthday",
			data : {
				birthday : dateText
			},
			type : "post",
			success : function(data) {
				if (obj == 1) {
					$("#age").val(data);
				} else {
					$("#husbandAge").val(data);
				}
			}
		});
	} else {
		alert("请选择出生日期！");
		if (obj == 1) {
			$("#age").val("");
		} else {
			$("#husbandAge").val("");
		}
	}
}

// 根据末次月经计算预产期
function getPregnantDateBylmp() {
	var lmp = $("#lmp").val();
	if (lmp != null && lmp != "") {
		$.ajax({
			url : "getPregnantDateBylmp",
			data : {
				lmp : lmp
			},
			type : "post",
			success : function(data) {
				$("#pregnantDate").val(data);
			}
		});
	} else {
		alert("请选择末次月经！");
		$("#pregnantDate").val("");
	}
}

// 表单验证
function checkForm() {
	var name = $("#name").val();
	var mobile = $("#mobile").val();
	var idNo = $("#idNo").val();
	var birthday = $("#birthday").val();
	var lmp = $("#lmp").val();
	var flag = true;
	if (name == null || name == "") {
		$("#name").tips({
			msg : "请输入您的名字"
		});
		flag = false;
	} else if (name.length > 10) {
		$("#name").tips({
			msg : "名字长度不能超过10位！"
		});
		flag = false;
	}
	if (mobile == null || mobile == "") {
		$("#mobile").tips({
			msg : "手机号码不能为空"
		});
		fla = false;
	} else if (!isPhoneNum(mobile)) {
		$("#mobile").tips({
			msg : "请输入合法的手机号码！"
		});
		fla = false;
	}
	if (idNo == null || idNo == "") {
		$("#idNo").tips({
			msg : "身份证号码不能为空！"
		});
		flag = false;
	} else if (!isCardNum(idNo)) {
		$("#idNo").tips({
			msg : "请输入合法的身份证号码！"
		});
		flag = false;
	}
	if (birthday == null || birthday == "") {
		$("#birthday").tips({
			msg : "请选择你的出生日期！"
		});
		flag = false;
	}
	if (lmp == null || lmp == "") {
		$("#lmp").tips({
			msg : "请选择你的末次月经日期！"
		});
		flag = false;
	}
	return flag;
}

// 表单验证
function checkFamilyForm() {
	var flag = true;
	$("input[name='personName']").each(function() {
		/*
		 * if ($(this).val() == "" || null == $(this).val()) { $(this).tips({
		 * msg : "请输入您的名字" }); flag = false; return; }
		 */
	});
	$("input[name='idNo']").each(
			function() {
				/*
				 * if ($(this).val() == "" || null == $(this).val()) {
				 * $(this).tips({ msg : "请输入您的身份证号码" }); flag = false; return; }
				 * else
				 */if ($(this).val() != null && $(this).val() != ""
						&& !isCardNum($(this).val())) {
					flag = false;
					$(this).tips({
						msg : "请输入合法的身份证号码"
					});
					return;
				}
			});
	$("input[name='birthday']").each(function() {
		/*if ($(this).val() == "" || null == $(this).val()) {
			flag = false;
			$(this).tips({
				msg : "请输入您的生日"
			});
			return;
		}*/
	});
	$("input[name='contactWay']").each(
			function() {
				/*if ($(this).val() == "" || null == $(this).val()) {
					flag = false;
					$(this).tips({
						msg : "请输入您的联系方式"
					});
					return;
				} else */if ($(this).val() != null && $(this).val() != ""
						&& !isPhoneNum($(this).val())) {
					flag = false;
					$(this).tips({
						msg : "请输入合法的手机号码"
					});
					return;
				}
			});
	return flag;
}

// 验证手机号
function isPhoneNum(phone) {
	var pattern = /^1[34578]\d{9}$/;
	return pattern.test(phone);
}

//验证身份证 
function isCardNum(card) {
	var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	return pattern.test(card);
}
