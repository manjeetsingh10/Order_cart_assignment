package com.grofers.ordercart.repositaryservices;

import com.grofers.ordercart.dto.VehicleDto;
import com.grofers.ordercart.exchanges.SubmittedOrderRequest;

import java.util.List;

/**
 * Contract of the Repository service available
 *
 */
public interface RepositoryService {
  void saveOrders(Integer slotKey,  SubmittedOrderRequest submittedOrderRequest);
  List<VehicleDto> getListOfVehicles(Integer slotKey);
}
