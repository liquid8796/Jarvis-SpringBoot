package com.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userservice.dto.UserDTO;
import com.userservice.entity.User;
import com.userservice.kafka.KafkaProducerWithKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducerWithKey kafkaProducerWithKey;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(KafkaController.class.getSimpleName());

    @PostMapping("/message")
    public String sendMessage(@RequestParam String topic, @RequestBody(required = false) Object object) {
        try {
            //kafkaTemplate.send(topic, object);
            //kafkaProducerDemo.sendMessage(topic, object);
            //KafkaProducerWithCallback.sendMessage(topic, object);
            UserDTO dto = objectMapper.convertValue(object, UserDTO.class);
            kafkaProducerWithKey.sendMessage(topic, dto.getUser_id(), object);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Json message sent successfully!";
    }
}
