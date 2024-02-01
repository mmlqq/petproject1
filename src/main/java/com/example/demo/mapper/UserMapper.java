package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User to(UserDto source);

    UserDto to(User source);

    List<UserDto> to(List<User> source);

    @Mapping(target = "id", ignore = true)
    void update(UserDto userDto, @MappingTarget User user);

}
