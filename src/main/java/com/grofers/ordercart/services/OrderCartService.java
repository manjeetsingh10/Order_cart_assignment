package com.grofers.ordercart.services;

import com.grofers.ordercart.exchanges.SubmittedOrderRequest;
import com.grofers.ordercart.exchanges.VehicleAssignedList;

import java.util.List;

/**
 * Service provided by the API.
 */
public interface OrderCartService {
  VehicleAssignedList getListOfVehiclesAssigned(Integer slotKey, SubmittedOrderRequest orderRequest);
}
