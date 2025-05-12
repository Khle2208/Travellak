package com.anhkhoa.travellak.Repository;

import com.anhkhoa.travellak.Entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, UUID> {
}
