$(function(){
	
	//修改之前回显数据
	var pregnancyTime=$("#pregnancyTimeData").val();
	var morningSicknessTime=$("#morningSicknessTimeData").val();
	var quickeningTime=$("#quickeningTimeData").val();
	var animalContactTime=$("#animalContactTimeData").val();
	var takeTeratogenicDrugContent=$("#takeTeratogenicDrugContentData").val();
	var anamnesis=$("#anamnesisData").val();
	var familyHistory=$("#familyHistoryData").val();
	var allergicType=$("#allergicTypeData").val();

	//当前孕周
	if(pregnancyTime!=null && pregnancyTime !=""){
		var pregnantTime=pregnancyTime.split(",");
		$("#pregnantWeek").val(pregnantTime[0]);
		$("#pregnantDay").val(pregnantTime[1]);
	}
	//初感胎动
	if(quickeningTime!=null && quickeningTime!=""){
		var quickenTime=quickeningTime.split(",");
		$("#quickeningWeek").val(quickenTime[0]);
		$("#quickeningDay").val(quickenTime[1]);
	}
	//猫狗动物接触周期
	if(animalContactTime!=null && animalContactTime!=""){
		var animalContact=animalContactTime.split(",");
		$("#animalContactStartWeek").val(animalContact[0]);
		$("#animalContactStartDay").val(animalContact[1]);
		$("#animalContactEndWeek").val(animalContact[2]);
		$("#animalContactEndDay").val(animalContact[3]);
		$("#animalContactNumber").text((animalContact[0]*7+animalContact[1])-(animalContact[2]*7+animalContact[3]));
	}
	//服用可能致畸的药物
	if(takeTeratogenicDrugContent!=null && takeTeratogenicDrugContent!=""){
		var html="";
		var takeTeratogenicDrugContentArray=takeTeratogenicDrugContent.split(";");
		for(var i=0;i<takeTeratogenicDrugContentArray.length-1;i++){
			if(i==0){
				if(takeTeratogenicDrugContentArray[i]!=null && takeTeratogenicDrugContentArray[i]!=""){
					var takeTeratogenicDrugContentEveryArray=takeTeratogenicDrugContentArray[i].split("+");
					html+='<tr style="height:45px;">'+
				    '<td width="30%"><input style="width:80%;"  type="text" value="'+takeTeratogenicDrugContentEveryArray[0]+'" placeholder="请输入药名"></td>'+                   
				    '<td width="6%">服用时间段：</td><td width="6%">' +
					'<input style="width: 160px; height:28px;" type="text"  id="takeTeratogenicDrugStartTime'+index1+'" placeholder="开始日期" class="form-control" value="'+takeTeratogenicDrugContentEveryArray[1]+'" ></td>'+  
					'<td width="3%">至</td><td width="10%">'+
					'<input style="width: 160px; height:28px;" type="text" value="'+takeTeratogenicDrugContentEveryArray[2]+'"  id="takeTeratogenicDrugEndTime'+index1+'" placeholder="结束日期"></td>'+
					'<td width="15%"><button type="button" class="btn btn-info" id="takeTeratogenicDrugBtnAdd"><i class="icon-plus"></i> 添加药物</button></td>'+
					'</tr>';
				}
			}else{
				if(takeTeratogenicDrugContentArray[i]!=null && takeTeratogenicDrugContentArray[i]!=""){
					var takeTeratogenicDrugContentEveryArray=takeTeratogenicDrugContentArray[i].split("+");
					html+='<tr style="height:45px;">'+
				    '<td width="30%"><input style="width:80%;"  type="text" value="'+takeTeratogenicDrugContentEveryArray[0]+'" placeholder="请输入药名"></td>'+                   
				    '<td width="6%">服用时间段：</td><td width="6%">' +
					'<input style="width: 160px; height:28px;" type="text"  id="takeTeratogenicDrugStartTime'+index1+'" placeholder="开始日期" class="form-control" value="'+takeTeratogenicDrugContentEveryArray[1]+'" ></td>'+  
					'<td width="3%">至</td><td width="10%">'+
					'<input style="width: 160px; height:28px;" type="text" value="'+takeTeratogenicDrugContentEveryArray[2]+'"  id="takeTeratogenicDrugEndTime'+index1+'" placeholder="结束日期"></td>'+
					'<td width="15%"><button type="button" class="btn btn-danger" id="takeTeratogenicDrugBtnDel" onclick="delTakeTeratogenicDrug(this);"><i class="icon-minus"></i> 删除药物</button></td>'+
					'</tr>';
				}
			}
		}
		$("#takeTeratogenicDrugContentTable").html(html);
	}
	//过敏史
	if(allergicType!=null && allergicType!=""){
		var allergicTypeArray=allergicType.split(";");
		var html="";
		if(allergicTypeArray!=null && allergicTypeArray!=""){
			for(var i=0;i<allergicTypeArray.length;i++){
				if(allergicTypeArray[i]!=null && allergicTypeArray[i]!=""){
					html+="<tr>";
					var allergicTypeEvery=allergicTypeArray[i].split(",");
					if(allergicTypeEvery!=null && allergicTypeEvery!=""){
						html+='<td>过敏类型：<select>';
						if(allergicTypeEvery[0]!=null && allergicTypeEvery[0]!=""){
								html+='<option value="0"';
								if(allergicTypeEvery[0]==0){
									html+='selected';
								}
								html+='>请选择</option>';
								html+='<option value="1">';
								if(allergicTypeEvery[0]==1){
									html+='selected';
								}
								html+='吸入式过敏</option>';
								html+='<option value="2">';
								if(allergicTypeEvery[0]==2){
									html+='selected';
								}
								html+='食入式过敏</option>';
								html+='<option value="3">';
								if(allergicTypeEvery[0]==3){
									html+='selected';
								}
								html+='接触式过敏</option>';
								html+='<option value="4">';
								if(allergicTypeEvery[0]==4){
									html+='selected';
								}
								html+='注射式过敏</option>';
								html+='<option value="5">';
								if(allergicTypeEvery[0]==5){
									html+='selected';
								}
								html+='自身组织抗原</option>';
						}
						html+="</select></td>";
						html+='<td>过敏物名称：<input style="width:150px;" type="text" value="'+allergicTypeEvery[1]+'"></td>'+
					    '<td>发现过敏日期：<input style="width:160px;" type="text" value="'+allergicTypeEvery[2]+'" id="allergicHistoryStartTime'+index2+'" placeholder="日期选择"></td>'+
					    '<td>脱敏日期：<input style="width:160px;" type="text" id="allergicHistoryEndTime'+index2+'" value="'+allergicTypeEvery[3]+'" placeholder="日期选择"></td>'+
					    '<td><button type="button" class="btn btn-info" id="allergicHistoryBtnAdd"><i class="icon-plus"></i> 添加</button></td>';
					}
					html+="</tr>";
				}
			}
		}
		$("#allergicTypeTable").html(html);
	}
	
	//既往史(遍历表格，与数据比较，存在就选择复选框并赋值日期)
	if(anamnesis!=null && anamnesis!=""){
		var anamnesisArray=anamnesis.split(";");
		if(anamnesisArray!=null && anamnesisArray!=""){
			for(var i=0;i<anamnesisArray.length;i++){
				var anamnesisEvery=anamnesisArray[i].split(",");
				$("#anamnesisTable tr td").each(function(){
					if(contains(anamnesisEvery,$(this).find("input:eq(0)").val())){
						$(this).find("input:eq(0)").attr("checked","checked");
						$(this).find("input:eq(1)").val(anamnesisEvery[1]);
					}
				});
			}
		}
	}
	//家族史(遍历表格，与数据比较，并勾选对应的复选框)
	if(familyHistory!=null && familyHistory!=""){
		var familyHistoryArray=familyHistory.split(";");
		if(familyHistoryArray!=null && familyHistoryArray!=""){
			for(var i=0;i<familyHistoryArray.length;i++){
				var familyHistoryEvery=familyHistoryArray[i].split(":");
				//循环表格
				$("#familyHistoryTable tr").each(function(){
					if(familyHistoryEvery[0]==$(this).find("td input:eq(0)").val()){
							if(familyHistoryEvery[1]!=null && familyHistoryEvery[1]!=""){
								var familyHistoryOne=familyHistoryEvery[1].split(",");
								if(contains(familyHistoryOne,$(this).find("input:eq(1)").val())){
									for(var j=0;j<familyHistoryOne.length;j++){
										if($(this).find("input:eq(1)").val()==familyHistoryOne[j]){
											$(this).find("input:eq(1)").attr("checked","checked");
										}
									}
								}
								if(contains(familyHistoryOne,$(this).find("input:eq(2)").val())){
									for(var j=0;j<familyHistoryOne.length;j++){
										if($(this).find("input:eq(2)").val()==familyHistoryOne[j]){
											$(this).find("input:eq(2)").attr("checked","checked");
										}
									}
								}
								if(contains(familyHistoryOne,$(this).find("input:eq(3)").val())){
									for(var j=0;j<familyHistoryOne.length;j++){
										if($(this).find("input:eq(3)").val()==familyHistoryOne[j]){
											$(this).find("input:eq(3)").attr("checked","checked");
										}
									}
								}
								if(contains(familyHistoryOne,$(this).find("input:eq(4)").val())){
									for(var j=0;j<familyHistoryOne.length;j++){
										if($(this).find("input:eq(4)").val()==familyHistoryOne[j]){
											$(this).find("input:eq(4)").attr("checked","checked");
										}
									}
								}
							}
						}
				});
			}
		}
	}
	
	
	
	var index1=2;
	var index2=2;
	//添加服用可能致畸药物
	var html='<tr style="height:45px;">'+
		    '<td width="30%"><input style="width:80%;"  type="text" value="" placeholder="请输入药名"></td>'+                   
		    '<td width="6%">服用时间段：</td><td width="6%">' +
			'<input style="width: 160px; height:28px;" type="text"  id="takeTeratogenicDrugStartTime'+index1+'" placeholder="开始日期" class="form-control" value="" ></td>'+  
			'<td width="3%">至</td><td width="10%">'+
			'<input style="width: 160px; height:28px;" type="text" value=""  id="takeTeratogenicDrugEndTime'+index1+'" placeholder="结束日期"></td>'+
			'<td width="15%"><button type="button" class="btn btn-danger" name="takeTeratogenicDrugBtnDel" onclick="delTakeTeratogenicDrug(this);"><i class="icon-minus"></i> 删除药物</button></td>'+
			'</tr>';
	$("#takeTeratogenicDrugBtnAdd").bind("click",function(){
		index1++;
		$(this).parent().parent().parent().append(html);
		$("input[id*='Time']").each(function(){
			laydate({
			    elem: '#'+$(this).attr("id"), 
			    event: 'focus' ,
				max: laydate.now(),
			});
		});
	});
	//添加过敏史
	var html1='<tr style="height:45px;">'+
			    '<td>过敏类型：'+
			    '<select>'+
						'<option value="0">请选择</option>'+
						'<option value="1">吸入式过敏</option>'+
            			'<option value="2">食入式过敏</option>'+
            			'<option value="3">接触式过敏</option>'+
            			'<option value="4">注射式过敏</option>'+
            			'<option value="5">自身组织抗原</option>'+
				'</select>'+
			    '</td>'+
			    '<td width="40%">过敏物名称：<input style="width:70%" type="text" value=""></td>'+
			    '<td>发现过敏日期：<input style="width:160px;" type="text" value="" id="allergicHistoryStartTime'+index2+'" placeholder="日期选择"></td>'+
			    '<td>脱敏日期：<input style="width:160px;" type="text" id="allergicHistoryEndTime'+index2+'" value="" placeholder="日期选择"></td>'+
			    '<td><button type="button" class="btn btn-danger" onclick="delAllergicHistory(this)"><i class="icon-minus"></i> 删除</button></td>'+
			'</tr>';
	$("#allergicHistoryBtnAdd").bind("click",function(){
		index2++;
		$(this).parent().parent().parent().append(html1);
		$("input[id*='Time']").each(function(){
			laydate({
			    elem: '#'+$(this).attr("id"), 
			    event: 'focus' ,
				max: laydate.now(),
			});
		});
	});
	
	//加载页面的时候判断所有单选按钮，如果是无，则禁用其后面的输入框
	$(":radio:checked").each(function(){
		if($(this).val()==0){
			//数字输入框
			$(this).parent().parent().find("input[type='number']").attr("disabled",true);
			//text文本框
			$(this).parent().parent().find("input[type='text']").attr("disabled",true);
			//checkbox复选框
			$(this).parent().parent().find("input[type='checkbox']").attr("disabled",true);
			//日期选择
			$(this).parent().parent().find("input[type='date']").attr("disabled",true);
			//select下拉框
			$(this).parent().parent().find("select").attr("disabled",true);
			//button按钮
			$(this).parent().parent().find("button").attr("disabled",true);
		}
	});
	
	//当单选按钮在切换的时候，启用其下面的输入框
	$(":radio").each(function(){
		$(this).bind("change",function(){
			if($(this).val()==0){
				//数字输入框
				$(this).parent().parent().find("input[type='number']").attr("disabled",true);
				$(this).parent().parent().find("input[type='number']").val("0");
				//text文本框
				$(this).parent().parent().find("input[type='text']").attr("disabled",true);
				$(this).parent().parent().find("input[type='text']").val("");
				//checkbox复选框
				$(this).parent().parent().find("input[type='checkbox']").attr("disabled",true);
				$(this).parent().parent().find("input[type='checkbox']").attr("checked",false);
				//日期选择
				$(this).parent().parent().find("input[type='date']").attr("disabled",true);
				$(this).parent().parent().find("input[type='date']").val("");
				//select下拉框
				$(this).parent().parent().find("select").attr("disabled",true);
				$(this).parent().parent().find("select").val("0");
				//button按钮
				$(this).parent().parent().find("button").attr("disabled",true);
			}else{
				//数字输入框
				$(this).parent().parent().find("input[type='number']").attr("disabled",false);
				//text文本框
				$(this).parent().parent().find("input[type='text']").attr("disabled",false);
				//checkbox复选框
				$(this).parent().parent().find("input[type='checkbox']").attr("disabled",false);
				//日期选择
				$(this).parent().parent().find("input[type='date']").attr("disabled",false);
				//select下拉框
				$(this).parent().parent().find("select").attr("disabled",false);
				//button按钮
				$(this).parent().parent().find("button").attr("disabled",false);
			}
		});
	});
	
	//点击保存，提交表单
	$("#updateArchiveHistoryTakingSubmit").bind("click",function(){
		mergerData();
		$("#updateArchiveHistoryTakingForm").submit();
	});
	
});

