/** 胎心音频播放对象 **/
var media = new Audio();
var timer;

$(function(){
	var isChat = false;
	/** 初始化所有chart **/
	$(".chart-div").each(function(){
    	var config = {
        		element: $(this).attr("id"),
        		fhr: $(this).find('.data').val() || [],
        		bl:  $(this).find('.baseData').val() || [],
        		ua:  $(this).find('.fetalMoveData').val() || [],
        		fmp: $(this).find('.uterusData').val() || [],
        		ad:  $(this).find('.adData').val() || []
        	}
        	new Chart().init(config);
    });
	/** 播放按钮绑定事件 **/
	$(".play").bind("click", Report.musicPlayer);
	/** 详情的显示隐藏 **/
	$(".up-down-operate").bind("click", Report.changeDetailState);
	/** 点击保存生成报告 **/
	$(".generate-report").bind("click", Report.generateReport);
	$(".go-back").click(function(){
		if(isChat){
			if(navigator.userAgent.indexOf("Firefox") > -1){
				history.go(-3);
			}else{
				history.go(-2);
			}
			return false;
		}else{
			history.go(-1);
			return false;
		}
	});
	$(".report-chat").click(function(){
		isChat = true;
		var userId = $(this).attr("userId");
		var hospitalId = $(this).attr("hospitalId");
		var userName = $(this).attr("userName");
		if(userName==null){
			userName = "该用户暂无昵称";
		}
		var orderId = $(this).attr("orderId");
		var consumerId = $(this).attr("consumerId");
		var monitorTimes = $(this).attr("monitorTimes");
		$.post(baseURL+"/report/checkConsumer",{consumerId:consumerId},function(data){
			data = eval("("+data+")");
			if(!isNaN(data.state)){
				if (data.report != "") {
					$(".reportUrl").attr("href", data.report);
					$('.report').show();
				}
	    		/** 用户类型/是否有效/消费订单ID/订单ID/第几次监测/医院ID/用户ID/用户名 **/
				var src = chatURL+"/chat/1/"+ data.state+"/"+consumerId+"/"+orderId +"/"+monitorTimes+"/"+hospitalId+"/"+userId+"/"+userName;
	        	$('#messageModalLabel').html("正在和【" +userName +"】对话中");
	        	$('#chatIframe').attr("src",src);
	        	$('#messageModal').modal('show');	
			}
		});
	});
});

var Report = function(){
	
	/** 是否是第一次播放，此处控制资源多次加载音频不能暂停问题 **/
	var isFirst = true;
	/** 是否是当前对象，如果不是当前对象，则将offsetX重置为0 **/
	var currentId;
	
	return {
		
		/** 胎心播放暂停 **/
		musicPlayer : function(){
			var prevId = $(this).attr("value");
			var musicFile = $($("#chart"+prevId).children(".music")).val();
			if(prevId != currentId){
				offsetX = 0;
				media.setAttribute("src", musicFile);
			}
			currentId = prevId;
			
			/** 如果状态为暂停则点击继续播放，播放则点击暂停 **/
			if (media.paused) {
				if(isFirst){
					media.setAttribute("src", musicFile);
				}
				timer = self.setInterval(function(){
					offsetX--;
					if(!media.ended){
						Report.chartCurrentTime(prevId);
						new Chart().draw("chart"+prevId, "auto");
					}
				},500);
				media.play();
				isFirst = false;
				$(this).attr("src", baseURL+"/media/image/button_luyin_hover.png");
            }else{
                media.pause();
                clearInterval(timer);
                $(this).attr("src", baseURL+"/media/image/button_play_hover.png");
            }
		},
		chartCurrentTime : function(element){
			var data = $($("#chart"+element).children(".data")).val();
			var dataArray;
			if(data.indexOf("[") != -1 && data.indexOf("]") != -1){
				dataArray = eval('(' + data + ')');
			}else{
				data.replace("[", "");
				data.replace("]", "");
				dataArray = data.split(",");
			}
			/** 渲染当前时间 **/
			var absOffsetX = Math.abs(offsetX);
			if(absOffsetX > dataArray.length){
				$($("#panel"+element).find(".timeNow")).text("--:--:--");
				$($("#chart"+element).parent("div").find(".valueNow")).text("--次/分");
			}else{
				var timeNow = Math.floor(absOffsetX / 2);
				var hour = Math.floor(timeNow / 3600);
			    var minute = Math.floor((timeNow % 3600) / 60);
			    var second = timeNow % 3600 % 60;
			    hour = hour < 10 ? "0" + hour : hour;
			    minute = minute < 10 ? "0" + minute : minute;
			    second = second < 10 ? "0" + second : second;
			    /** 渲染当前值 **/
			    $($("#panel"+element).find(".timeNow")).text(hour+":"+minute+":"+second);
			    
	            $($("#chart"+element).parent("div").find(".valueNow")).text(dataArray[absOffsetX]+"次/分");
			}
		},
		changeDetailState : function(){
			var operateId = $(this).attr("value");
			var detailObj = $("#panel"+operateId).find(".detail-info");
			if(detailObj.is(":hidden")){
				detailObj.show();
				$($(this).children("i")).removeClass("icon-double-angle-down");
				$($(this).children("i")).addClass("icon-double-angle-up");
			}else{
				detailObj.hide();
				$($(this).children("i")).removeClass("icon-double-angle-up");
				$($(this).children("i")).addClass("icon-double-angle-down");
			}
		},
		generateReport : function(){
			var id = $(this).attr("value");
			var data = $($("#chart"+id).children(".data")).val();
			
			var start_index = (start_point - default_start) * 2;
            var end_index = (end_point - default_start) * 2;
            var start_offset = start_index + Math.abs(offsetX) - Math.abs(offset_can);
            var end_offset = end_index + Math.abs(offsetX);
            
            var dataArray = data.split(",");
            if(start_offset >= dataArray.length){
            	App.dialog_show("", "请选择生成报告区域！", function(){
					$(".info-dialog-close").trigger("click");
				});
            	return;
            }
            
			App.confirm_show("", "确认生成报告？", function(){
				$(".info-confirm-cancel").trigger("click");
				var reason = $($("#panel"+id).find(".descContent")).val();
				if(reason.length > 50){
					App.dialog_show("", "备注信息在50字以内！", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
				
				$.ajax({
                    type : "POST",
                    url : baseURL+'/report/generate',
                    data : {'id' : id, 'reason' : reason, 'offset' : offsetX, 'start':start_offset, 'end':end_offset},
                    dataType : "json", 
                    timeout : 60000,
                    success : function(data){
                    	if(data == 'success'){
                    		App.dialog_show("", "报告信息生成成功！", function(){
    							$(".info-dialog-close").trigger("click");
    							location.reload();
    						});
                    	}else if(data == 'failed'){
                    		App.dialog_show("", "报告生成失败！", function(){
    							$(".info-dialog-close").trigger("click");
    						});
                    	}
                    }
                });
			});
		}
	}
}();