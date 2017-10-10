<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 消息提醒版本弹出 -->
<div class="modal fade" id="messageRemind" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true" style="top:10%" data-backdrop="static">
   <div class="modal-dialog message-dialog">
      <div class="modal-content" style="width: 712px;">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <center><h4 class="modal-title"  style="font-weight:bold" id="messageModalLabelRemind"></h4></center>
         </div>
         <div class="modal-body">
         	<center><div style="display:none" class="report"><a href="" class="reportUrl" target= _blank style="font-weight:bold">点击</a>查看监护报告详情</div></center>
		<!-- 开始 -->
		<div class="conter" id="conter" style="width:683px;">
			<div class="pleft" id="pleft">
				<ul id="friendList">
				</ul>
			</div>
			<div class="pright">
				<div class="tname">
					 <span style="color: #919499"></span> 
				</div>
				<div class="imMessageBox" id="imMessageCenterBox">
				</div>
				<div class="imgMessage">
						<a class="fl imga" href="javascript:;"></a>
				</div>
				<div class="sendMessageInput">
						<textarea id="messageBody" onkeydown="onTextareaKeyDown()"></textarea>
						<a class="sendMessageBotton" href="javascript:;" onclick="onSendMsg()">发送</a>
				</div>
			</div>		
		</div>
			<div id="uploadWindow">
				<h3 class="uploadTitle">
					<p class="fl">发送图片</p>
					<a class="fr" onclick="closeUpload();" href="javascript:void(0)"></a>
				</h3>
				<div class="uploadCenter">
				   <div  style="position: absolute;left: 80px;top:59px;" >
						<input type="file" class="fl" id="upload3" name="file" /> 
						<input type="submit" onclick="uploadImg()"  value="提&nbsp交" />
 					<iframe id="upframe" name="upframe" src="" style="display:none;"></iframe>
					 </div> 
				</div>
		</div>
      </div>
         <!-- 结束 -->
         <div class="modal-footer rule-modal-footer">
         	<center>
            <button type="button" class="btn btn-default close-chat" data-dismiss="modal">关闭</button>
            </center>
         </div>
      </div>
	</div>
</div>
