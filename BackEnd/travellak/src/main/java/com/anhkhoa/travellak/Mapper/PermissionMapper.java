package com.anhkhoa.travellak.Mapper;

import com.anhkhoa.travellak.Entity.Permission;
import com.anhkhoa.travellak.dto.Request.Permission.PermissionRequest;
import com.anhkhoa.travellak.dto.Response.PermissionResponse;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);

    List<PermissionResponse> toListPermissionResponse(List<Permission> permissions);

}
