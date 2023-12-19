package com.example.service;

public interface RegisterService {
    public boolean findByUsername(String username);
    public void addReader(String username,String password ,String name,String tel,String cardId,String gender);
}
