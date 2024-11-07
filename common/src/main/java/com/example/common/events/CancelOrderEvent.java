package com.example.common.events;

import com.example.common.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CancelOrderEvent {
  private String orderId;
  private OrderStatus orderStatus;

}
