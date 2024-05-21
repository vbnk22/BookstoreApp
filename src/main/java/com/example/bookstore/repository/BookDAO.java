package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO implements IBookDAO {
    @PersistenceContext
    private EntityManager em;
    private final String GET_ALL_BOOKS_SQL = "FROM com.example.bookstore.model.Book";

    public BookDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery(GET_ALL_BOOKS_SQL, Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    @Override
    public Optional<Book> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void saveOrUpdate(Book book) {

    }

    @Override
    public void delete(int id) {

    }
}
