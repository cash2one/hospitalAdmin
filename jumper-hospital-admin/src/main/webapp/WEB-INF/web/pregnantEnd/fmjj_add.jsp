<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/menu.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/daterangepicker-bs3.css">
<!DOCTYPE html>
<html>
<head>
<title>分娩结局</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
   <style type="text/css">
	html{overflow-x: scroll;}
	body {min-width: 1660px;overflow-y: hidden;}
	.message-dialog{width: 800px;height: 500px;}
 	*{padding: 0; margin: 0; }
	.panel{margin-bottom:0px;}
	.modal {
    position: fixed;
    top: 20%;
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
    width:50%;
    margin: 30px auto;
}
.modal-header {
    min-height: 16.42857143px;
    padding: 15px;
    border-bottom: 1px solid #fff;
}
input,select{ height:25px; padding:0 5px;}
input[type="radio"],input[type="checkbox"]{ margin:0;}
label{margin-right:10px;}
.gw_btn { width:280px;}
.cha_lab label{margin-right:15px;}
</style>
</head>

<body style="background: #F0F0F0;">
<div>
            <div class="row row-offcanvas row-offcanvas-right" style="margin-top: 20px;">                              
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
		                       <ul class="page-sidebar-menu">
		                          <li class="active"><a
											href="${pageContext.request.contextPath}/pregnant/archiveIndex">
												<i class="icon-plus-sign-alt"></i>&nbsp; <span class="title">档案管理</span>
										</a>
										</li>
		                       </ul>
	                       
	                    </div>

