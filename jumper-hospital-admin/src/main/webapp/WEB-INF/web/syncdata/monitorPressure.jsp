<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/syncdata_head.jsp"%>

<body style="">
	<!-- 顶部导航栏  start-->
	<div id="menu">
        <ul id="nav">
            <li class="mainlevel "><a href="${pageContext.request.contextPath}/monitor/weight?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">体重</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/heartrate?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">胎心</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/sugar?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">血糖</a></li>
            <li class="mainlevel active"><a href="${pageContext.request.contextPath}/monitor/pressure?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">血压</a></li>
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
	    	<div>
		    	<ul id="all">
<!-- 		    		<li>姓名：<input style="width: 90px;" readonly="readonly" value="${viewVO.name}"/></li> -->
		    		<li>年龄：<input style="width: 30px;" readonly="readonly" value="${viewVO.age}"/></li>
		    		<li>联系电话：<input style="width: 90px;" readonly="readonly" value="${viewVO.phone}"/></li>
		    		<li>当前孕周：<input style="width: 90px;" readonly="readonly" value="${viewVO.pregnantWeek}周${viewVO.pregnantDay}天"/></li>
		    		<li>总测量次数：<input style="width: 90px;" readonly="readonly" value="${viewVO.totalTestCount}次"/></li>
		    	</ul>
	    	</div>
		</div>
		<ul id="ul_table">
			<c:if test="${empty params.tableType}">
				<li class="table_li tabcolor"  name="" >血压趋势图</li>
				<li class="table_li"  name ="data"  >血压数据</li>
			</c:if>
			<c:if test="${params.tableType == 'data'}">
				<li class="table_li"  name=""  >血压趋势图</li>
				<li class="table_li tabcolor"  name = data" >血压数据</li>
			</c:if>
		</ul>
		<div id="table">
			<div id="some"> <div id="some_name">孕期血压</div>
                <div id="calendar">
                    <div style="float: left">
                         <c:if test="${empty params.tableType}">
                   	     <ul class="calender_btn" style="float: left" >
	                         <c:if test="${params.showType eq 'day'}">
	                             <li class="look looktype" title="day" >按日查看</li>
	                        	 <li class="look" title="">按孕周查看</li>
	                         </c:if>
	                         <c:if test="${empty params.showType}">
	                        	<li class="look" title="day">按日查看</li>
	                            <li class="look looktype" title="" >按孕周查看</li>
	                        </c:if>
                        </ul>
                        <ul class="calender_btn2" style="float: left">
                            <li>
                                <input type="button" style="width: 90px;height:20px;" readonly="readonly" value="当前测量${size}次"/>
                            </li>
                        </ul>
                     </c:if>
                     <c:if test="${params.tableType == 'data'}">
                        <ul class="calender_btn2" style="float: left">
                            <li>
                                <input type="button" style="width: 90px;height:20px;margin-left:200px" readonly="readonly" value="当前测量${page.totalCount}次"/>
                            </li>
                        </ul>
                     </c:if>
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
                             <form class="submitForm" action="${pageContext.request.contextPath}/monitor/pressure" method="post">
	                            <input type="hidden" id="userId" value="${params.userId}" name="userId">
							    <input type="hidden" id="type" value="${params.showType}" name="showType">
							    <input type="hidden" id="timeFlag" value="${params.timeFlag}" name="timeFlag">
							    <input type="hidden" id="tableType" value="${params.tableType}" name="tableType">
							    <input type="hidden" id="pageNum" value="${params.pageNum}" name="pageNum">
							    <input type="hidden" id="addDate" value="${params.addDate}" name="addDate">
							    <input type="hidden" id="token" value="${params.token}" name="token">
							    <input type="hidden"   value="${params.cssType}" name="cssType"><!-- 加载样式 -->
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
			<c:if test="${empty params.tableType}">
				<div id="control">
		  			<div id="container"> 
		  			</div>
		       	</div>
	       	</c:if>
	       	<c:if test="${params.tableType eq 'data'}">
	       	 <div id="box_show" >
                <div id="box" class="li_width_3">
                    <table id="table1">
                        <thead class="fixedThead">
                        <tr  style="background: #fff;">
                            <th>孕妇姓名</th>
                            <th>监测孕周</th>
                            <th>监测日期</th>
                            <th>舒张压</th>
                            <th>收缩压</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pressureList}" var="pressure">
	                        <tr>
	                            <td data-label="孕妇姓名">${pressure.name}</td>
	                            <td data-label="监测孕周">${pressure.pregnantWeek}周${pressure.pregnantDay}天</td>
	                            <td data-label="监测日期">${pressure.monitorTime}</td>
	                            <td data-label="舒张压">${pressure.diastoliPressure}mmHg </td>
	                            <td data-label="收缩压">${pressure.systolicPressure}mmHg</td>
	                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <ul id="page_ui">
                   	<input type="text" class="page_text"  >
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
    <input type="hidden" id="lowData" value="${low}">
    <input type="hidden" id="highData" value="${high}">
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/pressure.js"></script>
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