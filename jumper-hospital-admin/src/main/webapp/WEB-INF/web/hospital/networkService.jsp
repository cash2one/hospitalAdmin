<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link
	href="${pageContext.request.contextPath}/media/css/service.css?${v }" rel="stylesheet">

<script type="text/javascript">
		function btsubmit(){
			App.confirm_show("提示","确认开通网络诊室服务",function(){
			   window.location.href="${pageContext.request.contextPath}/hos/network?isnetwork=1";
			});
		}
		 
</script>

<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="/hospital/pregnantBook/pregnantBookList" method="post"
		id="form">
		<div class="panel panel-default">
			<div class="panel-body">

				<div class="actions" style="margin-top: auto;margin-bottom: auto;">
						<i class=" icon-remove-sign" style="color:#C00; font-size:18px;"></i> 当前医院未开通网络诊室服务
                       <button type="button" id="networkservice" onclick="btsubmit();" class="btn btn-success networkservice" title="开通服务" style="float:right;">开通服务</button>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<!-- style="margin-right: 200px"  -->
			<div class="panel-body">
				<div class="instraction" style="line-height:25px;">
					<p>网络问诊服务是向用户提供一项远程的、实时的、便捷的医患在线网络问诊服务，医生与患者可以预约时间，通过视频通话的方式进行网络沟通，实现远程问诊。网络诊室功能配有独立的排班管理、诊室管理及服务管理系统，医院可以随时管理服务中的医生账号、问诊记录。</p>
					<p>网络诊室功能旨在帮助医院与患者改善现场问诊的流程，使得网络诊室成为医院多方服务渠道之一。通过网络诊室，医生可以为患者进行初步的问诊服务，并给出一定的建议。在将来，网络诊室将进一步对接各种医疗服务。</p>
				</div>
				<div class="clearFix"></div>
				<hr style="margin:20px 0px;">
				<div class="weight-title">服务流程</div>
				<div class="instraction" style="margin-top:20px;">
					<p>1. 医院对网络诊室进行排班安排，设置好开诊的科室、专家、时间及费用。</p>
					<p>2. 用户在App端获取排班信息，进行预约并付费。</p>
					<p>3. 工作时间，医生登录网络诊室，对成功预约的用户进行叫号。</p>
					<p>4. 用户与医生进行视频问诊，医生在问诊过程中记录相关问诊信息。</p>
					<p>5. 完成问诊，用户在手机端可查看医生给出的问诊建议。</p>
					<p>备注：医生在操作时，只需点击下一位进行继续叫号；如果用户无应答，医生可以在等待时间过后进行跳过。系统会自动安排用户排队序列。</p>
				</div>
			</div>
		</div>		
	</form>
</div>

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>