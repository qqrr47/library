package com.example.service;

import com.example.entity.Book;
import com.example.entity.Borrow;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page);
    public int getPages();
    public int getBorrowPages(Integer readerid);
    public int getBorrowPagesByState(Integer state);
    public void addBorrow(Integer bookid,Integer readerid);
    public List<Borrow> findAllBorrowByReaderId(Integer id,Integer page);
    public List<Borrow> findAllBorrowByState(Integer state,Integer page);
    public void handleBorrow(Integer borrowId,Integer state,Integer adminId);
}
