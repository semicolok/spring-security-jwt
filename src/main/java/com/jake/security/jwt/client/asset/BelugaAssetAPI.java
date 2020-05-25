package com.jake.security.jwt.client.asset;

import com.jake.security.jwt.client.auth.dto.BelugaAuthRequest;
import com.jake.security.jwt.client.auth.dto.BelugaAuthResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BelugaAssetAPI {

    @POST("/auth2")
    Call<BelugaAuthResponse> postAuth(@Body BelugaAuthRequest request);

    @DELETE("/auth")
    Call<Void> deleteAuth(@Header("Authorization") String authorization);
}
