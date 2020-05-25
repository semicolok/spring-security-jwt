package com.jake.security.jwt.client.asset;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@EnableConfigurationProperties(BelugaAssetConfig.class)
@ConfigurationProperties(prefix = "client.asset.beluga")
public class BelugaAssetConfig {
    private String baseUrl;
    private long connectionTimeoutSeconds;
    private long readTimeoutSeconds;
    private long writeTimeoutSeconds;
}
