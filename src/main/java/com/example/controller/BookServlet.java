package com.example.controller;

import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.entity.Reader;
import com.example.service.BookService;
import com.example.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method == null){
            method = "findAll";
        }

        HttpSession session = req.getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        switch (method){
            case "findAll":
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<Book> list = bookService.findAll(page);
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",10);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getPages());
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                break;
            case "addBorrow":
                String bookidStr = req.getParameter("bookid");
                Integer bookid = Integer.parseInt(bookidStr);
                //添加借书请求
                bookService.addBorrow(bookid,reader.getId());
                //展示当前用户所有借书记录
                resp.sendRedirect("/book?method=findAllBorrow&page=1");
                break;
            case "findAllBorrow":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                List<Borrow> borrowList = bookService.findAllBorrowByReaderId(reader.getId(),page);
                req.setAttribute("borrowList",borrowList);
                req.setAttribute("dataPrePage",10);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPages(reader.getId()));
                req.getRequestDispatcher("borrow.jsp").forward(req,resp);
                break;
        }
    }
}
