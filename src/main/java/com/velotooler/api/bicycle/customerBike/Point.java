package com.velotooler.api.bicycle.customerBike;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.List;

@JsonAutoDetect
@Data
public class Point {
    private float x;
    private float y;
    private String type;
    private List<Float> coordinates;

}
