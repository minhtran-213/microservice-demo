package com.example.common.dtos;

import com.example.common.enums.ShipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {

  @Id
  private String id;
  private String shipmentId;
  private String orderId;
  private String userId;
  private ShipmentStatus paymentStatus;
  private String comment;
}
