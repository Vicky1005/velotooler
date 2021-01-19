package com.velotooler.api.auth;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginResponse {
    private String token;
}
