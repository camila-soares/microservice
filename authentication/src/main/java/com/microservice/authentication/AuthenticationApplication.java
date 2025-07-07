package com.microservice.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthenticationApplication  {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);

    }

//    @Bean
//    CommandLineRunner init(UserRepository userRepository,
//                           BCryptPasswordEncoder encoder) {
//        return args -> {
//            initUser(userRepository, encoder);
//        };
//    }

//    private void initUser(UserRepository userRepository,
//                          BCryptPasswordEncoder encoder) {
//
//        User admin = new User();
//        admin.setFirstName("Camila");
//        admin.setLastName("Silva");
//        admin.setEmail("camilasoares1507@gmail.com");
//        admin.setUserRole(UserRole.ADMIN);
//        admin.setPassword(encoder.encode("123456"));
//
//
//        Optional<User> find = userRepository.findByEmail("camilasoares1507@gmail.com");
//        if (find == null) {
//            userRepository.save(admin);
//        }
//    }

}
