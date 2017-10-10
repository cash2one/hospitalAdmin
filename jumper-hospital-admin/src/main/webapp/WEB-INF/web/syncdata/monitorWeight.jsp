<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/syncdata_head.jsp"%> 

<body style="">
	<!-- 顶部导航栏  start-->
	<div id="menu">
        <ul id="nav">
            <li class="mainlevel active"><a href="${pageContext.request.contextPath}/monitor/weight?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">体重</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/heartrate?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">胎心</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/sugar?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">血糖</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/pressure?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">血压</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/oxygen?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">血氧</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/bloodfat?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">血脂</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/urine?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">尿液</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/temperature?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">体温</a></li>
<!--             <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/ecg?userId=${params.userId}">心电</a></li>   -->
			<li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/fetal?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">胎动</a></li>
        </ul>
    </div>
    <!-- 顶部导航栏  end-->
    <div id="top">
    	<div id="detail">
    		<div id="user"> <span>孕妇基本信息</span></div>
	    	<div class="user-inpt">
		    	<ul id="all" style="border-bottom: 1px #cccccc dashed;">
<!-- 		    		<li>姓名：<input style="width: 50px;" readonly="readonly" value="${userExtra.realName}"/></li> -->
<!-- 		    		<li>年龄：<input style="width: 20px;" readonly="readonly" value="${userExtra.age}"/></li> -->
<!-- 		    		<li>联系电话：<input style="width: 80px;" readonly="readonly" value="${viewVO.phone}"/></li> -->
		    		<li>孕前体重：<input style="width: 80px;" readonly="readonly" value="${userExtra.weight}kg" id="initWeight" title="${userExtra.weight}"/></li>
		    		<li>孕前身高：<input style="width:80px;" readonly="readonly" value="${userExtra.height}cm"/></li>
		    		<li>孕前BMI：<input style="width: 80px;" readonly="readonly" value="${fn:substring(viewVO.BMI, 0, 5)}" id="bmi"/></li>
                    <li>孕前体重状态：<input style="width: 80px;" readonly="readonly" value="${viewVO.weightState}"/></li>
		    		<li>末次月经：<input style="width: 80px;" readonly="readonly" value="${fn:substring(userExtra.lastPeriod, 0, 11)}"/></li>
		    	</ul>
		    	<ul id="two">
