package com.example.muslich.belajar1.repository;

import com.example.muslich.belajar1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
