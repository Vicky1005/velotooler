package com.velotooler.api.bicycle.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@JsonAutoDetect
@Data
public class WheelSize {
    private String inch;
    private String iso;
    private String bicycleStandard;

}
