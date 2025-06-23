package com.microservice.authentication;

import com.microservice.authentication.entity.Permission;
import com.microservice.authentication.entity.User;
import com.microservice.authentication.repositories.PermissionRepository;
import com.microservice.authentication.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthenticationApplication  {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);

    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository,
                           BCryptPasswordEncoder encoder) {
        return args -> {
            initUser(userRepository, permissionRepository, encoder);
        };
    }

    private void initUser(UserRepository userRepository,
                          PermissionRepository permissionRepository, BCryptPasswordEncoder encoder) {
        Permission permission = null;
        Permission findPermission = permissionRepository.findByDescription("Admin");
        if (findPermission == null) {
            permission = new Permission();
            permission.setDescription("Admin");
            permission = permissionRepository.save(permission);
        } else {
            permission = findPermission;
        }

        User admin = new User();
        admin.setUsername("camila");
        admin.setAccountNonExpired(true);
        admin.setAccountNonLocked(true);
        admin.setCredentialsNonExpired(true);
        admin.setEnable(true);
        admin.setPassword(encoder.encode("123456"));
        admin.setPermissions(Arrays.asList(permission));

        User find = userRepository.findByUsername("camila");
        if (find == null) {
            userRepository.save(admin);
        }
    }

}
