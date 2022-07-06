package com.woowoow.mayrose.repository;

import com.woowoow.mayrose.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest4 {
    @Autowired
    private UserRepository userRepository;

    @Test
    void listenerTest() {
        User user = new User();
        user.setName("Kim, Younggun");
        user.setEmail("woowoow@gmail.com");
        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("KKKKK, YYYYYOOOOUUUUNNNGGGUUUUUNNN");
        userRepository.save(user2);

        userRepository.deleteById(4L);
    }

    // 일반적으로 이렇게 하지만.... 만약 개발자가 Updated Date 를 놓치면....
    @Test
    void prePersistTest() {
        User user = new User();
        user.setName("Kim, Younggun");
        user.setEmail("woowoow@gmail.com");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        System.out.println(userRepository.findByEmail("woowoow@gmail.com"));
    }

    @Test
    void prePersistTest2() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("as-is : " + user);

        user.setName("martin222222");
        userRepository.save(user);

        System.out.println(userRepository.findAll().get(0));
    }

    @Test
    void uerHistoryTest() {
        User user = new User();
        user.setName("martin-new");
        user.setEmail("martin-new@gmail.com");
        userRepository.save(user);

        user.setName("martin-changed-name");
        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);


    }
}