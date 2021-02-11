package com.velotooler.api.profile.currentProfile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;


import java.util.List;

@JsonAutoDetect
@Data
public class CurrentProfileResponse {
    private String id;
    private String name;
    private String surname;
    private String email;
    private List<Locations> locations;
    private String phone;
    private String role;
    private List<Object> images;
    private boolean blocked;
    private boolean verified;
    private boolean activated;
    private boolean acceptAgreement;
    private String signUpType;
    private boolean shouldPay;
    private  boolean racer;




}
