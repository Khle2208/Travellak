package com.anhkhoa.travellak.dto.Request.Role;

import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
