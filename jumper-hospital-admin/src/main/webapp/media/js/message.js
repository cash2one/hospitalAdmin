$(function(){
	$('.group-checkable').click(function(){
		$this = $(this);
		var allchecked=$this.is(':checked');
		if(allchecked){
			$('input[name="subBox"]').attr("checked","checked");
		}else{
			$('input[name="subBox"]').attr("checked",false);
		}
	});
	
	$("#news").click(function(){
		document.getElementById("errorMessage").style.display = "none";
		document.getElementById("push").disabled = false;
		$("#news_content").val("");
		$("#select1").find("option").remove();
		var weekList = $("#weekList").val();
		var babyList = $("#babyList").val();
		var weeks = weekList.substring(1,weekList.length-1).split(",");
		var babys = babyList.substring(1,babyList.length-1).split(",");
		$("#select1").append("<option value='-2'>--请选择用户类型--</option>");
		$("#select2").append("<option value='-2'>--请选择用户群--</option>");
		$("#select1").append("<option value='-1'>所有用户</option>");
		if(weeks!=null&&weeks!=""){
			$("#select1").append("<option value='1'>怀孕用户</option>");
		}
		if(babys!=null&&babys!=""){
			$("#select1").append("<option value='2'>已有宝宝用户</option>");
		}
		
		
		$(".myModel").trigger("click");
	});
	
	$("#select1").change(function(){
		$("#select2").find("option").remove();
		var selects = $("#select1").val();
		var weekList = $("#weekList").val();
		var babyList = $("#babyList").val();
		var weeks = weekList.substring(1,weekList.length-1).split(",");
		var babys = babyList.substring(1,babyList.length-1).trim().split(",");
		//alert(selects);
		if(selects==-1){
			$("#select2").append("<option value='-1'>所有用户</option>");
		}else if(selects==1){
			$("#select2").append("<option value='-1'>所有怀孕用户</option>");
			for(var i=0;i<weeks.length;i++){
				var week = weeks[i].trim();
				$("#select2").append("<option value='"+week+"'>孕"+week+"周</option>");
			}
		}else if(selects==2){
			$("#select2").append("<option value='-1'>所有已有宝宝用户</option>");
			for(var i=0;i<babys.length;i++){
				var baby = babys[i].trim().split(";");
				$("#select2").append("<option value='"+baby[0]+"_"+baby[1]+"'>宝宝"+baby[0]+'岁'+baby[1]+"月</option>");
			}
		}else if(selects==-2){
			$("#select2").append("<option value='-2'>--请选择对象--</option>");
		}
	});
	
	$(".pushMessage").click(function(){
		var select1 = $("#select1").val();
		var select2 = $("#select2").val();
		var content = $("#news_content").val();
		if(content==null||content==""){
			document.getElementById("errorMessage").style.display = "block";
			$(".errorMessage").text("消息内容不能为空，请输入内容！");
		}else if(content.length>100){
			document.getElementById("errorMessage").style.display = "block";
			$(".errorMessage").text("消息内容不得超过100字！");
		}else if(select1==-2){
			document.getElementById("errorMessage").style.display = "block";
			$(".errorMessage").text("请选择推送对象！");
		}else{
			document.getElementById("push").disabled = true;
			$.post(
				baseURL+"/pregnantBook/pushMyMessage",
				{
					content:content,
					select1:select1,
					select2:select2
				},function(data){
					if(data=="Y"){
						App.dialog_show("提示！", "消息推送成功！", function(){
							$(".info-dialog-close").trigger("click");
							window.location.reload();
						});
					}else if(data=="N"){
						App.dialog_show("提示！", "消息推送失败！", function(){
							$(".info-dialog-close").trigger("click");
						});
					}else if(data=="empty"){
						App.dialog_show("提示！", "医院当前无任何关联用户！", function(){
							$(".info-dialog-close").trigger("click");
						});
					}
				}
			);
		}
	});
	
});
