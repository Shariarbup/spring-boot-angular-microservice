package com.example.springangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springangular.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
}
