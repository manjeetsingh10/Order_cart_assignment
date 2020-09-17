package com.grofers.ordercart.repositories;

import com.grofers.ordercart.model.VehicleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends MongoRepository<VehicleEntity, String > {
}
