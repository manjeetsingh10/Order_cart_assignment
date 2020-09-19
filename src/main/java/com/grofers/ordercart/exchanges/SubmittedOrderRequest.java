package com.grofers.ordercart.exchanges;

import javax.validation.constraints.NotNull;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * POJO class that contains list of Submitted order details in the request.
 * NOTE: variables named in JAVA convection i.e camel case.
 *
 * {
 *     "submittedOrderRequestList": [
 *         {
 *             "orderId" : "1",
 *             "orderWeight": "30"
 *         },
 *          {
 *             "orderId" : "2",
 *             "orderWeight": "10"
 *         },
 *          {
 *             "orderId" : "3",
 *             "orderWeight": "15"
 *         },
 *          {
 *             "orderId" : "4",
 *             "orderWeight": "60"
 *         }
 *     ]
 * }
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmittedOrderRequest {
  @NotNull
  List<OrderRequest> submittedOrderRequestList;
}
