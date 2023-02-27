package com.example.blog_demo_2.repository;

import com.example.blog_demo_2.entity.Category;
import com.example.blog_demo_2.entity.Post;
import com.example.blog_demo_2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    @Query("select p from Post p where p.title like :key or p.content like :key")
    List<Post> searchByKeyword(@Param("key") String keyword);
}
