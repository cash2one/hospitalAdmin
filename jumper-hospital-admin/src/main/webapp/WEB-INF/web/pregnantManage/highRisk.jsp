<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<head>
	   <jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
	    <title>高危筛查</title>
	    <link href="${pageContext.request.contextPath}/media/css/gwsc.css?${v }" rel="stylesheet">
	</head>
<body style="background: #F0F0F0;">
<div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-right" style="margin-top: 20px;">                
                <!-- 档案管理 高危筛查 -->	                
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
		                       <ul class="page-sidebar-menu">
		                          <li class="active">
		                             <a href="">
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
		                <div class="panel-body">
		                        <table class="gw_xx">
		                                <tbody>
		                                <tr>
		                                    <td colspan="4" >姓名： 王大花</td>
		                                    <td colspan="4" >身份证号：421022111111111111</td>
		                                    <td colspan="4" >年龄：26岁</td>
		                                    <td colspan="4" >孕前BMI：22.5</td>
		                                </tr>
		                                <tr>
		                                    <td colspan="4" >末次月经：2015-09-15</td>
		                                    <td colspan="4" >预产期：2016-6-21</td>
		                                    <td colspan="4" >分娩日期：2016-06-21</td>
		                                    <td colspan="4" >当前孕周：3周4天</td>
		                                </tr>
		                                <tr>   
		                                    <td colspan="4" >联系电话：1388888888</td>
		                                    <td colspan="4" >现住地址：xxx</td>
		                                    <td colspan="4" >建册医院：妇幼保健院</td>
		                                    <td colspan="4" ></td>
		                                </tr>     
		                        </tbody>
		                     </table>
		                </div>
		            </div>
		            <div class="panel panel-default float-left" style="margin:15px 0px;width: 100%;">
		                <div class="float-left" style="width:100%;">
		                        <ul id="myTab" class="nav nav-tabs">
		   							<li ><a href="#">基本信息</a></li>
		   							<li><a href="#" >病史询问</a></li>
		                            <li><a href="#" >初检查情况</a></li>
		                            <li class="active"><a href="${pageContext.request.contextPath}/pregnant/archiveManage">高危筛查</a></li>
		                            <li><a href="#" >监测数据</a></li>
		                            <li><a href="#" >分娩结局</a></li>
		                            <li><a href="#">产后访视</a></li>
		                            <li><a href="#" >新生儿访视</a></li>
		  						</ul>
		                        <div id="myTabContent" class="tab-content">
		                           <div class="tab-pane fade in active float-left" id="gwsc">
		                              <div class="gwsc-all float-left">
		                              		<div class="gwsc-all-l float-left">
		                                    		<span class="float-left">高危评分 <a href="#" class="tooltip-test" data-toggle="tooltip" title="1、同时占上表两项以上者，其分数累加；
															2、高危评级分级
															轻：5分；中：10分—15分；重≥20分。
															3、转诊原则：一般卫生院不接受高危妊娠，
															中心卫生院10分及以下，15分及以上至县级
															及以上医疗机构">？</a></span>
													    <div class="gw_txt">20<span>分</span></div>
		                                				<div class="circle float-left">
															<canvas id="canvas_1" width="200" height="200"></canvas>
															<canvas id="canvas_2" width="200" height="200"></canvas>
														</div>
		                            					<div class="gwtab float-left">
		                                    				<img class="float-left" src="${pageContext.request.contextPath}/media/image/gw_1.jpg">
		                                                    <ul class="float-left">
		                                                    	<li style="color:#ff6767">重度高危</li>
		                                                        <li style="color:#d0d0d0">中度高危</li>
		                                                        <li style="color:#d0d0d0">轻度高危</li>
		                                                	</ul>
		                                    			</div>
		                                    </div>
		                                    <div class="gwsc-all-r float-left">
		                                    		<span>高危因素</span>
		                                            <div class="gwys">
		                                            	<ul>
		                                            		<li>身高≤1.45</li>
		                                                	<li>年龄<18岁≥35岁</li>
		                                                	<li>体重≤40公斤≥80公斤</li>
		                                                	<li> </li>
		                                            	</ul>
		                                    		</div>
		                                    </div>
		                            </div>
		                         </div>
							 </div>
		                </div>
		
		            	<div class="panel panel-default float-left" style=" margin:15px;width: 98%;">  
		                		 <div class="weight-title">诊断及意见</div>
		                   		 <div class="gw_jy">
			                    	<p >初步诊断：</p>
			                        <p>XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</p>
			                        <p style="margin-top:20px;">指导意见：</p>
			                        <p style="margin-bottom :20px;">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</p>
		                        </div>
		            	</div>     
		          		<div class="panel-body">
		                        <table class="gw_xx">
		                                <tbody>
		                                <tr>
		                                    <td colspan="4" >检查医院： XXXXXXXXXXXXXXXXXXXX</td>
		                                    <td colspan="4" >建档人员：XXX</td>
		                                    <td colspan="4" >建档日期：2016-06-23</td> 
		                                </tr>
		                        </tbody>
		                     </table>
		                </div>
		                
		            </div>
		            	<a href="${pageContext.request.contextPath}/pregnant/updatePersonArchive"><div class="gw_btn"><input class="btn btn-default" style="width: 85px; height:40px;padding:0px 0px;" readonly value="修改" type="button"></div></a>
		            </div>
			    </div>
		</div>
   <script type="text/javascript">
       $('.panel-body#myTab a').click(function (e) {
		      e.preventDefault();
		      $(this).tab('show');
  		  })

   $(function () { $("[data-toggle='tooltip']").tooltip(); });

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
		ctx_2.strokeStyle = "#ff6767";
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
			ctx_2.fillStyle = '#ff6767';   //圆环颜色值
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
