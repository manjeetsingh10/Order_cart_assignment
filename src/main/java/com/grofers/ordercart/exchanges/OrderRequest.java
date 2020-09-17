package com.grofers.ordercart.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
  private Integer orderId;
  private Integer orderWeight;
}
