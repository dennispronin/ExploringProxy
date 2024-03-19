package com.github.dennispronin.exploring.proxy.dynamic.example.bytebuddy;

import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class UserServiceCachingInterceptor {

    private final Map<Long, String> cache;

    public UserServiceCachingInterceptor() {
        this.cache = new HashMap<>();
    }

    public String intercept(Long userId, @SuperCall Callable<String> zuper) throws Exception {
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        } else {
            String user = zuper.call();
            cache.put(userId, user);
            return user;
        }
    }
}