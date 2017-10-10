<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 公用dialog -->
<!-- <a class="btn btn-pink hide info-dialog-href" data-toggle="modal" data-target="#info-dialog"></a>
<div class="modal" id="info-dialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
	        	<button type="button" class="close info-dialog-close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title info-dialog-title" id="gridSystemModalLabel">提示！</h4>
	      	</div>
			<div class="modal-body info-dialog-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger info-dialog-sure">确定</button>
			</div>
		</div>
	</div>
</div> -->

<!-- 公用confirm -->
<!-- <a class="btn btn-pink hide info-confirm-href" data-toggle="modal" data-target="#info-confrim"></a>
<div class="modal" id="info-confrim" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title info-confirm-title" id="confirmLabelTitle">提示！</h4>
	      	</div>
			<div class="modal-body info-confirm-body">aaa</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default info-confirm-cancel" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-danger info-confirm-sure">确定</button>
			</div>
		</div>
	</div>
</div> -->

<!-- 预警记录dialog -->
<a class="btn btn-pink hide warning-confirm-href" data-toggle="modal"  data-target="#warning-confirm"></a>
<div class="modal" id="warning-confirm" tabindex="-1" role="dialog" aria-labelledby="confirm" aria-hidden="true" style="margin-top: -120px;height:anto" data-backdrop="static">
	<div class="modal-dialog" style="width: 1000px; height:500px;">
		<form action="${pageContext.request.contextPath}/report/getWarnInfo" method="POST" id="form">
    	<div class="panel panel-default">
            <div class="clearFix"></div>
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4>预警列表</h4>
				<div class="clearFix"></div>
	            <table class="detail_center bordered">
	                <thead align="center">  
	                    <tr class="waring_class">
	                        <th>序号</th>
	                        <th>日期</th>
	                        <th>时间</th>
	                        <th>报警类型</th>
	                        <th>报警值</th>
	                        <th>上限</th>
	                        <th>下限</th>
	                    </tr>
	                </thead>
	            </table>
	        </div>
	    </div>
	</form>
	</div>
