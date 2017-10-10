<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.jumper.hospital.utils.Consts"%>  
<%@page import="com.jumper.hospital.utils.Const"%> 
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/media/css/service.css?${v }" rel="stylesheet">		
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/service.js?${v }"></script>
<style>
.modal{top:5%;}
</style>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<c:if test="${empty mHospital }">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="service-not-open">
					暂未开通远程监护医院。
					<button type="button" class="btn btn-link open-hospital">现在开通?</button>
				</div>
			</div>
		</div>
	</c:if>

	<c:if test="${!empty mHospital }">
		<div class="panel panel-default">
			<!-- 添加打印模式 -->
			<div class="panel-body">
				<input type="hidden" id="pt" value="${mHospital.hospitalId.printType.type }">
					<div class="panel-head"><img src="${pageContext.request.contextPath}/media/image/check_success.png">系统设置</div>					
				<div class="panel-title">
					<span>打印模式</span>
				</div>
				<div class="time-setting">		
					<input type="radio" name="printType" value="0" checked="checked">常规模式
 					<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" id="cg_print" style="margin-left:20px;height: 36px;font-size: 14px;text-align:center;background-color: #5cb85c;border:0px">查看示例图</button>
					
					<input type="radio" name="printType" value="1" style="margin-left:50px">监护曲线模式
					<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" id="qx_print" style="outline:none;margin-left:20px;height: 36px;font-size: 14px;text-align:center;background-color: #5cb85c;border:0px">查看示例图</button>
					<button  style="margin-left:150px;background-color: #337ab7;height: 36px;color: #fff;width: 80px;vertical-align: middle;border:0px" id="bt_print" >保存修改</button>
				</div>
				
				<!--评分方式  -->
				<div class="panel-title">
					<span>评分方式</span>
				</div>
			    <div class="time-setting">		
					<input type="radio" name="scoringMethod" value="1" <c:if test="${mHospital.scoringMethod==1}">checked="checked"</c:if>>Kreb's
					<input type="radio" name="scoringMethod" value="2" style="margin-left:50px"<c:if test="${mHospital.scoringMethod==2}">checked="checked"</c:if> >改良Fischer
					<input type="radio" name="scoringMethod" value="3" style="margin-left:50px"<c:if test="${mHospital.scoringMethod==3}">checked="checked"</c:if> >三级图形分类评分法(ACOG)
					<button  style="margin-left:150px;background-color: #337ab7;height: 36px;color: #fff;width: 80px;vertical-align: middle;border:0px" id="scoringMethodBtn">保存修改</button>
				</div>
			</div>
			
			
			<!-- 模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width:1100px">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<!-- <h4 class="modal-title" id="myModalLabel">
								模态框（Modal）标题
							</h4> -->
						</div>
						<div class="modal-body">
							<div class="m1">
							<img alt="" src="${pageContext.request.contextPath}/media/image/cgPrint.png" style="width:100%;">
							</div>
							<div class="m2">
							<img alt="" src="${pageContext.request.contextPath}/media/image/qxPrint.png" style="width:100%;">
							</div>							
						</div>						
					</div>
				</div>
			</div>
			
			<div class="panel-body">
