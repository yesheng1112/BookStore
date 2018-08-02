<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--通过include引入base.jsp--%>
	<%@ include file="/WEB-INF/include/base.jsp"%>
	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%--引入用户导航栏--%>
			<%@ include file="/WEB-INF/include/user_header.jsp"%>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty requestScope.orderList}">
				<h2>你还没有订单呢~赶紧去买吧！！！</h2>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>订单编号</td>
						<td>日期</td>
						<td>金额</td>
						<td>状态</td>
						<td>详情</td>
					</tr>
					<c:forEach items="${requestScope.orderList}" var="order">
						<tr>
							<td>${order.id}</td>
							<td><fmt:formatDate value="${order.orderTime}" type="both" dateStyle="short" timeStyle="short"/></td>
							<td>${order.totalAmount}</td>
							<td>
								<c:if test="${order.state==0}">未发货</c:if>
								<c:if test="${order.state==1}"><a href="OrderClientServlet?method=takeGoods&orderId=${order.id}">点击确认收货</a> </c:if>
								<c:if test="${order.state==2}">交易完成</c:if>
							</td>
							<td><a href="#">查看详情</a></td>
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