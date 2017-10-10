/*全局变量*/
 var messageAll=$("#imMessageBox");
 var reIm=/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/;
/*登录*/
function independentModeLogin1(name,pas) {
    loginInfo.identifier = name;
    loginInfo.userSig = pas;
    webimLogin();
}
function webimLogin() {
	console.log("denglu");
    webim.login(
            loginInfo, listeners, options,
            function (resp) {
                loginInfo.identifierNick = resp.identifierNick;//设置当前用户昵称
            },
            function (err) {
                alert(err.ErrorInfo);
            }
    );
    setTimeout(function(){
    	   getLastC2CHistoryMsgs(getHistoryMsgCallback,
                    function (err) {
                        alert(err.ErrorInfo);
                    });
    },600);
}
//发送消息(文本或者表情)
function onSendMsg() {
    if (!selToID) {
        art.dialog({
		        	content: '未找到用户信息,数据错误！',
		            icon: 'warning',
		            time: 2			
			});
        $("#messageBody").val('');
        return;
    }
    //获取消息内容
    var msgtosend = document.getElementById("messageBody").value;
    var msgLen = webim.Tool.getStrBytes(msgtosend);

    if (msgtosend.length < 1) {
         art.dialog({
		        	content: '消息不能为空！',
		            icon: 'warning',
		            time: 2			
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
        alert(errInfo);
        return;
    }
    if (!selSess) {
      var  selSess = new webim.Session(selType, selToID, selToID, friendHeadUrl, Math.round(new Date().getTime() / 1000));
    }
    var isSend = true;//是否为自己发送
    var seq = -1;//消息序列，-1表示sdk自动生成，用于去重
    var random = Math.round(Math.random() * 4294967296);//消息随机数，用于去重
    var msgTime = Math.round(new Date().getTime() / 1000);//消息时间戳
    var subType;//消息子类型
    if (selType == webim.SESSION_TYPE.C2C) {
        subType = webim.C2C_MSG_SUB_TYPE.COMMON;
    } else {
        subType = webim.GROUP_MSG_SUB_TYPE.COMMON;
    }
        console.log("=============");
    console.log(selSess);
    var msg = new webim.Msg(selSess, isSend, seq, random, msgTime, loginInfo.identifier, subType, loginInfo.identifierNick);
	 var y="'Data': '{\'bus_type\': 1,\'datetime\': \'2016-10-24 10:40:01\'}'";

    var text_obj, face_obj, tmsg, emotionIndex, emotion, restMsgIndex;
    //解析文本和表情
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
            text_obj = new w
            ebim.Msg.Elem.Text(msgtosend);
            msg.addText(text_obj);
        }
    }
    var z = new webim.Msg.Elem.Custom(y);
    msg.addCustom(z);
    webim.sendMsg(msg, function (resp) {
        if (selType == webim.SESSION_TYPE.C2C) {//私聊时，在聊天窗口手动添加一条发的消息，群聊时，长轮询接口会返回自己发的消息
            addMsg(msg);
        }
        webim.Tool.setCookie("tmpmsg_" + selToID, '', 0);
        $("#messageBody").val('');
    }, function (err) {
        alert(err.ErrorInfo);
        $("#messageBody").val('');
    });
}
/*增加一条消息*/
function addMsg(msg) {
    var isSelfSend, fromAccount, sessType, subType,time,messageBody1;
    time=webim.Tool.formatTimeStamp(msg.getTime());
		messageBody1=$("#messageBody").val();
    fromAccount = msg.getFromAccount();
    if (!fromAccount) {
        fromAccount = '';
    }
    sessType = msg.getSession().type();
    subType = msg.getSubType();
	newMessage(rightMessage({
    	time:time,
    	name:fromAccount,
    	messageBody:messageBody1
    }));

}
/*接收到一条消息*/
function addMsg1(msg) {
	console.log("?????????????????????????");
	console.log(msg);
    var isSelfSend, fromAccount, sessType, subType,time,messageBody1;
    time=webim.Tool.formatTimeStamp(msg.getTime());
	messageBody1=msg.elems[0].content.text;
    fromAccount = msg.getFromAccount();
    if (!fromAccount) {
        fromAccount = '';
    }
    sessType = msg.getSession().type();
    subType = msg.getSubType();
	newMessage(leftMessage({
    	time:time,
    	name:fromAccount,
    	messageBody:messageBody1
    }));

}
/*接收到一条消息*/
function addMsgOld1(msg) {
	console.log("?????????????????????????");
	console.log(msg);
    var isSelfSend, fromAccount, sessType, subType,time,messageBody1;
    time=webim.Tool.formatTimeStamp(msg.getTime());
	messageBody1=msg.elems[0].content.text;
    fromAccount = msg.getFromAccount();
    if (!fromAccount) {
        fromAccount = '';
    }
    sessType = msg.getSession().type();
    subType = msg.getSubType();
	oldMessage(leftMessage({
    	time:time,
    	name:fromAccount,
    	messageBody:messageBody1
    }));

}
function addMsg2(msg) {
    var isSelfSend, fromAccount, sessType, subType,time,messageBody1;
    time=webim.Tool.formatTimeStamp(msg.getTime());
	messageBody1=msg.elems[0].content.text;
    fromAccount = msg.getFromAccount();
    if (!fromAccount) {
        fromAccount = '';
    }
    sessType = msg.getSession().type();
    subType = msg.getSubType();
	newMessage(rightMessage({
    	time:time,
    	name:fromAccount,
    	messageBody:messageBody1
    }));

}

