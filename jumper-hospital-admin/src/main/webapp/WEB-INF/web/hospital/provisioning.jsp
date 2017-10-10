<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>

<!--style-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/menu.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/daterangepicker-bs3.css">
<style>
input,select {
	height: 25px;
	padding: 0 5px;
}

input[type="radio"],input[type="checkbox"] {
	margin: 0;
}

label {
	margin-right: 10px;
}
</style>

	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-right">
			<!--右边内容-->
			<div class="col-xs-12 col-sm-9" style="float:right;">
				<form action="/hospital/pregnantBook/pregnantBookList" method="post"
					id="form">
					<div class="panel panel-default">
						<div class="panel-body">

							<div class="actions"
								style="margin-top: auto;margin-bottom: auto;">
								<i class=" icon-ok-sign" style="color:#3C0; font-size:18px;"></i>
								当前医院已开通网络诊室服务
								<button type="button" onclick="btsubmit();" class="btn btn-danger" title="关闭服务" style="float:right;">关闭服务</button>

							</div>
						</div>
					</div>

					<div class="panel panel-default">
						<!-- style="margin-right: 200px"  -->
						<div class="panel-body" style="line-height:30px;">

							<div class="panel-head">

								<i class=" icon-cog" style="color:#ff7ea5;"></i> 设置网络问诊服务
							</div>
							<div class="clearFix"></div>
							<hr>
							<p>请设置本院科室情况</p>
							<div class="weight-title float-left" style="width:100%">
								<div class="float-left">
									<select id="selectid" onchange="changebtn();">
									    <option <c:if test="${state=='-1' }">selected="selected"</c:if> value="-1">全部科室</option>
										<option <c:if test="${state=='1' }">selected="selected"</c:if> value="1">已开通科室</option>
										<option <c:if test="${state=='0' }">selected="selected"</c:if> value="0">未开通科室</option>
									</select>
								</div>
							</div>
							<table class="bordered">
								<thead>
							    	<tr>
										<th>科室</th>
										<th>服务状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="tbodyid">
								<c:forEach items="${majorInfosList}" var="data">
				                	<tr>
					                    <td>${data.hospitalDoctorMajor.major}</td>
					                    <td>
					                    	<c:if test="${data.isnetwork=='1'}">已开通</c:if>
											<c:if test="${data.isnetwork!='1'}">未开通</c:if>
					                    </td>
					                    <td>
					                        <c:choose>
					                        	<c:when test="${data.isnetwork!='1'}">
					                        		<button type="button" onclick="openOrCloseSubmit(this.value,'1');" class="btn btn-success btn-sm" title="开通服务" style="width: 80px;" value="${data.id}"> <i class="icon-ok"></i> 开通服务</button>
					                        	</c:when>
					                        	<c:otherwise>
					                        		<button type="button" onclick="openOrCloseSubmit(this.value,'0');" class="btn btn-danger btn-sm" title="停止服务" style="width: 80px;" value="${data.id}"> <i class=" icon-remove"></i> 停止服务</button>
					                        	</c:otherwise>
					                        </c:choose>
					                    </td>
					                </tr>
		                        </c:forEach>
								</tbody>
							</table>

						</div>
					</div>
				</form>

			</div>
			<!--右边内容结束-->
		</div>
	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-body">
					<div style="padding:0 15px; margin-bottom:15px;">
						<span>科室名称：</span><input type="text" value=""
							style=" padding:0 10px;">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-toggle="modal"
						data-target="#myModal">取消</button>
					<button type="button" class="btn btn-primary">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/major/admin.js"></script>

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>