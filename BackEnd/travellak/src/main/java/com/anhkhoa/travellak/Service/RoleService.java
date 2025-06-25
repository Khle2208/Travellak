package com.anhkhoa.travellak.Service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.anhkhoa.travellak.Mapper.RoleMapper;
import com.anhkhoa.travellak.Repository.PermissionRepository;
import com.anhkhoa.travellak.Repository.RoleRepository;
import com.anhkhoa.travellak.dto.Request.Role.RoleCreationRequest;
import com.anhkhoa.travellak.dto.Response.RoleResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor // tạo constructor chứa tất cả các biến được khai báo final và tự động inject các dependency
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // khai báo phạm vi là private và final
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleCreationRequest request) {
        var role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissionName());
        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> getAll() {
        return roleMapper.toListRoleResponse(roleRepository.findAll());
    }

    public void deleteRole(String roleName) {
        roleRepository.deleteById(roleName);
    }
}
