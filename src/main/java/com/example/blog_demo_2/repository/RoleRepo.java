package com.example.blog_demo_2.repository;

import com.example.blog_demo_2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}
