package com.youssef.repos;

import com.youssef.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role,Long> {
Role findRoleByName(String name);

}
