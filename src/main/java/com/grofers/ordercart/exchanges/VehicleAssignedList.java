package com.grofers.ordercart.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAssignedList {
  List<VehicleAssigned> vehicleAssignedList;
}
