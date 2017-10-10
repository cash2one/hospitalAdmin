<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/syncdata_head.jsp"%> 

<body>
	<!-- 顶部导航栏  start-->
	<div id="menu">
        <ul id="nav">
             <li class="mainlevel "><a href="${pageContext.request.contextPath}/monitor/weight?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">体重</a></li>
            <li class="mainlevel active"><a href="${pageContext.request.contextPath}/monitor/heartrate?userId=${params.userId}&cssType=${params.cssType}&archiveId=${params.archiveId}&archiveBaseId=${params.archiveBaseId}">胎心</a></li>
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
	  <div id="table">
    		<div id="some" ><div id="some_name">孕妇基本信息</div></div>
	    	<div>
		    	<ul id="all">
		    		<li>姓名：<input style="width: 60px;" readonly="readonly" value="${params.name}" id="userName"/></li>
		    		<li>年龄：<input style="width: 50px;" readonly="readonly" value="${params.age}" id="userAge"/></li>
		    		<li>联系电话：<input style="width: 90px;" readonly="readonly" value="${params.phone}"/></li>
		    		<li>当前孕周：<input style="width: 60px;" readonly="readonly" value="${params.currentPregnant}"/></li>
		    	</ul>
	    	</div>
		</div>
        <div id="detail">
        	<div id="user"> <span>胎心数据图</span>
             <a id="print-icon" href="#"><img src="${pageContext.request.contextPath}/media/image/syncdata_img/5.png">打印</a>
	            <ul  style="float: right;">
	                <li>
	                    <select  class="select_one" id="paperSpeed">
	                        <option value="1">1厘米/分钟</option>
	                        <option value="2">2厘米/分钟</option>
	                        <option value="3">3厘米/分钟</option>
	                        <option value="20" selected = "selected">20分钟/页</option>
	                        <option value="30">30分钟/页</option>
	                    </select>
	                </li>
	            </ul>
            </div>
            <div class="panel panel-default" id="panel1">
			 <div class="panel-head">
		         <ul>
		             <li class="userName">监测孕周：${params.monitorPregnant}</li>
		             <li>监测日期：${params.addDate}</li>
		             <li>监测时长：${params.testLength}</li>
		             <li><img value="1" class="play" src="${pageContext.request.contextPath}/media/image/syncdata_img/3.png" name=""/></li>
		             <li class="timeNow">--</li>
		             <li class="valueNow">--</li>
		         </ul>
			 </div> 
	 		 <div id="ie" style="width:100%;height:500px;margin-left: 0%;display: none;" >
	            <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="fetalHeart">
	            	<param name="movie" value="${pageContext.request.contextPath}/media/swf/JumperChart.swf" />
	                <param name="quality" value="high" />
	                <param name="bgcolor" value="#ffffff" />
	                <param name="allowScriptAccess" value="sameDomain" />
	                <param name="allowFullScreen" value="true" />
	                <!--[if !IE]>-->
	                <object type="application/x-shockwave-flash" data="${pageContext.request.contextPath}/media/swf/JumperChart.swf" width="100%" height="100%">
	                    <param name="quality" value="high" />
	                    <param name="bgcolor" value="#ffffff" />
	                    <param name="allowScriptAccess" value="sameDomain" />
	                    <param name="allowFullScreen" value="true" />
	                <!--<![endif]-->
	                <!--[if gte IE 6]>-->
	                    <p> 
	                        Either scripts and active content are not permitted to run or Adobe Flash Player version
	                        10.0.0 or greater is not installed.
	                    </p>
	                <!--<![endif]-->
	                    <a href="http://www.adobe.com/go/getflashplayer">
	                        <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
	                    </a>
	                <!--[if !IE]>-->
	                </object>
	                <!--<![endif]-->
	                 </object>
	         </div>
			
			<div class="clearFix"></div>
			<div class="chart-div chart1" id="chart1">
				<input type="hidden" class="music" value="${FILE_DOMAIN}${userHeart.raw_files}">
				<input type="hidden" class="fetalMoveData" value="${fetalMoveJson}" />
				<input type="hidden" class="data" value="${heartJson}" />
		            <canvas class="oyLabel"></canvas>
		            <canvas class="contentChart"></canvas>
		            <div>
		                <canvas class="oxLabel"></canvas>
		            </div>
		    </div>	
          </div>
        </div>
		<div id="table2">
			<div id="some" >
                <div id="some_name">历史胎心数据</div>
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
                            <form class="submitForm" action="${pageContext.request.contextPath}/monitor/heartrate" method="post">
                             	<input type="hidden" id="userId" value="${params.userId}" name="userId">
							    <input type="hidden" id="timeFlag" value="${params.timeFlag}" name="timeFlag">
							    <input type="hidden" id="pageNum" value="${params.pageNum}" name="pageNum">
							    <input type="hidden" id="heartId" value="${params.id}" name="id">
							    <input type="hidden" id="token" value="${params.token}" name="token">
							    <input type="hidden" id="addDate" value="${params.addDate}" name="addDate">
							    <input type="hidden" id="monitorPregnant" value="${params.monitorPregnant}" name="monitorPregnant">
							    <input type="hidden" id="startPrintTime" value="${params.startPrintTime}" name="startPrintTime">
							    <input type="hidden" id="testLength" value="${params.testLength}" name="testLength">
							    <input type="hidden" id="monitorTimeLength" value="${params.monitorTimeLength}" name="monitorTimeLength">
	                            <input type="hidden" id="name" value="${params.name}" name="name">
							    <input type="hidden" id="monitorUserName" value="${params.monitorUserName}" name="monitorUserName">
							    <input type="hidden" id="age" value="${params.age}" name="age">
							    <input type="hidden" id="phone" value="${params.phone}" name="phone">
							    <input type="hidden" id="identification" value="${params.identification}" name="identification">
							    <input type="hidden" id="bookCode" value="${params.bookCode}" name="bookCode">
							    <input type="hidden" id="currentPregnant" value="${params.currentPregnant}" name="currentPregnant">
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
            <div  id="box_show" >
                <div id="box" >
                    <table id="table1">
                        <thead>
                        <tr  style="background: #fff;">
                            <th>孕妇姓名</th>
                            <th>监测孕周</th>
                            <th>监测日期</th>
                            <th>监测时长</th>
                            <th>平均胎心率</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${heartList}" var="heart" varStatus="status" >
                        	 <c:if test="${status.index == 0 }">
                        	 	 <input type="hidden" id="FhertId" name="FhertId" value="${heart.id }"  />
                        	 </c:if>
	                        <tr <c:if test="${params.id == heart.id}" >class="tr_bg"</c:if> >
	                            <td data-label="孕妇姓名" style="text-align: center">${heart.name}</td>
	                            <td data-label="监测孕周" style="text-align: center">${heart.pregnantWeek}周${heart.pregnantDay}天</td>
	                            <td data-label="监测日期" style="text-align: center">${heart.monitorTime}</td>
	                            <td data-label="监测时长" style="text-align: center">${heart.testTime}</td>
	                            <td data-label="平均胎心率" style="text-align: center">${heart.value}次/分钟</td>
	                            <td data-label="操作" style="text-align: center" ><a href="#" class="lookHeart" name="${heart.id}" length="${heart.testTimeLength}" testLength="${heart.testTime}"
	                           	   pregnant="${heart.pregnantWeek}周${heart.pregnantDay}天" startTime="${heart.startTime}" time="${heart.monitorTime}"
	                           	   userName="${heart.name}" age="${heart.age}" mobile = "${heart.phone}" healthNum="${heart.health_num}" currentPregnant="${heart.currentPregnant}">查看</a>
	                           	</td>
	                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <ul id="page_ui">
                   	<input type="text" class="page_text" >
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
       <input type="hidden" id="domain_path" value="${FILE_DOMAIN}" />
       <input type="hidden" id="base_path_in" value="${BASE_PATH_IN}" />
       <input type="hidden" id="base_path_out" value="${pageContext.request.contextPath}"/>
       <input type="hidden" id="pregnant" value="${viewVo.pregnantWeek}" name="${viewVo.pregnantDay}"/>
       <input type="hidden" id="swf" value="${pageContext.request.contextPath}/media/swf/JumperChart.swf"/>
       <input type="hidden" id="downloadFlash" value="${DOWNLOAD_FLASH}">
       <input type="hidden" id="flashVersion" value="${FLASH_VERSION}">
       <input type="hidden" class="uterusData" value="${uterusData}" />
       <input type="hidden" name="offset" id="offset" value="" />
       <input type="hidden" class="pageTotal" value="${page.totalPages}"/>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/canvas/html2canvas.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/canvas/report.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/canvas/chart.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/heartrate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/syncdata_js/swfobject.js"></script>
