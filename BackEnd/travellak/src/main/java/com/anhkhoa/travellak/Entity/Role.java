package com.anhkhoa.travellak.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Role {
    @Id
    String roleName;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    String description;

    @ManyToMany
    Set<Permission> permissions;
}
