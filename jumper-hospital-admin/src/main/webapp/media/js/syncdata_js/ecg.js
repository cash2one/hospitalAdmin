$(function () {
	 //点击历史记录的查看时
	 $(document).on("click",".lookEcg",function(){
		$("#id").val($(this).attr("name"));
		$("#image").val($(this).attr("image"));
		$("#addDate").val($(this).attr("time"));
		$("#monitorPregnant").val($(this).attr("pregnant"));
		//提交表单
		$(".submitForm").submit();
	});
});