package com.example.controller;

import com.example.entity.Admin;
import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.entity.Reader;
import com.example.service.BookService;
import com.example.service.LoginService;
import com.example.service.impl.BookServiceImpl;
import com.example.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginServiceImpl();
    private BookService bookService = new BookServiceImpl();
    /**
     * 处理登录业务逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        Object object = loginService.login(username,password,type);
        if(object != null){
            HttpSession session = req.getSession();
            switch (type){
                case "admin":
                    Admin admin = (Admin) object;
                    session.setAttribute("admin",admin);
                    //跳转到管理员首页
                    resp.sendRedirect("/admin?page=1");
                    break;
                case "reader":
                    Reader reader = (Reader) object;
                    session.setAttribute("reader",reader);
                    //跳转到读者首页
                    resp.sendRedirect("/book?page=1");
                    break;
            }
        }else{
            String message = "alert('您输入的账号或密码错误，请重新输入！');";
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().print("<script language='javascript'>" + message + "window.location.href='login.jsp';</script>");
        }

    }
}
