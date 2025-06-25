package com.anhkhoa.travellak.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhkhoa.travellak.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {}
