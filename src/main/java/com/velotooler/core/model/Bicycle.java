package com.velotooler.core.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Bicycle {
    private String brand;
    private String model;
    private String year;
    private String location;
}
