<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>新增高危筛查</title>
     <jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
     <link href="${pageContext.request.contextPath}/media/css/gwsc.css?${v }" rel="stylesheet">
     <script type="text/javascript">
     </script>
</head>
<body style="background: #F0F0F0;">
<div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-right" style="margin-top: 20px;">                
                <!-- 档案管理 高危筛查 -->	                
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
		                       <ul class="page-sidebar-menu">
		                          <li class="active">
		                             <a href="#">
		                               <i class="icon-plus-sign-alt"></i>&nbsp;
		                                <span class="title">档案管理</span>
		                            </a>
		                          </li>
		                       </ul>
	                    </div>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
		<div class="panel" >
        	<div class="gw_rote"> <span class="float-left" style="font-size:16px;">档案管理 > 档案详情 > <a style=" color:#ff7ea5">高危筛查</a></span></div>
        	<div class="panel-body">	
                <div class="panel panel-default" style="margin:15px 0px;">
                <div class="weight-title" style="text-align:center;">高危筛查</div>
                <div class="gw_xz height_limit">
                        <table id="content">
                                <tr>
                                    <td width="10%" height="25px" rowspan="2">一般情况</td>
                                    <td width="22.5%">5分</td>
                                    <td width="22.5%">10分</td>
                                    <td width="22.5%">15分</td>
                                    <td width="22.5%">20分</td>
                                </tr>
                                <tr height="80">
		                             <td width="22.5%">
		                             	<a class="btn btn-default tagadd" href="#" style="margin-bottom:5px;">体重≤40公斤≥80公斤</a>
		                             </td>
		                             <td>
		                             	<a class="btn btn-default tagadd" href="#" style="margin-bottom:5px;">年龄<18岁或≥35岁</a><br/>
		                             	<a class="btn btn-default tagadd" href="#" style="margin-bottom:5px;">身高≤1.45米</a><br/>
		                             	<a class="btn btn-default tagadd" href="#" style="margin-bottom:5px;">智力低下</a><br/>
		                             </td>
		                             <td>
		                             	<a class="btn btn-default tagadd" href="#">胸部脊柱畸形</a><br/>
		                             </td>
		                             <td></td>
		                             <td></td>
		                        </tr> 
                                <tr height="80">   
                                    <td>异常产史</td>
                                    <td>
                                    	<a class="btn btn-default tagadd" href="#" >自然流产≥2次</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" style="margin-bottom:5px;">人工流产≥2次</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" style="margin-bottom:5px;">异位妊娠</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" style="margin-bottom:5px;">早产史≥2次</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" style="margin-bottom:5px;">围产儿死亡史1次</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" style="margin-bottom:5px;">巨大儿分娩史</a><br/>
                                    </td>
                                    <td>
                                    	<a class="btn btn-default tagadd" href="#" >死胎,死产史≥2次</a>
                                    	<a class="btn btn-default tagadd" href="#" >先天异常儿史</a>
                                    	<a class="btn btn-default tagadd" href="#" >难产史</a>
                                    	<a class="btn btn-default tagadd" href="#" >产后出血史</a>
                                    	<a class="btn btn-default tagadd" href="#" >瘢痕子宫</a>
                                    	<a class="btn btn-default tagadd" href="#" >重度子痫前期,子痫史</a>
                                    </td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height="80">   
                                    <td>严重内科病症</td>
                                    <td width="22.5%">
                                    	<a class="btn btn-default tagadd" href="#" >妊娠合并贫血轻中度(≥60,＜100g/L)</a>
                                    </td>
                                    <td width="22.5%">
                                       <a class="btn btn-default tagadd" href="#" >妊娠合并贫血,重度(＜60g/L)</a>
                                       <a class="btn btn-default tagadd" href="#" >乙肝病毒携带者</a>
                                     </td>
                                    <td>
                                    	<a class="btn btn-default tagadd" href="#" >活动性肺结核</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >心脏病心功能I-II级</a>
                                    	<a class="btn btn-default tagadd" href="#" >糖尿病</a>
                                    	<a class="btn btn-default tagadd" href="#" >活动性病毒性肝炎</a>
                                    	<a class="btn btn-default tagadd" href="#" >甲状腺功能亢进或低下</a>
                                    	<a class="btn btn-default tagadd" href="#" >高血压</a>
                                    	<a class="btn btn-default tagadd" href="#" >肾脏疾病</a>
                                    </td>
                                    <td>
                                    	<a class="btn btn-default tagadd" href="#" >心脏病心功能III-IV级</a><br/>
                                    </td>
                                </tr> 
                                <tr height="80">   
                                    <td>性病</td>
                                    <td></td>
                                    <td>
                                    	<a class="btn btn-default tagadd" href="#" >淋病或梅毒或尖锐湿疣</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >艾滋病或沙眼衣原体感染</a><br/>
                                    </td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr height="80">   
                                    <td>本次妊娠异常情况</td>
                                    <td>
                                    	<a class="btn btn-default tagadd" href="#" >妊娠期高血压、轻度子痫前期</a><br/>
                                    </td>
                                    <td>
                                    	<a class="btn btn-default tagadd" href="#" >骨盆异常(畸形,狭窄)</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >胎位异常(臀先露,肩先露)</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >多胎妊娠</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >胎膜早破</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >胎儿先天畸形</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >死胎</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >重度子痫前期</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >妊娠期肝内胆汁淤积症</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >胎儿窘迫</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >盆腔肿瘤</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >羊水过多</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >估计巨大儿或FGR</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >过期妊娠</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >母儿ABO血型不合</a><br/>
                                    </td>
                                    <td>
                                    	<a class="btn btn-default tagadd" href="#" >淋病或梅毒或尖锐湿疣</a><br/>
                                    </td>
                                    <td>
                                    	<a class="btn btn-default tagadd" href="#" >羊水过少</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >母儿RH血型不合</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >子痫</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >前置胎盘</a><br/>
                                    </td>
                                </tr>
                                <tr height="80">   
                                    <td>致畸因素</td>
                                    <td>
                                    	<a class="btn btn-default tagadd" href="#" >孕妇及一级亲属有遗传病史</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >酗酒,吸烟≥20支/日</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >妊娠早期接触可疑至畸药物放射线等物理化学因素及病毒感染等</a><br/>
                                    </td>
                                    <td> </td>
                                    <td> </td>
                                    <td> </td>
                                </tr>
                                <tr height="80">   
                                    <td>社会因素</td>
                                    <td>
                                    	<a class="btn btn-default tagadd" href="#" >家庭贫困或家庭中受歧视</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >孕妇或丈夫为文盲或半文盲</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >丈夫长期不在家</a><br/>
                                    	<a class="btn btn-default tagadd" href="#" >居住地到卫生院需要一小时以上</a><br/>
                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>    
                     </table>
                </div>
            </div>
                <div class=" float-left" style="margin:15px 0px;width: 100%;">
                              		<div class="gwsc-all-l float-left">
	                                    		<span class="float-left">高危评分 <a href="#" class="tooltip-test" data-toggle="tooltip" title="1、同时占上表两项以上者，其分数累加；
														2、高危评级分级
														轻：5分；中：10分—15分；重≥20分。
														3、转诊原则：一般卫生院不接受高危妊娠，
														中心卫生院10分及以下，15分及以上至县级
														及以上医疗机构"> ？ </a>
												</span>
											    <div class="gw_txt" style="color:#fda44d">10<span>分</span></div>
                                				<div class="circle float-left">
													<canvas id="canvas_1" width="200" height="200"></canvas>
													<canvas id="canvas_2" width="200" height="200"></canvas>
												</div>
                            					<div class="gwtab float-left">
                                    				<img class="float-left" src="${pageContext.request.contextPath}/media/image/gw_2.jpg">
                                                    <ul class="float-left">
                                                    	<li style="color:#ff6767">重度高危</li>
                                                        <li style="color:#fabd5c">中度高危</li>
                                                        <li style="color:#6beba7">轻度高危</li>
                                                    </ul>
                                    			</div>
                                    </div>
                                    <div class="gwsc-all-r float-left">
                                    		<span>高危因素</span>
                                            <div class="gwys">
                                            	<ul>
                                            		<a><li>身高≤1.45<em></em></li></a>
                                                	<a><li>年龄<18岁≥35岁<em></em></li></a>
                                                	<a><li>体重≤40公斤≥80公斤<em></em></li></a>
                                                	<a><li> </li></a>
                                            	</ul>
                                    		</div>
                              		</div>
                </div>
                <div class="panel panel-default float-left" style="width: 100%;"> 
                	<div class="weight-title">诊断及意见</div>
                    <div class="gw_jy">
                    	<div class="cbzd">
                        	<p class="float-left">初步诊断：</p>
                        		<select class="float-right" style=" height:30px;">
                                                    <option value="1">选择模板</option>
                                                    <option value="2">全部</option>
                                                    <option value="3">近7天</option>
                                                    <option value="4">近30天</option>
                                                </select>
                        <a class="btn btn-default float-right " href="#" style="height:30px; width:90px;margin-right:10px;" data-toggle="modal" 
   data-target="#myModal">管理模板</a>
                     </div>
                        <p><textarea class="form-control" rows="3" placeholder="不超过1000字"></textarea></p>
                        <div class="cbzd">
                        	<p class="float-left" style="margin-top:20px;">指导意见：</p>
                        	<select class="float-right" style=" height:30px;margin-top:20px;">
                                                    <option value="1">选择模板</option>
                                                    <option value="2">全部</option>
                                                    <option value="3">近7天</option>
                                                    <option value="4">近30天</option>
                                                </select>
                           <a class="btn btn-default float-right " href="#" style="height:30px; width:90px;margin-right:10px; margin-top:20px;" data-toggle="modal" data-target="#myModal">管理模板</a>
                     </div>
                        <p style="margin-bottom :20px;"><textarea class="form-control" rows="3" placeholder="不超过1000字"></textarea></p>
                     </div>
                     	
            		</div>   
                    
                     

          
          <div class="panel-body">
                        <table class="gw_xx">
                                <tbody>
                                <tr>
                                    <td colspan="4" >检查医院： XXXXXXXXXXXXXXXXXXXX</td>
                                    <td colspan="4" >建档人员：XXX</td>
                                    <td colspan="4" >建档日期：2016-06-23</td> 
                                    <td colspan="4" >
                                    <select class="float-right" style=" height:30px;">
                                                    <option value="1">日期选择</option>
                                                    <option value="2">全部</option>
                                                    <option value="3">近7天</option>
                                                    <option value="4">近30天</option>
                                                </select>
                                     </td> 
                                </tr>
                        </tbody>
                     </table>
                </div>
                <div class="gw_btn">
            		<input class="btn btn-default oneline" style="width: 85px; height:40px;padding:0px 0px;" readonly value="取消" type="button">
            		<input class="btn btn-default oneline" style="width: 85px; height:40px;padding:0px 0px; margin-left:30px;" readonly value="保存" type="button">
            	</div>
            </div>
            
            
            </div>
            
            
   
            
	    </div>
    	
