<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <a href="${page.path}&pageNumber=1">首页</a>
    <a href="${page.path}&pageNumber=${page.pageNumber-1}">上一页</a>
    <%--
    控制分页页码的显示，一页只显示5个页码
    begin和end只能使用变量，动态计算起始和结束索引
    1.判断总页码
    page.totalPage<=5:begin=1,end=totalPage
    page.totalPage>5:
        如果page.pageNumber <= 3 begin=1,end=5
        如果page.pageNumber>3 begin=pageNumber-2,end=pageNumber+2
        当页码大于5，设置end值之后需要判断end是不是>=总页码end=totalPage,begin=end-4
    --%>
    <c:choose>
        <c:when test="${page.totalPage<=5 }">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${page.totalPage}"/>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${page.pageNumber<=3 }">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${page.pageNumber-2}"/>
                    <c:set var="end" value="${page.pageNumber+2}"/>
                    <c:if test="${end>=page.totalPage}">
                        <c:set var="begin" value="${page.totalPage-4}"/>
                        <c:set var="end" value="${page.totalPage}"/>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
    <%--遍历展示导航索引--%>
    <%--挑出当前页高亮显示--%>
    <c:forEach begin="${begin}" end="${end}" var="index">
        <c:choose>
            <c:when test="${index==page.pageNumber}">
                <span style="color:red">【${index}】</span>
            </c:when>
            <c:otherwise>
                <a href="${page.path}&pageNumber=${index}">${index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <a href="${page.path}&pageNumber=${page.pageNumber+1}">下一页</a>
    <a href="${page.path}&pageNumber=${page.totalPage}">末页</a>
    共${page.totalPage}页,${page.totalCount}条记录到第<input value="${page.pageNumber}" name="pn" id="pn_input"/>页
    <input id="sendBtn" type="button" value="确定"/>
    <script type="text/javascript">
        $("#sendBtn").click(function () {
            //点击确定按钮，跳转到pn_input表单项中用户输入的页面
            //点击时获取页码
            var $number = $("#pn_input").val();
            //跳转BookManagerServlet?method=findPage&pageNumber=$number
            //使用绝对路径，浏览器解析 需要添加项目名
            //判断用户输入的是不是一个数字
            //is not a number
            if (isNaN($number)){
                //.defaultValue代表input框的前一个值
                //不是数字给出提示，并且在input中显示之前的页码
                alert("请输入一个正确的数字！！");
                $("#pn_input").val($("#pn_input")[0].defaultValue);
            }else {
                //使用EL获取项目名，在js中使用EL时一定要写在引号中
                window.location = "${page.path}&pageNumber="+$number;
            }
        });
    </script>
</div>