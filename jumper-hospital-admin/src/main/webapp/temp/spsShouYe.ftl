<?xml version="1.0" encoding="UTF-8"?>
<html>
	<head>	
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <style type="text/css" >     
			body {     
			    font-family: SimSun; 
			}
			.pageNext {
				page-break-after: always;
			}
            .dataTable { 
				border:1px solid;  /* 表格边框 */ 
				border-collapse:collapse; /* 边框重叠 */  
				table-layout:fixed;
				word-break:break-strict; 
				font-size:13px;
				width:100%
			} 
			.AutoNewline {
				word-break: break-all;/*必须*/
				width:100px;
				text-overflow:ellipsis;
				word-wrap : break-word ;
			}
			.dataTable td,.dataTable tr { 
			    border:1px solid;
				border-collapse:collapse;
				font-size:13px;
			}
			 .dataTable1 { 
				border:1px solid;  /* 表格边框 */ 
				border-collapse:collapse; /* 边框重叠 */  
				font-size:13px;
				width:100%
			 } 
			 .fly {
             	font-family: STZhongsong;
             } 
		</style> 
	</head>
	
	<body style="text-align:center;font-size:11px;">

	<div style="text-align:center;font-size:15px;font-weight: bold;">院外孕检报告单</div>
	<hr />
	<table width="100%">
		<tr>
			<td align="left" width="25%">姓名:${e.name?if_exists}</td>
			<td align="center" width="25%">年龄:${e.age?if_exists}岁</td>
			<td align="center" width="25%">孕周:${e.yunzhou?if_exists}</td>
			<td align="right" width="25%">手机号码:${e.mobile?if_exists}</td>
		</tr>
	</table>
	<table  width="100%">
	 
 
		<tr>
			<td align="left" width="33%">${e.heartStartTime?if_exists}</td>
			<td align="center" width="33%">${e.heartEndTime?if_exists}</td>
			<td align="right" width="34%">${e.timeStrBySeconds?if_exists}</td>
		</tr>
	 
	</table>
		<table border="1"  width="100%" align="center" class="dataTable1" >
		<tr>
			<table width="100%" align="center">
				<tr>
					<th>检测项目</th>
					<th>结果</th>
					<th></th>
					<th>参考区间</th>
					
					<th>检测项目</th>
					<th>结果</th>
					<th></th>
					<th>参考区间</th>
				</tr>
			
				 
					<#list e.list as l>
					<#if (l_index+1)%2=0>
						<tr>
							<td>${e.list[l_index-1].project?if_exists}</td>
							<td>${e.list[l_index-1].result?if_exists}</td>
							<td>${e.list[l_index-1].resultState?if_exists}</td>
							<td>${e.list[l_index-1].area?if_exists}</td>
							
							<td>${e.list[l_index].project?if_exists}</td>
							<td>${e.list[l_index].result?if_exists}</td>
							<td>${e.list[l_index].resultState?if_exists}</td>
							<td>${e.list[l_index].area?if_exists}</td>
						</tr>
						<#else>
					</#if>	
					</#list>
			</table>
		</tr><!--String project, String result, String area, String resultState-->
		</table><br/>
		<div style="text-align:left;">备注:${e.reason?if_exists}</div><br/>
		<div style="text-align:left;">说明:1 本报告仅对监测样本负责，仅供临床参考，不做诊断证明之用。
		2 请根据医生建议按时复诊或孕妇自感异常随时复诊</div><br/>
		<table width="100%">
			<tr>
				<td width="60%" align="right">监护医生:</td>
				<td width="20%" align="left">${e.familyDoctorinfo.doctorName?if_exists}</td>
				<td width="20%" align="left">监测日期:${e.checkTime?string("yyyy-MM-dd")?if_exists}</td>
			</tr>
			<tr>
				<td width="60%" align="right">审核医生:</td>
				<td width="20%" align="left">${e.username?if_exists}</td>
				<td  width="20%" align="left">审核日期:${e.sysTime?if_exists}</td>
			</tr>
		</table>
	</body>
</html>