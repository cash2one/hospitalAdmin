<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/menu.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/daterangepicker-bs3.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/laydate-v1.1/laydate/laydate.js" ></script>
<style type="text/css">
.btn-group ul li a:hover {
	color: #F66;
}

.modal {
	position: fixed;
	top: 5%;
	right: 0;
	bottom: 0;
	left: 0;
	z-index: 1050;
	display: none;
	overflow: hidden;
	-webkit-overflow-scrolling: touch;
	outline: 0;
}

.modal-dialog {
	margin: 30px auto;
}

.modal-header {
	min-height: 16.42857143px;
	padding: 15px;
	border-bottom: 1px solid #fff;
}

.modal-body table td {
	height: 30px;
	font-size: 16px;
	text-align: left;
}
</style>
<script type="text/javascript">
$(function(){
	$(".yListr ul li em").click(function(){
		$(this).addClass("yListrclickem").siblings().removeClass("yListrclickem");
	});
});
</script>
<!-- 内容页 -->
 <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
    <ul class="page-sidebar-menu">
       <li class="active">
          <a href="javascript:void(0);">
            <i class="icon-plus-sign-alt"></i>&nbsp;
             <span class="title">档案管理</span>
         </a>
       </li>
    </ul>
</div>
<div class="col-xs-12 col-sm-9">
	<div class="panel panel-default">
		<div class="panel-body">
			<button type="button" class="btn btn-info add-order" onclick="window.location.href='${pageContext.request.contextPath}/pregnant/archiveBaseInfo'">
				<i class="icon-plus"></i> 新建档案
			</button>
			<form action="${pageContext.request.contextPath}/pregnant/archiveIndex" method="post" id="archiveSearchForm">
			<div class="col-lg-3" style="float:right">
				<div class="input-group">
					<input class="form-control" name="name"
						placeholder="姓名" value="${name }" type="text"> <span
						class="input-group-btn">
						<button class="btn btn-danger time-search-btn" type="button" id="serchNameBtn">
							<i class="icon-search"></i>
						</button> </span>
				</div>
			</div>
			<div class="clearFix"></div>
			<hr style="margin:20px 0px;"/>
				<div class="yListr float-left" id="archiveStateChooseBtn">
					<ul>
						<li>
							<em <c:if test="${empty isFinished }">class="yListrclickem"</c:if>  val="">全部</em>
						 	<em <c:if test="${isFinished==1 }">class="yListrclickem"</c:if> val="1">已建档库</em> 
						 	<em <c:if test="${isFinished==0 }">class="yListrclickem"</c:if> val="0">预建档库</em>
						 	<input type="hidden" name="isFinished"  value="${isFinished }"/>
						</li>
						<li>
							<em <c:if test="${empty pregnancyState }">class="yListrclickem"</c:if> val="">全部</em> 
							<em <c:if test="${pregnancyState==0 }">class="yListrclickem"</c:if> val="0">怀孕中</em> 
							<em <c:if test="${pregnancyState==1 }">class="yListrclickem"</c:if> val="1">已分娩</em>
							<input type="hidden" name="pregnancyState" value="${pregnancyState}"/>
						</li>
						<!-- <li><em class="yListrclickem">全部</em> <em>轻度高危</em> <em>中度高危</em>
							<em>重度高危</em></li> -->
						<li>
							<em <c:if test="${empty followUpState }">class="yListrclickem"</c:if> val="">全部</em> 
							<em <c:if test="${followUpState==1 }">class="yListrclickem"</c:if> val="1">未安排随访</em> 
							<em <c:if test="${followUpState==2 }">class="yListrclickem"</c:if> val="2">已安排随访</em>
							<em <c:if test="${followUpState==3 }">class="yListrclickem"</c:if> val="3">已随访</em>
							<input type="hidden" name="followUpState" value="${followUpState }"/>
						</li>
					</ul>
				</div>
					<div class="float-left" style="margin-top:10px;">
						<button type="button" class="btn btn-info time-search-btn" id="archiveTimeBtn"
							title="查询" style="margin-top:20px;float:right">查&nbsp;询</button>
						<div class="input-prepend input-group"
							style="width:400px;margin-top:20px;float:right">
		
							<span class="add-on input-group-addon"> <i
								class="icon-calendar"></i> </span> <input style="width: 150px"
								name="startTime" id="startTime" placeholder="开始时间"
								class="form-control" value="${startTime }" type="text"> <span
								class="add-on input-group-addon"> <i class="icon-calendar"></i>
							</span> <input style="width: 150px" name="endTime" id="endTime"
								placeholder="结束时间" class="form-control" value="${endTime }" type="text">
						</div>
					</div>
				
			<div class="clearFix"></div>

			<hr style="margin:20px 0px;"/>
			<table class="bordered">
				<thead>
					<tr>
						<th>姓名</th>
						<th>档案号</th>
						<th>年龄</th>
						<th>联系电话</th>
						<th>身份证号</th>
						<th>当前孕周</th>
						<th>预产期</th>
						<th>建档日期</th>
						<th>孕期状态</th>
						<!-- <th>高危状态</th> -->
						<th>随访状态</th>
						<th>操作</th>

					</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.result }" var="result">
					<tr>
						<td><input type="hidden" value="${result.id }"/><a href="${pageContext.request.contextPath}/pregnant/showArchiveBaseInfo?archiveId=${result.id}&archiveBaseId=${result.archiveId}">${result.name }</a></td>
						<td>${result.archivesNum }</td>
						<td>${result.age }</td>
						<td>${result.mobile }</td>
						<td>${result.idNo }</td>
						<td>孕${result.week }周${result.day }天</td>
						<td>${result.pregnantDate }</td>
						<td>${result.addTime }</td>
						<td>
							<c:if test="${result.pregnancyState==0 }">怀孕中</c:if>
							<c:if test="${result.pregnancyState==1 }">已分娩</c:if>
						</td>
						<!-- <td></td> -->
						<td>
							<c:if test="${result.followUpState==0 }"></c:if>
							<c:if test="${result.followUpState==1 }">未安排</c:if>
							<c:if test="${result.followUpState==2 }">已安排</c:if>
							<c:if test="${result.followUpState==3 }">已随访</c:if>
							
						</td>
						<td>
							<div class="btn-group">
   									<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
      									操作
                                        <span class="caret"></span>
   									</button>
                                       <ul class="dropdown-menu" role="menu" style="height:auto">
                                       <c:if test="${result.pregnancyState==0 }">
                                       	  <li><a href="${pageContext.request.contextPath}/pregnant/showArchiveBaseInfo?archiveId=${result.id}&archiveBaseId=${result.archiveId}">详情</a></li>
                                          <li><a href="${pageContext.request.contextPath}/pregnant/updatePregnancyState?archiveId=${result.id}&archiveBaseId=${result.archiveId}&pregnancyState=1">标记分娩</a></li>
                                       </c:if>
                                       <c:if test="${result.pregnancyState==1 and result.followUpState==0} ">
                                       		<li><a href="${pageContext.request.contextPath}/pregnant/showArchiveBaseInfo?archiveId=${result.id}&archiveBaseId=${result.archiveId}">详情</a></li>
                                       		<li><a href="${pageContext.request.contextPath}/pregnant/updatePregnancyState?archiveId=${result.id}&archiveBaseId=${result.archiveId}&pregnancyState=0">标记怀孕中</a></li>
                                       		<li><a href="#" data-toggle="modal" data-target="#myModal">安排随访</a></li>
                                       </c:if>
                                       <c:if test="${result.pregnancyState==1 and result.followUpState!=0 and result.followUpState!=1}">
                                       		<li><a href="${pageContext.request.contextPath}/pregnant/showArchiveBaseInfo?archiveId=${result.id}&archiveBaseId=${result.archiveId}">详情</a></li>
                                       </c:if> 
                                       <c:if test="${result.pregnancyState==1 and result.followUpState!=0 and result.followUpState!=2 and result.followUpState!=3}">
                                       		<li><a href="${pageContext.request.contextPath}/pregnant/showArchiveBaseInfo?archiveId=${result.id}&archiveBaseId=${result.archiveId}">详情</a></li>
                                       		<li><a href="#" data-toggle="modal" data-target="#myModal">安排随访</a></li>
                                       </c:if> 
                                       </ul>
								</div>
						</td>
					</tr>
				</c:forEach>

				</tbody>
			</table>
			<jsp:include page="../common/page.jsp"></jsp:include>
			</form>
		</div>


