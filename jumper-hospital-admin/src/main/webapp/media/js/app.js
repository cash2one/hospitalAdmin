//得到URL路径地址
getRoot=function () {
    //获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/ems
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return(localhostPath + projectName);
}
var baseURL = getRoot();
var chatURL = "http://192.168.2.124:6789";
var baseFileUrl = getRoot();
var consultURL = "http://192.168.0.3";
var realTimeRequestUrl = "http://192.168.0.3:8080/hospital-nodeapp/hospital";
var servicePath = "/hospital";
var imURL = "http://192.168.0.3:8081/common/chat/init?";
/**
var baseURL = "http://112.74.13.124:8086";
var chatURL = "http://112.74.13.124:9999";
var baseFileUrl = "http://image.jumper-health.com";
var consultURL = "http://112.74.13.124";
var realTimeRequestUrl = "http://112.74.13.124/hospital";
var servicePath = "/hospital";
**/
$(function(){
	$(".father-menu").bind("click", App.menuOperate);
});

var App = function(){
	
	return {
		/**
		 * ajax请求封装方法
		 * @param url 请求地址ַ
		 * @param data 请求数据 JSON
		 * @param reqType 请求方法 GET | POST
		 * @param async 是否异步阻塞 true | false
		 * @param dataType text | json
		 * @param resultHandler 请求成功处理函数
		 * @param faultHander 请求失败处理函数
		 */
		ajaxRequest : function(url, data, reqType, async, dataType, resultHandler, faultHander){
			$.ajaxSetup({ 
				async: async
			});
			if (typeof(faultHander) == 'undefined') {
				faultHander = function(data) {
					alert("error");
				};
			}
			$.ajax({
				url : baseURL+url,
				data : data,
				type : reqType,
				cache : false,
				dataType : dataType,
				success : function(data) {
					//var json = JSON.parse(data);
					resultHandler(data);
				},
				error : function(request, textStatus, errorThrown){
					var loginStatus = request.getResponseHeader("loginStatus");
					if(loginStatus == "accessDenied"){
						App.dialog_show("请求失败", "登录超时，请重新登录！", function(){
							$(".info-dialog-close").trigger("click");
						});
						setTimeout(function() {
							location.reload(true);
						}, 2000);
					}else{
						App.dialog_show("", "请求失败，网络错误！", function(){
							$(".info-dialog-close").trigger("click");
						});
					}
				}
			});
		},
		/**
		 * ajax请求封装方法
		 * @param url 请求地址ַ
		 * @param data 请求数据 JSON
		 * @param reqType 请求方法 GET | POST
		 * @param async 是否异步阻塞 true | false
		 * @param dataType text | json
		 * @param resultHandler 请求成功处理函数
		 * @param faultHander 请求失败处理函数
		 */
		ajaxDefineRequest : function(url, data, reqType, async, dataType, resultHandler, faultHander){
			$.ajaxSetup({ 
				async: async
			});
			if (typeof(faultHander) == 'undefined') {
				faultHander = function(data) {
					alert("error");
				};
			}
			$.ajax({
				url : url,
				data : data,
				type : reqType,
				cache : false,
				dataType : dataType,
				success : function(data) {
					//var json = JSON.parse(data);
					resultHandler(data);
				},
				error : function(request, textStatus, errorThrown){
					var loginStatus = request.getResponseHeader("loginStatus");
					if(loginStatus == "accessDenied"){
						App.dialog_show("请求失败", "登录超时，请重新登录！", function(){
							$(".info-dialog-close").trigger("click");
						});
						setTimeout(function() {
							location.reload(true);
						}, 2000);
					}else{
						App.dialog_show("", "请求失败，网络错误！", function(){
							$(".info-dialog-close").trigger("click");
						});
					}
				}
			});
		},
		/**
		 * ajax跨域jsonp请求封装方法
		 * @param url 请求地址
		 * @param data 请求数据 JSON
		 * @param reqType 请求方法 GET | POST
		 * @param resultHandler 请求成功处理函数
		 * @param faultHander 请求失败处理函数
		 */
		ajaxJsonpRequest : function(url, reqType, data, resultHandler, faultHander){
			$.ajax({
				async:true,
				url: url,
				type: reqType,
				dataType: 'jsonp',
				jsonp: 'callback',
				data: data,
				success: function (json) {
					resultHandler(json);
				},
				error: function(xhr){
					faultHander(xhr);
				}
			});
		},
		/**
		 * @param url 请求地址
		 * @param formId 表单提交Form id
		 * @param reqType 请求方法 GET | POST
		 * @param resultHandler 请求成功处理函数
		 * @param faultHander 请求失败处理函数
		 */
		ajaxFormSubmit : function(url,formId, reqType, resultHandler, faultHander){
			if (typeof(faultHander) == 'undefined') {
				faultHander = function(data) {
					alert("error");
				};
			}
			$.ajax({
				url : baseURL+url,
				data : $('#'+formId).serialize(),//formid
		        type : reqType,
		        cache : false,
		        async : false,
		        success : function(data) {
		        	//var str = JSON.stringify(data);
		        	var json = JSON.parse(data);
		        	resultHandler(json);
		        },
		        error : function(data) {
		        	var json = JSON.parse(data);
		        	faultHander(json);
		        }
		    });
		},
		dialog_show : function(title, message, sureHandle){
			if(title != ''){
				$(".info-dialog-title").text(title);
			}
			$(".info-dialog-body").text(message);
			$(".info-dialog-href").trigger("click");
			$(".info-dialog-sure").unbind('click').click(sureHandle);
		},
		confirm_show : function(title, message, handle){
			if(title != ''){
				$(".info-confirm-title").text(title);
			}
			$(".info-confirm-body").text(message);
			$(".info-confirm-href").trigger("click");
			$(".info-confirm-sure").unbind('click').click(handle);
		},
		checkField : function(value){
			if(value == ''){
				return false;
			}
			return true;
		},
		uploadReset : function(){
			$("#img_preview").val("");
			$("#img_preview").hide();
			$("#img_upload").show();
			$("#upload_reset").hide();
		},
		menuOperate : function(){
			$(".page-sidebar-menu li").removeClass("active");
			if($(this).next().is(":hidden")){
				$(this).next().show();
				$(this).parent().removeClass("active");
			}else{
				$("ul .sub-menu").hide();
				$(this).next().hide();
				$(this).parent().addClass("active");
			}
		},
		validateErrorInit : function(selecter, msg){
			$(selecter).removeClass("hide");
			$(selecter).text(msg);
		},
		validateSuccess : function(selecter){
			if(!$(selecter).hasClass("hide")){
				$(selecter).addClass("hide");
			}
		},
		fileUpload : function(event){
			$.ajaxFileUpload({
	            url: baseURL+'/base/upload', 
	            type: 'post',
	            secureuri: false, //一般设置为false
	            fileElementId: event.data.element, // 上传文件的id、name属性名
	            dataType: 'json', //返回值类型，一般设置为json、application/json
	            success: function(data, status){
	            	$("#"+event.data.element).next("input").val(data);
	            	$("#"+event.data.element).parent().parent().next("img").attr("src", baseFileUrl+data);
	            },
	            error: function(data, status, e){ 
		           	alert("图片上传失败！");
	            }
	        });
		},
		noPermission : function(){
			App.dialog_show("请求失败", "登录超时，请重新登录！", function(){
				$(".info-dialog-close").trigger("click");
			});
			setTimeout(function() {
				location.reload(true);
			}, 2000);
		},
		//添加日志的js
		addOperateLog:function(module,action,object,objectContent){
			$.ajax({
                type : "POST",
                url : baseURL+'/operateLog/addOperateLog',
                data : {"module" : module, "action" : action, "object": object,"objectContent":objectContent},
                dataType : "json", 
                async:false,//使用同步的Ajax请求 
                success : function(data){
                	console.log("操作的模块为："+module+",操作的动作为："+action+"操作的对象为："+object+"操作内容为："+objectContent);
                }
            });
		}
	}
}();