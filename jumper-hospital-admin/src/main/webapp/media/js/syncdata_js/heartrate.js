$(function () {
     //首先展示第一次的监测的数据
	 var file_domain = $("#file_domain").val();
	 var baseURL = $("#base_path").val();
	 //点击查看胎心时
	 $(document).on("click",".lookHeart",function(){
		 $("#addDate").val($(this).attr("time"));
		 $("#heartId").val($(this).attr("name"));
		 $("#startPrintTime").val($(this).attr("startTime"));
		 $("#monitorTimeLength").val($(this).attr("length"));
		 $("#monitorPregnant").val($(this).attr("pregnant"));
		 $("#testLength").val($(this).attr("testLength"));
		 $("#age").val($(this).attr("age"));
		 $("#name").val($(this).attr("userName"));
		 $("#phone").val($(this).attr("mobile"));
		 $("#currentPregnant").val($(this).attr("currentPregnant"));
		/** 初始化chart **/
		/*Chart.init($(".chart-div").attr("id"));
		//胎心播放全部重置
		media.pause();
		offsetX = 0;
		clearInterval(timer);
        $(".play").attr("src", baseURL+"/images/3.png");
        $($("#panel1").find(".timeNow")).text("--");
		$($("#chart1").parent("div").find(".valueNow")).text("--");*/
		//提交表单
		$(".submitForm").submit();
	 });
	 //播放音频
	 
	 //当点击打印报告时
	 $(document).on("click","#print-icon",function(){
		 var heartId = $("#heartId").val();
		 if(heartId == ""){
			 heartId = $("#FistHeartId").val();
		 }
		 //var domain_path = $("#domain_path").val();
		 var name = $("#userName").val();
		 var age = $("#userAge").val();
		 var pregnant = $("#monitorPregnant").val();
		 var monitorTime =  $("#monitorTimeLength").val();
		 var startPrintTime = $("#startPrintTime").val();
		 var data = $(".data").val();
		 var heartData = data.substring(1,data.length-2);
		 var fetalMoveData = $(".fetalMoveData").val();
		 var uterusData = $(".uterusData").val();
		 uterusData = uterusData.substring(1,uterusData.length-2);
		 var paperSpeed = $("#paperSpeed").val();
		 var offset = $("#offset").val();
		//与后台交互
		$.post("printHeartReport",
			{
					'heartId':heartId,
					'name':name,
					'age':age,
//					'healthNum':healthNum,
					'pregnant':pregnant,
					'monitorTime':monitorTime,
					'startPrintTime':startPrintTime,
					'heartData':heartData,
					'fetalMoveData':fetalMoveData,
					'uterusData':uterusData,
					'paperSpeed':paperSpeed,
					'offset':offset
			 },
			function(ret){	
				 if(ret != "" || ret != null){
					// window.location.href = domain_path+ret; 
					 window.open(ret);
					 //window.print();  
				 }
			 },"text");
	    });

});