package com.ducnguyen.sbkafka.controller;
import com.ducnguyen.sbkafka.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaProducerController {
    private final KafkaProducerService kafkaProducerService;

    public KafkaProducerController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("file") MultipartFile file, @RequestParam String typeTopic) {
        kafkaProducerService.sendDataEmployee(file,typeTopic);
        return ResponseEntity.ok("Message sent to kafka topic");
    }


}
