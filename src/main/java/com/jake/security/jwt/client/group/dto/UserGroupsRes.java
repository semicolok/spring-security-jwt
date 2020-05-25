package com.jake.security.jwt.client.group.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserGroupsRes {
    private UserGroupsDocumentRes userGroups;
}
