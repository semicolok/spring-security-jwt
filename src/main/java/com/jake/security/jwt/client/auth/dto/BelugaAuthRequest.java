package com.jake.security.jwt.client.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BelugaAuthRequest {

    @JsonProperty("userid")
    private String userId;
    private String password;
}
