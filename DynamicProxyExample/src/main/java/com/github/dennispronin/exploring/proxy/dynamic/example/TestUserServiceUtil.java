package com.github.dennispronin.exploring.proxy.dynamic.example;

public class TestUserServiceUtil {

    public static void test(UserService userService) {
        UserService realUserService = new UserServiceImpl(null);
        System.out.println("Real object and proxy have same classloader:\n");
        System.out.println(realUserService.getClass().getClassLoader());
        System.out.println(userService.getClass().getClassLoader());
        System.out.println("\nBut different class:");
        System.out.println(realUserService.getClass());
        System.out.println(userService.getClass());

        long timeStampBeforeCache = System.currentTimeMillis();
        String nameBeforeCache = userService.findUserById(1L);
        System.out.println("\nExecution time before cache: " + (System.currentTimeMillis() - timeStampBeforeCache));

        long secondTimeStampBeforeCache = System.currentTimeMillis();
        String nameAfterCache = userService.findUserById(1L);
        System.out.println("Execution time after cache: " + (System.currentTimeMillis() - secondTimeStampBeforeCache));

        assert nameBeforeCache.equals(nameAfterCache);
    }
}