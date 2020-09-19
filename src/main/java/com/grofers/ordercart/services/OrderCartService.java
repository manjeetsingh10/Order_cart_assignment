package com.grofers.ordercart.services;

import com.grofers.ordercart.exchanges.SubmittedOrderRequest;
import com.grofers.ordercart.exchanges.VehicleAssignedResponse;

/**
 * Service provided by the API.
 */
public interface OrderCartService {
  VehicleAssignedResponse getListOfVehiclesAssigned(Integer slotKey, SubmittedOrderRequest orderRequest);
}
