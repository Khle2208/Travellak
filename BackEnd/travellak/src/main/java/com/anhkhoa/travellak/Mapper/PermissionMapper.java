package com.anhkhoa.travellak.Mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.anhkhoa.travellak.Entity.Permission;
import com.anhkhoa.travellak.dto.Request.Permission.PermissionRequest;
import com.anhkhoa.travellak.dto.Response.PermissionResponse;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);

    List<PermissionResponse> toListPermissionResponse(List<Permission> permissions);
}
