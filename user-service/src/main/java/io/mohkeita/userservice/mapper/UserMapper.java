package io.mohkeita.userservice.mapper;

import io.mohkeita.userservice.dto.UserDto;
import io.mohkeita.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserDtoToUser(UserDto userDto);
    UserDto mapUserToUserDto(User user);
}
