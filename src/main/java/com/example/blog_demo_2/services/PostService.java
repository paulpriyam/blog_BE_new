package com.example.blog_demo_2.services;

import com.example.blog_demo_2.entity.Post;
import com.example.blog_demo_2.payload.PostDto;
import com.example.blog_demo_2.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostDto getPostById(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String filter,String sortedBy);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);
}
