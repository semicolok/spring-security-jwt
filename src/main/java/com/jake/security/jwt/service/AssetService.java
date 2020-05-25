package com.jake.security.jwt.service;

import com.jake.security.jwt.client.asset.BelugaAssetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final BelugaAssetClient belugaAssetClient;

    public void getAssets(String userId, Pageable page) {

    }
}
