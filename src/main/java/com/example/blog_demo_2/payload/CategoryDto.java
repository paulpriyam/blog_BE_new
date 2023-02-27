package com.example.blog_demo_2.payload;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty(message = "category Title shouldn't be empty")
    @NotNull
    @Size(min = 4,message = "Min Title would be of 4 characters")
    private String categoryTitle;

    @NotEmpty(message = "Category Description shouldn't be empty")
    @NotNull
    private String categoryDescription;
}
