package com.example.common.events;

import com.example.common.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCreateEvent {

  private String orderId;
  private String productId;
  private String userId;
  private String quantity;
  private String price;
  private OrderStatus orderStatus;
  private String comment;
}
