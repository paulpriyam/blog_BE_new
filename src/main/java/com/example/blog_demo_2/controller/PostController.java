package com.example.blog_demo_2.controller;

import com.example.blog_demo_2.payload.ApiResponse;
import com.example.blog_demo_2.payload.PostDto;
import com.example.blog_demo_2.payload.PostResponse;
import com.example.blog_demo_2.services.PostService;
import com.example.blog_demo_2.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    private ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto post = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/post/{postId}")
    private ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto post = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}")
    private ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("post successfully deleted", false), HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    private ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/posts")
    private ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "filter", defaultValue = AppConstants.FILTER, required = false) String filter,
                                                    @RequestParam(value = "sortedBy", defaultValue = AppConstants.SORTED_BY, required = false) String sortedBy) {
        PostResponse allPost = postService.getAllPost(pageNumber, pageSize, filter, sortedBy);
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    private ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> allPost = postService.getPostByUser(userId);
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    private ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> allPost = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    private ResponseEntity<List<PostDto>> searchByKeyword(@PathVariable String keyword) {
        List<PostDto> allPost = postService.searchPost("%" + keyword + "%");
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }
}
