//webIM登陆流程,首页初始化的时候登陆webIM

/*全局变量*/
var messageAll = $("#imMessageCenterBox");
var reIm = /\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/;
// 业务代码
var busCode = 60102;
// 颜色代码
var colorNum = 1;
var consultantId = 0;
// 总数
var msgCount = 0;
var isLogOn = true;// 是否开启控制台打印日志
var sdkAppID;
var accountType;
var selToID;
// 聊天图片
var audioIcon = "../resources/css/blue2/audio.gif";
var audioIcon = "../resources/css/blue2/audio.png";

// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
var curWwwPath = window.document.location.href;
// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
// 获取主机地址，如： http://localhost:8083
var localhostPaht = curWwwPath.substring(0, pos);
// 获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
console.log(pathName);
/* var url ="http://localhost:8081/common"; */
var url = localhostPaht + projectName;

// 当前用户身份
var loginInfo = {
	'sdkAppID' : sdkAppID, // 用户所属应用id,必填
	'appIDAt3rd' : sdkAppID, // 用户所属应用id，必填
	'accountType' : accountType, // 用户所属应用帐号类型，必填
	'identifier' : null, // 当前用户ID,必须是否字符串类型，必填
	'identifierNick' : null, // 当前用户昵称，选填
	'userSig' : null, // 当前用户身份凭证，必须是字符串类型，必填
};
var selType = webim.SESSION_TYPE.C2C;// 当前聊天类型
var selSess = null;// 当前聊天会话对象
var groupSystemNotifys = {};

// IE9(含)以下浏览器用到的jsonp回调函数
function jsonpCallback(rspData) {
	webim.setJsonpLastRspData(rspData);
}

// 监听事件
var listeners = {
	"onConnNotify" : onConnNotify,
	"jsonpCallback" : jsonpCallback,// IE9(含)以下浏览器用到的jsonp回调函数
	"onMsgNotify" : onMsgNotify,// 监听新消息(私聊，群聊，群提示消息)事件
	"onGroupInfoChangeNotify" : onGroupInfoChangeNotify,// 监听群资料变化事件
	"groupSystemNotifys" : groupSystemNotifys
// 监听（多终端同步）群系统消息事件
// "onKickedEventCall":onKickedEventCall
};
// 监听连接状态回调变化事件 false 未登录 true 已登录
var onConnNotify = function(resp) {
	switch (resp.ErrorCode) {
	case webim.CONNECTION_STATUS.ON:
		// webim.Log.warn('连接状态正常...');
		console.log('连接状态正常...');
		break;
	case webim.CONNECTION_STATUS.OFF:
		// webim.Log.warn('连接已断开，无法收到新消息，请检查下你的网络是否正常');
		console.log('连接已断开，无法收到新消息，请检查下你的网络是否正常');
		break;
	case webim.CONNECTION_STATUS.RECONNECT:
		info = '连接状态恢复正常: ' + resp.ErrorInfo;
		// webim.Log.warn(info);
		console.log(info);
		break;
	default:
		// webim.Log.error('未知连接状态,status='+resp.ErrorCode);
		console.log('未知连接状态,status=' + resp.ErrorCode);
		break;
	}
};

function onGroupInfoChangeNotify() {
};

// 刷新页面也要加载数据

// newMsgList 为新消息数组，结构为[Msg] 消息事件回调
function onMsgNotify(newMsgList) {
	var sess, newMsg;
	// 获取所有聊天会话
	var sessMap = webim.MsgStore.sessMap();
	// 取出业务代码不是60102(及时问诊)50101(医院问诊)的聊天记录

	// 添加声音提示
	var audio = document.getElementById("chatAudio");
	audio.play(); // 播放声音

	for ( var j in newMsgList) {// 遍历新消息
		newMsg = newMsgList[j];
//		var reN = newMsg.getSession().id();
		var reN = newMsg.getSession().fromAccount;
		// webIM的一个BUG新消息来了，不设置为未读
		// newMsg.sess.unread(msgCount+1);
		/*
		 * for(var j in sessMap){ if(j==newMsg.sess._impl.skey){
		 * sessMap[j]._impl.unread=sessMap[j]._impl.unread+1;
		 * //sessMap[j]._impl.unread++; } }
		 */
		if (reN == selToID) {// 为当前聊天对象的消息
			selSess = newMsg.getSession();
			// 在聊天窗体中新增一条消息
			if (newMsgList[0].elems[0].type == "TIMImageElem") {
				addImg(newMsg, true);
			} else {
				if (newMsgList[0].elems[0].type == "TIMTextElem") {
					addMsg1(newMsg);
				} else {
					//解析语音消息
					console.log("chehui.....=======================================================================================>");
					console.log(newMsg);
					if (newMsgList[0].elems[0].type == "TIMCustomElem") {
						var iNowList = newMsgList[0].elems[0].content.data;
						try{
							var testList = JSON.parse(iNowList);
						}catch(err){
							throw "服务端返回的参数格式有误";
						}
						console.log("----------------------------------------------------------------");
						console.log(testList);
						var f = testList.type;
//						alert(f+"=============================================");
						if(f == 15){
							console.log(typeof testList);
							var idList = testList.data.datas.backMsgId;
//							alert(idList);
//							$("#"+idList).remove();
							$("#"+idList).replaceWith('<div class="rightM" id="'+ idList + '"><p id="optTime" class="timeM">对方撤回了一条消息</p><div class="centerM"></div></div>');
//							var message = '<div class="rightM" id="'+ opt.msgId+ '"><p id="optTime" class="timeM">'+"\""+opt.niceName+"\""+"撤回了一条消息"+'</p><div class="centerM"></div></div>';
//							return message;
						}
						if(f == 14){
							convertSoundMsgToHtml(newMsg);
						}
						
					}/*else{
						convertSoundMsgToHtml(newMsg);
					}*/
				}
			}
		} else {
			// $("#im_"+reN).before();
			var number = parseInt($("#im_" + reN).find(".xdd").text());
			$("#im_" + reN).find(".xdd").text(number + 1).show();
			$("#im_" + reN).insertBefore($("#friendList").children().eq(0));

		}
	}
	// 消息来了调用刷新消息的方法
	initUnreadMsgCount1();
}

// 解析语音消息元素
function convertSoundMsgToHtml(content) {
	var yyArray = JSON.parse(content.elems[0].content.data);
	var yyArray1 = JSON.parse(content.elems[1].content.data);
	var msgid = "aAu_"+yyArray1.sendMsgId;
	var audioId = content.time;
	var time = webim.Tool.formatTimeStamp(content.getTime());
	var url = yyArray.data.content;
	var size = yyArray.data.size;
	var fromAccount = recveNickName;
	newMessage(leftAudio({
		audioId : audioId,
		msgId : msgid,
		time : time,
		niceName : fromAccount,
		src : url,
		size : size
	}));
};

