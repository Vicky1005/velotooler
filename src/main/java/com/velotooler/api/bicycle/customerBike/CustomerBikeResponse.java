package com.velotooler.api.bicycle.customerBike;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.List;

@JsonAutoDetect
@Data
public class CustomerBikeResponse {
    private String id;
    private String serialNumber;
    private String createdAt;
    private String updatedAt;
    private String type;
    private String make;
    private String model;
    private String year;
    private FixLocationResponse fixLocation;
    private WheelSize wheelSize;
    private String frameMaterial;
    private String userId;
    private String status;
    private String frameSize;
    private List<ComponentsResponse> components;
    private List<String> colors;
    private List<Object> images;
    private boolean canEdit;
    private FrameConditionResponse frameCondition;
    private int totalCondition;
    private boolean saleCertificate;


}
