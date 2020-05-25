package com.jake.security.jwt.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
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

        SecretKey secretKey = Keys.hmacShaKeyFor(Encoders.BASE64.encode("hhjkk".getBytes()).getBytes());

        String token = Jwts.builder()
//                .setClaims(claims)
                .setSubject("testUserId")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000))
//                .signWith(Keys.hmacShaKeyFor("wr32A5r2ubkwKwXYop8pw7J8hv6".getBytes()))
//                .signWith(SignatureAlgorithm.HS256, Decoders.BASE64.decode("wr32A5r2ubkwKwXYop8pw7J8hv6"))
                .signWith(secretKey)
                .compact();

        System.out.println(token);


        Jws<Claims> claimsJws = Jwts.parserBuilder()
//                .setSigningKey(Decoders.BASE64.decode("wr32A5r2ubkwKwXYop8pw7J8hv6"))
                .setSigningKey(secretKey)
                .build().parseClaimsJws(token);

        System.out.println(claimsJws.toString());
        System.out.println(claimsJws.getBody().toString());
    }
}