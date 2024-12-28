package com.management.library.mappings;

import com.management.library.dto.user.request.RoleRequest;
import com.management.library.dto.user.response.RoleResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

import com.management.library.model.Role;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toEntity(RoleRequest request);
    RoleResponse toResponse(Role role);
    List<RoleResponse> toResponseList(List<Role> roles);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoleFromRequest(RoleRequest request, @MappingTarget Role role);
    default void updateRoleNonNullFields(RoleRequest request, @MappingTarget Role role) {
        if (request.getName() != null) {
            role.setName(request.getName());
        }
        if (request.getDescription() != null) {
            role.setDescription(request.getDescription());
        }
        // Add more fields as needed
    }
}
