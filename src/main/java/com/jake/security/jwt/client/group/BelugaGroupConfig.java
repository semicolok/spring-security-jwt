package com.jake.security.jwt.client.group;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@EnableConfigurationProperties(BelugaGroupConfig.class)
@ConfigurationProperties(prefix = "client.group.beluga")
public class BelugaGroupConfig {
    private String baseUrl;
    private long connectionTimeoutSeconds;
    private long readTimeoutSeconds;
    private long writeTimeoutSeconds;
}
