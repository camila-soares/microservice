package com.microservice.authentication.mapper;

import com.microservice.authentication.dtos.UserDTO;
import com.microservice.authentication.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User toUser(UserDTO userDTO);


    UserDTO toUserDTO(User user);
}