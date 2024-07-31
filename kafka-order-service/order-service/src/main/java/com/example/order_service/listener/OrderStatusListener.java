package com.example.order_service.listener;

import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.model.OrderEvent;
import com.example.order_service.model.OrderStatusEvent;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderStatusListener {

    private final OrderService service;

    @KafkaListener(
            topics = "${app.kafka.kafkaOrderTopic}",
            groupId = "${app.kafka.kafkaOrderGroupId}",
            containerFactory = "kafkaOrderStatusEventConcurrentKafkaListenerContainerFactory"
    )
    public void listen(
            @Payload OrderEvent message,
            @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp
    ) {
        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);
    }

}
