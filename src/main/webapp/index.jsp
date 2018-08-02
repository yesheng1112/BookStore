<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>书城首页</h2>
<%--通过include引入base.jsp--%>
<%@ include file="/WEB-INF/include/base.jsp"%>
</body>
    <%--用户访问首页直接转发到BookClientServlet--%>
<jsp:forward page="/BookClientServlet?method=findPage"/>
</html>
