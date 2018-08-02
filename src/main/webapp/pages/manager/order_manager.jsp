<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--通过include引入base.jsp--%>
	<%@ include file="/WEB-INF/include/base.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%--引入用户导航栏--%>
			<%@ include file="/WEB-INF/include/manager_header.jsp"%>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty requestScope.orderList}">
				<h2>再等等！！！现在开张呢！</h2>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>日期</td>
						<td>金额</td>
						<td>详情</td>
						<td>发货</td>
					</tr>
					<c:forEach items="${requestScope.orderList}" var="order">
						<tr>
							<td>${order.orderTime}</td>
							<td>${order.totalAmount}</td>
							<td><a href="#">查看详情</a></td>
							<td>
								<c:if test="${order.state == 0}"><a href="OrderManagerServlet?method=sendGoods&orderId=${order.id}">点击发货</a> </c:if>
								<c:if test="${order.state == 1}">等待用户收货</c:if>
								<c:if test="${order.state == 2}">交易完成</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>