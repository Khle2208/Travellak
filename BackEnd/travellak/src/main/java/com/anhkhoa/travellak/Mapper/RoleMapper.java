package com.anhkhoa.travellak.Mapper;

import com.anhkhoa.travellak.Entity.Role;
import com.anhkhoa.travellak.dto.Request.Role.RoleCreationRequest;
import com.anhkhoa.travellak.dto.Response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleCreationRequest request);
    RoleResponse toRoleResponse(Role role);
    List<RoleResponse> toListRoleResponse(List<Role> roles);
}
