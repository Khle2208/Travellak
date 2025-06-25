package com.anhkhoa.travellak.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhkhoa.travellak.Entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
