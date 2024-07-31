package com.example.order_status_service.controller;

import com.example.order_status_service.model.OrderStatusEventSet;
import com.example.order_status_service.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderStatusEventController {
    @Autowired
    private OrderStatusService service;

    @Autowired
    private KafkaTemplate<String, OrderStatusEventSet> kafkaTemplate;

    @Value("${app.kafka.kafkaOrderStatusTopic}")
    private String topicName;


    public void addOrderStatus(@RequestBody OrderStatusEventSet status) {
        kafkaTemplate.send(topicName, status);
        service.add(status);
    }

}
