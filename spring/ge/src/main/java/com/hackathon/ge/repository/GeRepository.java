package com.hackathon.ge.repository;

import com.hackathon.ge.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeRepository extends JpaRepository<Email, Long> {
    Email findByHash(String hash);
}
