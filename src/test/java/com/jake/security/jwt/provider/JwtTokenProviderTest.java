package com.jake.security.jwt.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private static final String ANY_SECRET = "secret";
    public static final String ANY_SUBJECT = "testUserId";

    @Test
    public void testGenerateToken() throws ParseException {
        Algorithm algorithm = Algorithm.HMAC256(ANY_SECRET);

        String token = JWT.create()
                .withSubject(ANY_SUBJECT)
                .withClaim("some", "body")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 600 * 1000))
                .sign(algorithm);

        System.out.println(token);
    }

    @Test
    public void testDecodeToken() {
        Algorithm algorithm = Algorithm.HMAC256(ANY_SECRET);

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0VXNlcklkIiwic29tZSI6ImJvZHkiLCJleHAiOjE1OTAzODUyNjEsImlhdCI6MTU5MDM4NDY2MX0.XB_-_8rAyhuNUSv1-tYifK3f9gmZzfXKPw5dZiruweQ";
        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT jwt = verifier.verify(token);

        System.out.println(jwt);

        System.out.println(jwt.getSubject());
        System.out.println(jwt.getPayload());
        System.out.println(jwt.getClaim("some").asString());
    }
}
