<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/head.jsp"%> 

<body style="">
	<!-- 顶部导航栏  start-->
	<div id="menu">
        <ul id="nav">
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/weight?healthNum=${params.healthNum}&token=${params.token}">体重</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/heartrate?healthNum=${params.healthNum}&token=${params.token}">胎心</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/sugar?healthNum=${params.healthNum}&token=${params.token}">血糖</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/pressure?healthNum=${params.healthNum}&token=${params.token}">血压</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/oxygen?healthNum=${params.healthNum}&token=${params.token}">血氧</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/bloodfat?healthNum=${params.healthNum}&token=${params.token}">血脂</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/urine?healthNum=${params.healthNum}&token=${params.token}">尿液</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/temperature?healthNum=${params.healthNum}&token=${params.token}">体温</a></li>
            <li class="mainlevel"><a href="${pageContext.request.contextPath}/monitor/ecg?healthNum=${params.healthNum}&token=${params.token}">心电</a></li>
            <!-- 每日用户列表 -->
            <li class="mainlevel active"><a href="${pageContext.request.contextPath}/monitor/UserHeartRecordDay?healthNum=${params.healthNum}&token=${params.token}"  style="border-right: 1px solid #1BB1E3">用户列表</a></li>  
        </ul>
    </div>
    <!-- 顶部导航栏  end-->
	  <div id="top">
	  <div id="table">
    		<div id="some" ><div id="some_name">孕妇基本信息</div></div>
	    	<div>
	    	   
		      <form id="selForm" action="" method="post">
		    	<ul id="all">
		    		<li>姓名：<input style="width: 60px;" readonly="readonly" value="${params.name}" id="userName"/></li>
		    		<li>年龄：<input style="width: 30px;" readonly="readonly" value="${params.age}" id="userAge"/></li>
		    		<li>联系电话：<input style="width: 90px;" readonly="readonly" value="${params.phone}"/></li>
		    		<li>当前孕周：<input style="width: 60px;" readonly="readonly" value="${params.currentPregnant}"/></li>
		    		<li style="float: right;margin-right: 100px;">
		    			<select id="querySelect" name="query">
		    			  <option value="bookCode">手册条形码</option>