// 语音历史记录
function oldConvertSoundMsgToHtml(content) {
	var url = content.msgContent;
	var msgId = "aAu_"+content.msgId;
	var id = content.id;
	var time = content.sendTime;
	var size = content.size;
	var fromAccount = recveNickName;
	oldMessage(leftAudio({
		audioId : id,
		msgId : msgId,
		time : time,
		niceName : fromAccount,
		src : url,
		size : size
	}));
}

/*
 * 添加IM用户，暂时不用添加用户 addIMAccount = function(){ var resultJson; $.ajax({ url :
 * url+"/consult/addIMAccount", type : "POST", dataType : "json", async:false,
 * data:{}, success : function(json) { console.log(json+"添加成功");
 * resultJson=json; }, error : function(XMLHttpRequest, textStatus, errorThrown) {
 * errorTs("添加用户失败"); } }); return resultJson; }
 */

/* 获取当前用户IM登陆信息 */
getLoginInfo = function() {
	var resultLogInInfo;
	$.ajax({
		url : url + "/consult/getWebIMLoginInfo",
		type : "POST",
		dataType : "json",
		async : false,
		data : {},
		success : function(json) {
			resultLogInInfo = json;
			console.log(json);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			errorTs("获取用户信息失败");
		}
	});
	return resultLogInInfo;
}

// webIM登陆
function webimLogin() {
	webim.login(loginInfo, listeners, options, function(resp) {
		loginInfo.identifierNick = resp.identifierNick;// 设置当前用户昵称
	}, function(err) {
		alert(err.ErrorInfo);
	});
};

// 有消息传入的时候才调用
// setInterval(function(){
setTimeout(function() {
	webim.syncMsgs(initUnreadMsgCount1);// 初始化最近会话的消息未读数
}, 1000);

// 获取未读消息
function initUnreadMsgCount1() {
	// 每次获取未读消息的时候需要吧之前追加的全部都移除掉，不然会出现重复的list和undefined的list
	var userListDiv = $("#dropdown-menuTest li");
	for (var i = 1; i < userListDiv.length; i++) {
		$(userListDiv[i]).remove();
	}
	// 全局变量也要变为0
	msgCount = 0;
	var sess;
	var sessMap = webim.MsgStore.sessMap();
	// 过滤掉业务代码不符合的session 60102(及时问诊) 50101(医院问诊)
	for ( var i in sessMap) {
		var busData = JSON
				.parse(sessMap[i]._impl.msgs[0].elems[1].content.data);
		var busCode = busData.busCode;
		if ("60102" != busCode && "50101" != busCode) {// 如果都不满足就remove
			delete sessMap[i];
		}
	}

	// 写到标签里面 提示总数
	var aI = [];
	for ( var j in sessMap) {// 遍历新消息
		aI[j] = sessMap[j];
		// 当且仅当session 有未读消息是才会在右边有列表
		if (aI[j]._impl.unread > 0) {

			// console.log(aI[j]._impl.unread);
			msgCount += aI[j]._impl.unread;// 获得消息条数
			var keyMap = JSON.parse(aI[j]._impl.msgs[0].elems[1].content.data);
			console.log(keyMap);
			// 获得消息ID
			var busCode = keyMap['busCode'];
			var consultantId = keyMap['consultantId'];
			// 需要去取用户名
			// 获取touserid 或者
			var userId = aI[j]._impl.id.substr(
					aI[j]._impl.id.lastIndexOf("_") + 1, aI[j]._impl.id.length);
			// 获取发送用户昵称
			var xName = aI[j]._impl.name;
			// 查询当前sessMap是否退费，退费则设置为已读
			// isReturns(sessMap[j]);
			var username = getUserName(userId, xName);
			// 获取当前医院ID
			var hospitalName = $("#hospitalName").val();
			var hospitalId = $("#hospital_id").val();
			var x = addList(username == 0 ? xName : username, hospitalId,
					userId, username == 0 ? xName : username, hospitalName,
					consultantId, busCode);
			// 在提示消息弹出层
			$("#dropdown-menuTest").append(x);
		}
		// 显示条数
		$('.consult_message').text(msgCount);

	}
}

// 查询当前用户是否退费结束
function isReturns(sessMap) {
	var data = JSON.parse(sessMap._impl.msgs[0].elems[1].content.data);
	var consoultID = data.consultantId;
	$.ajax({
		url : url + "/consult/isReturns",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			"idInt" : consoultID
		},
		success : function(json) {
			var status = json
			if ("5" == status) {
				webim.setAutoRead(sessMap, true, false);

				webim.setAutoRead(sessMap, false, false);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			errorTs("没有当前问题ID");
		}
	});
}

/* 拼接右边的对话框的未读消息列表 */
var newAllmessage = $("#dropdown-menuTest");
function addList(message, hospitalId, userId, toNick, hospitalName, consultId,
		buscode) {
	var liList = '<li title=' + hospitalName + ' hospitalId = ' + hospitalId
			+ ' userId = ' + userId + ' toNick = ' + toNick + ' consultId ='
			+ consultId + '  buscode =' + buscode + ' ><a href="#" >' + message
			+ '</a></li>';
	return liList;
}

function newAddList(addList) {
	newAllmessage.append(addList);
}

// 根据ID获取用户的名称放在NickName上
getUserName = function(id, xName) {
	var param = 0;
	$.ajax({
		type : "GET",
		url : baseURL + '/user/userInfo/' + id,
		dataType : "json",
		async : false,
		success : function(data) {
			console.log(typeof data);
			if (typeof data == "string") {
				param = xName;
			} else {
				if (typeof data.realName != undefined
						&& typeof data.realName != ""
						&& typeof data.realName != null) {
					param = data.realName;
				} else {
					param = data.nickName;
				}
			}
		}
	});
	return param;
}

// 获取聊天
getChatInfo = function(data) {

	var resultJson;
	$.ajax({
		url : url + "/consult/chat",
		type : "POST",
		dataType : "json",
		async : false,
		data : data,
		success : function(json) {
			resultJson = json;
			console.log("获取对聊JSON" + resultJson);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			errorTs("获取对聊失败");
		}
	});
	return resultJson;
}

