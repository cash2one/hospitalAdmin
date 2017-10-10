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
	//字段验证
$(function(){
		//孕产期异常情况
		$(".checkNo1").click(function(){
			 $(".no1").attr('checked', false);
			 $(".no1").attr("disabled","true");
			 $(".clear").attr("disabled","true");
			 $(".clear").val("");
		});
		$(".checkOnly1").click(function(){
			 $(".no1").removeAttr("disabled");
			  $(".clear").removeAttr("disabled");
		});
		//正常   
		$(".ckeckNOeye").click(function(){
			 $(".noeye").attr("disabled","true");
			 $(".noeye").val("");
		});
		//异常  其他情况
		$(".checkOnlyeye").click(function(){
		 	  $(".noeye").removeAttr("disabled");
			  $(".noeye").removeAttr("readonly");
		});

		$(".ckeckNO2").click(function(){
			 $(".no2").attr("disabled","true");
			 $(".no2").val("");
		});
		$(".checkOnly2").click(function(){
		 	  $(".no2").removeAttr("disabled");
			  $(".no2").removeAttr("readonly");
		});
		$(".ckeckNO3").click(function(){
			 $(".no3").attr("disabled","true");
			 $(".no3").val("");
		});
		$(".checkOnly3").click(function(){
		 	  $(".no3").removeAttr("disabled");
			  $(".no3").removeAttr("readonly");
		});
		$(".ckeckNO4").click(function(){
			 $(".no4").attr("disabled","true");
			 $(".no4").val("");
		});
		$(".checkOnly4").click(function(){
		 	  $(".no4").removeAttr("disabled");
			  $(".no4").removeAttr("readonly");
		});
		
		$(".ckeckNO5").click(function(){
			 $(".no5").attr("disabled","true");
			 $(".no5").val("");
		});
		$(".checkOnly5").click(function(){
		 	  $(".no5").removeAttr("disabled");
			  $(".no5").removeAttr("readonly");
		});
		
		$(".ckeckNO6").click(function(){
			 $(".no6").attr("disabled","true");
			 $(".no6").val("");
		});
		$(".checkOnly6").click(function(){
		 	  $(".no6").removeAttr("disabled");
			  $(".no6").removeAttr("readonly");
		});
		$(".ckeckNO7").click(function(){
			 $(".no7").attr("disabled","true");
			 $(".no7").val("");
		});
		$(".checkOnly7").click(function(){
		 	  $(".no7").removeAttr("disabled");
			  $(".no7").removeAttr("readonly");
		});
		$(".ckeckNO8").click(function(){
			 $(".no8").attr("disabled","true");
			 $(".no8").val("");
		});
		$(".checkOnly8").click(function(){
		 	  $(".no8").removeAttr("disabled");
			  $(".no8").removeAttr("readonly");
		});
		$(".ckeckNO9").click(function(){
			 $(".no9").attr("disabled","true");
			 $(".no10").val("");
		});
		$(".checkOnly9").click(function(){
		 	  $(".no9").removeAttr("disabled");
			  $(".no9").removeAttr("readonly");
		});
		$(".ckeckNO10").click(function(){
			 $(".no10").attr("disabled","true");
			 $(".no10").val("");
		});
		$(".checkOnly10").click(function(){
		 	  $(".no10").removeAttr("disabled");
			  $(".no10").removeAttr("readonly");
		});
		$(".ckeckNO11").click(function(){
			 $(".no11").attr("disabled","true");
			 $(".no11").val("");
		});
		$(".checkOnly11").click(function(){
		 	  $(".no11").removeAttr("disabled");
			  $(".no11").removeAttr("readonly");
		});
		
		$(".skinNo").click(function(){
			 $(".noskin").attr("disabled","true");
			 $(".noskin").val("");
		});
		$(".skinOnly").click(function(){
		 	  $(".noskin").removeAttr("disabled");
			  $(".noskin").removeAttr("readonly");
		});
		//转诊建议
		$(".suggestCkeck").click(function(){
			 $(".suggestReason").attr("disabled","true");
			 $(".suggestReason").val("");
		});
		$(".suggestOnly").click(function(){
			 $(".suggestReason").removeAttr("disabled");
			  $(".suggestReason").removeAttr("readonly");
		});
		
 	}); 

    
$(function(){
		loadVisitBabyModel();
		//form表单
		$("#submit").click(function() {
			if (!(validform.check(false))) {
					return ;
			    }; 
				$.ajax({
					type : "post",
					url : "savaVisitBabyRecord",
					data : $("#submitForm").serialize(),
					success : function(res) {
						  alert(res.msg);
						  var pathUrl = getRootPath();
						  if(res.flag == '1'){
							  window.location.href=pathUrl+"/neonatal/lookIndex?userId="+$("#userId").val()+"&archiveId="+$("#archiveId").val()+"&archiveBaseId="+$("#archiveBaseId").val();
						  }
					}
				});
		});	
		
		//取消
		$("#cancel").bind("click",function(){
			 history.go(-1);  
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
			loadBabyVisitModel();
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
			$.ajax({
				url:"saveVisitBabaModel",
				data:{"modelName":modelName,"modelContent":modelContent,"operationType":operationType,"modelId":updateTestCaseId},
				type:"post",
				success:function(data){
					if(operationType=="insert"){
						if(data){
							//将模板信息写入页面的模板选择项中
							$("#myModal2").modal("hide");
							loadBabyVisitModel();
							//将新增的模板,拼接到类型后面
							loadVisitBabyModel();
							window.location.reload();
						}else{
							alert("新增模板失败！");
						}
					}else{
						$("#myModal2").modal("hide");
						if(data){
							loadBabyVisitModel();
							loadVisitBabyModel();
							window.location.reload();
						}else{
							alert("修改模板失败！");
						}
					}
				}
			});
		});
		
		
});



//初始化模板列表
function loadBabyVisitModel(){
	$.ajax({
		url:"queryAllBabyVisitModel",
		data:{"type":0},
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
		url:"queryVisitGuidanceById",
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
		url:"deleteVisitGuidanceById",
		data:{id:id},
		type:"post",
		success:function(data){
			if(data){
				loadBabyVisitModel();
				removeGuidenceById(id);   ///模板内容
			}else{
				alert("删除失败！");
			}
		}
	});
}

//去掉删除的页面元素
function removeGuidenceById(id){
	$("#"+id).remove();
}

//加载所有的指导模板拼接到类型后面
function loadVisitBabyModel(){
	$.ajax({
		url:"queryAllBabyVisitModel",
		data:{"type":0},
		async: false,
		type:"post",
		success:function(data){
			var html="";    // <label><input style="width:10px;" type="checkbox" name="adviceType" value="5"> 口腔保健指导</label>
			if(data!=null){
				for(var i=0;i<data.length;i++){
	            	html+='<label id="advice_'+data[i].id+'" >'+'<input style="width:12px;" type="checkbox"  name="advice" tagContent="'+data[i].content+'"  value="'+data[i].id+'" onclick="appendContent();">'+data[i].name+'</label>';
				}
			}
			$("#visitBabyType").append(html);
		}
	});
}

//在类型后追加一个onclick事件用来追加显示内容
function appendContent(){
	var contents="";
	$("input[name='advice']").each(function(){
		if($(this).attr("checked")){
//			alert($(this).attr("tagContent"));
			if($(this).attr("tagContent") != "" ){
				contents+=$(this).attr("tagContent")+",";
			}
		} 
	});
	$("#adviceContent").html(contents);
}



