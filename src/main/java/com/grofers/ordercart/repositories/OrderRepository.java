package com.grofers.ordercart.repositories;

import com.grofers.ordercart.model.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Creating Mongo Repository for OrderEntity.
 */

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {
}
