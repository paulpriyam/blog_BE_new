package com.example.blog_demo_2.services.impl;

import com.example.blog_demo_2.entity.Role;
import com.example.blog_demo_2.entity.User;
import com.example.blog_demo_2.exceptions.ResourceNotFoundException;
import com.example.blog_demo_2.payload.UserDto;
import com.example.blog_demo_2.repository.RoleRepo;
import com.example.blog_demo_2.repository.UsersRepo;
import com.example.blog_demo_2.services.UsersService;
import com.example.blog_demo_2.utils.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UsersService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User newUser = usersRepo.save(user);
        return modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserDtoToUserEntity(userDto);
        User savedUser = usersRepo.save(user);
        return UserEntityToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        User updatedUser = usersRepo.save(user);
        return UserEntityToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId.toString()));
        return UserEntityToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = usersRepo.findAll();
        return allUsers.stream().map(this::UserEntityToUserDto).collect(Collectors.toList());


    }

    @Override
    public void deleteUserByUserId(Integer userId) {
        User user = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId.toString()));
        usersRepo.delete(user);
    }

    User UserDtoToUserEntity(UserDto userDto) {
//        User user=modelMapper.map(userDto,User.class);
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

    UserDto UserEntityToUserDto(User user) {
//        UserDto userDto=modelMapper.map(user,UserDto.class);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
