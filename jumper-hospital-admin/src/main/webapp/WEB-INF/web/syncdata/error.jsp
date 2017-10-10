<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/menu.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/pregnant/daterangepicker-bs3.css">
<script type="text/javascript">
$(function(){
$(".mod_btn a").click(function(){
    $(this).toggleClass("active");
});
});
</script>
<style type="text/css">
	html{overflow-x: scroll;}
	body {min-width: 1660px;overflow-y: hidden;}
	.message-dialog{width: 800px;height: 500px;}
 	*{padding: 0; margin: 0; }
	.panel{margin-bottom:0px;}
	.modal {
    position: fixed;
    top: 3%;
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
    width: 50%;
    margin: 30px auto;
}
.modal-header {
    min-height: 16.42857143px;
    padding: 15px;
    border-bottom: 1px solid #fff;
}
.gw_btn { width:280px;}
	</style>
</head>
<body style="background: #F0F0F0;">
<div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-right" style="margin-top: 20px;">                                
	               	   <div class="col-xs-6 col-sm-3 sidebar-offcanvas" style="padding-right: 20px;">
		                       <ul class="page-sidebar-menu">
		                          <li class="active">
		                             <a href="/hospital/school/library">
		                               <i class="icon-plus-sign-alt"></i>&nbsp;
		                                <span class="title">档案管理</span>
		                            </a>
		                          </li>
		                       </ul>
	                    </div>
					<!-- 内容页 -->
					<div class="col-xs-12 col-sm-9">
							<div class="panel" >
					        	<div class="gw_rote">
					        		<span class="float-left" style="font-size:16px;">档案管理 > 档案详情 >
					        		 <a style=" color:#ff7ea5">监测数据</a></span>
					        	</div>
					        	<div class="panel-body">	
					                <div class="panel panel-default" style="margin:15px 0px;">
						                <div class="panel-body">
						                        <table class="gw_xx">
						                                <tbody>
								                                <tr>
								                                    <td colspan="4" >姓名： </td>
								                                    <td colspan="4" >联系电话：</td>
								                                    <td colspan="4" >末次月经：</td>
								                                    <td colspan="4" >预产期：</td>
								                                </tr>
								                                <tr>
								                                    <td colspan="4" >当前孕周：</td>
								                                    <td colspan="4" >状态：</td>
								                                    <td colspan="4" >分娩日期：</td>
								                                </tr>
								                        </tbody>
						                     </table>
						                </div>
					            	</div>
					            	
						            <div class="panel panel-default float-left" style="margin:15px 0px;width: 100%;" >
						                <div class=" float-left" style="width:100%;">
						                		<%@ include file="/WEB-INF/web/syncdata/syncdata_url.jsp"%>
						                </div>
						                <div class="panel-body" >
						                	<div style="text-align:center;margin: 0 auto;">
<!-- 												<img alt="" src="${pageContext.request.contextPath}/media/image/syncdata_img/error.png" style="vertical-align: middle"> -->
												<img src="${pageContext.request.contextPath}/media/image/gw_tj.jpg" >
											</div>
						                </div>
						            </div>
					            </div>
						    </div>
					</div>
<!-- 引入页尾 -->
</div>
</body>
</html>