<script type="text/javascript">
        function jumpPage(pageNo) {
            $("#pageNo").val(pageNo);
            $("#archiveSearchForm").submit();
        }
    </script>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<input type="hidden" id="visitPlanArchiveId"/>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel" style="text-align:center;">
					随访安排</h4>
			</div>
			<form action="${pageContext.request.contextPath}/pregnant/addVisitPlan" method="post" id="addVisitPlanForm">
			<div class="modal-body">
				<table id="visitPlanTable">
					<tr>
						<td style="width: 90px;">随访孕妇：</td>
						<td></td>
						
					</tr>
					<tr>
						<td style="width: 90px;">随访机构：</td>
						<td></td>
					</tr>
					<tr id="tb1">
						<td style="width: 90px;">随访医生：</td>
						<td></td>
					</tr>
				</table>
			</div>
			</form>
			<div class="modal-footer">
				<button type="button" id="addVisitPlanBtn" class="btn btn-primary" data-toggle="modal">
					确认</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

	</div>
</div>

<script>
	   $(function () { 
	   	//将所有id属性包含time的字段，初始化日期选择框
			$("input[id*='Time']").each(function(){
				laydate({
				    elem: '#'+$(this).attr("id"), 
				    event: 'focus' ,
					max: laydate.now(),
				});
			});
	   
	   //提交表单
	   $("#serchNameBtn").bind("click",function(){
	   		$("#archiveSearchForm").submit();
	   });
	   $("#archiveTimeBtn").bind("click",function(){
	  	 	$("#archiveSearchForm").submit();
	   });
	   $("#archiveStateChooseBtn ul li").each(function(){
			$(this).find("em").each(function(index){
				$(this).bind("click",function(){
					$(this).parent().find("input").val($(this).attr("val"));
					$("#archiveSearchForm").submit();
				});
			});	   
	   });
	   
	   
	   //点击安排随访的时候，为弹出框随访人赋值
	   $("a[data-toggle='modal']").each(function(){
	   	$(this).bind("click",function(){
	   		var name=$(this).parent().parent().parent().parent().parent().find("td:eq(0)").find("a").text();
	   		var id=$(this).parent().parent().parent().parent().parent().find("td:eq(0)").find("input").val();
	   		$("#myModal").find("table tr:eq(0) td:eq(1)").text(name);
	   		$("#visitPlanArchiveId").val(id);
	   		//加载所有的医院和社康
	   		$.ajax({
	   			url:"queryAllCommunityInfoByHospitalId",
	   			data:{},
	   			type:"post",
	   			success:function(data){
	   				var html="<select name='select1' id='select1' onchange='queryAllCommunityMemberInfoById(this)'>";
	   				for(var i=0;i<data.length;i++){
	   					if(i==0){
	   						html+="<option value="+data[i].id+" selected>"+data[i].name+"</option>";
	   					}else{
	   						html+="<option value="+data[i].id+">"+data[i].name+"</option>";
	   					}
	   					
	   				}
	   				html+="</select>";
	   				$("#visitPlanTable tr:eq(1) td:eq(1)").html(html);
	   			}
	   		});
	   		
	   		//加载本院的所有医生
	   		$.ajax({
	   			url:"queryAllCommunityMemberInfoById",
	   			data:{hospitalId:1},
	   			type:"post",
	   			success:function(data){
	   				var html="<select>";
	   				if(data!=null&&data.length>0){
		   				for(var i=0;i<data.length;i++){
		   					html+="<option value="+data[i].id+">"+data[i].name+"</option>";
		   				}
	   				}else{
	   					html+="<option value=''></option>";
	   				}
	   				html+="</select>";
	   				$("#visitPlanTable tr:eq(2) td:eq(1)").html(html);
	   			}
	   		});
	   	});
	   });
	   
	   //点击确认安排按钮
	   $("#addVisitPlanBtn").bind("click",function(){
	   	var userName=$("#visitPlanTable tr:eq(0) td:eq(1)").text();
	   	var hospitalId="";
	   	var communityId="";
	   	var archiveId=$("#visitPlanArchiveId").val();
	   	$("#visitPlanTable tr:eq(1) td:eq(1) select").find("option").each(function(index1){
	   			if($(this).is(":selected")){
	   				if(index1==0){
	   					hospitalId=$(this).val();
	   				}else{
	   					communityId=$(this).val();
	   				}
	   			}
	   		});
	   	var visiterDoctorId=$("#visitPlanTable tr:eq(2) td:eq(1) select").find("option:selected").val();
	   	var visiterDoctor=$("#visitPlanTable tr:eq(2) td:eq(1) select").find("option:selected").text();
	   	if(visiterDoctor==""){
	   		alert("请选择医生！");
	   	}else{
		   	$.ajax({
		   		url:"addVisitPlan",
		   		data:{userName:userName,hospitalId:hospitalId,communityId:communityId,visiterDoctorId:visiterDoctorId,visiterDoctor:visiterDoctor,archiveId:archiveId},
		   		type:"post",
		   		success:function(){
		   			$("#myModal").modal("hide");
		   			$("#archiveSearchForm").submit();
		   		}
		   	});
	   	}
	   });
	   
	   });
	   
	   function queryAllCommunityMemberInfoById(obj){
	   	 //切换医院或社康的时候重新赋值医生
	   		$("#visitPlanTable tr:eq(2) td:eq(1)").html("");
	   		var hospitalId="";
	   		var communityId="";
	   		var index="";
	   		$(obj).find("option").each(function(index1){
	   			if($(this).is(":selected")){
	   				index=index1;
	   			}
	   		});
	   		if(index==0){
	   			hospitalId=$(obj).val();
	   		}else{
	   			communityId=$(obj).val();
	   		}
	   		//加载本院的所有医生
	   		$.ajax({
	   			url:"queryAllCommunityMemberInfoById",
	   			data:{hospitalId:hospitalId,communityId:communityId},
	   			type:"post",
	   			success:function(data){
	   				var html="<select name='select1' id='select1'>";
	   				for(var i=0;i<data.length;i++){
	   					if(i==0){
	   						html+="<option value="+data[i].id+" selected>"+data[i].name+"</option>";
	   					}else{
	   						html+="<option value="+data[i].id+">"+data[i].name+"</option>";
	   					}
	   				}
	   				html+="</select>";
	   				$("#visitPlanTable tr:eq(2) td:eq(1)").html(html);
	   			}
	   		});
	   }
	</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/moment.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/daterangepicker.js"></script>


<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>
