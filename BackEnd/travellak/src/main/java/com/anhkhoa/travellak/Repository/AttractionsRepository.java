package com.anhkhoa.travellak.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhkhoa.travellak.Entity.Attractions;

@Repository
public interface AttractionsRepository extends JpaRepository<Attractions, UUID> {}
