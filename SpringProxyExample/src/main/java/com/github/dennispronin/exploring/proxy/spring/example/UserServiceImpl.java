package com.github.dennispronin.exploring.proxy.spring.example;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable
    @Override
    public String findUserById(Long userId) {
        return userRepository.findUserById(userId);
    }
}