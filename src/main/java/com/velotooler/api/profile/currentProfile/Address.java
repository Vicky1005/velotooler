package com.velotooler.api.profile.currentProfile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.velotooler.api.bicycle.customerBike.Bounds;
import com.velotooler.api.bicycle.customerBike.Point;
import lombok.Data;

@JsonAutoDetect
@Data
public class Address {
    private float latitude;
    private float longitude;
    private String address;
    private String building;
    private String street;
    private String city;
    private String county;
    private String state;
    private String country;
    private String googleAddress;
    private String placeId;
    private String zip;
    private Bounds bounds;
    private Point point;
}