function addMsgOld2(msg) {
    var isSelfSend, fromAccount, sessType, subType,time,messageBody1;
    time=webim.Tool.formatTimeStamp(msg.getTime());
	messageBody1=msg.elems[0].content.text;
    fromAccount = msg.getFromAccount();
    if (!fromAccount) {
        fromAccount = '';
    }
    sessType = msg.getSession().type();
    subType = msg.getSubType();
	oldMessage(rightMessage({
    	time:time,
    	name:fromAccount,
    	messageBody:messageBody1
    }));

}

/*接收到一条图片消息*/
function addImg(msg,boo) {
	console.log("??????imgimgimg???????????????????");
	console.log("img");
	console.log(msg);
    var isSelfSend, fromAccount, sessType, subType,time,messageBody1;
    time=webim.Tool.formatTimeStamp(msg.getTime());
	messageBody1=msg.elems[0].content.text;
    fromAccount = msg.getFromAccount();
    if (!fromAccount) {
        fromAccount = '';
    }
    sessType = msg.getSession().type();
    subType = msg.getSubType();
    var imgArray=msg.elems[0].content.ImageInfoArray;
    var src=imgArray[2].url;
    var width=imgArray[2].width;
    var height=imgArray[2].height;
    var src1=imgArray[0].url;
    var width1=imgArray[0].width;
    var height1=imgArray[0].height;
    width>380?380:width;
    if(boo){
    	newMessage(leftImg({
             time:time,
             name:fromAccount,
             src:src,
             width:width,
             height:height,
             src1:src1,
             width1:width1,
             height1:height1
               }));
    }else{
    	newMessage(rightImg({
             time:time,
             name:fromAccount,
             src:src,
             width:width,
             height:height,
             src1:src1,
             width1:width1,
             height1:height1
               }));
    }
	

}

