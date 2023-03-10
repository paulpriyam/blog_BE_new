package com.example.blog_demo_2.controller;

import com.example.blog_demo_2.payload.JwtAuthRequest;
import com.example.blog_demo_2.payload.JwtAuthResponse;
import com.example.blog_demo_2.payload.UserDto;
import com.example.blog_demo_2.security.JwtTokenHelper;
import com.example.blog_demo_2.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {


    @Autowired
    private JwtTokenHelper jwtTokenHelper;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> getToken(@RequestBody JwtAuthRequest request) {
        this.Authenticate(request.getUserName(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        String token = jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void Authenticate(String userName, String password) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        UserDto newUser = usersService.registerNewUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
