package com.woowoow.mayrose.repository;

import com.woowoow.mayrose.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() {
        userRepository.save(new User());

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("===================================================================================");
//        System.out.println(">>> " + userRepository.findAll());
//        userRepository.findAll().forEach(System.out::println);
        for(User user : userRepository.findAll()) {
            System.out.println(user);
        }
    }


    @Test
    void findAll() {
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }

    @Test
    void findAllOrder() {
        // 이름으로 내림차순.
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        users.forEach(System.out::println);
    }

    @Test
    void findAllById() {
        // ID 검색.
        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));
        users.forEach(System.out::println);
    }

    @Test
    void SaveOne() {
        User user1 = new User("jack", "jack@fastcampus.com");

        userRepository.save(user1);
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }


    @Test
    void SaveAll() {
        User user1 = new User("jack", "jack@fastcampus.com");
        User user2 = new User("steve", "steve@fastcampus.com");

        userRepository.saveAll(Lists.newArrayList(user1, user2));
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }


    @Test
    @Transactional   // getOne() 은 lazyFetch를 지원하므로 @Transactional 이 없으면 no Session Error가 발생함.
    void getOne() {
        User user = userRepository.getOne(1L);
        System.out.println(user);
    }

    @Test
    void findById() {
        User user = userRepository.findById(1L).orElse(null);
        System.out.println(user);
    }

    @Test
    void saveAndflush() {
        userRepository.save(new User("new martin", "new-martin@gmail.com"));
        userRepository.flush();
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void count() {
        long count = userRepository.count();
        System.out.println("Data Count : " + count);
    }


    @Test
    void existById() {
        boolean exists = userRepository.existsById(1L);
        System.out.println("1L Exists : " + exists);
    }


    // delete는 조회를 한 번 하고 나서 delete 수행함.
    @Test
    void delete() {
        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
        userRepository.findAll().forEach(System.out::println);
    }

    // delete는 조회를 한 번 하고 나서 delete 수행함.
    @Test
    void deleteById() {
        userRepository.deleteById(1L);
        userRepository.findAll().forEach(System.out::println);
    }


    // delete는 조회를 한 번 하고 나서 delete 수행함.
    @Test
    void deleteAll() {
        userRepository.deleteAll();
        userRepository.findAll().forEach(System.out::println);
    }


    // delete는 조회를 한 번 하고 나서 delete 수행함.
    // deleteAll( id lists )  --> in 구문으로 적용됨.
    @Test
    void deleteAll_InId() {
        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L)));
        userRepository.findAll().forEach(System.out::println);
    }

    //  select 실행하지 않고 delete 쿼리를 바로 실행함.
    @Test
    void deleteAllInBatch() {
        userRepository.deleteAllInBatch();
        userRepository.findAll().forEach(System.out::println);
    }

    //  select 실행하지 않고 delete 쿼리를 바로 실행함...  이 예제에서는 findAllById 때문에 select 하기는 함.
    @Test
    void deleteInBatch() {
        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L)));
        userRepository.findAll().forEach(System.out::println);
    }

    // 페이지....
    @Test
    void page1() {
        // 한 페이지당 3개씩 가져오고 그 첫번째 페이지를 가져와라... 전체 페이지는 2,
        Page<User> users = userRepository.findAll(PageRequest.of(1, 3));
        System.out.println("page : " + users);                              // Page 2 of 2 containing com.woowoow.mayrose.domain.User instances
        System.out.println("totalElements : " +  users.getTotalElements());
        System.out.println("totalPages : " + users.getTotalPages());
        System.out.println("numberOfElements : " + users.getNumberOfElements());
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize());

        users.getContent().forEach(System.out::println);
    }

    
    // QBE  (QueryBeExample)
    // where  ( user0_.email like ? escape ? )  like 검색하는 구문.
    @Test
    void qbe() {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")                            // name은 매칭하지 않음.
                .withMatcher("email", endsWith()) ;      // like 검색 조건 지정 endsWith()

        Example<User> example = Example.of(new User("ma", "fastcampus.com"), matcher);
        userRepository.findAll(example).forEach(System.out::println);
    }

    // where   user0_.email=?  and user0_.name=?
    @Test
    void qbe_Exact_Match() {
        Example<User> example = Example.of(new User("martin", "martin@fastcampus.com"));
        userRepository.findAll(example).forEach(System.out::println);
    }

    // 양방향 like 검색
    @Test
    void qbe_another_example() {
        User user = new  User();
        user.setEmail("slow");

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());       // contains 양방향 like 검색
        Example<User> example = Example.of(user, matcher);
        userRepository.findAll(example).forEach(System.out::println);
    }


}