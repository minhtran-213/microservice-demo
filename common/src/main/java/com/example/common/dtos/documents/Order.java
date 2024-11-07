package com.example.common.dtos.documents;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "order")
@Data
@NoArgsConstructor
public class Order {

  @Id
  private String id;

  private String orderId;
  private String productId;
  private String userId;
  private String quantity;
  private String price;
  private String orderStatus;
  private String comment;
}
