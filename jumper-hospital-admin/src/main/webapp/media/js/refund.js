$(function(){
	$(".refundBtn").on("click", RefundService.refundHandler);
});

var RefundService = function(){
	return {
		/**
		 * 退费处理
		 */
		refundHandler: function() {
			var refundReason = $(this).parent().prev().text(),
				refundId = $(this).attr("value");
			
			//弹出退费选择对话框
			$("#refund-select-confrim .modal-body").text(refundReason);
			$(".refund-select-href").trigger("click");
			
			//确认退费
			$(".refund-select-sure").unbind('click').click(function(){
				//关闭对话框
				$('#refund-select-confrim').modal('hide')
				App.ajaxRequest(
					'/user/addPayRefundAnnal',
					{'refundId': refundId, 'status': 50, 'reason': ""},
					'post',
					true,
					'json',
					function resultHandler(data){
						var message = "";
						
						if(data === 'failed'){
							message = "退费失败！";
						}else if(data === 'success'){
							message = "退费成功";
						}
						App.dialog_show("提示！", message, function(){
							$("#refund-select-confrim").trigger("click");
							if(data == 'success'){
								location.reload();
							}
						});
					}
				);
			});
			//拒绝退费
			var message = "";
			$(".refund-select-refuse").unbind("click").click(function(){
				//关闭对话框
				$('#refund-select-confrim').modal('hide')
				
				//弹出新的对话框
				$(".refund-reason-href").trigger("click");
				
				//提交拒绝原因
				$(".refund-reason-ok").unbind("click").click(function(){
					//关闭对话框
					$('#refund-reason-confrim').modal('hide');
					if($("#reason_id_2").val() && $("#reason_id_2").val().length < 200 && $("#reason_id_2").val().length >= 1){	
					
						App.ajaxRequest(
							'/user/addPayRefundAnnal',
							{'refundId': refundId, 'status': -1, 'reason': $("#reason_id_2").val()},
							'post',
							true,
							'json',
							function resultHandler(data){
								if(data === 'failed'){
									message = "退费失败！";
								}else if(data === 'success'){
									message = "退费已拒绝";
								}
								App.dialog_show("提示！", message, function(){
									$("#refund-select-confrim").trigger("click");
									if(data == 'success'){
										location.reload();
									}
								});
							}
						);
					}else{
						message = "拒绝退费原因应在1~200字符内";
						App.dialog_show("提示！", message, function(){
							$(".info-dialog-close").trigger("click");
						});
					}
				});
			});
		}
 	}
}();