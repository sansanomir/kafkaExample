package com.devcenter.kafkaPublisher.controller;

//import com.devcenter.kafkaPublisher.producer.Sender;
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

    /*@Autowired
    @Qualifier("Sender")
    private Sender sender;*/

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/predeterminado")
    public ResponseEntity<String> publicarMensajePredeterminadoEnTopic(HttpServletRequest request) {
        //sender.sendMessage2("test2", "Mensaje predeterminado");
        kafkaTemplate.send("test2", "Mensaje predeterminado");
        System.out.println("Predeterminado");
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/topic/{topic}/publicar/{message}")
    public ResponseEntity<String> publicarMensajeEnTopic(HttpServletRequest request,
                                                 @PathVariable("topic") String topicValue,
                                                   @PathVariable("message") String messageValue) {
        System.out.println("llamamos a publisher con topic:" + topicValue + " y el mensaje: " + messageValue);

        //sender.sendMessage2(topicValue, messageValue);
        kafkaTemplate.send(topicValue, messageValue);

        return new ResponseEntity(HttpStatus.OK);
    }

    // http://localhost:8081/producer/filter?topic=test2&messageValue=hola
    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> publicarMensajeEnTopicQuery(HttpServletRequest request,
                @RequestParam(value = "topic", defaultValue = "topic") String topicValue,
                @RequestParam(value = "messageValue", defaultValue = "messageValue") String messageValue) {

        System.out.println("llamamos a publisher con topic:" + topicValue + " y el mensaje: " + messageValue);

        //sender.sendMessage2(topicValue, messageValue);
        kafkaTemplate.send(topicValue, messageValue);

        return new ResponseEntity(HttpStatus.OK);
    }
}
