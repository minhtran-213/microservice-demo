package com.example.orderservice.repository;


import com.example.common.dtos.documents.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
  Order findByOrderId(String orderId);
}
