package com.velotooler.api.bicycle.customerBike;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.ToString;

@JsonAutoDetect
@Data
@ToString
public class Components {
    private String name;
    private String brand;
    private String model;
    private Condition condition;
    private boolean certified;

}
