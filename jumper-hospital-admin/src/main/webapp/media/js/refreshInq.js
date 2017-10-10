var msgNum = 0;
$(function() {
	msgNum = parseInt($('.consult_message').text());
	/*setInterval(request_consultant, 10000);
	$(".close-chat").click(function() {
		$('#chatIframe').attr("src", "");
		$('.report').hide();
	});*/
});
var consult_id = "0";
function request_consultant() {
	var hospitalId = $("#hospitalId").val();
	var xhrurl = consultURL + '/consultation?hospital_id=' + hospitalId + '&consult_id=' + consult_id;
	$.ajax({
				type : "get",
				url : xhrurl,
				success : function(json) {
					consult_id = json.consult_id;
					if (json.status == 1) {
						// window.location.reload(true);
						$("#tishi").attr("src",baseURL+"/media/image/msg.png");
						var media = new Audio();
						media.setAttribute("src",baseURL+"/media/image/mm.mp3");
						media.play();
					}
				},
				error : function(e) {
					alert("errors");
				}
			});
}
var msgNum = parseInt($('.consult_message').text());
window.addEventListener("message", function(event) {
	var data = event.data;
	if (isNaN(data)) {
		document.getElementById('msg_list').innerHTML = data;
		$('.chat').click(
				function() {
					$this = $(this);
					var user_id = $(this).attr("user_id");
					var user_name = $(this).attr("user_name");
					var hospital_id = $('#hospital_id').val();
					if (user_name == null) {
						user_name = "该用户暂无昵称";
					}
					var orderId = $(this).attr("order_id");
					var consumerId = $(this).attr("consumer_id");
					var monitorTimes = $(this).attr("monitor_times");
					$.post(baseURL + "/report/checkConsumer", 
						  {consumerId : consumerId}, 
					function(data) {
						data = eval("(" + data + ")");
						if (!isNaN(data.state)) {
							if (data.report != "") {
								$(".reportUrl").attr("href", data.report);
								$('.report').show();
							}
							/** 用户类型/是否有效/消费订单ID/订单ID/第几次监测/医院ID/用户ID/用户名 * */
							var src = chatURL + "/chat/1/" + data.state + "/"
									+ consumerId + "/" + orderId + "/"
									+ monitorTimes + "/" + hospital_id + "/"
									+ user_id + "/" + user_name;
							$('#messageModalLabel').html(
									"正在和【" + user_name + "】对话中");
							$('.userName').text(user_name);
							$('#chatIframe').attr("src", src);
							$('#messageModal').modal('show');
							$this.remove();
						}
					});
				});
	} else {
		$('.consult_message').text(data);
		if (parseInt(data) > msgNum) {
			var audio = document.getElementById("chatAudio");
			audio.play(); // 播放声音
			msgNum = parseInt(data);
		}
	}
}, false);
function showMsgList() {
	if ($('.consult_message').text() != "0") {
		document.getElementById('msg_list_iframe').contentWindow.postMessage("", chatURL);
	}else{
		document.getElementById('msg_list').innerHTML = "";
	}
}
