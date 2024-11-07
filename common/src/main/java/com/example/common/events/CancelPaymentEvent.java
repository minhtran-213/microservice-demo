package com.example.common.events;

import com.example.common.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CancelPaymentEvent {

  private String paymentId;
  private String orderId;
  private String userId;
  private String price;
  private PaymentStatus paymentStatus;
  private String comment;
}
