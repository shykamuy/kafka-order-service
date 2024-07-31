package com.example.order_service.controller;

import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderEvent;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    @Value("${app.kafka.kafkaOrderTopic}")
    private String topicName;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private final OrderService service;

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody Order order) {
        kafkaTemplate.send(topicName, OrderMapper.orderToEvent(order));
        service.add(order);
        return ResponseEntity.ok("Order was added");
    }

}
