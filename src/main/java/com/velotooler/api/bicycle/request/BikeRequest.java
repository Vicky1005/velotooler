package com.velotooler.api.bicycle.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.List;

@JsonAutoDetect
@Data
public class BikeRequest {
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
