package com.github.dennispronin.exploring.proxy.common;

public class TestUserServiceUtil {

    public static void test(UserService proxyService) {
        UserService realUserService = new UserServiceImpl(null);

        String realServiceClassLoader = realUserService.getClass().getClassLoader().toString();
        String proxyServiceClassLoader = proxyService.getClass().getClassLoader().toString();
        if (realServiceClassLoader.equals(proxyServiceClassLoader)) {
            System.out.println("Real service and proxy have same classloader:\n");
        } else {
            System.out.println("Real service and proxy have different classloader:\n");
        }
        System.out.println(realServiceClassLoader);
        System.out.println(proxyServiceClassLoader);

        String realServiceClass = realUserService.getClass().toString();
        String proxyServiceClass = proxyService.getClass().toString();
        if (realServiceClass.equals(proxyServiceClass)) {
            System.out.println("\nReal service and proxy have same class:\n");
        } else {
            System.out.println("\nReal service and proxy have different class:\n");
        }
        System.out.println(realServiceClass);
        System.out.println(proxyServiceClass);

        long timeStampBeforeCache = System.currentTimeMillis();
        proxyService.findUserById(1L);
        System.out.println("\nExecution time before cache: " + (System.currentTimeMillis() - timeStampBeforeCache));

        long secondTimeStampBeforeCache = System.currentTimeMillis();
        proxyService.findUserById(1L);
        System.out.println("Execution time after cache: " + (System.currentTimeMillis() - secondTimeStampBeforeCache));
    }
}