<!-- 		    			  <option value="name">姓名</option> -->
<!-- 		    			  <option value="identification">身份证</option> -->
						  <!--  <option value="pregnantWeek">孕周</option> -->
		    			</select>
		    			<input style="width: 150px;height: 20px;" name="queryValue" id="queryValue"/>
		    			<input style="width:38px;height:22px;line-height: 18px" type="button" value="确定" onclick="queryBySelect()"/>
		    		</li>
		    	</ul>
		     </form>
	    	</div>
		</div>
        <div id="detail" style="height:600px;">
        	<div id="user"> <span>胎心数据图</span>
             <a id="print-icon" href="#"><img src="${pageContext.request.contextPath}/images/5.png">打印</a>
	            <ul  style="float: right;">
	                <li>
	                    <select  class="select_one" id="paperSpeed">
	                        <option value="1">1厘米/分钟</option>
	                        <option value="2">2厘米/分钟</option>
	                        <option value="3">3厘米/分钟</option>
	                        <option value="20"  selected = "selected">20分钟/页</option>
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
		             <li><img value="1" class="play" src="${pageContext.request.contextPath}/images/3.png" name=""></li>
		             <li class="timeNow">--</li>
		             <li class="valueNow">--</li>
		         </ul>
			 </div> 
	 		 <div id="ie" style="width:100%;height:500px;margin-left: 0%;display: none;" >
	            <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="fetalHeart">
	            	<param name="movie" value="${pageContext.request.contextPath}/JumperChart.swf" />
	                <param name="quality" value="high" />
	                <param name="bgcolor" value="#ffffff" />
	                <param name="allowScriptAccess" value="sameDomain" />
	                <param name="allowFullScreen" value="true" />
	                <!--[if !IE]>-->
	                <object type="application/x-shockwave-flash" data="${pageContext.request.contextPath}/JumperChart.swf" width="100%" height="100%">
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
				<input type="hidden" class="music" value="${FILE_DOMAIN}${userHeart.rawFiles}">
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
        <!-- 用户列表 -->
		<div id="table2">
			<div id="some" >
                <div id="some_name">历史胎心数据</div>
                <div id="calendar">
                    <div>
                        <ul class="table">
                            <form class="submitForm" action="${pageContext.request.contextPath}/monitor/UserHeartRecordDay" method="post">
	                            <input type="hidden" id="healthNum" value="${params.healthNum}" name="healthNum">
	                            <input type="hidden" id="userHealthNum" value ="${params.userHealthNum}" name="userHealthNum"/>
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
                            <th>年龄</th>
                            <th>联系方式</th>
                            <th>保健号</th>
                            <th>添加时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${heartList}" var="heart">
	                        <tr <c:if test="${params.id == heart.id}" >class="tr_bg"</c:if> >
	                            <td data-label="孕妇姓名">${heart.name}</td>
	                            <td data-label="监测孕周">${heart.pregnantWeek}周${heart.pregnantDay}天</td>
	                            <td data-label="年龄">${heart.age}</td>
	                            <td data-label="联系方式">${heart.phone}</td>
	                            <td data-label="保健号">${heart.health_num}</td>
	                            <td data-label="添加时间">${heart.addTime}</td>
	                            <td data-label="操作">
	                            <a href="#" class="lookHeart" name="${heart.id}" length="${heart.testTimeLength}" testLength="${heart.testTime}"
	                           	   pregnant="${heart.pregnantWeek}周${heart.pregnantDay}天" startTime="${heart.startTime}" time="${heart.monitorTime}"
	                           	   userName="${heart.name}" age="${heart.age}" mobile = "${heart.phone}" healthNum="${heart.health_num}" currentPregnant="${heart.currentPregnant}">查看胎心</a>
	                           </td>
	                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <ul id="page_ui">
                   	<input type="text" class="page_text">
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
       <input type="hidden" id="base_path_out" value="${BASE_PATH_OUT}" />
       <%-- <input type="hidden" id="age" value="${viewVo.age}" /> --%>
       <%-- <input type="hidden" id="heartId" value="${params.id}" /> --%>
       <input type="hidden" id="pregnant" value="${viewVo.pregnantWeek}" name="${viewVo.pregnantDay}"/>
       <input type="hidden" id="swf" value="${pageContext.request.contextPath}/JumperChart.swf"/>
       <input type="hidden" id="downloadFlash" value="${DOWNLOAD_FLASH}">
       <input type="hidden" id="flashVersion" value="${FLASH_VERSION}">
       <input type="hidden" class="uterusData" value="${uterusData}" />
       <input type="hidden" name="offset" id="offset" value="" />
       <input type="hidden" class="pageTotal" value="${page.totalPages}"/>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/syncdata/canvas/html2canvas.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/syncdata/canvas/report.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/syncdata/canvas/chart.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/syncdata/heartrate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/swfobject.js"></script>
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
    /*function playMusic(){
		 var music = $(".music").val();
		//var music = document.getElementById("sound").value;
	   	if (navigator.appName.indexOf("Microsoft Internet Explorer")!=-1 && document.all){//IE浏览器
	        // document.getElementById("play").innerHTML = '<bgsound src='+'"'+music+'"'+' loop="infinite">';
	   		document.getElementById("play").innerHTML = '<object classid="clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95"><param name="AutoStart" value="1" /><param name="Src" value='+'"'+music+'"'+' /></object>';
	     } else {//其他浏览器
	         document.getElementById("play").innerHTML = '<embed src='+'"'+music+'"'+' hidden="true" autostart="true" loop="true">';
	     }
   }*/
   function queryBySelect(){
   	   var selname = $("#querySelect").val();
   	   var selValue = $("#queryValue").val();
   	   if(selname == "bookCode"){//说明是根据保健号查询
   	   		$("#bookCode").val(selValue);
   	   }
   	   if(selname == "name"){//说明是根据保姓名查询
   	   		$("#monitorUserName").val(selValue);
   	   }
   	   if(selname == "identification"){//说明是根据身份证号查询
   	   		$("#identification").val(selValue);
   	   }
   	   $(".submitForm").submit();
   }
   
</script>
</html>