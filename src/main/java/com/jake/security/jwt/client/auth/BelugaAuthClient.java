package com.jake.security.jwt.client.auth;

import com.jake.security.jwt.client.Client;
import com.jake.security.jwt.client.auth.dto.BelugaAuthRequest;
import com.jake.security.jwt.client.auth.dto.BelugaAuthResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Client
public class BelugaAuthClient {

    private final BelugaAuthAPI belugaAuthAPI;

    public BelugaAuthClient(BelugaAuthConfig belugaAuthConfig) {
        final var okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(belugaAuthConfig.getConnectionTimeoutSeconds(), TimeUnit.SECONDS)
                .readTimeout(belugaAuthConfig.getReadTimeoutSeconds(), TimeUnit.SECONDS)
                .writeTimeout(belugaAuthConfig.getWriteTimeoutSeconds(), TimeUnit.SECONDS)
                .build();

        final var retrofit = new Retrofit.Builder()
                .baseUrl(belugaAuthConfig.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        this.belugaAuthAPI = retrofit.create(BelugaAuthAPI.class);
    }

    public BelugaAuthResponse postAuth(BelugaAuthRequest request) {
        final var responseCall = belugaAuthAPI.postAuth(request);
        try {
            return responseCall.execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAuth(String authorizationHeader) {
        try {
            belugaAuthAPI.deleteAuth(authorizationHeader).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
