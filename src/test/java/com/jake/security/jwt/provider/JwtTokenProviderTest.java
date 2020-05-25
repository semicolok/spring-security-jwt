package com.jake.security.jwt.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    @Test
    public void test() {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        System.out.println(Instant.now());
        System.out.println(Date.from(Instant.now()));

        String base64EncodedSecretKey = Encoders.BASE64.encode("hhjqwerqwerwqerqwerwqewewerwerwerkk".getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(base64EncodedSecretKey.getBytes());

        String token = Jwts.builder()
//                .setClaims(claims)
                .setSubject("testUserId")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
//                .signWith(secretKey, SignatureAlgorithm.HS256)
//                .signWith(secretKey)
                .compact();

        System.out.println(token);


        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(base64EncodedSecretKey)
//                .setSigningKey(secretKey)
//                .build().parseClaimsJws(token);
                .build().parseClaimsJws("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzb21lIjoicGF5bG9hZCJ9.P0YsZThHpj-RPQk91uE0DUJq0rUZJ-TZTuwto4iGDsg");

        System.out.println(claimsJws.toString());
        System.out.println(claimsJws.getBody().toString());
    }
}