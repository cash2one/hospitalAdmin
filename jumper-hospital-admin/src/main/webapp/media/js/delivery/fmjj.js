$(function(){
	//输入框的长度
	$("input[type='text']").attr("maxlength","100");
	//表单校验
	try {
		var validform =$("#submitForm").Validform({
			tiptype : function(msg, o, cssctl) {
				if (o.type == 3) {
					$(o.obj).val("");
					$(o.obj).tips({"msg":msg});
				}
			}
		});
	} catch (e) {
		alert(e);
	}	
	
	//提交表单
	$("#submit").click(function() {
		if (!(validform.check(false))) {
				return ;
		    }; 
			$.ajax({
				type : "post",
				url : "savaDeliveryOutcome",
				data : $("#submitForm").serialize(),
				success : function(res) {
					  alert(res.msg);
					  var pathUrl = getRootPath();
					  if(res.flag == '1'){
						  window.location.href=pathUrl+"/PregnantEnd/lookIndex?userId="+$("#userId").val()+"&archiveId="+$("#archiveId").val()+"&archiveBaseId="+$("#archiveBaseId").val();
					  }
				}
			});
	});	
	
	//取消
	$("#cancel").click(function(){
		history.go(-1);  
	});
	
});

//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}


/*******************省市联动***********************/
$("#provinceId").change(function() {
	var proId = $("#provinceId option:selected").val();
	var option;
	$.ajax({
		type : "post",
		url : "getCity",
		data : {
			proId : proId
		},
		success : function(res) {
			if (res.flag == 1) {
				var results = res.ciList;
				$("#cityId").empty();
				for ( var i = 0; i < results.length; i++) {
					option = "<option value='" + results[i].id + "'>"
							+ results[i].cityName
							+ "</option>";
					$("#cityId").append(option);
				}
			}
		}
	});
});

