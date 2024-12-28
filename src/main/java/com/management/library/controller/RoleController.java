package com.management.library.controller;

import com.management.library.dto.user.request.RoleRequest;
import com.management.library.dto.user.response.RoleResponse;
import com.management.library.service.intf.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @PostMapping("")
    public ResponseEntity<RoleResponse> createRole(@RequestBody RoleRequest request){
        return ResponseEntity.ok(roleService.createRole(request));
    }
    @GetMapping("/{code}")
    public ResponseEntity<RoleResponse> getRole(@RequestParam String code){
        return ResponseEntity.ok(roleService.getRole(code));
    }
    @GetMapping("")
    public ResponseEntity<List<RoleResponse>> getRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> updateRole(@RequestParam UUID id, @RequestBody RoleRequest request){
        return ResponseEntity.ok(roleService.updateRole(id, request));
    }
    @GetMapping("/verify")
    public String verifyToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "Token is valid";
        } else {
            return "Token is invalid";
        }
    }
}
