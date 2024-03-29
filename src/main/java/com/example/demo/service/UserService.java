package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @Transactional
    public List<UserDto> findAll() {
        return userMapper.to(userRepository.findAll());
    }

    @Transactional
    public void save(UserDto userDto) {
        User user = userMapper.to(userDto);
        user.getBucket().setUser(user);
        userRepository.save(user);
    }

    @Transactional
    public UserDto findById(Integer id) {
        User user = userRepository.findById(id).
                orElseThrow(EntityNotFoundException::new);
        return userMapper.to(user);
    }

    @Transactional
    public User fetch(Integer id) {
        return userRepository.getReferenceById(id);
    }

    @Transactional
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateById(UserDto userDto, Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userMapper.update(userDto, user);
    }
}
