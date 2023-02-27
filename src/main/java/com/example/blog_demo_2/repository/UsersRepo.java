package com.example.blog_demo_2.repository;

import com.example.blog_demo_2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User,Integer> {

}
