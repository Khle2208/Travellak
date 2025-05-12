package com.anhkhoa.travellak.Controller;

import com.anhkhoa.travellak.Entity.Users;
import com.anhkhoa.travellak.Service.UsersService;
import com.anhkhoa.travellak.dto.Request.Users.UsersCreationRequest;
import com.anhkhoa.travellak.dto.Request.Users.UsersUpdateRequest;
import com.anhkhoa.travellak.dto.Response.UsersResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("Users")
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
    public UsersResponse myInfo() {
        return usersService.myInfo();
    }

    @PutMapping("/editProfile")
    public UsersResponse updateUser(@RequestBody @Valid UsersUpdateRequest request){
        return usersService.updateUser(request);
    }

    @GetMapping
    public List<UsersResponse> getAllUsers() {
        return usersService.getAllUser();
    }

    @PostMapping
    public UsersResponse createUser(@RequestBody @Valid UsersCreationRequest request) {
//        if (request.getPassword().equals(confirmPassword)){
//            return usersService.createUser(request);
//        }
        return usersService.createUser(request);
    }

    @PutMapping("/{userId}")
    public UsersResponse updateUser(@PathVariable("userId") UUID userId, @RequestBody @Valid UsersUpdateRequest request){
        return usersService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") UUID userId){
        return usersService.deleteUser(userId);
    }
}
