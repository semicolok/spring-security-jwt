package com.jake.security.jwt.provider;

import com.google.common.collect.Maps;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {

    private final String authHeader;
    private final String authPrefix;
    private final String base64EncodedSecretKey;
    private final long expirationSeconds;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.authHeader = jwtProperties.getAuthHeader();
        this.authPrefix = jwtProperties.getAuthPrefix();
        this.base64EncodedSecretKey = Encoders.BASE64.encode(jwtProperties.getSecret().getBytes());
        this.expirationSeconds = jwtProperties.getExpirationSeconds();
    }

    public String generateToken(String username, List<String> roles) {
        final Map<String, Object> claims = Maps.newHashMap();
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    public String resolveToken(HttpServletRequest req) {
        final var bearerToken = req.getHeader(authHeader);

        if (StringUtils.hasText(bearerToken) && StringUtils.startsWithIgnoreCase(bearerToken, authPrefix + " ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    public Authentication getAuthentication(String token) {
        try {
            final var claimsJws = Jwts.parserBuilder()
                    .setSigningKey(base64EncodedSecretKey)
                    .build().parseClaimsJws(token);

            final var claims = claimsJws.getBody();

            return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", new ArrayList<>());
        } catch (Exception e) {
            log.error("Failed to parse token. token: {}", token, e);
            return null;
        }
    }
}
