package com.jake.security.jwt.client.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BelugaAuthResponse {

    @JsonProperty("userid")
    private String userId;
    private List<String> roles;
    private String token;
}
