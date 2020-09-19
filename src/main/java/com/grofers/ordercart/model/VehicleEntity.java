package com.grofers.ordercart.model;

import javax.validation.constraints.NotNull;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Mongo Document that stores information about vehicles.
 *
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "vehicles")
public class VehicleEntity {

  @Id
  private String id;

  @NotNull
  private String vehicleType;

  @NotNull
  private Integer maxWeightAllowed;

  @NotNull
  private List<Integer> availableSlots;
}
