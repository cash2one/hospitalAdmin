$(function () {
   /*   *//**省，市联动查询*//*
	var nowProvinceHtml = $("#nowStayProvince");
	var nowCityHtml = $("#nowStayCity");
	var nowStayProvinceId = $("#nowStayProvince").val();
	var nowStayCityId=$("#nowStayCity").val();
	var countryId = $("#country").val();
	*//**孕产妇省市联动**//*
	当国家已经默认为中国时-省联动查询
	if(null != countryId && countryId != "0"){
		//与后台交互获取该国家对应的省份
		ajaxLiandong("getProvincesByCountry",nowProvinceHtml,countryId,"province",'<option value="0">请选择省份 </option>',nowStayProvinceId);
		if(nowStayProvinceId != "0" || null != nowStayProvinceId){
			//与后台交互获取省份对应的城市
			ajaxLiandong("getCitysByProvince",nowCityHtml,nowStayProvinceId,"city",'<option value="0">请选择城市 </option>',nowStayCityId);
		}
	}*/
	
	/* 所有回显省市级联的方法 */
	var countryId = $("#country").val();
	var nowProvinceHtml;
	var nowCityHtml;
	var nowStayProvinceId;
	var nowStayCityId;
	$("select[name*='Province']").each(function(){
		nowProvinceHtml = $(this);
		nowCityHtml = $(this).next();
		nowStayProvinceId = $(this).val();
		nowStayCityId=$(this).next().val();
		if(null != countryId && countryId != "0"){
			//与后台交互获取该国家对应的省份
			ajaxLiandong("getProvincesByCountry",nowProvinceHtml,countryId,"province",'<option value="0">请选择省份 </option>',nowStayProvinceId);
			if(nowStayProvinceId != "0" || null != nowStayProvinceId){
				//与后台交互获取省份对应的城市
				ajaxLiandong("getCitysByProvince",nowCityHtml,nowStayProvinceId,"city",'<option value="0">请选择城市 </option>',nowStayCityId);
			}
		}
	});
	
	
	/*省-市联动查询 籍贯*/
	$("#nativePlaceProvince").unbind("change");
	$("#nativePlaceProvince").bind("change",function(){
		//先清空
		$("#nativePlaceCity").html("");
		var nativePlaceProvinceId = $("#nativePlaceProvince").val();
		var nativePlaceCity=$("#nativePlaceCity");
		var nowStayCityId = $("#nativePlaceCity").val();
		//与后台交互获取省份对应的城市
		ajaxLiandong("getCitysByProvince",nativePlaceCity,nativePlaceProvinceId,"city",'<option value="0">请选择城市 </option>',nowStayCityId);
	});
	
	/*省-市联动查询 户籍*/
	$("#domicoleProvince").unbind("change");
	$("#domicoleProvince").bind("change",function(){
		//先清空
		$("#domicoleCity").html("");
		var nativePlaceProvinceId = $("#domicoleProvince").val();
		var nativePlaceCity=$("#domicoleCity");
		var nowStayCityId = $("#domicoleCity").val();
		//与后台交互获取省份对应的城市
		ajaxLiandong("getCitysByProvince",nativePlaceCity,nativePlaceProvinceId,"city",'<option value="0">请选择城市 </option>',nowStayCityId);
	});
	
	/*省-市联动查询 现居住地*/
	$("#nowStayProvince").unbind("change");
	$("#nowStayProvince").bind("change",function(){
		//先清空
		$("#nowStayCity").html("");
		var nativePlaceProvinceId = $("#nowStayProvince").val();
		var nativePlaceCity=$("#nowStayCity");
		var nowStayCityId = $("#nowStayCity").val();
		//与后台交互获取省份对应的城市
		ajaxLiandong("getCitysByProvince",nativePlaceCity,nativePlaceProvinceId,"city",'<option value="0">请选择城市 </option>',nowStayCityId);
	});
	/*省-市联动查询 现居住地 管辖派出所 */
	$("#nowPoliceStationProvince").unbind("change");
	$("#nowPoliceStationProvince").bind("change",function(){
		//先清空
		$("#nowPoliceStationCity").html("");
		var nativePlaceProvinceId = $("#nowPoliceStationProvince").val();
		var nativePlaceCity=$("#nowPoliceStationCity");
		var nowStayCityId = $("#nowPoliceStationCity").val();
		//与后台交互获取省份对应的城市
		ajaxLiandong("getCitysByProvince",nativePlaceCity,nativePlaceProvinceId,"city",'<option value="0">请选择城市 </option>',nowStayCityId);
	});
	
	/**丈夫信息省市联动*/
	var husbandProvinceId = $("#husband-provinceId").val();
	var husbandProvinceHtml = $("#husband-provinceId");
	var husbandCityHtml     = $("#husband-cityId");
	var husbandDomicoleProvince = $("#husband-domicoleProvince").val();
	var husbandDomicoleProvinceHtml = $("#husband-domicoleProvince");
	var husbandDomicoleCity = $("#husband-domicoleCity").val();
	var husbandDomicoleCityHtml = $("#husband-domicoleCity");
	var husbandNowStayProvince = $("#husband-nowStayProvince").val();
	var husbandNowStayProvinceHtml = $("#husband-nowStayProvince");
	var husbandNowStayCityHtml = $("#husband-nowStayCity");
	var husbandNowStayDistrictHtml = $("#husband-nowStayDistrict");
	/*当国家已经默认为中国时-省联动查询*/
	if(null != countryId && countryId != "0"){
		//与后台交互获取该国家对应的省份
		ajaxLiandong("getProvincesByCountry",husbandProvinceHtml,countryId,"province",'<option value="0">请选择省份 </option>',$("#husbandProvinceId").val());
		ajaxLiandong("getProvincesByCountry",husbandDomicoleProvinceHtml,countryId,"province",'<option value="0">请选择省份 </option>',$("#husbandDomicoleProvinceId").val());
		ajaxLiandong("getProvincesByCountry",husbandNowStayProvinceHtml,countryId,"province",'<option value="0">请选择省份 </option>',$("#husbandNowStayProvinceId").val());
		if($("#husbandProvinceId").val() != "0" || null != $("#husbandProvinceId").val()){
			ajaxLiandong("getCitysByProvince",husbandCityHtml,$("#husbandProvinceId").val(),"city",'<option value="0">请选择城市 </option>',$("#husbandCityId").val());
		}
		if($("#husbandDomicoleProvinceId").val() != "0" || null != $("#husbandDomicoleProvinceId").val()){
			ajaxLiandong("getCitysByProvince",husbandDomicoleCityHtml,$("#husbandDomicoleProvinceId").val(),"city",'<option value="0">请选择城市 </option>',$("#husbandDomicoleCityId").val());
		}
		if($("#husbandNowStayProvinceId").val() != "0" || null != $("#husbandNowStayProvinceId").val()){
			ajaxLiandong("getCitysByProvince",husbandNowStayCityHtml,$("#husbandNowStayProvinceId").val(),"city",'<option value="0">请选择城市 </option>',$("#husbandNowStayCityId").val());
		}
		if($("#husbandNowStayCityId").val() != "0" || null != $("#husbandNowStayCityId").val()){
			//与后台交互获取该城市对应的地区
			ajaxLiandong("getDistrictsByCity",husbandNowStayDistrictHtml,$("#husbandNowStayCityId").val(),"district",'<option value="0">请选择县（区） </option>',$("#husbandNowStayDistrictId").val());
		}
		
	}
	//丈夫
	/*省-市联动查询 籍贯*/
	$("#husbandProvince").unbind("change");
	$("#husbandProvince").bind("change",function(){
		//先清空
		$("#husbandCity").html("");
		var nativePlaceProvinceId = $("#husbandProvince").val();
		var nativePlaceCity=$("#husbandCity");
		var nowStayCityId = $("#husbandCity").val();
		//与后台交互获取省份对应的城市
		ajaxLiandong("getCitysByProvince",nativePlaceCity,nativePlaceProvinceId,"city",'<option value="0">请选择城市 </option>',nowStayCityId);
	});
	
	/*省-市联动查询 户籍*/
	$("#husbandDomicoleProvince").unbind("change");
	$("#husbandDomicoleProvince").bind("change",function(){
		//先清空
		$("#husbandDomicoleCity").html("");
		var nativePlaceProvinceId = $("#husbandDomicoleProvince").val();
		var nativePlaceCity=$("#husbandDomicoleCity");
		var nowStayCityId = $("#husbandDomicoleCity").val();
		//与后台交互获取省份对应的城市
		ajaxLiandong("getCitysByProvince",nativePlaceCity,nativePlaceProvinceId,"city",'<option value="0">请选择城市 </option>',nowStayCityId);
	});
	
	/*省-市联动查询 现居住地*/
	$("#husbandNowStayProvince").unbind("change");
	$("#husbandNowStayProvince").bind("change",function(){
		//先清空
		$("#husbandNowStayCity").html("");
		var nativePlaceProvinceId = $("#husbandNowStayProvince").val();
		var nativePlaceCity=$("#husbandNowStayCity");
		var nowStayCityId = $("#husbandNowStayCity").val();
		//与后台交互获取省份对应的城市
		ajaxLiandong("getCitysByProvince",nativePlaceCity,nativePlaceProvinceId,"city",'<option value="0">请选择城市 </option>',nowStayCityId);
	});
	
	
	//籍贯---同步医院的省市
	$("#nativePlaceBtn").toggle(function(){
		$.ajax({
			url:"getNativePlace",
			data:{},
			type:"post",
			success:function(data){
				$("#nativePlaceProvince").attr("value",data.provinceId);
				$("#nativePlaceCity").html("");
				$("#nativePlaceCity").html("<option value="+data.cityId+">"+data.city+"</option>");
			}
		});
	},function(){
		$("#nativePlaceProvince").attr("value",0);
		$("#nativePlaceCity").html("<option value='0'>请选择城市</option>");
	});

	//户籍---同步籍贯
	$("#domicoleProvinceBtn").toggle(function(){
		var nativePlaceProvince=$("#nativePlaceProvince").val();
		var nativePlaceCity=$("#nativePlaceCity").val();
		if(nativePlaceProvince!=0&&nativePlaceCity!=0){
			$("#domicoleProvince").attr("value",nativePlaceProvince);
			$("#domicoleCity").html("<option value="+nativePlaceCity+">"+$("#nativePlaceCity").text()+"</option>");
		}else{
			alert("请先选择籍贯地址！");
		}
	},function(){
		$("#domicoleProvince").attr("value",0);
		$("#domicoleCity").html("<option value='0'>请选择城市</option>");
	});
	
	//孕妇现住址---同步医院的省市
	$("#nowStayProvinceBtn").toggle(function(){
		$.ajax({
			url:"getNativePlace",
			data:{},
			type:"post",
			success:function(data){
				$("#nowStayProvince").attr("value",data.provinceId);
				$("#nowStayCity").html("");
				$("#nowStayCity").html("<option value="+data.cityId+">"+data.city+"</option>");
			}
		});
	},function(){
		$("#nowStayProvince").attr("value",0);
		$("#nowStayCity").html("<option value='0'>请选择城市</option>");
	});
	
	//丈夫籍贯---同步医院的省市
	$("#husbandProvinceBtn").toggle(function(){
		$.ajax({
			url:"getNativePlace",
			data:{},
			type:"post",
			success:function(data){
				$("#husbandProvince").attr("value",data.provinceId);
				$("#husbandCity").html("");
				$("#husbandCity").html("<option value="+data.cityId+">"+data.city+"</option>");
			}
		});
	},function(){
		$("#husbandProvince").attr("value",0);
		$("#husbandCity").html("<option value='0'>请选择城市</option>");
	});
	

	//丈夫户籍---同步籍贯
	$("#husbandDomicoleBtn").toggle(function(){
		var husbandProvince=$("#husbandProvince").val();
		var husbandCity=$("#husbandCity").val();
		if(husbandProvince!=0&&husbandCity!=0){
			$("#husbandDomicoleProvince").attr("value",husbandProvince);
			$("#husbandDomicoleCity").html("<option value="+husbandCity+">"+$("#husbandCity").text()+"</option>");
		}else{
			alert("请先选择籍贯地址！");
		}
	},function(){
		$("#husbandProvince").attr("value",0);
		$("#husbandCity").html("<option value='0'>请选择城市</option>");
	});
	
	//丈夫现住址---同步孕妇现住址
	$("#husbandNowStayBtn").toggle(function(){
		var nowStayProvince=$("#nowStayProvince").val();
		var nowStayCity=$("#nowStayCity").val();
		if(nowStayProvince!=0&&nowStayCity!=0){
			$("#husbandNowStayProvince").attr("value",nowStayProvince);
			$("#husbandNowStayCity").html("<option value="+nowStayCity+">"+$("#nowStayCity").text()+"</option>");
		}else{
			alert("请先选择孕妇现住址！");
		}
	},function(){
		$("#husbandNowStayProvince").attr("value",0);
		$("#husbandNowStayCity").html("<option value='0'>请选择城市</option>");
	});
	
	//点击下一步，保存基本信息，并跳向下一步
	$("#submitButton").bind("click",function(){
		var flag=checkForm();
		if(flag){
			$("#pregnantArchiveBaseForm").submit();
		}
	});
});

 
	//二级联动查询 与后台交互function
	function ajaxLiandong(url,htmlName,param,arrValue,text,entityId){
		//与后台交互
		$.ajax({
			type: 'POST',
			dataType: 'text',
			data: 'id='+param,
			url: ""+url,
			success: function(rs){
				if(rs != ''){
					var content = text;
					var results = eval('(' + rs + ')');
					if(arrValue == "province"){//国家-省 二级联动
						for(var i = 0;i < results.length; i++){
							if(results[i].id == entityId){
								content+='<option value="'+results[i].id+'" selected="selected">'+results[i].proName+'</option>';
							}else{
								content+='<option value="'+results[i].id+'">'+results[i].proName+'</option>';
							}
						}
					}
					if(arrValue == "city"){// 省-市 二级联动
						for(var i = 0;i < results.length; i++){
							if(results[i].id == entityId){
								content+='<option value="'+results[i].id+'" selected="selected">'+results[i].cityName+'</option>';
							}else{
								content+='<option value="'+results[i].id+'">'+results[i].cityName+'</option>';
							}
						}
					}
					if(arrValue == "district"){// 市-地区 二级联动
						for(var i = 0;i < results.length; i++){
							if(results[i].id == entityId){
								content+='<option value="'+results[i].id+'" selected="selected">'+results[i].name+'</option>';
							}else{
								content+='<option value="'+results[i].id+'">'+results[i].name+'</option>';
							}
						}
					}
					htmlName.html(content);
				}
			},
			error: function(rs){
				alert(rs);
			}
		});
	}
	
	//根据生日计算年龄
	function calculateAge(obj){
		var dateText="";
		if(obj==1){
			dateText=$("#birthday").val();
		}else{
			dateText=$("#husbandBirthday").val();
		}
		if(dateText!=null&&dateText!=""){
			//根据生日计算年龄
			$.ajax({
				url:"calculateAgeByBirthday",
				data:{birthday:dateText},
				type:"post",
				success:function(data){
				if(obj==1){
					$("#age").val(data);
				}else{
					$("#husbandAge").val(data);
				}
				}
			});
		}else{
			alert("请选择出生日期！");
			if(obj==1){
				$("#age").val("");
			}else{
				$("#husbandAge").val("");
			}
		}
	}
	
	//根据末次月经计算预产期
	function getPregnantDateBylmp(){
		var lmp=$("#lmp").val();
		if(lmp!=null&&lmp!=""){
			$.ajax({
				url:"getPregnantDateBylmp",
				data:{lmp:lmp},
				type:"post",
				success:function(data){
					$("#pregnantDate").val(data);
				}
			});
		}else{
			alert("请选择末次月经！");
			$("#pregnantDate").val("");
		}
	}
	
	//表单验证
	function checkForm(){
		var name=$("#name").val();
		var mobile=$("#mobile").val();
		var idNo=$("#idNo").val();
		var birthday=$("#birthday").val();
		var lmp=$("#lmp").val();
		var flag=true;
		if(name==null||name==""){
			$("#name").tips({msg:"请输入您的名字"});
			flag=false;
		}else if(name.length>10){
			$("#name").tips({msg:"名字长度不能超过10位！"});
			flag=false;
		}
		if(mobile==null||mobile==""){
			$("#mobile").tips({msg:"手机号码不能为空"});
			fla=false;
		}else if(!isPhoneNum(mobile)){
			$("#mobile").tips({msg:"请输入合法的手机号码！"});
			fla=false;
		}
		if(idNo==null||idNo==""){
			$("#idNo").tips({msg:"身份证号码不能为空！"});
			flag=false;
		}else if(!isCardNum(idNo)){
			$("#idNo").tips({msg:"请输入合法的身份证号码！"});
			flag=false;
		}
		if(birthday==null||birthday==""){
			$("#birthday").tips({msg:"请选择你的出生日期！"});
			flag=false;
		}
		if(lmp==null||lmp==""){
			$("#lmp").tips({msg:"请选择你的末次月经日期！"});
			flag=false;
		}
		return flag;
	}
	
	// 验证手机号
	function isPhoneNum(phone) { 
		 var pattern = /^1[34578]\d{9}$/; 
		 return pattern.test(phone); 
	}
	 
	//验证身份证 
	function isCardNum(card) { 
		 var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
		 return pattern.test(card); 
	} 
	
	
