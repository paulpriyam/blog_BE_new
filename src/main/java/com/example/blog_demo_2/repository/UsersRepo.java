package com.example.blog_demo_2.repository;

import com.example.blog_demo_2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
}
