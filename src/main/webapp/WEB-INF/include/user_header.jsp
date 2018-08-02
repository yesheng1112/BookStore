<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--当用户未登录显示未登录的导航栏，如果用户登录成功显示登录后的导航栏--%>
<c:choose>
    <c:when test="${!empty user}">
        <%--
            empty new User() :false
            empty null :true
        --%>
        <%--如果能够获取到user对象，代表已登录--%>
        <div>
            <span>欢迎<span class="um_span">${user.username}</span>光临尚硅谷书城</span>
            <a href="pages/cart/cart.jsp">购物车</a>&nbsp;&nbsp;
            <a href="OrderClientServlet?method=getOrderList">我的订单</a>
            <a href="UserServlet?method=logout">注销</a>&nbsp;&nbsp;
            <a href="index.jsp">返回</a>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            <a href="pages/user/login.jsp">登录</a>|
            <a href="pages/user/register.jsp">注册</a>&nbsp;&nbsp;
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="pages/manager/manager.jsp">后台管理</a>
            <a href="index.jsp">返回</a>
        </div>
    </c:otherwise>
</c:choose>