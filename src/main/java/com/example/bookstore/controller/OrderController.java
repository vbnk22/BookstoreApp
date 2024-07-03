package com.example.bookstore.controller;

import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderStatus;
import com.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public String submitOrder() {
        Order order = orderService.submitOrder();
        return "redirect:/order/" + order.getId();
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable long orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        model.addAttribute("order", order);

        model.addAttribute("totalSum", orderService.getSumOfOrder(order));

        return "order";
    }

    @RequestMapping(path = "/update/{orderId}", method = RequestMethod.GET)
    public String updateOrder(@PathVariable long orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        if (order == null) {
            return "redirect:/order/all";
        }
        model.addAttribute("order", order);
        return "order_form";
    }

    @RequestMapping(path = "/update/{orderId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateOrderStatus(@PathVariable("orderId") Long orderId, @RequestParam("status") OrderStatus status, Model model) {
        Order order = orderService.getOrder(orderId);
        if (order != null) {
            order.setStatus(status);
            orderService.saveOrder(order);
        }
        return "redirect:/order/allOrders";
    }

    /*@PostMapping("/update/{orderId}")
    public String updateOrder(@PathVariable long orderId, @RequestParam String status) {
        Order order = orderService.getOrder(orderId);
        if (order != null) {
            order.setStatus(OrderStatus.valueOf(status));
            orderService.saveOrder(order);
        }
        return "redirect:/admin/order/all";
    }*/

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
    public String usersOrders(Model model) {
        List<Order> orders = orderService.getOrdersByUserId();
        model.addAttribute("orders", orders);
        return "users_orders";
    }

    @RequestMapping(path = "/allOrders", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }
}
