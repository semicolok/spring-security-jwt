package com.jake.security.jwt.provider;

import com.jake.security.jwt.controller.dto.AuthRequest;
import com.jake.security.jwt.controller.dto.AuthResponse;
import com.jake.security.jwt.client.auth.BelugaAuthClient;
import com.jake.security.jwt.client.auth.dto.BelugaAuthResponseMapper;
import com.jake.security.jwt.controller.dto.AuthRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthProvider {

    private final BelugaAuthClient belugaAuthClient;
    private final AuthRequestMapper authRequestMapper;
    private final BelugaAuthResponseMapper belugaAuthResponseMapper;

    public AuthResponse authenticate(AuthRequest request) {
        final var belugaAuthResponse = belugaAuthClient.postAuth(authRequestMapper.toBelugaAuthRequest(request));

        return belugaAuthResponseMapper.toAuthResponse(belugaAuthResponse);
    }

    public void delete(String authorizationHeader) {
        belugaAuthClient.deleteAuth(authorizationHeader);
    }
}
