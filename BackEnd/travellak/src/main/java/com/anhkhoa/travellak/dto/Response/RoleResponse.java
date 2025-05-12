package com.anhkhoa.travellak.dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    String roleName;
    String description;
    Set<PermissionResponse> permissions;
}
