package com.grofers.ordercart.exchanges;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class, which contains information about the vehicle, and the Order it is going to deliver.
 * NOTE: variables named in JAVA convection i.e camel case.
 *  {
 *
 *    "vehicle_type": "bike",
 *
 *    "delivery_partner_id": 2,
 *
 *    "list_order_ids_assigned": [2, 3]
 *
 *  }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleAssigned {
  private String vehicleType;
  private Integer deliveryPartnerId;
  List<Integer> listOrderIdsAssigned;
}


