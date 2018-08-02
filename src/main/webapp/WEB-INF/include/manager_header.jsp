<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <%--相对路径，会和base.jsp页面中的base标签拼接--%>
    <%--请求交给BookManagerServlet查询图书集合时需要一个参数 method= 方法名
    模拟get请求携带参数--%>
    <a href="BookManagerServlet?method=findPage">图书管理</a>
    <a href="OrderManagerServlet?method=getAllOrder">订单管理</a>
    <a href="index.jsp">返回商城</a>
</div>
