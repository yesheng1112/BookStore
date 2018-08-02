<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>书城首页</title>
    <%--通过include引入base.jsp--%>
    <%@ include file="/WEB-INF/include/base.jsp" %>
    <script type="text/javascript">
        //提交价格时，验证范围
        $(function () {
            $(".addA").click(function () {
                //点击发送请求
                var url = "${pageContext.request.contextPath}/CartServlet";
                var data = {
                    "method": "addBook2Cart",
                    "bookId": this.id,
                    "t": Math.random()
                };
                var callback = function (result) {
                    //将响应结果设置到span中
                    $("#cartSpan").text("你的购物车中有" + result.count + "件商品");
                    $("#titleDiv").html("你刚刚将<span style='color: red'>" + result.title + "</span>加入购物车中");
                };
                //$.getJSON(url,data,callback());
                //$.post(url,data,callback(),"json");
                $.ajax(
                    {
                        url : url,
                        data : data,
                        dataType : "json",
                        type : "post",
                        success: function (result) {
                            callback(result);
                        }
                    }
                );
                //取消默认时间
                return false;
            });

            $("#queryBtn").click(function () {
                //获取min和max检查
                var $min = $("[name='min']").val();
                var $max = $("[name='max']").val();
                if (isNaN($min) || isNaN($max) || ($min - 0) < 0 || ($max - 0) <= 0) {
                    //不是数字
                    $("[name='min']").val($("[name='min']")[0].defaultValue);
                    $("[name='max']").val($("[name='max']")[0].defaultValue);
                    alert("请输入正确的数字");
                    return false;
                } else if (($max - $min) < 0) {
                    //都是数字时，min>max给出提示，不提交
                    alert("请输入正确的价格区间");
                    return false;
                }
            });
        });
    </script>
</head>

<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif"> <span
        class="wel_word">网上书城</span>
    <!-- 引入 用户导航栏 -->
    <%@ include file="/WEB-INF/include/user_header.jsp" %>
</div>

<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="BookClientServlet">
                <%--通过隐藏域携带方法名--%>
                <input type="hidden" name="method" value="findPageByPrice"/>
                价格:
                <input type="text" name="min" value="${param.min}"/>
                元-
                <input type="text" name="max" value="${param.max}"/>
                元
                <input id="queryBtn" type="submit" value="查询"/>
            </form>
        </div>
        <c:choose>
            <c:when test="${empty cart.cartItemList}">
                <div style="text-align: center">
                    <span id="cartSpan"></span>
                    <div id="titleDiv">
                        你的购物车空空入也！！！
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div style="text-align: center;">
                        <span id="cartSpan">
                            你的购物车中有
                            <span id="countSpan">${cart.totalCount}</span>
                            件商品
                        </span>
                    <div id="titleDiv">
                        您刚刚将
                        <span style="color: red;" id="titleSpan">${title}</span>
                        加入到了购物车中
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${empty page.data}">
                <%--图书集合为空--%>
                <h2>生意太好，你来晚了一步！！</h2>
            </c:when>
            <c:otherwise>
                <%--遍历展示分页图书--%>
                <c:forEach items="${page.data}" var="book">
                    <div class="b_list">
                        <div class="img_div">
                            <img class="book_img" src="${pageContext.request.contextPath }${book.imgPath }"/>
                        </div>
                        <div class="book_info">
                            <div class="book_name">
                                <span class="sp1">书名：</span>
                                <span class="sp2">${book.title}</span>
                            </div>
                            <div class="book_author">
                                <span class="sp1">作者：</span>
                                <span class="sp2">${book.author}</span>
                            </div>
                            <div class="book_price">
                                <span class="sp1">价格：</span>
                                <span class="sp2">￥${book.price}</span>
                            </div>
                            <div class="book_sales">
                                <span class="sp1">销量：</span>
                                <span class="sp2">${book.sales}</span>
                            </div>
                            <div class="book_amount">
                                <span class="sp1">库存：</span>
                                <span class="sp2">${book.stock}</span>
                            </div>
                            <div class="boo_add">
                                <a class="addA" id="${book.id}"
                                   href="CartServlet?method=addBook2Cart&bookId=${book.id}">加入到购物车</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    <%@ include file="/WEB-INF/include/nav.jsp" %>
</div>

<div id="bottom">
    <span> 尚硅谷书城.Copyright &copy;2015 </span>
</div>
</body>
</html>
