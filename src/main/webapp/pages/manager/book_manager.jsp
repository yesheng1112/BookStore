<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--通过include引入base.jsp--%>
    <%@ include file="/WEB-INF/include/base.jsp" %>
    <script type="text/javascript">
        //查找删除a标签，点击给出提示
        $(function () {
            //给删除绑定单击事件
            $(".delA").click(function () {
                //点击时查看图书标题
                var $title = $(this).parents("tr").find("td:eq(0)").text();
                if (!confirm("你真的要删除《" + $title + "》吗？")) {
                    //取消删除
                    //取消a标签的默认行为
                    return false;
                }
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%--引入用户导航栏--%>
    <%@ include file="/WEB-INF/include/manager_header.jsp" %>
</div>

<div id="main">
    <table>
        <%--
            判断图示集合是否为空
            判断分页对象的图书集合是否为空
        --%>
        <c:choose>
            <c:when test="${empty requestScope.page.data}">
                <%--集合为空--%>
                <tr>
                    <td colspan="7" align="center">
                        <h4>生意太好了，图书卖空，赶紧添加吧！！</h4>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                    <td>名称</td>
                    <td>价格</td>
                    <td>作者</td>
                    <td>销量</td>
                    <td>库存</td>
                    <td colspan="2">操作</td>
                </tr>
                <%--获取图书集合遍历展示--%>
                <c:forEach items="${page.data}" var="book">
                    <tr>
                        <td>${book.title}</td>
                        <td>${book.price}</td>
                        <td>${book.author}</td>
                        <td>${book.sales}</td>
                        <td>${book.stock}</td>
                            <%--点击修改需要将id交给servlet查询图书--%>
                        <td><a href="BookManagerServlet?method=getBook&bookId=${book.id}">修改</a></td>
                        <td><a class="delA" href="BookManagerServlet?method=deleteBook&bookId=${book.id}">删除</a></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_add.jsp">添加图书</a></td>
        </tr>
    </table>
    <br/>
    <%--引入分页导航栏--%>
    <%@ include file="/WEB-INF/include/nav.jsp" %>
</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>