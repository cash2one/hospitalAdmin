

var submitFlag=false;
function changebtn(){
	if (submitFlag) {// 防止重复提交
		return;
	}
	submitFlag=true;
	window.location.href=baseURL+"/hos/networkInte?isnetwork="+$("#selectid").val();
	
}


function btsubmit(){
	App.confirm_show("提示","确认关闭网络诊室服务",function(){
	    $(".info-confirm-cancel").trigger("click");
        var url = baseURL+"/hos/findschedulingByMajorID";
		$.ajax({
			url : url,
			type : 'POST',
			data : {isnetwork:'0'},
			dataType : 'json',
			success : function(msg) {
			   if(msg.success){
			      window.location.href=baseURL+"/hos/network?isnetwork=0";
			   }else{
			      App.dialog_show("提示", msg.errormessage, function(){
			           	$(".info-dialog-close").trigger("click");
					});
			   }
			},
			error : function(msg) {
				App.dialog_show("提示", "系统异常", function(){
		           	$(".info-dialog-close").trigger("click");
				});
				window.location.reload(true);
			}
			
		});
     
   });
}



var openflag=false;
//开通 OR 关闭科室网络诊断服务
function openOrCloseSubmit(val,status){
	if (openflag) {// 防止重复提交
		return;
	}
	App.confirm_show("提示","确认开通该科室网络诊室服务",function(){
	    openflag=true;
		$(".info-confirm-cancel").trigger("click");
		$.ajax({
			url : baseURL+'/hos/openOrCloseMajorById',
			type : 'POST',
			data : { ids: val,status:status },
			dataType : 'json',
			success : function(msg) {
			   if(msg.success){
			       window.location.href=baseURL+"/hos/networkInte?isnetwork="+$("#selectid").val();
			   }else{
			       openflag=false;
			       App.dialog_show("提示！", msg.errormessage, function() {
		            	$(".info-dialog-close").trigger("click");
				   });
			   }
				
			},error : function(msg) {
				 openflag=false;
				window.location.reload(true);
			}
		 });
   });
}

