package com.jake.security.jwt.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AuthResponse {

    @JsonProperty("userid")
    private String userId;
    private List<String> roles;
    private String token;
}
