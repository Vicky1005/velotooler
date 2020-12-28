package com.velotooler.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Auth {
    private String email;
    private String password;
}
