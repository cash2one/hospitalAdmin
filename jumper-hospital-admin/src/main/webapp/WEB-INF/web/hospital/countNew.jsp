
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- <link rel="stylesheet" href="http://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.css">  -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/media/im/css/jquery-ui.min.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/im/js/lib/jquery/jquery-ui.js?${v }"></script>  
<script type="text/javascript">
   $(function() {
   	var hospitalId = $("#userhospitalId").val();
   	if("49"==hospitalId){
   		$("#hospitalsDiv").show();
   	}
   
		var selectedData=[];
		 	$( "#hospitals" ).autocomplete({
		      source: function(request, response){
		      		var hospitalName=  $("#hospitals").val();
					$.post(baseURL+"/pregnantBook/allHospital",{"hospitalName": hospitalName},function(data){
						//封装数据
						var jsonData=eval(data);
 			    		for(var i=0;i<jsonData.length;i++){
			    			var obj={};
			    			obj.label=jsonData[i].id;
			    			obj.id=jsonData[i].name;
			    			selectedData.push(obj); 
			    		}
						    response($.map(jsonData,function(item) {
			                    return{
			                        label: item.name,
			                        id: item.id
			                    }
			                }));
			    	});
		      },
			  select:function(e, ui) {
				$("#userhospitalId").val(ui.item.id);
				chaxunCount();
   			  }
   			  
		   }); 
  }); 
  
</script>





<div   style="border-bottom:1px solid #d3d3d3; ">
				<div>用户统计</div>
				
				<div class="ui-widget" id="hospitalsDiv" style="padding-top: 8px; display:none;">
				  <label for="hospitals" style="font-size: 14px; ">医院名称: </label>
				  <input id="hospitals" style="width:200px;height:35px;">
				</div>
				
				<div style="width: 100%;height: 35px;margin-top: 20px;">
					<div style="border:1px solid #d3d3d3;width: 250px;height: 35px;float: left;line-height: 35px;text-align: center;">今日新增用户:<font id="todayNew">0</font>人</div>
					<div style="border:1px solid #d3d3d3;width: 250px;height: 35px;float: left;line-height: 35px;text-align: center;margin-left: 50px;">总用户数:<font id="allUserCount">0</font>人</div>
				</div>
				<div style="height: 50px;width: 100%;margin-top: 20px;">
				
					<div style="float:left;height: 35px;line-height: 35px;">新增时间:</div>
					<div style="margin-left: 15px;">
						<div class="input-prepend input-group" style="width:500px;float:left">
							<span class="add-on input-group-addon">
								<i class="icon-calendar"></i>
							</span>
						<input type="text" style="width: 200px" name="startTime" id="startTime" placeholder="申请开始时间" class="form-control"  />
						<span class="add-on input-group-addon">
							<i class="icon-calendar"></i>
						</span>
						<input type="text" style="width: 200px" name="endTime" id="endTime" placeholder="申请结束时间" class="form-control"  />
						</div>
					</div>
					<div style="float:left;"><button  style="background-color: #5bc0de; border-color: #5bc0de;color: #fff;width:60px;height: 35px;" id="countSelect">查询</button></div>
					<div style="float:left;height: 35px;line-height: 35px;margin-left: 15px;border:1px solid #d3d3d3;width: 250px;height: 35px;float: left;line-height: 35px;text-align: center;">新增用户数:<font id="timeUserCount">0</font>人</div>
				</div>
			</div>