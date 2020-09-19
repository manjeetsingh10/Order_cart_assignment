package com.grofers.ordercart.repositaryservices;

import com.grofers.ordercart.dto.VehicleDto;
import com.grofers.ordercart.exchanges.SubmittedOrderRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * DUMMY IMPLEMENTATION OF REPOSITORY SERVICE WHICH PROVIDES HARD CODED VALUES.
 *
 */

//@Service
public class RepositoryServiceDummyImpl implements RepositoryService {

  @Override
  public void saveOrders(Integer slotKey, SubmittedOrderRequest submittedOrderRequest) {

  }

  @Override
  public List<VehicleDto> getListOfVehicles(Integer slotKey) {
    List<VehicleDto> returnList = new ArrayList<>();
    int n = 5;
    String[] type = {"bike", "bike", "bike", "scooter", "scooter"};
    int[] maxWeight = {30,30,30,50,50};
    for (int i = 0; i < n; i++) {
      VehicleDto vehicle = VehicleDto.builder()
              .vehicleType(type[i])
              .maxWeightAllowed(maxWeight[i])
              .build();

      returnList.add(vehicle);
    }
    return returnList;
  }
}
