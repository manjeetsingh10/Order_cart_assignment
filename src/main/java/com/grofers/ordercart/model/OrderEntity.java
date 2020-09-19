package com.grofers.ordercart.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Mongo Document that stores information about order.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("orders")
public class OrderEntity {

  @Id
  private String id;

  @NotNull
  private Integer slotKey;

  @NotNull
  private String orderId;

  @NotNull
  private Integer orderWeight;
}
