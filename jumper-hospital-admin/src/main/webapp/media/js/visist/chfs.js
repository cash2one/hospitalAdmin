/*********************************产后访视js********************************/
$("input[type='text']").attr("maxlength","50");
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

$(function(){
	
	//孕产期异常情况
	$(":radio[name='healthStatus']").click(function(){
		if($(this).val()=="1"){
			$("input[name='illness']").each(function(){$(this).attr("disabled",true)});
			$("input[name='illness']").each(function(){$(this).attr("checked",false)});
		}else{
			$("input[name='illness']").each(function(){$(this).removeAttr("disabled")});
		}
	})
	
	loadPostnatalModel();

	//保存操作
	$("#submit").click(function(){
		if (!(validform.check(false))) {
			return ;
	    }; 
	    $.ajax({
			type : "post",
			url : "savaPostVisitReocrd",
			data : $("#submitForm").serialize(),
			success : function(res) {
//				  alert(res.msg);
				  var pathUrl = getRootPath();
				  if(res.flag == '1'){
					  var recordId = res.data;
//					  alert(recordId);
					  //跳转到展示页面
					  window.location.href=pathUrl+"/postnatal/vistRecord?userId="+$("#userId").val()+"&visitType="+$("#visitType").val()+"&recordId="+recordId+"&archiveId="+$("#archiveId").val()+"&archiveBaseId="+$("#archiveBaseId").val();
				  }
			}
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
	
	
	//当点击管理模板的时候，加载弹窗的数据
	$("#manageModel").bind("click",function(){
		initPostnatalModel();
	});
	
	//当点击新增模板的时候，清空文本框的值
	$("#addGuidanceModel").bind("click",function(){
		$("#ModelName").val("");
		$("#ModelContentNew").val("");
		$("#operationType").val("insert");
		$("#updateTestCaseId").val("");
	});
	
	//保存模板信息
	$("#saveTestCaseDiagnoseBtn").bind("click",function(){
		var modelName=$("#ModelName").val();
		var modelContent=$("#ModelContentNew").val();
		var operationType=$("#operationType").val();
		var updateTestCaseId=$("#updateTestCaseId").val();
		var visitType=$("#visitType").val(); //随访模板类型
		$.ajax({
			url:"savePostnatalVisit",
			data:{"modelName":modelName,"modelContent":modelContent,"operationType":operationType,"modelId":updateTestCaseId,"visitType":visitType},
			type:"post",
			success:function(data){
				if(operationType=="insert"){
					if(data){
						//将模板信息写入页面的模板选择项中
						$("#myModal2").modal("hide");
						initPostnatalModel();
						//将新增的模板,拼接到类型后面
						loadPostnatalModel();
						window.location.reload();
					}else{
						alert("新增模板失败！");
					}
				}else{
					$("#myModal2").modal("hide");
					if(data){
						initPostnatalModel();
						loadPostnatalModel();
					}else{
						alert("修改模板失败！");
					}
				}
			}
		});
	});
	
	

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
	
	
	
	/*******************医院社康联动***********************/
		$("#hopId").change(function() {
			var hosId = $("#hopId option:selected").val();
			var option;
			$.ajax({
				type : "post",
				url : "getCommunityInfo",
				data : { hosId : hosId },
				success : function(res) {
					if (res.flag == 1) {
						var results = res.ciList;
						$("#commId").empty();
						for ( var i = 0; i < results.length; i++) {
							option = "<option value='" + results[i].id + "'>" + results[i].name + "</option>";
							$("#commId").append(option);
						}
					}else{
						$("#commId").empty(); //清空级联
					}
				}
			});
		});
	
	
});


//加载所有的指导模板拼接到类型后面
function loadPostnatalModel(){
	var vType=$("#visitType").val(); //随访模板类型
	$.ajax({
		url:"queryPostnatalVisitModel",
		data:{"type":vType},
		async: false,
		type:"post",
		success:function(data){
			var html="";    // <label><input style="width:10px;" type="checkbox" name="adviceType" value="5"> 口腔保健指导</label>
			if(data!=null){
				for(var i=0;i<data.length;i++){
	            	html+='<label id="advice_'+data[i].id+'" >'+'<input style="width:12px;" type="radio"  name="guide" tagContent="'+data[i].content+'"  value="'+data[i].id+'" onclick="appendContent();">'+data[i].name+'</label>';
				}
			}
			$("#visitBabyType").append(html);
		}
	});
}


//在类型后追加一个onclick事件用来追加显示内容
function appendContent(){
	var contents="";
	$("input[name='guide']").each(function(){
		if($(this).attr("checked")){
//			alert($(this).attr("tagContent"));
			if($(this).attr("tagContent") != "" ){
				contents+=$(this).attr("tagContent")+",";
			}
		} 
	});
	$("#guideContent").html(contents.substring(0,contents.length-1));
}


//初始化模板列表
function initPostnatalModel(){
	var Type=$("#visitType").val(); //随访模板类型
	$.ajax({
		url:"queryPostnatalVisitModel",
		data:{"type":Type},
		type:"post",
		success:function(data){
			var html="";
			if(data!=null){
				for(var i=0;i<data.length;i++){
                	html+='<tr height="40px">'+
                        '<td >'+data[i].id +'</td>'+
                        '<td >'+data[i].name+'</td>'+
                        '<td title='+data[i].content +'>'+data[i].content +'</td>'+
                        '<td ><a href="#" data-toggle="modal" data-target="#myModal2" onclick="updateVisitGuidanceById('+data[i].id+')">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" onclick="deleteVisitGuidanceById('+data[i].id+')">删除</a></td>'+
                        '</tr>';
				}
			}else{
				html+="<tr><td colspan='4'>暂无模板！</td></tr>";
			}
			$("#testCaseDiagnoseModel").html(html);
		}
	});
}


//修改诊断模板
function updateVisitGuidanceById(id){
	$.ajax({
		url:"updatePostnatalVisitModel",
		data:{id:id},
		type:"post",
		success:function(data){
			$("#ModelName").val(data.name);
			$("#ModelContentNew").val(data.content);
			$("#operationType").val("update");
			$("#updateTestCaseId").val(id);
		}
	});
	
}
//删除模板
function deleteVisitGuidanceById(id){
	$.ajax({
		url:"deletePostnatalVisitById",
		data:{id:id},
		type:"post",
		success:function(data){
			if(data){
				initPostnatalModel();
				$("#"+id).remove();  ///删除追加在的模板类型
			}else{
				alert("删除失败！");
			}
		}
	});
}
