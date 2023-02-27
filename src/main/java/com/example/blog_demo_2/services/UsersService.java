package com.example.blog_demo_2.services;

import com.example.blog_demo_2.entity.User;
import com.example.blog_demo_2.payload.UserDto;

import java.util.List;

public interface UsersService {

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user,Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUserByUserId(Integer userId);
}
