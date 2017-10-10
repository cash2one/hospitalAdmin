<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/syncdata_head.jsp"%> 
<body>
	<!-- 顶部导航栏  start-->
	<div id="menu">
        <ul id="nav">
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/weight?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">体重</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/heartrate?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">胎心</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/sugar?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">血糖</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/pressure?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">血压</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/oxygen?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">血氧</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/bloodfat?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">血脂</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/urine?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">尿液</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/temperature?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">体温</a></li>
<!--             <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/ecg?userId=${params.userId}">心电</a></li>   -->
			<li class="mainlevel active"><a href="${pageContext.request.contextPath}/monitor/fetal?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">胎动</a></li>
        </ul>
    </div>
    <div id="top">
        <div id="table">
			<div id="some"> <div id="some_name">胎动趋势图</div>
			</div>
			<div id="control" style="height: 408px">
	  			<div id="container"> 
	  			</div>
	       	</div>

		</div>
        <div id="table2">
            <div id="some" >
                <div id="some_name"> 胎动数据
                <span type="button" style="width:25px; height:25px; margin-left:10px; " data-toggle="tooltip" 
					 data-placement="top"  title="1、上午、下午、晚上的检定标准<br>
	上午 的定义为：5:00~12:00<br>
	下午 的定义为：12:00~19:00<br>
	晚上 的定义为：19:00~5:00<br><br>
2、12小时胎动数计算公式为：<br>
	（1）（上午胎动次数+下午胎动次数+晚上胎动次数）*4<br>
	（2）（任意两次胎动次数之和）*6<br>
	（3）（任意一次的胎动次数）*12" data-html="true" >
					<img src="${pageContext.request.contextPath}/media/image/syncdata_img/wen.png">
				</span>
			</div>
                 <div id="calendar">
                    <div style="float: left">
                        <ul class="calender_btn2" style="float: left">
                            <li>
                                <input type="button" style="width: 90px;height:20px;margin-left:200px" readonly="readonly" value="当前测量${page.totalCount}次"/>
                            </li>

                        </ul>
                    </div>
                    <div>
                        <ul class="table">
                            <li>
                                <select  id="calendar_choose" style="width: 80px; margin-left: 8px" name="timeFlag">
                                    <option value="">选择时间</option>
                                    <option value="all" <c:if test="${params.timeFlag == 'all' }">selected="selected"</c:if>>全部 </option>
                                    <option value="7"   <c:if test="${params.timeFlag == '7' }"  >selected="selected"</c:if>>近7天</option>
                                    <option value="30"  <c:if test="${params.timeFlag == '30' }" >selected="selected"</c:if>>近30天</option>
                                </select>
                            </li>
                            <form class="submitForm" action="${pageContext.request.contextPath}/monitor/fetal" method="post">
	                            <input type="hidden" id="userId" value="${params.userId}" name="userId">
							    <input type="hidden" id="timeFlag" value="${params.timeFlag}" name="timeFlag">
							    <input type="hidden" id="pageNum" value="${params.pageNum}" name="pageNum">
							    <input type="hidden" name="cssType"  value="${params.cssType}"><!-- 加载样式 -->
							    <input type="hidden" name="archiveId" value="${params.archiveId}"  />
								<input type="hidden" name="archiveBaseId" value="${params.archiveBaseId}" /><!-- 档案id -->
	                            <li class="calendar_one">
			                        <input style="width: 100px" type="text" value="${params.startTime}"  id="date1" name="startTime" placeholder="选择开始时间">
	                            </li >
	                            <li class="calendar_one">
	                          		      到
	                            </li>
	                            <li class="calendar_one">
	                                <input style="width: 100px" type="text" value="${params.endTime}" id="date2" name="endTime" placeholder="选择结束时间">
	                            </li>
	                            <li class="calendar_one">
	                                <input style="width:38px;height:22px;line-height: 18px" type="button" value="确定" id="submitBtn"/>
	                            </li>
							</form>
                        </ul>
                    </div>
                </div>
            </div>
            
            <div  id="box_show" >
                <div id="box">
                    <table id="table1">
                        <thead >
                        <tr  style="background: #fff;">
                            <th>监测时间</th>
                        	<th>当前孕周</th>
                        	<th>上午</th>
                       		<th>下午</th>
                        	<th>晚上</th>
                        	<th>12小时胎动数</th>
                        </tr>
                        </thead>
	                        <tbody class="scrollTbody">
	                        	<c:forEach items="${fetalmoveList }" var="fetal">
	                        		<tr>
	                                    <td data-label="监测时间">${fetal.monitorTime }</td>
	                  					<td data-label="当前孕周">${fetal.pregnantWeek}周${fetal.pregnantDay}天</td>
	                  					<td data-label="上午">${fetal.monitorAm }</td>
	                  					<td data-label="下午">${fetal.monitorPm }</td>
	                  					<td data-label="晚上">${fetal.monitorNight }</td>
	                  					<c:set var="count" value="0"></c:set>
	                  					<c:if test="${fetal.monitorAm>0 }">
	                  						<c:set var="count" value="${count+1 }"></c:set>
	                  					</c:if>
	                  					<c:if test="${fetal.monitorPm>0 }">
	                  						<c:set var="count" value="${count+1 }"></c:set>
	                  					</c:if>
	                  					<c:if test="${fetal.monitorNight>0 }">
	                  						<c:set var="count" value="${count+1 }"></c:set>
	                  					</c:if>
	                  					<c:set var="num" value="0"></c:set>
	                  					<c:if test="${count==1 }">
	                  						<c:set var="num" value="12"></c:set>
	                  					</c:if>
	                  					<c:if test="${count==2 }">
	                  						<c:set var="num" value="6"></c:set>
	                  					</c:if>
	                  					<c:if test="${count==3 }">
	                  						<c:set var="num" value="4"></c:set>
	                  					</c:if>
	                  					
	                  					<td data-label="12小时胎动数">${(fetal.monitorAm+fetal.monitorPm+fetal.monitorNight)*num }</td>
                                  </tr>
	                        	</c:forEach>
	                       </tbody>
                    </table>
                   	<ul id="page_ui">
                    	<input type="text" class="page_text" style="width:30px;height: 23px;" >
                    	<li class="go">GO</li>
                        <li class="pageNum" name="1">首页</li>
                        <li class="up"><<</li>
                        <c:if test="${page.totalPages == 0}">
                        	<li>第0页/共${page.totalPages}页</li> 
                        </c:if>
                        <c:if test="${page.totalPages != 0}">
                            <li>第${page.pageNo}页/共${page.totalPages}页</li> 
                        </c:if>
                        <li class="down">>></li>
                        <li class="pageNum" name="${page.totalPages}">尾页</li>
	                </ul>
                </div>
            </div>

        </div>
    </div>
    <input type="hidden" id="totalData" value="${totalData}">
    <input type="hidden" class="pageTotal" value="${page.totalPages}">
    <input type="hidden" class="fetalmoveJson_now" value="${fetalBuffer}" />
    <input type="hidden" class="fetalmoveJson_twelve" value="${buffer_twelve}" />
    <input type="hidden" class="buffer_time" value="${buffer_time}" />
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/fetal.js"></script>
<script type="text/javascript">
	laydate({
	    elem: '#date1', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
	laydate({
	    elem: '#date2', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
</script>
</body>
</html>