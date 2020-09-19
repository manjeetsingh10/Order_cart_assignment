package com.grofers.ordercart.repositories;

import com.grofers.ordercart.model.VehicleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Creating Mongo Repository for VehicleEntity.
 *
 * NOTE: values for vehicle is pre populated when server starts. the implementation can be found in #PopulateData.java file
 */

@Repository
public interface VehicleRepository extends MongoRepository<VehicleEntity, String > {
}
