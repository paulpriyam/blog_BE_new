package com.example.blog_demo_2.payload;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String userName;
    private String password;
}
