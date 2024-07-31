package com.example.order_status_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class OrderStatusEventSet {

    private String status;

    private Instant date;

}
