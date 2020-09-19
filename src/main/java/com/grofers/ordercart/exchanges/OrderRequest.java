package com.grofers.ordercart.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class to contain order information sent in the request
 * NOTE: variables named in JAVA convection i.e camel case.
 *
 * {
 *   "orderId" : "1",
 *   "orderWeight": "30"
 * }
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
  private Integer orderId;
  private Integer orderWeight;
}
