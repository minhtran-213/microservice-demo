package com.example.common.events;

import com.example.common.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompletePaymentEvent {

  private String paymentId;
  private String orderId;
  private String userId;
  private PaymentStatus paymentStatus;
  private String comment;
}
