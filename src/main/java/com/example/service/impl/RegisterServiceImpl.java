package com.example.service.impl;

import com.example.repository.ReaderRepository;
import com.example.repository.impl.ReaderRepositoryImpl;
import com.example.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    @Override
    public boolean findByUsername(String username) {
        return readerRepository.findByUsername(username);
    }
    public void addReader(String username,String password ,String name,String tel,String cardId,String gender){
        readerRepository.addReader(username,password,name,tel,cardId,gender);
    }
}
