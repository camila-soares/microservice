package com.microservice.authentication.services;

import com.microservice.authentication.entity.User;
import com.microservice.authentication.repositories.UserRepository;
import com.microservice.commons.dtos.LoginDTO;
import com.microservice.commons.dtos.UserDTO;
import com.microservice.commons.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User authenticate(LoginDTO loginDTO) {

        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        boolean passwordMatch = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
        if (!passwordMatch) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for email: " + email));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getUserRole().toString())
                .build();
    }

    public User saveUser(UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .userRole(UserRole.CLIENTE)
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        userRepository.save(user);

        return user;

    }

    public void updateUser(Long id, UserDTO userDTO) {
       User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user = User.builder().email(userDTO.getEmail())
                        .firstName(userDTO.getFirstName())
                                .lastName(userDTO.getLastName()).build();
        userRepository.updateUser(id, user.getEmail(), user.getFirstName(), user.getLastName());
    }
}
