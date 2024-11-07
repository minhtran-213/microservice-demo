package com.example.orderservice.saga;

import com.example.common.commands.CancelOrderCommand;
import com.example.common.commands.CancelPaymentCommand;
import com.example.common.commands.CompletePaymentCommand;
import com.example.common.commands.CompleteShipCommand;
import com.example.common.enums.PaymentStatus;
import com.example.common.events.CancelOrderEvent;
import com.example.common.events.CompleteOrderEvent;
import com.example.common.events.CompletePaymentEvent;
import com.example.common.events.OrderCreateEvent;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class OrderSystemSaga {
  @Autowired
  private transient CommandGateway commandGateway;

  public OrderSystemSaga() {
  }

  @StartSaga
  @SagaEventHandler(associationProperty = "orderId")
  private void handle(OrderCreateEvent event) {
    String paymentId = UUID.randomUUID().toString();
    SagaLifecycle.associateWith("paymentId", paymentId);
    CompletePaymentCommand paymentCommand = CompletePaymentCommand.builder()
        .orderId(event.getOrderId())
        .paymentId(paymentId)
        .price(event.getPrice())
        .userId(event.getUserId())
        .build();
    commandGateway.send(paymentCommand);
  }

  @StartSaga
  @SagaEventHandler(associationProperty = "paymentId")
  private void handle(CompletePaymentEvent event) {
    if (event.getPaymentStatus().equals(PaymentStatus.ERROR)) {
      this.cancelOrderCommand(event.getOrderId());
    } else {
      String shipmentId = UUID.randomUUID().toString();
      SagaLifecycle.associateWith("shipmentId", shipmentId);
      CompleteShipCommand shipCommand = CompleteShipCommand.builder()
          .orderId(event.getOrderId())
          .shipmentId(shipmentId)
          .paymentId(event.getPaymentId())
          .userId(event.getUserId())
          .build();
      commandGateway.send(shipCommand);
    }
  }

  @SagaEventHandler(associationProperty = "orderId")
  public void handle(CancelPaymentCommand event) {
    cancelOrderCommand(event.getOrderId());
  }

  @SagaEventHandler(associationProperty = "orderId")
  @EndSaga
  public void handle(CompleteOrderEvent event) {
    log.info("Complete order event completed");
  }

  @SagaEventHandler(associationProperty = "orderId")
  @EndSaga
  public void handle(CancelOrderEvent event) {
    //Handle Event if necessary
    log.info("Order cancelled completed");
  }

  private void cancelOrderCommand(String orderId) {
    CancelOrderCommand orderCommand = CancelOrderCommand.builder()
        .orderId(orderId)
        .build();
    commandGateway.send(orderCommand);
  }
}
