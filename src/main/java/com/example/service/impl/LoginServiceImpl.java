package com.example.service.impl;

import com.example.entity.Reader;
import com.example.repository.AdminRepository;
import com.example.repository.ReaderRepository;
import com.example.repository.impl.AdminRepositoryImpl;
import com.example.repository.impl.ReaderRepositoryImpl;
import com.example.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private AdminRepository adminRepository = new AdminRepositoryImpl();
    @Override
    public Object login(String username, String password,String type) {
        Object object = null;
        switch (type){
            case "reader":
                object = readerRepository.login(username,password);
                break;
            case "admin":
                object = adminRepository.login(username,password);
                break;
        }

        return object;
    }

}
