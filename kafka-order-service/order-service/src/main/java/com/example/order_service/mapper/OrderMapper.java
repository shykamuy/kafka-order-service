package com.example.order_service.mapper;

import com.example.order_service.model.Order;
import com.example.order_service.model.OrderEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public static Order eventToOrder(OrderEvent event) {
        Order order1 = new Order();
        order1.setProduct(event.getProduct());
        order1.setQuantity(event.getQuantity());
        return order1;
    }

    public static OrderEvent orderToEvent(Order order) {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setProduct(order.getProduct());
        orderEvent.setQuantity(order.getQuantity());
        return orderEvent;
    }

}
