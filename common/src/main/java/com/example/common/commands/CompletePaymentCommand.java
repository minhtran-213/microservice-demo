package com.example.common.commands;

import com.example.common.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompletePaymentCommand {

  @TargetAggregateIdentifier
  private final String paymentId;
  private String orderId;
  private String userId;
  private String price;
  private String paymentStatus = PaymentStatus.COMPLETE.name();
}