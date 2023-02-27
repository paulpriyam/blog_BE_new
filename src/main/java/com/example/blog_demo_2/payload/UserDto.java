package com.example.blog_demo_2.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty(message = "Name shouldn't be empty")
    private String name;

    @NotEmpty(message = "password shouldn't be empty")
    @Size(min = 3,max = 10,message = "Password would be within")
    private String password;

    @Email
    private String email;

    @NotEmpty(message = "About shouldn't be empty")
    private String about;
}
