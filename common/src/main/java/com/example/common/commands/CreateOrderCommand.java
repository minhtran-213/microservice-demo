package com.example.common.commands;

import com.example.common.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateOrderCommand {

  @TargetAggregateIdentifier
  private final String orderId;
  private String productId;
  private String userId;
  private String quantity;
  private String price;
  private OrderStatus orderStatus;
}
