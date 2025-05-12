package com.anhkhoa.travellak.Configuration;

import com.anhkhoa.travellak.Entity.Role;
import com.anhkhoa.travellak.Entity.Users;
import com.anhkhoa.travellak.Repository.RoleRepository;
import com.anhkhoa.travellak.Repository.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UsersRepository usersRepository) {
        Role adminRole = roleRepository.findById("ADMIN").orElseThrow(() -> new RuntimeException("Role không tồn tại"));
        var roles = new HashSet<Role>();
        roles.add(adminRole);
        return args -> {
            if (usersRepository.findByEmail("admin@gmail.com").isEmpty()) {
                Users user = Users.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .roles(new HashSet<>(roles))
                        .build();
                usersRepository.save(user);
            }
        };
    }
}