// 开始解析对聊信息
parseChatData = function(chatMsgJsonData) {

	var chatInfoData = JSON.parse(chatInfo).data;
	var listInfo = JSON.parse(chatInfoData);
	/* 用户信息 */
	var userInfo = listInfo[0].accountList[0].fromAccount;
	tName = userInfo.chatId;
	userNickName = userInfo.nickName;
	var sig = userInfo.sig;
	userHeadImg = userInfo.headUrl;
	if (!userNickName) {
		userNickName = tName;
	}
	;
	if (!userHeadImg) {
		userHeadImg = "../resources/img/doctor.jpg";
	}
	;
	/* $(".tname").find("span").text(userNickName); */
	/* 收信人信息 */
	var recveInfo = listInfo[0].accountList[1].toAccount;
	selToID = recveInfo.chatId;
	recveImg = recveInfo.headUrl;
	recveNickName = recveInfo.nickName;
	if (!recveNickName) {
		recveNickName = selToID;
	}
	;
	// $('.tname span').remove();
	// $('.tname').append('<span style="color:
	// #919499;">'+recveNickName+'</span>');
	$('#messageModalLabelRemind').html("正在和【" + recveNickName + "】对话中");
	if (!recveImg) {
		recveImg = "../resources/img/2017.jpg";
	}
	;
	/* IM信息 */
	var sdkAppID = listInfo.sdkAppid;
	var accountType = listInfo.accountType;
	/* 确定使用哪个模板 */
	var color = listInfo.color;
	if (!color) {
		color = "blue";
	}
	// var colorLink = '<link rel="stylesheet"
	// href="${pageContext.request.contextPath}/resources/css/css/'+color+'.css"/>';
	// document.write(colorLink);
	/* 确定是单聊还是多聊 */
	if (listInfo[0].accountList.length == 1) {
		getFriendList(tName);
	} else {
		var allWidth = $("#conter").width();
		var leftWidth = $("#pleft").outerWidth();
		$("#pleft").remove();
		$("#conter").width(allWidth - leftWidth);

		setTimeout(function() {
			getHistory(true);
		}, 600);
	}
	;
}

$(document).on("click", "#dropdown-menuTest li", function() {
	// 用户如果退费的逻辑

	// 点击之前消除对话框里的记录
	$("#imMessageCenterBox").empty();

	// var chatMsgJsonData=JSON.parse();
	// 解析消息和获取历史消息
	var hospital_id = $(this).attr("hospitalId");
	// var hospital_id = 777777;
	var hospitalName = $(this).attr("title");
	var consultId = $(this).attr("consultId");
	var buscode_f = $(this).attr("buscode");
	var fromUserType = 3;
	userId = $(this).attr("userId");
	var user_name = $(this).attr("toNick");
	var data = {
		"fromUserId" : hospital_id,
		"fromNickName" : hospitalName,
		"fromUserType" : fromUserType,
		"toUserId" : userId,
		"toNickName" : user_name,
		"toUserType" : "2",
		"userType" : "1",
		"busCode" : buscode_f,
		"color" : colorNum,
		"consultantId" : consultId
	};

	chatInfo = getChatInfo(data);
	selToID = "101_yh_" + userId;
	busCode = buscode_f;
	consultantId = consultId;
	// alert(selToID+"==" +consultId+"==" +buscode_f);
	parseChatData();

	// 弹出框
	$('#messageRemind').modal('show');
	// 弹出对话框之后才可以把当前用户的设置为已读.开始拼接当前会话的sessionkey
	var sessMap = webim.MsgStore.sessMap();
	for ( var j in sessMap) {
		if (sessMap[j]._impl.id.indexOf(userId) > 0) {
			// 弹出框出来设置为已读
			webim.setAutoRead(sessMap[j], true, false);
			// 重新生成前端签到显示
			initUnreadMsgCount1(sessMap[j]);
		}
	}
	// 执行弹出框
})
$(".close-chat").click(function() {
	var hospital_id = $(this).attr("hospitalId");
	var sessMap = webim.MsgStore.sessMap();
	for ( var j in sessMap) {
		if (sessMap[j]._impl.id.indexOf(userId) > 0) {
			// 弹出框出来设置为已读
			webim.setAutoRead(sessMap[j], true, false);
			// 点击关闭按钮时候关闭会话
			// webim.setAutoRead(sessMap[j], false, false);
			// 重新生成前端签到显示
			initUnreadMsgCount1();
		}
	}
});
// 发送消息(文本或者表情)
function onSendMsg() {
	if (!selToID) {
		art.dialog({
			content : '未找到用户信息,数据错误！',
			icon : 'warning',
			time : 2
		});
		$("#messageBody").val('');
		return;
	}
	// 获取消息内容
	var msgtosend = tool.trimStr(document.getElementById("messageBody").value);
	var msgLen = webim.Tool.getStrBytes(msgtosend);

	if (msgtosend.length < 1) {
		art.dialog({
			content : '消息不能为空！',
			icon : 'warning',
			time : 2
		});
		$("#messageBody").val('');
		return;
	}
	var maxLen, errInfo;
	if (selType == webim.SESSION_TYPE.C2C) {
		maxLen = webim.MSG_MAX_LENGTH.C2C;
		errInfo = "消息长度超出限制(最多" + Math.round(maxLen / 3) + "汉字)";
	} else {
		maxLen = webim.MSG_MAX_LENGTH.GROUP;
		errInfo = "消息长度超出限制(最多" + Math.round(maxLen / 3) + "汉字)";
	}
	if (msgLen > maxLen) {
		return;
	}
	if (!selSess) {
		var selSess = new webim.Session(selType, selToID, selToID, Math
				.round(new Date().getTime() / 1000));
	}
	var isSend = true;// 是否为自己发送
	var seq = -1;// 消息序列，-1表示sdk自动生成，用于去重
	var random = Math.round(Math.random() * 4294967296);// 消息随机数，用于去重
	var msgTime = Math.round(new Date().getTime() / 1000);// 消息时间戳
	var subType;// 消息子类型
	if (selType == webim.SESSION_TYPE.C2C) {
		subType = webim.C2C_MSG_SUB_TYPE.COMMON;
	} else {
		subType = webim.GROUP_MSG_SUB_TYPE.COMMON;
	}
	var msg = new webim.Msg(selSess, isSend, seq, random, msgTime,
			loginInfo.identifier, subType, loginInfo.identifierNick);
	// huangzg 2017-01-10 add 添加了consultantId
	var y = '{"busCode":' + busCode + ',"consultantId":' + consultantId + '}';

	var text_obj, face_obj, tmsg, emotionIndex, emotion, restMsgIndex;
	// 解析文本和表情
	var expr = /\[[^[\]]{1,3}\]/mg;
	var emotions = msgtosend.match(expr);
	if (!emotions || emotions.length < 1) {
		text_obj = new webim.Msg.Elem.Text(msgtosend);
		msg.addText(text_obj);
	} else {

		for (var i = 0; i < emotions.length; i++) {
			tmsg = msgtosend.substring(0, msgtosend.indexOf(emotions[i]));
			if (tmsg) {
				text_obj = new webim.Msg.Elem.Text(tmsg);
				msg.addText(text_obj);
			}
			emotionIndex = webim.EmotionDataIndexs[emotions[i]];
			emotion = webim.Emotions[emotionIndex];

			if (emotion) {
				face_obj = new webim.Msg.Elem.Face(emotionIndex, emotions[i]);
				msg.addFace(face_obj);
			} else {
				text_obj = new webim.Msg.Elem.Text(emotions[i]);
				msg.addText(text_obj);
			}
			restMsgIndex = msgtosend.indexOf(emotions[i]) + emotions[i].length;
			msgtosend = msgtosend.substring(restMsgIndex);
		}
		if (msgtosend) {
			text_obj = new webim.Msg.Elem.Text(msgtosend);
			msg.addText(text_obj);
		}
	}
	var z = new webim.Msg.Elem.Custom(y);
	var i1 = "busCode=" + busCode + "";
	var i2 = "consultantId=" + consultantId + "";
	var i3 = "sender=" + tName + "";
	var iii = i1 + "&" + i2 + "&" + i3;
	var of = {
		"PushFlag" : 0,
		"Ext" : iii
	};
	msg.addCustom(z);
	msg.SyncOtherMachine = 1;
	msg.OfflinePushInfo = of;
	/*
	 * webim.sendMsg(msg, function (resp) { if (selType ==
	 * webim.SESSION_TYPE.C2C) {//私聊时，在聊天窗口手动添加一条发的消息，群聊时，长轮询接口会返回自己发的消息
	 * addMsg(msg); } webim.Tool.setCookie("tmpmsg_" + selToID, '', 0);
	 * $("#messageBody").val(''); }, function (err) { alert(err.ErrorInfo);
	 * $("#messageBody").val(''); });
	 */
	/*
	 * $.post(url+"/consult/send_message",msg,function(result){
	 * console.info("----------------------------------------------------------------------------------------------------------"+result);
	 * alert(result+"++++++++++++++"); });
	 */
	$.ajax({
		url : url + "/consult/send_message",
		type : "POST",
		dataType : "json",
		data : {
			"sender" : tName,
			"recevrer" : selToID,
			"busCode" : busCode,
			"consultantId" : consultantId,
			"content" : msgtosend,
			"type" : 1
		},
		success : function(json) {
			console.log("发送返回=======================================================>");
			console.log(json);
			var data;
			try {
				data = JSON.parse(json);
				console.log("data__" + typeof data + "-----");
				console.log(data);
			} catch (e) {
				console.log(e);
				throw "返回的数据格式错误";
			}
			if (data.msg == 1) {
				var iNow = data.data;
				var messageId = iNow.msgId;
				console.log(iNow)
				console.log(messageId);
				addMsg(msg, messageId);
			}

		}
	});
}

