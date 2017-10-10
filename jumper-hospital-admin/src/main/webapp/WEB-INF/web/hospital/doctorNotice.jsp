<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'htest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script src="${pageContext.request.contextPath}/resources/js/layer-v3.0.3/layer/layer.js"></script>
  </head>
  
  <style>
		*{margin:0;padding: 0;font-size:14px ;font-family: "微软雅黑";}
		.conter{width:750px;padding: 5px;overflow: hidden;background: #f3f3f3;}
		.Tleft ,.Tright{width: 50%;box-sizing: border-box;float: left;}
		.Tleft input{width:245px;height:25px;line-height:25px;padding:2px;margin-top:20px;border:1px solid #CCCCCC;}
		.Tleft button{width:65px;height:25px;background:#0099FF;border:1px solid #0099FF;color: #fff;margin-left: 5px;}
		.Tleft table, .Tright table{border-spacing: 0;border-collapse: collapse;width:100%;font-size: 12px;text-align: center;color: #555;}
		.Tleft td.active button{color: #0099FF;}
		.Tleft table td, .Tright table td{font-size:12px;padding:2px 0;background: #fff;border: 1px solid #efefef;}
		.Tright .cz{color:red;}
		.Tleft table td{border:0;}
		.Tright table td{padding:6px 0;}
		td{padding:5px 0;}
		td.cz{cursor:pointer}
		.cz button{border:0;    background: #fff;color:#555;}
		.Tright td{}
		tr.thcenter th{    text-align: center;}
		#layui-layer1 .layui-layer-title {
		    font-weight: 600;
		    background-color: #e6e6e6;
		}
		.layer-anim{background: #ececec;}
	</style>
	<body>
		<div id="conterd" class="conter" style="display:none;margin:10px;    background: #ececec;">
			<div class="Tleft" style="color:white;">
				<input type="text" value="" id="searchName" style="color:black" placeholder="输入姓名查找"/><button  class="searchName">搜索</button>
				<table style="margin-top:15px;">
					<tr   class="thcenter">
							<th>姓名</th>
							<th>职称</th>
							<th>手机</th>
							<th style="width:124px">操作</th>
						</tr>
				</table>
				<div id="" style="height:380px;background: #fff;overflow-y:scroll;border:1px solid #CCCCCC;">
				<table border="0" cellspacing="" cellpadding="">
						
					<tbody  id="nast">
					
					</tbody>
				</table>
				</div>
			</div>
			<div class="Tright" style="padding-left:15px">
				<p  style="padding:27px 10px 0px;color:#333;">推送名单：<span id="msgCounts"></span></p>
				<table style="margin-top:15px;">
					<tr  class="thcenter">
							<th style="width:68px">姓名</th>
							<th>职称</th>
							<th class="wid115" style="width:150px">手机</th>
							<th class="wid75" style="width:52px">操作</th>
						</tr>
				</table>
				<div id="" style="height:350px;overflow-x:hidden;overflow-y:auto;">
				
				<table border="0" cellspacing="" id="nast2" cellpadding="">
					<tr>
						<th>姓名</th>
						<th>职称</th>
						<th>手机</th>
						<th>操作</th>
					</tr>
					
				</table>
				</div>
			</div>
		</div>
	</body>
  <script>
  var url = "${pageContext.request.contextPath}";
  //点击搜索
  $(document).on('click',".searchName",function(){
  	var name = $("#searchName").val();
  	addhtml(name);
  	addhtml2(1);
  });
  
  //点击置灰
  $(document).on('click',".active .liss",function(){
  	var $this=$(this);
  	$this.attr("disabled",true);
  	$this.parent().removeClass("active");
  	var phone = $this.attr("data-phone");
  	var titleId = $this.attr("data-titleId");
  	var name = $this.attr("data-name");
  	var username = $this.attr("data-username");
  	$.ajax({
  		url:url+"/hos/addDoctorNotice",
  		type:'post',
 		dataType:'json',
 		data:{'phone':phone,'name':name,'titleId':titleId,'username':username},
 		success:function(data){
 			if(data.msg == 1){
 				addhtml2(1);
 			}
 		}
  	});
  });
   //移除操作
  $(document).on('click',".deusername",function(){
	var $this=$(this);
	var username = $this.attr("delete-username");
  	var html=$("#nast").html();
  	if(html!=''){
  		$("."+username).attr("disabled",false).parent().addClass("active");
	  	$this.parent().remove();
	  	$.ajax({
			url:url+"/hos/deleteDoctorNoticeByUsername",	
	  		data:{'username':username},
	  		dataType:'json',
	  		type:'post',
	  		success:function(data){
	  			if(data.msg == 1){
					addhtml2(1);
					//getNoticeCount(2);		  			
	  			}
	  		}
	  	});
  	}
  });
  
  function addhtml(name){
  	$("#nast").html('');
 	$.ajax({
 		url:url + "/hos/getAllDoctor",
 		data:{'name':name},
 		type:'post',
 		dataType:'json',
 		success:function(data){
 			if(data.msg == 1 && data.data != null && data.data.length>0){
 				var data = data.data;
 				var html='';
 				for(var i = 0;i<data.length;i++){
  						html +='<tr>';
						html +='<td>'+data[i].name+'</td>';
						html +='<td>'+data[i].titleId+'</td>';
						html +='<td>'+data[i].phone+'</td>';
						if(data[i].id == 1){
							html +='<td class="cz"><button data-titleId="'+data[i].titleId+'" data-name="'+data[i].name+'" data-phone="'+data[i].phone+'" data-username="'+data[i].username+'" class="liss '+data[i].username+'" disabled ="disabled">添加</button></td>';
						}else{
							html +='<td class="cz active"><button data-titleId="'+data[i].titleId+'" data-name="'+data[i].name+'" data-phone="'+data[i].phone+'" data-username="'+data[i].username+'" class="liss '+data[i].username+'">添加</button></td>';
						}
						html +='</tr>';
 				}
 				$("#nast").html(html);
 				
 			}else{
				var html='<tr><td>暂无数据</td></tr>'; 	
				$("#nast").html(html);		
 			}
 		},
 	
 	});
  }
  function addhtml2(flag){
  	$("#msgCounts").html('0');
  	$("#nast2").html('');
 	$.ajax({
 		url:url + "/hos/getDoctorNotice",
 		type:'post',
 		data:{'flag':flag},
 		dataType:'json',
 		success:function(data){
 			if(data.msg == 1 && data.data != null && data.data.length>0){
 				var data = data.data;
 				var html='';
 				for(var i=0;i<data.length;i++){
 					html+='<tr>';
					html+='<td>'+data[i].name+'</td>';
					html+='<td>'+data[i].titleId+'</td>';
					html+='<td>'+data[i].phone+'</td>';
					html+='<td class="cz active deusername" delete-username="'+data[i].username+'" >移除</td>';
					html+='</tr>';
 				}
 				$("#nast2").html(html);
				$("#msgCounts").html(data.length);
				widthTh();
 			}else{
 				var html='<tr><td>暂无推送人员</td></tr>';
 				$("#nast2").html(html);
 			}
 		}
    });
  }
  function submitNotice(){
  	var statu='';
  	
  }
  
  function layerTit(){
  	
  	layer.open({
				title:"短信通知名单",
		  		type: 1,
		  		area: ['auto','auto'], //宽高
		  		content: $('#conterd'),
		  		offset:'200px',
		  		btn: ['保存','取消'],
		  		yes: function(indexw){
		  			$.ajax({
					  		url:url+"/hos/updateDoctorNoticeStatus",
					  		type:'post',
					  		dataType:'json',
					  		success:function(data){
					  			getNoticeCount(2);	
					  			layer.close(indexw);//g关闭
						  	}
						});
		  		},
		    	end:function(){
		    		getNoticeCount(2);
		    	}
			});
			$("#conter").show();
			addhtml2("");
			setTimeout(function(){addhtml("");},50);
			
  }
  function widthTh(){
  	var width=$("#nast2").width();
  	if(width==354){
  		$(".wid115").width("115px");
  		$(".wid75").width("75px");
  	}else if(width==338){
  		$(".wid115").width("150px");
  		$(".wid75").width("52px");
  	}
  }
  </script>
</html>
