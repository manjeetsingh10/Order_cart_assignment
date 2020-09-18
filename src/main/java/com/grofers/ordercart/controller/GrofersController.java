package com.grofers.ordercart.controller;

import com.grofers.ordercart.exchanges.SubmittedOrderRequest;
import com.grofers.ordercart.exchanges.VehicleAssignedList;
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

  // write post mapping
  @PostMapping("/{slotKey}")
  public ResponseEntity<VehicleAssignedList> getListOfAssignedVehicles(
          @PathVariable Integer slotKey,
          @RequestBody SubmittedOrderRequest submittedRequest) {


    // TODO: change below statement
    return ResponseEntity.ok().body(cartService.getListOfVehiclesAssigned(slotKey, submittedRequest));
  }


}