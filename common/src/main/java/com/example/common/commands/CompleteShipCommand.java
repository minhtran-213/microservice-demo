package com.example.common.commands;

import com.example.common.enums.ShipmentStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CompleteShipCommand {

  @TargetAggregateIdentifier
  private final String shipmentId;
  private String orderId;
  private String userId;
  private String paymentId;
  private String shipmentStatus = ShipmentStatus.COMPLETE.name();

}
