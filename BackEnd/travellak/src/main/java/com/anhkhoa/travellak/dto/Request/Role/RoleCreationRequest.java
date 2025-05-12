package com.anhkhoa.travellak.dto.Request.Role;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleCreationRequest {
    String roleName;
    String description;
    Set<String> permissionName;
}
