package com.example.blog_demo_2.services.impl;

import com.example.blog_demo_2.entity.Category;
import com.example.blog_demo_2.entity.Post;
import com.example.blog_demo_2.entity.User;
import com.example.blog_demo_2.exceptions.ResourceNotFoundException;
import com.example.blog_demo_2.payload.PostDto;
import com.example.blog_demo_2.payload.PostResponse;
import com.example.blog_demo_2.repository.CategoryRepo;
import com.example.blog_demo_2.repository.PostRepo;
import com.example.blog_demo_2.repository.UsersRepo;
import com.example.blog_demo_2.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId.toString()));
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId.toString()));

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageUrl("default.png");
        post.setCreatedAt(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post createdPost = postRepo.save(post);
        return entityToDto(createdPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId.toString()));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUpdatedAt(new Date());
        Post updatedPost = postRepo.save(post);
        return entityToDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId.toString()));
        postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId.toString()));
        return entityToDto(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String filter, String sortedBy) {
        Sort sort = sortedBy.equalsIgnoreCase("asc") ? Sort.by(filter).ascending() : Sort.by(filter).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepo.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map(this::entityToDto).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setPosts(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId.toString()));
        List<Post> postByCategory = postRepo.findByCategory(category);
        return postByCategory.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId.toString()));
        List<Post> postByUser = postRepo.findByUser(user);
        return postByUser.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts = postRepo.searchByKeyword(keyword);
        return posts.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    private PostDto entityToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

    private Post DtoToEntity(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }
}
