package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private BookService bookService;

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "adminpanel";
    }
}