/* 查询历史消息,查询更多历史消息 */
function getHistory(oldBoolean, msgId, pageNo, pageSize) {
	if (!msgId) {
		msgId = "";
	}
	if (!pageNo) {
		pageNo = 1;
	}
	if (!pageSize) {
		pageSize = 20;
	}
	// huangzg 2017-01-10 add 添加了consultantId
	$.ajax({
		url : url + "/consult/sel_message",
		type : "POST",
		dataType : "json",
		data : {
			"sender" : tName,
			"recevrer" : selToID,
			"busCode" : busCode,
			"startTime" : "",
			"endTime" : "",
			"pageNo" : pageNo,
			"pageSize" : pageSize,
			"msgId" : msgId,
			"consultantId" : consultantId
		},
		success : function(json) {
			var json = JSON.parse(json);
			if (json.msg == 1) {
				var xaw = json.data.dataList;
				if (xaw.length == 0) {
					if (oldBoolean) {
						$("#imMessageCenterBox").empty();
					} else {
						errorTs("没有更多历史消息!");
					}
				} else {
					// return;
					$("#imMessageCenterBox").append(messageMore);
					for (var i = 0; i < xaw.length; i++) {
						showHistory(xaw[i],json);
						$("#imMessageCenterBox").find(".chag").remove();
						$("#imMessageCenterBox").children().eq(0).before(messageMore);
					}
				}
			} else {
				errorTs(json.msgbox);
			}
			;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			errorTs("历史消息请求错误!")
		}
	});

	// 弹出框出来的时候才可以调用这个滚动条

}
/*
 * niceScrollInit(); //滚动条初始化 function niceScrollInit(){
 * console.log("niceScroll执行"); $("#imMessageCenterBox,#pleft").niceScroll({
 * railpadding: { top:0, right: 1, left: 0, bottom:0 }, cursorborder:"",
 * cursoropacitymin: 0.2, cursoropacitymax: 0.2, cursorcolor:"#000000", zindex:
 * "99", cursorwidth: "5px", autohidemode:false }); }
 */

/* 查询历史消息,查询更多历史消息 */
function showHistory(msg,json) {
	if (msg.msgType == "TIMImageElem") {
		if (msg.sendChatId == tName) {
			addImgOld(msg, false);
		} else {
			addImgOld(msg, true);
		}
	} else {
			if (msg.msgType == "TIMSoundElem") {
				oldConvertSoundMsgToHtml(msg);
	
			}
			else{
				if (msg.sendChatId == tName) {
					addMsgOld2(msg,json);
				} else {
					addMsgOld1(msg);
				}
			}
		}
	}
//}

function checkHistory(time){
	var riegn= '<p id="optTime" class="timeM">'+time+'<br></p>';
	return riegn;
}
/* 增加一条消息 */
function addMsg(msg, msgId) {
	var isSelfSend, fromAccount, sessType, subType, time, messageBody1;
	time = webim.Tool.formatTimeStamp(msg.getTime());
	messageBody1 = $("#messageBody").val();
	fromAccount = msg.getFromAccount();
	if (!fromAccount) {
		fromAccount = '';
	}
	sessType = msg.getSession().type();
	subType = msg.getSubType();
	newMessage(rightMessage({
		msgId : msgId,
		time : time,
		name : fromAccount,
		messageBody : messageBody1
	}));
	$("#messageBody").val('');
}
/* 接收到一条消息 */
function addMsg1(msg) {
	var isSelfSend, fromAccount, sessType, subType, time, messageBody1;
	time = webim.Tool.formatTimeStamp(msg.getTime());
	messageBody1 = msg.elems[0].content.text;
	var datas = msg.elems[1].content.data;
	var msgId = JSON.parse(datas).sendMsgId;
	/* fromAccount = msg.fromAccountNick; */
	fromAccount = recveNickName;
	if (!fromAccount) {
		fromAccount = '';
	}
	sessType = msg.getSession().type();
	subType = msg.getSubType();
	var msgId = JSON.parse(datas).sendMsgId;
	newMessage(leftMessage({
		msgId :msgId,
		time : time,
		niceName : fromAccount,
		messageBody : messageBody1
	}));
}
/* 接收到一条消息 */
function addMsgOld1(msg) {
	var time = msg.sendTime;
	var niceName = recveNickName;
	var messageBody1 = msg.msgContent;
	var msgId = msg.msgId;
	oldMessage(leftMessage({
		time : time,
		niceName : niceName,
		msgId : msgId,
		messageBody : messageBody1
	}));

}
function addMsg2(msg) {
	var isSelfSend, fromAccount, sessType, subType, time, messageBody1;
	time = msg.sendTime;
	messageBody1 = msg.msgContent;
	fromAccount = msg.sendChatId;
	if (!fromAccount) {
		fromAccount = '';
	}
	sessType = msg.getSession().type();
	subType = msg.getSubType();
	newMessage(rightMessage({
		time : time,
		name : fromAccount,
		messageBody : messageBody1
	}));

}
//添加历史消息的方法
function addMsgOld2(msg,json) {
	console.info(msg.msgType+"**************"+msg.msgContent);
	var s = msg.msgContent;
	var tempStr = msg.msgContent ;
	temp = tempStr.slice(57,70);
	console.info(temp+"******************");
	if(msg.msgType){
		if(msg.msgType == 15){
			oldMessage(checkHistoryMsg(tempStr,temp))
			return;
		}
	}
	var time = msg.sendTime;
	var niceName = recveNickName;
	var messageBody1 = msg.msgContent;
	var msgId = msg.msgId;
	oldMessage(rightMessage({
		time : time,
		msgId : msgId,
		messageBody : messageBody1
	}));

}
function checkHistoryMsg(tempStr,temp){
	/*var message = '<div class="rightM" id="'
		+ opt.msgId
		+ '"><p id="optTime" class="timeM">'
		+ opt.time
		+ '</p><div class="centerM"><span class="nameM" title="'
		+ userNickName
		+ '">'
		+ userNickName
		+ '</span><p class="pointM"></p><i class="fr"><img src="'
		+ userHeadImg
		+ '"></i><a href="javascript:void(0);" onclick=recallMsg($(this))>撤回消息</a><p id="optMessageBody" class="messageB fr">'
		+ opt.messageBody + '</p></div></div>';*/
		var message = '<div class="rightM" id="'+ temp+ '"><p id="optTime" class="timeM">'+"您撤回了一条消息"+'</p><div class="centerM"></div></div>';
//		$("#" + temp + "").replaceWith('<p id="optTime" class="timeM">'+"您撤回了一条消息");
		console.info(tempStr+"==========================================="+temp);
		return message;
}

