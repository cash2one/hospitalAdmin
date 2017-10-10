<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/media/css/style.css?${v }" />
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/classroom/news.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<c:if test="${!hospitalInfo.isClass }">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="service-not-open">
					您还没有开通课堂自定义服务。<br />
					如您需要自行审核、编辑或发布课堂模块的资讯文章，<br />
					请到【医院管理】<a type="button" style="padding:0px 2px" class="btn btn-link open-chanel" href="${pageContext.request.contextPath}/hos/classroom">开通服务</a>。
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${hospitalInfo.isClass }">
		<div class="panel panel-default">
			<div class="panel-body">
				<c:if test="${hasChanel }">
					<a type="button" href="${pageContext.request.contextPath}/classroom/news_edit" class="btn btn-title search-btn" title="新增资讯">新增资讯</a>
				</c:if>
				<c:if test="${!hasChanel }">
					<div class="service-not-open">
						您还尚未添加任何频道，暂时无法发布文章资讯。<br>
						请到<a type="button" style="padding:0px 2px" class="btn btn-link open-chanel" href="${pageContext.request.contextPath}/classroom/chanel">【频道管理】</a>添加频道。
					</div>
				</c:if>
			</div>
		</div>
		<c:if test="${hasChanel }">
			<form action="${pageContext.request.contextPath}/classroom/news" method="POST" id="form">
				<input type="hidden" name="publish" id="publish-hidden" value="${publish }" />
				<input type="hidden" name="banner" id="banner-hidden" value="${banner }" />
				<input type="hidden" name="belong" id="belong-hidden" value="${belong }" />
		    	<div class="panel panel-default">
		        	<div class="panel-body">
		        		<ul class="nav nav-tabs no-border-margin" style="margin-bottom:20px;width:50%;display:inline">
			        		<li <c:if test="${publish == 1 }">class="current"</c:if>><a href="#" class="publish_type" value="1">已发布</a></li>
							<li <c:if test="${publish == 0 }">class="current"</c:if>><a href="#" class="publish_type" value="0">未发布</a></li>
						</ul>
						<div class="col-lg-6" style="float:right;text-align: right;">
			                <input type="text" class="form-control" name="searchKey" placeholder="新闻标题" value="${searchKey }" style="display:inline;width:180px" />
			                <select class="form-control" id="chanel-switch" name="chanelId" style="display:inline;width:170px">
			                	<option value="0">--请选择频道--</option>
			                	<c:forEach items="${chanels }" var="chanel">
			                		<option value="${chanel.id }" <c:if test="${chanelId == chanel.id }">selected</c:if>>${chanel.chanelName }</option>
			                	</c:forEach>
			                </select>
			                <select class="form-control" id="period-switch" name="periodId" style="display:inline;width:170px">
			                	<option value="0">--请选择孕期阶段--</option>
			                	<option value="1" <c:if test="${period == 1 }">selected</c:if>>孕早期</option>
								<option value="2" <c:if test="${period == 2 }">selected</c:if>>孕中期</option>
								<option value="3" <c:if test="${period == 3 }">selected</c:if>>孕晚期</option>
								<option value="4" <c:if test="${period == 4 }">selected</c:if>>0-6月</option>
								<option value="5" <c:if test="${period == 5 }">selected</c:if>>6月-1岁</option>
								<option value="6" <c:if test="${period == 6 }">selected</c:if>>1-3岁</option>
			                </select>
			                &nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-info" type="submit" style="display:inline"><i class="icon-search"></i>查询</button>
			            </div>
			            <div class="clearfix"></div>
		            	<div class="btn-group" role="group" style="margin-bottom: 16px;">
		            		<c:if test="${publish == 1 }">
		            			<c:choose>
		            				<c:when test="${banner == 0 }">
		            					<button type="button" class="btn btn-primary btn-banner" value="0">文章资讯列表</button>
										<button type="button" class="btn btn-default btn-banner" value="1">广告列表</button>
		            				</c:when>
		            				<c:otherwise>
		            					<button type="button" class="btn btn-default btn-banner" value="0">文章资讯列表</button>
										<button type="button" class="btn btn-primary btn-banner" value="1">广告列表</button>
		            				</c:otherwise>
		            			</c:choose>
		            		</c:if>
		            		<c:if test="${publish == 0 }">
		            			<c:choose>
		            				<c:when test="${belong == 49 }">
		            					<button type="button" class="btn btn-primary btn-belong" value="49">天使医生素材库</button>
										<button type="button" class="btn btn-default btn-belong" value="${hospitalInfo.id }">医院素材库</button>
		            				</c:when>
		            				<c:otherwise>
		            					<button type="button" class="btn btn-default btn-belong" value="49">天使医生素材库</button>
										<button type="button" class="btn btn-primary btn-belong" value="${hospitalInfo.id }">医院素材库</button>
		            				</c:otherwise>
		            			</c:choose>
		            		</c:if>
						</div>
			            <table class="bordered">
			                <thead>
			                    <tr>
			                        <th>文章标题</th>
			                        <th>所属频道</th>
			                        <th>所属阶段</th>
			                        <th>首页图片</th>
			                        <th>总阅读量</th>
			                        <th>分享数</th>
			                        <th>评论数</th>
			                        <c:if test="${publish == 0 }">
			                        	<th>保存时间</th>
			                        </c:if>
			                        <c:if test="${publish == 1 }">
			                        	<th>发布时间</th>
			                        </c:if>
			                        <th>操作</th>
			                    </tr>
			                </thead>
			                <c:forEach items="${page.result }" var="data" varStatus="status">
			                	<tr>
				                    <td>
				                    	<a href="${pageContext.request.contextPath}/classroom/detail?id=${data.id }">
					                    	<c:choose>
					                    		<c:when test="${fn:length(data.title) > 18}">
							                		${fn:substring(data.title, 0, 18)}...
							                   	</c:when>
							                   	<c:otherwise>
							                   		${data.title }
							                   	</c:otherwise>
					                    	</c:choose>
				                    	</a>
				                    </td>
				                    <td>${data.chanelName }</td>
				                    <td>
				                    	<c:set value="${fn:split(data.period, '|') }" var="periods" />
				                    	<c:forEach items="${periods }" var="period" varStatus="status">
				                    		<c:if test="${status.index == 3 }"><br></c:if>
											<c:if test="${period == 1 }">孕早期 </c:if>
											<c:if test="${period == 2 }">孕中期</c:if>
											<c:if test="${period == 3 }">孕晚期</c:if>
											<c:if test="${period == 4 }">0-6个月</c:if>
											<c:if test="${period == 5 }">6月-1岁</c:if>
											<c:if test="${period == 6 }">1-3岁</c:if>
										</c:forEach>
				                    </td>
				                    <td><img style="width: 128px;height: 64px;" src="${filePath }${data.imageUrl }" /></td>
				                    <td>${data.clicks }</td>
				                    <td>${data.shareNum }</td>
				                    <td>${data.commentNum }</td>
				                    <c:if test="${publish == 0 }">
			                        	<td><fmt:formatDate value="${data.addTime}" type="both" /></td>
			                        </c:if>
			                        <c:if test="${publish == 1 }">
			                        	<td><fmt:formatDate value="${data.publishTime}" type="both" /></td>
			                        </c:if>
		                   			<td>
		                   				<c:if test="${publish == 0 }">
		                   					<button type="button" class="btn btn-warning btn-sm news-update" isdia="0" title="修改" value="${data.id }"> 修改</button>
		                   				</c:if>
		                   				<c:if test="${publish == 1 }">
		                   					<button type="button" class="btn btn-warning btn-sm news-update" isdia="1" title="修改" value="${data.id }"> 修改</button>
		                   				</c:if>
		                   				<c:if test="${belong != 49 }">
		                   					<button type="button" class="btn btn-danger btn-sm news-delete" title="删除" value="${data.id }"> 删除</button>
		                   				</c:if>
		                   				<c:if test="${publish == 0 }">
		                   					<button type="button" class="btn btn-primary btn-sm news-publish" title="发布" value="${data.id }"> 发布</button>
		                   				</c:if>
		                   				<c:if test="${publish == 1 }">
			                        		<button type="button" class="btn btn-default btn-sm news-push" title="推送消息" value="${data.id }"> 推送消息</button>
			                        		<c:if test="${banner == 0 }">
			                        			<button type="button" class="btn btn-primary btn-sm cancel-publish" title="取消发布" value="${data.id }"> 取消发布</button>
			                        			<button type="button" class="btn btn-info btn-sm news-banner" title="设置为banner广告" value="${data.id }"> 设置为banner广告</button>
			                        		</c:if>
			                        		<c:if test="${banner == 1 }">
			                        			<button type="button" class="btn btn-success btn-sm news-top" title="置顶" value="${data.id }"> 置顶</button>
			                        		</c:if>
		                   				</c:if>
		                        	</td>
				                </tr>
			                </c:forEach>
			                <c:if test="${page == null || page.result == null || page.totalCount <= 0 }">
					      		<tr>
					      			<td colspan="9" style="color:red">暂无记录</td>
					      		</tr>
					      	</c:if>
			            </table>
			            <jsp:include page="../common/page.jsp"></jsp:include>
			        </div>
			    </div>
			</form>
		</c:if>
	</c:if>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/classroom/class_dialog.jsp"></jsp:include>