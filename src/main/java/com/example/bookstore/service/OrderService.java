package com.example.bookstore.service;

import com.example.bookstore.model.*;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order submitOrder() {
        User user = userService.getCurrentUser();
        Cart cart = user.getCart();

        Order order = new Order();
        order.setDate(new Date());
        order.setStatus(OrderStatus.SUBMITTED);
        order.setUser(user);

        for (CartItem item : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(item.getBook());
            orderItem.setQuantity(item.getQuantity());
            order.getItems().add(orderItem);
        }
        cart.getItems().clear();
        cartService.saveCart(cart);

        return orderRepository.save(order);
    }

    @Transactional
    public Order getOrder(long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