/* 接收到一条图片消息 */
function addImg(msg, boo) {
//	alert("接受一条图片消息"+msg.elems[1].content.data);
	var a = msg.elems[1].content.data;
	var sendMsgId = JSON.parse(a).sendMsgId;//发送者的图片ID
//	alert(b.sendMsgId);
	var isSelfSend, fromAccount, sessType, subType, time, messageBody1;
	time = webim.Tool.formatTimeStamp(msg.getTime());
	messageBody1 = msg.elems[0].content.text;
	fromAccount = msg.getFromAccount();
	if (!fromAccount) {
		fromAccount = '';
	}
	var imgArray = msg.elems[0].content.ImageInfoArray;
	var src = imgArray[2].url;
	var width = imgArray[2].width;
	var height = imgArray[2].height;
	var src1 = imgArray[0].url;
	var width1 = imgArray[0].width;
	var height1 = imgArray[0].height;
	if (parseInt(width) > 380) {
		height = height * (380 / parseInt(width));
		width = 380;
	}
	if (boo) {
		newMessage(leftImg({
			msgId:sendMsgId,
			time : time,
			name : fromAccount,
			src : src,
			width : width,
			height : height,
			src1 : src1,
			width1 : width1,
			height1 : height1
		}));
	} else {
		newMessage(rightImg({
			time : time,
			name : fromAccount,
			src : src,
			width : width,
			height : height,
			src1 : src1,
			width1 : width1,
			height1 : height1
		}));
	}

}

/* 接收到一条图片消息 */
function addImgOld(msg, boo) {
	var time = msg.sendTime;
	var imgArray = JSON.parse(msg.msgContent);
	var src = imgArray[1].thumbnail;
	var width = imgArray[1].width;
	var height = imgArray[1].height;
	var src1 = imgArray[0].original;
	var width1 = imgArray[0].width;
	var height1 = imgArray[0].height;
	var msgId = msg.msgId;
	if (parseInt(width) > 380) {
		height = height * (380 / parseInt(width));
		width = 380;
	}
	if (boo) {
		oldMessage(leftImg({
			time : time,
			src : src,
			msgId : msgId,
			width : width,
			height : height,
			src1 : src1,
			width1 : width1,
			height1 : height1
		}));
	} else {
		oldMessage(rightImg({
			time : time,
			src : src,
			msgId : msgId,
			width : width,
			height : height,
			src1 : src1,
			width1 : width1,
			height1 : height1
		}));
	}

}

/* new end */
// 上传图片进度条回调函数
// loadedSize-已上传字节数
// totalSize-图片总字节数
function onProgressCallBack(loadedSize, totalSize) {
	var progress = document.getElementById('upd_progress');// 上传图片进度条
	// progress.value = (loadedSize / totalSize) * 100;
}

// 上传图片
function uploadPic() {
	var uploadFiles = document.getElementById('upload3');
	var file = uploadFiles.files[0];

	var businessType;// 业务类型，1-发群图片，2-向好友发图片
	if (selType == webim.SESSION_TYPE.C2C) {// 向好友发图片
		businessType = webim.UPLOAD_PIC_BUSSINESS_TYPE.C2C_MSG;
	} else if (selType == webim.SESSION_TYPE.GROUP) {// 发群图片
		businessType = webim.UPLOAD_PIC_BUSSINESS_TYPE.GROUP_MSG;
	}
	// 封装上传图片请求
	var opt = {
		'file' : file, // 图片对象
		'onProgressCallBack' : onProgressCallBack, // 上传图片进度条回调函数
		// 'abortButton': document.getElementById('upd_abort'), //停止上传图片按钮
		'From_Account' : loginInfo.identifier, // 发送者帐号
		'To_Account' : selToID, // 接收者
		'businessType' : businessType
	// 业务类型
	};
	// 上传图片
	/*
	 * webim.uploadPic(opt, function (resp) { //上传成功发送图片 sendPic(resp);
	 * closeUpload(); }, function (err) { alert(err.ErrorInfo); } );
	 */
	var data = {
		"minWidth" : 80,
		"minHeight" : 80
	};
	/** 具体的异步上传图片实现* */
	$.ajaxFileUpload({
		url : url + '/consult/upload', // 需要链接到服务器地址
		secureuri : false,
		data : data,// 携带参数
		async : false,
		fileElementId : 'upload3', // 文件选择框的id属性
		dataType : 'json', // 服务器返回的格式
		success : function(data, status) // 相当于java中try语句块的用法
		{
			if (data == "false1") {
				alert("请上传正确的图片格式");
				return;
			}
			if (data == "false2") {
				art.dialog({
			        content: "图片不能小于80*80",
			        icon: 'warning',
			        time: 2			
				});
				return;
			}
			sendPic(data.responseText);
		},
		error : function(data, status, e) // 相当于java中catch语句块的用法
		{
			sendPic(data.responseText);
		}
	});
}

