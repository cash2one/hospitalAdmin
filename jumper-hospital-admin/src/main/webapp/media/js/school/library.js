$(function(){
	/** 菜单切换 **/
	$(".library_type").bind("click", library.menuChoose);
	/** 删除线下课程 **/
	$(".off-delete").bind("click", library.deleteCourse);
	/** 删除网络课程 **/
	$(".on-delete").bind("click", library.deleteCourse);
});

var library = function(){
	return {
		menuChoose:function(){
			var result = $(this).attr("value");
			$("#typeValue").val(result);
			$("#form").submit();
		},
		deleteCourse:function(){
			var type = $("#typeValue").val();
			var recordId = $(this).attr("value");
			App.confirm_show("", "是否删除课程信息？", function(){
				$(".info-confirm-cancel").trigger("click");
				App.ajaxRequest(
					'/school/courseDelete', {'recordId':recordId, 'libraryType':type}, 'get', true, 'json',
					function resultHandler(data){
						if(data == 'paramError'){
							App.dialog_show("提示！", "参数错误！", function(){
								$(".info-dialog-close").trigger("click");
							});
						}else if(data == 'failed'){
							App.dialog_show("提示！", "添加失败！", function(){
								$(".info-dialog-close").trigger("click");
							});
						}else if(data == 'error'){
							App.dialog_show("提示！", "系统错误！", function(){
								$(".info-dialog-close").trigger("click");
							});
						}else if(data == 'exist'){
							App.dialog_show("提示！", "该课程已经被安排不可以删除！", function(){
								$(".info-dialog-close").trigger("click");
							});
							setTimeout(function() {
								$(".info-dialog-close").trigger("click");
							}, 3000);
						}else{
							App.dialog_show("提示！", "删除成功", function(){
								$(".info-dialog-close").trigger("click");
								if(request == 'success'){
									location.reload();
								}
							});
							setTimeout(function() {
								location.reload();
							}, 3000);
						}
					}
				);
			});
		}
	}
}();