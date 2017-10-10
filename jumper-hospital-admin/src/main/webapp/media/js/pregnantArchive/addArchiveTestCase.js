$(function(){
	loadTestCaseDiagnoseModelName();
	//页面初始化时，禁用所有单选按钮后的文本输入框
	$(":radio:checked").each(function(){
		//文本输入框
		$(this).parent().parent().find("input[type='text']").attr("disabled",true);
	});
	
	//单选按钮切换的时候启用文本输入框
	$(":radio").each(function(){
		$(this).bind("change",function(){
			if($(this).val()==0){
				$(this).parent().parent().find("input[type='text']").attr("disabled",true);
				$(this).parent().parent().find("input[type='text']").val("");
			}else{
				$(this).parent().parent().find("input[type='text']").attr("disabled",false);
			}
		});
	});
	
	//保存初检信息
	$("#saveArchiveTestCaseBtn").bind("click",function(){
		mergerData();
		$("#archiveTestCaseForm").submit();
	});
	
	//保存模板信息
	$("#saveTestCaseDiagnoseBtn").bind("click",function(){
		var diagnoseModelName=$("#diagnoseModelName").val();
		var diagnoseModelContent=$("#diagnoseModelContentNew").val();
		var operationType=$("#operationType").val();
		var updateTestCaseId=$("#updateTestCaseId").val();
		$.ajax({
			url:"saveTestCaseDiagnose",
			data:{diagnoseModelName:diagnoseModelName,diagnoseModelContent:diagnoseModelContent,operationType:operationType,updateTestCaseId:updateTestCaseId},
			type:"post",
			success:function(data){
				if(operationType=="insert"){
					if(data>0){
						//将模板信息写入页面的模板选择项中
						$("#myModal2").modal("hide");
						loadTestCaseDiagnose();
						//将新增的模板，添加到下拉框中
						loadTestCaseDiagnoseModelName();
					}else{
						alert("新增模板失败！");
					}
				}else{
					$("#myModal2").modal("hide");
					if(data>0){
						loadTestCaseDiagnose();
						loadTestCaseDiagnoseModelName();
					}else{
						alert("修改模板失败！");
					}
				}
			}
		});
	});
	
	
	//当点击管理模板的时候，加载弹窗的数据
	$("#manageModel").bind("click",function(){
		loadTestCaseDiagnose();
	});
	
	//当在选择模板的时候更新模板对应的内容
	$("#testCaseDiagnoseId").bind("change",function(){
		var id=$("#testCaseDiagnoseId").val();
		$.ajax({
			url:"queryTescaseDiagnoseById",
			data:{id:id},
			type:"post",
			success:function(data){
				$("#diagnoseModelContent").val(data.diagnoseModelContent);
			}
		});
	});
	
	//当点击新增模板的时候，清空文本框的值
	$("#addTestCaseDiagnoseBtn").bind("click",function(){
		$("#diagnoseModelName").val("");
		$("#diagnoseModelContentNew").val("");
		$("#operationType").val("insert");
		$("#updateTestCaseId").val("");
	});
});

//修改诊断模板
function updateTestCaseDiagnoseById(id){
	$.ajax({
		url:"queryTescaseDiagnoseById",
		data:{id:id},
		type:"post",
		success:function(data){
			$("#diagnoseModelName").val(data.diagnoseModelName);
			$("#diagnoseModelContentNew").val(data.diagnoseModelContent);
			$("#operationType").val("update");
			$("#updateTestCaseId").val(id);
		}
	});
}

//删除诊断模板
function deleteTestCaseDiagnoseById(id){
	$.ajax({
		url:"deleteTestCaseDiagnoseById",
		data:{id:id},
		type:"post",
		success:function(data){
			if(data>0){
				loadTestCaseDiagnose();
				loadTestCaseDiagnoseModelName();
			}else{
				alert("删除失败！");
			}
		}
	});
}
//当新增，删除，修改，成功后，重新加载数据
function loadTestCaseDiagnose(){
	$.ajax({
		url:"queryAllTescaseDiagnose",
		data:{},
		type:"post",
		success:function(data){
			var html="";
			if(data!=null){
				for(var i=0;i<data.length;i++){
                	html+='<tr height="80px">'+
                        '<td >'+data[i].id +'</td>'+
                        '<td >'+data[i].diagnoseModelName+'</td>'+
                        '<td title='+data[i].diagnoseModelContent +'>'+data[i].diagnoseModelContent.substring(0,10) +'</td>'+
                        '<td ><a href="#" data-toggle="modal" data-target="#myModal2" onclick="updateTestCaseDiagnoseById('+data[i].id+')">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" onclick="deleteTestCaseDiagnoseById('+data[i].id+')">删除</a></td>'+
                        '</tr>';
				}
			}else{
				html+="<tr><td colspan='4'>暂无模板！</td></tr>";
			}
			$("#testCaseDiagnoseModel").html(html);
		}
	});
}

//加载诊断模板下拉框选项
function loadTestCaseDiagnoseModelName(){
	$.ajax({
		url:"queryAllTescaseDiagnose",
		data:{},
		type:"post",
		success:function(data){
			var html="<option value='0'>选择模板</option>";
			if(data!=null){
				for(var i=0;i<data.length;i++){
	            	html+="<option value="+data[i].id+">"+data[i].diagnoseModelName+"</option>";
				}
			}
			$("#testCaseDiagnoseId").html(html);
		}
	});
}
	
function mergerData(){
	//必查项目
	var willCheckProject="";
	$("#willCheckProjectTable tr td label").each(function(){
		if($(this).find("input[type='checkbox']").is(":checked")){
			willCheckProject+=$(this).find("input[type='checkbox']:checked").val()+",";
		}
	});
	if(willCheckProject.length>0){
		willCheckProject=willCheckProject.substring(0, willCheckProject.length);
	}
	$("#willCheckProject").val(willCheckProject);
	
	//备查项目
	var referenceProject="";
	$("#referenceProjectTable tr td label").each(function(){
		if($(this).find("input[type='checkbox']").is(":checked")){
			referenceProject+=$(this).find("input[type='checkbox']:checked").val()+",";
		}
	});
	if(referenceProject.length>0){
		referenceProject=referenceProject.substring(0, referenceProject.length-1);
	}
	$("#referenceProject").val(referenceProject);
}
