package com.grofers.ordercart.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAssigned {
  private String vehicleType;
  private Integer deliveryPartnerId;
  List<Integer> listOrderIdsAssigned;
}


// {
//
//         "vehicle_type": "bike",
//
//         "delivery_partner_id": 2,
//
//         "list_order_ids_assigned": [2, 3]
//
//         }