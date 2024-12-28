package com.management.library.dto.user.response;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.UUID;

@Data
public class RoleResponse {
    private UUID id;
    private String code;
    private String name;
    private String description;
}