// 发送图片消息
function sendPic(images) {
	if (!selToID) {
		art.dialog({
			content : '未找到用户信息,数据错误！',
			icon : 'warning',
			time : 2
		});
		return;
	}

	if (!selSess) {
		selSess = new webim.Session(selType, selToID, selToID, Math
				.round(new Date().getTime() / 1000));
	}
	var msg = new webim.Msg(selSess, true, -1, -1, -1, loginInfo.identifier, 0,
			loginInfo.identifierNick);
	var images_obj = new webim.Msg.Elem.Images(images.File_UUID);
	for ( var i in images.URL_INFO) {
		var img = images.URL_INFO[i];
		var newImg;
		var type;
		switch (img.PIC_TYPE) {
		case 1:// 原图
			type = 1;// 原图
			break;
		case 2:// 小图（缩略图）
			type = 3;// 小图
			break;
		case 4:// 大图
			type = 2;// 大图
			break;
		}

		newImg = new webim.Msg.Elem.Images.Image(type, img.PIC_Size,
				img.PIC_Width, img.PIC_Height, img.DownUrl);
		images_obj.addImage(newImg);
	}
	msg.addImage(images_obj);
	// huangzg 2017-01-10 add 添加了consultantId
	var y = '{"busCode":' + busCode + ',"consultantId":' + consultantId + '}';
	var z = new webim.Msg.Elem.Custom(y);
	msg.addCustom(z);
	$.ajax({
		url : url + "/consult/send_message",
		type : "POST",
		dataType : "json",
		data : {
			"sender" : tName,
			"recevrer" : selToID,
			"busCode" : busCode,
			"consultantId" : consultantId,
			"content" : images,
			"type" : 2
		},
		success : function(json) {
			try {
				var sObj = JSON.parse(json);
				console.info(sObj + " ==================================>>");
				console.log(sObj);			
				} catch (err) {
				throw "获取数据格式不对";
			}
			if (sObj.msg == 1) {
				var test = JSON.parse(sObj.data.msgContent);
				console.info(sObj.data
						+ " ==================================>>");
				var str = sObj.data.sendTime + "";
				var msgId = sObj.data.msgId;
				var time = webim.Tool.formatTimeStamp(str.substring(0, 10));
				var src = test[1].thumbnail;
				var width = test[1].width + "px";
				var height = test[1].height + "px";
				var src1 = test[0].original;
				var width1 = test[0].width;
				var height1 = test[0].height;
				var fromAccount = userNickName;
				if (parseInt(width) > 380) {
					height = height * (380 / parseInt(width));
					width = 380;
				}
				newMessage(rightImg({
					msgId:msgId,
					time : time,
					userNickName : fromAccount,
					src : src,
					width : width,
					height : height,
					src1 : src1,
					width1 : width1,
					height1 : height1
				}));
			}

		}
	});
	// 调用发送图片消息接口
	/*
	 * webim.sendMsg(msg, function (resp) {
	 * if(selType==webim.SESSION_TYPE.C2C){//私聊时，在聊天窗口手动添加一条发的消息，群聊时，长轮询接口会返回自己发的消息
	 * var fromAccount = msg.getFromAccount(); if (!fromAccount) { fromAccount =
	 * ''; } var time=webim.Tool.formatTimeStamp(msg.getTime()); var
	 * imgArray=msg.elems[0].content.ImageInfoArray; var src=imgArray[0].url;
	 * var width=imgArray[0].width+"px"; var height=imgArray[0].height+"px"; var
	 * src1=imgArray[2].url; var width1=imgArray[2].width; var
	 * height1=imgArray[2].height; if(parseInt(width)>380){ width = 380; }
	 * newMessage(rightImg({ time:time, name:fromAccount, src:src, width:width,
	 * height:height, src1:src1, width1:width1, height1:height1 })); } },
	 * function (err) { alert(err.ErrorInfo); });
	 */
}

// 检查文件类型和大小
function checkFile(obj, fileSize) {
	var picExts = 'jpg|jpeg|png|bmp|gif|webp';
	var photoExt = obj.value.substr(obj.value.lastIndexOf(".") + 1)
			.toLowerCase();// 获得文件后缀名
	var pos = picExts.indexOf(photoExt);
	if (pos < 0) {
		alert("您选中的文件不是图片，请重新选择");
		return false;
	}
	fileSize = Math.round(fileSize / 1024 * 100) / 100; // 单位为KB
	if (fileSize > 30 * 1024) {
		alert("您选择的图片大小超过限制(最大为30M)，请重新选择");
		return false;
	}
	return true;
}

/* 公用发送消息 */
/* rightMessage */
function rightMessage(opt) {
	/*var message = '<div class="rightM" id="'+ opt.msgId+ '"><p id="optTime" class="timeM">'
	+ opt.time+ '</p><div class="centerM"><span class="nameM" title="'+ userNickName	+ '">'
	+ userNickName+ '</span><p class="pointM"></p><i class="fr"><img src="'+
	userHeadImg+ '"></i><p id="optMessageBody" class="messageB optMessageBody1 fr">'+
	opt.messageBody 
	+ '</p><div class="menuMessageCh" style="display: none;" >'+'<span class="photoMessage"></span>'+
	'<a href="javascript:void(0);"  onclick=recallMsg($(this)) ">撤回</a></div></div></div>';*/
	var message = '<div class="rightM" id="'+ opt.msgId+ '"><p id="optTime" class="timeM">'
	+ opt.time+ '</p><div class="centerM"><span class="nameM" title="'+ userNickName	+ '">'
	+ userNickName+ '</span><p class="pointM"></p><i class="fr"><img src="'+
	userHeadImg+ '"></i><p id="optMessageBody" class="messageB optMessageBody1 fr">'+
	opt.messageBody 
	+ '</p><div class="menuMessageCh" style="display: none;" >'+'<span class="photoMessage"></span>'+
	'<a href="javascript:void(0);"  onclick=recallMsg($(this)) ">撤回</a></div></div></div>';
	return message;
}

//鼠标右击文字拦截
$(document).on("contextmenu",".rightM .optMessageBody1",function(ev){
/*	var oUl=document.getElementById('menu');*/
	var oEvent=ev||event;	
//	oUl.style.top='50px';
//	oUl.style.left='50px';
	/*oUl.style.display='block';
	$("#menu").show();*/
	$(this).parents(".rightM").siblings().find(".menuMessageCh").hide();
	var b = $(this).nextAll(".menuMessageCh").show();
	oEvent.preventDefault();
	//点击屏幕其他地方隐藏div
	$(document).bind('click', function(e) {  
        var e = e || window.event; //浏览器兼容性   
        var elem = e.target || e.srcElement;  
        while (elem) { //循环判断至跟节点，防止点击的是div子元素   
            if (elem.class && elem.class == 'menuMessageCh') {  
                return;  
            }  
            elem = elem.parentNode;  
        }  
        b.hide();
      }); 
    return false;
})
//图片拦截
$(document).on("contextmenu",".rightM .imgMessageBody1",function(ev){
/*	var oUl=document.getElementById('menu');*/
	var oEvent=ev||event;	
//	oUl.style.top='50px';
//	oUl.style.left='50px';
	/*oUl.style.display='block';
	$("#menu").show();*/
	$(this).parents(".rightM").siblings().find(".menuMessageCh").hide();
	var b = $(this).nextAll(".menuMessageCh").show();
	oEvent.preventDefault();
	
	//点击屏幕其他地方隐藏div
	$(document).bind('click', function(e) {  
        var e = e || window.event; //浏览器兼容性   
        var elem = e.target || e.srcElement;  
        while (elem) { //循环判断至跟节点，防止点击的是div子元素   
            if (elem.class && elem.class == 'menuMessageCh') {  
                return;  
            }  
            elem = elem.parentNode;  
        }  
       b.hide();
      }); 
    return false;
})
//弹出框
//window.onload=function(){
//      var oUl=document.getElementById('menu');
//        document.oncontextmenu=function(ev){
//          var oEvent=ev||event;
//          oUl.style.top='300px';
//          oUl.style.left='300px';
//          oUl.style.display='block';
//          return false;
//        }
//        document.onclick=function(){
//          oUl.style.display='none';
//        }
//    }; 


