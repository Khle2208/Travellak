package com.anhkhoa.travellak.dto.Request.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersCreationRequest {
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    String email;
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{6,}$",
            message = "Password phải ít nhất 6 ký tự, có 1 chữ hoa, 1 chữ thường và 1 ký tự đặc biệt"
    )
    String password;
    Set<String> roles = new HashSet<>(Set.of("USER"));
}
