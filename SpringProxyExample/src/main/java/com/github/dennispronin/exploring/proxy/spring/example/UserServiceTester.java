package com.github.dennispronin.exploring.proxy.spring.example;

import com.github.dennispronin.exploring.proxy.common.TestUserServiceUtil;
import com.github.dennispronin.exploring.proxy.common.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserServiceTester {

    private final UserService userService;

    public UserServiceTester(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        TestUserServiceUtil.test(userService);
    }
}