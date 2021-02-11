package com.velotooler.api.profile.currentProfile;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@JsonAutoDetect
@Data
public class Locations {
    private String id;
    private Address address;
    private String type;
    private String description;


}
