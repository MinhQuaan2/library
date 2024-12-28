package com.management.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.library.model.Role;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByCode(String code);
    Optional<Role> findByName(String name);
}
