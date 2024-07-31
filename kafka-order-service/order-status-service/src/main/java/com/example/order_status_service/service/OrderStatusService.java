package com.example.order_status_service.service;

import com.example.order_status_service.model.OrderStatusEventSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatusService {

    private final List<OrderStatusEventSet> orderStatusEventList = new ArrayList<>();

    @Value("${app.kafka.kafkaOrderStatusTopic")
    private String topicName;

    private final KafkaTemplate<String, OrderStatusEventSet> kafkaTemplate;

    public void add(OrderStatusEventSet event) {
        //kafkaTemplate.send(topicName, event);
        orderStatusEventList.add(event);
    }

}
