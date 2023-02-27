package com.example.blog_demo_2.repository;

import com.example.blog_demo_2.entity.Comment;
import com.example.blog_demo_2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

    List<Comment> findByPost(Post post);
}
