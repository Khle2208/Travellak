package com.anhkhoa.travellak.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anhkhoa.travellak.Entity.Permission;
import com.anhkhoa.travellak.Mapper.PermissionMapper;
import com.anhkhoa.travellak.Repository.PermissionRepository;
import com.anhkhoa.travellak.dto.Request.Permission.PermissionRequest;
import com.anhkhoa.travellak.dto.Response.PermissionResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor // tạo constructor chứa tất cả các biến được khai báo final và tự động inject các dependency
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // khai báo phạm vi là private và final
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);

        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    public List<PermissionResponse> getAll() {
        return permissionMapper.toListPermissionResponse(permissionRepository.findAll());
    }

    public void deletePermission(String permissionName) {
        permissionRepository.deleteById(permissionName);
    }
}
