package com.grofers.ordercart.exchanges;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmittedOrderRequest {
  @NotNull
  List<OrderRequest> submittedOrderRequestList;
}
