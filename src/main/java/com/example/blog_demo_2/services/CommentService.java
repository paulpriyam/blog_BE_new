package com.example.blog_demo_2.services;


import com.example.blog_demo_2.entity.Comment;
import com.example.blog_demo_2.payload.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getCommentsByPost(Integer postId);

    CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);

    void deleteComment(Integer commentId);
}