<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
		<div class="panel" >
        	<div class="gw_rote"> <span class="float-left" style="font-size:16px;">档案管理 >档案详情 > <a style=" color:#ff7ea5">分娩结局-添加</a></span></div>
        	<div class="panel-body">	
                <div class="panel panel-default" style="margin:15px 0px;">
                <div class="panel-body">
                        <table class="gw_xx">
                                <tbody>
	                           		<tr>
										<td colspan="4">姓名： ${userBaseInfo.name }</td>
										<td colspan="4">联系电话：${userBaseInfo.mobile }</td>
										<td colspan="4">末次月经：${userBaseInfo.lastPeriod }</td>
										<td colspan="4">预产期：  <fmt:formatDate value="${userBaseInfo.pregnantDate }" pattern="yyyy-MM-dd"/></td>
									</tr>
									<tr>
										<td colspan="4">当前孕周：${pregnantWeek }周 ${pregnantDay }天</td>
										<td colspan="4">状态：
											<c:if test="${userBaseInfo.pregnancyState==0 }">怀孕中</c:if>
											<c:if test="${userBaseInfo.pregnancyState==1 }">已分娩</c:if>
										</td>
										<td colspan="4">分娩日期：${userBaseInfo.deliveryTime }</td>
									</tr>
                        	</tbody>
                     </table>
                </div>
            </div>
            <div class="panel panel-default float-left" style="margin:15px 0px;width: 100%;">
                <div class=" float-left" style="width:100%;">
                        <%@ include file="/WEB-INF/web/pregnantEnd/fmjj_url.jsp"%>
                </div>

             <div id="cont_roll" class="panel panel-default float-left" style=" margin:15px;width: 98%;">
                 <form id="submitForm" method="post">
                	<div class="weight-title">基本情况</div>
                	<input type="hidden" value="${archiveId}" id="archiveId" />
					<input type="hidden" value="${archiveBaseId}" id="archiveBaseId" /><!-- 档案id -->
                	<input type="hidden" name="id" id="id" value="${delivery.id }">
                    <input type="hidden" name="userId" id="userId" value="${userId }">
                    <input type="hidden" name="type" value="add">
                    <div class="panel-body">
                        <table class="gw_xx">
                                    <tbody>
                                    <tr>
                                        <td width="35%">助产机构：<input  type="text" name="midwifery" datatype="*1-30" errormsg="助产机构至少1个字符,最多30个字符！"  ></td>
                                        <td width="30%">住院号：   <input  type="text" name="inpatientNumber" datatype="*1-20" errormsg="助产机构至少1个字符,最多20个字符！" ></td>
                                        <td width="35%">产后修养地址：
	                                        	<select name="restProvince" id="provinceId" style="width:90px">
	                                                <c:forEach items="${provinces}" var="province" >
	                                                	<option value="${province.id}">${province.proName }</option>
	                                                </c:forEach>
	                                            </select> 
	                                            <select name="restCity" id="cityId" style="width:90px">
	                                            </select>
	                                            <input style="width:20%" type="text" placeholder="请输入详细地址" name="restAddress">
                                        </td>
                                    </tr>
                                   
                                    <tr>
                                        <td>分娩时间：
                                        	<input style="width:40%;" placeholder="日期选择" name="deliveryTime" readonly="readonly"
                                        		onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',max:laydate.now()})" >
                                        </td>
                                        <td>分娩孕周：孕 
	                                        <input style="width:50px"  type="text" name="deliveryWeek" datatype="n0-11" errormsg="请输入数字" min="0"   ignore="ignore" > 周 
	                                        <input style="width:50px"  type="text" name="deliveryDay" datatype="n0-11"  errormsg="请输入数字" min="0"   ignore="ignore" >天</td>
                                        <td>胎数：
                                        	<label><input type="radio" name="tire" value="1" checked >单胎</label> 
                                            <label><input type="radio" name="tire" value="2">双胎</label>
                                            <label><input type="radio" name="tire" value="3">多胎</label>
                                            <input type="text" placeholder="请填写胎数" name="tireRemark" datatype="n0-11" errormsg="请输入数字"   ignore="ignore" >
                                        </td>
                                    
                                    </tr>
                                    <tr>
                                        <td>分娩方式：
                                        	<label><input type="radio" name="deliveryWay" value="1" checked>顺产</label> 
                                            <label><input type="radio" name="deliveryWay" value="2">胎吸</label>
                                            <label><input type="radio" name="deliveryWay" value="3">臀助产</label>
                                            <label><input type="radio" name="deliveryWay" value="4">臀牵引</label>
                                            <label><input type="radio" name="deliveryWay" value="5">产钳</label>
                                            <label><input type="radio" name="deliveryWay" value="6">剖宫产</label> 
                                        </td>
                                        <td>手术指征：<input  type="text" placeholder="请输入手术指征" name="operIndication"></td>
                                        <td>胎盘娩出：
                                        	<label><input type="radio" name="deliveryPlacenta" value="1" checked>自然娩出</label> 
                                            <label><input type="radio" name="deliveryPlacenta" value="2">人工剥离胎盘</label> 
                                        </td>
                                    </tr> 
                                    <tr>
                                        <td>胎盘情况：
                                        	<label><input type="radio" name="placentalState" value="1" checked>胎盘完整</label> 
                                            <label><input type="radio" name="placentalState" value="2">胎盘残留</label>
                                            <input  type="text"  name="placentalStateRemark" datatype="n0-11" errormsg="请输入数字"   ignore="ignore" > cm
                                        </td>
                                        <td colspan="2">会阴情况：
                                        	<label><input type="radio" name="perinealCondition" value="1" checked>完整</label> 
                                            <label><input type="radio" name="perinealCondition" value="2">切开</label>
                                            <label><input type="radio" name="perinealCondition" value="3">I级撕裂</label>
                                            <label><input type="radio" name="perinealCondition" value="4">II级撕裂</label>
                                            <label><input type="radio" name="perinealCondition" value="5">III级撕裂</label>
                                            <label><input type="radio" name="perinealCondition" value="6">IV级撕裂</label>
                                        </td>
                                     </tr>
                                     <tr>
	                                    <td>产后并发症：
                                        	<label><input type="radio" name="postCompli" value="0" class="checkCompli_0"  checked >无</label> 
                                            <label><input type="radio" name="postCompli" value="1" class="checkCompli_1">有</label>
                                            <input  type="text" placeholder="请填写" name="postCompliRemark" class="noCompli" disabled="disabled"> 
                                        </td>
                                    </tr>
                                    <tr>
	                                    <td colspan="3" >产后2小时：
                                        	<label>出血量</label><input type="text" name="bleedAmount" datatype="n0-4" errormsg="请输入数字"  ignore="ignore"  >ml 
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <label>血压</label>
                                            <input type="text" name="diastolic" placeholder="输入舒张压" datatype="n0-11" errormsg="请输入数字"  ignore="ignore"   >--
                                            <input type="text" name="systolic" placeholder="输入收缩压" datatype="n0-11" errormsg="请输入数字"  ignore="ignore"  >mmHg
                                        </td>
                                    </tr>
                                    <tr>
                                    	<td colspan="2" >总产程
                                    		 &nbsp;&nbsp;&nbsp;&nbsp;
                                    		<label><input type="text" name="produceHour" datatype="n0-2" errormsg="时、分不超过2位字符"  ignore="ignore">小时</label>
                                    		 &nbsp;&nbsp;&nbsp;&nbsp;
                                    		<label><input type="text" name="produceMin"  datatype="n0-2" errormsg="时、分不超过2位字符"  ignore="ignore">分</label>
                                    	</td>
                                    </tr>                                 
                            </tbody>
                         </table>
                     </div>
                    
                    <div class="weight-title">新生儿情况</div>
                    <div class="panel-body">
                        <table class="gw_xx">
                                    <tbody>
                                    <tr>
                                        <td >性别：
                                        	<label><input type="radio" name="babySex" value="1" checked>男</label> 
                                            <label><input type="radio" name="babySex" value="2">女</label>
                                            <label><input type="radio" name="babySex" value="0">其他</label>
                                        </td>
                                        <td >出生体重：<input style="width:100px;"  type="text" name="babyWeight" datatype="/^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/" errormsg="请输入数字类型"  ignore="ignore" > g</td>
                                        <td >身长：<input style="width:100px;"  type="text" name="babyHeight" datatype="/^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/" errormsg="请输入数字类型"  ignore="ignore" > cm</td>
                                    </tr>                                   
                            </tbody>
                         </table>
                     </div>
                    
                    <div class="weight-title">出生时情况</div>
                     <div class="panel-body mod_btn" >
                     	<table class="gw_xx ">
                                    <tbody>
                                    <tr>
                                        <td>Apgar评分：
                                        	1分钟 <input style="width:80px;" type="text" name="apgar1" datatype="n0-3" errormsg="评分不超过3位数字字符"  ignore="ignore" > 分
                                            <span class="kong">5分钟 <input style="width:80px;" type="text" name="apgar2" datatype="n0-3" errormsg="评分不超过3位数字字符"  ignore="ignore" > 分</span>
                                            <span class="kong">10分钟 <input style="width:80px;" type="text" name="apgar3" datatype="n0-3" errormsg="评分不超过3位数字字符"  ignore="ignore" > 分</span>
                                        </td>
                                        <td>胎儿状况：
                                        	<label><input type="radio" name="fetalCondition" value="1" checked>早产</label> 
                                            <label><input type="radio" name="fetalCondition" value="2">足月产</label>
                                            <label><input type="radio" name="fetalCondition" value="3">过期产</label>
                                            <label><input type="radio" name="fetalCondition" value="4">活产</label>
                                            <label><input type="radio" name="fetalCondition" value="5">死胎</label>
                                            <label><input type="radio" name="fetalCondition" value="6">死产</label>
                                        </td>
                                       
                                    </tr>
                                    <tr>
                                        <td>新生儿死亡：<input style="width:200px;"  placeholder="日期选择" name="babyDeadTime" readonly="readonly"
                                          onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',max:laydate.now()})"  >
                                        </td>
                                        <td>死亡原因：<input style="width:55%" type="text" name="deadReason"></td>
                                    </tr>
                                    <tr>
                                        <td>出生缺陷：
                                        	<label><input style="width:10px;" type="checkbox" name="birthDefect" value="神经管畸形"> 神经管畸形</label>
                                            <label><input style="width:10px" type="checkbox" name="birthDefect" value="唇腭裂"> 唇腭裂</label>
                                            <label><input style="width:10px" type="checkbox" name="birthDefect" value="先天愚型"> 先天愚型</label>
                                            <label><input style="width:10px" type="checkbox" name="birthDefect" value="肢体畸形"> 肢体畸形</label>
                                            <input  type="text" placeholder="请填写其他" name="birthDefectRemark"> 
                                        </td>
                                        <td>窒息：
                                        	<label><input type="radio" name="choke" value="0" checked>无</label> 
                                            <label><input type="radio" name="choke" value="1">有</label>
                                        </td>
                                        <td>窒息程度：
                                        	<label><input type="radio" name="chokeDegree" value="1" checked>轻</label> 
                                            <label><input type="radio" name="chokeDegree" value="2">重</label>
                                        </td>
                                    </tr>
                            </tbody>
                         </table>
                    </div>
                    
                    <div class="weight-title">诊断</div>
                    <div class="gw_jy">
                    	<div class="cbzd">
                        	<p class="float-left">诊断内容：</p>
                        	<p><textarea class="form-control" rows="3" placeholder="不超过100字" name="diagnosticContent"></textarea></p>
                       </div>
                    
                    <div class="panel-body">
                        <table class="gw_xx">
	                         <tbody>
	                                <tr>
	                                    <!--<td ><span style="color:red; font-size:18px;">*</span>检查医生： <input style="width:80px" type="text" ></td>-->
	                                    <td >登记机构：<input type="text" name="registerAgency"></td> 
	                                    <td >登记人员：<input type="text" name="registerPerson"></td>
	                                    
	                                    <td >登记日期：
	                                      <input style="width: 150px;height:28px; display:inline-block" readonly="readonly" name="registerTime" 
	                                      onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss',max:laydate.now()})" >
	                                    </td>
	                                </tr>
	                        </tbody>
	                     </table>
	                </div>
            		</div>     
  				</form>  
            </div>
            <input id="test">
           
            <div class="gw_btn" style=" margin-bottom:20px;">
           	 	<input class="btn btn-default oneline" style="width: 85px; height:40px;padding:0px 0px;" readonly value="取消" type="button">
            	<input class="btn btn-pink" style="width: 85px; height:40px;padding:0px 0px; margin-left:20px" id="submit" value="保存" type="button" >
            </div>
            </div>
            
            
	    </div>
    	
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/laydate/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/delivery/jquery.tips.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/delivery/fmjj.js"></script>
<script type="text/javascript">
  $(function(){
	$(".checkCompli_0").click(function(){
		 $(".noCompli").val("");
		 $(".noCompli").attr("disabled","true");
	});
	$(".checkCompli_1").click(function(){
		 $(".noCompli").removeAttr("disabled");
	});    
  });
</script>
<!-- 引入页尾 -->
</div></div>
</body>
</html>
