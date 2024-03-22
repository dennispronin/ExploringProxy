package com.github.dennispronin.exploring.proxy.spring.example;

import com.github.dennispronin.exploring.proxy.common.UserRepository;
import com.github.dennispronin.exploring.proxy.common.UserService;
import com.github.dennispronin.exploring.proxy.common.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }
}