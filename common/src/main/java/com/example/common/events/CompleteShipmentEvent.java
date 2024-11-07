package com.example.common.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompleteShipmentEvent {

  private String shipmentId;
  private String paymentId;
  private String orderId;
  private String userId;
  private String shipmentStatus;
  private String comment;

}
