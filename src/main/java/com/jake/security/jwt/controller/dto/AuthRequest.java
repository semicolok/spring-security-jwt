package com.jake.security.jwt.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthRequest {

    @JsonProperty("userid")
    private String userId;
    private String password;
}
