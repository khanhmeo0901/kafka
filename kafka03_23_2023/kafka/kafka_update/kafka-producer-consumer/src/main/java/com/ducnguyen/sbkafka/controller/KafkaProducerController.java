package com.ducnguyen.sbkafka.controller;

import com.ducnguyen.sbkafka.entities.Employee;
import com.ducnguyen.sbkafka.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;


    public KafkaProducerController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        kafkaProducerService.sendMessage(message);
        return ResponseEntity.ok("Message sent to kafka topic");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody String message){
        kafkaProducerService.sendMessage(message);
        return ResponseEntity.ok("Message sent to kafka topic");
    }
}
