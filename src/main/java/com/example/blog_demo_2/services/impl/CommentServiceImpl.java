package com.example.blog_demo_2.services.impl;

import com.example.blog_demo_2.entity.Comment;
import com.example.blog_demo_2.entity.Post;
import com.example.blog_demo_2.entity.User;
import com.example.blog_demo_2.exceptions.ResourceNotFoundException;
import com.example.blog_demo_2.payload.CommentDto;
import com.example.blog_demo_2.repository.CommentRepo;
import com.example.blog_demo_2.repository.PostRepo;
import com.example.blog_demo_2.repository.UsersRepo;
import com.example.blog_demo_2.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CommentDto> getCommentsByPost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId.toString()));
        List<Comment> comments = commentRepo.findByPost(post);
        return comments.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId.toString()));
        User user = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId.toString()));
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPost(post);
        comment.setUser(user);
        Comment createdComment = commentRepo.save(comment);
        return entityToDto(createdComment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId.toString()));
        commentRepo.delete(comment); 
    }

    private CommentDto entityToDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }
}