<%-- 				<c:forEach var="setting" items="${mHospital.settingList }">
					<c:if test="${setting.type == '常规监护' }">
						<c:set var="normal" value="${setting }"></c:set>
					</c:if>
				</c:forEach> --%>
				
 				<!-- 下面的设置只针对义务妇幼显示设置 -->
 				<c:set var="YWFY_HOSPITAL_ID" value="<%=Const.YWHOSPITAL_ID %>"/>  
				<c:if test="${hospitalInfo.id==YWFY_HOSPITAL_ID}">
				<div class="panel-title">
					<span>报告时效（设置后，在有效时间范围内若待审核报告未审核，则报告自动失效，服务次数将退还病人。）</span>
				</div>
				<div class="instraction">
					<p>有效时间：
						<select id="returnDeadline">
						<option value=''   <c:if test="${mHospital.returnDeadline}==null">selected ="selected" </c:if> >无限</option>
					    <option value='1'  <c:if test='${mHospital.returnDeadline==1}'>selected ="selected" </c:if> >1</option> 
						<option value='2'  <c:if test='${mHospital.returnDeadline=="2"}'>selected ="selected" </c:if> >2</option>
					    <option value='3'  <c:if test='${mHospital.returnDeadline=="3"}'>selected ="selected" </c:if> >3</option>
						<option value='4'  <c:if test='${mHospital.returnDeadline=="4"}'>selected ="selected" </c:if> >4</option>
					    <option value='5'  <c:if test='${mHospital.returnDeadline=="5"}'>selected ="selected" </c:if> >5</option>
						<option value='6'  <c:if test='${mHospital.returnDeadline=="6"}'>selected ="selected" </c:if> >6</option>
					    <option value='7'  <c:if test='${mHospital.returnDeadline=="7"}'>selected ="selected" </c:if> >7</option>
						<option value='8'  <c:if test='${mHospital.returnDeadline=="8"}'>selected ="selected" </c:if> >8</option>
					    <option value='9'  <c:if test='${mHospital.returnDeadline=="9"}'>selected ="selected" </c:if> >9</option>
						<option value='10' <c:if test='${mHospital.returnDeadline=="10"}'>selected ="selected" </c:if> >10</option>
					    <option value='11' <c:if test='${mHospital.returnDeadline=="11"}'>selected ="selected" </c:if> >11</option>
						<option value='12' <c:if test='${mHospital.returnDeadline=="12"}'>selected ="selected" </c:if> >12</option>
					    <option value='13' <c:if test='${mHospital.returnDeadline=="13"}'>selected ="selected" </c:if> >13</option>
						<option value='14' <c:if test='${mHospital.returnDeadline=="14"}'>selected ="selected" </c:if> >14</option>
					    <option value='15' <c:if test='${mHospital.returnDeadline=="15"}'>selected ="selected" </c:if> >15</option>
						<option value='16' <c:if test='${mHospital.returnDeadline=="16"}'>selected ="selected" </c:if> >16</option>
					    <option value='17' <c:if test='${mHospital.returnDeadline=="17"}'>selected ="selected" </c:if> >17</option>
						<option value='18' <c:if test='${mHospital.returnDeadline=="18"}'>selected ="selected" </c:if> >18</option>
					    <option value='19' <c:if test='${mHospital.returnDeadline=="19"}'>selected ="selected" </c:if> >19</option>
						<option value='20' <c:if test='${mHospital.returnDeadline=="20"}'>selected ="selected" </c:if> >20</option>
					    <option value='21' <c:if test='${mHospital.returnDeadline=="21"}'>selected ="selected" </c:if> >21</option>
						<option value='22' <c:if test='${mHospital.returnDeadline=="22"}'>selected ="selected" </c:if> >22</option>
						<option value='23' <c:if test='${mHospital.returnDeadline=="23"}'>selected ="selected" </c:if> >23</option>
						<option value='24' <c:if test='${mHospital.returnDeadline=="24"}'>selected ="selected" </c:if> >24</option>
						</select>&nbsp;&nbsp;小时
						<button style="margin-left:50px;background-color: #337ab7;height: 36px;color: #fff;width: 80px;vertical-align: middle;border:0px" id="SaveReturnDeadline">保存修改</button>
					</p>
					
					<!-- <p><span>短信通知</span><span>持续异常</span><span><button onclick="layerTit()">短信通知名单（）</button></span></p> -->
				</div>
				<div class="panel-title">
					<span>胎心预警（设置后，胎心超出设置的范围时，系统将进行预警和记录）</span>
					
				</div>
				<div class="instraction">
					<p>
						报警上限：
						<input type="number" min="160" max="190" step="10" editable="false" value="${ mHospital.upperLimit}" onkeypress="javascript:return false" style="width:80px" id="upperLimit">
						报警下限：
						<input type="number" min="90" max="120" step="10" editable="false" value ="${ mHospital.lowerLimit}" onkeypress="javascript:return false" style="width:80px" id="lowerLimit">
						<button style="margin-left:50px;background-color: #337ab7;height: 36px;color: #fff;width: 80px;vertical-align: middle;border:0px" id="bt_warning">保存修改</button>
					</p>
					<p>
						
						<input type="checkbox" id="checkboxId" name="checkName" <c:if test="${ mHospital.openMsg == 1}">checked="checked"</c:if>/>短信通知
						<span style="margin-left:25px">持续异常</span>
						<select id="sel_minute" <c:if test="${ mHospital.openMsg == 0}"> style="background-color: #CCC;"; disabled="disabled"</c:if>>
							<option value='0' <c:if test="${mHospital.abnormalTime=='1'}">selected="selected"</c:if>>1</option>
							<option value='1' <c:if test="${mHospital.abnormalTime=='2'}">selected="selected"</c:if>>2</option>
							<option value='2' <c:if test="${mHospital.abnormalTime=='3'}">selected="selected"</c:if>>3</option>
							<option value='3' <c:if test="${mHospital.abnormalTime=='4'}">selected="selected"</c:if>>4</option>
							<option value='4' <c:if test="${mHospital.abnormalTime=='5'}">selected="selected"</c:if>>5</option>
							<option value='5' <c:if test="${mHospital.abnormalTime=='6'}">selected="selected"</c:if>>6</option>
							<option value='6' <c:if test="${mHospital.abnormalTime=='7'}">selected="selected"</c:if>>7</option>
							<option value='7' <c:if test="${mHospital.abnormalTime=='8'}">selected="selected"</c:if>>8</option>
							<option value='8' <c:if test="${mHospital.abnormalTime=='9'}">selected="selected"</c:if>>9</option>
							<option value='9' <c:if test="${mHospital.abnormalTime=='10'}">selected="selected"</c:if> >10</option>
							<option value='10' <c:if test="${mHospital.abnormalTime=='11'}">selected="selected"</c:if>>11</option>
							<option value='11' <c:if test="${mHospital.abnormalTime=='12'}">selected="selected"</c:if>>12</option>
							<option value='12' <c:if test="${mHospital.abnormalTime=='13'}">selected="selected"</c:if>>13</option>
							<option value='13' <c:if test="${mHospital.abnormalTime=='14'}">selected="selected"</c:if>>14</option>
							<option value='14' <c:if test="${mHospital.abnormalTime=='15'}">selected="selected"</c:if>>15</option>
							<option value='15' <c:if test="${mHospital.abnormalTime=='16'}">selected="selected"</c:if>>16</option>
							<option value='16' <c:if test="${mHospital.abnormalTime=='17'}">selected="selected"</c:if>>17</option>
							<option value='17' <c:if test="${mHospital.abnormalTime=='18'}">selected="selected"</c:if>>18</option>
							<option value='18' <c:if test="${mHospital.abnormalTime=='19'}">selected="selected"</c:if>>19</option>
							<option value='19' <c:if test="${mHospital.abnormalTime=='20'}">selected="selected"</c:if>>20</option>
						</select>&nbsp;&nbsp;分
						<button style="margin-left:50px;background-color: #337ab7;height: 36px;color: #fff;width: 80px;vertical-align: middle;border:0px" id="bt_warning_message">保存修改</button>
						<button style="margin-left:50px;background-color: #666666;height: 36px;color: #d6d8d8;width: 155px;vertical-align: middle;border:0px" id="" onclick="layerTit()">短信通知名单（<span id="noticeCount"></span>）</button>
					</p>
				</div>
			</c:if>				
				<!-- 	去掉常规监护 -->
				<%-- 
				<div class="panel-head">
					<c:if test="${(!empty normal) && normal.isOpen }">
						<div class="float-left"><img src="${pageContext.request.contextPath}/media/image/check_success.png">常规监护</div>
						<div class="float-right">已开通</div>
					</c:if>
					<c:if test="${empty normal || !normal.isOpen }">
						<div class="float-left"><img src="${pageContext.request.contextPath}/media/image/check_failed.png">常规监护</div>
						<div class="float-right">未开通</div>
					</c:if>
				</div> 
				<div class="panel-title">
					<span>介绍</span>
				</div>
				<div class="instraction">
					<p>1.孕妇连接设备进行监测，监测完成后将监测数据上传到医院。</p>
					<p>2.医护人员查看孕妇上传的数据，与孕妇进行交流，给予解答建议。</p>
				</div>
				<hr />
				<div class="panel-title-both">
					<div class="float-left both-left">
						<span>金额设置</span>
					</div>
					<div class="float-right both-right">
						<button type="button" class="btn btn-link option-add" tag="0" value="${normal.id }"><i class="icon-pencil"></i>&nbsp;新增</button>
					</div>
				</div>
				<div class="clearFix"></div>
				<div class="panel-detail">
					<ul class="service-ul">
						<c:forEach var="option" items="${normal.options }">
							<li>
								<div class="service-panel">
									<c:if test="${option.price > 0 }">
										<label>${option.number }次${option.price }元</label>
									</c:if>
									<c:if test="${option.price <= 0 }">
										<label>免费试用(${option.number }次)</label>
									</c:if>
								</div>
								<div class="service-delete" style="position:relative;top: -100px;left: 60px;">
									<button type="button" class="btn btn-link option-delete" style="color:grey" value="${option.id }"><i class="icon-remove"></i></button>
								</div>
								<div class="service-edit" style="position:relative">
									<button type="button" class="btn btn-link option-update" tag="0" value="${option.id }|${option.number }|${option.price }|${normal.id }"><i class="icon-pencil"></i></button>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="clearFix"></div>
				<div class="panel-btn">
					<c:if test="${empty normal || !normal.isOpen }">
						<button type="button" class="btn btn-danger service-open" value="${normal.id }">开通服务</button>
					</c:if>
					<c:if test="${(!empty normal) && normal.isOpen }">
						<button type="button" class="btn btn-success service-close" value="${normal.id }">关闭服务</button>
					</c:if>
				</div>
			</div>
			--%>
			
			<div class="panel-body">
				<c:forEach var="setting" items="${mHospital.settingList }">
					<c:if test="${setting.type == '实时监护' }">
						<c:set var="current" value="${setting }"></c:set>
					</c:if>
				</c:forEach>
	
				<div class="panel-head">
					<c:if test="${(!empty current) && current.isOpen }">
						<div class="float-left"><img src="${pageContext.request.contextPath}/media/image/check_success.png">实时监护</div>
						<div class="float-right">已开通</div>
					</c:if>
					<c:if test="${empty current || !current.isOpen }">
						<div class="float-left"><img src="${pageContext.request.contextPath}/media/image/check_failed.png">实时监护</div>
						<div class="float-right">未开通</div>
					</c:if>
				</div>
				<div class="panel-title">
					<span>介绍</span>
				</div>
				<div class="instraction">
					<p>1.孕妇连接设备进行胎心监测，监测情况实时同步到医院。</p>
					<p>2.监测过程中，医护人员可实时查看胎心监测情况，及时发现孕妇异常情况，并给予指导提醒。</p>
					<p>3.监测结束后，医护人员可根据孕妇监测的胎心数据，给予解答建议。</p>
				</div>
				<hr />
				<div class="panel-title-both">
					<div class="float-left both-left">
						<span>金额设置</span>
					</div>
					<div class="float-right both-right">
						<button type="button" class="btn btn-link option-add" tag="1" value="${current.id }"><i class="icon-pencil"></i>&nbsp;新增</button>
					</div>
				</div>
				<div class="clearFix"></div>
				<div class="panel-detail">
					<ul class="service-ul">
						<c:forEach var="option" items="${current.options }">
							<li>
								<div class="service-panel">
									<c:if test="${option.price > 0 }">
										<label>${option.number }次${option.price }元</label>
									</c:if>
									<c:if test="${option.price <= 0 }">
										<label>免费试用(${option.number }次)</label>
									</c:if>
								</div>
								<div class="service-delete" style="position:relative;top: -100px;left: 60px;">
									<button type="button" class="btn btn-link option-delete" style="color:grey" value="${option.id }"><i class="icon-remove"></i></button>
								</div>
								<div class="service-edit" style="position:relative">
									<button type="button" class="btn btn-link option-update" tag="1" value="${option.id }|${option.number }|${option.price }|${current.id }"><i class="icon-pencil"></i></button>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="clearFix"></div>
				<div class="panel-title">
					<span>时间设置</span>
				</div>
				
				<!-- 老的时间设置 -->
				<%--
 				<div class="time-setting">
					<select name="startDay" id="startDay" class="form-control" style="width:140px;display:inline">
             	   		<c:forEach var="startW" items="${week }">
             	   			<option value="${startW.key }" <c:if test="${mHospital.startDay == startW.value}">selected</c:if>>${startW.value }</option>
             	   		</c:forEach>
                	</select>
                	<select name="endDay" id="endDay" class="form-control" style="width:140px;display:inline">
             	   		<c:forEach var="endW" items="${week }">
             	   			<option value="${endW.key }" <c:if test="${mHospital.endDay == endW.value}">selected</c:if>>${endW.value }</option>
             	   		</c:forEach>
                	</select>
                	&nbsp;&nbsp;-&nbsp;&nbsp;
                	<select name="startTime" id="startTime" class="form-control" style="width:140px;display:inline">
             	   		<c:forEach var="startT" items="${time }">
             	   			<option value="${startT.desc }" <c:if test="${mHospital.startTime == startT.desc}">selected</c:if>>${startT.desc }</option>
             	   		</c:forEach>
                	</select>
                	<select name="endTime" id="endTime" class="form-control" style="width:140px;display:inline">
             	   		<c:forEach var="endT" items="${time }">
             	   			<option value="${endT.desc }" <c:if test="${mHospital.endTime == endT.desc}">selected</c:if>>${endT.desc }</option>
             	   		</c:forEach>
                	</select>
                	<button class="btn btn-primary update-time" value="${mHospital.id }" style="margin-left:20px">修改时间</button>
				</div> 
				 --%>
				<!-- 新时间设置 -->
				<div class="time-setting">
						<c:forEach var="mntTime" varStatus="i" items="${monitorTime}">
						   <c:if test="${mntTime.isFirstItem=='Y'}">
						     <div id="currentDay_${mntTime.dayOfWeek}" dayofweek=${mntTime.dayOfWeek} style="width:140px;display:inline;border: 1px;">
						   </c:if>
	             	   			<div timeId="${mntTime.id}"  dataState="persisent" class="todayTimeInterval" style="display:inline" >
		             	   			<div  style="width:140px;display:inline" >
			             	   			<c:forEach var="startW" items="${week }">
			             	   						<c:if test="${mntTime.dayOfWeek == startW.key && mntTime.isFirstItem=='Y' }">${startW.value}:</c:if>
			             	   						<c:if test="${mntTime.dayOfWeek == startW.key && mntTime.isFirstItem=='N' }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if> 
			             	   			</c:forEach>
		             	   			</div>
	             	   				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						             <c:forEach var="startW" items="${week }">
							             <c:if test="${mntTime.dayOfWeek == startW.key && mntTime.isFirstItem=='Y' }">
		             	   					<input type="checkbox" id="is_enabled" ${mntTime.isEnabled==true?'checked=checked':''} />
		             	   				 </c:if>
	             	   				 </c:forEach>
		            	   			<select currentDay="${mntTime.dayOfWeek}"  timeId="${mntTime.id}_start"  class="form-control" style="width:160px;display:inline">
		            	   				<c:forEach var="startT"  items="${time}">
		            	   					<option   value="${startT.desc }" <c:if test="${mntTime.startTime == startT.desc}">selected</c:if>>${startT.desc }</option>
		            	   				</c:forEach>
		            	   			</select>
		            	   			&nbsp;&nbsp;-&nbsp;&nbsp;
		            	   			<select currentDay="${mntTime.dayOfWeek}"  timeId="${mntTime.id}_end" class="form-control" style="width:160px;display:inline">
		            	   				<c:forEach var="startT" items="${time }">
		            	   					<option  value="${startT.desc }" <c:if test="${mntTime.endTime == startT.desc}">selected</c:if>>${startT.desc }</option>
		            	   				</c:forEach>
		            	   			</select>
		         	   			    <c:forEach var="startW" items="${week }">
		         	   						<c:if test="${mntTime.dayOfWeek == startW.key && mntTime.isFirstItem=='Y' }">
			         	   						<button dayOfWeek="${mntTime.dayOfWeek}"  hospitalId="${mHospital.id }" class="btn  glyphicon glyphicon-plus add-time" style="margin-left:20px"></button>
			         	   						<button dayOfWeek="${mntTime.dayOfWeek}"  hospitalId="${mHospital.id }" class="btn btn-primary modify-time" style="margin-left:20px">保存修改</button>
		         	   						</c:if>
		         	   						<c:if test="${mntTime.dayOfWeek == startW.key && mntTime.isFirstItem=='N' }">
		         	   							<button timeId="${mntTime.id}"  dayOfWeek="${mntTime.dayOfWeek}"   hospitalId="${mHospital.id }" class="btn glyphicon glyphicon-minus delete-time" style="margin-left:20px"></button>
		         	   						</c:if>
		         	   				</c:forEach>
		           	   			</br>
             	   			 </div>
	             	   	 <c:if test="${mntTime.isLastItem=='Y'}">
             	   	</div>
             	   		 </c:if>
             	   	</c:forEach>
				</div>
				
				<div class="panel-btn">
					<c:if test="${empty current || !current.isOpen }">
						<button type="button" class="btn btn-danger service-open" value="${current.id }">开通服务</button>
					</c:if>
					<c:if test="${(!empty current) && current.isOpen }">
						<button type="button" class="btn btn-success service-close" value="${current.id }">关闭服务</button>
					</c:if>
				</div>
			</div>

			<div class="clearFix"></div>
			<div class="panel-title">
				<span>孕期诊所设置</span>
			</div>
			<!--  院内胎心暂时不要
			<div class="box boxMargin fl" style="display: block;">
				<p class="bIcon fl">
					<img src="${pageContext.request.contextPath}/media/image/yntx.png" alt="图片"/>
				</p>
				<div class="showTitle fl">
					<h3>院内胎心</h3>
					<p class="showMessage">
						简介：开启服务后，医生可通过孕期诊所App查看孕妇的院内监护报告，并通过手机进行判读。
					</p>
					<c:choose>
						<c:when test="">
							<a id="innerTubeHeart" state="1" class="subA ">已开启</a>
						</c:when>
						<c:otherwise>
							<a id="innerTubeHeart" state="0" class="subA aActive">未开启</a>
						</c:otherwise>
					</c:choose>
				</div>	
			</div>
			-->
			<div class="box fl">
				<p class="bIcon fl">
					<img src="${pageContext.request.contextPath}/media/image/txjd.png" alt="图片"/>
				</p>
				<div class="showTitle fl">
					<h3>胎心解读</h3>
					<p class="showMessage">
						简介：开启服务后，医生可通过孕期诊所App为孕妇提供私人解读报告的服务。
					</p>
					<c:choose>
						<c:when test="${mHospital.hospitalId.isDoctorNst}">
							<a id="fetalInterpretation" state="1"  class="subA">已开启</a>
						</c:when>
						<c:otherwise>
							<a id="fetalInterpretation" state="0" class="subA aActive">未开启</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<p class="clear"></p>
			
			
			<div class="panel-body">
				<c:forEach var="setting" items="${mHospital.settingList }">
					<c:if test="${setting.type == '院内监护' }">
						<c:set var="inside" value="${setting }"></c:set>
					</c:if>
				</c:forEach>
	
				<div class="panel-head">
					<c:if test="${(!empty inside) && inside.isOpen }">
						<div class="float-left"><img src="${pageContext.request.contextPath}/media/image/check_success.png">院内监护</div>
						<div class="float-right">已开通</div>
					</c:if>
					<c:if test="${empty inside || !inside.isOpen }">
						<div class="float-left"><img src="${pageContext.request.contextPath}/media/image/check_failed.png">院内监护</div>
						<div class="float-right">未开通</div>
					</c:if>
				</div> 
				
				<div class="panel-title-both" style="margin-top:10px">
					<div class="float-left both-left">
						<span>金额设置</span>
					</div>
					<c:if test="${empty inside.options }">
						<div class="float-right both-right">
							<button type="button" class="btn btn-link option-add" tag="2" value="${inside.id }"><i class="icon-pencil"></i>&nbsp;新增</button>
						</div>
					</c:if>
				</div>
				<div class="clearFix"></div>
				<div class="panel-detail">
					<ul class="service-ul">
						<c:forEach var="option" items="${inside.options }">
							<li>
								<div class="service-panel">
									<c:if test="${!empty option.price }">
										<label>${option.number }次${option.price }元</label>
									</c:if>
								</div>
								<div class="service-delete" style="position:relative;top: -100px;left: 60px;">&nbsp;</div>
								<div class="service-edit" style="position:relative">
									<button type="button" class="btn btn-link option-update" tag="2" value="${option.id }|${option.number }|${option.price }|${inside.id }"><i class="icon-pencil"></i></button>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="clearFix"></div>
				<div class="panel-btn">
					<c:if test="${empty inside || !inside.isOpen }">
						<button type="button" class="btn btn-danger service-open" value="${inside.id }">开通服务</button>
					</c:if>
					<c:if test="${(!empty inside) && inside.isOpen }">
						<button type="button" class="btn btn-success service-close" value="${inside.id }">关闭服务</button>
					</c:if>
				</div>
			</div>
			
		</div>
	</c:if>
</div>
<script type="text/javascript">
  getNoticeCount(2);
  function getNoticeCount(status){
	$("#noticeCount").html(0);
	$.ajax({
 		url: "${pageContext.request.contextPath}/hos/getDoctorNoticeByStatus",
 		type:'post',
 		dataType:'json',
 		data:{'status':status},
 		success:function(data){
 			if(data.msg == 1 && data.data != null && data.data.length>0){
 				$("#noticeCount").html(data.data.length);
 			}
 		},
 	});
 }
</script>
<jsp:include page="/WEB-INF/web/hospital/doctorNotice.jsp"></jsp:include>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>