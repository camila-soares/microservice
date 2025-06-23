package com.microservice.authentication.services;

import com.microservice.authentication.dtos.UserDTO;
import com.microservice.authentication.entity.User;
import com.microservice.authentication.mapper.UserMapper;
import com.microservice.authentication.repositories.UserRepository;
import com.microservice.authentication.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User authenticate(UserDTO userDTO) {

        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found for email: " + userDTO.getUsername());
        }

        boolean passwordMatch = passwordEncoder.matches(userDTO.getPassword(), user.getPassword());
        if (!passwordMatch) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Usuário" + username + " não encontrado!");
        }
    }

    public User saveUser(UserDTO userDTO) {
        User user;
        user = userRepository.save(userMapper.toUser(userDTO));
        return user;
    }
}
