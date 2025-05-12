package com.anhkhoa.travellak.Service;

import com.anhkhoa.travellak.Entity.Users;
import com.anhkhoa.travellak.Mapper.UsersMapper;
import com.anhkhoa.travellak.Repository.RoleRepository;
import com.anhkhoa.travellak.Repository.UsersRepository;
import com.anhkhoa.travellak.dto.Request.Users.UsersCreationRequest;
import com.anhkhoa.travellak.dto.Request.Users.UsersUpdateRequest;
import com.anhkhoa.travellak.dto.Response.UsersResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UsersService {
    UsersRepository usersRepository;
    UsersMapper usersMapper;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    @PostAuthorize("returnObject.email == authentication.email")
    public UsersResponse getUserById(UUID userId) {
        return usersMapper.toUsersResponse(usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tồn tại User")));
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public List<UsersResponse> getAllUser() {
        return usersMapper.toListUsersResponse(usersRepository.findAll());
    }

    public UsersResponse myInfo(){
        var context = SecurityContextHolder.getContext();

        String email = context.getAuthentication().getName();

        Users  user = usersRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User không tồn tại"));

        return usersMapper.toUsersResponse(user);

    }

    public UsersResponse updateUser(UsersUpdateRequest request){
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        Users  user = usersRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User không tồn tại"));
        usersMapper.updateUsers(user, request);
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return usersMapper.toUsersResponse(usersRepository.save(user));
    }

    public UsersResponse createUser(UsersCreationRequest request) {
        if (usersRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        Users user = usersMapper.toUsers(request);
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return usersMapper.toUsersResponse(usersRepository.save(user));
    }


    @PreAuthorize("hasRole('ADMIN')")
    public UsersResponse updateUser(UUID userId, UsersUpdateRequest request) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tồn tại User"));
        usersMapper.updateUsers(user, request);
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return usersMapper.toUsersResponse(usersRepository.save(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(UUID userId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tồn tại User"));
        usersRepository.delete(user);
        return "Xoá thành công";
    }
}
