package com.example.blog_demo_2.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer id;
    private String title;
    private String content;
    private String imageUrl;
    private Date createdAt;
    private Date updatedAt;
    private CategoryDto category;
    private UserDto user;
}
