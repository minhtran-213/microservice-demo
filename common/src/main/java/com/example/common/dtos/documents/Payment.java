package com.example.common.dtos.documents;

import com.example.common.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

  @Id
  private String id;
  private String paymentId;
  private String orderId;
  private String userId;
  private String price;
  private PaymentStatus paymentStatus;
  private String comment;

}
