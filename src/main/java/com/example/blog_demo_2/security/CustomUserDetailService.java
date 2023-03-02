package com.example.blog_demo_2.security;

import com.example.blog_demo_2.entity.User;
import com.example.blog_demo_2.exceptions.ResourceNotFoundException;
import com.example.blog_demo_2.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("user", "email", username));
        return user;
    }


}
