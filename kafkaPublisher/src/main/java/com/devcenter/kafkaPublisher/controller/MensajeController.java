package com.devcenter.kafkaPublisher.controller;

import com.devcenter.kafkaPublisher.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/producer")
public class MensajeController {

    @Autowired
    @Qualifier("Sender")
    private Sender sender;

    @PostMapping("/topic/{topic}/publicar/{message}")
    public ResponseEntity<String> publicarMensajeEnTopic(HttpServletRequest request,
                                                 @PathVariable("topic") String topicValue,
                                                   @PathVariable("message") String messageValue) {
        System.out.println("llamamos a publisher con topic:" + topicValue + " y el mensaje: " + messageValue);

        sender.sendMessage2(topicValue, messageValue);

        return new ResponseEntity(HttpStatus.OK);
    }
}
