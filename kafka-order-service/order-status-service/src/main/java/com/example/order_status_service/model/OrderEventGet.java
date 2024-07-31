package com.example.order_status_service.model;

import lombok.Data;

@Data
public class OrderEventGet {

    private String product;

    private Integer quantity;

}
