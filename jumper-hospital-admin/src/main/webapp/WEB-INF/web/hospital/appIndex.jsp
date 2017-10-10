<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
.form-control-1 {
  height: 34px;
  padding: 6px 12px;
  font-size: 14px;
  line-height: 1.42857143;
  color: #555;
  background-color: #fff;
  background-image: none;
  border: 1px solid #ccc;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
  -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
  -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
  transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}
</style>
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/service.css">
<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/ajaxfileupload.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/appIndex.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js?${v }"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/message.js?${v }"></script>

<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
		<input type="hidden" name="type" id="userType" value="${type }" />
		<button type="button" class="btn btn-success" id="add" title=" 添加" ><i class="icon-plus"></i> 添加</button>
    	<div class="panel panel-default" style="margin-top: 10px;">
        	<div class="panel-body">
        		<select id="show_mode">
        			<option value="1">按行显示</option>
        			<option value="2">按列显示</option>
        		</select>
				<div class="clearFix"></div>
				<br>
				<input type="hidden" id="weekList" value="${pregnantWeekList}"/>
				<input type="hidden" id="babyList" value="${babyList}"/>
	            <table class="bordered" id="lineShow">
	                <thead>  
	                    <tr>
	                        <th>序号</th>
	                        <th>标题</th>
	                       	<th>图标</th>
	                       	<th>显示</th>
	                       	<th>描述</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <c:forEach items="${upList}" var="data" varStatus="v">
	                	<c:if test="${data.showPosition == 1}">
	                		<tr>
			                    <td>${v.count}</td>
			                    <td>${data.title}</td>
			                    <td><img src="${data.url }" width="32px" height="32px" /></td>
			                    <td>${data.isShow==1?'是':'否' }</td>
			                    <td>${data.description }</td>
			                    <td>
			                    	<a role="button" class="btn btn-success btn-sm" title="编辑详情" href="javascript:edit(${data.id})"><i class="icon-folder-open-alt"></i> 编辑</a>
			                    	<a class="btn btn-danger btn-sm" href="###" onclick="del(${data.id});">删除</a>
			                    </td>
			                </tr>
	                	</c:if>
	                </c:forEach>
	                <c:if test="${upList == null}">
			      		<tr>
			      			<td colspan="6" style="color:red">暂无记录</td>
			      		</tr>
			      	</c:if>
	            </table>
	            <!-- 列显示 -->
	            <table id="columnShow" style="display: none; border-collapse:collapse;" border="1" bordercolor="#a0c6e5">
				  <c:forEach items="${upList}" var="data"  varStatus="status">     
				     <c:if test="${status.count eq 1 || (status.count-1) % 2 eq 0}">    
				      <tr>    
				     </c:if>    
				       <td style="border: solid 1px #a0c6e5; height: 20px;">
				       	<table border="0">
						  <tr>
						    <td>${data.title}</td>
						    <td rowspan="2"><img src="${data.url }" width="32" height="32" /></td>
						  </tr>
						  <tr>
						    <td>${data.description }</td>
						  </tr>
						</table>
				       </td>      
				      <c:if test="${status.count % 2 eq 0 || status.count eq 2}">    
				      </tr>    
				      </c:if>    
				  </c:forEach> 
				</table>
	        </div>
	    </div>
	    <!-- 下面 -->
	    <div class="panel panel-default">
        	<div class="panel-body">
				<div class="clearFix"></div>
				<br>
	            <table>
				  <c:forEach items="${downList}" var="obj"  varStatus="status">     
				     <c:if test="${status.count eq 1 || (status.count-1) % 4 eq 0}">    
				      <tr>    
				     </c:if>    
				       <td>
				       	<table>
			         		<tr>
			         			<td align="center"><img src="${obj.url }" width="32px" height="32px" /></td>
			         		</tr>  
			          		<tr>   
				       			<td align="center">${obj.title}</td>   
				      		</tr>   
			      		</table>  
				       </td>      
				      <c:if test="${status.count % 4 eq 0 || status.count eq 4}">    
				      </tr>    
				      </c:if>    
				  </c:forEach> 
				</table>
	        </div>
	    </div>
</div>

<!-- 模态框（Modal） -->
<a class="btn btn-pink hide myModel" data-toggle="modal" data-target="#desc-confrim"></a>
<div class="modal bs-example-modal-lg" id="desc-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="top:10%">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form id="edit-desc-form" action="${pageContext.request.contextPath}/appIndex/saveAppIndex" method="post">
				<input type="hidden" name="id" id="descId" value="" />
				<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title desc-confirm-title" id="labelTitle">添加</h4>
		      	</div>
		      	<div class="alert alert-danger errorMessage" role="alert" id="errorMessage" style="display:none;"></div>
		      	<input type="hidden" id="id" value="">
				<div class="modal-body desc-confirm-body">
					<div class="form-group" >
						<label class="col-sm-2 control-label inline-label font-normal" >标题：</label>
					    <div class="col-sm-10 float-right">
					        <input type="text" id="title" name="title" class="form-control-1" />
					    </div>
					</div>
					<div class="form-group" style="height: 30px;">
						<label class="col-sm-2 control-label inline-label font-normal" style="float:left;">描述：</label>
					   <div class="input-prepend input-group" style="width:500px;float:left">
							 <input type="text" id="description" name="description" class="form-control-1" />
						</div>
					</div>
					<div class="form-group" style="height: 30px;">
						<label class="col-sm-2 control-label inline-label font-normal" style="float:left;">选择医院：</label>
					   	<div class="input-prepend input-group" style="width:500px;float:left">
                			<select id="selectProvince" class="form-control" style="width:165px;display:inline" onchange="city()">
                			</select>
                			<select id="selectCity" class="form-control" style="width:160px;display:inline;margin-left:5px;" onchange="hospitalInfo()">
                			</select>
                			<select id="selectHospitalInfo" class="form-control" style="width:160px;display:inline;margin-left:5px;">
                			</select>
						</div>
					</div>
					<div class="form-group" style="height: 30px;">
						<label class="col-sm-2 control-label inline-label font-normal" style="float:left;">显示位置：</label>
					   <div class="input-prepend input-group" style="width:500px;float:left">
							 <select id="showPosition" name="showPosition" class="form-control-1">
							 	<option value="1">上</option>
							 	<option value="2">下</option>
							 </select>
						</div>
					</div>
					<div class="form-group" style="height: 30px;">
						<label class="col-sm-2 control-label inline-label font-normal" style="float:left;">是否显示：</label>
					   	<div class="input-prepend input-group" style="width:500px;float:left">
							 <select id="isShow" name="isShow" class="form-control-1">
							 	<option value="0">否</option>
							 	<option value="1">是</option>
							 </select>
						</div>
					</div>
					<div class="form-group" style="height: 30px;">
						<label class="col-sm-2 control-label inline-label font-normal" style="float:left;">图标：</label>
					   	<div class="input-prepend input-group" style="width:500px;float:left">
					   		<input id="hospitalFile" name="file" type="file" style="display:none">
							<input id="dialogHospitalImg" class="form-control-1" name="imgUrl" type="text" value=""  readonly="readonly" datatype="*" nullmsg="医院照片不能为空!" errormsg="医院照片不能为空!">
							<a class="btn btn-info" onclick="$('input[id=hospitalFile]').click();">选择文件</a>
							<span style="color:red">* 尺寸:32x32</span>
                			<img style="width:32px;height:32px" id="hospitalImg">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default desc-confirm-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger" id="push">确定</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>