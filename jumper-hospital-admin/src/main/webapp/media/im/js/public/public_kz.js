$(function(){
	/*放大图片*/
	$(document).on("click",".showImgIm",function(){ 
			var src = $(this).attr("data-src");
			var width = $(this).attr("data-width");
			var height = $(this).attr("data-height");
			if(parseInt(width)>900){
				height=parseInt(height*(850/width));
				width=850;
			}
			if(parseInt(height)>600){
				width=parseInt(width*(550/height));
				height=550;
			}
			art.dialog({
				    padding: 0,
				    title: '',
				    content: '<img src="'+src+'" width="'+width+'" height="'+height+'" />',
				    lock: true
				});
	});
	/*点击显示出发送图片框*/
	$(document).on("click",".imga",function(){
			$("#uploadWindow").show();
	});
	/*查询历史记录*/
	$(document).on("click","#xxjl",function(){
		$(".centerShowImg").hide();
		$(".messageLs").show();
		historyMessage();
	/*	getLastC2CHistoryMsgs(getHistoryMsgCallback,
                    function (err) {
                        alert(err.ErrorInfo);
                    });*/
		
	});
	
	$(document).on("click","#closeWindow",function(){
		getPrePageC2CHistoryMsgs(getHistoryMsgCallback1,
                    function (err) {
                        alert(err.ErrorInfo);
                    });
	})
})