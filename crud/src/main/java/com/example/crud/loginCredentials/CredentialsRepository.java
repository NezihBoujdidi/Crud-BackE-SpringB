package com.example.crud.loginCredentials;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credentials,Long> {
    Optional<Credentials> findByUserName(String usName);

}
