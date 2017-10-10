/** 胎心音频播放对象 **/
var media = new Audio(); 
var timerPlay;
var isMediaPlay = false; //paused有时候要点两次
$(function(){
	$('.call_hide').each(function(){
		$(this).dragging({
			move : 'both',
			randomPosition : false
		});
	});
	$(".call-fire3").click(function(){
		$(".call_hide").css("display","none");
	})
	var isChat = false;
	/** 初始化所有chart **/
	$(".chart-div").each(function(){
    	var config = {
    		element: $(this).attr("id"),
    		fhr: $(this).find('.data').val() || [],
    		bl:  $(this).find('.baseData').val() || [],
    		ua:  $(this).find('.uterusData').val() || [],
    		fmp: $(this).find('.fetalMoveData').val() || [],
    		ad:  $(this).find('.adData').val() || []
    	}
    	new Chart().init(config);
    });
	/** 播放按钮绑定事件 **/
	$(".play").bind("click", Report.isPlayer);
	/** 详情的显示隐藏 **/
	$(".up-down-operate").bind("click", Report.changeDetailState);
	/** 点击保存生成报告 **/
	$(".generate-report").bind("click", Report.generateReport);
	/** 拨打电话*/
	$(".call_cont").bind("click", Report.callToPatient);
	
	
	$(".automaticScoring").bind("click", Report.automaticScoring);
	
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
		var hospitalName =  $(this).attr("hospitalName");
		var busCode ="60102";//业务代码
		var color = 1;
		var fromUserType=3;//  1：医生 2：患者 用户 3：其他 
		if(userName==null){
			userName = "该用户暂无昵称";
		}
		var orderId = $(this).attr("orderId");
		var consumerId = $(this).attr("consumerId");
		var questionId = $(this).attr("questionId");
		var monitorTimes = $(this).attr("monitorTimes");
		$.post(baseURL+"/report/checkConsumer",{consumerId:consumerId},function(data){
			data = eval("("+data+")");
			if(!isNaN(data.state)){
				if (data.report != "") {
					$(".reportUrl").attr("href", data.report);
					$('.report').show();
				}
				var src = imURL+"fromUserId="+ hospitalId +"&fromNickName="+hospitalName+"&fromHeadUrl&fromUserType="+fromUserType
				+"&&toUserId="+userId+"&toNickName="+userName+"&toHeadUrl&toUserType=2&userType=1&busCode="+busCode+"&color="+color+"&consultantId="+userId;
	        	$('#messageModalLabel').html("正在和【" +userName +"】对话中");
	        	$('#chatIframe').attr("src",src);
	        	$('#messageModal').modal('show');	
			}
		});
	});
	var selects = document.getElementsByTagName("select");
	$.each(selects, function() {
		$(this).bind("change",function(){
			var prevId = $(this).attr("id");
			if(!prevId) return;
			var value = $(this).val();
			var element = $("#chart"+prevId).attr("id");
			new Chart().initSelect(element,value);
		});
	});
	
});
var Report = function(){
	var speed = 3;
	var timeSpeed = 1;
	/** 是否是第一次播放，此处控制资源多次加载音频不能暂停问题 **/
	var isFirst = true;
	/** 是否是当前对象，如果不是当前对象，则将offsetX重置为0 **/
	var currentId;
	var scrollWidth;
	var scrollLeft;
	var dataArray,dataIndex;
	//电话拨打解决乱码
	function encodeStr(str){
		return encodeURIComponent(encodeURIComponent(str));
	}
	return {
		/** 拨打电话*/
		callToPatient : function(){
			/*$(".box-3 call_hide").show();*/
			var userId = $(this).attr("userId");
			var userName = $(this).attr("userName");
			var userMobile = $(this).attr("userMobile");
			var hospitalId = $(this).attr("hospitalId");
			$.ajax({
                type : "POST",
                url : baseURL+'/visits/callToPatient',
                data : {'hospitalId' : hospitalId,'userId' : userId, 'userName' : userName, 'userMobile' : userMobile},
                dataType : "json", 
                timeout : 60000,
                success : function(data){
                	if(data != null){
                		var patName = data.patName;
                		//解决中文乱码
                		patName = encodeStr(patName);
                		$(".call_hide").show();
                		$("#callToPatient").attr("src",data.url + patName);
                		setTimeout(function () { 
                			$(".call-fire3").addClass("glyphicon glyphicon-remove")
                	    }, 2000);
                	}
                }
            });
		},
		isPlayer: function(){
			var prevId = $(this).attr("value");
			// 获取当前时间与实际时间的比例差距
			speed = $('button[consumerid="'+ prevId +'"]').attr('data-speed') || $("#chart"+prevId).siblings('.operate-btn').find('button').eq(0).attr('data-speed') || 3;
			if(speed == 1){
				timeSpeed = 0.877;
			}else if(speed == 2){
				timeSpeed = 0.6668;
			}else if(speed == 3){
				timeSpeed = 0.45;
			}else if(speed == 20){
				timeSpeed = 1;
			}
			scrollSize = timeSpeed
			var data = $("#chart"+prevId).children(".data").val();
			if(data.indexOf("[") != -1 && data.indexOf("]") != -1){
				dataArray = eval('(' + data + ')');
			}else{
				data.replace("[", "");
				data.replace("]", "");
				dataArray = data.split(",");
			}
			scrollWidth = $('#chart'+prevId).find('.content-chart .oxLabel').width() - 1200;
			scrollSize = 1;  //对应滚动条每次移动的距离
			var musicFile = $("#chart"+prevId).children(".music").val();
			if(prevId != currentId){
				media.pause();
				window.clearInterval(timerPlay);
				for(var i = 0;i < $(".play").length;i++){//将其他播放的 全部关闭 修改为不播放图
					var thisImg  = $(".play").eq(i).attr("value");
					if(prevId != thisImg){
						$(".play").eq(i).attr("src",baseURL+"/media/image/button_play_hover.png");
						$(".play").eq(i).attr('data-isMediaPlay', false);
					}
				}
				media.setAttribute("src", musicFile);
				currentId = prevId;
				isMediaPlay = false;
			}

//			if(media.networkState != 1){//如果网络情况不理想 则进入这里
//				$("#panel"+prevId).find(".play").attr("src", baseURL+"/media/image/load.gif");
//				// 加载正常
//				media.onloadedmetadata = function() { 
					// 老版本32位 google浏览器这里进不来有时候
//					Report.musicPlayer(prevId);
//				};
//				// 加载url异常情况
//				media.addEventListener("error", function() {
//					alert("url error");
//			    }, true);
//				return ;
//			}else{ //如果网络情况正常 则进入这里 播放
//				Report.musicPlayer(prevId);
//			}
			var isError = false;	
			media.addEventListener("error", function() {
				alert("url error");
				isError = true;
				return false;
		    }, true);
			!isError && Report.musicPlayer(prevId);
		},
		// 胎心播放暂停
		musicPlayer : function(prevId) {
			// 如果状态为暂停则点击继续播放，播放则点击暂停
			if(scrollLeft == 0){
				$("#panel"+prevId).find(".timeNow").text("--:--:--");
				$("#chart"+prevId).parent("div").find(".valueNow").text("--次/分");
				$('#chart'+prevId).find('.content-chart').scrollLeft(0);
			}
			if(!isMediaPlay){
				isMediaPlay = true;
				media.play();
				$("#panel"+prevId).find(".play").attr("src", baseURL+"/media/image/button_luyin_hover.png");
				window.clearInterval(timerPlay);
				timerPlay = window.setInterval(function(){
					scrollLeft = $('#chart'+prevId).find('.content-chart').scrollLeft() + scrollSize;
					$('#chart'+prevId).find('.content-chart').scrollLeft(scrollLeft);
					Report.chartCurrentTime(prevId);
				},500);
			}else{
				isMediaPlay = false;
                media.pause();
                window.clearInterval(timerPlay);
            	$("#panel"+prevId).find(".play").attr("src", baseURL+"/media/image/button_play_hover.png");
            }
			$("#panel"+prevId).find(".play").attr('data-isMediaPlay', isMediaPlay);
		},
		chartCurrentTime : function(prevId){
			/** 渲染当前时间 /1.1672975400773908  **/
			var timeNow = Math.floor(scrollLeft * timeSpeed);
			var hour = Math.floor(timeNow / 3600);
		    var minute = Math.floor((timeNow % 3600) / 60);
		    var second = timeNow % 3600 % 60;
		    hour = hour < 10 ? "0" + hour : hour;
		    minute = minute < 10 ? "0" + minute : minute;
		    second = second < 10 ? "0" + second : second;
		    /** 渲染当前值 **/
		    $("#panel"+prevId).find(".timeNow").text(hour+":"+minute+":"+second);
		    dataIndex = Math.ceil(scrollLeft * (dataArray.length / scrollWidth));
            $("#chart"+prevId).parent("div").find(".valueNow").text(dataArray[dataIndex >= dataArray.length - 1?dataArray.length - 1 : dataIndex]+"次/分");
            
			if( scrollLeft >= scrollWidth){
				$("#panel"+prevId).find(".play").attr("src", baseURL+"/media/image/button_play_hover.png");
				media.pause();
				//根据曲线播放完毕之后 重新设值 重新开始
				media.currentTime = 0;
                clearInterval(timerPlay);
            	scrollLeft = 0;
            	dataIndex = 0;
            	isMediaPlay = false;
            	$("#panel"+prevId).find(".play").attr('data-isMediaPlay', isMediaPlay);
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
			var currentBtn= $("[consumerid='"+id+"']");
			var start_offset=currentBtn.attr("data-start");
			var end_offset=currentBtn.attr("data-end");
			//如果有选段则转成整数传过去
			if(start_offset!=undefined&&start_offset!=undefined){
				start_offset=Math.round(start_offset);
				end_offset=Math.round(end_offset);
			}
			//如果开始时间为空，则取全部长度
			if(undefined ==start_offset||undefined==end_offset){
				start_offset="";
				end_offset="";
			}
			var remark = $($("#panel"+id).find(".descContent")).val();
			
			App.confirm_show("", "确认生成报告？", function(){
				$(".info-confirm-cancel").trigger("click");
				var check_value = "";
				$($("#panel"+id).find("input[name='result_item']:checked")).each(function(){
					if(this.checked){
						check_value+=$(this).val()+",";
					}
				});
				if(check_value.length > 0){
					check_value = check_value.substring(0, check_value.length - 1);
				}
				var source = $($("#panel"+id).find(".source_input")).val();
				var speed = $($("#panel"+id).find(".speed")).val();
				if(undefined ==speed){
					speed="";
				}
				
				if(remark.length > 120){
					App.dialog_show("", "备注信息在120字以内！", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
				$.ajax({
                    type : "POST",
                    url : baseURL+'/report/xgenerate',
                    data : {'id' : id, 'remark' : remark, 'offset' : "", 'resultItem' : check_value, 'source' : source, 'speed':speed, 'start':start_offset, 'end':end_offset},
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
		},
		//跳转自动评分页面
		automaticScoring:function(){
			
			var consumerId=$(this).attr("consumerId");
			//走纸速度
			var speed=$(this).attr("data-speed");
			//开始点
			var start_offset=$(this).attr("data-start");
			//结束点
			var end_offset=$(this).attr("data-end");
			//总长度
			var data_endpoint=$(this).attr("data-endpoint");
			
			
			//如果有选段则转成整数传过去
			if(start_offset!=undefined&&start_offset!=undefined){
				start_offset=Math.round(start_offset);
				end_offset=Math.round(end_offset);
			}
			
			//如果开始时间为空，则取全部长度
			if(undefined ==start_offset||undefined==end_offset){
				start_offset="";
				end_offset="";
			}
			
			if(undefined ==speed){
				speed="";
			}
			
			$.ajax({
                type : "POST",
                url : baseURL+'/report/isAbleAutomaticScor',
                data : {'consumerId' : consumerId, 'start':start_offset, 'end':end_offset},
                dataType : "json", 
                timeout : 60000,
                success : function(data){
                	var msg="";
                	if("able"==data){
                		//window.open(baseURL+'/report/automaticScoring?consumerId='+consumerId+'&start='+start_offset+'&end='+end_offset+'&speed='+speed);
                		location.href=baseURL+'/report/automaticScoring?consumerId='+consumerId+'&start='+start_offset+'&end='+end_offset+'&speed='+speed;
                		return ;
                	}else if("disable"==data){
                		App.dialog_show("", "报告的时间小于十分钟！", function(){
							$(".info-dialog-close").trigger("click");
						});
                		return ;
                	}else if("failed"==data){
                		App.dialog_show("", "调用自动评分接口失败！", function(){
							$(".info-dialog-close").trigger("click");
						});
                		return ;
                	}else{
                		App.dialog_show("", data, function(){
							$(".info-dialog-close").trigger("click");
						});
                		return ;
                	}
                }
            });
		},
	}
}();

//js获取地址栏参数
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return "";
}

$(function(){
	/** 点击关闭按钮刷新当前页面不然还是当前的sessionMap**/
	$(".close-chat").bind("click", function(){
		var id=GetQueryString("id");
		var backURL;
		if(""==id){
			backURL=baseURL+"/report/audit";
		}else{
			backURL=baseURL+"/report/detail?id="+id;
		}
		location.href=backURL;
	});
});