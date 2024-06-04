package com.example.bookstore.model;

import com.example.bookstore.repository.BookDAO;
import com.example.bookstore.repository.IBookDAO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addItem(Book book, int quantity) {
        CartItem item = findItemById(book.getId());
        if (item == null) {
            item = new CartItem(book, this, quantity);
            items.add(item);
        } else {
            item.setQuantity(item.getQuantity() + quantity);
        }
    }

    public void removeItem(int bookId) {
        CartItem item = findItemById(bookId);
        if (item == null) {
            throw new RuntimeException("There is no book with this ID");
        } else {
            item.setQuantity(item.getQuantity() - 1);
            if (item.getQuantity() == 0) {
                items.remove(item);
            }
        }
    }

    private CartItem findItemById(int bookId) {
        for (CartItem item : items) {
            if (item.getBook().getId() == bookId) {
                return item;
            }
        }
        return null;
    }
}
