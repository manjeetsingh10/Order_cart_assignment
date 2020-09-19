package com.grofers.ordercart;

import com.grofers.ordercart.model.VehicleEntity;
import com.grofers.ordercart.repositories.VehicleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to pre populate the data given in Problem Statement to MongoDB.
 * Given slots are represented with integers. So the 4 given slots are mapped with the following numbers
 *  1 ==> [6,9] Slot
 *  2 ==> [9,13] Slot
 *  3 ==> [16,19] Slot
 *  4 ==> [19, 23] Slot
 */

@Component
@Log4j2
public class PopulateData implements CommandLineRunner {

  @Autowired
  VehicleRepository vehicleRepository;

  // define constants given in problem statement.
  private final int GIVEN_NUMBER_OF_BIKES = 3;
  private final int MAX_WEIGHT_OF_BIKE = 30;

  private final int GIVEN_NUMBER_OF_SCOOTERS = 2;
  private final int MAX_WEIGHT_OF_SCOOTER = 50;

  private final int GIVEN_NUMBER_OF_TRUCKS = 1;
  private final int MAX_WEIGHT_OF_TRUCK = 100;

  @Override
  public void run(String... args) throws Exception {
    // delete existing record
    vehicleRepository.deleteAll();

    List<VehicleEntity> givenListOfVehicles = getGivenSetOfVehicleList();
    for (VehicleEntity vehicle : givenListOfVehicles) {
      vehicleRepository.save(vehicle);
    }

    // print records present in db on the log.
    log.warn("Total Vehicles in Vehicle Repository {}", givenListOfVehicles.size());
    vehicleRepository.findAll().forEach((vehicle) -> {
      log.warn("{}", vehicle);
    });

  }

  /** HELPER FUNCTION
   *
   * @return: list of total vehicles in the  Problem Statement.
   */
  private List<VehicleEntity> getGivenSetOfVehicleList() {
    List<VehicleEntity> returnList = new ArrayList<VehicleEntity>();

    // add bike to the list
    List<Integer> bikeAvailabeSlotsList = new ArrayList<>(Arrays.asList(1,2,3));
    for (int i = 0; i < GIVEN_NUMBER_OF_BIKES; i ++) {
      VehicleEntity bike = VehicleEntity.builder()
              .vehicleType("bike")
              .maxWeightAllowed(MAX_WEIGHT_OF_BIKE)
              .availableSlots(bikeAvailabeSlotsList)
              .build();
      returnList.add(bike);
    }

    // add scooter
    List<Integer> scooterAvailableSlotsList = new ArrayList<>(Arrays.asList(1,2,3));
    for (int i = 0; i < GIVEN_NUMBER_OF_SCOOTERS; i ++) {
      VehicleEntity scooter = VehicleEntity.builder()
              .vehicleType("scooter")
              .maxWeightAllowed(MAX_WEIGHT_OF_SCOOTER)
              .availableSlots(scooterAvailableSlotsList)
              .build();
      returnList.add(scooter);
    }

    // add truck
    List<Integer> turckAvailableSlotsList = new ArrayList<>(Arrays.asList(2,3,4));
    for (int i = 0; i < GIVEN_NUMBER_OF_TRUCKS; i ++) {
      VehicleEntity truck = VehicleEntity.builder()
              .vehicleType("truck")
              .maxWeightAllowed(MAX_WEIGHT_OF_TRUCK)
              .availableSlots(turckAvailableSlotsList)
              .build();
      returnList.add(truck);
    }

    return returnList;
  }
}
