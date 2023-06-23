package com.sobu.parkinglot.data.repository;

import com.sobu.parkinglot.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByCode(String code);
}
