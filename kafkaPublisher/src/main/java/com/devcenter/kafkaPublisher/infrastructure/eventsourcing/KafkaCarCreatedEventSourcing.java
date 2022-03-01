package com.devcenter.kafkaPublisher.infrastructure.eventsourcing;

import com.devcenter.kafkaPublisher.domain.model.Car;
import com.devcenter.kafkaPublisher.infrastructure.eventsourcing.events.CarCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
public class KafkaCarCreatedEventSourcing {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.createCar}")
    private String topicName;

    public CarCreatedEvent publicCreatePhoneEvent(Car car) throws JsonProcessingException {
        val id = UUID.randomUUID();
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        val json = objectWriter.writeValueAsString(car);
        log.info("Send json '{}' to topic {}", json, topicName);
        kafkaTemplate.send(topicName, json);
        return CarCreatedEvent.builder().uuid(id).car(car).build();
    }

}
