package com.anhkhoa.travellak.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Permission {
    @Id
    String permissionName;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    String description;
}
