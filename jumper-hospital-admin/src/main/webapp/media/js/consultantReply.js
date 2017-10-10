$(function(){

	$(".showImg").bind("click",function(){
		var src = $(this).attr("src");
		$("#big_img").attr("src", src);
		$("#dialog_img").trigger("click");
	});
	
	$("img").mouseout(function(){
		$(this).css("cursor", "pointer");
	});
	
	 var media = new Audio();
		//播放语音
		$('div.play').find('a[type=playVoice]').click(function(){
			if($(this).attr('value')==""){
				TopAlert('找不到文件!', true);
			}else{
				media.setAttribute("src", $(this).attr('value'));//$(this).attr('value')'assets/20150325035745487.mp3'
				media.play();
				
				$('div.play').css('display','block');	
				$('div.paused').css('display','none');	
				
				$(this).parent().css('display','none');
				$(this).parent().next().css('display','block');
			}
		});
		$(document).on('click','a[type=playVoice]',function(){ 
			if($(this).attr('value')==""){
				TopAlert('找不到文件!', true);
			}else{
				media.setAttribute("src", $(this).attr('value'));//$(this).attr('value')'assets/20150325035745487.mp3'
				media.play();
				
				$('div.play').css('display','block');	
				$('div.paused').css('display','none');	
				
				$(this).parent().css('display','none');
				$(this).parent().next().css('display','block');
			}
			  

			});
		//暂停或继续播放语音
		$('div.paused').find('a[type=pausedVoice]').click(function(){
			if(media.ended){
				media.load();
	            media.play();
			}else{
				if (media.paused) {
	                media.play();//播放
	            }else{
	                media.pause();
	            }
			}
		});
		
});