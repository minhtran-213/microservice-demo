package com.example.common.commands;

import com.example.common.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompleteOrderCommand {

  @TargetAggregateIdentifier
  private final String orderId;
  private OrderStatus orderStatus = OrderStatus.ORDERED;
}
