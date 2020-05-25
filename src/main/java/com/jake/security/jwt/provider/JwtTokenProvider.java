package com.jake.security.jwt.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jake.security.jwt.configuration.filter.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtTokenProvider {

    private final String authHeader;
    private final String authPrefix;
    private final Algorithm algorithm;
    private final long expirationSeconds;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.authHeader = jwtProperties.getAuthHeader();
        this.authPrefix = jwtProperties.getAuthPrefix();
        this.algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
        this.expirationSeconds = jwtProperties.getExpirationSeconds();
    }

    public String generateToken(String username, List<String> roles) {
        return JWT.create()
                .withSubject(username)
                .withClaim("roles", roles)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .sign(algorithm);
    }

    public String resolveToken(HttpServletRequest req) {
        final var bearerToken = req.getHeader(authHeader);

        if (StringUtils.hasText(bearerToken) && StringUtils.startsWithIgnoreCase(bearerToken, authPrefix + " ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    public Authentication getAuthentication(String token) {
        final var verifier = JWT.require(algorithm).build();
        final var jwt = verifier.verify(token);

        final var userPrincipal = new UserPrincipal();
        userPrincipal.setUserId(jwt.getSubject());

        return new UsernamePasswordAuthenticationToken(userPrincipal, "", new ArrayList<>());
    }
}
