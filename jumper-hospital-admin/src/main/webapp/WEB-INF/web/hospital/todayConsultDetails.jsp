<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="${pageContext.request.contextPath}/media/umeditor/themes/default/css/umeditor.css?${v }" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/hospital.css?${v }">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/consultDetail.css?${v }">
   <!-- <div class="clearfix"></div> -->
   <!-- BEGIN CONTAINER -->
   <!-- <div class="panel-body"> -->
   <!-- <div class="page-container"> -->
   <%@ include file="../common/head.jsp"%>
   <script type="text/javascript">
	$(function(){
	
		$("#remindEvaluate").click(function(){
			$.post(
				baseURL+"/consult/hospitalConsultRmind",
				{id:$("#consIds").val()},
				function(data){
					if(data=="success"){
						window.location.href=baseURL+"/consult/lookTodayDetails?id="+$("#consIds").val();
					}
				}
				
			);
		});
		$("a[type='delete']").click(function(){
			App.confirm_show("提示信息", "确定要结束问诊吗？", function(){
				$.post(
					baseURL+"/consult/toEndConsult1",
					{id:$("#consIds").val()},
					function(data){
						if(data=="success"){
							window.location.href=baseURL+"/consult/lookTodayDetails?id="+$("#consIds").val();
						}
					}
				);
			});
		});
		
		
		$('div.btn-cont').find('a[type=reply]').click(function(){
		 var myform=document.getElementById("myform");
		 if ($("#content").val()!=null&&$("#content").val()!=""&&$("#content").val().trim().length!=0) {
		 	if($("#content").val().length>255){
		 		App.dialog_show("提示！", "您的回复内容过长，请精简用词！", function(){
					$(".info-dialog-close").trigger("click");
				});
		 	}else{
		 		//document.getElementById("reply").onclick = function(){return false;}
		 		$.post(
		 		baseURL+"/consult/checkEvaluate",
		 		{consId:$("#id").val()},
		 		function(data){
		 			if(data=="Y"){
		 				App.dialog_show("提示！", "该问题已结束！", function(){
							$(".info-dialog-close").trigger("click");
							window.location.href=baseURL+"/consult/lookTodayDetails?id="+$("#id").val();
						});
		 			}else if(data=="N"){
		 				 myform.submit();
		 			}else{
		 				App.dialog_show("提示！", "处理异常！", function(){
							$(".info-dialog-close").trigger("click");
						});
		 			}
		 			
		 		}
		 	);
		 	}
		 	
		 }else{
		 	App.dialog_show("提示！", "您不能发送空消息！", function(){
				$(".info-dialog-close").trigger("click");
			});
		 }
		 request_consultant();//提交以后立即更新
	});
		//setInterval(freshChats,3000);
		if($("#flag").val()=="today"){
			$(".scroller").scrollTop($(".scroller")[0].scrollHeight);
		}
	});
	
	function freshChats(){
			$.post(
				baseURL+"/consult/freshChats",
				{ 
					consultId:$("#consIds").val(),
					lastTime:$("#lastTime").val()
				},
				function(data){
					var datas = eval("("+data+")");
					if(data!=""){
						$.each(datas,function(n,value){
							if(value.user_img==null||value.user_img==""){
							var str = "<img src='${pageContext.request.contextPath}/media/image/defaultuser.png'  class='avatar img-responsive' alt=''/>";
						}else{
							var str = "<img class='avatar img-responsive' alt='' src='"+value.user_img+"'>";
						}
						if(value.time!=null){
							$("#lastTime").attr("value",value.time);
						}
						var html = "";
						if(value.content!=null&&value.content!=""){
							html = value.content+"<br/>";
						}else{
							if(value.isVoice==0){
								html = "<img src='"+value.img_url+"' class='showImg' width='100px' />";
							}else if(value.isVoice==1){
								html = "<div class='play'><a href='###' type='playVoice' value='"+value.img_url+"'><img src='${pageContext.request.contextPath}/media/image/voice-play.png' width='30' height='30' /></a></div>"
								+ "<div class='paused' style='display: none;'><a href='###' type='pausedVoice' value='"+value.img_url+"'><img src='${pageContext.request.contextPath}/media/image/voice-play.png' width='30' height='30' /></a></div>";
							}
						}
						
						$(".chats").append(
							"<li class='in'>"+
							"<div class='left'>"+
										"<div class='left_img'>"+str+
										"</div>"+
										"<div class='left_dhk'>"+
										  "<pre class='zhangxiaoxian' style='width:462px;'>"+value.userName+"  "+value.time.substring(0,16)+"</pre>"+
										  "<div class='dhk_left'>"+
										   "<div class='write'><img src='${pageContext.request.contextPath}/media/image/question.png'></div>"+
										   "<div class='zishu'>"+
										    "<div class='write_font'>"+
										    	//value.content+"<br>"+
										    	html+
										    "</div></div>"+
										   "</div>"+
										"</div>"+
										"</div></li>"
						);
						});
						
						
						
						if($("#flag").val()=="today"){
							$(".scroller").scrollTop($(".scroller")[0].scrollHeight);
						}
					}
				}
			);
		}
	var imgSrc = "${pageContext.request.contextPath}/media/image/audio.gif";
	var oldImgSrc = "${pageContext.request.contextPath}/media/image/audio.png";
	//播放语音
	var timerNew = null;
	var newNumber = 0;
	$(document).on("click",".audioI",function(){
		var index = $(this).attr("id");
		for(var i = 0;i<$(".aAudio").length;i++){
			var xI = $(".aAudio").eq(i).prevAll().attr("id");
			if(xI != index){
				var id = $(".aAudio").eq(i).attr("id");
				var playAudio = document.getElementById(id);
				playAudio.pause();
			}
		}
		var _this = $(this);
		var id = $(this).next().attr("id");
		var playAudio = document.getElementById(id);
		var number = parseInt($(this).nextAll("span").text());
		var oldNumber = parseInt($(this).nextAll(".dataYl").text());
		if(playAudio.paused){
			clearInterval(timerNew);
			playAudio.play();
			timerNew = setInterval(function(){
				number = number - 1;
				_this.nextAll("span").text(number);
				if(number == -1){
					clearInterval(timerNew);
					_this.nextAll("span").text(oldNumber);
				}
			},1000)
		}else{
			playAudio.pause();
			clearInterval(timerNew);
		}
	})

	
	function leftAudio(opt){
	var message = '<div class="leftM" id="'+opt.msgId+'"><p class="timeM">'+opt.time+'</p><div class="centerM"><span class="nameM" title="'+opt.niceName+'">'
	+opt.niceName+'</span><p class="pointM"></p><i class="fl"><img src="'+recveImg+'"></i><p class="messageB fl"><i id="audio_'+opt.audioId+'" class="fl audioI"><img src="'
	+audioIcon+'"/></i><audio class="aAudio" style="display:none;" id="'+opt.audioId+'" src="'+opt.src+'" controls="controls"></audio><span class="fl" style="margin:2px 0 0 8px;">'
	+opt.size+'</span><i class="dataYl" style="display:none;">'+opt.size+'</i></p></div></div>';
	return message;
}
	
