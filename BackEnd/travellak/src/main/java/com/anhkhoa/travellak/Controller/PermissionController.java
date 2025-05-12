package com.anhkhoa.travellak.Controller;

import com.anhkhoa.travellak.Service.PermissionService;
import com.anhkhoa.travellak.dto.Request.Permission.PermissionRequest;
import com.anhkhoa.travellak.dto.Response.ApiResponse;
import com.anhkhoa.travellak.dto.Response.PermissionResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("Permission")
@CrossOrigin(origins = "http://localhost:5173")
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    public ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        System.out.println(request.getPermissionName());
        return ApiResponse.<PermissionResponse>builder().result(permissionService.create(request)).build();
    }

    @GetMapping("/{permissionName}")
    public ApiResponse<Void> delete(@PathVariable String permissionName){
        permissionService.deletePermission(permissionName);
        return ApiResponse.<Void>builder().build();
    }


    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder().result(permissionService.getAll()).build();
    }


}
