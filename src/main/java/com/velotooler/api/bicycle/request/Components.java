package com.velotooler.api.bicycle.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@JsonAutoDetect
@Data
public class Components {

    private String name;
    private String brand;
    private String model;
    private Condition condition;
    private boolean certified;

}
