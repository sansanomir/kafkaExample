package com.devcenter.kafkaPublisher.infrastructure.repository;

import com.devcenter.kafkaPublisher.domain.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car, Integer> {
}
