package com.ducnguyen.sbkafka.service;

import com.ducnguyen.sbkafka.constant.ApplicationConstant;
import com.ducnguyen.sbkafka.entities.Employee;
import com.ducnguyen.sbkafka.repo.EmployeeRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class KafkaConsumerService {


    private EmployeeRepo employeeRepo;
    public KafkaConsumerService(EmployeeRepo employeeRepo)
    {
        this.employeeRepo = employeeRepo;
    }

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = ApplicationConstant.TOPIC_EMPLOYEE, groupId = ApplicationConstant.GROUP_ID)
    public void consumeEmployee(String message) throws JsonProcessingException {
        logger.info(String.format("Da nhan duoc tin nhan"));
        ObjectMapper mapper = new ObjectMapper();
        List<Employee> employeeList = mapper.readValue(message, new TypeReference<List<Employee>>() {});
        employeeRepo.saveAll(employeeList);
        logger.info("================= Output message =========================");
        logger.info(message);
        logger.info("=====================End Output ==========================");
    }
}
