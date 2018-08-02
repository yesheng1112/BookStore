<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--通过include引入base.jsp--%>
	<%@ include file="/WEB-INF/include/base.jsp"%>
	<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
</style>
	<script type="text/javascript">
		$(function () {
			//给用户名输入框绑定内容监听，当内容更改变将用户名提交给服务器进行判断
			$("[name='username']").change(function () {
				//获取用户名
				var name = this.value;
				//发送ajax请求
				var url = "${pageContext.request.contextPath}/UserServlet";
				var data = {
				  	"method":"checkUsername",
					"username":name
                };
				var callback = function (result) {
					//根据响应结果给出提示
					if (result == 0){
					    //用户名可用
						$(".errorMsg").html("<span style='color: green'>用户名可用</span>");
                    	$("#sub_btn").attr("disabled",false);
                    	$("#sub_btn").attr("background","#bbffaa");
					}else {
					    //用户名不可用
						$(".errorMsg").text("用户名占用，请更改");
					    $("#sub_btn").attr("disable",true);
					    $("#sub_btn").css("background-color","gray");
                    }
                };
				var type = "text";
				$.post(url,data,callback,type);
            });

			var i = 1;
			//给验证码图片 绑定单击事件
			$("#codeImg").click(function () {
				//可以通过src属性值来修改图片
				//this代表被点击的验证码
				//IE和火狐 缓存严重，如果一个标签的属性值重新设置和之前一样的话，就不会重新请求直接使用缓存
            	//谷歌不使用缓存
				//解决：在地址后面拼接一个变化的参数，可以欺骗浏览器缓存
				this.src = "code.jpg?t="+i++;
			});

			//1.查找注册按钮绑定单击事件
			$("#sub_btn").click(function () {
				//2.点击时 获取用户注册信息逐个验证
				var $name = $("[name='username']").val();
				//js中直接支持正则对象
				//创建验证姓名的正则对象
				var nameReg = /^[a-zA-Z0-9_-]{3,16}$/;//包含大小写字母a-z,数字0-9,_,-的用户名
           		//使用正则对象 验证姓名字符串 reg.test(要验证的字符串)如果匹配上返回true，匹配不上返回false
				if (!nameReg.test($name)){
				    //提示
					alert("请输入一个包含大小写字母a-z,数字0-9,_,-的三位以上的用户名");
					//如果用户输入错误，取消按钮的默认行为
					return false;
				}

				var $password = $("[name='password']").val();
				//创建验证密码的正则对象
				var pwdReg = /^[a-z0-9_-]{6,18}$/;
				if(!pwdReg.test($password)){
				    alert("请输入一个包含小写字母a-z,数字0-9,_,-的六位以上的密码");
					return false;
				}
				var $repwd = $("[name='repwd']").val();
				//直接和$password比较是否一致，如果一致可以提交，不一致取消提交
				if ($repwd != $password){
				    alert("两次密码输入不一致！");
				    return false;
				}
				var $email = $("[name='email']").val();
				//创建邮箱正则对象
				var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				//测试邮箱
				if (!emailReg.test($email)){
				    alert("邮箱地址错误！");
				    return false;
				}
            });
        });
	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${errorMsg}</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<%--使用隐藏表单域携带数据--%>
									<input type="hidden" name="method" value="register"/>
									<label>用户名称：</label>
										<%--
											回显的值从请求参数中读取
											register.jsp填写注册信息--提交给Servlet--失败转发到register.jsp页面[此时需要获取请求报文中的参数回显到页面中，给用户显示
										--%>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" value="${param.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" value="${param.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code"/>
									<img id="codeImg" alt="" src="code.jpg" style="float: right; margin-right: 40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>