// 撤回消息
function recallMsg(_this) {
	// alert(tName+" "+selToID+" "+busCode+" "+consultantId+" ");
	var optMsgId = _this.parent().parent().parent().attr("id");
	var optMsgTime = _this.parent().parent().val(".class");
//	alert(optMsgId + ">>>>>>>>>>>>>>>>>>>>>>>>");
//	alert(optMsgTime+"=======");
	console.info(optMsgId+">>>>>>>>>>>>>>>>>>>>>>>");
	$.ajax({
		url : url + "/consult/recall_message",
		type : "POST",
		dataType : "json",
		data : {
			"content" : optMsgId,// 消息id
			"sender" : tName,// 发送者IM账号
			"receiver" : selToID,// 接受者IM账号
			"busCode" : busCode,// 业务代码
			"consultantId" : consultantId,// 服务ID/问题ID
			"type" : 15// 撤回消息类型
		},
		success : function(data) {
			if( window.JSON.parse(data).msg == 1){
				//表示该条消息可以撤回
				var recallMsgId = window.JSON.parse(data).data.backMsgId;
				var currentTime = new Date(); 
				var hours = currentTime.getHours();
				var minutes = currentTime.getMinutes(); 
				var seconds = currentTime.getSeconds();
				var recallTime = hours +":"+ minutes +":"+ seconds;//消息撤回的时间
				$("#" + recallMsgId + "").replaceWith('<p id="optTime" class="timeM">'+recallTime+'</br>'+'<p id="optTime" class="timeM">'+"您撤回了一条消息");
//				alert("撤回消息的ID>>>>>>>" + recallMsgId+ ">>>>>>>>");
				
				console.info("撤回消息的ID>>>>>>>" + recallMsgId+ ">>>>>>>>");
				return;
			}
			if (window.JSON.parse(data).msg == 0) {
		        art.dialog({
				        content: window.JSON.parse(data).msgbox,
				        icon: 'warning',
				        time: 2			
					});
		        $("#messageBody").val('');
//		        alert(">>>>>>>>>>>>>" + window.JSON.parse(data).msgbox + ">>>>>>>>>>>>>>>>");
		        console.info(">>>>>>>>>>>>>" + window.JSON.parse(data).msgbox + ">>>>>>>>>>>>>>>>");
		        return;
		    }
		}
	});
}
function leftMessage(opt) {
	//显示对方撤回的消息
	var a = opt.messageBody;
	if(a.indexOf("{") > -1){
		var jsonObj=JSON.parse(a);
	 
		var optMsgType = jsonObj.type;
		if(optMsgType == 15 && opt.niceName !== userNickName){
//			var message = '<div class="rightM" id="'+ opt.msgId+ '"><p id="optTime" class="timeM">'+"\""+opt.niceName+"\""+"撤回了一条消息"+'</p><div class="centerM"></div></div>';
			var message = '<div class="rightM" id="'+ jsonObj.data.datas.backMsgId + '"><p id="optTime" class="timeM">'+"对方撤回了一条消息"+'</p><div class="centerM"></div></div>';
			return message;
		}
	}
	//显示正常的数据
	var message = '<div class="leftM" id="' + opt.msgId + '"><p class="timeM">'
		+ opt.time + '</p><div class="centerM"><span class="nameM" title="'
		+ opt.niceName + '">' + opt.niceName
		+ '</span><p class="pointM"></p><i class="fl"><img src="'
		+ recveImg + '"></i><p class="messageB fl">' + opt.messageBody
		+ '</p></div></div>';
	return message;
//	if(optMsgType == 15 && opt.niceName !== userNickName){
//		var message = '<div class="rightM" id="'+ opt.msgId+ '"><p id="optTime" class="timeM">'+"\""+opt.niceName+"\""+"撤回了一条消息"+'</p><div class="centerM"></div></div>';
//		return message;
//	}
//	
//	alert(opt.msgId);
//	var message = '<div class="leftM" id="' + opt.msgId + '"><p class="timeM">'
//			+ opt.time + '</p><div class="centerM"><span class="nameM" title="'
//			+ opt.niceName + '">' + opt.niceName
//			+ '</span><p class="pointM"></p><i class="fl"><img src="'
//			+ recveImg + '"></i><p class="messageB fl">' + opt.messageBody
//			+ '</p></div></div>';
//	return message;
}

function rightImg(opt) {	
	var message = '<div class="rightM" id="'+ opt.msgId	+ '"><p class="timeM">'+ opt.time+ '</p><div class="centerM"><span class="nameM" title="'+ userNickName	+ '">'+ userNickName+ '</span><p class="pointM"></p><i class="fr"><img src="'+ userHeadImg+ '"></i><p class="messageB imgMessageBody1  fr"><img class="showImgIm" data-src="'+ opt.src1 + '" data-width="' + opt.width1 + '" data-height="'+ opt.height1 + '" src="' + opt.src + '" style="width: '+ opt.width + 'px;height: ' + opt.height + 'px;"></p><div class="menuMessageCh" style="display: none" ><span class="photoMessage"></span><a href="javascript:void(0);" onclick=recallMsg($(this))>撤回</a></div></div></div>';
	return message;
}
function leftImg(opt) {
	var message = '<div class="leftM" id="' + opt.msgId + '"><p class="timeM">'
			+ opt.time + '</p><div class="centerM"><span class="nameM" title="'
			+ recveNickName + '">' + recveNickName
			+ '</span><p class="pointM"></p><i class="fl"><img src="'
			+ recveImg
			+ '"></i><p class="messageB fl"><img class="showImgIm" data-src="'
			+ opt.src1 + '" data-width="' + opt.width1 + '" data-height="'
			+ opt.height1 + '" src="' + opt.src + '" style="width: '
			+ opt.width + 'px;height: ' + opt.height + 'px;"></p></div></div>';
	return message;
}
//
function leftAudio(opt) {
	var message = '<div class="leftM" id="'
			+ opt.msgId
			+ '"><p class="timeM">'
			+ opt.time
			+ '</p><div class="centerM"><span class="nameM" title="'
			+ opt.niceName
			+ '">'
			+ opt.niceName
			+ '</span><p class="pointM"></p><i class="fl"><img src="'
			+ recveImg
			+ '"></i><p class="messageB fl"><i id="audio_'
			+ opt.audioId
			+ '" class="fl audioI"><img src="'
			+ audioIcon
			+ '"/></i><audio class="aAudio" style="display:none;" id="'
			+ opt.audioId
			+ '" src="'
			+ opt.src
			+ '" controls="controls"></audio><span class="fl" style="margin:2px 0 0 8px;">'
			+ opt.size + '</span><i class="dataYl" style="display:none;">'
			+ opt.size + '</i></p></div></div>';
	return message;
}

