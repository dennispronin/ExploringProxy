package com.github.dennispronin.exploring.proxy.spring.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class UserServiceCachingAspect {

    private final Map<Long, String> cache;

    public UserServiceCachingAspect() {
        this.cache = new HashMap<>();
    }

    @Around("execution(String *(Long))" + "@annotation(com.github.dennispronin.exploring.proxy.spring.example.Cacheable)")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        Long argument = (Long) pjp.getArgs()[0];
        if (cache.containsKey(argument)) {
            return cache.get(argument);
        } else {
            String result = (String) pjp.proceed();
            cache.put(argument, result);
            return result;
        }
    }
}