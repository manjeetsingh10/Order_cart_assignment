package com.grofers.ordercart.services;

import com.grofers.ordercart.dto.VehicleDto;
import com.grofers.ordercart.exchanges.OrderRequest;
import com.grofers.ordercart.exchanges.SubmittedOrderRequest;
import com.grofers.ordercart.exchanges.VehicleAssigned;
import com.grofers.ordercart.exchanges.VehicleAssignedResponse;
import com.grofers.ordercart.repositaryservices.RepositoryService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderCartServiceImpl implements OrderCartService{


  @Autowired
  RepositoryService repositoryService;

  // hashset which is updated when an order is assigned with a vehicle.
  private HashSet<Integer> orderComplete;
  final int MAX_WEIGHT_ALLOWED_IN_SLOT = 100;


  /**
   *
   * @param slotKey:
   * @param orderRequest: given list of order details as input from the user
   * @return VehicleAssignedResponse obj, which contains list of vehicles assigned for the given set of orders.
   */
  @Override
  public VehicleAssignedResponse getListOfVehiclesAssigned(Integer slotKey, SubmittedOrderRequest orderRequest) {

    // initialize variables
    VehicleAssignedResponse response = new VehicleAssignedResponse();
    List<VehicleAssigned> vehicleAssignedList = new ArrayList<VehicleAssigned>();
    orderComplete = new HashSet<Integer>();
    int partnerId = 1;

    // get required lists from repository
    List<VehicleDto> availableVehicleList = repositoryService.getListOfVehicles(slotKey);
    List<OrderRequest> costumerOrderList = orderRequest.getSubmittedOrderRequestList();

    // TODO: write a function to get Slot details, like maximum capacity in a slot.

    for (VehicleDto vehicle : availableVehicleList) {
      List<Integer> assignedOrderId = knapsack(vehicle.getMaxWeightAllowed(), costumerOrderList, new ArrayList<Integer>(), 0);
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

  /**
   * APPROACH USING 0/1 knapsack.
   * @param capacityOfVehicle: max weight a vehicle can carry.
   * @param orderList: list of orders given as input from the user.
   * @param orderIdList: List of orderId which have been assigned with a vehicle.
   * @param pointer: to itetrate over the orderlist inorder to fetch details.
   * @return: list containing Order Ids, which have been assigned to the given vehicle.
   */
  private List<Integer> knapsack(Integer capacityOfVehicle, List<OrderRequest> orderList, List<Integer> orderIdList, int pointer) {
    // edge case
    if (capacityOfVehicle == 0 || pointer >= orderList.size()) {
      return orderIdList;
    }

    int orderWeight = getWeightOfOrder(pointer, orderList);

    // check if order weight is within the capacity && order Id not already assigned
    if (orderWeight <= capacityOfVehicle && !checkIfOrderIdPresentInSet(getOrderId(pointer, orderList))) {
      orderIdList.add(getOrderId(pointer, orderList)); // add order id to id list
      orderComplete.add(getOrderId(pointer, orderList)); // add order id to hash set

      //TODO: ROMOVE BELOW LINE
      log.warn("remaining capacity is {}", capacityOfVehicle );
      return knapsack(capacityOfVehicle - orderWeight, orderList, orderIdList, ++pointer);
    }
    else {
      // if order weight is greater than capacity
      return knapsack(capacityOfVehicle, orderList, orderIdList, ++pointer);
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