/*接收到一条图片消息*/
function addImgOld(msg,boo) {
	console.log("??????imgimgimg???????????????????");
	console.log("img");
	console.log(msg);
    var isSelfSend, fromAccount, sessType, subType,time,messageBody1;
    time=webim.Tool.formatTimeStamp(msg.getTime());
	messageBody1=msg.elems[0].content.text;
    fromAccount = msg.getFromAccount();
    if (!fromAccount) {
        fromAccount = '';
    }
    sessType = msg.getSession().type();
    subType = msg.getSubType();
    var imgArray=msg.elems[0].content.ImageInfoArray;
    var src=imgArray[2].url;
    var width=imgArray[2].width;
    var height=imgArray[2].height;
    var src1=imgArray[0].url;
    var width1=imgArray[0].width;
    var height1=imgArray[0].height;
    width>380?380:width;
    if(boo){
    	oldMessage(leftImg({
             time:time,
             name:fromAccount,
             src:src,
             width:width,
             height:height,
             src1:src1,
             width1:width1,
             height1:height1
               }));
    }else{
    	oldMessage(rightImg({
             time:time,
             name:fromAccount,
             src:src,
             width:width,
             height:height,
             src1:src1,
             width1:width1,
             height1:height1
               }));
    }
	

}


//监听新消息事件
//newMsgList 为新消息数组，结构为[Msg]
function onMsgNotify(newMsgList) {
	console.info(".....====?")
    console.warn(newMsgList);
    var sess, newMsg;
    //获取所有聊天会话
    var sessMap = webim.MsgStore.sessMap();

    for (var j in newMsgList) {//遍历新消息
        newMsg = newMsgList[j];

        if (newMsg.getSession().id() == selToID) {//为当前聊天对象的消息
            selSess = newMsg.getSession();
            //在聊天窗体中新增一条消息
            //console.warn(newMsg);
            if(newMsgList[0].elems[0].type == "TIMImageElem"){
            	addImg(newMsg,true);
            }else{
            	addMsg1(newMsg);
            }
        }
    }
    //消息已读上报，以及设置会话自动已读标记
    webim.setAutoRead(selSess, true, true);
}

//上传图片进度条回调函数
//loadedSize-已上传字节数
//totalSize-图片总字节数
function onProgressCallBack(loadedSize, totalSize) {
    var progress = document.getElementById('upd_progress');//上传图片进度条
  //  progress.value = (loadedSize / totalSize) * 100;
}

