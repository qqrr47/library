package com.example.repository;

import com.example.entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> findAll(int index);
    public int count();

}
