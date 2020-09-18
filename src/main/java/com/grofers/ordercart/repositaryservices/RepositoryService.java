package com.grofers.ordercart.repositaryservices;

import com.grofers.ordercart.dto.VehicleDto;
import com.grofers.ordercart.exchanges.SubmittedOrderRequest;

import java.util.List;

// get list of vehicles from db, by checking slot
public interface RepositoryService {
  // save vehicle

  // save slot
  void saveOrders(Integer slotKey,  SubmittedOrderRequest submittedOrderRequest);
  List<VehicleDto> getListOfVehicle(Integer slotKey);
}