</div>

					<!-- 弹出框 -->
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                                    <tbody>
                                    <tr height="25px">
                                    	<td>序号</td>
                                        <td>名称</td>
                                        <td>内容</td>
                                        <td>操作</td>
                                    </tr>
                                    <tr height="80px">
                                        <td >1  </td>
                                        <td >高血糖 </td>
                                        <td >xxxxxxxxxxxxxxx</td>
                                        <td ><a href="#">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">删除</a></td>
                                    </tr>
                                    <tr height="80px">
                                        <td >2 </td>
                                        <td >XXX</td>
                                        <td >xxxxxxxxxxxxxxx</td>
                                        <td ><a >修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a >删除</a></td>
                                    </tr>
                                        
                            </tbody>
                         </table>   
                                
                             </div>
                             <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                                   新增模板
                                </button>
                             </div>
                          </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                   </div>
                   
                   
                   
                   
                   <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
        
                        <table style="border-collapse:collapse; border:0px solid #D0D0D0;">
                                    <tbody>
                                    <tr height="25px">
                                    	<td>序号</td>
                                        <td>名称</td>
                                        <td>内容</td>
                                        <td>操作</td>
                                    </tr>
                                    <tr height="80px">
                                        <td >1  </td>
                                        <td >高血糖 </td>
                                        <td >xxxxxxxxxxxxxxx</td>
                                        <td ><a href="#">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">删除</a></td>
                                    </tr>
                                    <tr height="80px">
                                        <td >2 </td>
                                        <td >XXX</td>
                                        <td >xxxxxxxxxxxxxxx</td>
                                        <td ><a >修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a >删除</a></td>
                                    </tr>
                                        
                            </tbody>
                         </table>   
                                
                             </div>
                             <div class="modal-footer">
                                <button type="button" class="btn btn-primary">
                                   新增模板
                                </button>
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
        
                       				<div style="padding:0 15px; margin-bottom:15px;"><span>名称：</span><input type="text" value="" style=" width:95%;padding:0 10px;"></div>
                                    <div style="padding:0 15px;"><span style="vertical-align:top;">内容：</span><textarea style=" width:95%;padding:0 10px;"></textarea></div>
                                
                             </div>
                             <div class="modal-footer">
                                <button type="button" class="btn btn-primary">
                                   保存
                                </button>
                             </div>
                          </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                   </div>
	<script src="js/jquery.min.js"></script>
   <script src="js/bootstrap.js"></script>
   <script>
   
    $(function () { $('#myModal').on('hide.bs.modal', function () {
      $(function () { $('#myModal2').modal('show')})});
   });
   
       $('.panel-body#myTab a').click(function (e) {
      e.preventDefault();
      $(this).tab('show');
    })

   $(function () { $("[data-toggle='tooltip']").tooltip(); });
   
   $(".tagadd").click(function(){
        //$(this).addClass("table_li_click").siblings().removeClass("table_li_click")
        $(this).css("background","#ff7ea5").siblings().css("background","#ffffff")
        $(this).css("color","#fff").siblings().css("color","#333333")
    });

	function inte(percent) {
		var canvas_1 = document.querySelector('#canvas_1');
		var canvas_2 = document.querySelector('#canvas_2');
		var ctx_1 = canvas_1.getContext('2d');
		var ctx_2 = canvas_2.getContext('2d');
		ctx_1.lineWidth = 10;
		ctx_1.strokeStyle = "#f1f1f1";
		//画底部的灰色圆环
		ctx_1.beginPath();
		ctx_1.arc(canvas_1.width / 2, canvas_1.height / 2, canvas_1.width / 2 - ctx_1.lineWidth / 2, 0, Math.PI * 2, false);
		ctx_1.closePath();
		ctx_1.stroke();
		if (percent < 0 || percent > 100) {
			throw new Error('percent must be between 0 and 100');
			return
		}
		ctx_2.lineWidth = 12; //圆的宽度
		ctx_2.strokeStyle = "#fda44d";//圆环颜色值 橙色色值#fda44d   绿色77e084
		var angle = 0;
		var timer;
		(function draw() {
			timer = requestAnimationFrame(draw);
			ctx_2.clearRect(0, 0, canvas_2.width, canvas_2.height)
			//百分比圆环
			ctx_2.beginPath();
			ctx_2.arc(canvas_2.width / 2, canvas_2.height / 2, canvas_2.width / 2 - ctx_2.lineWidth / 2, 0, angle * Math.PI / 180, false);
			angle++;
			var percentAge = parseInt(((angle / 360) * 100))
			if (angle > (percent / 100 * 360)) {
				percentAge = percent
				window.cancelAnimationFrame(timer);
			};
			ctx_2.stroke();
			ctx_2.closePath();
			ctx_2.save();
			ctx_2.beginPath();
			ctx_2.rotate(90 * Math.PI / 180) 
			var text = percentAge ;
			ctx_2.closePath();
			ctx_2.restore();
		})()
	}
	window.onload = inte(100); 
	</script>
    
<!-- 引入页尾 -->

</div></div>
</body>
</html>
