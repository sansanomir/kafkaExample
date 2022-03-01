package com.devcenter.kafkaPublisher.controller;

//import com.devcenter.kafkaPublisher.producer.Sender;
import com.devcenter.kafkaPublisher.domain.service.CreateCarService;
import com.devcenter.kafkaPublisher.dto.CreateCarRequest;
import com.devcenter.kafkaPublisher.infrastructure.eventsourcing.events.CarCreatedEvent;
import org.apache.kafka.clients.producer.internals.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/producer")
public class MensajeController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private CreateCarService createCarService;

    @PostMapping("/topic/test2/predeterminado")
    public ResponseEntity<String> publicarMensajeEnTopicTest2(HttpServletRequest request,
                                                                       @PathVariable("message") String messageValue) {
        System.out.println("llamamos a publisher con topic: test2 y el mensaje: " + messageValue);

        kafkaTemplate.send("test2", messageValue);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/topic/twitter/{message}")
    public ResponseEntity<String> publicarMensajeEnTopicTwitter(HttpServletRequest request,
                                                         @PathVariable("message") String messageValue) {
        System.out.println("llamamos a publisher con topic: twitter y el mensaje: " + messageValue);

        kafkaTemplate.send("twitter", messageValue);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/topic/test2/body")
    public CarCreatedEvent publicarObjetoEnTopicTest2(HttpServletRequest request,
                                                      @RequestBody CreateCarRequest req) {
        System.out.println("llamamos a publisher con topic: test2 y el body: " + req);
        return createCarService.create(req);
    }

    // http://localhost:8081/producer/filter?topic=test2&messageValue=hola
    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> publicarMensajeEnTopicQuery(HttpServletRequest request,
                @RequestParam(value = "topic", defaultValue = "topic") String topicValue,
                @RequestParam(value = "messageValue", defaultValue = "messageValue") String messageValue) {

        System.out.println("llamamos a publisher con topic:" + topicValue + " y el mensaje: " + messageValue);

        kafkaTemplate.send(topicValue, messageValue);

        return new ResponseEntity(HttpStatus.OK);
    }
}
