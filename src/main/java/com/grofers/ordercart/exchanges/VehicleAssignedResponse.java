package com.grofers.ordercart.exchanges;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/** POJO class
 * Response returned for POST request in the following format
 * NOTE: variables named in JAVA convection i.e camel case.
 * {
 *     "vehicleAssignedList": [
 *         {
 *             "vehicleType": "bike",
 *             "deliveryPartnerId": 1,
 *             "listOrderIdsAssigned": [
 *                 1
 *             ]
 *         },
 *         {
 *             "vehicleType": "bike",
 *             "deliveryPartnerId": 2,
 *             "listOrderIdsAssigned": [
 *                 2,
 *                 3,
 *                 4
 *             ]
 *         }
 *     ]
 * }
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAssignedResponse {
  List<VehicleAssigned> vehicleAssignedList;
}
