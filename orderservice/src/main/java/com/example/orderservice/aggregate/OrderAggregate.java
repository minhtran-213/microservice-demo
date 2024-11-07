package com.example.orderservice.aggregate;

import com.example.common.commands.CancelOrderCommand;
import com.example.common.commands.CompleteOrderCommand;
import com.example.common.commands.CreateOrderCommand;
import com.example.common.enums.OrderStatus;
import com.example.common.events.CancelOrderEvent;
import com.example.common.events.CompleteOrderEvent;
import com.example.common.events.OrderCreateEvent;
import com.example.orderservice.handler.OrderEventHandler;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

@Aggregate
public class OrderAggregate {

  @AggregateIdentifier
  private String orderId;

  @Autowired
  private OrderEventHandler orderEventHandler;

  public OrderAggregate() {
  }

  @CommandHandler
  public OrderAggregate(CreateOrderCommand createOrderCommand) {
    OrderCreateEvent event = OrderCreateEvent.builder()
        .orderId(createOrderCommand.getOrderId())
        .productId(createOrderCommand.getProductId())
        .price(createOrderCommand.getPrice())
        .quantity(createOrderCommand.getQuantity())
        .userId(createOrderCommand.getUserId())
        .orderStatus(createOrderCommand.getOrderStatus())
        .build();

    AggregateLifecycle.apply(event);
  }

  @CommandHandler
  public void handle(CompleteOrderCommand command) {
    CompleteOrderEvent event = CompleteOrderEvent.builder()
        .orderStatus(command.getOrderStatus())
        .orderId(command.getOrderId())
        .build();

    AggregateLifecycle.apply(event);
  }

  @CommandHandler
  public void handle(CancelOrderCommand command) {
    CancelOrderEvent event = CancelOrderEvent.builder()
        .orderId(command.getOrderId())
        .orderStatus(OrderStatus.CANCELLED)
        .build();
    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(CompleteOrderEvent event) {
    this.orderId = event.getOrderId();
    orderEventHandler.onCompleteOrderEvent(event);
  }

  @EventSourcingHandler
  public void on(OrderCreateEvent event) {
    this.orderId = event.getOrderId();
    orderEventHandler.onOrderCreateEvent(event);
  }

  @EventSourcingHandler
  public void on(CancelOrderEvent event) {
    this.orderId = event.getOrderId();
    orderEventHandler.onCancelOrderEvent(event);
  }


}
