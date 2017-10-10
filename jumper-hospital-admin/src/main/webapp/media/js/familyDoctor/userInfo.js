//异步请求状态 默认闲置
var requestState=false;
//获取滚动条当前的位置 
function getScrollTop() { 
	var scrollTop = 0; 
	if (document.documentElement && document.documentElement.scrollTop) { 
		scrollTop = document.documentElement.scrollTop; 
	} 
	else if (document.body) { 
		scrollTop = document.body.scrollTop; 
	} 
	return scrollTop; 
} 

// 获取当前可是范围的高度
function getClientHeight() { 
	var clientHeight = 0; 
	if (document.body.clientHeight && document.documentElement.clientHeight) { 
		clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight); 
	} 
	else { 
		clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight); 
	} 
	return clientHeight; 
} 
// 获取文档完整的高度
function getScrollHeight() { 
	return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight); 
} 

//分页获取家庭医生数据
function getFamilyUserInfo(pageno,pageSize,fn){
	//只要请求就设置请求状态为正在请求
	requestState=true;
	$.ajax({
		url :"/JumperRemoteAdmin/fDoctor/getFamilyUserInfo",
		data : {
			pageNo : pageno,
			pageSize:pageSize
		},
		async:true,
		type : "get",
		dataType : "json",
		success : function(res) {
			 fn(res);
		}
	});
};
$(function(){
	//设置默认的起始页及每页显示数
	$("#pageno").val(1);
	$("#pageSize").val(50);
	//数据处理
	function refresh(res){
		$.each(res.data.result,function(index,obj){
			var html="";
			html= "<tr><td>"+obj.name+"</td>" +
			 "<td>"+obj.age+"</td>" +
			 "<td>"+obj.pregnantWeek+"</td>" +
			 "<td>"+obj.mobile+"</td> " +
			 "<td>"+new Date(parseInt(obj.lastPeriod)).toLocaleString().split(" ")[0].replace("/", "-").replace("/", "-")+"</td>" +
			 "<td>"+obj.splicingAddress+"</td>" +
			 '<td><button class="btn" style="background-color:rgba(255,255,255,0)" onclick="alert(123);return false;"><img src="${pageContext.request.contextPath}/media/image/detail.png" width="14px" height="18px"/>查看数据</button>'+
			 '<button  class="btn"  style="background-color:rgba(255,255,255,0)" onclick="alert(123);return false;">修改</button></td>'+
			 "</tr>"
			 $("#familyUserInfoList").append(html);
		});
  		$("#pageno").val(parseInt($("#pageno").val())+1);
		//设置请求状态为闲置
		requestState=false;
	}
	//请求第一页数据
	getFamilyUserInfo($("#pageno").val(),$("#pageSize").val(),refresh);
	// ------------------------
	window.onscroll = function () { 
		if (getScrollTop() + getClientHeight()+20 >= getScrollHeight()) { 
			if(!requestState){//判断是否有请求正在进行
				getFamilyUserInfo($("#pageno").val(),$("#pageSize").val(),refresh);
			}
		}
	} 
});
