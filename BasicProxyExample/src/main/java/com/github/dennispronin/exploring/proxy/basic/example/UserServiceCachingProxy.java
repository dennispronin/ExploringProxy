package com.github.dennispronin.exploring.proxy.basic.example;

import com.github.dennispronin.exploring.proxy.common.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceCachingProxy implements UserService {

    private final UserService delegate;
    private final Map<Long, String> cache;

    public UserServiceCachingProxy(UserService userService) {
        this.delegate = userService;
        this.cache = new HashMap<>();
    }

    @Override
    public String findUserById(Long userId) {
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        } else {
            String user = delegate.findUserById(userId);
            cache.put(userId, user);
            return user;
        }
    }
}