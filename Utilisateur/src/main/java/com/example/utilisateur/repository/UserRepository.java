package com.example.utilisateur.repository;

import com.example.utilisateur.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
