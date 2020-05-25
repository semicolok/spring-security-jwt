package com.jake.security.jwt.controller;

import com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class SecurityTestController {

    @GetMapping("/hello")
    public ResponseEntity<?> hello(Authentication authentication) {
        Map<String, String> map = Maps.newHashMap();
        map.put("result", "world");

        String userId = (String)authentication.getPrincipal();

        System.out.println(userId);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/tokens")
    public ResponseEntity<?> getTokens() {
        // TODO
        Map<String, String> map = Maps.newHashMap();
        map.put("result", "world");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
