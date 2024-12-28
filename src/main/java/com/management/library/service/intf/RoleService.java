package com.management.library.service.intf;

import com.management.library.dto.user.request.RoleRequest;
import com.management.library.dto.user.response.RoleResponse;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    List<RoleResponse> getAllRoles();
    RoleResponse getRole(String code);
    RoleResponse createRole(RoleRequest request);
    RoleResponse updateRole(UUID id, RoleRequest request);
    RoleResponse deleteRole (UUID id);
}
