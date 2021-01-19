package com.velotooler.api.bicycle.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@JsonAutoDetect
@Data
public class Condition {
    private int rate;
}
