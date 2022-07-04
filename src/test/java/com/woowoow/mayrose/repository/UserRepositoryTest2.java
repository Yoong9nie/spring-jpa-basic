package com.woowoow.mayrose.repository;

import com.woowoow.mayrose.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest2 {
    @Autowired
    private UserRepository userRepository;

    @Test
    void insertOrupdate() {
        // 이거는 insert
        userRepository.save(new User("david", "david@woowoow.com"));

        // 이거는 update..  ID : 1L 은 martin.....
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-update@fastcampus.com");
        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);
    }


    @Test
    void delete_Or_deleteInBatch() {

        // deleteAll()           -->  select 먼저 수행해서 삭제할 대상 물색
        // deleteAllInBatch()    -->  select 없이 그냥 지움.

        userRepository.findAll().forEach(System.out::println);
    }


}