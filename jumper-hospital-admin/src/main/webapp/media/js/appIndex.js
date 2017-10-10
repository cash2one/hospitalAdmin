$(function(){
	//加载省份列表
	$.ajax({
     	type: "POST",
     	url: path+"/appIndex/findProvince",
	    data: {},
	    success: function(json) {
	    	var province = "<option value='0'>--请选择省份--</option>";
	    	var data = json.result;
	    	for(var i=0; i<data.length; i++) {
	    		province += "<option value='"+data[i].id+"'>"+data[i].proName+"</option>";
	    	}
	    	$("#selectProvince").html(province);
	    	$("#selectCity").html("<option value='0'>--请选择城市--</option>");
	    	$("#selectHospitalInfo").html("<option value='0'>--请选择医院--</option>");
	    }
	});
	$("#add").on("click", function() {
		$("#labelTitle").html("添加");
		$("#errorMessage").attr("style", "display: none");
		$(".myModel").trigger("click");
		$("#id").val("");
		$("#title").val("");
		$("#description").val("");
		$("#url").val("");
		//显示位置
		$("#showPosition option[value='1']").attr("selected", "selected");
		//是否显示
		$("#isShow option[value='0']").attr("selected", "selected");
		//选中省份
		$("#selectProvince option[value='0']").attr("selected", "selected");
		//选中城市
		$("#selectCity option[value='0']").attr("selected", "selected");
		//选中医院
		$("#selectHospitalInfo option[value='0']").attr("selected", "selected");
	});
	$("#show_mode").on("change", function() {
		var showValue = $("#show_mode").val();
		//按行显示
		if(showValue == 1) {
			$("#lineShow").show();
			$("#columnShow").hide();
		} else {
			$("#lineShow").hide();
			$("#columnShow").show();
		}
	});
	//添加
	$("#push").on("click", function() {
		var id = $("#id").val();
		var title = $("#title").val();
		var description = $("#description").val();
		var url = $("#url").val();
		var showPosition = $("#showPosition").val();
		var isShow = $("#isShow").val();
		//医院ID
		var hospitaId = $("#selectHospitalInfo option:selected").val();
		if(title == "") {
			$("#errorMessage").attr("style", "display: block");
			$(".errorMessage").text("标题内容不能为空，请输入内容！");
			return;
		}
		if(hospitaId == 0) {
			$("#errorMessage").attr("style", "display: block");
			$(".errorMessage").text("请选择医院！");
			return;
		}
		$(".myModel").trigger("click");
		$.ajax({
	     	type: "POST",
	     	url: path+"/appIndex/saveAppIndex",
		    data: {"id":id, "title":title, "description":description, "url":url, "showPosition":showPosition, "isShow":isShow, "hospitaId":hospitaId},
		    success: function(data) {
		    	if(data.result == "Y") {
		    		App.dialog_show("提示", data.msg, function(){
						$(".info-dialog-close").trigger("click");
						window.location.reload();
					});
		    	} else if(data.result == "C") {
		    		App.dialog_show("提示", data.msg, function(){
						$(".info-dialog-close").trigger("click");
					});
		    	} else {
		    		App.dialog_show("提示", data.msg, function(){
						$(".info-dialog-close").trigger("click");
					});
		    	}
		    }
		});
	});
});
//查询对应省份下所有城市
function city() {
	//获取省份ID
	var proId = $("#selectProvince option:selected").val();
	//清空
	$("#selectCity").empty();
	if(proId == 0) {
		$("#selectCity").html("<option value='0'>--请选择城市--</option>");
    	$("#selectHospitalInfo").html("<option value='0'>--请选择医院--</option>");
    	return;
	}
	//获取城市
	$.ajax({
     	type: "POST",
     	url: path+"/appIndex/findCity",
	    data: {"proId":proId},
	    async:false,
	    success: function(json) {
	    	var city = "";
	    	var data = json.result;
	    	for(var i=0; i<data.length; i++) {
	    		city += "<option value='"+data[i].id+"'>"+data[i].cityName+"</option>";
	    	}
	    	$("#selectCity").html(city);
	    	//加载医院数据
	    	hospitalInfo();
	    }
	});
}
//查询对应城市下所有医院
function hospitalInfo() {
	//获取省份ID
	var proId = $("#selectProvince option:selected").val();
	//获取城市ID
	var cityId = $("#selectCity option:selected").val();
	//清空
	$("#selectHospitalInfo").empty();
	//获取医院
	$.ajax({
     	type: "POST",
     	url: path+"/appIndex/findHospitalInfo",
	    data: {"proId":proId, "cityId":cityId},
	    async:false,
	    success: function(json) {
	    	var city = "";
	    	var data = json.result;
	    	for(var i=0; i<data.length; i++) {
	    		city += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	    	}
	    	$("#selectHospitalInfo").html(city);
	    }
	});
}
//删除
function del(id) {
	App.confirm_show("提示！", "确定要删除数据吗？", function(){
		$(".info-confirm-cancel").trigger("click");
		$.ajax({
	     	type: "POST",
	     	url: path+"/appIndex/del",
		    data: {"id":id},
		    success: function(data) {
		    	if(data.result == "Y") {
		    		App.dialog_show("提示", "删除成功！", function(){
						$(".info-dialog-close").trigger("click");
						window.location.reload();
					});
		    	} else {
		    		App.dialog_show("提示", "删除失败！", function(){
						$(".info-dialog-close").trigger("click");
					});
		    	}
		    }
		});
	});
}
//编辑
function edit(id) {
	$("#labelTitle").html("编辑");
	$("#errorMessage").attr("style", "display: none");
	$(".myModel").trigger("click");
	$.ajax({
	     	type: "POST",
	     	url: path+"/appIndex/findAppIndexById",
		    data: {"id":id},
		    success: function(data) {
		    	var appIndex = data.result;
		    	$("#id").val(appIndex.id);
		    	$("#title").val(appIndex.title);
				$("#description").val(appIndex.description);
				$("#url").val(appIndex.url);
				//显示位置
				$("#showPosition option[value='"+appIndex.showPosition+"']").attr("selected", "selected");
				//是否显示
				$("#isShow option[value='"+appIndex.isShow+"']").attr("selected", "selected");
				//选中省份
				$("#selectProvince option[value='"+data.proId+"']").attr("selected", "selected");
				city();
				//选中城市
				$("#selectCity option[value='"+data.cityId+"']").attr("selected", "selected");
				hospitalInfo();
				//选中医院
				$("#selectHospitalInfo option[value='"+appIndex.hospitaId+"']").attr("selected", "selected");
		    }
		});
}
	
function numSort(id) {
	$("#errorSortMessage").attr("style", "display: none");
	$(".myNumModel").trigger("click");
}