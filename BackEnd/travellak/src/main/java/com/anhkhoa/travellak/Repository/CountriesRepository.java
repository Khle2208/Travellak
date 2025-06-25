package com.anhkhoa.travellak.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhkhoa.travellak.Entity.Countries;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, UUID> {}
