package com.ducnguyen.sbkafka.service;

import com.ducnguyen.sbkafka.config.kafka.topic.KafkaTopicConfig;
import com.ducnguyen.sbkafka.constant.ApplicationConstant;
import com.ducnguyen.sbkafka.entities.Employee;
import com.ducnguyen.sbkafka.util.ExcelUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendDataEmployee(MultipartFile file , String typeTopic)
    {
        try{
            InputStream inputStream = file.getInputStream();
            if(typeTopic.contains("employee"))
            {
                List<Employee> employeeList = ExcelUtil.excelEmployee(inputStream);
                Gson gson = new Gson();
                String jsonData = gson.toJson(employeeList);
                kafkaTemplate.send(ApplicationConstant.TOPIC_EMPLOYEE,jsonData);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void sendMessage(String message) {
       kafkaTemplate.send(ApplicationConstant.TOPIC_EMPLOYEE, message);

    }
}
