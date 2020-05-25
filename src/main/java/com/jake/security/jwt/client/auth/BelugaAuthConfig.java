package com.jake.security.jwt.client.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@EnableConfigurationProperties(BelugaAuthConfig.class)
@ConfigurationProperties(prefix = "client.auth.beluga")
public class BelugaAuthConfig {
    private String baseUrl;
    private long connectionTimeoutSeconds;
    private long readTimeoutSeconds;
    private long writeTimeoutSeconds;
}
