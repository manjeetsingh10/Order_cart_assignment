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
  private HashSet<Integer> orderAssigned;
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
    orderAssigned = new HashSet<Integer>();
    int partnerId = 1;

    // get required lists from repository
    List<VehicleDto> availableVehicleList = repositoryService.getListOfVehicles(slotKey);
    List<OrderRequest> costumerOrderList = orderRequest.getSubmittedOrderRequestList();

    // iterate for each vehicle and see if it can deliver the order
    for (VehicleDto vehicle : availableVehicleList) {
      List<Integer> assignedOrderIdList = knapsack(vehicle.getMaxWeightAllowed(), costumerOrderList, new ArrayList<Integer>(), 0);

      // if #assignedOrderList is not empty, that the vehicle has been assigned with some orders.
      if (assignedOrderIdList.size() > 0) {
        VehicleAssigned vehicleAssigned = VehicleAssigned.builder()
                .vehicleType(vehicle.getVehicleType())
                .listOrderIdsAssigned(assignedOrderIdList)
                .deliveryPartnerId(partnerId)
                .build();

        partnerId++;
        vehicleAssignedList.add(vehicleAssigned);
      }

      // check if all the orders has been assigned, if yes, we can break out of the loop.
      if (orderAssigned.size() == costumerOrderList.size()) {
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

    // check if order weight is within the capacity && order Id not already assigned to a vehicle
    if (orderWeight <= capacityOfVehicle && !checkIfOrderIdPresentInSet(getOrderId(pointer, orderList))) {
      orderIdList.add(getOrderId(pointer, orderList)); // add order id to id list
      orderAssigned.add(getOrderId(pointer, orderList)); // add order id to hash set

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
   * @return: True if orderId present in #orderAssigned HashSet, else False.
   */
  private boolean checkIfOrderIdPresentInSet(Integer orderId) {
    if (orderAssigned.contains(orderId)) {
      return true;
    }
    return false;
  }

  /**
   *
   * @param index: index of order in the given list
   * @param orderList: given order list as input in POST request
   * @return: orderId of order present at the given index.
   */
  private int getOrderId(Integer index, List<OrderRequest> orderList) {
    return orderList.get(index).getOrderId();
  }

  /**
   *
   * @param index: index of order in the given list.
   * @param orderList: given order list as input in POST request.
   * @return: weight of the order present at the given index.
   */
  private int getWeightOfOrder(Integer index, List<OrderRequest> orderList) {
    return orderList.get(index).getOrderWeight();
  }
}