</script>
      <!-- BEGIN SIDEBAR -->
      <!-- <div style="width:85%; height:880px; overflow-y:auto;background-color: white;"> -->
      <!-- <div style="width:95%;height:830px;overflow:auto;background-color:#EFF7F7;margin-top:50px;margin-left:45px;margin-right:45px;"> -->
      <!-- <div class="panel-body"> -->
      <div class="page-content">
         <!-- END PAGE HEADER-->
          <div class="col-xs-12 col-sm-9" style="height: auto;">
               <!-- BEGIN PORTLET-->
               <div class="portlet">
                  <div class="portlet-body" id="chats">
                  <div style="background-color: white;">
                  <input type="hidden" value="${flag}" id="flag"/>
                         <c:if test="${flag=='today'}">
			          		<!-- <a href="${pageContext.request.contextPath}/consult/consultList"><img src="${pageContext.request.contextPath}/media/image/backlist.png"/></a> -->
			          		<a href="${pageContext.request.contextPath}/consult/consultList"><img src="${pageContext.request.contextPath}/media/image/backlist.png"/></a>
			          	</c:if>
			          	<c:if test="${flag=='end'}">
			          		<!-- <a href="${pageContext.request.contextPath}/consult/endList"><img src="${pageContext.request.contextPath}/media/image/backlist.png"/></a> -->
			          		<a href="${pageContext.request.contextPath}/consult/endList"><img src="${pageContext.request.contextPath}/media/image/backlist.png"/></a>
			          	</c:if>
                     </div>    
                     <div class="slimScrollDiv" style="border: 1px solid white;position: relative; overflow: hidden; width: auto; height: auto;">
                     <div class="scroller" style="height: 73%; overflow: auto; width: auto;" data-always-visible="1" data-rail-visible1="1">
                     	
                        <ul class="chats" style="list-style: none;margin-left:40px;margin-top:20px;">
                        <input id="consIds" type="hidden" value="${consult.id}"/>
                        <input id="lastTime" type="hidden" value="${lastTime}"/>
                        <c:forEach items="${detail}" var="em" varStatus="v">
                        	
                        	<c:if test="${em.talker==0}">
                        		<li class="in">
		                              <div class="left"><!--左边对话框-->
										<div class="left_img">
											<c:choose>
										        <c:when test="${em.user_img==null||em.user_img==''}">
										       		<img src="${pageContext.request.contextPath}/media/image/defaultuser.png"  class="avatar img-responsive" alt=""/>
										       </c:when>
										       	<c:otherwise>
										       		<img class="avatar img-responsive" alt="" src="${em.user_img }">
										       	</c:otherwise>
										       </c:choose>
										</div>
										<div class="left_dhk">
										<c:set var="time" value="${em.sendTime}"></c:set>
										  <pre class="zhangxiaoxian" style="width:462px;">${em.userName}  <c:out value="${fn:substring(time, 0, 16)}" /></pre>
										  <div class="dhk_left">
										   <div class="write"><img src="${pageContext.request.contextPath}/media/image/question.png"></div>
										   <div class="zishu"><!-- 控制字数 -->
										    <div class="write_font">
										    
										    	<c:choose>
										        <c:when test="${em.msgType == 'TIMImageElem'}">
										       		<img src="${em.urlmsg.original}"   alt=""  width="${em.urlmsg.width}" height="${em.urlmsg.height}"/>
										       </c:when>
										       <c:when test="${em.msgType == 'TIMSoundElem'}">
										       		<audio class="aAudio" style="display:block;" src="${em.msgContent}" controls="controls"></audio>
										       </c:when>
										       <c:when test="${em.msgType == '15'}">
													 	对方有一条消息撤回！
												</c:when>
										       	<c:otherwise>
										       		${em.msgContent}
										       	</c:otherwise>
										       </c:choose>
										       
										    	
										    	<br>
		                                 <c:choose>
		                                 	<c:when test="${em.isVoice == 0}">
												<c:forEach items="${em.img_url}" var="s">
												<img src="${s}" class="showImg" width="100px" />
												</c:forEach>
		                                 	</c:when>
		                                 	<c:otherwise>
		                                 		 <div class="play">
		                                 	 	<c:forEach items="${em.img_url}" var="s">
														<a href="###" type="playVoice" value="${s }">
			                                 	 		<img src="${pageContext.request.contextPath}/media/image/voice-play.png" width="30" height="30" />
			                                 	 		</a>
												</c:forEach>
		                                 	 </div>
		                                 	 <div class="paused" style="display: none;">
		                                 	 	<c:forEach items="${em.img_url}" var="s">
														<a href="###" type="pausedVoice" value="${s}">
			                                 	 		<img src="${pageContext.request.contextPath}/media/image/voice-play.png" width="30" height="30" />
			                                 	 		</a>
												</c:forEach>
			                                 </div>
		                                 	</c:otherwise>
		                                 </c:choose>
										    </div></div>
										   </div>
										</div>
										</div><!--左边对话框结束-->
		                           </li>
		                           
                        	</c:if>
                        	
                        	<c:if test="${em.talker==1}">
                        		 <li class="out">
			                              
			                              <div class="right"><!--右边对话框-->
											  <div class="right_img">
											  	<c:choose>
											        <c:when test="${em.user_img==null||em.user_img==''}">
											       		<img src="${pageContext.request.contextPath}/media/image/hospital1.png"  class="avatar img-responsive" alt=""/>
											       </c:when>
											       	<c:otherwise>
											       		<img class="avatar img-responsive" alt="" src="${em.user_img }" id="">
											       	</c:otherwise>
											       </c:choose>
											  </div>
											  <div class="right_dhk">
											  <c:set var="times" value="${em.sendTime}"></c:set>
											    <pre class="xixiang">${em.doctorName}  <c:out value="${fn:substring(times, 0, 16)}" /></pre>
											    <br>
											    <div class="right_duihuakuang">
											      <div class="red"><img src="${pageContext.request.contextPath}/media/image/ask.png"></div>
											      <div class="red_font">  
											      		<c:choose>
													        <c:when test="${em.msgType == 'TIMImageElem'}">
													       		<img src="${em.urlmsg.original}"   alt=""  width="${em.urlmsg.width}" height="${em.urlmsg.height}"/>
													       </c:when>
													       <c:when test="${em.msgType == 'TIMSoundElem'}">
													       		<audio class="aAudio" style="display:block;" src="${em.msgContent}" controls="controls"></audio>
													       </c:when>
													       <c:when test="${em.msgType == '15'}">
													 			您有一条消息撤回！
													       </c:when>
													       	<c:otherwise>
													       		${em.msgContent}
													       	</c:otherwise>
													     </c:choose>
			                                 <!-- 0：表示图片；1：表示语音 -->
			                                 <c:choose>
		                                 	<c:when test="${em.isVoice == 0}">
			                                 	<c:forEach items="${em.img_url}" var="s">
													<img src="${s}" class="showImg" width="100px" />
													</c:forEach>
		                                 	</c:when>
		                                 	<c:otherwise>
		                                 		 <div class="play">
		                                 	 	<c:forEach items="${em.img_url}" var="s">
														<a href="###" type="playVoice" value="${s}">
			                                 	 		<img src="${pageContext.request.contextPath}/media/image/voice-play.png" width="30" height="30" />
			                                 	 		</a>
												</c:forEach>
		                                 	 </div>
		                                 	 <div class="paused" style="display: none;">
		                                 	 
		                                 	 	<c:forEach items="${em.img_url}" var="s">
														<a href="###" type="pausedVoice" value="${s}">
			                                 	 		<img src="${pageContext.request.contextPath}/media/image/voice-play.png" width="30" height="30" />
			                                 	 		</a>
												</c:forEach>
			                                 </div>
		                                 	</c:otherwise>
		                                 </c:choose>
											      </div>
											    </div>
											  </div>
											</div><!--右边对话框结束-->
			                           </li>
                        	</c:if>
                        </c:forEach>
                        
                        
                           
                        </ul>
                     </div>
                     <form id="myform" action="${pageContext.request.contextPath}/consult/hospitalConsultReply" method="post">
                     
                    <div class="page-content">
                 			<div class="input-cont" >
                 			<c:if test="${consult.evaluate==0}">
                 				<c:choose>
                 					<c:when test="${consult.status==2||consult.status==3||consult.status==4}">
                 						<!--写一个医生回复的文本域  -->
                 						<div class="chat-form" style="width:100%;margin-top:20px;">
                     			<div class="input-cont" style="width:90%;display:block;">
                     			  <input  type="hidden" value="${consult.id}" name="id" id="id" /><!-- class="form-control" -->
                       			  <!-- <input class="form-control" id="content" name="content" type="text" placeholder="Type a message here..." /> -->
                     				<textarea rows="3" style="width:100%;" class="form-control" id="content" name="content" placeholder="Type a message here..."></textarea>
                     			</div>
                     			
                     		    <div class="btn-cont" style="margin-right:10px;width:10%;"> 
                        				<!-- <span class="arrow"></span> -->
                        				
                        				<a href="javascript:void(0)" id="reply" type="reply" class="btn blue icn-only" ><!-- <i class="icon-ok icon-white"></i> -->发送</a>
                    			</div>
                    			<br/>
                    			<div style="float:left;margin-left:60px;">
                    				<!-- <a id="remindEvaluate" href="javascript:void(0);" style="text-decoration: underline;">提醒评价</a><span style="color:gray;font-weight: lighter;">&nbsp;医生，您好！您可以点击【提醒评价】，提醒孕妇对您的服务进行评价！</span> -->
                    				<span style="color:red;">&nbsp;请尽快回复孕妇的问题，${leftTime}小时后本次咨询将自动结束</span>
                    			</div>
                    			<%-- <div style="float:right;"><!-- margin-right:43px; -->
                    			<c:if test="${endVisit==1}">
                     			<a href="javascript:void(0)" class="btn red icn-only" type="delete" style="margin-right:12px;margin-top: 0px;">结束问诊</a>
                     			</c:if>
                    			<!-- <a type="delete"  href="javascript:void(0)"> 结束问诊&nbsp;&nbsp;</a> --><!--   href="${pageContext.request.contextPath}/consult/toEndConsult?id=${consult.id}" -->
                    			</div> --%>
                  				</div>
                 						
                 					</c:when>
                 					<c:otherwise>
                 						<center><span style="font-size:20px;">用户暂未评价！</span></center>
                 					</c:otherwise>
                 				</c:choose>
                 			</c:if>
                 			<c:if test="${consult.evaluate==1}">
                 			<center>
                 				<label style="font-size:20px;">提问已结束</label><br>
	                        	<span style="font-size:20px;">用户评价：${content }</span>
	                        	</center>
                 			</c:if>
                 			
                 			
                 			
                 			</div>
                  			</div>
                  </form>
                     </div>
                  </div>
               </div>
               <!-- END PORTLET-->
            </div>
      </div>
      
      </div>
     <!--  </div> -->
      
      <!-- END PAGE -->
   <!-- </div>
   </div>
   </div> -->
   
   
   <a class=" btn default" data-toggle="modal" href="#long" id="dialog_img" style="display:none"></a>
   
   <!-- 图片点击以后弹出层放大div -->
    <div id="long" class="modal fade" tabindex="-1" data-replace="true" style="top:0!important;">
        <div class="modal-dialog">
           <div class="modal-content">
              <div class="modal-header">
                 <button style="margin-top:-6px;" type="button" class="close" data-dismiss="modal" aria-hidden="true"><span>&times;</span></button>
              </div>
              <div class="modal-body" style="text-align: center;">
                 <img alt="" id="big_img" style="width: 500px;" src="">
              </div>
           </div>
        </div>
     </div>
   
   
   <!-- BEGIN FOOTER -->
    <%@ include file="../common/foot.jsp"%> 
   <!-- BEGIN PAGE LEVEL PLUGINS -->
   	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/consultantReply.js"></script>
   	<!-- 配置文件 -->  
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/hospital.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/doctorManager.js"></script>
    
   <!-- END PAGE LEVEL PLUGINS -->
   <!-- END FOOTER -->
   <jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>
</body>
<!-- END BODY -->
</html>
