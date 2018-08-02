<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--通过include引入base.jsp--%>
	<%@ include file="/WEB-INF/include/base.jsp"%>
	<script type="text/javascript">
		$(function () {
			//给input设置内容改变的监听，内容改变将数量和id交给CartServlet.updateCount处理
			$(".countInp").change(function () {
				var count = this.value;
				var id = this.id;
				var $amountTd = $(this).parents("tr").find("td:eq(3)");
            	//前端验证count是不是一个正确数字
				if(isNaN(count)){
				    this.value == this.defaultValue;
				    alert("请输入一个正确的数字");
				}else {
				    var url = "${pageContext.request.contextPath}/CartServlet";
				    var data = {
				        "method":"updateCount",
						"bookId":id,
						"count":count
					};
				    var callback = function (res) {
						//修改页面中的数据
						$(".b_count").text(res.totalCount);
						$(".b_price").text(res.totalAmount);
						$amountTd.text(res.amount);
                    };
                    $.post(url,data,callback,"json");
				}
			});
        });
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%--引入用户导航栏--%>
			<%@ include file="/WEB-INF/include/user_header.jsp"%>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty cart.cartItemList }" >
				<%--购物车中没有购物项--%>
				<h3>赶紧去买吧！！！！</h3>
			</c:when>
			<c:otherwise>
				<%--购物车中有购物项，遍历显示--%>
				<table>
					<tr>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>金额</td>
						<td>操作</td>
					</tr>
					<c:forEach items="${cart.cartItemList}" var="item">
						<tr>
							<td>${item.book.title}</td>
							<td><input type="text" id="${item.book.id}" class="countInp" value="${item.count}" style="width: 30px;text-align: center;"/></td>
							<td>${item.book.price}</td>
							<td class="amount">${item.amount}</td>
							<td><a href="CartServlet?method=delCartItem&bookId=${item.book.id}">删除</a></td>
						</tr>
					</c:forEach>
				</table>

				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count">${cart.totalCount}</span>件商品</span>
					<span class="cart_span">总金额<span class="b_price">${cart.totalAmount}</span>元</span>
					<span class="cart_span"><a href="CartServlet?method=clear">清空购物车</a></span>
					<span class="cart_span"><a href="OrderClientServlet?method=checkOut">去结账</a></span>
				</div>
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