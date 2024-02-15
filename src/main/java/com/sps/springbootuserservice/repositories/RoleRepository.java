package com.sps.springbootuserservice.repositories;

import com.sps.springbootuserservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);

    List<Role> findAllByIdIn(List<Long> roleId);
}
