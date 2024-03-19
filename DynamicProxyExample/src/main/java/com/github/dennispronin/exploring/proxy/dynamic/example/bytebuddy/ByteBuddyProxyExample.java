package com.github.dennispronin.exploring.proxy.dynamic.example.bytebuddy;

import com.github.dennispronin.exploring.proxy.dynamic.example.TestUserServiceUtil;
import com.github.dennispronin.exploring.proxy.dynamic.example.UserRepository;
import com.github.dennispronin.exploring.proxy.dynamic.example.UserService;
import com.github.dennispronin.exploring.proxy.dynamic.example.UserServiceImpl;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;

import java.lang.reflect.InvocationTargetException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ByteBuddyProxyExample {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        UserService userService = createUserService();
        TestUserServiceUtil.test(userService);
    }

    public static UserService createUserService() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return new ByteBuddy()
                .subclass(UserServiceImpl.class)
                .method(named("findUserById")).intercept(MethodDelegation.to(new UserServiceCachingInterceptor()))
                .make()
                .load(UserServiceImpl.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor(UserRepository.class)
                .newInstance(new UserRepository());
    }
}