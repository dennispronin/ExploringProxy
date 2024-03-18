package com.github.dennispronin.exploring.proxy.dynamic.example.jdk;

import com.github.dennispronin.exploring.proxy.dynamic.example.UserRepository;
import com.github.dennispronin.exploring.proxy.dynamic.example.UserService;
import com.github.dennispronin.exploring.proxy.dynamic.example.UserServiceImpl;

import java.lang.reflect.Proxy;

public class JDKProxyExample {

    public static void main(String[] args) {
        UserService userService = createUserService();

        UserService realUserService = new UserServiceImpl(null);
        System.out.println("Real object and proxy have same classloader:\n");
        System.out.println(realUserService.getClass().getClassLoader());
        System.out.println(userService.getClass().getClassLoader());
        System.out.println("\nBut different class:");
        System.out.println(realUserService.getClass());
        System.out.println(userService.getClass());

        long timeStampBefore = System.currentTimeMillis();
        userService.findUserById(1L);
        System.out.println("\nExecution time before cache: " + (System.currentTimeMillis() - timeStampBefore));

        long secondTimeStampBefore = System.currentTimeMillis();
        userService.findUserById(1L);
        System.out.println("Execution time after cache: " + (System.currentTimeMillis() - secondTimeStampBefore));
    }

    public static UserService createUserService() {
        UserService userService = new UserServiceImpl(new UserRepository());
        UserServiceCachingInvocationHandler handler = new UserServiceCachingInvocationHandler(userService);
        return (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),
                new Class[]{UserService.class},
                handler);
    }
}