//删除药物
function delTakeTeratogenicDrug(obj){
	$(obj).parent().parent().remove();
}

//删除过敏史
function delAllergicHistory(obj){
	$(obj).parent().parent().remove();
}

//判断数组中是否存在另一个值
function contains(arr, obj) {  
    var i = arr.length;  
    while (i--) {  
        if (arr[i] == obj) {  
            return true;  
        }  
    }  
    return false;  
}  

//提交表单之间，组装部分数据
function mergerData(){
	//怀孕周期
	$("#pregnancyTime").val($("#menstrualCycleWeek").val()+","+$("#menstrualCycleDay").val());
	//早孕反应周期
	$("#morningSicknessTime").val($("#morningSicknessWeek").val()+","+$("#morningSicknessDay").val());
	//初感胎动
	$("#quickeningTime").val($("#quickeningWeek").val()+","+$("#quickeningDay").val());
	//猫狗动物接触周期
	$("#animalContactTime").val($("#animalContactStartWeek").val()+","+$("#animalContactStartDay").val()+","+$("#animalContactEndWeek").val()+","+$("#animalContactEndDay").val());
	//服用可能致畸的药物
	var takeTeratogenicDrugContent="";
	$("#takeTeratogenicDrugTable tr").each(function(index){
		alert($(this).find("td input[type='text']").length);
		$(this).find("td input[type='text']").each(function(index1){
				takeTeratogenicDrugContent+=$(this).val()+"+";
		});
		takeTeratogenicDrugContent=takeTeratogenicDrugContent.substring(0, takeTeratogenicDrugContent.length-1);
		takeTeratogenicDrugContent+=";";
	});
	$("#takeTeratogenicDrugContent").val(takeTeratogenicDrugContent);
	
	
	//接触物理有害因素
	var contactPhysicsHarmfulContent="";
	$("input[name='contactPhysicsHarmfulChoose']:checked").each(function(index){
		contactPhysicsHarmfulContent+=$(this).val()+",";
	});
	if(contactPhysicsHarmfulContent.length>0){
		contactPhysicsHarmfulContent=contactPhysicsHarmfulContent.substring(0, contactPhysicsHarmfulContent.length-1);
	}
	$("#contactPhysicsHarmfulContent").val(contactPhysicsHarmfulContent);
	
	
	//接触高浓度工业毒物
	var contactIndustrialToxicantContent="";
	$("input[name='contactIndustrialToxicantChoose']:checked").each(function(){
		contactIndustrialToxicantContent+=$(this).val()+",";
	});
	if(contactIndustrialToxicantContent.length>0){
		contactIndustrialToxicantContent=contactIndustrialToxicantContent.substring(0, contactIndustrialToxicantContent.length-1);
	}
	$("#contactIndustrialToxicantContent").val(contactIndustrialToxicantContent);
	
	
	//病毒感染
	var virusInfection="";
	$("input[name='virusInfectionChoose']:checked").each(function(){
		virusInfection+=$(this).val()+",";
	});
	if(virusInfection.length>0){
		virusInfection=virusInfection.substring(0, virusInfection.length-1);
	}
	$("#virusInfection").val(virusInfection);
	
	//既往病史
	var anamnesis="";
	$("input[name='anamnesisChoose']:checked").each(function(){
		anamnesis+=$(this).val()+","+$(this).next().val()+";";
	});
	$("#anamnesis").val(anamnesis);
	$("#otherAnamnesis").val($("#otherAnamnesisText").val()+$("#anamnesisOtherTime").val());
	
	
	//家族史
	var familyHistory="";
	$("#familyHistoryTable tr").each(function(index){
		var familyHistoryLength=$(this).find("td input[type='checkbox']:checked").length;
		if(familyHistoryLength>0){
			familyHistory+=$(this).find("td input").val()+":";
			$(this).find("td").each(function(number){
				if($(this).find("input[type='checkbox']").is(":checked")){
					familyHistory+=$(this).find("input[type='checkbox']:checked").val()+",";
				}
			});
			familyHistory=familyHistory.substring(0,familyHistory.length-1);
			familyHistory+=";";
		}
	});
	
	
	
	$("#familyHistory").val(familyHistory);
	//过敏史
	var allergicType="";
	$("#allergicTypeTable tr").each(function(index1){
		allergicType+=$(this).find("select option:selected").val()+",";
		$(this).find("td input[type='text']").each(function(index2){
			allergicType+=$(this).val()+",";
		});
		allergicType=allergicType.substring(0, allergicType.length-1);
		allergicType+=";";
	});
	$("#allergicType").val(allergicType);
}