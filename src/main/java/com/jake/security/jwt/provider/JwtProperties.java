package com.jake.security.jwt.provider;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@EnableConfigurationProperties(JwtProperties.class)
@ConfigurationProperties(prefix = "jwt.properties")
public class JwtProperties {
    private String authHeader;
    private String authPrefix;
    private String secret;
    private long expirationSeconds;
}
