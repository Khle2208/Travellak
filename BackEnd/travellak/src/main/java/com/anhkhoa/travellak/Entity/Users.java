package com.anhkhoa.travellak.Entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID userId;

    String email;
    String password;
    String phoneNumber;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    String name;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createTime;

    @UpdateTimestamp
    LocalDateTime updateTime;

    @ManyToMany
    Set<Role> roles;
}
