package com.devcenter.kafkaPublisher.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCarRequest {
    private String name;
    private String model;
    private String color;
    private Double price;
}
