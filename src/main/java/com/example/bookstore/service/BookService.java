package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.IBookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookSerivce {

    @Autowired
    private final IBookDAO bookDAO;

    public BookService(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public void savOrUpdateBook(Book book) {
        this.bookDAO.saveOrUpdate(book);
    }

    @Override
    @Transactional
    public Optional<Book> getBookById(int id) {
        return this.bookDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return this.bookDAO.findAll();
    }

    @Override
    @Transactional
    public void deleteBookById(int id) {
        bookDAO.delete(id);
    }
}
