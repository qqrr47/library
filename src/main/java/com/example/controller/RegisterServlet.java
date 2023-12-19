package com.example.controller;

import com.example.service.LoginService;
import com.example.service.RegisterService;
import com.example.service.impl.LoginServiceImpl;
import com.example.service.impl.RegisterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private RegisterService registerService = new RegisterServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username=="" || password == ""){
            String message = "alert('用户名和密码不能为空！');";
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().print("<script language='javascript'>" + message + "window.location.href='register.jsp';</script>");
        } else{
            if(registerService.findByUsername(username)){
                String message = "alert('用户名已存在！请重新输入！');";
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().print("<script language='javascript'>" + message + "window.location.href='register.jsp';</script>");
            } else{
                String confirmPassword = req.getParameter("confirmPassword");
                if(!password.equals(confirmPassword)){
                    String message = "alert('两次输入密码不同！请重新输入！');";
                    resp.setContentType("text/html;charset=utf-8");
                    resp.getWriter().print("<script language='javascript'>" + message + "window.location.href='register.jsp';</script>");
                } else{
                    registerService.addReader(username,password,req.getParameter("name"),req.getParameter("tel"),req.getParameter("cardId"),req.getParameter("gender"));
                    String message = "alert('注册成功！');";
                    resp.setContentType("text/html;charset=utf-8");
                    resp.getWriter().print("<script language='javascript'>" + message + "window.location.href='login.jsp';</script>");
                }
            }
        }

    }
}
