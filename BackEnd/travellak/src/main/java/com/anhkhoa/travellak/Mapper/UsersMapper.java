package com.anhkhoa.travellak.Mapper;

import com.anhkhoa.travellak.Entity.Users;
import com.anhkhoa.travellak.dto.Request.Users.UsersCreationRequest;
import com.anhkhoa.travellak.dto.Request.Users.UsersUpdateRequest;
import com.anhkhoa.travellak.dto.Response.UsersResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "name", ignore = true)
    Users toUsers(UsersCreationRequest request);
    UsersResponse toUsersResponse(Users user);
    List<UsersResponse> toListUsersResponse(List<Users> users);
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateUsers(@MappingTarget Users user, UsersUpdateRequest request);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void changePassword(@MappingTarget Users user, UsersUpdateRequest request);

}
