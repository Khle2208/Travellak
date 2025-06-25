package com.anhkhoa.travellak.Entity;

import java.util.UUID;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID cityId;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    String cityName;

    @ManyToOne
    @JoinColumn(
            name = "country_id",
            foreignKey =
                    @ForeignKey(
                            name = "fk_country_city",
                            foreignKeyDefinition =
                                    "FOREIGN KEY (country_id) REFERENCES countries(country_id) ON DELETE CASCADE"))
    Countries country;
}
