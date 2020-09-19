package com.grofers.ordercart.controller;

import com.grofers.ordercart.exchanges.SubmittedOrderRequest;
import com.grofers.ordercart.exchanges.VehicleAssignedResponse;
import com.grofers.ordercart.services.OrderCartService;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(GrofersController.ORDER_ENDPOINT)
@RestController
@Log4j2
public class GrofersController {
  public static final String ORDER_ENDPOINT = "/cart";

  @Autowired
  OrderCartService cartService;

  /**
   *
   * @param slotKey: slot for which vehicles need to be assigned.
   * @param submittedRequest: list of order details submitted.
   * @return: vehicles assigned at a given slot.
   */
  @PostMapping("/{slotKey}")
  public ResponseEntity<VehicleAssignedResponse> getListOfAssignedVehicles(
          @PathVariable Integer slotKey,
          @RequestBody SubmittedOrderRequest submittedRequest) {
    VehicleAssignedResponse assignedVehicles = cartService.getListOfVehiclesAssigned(slotKey, submittedRequest);

    if (assignedVehicles == null){
      return ResponseEntity.badRequest().body(null);
    }
    return ResponseEntity.ok().body(cartService.getListOfVehiclesAssigned(slotKey, submittedRequest));
  }


}