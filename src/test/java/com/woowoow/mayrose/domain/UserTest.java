package com.woowoow.mayrose.domain;

import org.junit.jupiter.api.Test;

import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void test1() {
        User user = new User();
        user.setEmail("woowoow@gmail.com");
        user.setName("woowoow");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        System.out.println(">>> " + user);
    }

    /*
    @Test
    void test2() {
        User user = new User("martin", "woowoow@gmail.com", LocalDateTime.now(), LocalDateTime.now());
        System.out.println(">>> " + user);
    }
    */
    @Test
    void test3() {
        User user = User.builder()
                .name("martin")
                .email("martin@gmail.com")
                .build();
        System.out.println(">>> " + user);
    }

}