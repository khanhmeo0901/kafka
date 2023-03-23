package com.ducnguyen.sbkafka.entities;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name= "Employee")
@Data
public class Employee {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    @CsvBindByPosition(position = 0)
    private String name;

    @Column(name="address")
    @CsvBindByPosition(position = 1)
    private String address;

    @Column(name="phone")
    @CsvBindByPosition(position = 2)
    private String phone;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
