package com.example.order_status_service.listener;

import com.example.order_status_service.controller.OrderStatusEventController;
import com.example.order_status_service.model.OrderEventGet;
import com.example.order_status_service.model.OrderStatusEventSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;
@Component
@Slf4j
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderStatusEventController controller;


    @KafkaListener(
            topics = "${app.kafka.kafkaOrderTopic}",
            groupId = "${app.kafka.kafkaOrderGroupId}",
            containerFactory = "kafkaOrderEventConcurrentKafkaListenerContainerFactory"
    )
    public void listen(
            @Payload OrderEventGet message,
            @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp
    ) {
        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);
        controller.addOrderStatus(new OrderStatusEventSet("Created", Instant.now()));
    }

}
