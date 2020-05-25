package com.jake.security.jwt.configuration.filter;

import lombok.Data;

@Data
public class UserSecret {
    private String username;
    private String password;
}
