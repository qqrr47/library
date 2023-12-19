package com.example.repository;

import com.example.entity.Reader;

public interface ReaderRepository {
    public Reader login(String username,String password);
    public boolean findByUsername(String username);
    public void addReader(String username,String password ,String name,String tel,String cardId,String gender);

}
