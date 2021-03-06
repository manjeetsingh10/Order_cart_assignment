package com.grofers.ordercart.repositories;

import com.grofers.ordercart.model.SlotEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Creating Mongo Repository for SlotEntity.
 */

@Repository
public interface SlotRepository extends MongoRepository<SlotEntity, String> {
}
