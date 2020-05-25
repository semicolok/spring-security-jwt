package com.jake.security.jwt.client.group;

import com.jake.security.jwt.client.Client;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Client
public class BelugaGroupClient {

    private final BelugaGroupAPI belugaGroupAPI;

    public BelugaGroupClient(BelugaGroupConfig belugaAssetConfig) {
        final var okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(belugaAssetConfig.getConnectionTimeoutSeconds(), TimeUnit.SECONDS)
                .readTimeout(belugaAssetConfig.getReadTimeoutSeconds(), TimeUnit.SECONDS)
                .writeTimeout(belugaAssetConfig.getWriteTimeoutSeconds(), TimeUnit.SECONDS)
                .build();

        final var retrofit = new Retrofit.Builder()
                .baseUrl(belugaAssetConfig.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        this.belugaGroupAPI = retrofit.create(BelugaGroupAPI.class);
    }
}
