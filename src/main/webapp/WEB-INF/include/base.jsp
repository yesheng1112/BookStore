<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--动态获取拼接base标签中的url地址--%>
<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.7.2.js"></script>