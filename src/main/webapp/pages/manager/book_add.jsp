<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加图书</title>
	<%--通过include引入base.jsp--%>
	<%@ include file="/WEB-INF/include/base.jsp"%>
	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">添加图书</span>
			<%--引入用户导航栏--%>
			<%@ include file="/WEB-INF/include/manager_header.jsp"%>
		</div>
		
		<div id="main">
			<%--
			form表单中携带参数，使用表单项
			如果是get请求提交参数，action地址中url?后面拼接的内容会被覆盖
			--%>
			<form action="BookManagerServlet" method="post">
				<input type="hidden" name="method" value="addBook"/>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="title" type="text" value="时间简史"/></td>
						<td><input name="price" type="text" value="30.00"/></td>
						<td><input name="author" type="text" value="霍金"/></td>
						<td><input name="sales" type="text" value="200"/></td>
						<td><input name="stock" type="text" value="300"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
		</div>
		
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>