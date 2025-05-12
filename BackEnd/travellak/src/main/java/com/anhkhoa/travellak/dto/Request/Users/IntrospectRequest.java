package com.anhkhoa.travellak.dto.Request.Users;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class IntrospectRequest {
    String token;
}
