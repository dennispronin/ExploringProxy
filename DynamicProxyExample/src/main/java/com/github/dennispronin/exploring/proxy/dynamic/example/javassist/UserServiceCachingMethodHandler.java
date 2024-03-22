package com.github.dennispronin.exploring.proxy.dynamic.example.javassist;

import com.github.dennispronin.exploring.proxy.common.UserService;
import javassist.util.proxy.MethodHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class UserServiceCachingMethodHandler implements MethodHandler {

    private final Map<Long, String> cache;

    public UserServiceCachingMethodHandler() {
        this.cache = new HashMap<>();
    }

    @Override
    public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws InvocationTargetException, IllegalAccessException {
        if (!(self instanceof UserService)) {
            throw new IllegalArgumentException("Proxy of wrong type passed. Expected type: " + UserService.class);
        }
        Long userId = (Long) args[0];
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        } else {
            String user = (String) proceed.invoke(self, args);
            cache.put(userId, user);
            return user;
        }
    }
}