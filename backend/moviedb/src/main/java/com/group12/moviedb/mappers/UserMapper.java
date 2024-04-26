package com.group12.moviedb.mappers;
 
import com.group12.moviedb.dataSources.SignUpDto;
import com.group12.moviedb.dataSources.UserDto;
import com.group12.moviedb.models.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)


    User signUpToUser(SignUpDto signUpDto);

}