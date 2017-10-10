$(function(){
	//必查项目
	var willCheckProject=$("#willCheckProject").val();
	var willCheckProjectArray=["血常规","尿常规","血型","肝功能","肾功能","乙肝","梅毒","HIV","阴道分泌物"];
	if(willCheckProject!=null && willCheckProject!=""){
		var willCheckProjects=willCheckProject.split(",");
		if(willCheckProjects!=null && willCheckProjects!=""){
			var html="<ul>";
			for(var i=0;i<willCheckProjects.length-1;i++){
				html+="<li>"+willCheckProjectArray[willCheckProjects[i]]+"</li>";
			}
			html+="</ul>";
			$("#willCheckProjectTable tr td").html(html);
		}
	}
	
	//备查项目
	var referenceProject=$("#referenceProject").val();
	var referenceProjectArray=["丙肝","OGTT","空腹血糖","甲状腺功能","TORCH","血脂","血清铁蛋白","HCG","孕酮","结核菌素试验","抗D滴度检查","宫颈细胞学","宫颈分泌物（淋球菌和沙眼衣原体）","BV"];
	if(referenceProject!=null && referenceProject!=""){
		var referenceProjects=referenceProject.split(",");
		if(referenceProjects!=null && referenceProjects!=""){
			var html="<ul>";
			for(var i=0;i<referenceProjects.length-1;i++){
				html+="<li>"+referenceProjectArray[referenceProjects[i]]+"</li>";
			}
			html+="</ul>";
			$("#referenceProjectTable tr td").html(html);
		}
	}
});
