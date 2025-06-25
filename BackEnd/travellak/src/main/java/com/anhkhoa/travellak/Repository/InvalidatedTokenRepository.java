package com.anhkhoa.travellak.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhkhoa.travellak.Entity.InvalidatedToken;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
