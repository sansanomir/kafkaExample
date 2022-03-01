package com.devcenter.kafkaConsumer.domain.service;

import com.devcenter.kafkaConsumer.domain.model.Car;
import com.devcenter.kafkaConsumer.infraestructure.repository.CarRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public void createCar(Car c) {
        log.info("Insert new car: {}", c);
        carRepository.save(c);
    }
}
