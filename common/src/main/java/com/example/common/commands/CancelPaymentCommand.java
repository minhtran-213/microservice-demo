package com.example.common.commands;

import com.example.common.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CancelPaymentCommand {

  private final String paymentId;
  private String orderId;
  private String userId;
  private String price;
  private String paymentStatus = PaymentStatus.CANCELLED.name();
}
