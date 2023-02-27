package com.example.blog_demo_2.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    private List<PostDto> posts;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean isLastPage;
}
