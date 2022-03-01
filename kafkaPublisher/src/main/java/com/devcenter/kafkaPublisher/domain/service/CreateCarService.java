package com.devcenter.kafkaPublisher.domain.service;

import com.devcenter.kafkaPublisher.domain.converter.CarConverter;
import com.devcenter.kafkaPublisher.dto.CreateCarRequest;
import com.devcenter.kafkaPublisher.infrastructure.eventsourcing.KafkaCarCreatedEventSourcing;
import com.devcenter.kafkaPublisher.infrastructure.eventsourcing.events.CarCreatedEvent;

import com.devcenter.kafkaPublisher.infrastructure.repository.CarRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CreateCarService {

    @Autowired
    private CarConverter carConverter;
    //@Autowired
    //private CarRepository carRepository;
    @Autowired
    private KafkaCarCreatedEventSourcing kafkaCarCreatedEventSourcing;

    public CarCreatedEvent create(CreateCarRequest request) {
        log.info("Creating new phone");
        val car = carConverter.createCarRequestRequestToCar(request);
        //esto lo hará el consumer carRepository.save(car);
        try {
            return kafkaCarCreatedEventSourcing.publicCreatePhoneEvent(car);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
