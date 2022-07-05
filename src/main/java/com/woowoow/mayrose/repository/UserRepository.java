package com.woowoow.mayrose.repository;

import com.woowoow.mayrose.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    // User findByName(String name);
    // List<User> findByName(String name);
    // Optional<User> findByName(String name);

    Set<User> findByName(String name);
    User findByEmail(String email);
    User getByEmail(String email);
    User readByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);
    User findUserByEmail(String email);
    User findSomethingByEmail(String email);
    // User findByByEmail(String email);

    List<User> findFirst1ByName(String name);
    List<User> findTop1ByName(String name);

    List<User> findFirst2ByName(String name);
    List<User> findTop2ByName(String name);

    // Last 라는 것은 없다... Sorting 해서 First1 으로 하면 됨.
    List<User> findLast1ByName(String name);


    // And  Or
    List<User> findByEmailAndName(String email, String name) ;
    List<User> findByEmailOrName(String email, String name) ;

    // After Before
    List<User> findByCreatedAtAfter(LocalDateTime yesterday);
    List<User> findByIdAfter(Long id);

    // GreaterThan
    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);
    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    // Between
    List<User> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);
    List<User> findByIdBetween(Long id1, Long id2);
    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);

    // Null, isNull
    List<User> findByIdIsNotNull();

    // IsEmpty / IsNotEmpty can only be used on collection properties!
    // name is not null and name != ''
    List<User> findByAddressIsNotEmpty();

    // True, false

    // In, Not In
    List<User> findByNameIn(List<String> names);

    // contains, endsWith, startWith
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);
    List<User> findByNameLike(String name);

    // Is, Equals
    // List<User> findByName(String name);
    List<User> findUserByNameIs(String name);
    List<User> findUserByName(String name);
    List<User> findUserByNameEquals(String name);

    // 순서를 역으로 뒤집어서 하나만 가져오기.  Last1 과 같은 의미.
    List<User> findTop1ByNameOrderByIdDesc(String name);
    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);

    List<User> findFirstByName(String name, Sort sort);


    // Page 처리
    Page<User> findByName(String name, Pageable pageable);

    @Query(value = "select * from user limit 1;", nativeQuery=true)
    Map<String, Object> findRawRecord();
}
