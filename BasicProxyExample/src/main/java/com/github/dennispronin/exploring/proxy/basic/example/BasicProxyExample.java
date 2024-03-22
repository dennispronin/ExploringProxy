package com.github.dennispronin.exploring.proxy.basic.example;

import com.github.dennispronin.exploring.proxy.common.TestUserServiceUtil;
import com.github.dennispronin.exploring.proxy.common.UserRepository;
import com.github.dennispronin.exploring.proxy.common.UserService;
import com.github.dennispronin.exploring.proxy.common.UserServiceImpl;

public class BasicProxyExample {

    public static void main(String[] args) {
        UserService userService = createUserService();
        TestUserServiceUtil.test(userService);
    }

    public static UserService createUserService() {
        return new UserServiceCachingProxy(new UserServiceImpl(new UserRepository()));
    }
}