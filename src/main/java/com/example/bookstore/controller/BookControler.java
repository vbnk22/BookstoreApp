package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/book")
public class BookControler {
    @Autowired
    private BookService bookService;

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "book_form";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute Book book) {
        this.bookService.savOrUpdateBook(book);
        return "redirect:/main";
    }
}
