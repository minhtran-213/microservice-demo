package com.example.orderservice.service;

import com.example.common.dtos.models.OrderObject;

public interface OrderService {

  String createOrder(OrderObject orderModel);
}
