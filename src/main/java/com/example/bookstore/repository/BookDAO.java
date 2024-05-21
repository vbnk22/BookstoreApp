package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO implements IBookDAO {
    @PersistenceContext
    private EntityManager em;
    private final String GET_ALL_BOOKS_SQL = "FROM com.example.bookstore.model.Book";
    private final String GET_BOOK_FROM_ID_SQL = "FROM com.example.bookstore.model.Book WHERE id = :id";

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
        Query query = em.createQuery(GET_BOOK_FROM_ID_SQL);
        query.setParameter("id", id);
        List<Book> books = query.getResultList();
        if (books.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(books.get(0));
    }

    @Override
    public void saveOrUpdate(Book book) {
        em.persist(book);
    }

    @Override
    public void delete(int id) {
        em.remove(em.getReference(Book.class, id));
    }
}
