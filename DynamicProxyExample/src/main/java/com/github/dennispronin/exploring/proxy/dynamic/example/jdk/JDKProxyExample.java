package com.github.dennispronin.exploring.proxy.dynamic.example.jdk;

import com.github.dennispronin.exploring.proxy.dynamic.example.TestUserServiceUtil;
import com.github.dennispronin.exploring.proxy.dynamic.example.UserRepository;
import com.github.dennispronin.exploring.proxy.dynamic.example.UserService;
import com.github.dennispronin.exploring.proxy.dynamic.example.UserServiceImpl;

import java.lang.reflect.Proxy;

public class JDKProxyExample {

    public static void main(String[] args) {
        UserService userService = createUserService();
        TestUserServiceUtil.test(userService);
    }

    private static UserService createUserService() {
        UserService userService = new UserServiceImpl(new UserRepository());
        UserServiceCachingInvocationHandler handler = new UserServiceCachingInvocationHandler(userService);
        return (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),
                new Class[]{UserService.class},
                handler);
    }
}