//上传图片
function uploadPic() {
    var uploadFiles = document.getElementById('upload3');
    var file = uploadFiles.files[0];

    var businessType;//业务类型，1-发群图片，2-向好友发图片
    if (selType == webim.SESSION_TYPE.C2C) {//向好友发图片
        businessType = webim.UPLOAD_PIC_BUSSINESS_TYPE.C2C_MSG;
    } else if (selType == webim.SESSION_TYPE.GROUP) {//发群图片
        businessType = webim.UPLOAD_PIC_BUSSINESS_TYPE.GROUP_MSG;
    }
    //封装上传图片请求
    var opt = {
        'file': file, //图片对象
        'onProgressCallBack': onProgressCallBack, //上传图片进度条回调函数
        //'abortButton': document.getElementById('upd_abort'), //停止上传图片按钮
        'From_Account': loginInfo.identifier, //发送者帐号
        'To_Account': selToID, //接收者
        'businessType': businessType//业务类型
    };
    //上传图片
    webim.uploadPic(opt,
            function (resp) {
                //上传成功发送图片
                sendPic(resp);
                closeUpload();
            },
            function (err) {
                alert(err.ErrorInfo);
            }
    );
}
//发送图片消息
function sendPic(images) {
    if (!selToID) {
        art.dialog({
		        	content: '未找到用户信息,数据错误！',
		            icon: 'warning',
		            time: 2			
			});
        return;
    }

    if (!selSess) {
        selSess = new webim.Session(selType, selToID, selToID, friendHeadUrl, Math.round(new Date().getTime() / 1000));
    }
    var msg = new webim.Msg(selSess, true,-1,-1,-1,loginInfo.identifier,0,loginInfo.identifierNick);
    var images_obj = new webim.Msg.Elem.Images(images.File_UUID);
    for (var i in images.URL_INFO) {
        var img = images.URL_INFO[i];
        var newImg;
        var type;
        switch (img.PIC_TYPE) {
            case 1://原图
                type = 1;//原图
                break;
            case 2://小图（缩略图）
                type = 3;//小图
                break;
            case 4://大图
                type = 2;//大图
                break;
        }
        newImg = new webim.Msg.Elem.Images.Image(type, img.PIC_Size, img.PIC_Width, img.PIC_Height, img.DownUrl);
        images_obj.addImage(newImg);
    }
    msg.addImage(images_obj);
    //调用发送图片消息接口
    webim.sendMsg(msg, function (resp) {
        if(selType==webim.SESSION_TYPE.C2C){//私聊时，在聊天窗口手动添加一条发的消息，群聊时，长轮询接口会返回自己发的消息
        	console.log("msgmsgmsgmsgmsgmsgmsgmsgmsgmsgmsg");
        	console.log(msg);
    var fromAccount = msg.getFromAccount();
    if (!fromAccount) {
        fromAccount = '';
    }
    var time=webim.Tool.formatTimeStamp(msg.getTime());
    var imgArray=msg.elems[0].content.ImageInfoArray;
    var src=imgArray[0].url;
    var width=imgArray[0].width+"px";
    var height=imgArray[0].height+"px";
    var src1=imgArray[2].url;
    var width1=imgArray[2].width;
    var height1=imgArray[2].height;
    width>380?380:width;
	newMessage(rightImg({
             time:time,
             name:fromAccount,
             src:src,
             width:width,
             height:height,
             src1:src1,
             width1:width1,
             height1:height1
               }));
        }
    }, function (err) {
        alert(err.ErrorInfo);
    });
}
//检查文件类型和大小
function checkFile(obj, fileSize) {
    var picExts = 'jpg|jpeg|png|bmp|gif|webp';
    var photoExt = obj.value.substr(obj.value.lastIndexOf(".") + 1).toLowerCase();//获得文件后缀名
    var pos = picExts.indexOf(photoExt);
    if (pos < 0) {
        alert("您选中的文件不是图片，请重新选择");
        return false;
    }
    fileSize = Math.round(fileSize / 1024 * 100) / 100; //单位为KB
    if (fileSize > 30 * 1024) {
        alert("您选择的图片大小超过限制(最大为30M)，请重新选择");
        return false;
    }
    return true;
}

function onGroupInfoChangeNotify(){};

