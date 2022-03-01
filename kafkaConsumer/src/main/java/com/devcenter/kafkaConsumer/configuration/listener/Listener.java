package com.devcenter.kafkaConsumer.configuration.listener;

import com.devcenter.kafkaConsumer.domain.model.Car;
import com.devcenter.kafkaConsumer.domain.service.CarService;
import com.devcenter.kafkaConsumer.infraestructure.repository.CarRepository;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@EnableKafka
@Service
@Log4j2
@Component
public class Listener {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    private final CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "test2", groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo, topic test2: " + message);
    }

    @KafkaListener(topics = "twitter", groupId = "foo")
    public void listenTopicTwitter(String message) {
        System.out.println("Received Message in group foo, topic twitter: " + message);
    }

    @KafkaListener(topics = "create-car", groupId = "foo")
    public void listenTopicCreateCar(ConsumerRecord<String, String> stringStringConsumerRecord) {
        System.out.println("Received Message in group foo, topic create-car: " + stringStringConsumerRecord);
        Car car = new Gson().fromJson(stringStringConsumerRecord.value(), Car.class);
        carService.createCar(car);
        log.info("Insert car {} in reader database", car);
        latch.countDown();
    }
}
