package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderStatus;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "adminpanel";
    }

    @RequestMapping(path = "admin/order/all", method = RequestMethod.GET)
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @RequestMapping(path = "admin/order/update/{orderId}", method = RequestMethod.POST)
    public String updateOrderStatus(@PathVariable("orderId") Long orderId, @PathVariable("status") OrderStatus status, Model model) {
        Order order = orderService.getOrder(orderId);
        if (order != null) {
            order.setStatus(status);
            orderService.saveOrder(order);
        }
        return "redirect:/admin/order/all";
    }
}
