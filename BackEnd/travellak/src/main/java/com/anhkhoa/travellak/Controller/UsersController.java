package com.anhkhoa.travellak.Controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.anhkhoa.travellak.Service.UsersService;
import com.anhkhoa.travellak.dto.Request.Users.UsersCreationRequest;
import com.anhkhoa.travellak.dto.Request.Users.UsersUpdateRequest;
import com.anhkhoa.travellak.dto.Response.ApiResponse;
import com.anhkhoa.travellak.dto.Response.UsersResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/Users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UsersController {
    UsersService usersService;

    @GetMapping("/{userId}")
    public UsersResponse getUserById(@PathVariable("userId") UUID userId) {
        return usersService.getUserById(userId);
    }

    @GetMapping("/myInfo")
    public ApiResponse<UsersResponse> myInfo() {
        return ApiResponse.<UsersResponse>builder()
                .result(usersService.myInfo())
                .build();
    }

    @PutMapping("/editProfile")
    public ApiResponse<UsersResponse> updateUser(@RequestBody @Valid UsersUpdateRequest request) {
        return ApiResponse.<UsersResponse>builder()
                .result(usersService.updateUser(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<UsersResponse>> getAllUsers() {
        return ApiResponse.<List<UsersResponse>>builder()
                .result(usersService.getAllUser())
                .build();
    }

    @PostMapping("/SignUp")
    public ApiResponse<UsersResponse> createUser(@RequestBody @Valid UsersCreationRequest request) {
        return ApiResponse.<UsersResponse>builder()
                .code(1000)
                .result(usersService.createUser(request))
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UsersResponse> updateUser(
            @PathVariable("userId") UUID userId, @RequestBody @Valid UsersUpdateRequest request) {
        return ApiResponse.<UsersResponse>builder()
                .result(usersService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") UUID userId) {
        return usersService.deleteUser(userId);
    }
}
