package com.grofers.ordercart.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Mongo Document that stores information about slots.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "slots")
public class SlotEntity {

  @Id
  private String id;

  @NotNull
  private Integer slotKey;

  @NotNull
  private Integer startTime;

  @NotNull
  private Integer endTime;
}
