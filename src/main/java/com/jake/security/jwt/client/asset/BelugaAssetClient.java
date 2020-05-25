package com.jake.security.jwt.client.asset;

import com.jake.security.jwt.client.Client;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Client
public class BelugaAssetClient {

    private final BelugaAssetAPI belugaAssetAPI;

    public BelugaAssetClient(BelugaAssetConfig belugaAssetConfig) {
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

        this.belugaAssetAPI = retrofit.create(BelugaAssetAPI.class);
    }
}
