package com.jake.security.jwt.controller;

import com.google.common.collect.Maps;
import com.jake.security.jwt.configuration.filter.UserPrincipal;
import com.jake.security.jwt.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @GetMapping("/assets")
    public ResponseEntity<?> hello(Authentication authentication, Pageable page) {
        Map<String, String> map = Maps.newHashMap();
        map.put("result", "world");

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        assetService.getAssets(userPrincipal.getUserId(), page);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
