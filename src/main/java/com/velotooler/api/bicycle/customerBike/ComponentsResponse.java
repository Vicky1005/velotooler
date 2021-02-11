package com.velotooler.api.bicycle.customerBike;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@JsonAutoDetect
@Data
public class ComponentsResponse {
    private String name;
    private String brand;
    private String model;
    private boolean certified;
}
