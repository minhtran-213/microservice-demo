package com.example.orderservice.handler;

import com.example.common.dtos.documents.Order;
import com.example.common.enums.OrderStatus;
import com.example.common.events.CancelOrderEvent;
import com.example.common.events.CompleteOrderEvent;
import com.example.common.events.OrderCreateEvent;
import com.example.orderservice.repository.OrderRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {

  private final OrderRepository orderRepository;

  public void onOrderCreateEvent(OrderCreateEvent event) {
    Order order = orderRepository.findByOrderId(event.getOrderId());
    if (!Objects.nonNull(order)) {
      order = new Order();
      order.setOrderId(event.getOrderId());
      order.setOrderStatus(event.getOrderStatus().name());
      order.setQuantity(event.getQuantity());
      order.setUserId(event.getUserId());
      order.setProductId(event.getProductId());
      order.setComment("Order initiated");
      orderRepository.save(order);
    }
  }

  public void onCompleteOrderEvent(CompleteOrderEvent event) {
    Order order = orderRepository.findByOrderId(event.getOrderId());
    if (Objects.nonNull(order)) {
      order.setOrderStatus(event.getOrderStatus().name());
      order.setComment("Order created successfully");
      orderRepository.save(order);
    }
  }

  public void onCancelOrderEvent(CancelOrderEvent event) {
    Order order = orderRepository.findByOrderId(event.getOrderId());
    if (Objects.nonNull(order)) {
      order.setOrderStatus(OrderStatus.CANCELLED.name());
      order.setComment("Order has been cancelled");
      orderRepository.save(order);
    }
  }

}
