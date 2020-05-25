package com.jake.security.jwt.controller.dto;

import com.jake.security.jwt.client.auth.dto.BelugaAuthRequest;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthRequestMapper {

    BelugaAuthRequest toBelugaAuthRequest(AuthRequest source);

    @IterableMapping(elementTargetType = BelugaAuthRequest.class)
    List<BelugaAuthRequest> mapListToList(List<AuthRequest> source);
}
