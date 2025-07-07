package com.microservice.authentication.repositories;

import com.microservice.authentication.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Modifying
    @Query(value = "update User u set u.email = :email, u.firstName = :firstName," +
            "u.lastName = :lastName where u.id = :id")
   void updateUser(@Param("id") Long id,
                   @Param("email") String email,
                   @Param("firstName") String firstName, @Param("lastName") String lastName);

    //Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

//    Optional<User> findByUsername( String email );

    //@Query("SELECT u FROM User u WHERE u.username =: username")
    //User findByUsername( @Param( "username" ) String username);
}
