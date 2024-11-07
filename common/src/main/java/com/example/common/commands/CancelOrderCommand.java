package com.example.common.commands;

import com.example.common.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CancelOrderCommand {
  @TargetAggregateIdentifier
  private final String orderId;
  private String orderStatus = OrderStatus.CANCELLED.name();
}
