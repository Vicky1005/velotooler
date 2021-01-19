package com.velotooler.api.bicycle.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@JsonAutoDetect
@Data
public class Bounds {
    private double south;
    private double west;
    private double north;
    private double east;

}
