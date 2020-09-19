package com.grofers.ordercart.repositaryservices;

import com.grofers.ordercart.dto.VehicleDto;
import com.grofers.ordercart.exchanges.SubmittedOrderRequest;
import com.grofers.ordercart.model.VehicleEntity;
import com.grofers.ordercart.repositories.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * IMPLEMENTATION OF REPOSITORY SERVICE
 *
 * NOTE: saveOrders function is not implemented, but can be done in future requirements inorder to get #Order details
 */
@Service
public class RepositoryServiceImpl implements RepositoryService{

  @Autowired
  VehicleRepository vehicleRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public void saveOrders(Integer slotKey, SubmittedOrderRequest submittedOrderRequest) {

  }

  /**
   *
   * @param slotKey: 1 : [6, 9], 2:[9, 3], 3:[16, 19], 4:[19, 23]
   * @return list of available vehicles at the given slot number
   */
  @Override
  public List<VehicleDto> getListOfVehicles(Integer slotKey) {
    List<VehicleDto> availableVehiclesAtGivenSlot = new ArrayList<VehicleDto>();
    for (VehicleEntity vehicleEntity : vehicleRepository.findAll()) {
      // check if slot present in #availabelSlotList in the entity obj
      if (isSlotPresent(slotKey, vehicleEntity)) {
        availableVehiclesAtGivenSlot.add(modelMapper(vehicleEntity));
      }
    }
    return availableVehiclesAtGivenSlot;
  }

  /**
   *
   * @param slotKey: Slot number
   * @param vehicle: vehicle entity object
   * @return: true is vehicle available at the given slot
   */
  private boolean isSlotPresent(Integer slotKey, VehicleEntity vehicle) {
    if (vehicle.getAvailableSlots().contains(slotKey)) {
      return true;
    }
    return false;
  }

  /**
   *
   * @param vehicleEntity: entity object
   * @return: converts entity object to Dto object.
   */
  private VehicleDto modelMapper(VehicleEntity vehicleEntity) {
    return modelMapper.map(vehicleEntity, VehicleDto.class);
  }
}
