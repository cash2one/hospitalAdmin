$(function(){
	
	/*初始化自动补全下拉框事件*/
	//finish.getAuditDoctors();
	
	/** 初始化查询开始时间控件 **/
	$('#startTime').daterangepicker({
		singleDatePicker: true,
		format:"YYYY-MM-DD",
		startDate : new Date,
		language: 'zh-CN' //汉化
	});
	/** 初始化查询结束时间控件 **/
	$('#endTime').daterangepicker({
		singleDatePicker: true,
		format:"YYYY-MM-DD",
		startDate : new Date,
		language: 'zh-CN' //汉化
	});
	
	$(".report-detail").click(function(){
		$(this).next("a").trigger("click");
	});
	$(".search-btn").bind("click", finish.searchTimeCheck);
	
	$(".type_chg").bind("click", finish.monitorTypeChg);
	
	$(".hos_chg").bind("change", finish.hospitalChg);
	
	$(".checkReport").bind("click", finish.updateIsViewedState);
	
});

var finish = function(){
	return {
		
		hospitalChg : function(){
			$("#form").submit();
		},
		monitorTypeChg : function(){
			var type = $(this).attr("value");
			$("#input-type").val(type);
			$("#form").submit();
		},
		searchTimeCheck:function(){
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var startDate = new Date(startTime);
			var endDate = new Date(endTime);
			var auditDoctors=  $("#auditDoctors").val();
			if(!auditDoctors){
				$("#doctorId").val("");
			}
			if(startDate > endDate){
				App.dialog_show("", "开始时间不能大于结束时间！", function(){
					$(".info-dialog-close").trigger("click");
				});
				return;
			}
			$("#form").submit();
		},
		updateIsViewedState:function(){
			//ture  是子医院  false不是子医院
			var isSubordinatehospital=  $(this).find("span").attr('isSubordinatehospital');
			//当前报告的id
			var reportId=  $(this).find("span").attr("reportId");
			//当前报告是否被查看，如果被查看为1 则不用更新  如果为0 则需要更新 ，如果为空则不属于下级医院不需要更新
			var isViewed=  $(this).find("span").attr("isViewed");
			//报告的pdf地址
			var fileHref=  $(this).find("span").attr("fileHref");
			//如果为0则没有更新，点击之后需要更新   而且要是子医院点
			if("false"==isViewed && "true"== isSubordinatehospital){
			 	$.ajax({
			 		url : url+"/report/checkFinishedReport",
			 		type : "POST",
			 		dataType : "json",
			 		async:false,
			 		data:{"reportId":reportId},
			 		success : function(json) {
			 			//当前页面刷新样式
			 			location.href=url+"/report/complete";
			 			window.open(fileHref); 
			 		},
			 		error : function(XMLHttpRequest, textStatus, errorThrown) { 
			 			errorTs("系统异常");
			 			}
			 		});
			//如果不为0则只需要跳转到pdf报告页面就可以了
			}else{
				window.open(fileHref); 
			}
		}
/*		getAuditDoctors:function(){
			
			var selectedData=[];
				 	$( "#auditDoctors" ).autocomplete({
				      source: function(request, response){
				    	  
				      		var doctorName=  $("#auditDoctors").val();
							$.post(baseURL+"/admin/getDoctors",
									{"doctorName": doctorName},
								function(data){
								//封装数据
								var jsonData=eval(data);
		 			    		for(var i=0;i<jsonData.length;i++){
					    			var obj={};
					    			obj.id=jsonData[i].id;
					    			obj.label=jsonData[i].name;
					    			selectedData.push(obj); 
					    		}
								    response($.map(jsonData,function(item) {
					                    return{
					                        label: item.name,
					                    	//label: item.username,
					                        id: item.id
					                    }
					                }));
					    	});
				      },
					  select:function(e, ui) {
						//$( "#doctorId").val(ui.item.id);
						  $( "#doctorId").attr("value",ui.item.id);
		   			  }
				      
				   }); 
		}*/
	}
}();