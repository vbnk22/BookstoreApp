package com.example.bookstore.service;

import com.example.bookstore.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookSerivce {
    void savOrUpdateBook(Book book);
    Optional<Book> getBookById(int id);
    List<Book> getAllBooks();
    void deleteBookById(int id);
}
