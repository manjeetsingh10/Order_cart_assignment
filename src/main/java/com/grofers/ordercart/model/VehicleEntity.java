package com.grofers.ordercart.model;

import javax.validation.constraints.NotNull;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
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
