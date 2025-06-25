package com.anhkhoa.travellak.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhkhoa.travellak.Entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);
}
