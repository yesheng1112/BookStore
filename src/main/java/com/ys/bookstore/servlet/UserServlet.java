package com.ys.bookstore.servlet;

import com.ys.bookstore.bean.User;
import com.ys.bookstore.service.UserService;
import com.ys.bookstore.service.impl.UserServiceImpl;
import com.ys.bookstore.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserServlet",urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet{

    private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();

    protected void checkUsername(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        String username = request.getParameter("username");
        boolean b = service.checkUsername(username);
        if (b){
            //可用
            response.getWriter().write("0");
        }else {
            //不可用
            response.getWriter().write("1");
        }
    }

    /*
    * 处理注销请求
    * */
    protected void logout(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        //移除域中的user对象即可注销
        HttpSession session = request.getSession();
        //移除，用户注销最好就是session对象失效
        session.invalidate();
        //跳转到注销之前的页面
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    protected void login(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        User user = WebUtils.params2Bean(request,new User());
        //调用userservice处理登录业务
        User bean = service.login(user);
        //判断
        if (bean == null){
            //转发之前设置错误信息
            String msg = "账号或密码错误，请重新登录";
            //将字符串存到域中
            request.setAttribute("errorMsg",msg);
            //查询失败，用户不存在，转发到登录页面继续登录
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            //登录成功，保存用户
            HttpSession session = request.getSession();
            session.setAttribute("user",bean);
            //查询成功，重定向到成功页面
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
        }
    }

    protected void register(HttpServletRequest request , HttpServletResponse response)
                throws ServletException , IOException{
        //1.获取图片验证码[用户提交的验证码字符串]
        String clientCode = request.getParameter("code");
        //2.获取session中验证码字符串
        HttpSession session = request.getSession();
        String serverCode = (String) session.getAttribute("code");
        //3.判断，如果相等可以注册
        //4.移除session中的验证码
        session.removeAttribute("code");
        if (clientCode != null && clientCode.equals(serverCode)){
            User user = WebUtils.params2Bean(request,new User());
            //调用UserService的处理业务
            boolean b = service.regist(user);
            if (b){
                //重定向绝对路径 需要添加项目名
                response.sendRedirect(request.getContextPath()+"/pages/user/register_success.jsp");
            }else {
                //设置错误消息
                String errorMsg = "用户名已经存在！！！";
                request.setAttribute("errorMsg",errorMsg);
                //失败 转发
                request.getRequestDispatcher("/pages/user/register.jsp").forward(request,response);
            }
        }else {
            //验证码错误给出提示
            //设置错误消息
            String errorMsg= "验证码错误!!!";
            request.setAttribute("errorMsg" ,errorMsg);
            //失败 转发
            request.getRequestDispatcher("/pages/user/register.jsp").forward(request,response);
        }
    }
}
