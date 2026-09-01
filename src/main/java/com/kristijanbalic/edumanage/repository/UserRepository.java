package com.kristijanbalic.edumanage.repository;

import com.kristijanbalic.edumanage.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}