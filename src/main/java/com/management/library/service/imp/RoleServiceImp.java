package com.management.library.service.imp;

import com.management.library.dto.user.request.RoleRequest;
import com.management.library.dto.user.response.RoleResponse;
import com.management.library.enums.Prefix;
import com.management.library.enums.ResponseCode;
import com.management.library.exception.ApiException;
import com.management.library.mappings.RoleMapper;
import com.management.library.repository.RoleRepository;
import com.management.library.service.intf.RoleService;
import com.management.library.util.ApplicationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.management.library.model.Role;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final ApplicationUtil applicationUtil;
    @Override
    public List<RoleResponse> getAllRoles() {
        try{
            List<Role> roles = roleRepository.findAll();
            return roleMapper.toResponseList(roles);
        }
        catch (Exception e){
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public RoleResponse getRole(String code) {
        try{
            Role role = roleRepository.findByCode(code).orElseThrow(
                    () -> new ApiException(ResponseCode.ROLE_NOT_FOUND)
            );
            return roleMapper.toResponse(role);
        }
        catch(Exception ex){
            log.error("Role function error: {}", ex.getMessage());
        }
        throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR);
    }

    @Override
    public RoleResponse createRole(RoleRequest request) {
        try{
            Role role = roleMapper.toEntity(request);
            role.setCode(applicationUtil.generateUniqueCode(Prefix.ROLE.getPrefix()));
            roleRepository.save(role);
            return roleMapper.toResponse(role);
        }
        catch(Exception ex){
            log.error("Role function error: {}", ex.getMessage());
        }
        throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR);
    }

    @Override
    public RoleResponse updateRole(UUID id, RoleRequest request) {
        try{
            Role role = roleRepository.findById(id).orElseThrow(
                    () -> new ApiException(ResponseCode.ROLE_NOT_FOUND)
            );
            roleMapper.updateRoleFromRequest(request, role);
            return roleMapper.toResponse(roleRepository.save(role));
        }
        catch (Exception e) {
            log.error("Error updating role with id: {}", id, e);
        }
        throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR);
    }

    @Override
    public RoleResponse deleteRole(UUID id) {
        try{
            Role role = roleRepository.findById(id).orElseThrow(
                    () -> new ApiException(ResponseCode.ROLE_NOT_FOUND)
            );
            roleRepository.delete(role);
            return roleMapper.toResponse(role);
        }
        catch (Exception e) {
            log.error("Error updating role with id: {}", id, e);
        }
        throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR);
    }
}
