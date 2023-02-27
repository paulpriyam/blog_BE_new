package com.example.blog_demo_2.repository;

import com.example.blog_demo_2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
