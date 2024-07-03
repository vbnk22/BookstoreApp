package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.IBookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.User;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IBookDAO bookRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Cart getCart() {
        User user = userService.getCurrentUser();
        return user.getCart();
    }

    @Transactional
    public Cart addToCart(int bookId, int quantity) {
        Cart cart = getCart();
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        if (book.getQuantity() < 1) {
            return cart;
        }
        cart.addItem(book, quantity);
        book.setQuantity(book.getQuantity() - quantity);
        bookRepository.saveOrUpdate(book);
        return saveCart(cart);
    }

    @Transactional
    public Cart removeFromCart(int bookId) {
        Cart cart = getCart();
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        cart.removeItem(bookId);
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.saveOrUpdate(book);
        return saveCart(cart);
    }

    @Transactional
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
