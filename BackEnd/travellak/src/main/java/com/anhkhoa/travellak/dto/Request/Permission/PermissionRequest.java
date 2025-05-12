package com.anhkhoa.travellak.dto.Request.Permission;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionRequest {
    String permissionName;
    String  description;
}
