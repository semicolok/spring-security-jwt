package com.jake.security.jwt.client.auth.dto;

import com.jake.security.jwt.controller.dto.AuthResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BelugaAuthResponseMapper {

    AuthResponse toAuthResponse(BelugaAuthResponse source);
}
