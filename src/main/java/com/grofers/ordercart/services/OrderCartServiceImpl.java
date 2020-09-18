package com.grofers.ordercart.services;

import com.grofers.ordercart.dto.VehicleDto;
import com.grofers.ordercart.exchanges.OrderRequest;
import com.grofers.ordercart.exchanges.SubmittedOrderRequest;
import com.grofers.ordercart.exchanges.VehicleAssigned;
import com.grofers.ordercart.exchanges.VehicleAssignedList;
import com.grofers.ordercart.repositaryservices.RepositoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Log4j2
public class OrderCartServiceImpl implements OrderCartService{


  @Autowired
  RepositoryService repositoryService;

  // Set that stores the Id of the order, when Vehicle for delivery is assigned.
  private HashSet<Integer> orderComplete;
  final int MAX_WEIGHT_ALLOWED_IN_SLOT = 100;


  @Override
  public VehicleAssignedList getListOfVehiclesAssigned(Integer slotKey, SubmittedOrderRequest orderRequest) {

    // initialize variables
    VehicleAssignedList response = new VehicleAssignedList();
    List<VehicleAssigned> vehicleAssignedList = new ArrayList<VehicleAssigned>();
    orderComplete = new HashSet<Integer>();
    int partnerId = 1;

    // get required lists from repository
    List<VehicleDto> availableVehicleList = repositoryService.getListOfVehicle(slotKey);
    List<OrderRequest> costumerOrderList = orderRequest.getSubmittedOrderRequestList();

    // TODO: write a function to get Slot details, like maximum capacity in a slot.

    for (VehicleDto vehicle : availableVehicleList) {
      List<Integer> assignedOrderId = knapSack(vehicle.getMaxWeightAllowed(), costumerOrderList, new ArrayList<Integer>(), 0);
      // if costumer id list is not empty, this means that the order in the list has been assigned.
      if (assignedOrderId.size() > 0) {
        VehicleAssigned vehicleAssigned = VehicleAssigned.builder()
                .vehicleType(vehicle.getVehicleType())
                .listOrderIdsAssigned(assignedOrderId)
                .deliveryPartnerId(partnerId)
                .build();

        partnerId++;
        vehicleAssignedList.add(vehicleAssigned);
      }

      // check if all the orders has been assigned, if yes, we can break out of the look
      if (orderComplete.size() == costumerOrderList.size()) {
        break;
      }
    }

    response.setVehicleAssignedList(vehicleAssignedList);
    return response;
  }

  private List<Integer> knapSack(Integer capacityOfVehicle, List<OrderRequest> orderList, List<Integer> costumerIdList, int pointer) {
    // edge case
    if (capacityOfVehicle == 0 || pointer >= orderList.size()) {
      return costumerIdList;
    }

    int orderWeight = getWeightOfOrder(pointer, orderList);

    // check if order weight is within the capacity && order Id not already assigned
    if (orderWeight <= capacityOfVehicle && !checkIfOrderIdPresentInSet(getOrderId(pointer, orderList))) {
      costumerIdList.add(getOrderId(pointer, orderList)); // add order id to id list
      orderComplete.add(getOrderId(pointer, orderList)); // add order id to hash set

      //TODO: ROMOVE BELOW LINE
      log.warn("remaining capacity is {}", capacityOfVehicle );
      return knapSack(capacityOfVehicle - orderWeight, orderList, costumerIdList, ++pointer);
    }
    else {
      // if order weight is greater than capacity
      return knapSack(capacityOfVehicle, orderList, costumerIdList, ++pointer);
    }

  }



  // HELPER FUNCTIONS

  /**
   *
   * @param orderId: id of given order
   * @return: True if present, else False.
   */
  private boolean checkIfOrderIdPresentInSet(Integer orderId) {
    if (orderComplete.contains(orderId)) {
      return true;
    }
    return false;
  }

  /**
   *
   * @param index: index of order in the given list
   * @param orderList: given order list as input in the request
   * @return: ID of order persent at the given index.
   */
  private int getOrderId(Integer index, List<OrderRequest> orderList) {
    return orderList.get(index).getOrderId();
  }

  /**
   *
   * @param index: index of order in the given list.
   * @param orderList: given order list as input in the request.
   * @return: weight of the order at the given index.
   */
  private int getWeightOfOrder(Integer index, List<OrderRequest> orderList) {
    return orderList.get(index).getOrderWeight();
  }
}
