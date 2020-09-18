package com.grofers.ordercart.repositories;

import com.grofers.ordercart.dto.VehicleDto;
import com.grofers.ordercart.model.VehicleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends MongoRepository<VehicleEntity, String > {
  List<VehicleDto> findByAvailableSlots(Integer slotKey);
}
