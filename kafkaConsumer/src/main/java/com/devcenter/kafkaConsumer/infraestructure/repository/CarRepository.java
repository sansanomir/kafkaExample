package com.devcenter.kafkaConsumer.infraestructure.repository;

import com.devcenter.kafkaConsumer.domain.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends MongoRepository<Car, Integer> {
    Car save(Car c);
}