function newMessage(message) {
	messageAppend(messageAll, message);
	scrollBottom();
};
function oldMessage(message) {
	messageBefore(messageAll, message);
};
function messageBefore(messageAll, message) {
	messageAll.children().eq(0).before(message);
};

function messageAppend(messageAll, message) {
	messageAll.append(message);
};

/* 左侧加载好友 */
function leftFriend(opt) {
	var html = '<li id="' + opt.id + '"><img class="fl" src="' + opt.src
			+ '" alt=""><span class="imUserName fl">' + opt.userName
			+ '</span><p class="xdd fl">' + opt.wdMessage + '</p></li>';
	return html;
}
/* 发送按钮 */
var sendBotton = '<a id="sendMessage" href="javascript:;"  class="sendMessageA noSend" onclick="onSendMsg()">发送</a>';
/* 不能发送按钮 */
var noSendBotton = '<a class="sendMessageBotton nosend" href="javascript:;">发送</a>';
/* 没有好友文字 */
var noFriend = '<p class="noFriend">您暂时没有好友不能聊天</p>';
/* 查看更多按钮 */
var messageMore = '<div class="chag" style="text-align: center;text-decoration: underline;"><a id="moreOldMessage" href="javascript:;">查看记录</a></div>'
/* 滚动到底部 */
function scrollBottom() {
	setTimeout(function() {
		var num = messageAll[0].scrollHeight - messageAll.height();
		messageAll.scrollTop(num);
	}, 300);
};

/* 回车发送消息 */
function onTextareaKeyDown(event) {
	var event = arguments.callee.caller.arguments[0] || window.event;// 消除浏览器差异
	if (event.keyCode == 13) {
		setTimeout(function() {
			onSendMsg();
		}, 0);
	}
};
/* 关闭图片发送框 */
function closeUpload() {
	$("#uploadWindow").hide();
	setTimeout(function() {
		$("#upload3").val("");
	}, 1500);
};
/* 上传图片验证 */
function uploadImg() {
	var text = $("#upload3").val();
	if (text.length == 0) {
		art.dialog({
			content : '请上传图片！',
			icon : 'warning',
			time : 1.5
		});
		return;
	} else {
		if (!reIm.test(text)) {
			art.dialog({
				content : '上传文件必须是图片！',
				icon : 'warning',
				time : 1.5
			});
			return;
		} else {
			uploadPic();
			closeUpload();
		}
	}
};
/* 获取好友列表 */
function getFriendList(name, pageNo, pageSize) {
	if (!pageNo) {
		pageNo = "";
	}
	if (!pageSize) {
		pageSize = "";
	}
	$.ajax({
		url : url + "/base/friend_get_all",
		type : "POST",
		dataType : "json",
		data : {
			"busCode" : busCode,
			"imAccount" : name,
			"pageNo" : pageNo,
			"pageSize" : pageSize
		},
		success : function(json) {
			if (json.msg == 1) {
				var aIow = json.data.conts;
				if (aIow.length == 0) {
					$(".sendMessageInput").find(".sendMessageBotton").remove();
					$(".sendMessageInput").append(noSendBotton);
					$(".imga").remove();
					$("#pleft").append(noFriend);
					$("#messageBody").attr("disabled", "true");
					return;
				}
				var html = "";
				for (var i = 0; i < aIow.length; i++) {
					var src = aIow[i].headUrl;
					var id = "im_" + aIow[i].friendAccount;
					var userName = aIow[i].nickName;
					if (!userName) {
						userName = aIow[i].friendAccount;
					}
					if (!src) {
						src = "../resources/img/2017.jpg";
					}
					var wdMessage = aIow[i].noReadCount;
					html += leftFriend({
						id : id,
						src : src,
						userName : userName,
						wdMessage : wdMessage
					});
				}
				;
				$("#friendList").append(html);
				selToID = $("#friendList").children().eq(0).attr("id")
						.substring(3);
				$("#im_" + selToID).addClass("active");
				getHistory(true);
			} else {
				errorTs("获取好友列表失败");
				/*
				 * errorQt(); errorDefault();
				 */
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			errorTs("获取好友列表失败");
			/*
			 * errorQt(); errorDefault();
			 */
		}
	});
};

/* 获取信息错误 */
function errorDefault() {
	$(".userMessage").hide();
	$(".userNoMessage").show();
	$("#messageMore").hide();
	$(".imga").hide();
}
/* 获取信息成功 */
function successDefault() {
	$(".userMessage").show();
	$(".userNoMessage").hide();
	$("#messageMore").show();
	$(".imga").show();
}
/* 其它情况错误 */
function errorQt() {
	$("#messageBody").attr("disabled", true);
	$("#sendMessage").addClass("noSend");
	$(".sendMessage").css("background-color", "#ebebe4");
	$("#messageMore").hide();
	$(".imga").hide();
}
/* 其它情况成功 */
function successQt() {
	$("#messageBody").attr("disabled", false);
	$("#sendMessage").removeClass("noSend");
	$(".sendMessage").css("background-color", "#ffffff");
	$("#messageMore").show();
	$(".imga").show();
}

/* 发送消息之后变为默认样式 */
function defaultSend(text) {
	var timeA = tool.time().substring(10, 16);
	$("#im_" + selToID).find(".titleBottom").text(text);
	$("#im_" + selToID).find(".ttTime").text(timeA);
	$("#messageBody").val('');
}
/* 弹框模型 */
function errorTs(message) {
	art.dialog({
		content : message,
		icon : 'warning',
		time : 1.5
	});
};

;
function clog(obj) {
	console.log(obj);
};
// /////////////////////////////////////////

// 调用登陆方法，js执行顺序
independentModeLogin1();
/* 登录 */
function independentModeLogin1(name, pas) {
	// 添加IM用户，暂时不需要添加用户
	// var addAccount=JSON.parse(addIMAccount());
	// 设置nickName
	// resp.identifierNick=addAccount
	// 获取登陆信息
	var accountInfo = JSON.parse(getLoginInfo());
	loginInfo.appIDAt3rd = accountInfo.sdkappid;
	loginInfo.accountType = accountInfo.accountType;
	loginInfo.identifier = accountInfo.userid;
	loginInfo.userSig = accountInfo.sig;
	loginInfo.sdkAppID = accountInfo.sdkappid;
	console.log(loginInfo);
	options = {};
	options.isLogOn = "false";
	// 如果已经登陆，则不能重复登陆
	webimLogin();
	console.log("登陆成功==============================>")
}


/*
 * $('#messageRemind').on('show.bs.modal', function () { setTimeout(function(){
 * niceScrollInit(); },800); })
 */