package com.woowoow.mayrose.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
//@Getter
//@Setter
//@ToString
@NoArgsConstructor          // JPA에서는 인자 없는 constructor가 반드시 필요함.
@AllArgsConstructor
@RequiredArgsConstructor
//@EqualsAndHashCode          // JPA에서 많이 쓸 일은 없지만, 권고하고 있음.  Delombok 해서 생성되는 코드를  확인해 보자.
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getter and Setter 만들기
    // 1. 직접 코딩하기.
    // 2. IntelliJ에서 Generate 하기
    // 3. Lombok Anotation 붙이기.  ( 각각의 method에도 적용 가능, class Level에도 적용 가능 )
    //    @Getter, @Setter
    //    @Data
    //    Refactor --> Lombok, Delombok
    // 4. @ToString   --> 직접 toString() override 하게 되면 field추가 될 때마다 직접 toString() 함수를 수정해야만 할 것이야.
    // 5. 생성자 관련 Lombok Annotation
    //    NoArgsConstructor,
    //    AllArgsConstructor,
    //    RequiredArgsConstructor (@NonNull 이 있는 Field 대상)
    // 6. 전부 합쳐
    //    @Data =  @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
    // 7. @Builder
    //    빌드 패턴 지원함.

    // 8. Entity  ( DB로 저장할 수 있는 class 선언,  @Id가 필요함.  @GeneratedValue
}
