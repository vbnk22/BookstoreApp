package com.example.bookstore.repository;

import com.example.bookstore.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    Optional<Book> findById(int id);
    List<Book> findAll();
    void saveOrUpdate(Book book);
    void delete(int id);
}
