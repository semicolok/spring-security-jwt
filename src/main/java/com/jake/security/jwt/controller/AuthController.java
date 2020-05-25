package com.jake.security.jwt.controller;

import com.jake.security.jwt.controller.dto.AuthRequest;
import com.jake.security.jwt.controller.dto.AuthResponse;
import com.jake.security.jwt.provider.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthProvider authProvider;

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authenticateWithUserIdAndPassword(@RequestBody AuthRequest authRequest) {
        final var response = authProvider.authenticate(authRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/auth")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorizationHeader) {
        authProvider.delete(authorizationHeader);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
