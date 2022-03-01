package com.devcenter.kafkaPublisher.domain.converter;

import com.devcenter.kafkaPublisher.domain.model.Car;
import com.devcenter.kafkaPublisher.dto.CreateCarRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CarConverter {
    public Car createCarRequestRequestToCar(CreateCarRequest req) {
        return Car.builder().color(req.getColor())
                .model(req.getModel())
                .name(req.getName())
                .price(req.getPrice())
                .creationDate(LocalDateTime.now().toString())
                .build();
    }
}
