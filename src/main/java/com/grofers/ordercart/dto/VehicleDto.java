package com.grofers.ordercart.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * POJO Class
 * NOTE: 1) variables named in JAVA convection i.e camel case.
 *       2) Given slots in problem statement are mapped with Integers [1,2,3,4]
 *       for example:
 *       1 => [6, 9] slot
 *       2 => [9, 13] slot
 *       3 => [16, 19] slot
 *       4 => [19, 23] slot
 *
 * {
 *   "vehicleType" : "bike",
 *   "maxWeightAllowed" : "30",
 *   "availableSlots" : [1, 2 ,3]
 * }
 *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDto {
  private String vehicleType;
  private Integer maxWeightAllowed;

  @JsonIgnore
  private List<Integer> availableSlots;
}
