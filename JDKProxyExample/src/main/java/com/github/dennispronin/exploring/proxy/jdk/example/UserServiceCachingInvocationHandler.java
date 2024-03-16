package com.github.dennispronin.exploring.proxy.jdk.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class UserServiceCachingInvocationHandler implements InvocationHandler {

    private final Map<Long, String> cache;
    private final UserService delegate;

    public UserServiceCachingInvocationHandler(UserService delegate) {
        this.delegate = delegate;
        this.cache = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (!(proxy instanceof UserService)) {
            throw new IllegalArgumentException("Proxy of wrong type passed. Expected type: " + delegate.getClass());
        }
        Long userId = (Long) args[0];
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        } else {
            String user = delegate.findUserById(userId);
            cache.put(userId, user);
            return user;
        }
    }
}