package com.velotooler.core.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Auth {
    private String username;
    private String password;
}
