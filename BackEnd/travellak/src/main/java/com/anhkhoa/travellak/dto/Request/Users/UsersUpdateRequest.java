package com.anhkhoa.travellak.dto.Request.Users;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class UsersUpdateRequest {
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{6,}$",
            message = "Password phải ít nhất 6 ký tự, có 1 chữ hoa, 1 chữ thường và 1 ký tự đặc biệt")
    String password;

    String phoneNumber;
    String name;
    Set<String> roles = new HashSet<>(Set.of("USER"));
}