/*历史消息*/
function getHistoryMsgCallback(msgList) {
	console.log(msgList);
    var msg;
    for (var j in msgList) {//遍历新消息
        msg = msgList[j];
        if (msg.getSession().id() == selToID) {//为当前聊天对象的消息
            selSess = msg.getSession();
            //在聊天窗体中新增一条消息
          // addMsg(msg);
          if(msg.elems[0].type == "TIMImageElem"){
	          	if(msg.fromAccount == tName){
	          		addImg(msg,false);
	          	}else{
	          		addImg(msg,true);
	          	}
    
            }else{
            	if(msg.fromAccount == tName){
	          		addMsg1(msg);
	          	}else{
	          		addMsg2(msg);
	          	}
            	
            }
        }
    }
    //消息已读上报，以及设置会话自动已读标记
    webim.setAutoRead(selSess, true, true);
}
/*历史消息*/
function getHistoryMsgCallback1(msgList) {
	console.log(msgList);
    var msg;
    var mArray=msgList.reverse();
    for (var j in mArray) {//遍历新消息
        msg = mArray[j];
        if (msg.getSession().id() == selToID) {//为当前聊天对象的消息
            selSess = msg.getSession();
            //在聊天窗体中新增一条消息
            console.log("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            console.log(msg);
          // addMsg(msg);
          if(msg.elems[0].type == "TIMImageElem"){
	          	if(msg.fromAccount == tName){
	          		addImgOld(msg,false);
	          	}else{
	          		addImgOld(msg,true);
	          	}
    
            }else{
            	if(mArray.fromAccount == tName){
	          		addMsgOld1(msg);
	          	}else{
	          		addMsgOld2(msg);
	          	}
            	
            }
        }
    }
    //消息已读上报，以及设置会话自动已读标记
    webim.setAutoRead(selSess, true, true);
}



//获取最新的c2c历史消息,用于切换好友聊天，重新拉取好友的聊天消息
var getLastC2CHistoryMsgs = function (cbOk, cbError) {

    if (selType == webim.SESSION_TYPE.GROUP) {
        alert('当前的聊天类型为群聊天，不能进行拉取好友历史消息操作');
        return;
    }
    var lastMsgTime = Math.round(new Date().getTime() / 1000);//默认取当前时间
    var msgKey = '';
    var options = {
        'Peer_Account': selToID, //好友帐号
        'MaxCnt': reqMsgCount, //拉取消息条数
        'LastMsgTime': lastMsgTime, //最近的消息时间，即从这个时间点向前拉取历史消息
        'MsgKey': msgKey
    };
    webim.getC2CHistoryMsgs(
            options,
            function (resp) {
                var complete = resp.Complete;//是否还有历史消息可以拉取，1-表示没有，0-表示有
                var retMsgCount = resp.MsgCount;//返回的消息条数，小于或等于请求的消息条数，小于的时候，说明没有历史消息可拉取了

                if (resp.MsgList.length == 0) {
                	alert("没有历史消息了,在这呢.....");
                    webim.Log.error("没有历史消息了:data=" + JSON.stringify(options));
                    return;
                }
                getPrePageC2CHistroyMsgInfoMap[selToID] = {//保留服务器返回的最近消息时间和消息Key,用于下次向前拉取历史消息
                    'LastMsgTime': resp.LastMsgTime,
                    'MsgKey': resp.MsgKey
                };
                if (cbOk)
                    cbOk(resp.MsgList);
            },
            cbError
            );
};
//向上翻页，获取更早的好友历史消息
var getPrePageC2CHistoryMsgs = function (cbOk, cbError) {
    if (selType == webim.SESSION_TYPE.GROUP) {
        alert('当前的聊天类型为群聊天，不能进行拉取好友历史消息操作');
        return;
    }
    var tempInfo = getPrePageC2CHistroyMsgInfoMap[selToID];//获取下一次拉取的c2c消息时间和消息Key
    var lastMsgTime;
    var msgKey;
    if (tempInfo) {
        lastMsgTime = tempInfo.LastMsgTime;
        msgKey = tempInfo.MsgKey;
    } else {
        //lastMsgTime = Math.round(new Date().getTime() / 1000);//默认取当前时间
        //msgKey = '';
        alert('获取下一次拉取的c2c消息时间和消息Key为空');
        return; 
    }
    var options = {
        'Peer_Account': selToID, //好友帐号
        'MaxCnt': reqMsgCount, //拉取消息条数
        'LastMsgTime': lastMsgTime, //最近的消息时间，即从这个时间点向前拉取历史消息
        'MsgKey': msgKey
    };
    webim.getC2CHistoryMsgs(
            options,
            function (resp) {
                var complete = resp.Complete;//是否还有历史消息可以拉取，1-表示没有，0-表示有
                if (resp.MsgList.length == 0) {
                    webim.Log.error("没有历史消息了:data=" + JSON.stringify(options));
                    return;
                }
                getPrePageC2CHistroyMsgInfoMap[selToID] = {//保留服务器返回的最近消息时间和消息Key,用于下次向前拉取历史消息
                    'LastMsgTime': resp.LastMsgTime,
                    'MsgKey': resp.MsgKey
                };
                if (cbOk)
                    cbOk(resp.MsgList);
            },
            cbError
            );
};


/*公用发送消息*/
/*rightMessage*/
function rightMessage(opt){
var message = '<div class="rightMessage"><p class="timeMessage">'+opt.time+'</p><div class="centerMessage"><span class="nameMessage" title="'+opt.name+'">'+opt.name+'</span><i class="fr"><img src="../media/im/img/doctor.jpg"></i><p class="messageBodyRight fr">'+opt.messageBody+'</p></div></div>';
  return message;
}
function leftMessage(opt){
var message = '<div class="leftMessage"><p class="timeMessage">'+opt.time+'</p><div class="centerMessage"><span class="nameMessage" title="'+opt.name+'">'+opt.name+'</span><i class="fl"><img src="../media/im/img/2017.jpg"></i><p class="messageBodyRight fl">'+opt.messageBody+'</p></div></div>';
  return message;
}

function rightImg(opt){
var message = '<div class="rightMessage"><p class="timeMessage">'+opt.time+'</p><div class="centerMessage"><span class="nameMessage" title="'+opt.name+'">'+opt.name+'</span><i class="fr"><img src="../media/im/img/doctor.jpg"></i><p class="messageBodyRight fr"><img class="showImgIm" data-src="'+opt.src1+'" data-width="'+opt.width1+'" data-height="'+opt.height1+'" src="'+opt.src+'" style="width: '+opt.width+';height: '+opt.height+';"/></p></div></div>';
  return message;
}
function leftImg(opt){
var message = '<div class="leftMessage"><p class="timeMessage">'+opt.time+'</p><div class="centerMessage"><span class="nameMessage" title="'+opt.name+'">'+opt.name+'</span><i class="fl"><img src="../media/im/img/2017.jpg"></i><p class="messageBodyRight fl"><img class="showImgIm" data-src="'+opt.src1+'" data-width="'+opt.width1+'" data-height="'+opt.height1+'" src="'+opt.src+'" style="width: '+opt.width+';height: '+opt.height+';"/></p></div></div>';
  return message;
}

function newMessage(message){
	messageAppend(messageAll,message);
	scrollBottom();
};
function oldMessage(message){
	messageBefore(messageAll,message);
};
function messageBefore(messageAll,message){
		messageAll.children().eq(0).before(message);
};

function messageAppend(messageAll,message){
	messageAll.append(message);
};

/*后台接口获取历史消息*/
;function historyMessage(){
	alert("进入后台接口获取历史消息...");
	$.ajax({
		url : url+"/consult/sel_message",
		type : "POST",
		dataType : "json",
		data:{"busCode":"50101","sender":"yf_w5","recevrer":"yf_w4","startTime":"","endTime":"","pageNo":"1","pageSize":"20","msgId":""},
		success : function(json1) {
		console.log(12344214215);
		console.log("获取查询聊天记录............成功");
		console.log(json1);
			
		},
		error : function() { 
		console.log("获取查询聊天记录............失败");
			alert('Error');
		}
	}); 
} 

/*滚动到底部*/
function scrollBottom(){
	  setTimeout(function () {
       var num=messageAll[0].scrollHeight-messageAll.height();
       messageAll.scrollTop(num);
      }, 300);
};

/*回车发送消息*/
function onTextareaKeyDown(event) {
	var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异
	  if (event.keyCode == 13) {
      setTimeout(function(){
   			onSendMsg(); 
   		},0);
  	}
};
/*关闭图片发送框*/
function closeUpload(){
	$("#uploadWindow").hide();
	setTimeout(function(){
		$("#upload3").val("");
	},1500);
};
/*上传图片验证*/
function uploadImg(){
	var text = $("#upload3").val();
	if(text.length == 0){
		art.dialog({
        	content: '请上传图片！',
            icon: 'warning',
            time: 1.5
		});
		return;
	}else{
		if(!reIm.test(text)){
			art.dialog({
	        	content: '上传文件必须是图片！',
	            icon: 'warning',
	            time: 1.5
			});
			return;
		}else{
			uploadPic();
		}
	}
};