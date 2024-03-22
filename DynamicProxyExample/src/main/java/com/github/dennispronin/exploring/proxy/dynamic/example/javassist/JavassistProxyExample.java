package com.github.dennispronin.exploring.proxy.dynamic.example.javassist;

import com.github.dennispronin.exploring.proxy.common.TestUserServiceUtil;
import com.github.dennispronin.exploring.proxy.common.UserRepository;
import com.github.dennispronin.exploring.proxy.common.UserService;
import com.github.dennispronin.exploring.proxy.common.UserServiceImpl;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.InvocationTargetException;

public class JavassistProxyExample {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        UserService userService = createUserService();
        TestUserServiceUtil.test(userService);
    }

    private static UserService createUserService() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(UserServiceImpl.class);
        Class<?> clazz = factory.createClass();
        Object instance = clazz.getDeclaredConstructor(UserRepository.class).newInstance(new UserRepository());
        ((Proxy) instance).setHandler(new UserServiceCachingMethodHandler());
        return (UserService) instance;
    }
}