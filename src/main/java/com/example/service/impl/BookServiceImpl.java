package com.example.service.impl;

import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.repository.BookRepository;
import com.example.repository.BorrowRepository;
import com.example.repository.impl.BookRepositoryImpl;
import com.example.repository.impl.BorrowRepositoryImpl;
import com.example.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository = new BookRepositoryImpl();
    private BorrowRepository borrowRepository = new BorrowRepositoryImpl();
    @Override
    public List<Book> findAll(int page) {
        Integer index = (page-1)*10;
        return bookRepository.findAll(index);
    }

    @Override
    public int getPages() {
        int count = bookRepository.count();
        if(count==0){
            return 1;
        }
        int page =0;
        if(count % 10 == 0){
            page = count/10;
        }else {
            page = count/10 +1;
        }
        return page;
    }

    @Override
    public int getBorrowPages(Integer readerid) {
        int count = borrowRepository.count(readerid);
        if(count==0){
            return 1;
        }
        int page =0;
        if(count % 10 == 0){
            page = count/10;
        }else {
            page = count/10 +1;
        }
        return page;
    }

    @Override
    public int getBorrowPagesByState(Integer state) {
        int count = borrowRepository.countByState(state);
        if(count==0){
            return 1;
        }
        int page =0;
        if(count % 10 == 0){
            page = count/10;
        }else {
            page = count/10 +1;
        }
        return page;
    }



    @Override
    public void addBorrow(Integer bookid, Integer readerid) {
        //借书时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime =simpleDateFormat.format(date);
        //还书时间=借书时间+14天
        Calendar calendar = Calendar.getInstance();
        int dates = calendar.get(Calendar.DAY_OF_YEAR) + 14;
        calendar.set(Calendar.DAY_OF_YEAR, dates);
        Date date2 = calendar.getTime();
        String returnTime = simpleDateFormat.format(date2);
        borrowRepository.insert(bookid,readerid,borrowTime,returnTime,null,0);
    }

    @Override
    public List<Borrow> findAllBorrowByReaderId(Integer id,Integer page) {
        Integer index = (page-1)*10;
        return borrowRepository.findAllByReaderId(id,index);
    }

    @Override
    public List<Borrow> findAllBorrowByState(Integer state,Integer page) {
        Integer index = (page-1)*10;
        return borrowRepository.findAllByState(state,index);
    }

    @Override
    public void handleBorrow(Integer borrowId, Integer state, Integer adminId) {
        borrowRepository.handle(borrowId,state,adminId);
    }
}
