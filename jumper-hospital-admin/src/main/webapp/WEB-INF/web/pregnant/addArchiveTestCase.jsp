<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/menu.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/daterangepicker-bs3.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/laydate-v1.1/laydate/laydate.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/pregnantArchive/addArchiveTestCase.js"></script>
 <style type="text/css">
	html{overflow-x: scroll;}
	body {min-width: 1660px;overflow-y: hidden;}
	.message-dialog{width: 800px;height: 500px;}
 	*{padding: 0; margin: 0; }
	.panel{margin-bottom:0px;}
	.modal {
    position: fixed;
    top: 20%;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 1050;
    display: none;
    overflow: hidden;
    -webkit-overflow-scrolling: touch;
    outline: 0;
}
.modal-dialog {
    
    margin: 30px auto;
}
.modal-header {
    min-height: 16.42857143px;
    padding: 15px;
    border-bottom: 1px solid #fff;
}
input,select{ height:25px; padding:0 5px;}
input[type="radio"],input[type="checkbox"]{ margin:0;}
label{margin-right:10px;}
.gw_btn { width:280px;}
.cha_lab label{margin-right:15px;}
	</style>
   
    <!--style-->
    
</head>

<body style="background: #F0F0F0;">
                <!-- 档案管理 高危筛查 -->	                
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
	               	       
		                       <ul class="page-sidebar-menu">
		                          <li class="active"><a
										href="${pageContext.request.contextPath}/pregnant/archiveIndex">
											<i class="icon-plus-sign-alt"></i>&nbsp; <span class="title">档案管理</span>
									</a>
									</li>
		                       </ul>
	                    </div>

