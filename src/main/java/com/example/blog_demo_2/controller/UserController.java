package com.example.blog_demo_2.controller;

import com.example.blog_demo_2.entity.User;
import com.example.blog_demo_2.payload.ApiResponse;
import com.example.blog_demo_2.payload.UserDto;
import com.example.blog_demo_2.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UsersService usersService;

    @PostMapping("/")
    private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser=usersService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    private ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto updatedUser = usersService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        usersService.deleteUserByUserId(userId);
        return new ResponseEntity<>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
    }

    @GetMapping("/")
    private ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsers = usersService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
      UserDto userDto =  usersService.getUserById(userId);
      return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
}
