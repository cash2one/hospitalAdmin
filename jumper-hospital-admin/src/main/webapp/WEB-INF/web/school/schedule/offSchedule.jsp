<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 页头引入 -->
<jsp:include page="/WEB-INF/web/common/head.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/media/css/school-calendar.css?${v }" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/media/js/school/schedule.js?${v }"></script>
<!-- 内容页 -->
<div class="col-xs-12 col-sm-9">
	<form action="${pageContext.request.contextPath}/school/library" method="POST" id="form">
		<div class="panel panel-default">
        	<div class="panel-body">
				<div class="col-lg-3" style="float:right">
	                <div class="input-group" style="width:100%">
	                    <select id="year-select" class="form-control">
	                    	<option <c:if test="${year == 2016 }">selected</c:if> value="2016">2016</option>
	                    	<option <c:if test="${year == 2017 }">selected</c:if> value="2017">2017</option>
	                    	<option <c:if test="${year == 2018 }">selected</c:if> value="2018">2018</option>
	                    </select>
	                </div>
	            </div>
	        </div>
	    </div>
    	<div class="panel panel-default">
        	<div class="panel-body">
				<table class="month-list">
					<tr>
					<c:forEach begin="0" end="11" varStatus="c">
						<c:choose>
							<c:when test="${c.index + 1 == monthData.day }">
								<td>
									<div class="month-circle-container">
										<button type="button" class="month-circle circle-${(c.index) % 6 + 1 }">${c.index+1 }月</button>
										<div class="month-class">
											<span>课程数：${monthData.courseCount }节</span><br/>
											<span>已预约：${monthData.appointCount }人</span><br/>
											<span>已签到：${monthData.signCount }人</span><br/>
										</div>
									</div>
								</td>
							</c:when>
							<c:otherwise>
								<td>
									<div class="month-other">
										<button type="button" value="${c.index+1 }" class="circle-other circle-${(c.index) % 6 + 1 }">${c.index+1 }月</button>
									</div>
								</td>
							</c:otherwise>
						</c:choose>
						<c:if test="${c.index == 5}">
							</tr><tr>
						</c:if>
						<c:if test="${c.index == 11 }">
							</tr>
						</c:if>
					</c:forEach>
				</table>
				<table class="fc-border-separate" style="width:100%" cellspacing="0">
					<thead>
						<tr>
							<th>周一</th>
							<th>周二</th>
							<th>周三</th>
							<th>周四</th>
							<th>周五</th>
							<th>周六</th>
							<th>周日</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<c:forEach begin="0" end="41" varStatus="n">
							<c:if test="${n.index + 2 - day <= maxDay }">
								<c:choose>
									<c:when test="${n.index + 1 <= 7 && n.index + 1 >= day }">
										<td>
											<c:set var="flag" value="false"></c:set>
											<c:forEach var="course" items="${data }">
												<c:if test="${course.day == n.index + 2 - day }">
													<c:set var="flag" value="true"></c:set>
													<div class="day-container course-day-plan" y="${year }" m="${monthData.day }" d="${n.index + 2 - day }">
														<div class="fc-day-number">${n.index + 2 - day }</div>
														<div class="fc-day-content">
															<div class="class-container">
																<div class="class-desc">课程列表</div>
																<div class="class-plan">课程量：${course.courseCount }节</div>
																<div class="class-plan">已预约：${course.appointCount }人</div>
																<div class="class-plan">已签到：${course.signCount }人</div>
															</div>
														</div>
													</div>
												</c:if>
											</c:forEach>
											<c:if test="${!flag }">
												<div class="day-container">
													<div class="fc-day-number">${n.index + 2 - day }</div>
													<div class="fc-day-content">
														<div class="circle-container">
															<button type="button" class="circle-btn" y="${year }" m="${monthData.day }" d="${n.index + 2 - day }">
																<i class="icon-plus icon-3x" style="color:#E6E0DB"></i>
															</button>
														</div>
													</div>
												</div>
											</c:if>
										</td>
									</c:when>
									<c:when test="${n.index + 1 > 7 }">
										<td>
											<c:set var="flag" value="false"></c:set>
											<c:forEach var="course" items="${data }">
												<c:if test="${course.day == n.index + 2 - day  }">
													<c:set var="flag" value="true"></c:set>
													<div class="day-container course-day-plan" y="${year }" m="${monthData.day }" d="${n.index + 2 - day }">
														<div class="fc-day-number">${n.index + 2 - day }</div>
														<div class="fc-day-content">
															<div class="class-container">
																<div class="class-desc">课程列表</div>
																<div class="class-plan">课程量：${course.courseCount }节</div>
																<div class="class-plan">已预约：${course.appointCount }人</div>
																<div class="class-plan">已签到：${course.signCount }人</div>
															</div>
														</div>
													</div>
												</c:if>
											</c:forEach>
											<c:if test="${!flag }">
												<div class="day-container">
													<div class="fc-day-number">${n.index + 2 - day }</div>
													<div class="fc-day-content">
														<div class="circle-container">
															<button type="button" class="circle-btn" y="${year }" m="${monthData.day }" d="${n.index + 2 - day }">
																<i class="icon-plus icon-3x" style="color:#E6E0DB"></i>
															</button>
														</div>
													</div>
												</div>
											</c:if>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<div class="day-container">
												<div class="fc-day-number"></div>
												<div class="fc-day-content">
													<div class="circle-container"></div>
												</div>
											</div>
										</td>
									</c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${n.index + 2 - day > maxDay }">
								<td>
								<div class="day-container">
									<div class="fc-day-number"></div>
									<div class="fc-day-content">
										<div class="circle-container"></div>
									</div>
								</div>
							</td>
							</c:if>
							<c:if test="${n.index+1 != 0 && (n.index+1) % 7 == 0 && n.index != 41 }">
								</tr><tr>
							</c:if>
							<c:if test="${n.index == 41 }">
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
	        </div>
	    </div>
	</form>
</div>
<!-- 引入页尾 -->
<jsp:include page="/WEB-INF/web/common/foot.jsp"></jsp:include>
<jsp:include page="/WEB-INF/web/common/dialog.jsp"></jsp:include>