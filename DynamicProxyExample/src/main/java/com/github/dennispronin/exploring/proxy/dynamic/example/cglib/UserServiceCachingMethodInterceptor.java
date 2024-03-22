package com.github.dennispronin.exploring.proxy.dynamic.example.cglib;

import com.github.dennispronin.exploring.proxy.common.UserService;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class UserServiceCachingMethodInterceptor implements MethodInterceptor {

    private final Map<Long, String> cache;

    public UserServiceCachingMethodInterceptor() {
        this.cache = new HashMap<>();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (!(obj instanceof UserService)) {
            throw new IllegalArgumentException("Proxy of wrong type passed. Expected type: " + UserService.class);
        }
        Long userId = (Long) args[0];
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        } else {
            String user = (String) proxy.invokeSuper(obj, args);
            cache.put(userId, user);
            return user;
        }
    }
}