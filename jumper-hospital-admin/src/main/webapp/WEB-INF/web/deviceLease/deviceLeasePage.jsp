<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<input id="leaseMethod" type="hidden" value="${method }">
	<input id="hospitalId" type="hidden" value="${hospitalId }">
	<input id="username" type="hidden" value="${username }">
	<iframe id="leasePage" src="" width="100%" scrolling="no" frameborder="0" style="height:1200px"></iframe>
</div>
<script type="text/javascript">
	//常量
	//var leaseUrl = "http://localhost:8080/jumper-lease";
	//var leaseUrl = "http://www.tsys91.com/lease/";
	var leaseUrl = "${deviceUrl}"

	//生成token
	var access_token = "";
	$(function() {
		$.ajaxSetup({ //设置post为同步提交
	        async: false
	    });
		$.post(baseURL+"/lease/generatorToken",{token:1},function(data){
			access_token = data;
    	});
    	var method = $("#leaseMethod").val();
    	var hospitalId = $("#hospitalId").val();
    	var username = $("#username").val();
		if(method == "deviceInfo"){
			$("#leasePage").attr("src",leaseUrl+"/hospLease/deviceInfo?access_token="+access_token+"&hospitalId="+hospitalId);
		}else if(method == "marketingSet"){
			$("#leasePage").attr("src",leaseUrl+"/hospLease/marketingSet?access_token="+access_token+"&hospitalId="+hospitalId);
		}else if(method == "leaseOrder"){
			$("#leasePage").attr("src",leaseUrl+"/hospLease/leaseOrder?access_token="+access_token+"&hospitalId="+hospitalId+"&username="+username);
		}else{ //错误页面或404
			$("#leasePage").attr("src","");
		} 
	});
	
	
	
	
	
	//设置iframe高度自适应
	function changeFrameHeight(){
	    var ifm= document.getElementById("leasePage");
	    ifm.height=document.documentElement.clientHeight;
	}
	$(function() {
		changeFrameHeight();
	});
	window.onresize=function(){  
	     changeFrameHeight();  
	}
</script>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>
