<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <!-- 页头引入 -->
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<style type="text/css">
	#out{
		margin-left: 50px;
	}
	.mydiv{
		max-width:1350px;
		height:55px;
		background-color: #ECECEC;
		font-size: 20px;
		font-family:"宋体";
		line-height: 55px;
		padding-left: 15px;
		margin-bottom: 30px;
		margin-top: 20px;
		
	}
	.mytable tr td{
		font-size: 16px;
		font-family: "宋体";
		overflow: hidden;
		width: 300px;
		padding-bottom: 10px;
	}
	h4{
		font-weight: bold;
	}
	hr{
		height:1px;
		border:none;
		border-top:1px dashed black;
		
		margin-right:55px;
	}
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/mystyle.css?${v }"/>

<div class="col-xs-12 col-sm-9" style="float:right;">
<form action="${pageContext.request.contextPath}/pregnantBook/pregnantBookList" method="post" id="form" >
   

   <div class="panel panel-default"><!-- style="margin-right: 200px"  -->
       <div class="panel-body">
	   		<div id="out">
	   		
	   		<div class="mydiv">
	   			孕妇基本信息
	   		</div>
	   		<h4>一、基本信息</h4>
	   		<table class="mytable">
	   			<tr>
	   				<td>姓&nbsp;&nbsp;名：${usersBookBasicInfo.name}</td> <td>出生日期：<fmt:formatDate value="${usersBookBasicInfo.birthday}" pattern="yyyy-MM-dd"></fmt:formatDate></td>  <td>职&nbsp;&nbsp;业：${usersBookBasicInfo.job }</td>  <td>民&nbsp;&nbsp;族：${usersBookBasicInfo.userNations.name}</td> 
	   			</tr>
	   			<tr>
	   				<td>文化程度：${usersBookBasicInfo.userEducation.name}</td>
	   				<td>证件类型：
	   					<c:if test="${usersBookBasicInfo.ID_type==0}">身份证</c:if>
	   					<c:if test="${usersBookBasicInfo.ID_type==1}">护照</c:if>
	   					<c:if test="${usersBookBasicInfo.ID_type==2}">军官证</c:if>
	   					<c:if test="${usersBookBasicInfo.ID_type==3}">港澳居民通行证</c:if></td> <!--0：身份证；1：护照；2：军官照；3：港澳居民通行证  -->
	   				
	   				<td>证件号码：${usersBookBasicInfo.ID_no}</td>
	   			</tr>
	   			<tr>
	   				<td>手机号码：${usersBookBasicInfo.mobile}</td>
	   			</tr>
	   		</table>
	   		<hr/>
	   		<h4>二、户籍信息</h4>
	   		<table class="mytable">
	   			<tr>
	   				<td>户籍省市：${usersBookBasicInfo.privince.proName}${usersBookBasicInfo.city.cityName}</td>
	   				<td>户籍类型：
	   					<c:if test="${usersBookBasicInfo.resident_type==0}">深圳户籍</c:if>
	   					<c:if test="${usersBookBasicInfo.resident_type==1}">暂住</c:if>
	   					<c:if test="${usersBookBasicInfo.resident_type==2}">流动户籍</c:if>
	   				</td> <!--0:深圳户籍；1：暂住；2：流动户籍  -->
	   				
	   				<td>户&nbsp;&nbsp;籍：
	   					<c:if test="${usersBookBasicInfo.resident==0}">城镇户口</c:if>
	   					<c:if test="${usersBookBasicInfo.resident==1}">农村户口</c:if>
	   				</td> <!--0：广东户口；1：农村户口  -->
	   				<td><%-- 来深年数：${usersBookBasicInfo.stay_year} --%></td>
	   			</tr>
	   			<%-- <tr>
	   				<td>来深月数：${usersBookBasicInfo.stay_month}</td>
	   			</tr> --%>
	   			
	   		</table>
	   		<hr/>
	   		<h4>三、联系信息</h4>
	   		<table class="mytable">
	   			<tr>
	   				<td>现住地&nbsp;城市：${usersBookBasicInfo.now_stay_city.cityName}</td>  <td>现住地&nbsp;地区：${usersBookBasicInfo.nowStayDistrict.name}</td>  <td>现住地&nbsp;地址：${usersBookBasicInfo.now_stay_address}</td>  <td></td>
	   			</tr>
	   			<tr>
	   				<td>工作&nbsp;&nbsp;单位：${usersBookBasicInfo.work_unit}</td>  <td>产后修养城市：${usersBookBasicInfo.postpartum_cultivation_city_id.cityName}</td>  <td>产后修养地区：${usersBookBasicInfo.postpartumCultivationDistrictId.name}</td>  
	   			</tr>
	   			<tr>
	   				<td>产后修养地址：${usersBookBasicInfo.postpartum_cultivation_address}</td>  <td>联系&nbsp;&nbsp;电话：${usersBookBasicInfo.contact_phone}</td>  
	   			</tr>
	   		</table>
	   		<hr/>
	   		<h4>四、健康信息</h4>
	   		<table class="mytable">
	   			<tr>
	   				<td>初潮年龄：${usersBookBasicInfo.menarche}</td>  <td>月经周期：${usersBookBasicInfo.week}</td>  <td>月经量：<c:if test="${usersBookBasicInfo.menstruation==0}">少</c:if>
	   				<c:if test="${usersBookBasicInfo.menstruation==1}">中</c:if>
	   				<c:if test="${usersBookBasicInfo.menstruation==2}">多</c:if>
	   				</td>  <td></td>
	   			</tr>
	   			<tr>
	   				<td>既往&nbsp;史：${pastHistoryStr}</td>
	   			</tr>
	   			<tr>
	   				<td>遗传病史：${geneticHistoryStr}</td>
	   			</tr>
	   		</table>
	   		
	   		<div class="mydiv">
	   			丈夫基本信息
	   		</div>
	   		<h4>一、基本信息</h4>
	   		<table class="mytable">
	   			<tr>
	   				<td>姓&nbsp;&nbsp;名：${usersBookHusbandInfo.name}</td>  <td>出生日期：<fmt:formatDate value="${usersBookHusbandInfo.birthday}" pattern="yyyy-MM-dd"></fmt:formatDate></td>  
	   				<td>血&nbsp;&nbsp;型：<!--血型：0：A；1：B；2：AB;3:O  -->
	   				<c:if test="${usersBookHusbandInfo.blood_type==0}">A型</c:if>
	   				<c:if test="${usersBookHusbandInfo.blood_type==1}">B型</c:if>
	   				<c:if test="${usersBookHusbandInfo.blood_type==2}">AB型</c:if>
	   				<c:if test="${usersBookHusbandInfo.blood_type==3}">O型</c:if>
	   				</td>  
	   				<td>职&nbsp;&nbsp;业：${usersBookHusbandInfo.job}</td> 
	   			</tr>
	   			<tr>
	   				<td>国&nbsp;&nbsp;籍：${usersBookHusbandInfo.country.country}</td>  <td>民&nbsp;&nbsp;族：${usersBookHusbandInfo.userNations.name}</td>  <td>身&nbsp;高(cm)：${usersBookHusbandInfo.height}</td>  <td>体&nbsp;重(kg)：${usersBookHusbandInfo.weight}</td>  
	   			</tr>
	   			<tr>
	   				<td>证件类型：
	   					<c:if test="${usersBookHusbandInfo.ID_type==0}">身份证</c:if>
	   					<c:if test="${usersBookHusbandInfo.ID_type==1}">护照</c:if>
	   					<c:if test="${usersBookHusbandInfo.ID_type==2}">军官证</c:if>
	   					<c:if test="${usersBookHusbandInfo.ID_type==3}">港澳居民通行证</c:if>
	   				</td>  <td>证件号码：${usersBookHusbandInfo.ID_no}</td>  <td>文化程度：${usersBookHusbandInfo.userEducation.name}</td> 
	   			</tr>
	   		</table>
	   		<hr/>
	   		<h4>二、户籍信息</h4>
	   		<table class="mytable">
	   			<tr>
	   				<td>户籍省市：${usersBookHusbandInfo.privince.proName}${usersBookHusbandInfo.city.cityName}</td>  
	   				
	   				<td>户籍类型：
	   					<c:if test="${usersBookHusbandInfo.resident_type==0}">深圳户籍</c:if>
	   					<c:if test="${usersBookHusbandInfo.resident_type==1}">暂住</c:if>
	   					<c:if test="${usersBookHusbandInfo.resident_type==2}">流动户籍</c:if>
	   				</td>  <td></td><td></td>
	   			</tr>
	   		</table>
	   		<hr/>
	   		<h4>三、联系信息</h4>
	   		<table class="mytable">
	   			<tr>
	   				<td>联系电话：${usersBookHusbandInfo.mobile}</td>  <td>工作单位：${usersBookHusbandInfo.work_unit}</td>  <td></td><td></td>
	   			</tr>
	   			<tr>
	   				<td>住宅电话：${usersBookHusbandInfo.phone}</td>  
	   			</tr>
	   		</table>
	   		<hr/>
	   		<h4>四、健康信息</h4>
	   		<table class="mytable">
	   			<tr>
	   				<td>疾病史：${usersBookHusbandInfo.disease_history}</td>  
	   			</tr>
	   			<tr>
	   				<td>烟(支/天)：${usersBookHusbandInfo.smoke}</td>  <td>酒(两/天)：${usersBookHusbandInfo.drinking}</td>  <td></td><td></td>
	   			</tr>
	   		</table>
	   		
	   		<div class="mydiv">
	   			初次产检基本信息
	   		</div>
	   		<h4>一、基本信息</h4>
	   		<table class="mytable">
	   			<tr>
	   				<td>末次月经：<fmt:formatDate value="${usersBookFirstCheckInfo.last_menstruation_time}" pattern="yyyy-MM-dd"></fmt:formatDate></td>  <td>初检孕周：${usersBookFirstCheckInfo.first_check_week}</td>  <td>怀孕次数：${usersBookFirstCheckInfo.pregnant_times }</td>  <td>产&nbsp;&nbsp;数：${usersBookFirstCheckInfo.check_times}</td> 
	   			</tr>
	   			<tr>
	   				<td>结婚年龄：${usersBookFirstCheckInfo.marriage_year}</td>  <td>现有男孩：${usersBookFirstCheckInfo.now_boys}</td>  <td>现有女孩：${usersBookFirstCheckInfo.now_girls}</td>  
	   			</tr>
	   		</table>
	   		<hr/>
	   		<h4>二、健康信息</h4>
	   		<table class="mytable">
	   			<tr>
	   				<td>孕产&nbsp;&nbsp;&nbsp;史：${maternalHistoryStr}</td> 
	   			</tr>
	   			<tr>
	   				<td>早孕反应时间：${usersBookFirstCheckInfo.early_pregnancy_reaction_time}</td>  <td>阴道出血时间：${usersBookFirstCheckInfo.vaginal_bleeding_time}</td>  <td>胎动开始周数：${usersBookFirstCheckInfo.fetal_move_begin_week}</td>  <td></td>
	   			</tr>
	   			<tr>
	   				<td>孕早期用药：${usersBookFirstCheckInfo.early_pregnancy_medication}</td>  
	   			</tr>
	   			<tr>
	   				<td>物理性有害物质：${usersBookFirstCheckInfo.physics_harmful_substances}</td>  
	   			</tr>
	   			<tr>
	   				<td>化学性有害物质：${usersBookFirstCheckInfo.chemical_substances}</td>  
	   			</tr>
	   			<tr>
	   				<td>烟(支/天)：${usersBookFirstCheckInfo.smoke}</td>  <td>酒(两/天)：${usersBookFirstCheckInfo.drinking}</td>  <td></td><td></td>
	   			</tr>
	   		</table>
	   		<hr/>
	   		<h4>三、孕检基本信息</h4>
	   		<table class="mytable">
	   			<tr>
	   				<td>体力负担：<!--0:轻；1：一般；2重  -->
	   				<c:if test="${usersBookFirstCheckInfo.physical_burden==0}">轻</c:if>
	   				<c:if test="${usersBookFirstCheckInfo.physical_burden==1}">一般</c:if>
	   				<c:if test="${usersBookFirstCheckInfo.physical_burden==2}">重</c:if>
	   				
	   				</td>  <td>孕前体重(kg)：${usersBookFirstCheckInfo.pregregnancy_weight}</td>  <td>当前体重(kg)：${usersBookFirstCheckInfo.current_weight}</td>  <td></td>
	   			</tr>
	   			<tr>
	   				<td>身高(cm)：${usersBookFirstCheckInfo.height}</td>  <td>血&nbsp;压(mmHg)：${usersBookFirstCheckInfo.blood_press}</td>  <td></td><td></td>
	   			</tr>
	   		</table>
		       
				
			</div>
      </div>
   </div>
   </form>

</div>
 
  <!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>