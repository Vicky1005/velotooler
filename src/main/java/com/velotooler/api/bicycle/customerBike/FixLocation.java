package com.velotooler.api.bicycle.customerBike;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.ToString;

@JsonAutoDetect
@Data
@ToString
public class FixLocation {
    private String address;
    private String placeId;
    private double longitude;
    private double latitude;
    private String googleAddress;
    private String country;
    private String city;
    private String county;
    private String state;
    private String street;
    private String building;
    private String zip;
    private String apartment;
    private Bounds bounds;
}
