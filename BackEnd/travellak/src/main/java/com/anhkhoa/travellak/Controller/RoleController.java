package com.anhkhoa.travellak.Controller;
import com.anhkhoa.travellak.Service.RoleService;
import com.anhkhoa.travellak.dto.Request.Role.RoleCreationRequest;
import com.anhkhoa.travellak.dto.Response.ApiResponse;
import com.anhkhoa.travellak.dto.Response.RoleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("Role")
@CrossOrigin(origins = "http://localhost:5173")
public class RoleController {

    RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> create(@RequestBody RoleCreationRequest request) {
        return ApiResponse.<RoleResponse>builder().result(roleService.create(request)).build();
    }

    @GetMapping("/{permissionName}")
    public ApiResponse<Void> delete(@PathVariable String roleName) {
        roleService.deleteRole(roleName);
        return ApiResponse.<Void>builder().build();
    }


    @GetMapping
    public ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder().result(roleService.getAll()).build();
    }


}
