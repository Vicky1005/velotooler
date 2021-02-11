package com.velotooler.api.bicycle.customerBike;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@JsonAutoDetect
@ToString
@Data
public class CustomerBikeRequest {
    private List<String> colors;
    private List<Components> components;
    private FixLocation fixLocation;
    private String serialNumber;
    private String type;
    private String model;
    private String year;
    private String make;
    private WheelSize wheelSize;
    private String frameMaterial;
    private String frameSize;
    private FrameCondition frameCondition;
    private List<Object> images;
}
