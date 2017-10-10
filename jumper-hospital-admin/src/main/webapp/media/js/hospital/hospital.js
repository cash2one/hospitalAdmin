$(function(){
	/** 开通/关闭线下课程 **/
	$(".im-init").bind("click", function(){
		var nickName  = $(this).siblings("input[name='nickName']").val();
		var userId  = $(this).siblings("input[name='userId']").val();
		var userHeadUrl  = $(this).siblings("input[name='headUrl']").val();
		hospital.iminit(userId,nickName,userHeadUrl);
	});
});

$(function(){
	/** 点击关闭按钮刷新当前页面不然还是当前的sessionMap**/
	$(".close-chat").bind("click", function(){
		var backURL=baseURL+"/consult/consultList";
		location.href=backURL;
	});
});


var hospital = function(){
	return {
		iminit:function(userId,nickName ,userHeadUrl){
			//String busCode, String imAccount, String senderNickName, String senderHeadUrl, String friendAccount, String nickName, String headUrl
			var  busCode = "50101";
			var hospitalId =$("#hospitalId").val();
			var senderHeadUrl  = "";
			art.dialog.open('index?hospitalId='+hospitalId+'&userName='+nickName+'&userId='+userId+'&userHeadUrl='+userHeadUrl+'&busCode=50101',{
				width : 560,
				height : 620,
				lock : true,
				resize : false,
				title : '问卷宣教列表'
			});
		}
	}
}();

