package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return "redirect:/admin";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable int id, Model model) {
        Optional<Book> book = this.bookService.getBookById(id);
        if (book.isEmpty()) {
            return "redirect:/admin";
        }
        model.addAttribute("book", book.get());
        return "book_form";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public String updateBook(@PathVariable int id, @ModelAttribute Book book) {
        this.bookService.savOrUpdateBook(book);
        return "redirect:/admin";
    }

    @RequestMapping(path = "/delete")
    public String deleteBook(@RequestParam int id) {
        this.bookService.deleteBookById(id);
        return "redirect:/admin";
    }

}
