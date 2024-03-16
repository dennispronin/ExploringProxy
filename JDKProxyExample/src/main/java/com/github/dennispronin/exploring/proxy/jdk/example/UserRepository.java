package com.github.dennispronin.exploring.proxy.jdk.example;

import java.util.Map;

public class UserRepository {

    private final Map<Long, String> namesDatabase;

    public UserRepository() {
        this.namesDatabase = Map.of(1L, "Anna", 2L, "Toby", 3L, "Denis");
    }

    public String findUserById(Long userId) {
        try {
            // namesDatabase is taking long to respond
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return namesDatabase.get(userId);
    }
}