<!-- 		    		<li>预产期：<input  style="width: 70px;"readonly="readonly" value="${viewVO.expectedDate}"/></li> -->
<!-- 		    		<li>当前孕周：<input  style="width: 80px;"readonly="readonly" value="${viewVO.pregnantWeek}周${viewVO.pregnantDay}天"/></li> -->
		    		<li>总测量次数：<input style="width: 80px;" readonly="readonly" value="${viewVO.totalTestCount}次"/></li>
		    		<li>测量间隔平均时间：<input style="width: 80px;" readonly="readonly" value="${viewVO.averageTesttime}天"/></li>
		    		<li>最近体重状态：<input style="width: 80px;" readonly="readonly" value="${viewVO.state}"/></li>
		    	</ul>
	    	</div>

		</div>
		<ul id="ul_table">
			<c:if test="${empty params.tableType}">
				<li class="table_li tabcolor"  name="" >体重趋势图</li>
				<li class="table_li"  name ="data" style="">体重数据</li>
			</c:if>
			<c:if test="${params.tableType == 'data'}">
				<li class="table_li "  name="" style="">体重趋势图</li>
				<li class="table_li tabcolor "  name ="data">体重数据</li>
			</c:if>
		</ul>
		<div id="table">
			<div id="some"> <div id="some_name">孕期体重增长标准(IOM2009)</div>
                <div id="calendar">
                   <div style="float: left">
                  	 <c:if test="${empty params.tableType}">
                   	     <ul class="calender_btn" style="float: left" >
                         <c:if test="${params.showType eq 'day'}">
                             <li class="look looktype " title="day" >按日查看</li>
                        	 <li class="look" title="">按孕周查看</li>
                         </c:if>
                         <c:if test="${empty params.showType}">
                        	<li class="look" title="day">按日查看</li>
                            <li class="look looktype" title="" style="">按孕周查看</li>
                        </c:if>
                        </ul>
                        <ul class="calender_btn2" style="float: left">
                            <li>
                                <input type="button" style="width: 100px;height:25px" readonly="readonly" value="当前测量${size}次"/>
                            </li>
                        </ul>
                     </c:if>
                     <c:if test="${params.tableType == 'data'}">
                        <ul class="calender_btn2" style="float: left">
                            <li>
                                <input type="button" style="width: 100px;height:25px;margin-left:200px" readonly="readonly" value="当前测量${page.totalCount}次"/>
                            </li>
                        </ul>
                     </c:if>
                   </div>
                   <div>
                        <ul class="table">
                            <li>
                                <select  id="calendar_choose"  name="timeFlag">
                                    <option value="">选择时间</option>
                                    <option value="all" <c:if test="${params.timeFlag == 'all' }">selected="selected"</c:if>>全部 </option>
                                    <option value="7"   <c:if test="${params.timeFlag == '7' }"  >selected="selected"</c:if>>近7天</option>
                                    <option value="30"  <c:if test="${params.timeFlag == '30' }" >selected="selected"</c:if>>近30天</option>
                                </select>
                            </li>
                            <form class="submitForm" action="${pageContext.request.contextPath}/monitor/weight" method="post">
                            	<input type="hidden" id="userId" value="${params.userId}" name="userId">
							    <input type="hidden" id="type" value="${params.showType}" name="showType">
							    <input type="hidden" id="timeFlag" value="${params.timeFlag}" name="timeFlag">
							    <input type="hidden" id="tableType" value="${params.tableType}" name="tableType">
							    <input type="hidden" id="pageNum" value="${params.pageNum}" name="pageNum">
							    <input type="hidden" id="token" value="${params.token}" name="token">
							    <input type="hidden" id="token" value="${params.cssType}" name="cssType"><!-- 加载样式 -->
							    <input type="hidden" name="archiveId" value="${params.archiveId}"  />
								<input type="hidden" name="archiveBaseId" value="${params.archiveBaseId}" /><!-- 档案id -->
	                            <%--<c:if test="${empty timeFlag}"> --%>
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
								<%--</c:if>--%>
							</form>
                        </ul>
                    </div>
                </div>
			</div>
			<c:if test="${empty params.tableType}">
				<div id="control">
		  			<div id="container"> 
		  			</div>
		       	</div>
	       	</c:if>
	       	<c:if test="${params.tableType eq 'data'}">
	       	    <div id="box_show">
	                <div id="box" class="li_width_3">
	                    <table id="table1">
	                        <thead class="fixedThead">
	                        <tr  style="background: #fff;">
	                            <th>监测时间</th>
	                            <th>监测孕周</th>
	                            <th>当日健康区间</th>
	                            <th>健康状态</th>
	                            <th>检测结果</th>
	                        </tr>
	                        </thead>
	                        <tbody class="scrollTbody">
	                        <c:forEach items="${weightList}" var="weight"> 
		                        <tr class="active">
		                            <td data-label="日期">${weight.monitorTime}</td>
		                            <td data-label="监测孕周">${weight.pregnantWeek}周${weight.pregnantDay}天</td>
		                            <td data-label="当日健康区间">${fn:substring(weight.healthMin, 0, 5)}kg-${fn:substring(weight.healthMax, 0, 5)}kg</td>
		                            <c:if test="${weight.weightState == '0' }">
		                            	<td data-label="健康状态">偏瘦</td>
		                            </c:if>
		                            <c:if test="${weight.weightState == '1' }">
		                            	<td data-label="健康状态">标准</td>
		                            </c:if>
		                            <c:if test="${weight.weightState == '2' }">
		                            	<td data-label="健康状态">偏胖</td>
		                            </c:if>
		                            <c:if test="${weight.weightState == '3' }">
		                            	<td data-label="健康状态">肥胖</td>
		                            </c:if>
		                            <td data-label="检测结果">${weight.value}kg</td>
		                        </tr>
	                        </c:forEach>
	                        </tbody>
	                    </table>
	                    <ul id="page_ui">
<!-- 	                    	<input type="text" class="page_text" style="width:30px;height: 23px;" > -->
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
	        </c:if>
		</div>
    </div>
    <input type="hidden" id="weightData" value="${buffer}">
    <input type="hidden" id="xData" value="${xData}">
    <input type="hidden" id="totalData" value="${totalData}">
    <input type="hidden" class="pageTotal" value="${page.totalPages}">
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/weight2.js"></script>
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
</html>