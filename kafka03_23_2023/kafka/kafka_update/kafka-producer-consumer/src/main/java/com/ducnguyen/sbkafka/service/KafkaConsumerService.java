package com.ducnguyen.sbkafka.service;

import com.ducnguyen.sbkafka.constant.ApplicationConstant;
import com.ducnguyen.sbkafka.entities.Employee;
import com.ducnguyen.sbkafka.repo.EmployeeRepo;
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

    @Autowired
    private EmployeeRepo employeeRepo;

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = ApplicationConstant.TOPIC_NAME_2, groupId = ApplicationConstant.GROUP_ID)
    public void consume(String message){
        logger.info(String.format("Da nhan duoc tin nhan"));
        Gson gson =new Gson();
        Type collectionType = new TypeToken<Collection<Employee>>(){}.getType();
        Collection<Employee> enums = gson.fromJson(message, collectionType);
        enums.forEach(employee ->{
            System.out.println(employee.toString());
            employeeRepo.save(employee);
        });
        logger.info("Da luu thong tin");

    }
}
