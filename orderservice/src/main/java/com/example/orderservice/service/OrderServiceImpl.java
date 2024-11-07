package com.example.orderservice.service;

import com.example.common.commands.CreateOrderCommand;
import com.example.common.dtos.models.OrderObject;
import com.example.common.enums.OrderStatus;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;

@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final CommandGateway commandGateway;

  @Override
  public String createOrder(OrderObject orderModel) {
    String orderId = UUID.randomUUID().toString();
    CreateOrderCommand command = CreateOrderCommand.builder()
        .orderId(orderId)
        .orderStatus(OrderStatus.CREATED)
        .price(orderModel.getPrice())
        .productId(orderModel.getProductId())
        .quantity(orderModel.getQuantity())
        .build();

    var result = commandGateway.send(command);
    log.info("Order created: {}", result);
    return "Order created with order id: #" + orderId;
  }
}
