package io.mohkeita.userservice.service;

import io.mohkeita.userservice.dto.UserDto;
import io.mohkeita.userservice.entity.User;
import io.mohkeita.userservice.mapper.UserMapper;
import io.mohkeita.userservice.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {
        log.info("Creating User Request: {}", userDto);
        User savedUser = userRepository.save(UserMapper.INSTANCE.mapUserDtoToUser(userDto));

        log.info("Created User with Status CREATED");
        return UserMapper.INSTANCE.mapUserToUserDto(savedUser);
    }

    public ResponseEntity<UserDto> findUserById(Integer id) {
        log.info("Get User details for User Id : {}", id);
        Optional<User> fetchUser = userRepository.findById(id);

        if (fetchUser.isPresent()) {
            log.info("Fetch User details with Status OK");
            return new ResponseEntity<>(UserMapper.INSTANCE.
                    mapUserToUserDto(fetchUser.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
