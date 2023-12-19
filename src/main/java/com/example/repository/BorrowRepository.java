package com.example.repository;

import com.example.entity.Borrow;

import java.util.List;

public interface BorrowRepository {
    public void insert(Integer bookid,Integer readid,String borrowtime,String returntime,Integer adminid,Integer State);
    public List<Borrow> findAllByReaderId(Integer id,Integer index);
    public List<Borrow> findAllByState(Integer state,Integer index);
    public int count(Integer readerid);
    public int countByState(Integer state);
    public void handle(Integer borrowId,Integer state,Integer adminId);
}