<script type="text/javascript">
	laydate({
	    elem: '#date1', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
	laydate({
	    elem: '#date2', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	});
	
	var swf = $("#swf").val();
	swfobject.embedSWF(swf,"fetalHeart","1800","500","9");
    function getFateData(){
   	    var uterus = $(".uterusData").val();
        var result = $(".data").val()+"-"+$(".fetalMoveData").val()+"-"+uterus.substring(1,uterus.length-1);
        //alert(result);
        return result;
    }
    $(document).ready(function(){
        if(document.all){  
	        $("#ie").show();
	        $("#chart1").hide();
	        // alert("IE");   
        }else{   
	        $("#ie").hide();
	        $("#chart1").show();
	        // alert("not ie");   
    	}
    });
    //检测是否安装了flash
    function flashChecker()
    {
	    var hasFlash=0;//是否安装了flash
	    var flashVersion=0;//flash版本
	    if(document.all)
	    {
		    var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash'); 
		    if(swf) {
			    hasFlash=1;
			    VSwf=swf.GetVariable("$version");
			    flashVersion=parseInt(VSwf.split(" ")[1].split(",")[0]); 
	    	}
    	}else{
    		if (navigator.plugins && navigator.plugins.length > 0)
    		{
   				 var swf=navigator.plugins["Shockwave Flash"];
			     if (swf)
			        {
   						hasFlash=1;
	           			var words = swf.description.split(" ");
	          		    for (var i = 0; i < words.length; ++i)
   						{
				             if (isNaN(parseInt(words[i]))) continue;
				             flashVersion = parseInt(words[i]);
    					}
   					}
    		}
    	}
    	return {f:hasFlash,v:flashVersion};
    }

    var fls=flashChecker();
    var s="";
    if(fls.f){
    	if(fls.v < $("#flashVersion").val()){
	        //document.write("您安装了flash,当前flash版本为: "+fls.v+".x");
	       alert("您安装了flash,当前flash版本为: "+fls.v+".x，可能版本过低，请重新安装高版本flash");
	       window.location.href = $("#base_path_in").val()+$("#downloadFlash").val();
    	}
    }else{
        //document.write("您没有安装flash"); 
        alert("您还没有安装flash，请下载并安装");
        window.location.href = $("#base_path_in").val()+$("#downloadFlash").val();
    } 
    
    function setGenerateIndex(offset){
    	document.getElementById("offset").value = offset;
    	//alert(offset);
    }
</script>
</html>