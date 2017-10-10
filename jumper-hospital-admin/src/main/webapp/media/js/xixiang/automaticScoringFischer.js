/** 自动评分js 改良Fischer **/
$(function(){
	$(".call_cont").bind("click", AutoScore.saveAutoScore);
	autoGradeInfo=AutoScore.initOldScore();
	
	$(".leftTableData").bind("change",AutoScore.changeScore);
	
	$(".checkbox,.descContent").bind("change",AutoScore.setRightCheckBoxData);
	
	//生成报告页面,要先保存自动评分结果后生成报告
	$(".autoScoreGeneratePDF").bind("click",AutoScore.saveAutoScore);
	
	$("#info-dialog-autoScore .close-autoScore,#info-dialog-autoScore .info-dialog-sure").bind("click", AutoScore.closed); //确定取消
	
});
var focusInput;
var AutoScore = function(){
	return {
		/*	
		 * 	popup: 遮挡层
		 *	isShow：显示隐藏
		 *	message： 提示
		*/
		popup: function(isShow,message,currentInput){
			focusInput = currentInput;
			var masked = '<div class="modal-backdrop in"></div>';
			isShow = isShow? 'block' : 'none';
			if($('#info-dialog-autoScore').css('display') == 'block'){
				$('#info-dialog-autoScore,.modal-backdrop').hide();
				$('#info-dialog-autoScore .modal-body').text('');
			}else{
				if($('.modal-backdrop').length == 0){
					$('body').append(masked);
				}
				$('#info-dialog-autoScore,.modal-backdrop').show();
				$('#info-dialog-autoScore .modal-body').text(message);
			}
			currentInput && $(currentInput).select();
		},
		closed: function(){
			AutoScore.popup(false,"", focusInput);
		},
		/*保存自动评分数据*/
		saveAutoScore : function(){
			//添加输入框的校验
			var reg=/^\d{1,4}$/;
			var inputs= $(".source td input");
			for(var i=0;i<inputs.length;i++){
				var currentValue=$(inputs[i]).val();
				if(!reg.test(currentValue)){
					//添加校验
					if(""==currentValue){
	        			AutoScore.popup(true,"当前值不能为空！",inputs[i]);
					}else if(!reg.test(currentValue)){
						AutoScore.popup(true,"请输入1-4位数字！",inputs[i]);
					}
					return ;
				}
			}
			
			$.ajax({
                type : "POST",
                url : baseURL+'/report/saveAutoScore',
                data : autoGradeInfo ,
                dataType : "json", 
                timeout : 60000,
                async: false,
                success : function(data){
                	//保存结束后，生成报告
                	AutoScore.autoScoreGeneratePDF();
                }
            });
			
		},
		//自动评分生成报告
		autoScoreGeneratePDF:function(){
			
			App.confirm_show("", "确认生成报告？", function(){
				$(".info-confirm-cancel").trigger("click");
				
				var check_value = "";
				$($("input:checked").siblings()).each(function(){
					check_value+=$(this).eq(0).attr("value")+","
				});
				
				var id=$(".autoScoreGeneratePDF").attr("consumerid");
				var remark =autoGradeInfo.remark;
				var source = $("#sourceScore").text();
				var start_offset=$(".autoScoreGeneratePDF").attr("start");
				var end_offset= $(".autoScoreGeneratePDF").attr("end");
				var speed=$(".autoScoreGeneratePDF").attr("speed");
				
				if(remark.length > 120){
					App.dialog_show("", "备注信息在120字以内！", function(){
						$(".info-dialog-close").trigger("click");
					});
					return;
				}
				
				$.ajax({
                    type : "POST",
                    url : baseURL+'/report/autoScoreGenerate',
                    data : {'id' : id, 'remark' : remark, 'offset' : '', 'resultItem' : check_value, 'source' : source, 'speed':speed, 'start':start_offset, 'end':end_offset},
                    dataType : "json", 
                    timeout : 60000,
                    async: false,
                    success : function(data){
                    	if(data == 'success'){
                    		App.dialog_show("", "报告信息生成成功！", function(){
    							//$(".info-dialog-close").trigger("click");
    							location.href=baseURL+"/report/audit";
    						});
                    	}else if(data == 'failed'){
                    		App.dialog_show("", "报告生成失败！", function(){
    							//$(".info-dialog-close").trigger("click");
                    			location.href=baseURL+"/report/audit";
    						});
                    	}
                    }
                });
			});
			
		},
		/*修改评分方式的时候修改页面展示 改良Fischer */
		changeScore:function(){
			var currentChangeColumn= $(this).attr("name");
			// 填写1~4位的数字
			var reg=/^\d{1,4}$/;
			
			console.log(currentChangeColumn);
			//胎心率基线
			if("basal"==currentChangeColumn){
				var currentValue=autoGradeInfo.finalBaseLine=$(this).val();
				if(currentValue<100||currentValue>180){
					autoGradeInfo.finalBaseLine=currentValue;
					autoGradeInfo.finalBaseLineScore="0";
					$("#basalScore").text("0");
					$("#basalAnalysis").text("重度过缓（<100）或 重度过速(>180)");
					
				}else if(currentValue>=100&&currentValue<=109 || currentValue>=161&&currentValue<=180){
					autoGradeInfo.finalBaseLine=currentValue;
					autoGradeInfo.finalBaseLineScore="1";
					$("#basalScore").text("1");
					$("#basalAnalysis").text("轻度过缓（100-109）或者 轻度过速（161-180）");
					
				}else if(currentValue>=110&&currentValue<=160){
					autoGradeInfo.finalBaseLine=currentValue;
					autoGradeInfo.finalBaseLineScore="2";
					$("#basalScore").text("2");
					$("#basalAnalysis").text("正常(110-160)");
				}
				
				//添加校验
				if(""==currentValue){
        			AutoScore.popup(true,"请输入胎心率基线！",this);
				}else if(!reg.test(currentValue)){
					AutoScore.popup(true,"胎心率基线请输入1-4位数字！",this);
				}
				
				
			//振幅变异
			}else if("bpmVar"==currentChangeColumn){
				var currentValue=autoGradeInfo.finalBpmVar=$(this).val();
				
				if(currentValue<3){
					autoGradeInfo.finalBpmVar=currentValue;
					autoGradeInfo.finalBpmVarScore="0";
					$("#bpmVScore").text("0");
					$("#bpmVarAnalysis").text("过小（<3）");
					
				}else if(currentValue>=3&&currentValue<=5 || currentValue>25){
					autoGradeInfo.finalBpmVar=currentValue;
					autoGradeInfo.finalBpmVarScore="1";
					$("#bpmVScore").text("1");
					$("#bpmVarAnalysis").text("较小（3-5）或  较大（>25）");
					
				}else if(currentValue>=6&&currentValue<=25){
					autoGradeInfo.finalBpmVar=currentValue;
					autoGradeInfo.finalBpmVarScore="2";
					$("#bpmVScore").text("2");
					$("#bpmVarAnalysis").text("正常（6-25）");
				}
				
				//添加校验
				if(""==currentValue){
        			AutoScore.popup(true,"请输入振幅变异！",this);
				}else if(!reg.test(currentValue)){
					AutoScore.popup(true,"振幅变异请输入1-4位数字！",this);
				}
				
			//周期变异
			}else if("freqVar"==currentChangeColumn){
				var currentValue=autoGradeInfo.finalFreqVar=$(this).val();
				
				if(currentValue<2){
					autoGradeInfo.finalFreqVar=currentValue;
					autoGradeInfo.finalFreqVarScore="0";
					$("#freqVScore").text("0");
					$("#freqVarAnalysis").text("过低（<2）");
					
				}else if(currentValue>=2&&currentValue<=5){
					autoGradeInfo.finalFreqVar=currentValue;
					autoGradeInfo.finalFreqVarScore="1";
					$("#freqVScore").text("1");
					$("#freqVarAnalysis").text("较低（2-5）");
					
				}else if(currentValue>5){
					autoGradeInfo.finalFreqVar=currentValue;
					autoGradeInfo.finalFreqVarScore="2";
					$("#freqVScore").text("2");
					$("#freqVarAnalysis").text("正常（>5）");
				}
				
				
				//添加校验
				if(""==currentValue){
        			AutoScore.popup(true,"请输入周期变异！",this);
				}else if(!reg.test(currentValue)){
					AutoScore.popup(true,"周期变异请输入1-4位数字！",this);
				}
				
				
			//加速
			}else if("numAcc"==currentChangeColumn){
				var currentValue=autoGradeInfo.finalNumAcc=$(this).val();
				
				//0 分
				if(currentValue==0){
					autoGradeInfo.finalNumAcc=currentValue;
					autoGradeInfo.finalNumAccScore="0";
					$("#numAccScore").text("0");
					$("#numAccAnalysis").text("没有加速");
				//1分
				}else if(currentValue>=1&&currentValue<=2){
					autoGradeInfo.finalNumAcc=currentValue;
					autoGradeInfo.finalNumAccScore="1";
					$("#numAccScore").text("1");
					$("#numAccAnalysis").text("较少（1-2）");
				//2分
				}else if(currentValue>2){
					autoGradeInfo.finalNumAcc=currentValue;
					autoGradeInfo.finalNumAccScore="2";
					$("#numAccScore").text("2");
					$("#numAccAnalysis").text("正常（>2）");
				}
				
				//添加校验
				if(""==currentValue){
        			AutoScore.popup(true,"请输入加速次数！",this);
				}else if(!reg.test(currentValue)){
					AutoScore.popup(true,"加速次数请输入1-4位数字！",this);
				}
				
			//减速
			}else if("numDec"==currentChangeColumn){
				var currentValue=autoGradeInfo.finalNumDec=$(this).val();
				//0
				if(currentValue>=2){
					autoGradeInfo.finalNumDec=2;
					autoGradeInfo.finalNumDecScore="0";
					$("#numDecScore").text("0");
					$("#numDecAnalysis").text("LD,PD,重度VD");
				//2
				}else if(currentValue==1){
					autoGradeInfo.finalNumDec=1;
					autoGradeInfo.finalNumDecScore="1";
					$("#numDecScore").text("1");
					$("#numDecAnalysis").text("轻度VD>2,或ED>2");
				//2
				}else if(currentValue=="0"){
					autoGradeInfo.finalNumDec=0;
					autoGradeInfo.finalNumDecScore="2";
					$("#numDecScore").text("2");
					$("#numDecAnalysis").text("无");
				}
				
			}
			
			var  finalResult=parseInt(autoGradeInfo.finalBaseLineScore)+parseInt(autoGradeInfo.finalBpmVarScore)+parseInt(autoGradeInfo.finalFreqVarScore)
			+parseInt(autoGradeInfo.finalNumAccScore)+parseInt(autoGradeInfo.finalNumDecScore);
			
			autoGradeInfo.finalResult=finalResult;
			
			//更新最后总分显示变化
			$("#sourceScore").text(finalResult);
			
			return autoGradeInfo;
		},
		/*老数的表单数据*/
		initOldScore:function(){
			var autoGradeInfo={};
			//////////本例结果/////////
			//胎心基线
			autoGradeInfo.autoBaseLine=$("[name='basal']").val();
			autoGradeInfo.finalBaseLine=$("[name='basal']").val();
			//振幅变异
			autoGradeInfo.autoBpmVar=$("[name='bpmVar']").val();
			autoGradeInfo.finalBpmVar=$("[name='bpmVar']").val();
			//周期变异
			autoGradeInfo.autoFreqVar=$("[name='freqVar']").val();
			autoGradeInfo.finalFreqVar=$("[name='freqVar']").val();
			//加速次数
			autoGradeInfo.autoNumAcc=$("[name='numAcc']").val();
			autoGradeInfo.finalNumAcc=$("[name='numAcc']").val();
			//减速次数 下拉框
			autoGradeInfo.autoNumDec=$("[name='numDec']").val();
			autoGradeInfo.finalNumDec=$("[name='numDec']").val();
			//胎动次数
			autoGradeInfo.autoNumFetalMove=$("#numFetalMove").val();
			autoGradeInfo.finalNumFetalMove=$("#numFetalMove").val();
			
			/////////得分数据//////////
			//胎心基线评分
			autoGradeInfo.autoBaseLineScore=$("#basalScore").text();
			autoGradeInfo.finalBaseLineScore=$("#basalScore").text();
			//变异振幅评分
			autoGradeInfo.autoBpmVarScore=$("#bpmVScore").text();
			autoGradeInfo.finalBpmVarScore=$("#bpmVScore").text();
			//变异频率评分
			autoGradeInfo.autoFreqVarScore=$("#freqVScore").text();
			autoGradeInfo.finalFreqVarScore=$("#freqVScore").text();
			//胎心率加速评分
			autoGradeInfo.autoNumAccScore=$("#numAccScore").text();
			autoGradeInfo.finalNumAccScore=$("#numAccScore").text();
			//胎心率减速评分
			autoGradeInfo.autoNumDecScore=$("#numDecScore").text();
			autoGradeInfo.finalNumDecScore=$("#numDecScore").text();
			//胎动次数评分
			autoGradeInfo.autoFetalMoveScore=$("#fetalMoveScore").text();
			autoGradeInfo.finalFetalMoveScore=$("#fetalMoveScore").text();
			
			/////////////右边checkBox数据初始化///////////
			//有反应
			autoGradeInfo.response=0;
			//无反应
			autoGradeInfo.adiaphoria=0;
			//正弦曲线
			autoGradeInfo.sinusoid=0;
			//不满意
			autoGradeInfo.nstYawp=0;
			//CST或NS-CST
			autoGradeInfo.cstNsCst=0;
			//阳性
			autoGradeInfo.positive=0;
			//阴性
			autoGradeInfo.feminine=0;
			//可疑
			autoGradeInfo.suspicious=0;
			//不满意
			autoGradeInfo.octYawp=0;
			//备注
			autoGradeInfo.remark="";
			
			///////// rerecordId////////
			autoGradeInfo.recordId=$("[name='recordId']").val();
			autoGradeInfo.type=$("[name='scoringMethod']").val();
			
			return autoGradeInfo;
		},
		//右边的值设置
		setRightCheckBoxData:function(){

			var curentEle= $(this).find("input").attr("name");
			if(undefined==curentEle){
				curentEle= $(this).attr("name");
			}
			//有反应
			if("response"==curentEle){
				//如果这个被选中
				if($(this).find("input").is(':checked')){
					autoGradeInfo.response=1;
				//如果没有被选中
				}else{
					autoGradeInfo.response=0;
				}
			//无反应
			}else if("adiaphoria"==curentEle){
				//如果这个被选中
				if($(this).find("input").is(':checked')){
					autoGradeInfo.adiaphoria=1;
				//如果没有被选中
				}else{
					autoGradeInfo.adiaphoria=0;
				}
			//正弦曲线
			}else if("sinusoid"==curentEle){
				//如果这个被选中
				if($(this).find("input").is(':checked')){
					autoGradeInfo.sinusoid=1;
				//如果没有被选中
				}else{
					autoGradeInfo.sinusoid=0;
				}
			//不满意
			}else if("nstYawp"==curentEle){
				//如果这个被选中
				if($(this).find("input").is(':checked')){
					autoGradeInfo.nstYawp=1;
				//如果没有被选中
				}else{
					autoGradeInfo.nstYawp=0;
				}
			
			//CST或NS-CST
			}else if("cstNsCst"==curentEle){
				//如果这个被选中
				if($(this).find("input").is(':checked')){
					autoGradeInfo.cstNsCst=1;
				//如果没有被选中
				}else{
					autoGradeInfo.cstNsCst=0;
				}
			//阳性
			}else if("positive"==curentEle){
				//如果这个被选中
				if($(this).find("input").is(':checked')){
					autoGradeInfo.positive=1;
				//如果没有被选中
				}else{
					autoGradeInfo.positive=0;
				}
			//阴性
			}else if("feminine"==curentEle){
				//如果这个被选中
				if($(this).find("input").is(':checked')){
					autoGradeInfo.feminine=1;
				//如果没有被选中
				}else{
					autoGradeInfo.feminine=0;
				}
			//可疑
			}else if("suspicious"==curentEle){
				//如果这个被选中
				if($(this).find("input").is(':checked')){
					autoGradeInfo.suspicious=1;
				//如果没有被选中
				}else{
					autoGradeInfo.suspicious=0;
				}
			//不满意
			}else if("octYawp"==curentEle){
				//如果这个被选中
				if($(this).find("input").is(':checked')){
					autoGradeInfo.octYawp=1;
				//如果没有被选中
				}else{
					autoGradeInfo.octYawp=0;
				}
			//备注
			}else if("remark"==curentEle){
				autoGradeInfo.remark=$(this).val();
			}
		}
	}
}();
