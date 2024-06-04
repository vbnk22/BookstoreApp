package com.example.bookstore.controller;

import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    private BookService bookService;

    /*@GetMapping({"/home", "/", "/logout"})
    public String home(@RequestParam(value = "logout", required = false) String logout, Model model) {
        if (logout != null) {
            model.addAttribute("logoutMessage", "Logout successfully!");
        }
        return "home";
    }*/

    @RequestMapping(path = {"/main", "/"}, method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("books", this.bookService.getAllBooks());
        return "unlogged_view";
    }

    @RequestMapping(path = "/main_view")
    public String mainView(Model model) {
        model.addAttribute("books", this.bookService.getAllBooks());
        return "main_view";
    }
}
