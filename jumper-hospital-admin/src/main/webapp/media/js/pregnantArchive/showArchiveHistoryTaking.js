$(function(){
	var pregnancyTime=$("#pregnancyTime").val();
	var morningSicknessTime=$("#morningSicknessTime").val();
	var quickeningTime=$("#quickeningTime").val();
	var animalContactTime=$("#animalContactTime").val();
	var takeTeratogenicDrugContent=$("#takeTeratogenicDrugContent").val();
	var anamnesis=$("#anamnesis").val();
	var familyHistory=$("#familyHistory").val();
	var allergicType=$("#allergicType").val();
	
	//当前孕周
	if(pregnancyTime!=null && pregnancyTime !=""){
		var pregnantTime=pregnancyTime.split(",");
		$("#pregnantWeek").text(pregnantTime[0]);
		$("#pregnantDay").text(pregnantTime[1]);
	}
	//初感胎动
	if(quickeningTime!=null && quickeningTime!=""){
		var quickenTime=quickeningTime.split(",");
		$("#quickeningWeek").text(quickenTime[0]);
		$("#quickeningDay").text(quickenTime[1]);
	}
	//猫狗动物接触周期
	if(animalContactTime!=null && animalContactTime!=""){
		var animalContact=animalContactTime.split(",");
		$("#animalContactStartWeek").text(animalContact[0]);
		$("#animalContactStartDay").text(animalContact[1]);
		$("#animalContactEndWeek").text(animalContact[2]);
		$("#animalContactEndDay").text(animalContact[3]);
		$("#animalContactNumber").text((animalContact[0]*7+animalContact[1])-(animalContact[2]*7+animalContact[3]));
	}
	//服用可能致畸的药物
	if(takeTeratogenicDrugContent!=null && takeTeratogenicDrugContent!=""){
		var html="";
		var takeTeratogenicDrugContentArray=takeTeratogenicDrugContent.split(";");
		for(var i=0;i<takeTeratogenicDrugContentArray.length-1;i++){
			if(takeTeratogenicDrugContentArray[i]!=null && takeTeratogenicDrugContentArray[i]!=""){
				var takeTeratogenicDrugContentEveryArray=takeTeratogenicDrugContentArray[i].split("+");
				html+="<tr><td>"+takeTeratogenicDrugContentEveryArray[0]+"</td><td>"+takeTeratogenicDrugContentEveryArray[1]+"至"+takeTeratogenicDrugContentEveryArray[2]+"</td></tr>";
			}
		}
		$("#takeTeratogenicDrugContentTable").html(html);
	}
	
	//既往病史
	if(anamnesis!=null && anamnesis!=""){
		$.ajax({
			url:"getAnamnesisNameById",
			data:{anamnesis:anamnesis},
			type:"post",
			success:function(data){
				if(data!=null&&data!=""){
					var html="<tr>";
					var anamnesisArray=data.split(";");
					for(var i=0;i<anamnesisArray.length;i++){
						if(i%3==0 && i!=0){
							html+="</tr>";
						}else{
							if(anamnesisArray[i]!=null&&anamnesisArray[i]!=""){
								var anamnesisEvery=anamnesisArray[i].split(",");
									html+="<td>疾病名称："+anamnesisEvery[0]+"&nbsp;&nbsp;&nbsp;&nbsp;确诊时间："+anamnesisEvery[1]+"</td>";
							}
						}
					}
					html+="</tr>";
					$("#anamnesisTable").html(html);
				}
			}
		});
	}
	
	//家族史
	if(familyHistory!=null && familyHistory!=""){
		$.ajax({
			url:"getFamilyHistoryNameById",
			data:{familyHistory:familyHistory},
			type:"post",
			success:function(data){
				if(data!=null && data!=""){
					var html="";
					var familyHistoryArray=data.split(";");
					if(familyHistoryArray!=null && familyHistoryArray.length>0){
						for(var i=0;i<familyHistoryArray.length;i++){
							var familyHistoryEvery=familyHistoryArray[i].split(":");
							if(familyHistoryEvery!=null && familyHistoryEvery.length>0){
								for(var j=0;j<familyHistoryEvery.length-1;j++){
									html+="<tr>";
									var name=familyHistoryEvery[0];
									var state=familyHistoryEvery[1];
									if(state.indexOf(",")>0){
										var familyHistoryOne=state.split(",");
										for(var k=1;k<5;k++){
											if(contains(familyHistoryOne,k)){
												for(var h=0;h<familyHistoryOne.length;h++){
													if(k==familyHistoryOne[h]){
														html+="<td>"+name+"</td>";
													}
												}
											}else{
												html+="<td>无</td>";
											}
										}
									}else{
										if(1==familyHistoryEvery[1]){
											html+="<td>"+name+"</td>";
										}else{
											html+="<td>无</td>";
										}
										if(2==familyHistoryEvery[1]){
											html+="<td>"+name+"</td>";
										}else{
											html+="<td>无</td>";
										}
										if(3==familyHistoryEvery[1]){
											html+="<td>"+name+"</td>";
										}else{
											html+="<td>无</td>";
										}
										if(4==familyHistoryEvery[1]){
											html+="<td>"+name+"</td>";
										}else{
											html+="<td>无</td>";
										}
									}
								}
								html+="</tr>";
							}
						}
					}
				}
				$("#familyHistoryTable").html(html);
			}
		});
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
						if(allergicTypeEvery[0]!=null && allergicTypeEvery[0]!=""){
							if(allergicTypeEvery[0]==1){
								html+="<td>过敏类型：吸入式过敏</td>";
							}else if(allergicTypeEvery[0]==2){
								html+="<td>过敏类型：食入式过敏</td>";
							}else if(allergicTypeEvery[0]==3){
								html+="<td>过敏类型：接触式过敏</td>";
							}else if(allergicTypeEvery[0]==4){
								html+="<td>过敏类型：注射式过敏</td>";
							}else if(allergicTypeEvery[0]==5){
								html+="<td>过敏类型：自身组织抗原</td>";
							}else{
								html+="<td>过敏类型：</td>";
							}
						}
						html+="<td>过敏物名称："+allergicTypeEvery[1]+"</td><td>发现过敏日期："+allergicTypeEvery[2]+"</td><td>脱敏日期："+allergicTypeEvery[3]+"</td>";
					}
					html+="</tr>";
				}
			}
		}
		$("#allergicTypeTable").html(html);
	}
});
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