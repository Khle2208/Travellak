package com.anhkhoa.travellak.dto.Response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersResponse {
    UUID userId;
    String email;
    String password;
    String phoneNumber;
    String name;
    LocalDateTime createTime;
    LocalDateTime updateTime;
    Set<RoleResponse> roles;
}
