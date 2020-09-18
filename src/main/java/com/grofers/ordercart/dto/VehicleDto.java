package com.grofers.ordercart.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
