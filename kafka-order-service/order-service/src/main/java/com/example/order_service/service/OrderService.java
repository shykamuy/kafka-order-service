package com.example.order_service.service;

import com.example.order_service.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    public void add(Order order) {
        orders.add(order);
    }

}
