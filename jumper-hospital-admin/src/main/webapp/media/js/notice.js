$(function(){
	$(".edit-btn").click(function(){
		var params = $(this).attr("value").split("|");
		if(params == '' || params.length < 1){
			return;
		}
		var id = params[0];
		var notice = params[1];
		var endTime = params[2].substring(0,10);
		//首先判断该公告是否正在发布中，在发布中提示必须取消发布后才能修改
		$.post(
				baseURL+"/notice/checkNotice",
				{id:id},
				function(data){
					if(data=="Y"){
						App.dialog_show("提示！", "改公告处于发布中，请先取消发布！", function(){
							$(".info-dialog-close").trigger("click");
						});
					}else{
						$("#noticeId").val(id);
						$("#notice").text(notice);
						$("#endTime").val(endTime);
						$(".myModel").trigger("click");
					}
				}
		);
		
	});
	
	$("#ok").click(function(){
		var d = new Date().toLocaleString().substring(0, 10).trim();
		//var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		var endTime = $("#endTime").val();
		var now = new Date().format("yyyy-MM-dd");

		//验证日期是否正确，公告内容是否为空
		if($("#notice").val()==""||$("#notice").val()==null){
			App.dialog_show("提示！", "请设置公告内容！", function(){
				$(".info-dialog-close").trigger("click");
			});
		}else if($("#notice").val().length>30){
			App.dialog_show("提示！", "公告内容不得超过30字！", function(){
				$(".info-dialog-close").trigger("click");
			});
		}else if($("#endTime").val()==""||$("#endTime").val()==null){
			App.dialog_show("提示！", "请设置截止日期！", function(){
				$(".info-dialog-close").trigger("click");
			});
		}else if(endTime<now){
			App.dialog_show("提示！", "您的截止日期小于当前时间了！", function(){
				$(".info-dialog-close").trigger("click");
			});
		}else{
			document.getElementById("ok").disabled = true;
			$.post(
					baseURL+"/notice/edit",
					{
						id:$("#noticeId").val(),
						notice:$("#notice").val(),
						endTime:$("#endTime").val()
					},
					function(data){
						if(data=="editY"){
							App.dialog_show("提示！", "修改成功！", function(){
								$(".info-dialog-close").trigger("click");
								window.location.reload();
							});
						}else if(data=="addY"){
							App.dialog_show("提示！", "添加成功！", function(){
								$(".info-dialog-close").trigger("click");
								window.location.reload();
							});
						}
					}
				);
		}
	
	});
	
	$("#addNotice").click(function(){
		$("#notice").val("");
		$("#endTime").val("");
		document.getElementById("ok").disabled = false;
		$(".myModel").trigger("click");
	});
	
	$(".btn-sm").click(function(){
		var params = $(this).attr("value").split("|");
		if(params == '' || params.length < 1){
			return;
		}
		var id = params[0];
		var notice = params[1];
		var endTime = params[2].substring(0,10);
		App.dialog_show("公告详情", "公告内容："+notice+";截止日期："+endTime, function(){
			$(".info-dialog-close").trigger("click");
		});
	});
	
	
});
	
function del(id){
	App.confirm_show("提示！", "确定要删除公告吗？", function(){
		$(".info-confirm-cancel").trigger("click");
		$.post(
			baseURL+"/notice/checkNotice",
			{id:id},
			function(data){
				if(data=="Y"){
					App.dialog_show("提示！", "改公告处于发布中，请先取消发布！", function(){
						$(".info-dialog-close").trigger("click");
					});
				}else{
					$.post(
						baseURL+"/notice/delete",
						{id:id},
						function(data){
							if(data=="Y"){
								App.dialog_show("提示！", "删除成功！", function(){
									$(".info-dialog-close").trigger("click");
									window.location.reload();
								});
							}else{
								App.dialog_show("提示！", "删除失败！", function(){
									$(".info-dialog-close").trigger("click");
								});
							}
						}
					);
				}
			}
		);
	});
}

function publish(id){
	App.confirm_show("提示！", "确定要发布公告吗？", function(){
		$(".info-confirm-cancel").trigger("click");
		$.post(
			baseURL+"/notice/checkPublish",
			{id:id},
			function(data){
				if(data=="Y"){
					App.dialog_show("提示！", "已经有公告处于发布中，请先取消发布！", function(){
						$(".info-dialog-close").trigger("click");
					});
				}else if(data=="N"){
					$.post(
						baseURL+"/notice/publish",
						{id:id},
						function(data){
							if(data=="Y"){
								App.dialog_show("提示！", "发布成功！", function(){
									$(".info-dialog-close").trigger("click");
									window.location.reload();
								});
							}else{
								App.dialog_show("提示！", "发布失败！", function(){
									$(".info-dialog-close").trigger("click");
								});
							}
						}
					);
				}else{
					App.dialog_show("提示！", "操作异常！", function(){
						$(".info-dialog-close").trigger("click");
					});
				}
			}
		);
	});
}
	
function backPublish(id){
	App.confirm_show("提示！", "确定要取消发布吗？", function(){
		$(".info-confirm-cancel").trigger("click");
		$.post(
			baseURL+"/notice/backPublish",
			{id:id},
			function(data){
				if(data=="Y"){
					App.dialog_show("提示！", "取消成功！", function(){
						$(".info-dialog-close").trigger("click");
						window.location.reload();
					});
				}else{
					App.dialog_show("提示！", "取消失败！", function(){
						$(".info-dialog-close").trigger("click");
					});
				}
			}
		);
	});
}	

Date.prototype.format = function(format){ 
	var o = { 
	"M+" : this.getMonth()+1, //month 
	"d+" : this.getDate(), //day 
	"h+" : this.getHours(), //hour 
	"m+" : this.getMinutes(), //minute 
	"s+" : this.getSeconds(), //second 
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
	"S" : this.getMilliseconds() //millisecond 
	}

	if(/(y+)/.test(format)) { 
	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	}

	for(var k in o) { 
	if(new RegExp("("+ k +")").test(format)) { 
	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
	} 
	} 
	return format; 
	}
	//alert(new Date().format("yyyy-MM-dd").substr(0,10));

	
	
	
	
	