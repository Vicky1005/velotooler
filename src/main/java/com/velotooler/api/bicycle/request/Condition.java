package com.velotooler.api.bicycle.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.ToString;

@JsonAutoDetect
@Data
@ToString
public class Condition {
    private int rate;
}
