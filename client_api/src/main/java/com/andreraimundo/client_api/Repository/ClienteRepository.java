package com.andreraimundo.client_api.Repository;

import com.andreraimundo.client_api.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findOptionalByEmail(String email);
}
