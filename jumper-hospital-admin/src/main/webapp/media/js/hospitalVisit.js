window.onload=function(){
		var status = $("#status").val();
		if(status==0){
			document.getElementById("close").style.display="none";
		}else{
			document.getElementById("open").style.display="none";
		}
		
		var majors = $("#majors").val();
		var majorList = document.getElementsByName("major");
		var majorInt = majors.split(",");
		for(var i=0;i<majorInt.length;i++){
			for(var j=0;j<majorList.length;j++){
				if(majorInt[i]==majorList[j].value){
					$("input:checkbox").eq(j).attr("checked","true");
				}
			}
		}
	};
	$(function(){
		$("#ok").click(function(){
			if($("#money").val()!=null&&$("#money").val()!=""){
				var money = parseInt($("#money").val());
				document.getElementById("myMoney").innerText = money+" 元/次";
				$.post(
					"/JumperRemoteAdmin/hospitalVisit/editMoney",
					{cost:money},
					function(data){
						if(data=="success"){
							//alert("金额修改成功！");
							App.dialog_show("提示！", "金额修改成功！", function(){
								$(".info-dialog-close").trigger("click");
							});
						}else{
							//alert("金额修改失败！");
							App.dialog_show("提示！", "金额修改失败！", function(){
								$(".info-dialog-close").trigger("click");
							});
						}
					}
				);
			}
		});
		
		$("#editMoney").click(function(){
			$("#money").val($("#editMoney").val());
		});
		
		$("#week_high").change(function(){
			var weeklow = $("#week_low").val();
			var weekhigh = $("#week_high").val();
			if(weeklow>weekhigh){
				App.dialog_show("提示！", "您的开通日期设置不正确！", function(){
					$(".info-dialog-close").trigger("click");
				});
			}
		});
		
		//点击关闭服务，将页面内容设置为不可点击（按钮变灰）,开通状态设置为0
		$("#closeBut").click(function(){
			document.getElementById("close").style.display="none";
			document.getElementById("open").style.display="inline";
			$.post(
				"/JumperRemoteAdmin/hospitalVisit/closeVisit",
				function(data){
					if(data=="success"){
						App.dialog_show("提示！", "服务已关闭！", function(){
							$(".info-dialog-close").trigger("click");
						});
						$("#visitStatus1").text("已关闭");
						$("#editMoney").attr("disabled","true");
						$("#editTime").attr("disabled","true");
						$("#editMajor").attr("disabled","true");
					}
				}
			);
			
		});
		//点击开通，将按钮恢复
		$("#openBut").click(function(){
			document.getElementById("close").style.display="inline";
			document.getElementById("open").style.display="none";
			$.post(
				baseURL+"/hospitalVisit/openTwice",
				function(data){
					if(data=="success"){
						App.dialog_show("提示！", "服务已开通！", function(){
							$(".info-dialog-close").trigger("click");
							window.location.reload();
						});
						$("#visitStatus1").text("已开通");
						$("#editMoney").attr("disabled","false");
						$("#editTime").attr("disabled","false");
						$("#editMajor").attr("disabled","false");
						
					}
				}
			);
		});
		
		
		
		$("#editTime").click(function(){
			var weekLow = $("#week_low").val();
			var weekHigh = $("#week_high").val();
			var beginTime = $("#begin_Time").val();
			var endTime = $("#end_Time").val();
			if(weekLow>weekHigh){
				App.dialog_show("提示！", "您的开通日期设置不正确！", function(){
					$(".info-dialog-close").trigger("click");
				});
			}
			else if(beginTime>endTime){
				App.dialog_show("提示！", "您的问诊开始时间大于结束时间了！", function(){
					$(".info-dialog-close").trigger("click");
				});
			}
			else if(parseInt(endTime)-parseInt(beginTime)<7){
				App.dialog_show("提示！", "您的问诊时间必须大于7小时！", function(){
					$(".info-dialog-close").trigger("click");
				});
			}
			else{
				App.confirm_show("提示信息", "确定要修改时间吗？", function(){
					$.post(
					"/JumperRemoteAdmin/hospitalVisit/editTime",
					{weekLow:weekLow,weekHigh:weekHigh,beginTime:beginTime,endTime:endTime},
					function(data){
						$(".info-confirm-cancel").trigger("click");
						if(data=="success"){
							var message = "";
							if(data=="success"){
								message = "问诊时间修改成功！";
							}
							App.dialog_show("提示！", message, function(){
								$(".info-dialog-close").trigger("click");
								if(data == 'success'){
									location.reload();
								}
							});
						}
					}
				);
			});
			
			}
			
		});
		
		$("#editMajor").click(function(){
		    var checkedMajor = new Array();
			$("input[type='checkbox']").each(function(){
				if($(this).attr("checked")){
					checkedMajor.push($(this).attr("value"));
				}
			});
			if(checkedMajor.length==0){
				App.dialog_show("提示！", "至少选中一个科室！", function(){
					$(".info-dialog-close").trigger("click");
				});
			}
			App.confirm_show("提示信息", "确定要修改科室吗？", function(){
				$.post(
				"/JumperRemoteAdmin/hospitalVisit/editMajor",
				{checkedMajor:checkedMajor.toString()},
				function(data){
					$(".info-confirm-cancel").trigger("click");
					if(data=="success"){
						App.dialog_show("提示！", "科室修改成功！", function(){
							$(".info-dialog-close").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
					}
				}
			);
			});
			
		});
		
	});
	