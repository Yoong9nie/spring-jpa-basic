package com.woowoow.mayrose.repository;

import com.woowoow.mayrose.domain.Gender;
import com.woowoow.mayrose.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestExecutionListeners;

import java.time.LocalDateTime;

@SpringBootTest
class UserRepositoryTest3 {

    @Autowired
    private UserRepository userRepository;

    @Test
    void select(){
        // 두개가 검색되어서 에러가 남.
        // System.out.println(userRepository.findByName("martin"));
        // System.out.println(userRepository.findByName("dennis"));
        // System.out.println(userRepository.findByName("dennis"));

        System.out.println("findByEmail : " + userRepository.findByEmail("dennis@fastcampus.com"));
        System.out.println("getByEmail : " + userRepository.getByEmail("dennis@fastcampus.com"));
        System.out.println("readByEmail : " + userRepository.readByEmail("dennis@fastcampus.com"));
        System.out.println("queryByEmail : " + userRepository.queryByEmail("dennis@fastcampus.com"));
        System.out.println("searchByEmail : " + userRepository.searchByEmail("dennis@fastcampus.com"));
        System.out.println("streamByEmail : " + userRepository.streamByEmail("dennis@fastcampus.com"));
        System.out.println("findUserByEmail : " + userRepository.findUserByEmail("dennis@fastcampus.com"));
        System.out.println("findSomethingByEmail : " + userRepository.findSomethingByEmail("dennis@fastcampus.com"));
        // System.out.println("findByByEmail : " + userRepository.findByByEmail("dennis@fastcampus.com"));
    }

    @Test
    void first_Last() {
        System.out.println("findFirst1ByName : " + userRepository.findFirst1ByName("martin"));
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));

        System.out.println("findFirst2ByName : " + userRepository.findFirst2ByName("martin"));
        System.out.println("findTop2ByName : " + userRepository.findTop2ByName("martin"));

        // System.out.println("findLast1ByName : " + userRepository.findLast1ByName("martin"));
    }

    @Test
    void AndOr() {
        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@fastcampus.com", "martin" ));
        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName( "martin@fastcampus.com", "martin"));
    }

    @Test
    void afterBefore() {
        // -1 Days 부터 After된 데이타.
        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));
    }

    @Test
    void GreaterThan() {
        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
    }

    @Test
    void between() {
        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(3L, 4L));

        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(3L, 4L));
    }

    @Test
    void null_empty_check() {
        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
        System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());
    }

    @Test
    void in_check() {
        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin", "denis")));
    }

    @Test
    void starting_ending_contains_test() {
        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("mar"));
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("tin"));
        System.out.println("findByNameContains : " + userRepository.findByNameContains("arti"));
        System.out.println("findByNameLike : " + userRepository.findByNameLike("%arti%"));
    }

    @Test
    void is_check() {
        System.out.println("findUserByNameIs : " + userRepository.findUserByNameIs("martin"));
        System.out.println("findUserByName : " + userRepository.findUserByName("martin"));
        System.out.println("findUserByNameEquals : " + userRepository.findUserByNameEquals("martin"));
    }

    @Test
    void top_first_check() {
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
        System.out.println("findLast1ByName : " + userRepository.findLast1ByName("martin"));
    }

    @Test
    void orderby_check() {
        System.out.println("findTop1ByNameOrderByIdDesc : " + userRepository.findTop1ByNameOrderByIdDesc("martin"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("martin"));
    }

    @Test
    void sort_check() {
        System.out.println("findFirstByName : " + userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"))));
        System.out.println("findFirstByNameWithSortParams : " + userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
        System.out.println("findFirstByNameWithSortParams : " + userRepository.findFirstByName("martin", getSort()));
    }


    private Sort getSort() {
        return Sort.by(
                Sort.Order.desc("id"),
                Sort.Order.asc("email"),
                Sort.Order.desc("createdAt"),
                Sort.Order.asc("updatedAt")
        );
    }


    @Test
    void paging_test() {
        System.out.println("findByNameWithPaging : " + userRepository.findByName("martin", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getContent());
        System.out.println("findByNameWithPaging : " + userRepository.findByName("martin", PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")))).getTotalElements());
    }

    @Test
    void insertAndUpdateTest() {
        User user = new User();
        user.setName("martin");
        user.setEmail("martin@fastcampus.com");
        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("maaaaaaaaaaaatin");
        userRepository.save(user2);
    }

    @Test
    void enum_test() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);
        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);
        System.out.println(userRepository.findRawRecord().get("gender"));
    }
}