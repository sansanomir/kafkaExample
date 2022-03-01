package com.devcenter.kafkaPublisher.infrastructure.eventsourcing.events;

import com.devcenter.kafkaPublisher.domain.model.Car;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class CarCreatedEvent {
    private UUID uuid;
    private Car car;
}
