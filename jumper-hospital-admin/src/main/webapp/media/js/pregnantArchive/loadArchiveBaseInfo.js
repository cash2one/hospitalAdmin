$(function(){
	
	//根据档案id和医院关联id获取基本信息
	
	var archiveId=$("#archiveId").val();
	var archiveBaseId=$("#archiveBaseId").val();
	$.ajax({
		url:"loadArchiveBaseInfo",
		data:{archiveId:archiveId,archiveBaseId:archiveBaseId},
		type:"post",
		success:function(data){
			if(data!=null){
				$("#archive_name").text(data.name);
				$("#archive_mobile").text(data.mobile);
				$("#archive_lastPeriod").text(data.lastPeriod);
				$("#archive_pregnantDate").text(data.pregnantDate);
				$("#archive_pregnantWeek").text(data.pregnantWeek);
				$("#archive_pregnantDay").text(data.pregnantDay);
				$("#archive_deliveryTime").text(data.deliveryTime);
				if(data.pregnantState==1){
					$("#archive_pregnantState").text("已分娩");
				}
				if(data.pregnantState==0){
					$("#archive_pregnantState").text("怀孕中");
				}
			}
		}
	});
});
