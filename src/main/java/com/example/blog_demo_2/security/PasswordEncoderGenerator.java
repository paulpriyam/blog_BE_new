package com.example.blog_demo_2.security;

import com.example.blog_demo_2.entity.Role;
import com.example.blog_demo_2.repository.RoleRepo;
import com.example.blog_demo_2.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class PasswordEncoderGenerator implements CommandLineRunner {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(PasswordEncoderGenerator.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Role role = new Role();
            role.setId(AppConstants.ROLE_ADMIN);
            role.setName("ROLE_ADMIN");

            Role role1 = new Role();
            role1.setId(AppConstants.ROLE_NORMAL);
            role1.setName("ROLE_NORMAL");
            List<Role> roles = List.of(role, role1);
            List<Role> createdRoles = roleRepo.saveAll(roles);
            createdRoles.forEach(role2 -> {
                System.out.println(role2.getName());
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.print(this.passwordEncoder.encode("1234"));
    }
}
