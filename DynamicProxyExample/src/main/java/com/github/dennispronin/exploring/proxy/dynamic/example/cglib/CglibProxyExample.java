package com.github.dennispronin.exploring.proxy.dynamic.example.cglib;

import com.github.dennispronin.exploring.proxy.common.TestUserServiceUtil;
import com.github.dennispronin.exploring.proxy.common.UserRepository;
import com.github.dennispronin.exploring.proxy.common.UserService;
import com.github.dennispronin.exploring.proxy.common.UserServiceImpl;
import net.sf.cglib.proxy.Enhancer;

public class CglibProxyExample {

    public static void main(String[] args) {
        UserService userService = createUserService();
        TestUserServiceUtil.test(userService);
    }

    private static UserService createUserService() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new UserServiceCachingMethodInterceptor());
        return (UserServiceImpl) enhancer.create(new Class[]{UserRepository.class}, new Object[]{new UserRepository()});
    }
}