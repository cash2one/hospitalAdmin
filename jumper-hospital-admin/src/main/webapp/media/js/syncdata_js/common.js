//共有的js位置
$(document).ready(function(){ 
	    var submitForm = $(".submitForm");
	    var tableType = $("#tableType").val();
	 	//图表与数据表页的切换
	 	$(".table_li").bind("click",function(){
	 		$("#tableType").val($(this).attr("name"));
	 		if($(this).attr("name") == "data"){
	 			$("#pageNum").val("1");//默认查询第一页
	 		}else{
	 			$("#pageNum").val("");
	 		}
	 		//提交表单
	 		submitForm.submit();
	 	});
	 	//不同查看模式切换
		$(".look").on("click", function(){
			$("#type").val($(this).attr("title"));
			//提交表单
	 		submitForm.submit();
		});
	 	//选择时间 切换
        $('#calendar_choose').change(function(){ 
            if($(this).children('option:selected').val()=="") {
            	
            }else {
                 $("#timeFlag").val($(this).val());
                 $("#date1").val("");
                 $("#date2").val("");
                 $("#id").val("");
             	 $("#addDate").val("");
                 if(tableType == "data"){
                	 $("#pageNum").val("1");
                 }
                 //提交表单
     	 		 submitForm.submit();
            }
        });
        /*当点击选择时间的确定按钮时*/
        $("#submitBtn").bind("click",function(){
        	var startTime = $("#date1").val();
        	var endTime = $("#date2").val();
        	if(endTime != "" && startTime == ""){
        		alert("请您选择开始时间");
        		return false;
        	}
        	if(endTime != ""){
        		if(startTime > endTime){
        			alert("开始时间不能大于结束时间，请您重新选择");
        			return false;
        		}
        	}
        	//设置时间查询标记为空字符串
        	$("#timeFlag").val("");
        	$("#id").val("");
        	$("#addDate").val("");
        	if(tableType == "data"){
           	 	$("#pageNum").val("1");
            }
        	//提交表单
        	submitForm.submit();
        });
        
        /*当点击首页，尾页时*/
        $(".pageNum").bind("click",function(){
        	$("#pageNum").val($(this).attr("name"));
        	submitForm.submit();
        })
        /*当点击上一页时*/
        $(".up").bind("click",function(){
        	var num = $("#pageNum").val();
        	if(num == "1"){
        		return false;
        	}else{
        		num = parseInt($("#pageNum").val())-1;
        	}
        	$("#pageNum").val(num);
        	submitForm.submit();
        });
        /*当点击下一页时*/
        $(".down").bind("click",function(){
        	var num = $("#pageNum").val();
        	if(num == $(".pageTotal").val()){
        		return false;
        	}else{
        		num = parseInt($("#pageNum").val())+1;
        	}
        	$("#pageNum").val(num);
        	submitForm.submit();
        });
        /*当点击跳转到任意页时*/
        $(".go").bind("click",function(){
        	var pageText = $(".page_text").val();
        	if(pageText == "" || pageText > $(".pageTotal").val()){
        		alert("输入框内容不能为空或者输入的值超出页码的范围")
        		return false;
        	}
        	$("#pageNum").val(pageText);
        	submitForm.submit();
        })
        //当点击历史数据中的查看时
		$(".lookData").bind("click",function(){
			var pageNum = $("#pageNum").val();
			if(pageNum == ""){
				$("#pageNum").val("1");
			}
			$("#addDate").val($(this).attr("name"));
			$("#monitorPregnant").val($(this).attr("pregnant"));
			//提交表单
        	submitForm.submit();
		});
        /*function getChartXtime(data){
        	var dataArra = data.substring(1,data.length-1).split(",");
        	var dataDay = [];
        	for(var i=0;i<dataArra.length;i++){
        		var dataArrb = dataArra[i].substring(1,dataArra[i].length-1).split(":");
        		dataDay[i] = "[Date.UTC("+dataArrb[0]+","+(dataArrb[1]-1)+","+dataArrb[2]+"),"+dataArrb[3]+"]";
        	}
        	data = "["+dataDay+"]";
        	return data;
        }*/
    });

