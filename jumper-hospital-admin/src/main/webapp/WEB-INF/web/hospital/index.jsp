<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>公用聊天(IM版1)</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/media/im/css/react.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/media/im/css/blue2.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/media/im/css/publicIm.css" />
	<script>
		var messageDate = {"tName":"yf_w5","tNameNickName":"医生","selToID":"yf_w4","selToIDNickName":"孕妇","busCode":"50101"};
		var url = "${pageContext.request.contextPath}";
	</script>
	</head>
	<body>
		<input type="hidden" name="hospitalId" value="${vOChatRecord.hospitalId }"/>
		<input type="hidden" name="hospitalName" value="${hospitalInfo.name }"/>
		<input type="hidden" name="userId" value="${vOChatRecord.userId }"/>
		<input type="hidden" name="userName" value="${vOChatRecord.userName }"/>
		<input type="hidden" name="userHeadUrl" value="${vOChatRecord.userHeadUrl }"/>
		<div id="windowBody">
			<div class="windowTitle">
				<p class="titleLeft fl">Annie</p>
				<a class="fr" id="closeWindow" href="javascript:;"></a>
			</div>
			<div class="centerBox">
				<div class="centerLeft fl">
					<div id="imMessageBox"></div>
					<div class="imgMessage">
						<a class="fl imga" href="javascript:;"></a>
						<a class="fr xxjl" id="xxjl" href="javascript:;">消息记录</a>
					</div>
					<div class="sendMessageInput">
						<textarea id="messageBody" onkeydown="onTextareaKeyDown()" style="resize: none;"></textarea>
						<a class="sendMessageBotton" href="javascript:;" onclick="onSendMsg()">发送</a>
					</div>
				</div>
			</div>
		</div>
		
			<div id="uploadWindow">
				<h3 class="uploadTitle">
					<p class="fl">发送图片</p>
					<a class="fr" onclick="closeUpload();" href="javascript:void(0)"></a>
				</h3>
				<div class="uploadCenter">
					<form action="file/upload.do" method="post" enctype="multipart/form-data" target="upframe"/>  
						<input type="file" id="upload3" name="image" /> 
						<input type="submit" onclick="uploadImg()"  value="提&nbsp交" />
 					<iframe id="upframe" name="upframe" src="" style="display:none;"></iframe>
					</from> 
				</div>
			</div>
	</body>
	<!--工具类-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/im/js/lib/jquery/jquery.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/im/js/tool/jquery.nicescroll.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/im/js/sdk/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/im/js/tool/artDialog.source.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/im/js/tool/iframeTools.source.js" ></script>
	<!--Im类-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/im/js/sdk/webim.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/im/js/lib/md5/spark-md5.js" ></script>
	<!--控制类-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/im/js/public/public.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/im/js/public/public_kz.js"></script>


     <script>
   
      		      //帐号模式，0-表示独立模式，1-表示托管模式
           var accountMode=0;
         // var accountMode=1;
            //官方 demo appid,需要开发者自己修改（托管模式）
            //独立模式,实际开发
     //    var sdkAppID = 1400011255;
       //   var accountType = 5854;
            //测试
         var sdkAppID = 1400017059;
	      var accountType = 8056;
                  //当前用户身份
            var loginInfo = {
                    'sdkAppID': sdkAppID, //用户所属应用id,必填
                    'appIDAt3rd': sdkAppID, //用户所属应用id，必填
                    'accountType': accountType, //用户所属应用帐号类型，必填
                    'identifier': null, //当前用户ID,必须是否字符串类型，必填
                    'identifierNick': null, //当前用户昵称，选填
                    'userSig': null, //当前用户身份凭证，必须是字符串类型，必填
                    'headurl': 'img/2016.gif'//当前用户默认头像，选填
            };      
            

            
            var selType = webim.SESSION_TYPE.C2C;//当前聊天类型
            var selToID = null;//当前选中聊天id（当聊天类型为私聊时，该值为好友帐号，否则为群号）
            var selSess = null;//当前聊天会话对象
          /*   var tName=null; */
            
            //默认好友或群头像
            var friendHeadUrl = '${pageContext.request.contextPath}/media/im/img/2017.jpg';
            
            var maxNameLen=8;//我的好友或群组列表中名称显示最大长度，仅demo用得到
            var reqMsgCount = 15;//每次请求的历史消息(c2c获取群)条数，进demo用得到
            var pageSize = 15;//表格的每页条数，bootstrap table 分页时用到
            var totalCount = 200;//每次接口请求的条数，bootstrap table 分页时用到
            
            var emotionFlag = false;//是否打开过表情选择框
            
            var curPlayAudio=null;//当前正在播放的audio对象
            
            var getPrePageC2CHistroyMsgInfoMap={};//保留下一次拉取好友历史消息的信息
            var getPrePageGroupHistroyMsgInfoMap={};//保留下一次拉取群历史消息的信息
            var groupSystemNotifys={};
            
            //监听连接状态回调变化事件
            var onConnNotify=function(resp){
                switch(resp.ErrorCode){
                    case webim.CONNECTION_STATUS.ON:
                        webim.Log.warn('连接状态正常...');
                        break;
                    case webim.CONNECTION_STATUS.OFF:
                        webim.Log.warn('连接已断开，无法收到新消息，请检查下你的网络是否正常');
                        break;
                    default:
                        webim.Log.error('未知连接状态,status='+resp.ErrorCode);
                        break;
                }
            };
            
            //IE9(含)以下浏览器用到的jsonp回调函数
            function jsonpCallback(rspData) {
                webim.setJsonpLastRspData(rspData);
            }
            
            //监听事件
            var listeners = {
                "onConnNotify": onConnNotify,
                "jsonpCallback": jsonpCallback,//IE9(含)以下浏览器用到的jsonp回调函数
                "onMsgNotify": onMsgNotify,//监听新消息(私聊，群聊，群提示消息)事件
                "onGroupInfoChangeNotify": onGroupInfoChangeNotify,//监听群资料变化事件
                "groupSystemNotifys": groupSystemNotifys//监听（多终端同步）群系统消息事件
            };
 
                        
        //    var isLogOn=true;//是否开启控制台打印日志
        var isLogOn=false;
                      
            //初始化时，其他对象，选填
            var options={};
            
             //获取sig consult/init_data.do
	 	 	$.ajax({
				url : url+"/consult/init_data",
				type : "POST",
				dataType : "json",
				data:{"imAccount":messageDate.tName},
				success : function(json) {
				console.log("获取sig码............");
					console.log(json);
					console.log(typeof json);
					var aJson = JSON.parse(json);
					var userDate = aJson.data;
					console.log(userDate);
					tName = userDate.userid;
					selToID = messageDate.selToID;
					console.log(tName);
					console.log(userDate.sig);
					independentModeLogin1(tName,userDate.sig);
					$('#testBotton').hide();   
				},
				error : function() { 
				console.log("获取sig码............失败");
					alert('Error');
				}
			});  



 /*  var tName="yf_w5";
selToID = "yf_w4";
$(".titleLeft").text(tName);
                testLoginIM(tName);
             
  function testLoginIM(tName){
  
    					$.ajax({
				url : '${pageContext.request.contextPath}/media/im/sig1.html',
				type : "get",
				dataType : "json",
				success : function(json) {
										console.log(json);
										console.log(json[tName]);
										console.log(json[selToID]);
					independentModeLogin1(tName,json[tName]);
					$('#testBotton').hide();
				},
				error : function() { 
					alert('Error');
				}
			});
			return false;
    }; 
 */
      </script> 
      	<script>

		$(function(){
			$("#imMessageBox,#ltjlBox,#messageBody").niceScroll({
				railpadding: { top:0, right: 1, left: 0, bottom:0 },
				cursorborder:"",
				cursoropacitymin: 0.2,
				cursoropacitymax: 0.2,
				cursorcolor:"#000000",
				zindex: "99",
				cursorwidth: "5px",
			});
		});
				$(".titleLeft").text(messageDate.tNameNickName);
	</script>
</html>
