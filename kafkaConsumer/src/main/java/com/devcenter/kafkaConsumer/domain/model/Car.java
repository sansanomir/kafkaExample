package com.devcenter.kafkaConsumer.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Car {
    @Id
    //@JsonIgnore
    private String id;
    private String name;
    private String model;
    private String color;
    private Double price;
    private String creationDate;
}
