package com.github.dennispronin.exploring.proxy.common;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String findUserById(Long userId) {
        return userRepository.findUserById(userId);
    }
}