<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
		<div class="panel" >
        	<div class="gw_rote"> <span class="float-left" style="font-size:16px;">档案管理 >新建档案 > <a style=" color:#ff7ea5">初检情况</a></span></div>
        	<div class="panel-body">	
                
            <div class="panel panel-default float-left" style="margin:15px 0px;width: 100%;">
                <div class=" float-left" style="width:100%;">
                        <ul id="myTab" class="nav nav-tabs">
   							<li><a href="${pageContext.request.contextPath}/pregnant/archiveBaseInfo">基本信息</a></li>
   							<li><a href="${pageContext.request.contextPath}/pregnant/archiveHistoryTaking" >病史询问</a></li>
                            <li class="active"><a href="#" >初检情况</a></li>
  						</ul>
                        
                </div>
			<form action="${pageContext.request.contextPath}/pregnant/saveArchiveTestCaseInfo" method="post" id="archiveTestCaseForm">
			<input type="hidden" name="archiveId" value="${archiveBaseId }"/>
			<input type="hidden" name="hospitalMappingId" value="${archiveId }"/>
             <div id="cont_roll" class="panel panel-default float-left" style=" margin:15px;width: 98%;">  
                	<div class="weight-title">初次检查</div>
                    
                    <div class="panel-body">
                        <div id="live_inf" style="margin-top:25px;">
                                <span>神志</span>
                                <div class="live_inftable">
                                        <table class="gw_xx">
                                             <tbody>
                                                 <tr>
                                                 	<td>身高：<input style="width:80px;" value="0" type="text" name="height"  > cm</td>
                                                    <td>体重：<input style="width:80px;" value="0" type="text" name="weight"  > kg</td>
                                                    <td>BMI：<input type="text" name="bmi" id="bmi" style="border:0px;"/></td>
                                                    <td>血压：<input style="width:80px;" value="0" type="text"   name="dbp" placeholder="输入舒张值"> - <input style="width:80px;" value="0" name="sbp" type="text"   placeholder="输入收缩值">mmHg</td>
                                                    <td>浮肿：
		                                             <label><input style="width:10px;" type="radio" name="edemaState" value="0" checked>无</label>
		                                            <label><input style="width:10px;" type="radio" name="edemaState" value="1"> 轻</label>
		                                            <label><input style="width:10px;" type="radio" name="edemaState" value="2"> 中</label>
		                                            <label><input style="width:10px;" type="radio" name="edemaState" value="3"> 重</label>
                                                    </td>
                                                 </tr>
                                             </tbody>
                                        </table>
                                 </div>
                            </div>
                    </div>
                    <div class="panel-body">
                        <div id="live_inf">
                                <span>体格检查</span>
                                <div class="live_inftable">
                                        <table class="gw_xx">
                                             <tbody>
                                                 <tr>
                                                     <td>心肺：
                                            <label><input style="width:10px;" type="radio" name="heartLungState" value="0" checked>正常</label>
                                            <label><input style="width:10px;" type="radio" name="heartLungState" value="1"> 异常</label>
                                            <input type="text" value="" name="heartLungDescription" placeholder="请输入异常内容">
                                                                
                                                                </td>                   
                                                     <td>肝脾：
                                             <label><input style="width:10px;" type="radio" name="liverSpleenState" value="0" checked>正常</label>
                                            <label><input style="width:10px;" type="radio" name="liverSpleenState" value="1"> 异常</label>
                                            <input type="text" value="" name="liverSpleenDescription" placeholder="请输入异常内容">
                                                                
                                                     </td>
                                                     <td>甲状腺：
                                             <label><input style="width:10px;" type="radio" name="thyroidState" value="0" checked>正常</label>
                                            <label><input style="width:10px;" type="radio" name="thyroidState" value="1"> 异常</label>
                                            <input type="text" value="" name="thyroidDescription" placeholder="请输入异常内容">
                                                                
                                                     </td>
                                                     
                                                                        
                                                 </tr>
                                                 <tr>
                                                     <td>四肢：
                                             <label><input style="width:10px;" type="radio" name="fourLimbsState" value="0" checked>正常</label>
                                            <label><input style="width:10px;" type="radio" name="fourLimbsState" value="1"> 异常</label>
                                            <input type="text" value="" name="fourLimbsDescription" placeholder="请输入异常内容">
                                                                
                                                     </td> 
                                                     <td>皮肤：
                                             <label><input style="width:10px;" type="radio" name="skinState" value="0" checked>正常</label>
                                            <label><input style="width:10px;" type="radio" name="skinState" value="1"> 异常</label>
                                            <input type="text" value="" name="skinDescription" placeholder="请输入异常内容">
                                                                
                                                     </td>
                                                     <td>乳房：
                                            <label><input style="width:10px;" type="radio" name="breastState" value="0" checked>正常</label>
                                            <label><input style="width:10px;" type="radio" name="breastState" value="1"> 乳头凹陷</label>
                                            <label><input style="width:10px;" type="radio" name="breastState" value="2">乳头扁平</label>
                                            <label><input style="width:10px;" type="radio" name="breastState" value="3"> 乳房包块</label>
                                            <input type="text" value="" name="breastDescription" placeholder="请输入其他情况">
                                                                
                                                     </td>
                                                </tr>
                                                <tr>
                                                     <td>步态：
                                             <label><input style="width:10px;" type="radio" name="gaitState" value="0" checked>正常</label>
                                            <label><input style="width:10px;" type="radio" name="gaitState" value="1"> 异常</label>
                                            <input type="text" value="" name="gaitDescription" placeholder="请输入异常内容">
                                                                
                                                     </td> 
                                                </tr>
                                                                  
                                                                       
                                             </tbody>
                                        </table>
                                 </div>
                            </div>
                    </div>
                    
                    
                    <div class="panel-body">
                        <div id="live_inf">
                                <span>妇科检查</span>
                                <div class="live_inftable">
                                        <table class="gw_xx">
                                             <tbody>
                                                 <tr>
                                                 	<td>外阴：<input value="" type="text" name="vulva"></td>
                                                    <td>阴道：<input value="" type="text" name="vagina"></td>
                                                    <td>宫颈：<input value="" type="text" name="cervix"></td>
                                                    <td>内诊情况：<input value="" type="text" name="clinicalSituation"></td>
                                                    <td>宫体：<input value="" type="text" name="corpus"></td>
                                                 </tr>
                                                 <tr>
                                                 	
                                                    <td>宫高：<input style="width:100px;" value="0" type="text"   name="fundalHeight"> cm</td>
                                                    <td>附件：<input value="" type="text" name="adjunct"></td>
                                                    <td>腹围：<input style="width:100px;" value="0" type="text"   name="abdominalGirth"> cm</td>
                                                    <td>胎心：<input value="" type="text" name="fetalHeart"></td>
                                                    <td>先露：<input value="" type="text" name="presentation"></td>
                                                 </tr> 
                                                  <tr>
                                                 	
                                                    <td>衔接：<input value="" type="text" name="engagement"></td>
                                                    <td>水肿：<input value="" type="text" name="edema"></td>
                                                    <td>其他：<input value="" type="text" placeholder="" name="other"></td>
                                                 </tr>
                                                               
                                             </tbody>
                                        </table>
                                 </div>
                            </div>
                    </div>
                    
                    
                    
                    <div class="weight-title">骨盆外测量</div>
                    <div class="panel-body">
                        <table class="gw_xx">
                                    <tbody>
                                    <tr>
                                        <td >髂前上棘间径：<input style="width:100px;" value="0" type="text"   name="diameterSpineSkeletons"> cm</td>
                                        <td >髂嵴间径：<input style="width:100px;" value="0" type="text"   name="skeletonsRidgeDiameter"> cm</td>
                                        <td >骶耻外径：<input style="width:100px;" value="0" type="text"   name="externalConjugate"> cm</td>
                                        <td >坐骨结节间径：<input style="width:100px;" value="0" type="text"   name="ischialIntertuberalDiameter"> cm</td>
                                    </tr>
                                    <tr>
                                        
                                        <td >耻骨弓角度：<input style="width:100px;" value="0" type="text"   name="anglePubicArch"> 度</td>
                                    </tr>                                    
                            </tbody>
                         </table>
                     </div>
                    
                    
                     <div class="weight-title">实验室检查</div>
                     <div class="panel-body mod_btn">

                         <div id="live_inf" style="margin-top:25px;">
                        	<span>必查项目</span>
                        	<div class="live_inftable" id="willCheckProjectTable">
                        			<table class="gw_xx cha_lab">
                                         <tbody>
                                             <tr>
                                                 <td>
                                                 <label><input style="width:10px;" type="checkbox" value="1"> 血常规</label>
                                                 <label><input style="width:10px;" type="checkbox" value="2"> 尿常规</label>
                                                 <label><input style="width:10px;" type="checkbox" value="3"> 血型</label>
                                                 <label><input style="width:10px;" type="checkbox" value="4"> 肝功能</label>
                                                 <label><input style="width:10px;" type="checkbox" value="5"> 肾功能</label>
                                                 <label><input style="width:10px;" type="checkbox" value="6"> 乙肝</label>
                                                 <label><input style="width:10px;" type="checkbox" value="7"> 梅毒</label>
                                                 <label><input style="width:10px;" type="checkbox" value="8"> HIV</label>
                                                 <label><input style="width:10px;" type="checkbox" value="9"> 阴道分泌物</label>
                                                 </td>
                                            </tr>
                                         </tbody>
                                    </table>
                             </div>
                        </div>
                        <input type="hidden" name="willCheckProject" id="willCheckProject"/>
                        <div id="live_inf">
                        	<span>备查项目</span>
                        	<div class="live_inftable" id="referenceProjectTable">
                        			<table class="gw_xx cha_lab">
                                         <tbody>
                                             <tr>
                                                 <td>
                                                 <label><input style="width:10px;" type="checkbox" value="1"> 丙肝</label>
                                                 <label><input style="width:10px;" type="checkbox" value="2"> OGTT</label>
                                                 <label><input style="width:10px;" type="checkbox" value="3"> 空腹血糖</label>
                                                 <label><input style="width:10px;" type="checkbox" value="4"> 甲状腺功能</label>
                                                 <label><input style="width:10px;" type="checkbox" value="5"> TORCH</label>
                                                 <label><input style="width:10px;" type="checkbox" value="6"> 血脂</label>
                                                 <label><input style="width:10px;" type="checkbox" value="7"> 血清铁蛋白</label>
                                                 <label><input style="width:10px;" type="checkbox" value="8"> HCG</label>
                                                 <label><input style="width:10px;" type="checkbox" value="9"> 孕酮</label>
                                                 <label><input style="width:10px;" type="checkbox" value="10"> 结核菌素试验</label>
                                                 <label><input style="width:10px;" type="checkbox" value="11"> 抗D滴度检查（Rh阴性）</label>
                                                 <label><input style="width:10px;" type="checkbox" value="12"> 宫颈细胞学</label>
                                                 <label><input style="width:10px;" type="checkbox" value="13"> 宫颈分泌物（淋球菌和沙眼衣原体）</label>
                                                 <label><input style="width:10px;" type="checkbox" value="14"> BV</label>
                                                 </td>
                                            </tr>
                                         </tbody>
                                    </table>
                             </div>
                             <input type="hidden" name="referenceProject" id="referenceProject"/>
                        </div>
                        
                   <div class="weight-title">孕前检查结果</div>
                   <div class="panel-body mod_btn">
                        <div style="margin-bottom:25px;">
                         <table class="gw_xx">
                                 <tbody>
                                 	<tr>
                                    	<td colspan="2">血常规：
                                        Hb <input style="width:50px;" value="0" type="text"   name="hb"> g/l
                                        <span class="kong">MCV <input style="width:50px;" value="0" type="text"   name="mcv"></span>
                                        <span class="kong">MCH <input style="width:50px;" value="0" type="text"   name="mch"></span>
                                        <span class="kong">PLT <input style="width:50px;" value="0" type="text"   name="plt"> ×10</span>
                                        <span class="kong">其他 <input value="" type="text" name="other"></span>
                                        </td>
                                        <td>尿常规：
                                       	 白细胞 <input style="width:50px;" value="" type="text"   name="white_blood"> 个/u<span class="kong">
                                       	 红细胞 <input style="width:50px;" value="" type="text"   name="red_blood"> 个/ul</span>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                    	<td>血蛋白：
                                        <label><input style="width:10px;" value="" name="protein" type="radio" value="0" checked> 阴性</label>
                                        <label><input style="width:10px;" value="" name="protein" type="radio" value="1"> 阳性</label>
                                        </td>
                                        <td>尿比重：<input style="width:50px;" value="" type="text" name="sg"></td>
                                        <td>孕妇血型：
                                        <label><input style="width:10px;" value="" name="pregnantWomenBloodType" type="radio" value="0" checked> A</label>
                                        <label><input style="width:10px;" value="" name="pregnantWomenBloodType" type="radio" value="1"> B</label>
                                        <label><input style="width:10px;" value="" name="pregnantWomenBloodType" type="radio" value="2"> O</label>
                                        <label><input style="width:10px;" value="" name="pregnantWomenBloodType" type="radio" value="3"> AB</label>
                                        <span class="kong">RH：</span>
                                        </td>
                                        <td>丈夫血型：
                                        <label><input style="width:10px;" value="" name="husbardBloodType" type="radio" value="0" checked> A</label>
                                        <label><input style="width:10px;" value="" name="husbardBloodType" type="radio" value="1"> B</label>
                                        <label><input style="width:10px;" value="" name="husbardBloodType" type="radio" value="2"> O</label>
                                        <label><input style="width:10px;" value="" name="husbardBloodType" type="radio" value="3"> AB</label>
                                        <span class="kong">RH：</span>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                    	<td>肝肾功能：<input type="text" value="" name="hepatorenalFunction"></td>
                                        <td>围产四项：<input type="text" value="" name="perinatalFourItems"></td>
                                        <td>乙肝三对：<input type="text" value="" name="hepatitisThreePair"></td>
                                        <td>丙肝、病毒、HIV：<input type="text" value="" name="hiv"></td>
                                    </tr>
                                    
                                 </tbody>
                         </table>
                     </div>
                        
                     <div id="live_inf">
                        	<span>甲状腺功能</span>
                        	<div class="live_inftable">
                        			<table class="gw_xx">
                                         <tbody>
                                             <tr style="height:45px;">
                                                 <td>促甲状腺激素TSH：<input type="text" value="0"   name="tsh"> mIU/L</td>
                                                 <td>游离三碘甲状腺原氨酸FT3：<input type="text" value="0"   name="fts"> pmol/L</td>
                                                 <td>血清游离甲状腺激素FT4：<input type="text" value="0"   name="ftf"> nmol/L</td>
                                            </tr>
                                         </tbody>
                                    </table>
                             </div>
                        </div>
                        
                     <table class="gw_xx">
                     	<tr><td>地贫筛查：<input style="width:90%" type="text" value="" name="poorScreening"></td></tr>
                        <tr><td>支原体、衣原体、淋球菌：<input style="width:85%" type="text" value="" name="mcg"></td></tr>
                     </table>   
                   </div>
                    </div>
                    <div class="weight-title">孕期补充检查结果</div>
                     <div class="panel-body mod_btn" >
                     	<table class="gw_xx ">
                                    <tbody>
                                    <tr>
                                        <td width="50%">早期四维彩超（时间、头臀长、NT）：<input style="width:55%" type="text" value="" name="earlyFourDimensional"></td>
                                        <td width="50%">唐氏筛查情况：早期筛查 <input type="text" value="" name="earlyScreening"><span class="kong"> 中期筛查 <input type="text" value="" name="midScreening"></span></td>
                                       
                                    </tr>
                                    <tr>
                                        <td width="50%">无创DNA或羊水穿刺情况：<input style="width:55%" type="text" value="" name="noninvasiveDna"></td>
                                        <td width="50%">中晚期四维彩超情况（孕周及异常情况）：<input style="width:55%" type="text" value="" name="midLateFourDimensional"></td>
                                       
                                    </tr>
                                                                       
                            </tbody>
                         </table>
                        
                    </div>
                    
                    
                    <div class="weight-title">特殊检查</div>
                     <div class="panel-body mod_btn" >
                     	<table class="gw_xx cha_lab">
                                    <tbody>
                                    <tr>
                                        <td>
                                        		 <label><input style="width:10px;" type="checkbox" value=""> B超</label>
                                                 <label><input style="width:10px;" type="checkbox" value=""> 心电图</label>
                                                 <label><input style="width:10px;" type="checkbox" value=""> 营养测评</label>
                                                 <label><input style="width:10px;" type="checkbox" value=""> 心理测评</label>	
                                        </td>
                                        
                                       
                                    </tr>
                                                                       
                            </tbody>
                         </table>
                        <input type="hidden" name="specialExamination" id="specialExamination" value=""/>
                    </div>
                    
                    
                    
                    <div class="weight-title">诊断</div>
                    <div class="gw_jy">
                    	<div class="cbzd">
                        	<p class="float-left">诊断内容：</p>
                        		<select class="float-right" style=" height:30px;" name="testCaseDiagnoseId" id="testCaseDiagnoseId">
	                        		
                                </select>
                        <a class="btn btn-default float-right " href="#" style="height:30px; width:90px;margin-right:10px;" data-toggle="modal" 
   							data-target="#myModal1" id="manageModel">管理模板</a>
                     </div>
                        <p><textarea class="form-control" rows="10" maxlength="1000" placeholder="不超过1000字" id="diagnoseModelContent"></textarea></p>
                        
                     </div>
                    
                    <div class="panel-body">
                        <table class="gw_xx">
                                <tbody>
                                <tr>
                                    <td ><span style="color:red; font-size:18px;">*</span>检查医生： <input style="width:80px" type="text" value="" name="checkDoctor"></td>
                                    <td >登记人员：</td>
                                    <td >检查医院：</td> 
                                    <td >检查日期：
                                    <input style="width: 160px;height:28px; display:inline-block" type="text" name="startTime" id="startTime" placeholder="选择日期" class="form-control" value="" >
                                    </td>
                                    <td >初检孕周： 
                                     </td> 
                                </tr>
                        </tbody>
                     </table>
                </div>
                    
                     	
            		</div>     
  				</form>
            </div>
            
           
            <div class="gw_btn">
            <input class="btn btn-default oneline" style="width: 85px; height:40px;padding:0px 0px;" readonly value="取消" type="button" />
            <input class="btn btn-default oneline" style="width: 85px; height:40px;padding:0px 0px;" readonly value="保存" id="saveArchiveTestCaseBtn" type="button">
            </div>
            </div>
            
            <!-- 打印按钮触发 -->
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                       <div class="modal-dialog">
                          <div class="modal-content">
                          <div class="modal-header">
           						 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            						<h5 class="modal-title" id="myModalLabel" style="text-align:center">提示信息</h5>
        				 </div>
                             <div class="modal-body">
                             	
                                 
               					 <div class="alert alert-warning alert-dismissable">
   											<button type="button" class="close" data-dismiss="alert" aria-hidden="true"> &times;</button>
                                             请填写出生日期！
						         </div>
                                
                                
                                
                             </div>
                             <div class="modal-footer">
                                <button type="button" class="btn btn-default " data-dismiss="modal">确认</button>
                             </div>
                          </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                   </div>
                <!-- 模态框结束 -->
                
                <!-- 弹出框 -->
                    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                       <div class="modal-dialog">
                          <div class="modal-content">
                             <div class="modal-header" style="text-align:center">
                                <button type="button" class="close" 
                                   data-dismiss="modal" aria-hidden="true">
                                      &times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">
               							诊断
            					</h4>
                             </div>
                             <div class="modal-body">
        
                        <table style="border-collapse:collapse; border:0px solid #D0D0D0;">
                        		 <th height="25px">
                                    	<td>序号</td>
                                        <td>名称</td>
                                        <td>内容</td>
                                        <td>操作</td>
                                    </th>
                                    <tbody id="testCaseDiagnoseModel">
                                    
                            		</tbody>
                         </table>   
                             </div>
                             <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2" id="addTestCaseDiagnoseBtn"> 新增模板</button>
                             </div>
                          </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                   </div>
               		 <!-- 模态框结束 --> 
                     <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                       <div class="modal-dialog">
                          <div class="modal-content">
                             <div class="modal-header" style="text-align:center">
                                <button type="button" class="close" 
                                   data-dismiss="modal" aria-hidden="true">
                                      &times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">
               							新增模板
            					</h4>
                             </div>
	                             <div class="modal-body">
	                             <input type="hidden" id="operationType" value="insert"/>
	                             <input type="hidden" id="updateTestCaseId" value=""/>
	                       				<div style="padding:0 15px; margin-bottom:15px;"><span>名称：</span><input type="text" value="" name="diagnoseModelName" id="diagnoseModelName" style=" width:95%;padding:0 10px;"></div>
	                                    <div style="padding:0 15px;"><span style="vertical-align:top;">内容：</span><textarea style=" width:95%;padding:0 10px;" name="diagnoseModelContent" id="diagnoseModelContentNew"></textarea></div>
	                             </div>
                             <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="saveTestCaseDiagnoseBtn">保存</button>
                             </div>
                          </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                   </div>
            
            
	    </div>
    	
</div>
	
	<script type="text/javascript">
		$(function(){
			laydate({
			    elem: '#startTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			    event: 'focus' ,//响应事件。如果没有传入event，则按照默认的click
				max: laydate.now()
			});
		});
	</script>

</body>
