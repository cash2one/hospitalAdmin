var fn$ = function(id){
	return document.getElementById(id);
}
var ws = null;
window.onload=function(){
	init();
}
function init() {
    ws = new WebSocket(payNotifyUrl);
    ws.onopen = function() {
    	changeConnectState(true);
        console.log('Info: connection opened.');
    };
    ws.onmessage = function(e) {
    	 log(e.data);
    };
	ws.onclose = function() {
        changeConnectState(false);
        console.log('Info: connection closed.');
        var result = JSON.stringify(event);
        console.log(result);
	};
	ws.onerror = function() {
        changeConnectState(false);
        console.log('Info: connection closed.');
        var result = JSON.stringify(event);
        console.log(result);
	};
}

 /** 改变连接状态 **/
function changeConnectState(state){
	if(state){
		console.log("连接成功");
		fn$("connect_state").value="连接成功";
	}else{
		fn$("connect_state").value="连接关闭";
	}
}

/** 发送消息 **/
function sendMessage(){
	if (ws != null) {
         var message = fn$("message").value;
         var sendMsg = "{'message' : '"+message+"'}";
         ws.send(sendMsg);
         var myName = fn$("my_name").value;
         var showBack = "{'message' : '"+message+"'}";
         log(showBack);
    } else {
        alert('连接不可用, 请重新连接.');
    }
}

function log(message) {
	console.log("进入消息接收："+message);
	var date = {};
	var obj = eval('(' + message + ')');
	date = "<div>你的订单编号："+ obj.data.orderNo +"</div>"
	date += "<div>用户你好！</div>"
	date += "<div>医院："+ obj.data.doctorName +"</div>"
	date += "<div>服务类型："+ obj.monitor.remoteType +"</div>"
	date += "<div>服务次数："+ obj.monitor.monitorCount +"</div>"
	date += "<div>费用："+ obj.monitor.money +"</div>"
	$("#order-pay-form").find(".close").trigger("click");
	InitWEB.public_show("支付成功！", date, function(){
		$(".info-public-close").trigger("click");
	});
}

var InitWEB = function(){
	return{
		public_show : function(title, message, sureHandle){
			if(title != ''){
				$(".info-public-title").html(title);
			}
			$(".info-public-body").html(message);
			$(".info-public-href").trigger("click");
			$(".info-public-sure").unbind('click').click(sureHandle);
			if(sureHandle == null || sureHandle == undefined){
				$(".info-public-close").trigger("click");
			}
		},
	}
}();

